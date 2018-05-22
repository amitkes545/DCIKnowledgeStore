package com.kes.kote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.kes.kote.dbconnection.DBTransaction;
import com.kes.kote.domain.StacsAssignmentTypesDomain;
import com.kes.kote.domain.StacsCOSMapDomain;
import com.kes.kote.domain.StacsCOSTSTMapDomain;
import com.kes.kote.domain.StacsCertConfigDomain;
import com.kes.kote.domain.StacsCourseCatelogDomain;
import com.kes.kote.domain.StacsCourseCreditDomain;
import com.kes.kote.domain.StacsSGPACGPADomain;
import com.kes.kote.domain.StacsSubjectsDomain;
import com.kes.kote.domain.StacsTimeTableDomain;
import com.kes.kote.domain.StacsTopicsSubTopicDomain;
import com.kes.kote.services.Mailer;
import com.kes.kote.util.CLModuleTaskControlUtil;
import com.kes.kote.util.PropertiesUtil;
import com.kes.kote.util.SessionUtil;

public class StacsDao {

	TaskControlDao taskDao=new TaskControlDao();
	ModuleControlDao moduleDao=new ModuleControlDao();
	CommonDao commmonDao=new CommonDao();
	
	
	public int saveCourseCatelog(List<StacsCourseCatelogDomain> CourseCatelogDetails,HttpSession sess) {
		int ret=0;
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			String orgid=util.getOrgID();
			
			if(CourseCatelogDetails!=null && CourseCatelogDetails.size()>0)
			{
				for(int i=0;i<CourseCatelogDetails.size();i++)
				{
					StacsCourseCatelogDomain row=CourseCatelogDetails.get(i);
				
					//Action 1
					String ProgID=checkAndGetProgramIDByName(row.getDisciplineID(), row.getProgramName(),sess);
		    		printStringtoConsole("got ProgID == "+ProgID,sess);
		    		if(ProgID.length()>0)
		    		{
		    			//Action 2
		    			String CourseID=checkAndGetCourseIDByName(ProgID, row.getCourseName(),row.getCourseImageName(),sess);
			    		printStringtoConsole("got CourseID == "+CourseID,sess);
			    		
			    		if(CourseID.length()>0)
			    		{
			    			//Action 3
			    			String StreamID=checkAndGetStreamIDByName(CourseID, row.getDepartmentName(),row.getDepartmentImageName(),sess);
				    		printStringtoConsole("got StreamID == "+StreamID,sess);
				    		
				    		if(StreamID.length()>0)
				    		{
				    			//Action 4
				    			String TopLayerID=checkAndGetTopLayerID(CourseID, StreamID, orgid,sess);
					    		printStringtoConsole("got TopLayerID == "+TopLayerID,sess);
					    		
					    		if(TopLayerID.length()>0)
					    		{
					    			//Action 5
					    			String MLTID=checkAndGetMiddleLayerID(TopLayerID, orgid,sess);
						    		printStringtoConsole("got MLTID == "+MLTID,sess);
						    		if(MLTID.length()>0)
						    		{
						    			HashMap<String,String> htinfo=new HashMap<String,String>();
							    		htinfo.put("MLTID", MLTID);
							    		htinfo.put("CrsDuration", ""+row.getDurationPeriod());
							    		htinfo.put("TeachingPattern", row.getTeachingPattern());
							    		htinfo.put("CIDTS", row.getDepartmentID());
							    		htinfo.put("CourseID", StreamID);
							    		
							    		//Action 6
							    		String CrsDescID=insertCourseDescription(htinfo,sess);
							    		printStringtoConsole("inserted CourseDescription ",sess);
							    		
							    		//Action 7
							    		ret=insertCourseCatalogue(row, ProgID, CourseID, StreamID, CrsDescID,sess);
							    		printStringtoConsole("inserted CourseCatalogue ",sess);
							    		
							    		//Action 8
							    		ret=insertDCICourseDesc(row.getDepartmentID(), row.getCourseName(), row.getDepartmentName(), row.getDurationPeriod(), row.getNoOfSessions(), orgid,sess);
							    		printStringtoConsole("inserted DCICourseDesc ",sess);
							    		
							    		//Action 9
							    		ret=insertMapADtoCourse(row,sess);
							    		printStringtoConsole("inserted MapADtoCourse ",sess);
						    		}
						    			
					    		}
					    			
				    		}
				    			
			    		}
			    			
		    		}
				}
			}
			if(ret==1)
				taskDao.saveTaskControl(CLModuleTaskControlUtil.STACSCACS,"Completed",sess);
			else
				taskDao.saveTaskControl(CLModuleTaskControlUtil.STACSCACS,"Failed",sess);
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();
			}catch(Exception ex){}
			
		}
		return ret;
	}
	
	
	public int saveSubjects(List<StacsSubjectsDomain> SubjectsDetails,HttpSession sess) {
		int ret=0;
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			if(SubjectsDetails!=null && SubjectsDetails.size()>0)
			{
				for(int i=0;i<SubjectsDetails.size();i++)
				{
					StacsSubjectsDomain row=SubjectsDetails.get(i);
					
					String subid=commmonDao.getSeqVal("nsubid", "subid",sess);
					
					conn=DBTransaction.openConnection();
					String sql="INSERT INTO mst_subjects(subject_id, subject_name, subjectid_by_ts, subject_status, organization_id, created_datetime, last_updated_datetime, mod_user_id)"
							+ " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
					
					st=conn.prepareStatement(sql);
					st.setString(1, subid);
					st.setString(2, row.getSubjectName());
					st.setString(3, row.getSubjectId());
					st.setString(4, "Active");
					st.setString(5, util.getOrgID());
					st.setTimestamp(6, commmonDao.getTimeStamp());
					st.setTimestamp(7, commmonDao.getTimeStamp());
					st.setString(8, util.getuserID());
					printStringtoConsole("saveSubjects sql: "+st.toString(),sess);
					ret=st.executeUpdate();
				}
				if(ret==1)
					taskDao.saveTaskControl(CLModuleTaskControlUtil.STACSCORS, "Completed",sess);
				else
					taskDao.saveTaskControl(CLModuleTaskControlUtil.STACSCORS, "Failed",sess);
			}
			
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();
			}catch(Exception ex){}
			
		}
		return ret;
	}

	public int saveTopicsSubTopic(List<StacsTopicsSubTopicDomain> TopicsSubTopicDetails,HttpSession sess) {
		int ret=0;
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			if(TopicsSubTopicDetails!=null && TopicsSubTopicDetails.size()>0)
			{
				HashMap<String,String> htTopics=new HashMap<String,String>(); 
				for(int i=0;i<TopicsSubTopicDetails.size();i++)
				{
					StacsTopicsSubTopicDomain row=TopicsSubTopicDetails.get(i);
					String subject_id="";
					String TopicID="";
					String sql="";
					if(!htTopics.containsKey(row.getTopicId()))
					{
						//Action 1
						sql="SELECT subject_id FROM mst_subjects where subjectid_by_ts=? and organization_id=?";
						
						st=conn.prepareStatement(sql);
						st.setString(1, row.getSubjectId());
						st.setString(2, util.getOrgID());
						printStringtoConsole("select subject_id sql: "+st.toString(),sess);
						result=st.executeQuery();
						while(result.next())
						{
							subject_id=result.getString("subject_id");
						}
						st.close();
						result.close();
						
						String topicID=commmonDao.getSeqVal("ntopicid", "topic",sess);
						
						sql="INSERT INTO mst_topics(topic_id, subject_id, topic_name, topicid_by_ts, topic_status, organization_id, created_datetime, last_updated_datetime, mod_user_id)"
								+ " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
						
						
						st=conn.prepareStatement(sql);
						st.setString(1, topicID);
						st.setString(2, subject_id);
						st.setString(3, row.getTopicName());
						st.setString(4, row.getTopicId());
						st.setString(5, "Active");
						st.setString(6, util.getOrgID());
						st.setTimestamp(7, commmonDao.getTimeStamp());
						st.setTimestamp(8, commmonDao.getTimeStamp());
						st.setString(9, util.getuserID());
						printStringtoConsole("insert into mst_topics "+st.toString(),sess);
						ret=st.executeUpdate();
						
						htTopics.put(row.getTopicId(), "Added");
						st.close();
					}
					
					sql="SELECT topic_id FROM mst_topics where topicid_by_ts=? and organization_id=?";
					st=conn.prepareStatement(sql);
					st.setString(1, row.getTopicId());
					st.setString(2, util.getOrgID());
					printStringtoConsole("sql "+st.toString(),sess);
					result=st.executeQuery();
					while(result.next())
					{
						TopicID=result.getString("topic_id").toString().trim();
					}
					st.close();
					result.close();
					
					if(TopicID.trim().length()>0)
					{
						String nsubtopicid=commmonDao.getSeqVal("nsubtopicid", "subtopic",sess);
						
						sql="INSERT INTO mst_subtopics(sub_topic_id, topic_id, sub_topic_name, subtopic_id_by_ts, sub_topic_status, organization_id, created_datetime, last_updated_datetime, mod_user_id)"
								+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
						st=conn.prepareStatement(sql);
						st.setString(1, nsubtopicid);
						st.setString(2, TopicID);
						st.setString(3, row.getSubTopicName());
						st.setString(4, row.getSubTopicId());
						st.setString(5, "Active");
						st.setString(6, util.getOrgID());
						st.setTimestamp(7, commmonDao.getTimeStamp());
						st.setTimestamp(8, commmonDao.getTimeStamp());
						st.setString(9, util.getuserID());
						printStringtoConsole("sql "+st.toString(),sess);
						ret=st.executeUpdate();
					}
					
					
					st.close();
				}
				
				if(ret==1)
					taskDao.saveTaskControl(CLModuleTaskControlUtil.STACSTOPS, "Completed",sess);
				else
					taskDao.saveTaskControl(CLModuleTaskControlUtil.STACSTOPS, "Failed",sess);
			}
			
			
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();
			}catch(Exception ex){}
			
		}
		return ret;
	}

	
	public int saveCOSMap(List<StacsCOSMapDomain> COSMapDetails,HttpSession sess) {
		int ret=0;
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			conn=DBTransaction.openConnection();
			
			if(COSMapDetails!=null && COSMapDetails.size()>0)
			{
				for(int i=0;i<COSMapDetails.size();i++)
				{
					StacsCOSMapDomain row=COSMapDetails.get(i);
					
					String subid=commmonDao.getSeqVal("subid", "subid",sess);
					
					String sql="INSERT INTO subject_description(organization_id, course_id_defined_by_teaching_source, subject_id, subname) "
							+ " VALUES ( ?, ?, ?, ?)";
					st=conn.prepareStatement(sql);
					st.setString(1, util.getOrgID());
					st.setString(2, row.getDepartmentID());
					st.setString(3, subid);
					st.setString(4, row.getSubjectName());
					printStringtoConsole("subject_description == "+st.toString(),sess);
					ret=st.executeUpdate();
					st.close();
					
					String cacs_id="";
					sql="SELECT cacs_id FROM mst_course_catalogue where deptid_by_ts=? and organization_id= ?";
					st=conn.prepareStatement(sql);
					st.setString(1, row.getDepartmentID());
					st.setString(2, util.getOrgID());
					printStringtoConsole("sql "+st.toString(),sess);
					result=st.executeQuery();
					while(result.next())
					{
						cacs_id=result.getString("cacs_id");
					}
					st.close();
					result.close();
					
					String subject_id="";
					sql="SELECT subject_id FROM mst_subjects where subjectid_by_ts= ? and organization_id= ?";
					st=conn.prepareStatement(sql);
					st.setString(1, row.getSubjectId());
					st.setString(2, util.getOrgID());
					printStringtoConsole("sql "+st.toString(),sess);
					result=st.executeQuery();
					while(result.next())
					{
						subject_id=result.getString("subject_id");
					}
					st.close();
					result.close();
					
					String sylbusid=commmonDao.getSeqVal("sylbusid", "sylbus",sess);
					
					sql="INSERT INTO mst_course_syllabus(syllabus_id, cacs_id, subject_id, semester_id, semester_start_date,"
							+ "semester_end_date, syllabus_status, organization_id, created_datetime, last_updated_datetime, mod_user_id)"
							+ "  VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					
					int sem=row.getSemesterID();//non - semester 
					if(sem==0)
						sem=1;
					
					st=conn.prepareStatement(sql);
					st.setString(1, sylbusid);
					st.setString(2, cacs_id);
					st.setString(3, subject_id);
					st.setInt(4, sem);
					st.setDate(5, commmonDao.getDatebySlash(row.getSemesterStartDate(),util));
					st.setDate(6, commmonDao.getDatebySlash(row.getSemesterEndDate(),util));
					st.setString(7, "Active");
					st.setString(8, util.getOrgID());
					st.setTimestamp(9, commmonDao.getTimeStamp());
					st.setTimestamp(10, commmonDao.getTimeStamp());
					st.setString(11, util.getuserID());
					printStringtoConsole("mst_course_syllabus == "+st.toString(),sess);
					ret=st.executeUpdate();
					st.close();
					
					sql="INSERT INTO subject_mst_subject_mapping( mst_subject_id, subject_id, organization_id, map_status, created_datetime, last_updated_datetime, mod_user_id)"
							+ " VALUES (?, ?, ?, ?, ?, ?, ?) ";
					st=conn.prepareStatement(sql);
					st.setString(1, subject_id);
					st.setString(2, subid);
					st.setString(3, util.getOrgID());
					st.setString(4, "Active");
					st.setTimestamp(5, commmonDao.getTimeStamp());
					st.setTimestamp(6, commmonDao.getTimeStamp());
					st.setString(7, util.getuserID());
					printStringtoConsole("subject_mst_subject_mapping == "+st.toString(),sess);
					st.executeUpdate();
					
				}
				
				if(ret==1)
					taskDao.saveTaskControl(CLModuleTaskControlUtil.STACSCOSMaP, "Completed",sess);
				else
					taskDao.saveTaskControl(CLModuleTaskControlUtil.STACSCOSMaP, "Failed",sess);
			
			}
				
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();
			}catch(Exception ex){}
			
		}
		return ret;
	}

	
	
	
	public int saveCOSTSTMap(List<StacsCOSTSTMapDomain> COSTSTMapDetails,HttpSession sess) {
		int ret=0;
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			if(COSTSTMapDetails!=null && COSTSTMapDetails.size()>0)
			{
				String subject_id="";
				String topicid="";
				
				HashMap<String,String> htmailstatus=new HashMap<String,String>(); 
				for(int i=0;i<COSTSTMapDetails.size();i++)
				{
					StacsCOSTSTMapDomain row=COSTSTMapDetails.get(i);
					
					HashMap<String,String> htTopic=new HashMap<String,String>();
					
					String sql="";	
					if(!htTopic.containsKey(row.getTopicName()))
					{
						sql="SELECT subject_id FROM subject_description where subname= ? and course_id_defined_by_teaching_source= ? and organization_id= ?";
						st=conn.prepareStatement(sql);
						st.setString(1, row.getSubjectName());
						st.setString(2, row.getDepartmentID());
						st.setString(3, util.getOrgID());
						printStringtoConsole("select subject_id sql: "+st.toString(),sess);
						result=st.executeQuery();
						while(result.next())
						{
							subject_id=result.getString("subject_id");
						}
						result.close();
						st.close();
							
						topicid=commmonDao.getSeqVal("topicid", "topicid",sess);
						
						sql="INSERT INTO topic_description(subject_id, topic_id, topicname) VALUES ( ?, ?, ?)";
						st=conn.prepareStatement(sql);
						st.setString(1, subject_id);
						st.setString(2, topicid);
						st.setString(3, row.getTopicName());
						printStringtoConsole(" INSERT INTO topic_description  :: "+st.toString(),sess);
						ret=st.executeUpdate();
					
						st.close();
						
						htTopic.put(row.getTopicName(), "Added");
					}
					
					
					String subtopicid=commmonDao.getSeqVal("subtopicid", "subtopicid",sess);
					sql="INSERT INTO sub_topic_description(topic_id, sub_topic_id, subtopicname) VALUES ( ?, ?, ?)";
					st=conn.prepareStatement(sql);
					st.setString(1, topicid);
					st.setString(2, subtopicid);
					st.setString(3, row.getSubTopicName());
					
					printStringtoConsole("insert into sub_topic_description :: "+st.toString(),sess);
					ret=st.executeUpdate();
					
					st.close();
					
					String admin_user_id="";
					sql="SELECT admin_user_id FROM superadmin_admin_mapping where organization_id=? and course_id_defined_by_teaching_source= ?";
					st=conn.prepareStatement(sql);
					st.setString(1, util.getOrgID());
					st.setString(2, row.getDepartmentID());
					
					printStringtoConsole("select admin_user_id :: "+st.toString(),sess);
					result=st.executeQuery();
					while(result.next())
					{
						admin_user_id=result.getString("admin_user_id");
					}
					result.close();
					st.close();
					
					sql="INSERT INTO admin_faculty_mapping(admin_user_id, faculty_user_id, faculty_subject_id, faculty_topic_id, faculty_sub_topic_id, course_id_defined_by_teaching_source, organization_id)"
							+ "    VALUES (?, ?, ?, ?, ?, ?, ?)";
					st=conn.prepareStatement(sql);
					st.setString(1, admin_user_id);
					st.setString(2, row.getFacultyUID());
					st.setString(3, subject_id);
					st.setString(4, topicid);
					st.setString(5, subtopicid);
					st.setString(6, row.getDepartmentID());
					st.setString(7, util.getOrgID());
					printStringtoConsole("INSERT INTO admin_faculty_mapping == "+st.toString(),sess);
					ret=st.executeUpdate();
					
					try
					{
						if(ret==1)
						{
							HashMap<String,String> adm=getNameAndEmailbyUID(admin_user_id,util.getOrgID());
							HashMap<String,String> fac=getNameAndEmailbyUID(row.getFacultyUID(),util.getOrgID());
							//send mail from here
							if(fac.get("email").trim().length()>0)
							{
								String subject=PropertiesUtil.getMessageProperty("coursemapping.mailsubject");
								String Msg=PropertiesUtil.getMessageProperty("coursemapping.mailbody");
								if(Msg.contains("<User1>"))
								{
									Msg=Msg.replace("<User1>", fac.get("username").toString());
								}
								if(Msg.contains("<User2>"))
								{
									Msg=Msg.replace("<User2>", adm.get("username").toString());
								}
								if(Msg.contains("<course>"))
								{
									Msg=Msg.replace("<course>", row.getDepartmentID());
								}
								if(Msg.contains("<OrgName>"))
								{
									Msg=Msg.replace("<OrgName>", util.getOrgName());
								}
								
								String Link=PropertiesUtil.getMessageProperty("coursemapping.link");
								String Footer=PropertiesUtil.getMessageProperty("coursemapping.mailfooter");
								
								Msg=Msg+"<br> <br>"
										+Link +"<br> <br>"
										+Footer;
								try
								{
									String key=fac.get("email").toString()+"_"+row.getDepartmentID();
									if(!htmailstatus.containsKey(key))
									{
										Mailer.send(fac.get("email").toString(), subject, Msg,sess);
										htmailstatus.put(key, "sent");
									}
								}catch(Exception ex){}
								
								
							}
						}
						
						
					}catch(Exception ex){}
					
					
					sql="update users_info set privilege=? where user_id=?";
					st=conn.prepareStatement(sql);
					st.setString(1, "Faculty");
					st.setString(2, row.getFacultyUID());
					printStringtoConsole("change privilege "+st.toString(),sess);
					st.executeUpdate();
					
				}
				
				if(ret==1)
					taskDao.saveTaskControl(CLModuleTaskControlUtil.STACSCosTST, "Completed",sess);
				else
					taskDao.saveTaskControl(CLModuleTaskControlUtil.STACSCosTST, "Failed",sess);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();
			}catch(Exception ex){}
			
		}
		return ret;
	}

	HashMap<String,String> getNameAndEmailbyUID(String UID,String OrgId)
	{
		HashMap<String,String> ret=new HashMap<String,String>();
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		
		try
		{
			conn=DBTransaction.openConnection();
			String sql="select username,email from users_info where user_id=? and organization_id=?";
			st=conn.prepareStatement(sql);
			st.setString(1, UID);
			st.setString(2, OrgId);
			result=st.executeQuery();
			while(result.next())
			{
				String username=result.getString("username");
				String email=result.getString("email");
				
				ret.put("username", username);
				ret.put("email", email);
			}
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();
			}catch(Exception ex){}
			
		}
		return ret;
	}
	
	public int saveTimeTable(List<StacsTimeTableDomain> TimeTableDetails,HttpSession sess) {
		int ret=0;
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			conn=DBTransaction.openConnection();
			if(TimeTableDetails!=null && TimeTableDetails.size()>0)
			{
				for(int i=0;i<TimeTableDetails.size();i++)
				{
					StacsTimeTableDomain row=TimeTableDetails.get(i);
			
					String sesnid=commmonDao.getSeqVal("sesnid", "sesnid", sess);
					
					String cacs_id="";
					String syllabus_id="";
					String sql="SELECT cacs_id FROM mst_course_catalogue where deptid_by_ts=? and organization_id= ?";
					
					st=conn.prepareStatement(sql);
					st.setString(1, row.getDepartmentID());
					st.setString(2, util.getOrgID());
					printStringtoConsole("get cacs_id sql: "+st.toString(),sess);
					result=st.executeQuery();
					while(result.next())
					{
						cacs_id=result.getString("cacs_id");
					}
					result.close();
					st.close();
					
					sql="SELECT syllabus_id FROM mst_course_syllabus where cacs_id=? and subject_id=? and organization_id=?";
					
					st=conn.prepareStatement(sql);
					st.setString(1, cacs_id);
					st.setString(2, row.getSubjectId());
					st.setString(3, util.getOrgID());
					printStringtoConsole("sql: "+st.toString(),sess);
					result=st.executeQuery();
					while(result.next())
					{
						syllabus_id=result.getString("syllabus_id");	
					}
					result.close();
					st.close();
					
					sql="INSERT INTO time_table( session_id, syllabus_id, session_name, topic_id,"
							+ " sub_topic_id, faculty_id, session_start_datetime, session_end_datetime,"
							+ " session_duration, file_name, file_size, file_path, session_status,"
							+ " organization_id, created_datetime, last_updated_datetime, mod_user_id)"
							+ "    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
					
								
					st=conn.prepareStatement(sql);
					st.setString(1, sesnid);
					st.setString(2, syllabus_id);
					st.setString(3, row.getSessionName());
					st.setString(4, row.getTopicId());
					st.setString(5, row.getSubTopicId());
					st.setString(6, row.getFacultyUID());
					st.setTimestamp(7, commmonDao.getSessiondate(row.getSessionStartDateTime(),util));
					st.setTimestamp(8, commmonDao.getSessiondate(row.getSessionEndDateTime(),util));
					st.setString(9, row.getSessionDuration());
					st.setString(10, row.getSessionMaterial());
					st.setInt(11, -1); 
					st.setString(12, null);
					st.setString(13, "ACTIVE");
					st.setString(14, util.getOrgID());
					st.setTimestamp(15, commmonDao.getTimeStamp());
					st.setTimestamp(16, commmonDao.getTimeStamp());
					st.setString(17, util.getuserID());
					util.print("insert session_details :: "+st.toString());
					ret=st.executeUpdate();
					st.close();
					
					String sesssqe=commmonDao.getSeqVal("course_details_course_id_seq", "SES", sess);
					sql="INSERT INTO session_details(session_id, session_name, category, duration, session_start_time,"
							+ " cost_of_session, faculty_name, organization_id, recipient_type,"
							+ " session_end_time, session_status, file_path, subject_id, topic_id, subtopic_id)"
							+ "  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					
					st=conn.prepareStatement(sql);
					st.setString(1, sesssqe);
					st.setString(2, row.getSessionName());
					st.setString(3, row.getDepartmentID());
					st.setString(4, row.getSessionDuration());
					st.setString(5, commmonDao.getDateForSessionTable(row.getSessionStartDateTime(),util));
					st.setString(6, null);
					st.setString(7, row.getFacultyUID());
					st.setString(8, util.getOrgID());
					st.setString(9, "all");
					st.setString(10, commmonDao.getDateForSessionTable(row.getSessionEndDateTime(),util));
					st.setString(11, "ACTIVE");
					st.setString(12, row.getSessionMaterial());
					st.setString(13, row.getSubjectId());
					st.setString(14, row.getTopicId());
					st.setString(15, row.getSubTopicId());
					util.print("insert session_details :: "+st.toString());
					ret=st.executeUpdate();
					
				}
				
				if(ret==1)
					taskDao.saveTaskControl(CLModuleTaskControlUtil.STACSTIMETABLE, "Completed",sess);
				else
					taskDao.saveTaskControl(CLModuleTaskControlUtil.STACSTIMETABLE, "Failed",sess);
				
			}
			
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();
			}catch(Exception ex){}
			
		}
		return ret;
		
	}
	
	public int saveSgpaCgpa(List<StacsSGPACGPADomain> gradeDetails,HttpSession sess) {
		int ret=0;
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			conn=DBTransaction.openConnection();
			if(gradeDetails!=null && gradeDetails.size()>0)
			{
				for(int i=0;i<gradeDetails.size();i++)
				{
					StacsSGPACGPADomain row=gradeDetails.get(i);
					
					String sql="INSERT INTO instn_letter_grade_points(organization_id, letter_grade, description, grade_points, min_range_percent, max_range_percent, created_datetime, last_updated_datetime, mod_user_id)"
							+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
					st=conn.prepareStatement(sql);
					st.setString(1, util.getOrgID());
					st.setString(2, row.getLetterGrade());
					st.setString(3, row.getDescription());
					st.setDouble(4, row.getGradePoints());
					st.setDouble(5, row.getMinRange());
					st.setDouble(6, row.getMaxRange());
					st.setTimestamp(7, commmonDao.getTimeStamp());
					st.setTimestamp(8, commmonDao.getTimeStamp());
					st.setString(9, util.getuserID());
					util.print("SGPA CGPA :: "+st.toString());
					ret=st.executeUpdate(); 
				}
				if(ret==1)
					taskDao.saveTaskControl(CLModuleTaskControlUtil.STACSSGPACGPA, "Completed",sess);
				else
					taskDao.saveTaskControl(CLModuleTaskControlUtil.STACSSGPACGPA, "Failed",sess);
			}
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();
			}catch(Exception ex){}
			
		}
		return ret;
	}
	public int saveCourseCredit(List<StacsCourseCreditDomain> CourseCreditDetails,HttpSession sess) {
		int ret=0;
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			String sql="";
			
			if(CourseCreditDetails!=null && CourseCreditDetails.size()>0)
			{
				for(int i=0;i<CourseCreditDetails.size();i++)
				{
					StacsCourseCreditDomain row=CourseCreditDetails.get(i);
					
					String cacs_id="";
					String subject_id="";
					String syllabus_id="";
					sql="SELECT cacs_id FROM mst_course_catalogue where deptid_by_ts=? and organization_id= ?";
					
					st=conn.prepareStatement(sql);
					st.setString(1, row.getDepartmentID());
					st.setString(2, util.getOrgID());
					printStringtoConsole("get cacs_id sql: "+st.toString(),sess);
					result=st.executeQuery();
					while(result.next())
					{
						cacs_id=result.getString("cacs_id");
					}
					result.close();
					st.close();
					
					sql="SELECT subject_id FROM mst_subjects where subjectid_by_ts=? and organization_id=?";
					st=conn.prepareStatement(sql);
					st.setString(1, row.getSubjectID());
					st.setString(2, util.getOrgID());
					printStringtoConsole("sql: "+st.toString(),sess);
					result=st.executeQuery();
					while(result.next())
					{
						subject_id=result.getString("subject_id");
					}
					result.close();
					st.close();
					
					sql="SELECT syllabus_id FROM mst_course_syllabus where cacs_id=? and subject_id=? and semester_id=? and organization_id=?";
					int sem=row.getSemester();//non - semester
					if(sem==0)
						sem=1;
					
					st=conn.prepareStatement(sql);
					st.setString(1, cacs_id);
					st.setString(2, subject_id);
					st.setInt(3, sem);
					st.setString(4, util.getOrgID());
					printStringtoConsole("sql: "+st.toString(),sess);
					result=st.executeQuery();
					while(result.next())
					{
						syllabus_id=result.getString("syllabus_id");	
					}
					result.close();
					st.close();
					
					String ccsid=commmonDao.getSeqVal("ccsid", "ccsid",sess);
					
					sql="INSERT INTO course_credit_system(ccs_id, syllabus_id, max_session_points, max_theory_points, max_practical_points, ccs_status, min_session_points, min_theory_points, min_practical_points, organization_id, created_datetime, last_updated_datetime, mod_user_id, credit_hours)"
							+ "  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					st=conn.prepareStatement(sql);
					st.setString(1, ccsid);
					st.setString(2, syllabus_id);
					st.setDouble(3, row.getSessionMax());
					st.setDouble(4, row.getTheoryMax());
					st.setDouble(5, row.getPracticalMax());
					st.setString(6, "Active");
					st.setDouble(7, row.getSessionMin());
					st.setDouble(8, row.getTheoryMin());
					st.setDouble(9, row.getPracticalMin());
					st.setString(10, util.getOrgID());
					st.setTimestamp(11, commmonDao.getTimeStamp());
					st.setTimestamp(12, commmonDao.getTimeStamp());
					st.setString(13, util.getuserID());
					st.setDouble(14, row.getCreditHours());
					printStringtoConsole("sql: "+st.toString(),sess);
					ret=st.executeUpdate();
				}
				
				if(ret==1)
					taskDao.saveTaskControl(CLModuleTaskControlUtil.STACSCORCR, "Completed",sess);
				else
					taskDao.saveTaskControl(CLModuleTaskControlUtil.STACSCORCR, "Failed",sess);
			}
			
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();
			}catch(Exception ex){}
			
		}
		return ret;
	}

	public int saveAssignTypes(List<StacsAssignmentTypesDomain> assignTypeDetail, HttpSession sess)
	{
		int ret=0;
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			conn=DBTransaction.openConnection();
			if(assignTypeDetail!=null && assignTypeDetail.size()>0)
			{
				for(int i=0;i<assignTypeDetail.size();i++)
				{
					StacsAssignmentTypesDomain row=assignTypeDetail.get(i);
					
					String sql="INSERT INTO instn_assessment_types(organization_id, type_id, type_description, created_datetime, last_updated_datetime, mod_user_id)"
							+ " VALUES (?, ?, ?, ?, ?, ?) ";
					st=conn.prepareStatement(sql);
					st.setString(1, util.getOrgID());
					st.setString(2, row.getTypeId());
					st.setString(3, row.getTypeDesc());
					st.setTimestamp(4, commmonDao.getTimeStamp());
					st.setTimestamp(5, commmonDao.getTimeStamp());
					st.setString(6, util.getuserID());
					printStringtoConsole("sql: "+st.toString(),sess);
					ret=st.executeUpdate();
				}
			}
			if(ret==1)
				taskDao.saveTaskControl(CLModuleTaskControlUtil.STACSAssignType, "Completed",sess);
			else
				taskDao.saveTaskControl(CLModuleTaskControlUtil.STACSAssignType, "Failed",sess);
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();
			}catch(Exception ex){}
			
		}
		return ret;
	}
	
	public int saveCertConfig(List<StacsCertConfigDomain> CertConfigDetails,HttpSession sess) {
		int ret=0;
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			if(CertConfigDetails!=null && CertConfigDetails.size()>0)
			{
				for(int i=0;i<CertConfigDetails.size();i++)
				{
					StacsCertConfigDomain row=CertConfigDetails.get(i);
					String middle_layer_teaching_source_id="";
					String sql="select middle_layer_teaching_source_id from transaction_course_description_info where course_id_declared_by_teaching_source=? and middle_layer_teaching_source_id in (select middle_layer_teaching_source_id from transaction_middle_layer_teaching_source where organization_id=? )";
					
					st=conn.prepareStatement(sql);
					st.setString(1, row.getCourseID());
					st.setString(2, util.getOrgID());
					printStringtoConsole("get middle_layer_teaching_source_id sql: "+st.toString(),sess);
					result=st.executeQuery();
					while(result.next())
					{
						middle_layer_teaching_source_id=result.getString("middle_layer_teaching_source_id");	
					}
					result.close();
					st.close();
					
					/*if(middle_layer_teaching_source_id.length()==0)
						middle_layer_teaching_source_id="mltsid31";*/
					// '/images/'+Col G
					sql="INSERT INTO certificate_configuration( middle_layer_teaching_source_id, course_id_declared_by_teaching_source,  greeting_message_of_certificate, body_message_of_certificate,  wishing_message_of_certificate, footer_message_of_certificate, authorised_signatury_name_on_certificate, digital_signature, certificate_size, certificate_layout, corner_image_path, watermark_image_path, created_datetime, last_updated_datetime, mod_user_id)"
							+ "  VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
					
					
					st=conn.prepareStatement(sql);
					st.setString(1, middle_layer_teaching_source_id);
					st.setString(2, row.getCourseID());
					st.setString(3, row.getGreetingText());
					st.setString(4, row.getBodyText());
					st.setString(5, row.getWishingText());
					st.setString(6, row.getFooterText());
					st.setString(7, row.getAuthorizedSign());
					st.setString(8, "/images/"+row.getDigitalSign());
					st.setString(9, row.getCertificateSize());
					st.setString(10, row.getCertificateLayout());
					st.setString(11, "/images/"+row.getCornerImage());
					st.setString(12, "/images/"+row.getWaterMarkImage());
					st.setTimestamp(13, commmonDao.getTimeStamp());
					st.setTimestamp(14, commmonDao.getTimeStamp());
					st.setString(15, util.getuserID());
					printStringtoConsole("INSERT INTO certificate_configuration == "+st.toString(),sess);
					ret=st.executeUpdate();
					
				}
				
				if(ret==1)
				{
					taskDao.saveTaskControl(CLModuleTaskControlUtil.STACSCCSE, "Completed",sess);
					moduleDao.saveModuleControl("STACS", "completed", sess);
					moduleDao.saveModuleControl("CINE", "completed", sess);
				}
				else
				{
					taskDao.saveTaskControl(CLModuleTaskControlUtil.STACSCCSE, "Failed",sess);
					moduleDao.saveModuleControl("STACS", "Failed", sess);
					moduleDao.saveModuleControl("CINE", "Failed", sess);
				}
			}
			
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();
			}catch(Exception ex){}
			
		}
		return ret;
	}

	
	
	public String checkAndGetProgramIDByName(String DiscID,String ProgramName,HttpSession sess)
	{
		String ret="";
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			String ProgImagePath="";
			
			boolean programstatus=false;
			conn=DBTransaction.openConnection();
			String sql="select program_id from transaction_program_info where discipline_id= ? and program_name= ?";
			
			st=conn.prepareStatement(sql);
			st.setString(1, DiscID);
			st.setString(2, ProgramName);
			
			result=st.executeQuery();
			printStringtoConsole("sql == "+st.toString(),sess);
			while(result.next())
			{
				programstatus=true;
				ret=result.getString("program_id");
			}
			st.close();
			result.close();
			
			if(!programstatus)
			{
				sql="select program_image_path from transaction_program_info where program_name= ?";
				
				st=conn.prepareStatement(sql);
				st.setString(1, ProgramName);
				printStringtoConsole("sql "+st.toString(),sess);
				result=st.executeQuery();
				while(result.next())
				{
					if(ProgImagePath.trim().length()==0)
						if(result.getObject("program_image_path")!=null)
							ProgImagePath=result.getString("program_image_path").toString().trim();
				}
				st.close();
				result.close();
				
				sql="INSERT INTO transaction_program_info(discipline_id, program_name, program_discription, program_image_path)"
						+ " VALUES (?, ?, ?, ?)";
				
				st=conn.prepareStatement(sql);
				st.setString(1, DiscID);
				st.setString(2, ProgramName);
				st.setString(3, ProgramName);
				st.setString(4, ProgImagePath);
				printStringtoConsole("sql "+st.toString(),sess);
				st.executeUpdate();
				
				st.close();
				result.close();
				
				sql="select program_id from transaction_program_info where discipline_id= ? and program_name= ?";
				st=conn.prepareStatement(sql);
				st.setString(1, DiscID);
				st.setString(2, ProgramName);
				printStringtoConsole("sql "+st.toString(),sess);
				result=st.executeQuery();
				while(result.next())
				{
					
					ret=result.getString("program_id");
				}
			}
		}catch(Exception ex){ex.printStackTrace();}
		finally
		{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();
			}catch(Exception ex){}
			
		}
		return ret;
	}
	
	public String checkAndGetCourseIDByName(String ProgID,String CourseName,String CrsIconByOrg,HttpSession sess)
	{
		String ret="";
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
						
			String CrsImagePath="/images/"+CrsIconByOrg.trim();
			
			boolean coursestatus=false;
			conn=DBTransaction.openConnection();
			String sql="select course_id from transaction_course_info where program_id=? and course_name=? ";
			
			st=conn.prepareStatement(sql);
			st.setString(1, ProgID);
			st.setString(2, CourseName);
			printStringtoConsole(st.toString(),sess);
			result=st.executeQuery();
			while(result.next())
			{
				coursestatus=true;
				ret=result.getString("course_id");
			}
			st.close();
			result.close();
			
			if(!coursestatus)
			{
				sql="select course_image_path from transaction_course_info where course_name= ?";
				
				st=conn.prepareStatement(sql);
				st.setString(1, CourseName);
				printStringtoConsole(st.toString(),sess);
				result=st.executeQuery();
				while(result.next())
				{
					if(CrsImagePath.trim().length()==0)
						if(result.getObject("course_image_path")!=null)
							CrsImagePath=result.getString("course_image_path").toString().trim();
				}
				st.close();
				result.close();
				
				sql="INSERT INTO transaction_course_info(program_id, course_name, course_discription, department_information_applicable, course_image_path)"
						+ " VALUES (?, ?, ?, ?, ?)";
				
				st=conn.prepareStatement(sql);
				st.setString(1, ProgID);
				st.setString(2, CourseName);
				st.setString(3, CourseName);
				st.setBoolean(4, true);
				st.setString(5, CrsImagePath);
				printStringtoConsole(st.toString(),sess);
				st.executeUpdate();
				
				st.close();
				result.close();
				
				sql="select course_id from transaction_course_info where program_id=? and course_name=?";
				
				st=conn.prepareStatement(sql);
				st.setString(1, ProgID);
				st.setString(2, CourseName);
				printStringtoConsole(st.toString(),sess);
				result=st.executeQuery();
				while(result.next())
				{
					
					ret=result.getString("course_id");
				}
			}
		}catch(Exception ex){ex.printStackTrace();}
		finally
		{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();
			}catch(Exception ex){}
			
		}
		return ret;
	}

	public String checkAndGetStreamIDByName(String CourseID,String StreamName,String StreamIconByOrg,HttpSession sess)
	{
		String ret="";
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			String StreamImagePath="/images/"+StreamIconByOrg.trim();
			
			
			boolean streamstatus=false;
			conn=DBTransaction.openConnection();
			String sql="select department_id from transaction_department_info where course_id=? and department_name=?";
			
			st=conn.prepareStatement(sql);
			st.setString(1, CourseID);
			st.setString(2, StreamName);
			printStringtoConsole(st.toString(),sess);
			result=st.executeQuery();
			while(result.next())
			{
				streamstatus=true;
				ret=result.getString("department_id");
			}
			st.close();
			result.close();
			
			if(!streamstatus)
			{
				sql="select department_image_path from transaction_department_info where department_name=?";
				
				st=conn.prepareStatement(sql);
				st.setString(1, StreamName);
				printStringtoConsole(st.toString(),sess);
				result=st.executeQuery();
				while(result.next())
				{
					if(StreamImagePath.trim().length()==0)
						if(result.getObject("department_image_path")!=null)
							StreamImagePath=result.getString("department_image_path").toString().trim();
				}
				st.close();
				result.close();
				
				sql="INSERT INTO transaction_department_info(course_id, department_name, department_name_discription, department_image_path)"
						+ " VALUES ( ?, ?, ?, ?)";
				
				st=conn.prepareStatement(sql);
				st.setString(1, CourseID);
				st.setString(2, StreamName);
				st.setString(3, StreamName);
				st.setString(4, StreamImagePath);
				printStringtoConsole(st.toString(),sess);
				st.executeUpdate();
				
				st.close();
				result.close();
				
				sql="select department_id from transaction_department_info where course_id=? and department_name=?";
				
				st=conn.prepareStatement(sql);
				st.setString(1, CourseID);
				st.setString(2, StreamName);
				printStringtoConsole(st.toString(),sess);
				result=st.executeQuery();
				while(result.next())
				{
					
					ret=result.getString("department_id");
				}
			}
		}catch(Exception ex){ex.printStackTrace();}
		finally
		{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();	
			}catch(Exception ex){}
			
		}
		return ret;
	}
	
	public String checkAndGetTopLayerID(String CourseID,String DeptID,String OrgID,HttpSession sess)
	{
		String ret="";
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			String TopLayerName="";
			String TopLayerImagePath="/images/university-icon.png";
			
			boolean toplayerstatus=false;
			conn=DBTransaction.openConnection();
			String sql="select org_type from org_details where organization_id=?";
			
			st=conn.prepareStatement(sql);
			st.setString(1, OrgID);
			printStringtoConsole(st.toString(),sess);
			result=st.executeQuery();
			while(result.next())
			{
				TopLayerName=result.getString("org_type");
			}
			st.close();
			result.close();
			
			sql="select top_layer_teaching_source_id from transaction_top_layer_teaching_source where top_layer_teaching_source_name=? and department_id=? and course_id=?";
			
			st=conn.prepareStatement(sql);
			st.setString(1, TopLayerName);
			st.setString(2, DeptID);
			st.setString(3, CourseID);
			printStringtoConsole(st.toString(),sess);
			result=st.executeQuery();
			while(result.next())
			{
				toplayerstatus=true;
				ret=result.getString("top_layer_teaching_source_id");
			}
			st.close();
			result.close();
			
			if(!toplayerstatus)
			{
				sql="select top_layer_teaching_source_image_path from transaction_top_layer_teaching_source where top_layer_teaching_source_name=?";
				
				st=conn.prepareStatement(sql);
				st.setString(1, TopLayerName);
				printStringtoConsole(st.toString(),sess);
				result=st.executeQuery();
				while(result.next())
				{
					if(TopLayerImagePath.trim().length()==0)
						if(result.getObject("top_layer_teaching_source_image_path")!=null)
							TopLayerImagePath=result.getString("top_layer_teaching_source_image_path").toString().trim();
				}
				st.close();
				result.close();
				
				sql="INSERT INTO transaction_top_layer_teaching_source(course_id, department_id, top_layer_teaching_source_name, top_layer_teaching_source_discription, top_layer_teaching_source_image_path)"
						+ "    VALUES (?, ?, ?, ?, ? ) "; 
				
				st=conn.prepareStatement(sql);
				st.setString(1, CourseID);
				st.setString(2, DeptID);
				st.setString(3, TopLayerName);
				st.setString(4, TopLayerName);
				st.setString(5, TopLayerImagePath);
				printStringtoConsole("transaction_top_layer_teaching_source == "+sql,sess);
				st.executeUpdate();
				
				st.close();
				result.close();
				
				sql="select top_layer_teaching_source_id from transaction_top_layer_teaching_source where top_layer_teaching_source_name=?";
				
				st=conn.prepareStatement(sql);
				st.setString(1, TopLayerName);
				printStringtoConsole(st.toString(),sess);
				result=st.executeQuery();
				while(result.next())
				{
					
					ret=result.getString("top_layer_teaching_source_id");
				}
			} 
		}catch(Exception ex){ex.printStackTrace();}
		finally
		{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();	
			}catch(Exception ex){}
			
		}
		return ret;
	}

	public String checkAndGetMiddleLayerID(String TopLayerID,String OrgID,HttpSession sess)
	{
		String ret="";
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			String MiddleLayerName="";
			String MiddleLayerImagePath="";
			
			boolean middlelayerstatus=false;
			conn=DBTransaction.openConnection();
			String sql="select organization_name,logo from org_details where organization_id=?";
			
			st=conn.prepareStatement(sql);
			st.setString(1, OrgID);
			printStringtoConsole(st.toString(),sess);
			result=st.executeQuery();
			while(result.next())
			{
				MiddleLayerName=result.getString("organization_name");
				if(result.getObject("logo")!=null)
					if(result.getString("logo").length()>0)
						MiddleLayerImagePath=result.getString("logo");
			}
			st.close();
			result.close();
			
			if(!middlelayerstatus)
			{
				/*sql="select middle_layer_teaching_source_image_path from transaction_middle_layer_teaching_source where middle_layer_teaching_source_name='"+MiddleLayerName+"'";
				printStringtoConsole(st.toString());
				st=conn.prepareStatement(sql);
				result=st.executeQuery();
				while(result.next())
				{
					MiddleLayerImagePath=result.getString("middle_layer_teaching_source_image_path").toString().trim();
				}
				st.close();
				result.close();
				*/
				if(MiddleLayerImagePath!=null && MiddleLayerImagePath.trim().length()>0)
					MiddleLayerImagePath="/images/"+MiddleLayerImagePath;
				else
					MiddleLayerImagePath="/images/university-icon.png";
				
				sql="INSERT INTO transaction_middle_layer_teaching_source(top_layer_teaching_source_id, middle_layer_teaching_source_name, middle_layer_teaching_source_discription, bottom_layer_applicable, middle_layer_teaching_source_image_path, organization_id)"
						+ "  VALUES (?, ?, ?, ?, ?, ?) "; 
				
				st=conn.prepareStatement(sql);
				st.setString(1, TopLayerID);
				st.setString(2, MiddleLayerName);
				st.setString(3, MiddleLayerName);
				st.setBoolean(4, false);
				st.setString(5, MiddleLayerImagePath);
				st.setString(6, OrgID);
				
				printStringtoConsole(st.toString(),sess);
				st.executeUpdate();
				
				st.close();
				result.close();
				
				sql="select middle_layer_teaching_source_id from transaction_middle_layer_teaching_source where middle_layer_teaching_source_name=? and organization_id=?";
				st=conn.prepareStatement(sql);
				st.setString(1, MiddleLayerName);
				st.setString(2, OrgID);
				printStringtoConsole(st.toString(),sess);
				
				result=st.executeQuery();
				while(result.next())
				{
					
					ret=result.getString("middle_layer_teaching_source_id");
				}
			}
		}catch(Exception ex){ex.printStackTrace();}
		finally
		{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();
			}catch(Exception ex){}
			
		}
		return ret;
	}
	
	public String insertCourseDescription(HashMap<String,String> htinfo,HttpSession sess)
	{
		String ret="";
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		
		try
		{
			String MLTID=htinfo.get("MLTID");
			String CrsDuration=htinfo.get("CrsDuration");
			String TeachingPattern=htinfo.get("TeachingPattern");
			String CIDTS=htinfo.get("CIDTS");
			String CourseID=htinfo.get("CourseID"); // deptID
			String[] SylImages=buildsyllabusArray(CrsDuration,TeachingPattern);
			
			//printStringtoConsole(" testststs "+ getSqlArray(SylImages),sess) ;
			
			conn=DBTransaction.openConnection();
			String sql="INSERT INTO transaction_course_description_info(middle_layer_teaching_source_id, bottom_layer_teaching_source_id, course_duration_in_month, teaching_pattern, course_id_declared_by_teaching_source, course_id, image_path)"
					+ " VALUES ('"+MLTID+"', null, '"+CrsDuration+"', '"+TeachingPattern+"', '"+CIDTS+"', '"+CourseID+"', '"+getSqlArray(SylImages)+"')";
			printStringtoConsole(sql.toString(),sess);
			st=conn.prepareStatement(sql);  
			/*st.setString(1, MLTID); 
			st.setString(2, BLTID);
			st.setInt(3, Integer.parseInt(CrsDuration));
			st.setString(4, TeachingPattern);
			st.setString(5, CIDTS);
			st.setString(6, CourseID);
			final java.sql.Array sqlArray = conn.createArrayOf("character", SylImages); 
			st.setArray(7, sqlArray);
			*/
			st.executeUpdate();
			st.close();
			
			
			sql="select course_discription_id from transaction_course_description_info where middle_layer_teaching_source_id=? and course_id=?";
			
			st=conn.prepareStatement(sql);
			st.setString(1, MLTID);
			st.setString(2, CourseID);
			printStringtoConsole(st.toString(),sess);
			result=st.executeQuery();
			while(result.next())
			{
				ret=result.getString("course_discription_id");
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally
		{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();	
			}catch(Exception ex){}
		}
		
		return ret;
	}
	
	int insertCourseCatalogue(StacsCourseCatelogDomain row,String ProgramId,String CourseId,String DeptId,String CrsDescID,HttpSession sess)
	{
		int ret=0;
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		
		
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			
			String cacsid=commmonDao.getSeqVal("cacsid","cacs",sess);
			String sql="INSERT INTO mst_course_catalogue(cacs_id, tlt_type, discipline_id, program_id, course_id, dept_id, deptid_by_ts, courseid_by_ts,"
					+ " course_description_id, parallel_attempt, duration_type, duration_period, teaching_pattern, no_of_sessions, cacs_status,"
					+ " organization_id, created_datetime, last_updated_datetime, mod_user_id) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			st=conn.prepareStatement(sql);
			st.setString(1, cacsid);
			st.setString(2,util.getTeachingSource());
			st.setString(3, row.getDisciplineID());
			st.setString(4, ProgramId);
			st.setString(5, CourseId);
			st.setString(6, DeptId);
			st.setString(7, row.getDepartmentID());
			st.setString(8, row.getCourseID());
			st.setString(9, CrsDescID);
			st.setString(10, row.getCanBeDoneinParallel());
			st.setString(11, row.getDurationType());
			st.setInt(12, row.getDurationPeriod());
			st.setString(13, row.getTeachingPattern());
			st.setInt(14, row.getNoOfSessions());
			st.setString(15, "Active"); 
			st.setString(16, util.getOrgID());
			st.setTimestamp(17, commmonDao.getTimeStamp());
			st.setTimestamp(18, commmonDao.getTimeStamp());
			st.setString(19, util.getuserID());
			printStringtoConsole(st.toString(),sess);
			
			ret=st.executeUpdate();
		}catch(Exception ex){ex.printStackTrace();}
		finally
		{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();	
			}catch(Exception ex){}
		}
		return ret;
	}
	public int insertDCICourseDesc(String StreamID,String CourseName,String StreamName,int Duration,int NoOfSession,String OrgID,HttpSession sess)
	{
		int ret=0;
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		
		try
		{
			conn=DBTransaction.openConnection();
			String sql="INSERT INTO course_description(course_id_defined_by_teaching_source, course_name, stream, duration_in_month,"
					+ " number_of_session, customer_id) VALUES (?, ?, ?, ?, ?, ? )";
			
			st=conn.prepareStatement(sql);
			st.setString(1, StreamID);
			st.setString(2, CourseName);
			st.setString(3, StreamName);
			st.setInt(4, Duration);
			st.setInt(5, NoOfSession);
			st.setString(6, OrgID);
			printStringtoConsole(st.toString(),sess);
			ret=st.executeUpdate();
			
		}catch(Exception ex){ex.printStackTrace();}
		finally
		{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();	
			}catch(Exception ex){}
		}
		return ret;
	}
	
	public int insertMapADtoCourse(StacsCourseCatelogDomain row,HttpSession sess)
	{
		int ret=0;
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet result=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			String SAUID="";
			conn=DBTransaction.openConnection();
			String sql="SELECT user_id from mst_user_role_map a, mst_roles b where a.organization_id=? and b.role_id=a.role_id and b.role_type=?";
			
			st=conn.prepareStatement(sql);
			st.setString(1, util.getOrgID());
			st.setString(2, "Super Admin");
			printStringtoConsole(st.toString(),sess);
			result=st.executeQuery();
			while(result.next())
			{
				SAUID=result.getString("user_id").toString().trim();
			}
			
			st.close();
			result.close();
						
			sql="INSERT INTO superadmin_admin_mapping(organization_id, super_admin_user_id, admin_user_id, course_id_defined_by_teaching_source, secondary_admin_id)"
					+ "    VALUES ( ?, ?, ?, ?, ?)";
			
			st=conn.prepareStatement(sql);
			st.setString(1, util.getOrgID());
			st.setString(2, SAUID);
			st.setString(3, row.getPAdminUID());
			st.setString(4, row.getDepartmentID());
			st.setString(5, row.getSAdminUID());
			printStringtoConsole(st.toString(),sess);
			ret=st.executeUpdate();
			st.close();
			
			sql="update users_info set privilege=? where user_id=?";
			st=conn.prepareStatement(sql);
			st.setString(1, "Admin");
			st.setString(2, row.getPAdminUID());
			printStringtoConsole("change privilege "+st.toString(),sess);
			st.executeUpdate();
			
			sql="update users_info set privilege=? where user_id=?";
			st=conn.prepareStatement(sql);
			st.setString(1, "Admin");
			st.setString(2, row.getSAdminUID());
			printStringtoConsole("change privilege "+st.toString(),sess);
			st.executeUpdate();
			
			try
			{
				if(ret==1)
				{
					HashMap<String,String> SAdm=getNameAndEmailbyUID(SAUID,util.getOrgID());
					HashMap<String,String> Adm=getNameAndEmailbyUID(row.getPAdminUID(),util.getOrgID());
					//send mail from here
					if(Adm.get("email").trim().length()>0)
					{
						String subject=PropertiesUtil.getMessageProperty("coursemapping.mailsubject");
						String Msg=PropertiesUtil.getMessageProperty("coursemapping.mailbody");
						if(Msg.contains("<User1>"))
						{
							Msg=Msg.replace("<User1>", Adm.get("username").toString());
						}
						if(Msg.contains("<User2>"))
						{
							Msg=Msg.replace("<User2>", SAdm.get("username").toString());
						}
						if(Msg.contains("<course>"))
						{
							Msg=Msg.replace("<course>", row.getDepartmentID());
						}
						if(Msg.contains("<OrgName>"))
						{
							Msg=Msg.replace("<OrgName>", util.getOrgName());
						}
						
						String Link=PropertiesUtil.getMessageProperty("coursemapping.link");
						String Footer=PropertiesUtil.getMessageProperty("coursemapping.mailfooter");
						
						Msg=Msg+"<br> <br>"
								+Link +"<br> <br>"
								+Footer;
						try
						{
							Mailer.send(Adm.get("email").toString(), subject, Msg,sess); 
						}catch(Exception ex){}
						
						
					}
				}
				
				
			}catch(Exception ex){}
			
			
		}catch(Exception ex){ex.printStackTrace();}
		finally
		{
			try
			{
				if(conn!=null) conn.close();
				if(st!=null) st.close();
				if(result!=null) result.close();	
			}catch(Exception ex){}
		}
		return ret;
	}
	
	String getSqlArray(String[] arr)
	{
		String ret = "{";
		String single="/images/syllabus/Blank_Syllabus.png";
		if(arr.length>0)
		{
			
			
			for(int i=0;i<arr.length;i++)
			{
				
				if(i==0)
					ret=ret+single;
				else
					ret=ret+","+single;	
			}  
			ret=ret+"}";
			//printStringtoConsole(""+ret);
		}
		return ret;
	}
	String[] buildsyllabusArray(String CrsDuration,String TeachingPattrn)
	{
		 String[] arr=new String[1];
		 String SinglePath="/images/syllabus/Blank_Syllabus.png";
		 arr[0]=SinglePath;
		try
		{
			int CD=Integer.parseInt(CrsDuration);
			int TP=1;
			if(TeachingPattrn.trim().equalsIgnoreCase("nonsemester"))
			{
				arr=new String[1];
				arr[0]=SinglePath;
			}else if(TeachingPattrn.trim().equalsIgnoreCase("semester"))
			{
				TP=CD/6;
			}else if(TeachingPattrn.trim().equalsIgnoreCase("yearly"))
			{
				TP=CD/12;
			}else if(TeachingPattrn.trim().equalsIgnoreCase("trimester"))
			{
				TP=CD/3;
			}
			
			if(TP>0)
			{
				arr=new String[TP];
				for(int i=0;i<TP;i++)
				{
					arr[i]=SinglePath;
				}	
			}
			
		}catch(Exception ex){ return arr;}
		
		return arr;
	}

	void printStringtoConsole(String text,HttpSession sess)
	{
		SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
		
		util.print(text);
		//LOGGER.info(text);
	}	
}
