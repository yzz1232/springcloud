package com.yzz.util;

public class TestThreadLocal {
	
	static ThreadLocal<String> tt = new ThreadLocal<String>();
	
	public static void set(String str){
		tt.set(str);
	}
	
	public static String get(){
		return tt.get();
	} 
}
