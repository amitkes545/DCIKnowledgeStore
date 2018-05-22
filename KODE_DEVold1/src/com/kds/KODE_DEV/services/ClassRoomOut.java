package com.kds.KODE_DEV.services;
import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dbconnection.DBTransaction;



public class ClassRoomOut extends CommonService{
	// DBTransaction dbtranobj=new DBTransaction();
	Connection con = null;
	//ResultSet rs = null;
	public	String endSession(String id,String previlege){
		//session=request.getSession();
		//String id=request.getParameter("userid");
		 //String privilege=request.getParameter("privilege");
		 
		 //System.out.println("id="+id);
		 //System.out.println("privilege="+privilege);
		//String sesid= (String)session.getAttribute("SessionID");
		String sesid=request.getParameter("sesid");
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
			 
			 pstmt=con.prepareStatement("select * from classroom_tracking join (select student_id,max(date_time) max_dtm from classroom_tracking where student_id = '"+id+"' group by student_id) latest "
					+ "	 on classroom_tracking.student_id = latest.student_id and classroom_tracking.date_time = latest.max_dtm "
					+ "where log_status = 'in'");
			 System.out.println("out pstmt="+pstmt);
			  rs=pstmt.executeQuery();
				if(rs.next())
				{
					
			 String sql="insert into classroom_Tracking(student_id,date_time,log_status,privilege,session_id) values('"+id+"','"+new Timestamp(date.getTime()).toString()+"','out','"+previlege+"','"+sesid+"')";
			 System.out.println("out sql="+sql);
			 ps=con.prepareStatement(sql);
			 int i=ps.executeUpdate();
				if(i==1){
					status="success";
				}else {
					status="failure";
				}
				// response.sendRedirect("../JSP/All-in-one.jsp?sesid="+sesid);
				//session.setAttribute("logoutID", id);
				}
				return "success";
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 finally
		 {
		 	DBTransaction.closeConnection(con);
		 }
		return null;
	}
	@Override
	public void run() throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
}
