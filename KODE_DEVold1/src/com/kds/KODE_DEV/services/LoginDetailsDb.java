package com.kds.KODE_DEV.services;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.SessionTrackingDomain;

public class LoginDetailsDb 
{
	java.sql.Connection con=null;
	public String getSessionName(String facultyId, String priv){
		String status="";
		ResultSet rs=null;
		 try{
			  con = DBTransaction.connect();
			 java.sql.Statement st= con.createStatement();
			 if(priv.equalsIgnoreCase("faculty")){
			 rs=st.executeQuery("SELECT * FROM session_details  WHERE (CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Calcutta' <= CAST(session_start_time AS TIMESTAMP )"
			 		+ " OR (CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Calcutta' BETWEEN CAST(session_start_time AS TIMESTAMP ) AND CAST(session_end_time AS TIMESTAMP))) and faculty_name='"+facultyId+"' order by session_start_time limit 1");
			 }else if(priv.equalsIgnoreCase("student")){
				 rs=st.executeQuery("SELECT * FROM session_details  WHERE (CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Calcutta' <= CAST(session_start_time AS TIMESTAMP )"
					+ " OR (CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Calcutta' BETWEEN CAST(session_start_time AS TIMESTAMP ) AND CAST(session_end_time AS TIMESTAMP))) and (position('"+facultyId+"#' in recipient_type) > 0 or recipient_type='all') order by session_start_time limit 1");
			 }
				 //query added by prity to display the latest session
			 
		while(rs.next()){
			status=rs.getString("session_name");
			}	
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
		 	DBTransaction.closeConnection(con);
		 }
		 return status;
	}
	
	public String getSessionID(String facultyId, String priv){
		String status="";
		ResultSet rs=null;
		 try{
			  con = DBTransaction.connect();
			 java.sql.Statement st= con.createStatement();
			 if(priv.equalsIgnoreCase("faculty")){
			 rs=st.executeQuery("SELECT * FROM session_details  WHERE (CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Calcutta' <= CAST(session_start_time AS TIMESTAMP )"
			 		+ " OR (CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Calcutta' BETWEEN CAST(session_start_time AS TIMESTAMP ) AND CAST(session_end_time AS TIMESTAMP))) and faculty_name='"+facultyId+"' order by session_start_time limit 1");
			 }else if(priv.equalsIgnoreCase("student")){
				 rs=st.executeQuery("SELECT * FROM session_details  WHERE organization_id=(select organization_id from users_info where user_id='"+facultyId+"') and (CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Calcutta' <= CAST(session_start_time AS TIMESTAMP )"
					+ " OR (CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Calcutta' BETWEEN CAST(session_start_time AS TIMESTAMP ) AND CAST(session_end_time AS TIMESTAMP))) and (position('"+facultyId+"#' in recipient_type) > 0 or recipient_type='all') order by session_start_time limit 1");
			 }
			 //query added by prity to display the latest session
			 while(rs.next()){
			status=rs.getString("session_id");
			}	
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
		 	DBTransaction.closeConnection(con);
		 }
		 return status;
	}
	
	public ArrayList<SessionTrackingDomain> getLoginNames(String facultyId, String priv){
		//String facultyId){
		 try{
			 ArrayList<SessionTrackingDomain> arl=new ArrayList<SessionTrackingDomain>();
			  con = DBTransaction.connect();
			 java.sql.Statement st= con.createStatement();
		//ResultSet rs=st.executeQuery("select * from tracking_table order by date_time desc limit 2");
		String SESSID=getSessionID(facultyId, priv);
		String query = "select t.* from tracking_table t join (select student_id,max(date_time) max_dtm from tracking_table "
				+ "group by student_id) latest on t.student_id = latest.student_id and t.date_time = latest.max_dtm where log_status='in' and t.student_id"
				+ " in (select user_id from users_info u join session_details s on u.organization_id = s.organization_id and (u.user_id = s.faculty_name or "
				+ "(lower(u.privilege) = 'student' and (u.user_id = s.recipient_type or s.recipient_type = 'all' or position(u.user_id||'#' in s.recipient_type ) > 0)))   where session_id='"+SESSID+"'  )";
//		System.out.println(query);
		ResultSet rs=st.executeQuery(query);
		
				while(rs.next()){
			SessionTrackingDomain std=new SessionTrackingDomain();
			std.setStudentId(rs.getString("student_id"));
			std.setLoginStatus(rs.getString("log_status"));
			std.setPrivilege(rs.getString("privilege"));
			//System.out.println(rs.getString("privilege"));
			//System.out.println(rs.getString("log_status"));
			arl.add(std);
			
			}	
		return arl;
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
		 	DBTransaction.closeConnection(con);
		 }
		return null;	
	}
public ArrayList<SessionTrackingDomain> getLoginNamesOld(){
	 try{
		 ArrayList<SessionTrackingDomain> arl=new ArrayList<SessionTrackingDomain>();
		// DBTransaction1 dbt = new DBTransaction1();
		  con = DBTransaction.connect();
		 java.sql.Statement st= con.createStatement();
	ResultSet rs=st.executeQuery("select * from login_details order by date_time desc limit 2");	
	while(rs.next()){
		SessionTrackingDomain std=new SessionTrackingDomain();
		std.setStudentId(rs.getString("user_id"));
		std.setLoginStatus(rs.getString("log_status"));
		std.setPrivilege(rs.getString("privilege"));
		//System.out.println(rs.getString("privilege"));
		//System.out.println(rs.getString("log_status"));
		arl.add(std);
		
		}	
	return arl;
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }
	 finally
	 {
	 	DBTransaction.closeConnection(con);
	 }
	return null;	
}

public String[]getStartTimeAndEndTime(String sesid)
{  String  time[]=new String[2]; 
int i=0;
try{
	con = DBTransaction.connect();  
	java.sql.Statement st= con.createStatement();
	String query="select session_start_time,session_end_time from session_details where session_id='"+sesid+"'";
	ResultSet rs=st.executeQuery(query);
	System.out.println("query="+rs);
	while(rs.next()){
 time[i++]=(rs.getString("session_start_time")); 
 time[i]=(rs.getString("session_end_time")); }  
	}catch(Exception e) 
{   e.printStackTrace();  }  
finally   { 
  DBTransaction.closeConnection(con);  
  }   
return time;
}
}
