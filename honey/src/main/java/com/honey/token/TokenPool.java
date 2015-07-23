package com.honey.token;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public  class TokenPool {
	/**
	 * userId:token
	 */
	private final static Map<String,String> USER_TOKEN_POOL = new HashMap<String,String>();
	/**
	 * token
	 */
	private final static Set<String> TOKEN_POOL = new HashSet<String>();
	/**
	 * 生产唯一token值
	 * @param userId
	 * @return
	 */
	public static String getToken(String key){
		if (USER_TOKEN_POOL.get(key) != null) {
			TOKEN_POOL.remove(USER_TOKEN_POOL.remove(key));
		}
		String token = StringRandom.getRandomString(key);
		USER_TOKEN_POOL.put(key, token);
		TOKEN_POOL.add(token);
		return token;
	}
	/**
	 * 判断token是否存在
	 * @param token
	 * @return
	 */
	public static boolean isExistingToken(String token){
		return TOKEN_POOL.contains(token);
	}
}
