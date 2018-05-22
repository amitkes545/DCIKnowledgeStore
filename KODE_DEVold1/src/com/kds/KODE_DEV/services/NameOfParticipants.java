package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.SessionTrackingDomain;

public class NameOfParticipants extends CommonService 
{
	public void run() throws ServletException,IOException {
	
	
		//String facultyId){
		String SESSID=(String)request.getParameter("sesid");
		 ArrayList<SessionTrackingDomain> arl=new ArrayList<SessionTrackingDomain>();
		 try{
			
			 java.sql.Connection con = DBTransaction.connect();
			 java.sql.Statement st= con.createStatement();
		//ResultSet rs=st.executeQuery("select * from tracking_table order by date_time desc limit 2");
		//g SESSID=getSessionID(facultyId, priv);
		String query = "select t.* from tracking_table t join (select student_id,max(date_time) max_dtm from tracking_table where date(date_time) = current_date "
				+ "group by student_id) latest on t.student_id = latest.student_id and t.date_time = latest.max_dtm where log_status='in' and t.student_id"
				+ " in (select user_id from users_info u join session_details s on u.organization_id = s.organization_id and (u.user_id = s.faculty_name or "
				+ "(lower(u.privilege) = 'student' and (u.user_id = s.recipient_type or s.recipient_type = 'all' or position(u.user_id||'#' in s.recipient_type ) > 0)))   where session_id='"+SESSID+"'  )";
		System.out.println(query);
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
		//return arl;
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		//return null;	
		 request.setAttribute("allParticipantDetails",arl);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/All-in_one.jsp");
			requestDispatcher.forward(request, response);
	}

	}
