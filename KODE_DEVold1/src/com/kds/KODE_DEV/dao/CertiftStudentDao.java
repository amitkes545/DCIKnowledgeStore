package com.kds.KODE_DEV.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.AssessmentDomain;
import com.kds.KODE_DEV.domain.FacilitatorManageKnowStoreDomain;
import com.kds.KODE_DEV.domain.SessionDomain;

public class CertiftStudentDao  {
	//DBTransaction db= new DBTransaction();
	Connection connection= null;	
	ResultSet rs= null;
	PreparedStatement prepareStatement=null;
	ArrayList<AdminDomain> al= new ArrayList<AdminDomain>();
	static final Logger LOGGER = Logger.getLogger(CertiftStudentDao.class);
	
	public ArrayList<AdminDomain> retriveAssessMentDetails(String faculyid,String orgid){
		LOGGER.info("dao faculty id:"+faculyid);
	try{
	
		connection=DBTransaction.connect();
		String sql= "select assessment_id,assessment_name from assessment_info where uploaded_by='"+faculyid+"'"+"and organization_id='"+orgid+"'";
		prepareStatement=connection.prepareStatement(sql);
		rs=prepareStatement.executeQuery();
		
		while(rs.next())
		{
			AdminDomain cDomain= new AdminDomain();
		 cDomain.setAssignment_ID(rs.getString("assessment_id"));
		 cDomain.setAssignment_name(rs.getString("assessment_name"));
		 al.add(cDomain);
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}		
	finally
	   {
		   try{
		   connection.close();
		   prepareStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	return al;	
	}
	
	ArrayList<AdminDomain> asl= new ArrayList<AdminDomain>();
	//static final Logger LOGGER = Logger.getLogger(CertiftStudentDao.class);
	
	public ArrayList<AdminDomain> retriveStudentAssessMentDetails(String faculyid,String orgid){
		LOGGER.info("dao faculty id:"+faculyid);
	try{
	
		connection=DBTransaction.connect();
		String sql= "select assessment_info.assessment_id,assessment_name from assessment_student_info left join assessment_info on assessment_student_info.assessment_id=assessment_info.assessment_id where assessment_student_info.uploaded_by='"+faculyid+"'"+"and organization_id='"+orgid+"'";
		prepareStatement=connection.prepareStatement(sql);
		rs=prepareStatement.executeQuery();
		System.out.println(sql);
		while(rs.next())
		{
			AdminDomain cDomain= new AdminDomain();
		 cDomain.setAssignment_ID(rs.getString("assessment_id"));
		 cDomain.setAssignment_name(rs.getString("assessment_name"));
		 asl.add(cDomain);
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}			
	finally
	   {
		   try{
		   connection.close();
		   prepareStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	return asl;	
	}
	
	ArrayList<AdminDomain> asla= new ArrayList<AdminDomain>();
	//static final Logger LOGGER = Logger.getLogger(CertiftStudentDao.class);
	
	public ArrayList<AdminDomain> retriveStudentAssignMentDetails(String faculyid,String orgid){
		LOGGER.info("dao faculty id:"+faculyid);
	try{
	
		connection=DBTransaction.connect();
		String sql= "select distinct(assignment_info.assignment_id),assignment_name from assignment_student_info left join assignment_info on assignment_student_info.assignment_id=assignment_info.assignment_id where assignment_student_info.uploaded_by='"+faculyid+"'"+"and organization_id='"+orgid+"'";
		prepareStatement=connection.prepareStatement(sql);
		rs=prepareStatement.executeQuery();
		//System.out.println(sql);
		while(rs.next())
		{
			AdminDomain cDomain= new AdminDomain();
		 cDomain.setAssignment_ID(rs.getString("assignment_id"));
		 cDomain.setAssignment_name(rs.getString("assignment_name"));
		 asla.add(cDomain);
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}		
	finally
	   {
		   try{
		   connection.close();
		   prepareStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	return asla;	
	}
	
	
	ArrayList<String> sd=new ArrayList<String>();
	public ArrayList<String> getUploadedStudent(AdminDomain adomain){
		
		try{
			
			connection=DBTransaction.connect();
			String sql= "select uploaded_by from assessment_student_info where assessment_id='"+adomain.getAssignment_name()+"'";
			prepareStatement=connection.prepareStatement(sql);
			LOGGER.info("query is:"+prepareStatement);
			rs=prepareStatement.executeQuery();
			
			while(rs.next())
			{
				
				sd.add(rs.getString("uploaded_by"));
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		finally
		   {
			   try{
			   connection.close();
			   prepareStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return sd;	
		}
public String getFilePath(String uploadedid){
		String status="";
		try{
			
			connection=DBTransaction.connect();
			String sql= "select path from assessment_student_info where uploaded_by='"+uploadedid+"'";
			prepareStatement=connection.prepareStatement(sql);
			LOGGER.info("query is:"+prepareStatement);
			rs=prepareStatement.executeQuery();
			
			while(rs.next())
			{
				status=rs.getString("path");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}			
		finally
		   {
			   try{
			   connection.close();
			   prepareStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return status;	
		}


public ArrayList<String> getAssessmentType(){
	ArrayList assType=new ArrayList();
	try{
		
		connection=DBTransaction.connect();
		String sql= "select type_description from mst_assessment_types";
		prepareStatement=connection.prepareStatement(sql);
		LOGGER.info("query is:"+prepareStatement);
		rs=prepareStatement.executeQuery();
		
		while(rs.next())
		{
			
			assType.add(rs.getString("type_description"));
			
		}
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}		
	finally
	   {
		   try{
		   connection.close();
		   prepareStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	return assType;	
	}








      public String insertCertifyValue(SessionDomain sdomain){
	String status="";
	try{
		
		connection=DBTransaction.connect();
		String sql= "update assessment_student_info set marks=?,status=?,remarks=? where uploaded_by=?";
		prepareStatement=connection.prepareStatement(sql);
		prepareStatement.setString(1,sdomain.getMarks());
		prepareStatement.setString(2,sdomain.getStatus());
		prepareStatement.setString(3,sdomain.getReMarks());
		prepareStatement.setString(4,sdomain.getFacultyId());
		LOGGER.info("query is:"+prepareStatement);
		int n=prepareStatement.executeUpdate();
		
		if (n != 0)
			status = "valid";
		else
			status = "notvalid";
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}			
	finally
	   {
		   try{
		   connection.close();
		   prepareStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	return status;	
	}
      public ArrayList<AdminDomain> assessMentReportsDetails(){
  		//LOGGER.info("dao faculty id:"+faculyid);
  	try{
  	
  		connection=DBTransaction.connect();
  		String sql= "select assessment_id from assessment_student_info";
  		prepareStatement=connection.prepareStatement(sql);
  		rs=prepareStatement.executeQuery();
  		LOGGER.info("sql query in dao:"+sql);
  		while(rs.next())
  		{
  			AdminDomain cDomain= new AdminDomain();
  		 cDomain.setAssignment_name(rs.getString("assessment_id"));
  		 al.add(cDomain);
  		}
  	}
  	catch(Exception e)
  	{
  		e.printStackTrace();
  	}		
  	finally
	   {
		   try{
		   connection.close();
		   prepareStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
  	return al;	
  	}
      
      public SessionDomain getDetailsOfStudent(String assid){
    		//LOGGER.info("dao faculty id:"+faculyid);
    	  SessionDomain cDomain= new SessionDomain();
    	try{
    	
    		connection=DBTransaction.connect();
    		String sql= "select * from assessment_student_info where assessment_id='"+assid+"'";
    		prepareStatement=connection.prepareStatement(sql);
    		rs=prepareStatement.executeQuery();
    		LOGGER.info("student assessment query:"+sql);
    		while(rs.next())
    		{
    			
    		 cDomain.setAssessmentId(rs.getString("assessment_id"));
    		 cDomain.setFacultyId(rs.getString("uploaded_by"));
    		 cDomain.setPathOfFile(rs.getString("path"));
    		 cDomain.setMarks(rs.getString("marks"));
    		 cDomain.setStatus(rs.getString("status"));
    		 cDomain.setReMarks(rs.getString("remarks"));
    		
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}			
    	finally
 	   {
 		   try{
 		   connection.close();
 		   prepareStatement.close();
 		   }
 		   catch(Exception e){
 				e.printStackTrace();
 	   }
 	   }
    	return cDomain;	
    	}
      public SessionDomain getDetailsOfAsignment(String assid){
  		//LOGGER.info("dao faculty id:"+faculyid);
  	  SessionDomain cDomain= new SessionDomain();
  	try{
  	
  		connection=DBTransaction.connect();
  		String sql= "select * from assignment_student_info where assignment_id='"+assid+"'";
  		prepareStatement=connection.prepareStatement(sql);
  		rs=prepareStatement.executeQuery();
  		LOGGER.info("student assignment query:"+sql);
  		while(rs.next())
  		{
  			
  		 cDomain.setAssessmentId(rs.getString("assignment_id"));
  		 cDomain.setFacultyId(rs.getString("uploaded_by"));
  		 cDomain.setPathOfFile(rs.getString("path_of_file"));
  		 cDomain.setMarks(rs.getString("marks"));
  		 cDomain.setStatus(rs.getString("status"));
  		 cDomain.setReMarks(rs.getString("remarks"));
  		
  		}
  	}
  	catch(Exception e)
  	{
  		e.printStackTrace();
  	}		
  	finally
	   {
		   try{
		   connection.close();
		   prepareStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
  	return cDomain;	
  	}
      public ArrayList<AdminDomain> retriveAssignMentDetails(String faculyid,String orgid){
  		LOGGER.info("dao faculty id:"+faculyid);
  	try{
  	
  		connection=DBTransaction.connect();
  		String sql= "select assignment_id,assignment_name from assignment_info where uploaded_by='"+faculyid+"'"+"and organization_id='"+orgid+"'";
  		prepareStatement=connection.prepareStatement(sql);
  		rs=prepareStatement.executeQuery();
  		//System.out.println(prepareStatement);
  		while(rs.next())
  		{
  			AdminDomain cDomain= new AdminDomain();
  		 cDomain.setAssignment_ID(rs.getString("assignment_id"));
  		cDomain.setAssignment_name(rs.getString("assignment_name"));
  		 al.add(cDomain);
  		}
  	}
  	catch(Exception e)
  	{
  		e.printStackTrace();
  	}		
  	finally
	   {
		   try{
		   connection.close();
		   prepareStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
  	return al;	
  	}
      ArrayList<String> allist=new ArrayList<String>();
  	public ArrayList<String> getUploadedAssignStudent(AdminDomain adomain){
  		
  		try{
  			
  			connection=DBTransaction.connect();
  			String sql= "select uploaded_by from assignment_student_info where assignment_id='"+adomain.getAssignment_name()+"'";
  			prepareStatement=connection.prepareStatement(sql);
  			LOGGER.info("query is:"+prepareStatement);
  			rs=prepareStatement.executeQuery();
  			
  			while(rs.next())
  			{
  				
  				allist.add(rs.getString("uploaded_by"));
  				
  			}
  			
  		}
  		catch(Exception e)
  		{
  			e.printStackTrace();
  		}			
  		finally
 	   {
 		   try{
 		   connection.close();
 		   prepareStatement.close();
 		   }
 		   catch(Exception e){
 				e.printStackTrace();
 	   }
 	   }
  		return allist;	
  		}
  	public String getAssignFilePath(String uploadedid){
		String status="";
		try{
			
			connection=DBTransaction.connect();
			String sql= "select path_of_file from assignment_student_info where uploaded_by='"+uploadedid+"'";
			prepareStatement=connection.prepareStatement(sql);
			LOGGER.info("query is:"+prepareStatement);
			rs=prepareStatement.executeQuery();
			LOGGER.info("query:"+prepareStatement);
			while(rs.next())
			{
				status=rs.getString("path_of_file");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}			
		finally
		   {
			   try{
			   connection.close();
			   prepareStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return status;	
		}
  	 public String insertAssignCertifyValue(SessionDomain sdomain){
  		String status="";
  		try{
  			
  			connection=DBTransaction.connect();
  			String sql= "update assignment_student_info set marks=?,status=?,remarks=? where uploaded_by=?";
  			prepareStatement=connection.prepareStatement(sql);
  			prepareStatement.setString(1,sdomain.getMarks());
  			prepareStatement.setString(2,sdomain.getStatus());
  			prepareStatement.setString(3,sdomain.getReMarks());
  			prepareStatement.setString(4,sdomain.getFacultyId());
  			LOGGER.info("query is:"+prepareStatement);
  			//System.out.println("query is:"+prepareStatement);
  			int n=prepareStatement.executeUpdate();
  			
  			if (n != 0)
  				status = "valid";
  			else
  				status = "notvalid";
  			
  		}
  		catch(Exception e)
  		{
  			e.printStackTrace();
  		}		
  		finally
 	   {
 		   try{
 		   connection.close();
 		   prepareStatement.close();
 		   }
 		   catch(Exception e){
 				e.printStackTrace();
 	   }
 	   }
  		return status;	
  		}
  	 
  	 // It accepts Assessment values from JSP and update into Data Base
  	 public String UpdateForAssessmentCertify(ArrayList<AssessmentDomain> arl){
  		 
			String assesssmentStatus=null;
		//	PreparedStatement updpreparedStatement=null;
		//	PreparedStatement inspreparedStatement=null;
			Statement stmnt =null;
			try {
		                 connection = DBTransaction.connect();
		             // System.out.println("in dao certify");
						String sql = "update assessment_student_info set marks=?,status=?,remarks=? where uploaded_by=? and assessment_id=?";
						String inssql = "insert into assessment_student_info (assessment_id,marks,status,remarks,uploaded_by) values(?,?,?,?,?)";
						//updpreparedStatement = connection.prepareStatement(sql);
						//inspreparedStatement = connection.prepareStatement(inssql);
						
						stmnt = connection.createStatement();
						//System.out.println("sql="+sql);
						
						for(AssessmentDomain s:arl){
						if(IsAssessmentUploaded(s.getRecipientType(),s.getAssessmentId())){
							System.out.println("entry found!");
							sql = "update assessment_student_info set marks='"+s.getMark()+"',status='"+s.getStatus()+"',remarks='"+s.getReMarks()+"' where uploaded_by='"+s.getRecipientType()+"' and assessment_id='"+s.getAssessmentId()+"'";
							System.out.println("the query is:="+sql);
							stmnt.addBatch(sql);

						}
						else{  //By Prity --if not uploaded by student
							System.out.println("entry not found!");
							inssql = "insert into assessment_student_info (assessment_id,marks,status,remarks,uploaded_by) values('"+s.getAssessmentId()+"','"+s.getMark()+"','"+s.getStatus()+"','"+s.getReMarks()+"','"+s.getRecipientType()+"')";
							System.out.println("the query is:="+inssql);
							stmnt.addBatch(inssql);

	  						}
						}
						
						int[] updcount=stmnt.executeBatch();
						System.out.println(Arrays.toString(updcount)+"updated");
						if (updcount!=null )
							assesssmentStatus = "valid";
						else
							assesssmentStatus = "notvalid";
					}catch(Exception e){
						System.out.println("in cathch");
						e.printStackTrace();
					}
			finally
		   {//System.out.println("in finally");
			   try{
			   connection.close();
			// System.out.println("in finally try");
			//updpreparedStatement.close();
			//inspreparedStatement.close();
			   stmnt.close();
			   }
			   catch(Exception e){
				// System.out.println("in finally cathch");
					e.printStackTrace();
		   }
		   }
					return assesssmentStatus;
				}
  	 
  	 
  	 public String deleteAssignments(ArrayList<AssessmentDomain> arl){
  		 
			String assesssmentStatus=null;
		
			PreparedStatement pstmt1 = null;
			PreparedStatement pstmt2 = null;
			PreparedStatement pstmt3 = null;
			int i=0,j=0,k=0;
			try {
		                 connection = DBTransaction.connect();
		          
		                 if(arl.size()>0){
			                	Iterator<AssessmentDomain> it=arl.iterator();
			                	
			                    while(it.hasNext()){
			                    	AssessmentDomain mksDomain=it.next();	
						
		                 String sql1 = "delete from assessment_info where assessment_id='"+mksDomain.getAssessmentId()+"'";
		                 String sql2 = "delete from assessment_recepients where assessment_id='"+mksDomain.getAssessmentId()+"'";
		                 String sql3 = "delete from assessment_student_info where assessment_id='"+mksDomain.getAssessmentId()+"'";
		                 
		                 
		                 
		                 pstmt1 = connection.prepareStatement(sql3);
		                 pstmt2=connection.prepareStatement(sql1);
		                 pstmt3=connection.prepareStatement(sql2);
						
		                 System.out.println("Query for delete="+pstmt1);
		                 System.out.println("Query for delete="+pstmt2);
		                 System.out.println("Query for delete="+pstmt3);
		                  i = pstmt1.executeUpdate();
		                  j=pstmt2.executeUpdate();
		                  k=pstmt3.executeUpdate();
						
						
												}
			                    System.out.println("i===="+i);
			                    System.out.println("j==="+j);
			                    System.out.println("k==="+k);
						
							if (i!=0 && j!=0 && k!=0)
							assesssmentStatus = "valid";
						else
							assesssmentStatus = "notvalid";
		                 }
		                 }catch(Exception e){
						System.out.println("in catch");
						e.printStackTrace();
					}
			finally
		   {
			   try{
			   connection.close();
			
			   pstmt1.close();
			   pstmt2.close();
			   pstmt3.close();

			   }
			   catch(Exception e){
				
					e.printStackTrace();
		   }
		   }
					return assesssmentStatus;
				}
	 

  	 
  	 
  	 
  // It accepts Assignment values from JSP and update into Data Base
  	public String UpdateForAssignmenmentCertify(ArrayList<AssessmentDomain> arl){
 		 
			String assignmentStatus=null;

			try {
		                Connection connection = DBTransaction.connect();
		              
						String sql = "update assignment_student_info set marks=?,status=?,remarks=? where uploaded_by=? and assignment_id=?";
						PreparedStatement preparedStatement = connection.prepareStatement(sql);
						
						for(AssessmentDomain s:arl){
						preparedStatement.setString(1, s.getMark());
						preparedStatement.setString(2,s.getStatus());
						preparedStatement.setString(3,s.getReMarks());
						preparedStatement.setString(4,s.getRecipientType());
						preparedStatement.setString(5, s.getAssessmentId());
						preparedStatement.addBatch();
						System.out.println("the query is:"+preparedStatement);
						int[] count=preparedStatement.executeBatch();
						System.out.println(Arrays.toString(count)+"updated");
						
						if (count!=null)
							assignmentStatus = "valid";
						else
							assignmentStatus = "notvalid";
		                }
		                
			
					}catch(Exception e){
						e.printStackTrace();
					}
			finally
			   {
				   try{
				   connection.close();
				   prepareStatement.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
					return assignmentStatus;
				}
  	 public Boolean IsAssessmentUploaded(String Uploaded_by, String Assessment_id){
  		 
			ResultSet rs= null;
			Boolean status=false;
			PreparedStatement preparedStatement=null;
			try {
		                 //connection = DBTransaction.connect();
						String sql = "select 'yes' from assessment_student_info where uploaded_by='"+Uploaded_by+"' and assessment_id='"+Assessment_id+"'";
						preparedStatement = connection.prepareStatement(sql);
						System.out.println("sql="+sql);
						LOGGER.info("the query in certify Dao:"+preparedStatement);
						rs = preparedStatement.executeQuery();
						if (rs.next())
							status =  true;
						else
						 status = false;
					}catch(Exception e){
						System.out.println("in catch");
						e.printStackTrace();
					}
					return status;
				}
}
