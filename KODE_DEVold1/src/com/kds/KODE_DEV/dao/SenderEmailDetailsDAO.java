package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;

public class SenderEmailDetailsDAO {
	Connection con = null;
	PreparedStatement ps = null;
	
	//DBTransaction dbcon=null;
	
	public SenderEmailDetailsDomain senderdetails(){
		
		//dbcon =new DBTransaction();
		SenderEmailDetailsDomain senderDom =new SenderEmailDetailsDomain();
		try
		{
			con= DBTransaction.connect();
			//PreparedStatement ps= null;
			String sql="select * from senderemaildetails";
			ps = con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
				{	
				senderDom.setEmailid(rs.getString("emailid"));
				senderDom.setHost(rs.getString("host"));
				senderDom.setPassword(rs.getString("password"));
				senderDom.setPop(rs.getString("pop"));
				senderDom.setPort(rs.getInt("port"));
				//System.out.println("from sender details in dao=== emailid= "+senderDom.getEmailid()+"");

				}
			}
		catch(Exception e)
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
		return senderDom;
	}
	
}