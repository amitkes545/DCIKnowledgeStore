package com.kds.KODE_DEV.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.FacilitatorknowSetUpDao;
import com.kds.KODE_DEV.dao.KnowStoreSizeGetDao;
import com.kds.KODE_DEV.domain.FacilitatorKAssetsReportDomain;
import com.kds.KODE_DEV.util.PropertiesUtil;

@SuppressWarnings("serial")
public class AdminFileShareKnowStoreServlet extends CommonService {

	static final Logger LOGGER = Logger.getLogger(AdminFileShareKnowStoreServlet.class);

	KnowStoreSizeGetDao kDao = new KnowStoreSizeGetDao();

	private static String UPLOAD_DIRECTORY = "";
	private static final int THRESHOLD_SIZE = 1024 * 1024 * 3;//3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;//40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;//50MB

	String directoryPath = null;
	String status = "";
	String filePath = null;
	File storeFile  = null;
	String fileName = null;
	FileItem flItem;

	String ksID = null;
	String departMent = null;
	String subject = null;
	String description = null;
	String recipientType = "";

	public static long[] calculateDirectoryInfo(FTPClient ftpClient,
			String parentDir, String currentDir) throws IOException {
		long[] info = new long[3];
		long totalSize = 0;
		int totalDirs = 0;
		int totalFiles = 0;

		String dirToList = parentDir;
		if (!currentDir.equals("")) {
			dirToList += "/" + currentDir;
		}

		try {
			FTPFile[] subFiles = ftpClient.listFiles(dirToList);

			if (subFiles != null && subFiles.length > 0) {
				for (FTPFile aFile : subFiles) {
					String currentFileName = aFile.getName();
					if (currentFileName.equals(".")
							|| currentFileName.equals("..")) {
						// skip parent directory and the directory itself
						continue;
					}

					if (aFile.isDirectory()) {
						totalDirs++;
						long[] subDirInfo = calculateDirectoryInfo(ftpClient,
								dirToList, currentFileName);
						totalDirs += subDirInfo[0];
						totalFiles += subDirInfo[1];
						totalSize += subDirInfo[2];
					} else {
						totalSize += aFile.getSize();
						totalFiles++;
					}
				}
			}

			info[0] = totalDirs;
			info[1] = totalFiles;
			info[2] = totalSize;

			return info;
		} catch (IOException ex) {
			throw ex;
		}
	}
////////////////////////////////////////////////////////////////////////////////////////
	public void run() throws Exception {

		session = request.getSession();

		String username = (String) session.getAttribute("username");
		LOGGER.info(username);
		String userid = (String)session.getAttribute("userid");
		String orgid = (String)session.getAttribute("orgid");

		String testName = null;
		boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
		if (isMultiPart) {

			@SuppressWarnings("unused")
			PrintWriter writer = response.getWriter();
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(THRESHOLD_SIZE);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setSizeMax(MAX_REQUEST_SIZE);

			String s = System.getProperty("os.name");
			LOGGER.info("os name= " + s);
			String[] win = s.split(" ");
			String winname = win[0];

			LOGGER.info("windows name= " + winname);
			String uploadPath;
			if (winname.equalsIgnoreCase("Windows")) {
				UPLOAD_DIRECTORY = "/Windows/Temp";
				uploadPath = "C:/" + UPLOAD_DIRECTORY;
			} else {
				UPLOAD_DIRECTORY = "tmp";
				uploadPath = "/" + UPLOAD_DIRECTORY;
			}

			LOGGER.info("upload path= " + uploadPath);

			File uploadDir = new File(uploadPath);

			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			@SuppressWarnings("rawtypes")
			List items = upload.parseRequest(request);
			@SuppressWarnings("unchecked")
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem fileItem = iter.next();

				if (fileItem.getFieldName().equals("ksid")) {
					ksID = fileItem.getString();
					LOGGER.info(ksID);
				}
				if (fileItem.getFieldName().equals("departments")) {
					departMent = fileItem.getString();
					LOGGER.info(departMent);
				}
				if (fileItem.getFieldName().equals("subjects")) {
					subject = fileItem.getString();
					LOGGER.info(subject);
				}
				if (fileItem.getFieldName().equals("description")) {
					description = fileItem.getString();
					LOGGER.info("description " + description);
				}
				if (fileItem.getFieldName().equals("usersid")) {
					recipientType += fileItem.getString() + "#";
					// i++;
					LOGGER.info("recipientType " + recipientType);
				}

			}
			LOGGER.info("recipientType " + recipientType);

			@SuppressWarnings("unchecked")
			Iterator<FileItem> iter1 = items.iterator();
			while (iter1.hasNext()) {
				FileItem fileItem = iter1.next();
				if (!fileItem.isFormField()) {
					flItem = fileItem;
					fileName = new File(flItem.getName()).getName();
					LOGGER.info(fileName);
					filePath = uploadPath + File.separator + fileName;
					storeFile = new File(filePath);

					LOGGER.info("filePath is=" + filePath);
					LOGGER.info("storeFile is=" + storeFile);
					// saves the file on disk
					flItem.write(storeFile);
					status = "success";
				}
			}

			LOGGER.info("3.storeFile is=" + storeFile);
		}

