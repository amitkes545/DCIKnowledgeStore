package com.kds.KODE_DEV.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.SessionDomain;
import com.kds.KODE_DEV.domain.UsersInfoDomain;


public class SessionDao   {
	//DBTransaction dbT = new DBTransaction();
	Connection connection=null;
	PreparedStatement prepareStatement=null;
	ResultSet resultSet=null;
      SessionDomain al= new  SessionDomain();
	public ArrayList<SessionDomain> retriveSessionId() {
		ArrayList<SessionDomain> al= new ArrayList<SessionDomain>();
			try {
				connection = DBTransaction.connect();
				String sql = "select session_id from session_details";
				prepareStatement = connection.prepareStatement(sql);
				resultSet=prepareStatement.executeQuery();
				
				while(resultSet.next()){
					SessionDomain sDomain= new SessionDomain();
					sDomain.setSessionId(resultSet.getString("session_id"));
					////System.out.println("session id are"+rs.getString("session_id"));
					 al.add(sDomain);
				}

			} catch (Exception e) {
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
	public String updateSessionDetails(SessionDomain sdomain){
		String status=null;
		try {
			connection = DBTransaction.connect();
			String query="update session_details set session_name=?, category=?,cost_of_session=?,faculty_name=?,organization_id=?,recipient_type=? where session_id=?";
			prepareStatement=connection.prepareStatement(query);
			
			prepareStatement.setString(1,sdomain.getSessionName());
			prepareStatement.setString(2,sdomain.getCategory());
			prepareStatement.setString(3,sdomain.getCostOfSession());
			prepareStatement.setString(4,sdomain.getFacultyId());
			prepareStatement.setString(5,sdomain.getOrgId());
			prepareStatement.setString(6,sdomain.getRecipientType());
			prepareStatement.setString(7,sdomain.getSessionId());
			
			//System.out.println("the query is:"+query);
			
			int n=prepareStatement.executeUpdate();
			 if(n>=1)
                 status="success";
             else
             	status=" failure";
	}catch(Exception e)
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
	public String updateSessionGroupId(SessionDomain sdomain)
	{
		
        String status = null;
		//System.out.println("group values in dao:"+sdomain.getGroupId());
		try {

			connection = DBTransaction.connect();
			String quary="update session_details set session_name=?, category=?,cost_of_session=?,faculty_name=?,organization_id=?,recipient_type=? where session_id=?";
					
			prepareStatement = connection.prepareStatement(quary);
			//ps.setString(1,);
			prepareStatement.setString(1,sdomain.getSessionName());
			prepareStatement.setString(2,sdomain.getCategory());
			
			prepareStatement.setString(3,sdomain.getCostOfSession());
			prepareStatement.setString(4,sdomain.getFacultyId());
			prepareStatement.setString(5,sdomain.getOrgId());
			prepareStatement.setString(6,sdomain.getGroupId());
			
			prepareStatement.setString(7,sdomain.getSessionId());
			//System.out.println("the query is:"+prepareStatement);
			int n = prepareStatement.executeUpdate();
			//System.out.println("query is inserted successfully");
			if (n != 0)
				status = "valid";
			else
				status = "notvalid";
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
		return status;
	}public String updateSessionIndividual(SessionDomain sdomain)
	{
		
        String status = null;
		
		try {

			connection = DBTransaction.connect();
			String quary="update session_details set session_name=?, category=?,cost_of_session=?,faculty_name=?,organization_id=?,recipient_type=? where session_id=?";
					
			prepareStatement = connection.prepareStatement(quary);
			//ps.setString(1,);
			prepareStatement.setString(1,sdomain.getSessionName());
			prepareStatement.setString(2,sdomain.getCategory());
			
			prepareStatement.setString(3,sdomain.getCostOfSession());
			prepareStatement.setString(4,sdomain.getFacultyId());
			prepareStatement.setString(5,sdomain.getOrgId());
			prepareStatement.setString(6,sdomain.getIndividualId());
			
			prepareStatement.setString(7,sdomain.getSessionId());
			//System.out.println("the quary is:"+prepareStatement);
			int n = prepareStatement.executeUpdate();
			//System.out.println("query is inserted successfully");
			if (n != 0)
				status = "valid";
			else
				status = "notvalid";
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
		return status;
	}
	public String updateSessionAll(SessionDomain sdomain)
	{
		
        String status = null;
		
		try {

			connection = DBTransaction.connect();
			String quary="update session_details set session_name=?, category=?,cost_of_session=?,faculty_name=?,organization_id=?,recipient_type=?,file_path=? where session_id=?";
					
			prepareStatement = connection.prepareStatement(quary);
			
			prepareStatement.setString(1,sdomain.getSessionName());
			prepareStatement.setString(2,sdomain.getCategory());
			
			prepareStatement.setString(3,sdomain.getCostOfSession());
			prepareStatement.setString(4,sdomain.getFacultyId());
			prepareStatement.setString(5,sdomain.getOrgId());
			prepareStatement.setString(6,"all");
			
			prepareStatement.setString(7,sdomain.getSessionId());
			//System.out.println("the quary is:"+prepareStatement);
			int n = prepareStatement.executeUpdate();
			//System.out.println("query is inserted successfully");
			if (n != 0)
				status = "valid";
			else
				status = "notvalid";
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
		return status;
			
}
	public SessionDomain getSessionId(String facultyid,String orgid,String sessionName){
		//String status="";
		SessionDomain sessionDomain=new SessionDomain(); 
		try{
			connection = DBTransaction.connect();
			 String query="select session_id,category,cost_of_session from session_details where faculty_name='"+facultyid+"'"+"and organization_id='"+orgid+"'"+" and session_name='"+sessionName+"'";
			 prepareStatement = connection.prepareStatement(query);

				//System.out.println("the quary is:" + query);
				resultSet=prepareStatement.executeQuery();
				
				while(resultSet.next()){
					sessionDomain.setSessionId(resultSet.getString("session_id"));
					sessionDomain.setCategory(resultSet.getString("category"));
					sessionDomain.setCostOfSession(resultSet.getString("cost_of_session"));
				}

			} catch (Exception e) {
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
		//System.out.println("status value:"+sessionDomain);
			return sessionDomain;
}
	public String insertSessionAll(SessionDomain sdomain)
	{
		
        String status = null;
		//System.out.println("group values in dao:"+sdomain.getGroupId());
		try {

			connection = DBTransaction.connect();
			String query="update session_details set session_name=?, category=?,cost_of_session=?,faculty_name=?,organization_id=?,recipient_type=?,file_path=? where session_id=?";
			prepareStatement=connection.prepareStatement(query);
			
			prepareStatement.setString(1,sdomain.getSessionName());
			prepareStatement.setString(2,sdomain.getCategory());
			prepareStatement.setString(3,sdomain.getCostOfSession());
			prepareStatement.setString(4,sdomain.getFacultyId());
			prepareStatement.setString(5,sdomain.getOrgId());
			prepareStatement.setString(6,sdomain.getAll());
			prepareStatement.setString(7,sdomain.getPathOfFile());
			prepareStatement.setString(8,sdomain.getSessionId());
			
			//System.out.println("the query is:"+query);
			
			int n=prepareStatement.executeUpdate();
			 if(n>=1)
                 status="valid";
             else
             	status="notvalid";
	}catch(Exception e)
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
	public String insertSessionGroupId(SessionDomain sdomain)
	{
		
        String status = null;
		//System.out.println("group values in dao:"+sdomain.getGroupId());
		try {

			connection = DBTransaction.connect();
			String query="update session_details set session_name=?, category=?,cost_of_session=?,faculty_name=?,organization_id=?,recipient_type=? where session_id=?";
			prepareStatement=connection.prepareStatement(query);
			
			prepareStatement.setString(1,sdomain.getSessionName());
			prepareStatement.setString(2,sdomain.getCategory());
			prepareStatement.setString(3,sdomain.getCostOfSession());
			prepareStatement.setString(4,sdomain.getFacultyId());
			prepareStatement.setString(5,sdomain.getOrgId());
			prepareStatement.setString(6,sdomain.getGroupId());
			prepareStatement.setString(7,sdomain.getPathOfFile());
			prepareStatement.setString(8,sdomain.getSessionId());
			
			//System.out.println("the query is:"+query);
			
			int n=prepareStatement.executeUpdate();
			 if(n>=1)
                 status="valid";
             else
             	status="notvalid";
	}catch(Exception e)
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
	
	public String insertSessionIndividual(SessionDomain sdomain)
	{
		
        String status = null;
		//System.out.println("group values in dao:"+sdomain.getGroupId());
		try {

			connection = DBTransaction.connect();
			String query="update session_details set session_name=?, category=?,cost_of_session=?,faculty_name=?,organization_id=?,recipient_type=?,file_path=? where session_id=?";
			prepareStatement=connection.prepareStatement(query);
			
			prepareStatement.setString(1,sdomain.getSessionName());
			prepareStatement.setString(2,sdomain.getCategory());
			prepareStatement.setString(3,sdomain.getCostOfSession());
			prepareStatement.setString(4,sdomain.getFacultyId());
			prepareStatement.setString(5,sdomain.getOrgId());
			prepareStatement.setString(6,sdomain.getIndividualId());
			prepareStatement.setString(7,sdomain.getPathOfFile());
			prepareStatement.setString(8,sdomain.getSessionId());
			
			//System.out.println("the query is:"+query);
			
			int n=prepareStatement.executeUpdate();
			 if(n>=1)
                 status="valid";
             else
             	status="notvalid";
	}catch(Exception e)
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
public ArrayList<AdminDomain> getAllStudentEmailId(ArrayList<AdminDomain> usersId,String organizationId){
		
		ArrayList<AdminDomain> userIds=new ArrayList<AdminDomain>();
		try{
			
			connection=DBTransaction.connect();
			java.util.Iterator<AdminDomain> iterator=usersId.iterator();
			while(iterator.hasNext()){
				AdminDomain domain=iterator.next();
			 String sql = "select email from users_info where user_id='"+domain.getAdminId()+"'"+" and organization_id='"+organizationId+"'";
			 prepareStatement = connection.prepareStatement(sql);
			//System.out.println("sql query is:"+prepareStatement);
			 resultSet=prepareStatement.executeQuery();
			
			    while(resultSet.next()){
			    	AdminDomain adomain=new AdminDomain();
			    	adomain.setAdminId(resultSet.getString("email"));
			    	//System.out.println("student ids in dao:"+adomain.getAdminId());
			    	userIds.add(adomain);
			    }
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
		return userIds;	
		}
public ArrayList<UsersInfoDomain> getAllStudentEmailIdForUpdate(ArrayList<UsersInfoDomain> usersId,String organizationId){
	
	ArrayList<UsersInfoDomain> userIds=new ArrayList<UsersInfoDomain>();
	try{
		
		connection=DBTransaction.connect();
		java.util.Iterator<UsersInfoDomain> iterator=usersId.iterator();
		while(iterator.hasNext()){
			UsersInfoDomain domain=iterator.next();
		 String sql = "select email from users_info where user_id='"+domain.getUserId()+"'"+" and organization_id='"+organizationId+"'";
		 prepareStatement = connection.prepareStatement(sql);
		//System.out.println("sql query is:"+prepareStatement);
		 resultSet=prepareStatement.executeQuery();
		
		    while(resultSet.next()){
		    	UsersInfoDomain adomain=new UsersInfoDomain();
		    	adomain.setEmail(resultSet.getString("email"));
		    	adomain.setUserId(domain.getUserId());
		    	//System.out.println("student ids in dao:"+adomain.getAdminId());
		    	userIds.add(adomain);
		    }
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
	return userIds;	
	}
	
	}
	



	
