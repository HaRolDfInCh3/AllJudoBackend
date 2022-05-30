package com.example.demo;



public class SharedLib {
	public static final String header="Authorization";
	public static final String secret="mon_secret_a_moi";
	public static final String prefix="Bearer ";
	public static final int prefixlength=prefix.length();
	public static final int expirationAccessToken=20*60*1000;
	public static final int expirationRefreshToken=90*60*1000;
	
	
}
