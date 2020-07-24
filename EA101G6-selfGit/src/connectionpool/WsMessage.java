package connectionpool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class WsMessage {

	private static JedisPool pool = JedisUtil.getJedisPool();
	MsgVO msgvo = new MsgVO();
	Gson gson = new Gson();

	public void saveMbrmsg(String Mbrno, String message) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.auth("123456");

			String strMsgVO = null;
			Integer index = null;
			String MbrnoKey = Mbrno + ":Msg";
			// 是空的話就從1開始
			if (jedis.llen(MbrnoKey) == 0) {
				msgvo.setIndex(1);
			} else {
				// 不是空的，取得最左邊的值
				strMsgVO = jedis.lindex(MbrnoKey, 0);
				MsgVO msgvoTemp = gson.fromJson(strMsgVO, MsgVO.class);
				index = msgvoTemp.getIndex();
				msgvo.setIndex(index + 1);// 最左邊的索引值+1
			}
			msgvo.setRead(0);// 設為未讀
			msgvo.setMessage(message);// 放入訊息
			String msgvoJson = gson.toJson(msgvo);// 物件轉Json
			System.out.println("WsMessage.saveMbrmsg: Mbrno:" + MbrnoKey + ", message:" + msgvoJson);
			jedis.lpush(MbrnoKey, msgvoJson);// 從最左邊放入(舊的在右邊)
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (jedis != null)
			jedis.close();
	}

	public void updateMbrmsg(String Mbrno, Integer index) {// 傳進索引值
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.auth("123456");

			String MbrnoKey = Mbrno + ":Msg";
			String mbrMsgJson = jedis.lindex(MbrnoKey, index);// 從索引值取出
			MsgVO msgvo = gson.fromJson(mbrMsgJson, MsgVO.class);
			msgvo.setRead(1);// 設為已讀
			String msgvoJson = gson.toJson(msgvo);
			jedis.lset(MbrnoKey, index, msgvoJson);// 存回原本的索引值
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		if (jedis != null)
			jedis.close();
	}

	public List<MsgVO> getRead0Mbrmsg(String Mbrno) {// 每次進頁面都要get，索引值才能保持最新
		Jedis jedis = null;
		List<MsgVO> msgvoList = null;
		try {
			jedis = pool.getResource();
			jedis.auth("123456");
			String MbrnoKey = Mbrno + ":Msg";
			List<String> list = jedis.lrange(MbrnoKey, 0, -1);
			msgvoList = new ArrayList<MsgVO>();
			for (String strMsgvo : list) {
				MsgVO msgvo = gson.fromJson(strMsgvo, MsgVO.class);
				if (msgvo.getRead() == 0)// 拿未讀的訊息
					msgvoList.add(msgvo);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		if (jedis != null)
			jedis.close();
		return msgvoList;
	}

	public boolean deleteMbrAllMsg(String Mbrno) {
		Boolean dataRemoved = null;
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.auth("123456");

			String MbrnoKey = Mbrno + ":Msg";
			if (jedis.del(MbrnoKey) >= 1) {
				dataRemoved = true;
			} else {
				dataRemoved = false;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (jedis != null)
			jedis.close();
		return dataRemoved;
	}
}
