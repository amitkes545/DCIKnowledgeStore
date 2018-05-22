package com.kds.KODE_DEV.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.util.PropertiesUtil;



@SuppressWarnings("serial")
public class FacilitatorUploadFileServlet extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(FacilitatorUploadFileServlet.class);
	
	public void run() throws ServletException,IOException {
		
	                FTPClient ftpclient = new FTPClient();
	                FileInputStream fis = null;
	                boolean result;
	              // String ftpServerAddress = "localhost";
	                String ftpServerAddress = PropertiesUtil.getProperty("FTP_IP");
	               // String ftpServerAddress = "ftp://220.225.125.221/";
	         //       String userName = "ftpuser1";
	          //      String password = "ftp@123#1";

	                String userName =PropertiesUtil.getProperty("user1");
	    		String password =PropertiesUtil.getProperty("password1");	
	                
	                try {
	                        ftpclient.connect(ftpServerAddress);
	                        result = ftpclient.login(userName, password);

	                        if (result == true) {
	                                LOGGER.info("Logged in Successfully !");
	                        } else {
	                                LOGGER.info("Login Fail!");
	                                return;
	                        }
	                        ftpclient.setFileType(FTP.BINARY_FILE_TYPE);

	                        ftpclient.changeWorkingDirectory("/home/ftpkds1/");

	                        File file = new File("/home/paramvir/Documents/KODE_DEV-Product Screen Flow 1.0.pps");
	                        String testName = file.getName();
	                        fis = new FileInputStream(file);

	                        // Upload file to the ftp server
	                        result = ftpclient.storeFile(testName, fis);

	                        if (result == true) {
	                                LOGGER.info("File is uploaded successfully");
	                      
	                        } else {
	                                LOGGER.info("File uploading failed");
	                        }
	                        ftpclient.logout();
	                } catch (FTPConnectionClosedException e) {
	                        e.printStackTrace();
	                } 
	                catch (Exception e)
	        		{
	        			 RequestDispatcher rd=request.getRequestDispatcher("../JSP/error.jsp?errmsg="+e.toString());
	        	 		 rd.forward(request, response);
	        			e.printStackTrace();	
	        		}finally {
	                        try {
	                                ftpclient.disconnect();
	                        } catch (FTPConnectionClosedException e) {
	                                LOGGER.info(e);
	                        }
	                        

	                }
	        }	

 }

