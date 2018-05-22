package com.kds.KODE_DEV.services;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.FacilitatorManageLibFtpDao;
import com.kds.KODE_DEV.dao.FacilitatorUpdateLibDao;
import com.kds.KODE_DEV.domain.FacilitatorManageKnowStoreDomain;
import com.kds.KODE_DEV.util.PropertiesUtil;

@SuppressWarnings("serial")
public class FacilitatorManageLibServlet2 extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(FacilitatorManageLibServlet2.class);

	String status = "";
	String filePath = null;
	File storeFile = null;
	String fileName = null;

	@SuppressWarnings("unused")
	@Override
	public void run() throws ServletException, IOException {
		 boolean success =false;
		session = request.getSession();
        String libId = request.getParameter("libid");
		String ksID = request.getParameter("ksid");
		String oldlib = request.getParameter("oldlib");
		String libName = request.getParameter("libname");
		int libSize =Integer.parseInt(request.getParameter("libsize"));
		String userId = request.getParameter("userid");
		String orgid = request.getParameter("orgid");
		
		

		LOGGER.info("ksid=" + ksID);

		FacilitatorManageKnowStoreDomain fDomain = new FacilitatorManageKnowStoreDomain();
        fDomain.setLibId(libId);
		fDomain.setKsId(ksID);
		fDomain.setLibName(libName);
		fDomain.setLibSize(libSize);
		fDomain.setOrgId(orgid);
		fDomain.setUserId(userId);
		//fDomain.setStudentId(studentid);
	
		//session.setAttribute("ksID", ksID);
		session.getAttribute(ksID);
		session.setAttribute("libId", libId);

		FacilitatorManageLibFtpDao fDao = new FacilitatorManageLibFtpDao();

		String sTatus = fDao.updateValues(fDomain);

		String msg = null;

		// if(a == true){
		if ("valid".equals(sTatus)) {

			//String ksID1 = (String) session.getAttribute(ksID);
		//	LOGGER.info(ksID1);
			FacilitatorUpdateLibDao   ffDao = new FacilitatorUpdateLibDao();      
			FTPClient ftpclient = new FTPClient();
			boolean result; 
			//String ftpServerAddress = "localhost";
			String ftpServerAddress = PropertiesUtil.getProperty("FTP_IP");
			//String ftpServerAddress = "ftp://220.225.125.221/";
		//	String userName = "ftpuser1";
		//	String password = "ftp@123#1";

			String userName =PropertiesUtil.getProperty("user1");
			String password =PropertiesUtil.getProperty("password1");	
			
			try {
				ftpclient.connect(ftpServerAddress);
				result = ftpclient.login(userName, password);

				if (result == true) {
					LOGGER.info("Logged in Successfully !");

					ftpclient.setFileType(FTP.BINARY_FILE_TYPE);
					
					  success = ftpclient.rename(oldlib, libName);
			            if (success) {
			                LOGGER.info(oldlib + " was successfully renamed to: "+ libName);
			            
			                fDomain.setLibName(libName);  
			          
			            } else {
			                LOGGER.info("Failed to rename: " + libName);
			            }
					
					
					//String direPath = "/home/ftpkds1/KnowStore/SRM/Admin/"
							//+ ksID + "/" + nameoflib;
					//LOGGER.info(direPath);
					/*String[] pathElements = direPath.split("/");
					if (pathElements != null && pathElements.length > 0) 
					{
						for (String direPath1 : pathElements) 
						{

							boolean existed = ftpclient
									.changeWorkingDirectory(direPath1);
							LOGGER.info(direPath1 + "exists");
							if (!existed) {
								boolean created = ftpclient
										.makeDirectory(direPath1);
								LOGGER.info(direPath1 + "created");
								if (created) {
									System.out
											.println("Directory created successfully !");


								} else {
									System.out
											.println("Error in creating directory !");
								}

							}
						}
					}*/
				}
			} catch (FTPConnectionClosedException e1) {
				e1.printStackTrace();

			} finally {
				try {
					ftpclient.logout();

					ftpclient.disconnect();
				} catch (FTPConnectionClosedException e2) {
					LOGGER.info(e2);
				}
			}

			msg = "success";
			session.setAttribute("MsgValue", msg);

			response.sendRedirect("../JSP/success.jsp");

		}
	}
}