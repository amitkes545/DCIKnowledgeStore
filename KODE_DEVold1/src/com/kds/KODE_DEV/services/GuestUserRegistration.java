package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.CustomerDao;
import com.kds.KODE_DEV.dao.InsertDao;
import com.kds.KODE_DEV.domain.AdminDomain;

@SuppressWarnings("serial")
public class GuestUserRegistration extends CommonService {
	
	String guestUserPassword="";
	public void run() throws ServletException,IOException {
		
		AdminDomain adminDomain=new AdminDomain();
		InsertDao insertDao=new InsertDao();
		 CustomerDao customerDao=new CustomerDao();
		
		String guestUser=request.getParameter("userName");
		String address=request.getParameter("address");
		
		
		 if (guestUser.length() > 4){
			 guestUser =guestUser.substring(0, 3);
          }
		 if (address.length() > 4){
			 address =address.substring(0, 3);
          }
		 String guestUserId=guestUser+address;
		 //System.out.println("guestUserId:"+guestUserId);
		 
		//calling dao method for generate password randomlly
		   String password= customerDao. randomSeriesForThreeCharacter();
		   guestUserPassword=guestUser+password;
		   //System.out.println("guestUserPassword:"+guestUserPassword);
		   adminDomain.setAdminId(guestUserId);
		   adminDomain.setAdminName(request.getParameter("userName"));
		   adminDomain.setPwd(guestUserPassword);
			adminDomain.setEmail(request.getParameter("email"));
			adminDomain.setAddress(request.getParameter("address"));
			adminDomain.setPhone(request.getParameter("mobile"));
		   
		String status=insertDao.addGuestUserDetails(adminDomain);
		//System.out.println("status:"+status);
	}

}
