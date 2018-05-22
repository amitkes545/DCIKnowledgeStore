package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.AssessmentDomain;


public class AssignmentDao {
	//DBTransaction db= new DBTransaction();
	Connection con= null;	
	ResultSet rs= null;
	PreparedStatement ps=null;
	ArrayList<AdminDomain> al= new ArrayList<AdminDomain>();
	public ArrayList<AdminDomain> sendIndualGroup( String faculyid,String orgid){
		//System.out.println("dao faculty id:"+faculyid);
	try{
	
		con=DBTransaction.connect();
		String sql= "select user_id,username from users_info where created_by='"+faculyid+"' and organization_id='"+orgid+"' order by user_id";
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
	public ArrayList<AdminDomain> sendGroupId(String faculyid,String orgid){
		//System.out.println("dao faculty id:"+faculyid);
	try{
		
		con=DBTransaction.connect();
		String sql= "select group_name from student_groups where created_by='"+faculyid+"' and organization_id='"+orgid+"'";
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		//System.out.println(sql);
		while(rs.next())
		{
			AdminDomain cDomain= new AdminDomain();
		 cDomain.setGroup_name(rs.getString("group_name"));
		// System.out.println(rs.getString("group_name"));
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
			//System.out.println("assessment name:"+asdomain.getAssessmentName());
			if(i==0){
				String query="insert into assignment_info(assignment_id,organization_id,uploaded_by,uploaded_time,uploaded_date,assignment_description,recipient_type,path_of_file,assignment_name) values(?,?,?,?,?,?,?,?,?)";
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
				System.out.println("the query is:"+query);
				int n = ps.executeUpdate();
				System.out.println("query is inserted successfully"+ps);
				
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
	public String insertAssinmentDetails(AssessmentDomain asdomain){
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
			//System.out.println("assessment name:"+asdomain.getAssessmentName());
			if(i==0){
				String query="insert into assignment_info(assignment_id,organization_id,uploaded_by,uploaded_time,uploaded_date,assignment_description,recipient_type,path_of_file,assignment_name) values(?,?,?,?,?,?,?,?,?)";
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
				//System.out.println("the query is:"+query);
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
		return status;
	}
	 public String getKsId(String facultyid,String orgid){
		 String ksid=" ";
		 try{
			 con=DBTransaction.connect();
			 String sql="select ksid from knowledgestore_info where user_id='"+facultyid+"'"+ " and organization_id='"+orgid+"'";
			 ps=con.prepareStatement(sql);
			 rs=ps.executeQuery();
			 //System.out.println("sql query is:"+sql);
			 while(rs.next()){
				 ksid=rs.getString("ksid");
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
		return ksid;
		
	 }
	 public String getGroupValues(String groupid){
		 String student_id=" ";
		 //System.out.println("group id in dao is:"+groupid);
		 try{
			 con=DBTransaction.connect();
			 String sql="select student_id from student_groups where group_name='"+groupid+"'";
			 ps=con.prepareStatement(sql);
			 rs=ps.executeQuery();
			 //System.out.println("sql query is:"+sql);
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
		
	 }public String insertAssinmentAllDetails(AssessmentDomain asdomain){
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
				//System.out.println("assessment name:"+asdomain.getAssessmentName());
				if(i==0){
					String query="insert into assignment_info(assignment_id,organization_id,uploaded_by,uploaded_time,uploaded_date,assignment_description,recipient_type,path_of_file,assignment_name) values(?,?,?,?,?,?,?,?,?)";
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
					//System.out.println("the query is:"+query);
					int n = ps.executeUpdate();
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
			return status;
		}
	 public boolean getAssignmentId(AssessmentDomain adomain)
		{
		
	    	String adminid=adomain.getAssessmentId();
	    	boolean result=false;
	    	try
	    	{
	    	 con = DBTransaction.connect();
	    	String quary="select assignment_id from assignment_info where assignment_id=?";
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
	 public ArrayList<AdminDomain> assignMentReportsDetails(){
 		////System.out.println("dao faculty id:"+faculyid);
 	try{
 	
 		con=DBTransaction.connect();
 		String sql= "select assignment_id from assignment_student_info";
 		ps=con.prepareStatement(sql);
 		rs=ps.executeQuery();
 		//System.out.println("sql query in dao:"+sql);
 		while(rs.next())
 		{
 			AdminDomain cDomain= new AdminDomain();
 		 cDomain.setAssignment_name(rs.getString("assignment_id"));
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
}
