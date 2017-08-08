package com.durian.user.utils.sms;

import com.durian.user.utils.net.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;


/**
 * 发送短信验证码
 */
public class SmsUtil {
	
	
	private static String smsUrl = "http://sms.253.com/msg/send";
	private static String account = "N5305049";
	private static String pswd = "WhcHEKe37F6231";

	public static String sendMessage(String mobile,String content){
		Map<String, String> map = new HashMap<String, String>();
		//CryptoUtils.encryptParams(map, paramMap);
		map.put("un",account);
		map.put("pw", pswd);
		map.put("phone",mobile);
		map.put("msg", content);
		map.put("needstatus", Boolean.TRUE.toString());
		map.put("rd", "");
		map.put("ex", "");
		String result = HttpClientUtil.httpPost(smsUrl, map);
		return result ;
	}


	public static void main(String[] args) {
		String result = SmsUtil.sendMessage("18627038327","testing");
		System.out.print(result);
	}

}
