	package com.kds.KODE_DEV.services;

	  import org.apache.cordova.api.CordovaPlugin;
	  import android.content.Context;
	  import android.telephony.TelephonyManager;
	 
	  
	public class findimei extends CordovaPlugin 
	{
		        TelephonyManager telephonyManager = (TelephonyManager)this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
		        String result = telephonyManager.getLine1Number();
		
		       // System.out.println("result="+result);
		}
	