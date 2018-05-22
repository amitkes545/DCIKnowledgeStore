package com.kes.kote.util;

import java.util.Random;

public class GenerateRandomPwd {

	static String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
	
	public static String getRandomPwd()
	{
		String ret="";
		try
		{
			StringBuilder salt = new StringBuilder();
		    Random rnd = new Random();
		    while (salt.length() < 8) { // length of the random string.
		        int index = (int) (rnd.nextFloat() * SALTCHARS.length());
		        salt.append(SALTCHARS.charAt(index));
		    }
		    ret = salt.toString();
		    	
		}catch(Exception ex){ex.printStackTrace();}
		
	    return ret;
	}
    
}
