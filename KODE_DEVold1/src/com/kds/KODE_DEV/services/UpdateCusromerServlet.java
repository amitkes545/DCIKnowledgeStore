package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;



/**
 * Servlet implementation class UpdateCusromerServlet
 */
//@WebServlet("/UpdateCusromerServlet")
public class UpdateCusromerServlet extends CommonService {
	String host="" ;
	String pop="";
	int    port;
	String emailid="";
	String password="";
	String   organization_name="";
	private static final long serialVersionUID = 1L;
  public void run() throws ServletException,IOException
  {
	  try {
		 
	  DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload pic=new ServletFileUpload(factory);
      java.util.List<FileItem> items;
	
		items = pic.parseRequest(request);
	

        FileItem org_id = (FileItem) items.get(0);
        String   organization_id =  org_id.getString();
        
        FileItem org_name = (FileItem) items.get(1);
         organization_name =  org_name.getString();
        
        FileItem org_type = (FileItem) items.get(2);
        String   organization_type =  org_type.getString();
       
        FileItem address = (FileItem) items.get(3);
        String   address1 =  address.getString();
        //System.out.println("address  is:"+address1);
       
        FileItem country = (FileItem) items.get(4);
        String   Country =  country.getString();
        
        FileItem state = (FileItem) items.get(6);
        String   State =  state.getString();
        
        FileItem city = (FileItem) items.get(7);
        String   City =  city.getString();
       
        FileItem pcode = (FileItem) items.get(8);
        String   postal_code =  pcode.getString();
        
        FileItem phone = (FileItem) items.get(9);
        String   telephone =  phone.getString();
        
        FileItem fax = (FileItem) items.get(10);
        String   fax1 =  fax.getString();
        
        FileItem ecno = (FileItem) items.get(11);
        String   emergency_contact_no =  ecno.getString();
        
        FileItem email = (FileItem) items.get(12);
        String   email_id =  email.getString();
        
        FileItem url = (FileItem) items.get(13);
        String   url1 =  url.getString();
        
        FileItem photo=items.get(14); 
       // FileInputStream fis=new FileInputStream(photo);
        //System.out.println("photo value is:"+photo);
        
        FileItem yof = (FileItem) items.get(15);
        String   year_of_foundation =  yof.getString();
       // //System.out.println("year of foundation:"+year_of_foundation);
        
       /* FileItem belongs = (FileItem) items.get(15);
        String   belongs1 =  belongs.getString();*/
        
      /* FileItem date = (FileItem) items.get(15);
       String   date_time =  date.getString();*/
       String update=request.getParameter("update");
       String delete=request.getParameter("delete");
      // //System.out.println("update value:"+update+ " delete value:"+delete);
       SendEmailDao senderDao =new SendEmailDao();
		//session.removeAttribute("MsgValue");
		SenderEmailDetailsDomain senderDom =(SenderEmailDetailsDomain)senderDao.senderdetails();
		 emailid=senderDom.getEmailid();
		 password=senderDom.getPassword();
		 host= senderDom.getHost();
		 pop=senderDom.getPop();
		   port=senderDom.getPort();
       
      /* FileItem update = (FileItem) items.get(16);
       String   update1 =  update.getString();
        //System.out.println("updatevalue is:"+update1);*/
   
	  if(update!=null)
  	{
		// //System.out.println("update value");
		 try {
		 
         // //System.out.println("update page");
         /* Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/keducom", "postgres", "12345");*/
			 //DBTransaction DBT = new DBTransaction();
			Connection con = DBTransaction.connect();
			 ////System.out.println("update page");
          String sql="";
          if(photo.getSize()==0){
        	  sql="update org_details set organization_name=?,org_type=?,address=?,city=?,country=?,postal_code=?,telephone=?,fax=?,emergency_contact_no=?,email=?,url=?,year_of_foundation=?,date_time=?,state=? where organization_id=?";
        		
              PreparedStatement ps = con.prepareStatement(sql);
              //System.out.println("the quary is:"+sql);
             
    			ps.setString(1, organization_name);
    			ps.setString(2, organization_type);
    			ps.setString(3, address1);
    			ps.setString(4,City );
    			ps.setString(5,Country);
    			ps.setString(6, postal_code);
    			ps.setString(7, telephone);
    			ps.setString(8, fax1);
    			ps.setString(9, emergency_contact_no);
    			ps.setString(10, email_id);
    			ps.setString(11, url1);
    			
    			//ps.setBinaryStream(12,photo.getInputStream(),(int)photo.getSize());
    			ps.setString(12, year_of_foundation);
    			
    			/*ps.setTimestamp(13,(Timestamp) new java.sql.Timestamp(new Date().getTime()));*/
    			ps.setTimestamp(13, getCurrentTimeStamp());
    			//System.out.println("time in service:"+getCurrentTimeStamp());
    			ps.setString(14, State);
    			 ps.setString(15,organization_id);
    			////System.out.println("date value");
    			
    			//System.out.println("the quary is:"+ps);
    	     //  ps.executeUpdate();

    			int i = ps.executeUpdate();
    			//System.out.println(i+"row is updated successfully");
    			if (i==1){
    				String to=email_id;
    	           	 //System.out.println("email ID:"+to);
    	           	 sendMailIntern(host,to); 
    				 String msg="Profile updated successfully";
    					request.setAttribute("OwnerSuccess",msg);
    					RequestDispatcher rd=request.getRequestDispatcher("../JSP/Search.jsp");
    					rd.forward(request, response);
    			}
    			else {
    				String msg="Failed for updating profile";
    				request.setAttribute("OwnerFailure",msg);
    				RequestDispatcher rd=request.getRequestDispatcher("../JSP/Search.jsp");
    				rd.forward(request, response);

    			}
    		
          }
          else{
           sql="update org_details set organization_name=?,org_type=?,address=?,city=?,country=?,postal_code=?,telephone=?,fax=?,emergency_contact_no=?,email=?,url=?,logo=?,year_of_foundation=?,date_time=?,state=? where organization_id=?";
         // //System.out.println("quary is:"+sql);
          
			
          PreparedStatement ps = con.prepareStatement(sql);
         // //System.out.println("the quary is:"+sql);
         
			ps.setString(1, organization_name);
			ps.setString(2, organization_type);
			ps.setString(3, address1);
			ps.setString(4,City );
			ps.setString(5,Country);
			ps.setString(6, postal_code);
			ps.setString(7, telephone);
			ps.setString(8, fax1);
			ps.setString(9, emergency_contact_no);
			ps.setString(10, email_id);
			ps.setString(11, url1);
			
			ps.setBinaryStream(12,photo.getInputStream(),(int)photo.getSize());
			ps.setString(13, year_of_foundation);
			
			/*ps.setTimestamp(14,(Timestamp) new java.sql.Timestamp(new Date().getTime()));*/
			ps.setTimestamp(14, getCurrentTimeStamp());
			//System.out.println("time in service:"+getCurrentTimeStamp());
			ps.setString(15, State);
			 ps.setString(16,organization_id);
			////System.out.println("date value");
			
			//System.out.println("the quary is:"+ps);
	        //ps.executeUpdate();

			int i = ps.executeUpdate();
			//System.out.println(i+"row is updated successfully");
			if (i==1){
				String to=email_id;
	           	 //System.out.println("email ID:"+to);
	           	 sendMailIntern(host,to); 
				 String msg="Profile updated successfully";
			request.setAttribute("OwnerSuccess",msg);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Search.jsp");
			rd.forward(request, response);
			}
			else {
				String msg="Failed for update profile";
				request.setAttribute("OwnerFailure",msg);
				RequestDispatcher rd=request.getRequestDispatcher("../JSP/Search.jsp");
				rd.forward(request, response);
			}
          }
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}else if(delete!=null) {
		
       		try {
			 //DBTransaction db= new DBTransaction();
             Connection  con=DBTransaction.connect();
		
        String quary="delete from org_details where organization_id='"+organization_id+"'";
		PreparedStatement ps=con.prepareStatement(quary);
		//System.out.println("the quary is"+quary);
		int i=ps.executeUpdate();
		//System.out.println(i+"rows deleted");
		if(i==1){
			String to=email_id;
         	 //System.out.println("email ID:"+to);
         	 sendMailInternDelete(host,to); 
		 String msg="profile deleted successfully";
		request.setAttribute("OwnerSuccess",msg);
		RequestDispatcher rd=request.getRequestDispatcher("../JSP/Search.jsp");
		rd.forward(request, response);
		}
		else{
			String msg="Failed for delete profile";
			request.setAttribute("OwnerFailure",msg);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Search.jsp");
			rd.forward(request, response);
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
		
	}
	else{
		//System.out.println("not valid");
	}
	  
  
  } catch (FileUploadException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
  }
  private static java.sql.Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

	}
  public void sendMailIntern(String host1,String to )
	{
	 
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
			message.setSubject("User Information"); // Now set the actual message
			message.setText("Hi "+organization_name+" ,\n"  +"Welcome to KODE_DEV!!!\n "  +"Your profile has been updated " +"\n\n"+"Thanks & Regards"+"\n"+"Kompac Digital Systems Pvt. Ltd."+"\n" +"Website : www.kompacdigit.com"); 
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
public void sendMailInternDelete(String host1,String to )
	{
	 
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
			message.setSubject("User Information"); // Now set the actual message
			message.setText("Hi "+organization_name+" ,\n"  +"Welcome to KODE_DEV!!!\n "  +"Your ID deleted "+"\n\n"+"Thanks & Regards"+"\n"+"Kompac Digital Systems Pvt. Ltd."+"\n" +"Website : www.kompacdigit.com" ); 
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
  




  	
  

