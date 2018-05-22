package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminManageKnowStoreDomain;

public class AdminUpdateManageKStoreDao {
	
	public String updateValues(AdminManageKnowStoreDomain aksDomain) {
	
	String status=null;
	PreparedStatement ps = null;
	Connection con =null;

	try {
                con = DBTransaction.connect();
				String sql = "update knowledgestore_info set " +"ksid='"+aksDomain.getKsId()+"'"+","+"organization_id='"+aksDomain.getOrgId()+"'"+","+"user_id='"+aksDomain.getUserId()+"'"+","+"knowledge_store_space='"+aksDomain.getKsSize()+"'"+","+"status='"
				+aksDomain.getStatus()+"'" +"where ksid='"+aksDomain.getKsId()+"'";
				ps = con.prepareStatement(sql);
				//System.out.println("the query is:"+sql);
				int i = ps.executeUpdate();
				//System.out.println(i+"row is updated successfully");
				if (i==1)
					status = "success";
				else
					status = "failure";
			}catch(Exception e){
				e.printStackTrace();
			}
	finally
	   {
		   try{
		   con.close();
		   ps.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
			return status;
		}
	}