package com.kds.KODE_DEV.dao;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.CreateDomain;


public class UpdateCustomerDao {
	Connection con = null;
	PreparedStatement pstmt = null;
	public boolean update(CreateDomain editCreateDomain) throws ServletException, IOException
	{
		
		
		
		int row = 0;
		//DBTransaction db= new DBTransaction();
		try{
			con = DBTransaction.connect();
			//PreparedStatement pstmt = null;
			InputStream i= editCreateDomain.getImage();
			//System.out.println("Input stream"+i);
			/*if(i==null)
			{
				//System.out.println("if part"+editCreateDomain.getOrg_id());
				pstmt=con.prepareStatement("update org_details set organization_name=?,org_type=?, address=?,city=?,country=?,postal_code=?,telephone=?,fax=?,emergency_contact_no=?,email=?,url=?,year_of_foundation=?,belongs=?,date_time=?,state=? where organization_id=?");
				pstmt.setString(16,editCreateDomain.getOrg_id());
			}
			else
			{
				long j= editCreateDomain.getImageSize();
				//System.out.println("image value"+j);
				//System.out.println("else part"+editCreateDomain.getOrg_name());
				pstmt=con.prepareStatement("update org_details set organization_name=?,org_type=?, address=?,city=?,country=?,postal_code=?,telephone=?,fax=?,emergency_contact_no=?,email=?,url=?,logo=?,year_of_foundation=?,belongs=?,date_time=?,state=? where organization_id=?");
				
				pstmt.setAsciiStream(12,i);
				pstmt.setString(17,editCreateDomain.getOrg_id());
			}
*/
			pstmt.setString(1,editCreateDomain.getOrg_name());
			pstmt.setString(2,editCreateDomain.getOrg_type());
			pstmt.setString(3,editCreateDomain.getAddress());
			pstmt.setString(4,editCreateDomain.getCity());
			pstmt.setString(5,editCreateDomain.getCountry());
			pstmt.setString(6,editCreateDomain.getPcode());
			pstmt.setString(7,editCreateDomain.getPhone());
			pstmt.setString(8,editCreateDomain.getFax());
			pstmt.setString(9,editCreateDomain.getEcno());
			pstmt.setString(10,editCreateDomain.getEmail_id());
			pstmt.setString(11,editCreateDomain.getUrl());
			pstmt.setString(13,editCreateDomain.getYof());
			pstmt.setString(14,editCreateDomain.getBelongs());
			pstmt.setTimestamp(15,editCreateDomain.getDate());
			pstmt.setString(16,editCreateDomain.getState());
		
			////System.out.println("imgname="+editCompanyDomain.getImgName());
			////System.out.println("image="+i);
			////System.out.println(pstmt);
			row = pstmt.executeUpdate();
			
		if(row == 1)
			{
				return false;
			}
			//con.close();
			pstmt.close();
				
		}
		
		catch(Exception e)
		{
			
			e.printStackTrace();
			
		}
		finally
		   {
			   try{
				   con.close();
				   pstmt.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return true;
			
	}
	
}
