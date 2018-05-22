package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;


import com.kds.KODE_DEV.dbconnection.DBTransaction;

public class RemoveUsersFromSession extends CommonService {
	
	private static final long serialVersionUID = 1L;
	Connection con = null;

public void run() throws ServletException, IOException {
	session=request.getSession();
		String reason=request.getParameter("reason");
		String userid=request.getParameter("userid");
		String sessionId=request.getParameter("sessionid");
		String facultyUserId=(String)session.getAttribute("userId");
			 System.out.println("reason="+reason);
			 System.out.println("userid="+userid);
			 System.out.println("sessionId="+sessionId);
		PreparedStatement ps=null;
		String status = null;
		java.util.Date utilDate = new Date();
		System.out.println("utilDate="+utilDate);
		java.sql.Date date = new java.sql.Date(utilDate.getTime());
		System.out.println("date :"+date);
	
		 try{
			
			
			 con = DBTransaction.connect();
			 java.sql.Statement st= con.createStatement();
			 String sql="update users_info set users_status=? where user_id=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, "InActive");
				ps.setString(2, userid);
				int retValue=ps.executeUpdate();
				System.out.println("retValue="+retValue);
				
		
				
				if(retValue==1)
				{
					retValue=0;
					String sql1="insert into user_status_log(user_id,user_status,status_change_datetime,change_reason,changed_by_userid)values(?,?,?,?,?)";
					ps = con.prepareStatement(sql1);
					ps.setString(1,userid);
					ps.setString(2,"InActive");
					ps.setTimestamp(3,new Timestamp(System.currentTimeMillis()));
					ps.setString(4,reason );
					ps.setString(5,facultyUserId );
					
					retValue=ps.executeUpdate();
				}
				
				if(retValue==1){
				status="success";
			}else {
				status="failure";
			}
			
				if ("success".equals(status)) {
					String msg = "User blocked";
					request.setAttribute("success", msg);
						
					  RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/All-in-one.jsp?sesid="+sessionId);
			  			requestDispatcher.forward(request, response);
			  			
				}
				if ("failure".equals(status)) {

					String msg = "Failure to blocked user";
					request.setAttribute("failure", msg);
					 RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/All-in-one.jsp?sesid="+sessionId);
			  			requestDispatcher.forward(request, response);
			  		
						}

				
				
					
				
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 finally
		 {
		 	DBTransaction.closeConnection(con);
		 }
	}
}