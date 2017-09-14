package com.durian.user.utils.code;

import java.util.Random;

/**
 *
 */
public class RandomValidateCode {

	private static String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";// 随机产生的字符串
	
	private static String randNumber = "0123456789";// 随机产生的字符串

	/*
	 * 获取随机的字符
	 */
	public static String getRandomString(int num) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++) {
			sb.append(randString.charAt(new Random().nextInt(randString.length())));
		}
		
		return sb.toString();
	}
	
	
	/*
	 * 获取随机的字符
	 */
	public static String getRandomNumber(int num) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++) {
			sb.append(randNumber.charAt(new Random().nextInt(randNumber.length())));
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		for (int i = 0;i<100;i++){
			System.out.println(getRandomNumber(4));
		}
	}

}
