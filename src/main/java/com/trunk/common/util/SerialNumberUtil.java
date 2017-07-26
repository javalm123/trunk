package com.trunk.common.util;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 获得序列号
 * @author 周颖
 */
public class SerialNumberUtil {

	/**
	 * 获取当前时间毫秒数+4位随机数
	 * 
	 * @return
	 */
	public static String getSerialNumber() {
		String sd = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		long num=Math.round(Math.random()*9999);
		return sd+num+"";
	}
	public static void main(String[] args) {
		System.out.println(getSerialNumber());
	}
}
