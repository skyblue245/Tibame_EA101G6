package com.websocket;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

import connectionpool.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisService {
	private static JedisPool pool = JedisUtil.getJedisPool();
	Gson gson = new Gson();
	
	public Set<String> getUnDone(){
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		Set<String> unDoneSet = jedis.smembers("unDone");
		
		jedis.close();
		return unDoneSet;
	}
	
	public void setUnDone(String sender,String seName){
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		
		ChatMessage unDone=new ChatMessage();
		unDone.setSender(sender);
		unDone.setSeName(seName);
		unDone.setType("unDone");
		String unDoneStr = gson.toJson(unDone);
		jedis.sadd("unDone",unDoneStr);
		
		jedis.close();
	}
	
	public void deleteUnDone(ChatMessage unDone){
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		
		String unDoneStr = gson.toJson(unDone);
		System.out.println(unDoneStr);
		jedis.srem("unDone", unDoneStr);
		
		jedis.close();
	}
	
	public void setMessage(ChatMessage chatMessage,String message){
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		
		jedis.rpush(chatMessage.getSender()+":"+chatMessage.getReceiver(),message);
		jedis.rpush(chatMessage.getReceiver()+":"+chatMessage.getSender(),message);
		
		jedis.close();
	} 
	
	public List<String> getHistoryList(ChatMessage chatMessage) {
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		
		List<String> historyList = jedis.lrange(chatMessage.getSender()+":"+chatMessage.getReceiver(),0,-1);
		
		jedis.close();
		
		return historyList;
	}
	
	public List<String> getHistoryList(String account) {
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		
		List<String> historyList = jedis.lrange(account+":LE00001",0,-1);
		
		jedis.close();
		
		return historyList;
	}
	
	
}
