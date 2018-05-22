package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;
import com.kds.KODE_DEV.domain.ParticipantSessionDomain;
import com.kds.KODE_DEV.domain.SessionDomain;

public class ParticipantSessionDetailsDao {
	Connection connection = null;
	PreparedStatement prepareStatement = null;
	static final Logger LOGGER = Logger.getLogger(ParticipantSessionDetailsDao.class);
	 public ArrayList<ParticipantSessionDomain> selectCourses(DisplayCoursesDomain ri){
			//DBTransaction1 dbt = new DBTransaction1();
			 ArrayList<ParticipantSessionDomain> arl=new  ArrayList<ParticipantSessionDomain>();
			try {
				connection = DBTransaction.connect();
				//PreparedStatement prepareStatement = null;
				ResultSet resultSet = null;
				String quary = "SELECT distinct * FROM session_details where organization_id='"+ri.getCourseFee()+"' and faculty_name='"+ri.getCourseDetails()+"' and session_id='"+ri.getCourseName()+"'and (recipient_type like '%"+ri.getImagePath()+"%' or recipient_type='All')";
				LOGGER.info(quary);
				prepareStatement = connection.prepareStatement(quary);
				resultSet = prepareStatement.executeQuery();
				while(resultSet.next()){
					ParticipantSessionDomain dcd=new ParticipantSessionDomain();
					dcd.setCostOfSession(resultSet.getString("cost_of_session"));
					dcd.setDuration(resultSet.getString("duration"));
					dcd.setFacultyName(resultSet.getString("faculty_name"));
					dcd.setOrganizationId(resultSet.getString("organization_id"));
					dcd.setSessionId(resultSet.getString("session_id"));
					dcd.setSessionName(resultSet.getString("session_name"));
					dcd.setSessionStartTiming(resultSet.getString("session_start_time"));
					dcd.setSessionEndTime(resultSet.getString("session_end_time"));
					dcd.setPathOfFile(resultSet.getString("file_path"));
					arl.add(dcd);
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
		return arl;
		   
	   }
	 public ArrayList<SessionDomain> SessionDetails(String sessionId){
			//DBTransaction1 dbt = new DBTransaction1();
			 ArrayList<SessionDomain> arl=new  ArrayList<SessionDomain>();
			try {
				connection = DBTransaction.connect();
				//PreparedStatement prepareStatement = null;
				ResultSet resultSet = null;
				String quary = "SELECT distinct * FROM session_details where session_id='"+sessionId+"'";
				LOGGER.info(quary);
				prepareStatement = connection.prepareStatement(quary);
				resultSet = prepareStatement.executeQuery();
				while(resultSet.next()){
					SessionDomain domain=new SessionDomain();
					domain.setSessionId(resultSet.getString("session_id"));
					domain.setCostOfSession(resultSet.getString("cost_of_session"));
					domain.setDuration(resultSet.getString("duration"));
					domain.setFacultyId(resultSet.getString("faculty_name"));
					domain.setOrgId(resultSet.getString("organization_id"));
					
					domain.setSessionName(resultSet.getString("session_name"));
					domain.setSessionStartTime(resultSet.getString("session_start_time"));
					domain.setSessionEndTime(resultSet.getString("session_end_time"));
					domain.setPathOfFile(resultSet.getString("file_path"));
					arl.add(domain);
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
		return arl;
		   
	   }
	}	

