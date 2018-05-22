package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.OwnerManageKnowStoreDomain;

public class ManageKnowStoreDao   {
	Connection con = null;
	 PreparedStatement ps = null;
	// ResultSet rs = null;
	public String updateValues(OwnerManageKnowStoreDomain mksDomain) {

		//DBTransaction dbT = new DBTransaction();
		// boolean ret = false;
		
		String status=null;

		try {
                    con = DBTransaction.connect();
					// PreparedStatement ps = null;
					// ResultSet rs = null;
					String sql = "update knowledgestore_info set " +"ksid='"+mksDomain.getKsId()+"'"+","+"organization_id='"+mksDomain.getOrgId()+"'"+","+"admin_id='"+mksDomain.getUserId()+"'"+","+"knowledge_store_space='"+mksDomain.getKsSize()+"'"+","+"status='"
					+mksDomain.getStatus()+"'" +"where ksid='"+mksDomain.getKsId()+"'";
					ps = con.prepareStatement(sql);
					
					//System.out.println("the quary is:"+sql);
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