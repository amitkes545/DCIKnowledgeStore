package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.kds.KODE_DEV.dao.AssessmentDao;
import com.kds.KODE_DEV.dao.CreateSessionDao;
import com.kds.KODE_DEV.dao.ParameterDao;
import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;
import com.kds.KODE_DEV.domain.SessionDomain;

@SuppressWarnings("serial")
public class ManageSessionServlet extends CommonService{
	
	String userid="";
	String SessionStartTime="";
	String SessionEndTime="";
	String hours="";
	String SessionName="",duration="",cost="",category="",newRecipientType="",id="",newStatus="";
	String emailid="";
	String password="";
	String host="" ;
	String start_date,end_date="";
	String pop="";
	int    port;
	 String facultyid="",facultyName="";
	String oldSessionID="",recipientType="",oldSessionName="",oldCategory="",oldStartTime="",oldEndTime="",oldDuartion="",oldResipientType="",oldCost="",oldStatus="";
	SessionDomain sdomain=null;
	SessionDomain old_sdomain=null;
	 ArrayList<SessionDomain> arrayList=new ArrayList<SessionDomain>();
	ArrayList<String> group_member_email_array_list=new ArrayList<String>();
	ArrayList<SessionDomain> old_session_array_list=new ArrayList<SessionDomain>();
	String teachingSourceName;
	public void run() throws ServletException,IOException{
		group_member_email_array_list.clear();
		session=request.getSession(true);
		teachingSourceName=(String)session.getAttribute("organizationName");
		//response.setContentType("text/html");  
		//retriving session old details
		//ArrayList<SessionDomain> oldDetails=new ArrayList<SessionDomain>();
		ArrayList<SessionDomain> oldDetails=(ArrayList<SessionDomain>)session.getAttribute("oldSessionDetails");
		//CreateSessionDao createSessionDao= new CreateSessionDao();
		 AssessmentDao assessmentDao=new AssessmentDao();
		 CreateSessionDao sessiondao=new CreateSessionDao();
		 SessionDomain sessionDomain=new SessionDomain();
		
		 facultyid=(String)session.getAttribute("userid");
		 facultyName=(String)session.getAttribute("username");
			String organizationId=(String)session.getAttribute("orgid");
			sessionDomain.setOrgId(organizationId);
			sessionDomain.setFacultyId(facultyid);
			SendEmailDao senderDao =new SendEmailDao();
			//session.removeAttribute("MsgValue");
			
			ParameterDao numDao=new ParameterDao();
			   String paramvalue=numDao.GetParameterValue("No_Of_Participant");
			   
			   int nump = Integer.parseInt(paramvalue);
			   System.out.println("nump="+nump);
			
			SenderEmailDetailsDomain senderDom =(SenderEmailDetailsDomain)senderDao.senderdetails();
			
			 emailid=senderDom.getEmailid();
			 password=senderDom.getPassword();
			 host= senderDom.getHost();
			 pop=senderDom.getPop();
			   port=senderDom.getPort();
		 String groupvalue="";
		String[] checkboxGroup = request.getParameterValues("checkboxGroup");
		 if(checkboxGroup.length>0){
		 //System.out.println("checked values:"+checkboxGroup.length);
		 
			        for(int i=0; i<checkboxGroup.length; i++) {
			        	sdomain=new SessionDomain();
	            ////System.out.println(values[i]);
	            //String id=request.getParameter(values[0]);
			        	
			        	 String oldFormat = "dd/MM/yyyy HH:mm";
			            String newFormat = "yyyy/MM/dd HH:mm";

			            SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
			            SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);


			            try {
			                System.out.println(sdf2.format(sdf1.parse(request.getParameter("startTime"+checkboxGroup[i]))));
			                 start_date=sdf2.format(sdf1.parse(request.getParameter("startTime"+checkboxGroup[i])));
			                 end_date=sdf2.format(sdf1.parse(request.getParameter("endTime"+checkboxGroup[i])));
			                

			            } catch (ParseException e) {
			                // TODO Auto-generated catch block
			                e.printStackTrace();
			            }
	            
	            sdomain.setSessionId(request.getParameter("sessionId"+checkboxGroup[i]));
	            sdomain.setSessionName(request.getParameter("sessionName"+checkboxGroup[i]));
	            sdomain.setCategory(request.getParameter("category"+checkboxGroup[i]));
	            sdomain.setSessionStartTime(start_date);//request.getParameter("startTime"+checkboxGroup[i]));
	            sdomain.setSessionEndTime(end_date);//request.getParameter("endTime"+checkboxGroup[i]));
	            sdomain.setDuration(request.getParameter("duration"+checkboxGroup[i]));
	            sdomain.setCostOfSession(request.getParameter("costOfsession"+checkboxGroup[i]));
	            sdomain.setRecipientType(request.getParameter("recipientType"+checkboxGroup[i]));
	            //System.out.println("id value:"+request.getParameter("sessionId"+checkboxGroup[i]));
	            System.out.println("recipient type::"+request.getParameter("recipientType"+checkboxGroup[i]));
	            sdomain.setStatus(request.getParameter("status"+checkboxGroup[i]));
	           // String groupName=request.getParameter("recipientType"+checkboxGroup[i]);
	            /*//selecting group values based on group name from dao
	            if(groupName!=null){
	            	
	         	   
	         			 groupvalue=assessmentDao.getGroupValues(groupName,facultyid,organizationId);
	         			//System.out.println("group values:"+groupName);
	         		
	            }
	            //setting group value to domain
	            sdomain.setRecipientType(groupvalue);*/
	            if(request.getParameter("recipientType"+checkboxGroup[i]).equalsIgnoreCase("individual")){
	            	//System.out.println("individual id::"+request.getParameter("student_id"));
	            	//setting individual id to domain
	            	sdomain.setRecipientType(request.getParameter("student_id"));
	            }else if(request.getParameter("recipientType"+checkboxGroup[i]).equalsIgnoreCase("group")){
	            	//System.out.println("group id::"+request.getParameter("group_id"));
	            	String groupName=request.getParameter("group_id");
	            	//selecting group values based on group name from dao
		            if(groupName!=null){
		         			 groupvalue=assessmentDao.getGroupValues(groupName,facultyid,organizationId);
		         			//System.out.println("group values:"+groupName);
		         		
		            }
		            //setting group value to domain
		            sdomain.setRecipientType(groupvalue);
		            
	            }else if(request.getParameter("recipientType"+checkboxGroup[i]).equalsIgnoreCase("all")){
	            	//System.out.println("recipientType all");
	            	
	            	int countstudent=assessmentDao.getStudent(organizationId);
	            	System.out.println("=countstudent=="+countstudent);
	            	if(countstudent>15)
	            	{
	            		String sessionStatus="Session can not be assigned to a group of more than "+nump+" participants!";
	        			request.setAttribute("FacultyFailure",sessionStatus);
	        				  			//request.setAttribute("sessionDetails", arrayList);
	        	  			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/PostPoneSession.jsp");
	        	  			requestDispatcher.forward(request, response);

	            	System.out.println("countstudent "+countstudent);
	            	return;
	            	
	            	}
	            	else{
	            	 sdomain.setRecipientType("recipientType"+checkboxGroup[i]);
	            	 }
	            }
	            arrayList.add(sdomain);
	         // added by prity for conflict         
	            sdomain.setFacultyId(facultyid);     
	            sdomain.setOrgId(organizationId);    
	            CreateSessionDao sessionDao=new CreateSessionDao();      
	            if(sessionDao.isConflictTimingModify(sdomain))      
	            {     
	            	System.out.println("in if");      
	            String sessionStatus="Conflict with other Session Timing, Please change the time!"; 
	            request.setAttribute("FacultyFailure",sessionStatus);    
	            RequestDispatcher rd=request.getRequestDispatcher("../JSP/PostPoneSession.jsp");   
	            rd.forward(request, response);    
	            return;     
	            }       //end here
	           
	} //number of checkboxes user slected
			  //      Iterator<SessionDomain> iterator =arrayList.iterator();
				/*	while(iterator.hasNext())
						{
						SessionDomain domain=iterator.next();
						//System.out.println("domain value for id::"+domain.getSessionId());
						//System.out.println("domain value for recipient::"+domain.getRecipientType());
						}*/
					System.out.println("arrayList="+arrayList);
					//get all selected old values
				//	for(int oldsessioncounter=0; oldsessioncounter <arrayList.size();oldsessioncounter++)
				//	{
				//		old_sdomain= new SessionDomain();
					//	old_session_array_list=sessiondao.ret
						
				//	}
					String status=sessiondao.updateSessionDetails(arrayList);
			        
			        //System.out.println("status in service:"+status);
			        if(status.equalsIgnoreCase("success")){
			        	
			        	
			        	
			        //retriving session new details
			        	Iterator<SessionDomain> it=arrayList.iterator();
			        	Iterator<SessionDomain> old_session_iterator =oldDetails.iterator();
			        while(it.hasNext()){
			        	
			        	SessionDomain domain=it.next();
			        	id=domain.getSessionId();
			        	
			        	if(oldDetails!=null){ //to get the value of old session details
				        	
				    		 
				    			while(old_session_iterator.hasNext())
				    				{
				    				old_sdomain=old_session_iterator.next();
				    				
				    				oldSessionID=old_sdomain.getSessionId();
				    						if(!oldSessionID.equals(id)){
				    							continue;
				    						}
				    				oldSessionName=old_sdomain.getSessionName();
				    				//System.out.println("oldSessionName::"+oldSessionName);
				    				oldCategory=old_sdomain.getCategory();
				    				oldStartTime=old_sdomain.getSessionStartTime();
				    				oldEndTime=old_sdomain.getSessionEndTime();
				    				oldDuartion=old_sdomain.getDuration();
				    				oldResipientType=old_sdomain.getRecipientType();
				    				oldCost=old_sdomain.getCostOfSession();
				    				oldStatus=old_sdomain.getStatus();
				    				break;
				    				}
				        	}
			        	
			        	System.out.println("id:"+id);
			        	SessionName=domain.getSessionName();
			        	SessionStartTime=domain.getSessionStartTime();
			        	SessionEndTime=domain.getSessionEndTime();
			        	hours=domain.getDuration();
			        	cost=domain.getCostOfSession();
			        	newStatus=domain.getStatus();
			        	category=domain.getCategory();
			        	newRecipientType=domain.getRecipientType();
			        	if(newRecipientType.contains("#")){
			        		String groupValue=domain.getRecipientType();
			        		
			        		 newRecipientType=sessiondao.getGroupName(facultyid,organizationId,groupValue);
			        	System.out.println("recipient type group::"+newRecipientType);
			        	}
			        	String groupid=domain.getRecipientType();
			        	System.out.println("groupid="+groupid);
			        	 String group_value=assessmentDao.getGroupValues(groupid,facultyid,organizationId);
			        		System.out.println("group_value="+group_value);
			        	System.out.println("receipient type::"+groupid+" new status::"+status);
			        	if(domain.getRecipientType().equalsIgnoreCase("all")){
			        		//System.out.println("recipient type in all::");
			        //		ArrayList<String> usersId=sessiondao.getAllUsersId(sessionDomain);
			              	ArrayList<SessionDomain> resultID=sessiondao.getAllEmailIdUserName(sessionDomain);
			                Iterator<SessionDomain> it2=resultID.iterator();
					        while(it2.hasNext()){
					        	
					        	
					        	SessionDomain domain2=it2.next();
					        //	String TO=domain2.getEmail();
					        	group_member_email_array_list.add(domain2.getEmail());
					        	//studentName=domain2.getStudentName();
					        	 
					        }
					        System.out.println("length "+group_member_email_array_list.size());
					        sendMailSession();
			              	/*for(String TO:resultID){
			              		sendMailSession(host,TO); 
			              	}*/
			        	}else if(domain.getRecipientType().contains("#") || group_value.contains("#") ){
			        		System.out.println("recipient type in group::");
			        		String groupId=group_value;//domain.getRecipientType();
			        		System.out.println("groupId="+groupId);
		                	String groupIDS[]=groupId.split("#");
		                	System.out.println("grgroupID SoupId=="+groupIDS);
		                	ArrayList<SessionDomain> to=sessiondao.getEmailGroupIdUserName(groupIDS);
		                	Iterator<SessionDomain> it3=to.iterator();
					        while(it3.hasNext()){
					        	
					        	
					        	SessionDomain domain2=it3.next();
					        	group_member_email_array_list.add(domain2.getEmail());
					        //	String TO=domain2.getEmail();
					        //	studentName=domain2.getStudentName();
					        	//sendMailSession(host,TO); 
					        }
					        System.out.println("length "+group_member_email_array_list.size());
					        sendMailSession();
		                	/*for(String TO:to){
		                		//System.out.println("user id are:"+TO);
		                		sendMailSession(host,TO); 
		                		
		                	}*/
			        	} else {
			        		
			        		//System.out.println("single user::");
			        		String studentid=group_value;//domain.getRecipientType();
			        		System.out.println("individual id is::"+studentid);
		                 	ArrayList<SessionDomain> to=sessiondao.getEmailIdUserName(studentid);
		                 	Iterator<SessionDomain> it4=to.iterator();
					        while(it4.hasNext()){
					        	
					        	
					        	SessionDomain domain2=it4.next();
					        	group_member_email_array_list.add(domain2.getEmail());
					        	String TO=domain2.getEmail();
					        	String studentName=domain2.getStudentName();
					        	try {
									Mailer.send(host,TO,"your session got canceled");
								} catch (AddressException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (MessagingException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} 
					        }
					        System.out.println("length "+group_member_email_array_list.size());
					      //  sendMailSession();
			        	}
			        	
			        }//end of while for all selected session
			        String sessionStatus="Session modified successfully";
		  			request.setAttribute("FacultySuccess",sessionStatus);
		  			request.setAttribute("sessionDetails", arrayList);
		  			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/PostPoneSession.jsp");
		  			requestDispatcher.forward(request, response);
			        }else if(status.equalsIgnoreCase("failure")){
			        	String sessionStatus="Failure for updating session values";
			  			request.setAttribute("FacultyFailure",sessionStatus);
			  			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/PostPoneSession.jsp");
			  			requestDispatcher.forward(request, response);
			        }
				
			        
		 }
	}
	 public void sendMailSession()
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
				message.setSubject("Session modification");
				/*
				 * "Hello <Student>,

Your session <ID> has been modified by the faculty <name>. Your session starts on <date>, at <time>, and will end at <time>. The total duration of the session will be <x min>.

TABLE TO BE INSERTED

Thanks and Regards
ABC

For any technical assistance please contact on the details provided below:
Toll Free No: 1800123456
SMS: 
Email ID: techicalsupport@kompaceducation.comScreen shot attached"

				 */
				String old_dateonly= oldStartTime.substring(8,10)+"/"+oldStartTime.substring(5,7)+"/"+oldStartTime.substring(0,4);
				String old_starttimeonly= oldStartTime.substring(11);
				String old_endtimeonly= oldEndTime.substring(11);
			
