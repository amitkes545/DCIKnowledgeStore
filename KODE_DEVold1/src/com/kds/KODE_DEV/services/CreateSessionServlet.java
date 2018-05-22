package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.AssessmentDao;
import com.kds.KODE_DEV.dao.CreateSessionDao;
import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.dao.ParameterDao;

import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;
import com.kds.KODE_DEV.domain.SessionDomain;
@SuppressWarnings("serial")
public class CreateSessionServlet extends CommonService {
	String userid="";
	String SessionStartTime="";
	String SessionEndTime="";
	String hours="";
	String SessionName="";
	String emailid="";
	String password="";
	String host="" ;
	String pop="";
	String start_date="",end_date="";
	int    port;
	String link="";
	String username;
	String teachingSourceName;
	ArrayList<String> group_member_email_array_list=new ArrayList<String>();
	//private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(CreateSessionServlet.class);
   public void run() throws ServletException,IOException, MessagingException{
	   group_member_email_array_list.clear();
	    session = request.getSession();
	   String groupvalue="";
	 //  long diffHours = 0;
	   SessionDomain sessionDomain=new SessionDomain();
	   AssessmentDao assessmentDao=new AssessmentDao();
	   System.out.println("Assessment DAO");
	   CreateSessionDao sessionDao=new CreateSessionDao();
	   ParameterDao numDao=new ParameterDao();
	   //ParameterDomain numDomain=new ParameterDomain();
	   String paramvalue=numDao.GetParameterValue("No_Of_Participant");
	   //System.out.println("paramvalue="+paramvalue);
	   //int nump=numDomain.getNum();
	   username=(String)session.getAttribute("userName");
	   teachingSourceName=(String)session.getAttribute("organizationName");
	   int nump = Integer.parseInt(paramvalue);
	   System.out.println("nump="+nump);
	   
	   String sessionstarttime=request.getParameter("sSTime");
	   String sessionendTime=request.getParameter("sETime");
	   
	   System.out.println("sessionstarttime="+sessionstarttime);
	   System.out.println("sessionendTime="+sessionendTime);
	   
	   
	   
	   userid=request.getParameter("facultyId");
	   //String facultyid=(String)session.getAttribute("userid");
		String organizationId=(String)session.getAttribute("orgid");
	   //System.out.println("sessionstarttime:"+sessionstarttime+ "sessionendTime:"+sessionendTime);
		
		String courseId=request.getParameter("category");
		//String subname=request.getParameter("subject");
		//String topicname=request.getParameter("topic");
		//String syllabusId="syl1";//request.getParameter("syllabusId");
		
		String cacs_id=sessionDao.getCacsId(courseId,organizationId);
				
		
		
		String subjectId=request.getParameter("subject");
		String topicId=request.getParameter("topic");
		String subtopicId=request.getParameter("subtopic");
		
		
		String syllabusId=sessionDao.getSyllabusId(cacs_id, subjectId,organizationId);
		
		sessionDomain.setSyllabusId(syllabusId);
		
		System.out.println("subjectId="+subjectId);
		System.out.println("topicId="+topicId);
		System.out.println("subtopicId="+subtopicId);
		
		sessionDomain.setCourseId(courseId);
		sessionDomain.setSyllabusId(syllabusId);
		sessionDomain.setSubjectId(subjectId);
		sessionDomain.setTopicId(topicId);
		sessionDomain.setSubtopicId(subtopicId);
		

	     try {
	//	DateFormat formatter = new SimpleDateFormat("yyyy/mm/dd HH:mm");
		  // java.util.Date date_st = formatter.parse(sessionstarttime);
		   //java.util.Date date_end = formatter.parse(sessionendTime);
		   
		   //String dateSample = ""19/06/2017 13:00"";
		   //  String oldFormat = "dd/MM/yyyy HH:mm";
		     String newFormat = "yyyy/MM/dd HH:mm";

		     SimpleDateFormat sdf1 = new SimpleDateFormat(newFormat);
		     SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);


		         System.out.println(sdf2.format(sdf1.parse(sessionstarttime)));
		          start_date=sdf2.format(sdf1.parse(sessionstarttime));
		          end_date=sdf2.format(sdf1.parse(sessionendTime));
		         

		     } catch (ParseException e) {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		     }
	 //  if(!(sessionstarttime.isEmpty()) && !(sessionendTime.isEmpty())){
		  /* SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");

			Date sessionStartTime = null;
			Date sessionEndTime = null;

			try {
				sessionStartTime = format.parse(sessionstarttime);
				sessionEndTime = format.parse(sessionendTime);

				//in milliseconds
				long diff = sessionEndTime.getTime() - sessionStartTime.getTime();

				long diffSeconds = diff / 1000 % 60;
				long diffMinutes = diff / (60 * 1000) % 60;
				diffHours = diff / (60 * 60 * 1000) % 24;
				long diffDays = diff / (24 * 60 * 60 * 1000);
	             hours=diffHours+ "hr"+" "+diffMinutes+ "Minutes";
	             //System.out.println("hours :"+hours);
				System.out.print(diffDays + " days, ");
				System.out.print(diffHours + " hours, ");
				System.out.print(hours + " hours and minutes.");
				System.out.print(diffMinutes + " minutes, ");
				System.out.print(diffSeconds + " seconds.");

			} catch (Exception e) {
				e.printStackTrace();
			}*/
			//sessionDomain.setDuration(hours);
			//request.setAttribute("Duration",hours);
			//response.sendRedirect("..JSP/CreateSession.jsp");
			/*RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/CreateSession.jsp");
			requestDispatcher.forward(request, response);*/
			 
