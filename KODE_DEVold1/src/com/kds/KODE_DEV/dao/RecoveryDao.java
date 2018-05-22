package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.SessionDomain;


public class RecoveryDao  {
	Connection con = null;
	PreparedStatement ps = null;
	
	ArrayList<SessionDomain> al= new ArrayList<SessionDomain>();
	public String updateStatus(SessionDomain sdomain) {

	//DBTransaction dbT = new DBTransaction();
	// boolean ret = false;
	
	String status=null;

	try {
                con = DBTransaction.connect();
				 
				 ResultSet rs = null;
                String sql="update session_info set status='active' where session_id='"+sdomain.getSessionId()+"'";
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


