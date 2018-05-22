package com.kds.KODE_DEV.util;

public class Utilities {
	
	
	public static String getBytes(int size,String spaceUom)
	{
		String st="";
		long allLibSize=0l;
		long sizeLong=size;
		if("TB".equals(spaceUom))
		{
			allLibSize = 109951162777600l*sizeLong;
			return ""+allLibSize;
		}
		if("GB".equals(spaceUom))
		{
			allLibSize = 1073741824*sizeLong;
			return ""+allLibSize;
		}
		if("MB".equals(spaceUom))
		{
			allLibSize = 1048576*sizeLong;
			return ""+allLibSize;
		}
		if("KB".equals(spaceUom))
		{
			allLibSize = 1024*sizeLong;
			return ""+allLibSize;
		}
		
		return st;
	}
	
	public static String convert(Long a)
	{
		if(a==null || a<=0)
			return "0";
		String st="";
		if(a>=109951162777600l)
		{
			long tb=a/109951162777600l;
			a %= 109951162777600l;
			st+=tb+" TB ";
		}
	
		if(a>=1073741824)
		{
			long gb=a/1073741824;
			a %= 1073741824;
			st+=gb+" GB ";
			
		}
		
		if(a>=1048576)
		{
			long mb=a/1048576;
			a %= 1048576;
			st+=mb+" MB ";
			
		}
		if(a>=1024)
		{
			long kb=a/1024;
			a %= 1048576;
			st+=kb+" KB ";
		}
		
		return st;
	}

}