	  // }else {
	   
	   SessionName=request.getParameter("sessionName");
		SessionStartTime=request.getParameter("sSTime");
		SessionEndTime=request.getParameter("sETime");
		
		
		sessionDomain.setSessionName(SessionName);
		sessionDomain.setCategory(request.getParameter("category"));
		sessionDomain.setSessionStartTime(start_date);
		sessionDomain.setSessionEndTime(end_date);
		sessionDomain.setDuration(request.getParameter("duration"));
		//sessionDomain.setStatus(request.getParameter("status"));
	   System.out.println("sesstion start time:"+sessionDomain.getSessionStartTime()+ "sesstion end time:"+sessionDomain.getSessionEndTime()+ "duration :"+request.getParameter("duration"));
	  // String dateStart=request.getParameter("sSTime");
	   //String dateStop=request.getParameter("sETime");
	   sessionDomain.setCostOfSession(request.getParameter("costOfSession"));
	   sessionDomain.setFacultyId(request.getParameter("facultyId"));
	   sessionDomain.setOrgId(request.getParameter("organizationId"));
	   String individual=request.getParameter("student_id"); //individual student ID
	   String groupid=request.getParameter("group_id");
	   String all=request.getParameter("group"); //receipient type which been selected by faculty
	   System.out.println("groupid:"+groupid);
	   System.out.println("Receipient type: "+all);
	   System.out.println("student_id: "+individual);
	   String recipientType=all;
	   sessionDomain.setRecipientType(recipientType);
	   // additions courseId ,subjectId , topicId , subtopicId
	   sessionDomain.setCourseId(courseId);
	   sessionDomain.setCategory(courseId);
	   sessionDomain.setSubjectId(subjectId);
	   sessionDomain.setTopicId(topicId);
	   sessionDomain.setSubtopicId(subtopicId);
	   sessionDomain.setStatus("Available");
	   
