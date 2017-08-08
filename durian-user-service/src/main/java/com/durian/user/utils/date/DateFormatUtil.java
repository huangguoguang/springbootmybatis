package com.durian.user.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

	public static String convertCurrentDate(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date());
	}
	
	public static String convertDate(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static Date convertDate(String time, String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(time);
	}

	public static void main(String[] args) throws ParseException {
		System.out.println(convertCurrentDate("yyyyMMddHHmmss"));
		System.out.println(convertCurrentDate("yyyy-MM-dd HH:mm:ss.SSS"));
		System.out.println(convertDate("20170705", "yyyyMMdd"));
	}
	
	

	
	/**
	 * 格式化日期,返回日期字符串		yyyy-MM-dd HH:mm:ss
	 * @param date				日期数据
	 * @param formatStr			格式化参数
	 * @return
	 */
	public static String format(Date date, String formatStr){
		if(formatStr == null || "".equals(formatStr)){
			formatStr = "yyyy-MM-dd";
		}
		SimpleDateFormat dataDateFormat = new SimpleDateFormat(formatStr);
		return dataDateFormat.format(date);
	}
	
	
	/**
	 * 将字符串转换成Date类型
	 * @param dateString
	 * @param dateFormat
	 * @return
	 */
	public static Date parse(String dateString, String dateFormatString) {
		if(dateFormatString == null || "".equals(dateFormatString)){
			dateFormatString = "yyyy-MM-dd";
		}
		SimpleDateFormat dFormat = new SimpleDateFormat(dateFormatString);
		try {
			return dFormat.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
