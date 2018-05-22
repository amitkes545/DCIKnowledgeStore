package com.kds.KODE_DEV.services;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.kds.KODE_DEV.dao.InsertStudentDao;
import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.ActiveSessionDomain;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;
@SuppressWarnings("serial")
//@MultipartConfig(maxFileSize = 16177215)   
public class CreateUserManagement extends CommonService {
	String host="" ;
	String pop="";
	int    port;
	String emailid="";
	String password="";
    String to="";
    String   organization_name="";
    String   organization_id="";
    String link="",superAdminID="",Org_Type_Name="",genaratePassword="",userID="";
    java.sql.Date sqlDate;
    String uid="";
  //  String teachingSourceName;
    //method for password created ramdomly
    public static String randomSeriesForThreeCharacter() {
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
	}
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
        
    //    teachingSourceName=(String)session.getAttribute("organizationName");
        FileItem org_name = (FileItem) items.get(0);
          organization_name =  org_name.getString();
          String SAName = removeSpaceFromString(organization_name);
          
          
        //  //System.out.println("organization_name="+organization_name);
          
          FileItem org_id = (FileItem) items.get(1);
          organization_id =  org_id.getString();
        //  //System.out.println("organization_id="+organization_id);
        
//      organization_id=organization_type.substring(0, 3)+Org_Type_Name.substring(0, 3)+organization_name.substring(0,3);
     // //System.out.println("organization_id="+organization_id);
      
        FileItem address = (FileItem) items.get(10);
        String   Address =  address.getString();
       System.out.println("Address="+Address);
        
        FileItem privilege = (FileItem) items.get(2);
        String   privileges =  privilege.getString();
       // //System.out.println("privilege="+privileges);
       
             
        FileItem email = (FileItem) items.get(4);
        String   email_id =  email.getString();
        ////System.out.println("email_id="+email_id);
        to=email_id;
        
        FileItem datepicker = (FileItem) items.get(5);
        String   datepickers =  datepicker.getString();
       // //System.out.println("datepicker="+datepickers);
        
        FileItem gender = (FileItem) items.get(6);
        String   genders =  gender.getString();
       // //System.out.println("gender="+genders);
        
        FileItem country = (FileItem) items.get(7);
        String   countrys =  country.getString();
        ////System.out.println("country="+countrys);
        
        FileItem state = (FileItem) items.get(8);
        String   states =  state.getString();
       System.out.println("state="+states);
        
        FileItem city = (FileItem) items.get(9);
        String   citys =  city.getString();
        System.out.println("city="+citys);

        FileItem pcode = (FileItem) items.get(11);
        String pcodes="";
        if(!(pcode.equals(null)))
             pcodes =  pcode.getString();
        else 
        	pcodes="12345";
       ////System.out.println("pcode="+pcodes);
        
        FileItem phone = (FileItem) items.get(3);
        String   telephone =  phone.getString();
        ////System.out.println("telephone="+telephone);
             
/*        FileItem course = (FileItem) items.get(13);
        String   courses =  course.getString();
     //  //System.out.println("courses="+courses);
       
       FileItem txtcourse = (FileItem) items.get(14);
       String   txtcourses =  txtcourse.getString();
     // //System.out.println("txtcourses="+txtcourses);
       
       FileItem subject = (FileItem) items.get(15);
       String   subjects =  subject.getString();
     // //System.out.println("subjects="+subjects);
      
     FileItem txtsubject = (FileItem) items.get(16);
     String   txtsubjects =  txtsubject.getString();
//    //System.out.println("txtsubjects="+txtsubjects);

    FileItem topic = (FileItem) items.get(17);
    String   topics =  topic.getString();
  // //System.out.println("topics="+topics);
   
    FileItem txttopic = (FileItem) items.get(18);
    String   txttopics =  txttopic.getString();*/
  // //System.out.println("txttopics="+txttopics);
   
