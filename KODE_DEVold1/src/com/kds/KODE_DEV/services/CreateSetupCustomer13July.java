package com.kds.KODE_DEV.services;

import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

public class CreateSetupCustomer13July
  extends CommonService
{
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
  
  static final Logger LOGGER = Logger.getLogger(CreateSetupCustomer13July.class);
  
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
        
        ps.setBinaryStream(13, photo.getInputStream(), (int)photo.getSize());
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
  		        "<br/><br/>" + "Thanks and Regards" + "<br/>" + "Knowledge Store Technical Team"+"<br/><br/>"+"For any technical assistance please contact on the details provided below:" + "<br/>"
  		     +   "<strong>"+"Toll Free No:"+"</strong>"+" 1800123456"+"<br/>"+"<strong>"+"SMS:"+"</strong>"+" 1234567891"+"<br/>"+"<strong>"+"Email ID:"+"</strong>"+" <a href='mailto:technicalsupport@kompaceducation.com'>technicalsupport@kompaceducation.com</a>";
    
        if (n != 0)
        {
          //sendMail(this.host, this.to);
        	Mailer.send(this.to,subject,message);
          String OrgStatus = "New customer sucessfully configured";
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

}
