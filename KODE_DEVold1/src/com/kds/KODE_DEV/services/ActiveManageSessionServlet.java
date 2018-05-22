package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.ActivateStudentDao;
import com.kds.KODE_DEV.dao.AssessmentDao;
import com.kds.KODE_DEV.dao.CreateSessionDao;
import com.kds.KODE_DEV.dao.FacultySetupknowStoreDao;
import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.domain.ActiveSessionDomain;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;
import com.kds.KODE_DEV.domain.SessionDomain;
import com.kds.KODE_DEV.services.Mailer;

@SuppressWarnings("serial")
public class ActiveManageSessionServlet extends CommonService{
	String teachingSourceName;
	public void run() throws ServletException,IOException{
		ActivateStudentDao status=new ActivateStudentDao();
		int count=0;
		session=request.getSession(true);
		teachingSourceName=(String)session.getAttribute("organizationName");
		int result=0;
		String[] checkboxGroup = request.getParameterValues("checkboxGroup");
		ActiveSessionDomain sdomain=new ActiveSessionDomain();
		FacultySetupknowStoreDao ksDao = new FacultySetupknowStoreDao();
		 if(checkboxGroup.length>0){
		
			 String subject="Current status of students in DCI";
    			String message="";
    			
			        for(int i=0; i<checkboxGroup.length; i++) {
			        	
			        	System.out.println("checkboxgroup "+checkboxGroup[i]);
			        	System.out.println(request.getParameter("status"+checkboxGroup[i]));
			        	sdomain.setStatus(request.getParameter("status"+checkboxGroup[i]));
			        	String statusofuser=sdomain.getStatus();
			        	/*
			        	 * "Hi XYZ, 

Your status in DCI has been changed, please login to DCI to view the current status. 
DCI URL - https://www.kompacdigit.com:8443/KODE

Thanks and Regards
Anna University

For any technical assistance please contact on the details provided below:
Toll Free No: 1800123456
SMS: 
Email ID: techicalsupport@kompaceducation.com"

			        	 */
			        	message = "Hi "+checkboxGroup[i]+",<br/><br/>"
			        			+"Your status in DCI has been changed, please login to DCI to view the current status."/* as an "+statusofuser+"."+"<br/>"*/
			        					+ "<br/>DCI URL - <a href='https://www.kompacdigit.com:8443/KODE/'>https://www.kompacdigit.com:8443/KODE/</a>"+"<br/><br/>" + "Thanks and Regards" + "<br/>" + teachingSourceName+"<br/><br/>"+"For any technical assistance please contact on the details provided below:" + "<br/><br/>"
			        					+   "<strong>Toll Free No:</strong> 1800123456"+"<br/>"+"<strong>SMS:</strong> 1234567891"+"<br/>"+"<strong>Email ID:</strong> <a href='mailto:technicalsupport@kompaceducation.com'>technicalsupport@kompaceducation.com</a>";
			        	 String to=ksDao.getEmailId(checkboxGroup[i]);
			        	try {
							count=status.updateStatus(checkboxGroup[i],statusofuser);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        	if(count==1)
			        	{
			        		try {
								Mailer.send(to, subject, message);
							} catch (AddressException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (MessagingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			        		result++;
			        	}
			        }
			        
			        if(result==checkboxGroup.length)
			        {
			        	System.out.println("update");
			        	String sessionStatus="Student status updation successful; details sent via e-mail";
			  			request.setAttribute("FacultySuccess",sessionStatus);
			  			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/AdminActivateStudent.jsp");
			  			requestDispatcher.forward(request, response);
				        }else{
				        	System.out.println("not update");
				        	String sessionStatus="Failure for updating student values";
				  			request.setAttribute("FacultyFailure",sessionStatus);
				  			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/AdminActivateStudent.jsp");
				  			requestDispatcher.forward(request, response);
				        }
		 
			        }
		 }
	        
			       	
					       
	}
	


