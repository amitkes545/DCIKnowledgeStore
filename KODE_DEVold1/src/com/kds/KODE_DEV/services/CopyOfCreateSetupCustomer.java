package com.kds.KODE_DEV.services;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;
import com.kds.KODE_DEV.util.PropertiesUtil;
@SuppressWarnings("serial")
//@MultipartConfig(maxFileSize = 16177215)   
public class CopyOfCreateSetupCustomer extends CommonService {
	String host="" ;
	String pop="";
	int    port;
	String emailid="";
	String password="";
    String to="";
    String   organization_name="";
    String   organization_id="";
    String link="",superAdminID="",Org_Type_Name="",genaratePassword="";
    public boolean duplicate(String orgid) throws SQLException
    {
    	 Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	boolean result=false;
    	try
    	{
    		//DBTransaction DBT = new DBTransaction();
			  con = DBTransaction.connect();
    	
         
    	String quary="select organization_id from org_details where organization_id=?";
    	
    	 ps = con.prepareStatement(quary);
    	ps.setString(1,orgid);
    	  rs=ps.executeQuery();
    	 if(rs.next())
    		 result = true; 
    	 
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return result;
    }
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
    static final Logger LOGGER = Logger.getLogger(CopyOfCreateSetupCustomer.class);
    	@Override
	public void run() throws ServletException, IOException {
		//PrintWriter out=response.getWriter();
    		
    		System.out.println("in servlet");
		String msg=null;
		try {
		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload pic=new ServletFileUpload(factory);
        java.util.List<FileItem> items=pic.parseRequest(request);
       System.out.println("items="+items);
        
        
        FileItem org_id = (FileItem) items.get(0);
          organization_id =  org_id.getString();
         System.out.println("organization_id="+organization_id);
          
        FileItem org_name = (FileItem) items.get(2);
          organization_name =  org_name.getString();
       System.out.println("organization_name="+organization_name);
        
        FileItem org_type = (FileItem) items.get(1);
        String   organization_type =  org_type.getString();
    //    //System.out.println("organization_type="+organization_type);
        
        FileItem sName = (FileItem) items.get(3);
        Org_Type_Name =  sName.getString();
  //    //System.out.println("Org_Type_Name="+Org_Type_Name);
        
      organization_id=organization_type.substring(0, 3)+Org_Type_Name.substring(0, 3)+organization_name.substring(0,3);
     // //System.out.println("organization_id="+organization_id);
      
        FileItem sId = (FileItem) items.get(4);
        superAdminID =  sId.getString();
       // //System.out.println("superAdminID="+superAdminID);
        superAdminID=organization_id;
        FileItem address = (FileItem) items.get(11);
        String   Address =  address.getString();
      //  //System.out.println("Address="+Address);
       
        FileItem country = (FileItem) items.get(8);
        String   Country =  country.getString();
        LOGGER.info("country value is:"+Country);
        ////System.out.println("Country="+Country);
       
        FileItem state = (FileItem) items.get(9);
        String   state1 =  state.getString();
    //    //System.out.println("state1="+state1);
        
        FileItem city = (FileItem) items.get(10);
        String   City =  city.getString();
      //  //System.out.println("City="+City);
       
        FileItem pcode = (FileItem) items.get(12);
        String   postal_code =  pcode.getString();
       // //System.out.println("postal_code="+postal_code);
        
        FileItem email = (FileItem) items.get(7);
        String   email_id =  email.getString();
     //   //System.out.println("email_id="+email_id);
        to=email_id;
        
        FileItem phone = (FileItem) items.get(6);
        String   telephone =  phone.getString();
        ////System.out.println("telephone="+telephone);
        
        FileItem ecno = (FileItem) items.get(13);
        String   emergency_contact_no =  ecno.getString();
     //   //System.out.println("emergency_contact_no="+emergency_contact_no);
        
        FileItem fax = (FileItem) items.get(14);
        String   Fax =  fax.getString();
     //   //System.out.println("Fax="+Fax);
        
        FileItem url = (FileItem) items.get(15);
        String   url1 =  url.getString();
        System.out.println("url1="+url1);
        
        FileItem photo=items.get(16); 
        
    System.out.println("photo="+photo);
     //   FileInputStream fis=new FileInputStream((File) photo);
        String filename=photo.getName();
        System.out.println("filename value is:"+filename);
        
        FileItem yof = (FileItem) items.get(17);
        String   year_of_foundation =  yof.getString();
    System.out.println("year_of_foundation="+year_of_foundation);
    Iterator<FileItem> iter = items.iterator();
    FileItem fileItem = iter.next();
    FileItem flItem = null;
    String fileName = null;
    String uploadPath;
    String download_path = System.getProperty("user.home") + "/Downloads";
    String download_path1 = download_path.replace("\\", "/");
    String s =System.getProperty ("os.name");
    String [] win= s.split(" ");
    String winname=win[0];
    if(winname.equalsIgnoreCase("Windows"))
    {
    	//UPLOAD_DIRECTORY=download_path1;
    	 uploadPath =  "/"+ download_path1;
    }else{
    	//UPLOAD_DIRECTORY="/home";
    	uploadPath =  "/"+ download_path1;
    	LOGGER.info("uploadpath:"+uploadPath);
    }
    String filePath = null;
    File storeFile = null;
    System.out.println("before if");
    if (!fileItem.isFormField())   //file select field
    { 
    	System.out.println("in if");
       flItem = fileItem;
       fileName = new File(flItem.getName()).getName();
       System.out.println("fileName="+fileName);
       filePath = uploadPath + File.separator + fileName;
       System.out.println("filePath="+filePath);
       storeFile = new File(filePath);
      LOGGER.info("filePath is="+filePath);
      LOGGER.info("1.storeFile is="+storeFile);
     System.out.println("storeFile:"+storeFile);
      //System.out.println("1.storeFile is="+storeFile);
      // saves the file on disk
      try {
			flItem.write(storeFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      File file = new File(storeFile.getPath());
        
       LOGGER.info("superadmin name:"+Org_Type_Name);
        LOGGER.info("year of foundation :"+year_of_foundation);
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

             

        boolean isDuplicate = duplicate(organization_id);
        if(isDuplicate)
        {
//        	request.setAttribute("isDuplicate", "Y");
//        	request.setAttribute("message", "Oganization id"+organization_id+"already present in DB");
//            response.sendRedirect("../JSP/SetupCustomer.jsp");
        	msg = "UserId is exit!please try another";
  		  request.setAttribute("MsgValue", msg);
  		  RequestDispatcher rd=request.getRequestDispatcher("../JSP/SetupCustomer.jsp");
  		   rd.forward(request, response);
        }
//        java.util.Date dob1 =new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("date"));
//		LOGGER.info(dob1);
        
       // LOGGER.info(dateFormat.format(cal.getTime())); //2014/08/06 16:00:22
        
        FTPClient ftpclient = new FTPClient();
        
      //String ftpServerAddress = "localhost";
		String ftpServerAddress = PropertiesUtil.getProperty("FTP_IP");
		//String ftpServerAddress = "ftp://220.225.125.221/";
		/*String userName = "ftpuser1";
		String ftppassword = "ftp@123#1";
		*///String filePath = null;
		String userName =PropertiesUtil.getProperty("user1");
		String ftppassword =PropertiesUtil.getProperty("password1");	
		String s1="";
		boolean result;
        	if(!isDuplicate){
        		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
    			LOGGER.info(timeStamp );
    			
    			
    			try {
    				ftpclient.connect(ftpServerAddress);
    				result = ftpclient.login(userName, ftppassword);
    				System.out.println("after connection result="+result);
    				if (result) {
    					LOGGER.info("Logged in Successfully !");

    					ftpclient.setFileType(FTP.BINARY_FILE_TYPE); //commented by prity
    					 LOGGER.info("filePath1 in try="+filePath);
    					String str = filePath.toString();
    					LOGGER.info("str="+str);
    					System.out.println(" filePath1="+str);
    					String[] pathElements = str.split("/");
    					//System.out.println("vefore pathElements="+pathElements);
    										for(int i=1;i<pathElements.length-1;i++)
    					{
    						s1=s1+"/"+pathElements[i];
    						LOGGER.info("s1 under for="+s1);
    					}
    									System.out.println("before s1="+s1);
    									//"/home/ftpuser1/KnowStore/SRM/Admin/KS73/student1@srm/pgadmin.log"
    					//s1="KnowStore1"+s1+"/"+session.getAttribute("userId").toString();
    									//s1="/home/ftpuser1/KnowStore/SRM/KS387"+"/"+session.getAttribute("userId").toString();
    //s1="KnowStore1"+"/"+session.getAttribute("userId").toString();
    					System.out.println("after s1="+s1);
    					LOGGER.info("file full path in service:"+s1);
    					System.out.println("file full path in service:"+s1);
    					s1 = "/home/ftpkds1/KnowStore/"
    							+ session.getAttribute("orgid");
    							
    					System.out.println("directoryPath="+s1);
    				String	pathElements1[]=s1.split("/");
    					if (pathElements1 != null && pathElements1.length  > 0) {
    						for (String singleDir : pathElements1) 
    						{
    							boolean existed = ftpclient.changeWorkingDirectory(singleDir);
    							LOGGER.info("CurrentDir: "+singleDir );
    							System.out.println("existed="+existed);
    							if (!existed) {
    								boolean created = ftpclient.makeDirectory(singleDir);
    								ftpclient.changeWorkingDirectory(singleDir);
    								LOGGER.info("CurrentDir: "+singleDir );
    								if (created) {
    									LOGGER.info(singleDir);
    									System.out.println("Directory created successfully !");
    								} else {
    									System.out.println("Error in creating directory !");
    								}
    							}
    						}
    					}
    				} else {
    					LOGGER.info("Login Fail!");
    				}
    				//File storeFile = null;
    				storeFile = new File(filePath);
    				// File file = new File(storeFile.getPath());
    				System.out.println("file in:"+file);
    				LOGGER.info(file);
    			//	String testName = null;
    				
    				//testName = fileName;
    				//LOGGER.info(testName);
    				FileInputStream fis = null;
    				fis = new FileInputStream(file);
                      System.out.println("ftp path in service:"+fis);
    				// Upload file to the ftp server
    				result = ftpclient.storeFile(s1, fis);

    				// DBTransaction dbTransaction = new DBTransaction();

    				if (result) {
    					LOGGER.info("File is uploaded successfully");
    				} else {
    					LOGGER.info("File uploading failed");
    				}
    				System.out.println("result=:"+result);
    				ftpclient.logout();
    				System.out.println("ftpclient logout");
    			} catch (FTPConnectionClosedException e1) {
    				e1.printStackTrace();
    			} finally 
    			{
    				try {
    					ftpclient.disconnect();
    				} catch (FTPConnectionClosedException e2) {
    					LOGGER.info(e2);
    				}
    			}
    			
    			
    			
        		  /* Class.forName("org.postgresql.Driver");
        	        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KODE_DEV", "postgres", "root");*/
        	       
        		//DBTransaction DBT = new DBTransaction();
   			    Connection con = DBTransaction.connect();
   			 //con.setAutoCommit(false);
   			    //generating random password 
                  String password=  randomSeriesForThreeCharacter();
                  String  OrgTypeName="";
                  if (OrgTypeName.length() > 3){
                	  OrgTypeName = OrgTypeName.substring(0, 3);
                  }
                  String path_of_file = "/home/ftpkds1/KnowStore/"+session.getAttribute("orgid")+"/"+filename;
                  genaratePassword=OrgTypeName+password;
                 // //System.out.println("new password:"+genaratePassword);
                  LOGGER.info("password:"+password+ "string with password:"+genaratePassword);
        	        String sql = "insert into org_details(organization_id,organization_name,org_type,address,city,country,postal_code, " +
        					"telephone,fax,emergency_contact_no,email,url,year_of_foundation,date_time,state,logo) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        			PreparedStatement ps = con.prepareStatement(sql);
        			////System.out.println("insert org details="+sql);
        			LOGGER.info("the quary is:"+sql);
        			ps.setString(1,organization_id);
        			ps.setString(2, Org_Type_Name);
        			ps.setString(3, organization_type);
        			ps.setString(4, Address);
        			ps.setString(5,City );
        			ps.setString(6, Country);
        			ps.setString(7, postal_code);
        			ps.setString(8, telephone);
        			ps.setString(9, Fax);
        			ps.setString(10, emergency_contact_no);
        			ps.setString(11, email_id);
        			ps.setString(12, url1);
        			ps.setString(13, path_of_file);
        			
        			//ps.setBinaryStream(13,photo.getInputStream(),(int)photo.getSize());
        			ps.setString(13, year_of_foundation);
        			ps.setTimestamp(14,(Timestamp) new java.sql.Timestamp(new Date().getTime()));
        			ps.setString(15, state1);
        			LOGGER.info("date value");
        			/*(Timestamp) new java.sql.Timestamp(new Date().getTime())*/
        			//LOGGER.info("database query before execute:"+ps);
        			//System.out.println("insert org details ps="+ps);
        	       int n= ps.executeUpdate();
        	       LOGGER.info("database query:"+ps);
        	     LOGGER.info("integer value:"+n);
        	     System.out.println("insert org details n="+n);
        	       if (n != 0 )
        	       {
        	    	   
        	/*    	   String insertQuery="insert into users_info(username,user_id,password,email,address,contact_no,privilege,organization_id,created_by) values(?,?,?,?,?,?,?,?,?)";
              	     PreparedStatement prepareStatement = con.prepareStatement(insertQuery);
              	     //System.out.println("insert users info="+insertQuery);
           			LOGGER.info("the quary is:"+sql);
           			prepareStatement.setString(1,organization_name);
           			prepareStatement.setString(2, superAdminID);
           			prepareStatement.setString(3, genaratePassword);
           			prepareStatement.setString(4, email_id);
           			prepareStatement.setString(5,Address );
           			prepareStatement.setString(6, telephone);
           			prepareStatement.setString(7, "SuperAdmin");
           			prepareStatement.setString(8, organization_id);
           			prepareStatement.setString(9, "owner@kds");
           			int p= prepareStatement.executeUpdate();*/
           			//System.out.println("insert users info prepareStatement="+prepareStatement);
           		       /* if (p != 0 )
       	            {*/
           		        	//System.out.println("before sending setup info");
        	    	   sendMail(host,to); 
        	    	   String OrgStatus="Institution created successfully"+"#"+"  Information sent to your email ID";
          			request.setAttribute("OwnerSuccessMAil",OrgStatus);
          			RequestDispatcher rd=request.getRequestDispatcher("../JSP/SetupCustomer.jsp");
          			rd.forward(request, response);
        	     //  }
        	       }
       			  else {
       				String OrgStatus="Failure for creating institution";
          			request.setAttribute("OwnerFailure",OrgStatus);
          			RequestDispatcher rd=request.getRequestDispatcher("../JSP/SetupCustomer.jsp");
          			rd.forward(request, response);
       			  }
        	        //out.println("Organization details stores database Successfully.");
        	}
		}
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
				message.setText("Hi "+organization_name+" ,\n"  +"Welcome to KODE_DEV!!!\n "  +"Your Institution ID :"+ organization_id+"\n "+
						"\n\n"+"Thanks & Regards"+"\n"+"Kompac Digital Systems Pvt. Ltd."+"\n" +"Website : www.kompacdigit.com"); 
				//message.setText("SessionStartTime:"+SessionStartTime);
				//message.setText("SessionEndTime:"+SessionEndTime);
				//message.setText("Duration :"+hours);
				//LOGGER.info();
				//System.out.println("text=");
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
		
    	
    	
    	




