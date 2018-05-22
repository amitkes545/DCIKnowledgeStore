package com.kds.KODE_DEV.services;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;

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
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;
import com.kds.KODE_DEV.domain.CourseDomain;
@SuppressWarnings("serial")
//@MultipartConfig(maxFileSize = 16177215)   
public class CreateCourse extends CommonService {
	String host="" ;
	String pop="";
	int    port;
	String emailid="";
	String password="";
    String to="";
    String   organization_name="";
    String   organization_id="";
    String link="",superAdminID="",Org_Type_Name="";
   
    //method for password created ramdomly
/*    public static String randomSeriesForThreeCharacter() {
           LOGGER.info("calling password method");
	    Random random = new Random();
	    LOGGER.info("random charactor:"+random);
	             String value="";
	                int random_Char ;
	    for(int i=0; i<2;i++)
	             { 
	               random_Char = random.nextInt(74);
	                value=value+random_Char;
	             }
	    return value;
	}*/
    static final Logger LOGGER = Logger.getLogger(CreateUserManagement.class);
    	@Override
	public void run() throws ServletException, IOException {
		//PrintWriter out=response.getWriter();
    		
    		//session = request.getSession(true);
    	//	organization_id=(String)session.getAttribute("orgid");
		String msg=null;
		try {
		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload pic=new ServletFileUpload(factory);
        java.util.List<FileItem> items=pic.parseRequest(request);
       // //System.out.println("items="+items);
        
        
       /* FileItem org_name = (FileItem) items.get(0);
          organization_name =  org_name.getString();
        System.out.println("organization_name="+organization_name);*/
          
          FileItem org_id = (FileItem) items.get(0);
          organization_id =  org_id.getString();
        System.out.println("organization_id="+organization_id);
    
        
        FileItem cname = (FileItem) items.get(1);
        String   csname =  cname.getString();
       // //System.out.println("privilege="+privileges);
       
             
        FileItem stream = (FileItem) items.get(2);
        String   sstream =  stream.getString();
        ////System.out.println("email_id="+email_id);
        //to=email_id;
        
        FileItem duration = (FileItem) items.get(3);
        int   sduration =  Integer.parseInt(duration.getString());
       // //System.out.println("datepicker="+datepickers);
        
        FileItem subject = (FileItem) items.get(4);
        String   ssubject =  subject.getString();
       // //System.out.println("gender="+genders);
        
        FileItem topic = (FileItem) items.get(5);
        String   stopic =  topic.getString();
        ////System.out.println("country="+countrys);
        
        FileItem subtopic = (FileItem) items.get(6);
        String   ssubtopic =  subtopic.getString();
        System.out.println("ssubtopic="+ssubtopic);

        FileItem nsession = (FileItem) items.get(7);
        int   snsession =  Integer.parseInt(nsession.getString());
        ////System.out.println("telephone="+telephone);
   
       LOGGER.info("superadmin name:"+Org_Type_Name);
     

        SendEmailDao senderDao =new SendEmailDao();
		//session.removeAttribute("MsgValue");
		SenderEmailDetailsDomain senderDom =(SenderEmailDetailsDomain)senderDao.senderdetails();
		 emailid=senderDom.getEmailid();
		 password=senderDom.getPassword();
		 host= senderDom.getHost();
		 pop=senderDom.getPop();
		   port=senderDom.getPort();
		   link=senderDao.getWebLink();//selecting web room link from dao


        		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
    			LOGGER.info(timeStamp );
   			    Connection con = DBTransaction.connect();
                //  String password=  randomSeriesForThreeCharacter();
                  String  subs_csname="";
                  if (csname.length() > 3){
                	  subs_csname = csname.substring(0, 3);
                  }
                 // genaratePassword=OrgTypeName+password;
                //  System.out.println("genaratePassword="+genaratePassword);
                 // LOGGER.info("password:"+password+ "string with password:"+genaratePassword);
        	        	    	
           
        		//System.out.println("COUNT="+count);
        		// userID=organization_name.substring(0,3);//+count1; //organization_id.substring(0,3)+genaratePassword;
                 //System.out.println("new userID:"+userID);
                
        			//System.out.println("insert users info="+pstmt);
        	    	   String insertQuery="insert into course_description(course_id_defined_by_teaching_source,course_name,stream,duration_in_month,subject,topic,subtopic,number_of_session,customer_id) values(?,?,?,?,?,?,?,?,?)";
              	     PreparedStatement prepareStatement = con.prepareStatement(insertQuery);
              	     System.out.println("insert users info="+insertQuery);
           			LOGGER.info("the quary is:"+insertQuery);
           			prepareStatement.setString(1,subs_csname);
           			prepareStatement.setString(2, csname);
           			prepareStatement.setString(3, sstream);
           			prepareStatement.setInt(4, sduration);
           			prepareStatement.setString(5, ssubject);
           			prepareStatement.setString(6, stopic);
           			prepareStatement.setString(7, ssubtopic);
           			prepareStatement.setInt(8, snsession);
           			prepareStatement.setString(9, organization_id);
           			
           			int p= prepareStatement.executeUpdate();
           			System.out.println("insert users info prepareStatement="+prepareStatement);
           		        if (p != 0 )
       	            {
           		        	//System.out.println("before sending setup info");
        	    	   sendMail(host,to); 
        	    	   String OrgStatus="Institution created successfully"+"#"+"  ID sent to your email ID";
          			request.setAttribute("OwnerSuccessMAil",OrgStatus);
          			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Create_Course.jsp");
          			rd.forward(request, response);
        	       }
       			  else {
       				String OrgStatus="Failure for creating institution";
          			request.setAttribute("OwnerFailure",OrgStatus);
          			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Create_Course.jsp");
          			rd.forward(request, response);
       			  }
        	        //out.println("Organization details stores database Successfully.");
        	
		}
		catch(Exception e){
		e.printStackTrace();
	}
        	

    	}
    	public void sendMail(String host1,String to )
		{
		 
			 Properties props= new Properties();
			 props.put("mail.smtp.auth",pop);
		     props.put("mail.smtp.starttls.enable",pop);
			 props.put("mail.smtp.host",host1);
			 props.put("mail.smtp.port",port);
			 props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			 Session session= Session.getInstance(props, new javax.mail.Authenticator(){
				 protected PasswordAuthentication getPasswordAuthentication(){return new PasswordAuthentication(emailid,password);
				 																} });
			 //System.out.println("in sending");	
			 try
			 { 
				 //System.out.println("in try");	
				Message message= new MimeMessage(session);
				message.setFrom(new InternetAddress(emailid));
				message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
				//message.setSubject("Testing");
				//message.setText("UserID:"+emailid+ "Password:"+password);
				message.setSubject("User Information"); // Now set the actual message
				message.setText("Hi "+" ,\n"  +"Welcome to KODE_DEV!!!\n "  +"Course created for Your Institution ID :"+ organization_id+"\n  "+ "&&"+
						"\n\n"+"Thanks & Regards"+"\n"+"Kompac Digital Systems Pvt. Ltd."+"\n" +"Website : www.kompaceducation.com"); 
				//message.setText("SessionStartTime:"+SessionStartTime);
				//message.setText("SessionEndTime:"+SessionEndTime);
				//message.setText("Duration :"+hours);
				//LOGGER.info();
				System.out.println("text=");
				 Transport.send(message);
				LOGGER.info("send successfully");
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
		
    	
    	
    	