   String disc="",sub="",top="";
  /* if(courses.equalsIgnoreCase("Other"))
   {
	   disc=txtcourses;
	   sub=txtsubjects;
	   top=txttopics;
   }
   else  if(subjects.equalsIgnoreCase("Other"))
   {
	   disc=courses;
		   sub=txtsubjects;
		   top=txttopics;
	   }
   else if(topics.equalsIgnoreCase("Other"))
   {
	   top=txttopics;
	   disc=courses;
	   sub=subjects;
   }
	   else
	   {
		   disc=courses;
		   sub=subjects;
		   top=topics;
	   }*/
	   //System.out.println("disc="+disc);
	   //System.out.println("top="+top);
	   //System.out.println("sub="+sub);
	   
       LOGGER.info("superadmin name:"+Org_Type_Name);
       // LOGGER.info("year of foundation :"+year_of_foundation);
       /*FileItem date = (FileItem) items.get(15);
       String   date_time =  date.getString();
        LOGGER.info("date time is:"+date_time);*/
       // String date_time=request.getParameter("date");

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
                  String password=  randomSeriesForThreeCharacter();
                  String  OrgTypeName="";
                  if (OrgTypeName.length() > 3){
                	  OrgTypeName = OrgTypeName.substring(0, 3);
                  }
                  genaratePassword=OrgTypeName+password;
                  System.out.println("genaratePassword="+genaratePassword);
                  LOGGER.info("password:"+password+ "string with password:"+genaratePassword);
        	        	    	
