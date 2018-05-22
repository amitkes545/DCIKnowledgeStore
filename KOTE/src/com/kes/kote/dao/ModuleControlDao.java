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
import com.kes.kote.domain.CLMduleControlDomain;
import com.kes.kote.util.SessionUtil;

public class ModuleControlDao {

	CommonDao commondao=new CommonDao();
	
	public void createModuleControl(String OrgID,HttpSession sess)
	{
		Connection conn=null;
		PreparedStatement st=null;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			String sql="SELECT cfl_module_id, module_sequence, module_status FROM mst_configlayer_module_control where organization_id=?";
			st=conn.prepareStatement(sql);
			st.setString(1, "OwnKesInd");
			util.print("sql :: "+st.toString());
			
			ResultSet rs=st.executeQuery();
			List<CLMduleControlDomain> template=new ArrayList<CLMduleControlDomain>();
			
			while(rs.next())
			{
				CLMduleControlDomain domain=new CLMduleControlDomain();
				
				domain.setModuleID(rs.getString("cfl_module_id").toString().trim());
				domain.setModuleSeq(rs.getString("module_sequence").toString().trim());
				domain.setModuleStatus(rs.getString("module_status").toString().trim());
				
				template.add(domain);
			}
			rs.close();
			
			if(template!=null && template.size()>0)
			{
				for(int i=0;i<template.size();i++)
				{
					CLMduleControlDomain domain=template.get(i);
										
					sql="INSERT INTO mst_configlayer_module_control(cfl_module_id, module_sequence, module_status, organization_id, created_datetime, last_updated_datetime, mod_user_id)"
							+ " VALUES (?, ?, ?, ?, ?, ?, ?) ";
					st=conn.prepareStatement(sql);
					st.setString(1, domain.getModuleID());
					st.setInt(2, Integer.parseInt(domain.getModuleSeq()));
					st.setString(3, domain.getModuleStatus());
					st.setString(4, OrgID);
					st.setTimestamp(5, commondao.getTimeStamp());
					st.setTimestamp(6, commondao.getTimeStamp());
					st.setString(7, util.getuserID());
					util.print("createModuleControl == "+st.toString());
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
	
	
	public void saveModuleControl(String ModuleID,String Status,HttpSession sess)
	{
		Connection conn=null;
		PreparedStatement st=null;
		
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			conn=DBTransaction.openConnection();
			
			String OrgID=util.getOrgID();
			String sql="UPDATE mst_configlayer_module_control SET module_status=?, last_updated_datetime=? WHERE cfl_module_id= ? and organization_id= ? ";
			
			st=conn.prepareStatement(sql);
			st.setString(1, Status);
			st.setTimestamp(2, commondao.getTimeStamp());
			st.setString(3, ModuleID);
			st.setString(4, OrgID);
			util.print("sql :: "+st.toString());
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
