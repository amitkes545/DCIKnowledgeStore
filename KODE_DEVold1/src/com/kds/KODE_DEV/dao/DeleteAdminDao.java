package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;



public class DeleteAdminDao  {
	public String deleteValues(AdminDomain adomain)
	{
		//DBTransaction dbT = new DBTransaction();
		Connection con= null;	
		
		PreparedStatement ps=null;
		String status=null;
		try
		{
			con=DBTransaction.connect();
			String quary="delete from users_info where user_id='"+adomain.getAdminId()+"'";
			ps=con.prepareStatement(quary);
			//System.out.println("the quary is"+quary);
			int i=ps.executeUpdate();
			//System.out.println(i+"rows deleted");
			if(i==1)
				status="success";
			else
				status="failure";
			
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
	
       
   