                 // DBTransaction dbtranobj = new DBTransaction();
        /*          Connection dbCon=null;
                  Statement st =null;
                  ResultSet rs=null;
                  
                  dbCon = DBTransaction.connect();
                	st = dbCon.createStatement();
                	
                  rs = st.executeQuery("select count(*) from faculty_subject_mapping");
                //  //System.out.println("select count(*) from faculty_subject_mapping");
                 int count=0;
              	while(rs.next())
              	{
              		count = rs.getInt("count");
              	
              	} */
        		//System.out.println("COUNT="+count);
        		//System.out.println("sub="+sub);
        		//String subid=sub.substring(0,3)+count+1;
        	//	System.out.println("subid="+subid);
        		//int count1=count+1;
        		 userID=organization_id.substring(0,3)+organization_name.substring(0,3)+telephone.substring(7,telephone.length());//+count1; //organization_id.substring(0,3)+genaratePassword;
                 System.out.println("new userID:"+userID);
                 
           
                 InsertStudentDao insertStudentDao=new InsertStudentDao();
             	 boolean email_conflict=insertStudentDao.email_conflict(email_id);
        	     if(email_conflict==true)
        	        {  
        	    	 String Studentymsg="Email ID already exists.";
        				request.setAttribute("OwnerFailure",Studentymsg);
        				RequestDispatcher rd=request.getRequestDispatcher("../JSP/Create_user_management.jsp");
        				rd.forward(request, response);
        				return;
        	        }
        	     boolean phone_conflict=insertStudentDao.phone_conflict(telephone);
        	     if(phone_conflict==true)
        	        {  
        	    	 String Studentymsg="Mobile No. already exists.";
        				request.setAttribute("OwnerFailure",Studentymsg);
        				RequestDispatcher rd=request.getRequestDispatcher("../JSP/Create_user_management.jsp");
        				rd.forward(request, response);
        				return;
        	        }             
              
                 
                 
                 
     /*   		 if(courses.equalsIgnoreCase("Other")){
        		String insertmaster="insert into discipline_master(discipline,customer_id) values(?,?)";
         	     PreparedStatement cmaster = con.prepareStatement(insertmaster);
         	     //System.out.println("insert users info="+insertmaster);
      			LOGGER.info("the quary is:"+insertmaster);
      			//cmaster.setString(1,organization_name);
      			cmaster.setString(1, txtcourses);
      			cmaster.setString(2, organization_id);
      			
      			 rs = st.executeQuery("select discipline_id from discipline_master where discipline='"+disc+"' ");
                 //System.out.println("select discipline_id from discipline_master where discipline='"+disc+"' ");
                String dispid="";
             	
      			int p= cmaster.executeUpdate();
      			while(rs.next())
             	{
             		dispid = rs.getString("discipline_id");
             	} 
             	disc=dispid;
             	//System.out.println("disc="+disc);
        		 }
        		 
        		 if(subjects.equalsIgnoreCase("Other") || subjects.equalsIgnoreCase("Blank")){
             		
        			
        			 String insertmaster="insert into subject_master(discipline_id,subject) values(?,?)";
              	     PreparedStatement cmaster = con.prepareStatement(insertmaster);
              	     //System.out.println("insert users info="+insertmaster);
           			LOGGER.info("the quary is:"+insertmaster);
           			//cmaster.setString(1,organization_name);
           			cmaster.setString(1, disc);
           			cmaster.setString(2, sub);
           			
           			int p= cmaster.executeUpdate();
           			
           			rs = st.executeQuery("select subject_id from subject_master where subject='"+sub+"' ");
                    //System.out.println("select subject_id from subject_master where subject='"+sub+"' ");
                   String subjid="";
                	while(rs.next())
                	{
                		subjid = rs.getString("subject_id");
                	} 
                	sub=subjid;
             		 }
        		 ////System.out.println("select subject_id from subject_master where subject='"+sub+"' ");
        		 if(topics.equalsIgnoreCase("Other") || topics.equalsIgnoreCase("Blank")){
              		
        			 
                 	
        			 String insertmaster="insert into topic_master(subject_id,topic_details) values(?,?)";
              	     PreparedStatement tmaster = con.prepareStatement(insertmaster);
              	     //System.out.println("insert users info="+insertmaster);
           			LOGGER.info("the quary is:"+insertmaster);
           			//cmaster.setString(1,organization_name);
           			tmaster.setString(1, sub);
           			tmaster.setString(2, top);
           			
           			int p= tmaster.executeUpdate();
             		 }*/
      			
        		 
                /*  String sql="insert into faculty_subject_mapping(faculty_id,subject_id,discipline,subject,topic) values(?,?,?,?,?)";
           	     PreparedStatement pstmt = con.prepareStatement(sql);
           	     ////System.out.println("insert users info="+insertQuery);
        			LOGGER.info("the quary is:"+sql);
        			pstmt.setString(1,userID);
        			pstmt.setString(2, subid);
        			pstmt.setString(3, disc);
        			pstmt.setString(4, sub);
        			pstmt.setString(5,top );
        			
        			int p1= pstmt.executeUpdate();*/
        			//System.out.println("insert users info="+pstmt);
                 int result=0;
                 String sqlsa="Select * from users_info where organization_id='"+organization_id+"' and privilege='Super Admin'";
                 PreparedStatement ps1 = con.prepareStatement(sqlsa);
                 System.out.println("ps1="+ps1);
                // ps1.setString(1,organization_id);
                 ResultSet rs1=ps1.executeQuery();
     	     			while(rs1.next())
     			{
     			result++;
     			}
     	     			System.out.println("result="+result);
                 if(result==0)
                 {
                	 DateFormat  formatter = new SimpleDateFormat("dd/MM/yyyy");
              		
      			    try {
      			    
      			        java.util.Date date = formatter.parse(datepickers);
      			        System.out.println("date="+date);
      			        sqlDate = new java.sql.Date(date.getTime());
      			   
      			        System.out.println("sqlDate="+sqlDate);
      			
      			       
      			    } catch (Exception e) {
      			        // TODO Auto-generated catch block
      			        e.printStackTrace();
      			    } 
                	 
                	 String insertQuery="insert into users_info(username,user_id,password,email,address,contact_no,privilege,organization_id,created_by,country,state,city,postal_code,users_status,gender,dateofbirth,space_uom) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
              	     PreparedStatement prepareStatement = con.prepareStatement(insertQuery);
              	     System.out.println("insert users info="+insertQuery);
           			LOGGER.info("the quary is:"+insertQuery);
           			String uname=removeSpaceFromString(organization_name);
           			uid=removeSpaceFromUserid(userID);
           			prepareStatement.setString(1,uname);
           			prepareStatement.setString(2, uid);
           			prepareStatement.setString(3, genaratePassword);
           			prepareStatement.setString(4, email_id);
           			prepareStatement.setString(5,Address );
           			prepareStatement.setString(6, telephone);
           			prepareStatement.setString(7, "Super Admin");
           			prepareStatement.setString(8, organization_id);
           			prepareStatement.setString(9, "Owner");
           			prepareStatement.setString(10, countrys);
           			prepareStatement.setString(11, states);
           			prepareStatement.setString(12, citys);
           			prepareStatement.setString(13, pcodes);
           			prepareStatement.setString(14, "Active");
           			prepareStatement.setString(15, genders);
           			prepareStatement.setDate(16, sqlDate);
           			prepareStatement.setString(17, "GB");
           			
           			int p= prepareStatement.executeUpdate();
           			System.out.println("insert users info prepareStatement="+prepareStatement);
           		        if (p != 0 )
       	            {
           		        	//System.out.println("before sending setup info");
        	    	   sendMail(host,to); 
        	    	   String OrgStatus="Super Admin created successfully, details sent via e-mail.";
          			request.setAttribute("OwnerSuccessMAil",OrgStatus);
          			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Create_user_management.jsp");
          			rd.forward(request, response);
        	       }
       			  else {
       				String OrgStatus="Failure for creating institution";
          			request.setAttribute("OwnerFailure",OrgStatus);
          			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Create_user_management.jsp");
          			rd.forward(request, response);
       			  }
        	        //out.println("Organization details stores database Successfully.");
        	
		}
                 else{
         			String OrgStatus="Only one Super Admin is allowed";
           			request.setAttribute("OwnerFailure",OrgStatus);
           			RequestDispatcher rdforSA=request.getRequestDispatcher("../JSP/Create_user_management.jsp");
           			rdforSA.forward(request, response);
         		}
		}
		
		catch(Exception e){
			String OrgStatus="Failure for creating Super Admin";
  			request.setAttribute("OwnerFailure",OrgStatus);
  			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Create_user_management.jsp");
  			rd.forward(request, response);
		e.printStackTrace();
	}
        	

    	}
    	public void sendMail(String host1,String to )
		{
		 
			 Properties props= new Properties();
			 props.put("mail.smtp.auth",pop);
		     props.put("mail.smtp.starttls.enable",pop);
			 props.put("mail.smtp.host",host1);
			// props.put("mail.smtp.socketFactory.port", port);
			 props.put("mail.smtp.port",port);
			 props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			 Session session= Session.getInstance(props, new javax.mail.Authenticator(){
				 protected PasswordAuthentication getPasswordAuthentication(){return new PasswordAuthentication(emailid,password);
				 																} });
			 //System.out.println("in sending");	
			 try
			 { 
				 //System.out.println("in try");	
				 String msg="Hi "+organization_name+",<br/><br/>"  +"Welcome to KODE_DEV !!<br/><br/> "  +"Please find your Institution ID: "+ organization_id+"<br/>Super Admin ID: "+ uid+"<br/>Password: "+ genaratePassword+"<br/><br/>"+ "Kindly login into the URL to start accessing the product:<br/>" +"<a href='https://www.kompacdigit.com:8443/KODE_DEV/'>https://www.kompacdigit.com:8443/KODE_DEV/</a>"+
							"<br/><br/>"+"Thanks & Regards"+"<br/>"+"Knowledge Store Technical Team"+"<br/><br/>" +"For any technical assistance please contact on the details provided below:" + "<br/>"
							+ "<strong>Toll Free No:</strong> 1800123456"+"<br/>"+"<strong>SMS:</strong> 1234567891"+"<br/>"+"<strong>Email ID:</strong> <a href='mailto:technicalsupport@kompaceducation.com'>technicalsupport@kompaceducation.com</a>";
				Message message= new MimeMessage(session);
				message.setFrom(new InternetAddress(emailid));
				message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
				//message.setSubject("Testing");
				//message.setText("UserID:"+emailid+ "Password:"+password);
				message.setSubject("User Information"); // Now set the actual message
				message.setContent(msg, "text/html; charset=utf-8"); 
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
    	private String removeSpaceFromString(String str) {
    		if (str != null)
    			str = str.replaceAll("\\s+", " ").trim();
    		return str;
    	}
    	
    	
    	
    	private String removeSpaceFromUserid(String str) {
    		if (str != null)
    			str = str.replaceAll("\\s+","");
    		return str;
    	}
    	

}
		
    	
    	
    	




