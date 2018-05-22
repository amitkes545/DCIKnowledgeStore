package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.ActivateStudentDao;
import com.kds.KODE_DEV.dao.CreateSessionDao;
import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.domain.ActiveSessionDomain;
import com.kds.KODE_DEV.domain.AssessmentDomain;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;
import com.kds.KODE_DEV.domain.SessionDomain;

@SuppressWarnings("serial")
public class AdminUpdateStudent extends CommonService {
	
	
	static final Logger LOGGER = Logger.getLogger(AdminUpdateStudent.class);
	public void run() throws ServletException,IOException{
		
		session=request.getSession();
		ActivateStudentDao createSessionDao=new ActivateStudentDao();
		ActiveSessionDomain sessionDomain=new ActiveSessionDomain();
		 
		ArrayList<ActiveSessionDomain> arrayList=new ArrayList<ActiveSessionDomain>();
		String selectstudent=request.getParameter("selectstudent");
		String courseid=request.getParameter("courseid");
		String status=request.getParameter("status");
		System.out.println("Sessionid"+selectstudent);
		System.out.println("courseid"+courseid);
		System.out.println("status"+status);
		
		//System.out.println("user value:"+selectstudent);
		String sessionname=request.getParameter("sessionid");
		sessionDomain.setSessionName(request.getParameter("sessionid"));
		String facultyid=(String)session.getAttribute("userid");
		String organizationId=(String)session.getAttribute("orgid");
	
		
		LOGGER.info("category :"+sessionname+ "facultyid:"+facultyid);
		
		ArrayList<ActiveSessionDomain> arrayListForAddDomain = new ArrayList<ActiveSessionDomain>();
		
		int counter=0;
		String[] checkboxGroup = request.getParameterValues("checkboxGroup");
		if(checkboxGroup !=null )
		{
			System.out.println("Number of Records being updated : "+checkboxGroup.length);
			
			
			String firstKey ="";
			String valueForFirstKey = "";
			
			ArrayList alist=null;
			String course="";
			String orgEmail="";
			String name="";
			String ts="";
			String email="";
			int cnt=0;
			java.util.Date date =null;
		    SimpleDateFormat sdf = null;
		    String statusForStudent=null;
			
			
			
			
for(int i=0; i<checkboxGroup.length; i++){
	String enrid=request.getParameter("enrid"+checkboxGroup[i]);
			System.out.println(i+"value : "+request.getParameter("enrid"+checkboxGroup[i]));
			System.out.println("status : "+request.getParameter("status"+checkboxGroup[i]));
			statusForStudent=request.getParameter("status"+checkboxGroup[i]);
			try {
				 cnt=createSessionDao.updateStatusForDoc(request.getParameter("enrid"+checkboxGroup[i]),request.getParameter("status"+checkboxGroup[i]));
				HashMap <String,String>hmap=createSessionDao.getEmailId(request.getParameter("enrid"+checkboxGroup[i]));
	
				
			 firstKey = (String) hmap.keySet().toArray()[0];
			 valueForFirstKey = hmap.get(firstKey);
			
			 alist=createSessionDao.getEmailData(request.getParameter("enrid"+checkboxGroup[i]));
			 course=createSessionDao.getCourseIdStudent(request.getParameter("enrid"+checkboxGroup[i]));
			 orgEmail=createSessionDao.getOrgEmail((String)request.getAttribute("orgId"));
			 name=(String) alist.get(0);
			 ts=(String) alist.get(1);
			 email=(String)alist.get(2);
			
			
			 date = Calendar.getInstance().getTime();
		     sdf = new SimpleDateFormat("yyyyMMdd.hhmmss");
		    
			
			
			
			System.out.println("1.status="+statusForStudent);
			
			//if("Completed".equalsIgnoreCase(statusForStudent));
			String ProjectURL="www.kompacdgit.com/KnowledgeStore/JSP/";
			System.out.println("boolean="+statusForStudent.equals("Completed"));
			if(statusForStudent.equals("Completed") && cnt!=0)
			{	
			System.out.println("email="+email);
			System.out.println("valueForFirstKey values="+valueForFirstKey);
			
						
						
	String msg=" Dear " + name + ",<br/><br/>" + "Greetings from "+ts+"!! <br/><br/>" 
+"Thank you for your interest in the "+course+", your verification has been completed successfully.<br/><br/>" 
+"Please click on the link below for the course fee payment:<br/><br/>"
+"Payment Link: <a href='"+ProjectURL+"Student-fee-Payment.jsp?enrId="+enrid+"'>Access the Admission Fee Payment by clicking here</a><br/><br/>"
+"Please kindly do the fee payment.The said payment needs to be made within 5 working days from "+sdf.format(date)+"<br/><br/>."
+"Post the confirmation of the fees payment received, you will be provided with the enrollment no,<br/><br/>"
+"course timetable and the digital classroom infrastructre login details<br/><br/>."
+"Delay in the payment of fees will lead to cancellation of the enrollment.<br/><br/><br/><br/>"
+"Thanks and Regards<br/>"
+""+ts+"<br/><br/><br/>"
+"For any technical assistance please contact on the detail provided below:<br/><br/>"
+"Email ID:"+orgEmail+"";

	try {							
							
						Mailer.send(email, "Document Verification Process status", msg);
					counter++;
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
					System.out.println("completed");
			}
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		System.out.println("2.Status="+statusForStudent);
		if(statusForStudent.equals("In Completed") && cnt!=0)
			{	
			System.out.println("firstKey values="+firstKey);
			System.out.println("valueForFirstKey values="+valueForFirstKey);
			
						
						
	String msg=" Dear " + name + ",<br/><br/>" + "Greetings from "+ts+"!! <br/><br/>" 
+"Thank you for your interest in the "+course+", we regret to inform you that your verification has been In Completed.<br/><br/>" 
+"Would request you to get in touch with our admissions department.<br/><br/>."
+"You may also write directly to our admissions department on the email address.<br/><br/> "
+"provided "+orgEmail+" stating your temporary reference no.<br/><br/><br/>"
+"Thanks and Regards<br/>"
+""+ts+"<br/><br/><br/>"
+"For any technical assistance please contact on the detail provided below:<br/><br/>"
+"Email ID:"+orgEmail+"";

	
	
	
	
	try {							
							
						Mailer.send(email, "Document Verification Process status", msg);
					counter++;
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
					System.out.println("In completed");
			}
			System.out.println("3.status="+statusForStudent);
			
			if(statusForStudent.equals("In Progress") && cnt!=0)
			{	
			System.out.println("firstKey values="+firstKey);
			System.out.println("valueForFirstKey values="+valueForFirstKey);
			
						
						
	String msg=" Dear " + name + ",<br/><br/>" + "Greetings from "+ts+"!! <br/><br/>" 
+"Thank you for your interest in the "+course+", your verification is still under progress.<br/><br/>" 
+"Once the same is completed you will receive an email from our admissions department initmating the same.<br/><br/>."
+"Thanks and Regards<br/>"
+""+ts+"<br/><br/><br/>"
+"For any technical assistance please contact on the detail provided below:<br/><br/>"
+"Email ID:"+orgEmail+"";
	
	try {							
							
						Mailer.send(email, "Document Verification Process status", msg);
					counter++;
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
					System.out.println("completed");
			}
		
			System.out.println("4.status="+statusForStudent);
			if(statusForStudent.equals("On Hold") && cnt!=0)
			{	
			System.out.println("firstKey values="+firstKey);
			System.out.println("valueForFirstKey values="+valueForFirstKey);
			
						
						
	String msg=" Dear " + name + ",<br/><br/>" + "Greetings from "+ts+"!! <br/><br/>" 
+"Thank you for your interest in the "+course+", your verification is still On Hold.<br/><br/>" 
+"Once the same is completed you will receive an email from our admissions department initmating the same.<br/><br/>."
+"Thanks and Regards<br/>"
+""+ts+"<br/><br/><br/>"
+"For any technical assistance please contact on the detail provided below:<br/><br/>"
+"Email ID:"+orgEmail+"";
	
	try {							
							
						Mailer.send(email, "Document Verification Process status", msg);
					counter++;
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
					System.out.println("completed");
			}			
			
			
		
		}
		}
if(counter==checkboxGroup.length)
{
	String sessionStatus="Status is updated successfully";
		request.setAttribute("FacultySuccess",sessionStatus);
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/DocumentVerification.jsp");
		requestDispatcher.forward(request, response);
}
else
{
	String sessionStatus="Failed to update status";
	request.setAttribute("FacultyFailure",sessionStatus);
	RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/DocumentVerification.jsp");
	requestDispatcher.forward(request, response);
}
		
	}
	}
		
	