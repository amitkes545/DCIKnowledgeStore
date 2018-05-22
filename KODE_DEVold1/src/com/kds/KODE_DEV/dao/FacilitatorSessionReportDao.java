
package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.FacilitatorSessionReportDomain;
import com.kds.KODE_DEV.domain.SessionDomain;

public class FacilitatorSessionReportDao {
	
	FacilitatorSessionReportDomain mksDomain = new FacilitatorSessionReportDomain();
	//Connection con;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	ArrayList<SessionDomain> sessionDetails=new ArrayList<SessionDomain>();
	ArrayList<String> arl=new ArrayList<String>();
	Date date1;
	Connection con =null;
	public ArrayList<SessionDomain> facultySessionOldDetails(String orgId , String facultyId) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Calendar cal = Calendar.getInstance();
		
		//System.out.println(dateFormat.format(cal.getTime())); //2014/08/06 16:00:22
		
		
		String date=dateFormat.format(cal.getTime());
		
		try {
			 date1 = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(date);
			//System.out.println("date1 in try:"+date1);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] DateAndTime1=date.split("");
		String fromDbDate1=DateAndTime1[0];
		String fromDbTime1=DateAndTime1[1];
		
		//System.out.println("current date:"+fromDbDate1);
		//System.out.println("current time:"+fromDbTime1);
		////System.out.println("date:"+date);
		try {
			 con = DBTransaction.connect();
			
			
			 String sql = "select * from session_details sd left join student_groups sg on sd.faculty_name=sg.created_by and "
						+ "sd.organization_id=sg.organization_id and sd.recipient_type=sg.student_id left join course_description cd on sd.category=cd.course_id_defined_by_teaching_source and sd.organization_id=cd.customer_id "
						+ " where sd.organization_id='"+orgId+"'"+"and faculty_name='"+facultyId+"'";
			
			preparedStatement = con.prepareStatement(sql);

		     System.out.println("the query is:" + sql);
		          preparedStatement = con.prepareStatement(sql);
		        resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {	
				String datefromdb=resultSet.getString("session_start_time");
				/*String[] DateAndTime=date1.split("");
				String fromDbDate=DateAndTime[0];
				String fromDbTime=DateAndTime[1];
				//System.out.println("from database:"+fromDbDate);
				//System.out.println("fromDbTime:"+fromDbTime);*/
				Date date12 = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(datefromdb);
				//System.out.println("date12 from db:"+date12);
				//System.out.println("date1 from db:"+date1);
				if(date12.before(date1)){
					SessionDomain sessionDomain=new SessionDomain();
					////System.out.println(resultSet.getString("session_name"));
					sessionDomain.setSessionId(resultSet.getString("session_id"));
					sessionDomain.setSessionName(resultSet.getString("session_name"));
					sessionDomain.setCategory(resultSet.getString("category"));
					sessionDomain.setCourseName(resultSet.getString("course_name"));
					sessionDomain.setDuration(resultSet.getString("duration"));
					sessionDomain.setCostOfSession(resultSet.getString("cost_of_session"));
					//sessionDomain.setRecipientType(resultSet.getString("recipient_type"));
				
					String newrtype="",rtype_split[]=null;
					String rtype=resultSet.getString("recipient_type");
										System.out.println("rtype="+rtype);
										if(rtype!=null){
										if(rtype.contains("_group"))
											{
											rtype_split=rtype.split("_group");
											newrtype=rtype_split[0];
											}
										else{
											newrtype=rtype;
										}}
					sessionDomain.setRecipientType(newrtype);
					
					sessionDomain.setGroupName(resultSet.getString("group_name"));
					sessionDomain.setSessionStartTime(resultSet.getString("session_start_time"));
					sessionDomain.setSessionEndTime(resultSet.getString("session_end_time"));
					sessionDomain.setStatus(resultSet.getString("session_status"));
					sessionDomain.setGroupName(resultSet.getString("group_name"));
					sessionDetails.add(sessionDomain);
					////System.out.println("db date is before current date:");
				}else if(date12.after(date1)){
					/*SessionDomain sessionDomain=new SessionDomain();
					////System.out.println(resultSet.getString("session_name"));
					sessionDomain.setSessionId(resultSet.getString("session_id"));
					sessionDomain.setSessionName(resultSet.getString("session_name"));
					sessionDomain.setCategory(resultSet.getString("category"));
					sessionDomain.setDuration(resultSet.getString("duration"));
					sessionDomain.setCostOfSession(resultSet.getString("cost_of_session"));
					sessionDomain.setRecipientType(resultSet.getString("recipient_type"));
					sessionDomain.setSessionStartTime(resultSet.getString("session_start_time"));
					sessionDomain.setSessionEndTime(resultSet.getString("session_end_time"));
					sessionDomain.setStatus(resultSet.getString("session_status"));
					sessionDetails.add(sessionDomain);*/	
					//System.out.println("db date is after current date:");
			}
				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			DBTransaction.closeConnection(con);
		}
		return sessionDetails;
	}
public ArrayList<SessionDomain> sessionOldDetails(String orgId , String facultyId,String userID) {
		
	  
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Calendar cal = Calendar.getInstance();
		
		//System.out.println(dateFormat.format(cal.getTime())); //2014/08/06 16:00:22
		
		
		String date=dateFormat.format(cal.getTime());
		
		try {
			 date1 = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(date);
			//System.out.println("date1 in try:"+date1);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] DateAndTime1=date.split("");
		String fromDbDate1=DateAndTime1[0];
		String fromDbTime1=DateAndTime1[1];
		
		//System.out.println("current date:"+fromDbDate1);
		//System.out.println("current time:"+fromDbTime1);
		////System.out.println("date:"+date);
		try {
			 con = DBTransaction.connect();
			
			
			//String sql = "select * from session_details where organization_id='"+orgId+"'"+"and faculty_name='"+facultyId+"'";
			 String sql = "SELECT '"+userID+"' Student_id,course_name,session_details.*,users_info.username as faculty_name,case when classroom.session_id is null then 'Un-Attended' else 'Attended' end as Attendence "
			 		+ "FROM  session_details left join (select  session_id from classroom_tracking where log_status='in' and student_id = '"+userID+"' group by session_id) classroom "
			 		+ "on session_details.session_id = classroom.session_id left join course_description CD on session_details.category=CD.course_id_defined_by_teaching_source and session_details.organization_id=CD.customer_id left join users_info on users_info.organization_id = session_details.organization_id and users_info.user_id = session_details.faculty_name where session_details.organization_id = '"+orgId+"'/* and faculty_name= '"+facultyId+"'*/ "
			 		+ " and cast(session_end_time as timestamp) <= current_timestamp and (recipient_type like '%"+userID+"%' or recipient_type='all') order by session_start_time";

			 			


			preparedStatement = con.prepareStatement(sql);

		     System.out.println("the query is:" + sql);
		          preparedStatement = con.prepareStatement(sql);
		        resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {	
				String datefromdb=resultSet.getString("session_start_time");
				/*String[] DateAndTime=date1.split("");
				String fromDbDate=DateAndTime[0];
				String fromDbTime=DateAndTime[1];
				//System.out.println("from database:"+fromDbDate);
				//System.out.println("fromDbTime:"+fromDbTime);*/
				Date date12 = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(datefromdb);
				//System.out.println("date12 from db:"+date12);
				//System.out.println("date1 from db:"+date1);
				if(date12.before(date1)){
					SessionDomain sessionDomain=new SessionDomain();
					////System.out.println(resultSet.getString("session_name"));
					sessionDomain.setSessionId(resultSet.getString("session_id"));
					System.out.println("----------------->"+resultSet.getString("session_id"));
					sessionDomain.setSessionName(resultSet.getString("session_name"));
					System.out.println("----------------->"+resultSet.getString("session_name"));
					sessionDomain.setCategory(resultSet.getString("category"));
					System.out.println("----------------->"+resultSet.getString("category"));
					sessionDomain.setCourseName(resultSet.getString("course_name"));
					System.out.println("----------------->"+resultSet.getString("course_name"));
					sessionDomain.setDuration(resultSet.getString("duration"));
					System.out.println("jhjhjhjhjhjhjhjhjhjhjhjhjhjhjhjhjhjhjhjhjhjhjhjhjhjhjh");
					sessionDomain.setUserName(resultSet.getString("faculty_name"));
					System.out.println("UserName::::::::::::::::"+resultSet.getString("faculty_name"));
					System.out.println("----------------->"+resultSet.getString("duration"));
					sessionDomain.setCostOfSession(resultSet.getString("cost_of_session"));
					System.out.println(resultSet.getString("cost_of_session"));
					sessionDomain.setRecipientType(resultSet.getString("recipient_type"));
					System.out.println(resultSet.getString("recipient_type"));
					sessionDomain.setSessionStartTime(resultSet.getString("session_start_time"));
					System.out.println(resultSet.getString("session_start_time"));
					sessionDomain.setSessionEndTime(resultSet.getString("session_end_time"));
					System.out.println(resultSet.getString("session_start_time"));
					sessionDomain.setStatus(resultSet.getString("session_status"));
					System.out.println(resultSet.getString("session_status"));
					sessionDomain.setAttendance(resultSet.getString("Attendence"));
					System.out.println(resultSet.getString("Attendence"));
					sessionDetails.add(sessionDomain);
					////System.out.println("db date is before current date:");
				}else if(date12.after(date1)){
					/*SessionDomain sessionDomain=new SessionDomain();
					////System.out.println(resultSet.getString("session_name"));
					sessionDomain.setSessionId(resultSet.getString("session_id"));
					sessionDomain.setSessionName(resultSet.getString("session_name"));
					sessionDomain.setCategory(resultSet.getString("category"));
					sessionDomain.setDuration(resultSet.getString("duration"));
					sessionDomain.setCostOfSession(resultSet.getString("cost_of_session"));
					sessionDomain.setRecipientType(resultSet.getString("recipient_type"));
					sessionDomain.setSessionStartTime(resultSet.getString("session_start_time"));
					sessionDomain.setSessionEndTime(resultSet.getString("session_end_time"));
					sessionDomain.setStatus(resultSet.getString("session_status"));
					sessionDetails.add(sessionDomain);*/	
					//System.out.println("db date is after current date:");
			}
				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			DBTransaction.closeConnection(con);
		}
		return sessionDetails;
	}


public ArrayList<SessionDomain> facultySessionUpcomingDetailsForIndividual(String orgId , String facultyId , String subject_Name) {
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	Calendar cal = Calendar.getInstance();
	
	
	
	
	String date=dateFormat.format(cal.getTime());
	
	try {
		 date1 = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(date);
		System.out.println("date1====="+date1);
	} catch (ParseException e1) {
		
		e1.printStackTrace();
	}
	String[] DateAndTime1=date.split("");
	String fromDbDate1=DateAndTime1[0];
	String fromDbTime1=DateAndTime1[1];

	try {
		 con = DBTransaction.connect();
		/*select distinct *,USERNAME from session_details SD inner join course_description CD on SD.category=CD.course_id_defined_by_teaching_source  and SD.organization_id=CD.customer_id 	
		 left join student_groups SG on SG.student_id=SD.recipient_type and SD.organization_id=SG.organization_id 
				 LEFT JOIN USERS_INFO UI ON SD.FACULTY_NAME = UI.USER_ID
					 where SD.organization_id='Unicomsan' and faculty_name='ajipri546'  and created_by='ajipri546' order by session_start_time 
		*/
		/*String sql = "select distinct * from session_details SD inner join course_description CD on SD.category=CD.course_id_defined_by_teaching_source and SD.organization_id=CD.customer_id  where organization_id='"+orgId+"' and faculty_name='"+facultyId+"' order by session_start_time";*/
		String sql= "select distinct *,username from session_details SD inner join course_description CD on SD.category=CD.course_id_defined_by_teaching_source  and SD.organization_id=CD.customer_id "
				+ "	 left join student_groups SG on SG.student_id=SD.recipient_type and SD.organization_id=SG.organization_id  LEFT JOIN USERS_INFO UI ON SD.FACULTY_NAME = UI.USER_ID"
				+ "	 where SD.organization_id='"+orgId+"' and SD.subject_id='"+subject_Name+"' and faculty_name='"+facultyId+"' /* and created_by='"+facultyId+"'*/ order by session_start_time   ";
					preparedStatement = con.prepareStatement(sql);

	     System.out.println("the query is:" + sql);
	          preparedStatement = con.prepareStatement(sql);
	        resultSet = preparedStatement.executeQuery();
	        System.out.println("the query is:" + preparedStatement);
		while(resultSet.next()) {	
			String datefromdb=resultSet.getString("session_start_time");
			
			System.out.println("date from db before="+datefromdb);
			
			
			Date date12 = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(datefromdb);
			System.out.println("date from db:"+date12);
			if(date12.before(date1)){
				System.out.println("before");
				/*SessionDomain sessionDomain=new SessionDomain();
				////System.out.println(resultSet.getString("session_name"));
				sessionDomain.setSessionId(resultSet.getString("session_id"));
				sessionDomain.setSessionName(resultSet.getString("session_name"));
				sessionDomain.setCategory(resultSet.getString("category"));
				sessionDomain.setDuration(resultSet.getString("duration"));
				sessionDomain.setCostOfSession(resultSet.getString("cost_of_session"));
				sessionDomain.setRecipientType(resultSet.getString("recipient_type"));
				sessionDomain.setSessionStartTime(resultSet.getString("session_start_time"));
				sessionDomain.setSessionEndTime(resultSet.getString("session_end_time"));
				sessionDomain.setStatus(resultSet.getString("session_status"));
				sessionDetails.add(sessionDomain);*/
				////System.out.println("db date is before current date:");
			}else if(date12.after(date1)){
				SessionDomain sessionDomain=new SessionDomain();
				////System.out.println(resultSet.getString("session_name"));
				sessionDomain.setSessionId(resultSet.getString("session_id"));
				sessionDomain.setSessionName(resultSet.getString("session_name"));
				sessionDomain.setCourseName(resultSet.getString("course_name"));
				sessionDomain.setSubjectName(subject_Name);
				sessionDomain.setCategory(resultSet.getString("category"));
				sessionDomain.setDuration(resultSet.getString("duration"));
				sessionDomain.setCostOfSession(resultSet.getString("cost_of_session"));
				sessionDomain.setGroupName(resultSet.getString("group_name"));
				
				String newrtype="",rtype_split[]=null;
				String rtype=resultSet.getString("recipient_type");
									System.out.println("rtype="+rtype);
									if(rtype!=null){
									if(rtype.contains("_group"))
										{
										rtype_split=rtype.split("_group");
										newrtype=rtype_split[0];
										}
									else{
										newrtype=rtype;
									}}
				sessionDomain.setRecipientType(newrtype);
				
			//	sessionDomain.setRecipientType(resultSet.getString("recipient_type"));
				sessionDomain.setSessionStartTime(resultSet.getString("session_start_time"));
				sessionDomain.setSessionEndTime(resultSet.getString("session_end_time"));
				//sessionDomain.setStatus(resultSet.getString("session_status"));
				sessionDomain.setStatus(resultSet.getString("session_status"));
				sessionDetails.add(sessionDomain);	
				////System.out.println("db date is after current date:");
		}
			
		}
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	finally
	{
		DBTransaction.closeConnection(con);
	}
	return sessionDetails;
}

public ArrayList<SessionDomain> facultySessionUpcomingDetails(String orgId , String facultyId ,String course, String subject_Name) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Calendar cal = Calendar.getInstance();
		
		
		
		
		String date=dateFormat.format(cal.getTime());
		
		try {
			 date1 = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(date);
			
		} catch (ParseException e1) {
		
			e1.printStackTrace();
		}
		String[] DateAndTime1=date.split("");
		String fromDbDate1=DateAndTime1[0];
		String fromDbTime1=DateAndTime1[1];

		try {
			 con = DBTransaction.connect();
			/*select distinct *,USERNAME from session_details SD inner join course_description CD on SD.category=CD.course_id_defined_by_teaching_source  and SD.organization_id=CD.customer_id 	
			 left join student_groups SG on SG.student_id=SD.recipient_type and SD.organization_id=SG.organization_id 
					 LEFT JOIN USERS_INFO UI ON SD.FACULTY_NAME = UI.USER_ID
						 where SD.organization_id='Unicomsan' and faculty_name='ajipri546'  and created_by='ajipri546' order by session_start_time 
			*/
			/*String sql = "select distinct * from session_details SD inner join course_description CD on SD.category=CD.course_id_defined_by_teaching_source and SD.organization_id=CD.customer_id  where organization_id='"+orgId+"' and faculty_name='"+facultyId+"' order by session_start_time";*/
			String sql= "select distinct *,username from session_details SD inner join course_description CD on SD.category=CD.course_id_defined_by_teaching_source  and SD.organization_id=CD.customer_id "
					+ "	 left join student_groups SG on SG.student_id=SD.recipient_type and SD.organization_id=SG.organization_id  LEFT JOIN USERS_INFO UI ON SD.FACULTY_NAME = UI.USER_ID"
					+ "	 where SD.organization_id='"+orgId+"' and faculty_name='"+facultyId+"' /* and created_by='"+facultyId+"'*/ order by session_start_time   ";
						preparedStatement = con.prepareStatement(sql);

		    
		          preparedStatement = con.prepareStatement(sql);
		        resultSet = preparedStatement.executeQuery();
		        System.out.println("the query is preparedStatement:" + preparedStatement);
			while(resultSet.next()) {	
				String datefromdb=resultSet.getString("session_start_time");
				Date date12 = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(datefromdb);
				//System.out.println("date from db:"+date12);
				if(date12.before(date1)){
					/*SessionDomain sessionDomain=new SessionDomain();
					////System.out.println(resultSet.getString("session_name"));
					sessionDomain.setSessionId(resultSet.getString("session_id"));
					sessionDomain.setSessionName(resultSet.getString("session_name"));
					sessionDomain.setCategory(resultSet.getString("category"));
					sessionDomain.setDuration(resultSet.getString("duration"));
					sessionDomain.setCostOfSession(resultSet.getString("cost_of_session"));
					sessionDomain.setRecipientType(resultSet.getString("recipient_type"));
					sessionDomain.setSessionStartTime(resultSet.getString("session_start_time"));
					sessionDomain.setSessionEndTime(resultSet.getString("session_end_time"));
					sessionDomain.setStatus(resultSet.getString("session_status"));
					sessionDetails.add(sessionDomain);*/
					////System.out.println("db date is before current date:");
				}else if(date12.after(date1)){
					SessionDomain sessionDomain=new SessionDomain();
					////System.out.println(resultSet.getString("session_name"));
					sessionDomain.setSessionId(resultSet.getString("session_id"));
					sessionDomain.setSessionName(resultSet.getString("session_name"));
					sessionDomain.setCourseName(resultSet.getString("course_name"));
					sessionDomain.setSubjectName(subject_Name);
					sessionDomain.setCategory(resultSet.getString("category"));
					sessionDomain.setDuration(resultSet.getString("duration"));
					sessionDomain.setCostOfSession(resultSet.getString("cost_of_session"));
					sessionDomain.setGroupName(resultSet.getString("group_name"));
					
					String newrtype="",rtype_split[]=null;
					String rtype=resultSet.getString("recipient_type");
										System.out.println("rtype="+rtype);
										if(rtype!=null){
										if(rtype.contains("_group"))
											{
											rtype_split=rtype.split("_group");
											newrtype=rtype_split[0];
											}
										else{
											newrtype=rtype;
										}}
					sessionDomain.setRecipientType(newrtype);
					
				//	sessionDomain.setRecipientType(resultSet.getString("recipient_type"));
					sessionDomain.setSessionStartTime(resultSet.getString("session_start_time"));
					sessionDomain.setSessionEndTime(resultSet.getString("session_end_time"));
					//sessionDomain.setStatus(resultSet.getString("session_status"));
					sessionDomain.setStatus(resultSet.getString("session_status"));
					sessionDetails.add(sessionDomain);	
					////System.out.println("db date is after current date:");
			}
				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			DBTransaction.closeConnection(con);
		}
		return sessionDetails;
	}
public ArrayList<SessionDomain> sessionUpcomingDetails(String orgId , String facultyId,String userID,String course,String subjectId) {
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	Calendar cal = Calendar.getInstance();
	
	//System.out.println(dateFormat.format(cal.getTime())); //2014/08/06 16:00:22
	
	
	String date=dateFormat.format(cal.getTime());
	
	try {
		 date1 = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(date);
		//System.out.println("date1 in try:"+date1);
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	String[] DateAndTime1=date.split("");
	String fromDbDate1=DateAndTime1[0];
	String fromDbTime1=DateAndTime1[1];
	
	try {
		 con = DBTransaction.connect();
		
		
		
		 
		 String sql = "select distinct(course_name),username,session_details.*, case when log_status is null then 'Available' when log_status='out' then 'Closed' when log_status='in' "
			 		+ "then 'Open' end Status from session_details left join (select classroom.session_id,log_status from classroom_tracking"
			 		+ " join (select session_id,max(date_time) date_time from classroom_tracking where student_id='"+userID+"' group by session_id) classroom "
			 		+ "on classroom_tracking.session_id = classroom.session_id and classroom_tracking.date_time = classroom.date_time) logstatus "
			 		+ "on session_details.session_id = logstatus.session_id left join  course_description CD on session_details.category=CD.course_id_defined_by_teaching_source and session_details.organization_id=CD.customer_id "
			 		+ "left join users_info UI on UI.user_id=session_details.faculty_name  where cast(session_end_time as timestamp) >= current_timestamp and session_details.organization_id='"+orgId+"' and category='"+course+"' and (session_details.recipient_type = 'all' or session_details.recipient_type = '"+userID+"' "
			 		+ " or  session_details.recipient_type like '%"+userID+"%' or session_details.recipient_type in (select group_name from student_groups where student_id like '%"+userID+"%' ) "
			 		+ ") order by session_start_time";
		
		 preparedStatement = con.prepareStatement(sql);

	     System.out.println("the query is:" + sql);
	          preparedStatement = con.prepareStatement(sql);
	        resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {	
			String datefromdb=resultSet.getString("session_start_time");
			Date date12 = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(datefromdb);
			//System.out.println("date from db:"+date12);
			if(date12.before(date1)){
				/*SessionDomain sessionDomain=new SessionDomain();
				////System.out.println(resultSet.getString("session_name"));
				sessionDomain.setSessionId(resultSet.getString("session_id"));
				sessionDomain.setSessionName(resultSet.getString("session_name"));
				sessionDomain.setCategory(resultSet.getString("category"));
				sessionDomain.setDuration(resultSet.getString("duration"));
				sessionDomain.setCostOfSession(resultSet.getString("cost_of_session"));
				sessionDomain.setRecipientType(resultSet.getString("recipient_type"));
				sessionDomain.setSessionStartTime(resultSet.getString("session_start_time"));
				sessionDomain.setSessionEndTime(resultSet.getString("session_end_time"));
				sessionDomain.setStatus(resultSet.getString("session_status"));
				sessionDetails.add(sessionDomain);*/
				////System.out.println("db date is before current date:");
			}else if(date12.after(date1)){
				SessionDomain sessionDomain=new SessionDomain();
				////System.out.println(resultSet.getString("session_name"));
				sessionDomain.setSessionId(resultSet.getString("session_id"));
				sessionDomain.setSessionName(resultSet.getString("session_name"));
				sessionDomain.setCategory(resultSet.getString("category"));
				sessionDomain.setCourseName(resultSet.getString("course_name"));
				sessionDomain.setSubjectName(subjectId);
				sessionDomain.setDuration(resultSet.getString("duration"));
				sessionDomain.setCostOfSession(resultSet.getString("cost_of_session"));
				sessionDomain.setRecipientType(resultSet.getString("faculty_name"));
				sessionDomain.setSessionStartTime(resultSet.getString("session_start_time"));
				sessionDomain.setSessionEndTime(resultSet.getString("session_end_time"));
				sessionDomain.setUserName(resultSet.getString("username"));
				//sessionDomain.setStatus(resultSet.getString("session_status"));
				sessionDomain.setStatus(resultSet.getString("Status"));
				sessionDetails.add(sessionDomain);	
				////System.out.println("db date is after current date:");
		}
			
		}
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	finally
	{
		DBTransaction.closeConnection(con);
	}
	return sessionDetails;
}
	public ArrayList<String> fetchRecipientType(String facultyId) {
		
		try {
			 con = DBTransaction.connect();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String sql = "select recipient_type from session_details where session_id='"+mksDomain.getSessionId()+"'"+"and faculty_name='"+facultyId+"'";
			preparedStatement = con.prepareStatement(sql);

		//System.out.println("the query is:" + sql);
			preparedStatement = con.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {		
				//System.out.println(resultSet.getString("recipient_type"));
			     mksDomain.setSessionId(resultSet.getString("recipient_type"));
				String recipientType = resultSet.getString("recipient_type");
				arl.add(recipientType);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			DBTransaction.closeConnection(con);
		}
		return arl;
	}
	 ArrayList<SessionDomain> sessionDetails1=new ArrayList<SessionDomain>();
		
		public ArrayList<SessionDomain> fetchValue(String orgId , String facultyId) {

			try {
			 con = DBTransaction.connect();
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				String sql = "select session_name from session_details where organization_id='"+orgId+"'"+"and faculty_name='"+facultyId+"'";
				preparedStatement = con.prepareStatement(sql);

			//System.out.println("the query is:" + sql);
			preparedStatement = con.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {	
					SessionDomain sdomain=new SessionDomain();
					//System.out.println(resultSet.getString("session_name"));
					sdomain.setSessionName(resultSet.getString("session_name"));
					//String sessionName=resultSet.getString("session_name");
					sessionDetails1.add(sdomain);
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally
			{
				DBTransaction.closeConnection(con);
			}
			return sessionDetails1;
		}
		ArrayList<String> arl1=new ArrayList<String>();
		
		public ArrayList<String> fetchStudentValue(String orgId , String facultyId) {

			try {
				 con = DBTransaction.connect();
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				String sql = "select session_name from session_details where organization_id='"+orgId+"'"+"and faculty_name='"+facultyId+"'";
				preparedStatement = con.prepareStatement(sql);

			//System.out.println("the query is:" + sql);
			preparedStatement = con.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {		
					//System.out.println(resultSet.getString("session_name"));
				     mksDomain.setOrgId(resultSet.getString("session_name"));
					String sessionName=resultSet.getString("session_name");
					arl1.add(sessionName);
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally
			{
				DBTransaction.closeConnection(con);
			}
			return arl1;
		}
}