	   if("Group".equals(recipientType))
	   {
		   sessionDomain.setGroupId(groupid);
	   }else if("Individual".equals(recipientType))
	   {
		   sessionDomain.setIndividualId(individual);
	   }else
	   {
	   }
	   
	   
	   
	   
	   //selecting group values based on group name from dao
	   //if(all.equalsIgnoreCase("Individual")){
	   //Below condition to get list of user from either Group or All
    if(all.equalsIgnoreCase("Group")){ //faculty selected group as receipient type
	   if(groupid!=""){
		   System.out.println("in Group");
			 groupvalue=assessmentDao.getGroupValues(groupid,userid,organizationId);
			//System.out.println("group values:"+groupvalue);
			String[]students= groupvalue.split("#");
			System.out.println("numbers:"+students.length);
//By Prity to check number of students in group
			if(students.length > nump)
			{
				String sessionStatus="Session can not be assigned to a group of more than "+nump+" participants!";
				request.setAttribute("FacultyFailure",sessionStatus);
				RequestDispatcher rd=request.getRequestDispatcher("../JSP/CreateSession.jsp");
				rd.forward(request, response);
				return;
			}
			/*else{
				String sessionStatus="Session Created Successfully";
				request.setAttribute("FacultySuccess",sessionStatus);
				RequestDispatcher rd=request.getRequestDispatcher("../JSP/CreateSession.jsp");
				rd.forward(request, response);
				return;
			}*/
		}
	   }else if(all.equalsIgnoreCase("All")){ //Faculty selected All as receipient type
	   if(all!=""){
		   ArrayList<String> usersIds=sessionDao.getUsersId(sessionDomain);
		   System.out.println(usersIds.toArray().toString());
		   System.out.println(usersIds.toArray().length);
			//String[]students= groupvalue.split("#");
			//System.out.println("numbers:"+students.length);
//By Prity to check number of students in group
			if(usersIds.toArray().length > nump)
			{
				String sessionStatus="Session can not be assigned to more than " +nump+ " participants!";
				request.setAttribute("FacultyFailure",sessionStatus);
				RequestDispatcher rd=request.getRequestDispatcher("../JSP/CreateSession.jsp");
				rd.forward(request, response);
				return;
			}/*else{
			String sessionStatus="Session Created Successfully";
			request.setAttribute("FacultySuccess",sessionStatus);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/CreateSession.jsp");
			rd.forward(request, response);
			return;
			}*/
		}
	   }
/*    else if(all.equalsIgnoreCase("Individual")){
			String sessionStatus="Session Created Successfully";
			request.setAttribute("FacultySuccess",sessionStatus);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/CreateSession.jsp");
			rd.forward(request, response);
			return;
    }*/
//By prity end	  
	   sessionDomain.setAll(all);
	   sessionDomain.setIndividualId(individual);
	   sessionDomain.setGroupId(groupid);//groupvalue);
	   sessionDomain.setGroupValue(groupvalue);
	  //System.out.println("group value in servlet:"+sessionDomain.getGroupId());
	   System.out.println("individual: "+individual+  "groupid: "+groupid+  "all: "+all);
	   //System.out.println("sessionname:"+request.getParameter("sessionName"));
	   System.out.println("orgid is:"+request.getParameter("organizationId"));
	   
	  
		 SendEmailDao senderDao =new SendEmailDao();//create dao object for sending email details
 		//session.removeAttribute("MsgValue");
 		SenderEmailDetailsDomain senderDom =(SenderEmailDetailsDomain)senderDao.senderdetails();//selecting email details from dao
 		
 		 emailid=senderDom.getEmailid();
 		 password=senderDom.getPassword();
 		 host= senderDom.getHost();
 		 pop=senderDom.getPop();
 		   port=senderDom.getPort();
 		   link=senderDao.getClassRoomLink();//selecting class room link from dao
 		  System.out.println("selecting class room link from dao");
 		   if(sessionDao.isConflictTiming(sessionDomain))
 		   {
 			  System.out.println("in if");
 			   	String sessionStatus="Conflict with other Session Timing, Please change the time!";
    			request.setAttribute("FacultyFailure",sessionStatus);
    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/CreateSession.jsp");
    			rd.forward(request, response);
    			return;
 		   }
 		   
