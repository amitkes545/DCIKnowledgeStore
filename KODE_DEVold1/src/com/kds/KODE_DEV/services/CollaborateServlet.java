package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;
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

import com.kds.KODE_DEV.dao.CollaborateDao;
import com.kds.KODE_DEV.dao.ParameterDao;
import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.dao.SessionDao;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;

@SuppressWarnings("serial")
public class CollaborateServlet extends CommonService {
	String emailid="";
	String password="";
	String host="" ; 
	String pop="";
	int    port;
	String link="",groupName=""; 
	ArrayList<AdminDomain> to;
	String teachingSourceName;
	ArrayList<String> group_member_email_array_list=new ArrayList<String>();
	static final Logger LOGGER = Logger.getLogger(CollaborateServlet.class);
    public void run() throws ServletException,IOException{
    	session = request.getSession(true);
    	AdminDomain admindomain=new AdminDomain();
    	CollaborateDao collaboratedao=new CollaborateDao();
    	SessionDao sessionDao=new SessionDao();
    	ArrayList<AdminDomain> arraylist=new ArrayList<AdminDomain>();
    	String facultyid=request.getParameter("userid");
    	String organizationId=request.getParameter("orgid");
    	LOGGER.info("faculty id:"+facultyid+ "orgid :"+organizationId);
    	admindomain.setCreated_by(facultyid);
    	admindomain.setOrgid(organizationId);
    	 groupName=request.getParameter("groupname");
    	 groupName=groupName.trim()+"_group";
    	  teachingSourceName=(String)session.getAttribute("organizationName");
    	 //System.out.println("groupName==="+groupName);
    	SendEmailDao senderDao =new SendEmailDao();//create dao object for sending email details
 		//session.removeAttribute("MsgValue");
 		SenderEmailDetailsDomain senderDom =(SenderEmailDetailsDomain)senderDao.senderdetails();//selecting email details from dao
 		
 		 emailid=senderDom.getEmailid();
 		 password=senderDom.getPassword();
 		 host= senderDom.getHost();
 		 pop=senderDom.getPop();
 		   port=senderDom.getPort();
 		   link=senderDao.getClassRoomLink();//selecting class room link from dao
    	LOGGER.info("faculty id"+admindomain.getCreated_by());
    	String[] values=request.getParameterValues("selectstudent");
    	
    	StringBuffer stuidbuffer = new StringBuffer();
        
        if(values.length > 0){ 
               
        	stuidbuffer.append(values[0]);
                for(int i=1; i < values.length; i++){
                	stuidbuffer.append(",  ").append(values[i]);
                }
               
        } 
        //System.out.println("studnet id are----------"  + stuidbuffer.toString());

//--By prity for limiting the number of students in a group
 	   ParameterDao numDao=new ParameterDao();
 	   String paramvalue=numDao.GetParameterValue("No_Of_Participant");
 	   int nump = Integer.parseInt(paramvalue);
//--By Prity end        
    	         
    	
    	if(values == null){
    		LOGGER.info("Students are not selected for this group");
    		String sessionmsg="Students are not selected for this group";
			request.setAttribute("FacultySuccess",sessionmsg);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/CollaborateStudent.jsp");
			requestDispatcher.forward(request, response);
    	}else {
    		int no_of_students = 0;
    	for(String s:values){
    		AdminDomain fdomain=new AdminDomain();
    		fdomain.setAdminId(s);
    		arraylist.add(fdomain);
    		LOGGER.info("selected student id are:"+fdomain.getAdminId());
    		//System.out.println("selected student id are:"+fdomain.getAdminId());
    		no_of_students++; //By prity for limiting number of participants
    	}
    	java.util.Iterator<AdminDomain> it=arraylist.iterator();
    	while(it.hasNext()){
    		LOGGER.info("array values in service:"+it.next().getAdminId());
    		
    	}
    	//--By Prity for limiting the number of participants    	
    	if(no_of_students > nump){
    		LOGGER.info("A group can not contain more than "+nump+" students!");
    		String sessionmsg="A group can not contain more than "+nump+" students!";
			request.setAttribute("FacultyFailure",sessionmsg);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/CollaborateStudent.jsp");
			requestDispatcher.forward(request, response);	
    	}
    	else{
//--By Prity end    	
    		String status=collaboratedao.insertGroupId(arraylist,groupName,admindomain);
    		LOGGER.info("status values are:"+status);
    	
    		if("success".equalsIgnoreCase(status)){ 
    			 to=sessionDao.getAllStudentEmailId(arraylist,organizationId);
    			//System.out.println("student details:"+to.size());
    			for(AdminDomain TO:to){
    			//	sendMailGroup(host,TO,stuidbuffer);
    				group_member_email_array_list.add(TO.getAdminId());
    				////System.out.println("ids in service:"+TO.getAdminId());
    			}
    			String sessionmsg="Group created successfully";
    		    //System.out.println("group member "+group_member_email_array_list.size());
    			sendMailGroup(stuidbuffer);
    			request.setAttribute("FacultySuccess",sessionmsg);
    			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/CollaborateStudent.jsp");
    			requestDispatcher.forward(request, response);
    			
    		}else {
    			String sessionmsg="Group creation has failed";
    			request.setAttribute("FacultyFailure",sessionmsg);
    			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/CollaborateStudent.jsp");
    			requestDispatcher.forward(request, response);
    		}
    	}
    	}
    }
    public void sendMailGroup(StringBuffer stuidval)
   	{
    	try
  		 {
    	  InternetAddress[] address = new InternetAddress[group_member_email_array_list.size()];
    	  for(int i =0; i< group_member_email_array_list.size(); i++)
    	  {
    	      address[i] = new InternetAddress(group_member_email_array_list.get(i));
    	  }
   		 Properties props= new Properties();
   		 props.put("mail.smtp.auth",pop);
   	     props.put("mail.smtp.starttls.enable",pop);
   		 props.put("mail.smtp.host",host);
   		 props.put("mail.smtp.port",port);
   		 props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
   		props.put("mail.smtp.ssl.enable", "true");
   		 Session session= Session.getInstance(props, new javax.mail.Authenticator(){
   			 protected PasswordAuthentication getPasswordAuthentication(){
   				 return new PasswordAuthentication(emailid,password);
   			 	} 
   			 });
   		  
   			Message message= new MimeMessage(session);
   			message.setFrom(new InternetAddress(emailid));
   			message.setRecipients(Message.RecipientType.TO,address);
   			message.setSubject("Group setup"); 
   			/*
   			 * "Hello <student>,

You have been assigned to the group: <name of the group>. Members of this group are <X,Y,Z>.

Thanks and Regards
ABC

For any technical assistance please contact on the details provided below:
Toll Free No: 1800123456
SMS: 
Email ID: techicalsupport@kompaceducation.com"

   			 */
   			String msg="Hello, "+"<br/><br/> You have been assigned to the group: "+groupName+". Members of the group are "+stuidval+". <br/><br/>Thanks and Regards" + "<br/>" + teachingSourceName +"<br/><br/>"+"For any technical assistance please contact on the details provided below:" + "<br/>"
   					+ "<strong>Toll Free No:</strong> 1800123456"+"<br/>"+"<strong>SMS:</strong> 1234567891"+"<br/>"+"<strong>Email ID:</strong> <a href='mailto:technicalsupport@kompaceducation.com'>technicalsupport@kompaceducation.com</a>";
   			
   			message.setContent(msg, "text/html");
   			
   			 Transport.send(message);
   			//Mailer.send(to, subject, msg);
   			//System.out.println("send successfully");
   			/*try { 
   				response.sendRedirect("../JSP/success.jsp");
   			} catch (IOException e) {
   				e.printStackTrace();
   			}*/
   		 	}
   		 catch(MessagingException e) 
   		 {
   			 throw new RuntimeException(e);
   		 }
       }	
    }

