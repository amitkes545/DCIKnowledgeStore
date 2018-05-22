package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.SessionDomain;


public class CreateSessionDao  {
	//DBTransaction db= new DBTransaction();
	Connection connection= null;	
	
	ResultSet resulSet= null;
	PreparedStatement prepareStatement=null;
	
	ArrayList<AdminDomain> al= new ArrayList<AdminDomain>();
	ArrayList<SessionDomain> arrayList2=new ArrayList<SessionDomain>();
	ArrayList<SessionDomain> sessionDetails=new ArrayList<SessionDomain>();
	
	public ArrayList<AdminDomain> getSubjectId(String facultyid){
		
	try{
		
		connection=DBTransaction.connect();
		//String sql= "select subject_id from faculty_subject_mapping where faculty_id='"+facultyid+"'";
		//Qurry added by Prity on 11 March' 16
		//Because in subject_id, value is showing like Sub1,Sub2
		/*String sql= "select distinct discipline_master.discipline from faculty_subject_mapping inner join discipline_master on discipline_master.discipline_id=faculty_subject_mapping.discipline where faculty_id='"+facultyid+"'";*/
		String sql="select * from course_description where customer_id = '"+facultyid+"'";
		prepareStatement=connection.prepareStatement(sql);
		resulSet=prepareStatement.executeQuery();
		
		while(resulSet.next())
		{
			AdminDomain cDomain= new AdminDomain();
		 cDomain.setSubject_id(resulSet.getString("discipline"));
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
	//added by prity to check conflict during modify means just excluse the sesid
	public boolean isConflictTimingModify(SessionDomain sdomain) 
	{
		boolean status=false;
		try {
			System.out.println("in conflict");
			connection = DBTransaction.connect();
			String selectQuery="select * from session_details where faculty_name=? and ((cast(? as timestamp) <= cast(session_start_time as timestamp) and cast(? as timestamp) > cast(session_start_time as timestamp)) or (cast(? as timestamp) >= cast(session_start_time as timestamp) and cast(? as timestamp) < cast(session_end_time as timestamp)) ) and organization_id=? and session_id!=? and session_status!='Cancel'";
			prepareStatement=connection.prepareStatement(selectQuery);
			prepareStatement.setString(2,sdomain.getSessionStartTime());
			prepareStatement.setString(4,sdomain.getSessionStartTime());
			prepareStatement.setString(5,sdomain.getSessionStartTime());
			prepareStatement.setString(1,sdomain.getFacultyId());
			prepareStatement.setString(6,sdomain.getOrgId());
			prepareStatement.setString(3,sdomain.getSessionEndTime());
			prepareStatement.setString(7,sdomain.getSessionId());
			System.out.println("conflict query in modify:"+prepareStatement.toString());
			resulSet=prepareStatement.executeQuery();
			if(resulSet.next())
			{
				status=true;
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
//by prity to find time conflict	
	public boolean isConflictTiming(SessionDomain sdomain) 
	{
		boolean status=false;
		try {
			System.out.println("in conflict");
			connection = DBTransaction.connect();
			String selectQuery="select * from session_details where faculty_name=? and ((cast(? as timestamp) <= cast(session_start_time as timestamp) and cast(? as timestamp) > cast(session_start_time as timestamp)) or (cast(? as timestamp) >= cast(session_start_time as timestamp) and cast(? as timestamp) < cast(session_end_time as timestamp)) ) and organization_id=? and session_status!='Cancel' ";
			prepareStatement=connection.prepareStatement(selectQuery);
			prepareStatement.setString(2,sdomain.getSessionStartTime());
			prepareStatement.setString(4,sdomain.getSessionStartTime());
			prepareStatement.setString(5,sdomain.getSessionStartTime());
			prepareStatement.setString(1,sdomain.getFacultyId());
			prepareStatement.setString(6,sdomain.getOrgId());
			prepareStatement.setString(3,sdomain.getSessionEndTime());
			System.out.println("conflict query:"+prepareStatement.toString());
			resulSet=prepareStatement.executeQuery();
			if(resulSet.next())
			{
				status=true;
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
	}  ///end
	public String insertSessionAll(SessionDomain sdomain)
	{
		
        String status = null;
		
		try {

			connection = DBTransaction.connect();
			String insertQuary="insert into session_details(session_name, category, duration, session_start_time, cost_of_session, faculty_name, organization_id,recipient_type,session_end_time,session_status,subject_id,topic_id,subtopic_id) " +
					"values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			prepareStatement = connection.prepareStatement(insertQuary);
			//ps.setString(1,);
			prepareStatement.setString(1,sdomain.getSessionName());
			prepareStatement.setString(2,sdomain.getCategory());
			prepareStatement.setString(3,sdomain.getDuration());
			prepareStatement.setString(4,sdomain.getSessionStartTime());
			prepareStatement.setString(5,sdomain.getCostOfSession());
			prepareStatement.setString(6,sdomain.getFacultyId());
			prepareStatement.setString(7,sdomain.getOrgId());
			prepareStatement.setString(8,"all");
			prepareStatement.setString(9,sdomain.getSessionEndTime());
			prepareStatement.setString(10,"Available");
			prepareStatement.setString(11,sdomain.getSubjectId());
			prepareStatement.setString(12,sdomain.getTopicId());
			prepareStatement.setString(13,sdomain.getSubtopicId());
			
			//System.out.println("the quary is:"+prepareStatement);
			int n = prepareStatement.executeUpdate();
			//System.out.println("query is inserted successfully");
			if (n ==1){
				//status = "valid";
				String selectQuery="SELECT currval('course_details_course_id_seq')";
				prepareStatement=connection.prepareStatement(selectQuery);
				resulSet=prepareStatement.executeQuery();
				
				while(resulSet.next())
				{
					status="SES"+resulSet.getString(1);
				
				}
				
			}
			   
			else{
				status = "notvalid";
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
		String check="invalid";
		if(!"invalid".equals(status))
			check=insertInTimeTable(sdomain,status);
		
		if("invalid".equals(check))
			 status="invalid";
		
		return status;
			
}
	public String insertSessionIndividual(SessionDomain sdomain)
	{
		
        String status = null;
		
		try {
			System.out.println("in dao"+sdomain.getSessionStartTime()+"...."+sdomain.getSessionEndTime());
			connection = DBTransaction.connect();
			String quary="insert into session_details(session_name, category, duration, session_start_time, cost_of_session, faculty_name, organization_id,recipient_type,session_end_time,session_status,subject_id,topic_id,subtopic_id) " +
					"values(?,?,?,'"+sdomain.getSessionStartTime()+"',?,?,?,?,'"+sdomain.getSessionEndTime()+"',?,?,?,?)";
			prepareStatement = connection.prepareStatement(quary);
			//ps.setString(1,);
			System.out.println("the quary is.....:"+prepareStatement);
			prepareStatement.setString(1,sdomain.getSessionName());
			prepareStatement.setString(2,sdomain.getCategory());
			prepareStatement.setString(3,sdomain.getDuration());
		//	prepareStatement.setString(4,sdomain.getSessionStartTime());
			prepareStatement.setString(4,sdomain.getCostOfSession());
			prepareStatement.setString(5,sdomain.getFacultyId());
			prepareStatement.setString(6,sdomain.getOrgId());
			prepareStatement.setString(7,sdomain.getIndividualId());
			//prepareStatement.setString(9,sdomain.getSessionEndTime());
			prepareStatement.setString(8,"Available");
			prepareStatement.setString(9,sdomain.getSubjectId());
			prepareStatement.setString(10,sdomain.getTopicId());
			prepareStatement.setString(11,sdomain.getSubtopicId());
		
			sdomain.setStatus("Available");
			System.out.println("the quary is:"+prepareStatement);
			//prepareStatement = connection.prepareStatement(quary);
			int n = prepareStatement.executeUpdate();
			System.out.println("query is inserted successfully"+prepareStatement);
			//System.out.println("valu of n :"+n);
			if (n==1){
				//status = "valid";
				String selectQuery="SELECT currval('course_details_course_id_seq')";
				prepareStatement=connection.prepareStatement(selectQuery);
				resulSet=prepareStatement.executeQuery();
				
				while(resulSet.next())
				{
					status="SES"+resulSet.getString(1);
				
				}
				
			}
			else{
				status = "notvalid";
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
		String check="invalid";
		if(!"invalid".equals(status))
			check=insertInTimeTable(sdomain,status);
		
		if("invalid".equals(check))
			 status="invalid";
		
		
		return status;
	}
	public String insertInTimeTable(SessionDomain sdomain, String sessionId)
	{
		  String status = null;
			
			try {
				System.out.println("in dao"+sdomain.getSessionStartTime()+"...."+sdomain.getSessionEndTime());
				connection = DBTransaction.connect();
				// count number of fields 
				String quary="INSERT INTO time_table( session_id, syllabus_id, session_name, subject_id, topic_id, sub_topic_id, faculty_id, session_start_datetime, session_end_datetime, session_status, organization_id, created_datetime, last_updated_datetime, mod_user_id) "+
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), now(), ?);";
				
				
				
				prepareStatement = connection.prepareStatement(quary);
				
				System.out.println("the quary is.....:"+prepareStatement);
				prepareStatement.setString(1,sessionId);// session id 
				prepareStatement.setString(2,sdomain.getSyllabusId());// syllabus id
				prepareStatement.setString(3,sdomain.getSessionName());//session name
			//	prepareStatement.setString(4,sdomain.getSessionStartTime());
				prepareStatement.setString(4,sdomain.getSubjectId());//subject id
				String topicid=sdomain.getTopicId();
				String topicArray[]=topicid.split("id");
				String topic=topicArray[0]+topicArray[1];
				System.out.println("topic="+topic);
				prepareStatement.setString(5,topic);// topic id
				
				String subtopicid=sdomain.getSubtopicId();
				String subtopicArray[]=subtopicid.split("id");
				String subtopic=subtopicArray[0]+subtopicArray[1];
				System.out.println("subtopic="+subtopic);
				
				prepareStatement.setString(6,subtopic);// subtopic id
				prepareStatement.setString(7,sdomain.getFacultyId());// faculty id
				//prepareStatement.setString(9,sdomain.getSessionEndTime());
				   String newFormat = "yyyy/MM/dd HH:mm";
				   
				   SimpleDateFormat sdf1 = new SimpleDateFormat(newFormat);
				   java.util.Date date = sdf1.parse(sdomain.getSessionStartTime());
				   java.sql.Timestamp toDB = new java.sql.Timestamp(date.getTime());
				//Timestamp timestamp = new Timestamp((sdomain.getSessionStartTime()).getTime());
				prepareStatement.setTimestamp(8, toDB);// session start time
			//	timestamp = new Timestamp(new Date(sdomain.getSessionEndTime()).getTime());
				
				java.util.Date date2 = sdf1.parse(sdomain.getSessionEndTime());
				java.sql.Timestamp toDB2 = new java.sql.Timestamp(date2.getTime());
				
				
				prepareStatement.setTimestamp(9, toDB2); // session end time 
				prepareStatement.setString(10,sdomain.getStatus()); // session status 
				prepareStatement.setString(11,sdomain.getOrgId()); //organization id 
				prepareStatement.setString(12,sdomain.getFacultyId()); // mod user id
				System.out.println("the quary is:"+prepareStatement);
				//prepareStatement = connection.prepareStatement(quary);
				int n = prepareStatement.executeUpdate();
				System.out.println("query is inserted successfully"+prepareStatement);
				//System.out.println("valu of n :"+n);
				if (n==1){
					status = "valid";
				}
				else{
					status = "notvalid";
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
			
			String check="invalid";
			if(!"invalid".equals(status))
				check=insertInSessionRecipients(sdomain,sessionId);
			
			if("status".equals(check))
				status="valid";
		
		
		return status;
	}
	
	public String insertInSessionRecipients(SessionDomain sdomain,String sessionId)
	{
		
		 String status = null;
		 String recepientType="";
			try {
		 connection = DBTransaction.connect();
		 ArrayList<String> enrollmentIdsOfRecords=new ArrayList<String>();
		int n=0;
		 
		 if("Individual".equals(sdomain.getRecipientType()))
			{
		recepientType=sdomain.getIndividualId();
		enrollmentIdsOfRecords.add(recepientType);
			}else if("Group".equals(sdomain.getRecipientType()))
			{
				if(sdomain.getGroupId() != null)
					recepientType=sdomain.getGroupId();
				
				String query="select student_id from student_groups where group_name='"+recepientType+"' and organization_id='"+sdomain.getOrgId()+"'";
				enrollmentIdsOfRecords=getEnrollmentIdsForGroup(query,connection);
			}else{
				String query="select a.enrollment_process_id, a.full_name from transaction_enrollment_process a, org_details b where "+
					     "b.organization_id = '"+sdomain.getOrgId() +"' and a.middle_layer_teaching_source_name = b.organization_name and "+
					     "a.course_id = '"+sdomain.getCategory() +"'  and a.course_completed <> 'YES' order by a.full_name";
			enrollmentIdsOfRecords=getEnrollmentIdsForAll(query,connection);
				recepientType="All";
			}
			
		
				System.out.println("in dao"+sdomain.getSessionStartTime()+"...."+sdomain.getSessionEndTime());
				
				
				// Get enrollment ids for group and all
				
				String queryForGroup="";
				String queryForAll="";
				
				// count number of fields 
				String quary="INSERT INTO session_recepients(session_id, recepient_id,receipient_type,session_status, organization_id, created_datetime, last_updated_datetime, mod_user_id) VALUES "
						+ " (?,?,?, ?, ?, now(), now(), ?);";
				prepareStatement = connection.prepareStatement(quary);
				
				System.out.println("the quary is.....:"+prepareStatement);
				for(String enrId: enrollmentIdsOfRecords)
				{
				prepareStatement.setString(1,sessionId);//session id
				prepareStatement.setString(2,enrId);// recipient id 
				prepareStatement.setString(3,recepientType);// recipient type
			//	prepareStatement.setString(3,sdomain.getSessionStartTime());
				System.out.println("STATUS RECI............."+sdomain.getStatus());
				prepareStatement.setString(4,sdomain.getStatus());// session status
				prepareStatement.setString(5,sdomain.getOrgId());// organisation id
				prepareStatement.setString(6,sdomain.getFacultyId());//faculty id
				//prepareStatement.setString(7,sdomain.getIndividualId());
				//prepareStatement.setString(9,sdomain.getSessionEndTime());
				//prepareStatement.setString(8,"Available");
				System.out.println("the quary is:"+prepareStatement);
				//prepareStatement = connection.prepareStatement(quary);
				n = prepareStatement.executeUpdate();
				System.out.println("query is inserted successfully"+prepareStatement);
				//System.out.println("valu of n :"+n);
				}
				if (n==1)
				{
					status = "valid";
				}
				else{
					status = "notvalid";
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
	
	 ArrayList<String> getEnrollmentIdsForGroup(String sql,Connection con)
	 {
		
			ResultSet rs= null;
			PreparedStatement ps=null;
		  ArrayList<String> enrollmentIdsOfRecords= new ArrayList<String>();
		  String studentIds="";
		  try{
				// con=DBTransaction.connect();
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
				   
				   ps.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
		        String enr[]=studentIds.split("#");
		        //  System.out.println(enr.length);
		        // System.out.println(enr[0]);
		        // System.out.println(enr[1]);
		        
		        
		        
		        for(int i=0;i<enr.length;i++)
		        {
		        	enrollmentIdsOfRecords.add(enr[i]);
		        }
		  
		  
		  
		  return enrollmentIdsOfRecords;
	 }
	 ArrayList<String> getEnrollmentIdsForAll(String sql,Connection con)
	 {
		 PreparedStatement ps=null;
		  ArrayList<String> enrollmentIdsOfRecords= new ArrayList<String>();
		  try{
				
				 ps=con.prepareStatement(sql);
				  ResultSet rs=ps.executeQuery();
				 //System.out.println("sql query is:"+sql);
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
	
	public String insertSessionGroupId(SessionDomain sdomain)
	{
		
        String status = null;
		//System.out.println("group values in dao:"+sdomain.getGroupId());
		try {

			connection = DBTransaction.connect();
			String quary="insert into session_details(session_name, category, duration, session_start_time, cost_of_session, faculty_name, organization_id,recipient_type,session_end_time,session_status,subject_id,topic_id,subtopic_id) " +
					"values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			prepareStatement = connection.prepareStatement(quary);
			//ps.setString(1,);
			prepareStatement.setString(1,sdomain.getSessionName());
			prepareStatement.setString(2,sdomain.getCategory());
			prepareStatement.setString(3,sdomain.getDuration());
			prepareStatement.setString(4,sdomain.getSessionStartTime());
			prepareStatement.setString(5,sdomain.getCostOfSession());
			prepareStatement.setString(6,sdomain.getFacultyId());
			prepareStatement.setString(7,sdomain.getOrgId());
			prepareStatement.setString(8,sdomain.getGroupId());
			prepareStatement.setString(9,sdomain.getSessionEndTime());
			prepareStatement.setString(10,"Available");
			prepareStatement.setString(11,sdomain.getSubjectId());
			prepareStatement.setString(12,sdomain.getTopicId());
			prepareStatement.setString(13,sdomain.getSubtopicId());
			System.out.println("the query is:"+prepareStatement);
			int n = prepareStatement.executeUpdate();
			//System.out.println("query  inserted successfully");
			if (n ==1){
				//status = "valid";
				String selectQuery="SELECT currval('course_details_course_id_seq')";
				prepareStatement=connection.prepareStatement(selectQuery);
				resulSet=prepareStatement.executeQuery();
				
				while(resulSet.next())
				{
					status="SES"+resulSet.getString(1);
				
				}
				
			}
			else {
				status = "notvalid";
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
		String check="invalid";
		if(!"invalid".equals(status))
			check=insertInTimeTable(sdomain,status);
		
		if("invalid".equals(check))
			 status="invalid";
		
		return status;
	}
	public ArrayList<SessionDomain> getSessionCategory(String facultyid,String organizationId){
		
		try{
			 
			connection=DBTransaction.connect();
		//	String sql= "select session_id,session_name from session_details where faculty_name='"+facultyid+"'"+" and organization_id='"+organizationId+"'";
			String sql= "select session_id,session_name from session_details where faculty_name='"+facultyid+"'"+" and organization_id='"+organizationId+"' and cast(session_end_time as timestamp) >=now()";

		//	String sql= "select session_id,session_name from session_details where faculty_name='"+facultyid+"'"+" and organization_id='"+organizationId+"' and cast(session_end_time as timestamp) >=CURRENT_DATE + INTERVAL '7 day'";
			
			System.out.println("sql in getSessionCategory="+sql);
			prepareStatement=connection.prepareStatement(sql);
			resulSet=prepareStatement.executeQuery();
			
			while(resulSet.next())
			{
				SessionDomain sessionDomain=new SessionDomain();
				sessionDomain.setSessionId(resulSet.getString("session_id"));
				sessionDomain.setSessionName(resulSet.getString("session_name"));
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
	
	public ArrayList<SessionDomain> getSesstionTime(String facultyid,String sessionId,String organizationId){
		
		try{
			
			connection=DBTransaction.connect();
			String sql= "select * from session_details where faculty_name='"+facultyid+"'"+"and organization_id='"+organizationId+"'"+" and session_id='"+sessionId+"'";
			prepareStatement=connection.prepareStatement(sql);
			//System.out.println("query is:"+prepareStatement);
			resulSet=prepareStatement.executeQuery();
		
			while(resulSet.next())
			{
			
			
				SessionDomain sessionDomain=new SessionDomain();
				sessionDomain.setSessionId(resulSet.getString("session_id"));
				sessionDomain.setSessionName(resulSet.getString("session_name"));
				sessionDomain.setCategory(resulSet.getString("category"));
				sessionDomain.setDuration(resulSet.getString("duration"));
				sessionDomain.setCostOfSession(resulSet.getString("cost_of_session"));
				sessionDomain.setRecipientType(resulSet.getString("recipient_type"));
				sessionDomain.setSessionStartTime(resulSet.getString("session_start_time"));
				sessionDomain.setSessionEndTime(resulSet.getString("session_end_time"));
				sessionDomain.setStatus(resulSet.getString("session_status"));
			
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
public ArrayList<SessionDomain> getSesstionAllDetails(String facultyid,String sessionId,String organizationId){
		
		try{
			
			connection=DBTransaction.connect();
			//String sql= "select * from session_details where faculty_name='"+facultyid+"'"+" and organization_id='"+organizationId+"'";
			String sql= "select * from session_details where faculty_name='"+facultyid+"'"+" and organization_id='"+organizationId+"' and cast(session_end_time as timestamp) > now()";
			System.out.println("sql in getSesstionAllDetails="+sql);
			prepareStatement=connection.prepareStatement(sql);
			//System.out.println("query is:"+prepareStatement);
			resulSet=prepareStatement.executeQuery();
			
			while(resulSet.next())
			{
				
				//System.out.println("result set");
				SessionDomain sessionDomain=new SessionDomain();
				sessionDomain.setSessionId(resulSet.getString("session_id"));
				sessionDomain.setSessionName(resulSet.getString("session_name"));
				sessionDomain.setCategory(resulSet.getString("category"));
				sessionDomain.setDuration(resulSet.getString("duration"));
				sessionDomain.setCostOfSession(resulSet.getString("cost_of_session"));
				sessionDomain.setRecipientType(resulSet.getString("recipient_type"));
				sessionDomain.setSessionStartTime(resulSet.getString("session_start_time"));
				sessionDomain.setSessionEndTime(resulSet.getString("session_end_time"));
				sessionDomain.setStatus(resulSet.getString("session_status"));
				
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
	ArrayList<AdminDomain> sl= new ArrayList<AdminDomain>();
	public ArrayList<AdminDomain> sendIndualGroup(String faculyid,String orgid){
		//System.out.println("dao faculty id:"+faculyid);
	try{
	
		connection=DBTransaction.connect();
		/*String sql= "select user_id,username from users_info where created_by='"+faculyid+"'"+" and organization_id='"+orgid+"'";*/
		String sql= "select distinct user_id,username from users_info left join course_description on users_info.organization_id=course_description.customer_id "
				+ "				where  organization_id='"+orgid+"' and privilege='Student' order by username";
		prepareStatement=connection.prepareStatement(sql);
		 resulSet=prepareStatement.executeQuery();
		System.out.println("sql="+prepareStatement);
		while(resulSet.next())
		{
			AdminDomain cDomain= new AdminDomain();
		 cDomain.setAdminId(resulSet.getString("user_id"));
		 cDomain.setAdminName(resulSet.getString("username"));
		 sl.add(cDomain);
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
	return sl;	
	}
	public ArrayList<AdminDomain> sendGroupId(String faculyid,String orgid){
		//System.out.println("dao faculty id:"+faculyid);
	try{
		
		connection=DBTransaction.connect();
		String sql= "select group_name from student_groups where created_by='"+faculyid+"'"+" and organization_id='"+orgid+"' order by group_name";
		prepareStatement=connection.prepareStatement(sql);
		resulSet=prepareStatement.executeQuery();
		String newgrp_name="",grp_namesplit[]=null;
		
		while(resulSet.next())
		{
			AdminDomain cDomain= new AdminDomain();
			String grp_name=resulSet.getString("group_name");
			if(grp_name.contains("_group"))
				{grp_namesplit=grp_name.split("_group");
				newgrp_name=grp_namesplit[0];
				}
			else{
				newgrp_name=grp_name;
			}
		 cDomain.setGroup_name(grp_name);
		 cDomain.setNew_Group_name(newgrp_name);
		// cDomain.setGroup_name(resulSet.getString("group_name"));
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
//FUNCTION TO GET GROUPNAME AND ALL students ID IN THAT GROUP
	public ArrayList<AdminDomain> sendGroupNameAndStuId(String faculyid,String orgid){
		////System.out.println("dao faculty id:"+faculyid);
	try{
		
		connection=DBTransaction.connect();
		String sql= "select group_name,student_id from student_groups where created_by='"+faculyid+"'"+" and organization_id='"+orgid+"' order by group_name";
		prepareStatement=connection.prepareStatement(sql);
		resulSet=prepareStatement.executeQuery();
		String newgrp_name="",grp_namesplit[]=null;
		
		while(resulSet.next())
		{
			AdminDomain cDomain= new AdminDomain();
			String grp_name=resulSet.getString("group_name");
			if(grp_name.contains("_group"))
				{grp_namesplit=grp_name.split("_group");
				newgrp_name=grp_namesplit[0];
				}
			else{
				newgrp_name=grp_name;
			}
			cDomain.setGroup_name(grp_name);
			cDomain.setNew_Group_name(newgrp_name);
			
		// cDomain.setGroup_name(resulSet.getString("group_name"));
		 cDomain.setIndivialStudentId(resulSet.getString("student_id"));
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

	
	
//	
	public SessionDomain retriveSessionDetails(SessionDomain sDomain){
		SessionDomain sdomain=new SessionDomain();
		//System.out.println("faculty is in dao:"+sDomain.getFacultyId());
		try{
			connection=DBTransaction.connect();
			String query="select * from session_details where session_id='"+sDomain.getSessionName()+"'"+" and faculty_name='"+sDomain.getFacultyId()+"'";
			prepareStatement=connection.prepareStatement(query);
			
			//System.out.println("result query is:"+query);
			resulSet=prepareStatement.executeQuery();
			while(resulSet.next()){
				
				sdomain.setSessionId(resulSet.getString("session_id"));
				sdomain.setSessionName(resulSet.getString("session_name"));
				sdomain.setCategory(resulSet.getString("category"));
				//System.out.println("session name in dao:"+resulSet.getString("session_name"));
				sdomain.setCostOfSession(resulSet.getString("cost_of_session"));
				sdomain.setFacultyId(resulSet.getString("faculty_name"));
				sdomain.setOrgId(resulSet.getString("organization_id"));
				sdomain.setRecipientType(resulSet.getString("recipient_type"));
				sdomain.setStatus(resulSet.getString("session_status"));
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
		return sdomain;
	}
	public String getEmailId(String studentid){
		String status="";
		try{
			
			connection=DBTransaction.connect();
			String sql= "select email from users_info where user_id='"+studentid+"'";
			prepareStatement=connection.prepareStatement(sql);
			resulSet=prepareStatement.executeQuery();
			
			while(resulSet.next())
			{
				status=resulSet.getString("email");
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
	
	public ArrayList<String> getEmailGroupId(String[] groupid){
		
		ArrayList<String> al=new ArrayList<String>();
		try{
			
			connection=DBTransaction.connect();
			for(int i=0;i<groupid.length;i++){
			 String sql = "select email from users_info where user_id='"+groupid[i]+"'";
			 System.out.println("email id query ="+sql);
			 prepareStatement = connection.prepareStatement(sql);
			
			    resulSet=prepareStatement.executeQuery();
			
			    while(resulSet.next()){
			    	al.add(resulSet.getString("email"));
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
		return al;	
		}

	public ArrayList<String> getUsersId(SessionDomain sdomain){
		
		ArrayList<String> ul=new ArrayList<String>();
		try{
			
			connection=DBTransaction.connect();
			
			 String sql = "select user_id from users_info where organization_id='"+sdomain.getOrgId()+"'"+" and privilege='Student'";
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
	
	public ArrayList<String> getAllEmailIDs(SessionDomain sdomain){
		
		ArrayList<String> ul=new ArrayList<String>();
		try{
			
			connection=DBTransaction.connect();
			
			 String sql = "select email from users_info where organization_id='"+sdomain.getOrgId()+"'"+" and privilege='Student'";
			 prepareStatement = connection.prepareStatement(sql);
			    resulSet=prepareStatement.executeQuery();
			    //System.out.println("sql query is:"+prepareStatement);
			    while(resulSet.next()){
			    	ul.add(resulSet.getString("email"));
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
	
public ArrayList<String> getAllEmailId(ArrayList<String> usersId){
		
		ArrayList<String> al=new ArrayList<String>();
		try{
			
			connection=DBTransaction.connect();
			java.util.Iterator<String> it=usersId.iterator();
			while(it.hasNext()){
			 String sql = "select email from users_info where user_id='"+it.next()+"'";
			 prepareStatement = connection.prepareStatement(sql);
			
			    resulSet=prepareStatement.executeQuery();
			
			    while(resulSet.next()){
			    	al.add(resulSet.getString("email"));
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
		return al;	
		}
public ArrayList<String> getRecipientType(SessionDomain sdomain){
	
	ArrayList<String> al=new ArrayList<String>();
	try{
		
		connection=DBTransaction.connect();
		//java.util.Iterator<String> it=sdomain.iterator();
		//while(it.hasNext()){
		 String sql = "select session_start_time,recipient_type,session_end_time from session_details where session_name='"+sdomain.getSessionName()+"'"+"and  faculty_name='"+sdomain.getFacultyId()+"'"+ "and  organization_id='"+sdomain.getOrgId()+"'";
		 prepareStatement = connection.prepareStatement(sql);
		
		    resulSet=prepareStatement.executeQuery();
		
		    while(resulSet.next()){
		    	al.add(resulSet.getString("session_start_time"));
		    	al.add(resulSet.getString("recipient_type"));
		    	al.add(resulSet.getString("session_end_time"));
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
public ArrayList<String> retriveSessionName(String facultyid, String organizationId){
	ArrayList<String> arrayList=new ArrayList<String>();
	try{
		 
		connection=DBTransaction.connect();
		String sql= "select session_name from session_details where faculty_name='"+facultyid+"'"+" and organization_id='"+organizationId+"'";
		prepareStatement=connection.prepareStatement(sql);
		resulSet=prepareStatement.executeQuery();
		
		while(resulSet.next())
		{
			arrayList.add(resulSet.getString("session_name"));
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
	return arrayList;	
	}
public String updateSessionDetails(ArrayList<SessionDomain> list){
	
	String status="";
	try {
		
		
		connection=DBTransaction.connect();
		String sql="update session_details set session_name=?,category=?,duration=? , session_start_time=? ,cost_of_session=?,recipient_type=?,session_status=?, session_end_time=? where session_id=?";
		prepareStatement = connection.prepareStatement(sql);
		System.out.println("query for update session="+sql);
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
			System.out.println("query string in dao during session modify:"+sql);
		}
		System.out.println("query string in dao:"+sql);
		
		int[] count=prepareStatement.executeBatch();
		System.out.println(count.length+"values are inserted");
		System.out.println("query string in dao:"+sql);
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
		
		 String sql = "select user_id from users_info where organization_id='"+sdomain.getOrgId()+"'"+" and privilege='Student'";
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


public String getCacsId(String courseid,String organizationId){
	String cacs_id="";
	try{
		 
		connection=DBTransaction.connect();
		String sql="SELECT cacs_id FROM mst_course_catalogue where deptid_by_ts='"+courseid+"' and organization_id= '"+organizationId+"'";
		prepareStatement=connection.prepareStatement(sql);
		resulSet=prepareStatement.executeQuery();
		
		while(resulSet.next())
		{
			cacs_id=resulSet.getString("cacs_id");
			
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
	return cacs_id;	
}


public String getSyllabusId(String cacs_id,String subjectId,String organizationId){
	String syallabusId="";
	try{
		 
		connection=DBTransaction.connect();

	
		String sql="SELECT syllabus_id FROM mst_course_syllabus where cacs_id='"+cacs_id+"' and subject_id='"+subjectId+"' and organization_id='"+organizationId+"'";
		
		prepareStatement=connection.prepareStatement(sql);
		resulSet=prepareStatement.executeQuery();
		
		while(resulSet.next())
		{
			syallabusId=resulSet.getString("syllabus_id");
			
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
	return syallabusId;	
}






public ArrayList<SessionDomain> getAllEmailIdUserName(SessionDomain sdomain){
	
	ArrayList<SessionDomain> al1=new ArrayList<SessionDomain>();
	try{
		
		connection=DBTransaction.connect();
		//java.util.Iterator<String> it=usersId.iterator();
	//	while(it.hasNext()){
			 String sql = "select email from users_info where organization_id='"+sdomain.getOrgId()+"'"+" and privilege='Student'";
		 prepareStatement = connection.prepareStatement(sql);
		
		    resulSet=prepareStatement.executeQuery();
		
		    while(resulSet.next()){
		    	SessionDomain domain=new SessionDomain();
		    	domain.setEmail(resulSet.getString("email"));
		 //   	domain.setStudentName(resulSet.getString("username"));
		    	al1.add(domain);
		    }
	//	}
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
/*public ArrayList<String> retriveOldSessionDetails(ArrayList<SessionDomain> old_session_array_list){
	ArrayList<String> arrayList=new ArrayList<String>();
	try{
		 
		connection=DBTransaction.connect();
		String sql= "select session_name from session_details where faculty_name='"+facultyid+"'"+" and organization_id='"+organizationId+"'";
		prepareStatement=connection.prepareStatement(sql);
		resulSet=prepareStatement.executeQuery();
		
		while(resulSet.next())
		{
			arrayList.add(resulSet.getString("session_name"));
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
	return arrayList;	
	}
*/
}