            if("".equalsIgnoreCase(groupid) && "".equalsIgnoreCase(individual)){ //this is the condition if user click on receipient type all
            	System.out.println("all in if else :"+all);
            	String resultAll=sessionDao.insertSessionAll(sessionDomain);
            	
         	  System.out.println("resultstatus:"+resultAll);
         	   if(resultAll.equalsIgnoreCase(resultAll)){
         		//  ArrayList<String> usersId=sessionDao.getUsersId(sessionDomain);
              	ArrayList<String> resultID=sessionDao.getAllEmailIDs(sessionDomain);
              	System.out.println("length" +resultID.size());
              	for(int i=0;i<resultID.size();i++){
              		group_member_email_array_list.add(resultID.get(i));
              	}
        		sendMailIntern();
         		  String sessionStatus="Session created successfully";
         		
      			request.setAttribute("FacultySuccessMail",sessionStatus);
      			RequestDispatcher rd=request.getRequestDispatcher("../JSP/CreateSession.jsp");
      			rd.forward(request, response);
      			return;
         	   }//end of  success message if
            else if("notvalid".equalsIgnoreCase(resultAll)){
         		  String sessionStatus="Failed for creating session ";
      			request.setAttribute("FacultyFailure",sessionStatus);
      			RequestDispatcher rd=request.getRequestDispatcher("../JSP/CreateSession.jsp");
      			rd.forward(request, response);
      			return;
         	   }// end of failure message else
            	
            }//end of if  sending session details to all
            else if("".equalsIgnoreCase(groupid)){ //this is for individual user
            	System.out.println("in if....");
            	String resultIndividual=sessionDao.insertSessionIndividual(sessionDomain);
            	System.out.println("induvidual values are:"+individual+ "resultIndividual:"+resultIndividual);
            	 
            		String studentid=sessionDomain.getIndividualId();
                 	String to=sessionDao.getEmailId(studentid);
                 	group_member_email_array_list.add(to);
                  	System.out.println("length" +group_member_email_array_list);
                 	String sessionStatus="Session created successfully";
         			request.setAttribute("FacultySuccessMail",sessionStatus);
                 	sendMailIntern(); 
                 	RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/CreateSession.jsp");
                 	requestDispatcher.forward(request, response);
                	System.out.println("request is transfer to jsp page");
                 	return;
                 
          			//response.sendRedirect("../JSP/CreateSession.jsp");
         			//response.sendRedirect("../JSP/CreateSession.jsp");
         			//response.sendRedirect("../JSP/CreateSession.jsp");
         			 //end of success message if
            	 //end of failure message else
            	
            }//end of if sending session details to individual
            else if("".equalsIgnoreCase(individual)){ // this is for when user select receipient type or group
            	String resultGroup=sessionDao.insertSessionGroupId(sessionDomain);
            	
            	System.out.println("group id in if else:"+groupid+ "resultGroup:"+resultGroup);
            	
            		String groupId=sessionDomain.getGroupValue();
                	String groupIDS[]=groupId.split("#");
                	ArrayList<String> to=sessionDao.getEmailGroupId(groupIDS);
                  	System.out.println("length" +to.size());
                	for(int i=0;i<to.size();i++){
                  		group_member_email_array_list.add(to.get(i));
                  	}
            		//sendMailIntern();
                	String subject="Session details";
                	String dateonly= SessionStartTime.substring(0,2)+"/"+SessionStartTime.substring(3,5)+"/"+SessionStartTime.substring(6,10);
        			//dateonly=date.substring(8,10)+"/"+date.substring(5,7)+"/"+date.substring(0,4);
        			String starttimeonly= SessionStartTime.substring(11);
        			String endtimeonly= SessionEndTime.substring(11);
                	String msg="Hello, <br/><br/> Your Session "+SessionName+ " has been created by the faculty "+username+". Your session starts on "+dateonly+ ", at "+starttimeonly+", and will end at "+endtimeonly+". The total duraion of the session will be "+request.getParameter("duration") +"."
        					+  "<br/><br/>Thanks and Regards" + "<br/>" +teachingSourceName+ "<br/><br/>"+"For any technical assistance please contact on the details provided below:" + "<br/>"
        					+ "<strong>Toll Free No:</strong> 1800123456"+"<br/>"+"<strong>SMS:</strong> 1234567891"+"<br/>"+"<strong>Email ID:</strong> <a href='mailto:technicalsupport@kompaceducation.com'>technicalsupport@kompaceducation.com</a>";
                	 InternetAddress[] address = new InternetAddress[group_member_email_array_list.size()];
                	 for(int i =0; i< group_member_email_array_list.size(); i++)
                	  {
                		 
                	      try {
                	    	  System.out.println("id="+group_member_email_array_list.get(i));
							//address[i] = new InternetAddress(group_member_email_array_list.get(i));
							Mailer.send(group_member_email_array_list.get(i), subject, msg);
						} catch (AddressException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                	      
                	  }
                	
             		
            		////System.out.println("valid session");
          		  String sessionStatus="Session created successfully";
       			request.setAttribute("FacultySuccessMail",sessionStatus);
       			RequestDispatcher rd=request.getRequestDispatcher("../JSP/CreateSession.jsp");
       			rd.forward(request, response);
       			return;
       			
       		
          	  // }//end of success message if
            	
            }//end of if sending session details to group
            
	       
   }
   
