package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.FacilitatorManageKnowStoreDomain;

public class FacilitatorManageLibFtpDao {
	Connection con = null;
	PreparedStatement preparedStatement = null;
	
	public String updateValues(FacilitatorManageKnowStoreDomain aksDomain) {

	String status=null;

	try {
                con = DBTransaction.connect();
				String sql = "update knowledgestorelib_info set " +"ksid='"+aksDomain.getKsId()+"'"+","+"name_of_lib='"+aksDomain.getLibName()+"'"+","+"size_of_lib='"+aksDomain.getLibSize()+"'"+","+"user_id='"+aksDomain.getUserId()+"'"+","+"organization_id='"
				+aksDomain.getOrgId()+"'" +"where lib_id='"+aksDomain.getLibId()+"'";
				preparedStatement = con.prepareStatement(sql);
				
				//System.out.println("the query is:"+sql);
				int i = preparedStatement.executeUpdate();
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
			   preparedStatement.close();
		   }
		   catch(Exception e){
				e.printStackTrace();
	   }
	   }
			return status;
		}
     }