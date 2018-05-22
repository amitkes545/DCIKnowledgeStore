package com.kds.KODE_DEV.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.SessionDomain;

public class SessionBackUpDao  {
	//DBTransaction dbT = new DBTransaction();
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs;
      SessionDomain al= new  SessionDomain();
	public String getStudentId(String sessionid) {
      String status = null;
		try {
		 con = DBTransaction.connect();
			
			String sql = "select recipient_type from session_details where session_id='"+sessionid+"'";
			 ps = con.prepareStatement(sql);
             rs = ps.executeQuery();
			//System.out.println("sql query is:"+sql);

			while(rs.next()){
				status=rs.getString("recipient_type");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   con.close();
				   ps.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return status;
	}
	public String getResultId(String studentid) {
	      String status = null;
			try {
			 con = DBTransaction.connect();
				
				String sql = "select log_status from tracking_table where student_id='"+studentid+"'";
				 ps = con.prepareStatement(sql);
	             rs = ps.executeQuery();
				//System.out.println("sql query is:"+sql);

				while(rs.next()){
					status=rs.getString("log_status");
				}
					
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally
			   {
				   try{
					   con.close();
					   ps.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
			return status;
		}
	public String insertSessionAccessValues(SessionDomain sdomain)
	{
		
        String status = null;
		try {

			 con = DBTransaction.connect();
			String quary="insert into session_access(session_id,user_id,access) values(?,?,?)";
					
			 ps = con.prepareStatement(quary);
			
			 ps.setString(1,sdomain.getSessionId());
				ps.setString(2,sdomain.getFacultyId());
				ps.setString(3,sdomain.getStatus());
			
			//System.out.println("the query is:"+ps);
			int n = ps.executeUpdate();
			//System.out.println("query is inserted successfully");
			if (n != 0)
				status = "valid";
			else
				status = "notvalid";
		}catch(Exception e){
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   con.close();
				   ps.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return status;
		
	}
	public SessionDomain getDateTime(String studentid) {
		SessionDomain sdomain=new SessionDomain();
			try {
			 con = DBTransaction.connect();
				
				String sql = "select date_time from tracking_table where student_id='"+studentid+"'"+"and log_status=in"+"order by date limit 1";
				 ps = con.prepareStatement(sql);
	             rs = ps.executeQuery();
				//System.out.println("sql query is:"+sql);

				while(rs.next()){
					sdomain.setDate(rs.getString("date_time"));
					//sdomain.setTime(rs.getString("time"));
				}
					
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally
			   {
				   try{
					   con.close();
					   ps.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
			return sdomain;
		}
	public ArrayList<String> getAllStudentID(String orgid,String userid) {
	     ArrayList<String> al=new ArrayList<String>();
			try {
			 con = DBTransaction.connect();
				
				String sql = "select user_id from users_info where created_by='"+userid+"'";
				 ps = con.prepareStatement(sql);
	             rs = ps.executeQuery();
				//System.out.println("sql query is:"+sql);

				while(rs.next()){
					al.add(rs.getString("user_id"));
				}
					
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally
			   {
				   try{
					   con.close();
					   ps.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
			return al;
		}
}
