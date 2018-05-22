package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.FacilitatorBuildKnowLibDao;
import com.kds.KODE_DEV.domain.FacilitatorBuildKnowLibDomain;
import com.kds.KODE_DEV.util.PropertiesUtil;

@SuppressWarnings("serial")
public class FacilitatorBuildKnowServlet24june extends CommonService {

	static final Logger LOGGER = Logger.getLogger(FacilitatorBuildKnowServlet24june.class);

	String status = "";

	FacilitatorBuildKnowLibDao fDao = new FacilitatorBuildKnowLibDao();
	FacilitatorBuildKnowLibDomain fDomain = new FacilitatorBuildKnowLibDomain();

	@Override
	public void run() throws ServletException, IOException {

		session = request.getSession();

		String ksID = request.getParameter("ksid");
		String libName = request.getParameter("libname");
		int libSize = Integer.parseInt(request.getParameter("libsize"));

		LOGGER.info("ksid=" + ksID);

		FacilitatorBuildKnowLibDomain fDomain = new FacilitatorBuildKnowLibDomain();

		fDomain.setKsId(ksID);
		fDomain.setNewName(libName);
		fDomain.setLibSize(libSize);
		fDomain.setUserId(session.getAttribute("userid").toString());
		fDomain.setOrgId(session.getAttribute("orgid").toString());
String orgid=session.getAttribute("orgid").toString();
		// session.setAttribute("ksID", ksID);
		// session.getAttribute(ksID);
		session.setAttribute("libname", libName);
		// session.getAttribute("orgid");
		// session.getAttribute("userid");

		boolean isDuplicate = fDao.libDuplicate(fDomain);
		LOGGER.info("duplicate value is" + isDuplicate);

		if (isDuplicate) {
			String msg3 = "Library exist ! please try another library";
			session.setAttribute("FaceLibNew", msg3);
			response.sendRedirect("../JSP/FacilitatorBuildKnowLib.jsp");
		}else{

	
		String userId = session.getAttribute("userid").toString();
		String allLibSizeOfDb = fDao.selectAllLibSpace(userId);
		System.out.println("allLibSizeOfDb:" + allLibSizeOfDb);

		if (allLibSizeOfDb == null) {

			FTPClient ftpclient = new FTPClient();
			System.out.println("before connection");
			//String ftpServerAddress = "localhost";
			String ftpServerAddress = PropertiesUtil.getProperty("FTP_IP");
			//String ftpServerAddress = "ftp://220.225.125.221/"; 
			//String userName = "ftpuser1";
			//String password = "ftp@123#1";
			String userName =PropertiesUtil.getProperty("user1");
			String password =PropertiesUtil.getProperty("password1");	

			try {
				boolean result;
				ftpclient.connect(ftpServerAddress);
				System.out.println("after connection");
				result = ftpclient.login(userName, password);
				System.out.println("after connection1");
				// boolean result;

				/*
				 * FTPConnection ftpConnection = new FTPConnection(); FTPClient
				 * ftpClient = ftpConnection.connect();
				 */
				if (result == true)

					ftpclient.setFileType(FTP.BINARY_FILE_TYPE);
				String direPath = "/home/ftpkds1/KnowStore/"
						+ session.getAttribute("orgid") + '/'
						+ session.getAttribute("userid") + '/' + ksID + "/"
						+ libName;
				LOGGER.info(direPath);
				//System.out.println("Library path is:" + direPath);

				String[] pathElements = direPath.split("/");
				if (pathElements != null && pathElements.length > 0) {
					for (String direPath1 : pathElements) {
						LOGGER.info(pathElements + ":: pathElements");
						LOGGER.info(direPath1 + ":: direPath1");
						//ftpclient=(FTPClient)pathElements.toString();
						//pathElements=ftpclient.toString();
						boolean existed = ftpclient.changeWorkingDirectory(direPath1);
						LOGGER.info(direPath1 + " folder exists");
						LOGGER.info(existed + " existed");
						if (!existed) {
							boolean created = ftpclient.makeDirectory(direPath1);
							LOGGER.info(direPath1 + " folder created");
							if (created) {
								LOGGER.info("Directory created successfully !");
								System.out.println("Directory created successfully in first !");

							} else {
								LOGGER.info("Error in creating directory !");
								System.out.println("Error in creating directory in first !");

								//System.out.println("hello");
								String sTatus = fDao.insertLibInformation(fDomain);
								//System.out.println("insert ret 111111111111111111111111 ");
								LOGGER.info("sTatus="+sTatus);
								if ("valid".equalsIgnoreCase(sTatus)) {

									String msg1 = "Library created successfully";
									LOGGER.info("Library created successfully");
									request.setAttribute("FacultySuccess", msg1);
									RequestDispatcher requestDispatcher = request
											.getRequestDispatcher("../JSP/FacilitatorBuildKnowLib.jsp");
									requestDispatcher
											.forward(request, response);
									return;

								} else {

									String msg1 = "Failed to create library ! Please try again !";
									LOGGER.info("Failed to create library ! Please try again !");
									request.setAttribute("FacultyFailure", msg1);
									RequestDispatcher requestDispatcher = request
											.getRequestDispatcher("../JSP/FacilitatorBuildKnowLib.jsp");
									requestDispatcher
											.forward(request, response);
									return;
								}

							}
						}
					}
				}
			}

			catch (FTPConnectionClosedException e1) {
				e1.printStackTrace();

			} finally {
				try {
					ftpclient.logout();

					ftpclient.disconnect();
				} catch (FTPConnectionClosedException e2) {
					LOGGER.info(e2);
				}

			}
		}
		 int allLibSizeOfDbInt=0;
		 if(allLibSizeOfDb !=null){
		 allLibSizeOfDbInt = Integer.parseInt(allLibSizeOfDb);
		 }
		int facultyKnowSpace = fDao.selectFacultyKnowSpace(orgid);
		System.out.println("facultyKnowSpace:" + facultyKnowSpace);
		int facultyKnowSpaceInt = facultyKnowSpace;//Integer.parseInt(facultyKnowSpace);

		int remainingSize = facultyKnowSpaceInt - allLibSizeOfDbInt;
		System.out.println("before if");
		if (remainingSize >= libSize) {

			FTPClient ftpclient = new FTPClient();
			System.out.println("in if before connection");
			String ftpServerAddress =PropertiesUtil.getProperty("FTP_IP");
			//String ftpServerAddress = "localhost";
			//String ftpServerAddress = "ftp://220.225.125.221/";
			//String ftpServerAddress = "220.225.125.221";
		//	String userName = "ftpuser1";
		//	String password = "ftp@123#1";
			
			String userName =PropertiesUtil.getProperty("user1");
			String password =PropertiesUtil.getProperty("password1");	

			try {
				boolean result;
				System.out.println("before connection");
				ftpclient.connect(ftpServerAddress,21);
				System.out.println("after connection");
				result = ftpclient.login(userName, password);
				System.out.println("after connection11");
				// boolean result;

				/*
				 * FTPConnection ftpConnection = new FTPConnection(); FTPClient
				 * ftpClient = ftpConnection.connect();
				 */
				if (result == true)

					ftpclient.setFileType(FTP.BINARY_FILE_TYPE);
				String direPath = "/home/ftpkds1/KnowStore/"
						+ session.getAttribute("orgid") + '/'
						+ session.getAttribute("userid") + '/' + ksID + "/"
						+ libName;
				LOGGER.info(direPath);
				//System.out.println("Library path is:" + direPath);

				String[] pathElements = direPath.split("/");
				if (pathElements != null && pathElements.length > 0) {
					for (String direPath1 : pathElements) {
						LOGGER.info(pathElements + ":: pathElements1");
						LOGGER.info(direPath1 + ":: direPath11");
						boolean existed = ftpclient.changeWorkingDirectory(direPath1);
						LOGGER.info(direPath1 + " == exists");
						LOGGER.info(existed + ":: existed");
						if (!existed) {
							boolean created = ftpclient.makeDirectory(direPath1);
							LOGGER.info(direPath1 + "created");
							if (created) {
								LOGGER.info("Directory created successfully !");
								existed = ftpclient.changeWorkingDirectory(direPath1); // added by prity
								LOGGER.info(existed + "::existed1 after created");
								System.out.println("Directory created successfully !");

							} else {
								LOGGER.info("Error in creating directory !");
								System.out.println("Error in creating directory !");
							} // added by prity
						} // added by prity
					}// added by prity
				}// added by prity
								String sTatus = fDao.insertLibInformation(fDomain);
								//System.out.println("insert this one re 2222222222");
								LOGGER.info("sTatus="+sTatus);
								if ("valid".equalsIgnoreCase(sTatus)) {

									String msg1 = "Library created successfully";
									LOGGER.info(msg1);
									request.setAttribute("FacultySuccess", msg1);
									RequestDispatcher requestDispatcher = request
											.getRequestDispatcher("../JSP/FacilitatorBuildKnowLib.jsp");
									requestDispatcher
											.forward(request, response);

								} else {

									String msg1 = "Failed to create library ! Please try again !";
									LOGGER.info(msg1);
									request.setAttribute("FacultyFailure", msg1);
									RequestDispatcher requestDispatcher = request
											.getRequestDispatcher("../JSP/FacilitatorBuildKnowLib.jsp");
									requestDispatcher
											.forward(request, response);
								}

						//	}
						//}
					//}
			//	}
			}

			catch (FTPConnectionClosedException e1) {
				e1.printStackTrace();

			} finally {
				try {
					ftpclient.logout();

					ftpclient.disconnect();
				} catch (FTPConnectionClosedException e2) {
					LOGGER.info(e2);
				}

			}

		} else {

			String msg = "Sorry you don't have enough space !";
			session.setAttribute("LibSize", msg);
			response.sendRedirect("../JSP/FacilitatorBuildKnowLib.jsp");

		}

	}
	}
}