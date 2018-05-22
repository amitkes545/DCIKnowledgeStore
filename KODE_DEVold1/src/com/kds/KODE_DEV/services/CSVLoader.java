package com.kds.KODE_DEV.services;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;

import au.com.bytecode.opencsv.CSVReader;

/**
 * 
 * @author viralpatel.net
 * 
 */
public class CSVLoader {
	String host="" ;
	String pop="";
	int    portNumber;
	String emailId="";
	String password="";
	String link="";
	String userName;
	String userid;
	String pwd1;
	String privilege1;
	String classroomLink;
	StringBuilder studentName,studentId,privilize,email,studentPassword;
	ArrayList<AdminDomain> userInfo= new ArrayList<AdminDomain>();
	 AdminDomain adminDomain= new AdminDomain();
	private static final 
		String SQL_INSERT = "INSERT INTO ${table}(${keys}) VALUES(${values})";
	private static final String TABLE_REGEX = "\\$\\{table\\}";
	private static final String KEYS_REGEX = "\\$\\{keys\\}";
	private static final String VALUES_REGEX = "\\$\\{values\\}";
	private Connection connection;
	private char seprator;

	/**
	 * Public constructor to build CSVLoader object with
	 * Connection details. The connection is closed on success
	 * or failure.
	 * @param connection
	 */
	static final Logger LOGGER = Logger.getLogger(CSVLoader.class);
	public CSVLoader(Connection connection) {
		this.connection = connection;
		//Set default separator
		this.seprator = ',';
	}
	
	/**
	 * Parse CSV file using OpenCSV library and load in 
	 * given database table. 
	 * @param csvFile Input CSV file
	 * @param tableName Database table name to import data
	 * @param truncateBeforeLoad Truncate the table before inserting 
	 * 			new records.
	 * @throws Exception
	 */
	public String loadCSV(String csvFile, String tableName,
			boolean truncateBeforeLoad) throws Exception {
              String status="";
		CSVReader csvReader = null;
		
		SendEmailDao senderDao =new SendEmailDao();
		//session.removeAttribute("MsgValue");
		SenderEmailDetailsDomain senderDom =(SenderEmailDetailsDomain)senderDao.senderdetails();
		 emailId=senderDom.getEmailid();
		 password=senderDom.getPassword();
		 host= senderDom.getHost();
		 pop=senderDom.getPop();
		   portNumber=senderDom.getPort();
		   classroomLink=senderDao.getClassRoomLink();
		   link=senderDao.getKnowStoreLink();//selecting web room link from dao
		int n[];
				if(null == this.connection) {
			throw new Exception("Not a valid connection.");
		}
		try {
			
			csvReader = new CSVReader(new FileReader(csvFile), this.seprator);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error occured while executing file. "
					+ e.getMessage());
		}

		String[] headerRow = csvReader.readNext();

		if (null == headerRow) {
			throw new FileNotFoundException(
					"No columns defined in given CSV file." +
					"Please check the CSV file format.");
		}

		String questionmarks = StringUtils.repeat("?,", headerRow.length);
		questionmarks = (String) questionmarks.subSequence(0, questionmarks
				.length() - 1);

		String query = SQL_INSERT.replaceFirst(TABLE_REGEX, tableName);
		query = query
				.replaceFirst(KEYS_REGEX, StringUtils.join(headerRow, ","));
		query = query.replaceFirst(VALUES_REGEX, questionmarks);

		 LOGGER.info("Query: " + query);

