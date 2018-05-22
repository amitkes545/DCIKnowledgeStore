package com.kds.KODE_DEV.services;
import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.services.CommonService;
import com.kds.KODE_DEV.dbconnection.DBTransaction;



public class ClassRoomIn extends CommonService{
	// DBTransaction dbtranobj=new DBTransaction();
	Connection con = null;
	//ResultSet rs = null;
	public void run() throws ServletException, IOException {
		
		String id=request.getParameter("userid");
		 String privilege=request.getParameter("privilege");
		 String sesid=request.getParameter("sesid");
		// System.out.println("sesid="+sesid);
		 //System.out.println("privilege="+privilege);
		 
		PreparedStatement ps=null;
		String status = null;
		 java.util.Date date= new java.util.Date();
		// System.out.println("crm"+new Timestamp(date.getTime()));
		 try{
			 
			 
			
			// Class.forName("org.postgresql.Driver");
			 con = DBTransaction.connect();
			 java.sql.Statement st= con.createStatement();
			
			 String sql="insert into classroom_Tracking(student_id,date_time,log_status,privilege,session_id) values('"+id+"','"+new Timestamp(date.getTime()).toString()+"','in','"+privilege+"','"+sesid+"')";
			
			 ps=con.prepareStatement(sql);
			 int i=ps.executeUpdate();
				if(i==1){
					status="success";
				}else {
					
					status="failure";
				}
				 response.sendRedirect("../JSP/All-in-one.jsp?sesid="+sesid);
				/*RequestDispatcher rd=request.getRequestDispatcher("../JSP/All-in-one.jsp");
		 		   rd.forward(request, response);*/
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
