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
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.ParameterDao;
import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.dao.SessionDao;
import com.kds.KODE_DEV.dao.StudentGroupNameDao;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

public class ParticipantUpdateGroupDetailsService extends CommonService {

	/**
	 * @author jitendra
	 */
	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(CollaborateServlet.class);
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
	String groupid=null;
	String orgid=null;
	String newgroupname=null;
	String teachingSourceName;
	ArrayList<String> group_member_email_array_list=new ArrayList<String>();
	@Override
	public void run() throws ServletException, IOException, FileUploadException, Exception {
		session=request.getSession(true);
	//	stuidbuffer.append("");
		teachingSourceName=(String)session.getAttribute("organizationName");
		orgid=(String)session.getAttribute("orgid");
		groupid=request.getParameter("ViewID");
		newgroupname=request.getParameter("newgroupname");
		if(newgroupname==null || newgroupname.trim().equals(""))
		{
			newgroupname=groupid+"_group";
		}
		else
		{
			newgroupname=newgroupname.trim()+"_group";
		}
		String action=request.getParameter("action");
		//System.out.println("action="+action);
		if(action.equals("update")) {
			String status=updateGroupMember();
			if(status.equals("success")){
	        	String Tabmsg="tab2";
	        	String responseMsg="Profile updated successfully!";
	        	request.setAttribute("responseMsg", responseMsg);
	  			RequestDispatcher requestDispatcher=request.getRequestDispatcher("/ControllerServlet/ParticipantModifyUserGroupDetailsService?idvalue="+newgroupname+"&tabvalue="+Tabmsg);
	  			requestDispatcher.forward(request, response);	
				}
			else{
	        	String Tabmsg="tab2";
	        	String responseMsg="User detalis are not updated. Please try Again later!";
	        	request.setAttribute("Studentymsg", responseMsg);
	  			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/ParticipantModifyAllInTabView.jsp?tabvalue="+Tabmsg);
	  			requestDispatcher.forward(request, response);	
	        	}   	 
			}
		else if(action.equals("save")) {
			///Allow only if number of members are in limit allowed 
			ParameterDao numDao=new ParameterDao();  
			String paramvalue=numDao.GetParameterValue("No_Of_Participant");   
			int nump = Integer.parseInt(paramvalue);   
			String[] checkboxGroup = request.getParameterValues("checkboxGroup");   
			if(checkboxGroup.length > nump){     
				LOGGER.info("A group can not contain more than "+nump+" students!");  
				String Tabmsg="tab2";      
				String responseMsg="A group can not contain more than "+nump+" students!";   
				request.setAttribute("responseErrMsg", responseMsg);   
				RequestDispatcher requestDispatcher=request.getRequestDispatcher("/ControllerServlet/ParticipantModifyUserGroupDetailsService?idvalue="+newgroupname+"&tabvalue="+Tabmsg);   
			
			requestDispatcher.forward(request, response);   
			}else{
		String status=saveGroup();
		//System.out.println("status="+status);
		if(status.equals("success")){
        	String Tabmsg="tab2";
        	String responseMsg="Group updated successfully!";
        	System.out.println("Update Successfully in PGDS"+responseMsg);
        	request.setAttribute("responseMsg", responseMsg);
  			RequestDispatcher requestDispatcher=request.getRequestDispatcher("/ControllerServlet/ParticipantModifyUserGroupDetailsService?idvalue="+newgroupname+"&tabvalue="+Tabmsg);
  			requestDispatcher.forward(request, response);	
			}
		else{
        	String Tabmsg="tab2";
        	String responseMsg="Group detalis not updated. Please try Again later!";
        	request.setAttribute("responseMsg", responseMsg);
  			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/ParticipantModifyAllInTabView.jsp?tabvalue="+Tabmsg);
  			requestDispatcher.forward(request, response);	
        	}   	 
		}
		}
		else if(action.equals("delete")) {
			String status=deleteGroup();
			//System.out.println("status="+status);
			if(status.equals("success")){
	        	String Tabmsg="tab2";
	        	String responseMsg="Group deleted successfully!";
	        	//System.out.println(responseMsg);
	        	request.setAttribute("responseMsg", responseMsg);
	  			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/ParticipantModifyAllInTabView.jsp?tabvalue="+Tabmsg);
	  			requestDispatcher.forward(request, response);	
				}
			else{
	        	String Tabmsg="tab2";
	        	String responseMsg="Group detalis not deleted. Please try Again";
	        	request.setAttribute("responseMsg", responseMsg);
	  			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/ParticipantModifyAllInTabView.jsp?tabvalue="+Tabmsg);
	  			requestDispatcher.forward(request, response);	
	        	}   	 
			}
		else if(action.equals("add")) addGroupMember();
	}
	private String deleteGroup(){
		 StudentGroupNameDao stugroupdao=new StudentGroupNameDao();
		 //System.out.println("getting chkboxgroup");

		        String status=stugroupdao.deleteGroup(groupid,orgid);
		        //System.out.println("returnstaus== "+status);
		        if(status.equalsIgnoreCase("success")){
		        	return("success");
		        }else{
		        	return("failed");
		        }   	 
		
		
	}
	private String saveGroup(){
		StringBuffer stuidbuffer = new StringBuffer();
		//stuidbuffer=new StringBuffer("");
		SessionDao sessionDao=new SessionDao();
		ArrayList<UsersInfoDomain> to;
		ArrayList<UsersInfoDomain> old_userDetails =(ArrayList<UsersInfoDomain>)session.getAttribute("studentdetail1");
		UsersInfoDomain userinfodom=null;
		 ArrayList<UsersInfoDomain> arrayList=new ArrayList<UsersInfoDomain>();
		 StudentGroupNameDao stugroupdao=new StudentGroupNameDao();
		 //System.out.println("getting chkboxgroup");
		String[] checkboxGroup = request.getParameterValues("checkboxGroup");
		
		System.out.println("newgroupname= "+newgroupname);
		//System.out.println("checked values for delete:"+checkboxGroup.length);
			 if(checkboxGroup!=null && checkboxGroup.length>0){
				        for(int i=0; i<checkboxGroup.length; i++) {
				        	userinfodom= new UsersInfoDomain();
				        	userinfodom.setUserId(request.getParameter("userid"+checkboxGroup[i]));
				        	stuidbuffer.append(request.getParameter("userid"+checkboxGroup[i])+", ");
				        //	System.out.println("userid in list="+request.getParameter("userid"+checkboxGroup[i]));
				        	//System.out.println("string buffer in each cycle "+stuidbuffer);
				        	userinfodom.setUserName(request.getParameter("name"+checkboxGroup[i]));
				        	userinfodom.setAddress(request.getParameter("address"+checkboxGroup[i]));
				        	userinfodom.setContactno(request.getParameter("contact"+checkboxGroup[i]));
				        	userinfodom.setDateofbirth(request.getParameter("dob"+checkboxGroup[i]));
				        	userinfodom.setEmail(request.getParameter("email"+checkboxGroup[i]));
				        //	System.out.println("request.getParameter(email+checkboxGroup[i]) "+ request.getParameter("email"+checkboxGroup[i]));
				        	userinfodom.setStatus(request.getParameter("status"+checkboxGroup[i]));
				        	userinfodom.setOrgId(orgid);
				        	arrayList.add(userinfodom);	
				        }
				    }
		        String status=stugroupdao.updateGroup(arrayList,groupid,orgid,newgroupname);
		        
		        System.out.println("returnstaus== "+status);
		        if(status.equalsIgnoreCase("success")){
		        	to=sessionDao.getAllStudentEmailIdForUpdate(arrayList,orgid);
	    			//System.out.println("student details:"+to.size());
	    			for(UsersInfoDomain TO:to){
	    				//sendMailGroup(host,TO,stuidbuffer);
	    		//		System.out.println("stuidbuffer "+stuidbuffer.substring(0,stuidbuffer.length()-2));
	    				//System.out.println("user ids in service:"+TO.getUserId());
	    				group_member_email_array_list.add(TO.getEmail());
	    			//	sendMailToMdifiedUser(TO.getEmail(),stuidbuffer);
	    			}
	    			sendMailToMdifiedUser(stuidbuffer);
//		        	Iterator<UsersInfoDomain> it= arrayList.iterator();
//					 while(it.hasNext()){
//						 UsersInfoDomain udom=it.next();
//						 new_userid=udom.getUserId();
//						 new_contact=udom.getContactno();
//						 new_dob=udom.getDateofbirth();
//						 new_name=udom.getUserName();
//						 new_email=udom.getEmail();
//						 new_address=udom.getAddress();
//						 new_status=udom.getStatus();
//						 System.out.println("new_email " + new_email);
//						// stuidbuffer= stuidbuffer.concat(new_userid+", ");
//					if(old_userDetails!=null){
//						Iterator<UsersInfoDomain> itr_old_detail= old_userDetails.iterator();
//						while(itr_old_detail.hasNext()){
//							UsersInfoDomain old_userdom=itr_old_detail.next();
//								old_userid=old_userdom.getUserId();
//								old_contact=old_userdom.getContactno();
//								old_dob=old_userdom.getDateofbirth();
//								old_name=old_userdom.getUserName();
//								old_email=old_userdom.getEmail();
//								old_address=old_userdom.getAddress();
//								old_status=old_userdom.getStatus();
//						if(old_userid.equalsIgnoreCase(new_userid)){
//						//	to=new_email;
//							System.out.println("to " +to);
//						//	sendMailToMdifiedUser(to);
//						}
//					}
//					}
//					 }
		        	return("success");
		        }else{
		        	return("failed");
		        }   	 
		
		
	}
	
