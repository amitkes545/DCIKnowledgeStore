package com.kds.KODE_DEV.dao;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.CreateDomain;



public class CreateDao  {
	
       
	@SuppressWarnings("unused")
	public String checkUserCredential1s(CreateDomain cDomain) {

		//DBTransaction dbT = new DBTransaction();
		// boolean ret = false;
		InputStream inputStream = null; // input stream of the upload file
		String status = null;
		Connection con=null;
		PreparedStatement ps=null;
		
		try {

			con = DBTransaction.connect();
			// PreparedStatement ps = null;
			// ResultSet rs = null;
			String sql = "insert into org_details(organization_id, organization_name, org_type, address, city, country, postal_code, " +
					"telephone, fax, emergency_contact_no, email,  url, logo, year_of_foundation, belongs, date_time) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1,cDomain.getOrg_id());
			ps.setString(2, cDomain.getOrg_name());
			ps.setString(3, cDomain.getOrg_type());
			ps.setString(4, cDomain.getAddress());
			ps.setString(5, cDomain.getCity());
			ps.setString(6, cDomain.getCountry());
			ps.setString(7, cDomain.getPcode());
			ps.setString(8, cDomain.getPhone());
			ps.setString(9, cDomain.getFax());
			ps.setString(10, cDomain.getEcno());
			ps.setString(11, cDomain.getEmail_id());
			ps.setString(12, cDomain.getUrl());
			//ps.setString(13, cDomain.getLogo());
//			 if (inputStream != null) {
//	                // fetches input stream of the upload file for the blob column
//	                ps.setBlob(13, cDomain.getInputstream());
//	            }
			HttpServletRequest request;
			//boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
			////System.out.println("path value is:"+isMultiPart);
			////System.out.println("Inside Servlet");
		    //if(!isMultiPart){
		        //System.out.println("Form type is not multipart/form-data");
		        //System.out.println("File Not Uploaded");
		    //}
		   // else
		    {
		        FileItemFactory dfit = new DiskFileItemFactory();
		        ServletFileUpload sfu = new ServletFileUpload(dfit);
		        int size=(int) sfu.getFileSizeMax();
		    }
	       // ps.setString(13,logo);
			ps.setString(14, cDomain.getYof());
			ps.setString(15, cDomain.getBelongs());
			ps.setTimestamp(16,cDomain.getDate());
			//System.out.println("the quary is:"+ps);
			int n = ps.executeUpdate();
			//System.out.println("query is inserted successfully");
			if (n != 0)
				status = "valid";
			else
				status = "notvalid";
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

			
