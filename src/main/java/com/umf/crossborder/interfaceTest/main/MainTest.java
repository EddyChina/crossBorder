package com.umf.crossborder.interfaceTest.main;

import com.google.gson.Gson;
import com.umf.crossborder.interfaceTest.utils.JsonPrintUtil;
import com.umf.crossborder.interfaceTest.utils.PrintUtil;
import com.umf.crossborder.interfaceTest.utils.TestUtil;

public class MainTest {
	
	public static String httpPost(Object obj,String merId,String uri) throws Exception{
		String urll = uri.substring(0, uri.indexOf("cbeweb_rest")+11);
		String port = uri.substring(uri.indexOf("cbeweb_rest")+11,uri.length());
		String json = new Gson().toJson(obj);
		PrintUtil.printInConsole("-------请求------------");
		JsonPrintUtil.formatJson(json);
		String result = TestUtil.sendMsg(merId, urll, port, json, "POST");
		PrintUtil.printInConsole("-------响应------------");
		return JsonPrintUtil.formatJson(result);
		
	}
	
	public static String httpGet(String merId,String uri) throws Exception{
		PrintUtil.printInConsole("-------请求------------");
		String urll = uri.substring(0, uri.indexOf("cbeweb_rest")+11);
		String port = uri.substring(uri.indexOf("cbeweb_rest")+11,uri.length());
		JsonPrintUtil.formatJson(uri);
		String result = TestUtil.sendMsg(merId, urll, port, null, "GET");
		PrintUtil.printInConsole("-------响应------------");
		return JsonPrintUtil.formatJson(result);
		
	}
	
}
