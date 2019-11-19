package com.rl.ecps.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;


public class ECPSUtils {
	//读取.properties文件
	public static String readProp(String key)  {
		//实现获取在classpath路径下的资源文件的输入流。
		InputStream inputStream=ECPSUtils.class.getClassLoader().getResourceAsStream("system.properties");
		Properties prop=new  Properties();
		try {
			prop.load(inputStream);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		
		return prop.getProperty(key);
	}
	
	
	public static void printJson(String result,HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
