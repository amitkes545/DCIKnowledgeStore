package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;
import com.kds.KODE_DEV.domain.RetriveImagesDomain;
import com.kds.KODE_DEV.services.ShareFileToFTPServlet;

public class ParticipantAssessmentsDao{
	Connection con = null;
//	PreparedStatement pstmt1 = null;
	static final Logger LOGGER = Logger.getLogger(ParticipantAssessmentsDao.class);
	   public ArrayList<RetriveImagesDomain> selectViewCourses(DisplayCoursesDomain ri){
			//DBTransaction1 dbt = new DBTransaction1();
		//	DBTransaction1 dbt1 = new DBTransaction1();
			 ArrayList<RetriveImagesDomain> arl=new  ArrayList<RetriveImagesDomain>();
			try {
				con = DBTransaction.connect();
				
				//PreparedStatement pstmt1 = null;
			/*	ResultSet rs1 = null;
				String quary1 = "select faculty_id from student_faculty_mapping where organization_id='"+ri.getCourseDetails()+"' and student_id='"+ri.getCourseName()+"'";
				System.out.println(quary1);
				pstmt1 = con.prepareStatement(quary1);
				rs1 = pstmt1.executeQuery();
				while(rs1.next())
				{*/	
				PreparedStatement pstmt = null;
				ResultSet rs = null;			
				String quary = "SELECT assessment_name,assessment_id,uploaded_by FROM assessment_info where organization_id='"+ri.getCourseDetails()+"'  ";
				System.out.println(quary);
				LOGGER.info("assessment query="+quary);
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					RetriveImagesDomain dcd=new RetriveImagesDomain();
					dcd.setCourseName(rs.getString("assessment_name"));		
					dcd.setCourseImageLocation(rs.getString("uploaded_by"));
					dcd.setCourseImageSize(rs.getString("assessment_id"));
					arl.add(dcd);
				}
				//}
				}catch(Exception e){
				e.printStackTrace();
			}
			finally
			   {
				   try{
					   con.close();
					//   pstmt1.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
		return arl;
		   
	   }
	   public ArrayList<RetriveImagesDomain> selectCourses(DisplayCoursesDomain ri){
			//DBTransaction1 dbt = new DBTransaction1();
		//	DBTransaction1 dbt1 = new DBTransaction1();
			 ArrayList<RetriveImagesDomain> arl=new  ArrayList<RetriveImagesDomain>();
			try {
				con = DBTransaction.connect();
				
				//PreparedStatement pstmt1 = null;
			/*	ResultSet rs1 = null;
				String quary1 = "select faculty_id from student_faculty_mapping where organization_id='"+ri.getCourseDetails()+"' and student_id='"+ri.getCourseName()+"'";
				System.out.println(quary1);
				pstmt1 = con.prepareStatement(quary1);
				rs1 = pstmt1.executeQuery();
				while(rs1.next())
				{*/	
				PreparedStatement pstmt = null;
				ResultSet rs = null;			
				//String quary = "SELECT assessment_id,assessment_name,uploaded_by FROM assessment_info where organization_id='"+ri.getCourseDetails()+"'  and (recipient_type like '%"+ri.getCourseName()+"%' or recipient_type='All')";
				String quary = "SELECT assessment_id,assessment_name,uploaded_by FROM assessment_info where organization_id='"+ri.getCourseDetails()+"'  and "
						+ "(recipient_type like '%"+ri.getCourseName()+"%' or recipient_type='All' or recipient_type in "
								+ "(select group_name from student_groups where student_id like '%"+ri.getCourseName()+"%' ))";

				System.out.println(quary);
				LOGGER.info("assessment query="+quary);
				pstmt = con.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					RetriveImagesDomain dcd=new RetriveImagesDomain();
					dcd.setCourseName(rs.getString("assessment_name"));		
					dcd.setCourseImageLocation(rs.getString("uploaded_by"));
					dcd.setCourseImageSize(rs.getString("assessment_id"));
					arl.add(dcd);
				}
				//}
				}catch(Exception e){
				e.printStackTrace();
			}
			finally
			   {
				   try{
					   con.close();
					  // pstmt1.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
		return arl;
		   
	   }
	}