   public void sendMailIntern( )
	{
	   try
		 { 
	   InternetAddress[] address = new InternetAddress[group_member_email_array_list.size()];
 	  for(int i =0; i< group_member_email_array_list.size(); i++)
 	  {
 	      address[i] = new InternetAddress(group_member_email_array_list.get(i));
 	  }
	 System.out.println("sending email...");
		 Properties props= new Properties();
		 props.put("mail.smtp.auth",pop);
	     props.put("mail.smtp.starttls.enable",pop);
		 props.put("mail.smtp.host",host);
		 props.put("mail.smtp.port",port);
		 props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		 Session session= Session.getInstance(props, new javax.mail.Authenticator(){
			 protected PasswordAuthentication getPasswordAuthentication(){return new PasswordAuthentication(emailid,password);
			 																		
			 }
			 });
		 
			Message message= new MimeMessage(session);
			message.setFrom(new InternetAddress(emailid));
			message.setRecipients(Message.RecipientType.TO,address);
			message.setSubject("Session details");
			/*
			 * "Hello <Student>,

Your session <ID> has been created by the faculty <name>. Your session starts on <date>, at <time>, and will end at <time>. The total duration of the session will be <x min>.

Thanks and Regards
ABC

For any technical assistance please contact on the details provided below:
Toll Free No: 1800123456
SMS: 
Email ID: techicalsupport@kompaceducation.com"

			 */	
			String dateonly= SessionStartTime.substring(0,2)+"/"+SessionStartTime.substring(3,5)+"/"+SessionStartTime.substring(6,10);
			//dateonly=date.substring(8,10)+"/"+date.substring(5,7)+"/"+date.substring(0,4);
			String starttimeonly= SessionStartTime.substring(11);
			String endtimeonly= SessionEndTime.substring(11);
			String msg="Hello, <br/><br/> Your Session "+SessionName+ " has been created by the faculty "+username+". Your session starts on "+dateonly+ ", at "+starttimeonly+", and will end at "+endtimeonly+". The total duraion of the session will be "+request.getParameter("duration") +"."
					+  "<br/><br/>Thanks and Regards" + "<br/>" +teachingSourceName+ "<br/><br/>"+"For any technical assistance please contact on the details provided below:" + "<br/>"
					+ "<strong>Toll Free No:</strong> 1800123456"+"<br/>"+"<strong>SMS:</strong> 1234567891"+"<br/>"+"<strong>Email ID:</strong> <a href='mailto:technicalsupport@kompaceducation.com'>technicalsupport@kompaceducation.com</a>";
			message.setContent(msg, "text/html; charset=utf-8");
			//message.setText("SessionStartTime:"+SessionStartTime);
			//message.setText("SessionEndTime:"+SessionEndTime);
			//message.setText("Duration :"+hours);
			//System.out.println();
			 Transport.send(message);
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
