package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.CreateDomain;
import com.kds.KODE_DEV.domain.DisplayDomain;



/**
 * Servlet implementation class DisplayDao
 */

public class DisplayDao  {
	
	ArrayList<CreateDomain> al= new ArrayList<CreateDomain>();
	
	CreateDomain cDomain= new CreateDomain();
	
	
	public  CreateDomain retriveData(DisplayDomain dDomain) {
		//DBTransaction DBT = new DBTransaction();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBTransaction.connect();
			//PreparedStatement pstmt = null;
			ResultSet rs = null;
			String quary = "select * from org_details where organization_id='"+dDomain.getOrg_id()+"'";
			pstmt = con.prepareStatement(quary);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String org_id=rs.getString("organization_id");
				String org_name=rs.getString("organization_name");
				String org_type=rs.getString("org_type");
				String address=rs.getString("address");
				String city=rs.getString("city");
				String country=rs.getString("country");
				String pcode=rs.getString("postal_code");
				String phone=rs.getString("telephone");				
				String fax=rs.getString("fax");
				String ecno=rs.getString("emergency_contact_no");
				String email_id=rs.getString("email");
				String url=rs.getString("url");
				//String logo=rs.getString("logo");
				////System.out.println("logo value is"+logo);
				 //String img = rs.getString("logo");
		         java.io.InputStream img = rs.getBinaryStream("logo");
		         //System.out.println("image value is"+img);
			    //java.io.InputStream imgstream = rs.getBinaryStream("img");
			    //int index = imgstream.read(img2, 0, img.length());
			   // //System.out.println("index value is:"+index);
			    
				String yof=rs.getString("year_of_foundation");
				String belongs=rs.getString("belongs");
				String date=rs.getString("date_time");
				
				cDomain.setOrg_id(org_id);
				cDomain.setOrg_name(org_name);
				cDomain.setOrg_type(org_type);
				cDomain.setAddress(address);
				cDomain.setCity(city);
				cDomain.setCountry(country);
				cDomain.setPcode(pcode);
				cDomain.setPhone(phone);
				cDomain.setFax(fax);
				cDomain.setEcno(ecno);
				cDomain.setEmail_id(email_id);
				cDomain.setUrl(url);
			   // cDomain.setLogo(logo);
				cDomain.setYof(yof);
				cDomain.setBelongs(belongs);
				cDomain.setDate(convertStringToTimestamp(date));
			}
				
				}catch(Exception e){
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
		return cDomain;
	}
	public static Timestamp convertStringToTimestamp(String str_date) {
	    try {
	      DateFormat formatter;
	      formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	       // you can change format of date
	      Date date = formatter.parse(str_date);
	      java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());

	      return timeStampDate;
	    } catch (ParseException e) {
	      //System.out.println("Exception :" + e);
	      return null;
	    }
	  }
}
       
    
