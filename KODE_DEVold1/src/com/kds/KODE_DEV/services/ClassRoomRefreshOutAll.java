package com.kds.KODE_DEV.services;
import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dbconnection.DBTransaction;



public class ClassRoomRefreshOutAll extends CommonService{
	
	 //DBTransaction dbtranobj=new DBTransaction();
	Connection con = null;
	//ResultSet rs = null;
	public void run() throws ServletException, IOException {
		// System.out.println("id refresh start=");
		//session=request.getSession();
		String id=null;//request.getParameter("userid");
		 String privilege=null;//request.getParameter("privilege");
		 String sesid=request.getParameter("session_id");
		 
		 //System.out.println("id refresh="+sesid);
		 //System.out.println("privilege="+privilege);
		//String sesid= (String)session.getAttribute("SessionID");
		PreparedStatement ps=null,pstmt=null;
		String status = null;
		 ResultSet rs=null;
		 java.util.Date date= new java.util.Date();
		 //System.out.println("crm"+new Timestamp(date.getTime()));
		 try{
			 
			 
			
			// Class.forName("org.postgresql.Driver");
			 con = DBTransaction.connect();
			 java.sql.Statement st= con.createStatement();
			// java.sql.Connection con = 
			 //DriverManager.getConnection("jdbc:postgresql://localhost:5432/KODE_DEV", "postgres","kompac_security");
			// DriverManager.getConnection("jdbc:postgresql://122.165.130.69:5432/KODE_DEV", "postgres","kompac_security");
			 //java.sql.Statement st= con.createStatement();
		//st.executeUpdate("insert into Tracking_table(student_id,date_time,log_status,privilege) values('"+id+"','"+new Timestamp(date.getTime()).toString()+"','in')");
			//System.out.println(new Timestamp(date.getTime()).toString());
		//System.out.println("in sselect");
			 
			/* pstmt=con.prepareStatement("select * from classroom_tracking join (select student_id,max(date_time) max_dtm from classroom_tracking where student_id = '"+id+"' group by student_id) latest "
					+ "	 on classroom_tracking.student_id = latest.student_id and classroom_tracking.date_time = latest.max_dtm "
					+ "where log_status = 'in'");
			 */

		pstmt=con.prepareStatement("select * from classroom_tracking join (select student_id,max(date_time) max_dtm from classroom_tracking group by student_id) latest "
				+ "on classroom_tracking.student_id = latest.student_id and classroom_tracking.date_time = latest.max_dtm "
				+ "where log_status = 'in' and classroom_tracking.student_id in (select user_id from users_info u join session_details s on u.organization_id = s.organization_id "
				+ "and (u.user_id = s.faculty_name or (lower(u.privilege) = 'student' and (u.user_id = s.recipient_type or s.recipient_type = 'all' or position(u.user_id||'#' in s.recipient_type ) > 0)))   where session_id='"+sesid+"')");
			 
		rs=pstmt.executeQuery();
		System.out.println(pstmt);
				while(rs.next())
				{
					id=rs.getString("student_id");
					privilege=rs.getString("privilege");
			 String sql="insert into classroom_Tracking(student_id,date_time,log_status,privilege,session_id) values('"+id+"','"+new Timestamp(date.getTime()).toString()+"','out','"+privilege+"','"+sesid+"')";
			 //System.out.println("out sql="+sql);
			 ps=con.prepareStatement(sql);
			 int i=ps.executeUpdate();
				/*if(i==1){
					status="success";
				}else {
					status="failure";
				}*/
				 //response.sendRedirect("../JSP/logout_knowstore.jsp?sesid="+sesid);
				
				//session.setAttribute("logoutID", id);
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
