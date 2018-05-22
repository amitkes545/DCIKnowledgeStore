package com.kds.KODE_DEV.dao;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import com.kds.KODE_DEV.dbconnection.DBTransaction;

public class MISReportsDao {
	Connection con = null;
	
	PreparedStatement preparedStatement = null;

		
	//ReportID = MIS_UA_001
	public ArrayList<HashMap<String,String>> getDailyLoginDetailsByDateByrole(String user_id,String Org_id,String startDate,String endDate,String Role,String SearchVal)
	{
		ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
		try
		{
			con = DBTransaction.connect();
			
			ResultSet resultSet = null;
			String sql="select a.user_id, a.username, a.privilege, b.date_time, b.log_status from users_info a, tracking_table b where a.organization_id = '"+Org_id+"' and a.privilege in('Faculty','Student') and a.user_id = b.student_id order by a.user_id, b.date_time";
			if(Role.trim().equalsIgnoreCase("faculty")) // role checking 
			{
				sql="select a.user_id, a.username, a.privilege, b.date_time, b.log_status from users_info a, tracking_table b where a.privilege='Faculty' and a.organization_id = '"+Org_id+"' and a.user_id = b.student_id order by a.user_id, b.date_time";
				if(!SearchVal.trim().equalsIgnoreCase("All"))
					sql="select a.user_id, a.username, a.privilege, b.date_time, b.log_status from users_info a, tracking_table b where a.user_id='"+SearchVal+"' and a.privilege='Faculty' and a.organization_id = '"+Org_id+"' and a.user_id = b.student_id order by a.user_id, b.date_time";
			}
			if(Role.trim().equalsIgnoreCase("student")) // role checking
			{
				sql="select a.user_id, a.username, a.privilege, b.date_time, b.log_status from users_info a, tracking_table b where a.privilege='Student' and a.organization_id = '"+Org_id+"' and a.user_id = b.student_id order by a.user_id, b.date_time";
				if(!SearchVal.trim().equalsIgnoreCase("All"))
					sql="select a.user_id, a.username, a.privilege, b.date_time, b.log_status from users_info a, tracking_table b where a.user_id='"+SearchVal+"' and a.privilege='Student' and a.organization_id = '"+Org_id+"' and a.user_id = b.student_id order by a.user_id, b.date_time";
			}
			
			
			System.out.println("getDailyLoginDetailsByDateByrole \n"+sql);
			preparedStatement = con.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) 
			{
				String UserId=resultSet.getString("user_id").toString().trim();
				String UserName=resultSet.getString("username").toString().trim();
				String Privilege=resultSet.getString("privilege").toString().trim();
				String Date_Time=resultSet.getString("date_time").toString().trim();
				String LogStatus=resultSet.getString("log_status").toString().trim();
				
				
				SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
								
				Date Logindt=sdf.parse(formatDate(Date_Time, "yyyy-MM-dd HH:mm:ss.SSS", "dd/MM/yyyy"));
				Date sdt=sdf.parse(startDate);
				Date edt=sdf.parse(endDate);
				
				// System.out.println("L::"+Date_Time +"		S::"+startDate+"		E::"+endDate);
				
				if( validateDateRange(Logindt, sdt, edt) ) // after startDate and before endDate checking here
				{
										
					HashMap<String,String> stuinfo=new HashMap<String,String>();
					stuinfo.put("UserId", UserId);
					stuinfo.put("UserName", UserName);
					stuinfo.put("Privilege", Privilege);
					String Date=formatDate(Date_Time, "yyyy-MM-dd HH:mm:ss.SSS", "dd/MM/yyyy");
					String Time=formatDate(Date_Time, "yyyy-MM-dd HH:mm:ss.SSS", "HH:mm:ss");
					stuinfo.put("Date", Date);
					stuinfo.put("Time",Time);
					stuinfo.put("LogStatus", LogStatus);
					
					ret.add(stuinfo);
					
					
				}
					
			}
		      
			
		}catch(Exception ex){ex.printStackTrace();}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return ret;
	}
	
	//ReportID = MIS_UA_002
	public ArrayList<HashMap<String,String>> getHourlyCountLoginDetailsByDateByrole(String user_id,String Org_id,String startDate,String endDate,String Role )
	{
		ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
		try
		{
			con = DBTransaction.connect();
			
			ResultSet resultSet = null;
			String sql="select  to_date(b.date_time,'YYYY-MM-DD') as bdate, a.privilege, extract(hour from to_timestamp(b.date_time,'YYYY-MM-DD HH24:MI:SS:MS')) as bhour, count(*) from users_info a, tracking_table b  where a.organization_id = '"+Org_id+"' and a.privilege in('Faculty','Student') and a.user_id = b.student_id and b.log_status = 'in'  group by bdate, a.privilege, bhour  order by bdate, a.privilege, bhour";
			if(Role.trim().equalsIgnoreCase("faculty")) // role checking 
			{
				sql="select  to_date(b.date_time,'YYYY-MM-DD') as bdate, a.privilege, extract(hour from to_timestamp(b.date_time,'YYYY-MM-DD HH24:MI:SS:MS')) as bhour, count(*) from users_info a, tracking_table b  where a.privilege='Faculty' and a.organization_id = '"+Org_id+"' and a.user_id = b.student_id and b.log_status = 'in'  group by bdate, a.privilege, bhour  order by bdate, a.privilege, bhour";
			}
			if(Role.trim().equalsIgnoreCase("student")) // role checking
			{
				sql="select  to_date(b.date_time,'YYYY-MM-DD') as bdate, a.privilege, extract(hour from to_timestamp(b.date_time,'YYYY-MM-DD HH24:MI:SS:MS')) as bhour, count(*) from users_info a, tracking_table b  where a.privilege='Student' and a.organization_id = '"+Org_id+"' and a.user_id = b.student_id and b.log_status = 'in'  group by bdate, a.privilege, bhour  order by bdate, a.privilege, bhour";
			}
					
			System.out.println("getHourlyCountLoginDetailsByDateByrole \n"+sql);
			preparedStatement = con.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) 
			{
				String Date=resultSet.getString("bdate").toString().trim();
				String Privilege=resultSet.getString("privilege").toString().trim();
				String Hour=resultSet.getString("bhour").toString().trim();
				String LoginCount=resultSet.getString("count").toString().trim();
				
				if(Hour.trim().length()>0)
				{
					try
					{
						int hr=Integer.parseInt(Hour);
						int hrLat=hr+1;
						/*if(hrLat==24)
							hrLat=0;
						*/
						Hour=hr+" - "+hrLat;	
					}catch(Exception ex){}
					
				}
				
				SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
								
				Date Logindt=sdf.parse(formatDate(Date, "yyyy-MM-dd", "dd/MM/yyyy"));
				
				Date sdt=sdf.parse(startDate);
				Date edt=sdf.parse(endDate);
				
				if( validateDateRange(Logindt, sdt, edt) ) // after startDate and before endDate checking here
				{
					
					HashMap<String,String> stuinfo=new HashMap<String,String>();
					stuinfo.put("Date", formatDate(Date, "yyyy-MM-dd", "dd/MM/yyyy"));
					stuinfo.put("Privilege", Privilege);
					stuinfo.put("Hour", Hour);
					stuinfo.put("LoginCount", LoginCount);
					
					ret.add(stuinfo);
					
					
				}
					
			}
		      
			
		}catch(Exception ex){}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return ret;
	}

	//ReportID = MIS_UA_003
	public ArrayList<HashMap<String,String>> getUsersClockTimeDetailsByDateByrole(String user_id,String Org_id,String startDate,String endDate,String Role,String SearchVal)
	{
		ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
		try
		{
			con = DBTransaction.connect();
			
			ResultSet resultSet = null;
			String sql="select a.privilege, b.student_id, a.username, to_date(b.date_time,'YYYY-MM-DD') as bdate, to_timestamp(b.date_time,'YYYY-MM-DD HH24:MI:SS') as bdatetime, b.log_status from users_info a, tracking_table b where a.organization_id = '"+Org_id+"' and a.privilege in('Faculty','Student') and a.user_id = b.student_id  order by a.privilege, b.student_id, bdatetime";
						
			if(Role.trim().equalsIgnoreCase("faculty")) // role checking 
			{
				sql="select a.privilege, b.student_id, a.username, to_date(b.date_time,'YYYY-MM-DD') as bdate, to_timestamp(b.date_time,'YYYY-MM-DD HH24:MI:SS') as bdatetime, b.log_status from users_info a, tracking_table b where a.privilege='Faculty' and a.organization_id = '"+Org_id+"' and a.user_id = b.student_id  order by a.privilege, b.student_id, bdatetime";
				if(!SearchVal.trim().equalsIgnoreCase("All"))
					sql="select a.privilege, b.student_id, a.username, to_date(b.date_time,'YYYY-MM-DD') as bdate, to_timestamp(b.date_time,'YYYY-MM-DD HH24:MI:SS') as bdatetime, b.log_status from users_info a, tracking_table b where a.user_id='"+SearchVal+"' and a.privilege='Faculty' and a.organization_id = '"+Org_id+"' and a.user_id = b.student_id  order by a.privilege, b.student_id, bdatetime";
			}
			if(Role.trim().equalsIgnoreCase("student")) // role checking
			{
				sql="select a.privilege, b.student_id, a.username, to_date(b.date_time,'YYYY-MM-DD') as bdate, to_timestamp(b.date_time,'YYYY-MM-DD HH24:MI:SS') as bdatetime, b.log_status from users_info a, tracking_table b where a.privilege='Student' and a.organization_id = '"+Org_id+"' and a.user_id = b.student_id  order by a.privilege, b.student_id, bdatetime";
				if(!SearchVal.trim().equalsIgnoreCase("All"))
					sql="select a.privilege, b.student_id, a.username, to_date(b.date_time,'YYYY-MM-DD') as bdate, to_timestamp(b.date_time,'YYYY-MM-DD HH24:MI:SS') as bdatetime, b.log_status from users_info a, tracking_table b where a.user_id='"+SearchVal+"' and a.privilege='Student' and a.organization_id = '"+Org_id+"' and a.user_id = b.student_id  order by a.privilege, b.student_id, bdatetime";
			
			}
						

			System.out.println("getUsersClockTimeDetailsByDateByrole \n"+sql);
			preparedStatement = con.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			Hashtable<String,HashMap<String,String>> htDuration=new Hashtable<String,HashMap<String,String>>(); 
			while(resultSet.next()) 
			{
				String Privilege=resultSet.getString("privilege").toString().trim();
				String student_id=resultSet.getString("student_id").toString().trim();
				String username=resultSet.getString("username").toString().trim();
				String bdate=resultSet.getString("bdate").toString().trim();
				String bdatetime=resultSet.getString("bdatetime").toString().trim();
				String log_status=resultSet.getString("log_status").toString().trim();
			
				SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
				Date Logindt=sdf.parse(formatDate(bdate, "yyyy-MM-dd", "dd/MM/yyyy"));
				Date sdt=sdf.parse(startDate);
				Date edt=sdf.parse(endDate);
				
				if( validateDateRange(Logindt, sdt, edt) ) // after startDate and before endDate checking here
				{
					HashMap<String,String> ClockInfo=new HashMap<String,String>();
					ClockInfo.put("Privilege", Privilege);
					ClockInfo.put("UserID", student_id);
					ClockInfo.put("LoginDate", formatDate(bdate, "yyyy-MM-dd", "dd/MM/yyyy"));
					ClockInfo.put("UserName", username);
					ClockInfo.put("LoginDateTime", formatDate(bdatetime, "yyyy-MM-dd HH:mm:ss", "dd/MM/yyyy HH:mm:ss"));
					ClockInfo.put("log_status", log_status);
					ClockInfo.put("Duration", "0");
					
					ret.add(ClockInfo);
					
				}
					
			}
		      
			
		}catch(Exception ex){}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return ret;
	}
	//
	//ReportID = MIS_UA_010
		public ArrayList<HashMap<String,String>> getActiveClassroomsDetailsByDate(String user_id,String Org_id,String startDate,String endDate,String sessionId)
		{
			ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
			try
			{
				con = DBTransaction.connect();
				
				ResultSet resultSet = null;
				String sql="select to_date((to_char(b.date_time, 'YYYY-MM-DD HH24:MI:SS:MS')),'YYYY-MM-DD') as cdate, b.session_id, b.student_id, b.log_status	from users_info a, classroom_tracking b where a.organization_id = '"+Org_id+"' and a.user_id = b.student_id and b.privilege = 'Student' and b.log_status = 'in' order by cdate, b.session_id, b.student_id";
				if(!sessionId.trim().equalsIgnoreCase("All"))
					sql="select to_date((to_char(b.date_time, 'YYYY-MM-DD HH24:MI:SS:MS')),'YYYY-MM-DD') as cdate, b.session_id, b.student_id, b.log_status	from users_info a, classroom_tracking b where b.session_id='"+sessionId+"' and a.organization_id = '"+Org_id+"' and a.user_id = b.student_id and b.privilege = 'Student' and b.log_status = 'in' order by cdate, b.session_id, b.student_id";
						 
				System.out.println("getActiveClassroomsDetailsByDate \n"+sql);
				preparedStatement = con.prepareStatement(sql);

				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) 
				{
					String cdate=resultSet.getString("cdate").toString().trim();
					String session_id="";if(resultSet.getString("session_id")!=null) session_id=resultSet.getString("session_id").toString().trim();
					String student_id=resultSet.getString("student_id").toString().trim();
					String LogStatus=resultSet.getString("log_status").toString().trim();
					
					
					SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
					Date Logindt=sdf.parse(formatDate(cdate, "yyyy-MM-dd","dd/MM/yyyy"));
					Date sdt=sdf.parse(startDate);
					Date edt=sdf.parse(endDate);
					
					if( validateDateRange(Logindt, sdt, edt) ) // after startDate and before endDate checking here
					{
						
						HashMap<String,String> stuinfo=new HashMap<String,String>();
						stuinfo.put("Date", formatDate(cdate, "yyyy-MM-dd","dd/MM/yyyy"));
						stuinfo.put("SessionID", session_id);
						stuinfo.put("student_id", student_id);
						stuinfo.put("LogStatus", LogStatus);
												
						ret.add(stuinfo);
						
					}
						
				}
			      
				
			}catch(Exception ex){ex.printStackTrace();}
			finally
			   {
				   try{
					   con.close();
					   preparedStatement.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
			return ret;
		}

		//ReportID = MIS_UA_011
		public ArrayList<HashMap<String,String>> getDisconnectedClassroomsDetailsByDate(String user_id,String Org_id,String startDate,String endDate,String sessionId)
		{
			ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
			try
			{
				con = DBTransaction.connect();
				
				ResultSet resultSet = null;
				String sql="select to_date((to_char(b.date_time, 'YYYY-MM-DD HH24:MI:SS:MS')),'YYYY-MM-DD') as cdate, b.session_id, b.student_id, b.date_time,b.log_status from users_info a, classroom_tracking b where a.organization_id = '"+Org_id+"' and a.user_id = b.student_id and b.privilege = 'Student'  order by cdate, b.session_id, b.student_id, b.date_time";
				if(!sessionId.trim().equalsIgnoreCase("All"))
					sql="select to_date((to_char(b.date_time, 'YYYY-MM-DD HH24:MI:SS:MS')),'YYYY-MM-DD') as cdate, b.session_id, b.student_id, b.date_time,b.log_status from users_info a, classroom_tracking b where b.session_id='"+sessionId+"' and a.organization_id = '"+Org_id+"' and a.user_id = b.student_id and b.privilege = 'Student'  order by cdate, b.session_id, b.student_id, b.date_time";
						  
				System.out.println("getDisconnectedClassroomsDetailsByDate \n"+sql);
				preparedStatement = con.prepareStatement(sql);

				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) 
				{
					
					String cdate=resultSet.getString("cdate").toString().trim();
					if(resultSet.getObject("session_id")==null)
						continue;
					String session_id=resultSet.getString("session_id").toString().trim();
					String student_id=resultSet.getString("student_id").toString().trim();
					String LogStatus=resultSet.getString("log_status").toString().trim();
					
					SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
					
					Date Logindt=sdf.parse(formatDate(cdate, "yyy-MM-dd", "dd/MM/yyyy")); 
					Date sdt=sdf.parse(startDate);
					Date edt=sdf.parse(endDate);
					
					if( validateDateRange(Logindt, sdt, edt) ) // after startDate and before endDate checking here
					{
						
						HashMap<String,String> stuinfo=new HashMap<String,String>();
						stuinfo.put("Date", formatDate(cdate, "yyy-MM-dd", "dd/MM/yyyy"));
						stuinfo.put("SessionID", session_id);
						stuinfo.put("student_id", student_id);
						stuinfo.put("LogStatus", LogStatus);
						stuinfo.put("StudentID", student_id);
												
						ret.add(stuinfo);
						
					}
						
				}
			      
				
			}catch(Exception ex){ex.printStackTrace();}
			finally
			   {
				   try{
					   con.close();
					   preparedStatement.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
			return ret;
		}	
	
				
	//ReportID = MIS_AD_001
	public ArrayList<HashMap<String,String>> getPendingRegistrationsByOrgID(String Org_id)
	{
		ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
		try
		{
			System.out.println("Org_id :: "+Org_id);
			con = DBTransaction.connect();
			
			ResultSet resultSet = null;
			String sql="select user_id, username, email, contact_no, country from users_info where organization_id = '"+Org_id+"' and privilege = 'Student' and users_status in ('Inactive','InActive')";
						
			System.out.println("getPendingRegistrationsByOrgID \n"+sql);
			preparedStatement = con.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) 
			{
				String UserId=resultSet.getString("user_id").toString().trim();
				String UserName=resultSet.getString("username").toString().trim();
				String Email=resultSet.getString("email").toString().trim();
				String contact_no=resultSet.getString("contact_no").toString().trim();
				String country=""; if(resultSet.getString("country")!=null) country=resultSet.getString("country").toString().trim();
				
				HashMap<String,String> stuinfo=new HashMap<String,String>();
				stuinfo.put("UserId", UserId);
				stuinfo.put("UserName", UserName);
				stuinfo.put("Email", Email);
				stuinfo.put("contact_no", contact_no);
				stuinfo.put("country", country);
					
				ret.add(stuinfo);
			}
		      
			
		}catch(Exception ex){}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return ret;
	}

	//ReportID = MIS_AD_002
	public ArrayList<HashMap<String,String>> getCompletedRegistrationsByOrgID(String Org_id)
	{
		ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
		try
		{
			System.out.println("Org_id :: "+Org_id);
			con = DBTransaction.connect();
			
			ResultSet resultSet = null;
			String sql="select user_id, username, email, contact_no, country from users_info where organization_id = '"+Org_id+"' and privilege = 'Student' and users_status in ('Active','active')";
			
			System.out.println("getCompletedRegistrationsByOrgID \n"+sql);
			preparedStatement = con.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) 
			{
				String UserId=resultSet.getString("user_id").toString().trim();
				String UserName=resultSet.getString("username").toString().trim();
				String Email=resultSet.getString("email").toString().trim();
				String contact_no=resultSet.getString("contact_no").toString().trim();
				String country=resultSet.getString("country").toString().trim();
				
				HashMap<String,String> stuinfo=new HashMap<String,String>();
				stuinfo.put("UserId", UserId);
				stuinfo.put("UserName", UserName);
				stuinfo.put("Email", Email);
				stuinfo.put("contact_no", contact_no);
				stuinfo.put("country", country);
					
				ret.add(stuinfo);
			}
		      
			
		}catch(Exception ex){}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return ret;
	}

	//ReportID = MIS_AC_001
	public ArrayList<HashMap<String,String>> getDetailsStudentAttendingClasses(String user_id,String Org_id,String startDate,String endDate,String sessionId)
	{
		
		ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
		try
			{
			
			con = DBTransaction.connect();
			ResultSet resultSet=null;
			
			String sql="select a.course_id_defined_by_teaching_source, b.category, b.session_id, b.session_name, to_date((to_char(c.date_time, 'YYYY-MM-DD HH24:MI:SS:MS')),'YYYY-MM-DD') as cdate, c.student_id, d.username "
							+ "from course_description a, session_details b, classroom_tracking c, users_info d "
							+ "where a.customer_id = '"+Org_id+"' and b.category = a.course_id_defined_by_teaching_source and b.session_id = c.session_id and c.student_id = d.user_id and c.privilege = 'Student' "
							+ "group by a.course_id_defined_by_teaching_source, b.category, b.session_id, b.session_name, cdate, c.student_id, d.username "
							+ "order by b.category, b.session_id, cdate, c.student_id ";
			
			if(!sessionId.trim().equalsIgnoreCase("All"))
						sql="select a.course_id_defined_by_teaching_source, b.category, b.session_id, b.session_name, to_date((to_char(c.date_time, 'YYYY-MM-DD HH24:MI:SS:MS')),'YYYY-MM-DD') as cdate, c.student_id, d.username "
								+ "from course_description a, session_details b, classroom_tracking c, users_info d "
								+ "where a.customer_id = '"+Org_id+"' and b.category = a.course_id_defined_by_teaching_source and b.session_id = c.session_id and c.student_id = d.user_id and c.privilege = 'Student' and c.session_id='"+sessionId+"' "
								+ "group by a.course_id_defined_by_teaching_source, b.category, b.session_id, b.session_name, cdate, c.student_id, d.username "
								+ "order by b.category, b.session_id, cdate, c.student_id ";
					
						
					
					System.out.println("getDetailsStudentAttendingClasses \n"+sql);
					preparedStatement = con.prepareStatement(sql);

					resultSet = preparedStatement.executeQuery();
					while(resultSet.next()) 
					{
						String SessionId=resultSet.getString("session_id").toString().trim();
						String StudentId=resultSet.getString("student_id").toString().trim();
						String CDate=resultSet.getString("cdate").toString().trim();	
						
						String SessionName=resultSet.getString("session_name").toString().trim();
						String UserName=resultSet.getString("username").toString().trim();
						
						//System.out.println("DAO"+SessionId+"=="+StudentId+"=="+CDate);
						SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
						
						Date Logindt=sdf.parse(formatDate(CDate, "yyyy-MM-dd", "dd/MM/yyyy"));
						Date sdt=sdf.parse(startDate);
						Date edt=sdf.parse(endDate);
						
						if( validateDateRange(Logindt, sdt, edt) ) // after startDate and before endDate checking here
						{
							HashMap<String,String> stuinfo=new HashMap<String,String>();
							stuinfo.put("SessionId", SessionId);
							stuinfo.put("StudentId", StudentId);
							stuinfo.put("Date_Time", formatDate(CDate, "yyyy-MM-dd", "dd/MM/yyyy"));
							
							stuinfo.put("StudentName", UserName);
							stuinfo.put("SessionName", SessionName);
							ret.add(stuinfo);
													
						}
							
					}
				      
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
				finally
				   {
					   try{
						   con.close();
						   preparedStatement.close();
					   }
					   catch(Exception e){
							e.printStackTrace();
				   }
				   }
				return ret;
			}

	//ReportID = MIS_AC_001
		public ArrayList<HashMap<String,String>> getDetailsFacultyPresentingClasses(String user_id,String Org_id,String startDate,String endDate,String sessionId)
		{
			
			ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
			try
				{
						con = DBTransaction.connect();
						
						ResultSet resultSet = null;
						String sql="select a.course_id_defined_by_teaching_source, b.category, b.session_id, b.session_name, to_date((to_char(c.date_time, 'YYYY-MM-DD HH24:MI:SS:MS')),'YYYY-MM-DD') as cdate, c.student_id, d.username "
								+ "from course_description a, session_details b, classroom_tracking c, users_info d "
								+ "where a.customer_id = '"+Org_id+"' and b.category = a.course_id_defined_by_teaching_source and b.session_id = c.session_id and c.student_id = d.user_id and c.privilege = 'Faculty' "
								+ "group by a.course_id_defined_by_teaching_source, b.category, b.session_id, b.session_name, cdate, c.student_id, d.username "
								+ "order by b.category, b.session_id, cdate, c.student_id ";
				
				if(!sessionId.trim().equalsIgnoreCase("All"))
							sql="select a.course_id_defined_by_teaching_source, b.category, b.session_id, b.session_name, to_date((to_char(c.date_time, 'YYYY-MM-DD HH24:MI:SS:MS')),'YYYY-MM-DD') as cdate, c.student_id, d.username "
									+ "from course_description a, session_details b, classroom_tracking c, users_info d "
									+ "where a.customer_id = '"+Org_id+"' and b.category = a.course_id_defined_by_teaching_source and b.session_id = c.session_id and c.student_id = d.user_id and c.privilege = 'Faculty' and c.session_id='"+sessionId+"' "
									+ "group by a.course_id_defined_by_teaching_source, b.category, b.session_id, b.session_name, cdate, c.student_id, d.username "
									+ "order by b.category, b.session_id, cdate, c.student_id ";
				
						System.out.println("getDetailsFacultyPresentingClasses \n"+sql);
						preparedStatement = con.prepareStatement(sql);

						resultSet = preparedStatement.executeQuery();
						while(resultSet.next()) 
						{
							String SessionId=resultSet.getString("session_id").toString().trim();
							String FacultyId=resultSet.getString("student_id").toString().trim();
							String CDate=resultSet.getString("cdate").toString().trim();	
							
							String SessionName=resultSet.getString("session_name").toString().trim();
							String UserName=resultSet.getString("username").toString().trim();
							
							SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
							Date Logindt=sdf.parse(formatDate(CDate, "yyyy-MM-dd", "dd/MM/yyyy"));
							Date sdt=sdf.parse(startDate);
							Date edt=sdf.parse(endDate);
							
							if( validateDateRange(Logindt, sdt, edt) ) // after startDate and before endDate checking here
							{
								
								HashMap<String,String> stuinfo=new HashMap<String,String>();
								stuinfo.put("SessionId", SessionId);
								stuinfo.put("FacultyId", FacultyId);
								stuinfo.put("Date_Time", formatDate(CDate, "yyyy-MM-dd", "dd/MM/yyyy"));
								
								stuinfo.put("FacultyName", UserName);
								stuinfo.put("SessionName", SessionName);
								
								ret.add(stuinfo);
								
								
							}
								
						}
					      
						
					}catch(Exception ex){
						ex.printStackTrace();
					}
					finally
					   {
						   try{
							   con.close();
							   preparedStatement.close();
						   }
						   catch(Exception e){
								e.printStackTrace();
					   }
					   }
					return ret;
	}
	  
		public HashMap<String,String> getAllFacultiesByOrgID(String OrgID)
		{
			HashMap<String,String> ret=new HashMap<String,String>();
			try
			{
				con = DBTransaction.connect();
				
				ResultSet resultSet = null;
				String sql="select * from users_info where organization_id='"+OrgID+"' and privilege='Faculty' ";
						
				preparedStatement = con.prepareStatement(sql);

				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) 
				{
					String session_id=resultSet.getString("user_id").toString().trim();
					String session_name=resultSet.getString("username").toString().trim();
					ret.put(session_id, session_name);
				}
			      
				
			}catch(Exception ex){}
			finally
			   {
				   try{
					   con.close();
					   preparedStatement.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
			
			return ret;
		}
		public HashMap<String,String> getAllStudentsByOrgID(String OrgID)
		{
			HashMap<String,String> ret=new HashMap<String,String>();
			try
			{
				con = DBTransaction.connect();
				
				ResultSet resultSet = null;
				String sql="select * from users_info where organization_id='"+OrgID+"' and privilege='Student' ";
						
				preparedStatement = con.prepareStatement(sql);

				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) 
				{
					String session_id=resultSet.getString("user_id").toString().trim();
					String session_name=resultSet.getString("username").toString().trim();
					ret.put(session_id, session_name);
				}
			      
				
			}catch(Exception ex){}
			finally
			   {
				   try{
					   con.close();
					   preparedStatement.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
			
			return ret;
		}
		
	public HashMap<String,String> getAllSessionsByOrgID(String OrgID)
	{
		HashMap<String,String> ret=new HashMap<String,String>();
		try
		{
			con = DBTransaction.connect();
			
			ResultSet resultSet = null;
			String sql="select * from session_details where organization_id='"+OrgID+"' ";
					
			preparedStatement = con.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) 
			{
				String session_id=resultSet.getString("session_id").toString().trim();
				String session_name=resultSet.getString("session_name").toString().trim();
				ret.put(session_id, session_name);
			}
		      
			
		}catch(Exception ex){}
		finally
		   {
			   try{
				   con.close();
				   preparedStatement.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		
		return ret;
	}
	
	//ReportID = MIS_AD_004
		public ArrayList<HashMap<String,String>> getStudentsAppliedCourses(String OrgID)
		{
			ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
			try
			{
				String OrgName="";
				con=DBTransaction.connect();
				ResultSet resultSet = null;
				String sqlorgname="select organization_name from org_details where organization_id ='"+OrgID+"'";
				preparedStatement=con.prepareStatement(sqlorgname);
				resultSet=preparedStatement.executeQuery();
				while(resultSet.next()) 
				{
					OrgName=resultSet.getString("organization_name").toString().trim();
				}
				
				preparedStatement.close();
				con.close();
				
				con = DBTransaction.connectKnoWStore();
				
				//String sql="select a.course_id, b.course_name, a.enrollment_process_id, a.full_name, a.username, a.country, a.email from transaction_enrollment_process a, transaction_course_info b where a.course_id = b.course_id order by a.course_id, a.country";
				String sql=	"select b.course_id_declared_by_teaching_source as course_id,c.department_name as course_name,a.enrollment_process_id, a.full_name, a.username,  a.country, a.email from transaction_enrollment_process a, transaction_course_description_info b, transaction_department_info c	"
							+ "where a.course_id = b.course_id and a.middle_layer_teaching_source_name in ('"+OrgID+"','"+OrgName+"') and c.department_id = b.course_id order by a.enrollment_process_id, b.course_id_declared_by_teaching_source";
				
				System.out.println("getStudentsAppliedCourses \n"+sql);
				preparedStatement = con.prepareStatement(sql);

				resultSet = preparedStatement.executeQuery();
				// System.out.println("resultSet size == "+resultSet.getFetchSize());
				while(resultSet.next()) 
				{
					String course_id=resultSet.getString("course_id").toString().trim();
					String course_name=resultSet.getString("course_name").toString().trim();
					String enrollment_process_id=resultSet.getString("enrollment_process_id").toString().trim();
					String full_name=resultSet.getString("full_name").toString().trim();
					String username=resultSet.getString("username").toString().trim();
					String country="";
					if(resultSet.getObject("country")!=null)
						country=resultSet.getString("country").toString().trim();
					String email=resultSet.getString("email").toString().trim();
					
					HashMap<String,String> info=new HashMap<String,String>();
					info.put("course_id", course_id);
					info.put("course_name", course_name);
					info.put("enrollment_process_id", enrollment_process_id);
					info.put("full_name", full_name);
					info.put("username", username);
					info.put("country", country);
					info.put("email", email);
						
					ret.add(info);
				}
			      
				
			}catch(Exception ex){ex.printStackTrace();}
			finally
			   {
				   try{
					   con.close();
					   preparedStatement.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
			return ret;
		}

		//getStudentAssignmentsResultReports
		public ArrayList<HashMap<String,String>> getStudentResultReports(String user_id,String Org_id, String status,String previlege){
			ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
			try
			{

				con = DBTransaction.connect();
				
				ResultSet resultSet = null;
				String sql="select a.uploaded_by, a.assessment_id, b.assessment_description, a.marks, a.status from assessment_student_info a, assessment_info b where b.organization_id = '"+Org_id+"' and b.assessment_id = a.assessment_id order by a.uploaded_by";
				if(previlege.trim().equalsIgnoreCase("Student"))
					sql="select a.uploaded_by, a.assessment_id, b.assessment_description, a.marks, a.status from assessment_student_info a, assessment_info b where a.uploaded_by = '"+user_id+"' and b.organization_id = '"+Org_id+"' and b.assessment_id = a.assessment_id order by a.uploaded_by";
				
				System.out.println("getStudentResultReports \n"+sql);
				preparedStatement = con.prepareStatement(sql);

				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) 
				{
					if(resultSet.getObject("status")==null)
						continue;
					
					String ststus=resultSet.getString("status").toString().trim();
					String uploaded_by=resultSet.getString("uploaded_by").toString().trim();
					String assessment_id=resultSet.getString("assessment_id").toString().trim();
					String assessment_description=resultSet.getString("assessment_description").toString().trim();
					String marks=resultSet.getString("marks").toString().trim();
					
				//	System.out.println("DAO  "+uploaded_by+"=="+ststus+"=="+assessment_id+"=="+assessment_description+"=="+marks);
					HashMap<String,String> stuinfo=new HashMap<String,String>();
					stuinfo.put("uploaded_by", uploaded_by);
					stuinfo.put("ststus", ststus);
					
					if(ststus.trim().equalsIgnoreCase("pass"))
						stuinfo.put("ststus", "Pass");
					else if(ststus.trim().equalsIgnoreCase("fail"))
						stuinfo.put("ststus", "Fail");
					
					stuinfo.put("assessment_id", assessment_id); 
					stuinfo.put("assessment_description", assessment_description);
					stuinfo.put("marks", marks);
					
					if(status.trim().equalsIgnoreCase("Pass") && ststus.trim().equalsIgnoreCase("Pass"))
						ret.add(stuinfo);
					else if(status.trim().equalsIgnoreCase("Fail") && ststus.trim().equalsIgnoreCase("Fail"))
						ret.add(stuinfo);
					else if(status.trim().equalsIgnoreCase("All"))
						ret.add(stuinfo);
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
			finally
			   {
				   try{
					   con.close();
					   preparedStatement.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
			return ret;
		}

		public ArrayList<HashMap<String,String>> getStudentAssignmentsResultReports(String user_id,String Org_id, String status,String previlege){
			ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
			try
			{

				String Resultstatus="'Pass','PASS','pass','Good','GOOD','good',"
						+ "'Fail','FAIL','fail','Bad','BAD','bad'";
				if(status.trim().equalsIgnoreCase("Pass"))
				{
					Resultstatus="'Pass','PASS','pass','Good','GOOD','good'";
				}else if(status.trim().equalsIgnoreCase("Fail"))
				{
					Resultstatus="'Fail','FAIL','fail','Bad','BAD','bad'";
				}
				con = DBTransaction.connect();
				
				ResultSet resultSet = null;
				String	sql="select b.uploaded_by, c.username, a.assessment_id as assignment_id, a.assessment_name as assignment_description, b.marks,b.status from assessment_info a, assessment_student_info b, users_info c "
						+ "where a.organization_id ='"+Org_id+"'  and  b.status in ("+Resultstatus+") and b.assessment_id = a.assessment_id "
						+ "and c.user_id = b.uploaded_by";
				if(previlege.trim().equalsIgnoreCase("Faculty"))
						sql="select b.uploaded_by, c.username, a.assessment_id as assignment_id, a.assessment_name as assignment_description, b.marks,b.status from assessment_info a, assessment_student_info b, users_info c "
							+ "where a.uploaded_by = '"+user_id+"' and a.organization_id ='"+Org_id+"'  and  b.status in ("+Resultstatus+") and b.assessment_id = a.assessment_id "
							+ "and c.user_id = b.uploaded_by";
				if(previlege.trim().equalsIgnoreCase("Student"))
						sql="select b.uploaded_by, c.username, a.assessment_id as assignment_id, a.assessment_name as assignment_description, b.marks,b.status from assessment_info a, assessment_student_info b, users_info c "
							+ "where b.uploaded_by = '"+user_id+"' and a.organization_id ='"+Org_id+"' and b.status in ("+Resultstatus+") and b.assessment_id = a.assessment_id "
							+ "and c.user_id = b.uploaded_by";
				
				System.out.println("getStudentAssignmentsResultReports \n"+sql);
				preparedStatement = con.prepareStatement(sql);

				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) 
				{
					if(resultSet.getObject("status")==null)
						continue;
					
					String ststus=resultSet.getString("status").toString().trim();
					String uploaded_by=resultSet.getString("uploaded_by").toString().trim();
					String assignment_id=resultSet.getString("assignment_id").toString().trim();
					String assignment_description=resultSet.getString("assignment_description").toString().trim();
					String marks=resultSet.getString("marks").toString().trim();
					
					// System.out.println("DAO  "+uploaded_by+"=="+ststus+"=="+assignment_id+"=="+assignment_description+"=="+marks);
					HashMap<String,String> stuinfo=new HashMap<String,String>();
					stuinfo.put("uploaded_by", uploaded_by);
					stuinfo.put("ststus", ststus);
					
					if(ststus.trim().equalsIgnoreCase("pass") || ststus.trim().equalsIgnoreCase("good"))
						stuinfo.put("ststus", "Pass");
					else if(ststus.trim().equalsIgnoreCase("fail") || ststus.trim().equalsIgnoreCase("bad") )
						stuinfo.put("ststus", "Fail");
					
					stuinfo.put("assignment_id", assignment_id); 
					stuinfo.put("assignment_description", assignment_description);
					stuinfo.put("marks", marks);

					/*if(status.trim().equalsIgnoreCase("Pass") && ststus.trim().equalsIgnoreCase("Pass"))
						ret.add(stuinfo);
					else if(status.trim().equalsIgnoreCase("Fail") && ststus.trim().equalsIgnoreCase("Fail"))
						ret.add(stuinfo);
					else if(status.trim().equalsIgnoreCase("All"))
					*/
					ret.add(stuinfo);
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
			finally
			   {
				   try{
					   con.close();
					   preparedStatement.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
			return ret;
		}

		
		public ArrayList<HashMap<String,String>> getCertificateStatusReports(String Org_id, String issued_status,String Course){
			ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
			try
			{

				String OrgName="";
				con=DBTransaction.connect();
				ResultSet resultSet = null;
				String sqlorgname="select organization_name from org_details where organization_id ='"+Org_id+"'";
				preparedStatement=con.prepareStatement(sqlorgname);
				resultSet=preparedStatement.executeQuery();
				while(resultSet.next()) 
				{
					OrgName=resultSet.getString("organization_name").toString().trim();
				}
				
				preparedStatement.close();
				con.close();
				
				con = DBTransaction.connectKnoWStore();
				
				
				String sql="select a.enrollment_process_id, a.full_name, a.phone, c.course_id_declared_by_teaching_source, b.department_name as course_name,  a.email "
						+ "from transaction_enrollment_process a, transaction_department_info b, transaction_course_description_info c "
						+ "where a.middle_layer_teaching_source_name in ('"+Org_id+"','"+OrgName+"')  and a.course_completed = 'YES' and "
						+ "a.certificate_issued = '"+issued_status+"' and b.department_id = a.course_id and c.course_id = b.department_id";

				if(!Course.trim().equalsIgnoreCase("All"))
					sql="select a.enrollment_process_id, a.full_name, a.phone, c.course_id_declared_by_teaching_source, b.department_name as course_name,  a.email "
							+ "from transaction_enrollment_process a, transaction_department_info b, transaction_course_description_info c "
							+ "where a.middle_layer_teaching_source_name in ('"+Org_id+"','"+OrgName+"')  and a.course_completed = 'YES' and "
							+ "a.certificate_issued = '"+issued_status+"' and a.course_id in ('"+Course+"') and b.department_id = a.course_id and c.course_id = b.department_id";

				
				 System.out.println("getCertificateStatusReports \n "+sql);
				preparedStatement = con.prepareStatement(sql);

				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) 
				{
										
					String course_id_declared_by_teaching_source=resultSet.getString("course_id_declared_by_teaching_source").toString().trim();
					String course_name=resultSet.getString("course_name").toString().trim();
					String enrollment_process_id=resultSet.getString("enrollment_process_id").toString().trim();
					String full_name=resultSet.getString("full_name").toString().trim();
					String phone=resultSet.getString("phone").toString().trim();
					String email=resultSet.getString("email").toString().trim();
					
					HashMap<String,String> stuinfo=new HashMap<String,String>();
					stuinfo.put("course_id_declared_by_teaching_source", course_id_declared_by_teaching_source);
					stuinfo.put("course_name", course_name);
					stuinfo.put("enrollment_process_id", enrollment_process_id); 
					stuinfo.put("full_name", full_name);
					stuinfo.put("phone", phone);
					stuinfo.put("email", email);
					ret.add(stuinfo);
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
			finally
			   {
				   try{
					   con.close();
					   preparedStatement.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
			
			// System.out.println("ret :: "+ret.size());
			return ret;
		}

		public ArrayList<HashMap<String,String>> getExamEligibleStatusReports(String Org_id, String issued_status,String Course,String previlege,String user_id){
			ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
			try
			{
				
				ResultSet resultSet = null;
				String sql="";
				
				String cdid="";
				ArrayList<HashMap<String,String>> courseList= getCourseDetailsByOrgId(Org_id);
				if(courseList!=null && courseList.size()>0)
				 {
					 for(int i=0;i<courseList.size();i++)
					 {
						 HashMap<String,String> info=courseList.get(i);
						 String course_id=info.get("course_id").toString().trim();
						 String course_discription_id=info.get("course_discription_id").toString().trim();
						 if(course_id.trim().equalsIgnoreCase(Course))
							 {
							 	cdid=course_discription_id;
							 	i=courseList.size();
							 }
					 }
				 }
				
				con = DBTransaction.connectKnoWStore();
				if(issued_status.trim().equalsIgnoreCase("NO"))
				{
					sql="select enrollment_process_id, full_name, phone, email, username, 'Fees Pending' as reason from transaction_enrollment_process  "
							+ "where course_id in('"+Course+"') and enrollment_process_id not in "
							+ "(select distinct(enrolment_id) from txn_fee_structure where dept_id in('"+cdid+"') and semester_id = 1 and status = 'Received')";
					
					if(previlege.trim().equalsIgnoreCase("Student"))
					{
						sql="select enrollment_process_id, full_name, phone, email, username, 'Fees Pending' as reason from transaction_enrollment_process  "
								+ "where enrollment_process_id='"+user_id+"' and course_id in('"+Course+"') and enrollment_process_id not in "
								+ "(select distinct(enrolment_id) from txn_fee_structure where dept_id in('"+cdid+"') and semester_id = 1 and status = 'Received')";
					}
							
				}else if(issued_status.trim().equalsIgnoreCase("YES"))
				{
					sql="select enrollment_process_id, full_name, phone, email, username from transaction_enrollment_process  "
							+ "where course_id in('"+Course+"') and enrollment_process_id in "
							+ "(select distinct(enrolment_id) from txn_fee_structure where dept_id in('"+cdid+"') and semester_id = 1 and status = 'Received')";
					
					if(previlege.trim().equalsIgnoreCase("Student"))
					{
						sql="select enrollment_process_id, full_name, phone, email, username from transaction_enrollment_process  "
								+ "where enrollment_process_id='"+user_id+"' and course_id in('"+Course+"') and enrollment_process_id in "
								+ "(select distinct(enrolment_id) from txn_fee_structure where dept_id in('"+cdid+"') and semester_id = 1 and status = 'Received')";
					}
					
				}
				System.out.println("getExamEligibleStatusReports \n "+sql);
				preparedStatement = con.prepareStatement(sql);

				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) 
				{
										
					String enrollment_process_id=resultSet.getString("enrollment_process_id").toString().trim();
					String full_name=resultSet.getString("full_name").toString().trim();
					String phone=resultSet.getString("phone").toString().trim();
					String email=resultSet.getString("email").toString().trim();
					String username=resultSet.getString("username").toString().trim();
					String reason="";
					
					if(issued_status.trim().equalsIgnoreCase("NO") && resultSet.getString("reason")!=null)
						reason=resultSet.getString("reason").toString().trim();
					
					
					HashMap<String,String> stuinfo=new HashMap<String,String>();
					stuinfo.put("enrollment_process_id", enrollment_process_id); 
					stuinfo.put("full_name", full_name);
					stuinfo.put("phone", phone);
					stuinfo.put("email", email);
					stuinfo.put("username", username);
					stuinfo.put("reason", reason);
					ret.add(stuinfo);
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
			finally
			   {
				   try{
					   
					   con.close();
					   preparedStatement.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
			
			// System.out.println("ret :: "+ret.size());
			return ret;
		}
	
		public ArrayList<HashMap<String,String>> getStudentFeesPaidReports(String Org_id, String Course){
			ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
			try
			{

				con = DBTransaction.connectKnoWStore();
				ResultSet resultSet = null;
				
				/*String sql="select a.enrollment_process_id, a.full_name, d.course_id_declared_by_teaching_source, c.course_name, b.fee_details, b.amount, b.payment_mode,  "
						+ "(to_char(b.payment_date_time, 'DD/MM/YYYY')) as payment_date  from transaction_enrollment_process a, payment_management b, transaction_course_info c,"
						+ " transaction_course_description_info d  where b.username = a.username and b.course_id_declared_by_teaching_source = a.course_id and"
						+ "  b.course_id_declared_by_teaching_source = d.course_id and d.course_id = c.course_id";
				*/
				String sql=" select a.course_id,e.department_name, a.enrollment_process_id, a.full_name,a.phone,a.email, b.mlt_course_fee_code, d.mlt_fee_name, b.fee_amount, b.status "
						+ " from transaction_enrollment_process a, txn_fee_structure b, mst_mltsource_fees_x_mltsource_course c, mst_mltsource_x_fee_components d ,transaction_department_info e"
						+ " where a.course_id in ('"+Course+"') and b.enrolment_id = a.enrollment_process_id and b.semester_id = 1 " // and b.status <> 'Received'
						+ " and c.mlt_course_fee_code = b.mlt_course_fee_code and d.mlt_fee_code = c.mlt_fee_code and a.course_id=e.department_id";
				
				System.out.println("getStudentFeesPaidReports \n"+sql);
				preparedStatement = con.prepareStatement(sql);

				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) 
				{
										
					String enrollment_process_id=resultSet.getString("enrollment_process_id").toString().trim();
					String full_name=resultSet.getString("full_name").toString().trim();
					String course_id=resultSet.getString("course_id").toString().trim();
					String department_name=resultSet.getString("department_name").toString().trim();
					String mlt_course_fee_code=resultSet.getString("mlt_course_fee_code").toString().trim();
					String mlt_fee_name=resultSet.getString("mlt_fee_name").toString().trim();
					String fee_amount=resultSet.getString("fee_amount").toString().trim();
					String status=resultSet.getString("status").toString().trim();
					
					
					HashMap<String,String> info=new HashMap<String,String>();
					info.put("enrollment_process_id", enrollment_process_id);
					info.put("full_name", full_name);
					info.put("course_id", course_id);
					info.put("department_name", department_name);
					info.put("mlt_fee_name", mlt_fee_name);
					info.put("fee_amount", fee_amount);
					info.put("status", status);
					
					
					String StudentName=full_name + " ( "+enrollment_process_id +" )";
		    		String CourseName=department_name + " ( "+course_id +" )";
		    				    		
		    		info.put("StudentName", StudentName);
		    		info.put("CourseName", CourseName);
					
					ret.add(info);
					
				}
			}catch(Exception ex){}
			finally
			{

				   try{
					   con.close();
					   preparedStatement.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			}
			
			return ret;
		}
	
		public ArrayList<HashMap<String,String>> getNoOfEnrollmentsReports(String Org_id, String Course){
			ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
			try
			{

				String OrgName="";
				con=DBTransaction.connect();
				ResultSet resultSet = null;
				String sqlorgname="select organization_name from org_details where organization_id ='"+Org_id+"'";
				preparedStatement=con.prepareStatement(sqlorgname);
				resultSet=preparedStatement.executeQuery();
				while(resultSet.next()) 
				{
					OrgName=resultSet.getString("organization_name").toString().trim();
				}
				
				preparedStatement.close();
				con.close();
				
				con = DBTransaction.connectKnoWStore();
								
				//String sql="select c.course_name, a.country, count(*) as no_of_enrolments from transaction_enrollment_process a, transaction_course_description_info b, transaction_course_info c  where a.course_id = b.course_id and b.course_id = c.course_id  group by c.course_name, a.country";
				String sql="select b.department_name as course_name, a.country, count(*) as no_of_enrolments from transaction_enrollment_process a, transaction_department_info b	where a.middle_layer_teaching_source_name in( '"+Org_id+"','"+OrgName+"') and a.course_id = b.department_id group by b.department_name, a.country";

				if(!Course.trim().equalsIgnoreCase("All"))
				{
					// sql="select c.course_name, a.country, count(*) as no_of_enrolments from transaction_enrollment_process a, transaction_course_description_info b, transaction_course_info c  where a.course_id='"+Course+"' and a.course_id = b.course_id and b.course_id = c.course_id  group by c.course_name, a.country";
					
					sql="select b.department_name as course_name, a.country, count(*) as no_of_enrolments from transaction_enrollment_process a, transaction_department_info b	where a.course_id='"+Course+"' and a.middle_layer_teaching_source_name in( '"+Org_id+"','"+OrgName+"') and a.course_id = b.department_id group by b.department_name, a.country";
				}
				
				System.out.println("getNoOfEnrollmentsReports \n"+sql); 
				preparedStatement = con.prepareStatement(sql);
				
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) 
				{
						// System.out.println("inside while");				
					String course_name="";	
						if(resultSet.getString("course_name")!=null) 
								course_name=resultSet.getString("course_name").toString().trim();
					String country="";
						if(resultSet.getString("country")!=null) 
								country=resultSet.getString("country").toString().trim();
					String no_of_enrolments="";
						if(resultSet.getString("no_of_enrolments")!=null)
								no_of_enrolments=resultSet.getString("no_of_enrolments").toString().trim();
				
					// System.out.println(course_name + "  "+ country +"   "+no_of_enrolments);
					HashMap<String,String> info=new HashMap<String,String>();
					info.put("course_name", course_name);
					info.put("country", country);
					info.put("no_of_enrolments", no_of_enrolments);
					
					ret.add(info);
					
					
				}
				
				System.out.println(ret.toString());
				
			}catch(Exception ex){ex.printStackTrace();}
			finally
			{

				   try{
					   con.close();
					   preparedStatement.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			}
			
			return ret;
		}
	
		public HashMap<String,String> getOrganisationDetailsbyOrgID(String OrgID)
		{
			HashMap<String,String> ret=new HashMap<String,String>();
			try
			{
				con = DBTransaction.connect();
				
				ResultSet resultSet = null;
				String sql="select * from org_details where organization_id='"+OrgID+"'";
				
				preparedStatement = con.prepareStatement(sql);

				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) 
				{
					String organization_id=resultSet.getString("organization_id").toString().trim();
					String organization_name=resultSet.getString("organization_name").toString().trim();
					String org_type=resultSet.getString("org_type").toString().trim();
					String address=resultSet.getString("address").toString().trim();
					String city=resultSet.getString("city").toString().trim();
					String country=resultSet.getString("country").toString().trim();
					String postal_code=resultSet.getString("postal_code").toString().trim();
					String telephone=resultSet.getString("telephone").toString().trim();
					String fax=resultSet.getString("fax").toString().trim();
					String emergency_contact_no=resultSet.getString("emergency_contact_no").toString().trim();
					String email=resultSet.getString("email").toString().trim();
					String url=resultSet.getString("url").toString().trim();
					String logo=resultSet.getString("logo").toString().trim();
					String year_of_foundation=resultSet.getString("year_of_foundation").toString().trim();
					String state=resultSet.getString("state").toString().trim();
					
					ret.put("organization_id", organization_id);
					ret.put("organization_name", organization_name);
					ret.put("org_type", org_type);
					ret.put("address", address);
					ret.put("city", city);
					ret.put("country", country);
					ret.put("postal_code", postal_code);
					ret.put("telephone", telephone);
					ret.put("email", email);
					ret.put("url", url);
					ret.put("logo", logo);
					ret.put("state", state);
					ret.put("emergency_contact_no", emergency_contact_no);
					ret.put("fax", fax);
					ret.put("year_of_foundation", year_of_foundation);
					
				}
			      
				
			}catch(Exception ex){}
			finally
			   {
				   try{
					   con.close();
					   preparedStatement.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
			return ret;
		}
		
		public static String formatDate(String date, String initDateFormat, String endDateFormat) throws ParseException {

		    Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
		    SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
		    String parsedDate = formatter.format(initDate);

		   // System.out.println("input:"+date +"		intfor:"+initDateFormat +"		outfor:"+endDateFormat +"		output:"+parsedDate);
		    
		    return parsedDate;
		}
		
		public boolean validateDateRange(Date LoginDate,Date StartDate,Date EndDate)
		{
			boolean ret=false;
			try
			{
				ret=( (LoginDate.equals(StartDate) || LoginDate.after(StartDate)) && (LoginDate.equals(EndDate) || LoginDate.before(EndDate)) );
			}catch(Exception ex){ex.printStackTrace(); ret=false;}
			
			return ret;
		}
		
		public ArrayList<HashMap<String,String>> getSessionDetailsByOrgIdByrole(String user_id,String userName,String Org_id)
		{
			//System.out.println("user_id:"+user_id +"	userName:"+userName+"	Org_id:"+Org_id+"		privilege:"+privilege);
			
			ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
			try
			{
				con = DBTransaction.connect();
				
				ResultSet resultSet = null;
				String sql="select * from session_details where organization_id='"+Org_id+"' ";
				
				
				preparedStatement = con.prepareStatement(sql);

				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) 
				{
					String session_id=resultSet.getString("session_id").toString().trim();
					String session_name=resultSet.getString("session_name").toString().trim();
					
					HashMap<String,String> ClockInfo=new HashMap<String,String>();
					ClockInfo.put("session_id", session_id);
					ClockInfo.put("session_name", session_name);
					ret.add(ClockInfo);
						
				}
			      
				
			}catch(Exception ex){ex.printStackTrace();}
			finally
			   {
				   try{
					   con.close();
					   preparedStatement.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
			return ret;
		}

		public ArrayList<HashMap<String,String>> getAllFacultiesDetailsByOrgIdByrole(String Org_id)
		{
			//System.out.println("user_id:"+user_id +"	userName:"+userName+"	Org_id:"+Org_id+"		privilege:"+privilege);
			
			ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
			try
			{
				con = DBTransaction.connect();
				
				ResultSet resultSet = null;
				String sql="select * from users_info where organization_id='"+Org_id+"' and privilege='Faculty'";
				
				
				preparedStatement = con.prepareStatement(sql);

				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) 
				{
					String user_id=resultSet.getString("user_id").toString().trim();
					String username=resultSet.getString("username").toString().trim();
					
					HashMap<String,String> ClockInfo=new HashMap<String,String>();
					ClockInfo.put("user_id", user_id);
					ClockInfo.put("username", username);
					ret.add(ClockInfo);
						
				}
			      
				
			}catch(Exception ex){ex.printStackTrace();}
			finally
			   {
				   try{
					   con.close();
					   preparedStatement.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
			return ret;
		}
		
		public ArrayList<HashMap<String,String>> getAllStudentsDetailsByOrgIdByrole(String Org_id)
		{
			//System.out.println("user_id:"+user_id +"	userName:"+userName+"	Org_id:"+Org_id+"		privilege:"+privilege);
			
			ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
			try
			{
				con = DBTransaction.connect();
				
				ResultSet resultSet = null;
				String sql="select * from users_info where organization_id='"+Org_id+"' and privilege='Student'";
				
				
				preparedStatement = con.prepareStatement(sql);

				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) 
				{
					String user_id=resultSet.getString("user_id").toString().trim();
					String username=resultSet.getString("username").toString().trim();
					
					HashMap<String,String> ClockInfo=new HashMap<String,String>();
					ClockInfo.put("user_id", user_id);
					ClockInfo.put("username", username);
					ret.add(ClockInfo);
						
				}
			      
				
			}catch(Exception ex){ex.printStackTrace();}
			finally
			   {
				   try{
					   con.close();
					   preparedStatement.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
			return ret;
		}
	
	
		public ArrayList<HashMap<String,String>> getCourseDetailsByOrgId(String Org_id)
		{
			
			
			ArrayList<HashMap<String,String>> ret=new ArrayList<HashMap<String,String>>();
			try
			{
				con = DBTransaction.connectKnoWStore();
				
				ResultSet resultSet = null;
				String sql="select a.course_discription_id,a.course_id, a.course_id_declared_by_teaching_source, b.department_name as course_name from transaction_course_description_info a, transaction_department_info b, transaction_middle_layer_teaching_source c "
						+ "where c.organization_id = '"+Org_id+"' and c.middle_layer_teaching_source_id = a.middle_layer_teaching_source_id and a.course_id = b.department_id order by a.course_id_declared_by_teaching_source";
				
				System.out.println("getCourseDetailsByOrgId \n "+sql);
				preparedStatement = con.prepareStatement(sql);

				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) 
				{
					String TS_course_id=resultSet.getString("course_id_declared_by_teaching_source").toString().trim();
					String course_name=resultSet.getString("course_name").toString().trim();
					String course_id=resultSet.getString("course_id").toString().trim();
					String course_discription_id=resultSet.getString("course_discription_id").toString().trim();
					
					
					HashMap<String,String> ClockInfo=new HashMap<String,String>();
					ClockInfo.put("TS_course_id", TS_course_id);
					ClockInfo.put("course_name", course_name);
					ClockInfo.put("course_id", course_id);
					ClockInfo.put("course_discription_id", course_discription_id);
					
					ret.add(ClockInfo);
						
				}
			      
				
			}catch(Exception ex){ex.printStackTrace();}
			finally
			   {
				   try{
					   con.close();
					   preparedStatement.close();
				   }
				   catch(Exception e){
						e.printStackTrace();
			   }
			   }
			return ret;
		}

}