		String[] nextLine;
		Connection con = null;
		PreparedStatement ps = null;
		String names="";
		ArrayList<String> mailid = new ArrayList<String>();
		String mail = null;
		ArrayList<String> list1=null;
		try {
			con = this.connection;
			con.setAutoCommit(false);
			ps = con.prepareStatement(query);
			
//			if(truncateBeforeLoad) {
//				//delete data from table before loading csv
//				con.createStatement().execute("DELETE FROM " + tableName);
//			}
			studentId=new StringBuilder();
			studentName=new StringBuilder();
			studentPassword=new StringBuilder();
			privilize=new StringBuilder();
			email=new StringBuilder();
			final int batchSize = 1000;
			int count = 0;
			Date date = null;
			while ((nextLine = csvReader.readNext()) != null) {
                  LOGGER.info("next value in csv:"+nextLine);
				if (null != nextLine) {
					int index = 1;
					for (String string : nextLine) {
						
						 LOGGER.info("string value in csv:"+string+ "index value :"+index);
						int count1=index-1;
						
						// LOGGER.info("names in files"+nextLine[count1]);
						//for(int i=0;i<count1;i++){
						//userName.add(nextLine[0]);
						//userId.add(nextLine[1]);
						//pwd.add(nextLine[2]);
						//privilege.add(nextLine[5]);
						//}
						
						 
						 //userInfo.add(adminDomain);
						String names1=nextLine[count1];
						if(names1.contains("."))
						{
							mail=names1;
							
						}
						//ar.add(mail);
						
						date = DateUtil.convertToDate(string);
						 LOGGER.info("names in file"+names1);
						if (null != date) {
							ps.setDate(index++, new java.sql.Date(date
									.getTime()));
						} else {
							ps.setString(index++, string);
						}
						
					}
					
					ps.addBatch();
					adminDomain.setAdminName(nextLine[0]);
					studentName.append(nextLine[0]+",");
					 adminDomain.setAdminId(nextLine[1]);
					 studentId.append(nextLine[1]+","); 
					 adminDomain.setPwd(nextLine[2]);
					 studentPassword.append(nextLine[2]+",");
					 adminDomain.setEmail(nextLine[3]);
					 email.append(nextLine[3]+",");
					 adminDomain.setPrivilege(nextLine[6]);
					 privilize.append(nextLine[6]+",");
					 userInfo.add(adminDomain);
					 LOGGER.info("username in domain:"+adminDomain.getAdminId());
				}
				 LOGGER.info("mail is:"+mail);	
				mailid.add(mail);
				 LOGGER.info("array list values:"+mailid.size());
				 LOGGER.info("student names in string builder::"+studentName);
				 LOGGER.info("student id in string builder::"+studentId);
				 LOGGER.info("student password in string builder::"+studentPassword);
				 LOGGER.info("student email in string builder::"+email);
				//list1.add(mail);
				if (++count % batchSize == 0) {
					ps.executeBatch();
					
				}
			}
			
			try{
	               n=	ps.executeBatch(); // insert remaining records
			
			if(n!=null){
				// LOGGER.info("student records inserted successfully");
				
				
				// LOGGER.info("username:"+userName + "userId:"+userId+ "pwd:"+pwd);
				/*for(int i=1;i<= userName.size();i++){
					 username=userName.get(1);
					// sendMail(host,to); 
				}
				for(int i=1;i<= userId.size();i++){
					 userid=userId.get(1);
					// sendMail(host,to); 
				}
				for(int i=1;i<= pwd.size();i++){
					 pwd1=pwd.get(1);
					// sendMail(host,to); 
				}*/
				/*for(int i=1;i<= userInfo.size();i++){
					// privilege1=privilege.get(1);
					// sendMail(host,to); 
					
				}*/
		
				String studentId1=studentId.substring(0,studentId.length()-1);
				String studentName1=studentName.substring(0, studentName.length()-1);
				String password1=studentPassword.substring(0,studentPassword.length()-1);
				String emailId=email.substring(0,email.length()-1);
				String privilize1=privilize.substring(0, privilize.length()-1);
				
				 LOGGER.info("student id::"+studentId1);
				 LOGGER.info("student name::"+studentName1);
				 LOGGER.info("password::"+password1);
				 LOGGER.info("emailId::"+emailId);
				 LOGGER.info("privilize1::"+privilize1);
				
				String studentId[]=studentId1.split(",");
				String studentName[]=studentName1.split(",");
				String password[]=password1.split(",");
				String emailId1[]=emailId.split(",");
				String privilize[]=privilize1.split(",");
				
						status="Inserted";
						String sendDetails="";
						String mailName="";
						 LOGGER.info("length in string builder::"+studentId.length);
				for(int i=0;i<studentId.length;i++){
					 LOGGER.info("i valu is"+i);
					//System.out.println("i value "+i);
					sendDetails="Hi  "+studentName[i]+"\n"+"your userId::"+studentId[i]+"&&"+"password :"+password[i]+"\nRole :" +" " +privilize[i]+",\n"+"\n"+"Kindly login into the below URL to start accessing it:\n"+link+"\nKindly login into classroom the below URL to start accessing it:\n"+classroomLink+"\n"+"Thanks & Regards"+"\n"+"Kompac Digital Systems Pvt. Ltd."+"\n" +"Website : www.kompacdigit.com";
					mailName=emailId1[i];
					sendMail(mailName,sendDetails);
					
				}
				
				
				
				 LOGGER.info("student records inserted successfully");
			}
			
			else {
				 LOGGER.info("failed for inserting student records");
				status="Failure";
			}
			con.setAutoCommit(true);
		}catch(BatchUpdateException batch){
			 LOGGER.info("batchupdateexception in csv reader");
			status="BatchException";
			con.rollback();
			//return status;		
			//batch.printStackTrace();
			
		}
		}catch (Exception e) {
			con.rollback();
			e.printStackTrace();
			throw new Exception(
					"Error occured while loading data from file to database."
							+ e.getMessage());
		}/* finally {
			if (null != ps)
				ps.close();
			if (null != con)
				//con.close();

			csvReader.close();
		}*/
		return status;
	}

	public char getSeprator() {
		return seprator;
	}

	public void setSeprator(char seprator) {
		this.seprator = seprator;
	}

	 public void sendMail(String mails,String text )
	   	{
	   	final String to="help.kds@gmail.com";
	   final String password="kompacdigital";
	   		 Properties props= new Properties();
	   		 props.put("mail.smtp.auth",pop);
	   	     props.put("mail.smtp.starttls.enable",pop);
	   		 props.put("mail.smtp.host",host);
	   		 props.put("mail.smtp.port",portNumber);
	   		 props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	   		 Session session= Session.getInstance(props, new javax.mail.Authenticator(){
	   			 protected PasswordAuthentication getPasswordAuthentication(){return new PasswordAuthentication(to,password);
	   			 																		} });
	   		 try
	   		 { 
	   			Message message= new MimeMessage(session);
	   			message.setFrom(new InternetAddress(to));
	   			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mails));
	   			//message.setSubject("Testing");
	   			//message.setText("UserID:"+emailid+ "Password:"+password);
	   			message.setSubject("User Information"); // Now set the actual message
	   			message.setText(text);
	   		/*	message.setText("Hi "+userName+" ,\n"  +"Welcome to KODE_DEV!!!\n "  +" Your user ID:"+ userid+" & "+ "Password :" +" " +pwd1+",\n"+"Role :" +" " +privilege1+",\n"  +" Kindly login into the below URL to start accessing it:\n"+link+
	   					"\n\n"+"Thanks & Regards"+"\n"+"Kompac Digital Systems Pvt. Ltd."+"\n" +"Website : www.kompacdigit.com"); */
	   			//message.setText("SessionStartTime:"+SessionStartTime);
	   			//message.setText("SessionEndTime:"+SessionEndTime);
	   			//message.setText("Duration :"+hours);
	   			// LOGGER.info();
	   			 Transport.send(message);
	   			 //System.out.println("send successfully");
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