				String new_dateonly= SessionStartTime.substring(8,10)+"/"+SessionStartTime.substring(5,7)+"/"+SessionStartTime.substring(0,4);
					String new_starttimeonly= SessionStartTime.substring(11);
					String new_endtimeonly= SessionEndTime.substring(11);
					
				 String htmlText ="Hello, <br><br>"+"Your session "+SessionName+" has been modified by the faculty "+facultyName+". Your session starts on "+new_dateonly+", at "+new_starttimeonly+", and will end at "+new_endtimeonly+". The total duration of the session will be "+sdomain.getDuration()+".<br><br>"+"<table border="+1+" cellspacing="+0+" cellpadding="+5+"><tr><td></td><td><strong>ID</strong></td><td><strong>Name</strong></td><td><strong>Course</strong></td><td><strong>Start Date</strong></td><td><strong>Start Time</strong></td><td><strong>End Time</strong></td>"
							+"<td><strong>Duration</strong></td><td><strong>Recipient Type</strong></td><td><strong>Status</strong></td></tr>"+"<tr><td><strong>Old</strong></td>"
							+"<td>"+id+"</td><td>"+oldSessionName+"</td><td>"+oldCategory+"</td><td>"+old_dateonly+"</td><td>"+old_starttimeonly+"</td><td>"+old_endtimeonly+"</td><td>"+oldDuartion+"</td><td>"+oldResipientType+"</td><td>"+oldStatus+"</td></tr>"+"<tr><td><strong>New</strong></td><td>"+id+"</td><td>"+SessionName+"</td><td>"+category+"</td><td>"+new_dateonly+
							"</td><td>"+new_starttimeonly+"</td><td>"+new_endtimeonly+"</td><td>"+hours+"</td><td>"+newRecipientType+"</td><td>"+newStatus+"</td></tr>"+"</table>"+ "<br/><br/>" + "Thanks and Regards" + "<br/>" + teachingSourceName +"<br/><br/>"+"For any technical assistance please contact on the details provided below:" + "<br/>"
							+ "<strong>Toll Free No:</strong> 1800123456"+"<br/>"+"<strong>SMS:</strong> 1234567891"+"<br/>"+"<strong>Email ID:</strong> <a href='mailto:technicalsupport@kompaceducation.com'>technicalsupport@kompaceducation.com</a>";
						    
                       
				 message.setContent(htmlText, "text/html");
				/*message.setText("Hi"+studentName+"<br/>"+facultyName+" updated your  session with following details::<br/>"+"<table><tr><td>ID</td><td>Name</td><td><td>Category</td><td>Start Time</td>"
				+"End Time</td><td>Duration</td><td>Recipient Type</td><td>Cost</td><td>Status</td></tr>"+"<tr><td>Old</td><td>"+id+"</td><td>"+oldSessionName+"</td><td>"+oldCategory+"</td><td>"+oldStartTime+
				"</td><td>"+oldEndTime+"</td><td>"+oldDuartion+"</td><td>"+oldResipientType+"</td><td>"+oldCost+"</td><td>"+oldStatus+"</td></tr>"+"<tr><td>New</td><td>"+id+"</td><td>"+SessionName+"</td><td>"+category+"</td><td>"+SessionStartTime+
				"</td><td>"+SessionEndTime+"</td><td>"+hours+"</td><td>"+newRecipientType+"</td><td>"+cost+"</td><td>"+status+"</td><td></tr>"+"</table>");*/
				/*message.setText("SessionName:"+SessionName+ "<br/>SessionStartTime:"+SessionStartTime+ "<br/>SessionEndTime:"+SessionEndTime+ "<br/> Duration:"+hours);*/
				//message.setText("SessionStartTime:"+SessionStartTime);
				//message.setText("SessionEndTime:"+SessionEndTime);
				//message.setText("Duration :"+hours);
				//LOGGER.info();
				 Transport.send(message);
				 //System.out.println("send successfully");
				//LOGGER.info("send successfully");
				/*try {
					response.sendRedirect("../JSP/success.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}*/
			 	}
			 catch(MessagingException e)
			 {
				 //throw new RuntimeException(e);
			     String sessionStatus="Session modified successfully";
		  			request.setAttribute("FacultySuccess",sessionStatus);
		  			request.setAttribute("sessionDetails", arrayList);
		  			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/PostPoneSession.jsp");
		  			try {
						requestDispatcher.forward(request, response);
					} catch (ServletException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			 }
	    }	
}

