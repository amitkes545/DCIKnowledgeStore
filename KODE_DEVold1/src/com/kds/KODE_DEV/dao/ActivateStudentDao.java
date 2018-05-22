package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.ActiveSessionDomain;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.SessionDomain;
import com.mysql.jdbc.Statement;


public class ActivateStudentDao  {
	//DBTransaction db= new DBTransaction();
	Connection connection= null;	
	
	ResultSet resulSet= null;
	PreparedStatement prepareStatement=null;
	
	
	ArrayList<ActiveSessionDomain> arrayList2=new ArrayList<ActiveSessionDomain>();
	
	ArrayList<ActiveSessionDomain> sessionDetails=new ArrayList<ActiveSessionDomain>();
	ArrayList<AdminDomain> aladmin=new ArrayList<AdminDomain>();
	ActiveSessionDomain studentdomain=new ActiveSessionDomain();
	public int updateStatus (String userid,String status) throws SQLException{
		PreparedStatement stmt=null;
		int count=0;
		try{
			System.out.println();
			connection=DBTransaction.connect();
			stmt=connection.prepareStatement("update  users_info set users_status='"+status+"'"+" where user_id='"+userid+"'");
			count=stmt.executeUpdate();  
		}
				catch(Exception e)
				{
					e.printStackTrace();
				}
		finally{
			if(stmt!=null)
				stmt.close();
		}
		return count;
		}
	
	
	