	private void addGroupMember(){}
	
	private String updateGroupMember(){
		ArrayList<UsersInfoDomain> old_userDetails =(ArrayList<UsersInfoDomain>)session.getAttribute("studentdetail1");
		UsersInfoDomain userinfodom=null;
		 ArrayList<UsersInfoDomain> arrayList=new ArrayList<UsersInfoDomain>();
		 StudentGroupNameDao stugroupdao=new StudentGroupNameDao();
		 
			String[] checkboxGroup = request.getParameterValues("checkboxGroup");
			 if(checkboxGroup.length>0){
			 //System.out.println("checked values for update:"+checkboxGroup.length);
			 
				        for(int i=0; i<checkboxGroup.length; i++) {
				        	userinfodom= new UsersInfoDomain();
				        	userinfodom.setUserId(request.getParameter("userid"+checkboxGroup[i]));
				//        System.out.println("userid in upd="+request.getParameter("userid"+checkboxGroup[i]));
				        	userinfodom.setUserName(request.getParameter("name"+checkboxGroup[i]));
				        	userinfodom.setAddress(request.getParameter("address"+checkboxGroup[i]));
				        	userinfodom.setContactno(request.getParameter("contact"+checkboxGroup[i]));
				        	userinfodom.setDateofbirth(request.getParameter("dob"+checkboxGroup[i]));
				        	userinfodom.setEmail(request.getParameter("email"+checkboxGroup[i]));
				        	userinfodom.setStatus(request.getParameter("status"+checkboxGroup[i]));
				        	
				        	arrayList.add(userinfodom);	
				        }
				    }
		        String status=stugroupdao.updateUserDetailsGroup(arrayList);
		        System.out.println("returnstaus after update in table== "+status);
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
						//	sendMailToMdifiedUser(to);
						}
					}
					}
					 }
		        	return("success");
		        }else{
		        	return("failed");
		        }   	 
			
	}
	public void sendMailToMdifiedUser(StringBuffer stuidbuffer)
   	{
		try
  		 {
		  InternetAddress[] address = new InternetAddress[group_member_email_array_list.size()];
    	  for(int i =0; i< group_member_email_array_list.size(); i++)
    	  {
    	      address[i] = new InternetAddress(group_member_email_array_list.get(i));
    	  }   		
		System.out.println("in send mail method========== "+to);
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
   			 }
   			 });	
   			Message message= new MimeMessage(session);
   			message.setFrom(new InternetAddress(emailid));
   			message.setRecipients(Message.RecipientType.TO,address);
   			//message.setSubject("Testing");
   			//message.setText("UserID:"+emailid+ "Password:"+password);
   			message.setSubject("Group setup"); // Now set the actual message
   			
