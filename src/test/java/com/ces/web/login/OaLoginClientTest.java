package com.ces.web.login;

import com.ws.util.HttpClientTool;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;


public class OaLoginClientTest {
	
	private static String baseApp = "http://172.16.66.62/";
	
	private static String loginUrl = baseApp + "/pages/OaLogin/loginAuthen.do";
	
	private static String validateTokenUrl = baseApp + "pages/OaLogin/validateToken.do";
	
	private static String printTemesUrl = baseApp + "pages/OaLogin/printTemes.do";
	
	private static String signStatusUrl = baseApp + "pages/MobileService/noSignList.do";
	
	
	
	public void login() {//获取空气基本数据
//		System.setProperty("http.proxyHost", "localhost"); 
//        System.setProperty("http.proxyPort", "8888"); 
//        System.setProperty("https.proxyHost", "localhost"); 
//        System.setProperty("https.proxyPort", "8888"); 

		
		List<NameValuePair> params = new ArrayList<NameValuePair>();		
		params.add(new BasicNameValuePair("username", "chejincheng"));  
		params.add(new BasicNameValuePair("password", "abc123"));
		String data = HttpClientTool.post(loginUrl,params);		
//		String data = HttpClientTool.get(loginUrl+"?username=ces123&password=000000");
		System.out.println(data);
	}
	
	public void validateToken() {//获取空气基本数据
		for (int i = 0; i < 1000; i++) {
			try {
				Thread.sleep(10000);
			} catch (Exception e) {
				System.exit(0);// 退出程序
			}
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("token", "CA789CC25266104B85B17A9DDF37FC9679"));
			String data = HttpClientTool.post(printTemesUrl, params);
			System.out.println("第【"+i*10+"】秒:" +data);

		}				
	}
	
	
	public void signStatus(){
		List<NameValuePair> params = new ArrayList<NameValuePair>();		
		params.add(new BasicNameValuePair("token", "CAEFB0C77D71B247509622E091C6C410EC"));  
		params.add(new BasicNameValuePair("modelType", "ZLQZ"));  
		params.add(new BasicNameValuePair("timeBegin", "2016-05-01"));  
		params.add(new BasicNameValuePair("timeEnd", "2016-05-31"));  
		String data = HttpClientTool.post(signStatusUrl,params);
		System.out.println(data);
	}
	

}