	public String getOrgId(String userid){
		String orgid="";
		try{
			
			connection=DBTransaction.connect();
			String sql= "select organization_id from superadmin_admin_mapping where admin_user_id='"+userid+"'";
			prepareStatement=connection.prepareStatement(sql);
			resulSet=prepareStatement.executeQuery();
			
			while(resulSet.next())
			{
			
				orgid=resulSet.getString("organization_id");
			
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
		return orgid;	
		}
	
	
	
	public String getSuperAdminID(String userid){
		String superadminid="";
		try{
			
			connection=DBTransaction.connect();
			String sql= "select super_admin_user_id from superadmin_admin_mapping where admin_user_id='"+userid+"'";
			prepareStatement=connection.prepareStatement(sql);
			resulSet=prepareStatement.executeQuery();
			
			while(resulSet.next())
			{
			
				superadminid=resulSet.getString("super_admin_user_id");
			
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
		return superadminid;	
		}
	public String getCourseIdStudent(String userid){
		String courseid="";
		try{
			
			connection=DBTransaction.connect();
			String sql= "select course_id from users_info where user_id='"+userid+"'";
			prepareStatement=connection.prepareStatement(sql);
			resulSet=prepareStatement.executeQuery();
			
			while(resulSet.next())
			{
			
				courseid=resulSet.getString("course_id");
			
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
		return courseid;	
		}


	
	
	public String getOrgEmail(String orgid){
		String email="";
		try{
			
			connection=DBTransaction.connect();
			String sql= "select  email from org_details where organization_id='"+orgid+"'";
			prepareStatement=connection.prepareStatement(sql);
			resulSet=prepareStatement.executeQuery();
			
			while(resulSet.next())
			{
			
				email=resulSet.getString("email");
			
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
		return email;	
		}


	
	
	
	
	
	
	public ArrayList getDecipline(){
		ArrayList <String>decId=new ArrayList<String>();
		try{
			
			connection=DBTransaction.connect();
			String sql= "select discipline_name from transaction_discipline_info";
			prepareStatement=connection.prepareStatement(sql);
			resulSet=prepareStatement.executeQuery();
			
			while(resulSet.next())
			{
			
				decId.add(resulSet.getString("discipline_name"));
			
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
		return decId;	
		}

	

public ArrayList<ActiveSessionDomain> getStuDetails(String courseid,String status, String organization_id){
		String privilege="Student";
		try{
			
			connection=DBTransaction.connect();
			String sql= "select * from users_info where organization_id='"+organization_id+"' and course_id='"+courseid+"' and users_status='"+status+"' and privilege='"+privilege+"'";
			prepareStatement=connection.prepareStatement(sql);
			
			resulSet=prepareStatement.executeQuery();
			
			while(resulSet.next())
			{
				//System.out.println("result set");
				ActiveSessionDomain sessionDomain=new ActiveSessionDomain();
				sessionDomain.setEnrid(resulSet.getString("user_id"));
				sessionDomain.setStudentname(resulSet.getString("username"));
				sessionDomain.setEnrdate(resulSet.getString("current_date_time"));
				sessionDomain.setStatus(resulSet.getString("users_status"));

				
				
                 sessionDetails.add(sessionDomain);
				
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
		return sessionDetails;	
		}
public ArrayList<ActiveSessionDomain> getStudentsForStatus(String courseid, String organization_id){
	String privilege="Student";
	try{
		
		connection=DBTransaction.connect();
		String sql= "select * from users_info where organization_id='"+organization_id+"' and course_id='"+courseid+"' and privilege='"+privilege+"'";
		prepareStatement=connection.prepareStatement(sql);
		
		resulSet=prepareStatement.executeQuery();
		
		while(resulSet.next())
		{
			//System.out.println("result set");
			ActiveSessionDomain sessionDomain=new ActiveSessionDomain();
			sessionDomain.setEnrid(resulSet.getString("user_id"));
			sessionDomain.setStudentname(resulSet.getString("username"));
			sessionDomain.setEnrdate(resulSet.getString("current_date_time"));
			sessionDomain.setStatus(resulSet.getString("users_status"));

			
			
             sessionDetails.add(sessionDomain);
			
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
	return sessionDetails;	
	}


public ArrayList<ActiveSessionDomain> getStuDetailsForAll(String userid,String courseid,String status){
	String privilege="Student";
	try{
		
		connection=DBTransaction.connect();
		String sql= "select * from users_info where course_id='"+courseid+"' and user_id='"+userid+"' and privilege='"+privilege+"'";
		prepareStatement=connection.prepareStatement(sql);
		
		resulSet=prepareStatement.executeQuery();
		
		while(resulSet.next())
		{
			//System.out.println("result set");
			ActiveSessionDomain sessionDomain=new ActiveSessionDomain();
			sessionDomain.setEnrid(resulSet.getString("user_id"));
			sessionDomain.setStudentname(resulSet.getString("username"));
			sessionDomain.setEnrdate(resulSet.getString("current_date_time"));
			sessionDomain.setStatus(resulSet.getString("users_status"));

			
			
             sessionDetails.add(sessionDomain);
			
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
	return sessionDetails;	
	}

public ArrayList<ActiveSessionDomain> getStudentForIndividual(String userid,String courseid,String status,String organization_id){
	String privilege="Student";
	try{
		
		connection=DBTransaction.connect();
		String sql= "select * from users_info where organization_id='"+organization_id+"' and course_id='"+courseid+"' and user_id='"+userid+"' and users_status='"+status+"' and privilege='"+privilege+"'";
		prepareStatement=connection.prepareStatement(sql);
		
		resulSet=prepareStatement.executeQuery();
		
		while(resulSet.next())
		{
			//System.out.println("result set");
			ActiveSessionDomain sessionDomain=new ActiveSessionDomain();
			sessionDomain.setEnrid(resulSet.getString("user_id"));
			sessionDomain.setStudentname(resulSet.getString("username"));
			sessionDomain.setEnrdate(resulSet.getString("current_date_time"));
			sessionDomain.setStatus(resulSet.getString("users_status"));

			
			
             sessionDetails.add(sessionDomain);
			
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
	return sessionDetails;	
	}


public ArrayList<ActiveSessionDomain> getStudentForDocVerification(String streamId){
	String privilege="Student";
	try{
		
		connection=DBTransaction.connect();
	//	String sql= "select enrollment_process_id,full_name from transaction_enrollment_process where course_id in (select department_id from transaction_department_info where department_name='"+streamId+"')";
		
	//	String sql= "select enrollment_id,doc_path from student_documents where enrollment_id in(select enrollment_process_id from transaction_enrollment_process tep where course_id in (select department_id from transaction_department_info where department_name='"+streamId+"'))";
		
		String sql= "select distinct enrollment_id,doc_path,status, tep.full_name from student_documents sd,transaction_enrollment_process tep where enrollment_id in(select enrollment_process_id from transaction_enrollment_process tep where course_id in (select department_id from transaction_department_info where department_name='"+streamId+"'))";
		prepareStatement=connection.prepareStatement(sql);
		
		System.out.println("Query for docs= "+prepareStatement);
		resulSet=prepareStatement.executeQuery();
		
		while(resulSet.next())
		{
			//System.out.println("result set");
		//	String fullname=getFullName(resulSet.getString("enrollment_id"));
			ArrayList<ActiveSessionDomain> getFullData=new ArrayList <ActiveSessionDomain>();
			
			ActiveSessionDomain sessionDomain=new ActiveSessionDomain();
			sessionDomain.setEnrid(resulSet.getString("enrollment_id"));
			sessionDomain.setStudentname(resulSet.getString("full_name"));
			sessionDomain.setDocName(resulSet.getString("doc_path"));		
			sessionDomain.setStatus(resulSet.getString("status"));
             sessionDetails.add(sessionDomain);
			
		}
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}			
	finally
	   {
		   try{
		//   connection.close();
		//   prepareStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	return sessionDetails;	
	}







public ArrayList<ActiveSessionDomain> getStuDetailsStatusWise(String userid,String courseid,String status){
	String privilege="Student";
	try{
		
		connection=DBTransaction.connect();
		String sql= "select * from users_info where course_id='"+courseid+"'"+"and user_id='"+userid+"'"+"and status='"+status+"'"+"and privilege='"+privilege+"'";
		prepareStatement=connection.prepareStatement(sql);
		
		resulSet=prepareStatement.executeQuery();
		
		while(resulSet.next())
		{
			//System.out.println("result set");
			ActiveSessionDomain sessionDomain=new ActiveSessionDomain();
			sessionDomain.setEnrid(resulSet.getString("user_id"));
			sessionDomain.setStudentname(resulSet.getString("username"));
			sessionDomain.setEnrdate(resulSet.getString("current_date_time"));
			sessionDomain.setStatus(resulSet.getString("users_status"));

			
			
             sessionDetails.add(sessionDomain);
			
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
	return sessionDetails;	
	}

	public ArrayList<ActiveSessionDomain> getStudentName(String user_id,String organizationId,String created_by,String course_id){
		String privilege="Student";
		try{
			 
			connection=DBTransaction.connect();
			String sql= "select user_id,username from users_info where organization_id='"+organizationId+"'"+"and course_id='"+course_id+"'"+"and privilege='"+privilege+"'";
			prepareStatement=connection.prepareStatement(sql);
			resulSet=prepareStatement.executeQuery();
			
			while(resulSet.next())
			{
				ActiveSessionDomain sessionDomain=new ActiveSessionDomain();
				sessionDomain.setEnrid(resulSet.getString("user_id"));
				sessionDomain.setStudentname(resulSet.getString("username"));
				arrayList2.add(sessionDomain);
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
		return arrayList2;	
		}
	
public ArrayList<ActiveSessionDomain> getCourseIdForList(String user_id){
	
		try{
			 
			connection=DBTransaction.connect();
			String sql= "select SAM.course_id_defined_by_teaching_source,course_name from superadmin_admin_mapping SAM left join course_description CD "
					+ " on SAM.course_id_defined_by_teaching_source=CD.course_id_defined_by_teaching_source and SAM.organization_id=CD.customer_id where admin_user_id='"+user_id+"'";
			prepareStatement=connection.prepareStatement(sql);
			resulSet=prepareStatement.executeQuery();
			System.out.println("ps in course="+prepareStatement);
			while(resulSet.next())
			{
				ActiveSessionDomain sessionDomain=new ActiveSessionDomain();
				sessionDomain.setCourseid(resulSet.getString("course_id_defined_by_teaching_source"));
				sessionDomain.setCategory(resulSet.getString("course_name"));
				arrayList2.add(sessionDomain);
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
		return arrayList2;	
		}
public ArrayList<ActiveSessionDomain> getCourseList(String org_id){
	
	try{
		 
		connection=DBTransaction.connect();
		String sql= "select course_id_defined_by_teaching_source,course_name from course_description where customer_id='"+org_id+"'";
		prepareStatement=connection.prepareStatement(sql);
		resulSet=prepareStatement.executeQuery();
		System.out.println("ps in course="+prepareStatement);
		while(resulSet.next())
		{
			ActiveSessionDomain sessionDomain=new ActiveSessionDomain();
			sessionDomain.setCourseid(resulSet.getString("course_id_defined_by_teaching_source"));
			sessionDomain.setCategory(resulSet.getString("course_name"));
			arrayList2.add(sessionDomain);
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
	return arrayList2;	
	}
public int updateCourseToStudent(ArrayList<AdminDomain> list){
	
	String status="";
	 int counter=0;
	try {
		
		
		connection=DBTransaction.connect();
		String sql="update users_info set course_id=? where user_id=? and organization_id=?";
		prepareStatement = connection.prepareStatement(sql);
		//System.out.println("query for update session="+sql);
		System.out.println("list size="+list.size());
		for(AdminDomain s:list){
			prepareStatement.setString(1, s.getCity());
			prepareStatement.setString(2,s.getAdminId());
			prepareStatement.setString(3,s.getOrgid());
			
			prepareStatement.addBatch();
			//System.out.println("query string in dao during session modify:"+prepareStatement);
		}
		//System.out.println("query string in dao:"+prepareStatement);
		
		int[] count=prepareStatement.executeBatch();
		System.out.println(count.length+" values are inserted");
		counter=counter+count.length;
		//System.out.println("counter="+counter);
		//System.out.println("query string in dao:"+sql);
		if(count!=null){
			status="success";
		}else {
			status="failure";
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
	return counter;
}

public ArrayList<AdminDomain> studentsList(String org_id){
	
	try{
		 
		connection=DBTransaction.connect();
		String sql= "select user_id,password,email,username from users_info where organization_id='"+org_id+"' and (course_id is null or course_id='')";
		prepareStatement=connection.prepareStatement(sql);
		resulSet=prepareStatement.executeQuery();
		System.out.println("ps in course="+prepareStatement);
		while(resulSet.next())
		{
			AdminDomain aDomain=new AdminDomain();
			aDomain.setAdminId(resulSet.getString("user_id"));
			aDomain.setPwd(resulSet.getString("password"));
			aDomain.setEmail(resulSet.getString("email"));
			aDomain.setAdminName(resulSet.getString("username"));
			
			aladmin.add(aDomain);
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
	return aladmin;	
	}
	public ArrayList<ActiveSessionDomain> getStudentName(String orgid,String courseid){
		
		try{
			
			connection=DBTransaction.connect();
			String sql= "select * from users_info where course_id='"+courseid+"'"+"and organization_id='"+orgid+"'"+"";
			prepareStatement=connection.prepareStatement(sql);
			//System.out.println("query is:"+prepareStatement);
			resulSet=prepareStatement.executeQuery();
			
			while(resulSet.next())
			{
				//System.out.println("result set");
				ActiveSessionDomain sessionDomain=new ActiveSessionDomain();
				//sessionDomain.setSessionId(resulSet.getString("session_id"));
				//sessionDomain.setSessionName(resulSet.getString("session_name"));
				//sessionDomain.setCategory(resulSet.getString("category"));
				//sessionDomain.setDuration(resulSet.getString("duration"));
				//sessionDomain.setCostOfSession(resulSet.getString("cost_of_session"));
				//sessionDomain.setRecipientType(resulSet.getString("recipient_type"));
				sessionDomain.setCurrent_date_time(resulSet.getString("user_id"));
				//sessionDomain.setSessionEndTime(resulSet.getString("session_end_time"));
				sessionDomain.setStatus(resulSet.getString("usernname"));
                 sessionDetails.add(sessionDomain);
				//System.out.println(resulSet.getString("session_start_time"));
			}
			//sessionDomain.setSessionName(sessionId);
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
		return sessionDetails;	
		}
public ArrayList<ActiveSessionDomain> getSesstionAllDetails(String status,String courseid){
		String privilege="Student";
		try{
			
			connection=DBTransaction.connect();
			String sql= "select * from users_info where users_status='"+status+"'"+" and course_id='"+courseid+"'"+"and privilege='"+privilege+"'";
			prepareStatement=connection.prepareStatement(sql);
			
			resulSet=prepareStatement.executeQuery();
			
			while(resulSet.next())
			{
				
				ActiveSessionDomain sessionDomain=new ActiveSessionDomain();
				sessionDomain.setEnrid(resulSet.getString("user_id"));
				sessionDomain.setStudentname(resulSet.getString("username"));
				sessionDomain.setEnrdate(resulSet.getString("current_date_time"));
				sessionDomain.setStatus(resulSet.getString("users_status"));
				sessionDetails.add(sessionDomain);
				
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
		return sessionDetails;	
}

public ArrayList<ActiveSessionDomain> getIndividualStudent(String selectstudent,String status,String courseid){
	String privilege="Student";
	try{
		
		connection=DBTransaction.connect();
		String sql= "select * from users_info where users_status='"+status+"'"+" and course_id='"+courseid+"'"+" and user_id='"+selectstudent+"'"+"and privilege='"+privilege+"'";
		prepareStatement=connection.prepareStatement(sql);
		
		resulSet=prepareStatement.executeQuery();
		
		while(resulSet.next())
		{
			
			ActiveSessionDomain sessionDomain=new ActiveSessionDomain();
			sessionDomain.setEnrid(resulSet.getString("user_id"));
			sessionDomain.setStudentname(resulSet.getString("username"));
			sessionDomain.setEnrdate(resulSet.getString("current_date_time"));
			sessionDomain.setStatus(resulSet.getString("users_status"));
			sessionDetails.add(sessionDomain);
			
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
	return sessionDetails;	
}

public ArrayList<ActiveSessionDomain> getSesstionAllDetailsForStatusAll(String courseid){
	String privilege="Student";
	try{
		
		connection=DBTransaction.connect();
		String sql= "select * from users_info where course_id='"+courseid+"'"+"and privilege='"+privilege+"'";
		prepareStatement=connection.prepareStatement(sql);
		
		resulSet=prepareStatement.executeQuery();
		
		while(resulSet.next())
		{
			
			ActiveSessionDomain sessionDomain=new ActiveSessionDomain();
			sessionDomain.setEnrid(resulSet.getString("user_id"));
			sessionDomain.setStudentname(resulSet.getString("username"));
			sessionDomain.setEnrdate(resulSet.getString("current_date_time"));
			sessionDomain.setStatus(resulSet.getString("users_status"));
			sessionDetails.add(sessionDomain);
			
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
	return sessionDetails;	
}
	public String updateSessionTime(SessionDomain sdomain)
	{
	

        String status = null;
		
		try {

			connection = DBTransaction.connect();
			String quary="update session_details set duration=? , session_start_time=? , session_end_time=? where faculty_name=? and organization_id= ? and session_name=?";
			prepareStatement = connection.prepareStatement(quary);
			prepareStatement.setString(1,sdomain.getDuration());
			prepareStatement.setString(2,sdomain.getSessionStartTime());
			prepareStatement.setString(3,sdomain.getSessionEndTime());
			prepareStatement.setString(4,sdomain.getFacultyId());
			prepareStatement.setString(5, sdomain.getOrgId());
			prepareStatement.setString(6,sdomain.getSessionName());
			//System.out.println("the quary is:"+prepareStatement);
			int n = prepareStatement.executeUpdate();
			
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
	

	
	
//	
	
	
	


	



public String updateSessionDetails(ArrayList<SessionDomain> list){
	
	String status="";
	try {
		
		
		connection=DBTransaction.connect();
		String sql="update session_details set session_name=?,category=?,duration=? , session_start_time=? ,cost_of_session=?,recipient_type=?,session_status=?, session_end_time=? where session_id=?";
		prepareStatement = connection.prepareStatement(sql);
		//System.out.println("query is"+sql);
		for(SessionDomain s:list){
			prepareStatement.setString(1, s.getSessionName());
			prepareStatement.setString(2,s.getCategory());
			prepareStatement.setString(3,s.getDuration());
			prepareStatement.setString(4,s.getSessionStartTime());
			prepareStatement.setString(5,s.getCostOfSession());
			prepareStatement.setString(6,s.getRecipientType());
			prepareStatement.setString(7,s.getStatus());
			prepareStatement.setString(8,s.getSessionEndTime());
			prepareStatement.setString(9,s.getSessionId());
			prepareStatement.addBatch();
		}
		System.out.println("query string in dao:"+sql);
		
		int[] count=prepareStatement.executeBatch();
		System.out.println(count+"values are inserted");
		if(count!=null){
			status="success";
		}else {
			status="failure";
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
	return status;
}
public ArrayList<String> getAllUsersId(SessionDomain sdomain){
	
	ArrayList<String> ul=new ArrayList<String>();
	try{
		
		connection=DBTransaction.connect();
		
		 String sql = "select user_id from users_info where organization_id='"+sdomain.getOrgId()+"'"+" and created_by='"+sdomain.getFacultyId()+"'";
		 prepareStatement = connection.prepareStatement(sql);
		    resulSet=prepareStatement.executeQuery();
		    //System.out.println("sql query is:"+prepareStatement);
		    while(resulSet.next()){
		    	ul.add(resulSet.getString("user_id"));
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
	return ul;	
	}
public String getGroupName(String facultyid,String organizationId,String groupVale){
	String groupName="";
	try{
		 
		connection=DBTransaction.connect();
		String sql= "select group_name from student_groups where created_by='"+facultyid+"'"+" and organization_id='"+organizationId+"'"+" and student_id='"+groupVale+"'";
		prepareStatement=connection.prepareStatement(sql);
		resulSet=prepareStatement.executeQuery();
		//System.out.println("query::"+prepareStatement);
		while(resulSet.next())
		{
			groupName=resulSet.getString("group_name");
			//System.out.println("groupName::"+groupName);
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
	return groupName;	
}


public String getFullName(String enrid){
	String fullName="";
	try{
		 
		connection=DBTransaction.connect();
		String sql= "select full_name from transaction_enrollment_process where enrollment_process_id='"+enrid+"'";

		prepareStatement=connection.prepareStatement(sql);
		resulSet=prepareStatement.executeQuery();
		//System.out.println("query::"+prepareStatement);
		while(resulSet.next())
		{
			fullName=resulSet.getString("full_name");
			System.out.println("groupName::"+fullName);
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}			
	finally
	   {
		   try{
		//   connection.close();
		//   prepareStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	return fullName;	
}
public ArrayList<SessionDomain> getAllEmailIdUserName(ArrayList<String> usersId){
	
	ArrayList<SessionDomain> al1=new ArrayList<SessionDomain>();
	try{
		
		connection=DBTransaction.connect();
		java.util.Iterator<String> it=usersId.iterator();
		while(it.hasNext()){
		 String sql = "select email,username from users_info where user_id='"+it.next()+"'";
		 prepareStatement = connection.prepareStatement(sql);
		
		    resulSet=prepareStatement.executeQuery();
		
		    while(resulSet.next()){
		    	SessionDomain domain=new SessionDomain();
		    	domain.setEmail(resulSet.getString("email"));
		    	domain.setStudentName(resulSet.getString("username"));
		    	al1.add(domain);
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
	return al1;
}
public ArrayList<SessionDomain> getEmailGroupIdUserName(String[] groupid){
	
	ArrayList<SessionDomain> al2=new ArrayList<SessionDomain>();
	try{
		
		connection=DBTransaction.connect();
		for(int i=0;i<groupid.length;i++){
		 String sql = "select email,username from users_info where user_id='"+groupid[i]+"'";
		 prepareStatement = connection.prepareStatement(sql);
		
		    resulSet=prepareStatement.executeQuery();
		
		    while(resulSet.next()){
		    	SessionDomain domain=new SessionDomain();
		    	domain.setEmail(resulSet.getString("email"));
		    	domain.setStudentName(resulSet.getString("username"));
		    	al2.add(domain);
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
	return al2;	
	}
public ArrayList<SessionDomain> getEmailIdUserName(String studentid){
	ArrayList<SessionDomain> al3=new ArrayList<SessionDomain>();
	try{
		
		connection=DBTransaction.connect();
		String sql= "select email,username from users_info where user_id='"+studentid+"'";
		prepareStatement=connection.prepareStatement(sql);
		resulSet=prepareStatement.executeQuery();
		
		while(resulSet.next())
		{
			SessionDomain domain=new SessionDomain();
	    	domain.setEmail(resulSet.getString("email"));
	    	domain.setStudentName(resulSet.getString("username"));
	    	al3.add(domain);
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
	return al3;	
	}


public int updateStatusForDoc (String enrid,String status) throws SQLException{
	PreparedStatement stmt=null;
	int count=0;

	System.out.println("status="+status);
	
	try{
		System.out.println();
		connection=DBTransaction.connect();
		stmt=connection.prepareStatement("update  student_documents set status='"+status+"'"+" where enrollment_id='"+enrid+"'");
		System.out.println("enrollment id quey="+stmt);
		count=stmt.executeUpdate();  
	}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	finally{
		if(stmt!=null)
			stmt.close();
	}
	return count;
	}
/*
public String getEmailId (String enrid) throws SQLException{
	PreparedStatement stmt=null;
	String email="";

	
	try{
		System.out.println();
		connection=DBTransaction.connect();
		stmt=connection.prepareStatement("select email  from transaction_enrollment_process where enrollment_process_id='"+enrid+"'");
		
		
		resulSet=stmt.executeQuery();
		
		while(resulSet.next())
		{
		
			email=resulSet.getString("email");
		
		}
		
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	finally{
		if(stmt!=null)
			stmt.close();
	}
	return email;
	}
*/


public HashMap<String,String> getEmailId (String enrid) throws SQLException{
	PreparedStatement stmt=null;
	String email="";
HashMap <String,String> hmap=new HashMap<String,String>();
	
	try{
		System.out.println();
		connection=DBTransaction.connect();
		stmt=connection.prepareStatement("select email,password  from users_info where user_id='"+enrid+"'");
		
		
		resulSet=stmt.executeQuery();
		
		while(resulSet.next())
		{
			hmap.put(resulSet.getString("email"),resulSet.getString("password"));
			
		
		}
		
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	finally{
		if(stmt!=null)
			stmt.close();
	}
	return hmap;
	}


public ArrayList<String> getEmailData(String studentid){
	ArrayList<SessionDomain> al3=new ArrayList<SessionDomain>();
	ArrayList <String> alist=new ArrayList<String>();
	try{
		
		connection=DBTransaction.connect();
		String sql= "select full_name,middle_layer_teaching_source_name,email from transaction_enrollment_process where enrollment_process_id='"+studentid+"'";
		prepareStatement=connection.prepareStatement(sql);
		resulSet=prepareStatement.executeQuery();
		
		while(resulSet.next())
		{
			
	    alist.add(resulSet.getString("full_name"));
	    alist.add(resulSet.getString("middle_layer_teaching_source_name"));
	    alist.add(resulSet.getString("email"));
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
	return alist;	
	}

public ArrayList<ActiveSessionDomain> getStudentForDocVerification(String streamId,String orgId){
	String privilege="Student";
	try{
		
		connection=DBTransaction.connect();
	//	String sql= "select enrollment_process_id,full_name from transaction_enrollment_process where course_id in (select department_id from transaction_department_info where department_name='"+streamId+"')";
		
	//	String sql= "select enrollment_id,doc_path from student_documents where enrollment_id in(select enrollment_process_id from transaction_enrollment_process tep where course_id in (select department_id from transaction_department_info where department_name='"+streamId+"'))";
		
		//String sql= "select distinct enrollment_id,doc_path,status, tep.full_name from student_documents sd,transaction_enrollment_process tep where enrollment_id in(select enrollment_process_id from transaction_enrollment_process tep where course_id in (select department_id from transaction_department_info where department_name='"+streamId+"'))";
	
		String sql="select sd.enrollment_id,sd.doc_path,sd.status,enr.full_name from student_documents sd, transaction_enrollment_process enr where sd.enrollment_id=enr.enrollment_process_id and enr.course_id='"+streamId+"' and enr.middle_layer_teaching_source_name in (select middle_layer_teaching_source_name from transaction_middle_layer_teaching_source where organization_id ='"+orgId+"')";



		
		prepareStatement=connection.prepareStatement(sql);
		
		System.out.println("Query for docs= "+prepareStatement);
		resulSet=prepareStatement.executeQuery();
		
		while(resulSet.next())
		{
			//System.out.println("result set");
		//	String fullname=getFullName(resulSet.getString("enrollment_id"));
			ArrayList<ActiveSessionDomain> getFullData=new ArrayList <ActiveSessionDomain>();
			
			ActiveSessionDomain sessionDomain=new ActiveSessionDomain();
			sessionDomain.setEnrid(resulSet.getString("enrollment_id"));
			sessionDomain.setStudentname(resulSet.getString("full_name"));
			sessionDomain.setDocName(resulSet.getString("doc_path"));		
			sessionDomain.setStatus(resulSet.getString("status"));
             sessionDetails.add(sessionDomain);
			
		}
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}			
	finally
	   {
		   try{
		//   connection.close();
		//   prepareStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
	return sessionDetails;	
	}


}