//   			String htmlText ="Hello "+new_name+","+"<br>Your Profile has been Updated:<br>"
//   			+"Here are the details:<br/><br/>"+"<table border="+1+" cellspacing="+0+" cellpadding="+5+">"
//   					+"<tr><td>Details </td><td>UserID</td><td>Name</td><td>Email</td><td>Mobile No.</td><td>Date of Birth</td><td>Address</td>"
//					+"<tr><td>Old </td><td>"+old_userid+"</td><td>"+old_name+"</td><td>"+old_email+"</td><td>"+old_contact+"</td><td>"+old_dob+"</td><td>"+old_address+"</td></tr>"
//   					+"<tr><td>New </td><td>"+new_userid+"</td><td>"+new_name+"</td><td>"+new_email+"</td><td>"+new_contact+"</td><td>"+new_dob+"</td><td>"+new_address+"</td></tr>"+"</table>"
//   					+"<br/><br/>This is computer generated mail no need to reply<br/><br/>-- "
//   					+"<br/>Thanks & Regards"+"<br/>"+teachingSourceName+"<br/><br/>" +"For any technical assistance please contact on the details provided below:"
//   					+   "<strong>Toll Free No:</strong> 1800123456"+"<br/>"+"<strong>SMS:</strong> 1234567891"+"<br/>"+"<strong>Email ID:</strong> <a href='mailto:technicalsupport@kompaceducation.com'>technicalsupport@kompaceducation.com</a>";
//   			
   			String msg="Hello"+",<br/><br/> You have been assigned to a new group: "+newgroupname+". Members of the group are "+stuidbuffer.substring(0,stuidbuffer.length()-2)+". <br/><br/>Thanks and Regards" + "<br/>" + teachingSourceName +"<br/><br/>"+"For any technical assistance please contact on the details provided below:" + "<br/>"
   					+ "<strong>Toll Free No:</strong> 1800123456"+"<br/>"+"<strong>SMS:</strong> 1234567891"+"<br/>"+"<strong>Email ID:</strong> <a href='mailto:technicalsupport@kompaceducation.com'>technicalsupport@kompaceducation.com</a>";
   			
   			//System.out.println("htmlText== "+htmlText);
   			message.setContent(msg, "text/html"); 
   			 Transport.send(message);
   		 	}
   		 catch(MessagingException e)
   		 {
   			String Tabmsg="tab2";
   			String Studentymsg="Please check your net connection and try Again!";
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
   			String Tabmsg="tab2";
   			String Studentymsg="Please Again Later!";
			request.setAttribute("Studentymsg",Studentymsg);
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


