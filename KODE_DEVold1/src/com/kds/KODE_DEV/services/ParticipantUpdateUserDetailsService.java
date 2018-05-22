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

import org.apache.commons.fileupload.FileUploadException;

import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.dao.StudentGroupNameDao;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

public class ParticipantUpdateUserDetailsService extends CommonService {

	/**
	 *@author jitendra
	 */
	private static final long serialVersionUID = 1L;
	 String old_userid=null,
			old_contact=null,
			old_dob=null,
			old_name=null,
			old_email=null,
			old_address=null,
			old_status=null;
	 String new_userid=null,
			new_contact=null,
			new_dob=null,
			new_name=null, 
			new_email=null,
			new_address=null,
			new_status=null,
			to=null;
	 		String emailid=null;
	 		String password=null;

	@Override
	public void run() throws ServletException, IOException,FileUploadException, Exception {
		session=request.getSession(true);
		 ArrayList<UsersInfoDomain> arrayList=new ArrayList<UsersInfoDomain>();
		 StudentGroupNameDao stugroupdao=new StudentGroupNameDao();
		 ArrayList<UsersInfoDomain> old_userDetails =(ArrayList<UsersInfoDomain>)session.getAttribute("studentdetail");
			String[] checkboxGroup = request.getParameterValues("checkboxGroup");
			 if(checkboxGroup.length>0){
			 //System.out.println("checked values:"+checkboxGroup.length);
			 
				        for(int i=0; i<checkboxGroup.length; i++) {
				        	UsersInfoDomain userinfodom= new UsersInfoDomain();
		            ////System.out.println(values[i]);
		            //String id=request.getParameter(values[0]);
				        	userinfodom.setUserId(request.getParameter("userid"+checkboxGroup[i]));
				        	userinfodom.setUserName(request.getParameter("name"+checkboxGroup[i]));
				        	userinfodom.setAddress(request.getParameter("address"+checkboxGroup[i]));
				        	userinfodom.setContactno(request.getParameter("contact"+checkboxGroup[i]));
				        	userinfodom.setDateofbirth(request.getParameter("dob"+checkboxGroup[i]));
				        	userinfodom.setEmail(request.getParameter("email"+checkboxGroup[i]));
				        	userinfodom.setStatus(request.getParameter("status"+checkboxGroup[i]));
				        	
				        	//System.out.println("values=== "+request.getParameter("userid"+checkboxGroup[i])+" "
				        			// + " "+request.getParameter("name"+checkboxGroup[i])+" "+request.getParameter("address"+checkboxGroup[i])+""
				        				// 	+ " "+request.getParameter("contact"+checkboxGroup[i])+" "+request.getParameter("dob"+checkboxGroup[i])+
				        					// " "+request.getParameter("email"+checkboxGroup[i])+" "+request.getParameter("status"+checkboxGroup[i]));
				        	arrayList.add(userinfodom);	
				        }
				        
	}
			/* Iterator<UsersInfoDomain> it= arrayList.iterator();
			 while(it.hasNext()){
				 UsersInfoDomain udom=it.next();
				 //System.out.println("after iterator in service== "+udom.getUserId());
			 }*/
		        //System.out.println("arrayList size="+arrayList.size());
		        String status=stugroupdao.updateUserDetails(arrayList);
		      //  System.out.println(status);
		        if(status.equalsIgnoreCase("success")){
		        	Iterator<UsersInfoDomain> it= arrayList.iterator();
					 while(it.hasNext()){
						 UsersInfoDomain udom=it.next();
						 new_userid=udom.getUserId();
						 new_contact=udom.getContactno();
						 new_dob=udom.getDateofbirth();
						 new_name=udom.getUserName();
						 new_email=udom.getEmail();
						 new_address=udom.getAddress();
						 new_status=udom.getStatus();
					if(old_userDetails!=null){
						Iterator<UsersInfoDomain> itr_old_detail= old_userDetails.iterator();
						while(itr_old_detail.hasNext()){
							UsersInfoDomain old_userdom=itr_old_detail.next();
								old_userid=old_userdom.getUserId();
								old_contact=old_userdom.getContactno();
								old_dob=old_userdom.getDateofbirth();
								old_name=old_userdom.getUserName();
								old_email=old_userdom.getEmail();
								old_address=old_userdom.getAddress();
								old_status=old_userdom.getStatus();
						if(old_userid.equalsIgnoreCase(new_userid)){
							to=new_email;
							sendMailToMdifiedUser(to);
						}
					}
					}
					 }
					 String userid=request.getParameter("ViewID");
					 //request.setAttribute("ViewID", userid);
			   			String Tabmsg="tab1";
			   			String Studentymsg="Profile updated Successfully!";
						request.setAttribute("responseMsg",Studentymsg);
						RequestDispatcher rd=request.getRequestDispatcher("/ControllerServlet/ParticipantModifyUserDetailsService?idvalue="+userid+"&tabvalue="+Tabmsg);
						rd.forward(request, response);
					 
		        	
		        }else{
		        	String Tabmsg="tab1";
		        	String responseMsg="User detalis are not updated. Please try Again later!";
		        	request.setAttribute("responseMsg", responseMsg);
		  			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/ParticipantModifyAllInTabView.jsp?tabvalue="+Tabmsg);
		  			requestDispatcher.forward(request, response);	
		        }
			
	}
	public void sendMailToMdifiedUser(String to){

		//System.out.println("in send mail method========== "+to);
		SendEmailDao senderDao =new SendEmailDao();
		SenderEmailDetailsDomain senderDom =(SenderEmailDetailsDomain)senderDao.senderdetails();
		 emailid=senderDom.getEmailid();
		 password=senderDom.getPassword();
		String host= senderDom.getHost();
		String pop=senderDom.getPop();
		int port=senderDom.getPort();
		//String  link=senderDao.getKnowStoreLink();//selecting web room link from dao
		//String  classRoomLink=senderDao.getClassRoomLink();
   		 Properties props= new Properties();
   		 props.put("mail.smtp.auth",pop); 
   	     props.put("mail.smtp.starttls.enable",pop);
   		 props.put("mail.smtp.host",host);
   		 props.put("mail.smtp.port",port);
   		 props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
   		 Session session= Session.getInstance(props, new javax.mail.Authenticator(){
   			 protected PasswordAuthentication getPasswordAuthentication(){return new PasswordAuthentication(emailid,password);
   			 																		} });
   		 try
   		 { 
			
   			Message message= new MimeMessage(session);
   			message.setFrom(new InternetAddress(emailid));
   			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
   			//message.setSubject("Testing");
   			//message.setText("UserID:"+emailid+ "Password:"+password);
   			message.setSubject("User Profile Updated"); // Now set the actual message
   			String htmlText ="Hi "+new_name+","+"<br>Your Profile has been Updated:<br>"
   			+"Here are the details:<br/><br/>"+"<table border="+1+" cellspacing="+0+" cellpadding="+5+">"
   					+"<tr><td>Details </td><td>UserID</td><td>Name</td><td>Email</td><td>Mobile No.</td><td>Date of Birth</td><td>Address</td>"
					+"<tr><td>Old </td><td>"+old_userid+"</td><td>"+old_name+"</td><td>"+old_email+"</td><td>"+old_contact+"</td><td>"+old_dob+"</td><td>"+old_address+"</td></tr>"
   					+"<tr><td>New </td><td>"+new_userid+"</td><td>"+new_name+"</td><td>"+new_email+"</td><td>"+new_contact+"</td><td>"+new_dob+"</td><td>"+new_address+"</td></tr>"+"</table>"
   					+"<br/><br/>This is computer generated mail no need to reply.<br/><br/>--"
   					+"<br/>Thanks & Regards"+"<br/>"+"KDS Team"+"<br/>"+"Kompac Digital Systems Pvt. Ltd."+"<br/>" +"Website : www.kompacdigit.com";
         //System.out.println("htmlText== "+htmlText);
   			message.setContent(htmlText, "text/html"); 
   			 Transport.send(message);
   			//LOGGER.info("send successfully");
/*   			String Tabmsg="tab1";
   			String Studentymsg="Profile updated Successfully!";
			request.setAttribute("responseMsg",Studentymsg);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ParticipantModifyAllInTabView.jsp?tabvalue="+Tabmsg);
			rd.forward(request, response);
*/			}
   		 catch(MessagingException e)
   		 {
   			String Tabmsg="tab1";
   			String Studentymsg="Please check your internet connection and try Again!";
			request.setAttribute("responseMsg",Studentymsg);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ParticipantModifyAllInTabView.jsp?tabvalue="+Tabmsg);
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
   			 throw new RuntimeException(e);
   		 }catch(Exception e){
   			String Tabmsg="tab1";
   			String Studentymsg="Please Again Later!";
			request.setAttribute("responseMsg",Studentymsg);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ParticipantModifyAllInTabView.jsp?tabvalue="+Tabmsg);
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
   		 }      
	}
}
