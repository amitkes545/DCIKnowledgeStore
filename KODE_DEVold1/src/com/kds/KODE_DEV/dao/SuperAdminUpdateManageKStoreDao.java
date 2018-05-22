package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.SuperAdminManageKnowStoreDomain;

public class SuperAdminUpdateManageKStoreDao {
	
	public String updateValues(SuperAdminManageKnowStoreDomain aksDomain) {
	
	String status=null;

	try {
                Connection con = DBTransaction.connect();
				String sql = "update knowledgestore_info set " +"ksid='"+aksDomain.getKsId()+"'"+","+"organization_id='"+aksDomain.getOrgId()+"'"+","+"user_id='"+aksDomain.getUserId()+"'"+","+"knowledge_store_space='"+aksDomain.getKsSize()+"'"+","+"status='"
				+aksDomain.getStatus()+"'" +"where ksid='"+aksDomain.getKsId()+"'";
				PreparedStatement ps = con.prepareStatement(sql);
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
			return status;
		}
	}