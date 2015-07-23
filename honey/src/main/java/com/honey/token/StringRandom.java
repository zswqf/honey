package com.honey.token;

import java.util.Random;

public class StringRandom {
	private static String generated(int length)
	 {
	  String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	  Random  random = new Random();
	  StringBuffer buf = new StringBuffer();
	  for(int i = 0 ;i < length ; i ++)
	  {
	   int num = random.nextInt(62);
	   buf.append(str.charAt(num));
	  }
	  return buf.toString();
	 }
	
	public static String getRandomString(String key){
		return key+generated(10);
	}
}
