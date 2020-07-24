package com.websocket;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import connectionpool.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;



@ServerEndpoint("/MyWebSocket/{account}")
public class MyWebSocket {
	// concurrent包的執行緒安全Map，用來存放每個客戶端對應的MyWebSocket物件。
	private static Map<String, Session> empSessionsMap = new ConcurrentHashMap<>();
	private static Map<String, Session> mbrSessionsMap = new ConcurrentHashMap<>();
	String empReg="^(LE){1}\\d{5}$";
	String mbrReg="^(BM){1}\\d{5}$";
	Gson gson = new Gson();
	RedisService redisSvc=new RedisService();
	/*
	 * 如果想取得HttpSession與ServletContext必須實作
	 * ServerEndpointConfig.Configurator.modifyHandshake()，
	 * 參考https://stackoverflow.com/questions/21888425/accessing-servletcontext-and-httpsession-in-onmessage-of-a-jsr-356-serverendpoint
	 */
	
	@OnOpen
	public void onOpen(@PathParam("account") String account, Session userSession) throws IOException {
		//String historyOwn=
		
		if(account.matches(mbrReg)) {
			mbrSessionsMap.put(account,userSession);
			List<String> list = redisSvc.getHistoryList(account);
			String historyMsg = gson.toJson(list);
			userSession.getAsyncRemote().sendText(historyMsg);
		}else if(account.matches(empReg)) {
			empSessionsMap.put(account,userSession);
			Set<String> set=redisSvc.getUnDone();
			String unDone=gson.toJson(set);
			userSession.getAsyncRemote().sendText(unDone);
		}
		
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
		
		if(chatMessage.getSender().matches(empReg)) {
			
			if("history".equals(chatMessage.getType())) {
				List<String> historyList = redisSvc.getHistoryList(chatMessage);
				String historyMsg = gson.toJson(historyList);
				empSessionsMap.get(chatMessage.getSender()).getAsyncRemote().sendText(historyMsg);
				return;
			}else {
				redisSvc.setMessage(chatMessage, message);
				if(mbrSessionsMap.get(chatMessage.getReceiver())!=null) {
					mbrSessionsMap.get(chatMessage.getReceiver()).getAsyncRemote().sendText(message);
				}
				return;
			}
	
		}
		//完成後
		if(chatMessage.getType()!=null&&"unDone".equals(chatMessage.getType())) {
			redisSvc.deleteUnDone(chatMessage);
			return;
		}
		
		
		if(chatMessage.getSender().matches(mbrReg)) {
			redisSvc.setUnDone(chatMessage.getSender(), chatMessage.getSeName());
			redisSvc.setMessage(chatMessage, message);
			empSessionsMap.get(chatMessage.getReceiver()).getAsyncRemote().sendText(message);
			return;
		}
	
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String userNameClose = null;
		if(empSessionsMap.containsValue(userSession)){
			Set<String> userNames = empSessionsMap.keySet();
			for (String userName : userNames) {
				if (empSessionsMap.get(userName)!=null && empSessionsMap.get(userName).equals(userSession)) {
					userNameClose = userName;
					empSessionsMap.remove(userName);
					break;
				}
			}
				
		}else if(mbrSessionsMap.containsValue(userSession)){
			Set<String> userNames = empSessionsMap.keySet();
			for (String userName : userNames) {
				if (mbrSessionsMap.get(userName)!=null && mbrSessionsMap.get(userName).equals(userSession)) {
					userNameClose = userName;
					mbrSessionsMap.remove(userName);
					break;
				}
			}
		}
		
		try {
			userSession.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
	}
	
	
	

}
