package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.RetriveImagesDomain;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

public class DisplayStudentsDao { 
	Connection connection=null;
	PreparedStatement prepareStatement = null;
	ResultSet resultSet = null;
	 public ArrayList<RetriveImagesDomain> selectIndividualStudents(UsersInfoDomain uid){
			//DBTransaction1 dbt = new DBTransaction1();
			 ArrayList<RetriveImagesDomain> arrayList=new  ArrayList<RetriveImagesDomain>();
			try {
				connection = DBTransaction.connect();
				String quary = "select username,user_id from users_info where created_by='"+uid.getCreatedBy()+"' and organization_id='"+uid.getOrgId()+"'";
				//System.out.println(quary);
				prepareStatement = connection.prepareStatement(quary);
				resultSet = prepareStatement.executeQuery();
				while(resultSet.next())
				{
					RetriveImagesDomain retriveImageDomain=new RetriveImagesDomain();
					retriveImageDomain.setCourseName(resultSet.getString("username"));
					retriveImageDomain.setCourseImageLocation(resultSet.getString("user_id"));
					arrayList.add(retriveImageDomain);
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
		return arrayList;
		   
	   }
	 public ArrayList<RetriveImagesDomain> selectStream(){
			//DBTransaction1 dbt = new DBTransaction1();
			 ArrayList<RetriveImagesDomain> arrayList=new  ArrayList<RetriveImagesDomain>();
			try {
				connection = DBTransaction.connect();
				String quary = "select * from stream_master";
				//System.out.println(quary);
				prepareStatement = connection.prepareStatement(quary);
				resultSet = prepareStatement.executeQuery();
				while(resultSet.next())
				{
					RetriveImagesDomain retriveImageDomain=new RetriveImagesDomain();
					retriveImageDomain.setStreamId(resultSet.getString("stream_id"));
					retriveImageDomain.setStreamName(resultSet.getString("stream_name"));
					retriveImageDomain.setImagePath(resultSet.getString("image_path"));
					arrayList.add(retriveImageDomain);
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
		return arrayList;
		   
	   }
	}
