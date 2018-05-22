package com.kds.KODE_DEV.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.CreateDomain;



public class DeleteDao {
	
	public String deleteValues(CreateDomain cDomain) {

		//DBTransaction dbT = new DBTransaction();
		// boolean ret = false;
		Connection con= null;	
		
		PreparedStatement ps=null;
		String status=null;

		try {
                    con = DBTransaction.connect();
					// PreparedStatement ps = null;
					// ResultSet rs = null;
					String sql ="delete from org_details where organization_id='"+cDomain.getOrg_id()+"'";
					ps = con.prepareStatement(sql);
					
					//System.out.println("the quary is:"+sql);
					int i = ps.executeUpdate();
					//System.out.println(i+"row is deleted successfully");
					
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
