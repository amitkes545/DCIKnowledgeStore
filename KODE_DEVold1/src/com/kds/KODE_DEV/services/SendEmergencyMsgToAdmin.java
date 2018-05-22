package com.kds.KODE_DEV.services;
import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.kds.KODE_DEV.services.CommonService;
import com.kds.KODE_DEV.util.SingleSms;
import com.kds.KODE_DEV.dao.sendEmergencyMsgToAdminDao;
import com.kds.KODE_DEV.dbconnection.DBTransaction;



public class SendEmergencyMsgToAdmin extends CommonService{
	
	public void run() throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		 String userId=request.getParameter("userid");
		 String privilege=request.getParameter("privilege");
		 String sesid=request.getParameter("sesid");
		 String orgId=(String)session.getAttribute("orgId");
		 System.out.println("userId="+userId);
		 System.out.println("privilege="+privilege);
		 System.out.println("sesid="+sesid);
		 
		 sendEmergencyMsgToAdminDao sendEmergencyMsgToAdminDao=new sendEmergencyMsgToAdminDao();
		 String adminUserId=sendEmergencyMsgToAdminDao.getAdminUserId(userId,session.getAttribute("orgId").toString());
		 
		 System.out.println("adminUserId="+adminUserId);
		 
		 String MobileNo=sendEmergencyMsgToAdminDao.getMobileNo(adminUserId);
		 String userName=sendEmergencyMsgToAdminDao.getUserName(adminUserId);
		 String recipentEmailId=sendEmergencyMsgToAdminDao.getEmailId(adminUserId);
		 String senderEmailId=sendEmergencyMsgToAdminDao.getSenderEmail();
		 System.out.println("MobileNo="+MobileNo);
		 
		 int status=sendEmergencyMsgToAdminDao.sendNotificationData(orgId,adminUserId,recipentEmailId,senderEmailId,userName);
		if(status!=0)
		{
		 SingleSms.sendSMS("Hello "+userName+", "+userId+" wants emergency break,Please contact "+userId+" for getting reason, and put the reson on your dashbord notification", MobileNo);
		
		}
		else{
			System.out.println("data not inserted in sender_mail_notification tabel");
		}
		 /*		 response.sendRedirect("../JSP/All-in-one.jsp?sesid="+sesid);
				RequestDispatcher rd=request.getRequestDispatcher("../JSP/All-in-one.jsp");
		 		   rd.forward(request, response);*/
		 }
		
	
}
