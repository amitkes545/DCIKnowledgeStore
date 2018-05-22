package com.kds.KODE_DEV.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;

/**
 * Servlet implementation class UpdateFacultyDao
 */

public class UpdateFacultyDao  {
	Connection con = null;
	PreparedStatement ps = null;
	//DBTransaction dbT = new DBTransaction();
	public String updateFacultyValues(AdminDomain adomain){
	
	// boolean ret = false;
	String facultyid=adomain.getAdminId();
	//System.out.println("faculty id:"+facultyid);
	String status=null;

	try {
                con = DBTransaction.connect();
                String quary="update users_info set " +"username='"+adomain.getAdminName()+"'"+","+"user_id='"+adomain.getAdminId()+"'"+","
                +"password='"+adomain.getPwd()+"'"+","+"email='"+adomain.getEmail()+"'"+","+"address='"+adomain.getAddress()+"'"+","+"contact_no='"+adomain.getPhone()+"'"+","
                		+"privilege='"+adomain.getPrivilege()+"'"+","+"organization_id='"+adomain.getOrgid()+"'"+","+"created_by='"+adomain.getCreated_by()+"'"+"where user_id='"+adomain.getAdminId()+"'";
                ps=con.prepareStatement(quary);
                //System.out.println("the quary is:"+quary);
                int i=ps.executeUpdate();
                //System.out.println(i+"row is updated successfully");
                if(i==1)
                    status="success";
                else
                	status=" failure";
	}catch(Exception e)
	{
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
	public String deletFacultyValues(AdminDomain adomain)
	{
	
		
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
