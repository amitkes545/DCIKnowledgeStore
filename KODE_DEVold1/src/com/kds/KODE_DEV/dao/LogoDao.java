package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.CreateDomain;
public class LogoDao  {
	Connection con= null;	
	
	ResultSet rs= null;
	PreparedStatement ps=null;
	public String getLogoName(String uid)
	{
		
        String logoname = null;
	
		
		try {

			
			con=DBTransaction.connect();
			String sql= "select logo from org_details where organization_id= (select organization_id from users_info where user_id='"+uid+"')";
						ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				
				logoname=rs.getString("logo");

				
			}
			
			 
		}catch(Exception e){
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   if(con!=null)
			   con.close();
				   if(ps!=null)
				ps.close();
				   if(rs!=null)
					   rs.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		
	  return logoname;

}
	
	
	}
	


