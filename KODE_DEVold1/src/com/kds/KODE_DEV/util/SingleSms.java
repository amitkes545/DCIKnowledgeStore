
package com.kds.KODE_DEV.util;

import java.net.*;
import java.io.*;

public class SingleSms {

	public static String sendSMS(String message, String phone) {
		
		String line = null;
				String otp_job_id= null;
		
		try {
			
			String data = "";

			data += "username=" + URLEncoder.encode("kes2017", "ISO-8859-1");
			data += "&password=" + URLEncoder.encode("kes@2017", "ISO-8859-1");
			data += "&message="	+ URLEncoder.encode(message,"ISO-8859-1");
			data += "&mobile="+phone;
			data += "&sendername=KOMPAC";

			// Send data
			URL url = new URL("http://nettyhost.com/smsapi/TR/sendmsg.aspx?"
					+ data);
		
			System.out.println("URL" + url);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());
			wr.write(data);
			wr.flush();

			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			
			while ((line = rd.readLine()) != null) {
				// Print the response output...
			
				System.out.println("Line is:"+line);
				otp_job_id = line.substring(38);
			}
			  
				//System.out.println("OTP" +OTP);
			    System.out.println("random OTP with message" +message); // 1234
			    
			  
			System.out.println("otp_job_id" +otp_job_id);
			//System.out.println("session_id"+session_id);
			
			
			    wr.close();
				rd.close();
			  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return otp_job_id;
	}
}