		if ("success".equals(status)) {

			FTPClient ftpclient = new FTPClient();
			FileInputStream fis = null;
			boolean result;
			String ftpServerAddress = PropertiesUtil.getProperty("FTP_IP");
			//String ftpServerAddress = "localhost";
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
////////////////////////////////////////////////////////////////////////////////////////////
					if (true) {

						// LOGGER.info("Directory created successfully !");
						// ftpclient.changeWorkingDirectory("/home/ftpkds1/KnowStore/");
						String space = kDao.fetchKnowStoreSpace(userid, orgid);
						LOGGER.info("KnowSpace space is for Admin :" + space);

						long spaceInLong = Long.parseLong(space);
						// double spaceInBytes = 0;
						// double remainingSpace = 0;
						String directoryFolder = "/home/ftpkds1/home/ftpkds1/KnowStore/"
								+ session.getAttribute("orgid")
								+ '/'
								+ session.getAttribute("userid");

						

						long[] dirInfo = calculateDirectoryInfo(ftpclient,
								directoryFolder, "");
						long spaceInBytes1 = (spaceInLong * 1024 * 1024 * 1024);
						//System.out.println("Total dirs: " + dirInfo[0]);
						//System.out.println("Total files: " + dirInfo[1]);
						//System.out.println("Total size: " + dirInfo[2]);
						long remainingSizeInDb = (spaceInBytes1 - dirInfo[2]);
						//System.out.println("Remaining Free Size   "+ (spaceInBytes1 - dirInfo[2]));

						if (remainingSizeInDb >= storeFile.length()) {

							File file = new File(storeFile.getPath());
							if (file.exists()) {
								//System.out.println("file path   "+ file.getPath());

								//System.out.println("uploadedfile size  "+ storeFile.length());

								LOGGER.info(file);
								testName = fileName;
								directoryPath = "/home/ftpkds1/KnowStore/"
										+ session.getAttribute("orgid") + '/'
										+ session.getAttribute("userid");

								String pathElements1[] = directoryPath
										.split("/");
								if (pathElements1 != null
										&& pathElements1.length > 0) {
									for (String singleDir : pathElements1) {
										boolean existed = ftpclient
												.changeWorkingDirectory(singleDir);
										LOGGER.info("CurrentDir: " + singleDir);

										if (!existed) {
											boolean created = ftpclient
													.makeDirectory(singleDir);
											ftpclient
													.changeWorkingDirectory(singleDir);
											LOGGER.info("CurrentDir: "
													+ singleDir);
											if (created) {
												LOGGER.info(singleDir);
												System.out
														.println("Directory created successfully !");
											} else {
												System.out
														.println("Error in creating directory !");
											}
										}
									}

								}

								fis = new FileInputStream(file);

								// Upload file to the ftp server
								result = ftpclient.storeFile(testName, fis);

								if (result == true) {
									@SuppressWarnings("unused")
									String directoryPath1 = directoryPath + "/"
											+ file.getName();
								} else {
									LOGGER.info("File uploading failed");
								}
								ftpclient.logout();
							}
						} else {
							LOGGER.info("Login Fail!");
							String Msg = "Sorry you dont have enough space in Know store ! ";
							session.setAttribute(Msg, "AdminFtpSuccess");
							response.sendRedirect("../JSP/AdminFileShareknowStore.jsp");
							return;

						}
					}
				}
			}

			catch (FTPConnectionClosedException e1) {
				e1.printStackTrace();
			} finally {
				try {
					ftpclient.disconnect();
				} catch (FTPConnectionClosedException e2) {
					LOGGER.info(e2);
				}

				LOGGER.info(testName);
				String[] details = testName.split(".");
				for (String s1 : details) {
					LOGGER.info(s1);
				}

				int size = fileName.length();
				LOGGER.info(size);

				@SuppressWarnings("unused")
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

				Calendar calendar = Calendar.getInstance();
				java.util.Date now = calendar.getTime();
				java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(
						now.getTime());

				@SuppressWarnings("unused")
				DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");

				FacilitatorKAssetsReportDomain ksfDomain = new FacilitatorKAssetsReportDomain();

				ksfDomain.setKsId(ksID);
				ksfDomain.setDepartment(departMent);
				ksfDomain.setSubject(subject);
				ksfDomain.setDescription(description);
				ksfDomain.setFileName(fileName);
				ksfDomain.setFileSize(size);
				ksfDomain.setFilePath(directoryPath);
				ksfDomain.setUploadDate(currentTimestamp);

				ksfDomain.setUploadedBy(session.getAttribute("userid")
						.toString());
				LOGGER.info(recipientType);
				// String[] a= recipientType.getString();
				ksfDomain.setRecipientType(recipientType);

				FacilitatorknowSetUpDao ksfDao = new FacilitatorknowSetUpDao();

				String retStatus = ksfDao.insertAllInformation(ksfDomain);

				String msg = null;

				if ("valid".equals(retStatus)) {

					msg = "File shared successfully !!";
					session.setAttribute("AdminSuccess", msg);
					response.sendRedirect("../JSP/AdminFileShareknowStore.jsp");
				}
		     }

		}
	}
}