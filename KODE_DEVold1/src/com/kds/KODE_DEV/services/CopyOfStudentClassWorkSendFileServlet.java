package com.kds.KODE_DEV.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.log4j.Logger;
import org.apache.commons.fileupload.FileItem;

import com.kds.KODE_DEV.dao.StudentClassWorkFileDao;
import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.ClassWorkFileDomain;
import com.kds.KODE_DEV.util.PropertiesUtil;

@SuppressWarnings("serial")
public class CopyOfStudentClassWorkSendFileServlet extends CommonService {
	
	
	static final Logger LOGGER = Logger.getLogger(CopyOfStudentClassWorkSendFileServlet.class);
	@Override
	public void run() throws ServletException, IOException {
		System.out.println("in service");
	Connection con = null;
	String userid = "";
	String emailid = "";
	//String password = "";
	String host = "";
	String pop = "";
	int port;
	String ksId = "";
	
	//String directoryPath = null;
	String status = "";
	String filePath = null;
	File storeFile = null;
	String fileName = null;
/*	private static String UPLOAD_DIRECTORY = "Upload";
	private static final int THRESHOLD_SIZE = 1024 * 1024 * 1024 * 3; // 3MB added by prity 1024
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 1024 * 5; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 1024 * 5; // 50MB
	private static final FileInputStream ROOT = null;*/
	
	FileItem flItem;
	FileItem ksID = null;
	FileItem libId= null;
	
	ClassWorkFileDomain aksDomain = new ClassWorkFileDomain();
	StudentClassWorkFileDao ksDao = new StudentClassWorkFileDao();

	String filename = request.getParameter("filename");
		
		FTPClient ftpclient = new FTPClient();
		FileInputStream fis = null;
		boolean result;
		//String ftpServerAddress = "localhost";
		String ftpServerAddress=PropertiesUtil.getProperty("FTP_IP");
		//String ftpServerAddress = "220.225.125.221";

		/*String userName = "ftpuser1";
		String password = "ftp@123#1";*/
		String userName =PropertiesUtil.getProperty("user1");
		String password =PropertiesUtil.getProperty("password1");	
		String testName = null;
		try {
			System.out.println("in try");
			con = DBTransaction.connect();
			ftpclient.connect(ftpServerAddress,21);
			System.out.println("After conn");
			LOGGER.info("after connect");

			//result = true;//ftpclient.login(userName, password);
			result = ftpclient.login(userName, password);
			LOGGER.info("after login");
			System.out.println("after login");
			if (result) {
				System.out.println("after ture");
				LOGGER.info("after true");
				LOGGER.info("Logged in Successfully !");

				ftpclient.setFileType(FTP.BINARY_FILE_TYPE);

				if (true) {

					String ftpDirePath = "/home/ftpkds1/KnowStore/"
							+ "orgid" + '/'
							+ "userid" + '/';
					
					File uploadDir = new File(ftpDirePath);
					System.out.println("uploadDir="+uploadDir);
					if (!uploadDir.exists()) {
						uploadDir.mkdir();
					}
					filePath = ftpDirePath + File.separator + filename;
					System.out.println("filePath="+filePath);
					storeFile = new File(filePath);
					System.out.println("storeFile="+storeFile);
					LOGGER.info("ftpDirePath="+ftpDirePath);
					System.out.println("ftpDirePath="+ftpDirePath);
						File file = new File(storeFile.getPath());
						LOGGER.info(file);
						testName = filename;
						System.out.println("file="+file);
						/*directoryPath = "/home/ftpkds1/KnowStore/"
								+ "orgid" + '/'
								+ ksID.getString() + '/'
								+ userid + '/'
								+ libId.getString();*/

						String pathElements1[] = ftpDirePath.split("/");
						if (pathElements1 != null
								&& pathElements1.length > 0) {
							for (String singleDir : pathElements1) {
								//System.out.println("in  for");
								System.out.println("ftpclient="+ftpclient);
								boolean existed = ftpclient.changeWorkingDirectory(singleDir);
								System.out.println("existed="+existed);
								LOGGER.info("CurrentDir: " + singleDir);

								if (!existed) {
									boolean created = ftpclient.makeDirectory(singleDir);
									System.out.println("created="+created);
									ftpclient.changeWorkingDirectory(singleDir);
									LOGGER.info("CurrentDir: " + singleDir);
									if (created) {
										LOGGER.info(singleDir);
										LOGGER.info("Directory created successfully !");
									} else {
										LOGGER.info("Error in creating directory !");
									}
								}
							}

						}

						LOGGER.info("testName="+testName);
						fis = new FileInputStream(file);
						
						System.out.println("Calling ftpclient.storeFile()  "+fis);
						// Upload file to the ftp server
						LOGGER.info("fis="+fis);
						result = ftpclient.storeFile(testName, fis);
						System.out.println("ftpclient.storeFile()  Executed");
						if (result == true) {
							LOGGER.info("FTP success");
							LOGGER.info("result="+result);
							System.out.println("testName="+testName);
								
								
								// till here
							LOGGER.info("File is uploaded successfully");
						} else {
							LOGGER.info("File uploading failed");
						}
						ftpclient.logout();
					}
				}
			 else {
				LOGGER.info("Login Fail!");
				return;
			}
		
		
		session = request.getSession(false);
		
		System.out.println("filename=" + filename);

		aksDomain.setFileName(filename);
		aksDomain.setUploadedBy("faculty@srm");//session.getAttribute("userid").toString());
		aksDomain.setSessionID("SES80");
		aksDomain.setFilePath(testName);

		System.out.println("after set");
		System.out.println(aksDomain.getFileName());
	//	session.setAttribute("usersID", userID);


					String retStatus =ksDao.insertClassWorkFileInformation(aksDomain);
					String msg="";
					if ("success".equals(retStatus)) {
						 msg = "Successfully sent !";

						//request.setAttribute("SuperAdminSuccess", msg);

					} else if ("failure".equals(retStatus)) {

						 msg = "Failed to send file! Please try again ! ";

					}
					RequestDispatcher requestDispatcher = request
							.getRequestDispatcher("../JSP/KSStudentView.jsp?message="+msg);
					requestDispatcher.forward(request, response);
		} 
		catch(FTPConnectionClosedException e1) {
			e1.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();} 
		 finally {
			try {
				ftpclient.disconnect();
			} catch (FTPConnectionClosedException e2) {
				LOGGER.info(e2);
			}
		}
	}
}
