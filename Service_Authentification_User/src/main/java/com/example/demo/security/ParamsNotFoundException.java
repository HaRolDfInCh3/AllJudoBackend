package com.example.demo.security;

public class ParamsNotFoundException extends Exception {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParamsNotFoundException(String s)
	    {
	        // Call constructor of parent Exception
	        super(s);
	    }
}
