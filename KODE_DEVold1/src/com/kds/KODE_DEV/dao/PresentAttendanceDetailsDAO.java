package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AssessmentsDetailsDomain;
import com.kds.KODE_DEV.domain.CourseFacultyDomain;
import com.kds.KODE_DEV.domain.GetSessionForFacultyDomain;

public class PresentAttendanceDetailsDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt1 = null;
	ResultSet rs=null;
	String query=null;
	 int result2 =0;
	 int res2=0;
	 String enrId=null;
	 String sessionId=null;
	 String sessionStatTime=null;
	 String sessionEndTime=null;
	 
	 
	 GetSessionForFacultyDomain getSessionDomain =null;
	 public int savePresentStudentData(String  orgId,String userName,String se,String enr){
		 int result=0;
		 enrId=enr;
		 PresentAttendanceDetailsDAO pad=new PresentAttendanceDetailsDAO();
		 sessionId=se;
		 ArrayList<AssessmentsDetailsDomain> arl=new  ArrayList<AssessmentsDetailsDomain>();
		 GetSessionForFacultyDomain getSessionDomain = new GetSessionForFacultyDomain();
		 GetCourseForFacultyDao getCourseForFacultyDao = new GetCourseForFacultyDao();
		 List<CourseFacultyDomain> courses = getCourseForFacultyDao.getCourseForFacultyMarkingAttedance(userName,orgId);
			for (CourseFacultyDomain course1 : courses) {
				System.out.println("course :" + course1.getCourseId());
				String courseName = course1.getCourseName();
		 CourseFacultyDomain course = new CourseFacultyDomain();
		 CourseFacultyDomain cfd= new CourseFacultyDomain();
			 query="select session_start_time, session_end_time from session_details where organization_id = '"+orgId+"' and session_id = '"+se+"' and faculty_name = '"+userName+"' and category = '"+courseName+"'";
			try {
				
				con = DBTransaction.connect();	
				pstmt = con.prepareStatement(query);
				rs = pstmt.executeQuery();
				
				System.out.println( " query===>"+query);
				
				while (rs.next()) {
					
					
					sessionStatTime=rs.getString("session_start_time");
					sessionEndTime=rs.getString("session_end_time");
					
					System.out.println("start end time==>"+rs.getString("session_start_time")+"=="+rs.getString("session_end_time"));
					
					}	
				 result=PresentStudentDataInsert();
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
		
			}
		
		   
	   
			return result;
	
	
	
	 }
	 
	 //
	 
 public int PresentStudentDataInsert(){
		 
	 System.out.println("insert datamethod");
	 
	 System.out.println("enrid---"+enrId);
	 System.out.println("---time--"+sessionStatTime);
	 System.out.println("sesid--->"+sessionId);
		 
	  String inQuery="INSERT INTO public.classroom_tracking(student_id, date_time, log_status, privilege, session_id) VALUES ('"+enrId+"', '"+sessionStatTime+"', 'in',  'Student', '"+sessionId+"')";
		  String  outQuery="INSERT INTO public.classroom_tracking(student_id, date_time, log_status, privilege, session_id) VALUES ('"+enrId+"','"+sessionEndTime+"', 'out', 'Student', '"+sessionId+"')";
					 
			//System.out.println("query for out===>"+outQuery);
		  try {
				
				con = DBTransaction.connect();	
				pstmt = con.prepareStatement(inQuery);
				pstmt1 = con.prepareStatement(outQuery);
			    System.out.println("query for in===>"+inQuery);
				int rs1 = pstmt.executeUpdate();
				if(rs1!=0)
					
					res2=pstmt1.executeUpdate();
				    System.out.println("rs2--->"+res2);
				
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
			return res2;

	
	
	
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	

}
