package com.kes.kote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.kes.kote.dbconnection.DBTransaction;
import com.kes.kote.domain.CLTaskControlDomain;
import com.kes.kote.util.SessionUtil;

public class TaskControlDao {

	CommonDao commondao=new CommonDao();
	
	public void createTaskControl(String OrgID,HttpSession sess)
	{
		Connection conn=null;
		PreparedStatement st=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			String sql="SELECT task_id, task_desc, task_sequence, cfl_module_id, task_status FROM mst_configlayer_task_control where organization_id=? order by task_id";
			st=conn.prepareStatement(sql);
			st.setString(1, "OwnKesInd");
			util.print("get tasklist : "+st.toString());
			ResultSet rs=st.executeQuery();
			List<CLTaskControlDomain> template=new ArrayList<CLTaskControlDomain>();
			
			while(rs.next())
			{
				CLTaskControlDomain domain=new CLTaskControlDomain();
				domain.setTaskID(rs.getString("task_id").toString().trim());
				domain.setTaskDesc(rs.getString("task_desc").toString().trim());
				domain.setTaskSeq(rs.getString("task_sequence").toString().trim());
				domain.setModuleID(rs.getString("cfl_module_id").toString().trim());
				domain.setTaskStatus(rs.getString("task_status").toString().trim());
				
				template.add(domain);
			}
			rs.close();
			
			if(template!=null && template.size()>0)
			{
				for(int i=0;i<template.size();i++)
				{
					CLTaskControlDomain domain=template.get(i);
										
					sql="INSERT INTO mst_configlayer_task_control(task_id, task_desc, task_sequence, cfl_module_id, task_status, organization_id, created_datetime, last_updated_datetime, mod_user_id)"
							+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
					st=conn.prepareStatement(sql);
					st.setString(1, domain.getTaskID());
					st.setString(2, domain.getTaskDesc());
					st.setInt(3, Integer.parseInt(domain.getTaskSeq()));
					st.setString(4, domain.getModuleID());
					st.setString(5, domain.getTaskStatus());
					st.setString(6, OrgID);
					st.setTimestamp(7, commondao.getTimeStamp());
					st.setTimestamp(8, commondao.getTimeStamp());
					st.setString(9, util.getuserID());
					util.print("createTaskControl == "+st.toString());
					st.executeUpdate();
							
				}
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try {
					conn.close();
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	
	public void saveTaskControl(String TaskID,String Status,HttpSession sess)
	{
		Connection conn=null;
		PreparedStatement st=null;
		
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			
			String OrgID=util.getOrgID();
			String sql="UPDATE mst_configlayer_task_control SET task_status=?, last_updated_datetime=? WHERE task_id= ? and organization_id= ? ";
			
			st=conn.prepareStatement(sql);
			st.setString(1, Status);
			st.setTimestamp(2, commondao.getTimeStamp());
			st.setString(3, TaskID);
			st.setString(4, OrgID);
			util.print("saveTaskControl query : "+st.toString());
			st.executeUpdate();
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			try {
					conn.close();
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	
}
