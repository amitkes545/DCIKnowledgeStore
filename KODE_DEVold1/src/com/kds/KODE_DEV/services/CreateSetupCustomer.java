package com.kds.KODE_DEV.services;

import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;
import com.kds.KODE_DEV.util.PropertiesUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;
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
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.log4j.Logger;

public class CreateSetupCustomer
  extends CommonService
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String host = "";
  String pop = "";
  int port;
  String emailid = "";
  String pass = "";
  String to = "";
  String organization_name = "";
  String organization_id = "";
  String link = "";
  String superAdminID = "";
  String Org_Type_Name = "";
  String genaratePassword = "";
  
  public boolean duplicate(String orgid)
    throws SQLException
  {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    boolean result = false;
    try
    {
      con = DBTransaction.connect();
      
      String quary = "select organization_id from org_details where organization_id=?";
      
      ps = con.prepareStatement(quary);
      ps.setString(1, orgid);
      rs = ps.executeQuery();
      if (rs.next()) {
        result = true;
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return result;
  }
  
  public static String randomSeriesForThreeCharacter()
  {
    LOGGER.info("calling password method");
    Random random = new Random();
    LOGGER.info("random charactor:" + random);
    String value = "";
    for (int i = 0; i < 2; i++)
    {
      int random_Char = random.nextInt(74);
      value = value + random_Char;
    }
    return value;
  }
  
  static final Logger LOGGER = Logger.getLogger(CreateSetupCustomer.class);
  
  public void run()
    throws ServletException, IOException
  {
    String msg = null;
    try
    {
      DiskFileItemFactory factory = new DiskFileItemFactory();
      ServletFileUpload pic = new ServletFileUpload(factory);
      List<FileItem> items = pic.parseRequest(this.request);
      
      FileItem org_id = (FileItem)items.get(0);
      this.organization_id = org_id.getString();
      
      FileItem org_name = (FileItem)items.get(2);
      this.organization_name = org_name.getString();
      
      FileItem org_type = (FileItem)items.get(1);
      String organization_type = org_type.getString();
      
      FileItem sName = (FileItem)items.get(3);
      this.Org_Type_Name = sName.getString();
      
      this.organization_id = (organization_type.substring(0, 3) + this.Org_Type_Name.substring(0, 3) + this.organization_name.substring(0, 3));
      organization_id=removeSpaceFromUserid(this.organization_id);

      
      FileItem sId = (FileItem)items.get(4);
      this.superAdminID = sId.getString();
      
      this.superAdminID = this.organization_id;
      FileItem address = (FileItem)items.get(11);
      String Address = address.getString();
      
      FileItem country = (FileItem)items.get(8);
      String Country = country.getString();
      LOGGER.info("country value is:" + Country);
      
      FileItem state = (FileItem)items.get(9);
      String state1 = state.getString();
      
      FileItem city = (FileItem)items.get(10);
      String City = city.getString();
      
      FileItem pcode = (FileItem)items.get(12);
      String postal_code = pcode.getString();
      
      FileItem email = (FileItem)items.get(7);
      String email_id = email.getString();
      
      this.to = email_id;
      
      FileItem phone = (FileItem)items.get(6);
      String telephone = phone.getString();
      
      FileItem ecno = (FileItem)items.get(13);
      String emergency_contact_no = ecno.getString();
      
      FileItem fax = (FileItem)items.get(14);
      String Fax = fax.getString();
      
      FileItem url = (FileItem)items.get(15);
      String url1 = url.getString();
      
      FileItem photo = (FileItem)items.get(16);
      
      FileItem yof = (FileItem)items.get(17);
      String year_of_foundation = yof.getString();
      
      Iterator<FileItem> iter = items.iterator();
		System.out.println("iter="+iter);
		FileItem fileItem = iter.next();
		LOGGER.info("fileItem="+fileItem);
		System.out.println("fileItem="+fileItem);
		FileItem flItem = null;
		String fileName = null;
		String filePath = null;
		File storeFile = null;
		File file = null;
		 String download_path = System.getProperty("user.home") + "/Downloads";
	       String download_path1 = download_path.replace("\\", "/");
	       String s =System.getProperty ("os.name");
	       LOGGER.info("download_path= "+download_path);
	       LOGGER.info("download_path1= "+download_path1);
	       LOGGER.info("os name= "+s);
	       System.out.println("download_path="+download_path);
	       String [] win= s.split(" ");
	       String winname=win[0];
	      LOGGER.info("windows name= "+winname);
	       String uploadPath;
		 if(winname.equalsIgnoreCase("Windows"))
	      {
	      	//UPLOAD_DIRECTORY=download_path1;
	      	 uploadPath =  "/"+ download_path1;
	      }else{
	      	//UPLOAD_DIRECTORY="/home";
	      	uploadPath =  "/"+ download_path1;
	      	LOGGER.info("uploadpath:"+uploadPath);
	      }
		// String uploadPath;
      while (iter.hasNext()) 
    	  
      {
     	 fileItem= iter.next();
     	/* if(fileItem.getFieldName().contains("assessmentId")){
     		 assid = fileItem.getString();
     		 LOGGER.info("Assessment id fileservice "+assid);
     		 System.out.println("assessmentId="+fileItem.getString());
     	 }*/
     	 
       if (!fileItem.isFormField())   //file select field
       {
          flItem = fileItem;
         // fileName = new File(flItem.getName()).getName();
          filePath = uploadPath + File.separator +  this.organization_id +".png";
          storeFile = new File(filePath);
         LOGGER.info("filePath is="+filePath);
         LOGGER.info("1.storeFile is="+storeFile);
        System.out.println("filepath:..."+filePath);
        System.out.println("storeFile:..."+storeFile);
         //System.out.println("1.storeFile is="+storeFile);
         // saves the file on disk
        
         try {
				flItem.write(storeFile);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          file = new File(storeFile.getPath());
          System.out.println("file:.-----.."+file);
       }}
      uploadLogoOnFTP(this.organization_id,file);
      
      LOGGER.info("superadmin name:" + this.Org_Type_Name);
      LOGGER.info("year of foundation :" + year_of_foundation);
      
      SendEmailDao senderDao = new SendEmailDao();
      
      SenderEmailDetailsDomain senderDom = senderDao.senderdetails();
      this.emailid = senderDom.getEmailid();
      this.pass = senderDom.getPassword();
      this.host = senderDom.getHost();
      this.pop = senderDom.getPop();
      this.port = senderDom.getPort();
      this.link = senderDao.getWebLink();
      
     
      
      boolean isDuplicate = duplicate(this.organization_id);
      System.out.println("isDuplicate"+isDuplicate);
      if (isDuplicate)
      {
        msg = "UserId is exit!please try another";
        this.request.setAttribute("OwnerFailure", msg);
        RequestDispatcher rd = this.request.getRequestDispatcher("../JSP/SetupCustomer.jsp");
        rd.forward(this.request, this.response);
        return;
        
        
      }
      if (!isDuplicate)
      {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        LOGGER.info(timeStamp);
        
        Connection con = DBTransaction.connect();
        
        String password = randomSeriesForThreeCharacter();
        String OrgTypeName = "";
        if (OrgTypeName.length() > 3) {
          OrgTypeName = OrgTypeName.substring(0, 3);
        }
        this.genaratePassword = (OrgTypeName + password);
        
        LOGGER.info("password:" + password + "string with password:" + this.genaratePassword);
        String sql = "insert into org_details(organization_id,organization_name,org_type,address,city,country,postal_code, telephone,fax,emergency_contact_no,email,url,logo,year_of_foundation,date_time,state) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        PreparedStatement ps = con.prepareStatement(sql);
        
        LOGGER.info("the quary is:" + sql);
        ps.setString(1, this.organization_id);
        ps.setString(2, this.Org_Type_Name);
        ps.setString(3, organization_type);
        ps.setString(4, Address);
        ps.setString(5, City);
        ps.setString(6, Country);
        ps.setString(7, postal_code);
        ps.setString(8, telephone);
        ps.setString(9, Fax);
        ps.setString(10, emergency_contact_no);
        ps.setString(11, email_id);
        ps.setString(12, url1);
        
     //  ps.setBinaryStream(13, photo.getInputStream(), (int)photo.getSize());
        ps.setString(13, this.organization_id+".png");
        ps.setString(14, year_of_foundation);
        ps.setTimestamp(15, new Timestamp(new Date().getTime()));
        ps.setString(16, state1);
        LOGGER.info("date value");
        
        int n = ps.executeUpdate();
        LOGGER.info("database query:" + ps);
        LOGGER.info("integer value:" + n);
        String subject="Welcome to KODE_DEV";
        String message="Hi " + this.organization_name + ","+"<br/><br/>" + "Welcome to KODE_DEV !!"+"<br/><br/> " + "Please find your Customer ID :" + this.organization_id + "<br/> "
  		      +"Kindly login into the URL to start accessing the product: "+"<a href='https://www.kompacdigit.com:8443/KODE_DEV'>https://www.kompacdigit.com:8443/KODE_DEV</a>"+
  		        "<br/><br/>" + "Thanks and Regards" + "<br/>" + "Knowledge Store Technical Team"+"<br/><br/>"+"For any technical assistance please contact on the details provided below:" + "<br/><br/>"
  		     +   "<strong>"+"Toll Free No:"+"</strong>"+" 1800123456"+"<br/>"+"<strong>"+"SMS:"+"</strong>"+" 1234567891"+"<br/>"+"<strong>"+"Email ID:"+"</strong>"+" <a href='mailto:technicalsupport@kompaceducation.com'>technicalsupport@kompaceducation.com</a>";
    
        if (n != 0)
        {
          //sendMail(this.host, this.to);
        	Mailer.send(this.to,subject,message);
          String OrgStatus = "New customer successfully configured";
          this.request.setAttribute("OwnerSuccessMAil", OrgStatus);
          RequestDispatcher rd = this.request.getRequestDispatcher("../JSP/SetupCustomer.jsp");
          rd.forward(this.request, this.response);
        }
        else
        {
          String OrgStatus = "Failure for creating institution";
          this.request.setAttribute("OwnerFailure", OrgStatus);
          RequestDispatcher rd = this.request.getRequestDispatcher("../JSP/SetupCustomer.jsp");
          rd.forward(this.request, this.response);
        }
      }
    }
    catch (Exception e)
    {
    	String OrgStatus = "Failure for creating institution";
        this.request.setAttribute("OwnerFailure", OrgStatus);
        RequestDispatcher rd = this.request.getRequestDispatcher("../JSP/SetupCustomer.jsp");
        rd.forward(this.request, this.response);
      e.printStackTrace();
    }
  }
  
  public void sendMail(String host1, String to)
  {
    Properties props = new Properties();
	/*props.put("mail.smtp.socketFactory.port", port);
	props.put("mail.smtp.socketFactory.class",
			"javax.net.ssl.SSLSocketFactory");
	props.put("mail.smtp.port", port);*/
System.out.println("before port");
    props.put("mail.smtp.auth", this.pop);
    props.put("mail.smtp.socketFactory.port", port);
    System.out.println("after port");
   // props.put("mail.smtp.starttls.enable", this.pop);
    props.put("mail.smtp.host", host1);
    props.put("mail.smtp.port", Integer.valueOf(this.port));
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
   // Session session = Session.getInstance(props);
    Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(emailid, pass);
				}
			});
    try
    {
//    	 String msg="Hello " + this.organization_name + ","+"<br/><br/>" + "Welcome to KODE_DEV!!!"+"<br/><br/> " + "Please find your Customer ID: " + this.organization_id + "<br/> "
//    		      +"Kindly login into the URL to start accessing the product: "+"<a href='https://www.kompacdigit.com:8443/KODE_DEV/'>https://www.kompacdigit.com:8443/KODE_DEV/</a>"+
//    		        "<br/><br/>" + "Thanks and Regards" + "<br/>" + "Knowledge Store Technical Team"+"<br/><br/>"+"For any technical assistance please contact on the details provided below:" + "<br/>"
//    		     +   "<strong>"+"Toll Free No:"+"</strong>"+" 1800123456"+"<br/>"+"<strong>"+"SMS:"+"</strong>"+" 1234567891"+"<br/>"+"<strong>"+"Email ID:"+"</strong>"+" <a href='mailto:technicalsupport@kompaceducation.com'>technicalsupport@kompaceducation.com</a>";
//    		     
     // Message message = new MimeMessage(session);
    	MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(this.emailid));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
      
      message.setSubject("User Information");
      /*Hi XYZ,

      Welcome to KODE_DEV !!

      Please find your Customer ID:
      You have been assigned to: <CS01>  course by <user>. Kindly login into the URL to start accessing the product: http://www.kompacdigit.com:8443/KODE_DEV/

      Thanks and Regards
      Knowledge Store Technical Team

      For any technical assistance please contact on the details provided below:
      Toll Free No: 1800123456
      SMS: 
      Email ID: techicalsupport@kompaceducation.com"*/

     // message.setContent(msg, "text/html; charset=utf-8");
      Transport.send(message);
      LOGGER.info("send successfully");
    }
    catch (MessagingException e)
    {
      throw new RuntimeException(e);
    }
  }
  private String removeSpaceFromUserid(String str) {
		if (str != null)
			str = str.replaceAll("\\s+","");
		return str;
	}
  private void uploadLogoOnFTP(String org_id,File file) throws FileUploadException, SocketException, IOException, ServletException
  {
	 String filename=org_id+".png";
	//	FileItem flItem = null;
		FileItem ksID = null;
		FileItem libId= null;
		
		String filePath = null;
		File storeFile = null;
		String fileName = null;
		
		//int size=0;
		String testName = null;
		String s1="";
	  final int THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB
		final int MAX_FILE_SIZE = 1024 * 1024 * 100; // 40MB
		final int MAX_REQUEST_SIZE = 1024 * 1024 * 100; // 50MB
		 session = request.getSession();
		    String msg = null;
		    String orgid = (String) session.getAttribute("orgid");
		    System.out.println("orgid="+orgid);
		    FileInputStream fis = null;
			boolean result;
			//String ftpServerAddress = "localhost";
			//String ftpServerAddress ="61.12.44.18";
			String ftpServerAddress = PropertiesUtil.getProperty("FTP_IP");
			//String ftpServerAddress = "ftp://220.225.125.221/";
			/*String userName = "ftpuser1";
			String passwordftp = "ftp@123#1"*/
			String userName =PropertiesUtil.getProperty("user1");
			String passwordftp =PropertiesUtil.getProperty("password1");	
			FTPClient ftpclient = new FTPClient();
			  DiskFileItemFactory factory = new DiskFileItemFactory();
			 factory.setSizeThreshold(THRESHOLD_SIZE);
				factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setFileSizeMax(MAX_FILE_SIZE);
				upload.setSizeMax(MAX_REQUEST_SIZE);
//try{
				// constructs the directory path to store upload file
				ServletFileUpload pic = new ServletFileUpload(factory);
				 List<FileItem> items = pic.parseRequest(this.request);
				//String uploadPath = "home/paramvir/" + UPLOAD_DIRECTORY;
				 String download_path = System.getProperty("user.home") + "/Downloads";
		       String download_path1 = download_path.replace("\\", "/");
		       String s =System.getProperty ("os.name");
		       LOGGER.info("download_path= "+download_path);
		       LOGGER.info("download_path1= "+download_path1);
		       LOGGER.info("os name= "+s);
		       System.out.println("download_path="+download_path);
		       String [] win= s.split(" ");
		       String winname=win[0];
		      LOGGER.info("windows name= "+winname);
		       String uploadPath;
		      if(winname.equalsIgnoreCase("Windows"))
		      {
		      	//UPLOAD_DIRECTORY=download_path1;
		      	 uploadPath =  "/"+ download_path1;
		      }else{
		      	//UPLOAD_DIRECTORY="/home";
		      	uploadPath =  "/"+ download_path1;
		      	LOGGER.info("uploadpath:"+uploadPath);
		      }

				File uploadDir = new File(uploadPath);
				System.out.println("uploadDir="+uploadDir);
				if (!uploadDir.exists()) {
					uploadDir.mkdir();
				}
				System.out.println("before list");
				//List items = upload.parseRequest(request);
				System.out.println("alter list"+items);
				
				Iterator<FileItem> iter = items.iterator();
				System.out.println("iter="+iter);
				//FileItem fileItem = iter.next();
//				LOGGER.info("fileItem="+fileItem);
				//System.out.println("fileItem="+fileItem);
//				 while (iter.hasNext()) 
//			      {
//			     	 fileItem= iter.next();
//			     	/* if(fileItem.getFieldName().contains("assessmentId")){
//			     		 assid = fileItem.getString();
//			     		 LOGGER.info("Assessment id fileservice "+assid);
//			     		 System.out.println("assessmentId="+fileItem.getString());
//			     	 }*/
			     	 
			   //    if (!fileItem.isFormField())   //file select field
			     //  {
			         // flItem = fileItem;
			       //   fileName = new File(flItem.getName()).getName();
				  System.out.println("filename:"+filename);
			          filePath = uploadPath + File.separator + filename;
			          storeFile = new File(filePath);
			         LOGGER.info("filePath is="+filePath);
			         LOGGER.info("1.storeFile is="+storeFile);
			        System.out.println("filepath:"+filePath);
			         //System.out.println("1.storeFile is="+storeFile);
			         // saves the file on disk
			        /* try {
							flItem.write(storeFile);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			         File file = new File(storeFile.getPath());*/
						//size = (int) file.length();
					//	LOGGER.info("file=="+file);
					//	double avail_space = ksfDao.CheckLibSize(orgid,libidstring);
						//System.out.println("avail_space="+avail_space);
						//LOGGER.info("avail_space in lib="+avail_space);
						//LOGGER.info("size of file="+size);
						
//			      }   //file select field end
			//  }   //while end
			//}  //else end


				try {
					ftpclient.connect(ftpServerAddress);
					result = ftpclient.login(userName, passwordftp);
					System.out.println("after connection result="+result);
					if (result) {
						LOGGER.info("Logged in Successfully !");

						ftpclient.setFileType(FTP.BINARY_FILE_TYPE); //commented by prity
						 LOGGER.info("filePath1 in try="+filePath);
						String str = filePath.toString();
						LOGGER.info("str="+str);
						System.out.println(" filePath1="+str);
						String[] pathElements = str.split("/");
						
											for(int i=1;i<pathElements.length-1;i++)
						{
							s1=s1+"/"+pathElements[i];
							LOGGER.info("s1 under for="+s1);
						}
										System.out.println("before s1="+s1);
										System.out.println("after s1="+s1);
						LOGGER.info("file full path in service:"+s1);
						System.out.println("file full path in service:"+s1);
						s1 = "/home/ftpkds1/KnowStore/"
								+ session.getAttribute("orgid") + '/'
								+ "Logo"; 
								
						System.out.println("directoryPath="+s1);
					String	pathElements1[]=s1.split("/");
						if (pathElements1 != null && pathElements1.length  > 0) {
							for (String singleDir : pathElements1) 
							{
								System.out.println("singleDir="+singleDir);
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
					
					System.out.println("file in:"+file);
					LOGGER.info(file);
					System.out.println("filename in service:"+filename);
					testName = filename;
					LOGGER.info(testName);
					fis = new FileInputStream(file);
			       System.out.println("ftp path in service:"+fis);
			       System.out.println("testName in service:"+testName);
					// Upload file to the ftp server
					result = ftpclient.storeFile(testName, fis);

					// DBTransaction dbTransaction = new DBTransaction();

					if (result == true) {
						LOGGER.info("FTP success");
			//addedd by prity from here
						LOGGER.info("result="+result);
						String extension="";
						System.out.println("testName="+testName);
						int ind=testName.lastIndexOf(".");
							if(ind!=-1) 
							extension = testName.substring(ind); 
							System.out.println("ext="+extension);
							if(extension.equalsIgnoreCase(".mp4") || extension.equalsIgnoreCase(".pdf")) {
								System.out.println("in if="+testName);
								
								//File f = new File("C:\\Apache24\\htdocs\\"+testName);
								//File f = new File("/var/www/html/KODE_DEV/"+testName);
								File f = new File("/usr/tomcat7/webapps/KODE_DEV/Image/");
								// /var/www/HTML/video/sample.mp4
								if(!(f.exists() && f.isFile())){
									LOGGER.info("File not exists in local dir");
						//Files.copy(new File("G:\\ftp\\home\\ftpkds1\\KnowStore\\"+session.getAttribute("orgid")+"\\"+ksID.getString() +"\\"+session.getAttribute("userid")+"\\"+libId.getString()+"\\"+testName).toPath(),new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\webapps\\ROOT\\"+testName).toPath());
									/*File sourceFile = new File("/home/ftpuser1/home/ftpkds1/KnowStore/"+session.getAttribute("orgid")+"/"+ksID.getString() +"/"+session.getAttribute("userid")+"/"+libId.getString()+"/"+testName);
								    InputStream iStream = new FileInputStream(sourceFile);
								    
								    File targetFile = new File("/var/www/html/KODE_DEV/"+testName);
								    OutputStream tStream = new FileOutputStream(targetFile);*/
									
								    System.out.println("path s1=home/ftpkds1/KnowStore/srm/KS387/faculty@srm/Lib16");
								    LOGGER.info("before source : /home/ftpuser1/home/ftpkds1/KnowStore/"+session.getAttribute("orgid")+"/"+ksID.getString() +"/"+session.getAttribute("userid")+"/"+libId.getString()+"/"+testName);
								    String source="/home/ftpuser1/home/ftpkds1/KnowStore/"+session.getAttribute("orgid")+"/"+"Logo" +"/"+testName;
								    //String source="/home/kes2/Downloads/"+testName;
								    System.out.println("source==="+source);
								    LOGGER.info("source path="+ source);
									//Files.copy(new File("/home/ftpuser1/home/ftpkds1/KnowStore/"+session.getAttribute("orgid")+"/"+ksID.getString() +"/"+session.getAttribute("userid")+"/"+libId.getString()+"/"+testName).toPath(),new File("/var/www/html/KODE_DEV/"+testName).toPath());
									//Files.copy(new File(source).toPath(),new File("/usr/tomcat7/webapps/KODE_DEV/Assets/"+testName).toPath());
								 try{  
								//	 Files.copy(new File(source).toPath(),new File("/usr/tomcat7/webapps/KODE_DEV/Logo/"+testName).toPath(),StandardCopyOption.REPLACE_EXISTING);
								 
								 
								 }catch(Exception e){
									 LOGGER.info(e);
									// LOGGER.info(e.getLocalizedMessage());
								 }
								    LOGGER.info("file copied");
									//Files.copy(new File("C:/PRITY/abc.txt").toPath(), new File("E:/PRTy/sss/txt").toString());
								    //Files.copy(iStream,tStream);
						///home/ftpkds1/KnowStore/"	+ session.getAttribute("orgid")+'/'+ksID.getString() +'/'+session.getAttribute("userid")+'/'+ libId.getString()
						//C:\Program Files\Apache Software Foundation\Tomcat 7.0\webapps\ROOT
						//G:\ftp\home\ftpkds1\KnowStore\InsComBal
								} else{
									 msg = "File already exists on server  !!";
								request.setAttribute("FacultyFailure", msg);
								ftpclient.logout();
								RequestDispatcher requestDispatcher = request.getRequestDispatcher("../JSP/FacilitatorKnowSetup.jsp");
								requestDispatcher.forward(request, response);
								}
							}
							LOGGER.info("File is uploaded successfully");
					}else {
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
			      
			      LOGGER.info("superadmin name:" + this.Org_Type_Name);
			    //  LOGGER.info("year of foundation :" + year_of_foundation);
			    
  //}// if end
//}// while end
/*} catch (Exception e)
{
	String OrgStatus = "Failure for creating institution";
    this.request.setAttribute("OwnerFailure", OrgStatus);
    RequestDispatcher rd = this.request.getRequestDispatcher("../JSP/SetupCustomer.jsp");
    rd.forward(this.request, this.response);
  //e.printStackTrace();
}*/
}
}