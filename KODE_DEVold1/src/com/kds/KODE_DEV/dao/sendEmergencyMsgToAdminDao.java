package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;

public class sendEmergencyMsgToAdminDao {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public String getAdminUserId(String userId,String orgId)
	{
		
	String adminUserId=null;
	try {
		con = DBTransaction.connect();
	
		String quary = "select distinct admin_user_id from admin_faculty_mapping where organization_id='"+orgId+"' and faculty_user_id='"+userId+"'";
		pstmt = con.prepareStatement(quary);
		System.out.println("pstmt="+pstmt);
		rs = pstmt.executeQuery();
		if(rs.next()){
			adminUserId=rs.getString("admin_user_id");	
			
			
		}
		}catch(Exception e){
		e.printStackTrace();
	}
	finally
	   {
		   try{
			   con.close();
			   pstmt.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	return adminUserId;
}

	
	public String getMobileNo(String userId)
	{
		
	String mobileNo=null;
	try {
		con = DBTransaction.connect();
	
		String quary = "select contact_no from users_info where user_id='"+userId+"'";
		pstmt = con.prepareStatement(quary);
		rs = pstmt.executeQuery();
		if(rs.next()){
			mobileNo=rs.getString("contact_no");	
			
			
		}
		}catch(Exception e){
		e.printStackTrace();
	}
	finally
	   {
		   try{
			   con.close();
			   pstmt.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	return mobileNo;
}

	public String getUserName(String userId)
	{
		
	String userName=null;
	try {
		con = DBTransaction.connect();
	
		String quary = "select username from users_info where user_id='"+userId+"'";
		pstmt = con.prepareStatement(quary);
		rs = pstmt.executeQuery();
		if(rs.next()){
			userName=rs.getString("username");	
			
			
		}
		}catch(Exception e){
		e.printStackTrace();
	}
	finally
	   {
		   try{
			   con.close();
			   pstmt.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	return userName;
}
	
	public String getEmailId(String userId)
	{
		
	String emailId=null;
	try {
		con = DBTransaction.connect();
	
		String quary = "select email from users_info where user_id='"+userId+"'";
		pstmt = con.prepareStatement(quary);
		rs = pstmt.executeQuery();
		if(rs.next()){
			emailId=rs.getString("email");	
			
			
		}
		}catch(Exception e){
		e.printStackTrace();
	}
	finally
	   {
		   try{
			   con.close();
			   pstmt.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	return emailId;
}
	
	
	public String getSenderEmail()
	{
		
	String senderEmailId=null;
	try {
		con = DBTransaction.connect();
	
		String quary = "select emailid from senderemaildetails";
		pstmt = con.prepareStatement(quary);
		rs = pstmt.executeQuery();
		if(rs.next()){
			senderEmailId=rs.getString("emailid");	
			
			
		}
		}catch(Exception e){
		e.printStackTrace();
	}
	finally
	   {
		   try{
			   con.close();
			   pstmt.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	return senderEmailId;
}
	
	public int sendNotificationData(String orgId,String adminUserId,String recipentEmailId,String senderEmailId,String userName)
	{
		
	int status=0;
	String queryForInsertBreakNotification = "INSERT INTO public.notification_mail_store("
			+ "  organization_id, sender_mail_id, recipient_mail_id,"
			+ " mail_subject, mail_body, response_status, created_datetime, read_datetime,"
			+ " responded_datetime, response_mail_refno, sender_uid, sender_name, recipient_uid, recipient_name,"
			+ " mail_category, mail_status)" + " VALUES ( '" + orgId + "','" + recipentEmailId
			+ "','" + senderEmailId + "'," + " 'Emergency break request','" + adminUserId + " ("
			+ userName + ") has requested for Emergency break','Notification', now(),null,"
			+ " null,null,'" + adminUserId + " ','" + userName + "', '"
			+ adminUserId + "', '" + userName+ "', null,'Sent')";
try{
	con = DBTransaction.connect();
pstmt=con.prepareStatement(queryForInsertBreakNotification);
System.out.println("queryForInsertBreakNotification="+queryForInsertBreakNotification);
status=pstmt.executeUpdate();
/*while(rs.next())
{
	status=1;
	break;

}*/

	}catch(Exception e){
		e.printStackTrace();
	}
	finally
	   {
		   try{
			   con.close();
			   pstmt.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
System.out.println("status="+status);
return status;
	}
	
}