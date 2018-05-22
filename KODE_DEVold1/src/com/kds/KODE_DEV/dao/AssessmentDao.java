package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.util.SystemOutLogger;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.AssessmentDomain;


public class AssessmentDao {
	java.sql.Date dueDateForAssignments;
		Connection con= null;	
	ResultSet rs= null;
	PreparedStatement ps=null;
	ArrayList<AdminDomain> al= new ArrayList<AdminDomain>();
	public ArrayList<AdminDomain> sendIndualGroup( String faculyid,String orgid){
		
	try{
	
		con=DBTransaction.connect();
		String sql= "select distinct user_id,username from users_info left join course_description on users_info.organization_id=course_description.customer_id "
				+ "where privilege='Student' and organization_id='"+orgid+"' order by user_id";
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		
		while(rs.next())
		{
			AdminDomain cDomain= new AdminDomain();
		 cDomain.setAdminId(rs.getString("user_id"));
		 cDomain.setAdminName(rs.getString("username"));
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
		   con.close();
		   ps.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	return al;	
	}
	ArrayList<AdminDomain> alg= new ArrayList<AdminDomain>();
	public ArrayList<AdminDomain> sendGroupId( String faculyid,String orgID){
		//System.out.println("dao faculty id:"+faculyid);
	try{
		
		con=DBTransaction.connect();
		String sql= "select group_name from student_groups where organization_id='"+orgID+"' and created_by='"+faculyid+"' order by group_name ";
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		String newgrp_name="",grp_namesplit[]=null;
		while(rs.next())
		{
			AdminDomain cDomain= new AdminDomain();
			String grp_name=rs.getString("group_name");
			if(grp_name.contains("_group"))
				{
				grp_namesplit=grp_name.split("_group");
				newgrp_name=grp_namesplit[0];
				}
			else{
				newgrp_name=grp_name;
			}
		 cDomain.setGroup_name(grp_name);
		 cDomain.setNew_Group_name(newgrp_name);

		 //cDomain.setGroup_name(rs.getString("group_name"));
		 alg.add(cDomain);
		}
	}
	catch(Exception e)
	{
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
	return alg;	
	}
	 public String getOrgId(String facultyid){
		 String orgid=" ";
		 try{
			 con=DBTransaction.connect();
			 String sql="select organization_id from users_info where user_id='"+facultyid+"'";
			 ps=con.prepareStatement(sql);
			 rs=ps.executeQuery();
			 //System.out.println("sql query is:"+sql);
			 while(rs.next()){
				 orgid=rs.getString("organization_id");
			 }
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
		 //System.out.println("result status:"+orgid);
		return orgid;
		
	 }
	public String insertGroupDetails(AssessmentDomain asdomain){
		String status="";
		try {
			con=DBTransaction.connect();
			java.util.Date utilDate = new Date();
			java.sql.Date date = new java.sql.Date(utilDate.getTime());
			//System.out.println("date :"+date);
			Calendar calendar = Calendar.getInstance();
			java.util.Date now = calendar.getTime();
			java.sql.Time currentTime = new java.sql.Time(now.getTime());
			//System.out.println("time:"+currentTime);
			long i= asdomain.getSizeInBytes();
			int marks=Integer.parseInt(asdomain.getMark());
			System.out.println("getGroupId name:"+asdomain.getGroupId());
			if(i==0){
				String query="insert into assessment_info(assessment_id,organization_id,uploaded_by,uploaded_time,uploaded_date,assessment_description,recipient_type,path_of_file,assessment_name,marks) values(?,?,?,?,?,?,?,?,?,?)";
				ps=con.prepareStatement(query);
				ps.setString(1,asdomain.getAssessmentId());
				ps.setString(2,asdomain.getOrgId());
				ps.setString(3,asdomain.getUploadedBy());
				ps.setTime(4,currentTime);
				ps.setDate(5,date);
				ps.setString(6,asdomain.getDescrition());
				ps.setString(7,asdomain.getGroupId());
				ps.setString(8,asdomain.getPathOfFile());
				ps.setString(9,asdomain.getAssessmentName());
				ps.setInt(10,marks);
				System.out.println("the query for insert group:"+query);
				int n = ps.executeUpdate();
				System.out.println("query is inserted successfully");
				
				if (n != 0)
					status = "valid";
				else
					status = "notvalid";
			}
			
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
		String check="invalid";
		if(!"invalid".equals(status))
			check=insertInAssignmentRecepients(asdomain);
		
		if("invalid".equals(check))
			 status="invalid";
		return status;
	}
	
	public String insertIndividualAssinmentDetailsWithTransaction(AssessmentDomain asdomain)
	{
		String status="";
		PreparedStatement updateAssessmentInfo = null;
		PreparedStatement updateAssessmentReceipients = null;
		
		java.util.Date utilDate = new Date();
		java.sql.Date date = new java.sql.Date(utilDate.getTime());
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		java.sql.Time currentTime = new java.sql.Time(now.getTime());
		long i= asdomain.getSizeInBytes();
		int marks=Integer.parseInt(asdomain.getMark());

		System.out.println("Start of Assignment ..... for INDIVIDULAL ......");   
		DateFormat  formatter = new SimpleDateFormat("dd/MM/yyyy");
		

        
			String insertIndividualQueryAS="insert into assessment_info(assessment_id,organization_id,uploaded_by,uploaded_time,uploaded_date,assessment_description,recipient_type,path_of_file,assessment_name,marks,course_id,subject_id,due_date,assessment_type) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String insertIndividualQueryAR="insert into assessment_recepients(assessment_id, recepient_id, receipient_type, assessment_status, organization_id, created_datetime, last_updated_datetime, mod_user_id) values (?, ?, ?, ?, ?, now(), now(), ?)";
		  
		try
		{
			con=DBTransaction.connect();
			
			
			java.util.Date date1 = formatter.parse(asdomain.getDueDate());
			dueDateForAssignments = new java.sql.Date(date.getTime());
			
			
			updateAssessmentInfo = con.prepareStatement(insertIndividualQueryAS);
			updateAssessmentReceipients = con.prepareStatement(insertIndividualQueryAR);
			updateAssessmentInfo.setString(1,asdomain.getAssessmentId());
			updateAssessmentInfo.setString(2,asdomain.getOrgId());
			updateAssessmentInfo.setString(3,asdomain.getUploadedBy());
			updateAssessmentInfo.setTime(4,currentTime);
			updateAssessmentInfo.setDate(5,date);
			updateAssessmentInfo.setString(6,asdomain.getDescrition());
			updateAssessmentInfo.setString(7,asdomain.getIndividualId());
			updateAssessmentInfo.setString(8,asdomain.getPathOfFile());
			updateAssessmentInfo.setString(9,asdomain.getAssessmentName());
			updateAssessmentInfo.setInt(10,marks);
			updateAssessmentInfo.setString(11,asdomain.getCourseId());
			updateAssessmentInfo.setString(12,asdomain.getSubjectId());
			updateAssessmentInfo.setDate(13, dueDateForAssignments);
			updateAssessmentInfo.setString(14, asdomain.getAssessmentType());
			
			System.out.println("the query is insertAssinmentDetails for individual=:"+insertIndividualQueryAS);
			int n = updateAssessmentInfo.executeUpdate();
			if(n!=0)
			{
				updateAssessmentReceipients.setString(1,asdomain.getAssessmentId());// assessment id 
				updateAssessmentReceipients.setString(2,asdomain.getIndividualId());// recepient_id
				updateAssessmentReceipients.setString(3,asdomain.getIndividualId());//receipient_type
				updateAssessmentReceipients.setString(4,"ACTIVE");//assessment_status
				updateAssessmentReceipients.setString(5,asdomain.getOrgId());//organization_id
				updateAssessmentReceipients.setString(6,asdomain.getUserId());//modified by
				n = updateAssessmentReceipients.executeUpdate();
				if(n != 0)
					status="valid";
				else
					status="invalid";
			}else
				status="invalid";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
	    {
		   try
		   {
			   con.close();
			   ps.close();
		   }
		   catch(Exception e)
		   {
				e.printStackTrace();
		   }
	    }

		return status;
	}
	
	public String insertGroupAssignmentDetailsWithTransaction(AssessmentDomain asdomain)
	{
		String status="";
		PreparedStatement updateAssessmentInfo = null;
		PreparedStatement updateAssessmentReceipients = null;
		
		java.util.Date utilDate = new Date();
		java.sql.Date date = new java.sql.Date(utilDate.getTime());
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		java.sql.Time currentTime = new java.sql.Time(now.getTime());
		long i= asdomain.getSizeInBytes();
		int marks=Integer.parseInt(asdomain.getMark());

		   
			String insertGroupQueryAS="insert into assessment_info(assessment_id,organization_id,uploaded_by,uploaded_time,uploaded_date,assessment_description,recipient_type,path_of_file,assessment_name,marks,course_id,subject_id) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			String insertGroupQueryAR="insert into assessment_recepients(assessment_id, recepient_id, receipient_type, assessment_status, organization_id, created_datetime, last_updated_datetime, mod_user_id) values (?, ?, ?, ?, ?, now(), now(), ?)";
		  
		try
		{
			con=DBTransaction.connect();
			
			updateAssessmentInfo = con.prepareStatement(insertGroupQueryAS);
			updateAssessmentReceipients = con.prepareStatement(insertGroupQueryAR);
			updateAssessmentInfo.setString(1,asdomain.getAssessmentId());
			updateAssessmentInfo.setString(2,asdomain.getOrgId());
			updateAssessmentInfo.setString(3,asdomain.getUploadedBy());
			updateAssessmentInfo.setTime(4,currentTime);
			updateAssessmentInfo.setDate(5,date);
			updateAssessmentInfo.setString(6,asdomain.getDescrition());
			updateAssessmentInfo.setString(7,asdomain.getGroupId());
			updateAssessmentInfo.setString(8,asdomain.getPathOfFile());
			updateAssessmentInfo.setString(9,asdomain.getAssessmentName());
			updateAssessmentInfo.setInt(10,marks);
			updateAssessmentInfo.setString(11,asdomain.getCourseId());
			updateAssessmentInfo.setString(12,asdomain.getSubjectId());
			System.out.println("the query is insertAssinmentDetails for individual=:"+insertGroupQueryAS);
			int n = updateAssessmentInfo.executeUpdate();
			if(n!=0)
			{
				String query="select student_id from student_groups where group_name='"+asdomain.getGroupId()+"' and organization_id='"+asdomain.getOrgId()+"'";
				ArrayList<String> enrollmentIdsOfRecords=getEnrollmentIdsForGroup(query);
				
				
				for(String enrId:enrollmentIdsOfRecords)
				{
					updateAssessmentReceipients.setString(1,asdomain.getAssessmentId());// assessment id 
					updateAssessmentReceipients.setString(2,enrId);// recepient_id
					updateAssessmentReceipients.setString(3,asdomain.getGroupId());//receipient_type
					updateAssessmentReceipients.setString(4,"ACTIVE");//assessment_status
					updateAssessmentReceipients.setString(5,asdomain.getOrgId());//organization_id
					updateAssessmentReceipients.setString(6,asdomain.getUserId());//modified by
					n = updateAssessmentReceipients.executeUpdate();
				}
				
				
				if(n != 0)
					status="valid";
				else
					status="invalid";
			}else
				status="invalid";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
	    {
		   try
		   {
			   con.close();
			   ps.close();
		   }
		   catch(Exception e)
		   {
				e.printStackTrace();
		   }
	    }

		return status;
	}
	
	public String insertAllAssignmentDetailsWithTransaction(AssessmentDomain asdomain)
	{
		String status="";
		PreparedStatement updateAssessmentInfo = null;
		PreparedStatement updateAssessmentReceipients = null;
		
		java.util.Date utilDate = new Date();
		java.sql.Date date = new java.sql.Date(utilDate.getTime());
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		java.sql.Time currentTime = new java.sql.Time(now.getTime());
		long i= asdomain.getSizeInBytes();
		int marks=Integer.parseInt(asdomain.getMark());

		   
			String insertGroupQueryAS="insert into assessment_info(assessment_id,organization_id,uploaded_by,uploaded_time,uploaded_date,assessment_description,recipient_type,path_of_file,assessment_name,marks,course_id,subject_id) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			String insertGroupQueryAR="insert into assessment_recepients(assessment_id, recepient_id, receipient_type, assessment_status, organization_id, created_datetime, last_updated_datetime, mod_user_id) values (?, ?, ?, ?, ?, now(), now(), ?)";
		  
		try
		{
			Connection con=DBTransaction.connect();
			
			updateAssessmentInfo = con.prepareStatement(insertGroupQueryAS);
			updateAssessmentReceipients = con.prepareStatement(insertGroupQueryAR);
			updateAssessmentInfo.setString(1,asdomain.getAssessmentId());
			updateAssessmentInfo.setString(2,asdomain.getOrgId());
			updateAssessmentInfo.setString(3,asdomain.getUploadedBy());
			updateAssessmentInfo.setTime(4,currentTime);
			updateAssessmentInfo.setDate(5,date);
			updateAssessmentInfo.setString(6,asdomain.getDescrition());
			updateAssessmentInfo.setString(7,"All");
			updateAssessmentInfo.setString(8,asdomain.getPathOfFile());
			updateAssessmentInfo.setString(9,asdomain.getAssessmentName());
			updateAssessmentInfo.setInt(10,marks);
			updateAssessmentInfo.setString(11,asdomain.getCourseId());
			updateAssessmentInfo.setString(12,asdomain.getSubjectId());
	
			
			System.out.println("the query is insertAssinmentDetails for individual=:"+insertGroupQueryAS);
			int n = updateAssessmentInfo.executeUpdate();
			if(n!=0)
			{
				System.out.println("=================="+asdomain.getCourseId()+"------------------");
				// waiting for the query..
				String query="select a.enrollment_process_id, a.full_name from transaction_enrollment_process a, org_details b where "+
						     "b.organization_id = '"+asdomain.getOrgId() +"' and a.middle_layer_teaching_source_name = b.organization_name and "+
						     "a.course_id = '"+asdomain.getCourseId()+"' and a.course_completed <> 'YES' order by a.full_name";
				ArrayList<String> enrollmentIdsOfRecords=getEnrollmentIdsForAll(query,con);
				
				
				for(String enrId:enrollmentIdsOfRecords)
				{
					updateAssessmentReceipients.setString(1,asdomain.getAssessmentId());// assessment id 
					updateAssessmentReceipients.setString(2,enrId);// recepient_id
					updateAssessmentReceipients.setString(3,"All");//receipient_type
					updateAssessmentReceipients.setString(4,"ACTIVE");//assessment_status
					updateAssessmentReceipients.setString(5,asdomain.getOrgId());//organization_id
					updateAssessmentReceipients.setString(6,asdomain.getUserId());//modified by
					n = updateAssessmentReceipients.executeUpdate();
				}
				
				
				if(n != 0)
					status="valid";
				else
					status="invalid";
			}else
				status="invalid";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
	    {
		   try
		   {
			   ps.close();
			   con.close();
			   
		   }
		   catch(Exception e)
		   {
				e.printStackTrace();
		   }
	    }

		return status;
	}
	
	
	public String insertAssinmentDetails(AssessmentDomain asdomain){
		String status="";
		try {
			con=DBTransaction.connect();
			java.util.Date utilDate = new Date();
			java.sql.Date date = new java.sql.Date(utilDate.getTime());
			Calendar calendar = Calendar.getInstance();
			java.util.Date now = calendar.getTime();
			java.sql.Time currentTime = new java.sql.Time(now.getTime());
			long i= asdomain.getSizeInBytes();
			int marks=Integer.parseInt(asdomain.getMark());
			//System.out.println("assessment name:"+asdomain.getAssessmentName());
			if(i==0){
				
				String query="insert into assessment_info(assessment_id,organization_id,uploaded_by,uploaded_time,uploaded_date,assessment_description,recipient_type,path_of_file,assessment_name,marks) values(?,?,?,?,?,?,?,?,?,?)";
				 ps=con.prepareStatement(query);
				ps.setString(1,asdomain.getAssessmentId());
				ps.setString(2,asdomain.getOrgId());
				ps.setString(3,asdomain.getUploadedBy());
				ps.setTime(4,currentTime);
				ps.setDate(5,date);
				ps.setString(6,asdomain.getDescrition());
				ps.setString(7,asdomain.getIndividualId());
				ps.setString(8,asdomain.getPathOfFile());
				ps.setString(9,asdomain.getAssessmentName());
				ps.setInt(10,marks);
				System.out.println("the query is insertAssinmentDetails for individual=:"+query);
				int n = ps.executeUpdate();
				//System.out.println("query is:"+ps);
				
				if (n != 0)
					status = "valid";
				else
					status = "notvalid";
			}
			
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
		String check="invalid";
		if(!"invalid".equals(status))
			check=insertInAssignmentRecepients(asdomain);
		
		if("invalid".equals(check))
			 status="invalid";
		
		return status;
	}
	 public String getKsId(String facultyid,String orgid){
		String status="";
		 try{
			 con=DBTransaction.connect();
			 String sql="select ksid from knowledgestore_info where user_id='"+facultyid+"'"+"and organization_id='"+orgid+"'";
			 ps=con.prepareStatement(sql);
			 rs=ps.executeQuery();
			 //System.out.println("sql query is:"+sql);
			 while(rs.next()){
				 status=rs.getString("ksid");
			 }
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
		 //System.out.println("result status:"+orgid);
		return status;
		
	 }
	 public String getGroupValues(String groupid,String userId,String organizationId){
		 String student_id=" ";
		 //System.out.println("group id in dao is:"+groupid);
		 try{
			 con=DBTransaction.connect();
			 String sql="select student_id from student_groups where group_name='"+groupid+"'"+" and created_by='"+userId+"'"+" and organization_id='"+organizationId+"'";
			 ps=con.prepareStatement(sql);
			 rs=ps.executeQuery();
			 System.out.println("sql query is:"+sql);
			 while(rs.next()){
				 student_id=rs.getString("student_id");
			 }
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
		 //System.out.println("result status:"+student_id);
		return student_id;
		
	 }
	 public String insertAssinmentAllDetails(AssessmentDomain asdomain){
			String status="";
			try {
				con=DBTransaction.connect();
				java.util.Date utilDate = new Date();
				java.sql.Date date = new java.sql.Date(utilDate.getTime());
				//System.out.println("date :"+date);
				Calendar calendar = Calendar.getInstance();
				java.util.Date now = calendar.getTime();
				java.sql.Time currentTime = new java.sql.Time(now.getTime());
				//System.out.println("time:"+currentTime);
				long i= asdomain.getSizeInBytes();
				int marks=Integer.parseInt(asdomain.getMark());
				//System.out.println("assessment name:"+asdomain.getAssessmentName());
				if(i==0){
					String query="insert into assessment_info(assessment_id,organization_id,uploaded_by,uploaded_time,uploaded_date,assessment_description,recipient_type,path_of_file,assessment_name,marks) values(?,?,?,?,?,?,?,?,?,?)";
					 ps=con.prepareStatement(query);
					 ps.setString(1,asdomain.getAssessmentId());
					ps.setString(2,asdomain.getOrgId());
					ps.setString(3,asdomain.getUploadedBy());
					ps.setTime(4,currentTime);
					ps.setDate(5,date);
					ps.setString(6,asdomain.getDescrition());
					ps.setString(7,"All");
					ps.setString(8,asdomain.getPathOfFile());
					ps.setString(9,asdomain.getAssessmentName());
					ps.setInt(10,marks);
					
					int n = ps.executeUpdate();
					System.out.println("the query is insertAssinmentAllDetails for all=:"+query);
					//System.out.println("query is inserted successfully");
					
					if (n != 0)
						status = "valid";
					else
						status = "notvalid";
				}
				
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
			String check="invalid";
			if(!"invalid".equals(status))
				check=insertInAssignmentRecepients(asdomain);
			
			if("invalid".equals(check))
				 status="invalid";
			return status;
		}
	 
	 public String insertInAssignmentRecepients(AssessmentDomain asdomain)
	 {
		
		String status="";
		try {
			
			java.util.Date utilDate = new Date();
			java.sql.Date date = new java.sql.Date(utilDate.getTime());
			//System.out.println("date :"+date);
			Calendar calendar = Calendar.getInstance();
			java.util.Date now = calendar.getTime();
			java.sql.Time currentTime = new java.sql.Time(now.getTime());
			//System.out.println("time:"+currentTime);
			long i= asdomain.getSizeInBytes();
			int marks=Integer.parseInt(asdomain.getMark());
			ArrayList<String> enrollmentIdsOfRecords= new ArrayList<String>();
			String recepientType="All";
			if("Individual".equals(asdomain.getRecipientType()))
					{
				recepientType=asdomain.getIndividualId();
				enrollmentIdsOfRecords.add(recepientType);
					}else if("Group".equals(asdomain.getRecipientType()))
					{
						if(asdomain.getGroupId() != null)
							recepientType=asdomain.getGroupId();
						else
							recepientType="tempGName";
						String query="select student_id from student_groups where group_name='"+recepientType+"' and organization_id='"+asdomain.getOrgId()+"'";
						
						
						
						enrollmentIdsOfRecords=getEnrollmentIdsForGroup(query);
					}else{
						String query="";
						enrollmentIdsOfRecords=getEnrollmentIdsForAll(query,con);
						recepientType="All";
					}
			//System.out.println("assessment name:"+asdomain.getAssessmentName());
			con=DBTransaction.connect();
			if(i==0){
				//String query="insert into assessment_info(assessment_id,organization_id,uploaded_by,uploaded_time,uploaded_date,assessment_description,recipient_type,path_of_file,assessment_name,marks) values(?,?,?,?,?,?,?,?,?,?)";
				String query="INSERT INTO assessment_recepients(assessment_id, recepient_id, receipient_type, assessment_status, organization_id, created_datetime, last_updated_datetime, mod_user_id)  "
						 +"VALUES (?, ?, ?, ?, ?, now(), now(), ?)"; 
				
				
				ps=con.prepareStatement(query);
				int n=0;
				for(int a=0;a<enrollmentIdsOfRecords.size();a++)
				{
					ps.setString(1,asdomain.getAssessmentId());// assessment id 
					ps.setString(2,enrollmentIdsOfRecords.get(a));// recepient_id
					ps.setString(3,recepientType);//receipient_type
					ps.setString(4,"ACTIVE");//assessment_status
					ps.setString(5,asdomain.getOrgId());//organization_id
					ps.setString(6,asdomain.getUserId());//modified by
					n = ps.executeUpdate();
				}	
				
				//int n = ps.executeUpdate();
				
				if (n != 0)
					status = "valid";
				else
					status = "notvalid";
			}
			
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
	 
	  ArrayList<String> getEnrollmentIdsForGroup(String sql)
	 {
		  ArrayList<String> enrollmentIdsOfRecords= new ArrayList<String>();
		  String studentIds="";
		  try{
				 con=DBTransaction.connect();
				 ps=con.prepareStatement(sql);
				 rs=ps.executeQuery();
				 System.out.println("sql query is:"+sql);
				
				 while(rs.next())
				 {
					 studentIds=rs.getString(1);
				 }
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
		        String enr[]=studentIds.split("#");
		        System.out.println(enr.length);
		        System.out.println(enr[0]);
		        System.out.println(enr[1]);
		        
		        
		        
		        for(int i=0;i<enr.length;i++)
		        {
		        	enrollmentIdsOfRecords.add(enr[i]);
		        }
		  
		  
		  
		  return enrollmentIdsOfRecords;
	 }
	  
	  ArrayList<String> getEnrollmentIdsForAll(String sql,Connection con)
		 {
			  ArrayList<String> enrollmentIdsOfRecords= new ArrayList<String>();
			  try{
					
					 PreparedStatement ps=con.prepareStatement(sql);
					  ResultSet rs=ps.executeQuery();
					 System.out.println("sql query is:"+sql);
					 while(rs.next())
					 {
						 enrollmentIdsOfRecords.add(rs.getString(1));
					 }
				 }catch(Exception e){
					 e.printStackTrace();
				 }
				 finally
				   {
					   try{
					   
					   ps.close();
					   }
					   catch(Exception e){
							e.printStackTrace();
				   }
				   }
			  return enrollmentIdsOfRecords;
		 }
	  
	 
	 public boolean getAssessmentId(AssessmentDomain adomain)
		{
		
	    	String adminid=adomain.getAssessmentId();
	    	boolean result=false;
	    	try
	    	{
	    	 con = DBTransaction.connect();
	    	String quary="select assessment_id from assessment_info where assessment_id=?";
	    	 ps = con.prepareStatement(quary);
	    	ps.setString(1,adminid);
	    	  rs=ps.executeQuery();
	    	 if(rs.next())
	    		 result = true; 
	    	 
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
	    	return result;
		}

	 public int getStudent(String orgid)
	 {
		 int count=0;
		 try{
				
				con=DBTransaction.connect();
				
				 String sql = "select count(user_id) numuser from users_info where organization_id='"+orgid+"'"+" and privilege='Student'";
				 ps = con.prepareStatement(sql);
				    rs=ps.executeQuery();
				    //System.out.println("sql query is:"+prepareStatement);
				    if(rs.next()){
				    	//count++;
				    	System.out.print("in if");
				    	count=rs.getInt("numuser");
				    }
				    System.out.print("count="+count);
				}
				
			catch(Exception e)
			{
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
		 return count; }
	 
	 
	 public String getAssessmentIdBsedOnOrgid(String organizationId)
		{
		 String assessmentId=null;
	    	//String adminid=adomain.getAssessmentId();
	    	boolean result=false;
	    	try
	    	{
	    	 con = DBTransaction.connect();
	    	String quary="select assessment_id from assessment_info where organization_id=?";
	    	 ps = con.prepareStatement(quary);
	    	ps.setString(1,organizationId);
	    	  rs=ps.executeQuery();
	    	 if(rs.next())
	    		assessmentId=rs.getString(1); 
	    	 
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
	    	return assessmentId;
		}

	 
	 
	 

}
