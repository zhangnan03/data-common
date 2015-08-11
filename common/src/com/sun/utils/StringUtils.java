package com.sun.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author fangguanhong
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

	/**
	 * 把一个字符串中连续的几个空格替换成一个@，字符串前后各添一个@
	 * 
	 * @param str
	 * @return
	 */
	public static String getString(String str) {
		String newBlankList = "";
		try {
			String[] black = str.split(" ");
			for (int i = 0; i < black.length; i++) {
				String subString = black[i];
				if (subString.equals(" ") || subString.equals("")) {
					continue;
				}
				newBlankList += "@" + subString.trim();
			}
			newBlankList += "@";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newBlankList;
	}

	public static String encode(String s) {
		String str = null;
		if (!StringUtils.isEmpty(s)) {
			try {
				str = URLEncoder.encode(s, "utf-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return str;
	}

	public static String encode(String s, String code) {
		String str = null;
		if (!StringUtils.isEmpty(s)) {
			try {
				str = URLEncoder.encode(s, code);
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return str;
	}

	/**
	 * @param obj
	 * @return
	 */
	public static String dealNull(Object obj) {
		String str = "";
		if (obj != null) {
			if (obj instanceof String) {
				str = (String) obj;
			} else {
				str = obj.toString();
			}
		}
		return str;
	}

	/**
	 * equals
	 * 
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static boolean equals(Object value1, Object value2) {
		boolean is = false;
		if (value1 == value2) { // is null or self
			return is = true;
		}
		if (value1 != null && value2 != null) { // is not null;
			return value1.equals(value2);
		}
		return is;
	}

	/**
	 * 将一个字符串转为合法的sql语句要求的表示方法�?主要是对单引号的处理
	 * 
	 * @param str
	 * @return 处理后的字符
	 */
	public static String formatSQLString(String str) {
		if (str == null) {
			return null;
		}
		return str.replace("'", "''");
	}

	public static String decode(String s) {
		String str = null;
		if (!StringUtils.isEmpty(s)) {
			try {
				str = URLDecoder.decode(s, "utf-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return str;
	}

	public static boolean isEmpty(String str) {
		return str == null || "".equals(str);
	}

	// 数组转字符串
	public static String arrayToString(String[] arr) {
		StringBuffer bf = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			bf.append(arr[i]);
		}
		return bf.toString();
	}

	public static final String randomString(int length) {
		Random randGen = null;
		char[] numbersAndLetters = null;
		if (length < 1) {
			return null;
		}
		if (randGen == null) {
			randGen = new Random();
			numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz").toCharArray();
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(35)];
		}
		return new String(randBuffer);
	}
	/**
	 *@param str
	 *@return
	 *@Description:验证str是否为数字形式 包括小数
	 *@Author:wangshupeng#zplay.cn
	 *@Since:2015年3月11日  下午6:34:47
	 *@Version:1.0
	 */
	public static Boolean validNumber(String str){
		Pattern pattern = Pattern.compile("^[0-9]+(\\.[0-9]+)?$");
		Matcher matchAmount = pattern.matcher(str);
		return matchAmount.matches();
	}
	/**
	 *@param object
	 *@return
	 *@Description: 判断是否为空
	 *@Author:lizhen#zplay.cn
	 *@Since:2015年5月5日  下午6:42:19
	 *@Version:1.0
	 */
	public static String isNull(Object object){
		if(object == null){
			return "";
		}	
			return object.toString();
	}
	/**
	 *@param i1
	 *@param i2
	 *@return
	 *@Description: 判断是否为0
	 *@Author:lizhen#zplay.cn
	 *@Since:2015年5月6日  下午2:28:18
	 *@Version:1.0
	 */
	public static String isZero(Integer i1,Integer i2){
		if(i2 == 0 || i1 ==0){
			return "0";
		}
		return String.format("%.2f", (double)i1/i2*100);
	}
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println("{\"errcode\":40003,\"errmsg\":\"invalid openid\"}".indexOf("40001"));
	}
}
