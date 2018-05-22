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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
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
public class FacilitatorKAssetsKnowSetupServiceFTP8may extends CommonService {

	
	
	static final Logger LOGGER = Logger.getLogger(FacilitatorKAssetsKnowSetupServiceFTP.class);
	KnowStoreSizeGetDao kDao = new KnowStoreSizeGetDao();

	String directoryPath = null;
	String status = "";
	String filePath = null;
	File storeFile = null;
	String fileName = null;
	private static String UPLOAD_DIRECTORY = "Upload";
	private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB added by prity 1024
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 1024 * 4; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 1024 * 4; // 50MB

	FileItem flItem;
	FileItem ksID = null;
	FileItem libId= null;
	// String orgID = null;
	// String userid=null;
	FileItem departMent = null;
	FileItem subject = null;
	FileItem description = null;
	// FileItem[] recipientType = null;
	
	// HttpSession mess =request.getSession(false);

	FacilitatorKAssetsReportDomain ksfDomain = new FacilitatorKAssetsReportDomain();

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

	// @SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public void run() throws Exception {
		session = request.getSession();

		String userid = (String) session.getAttribute("userid");
		String orgid = (String) session.getAttribute("orgid");
		
		FacilitatorknowSetUpDao ksfDao = new FacilitatorknowSetUpDao();
		
		String testName = null;
		boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
		if (isMultiPart) {
			System.out.println("in multipart");
			PrintWriter writer = response.getWriter();
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024 * 1024*3);//THRESHOLD_SIZE);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			
			
			/*THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB added by prity 1024
			MAX_FILE_SIZE = 1024 * 1024 * 1024 * 4; // 40MB
			 MAX_REQUEST_SIZE = 1024 * 1024 * 1024 * 4; // 50MB
*/			
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(1024 * 1024 * 1024 * 4);//MAX_FILE_SIZE);
			upload.setSizeMax(1024 * 1024 * 1024 * 4);//MAX_REQUEST_SIZE);


			// String uploadPath = "/home/paramvir/" + UPLOAD_DIRECTORY;
			String s = System.getProperty("os.name");
			System.out.println("s=="+s);
			LOGGER.info("os name= " + s);
			String[] win = s.split(" ");
			String winname = win[0];
			// String winver=win[1];
			LOGGER.info("windows name= " + winname);
			String uploadPath;
			if (winname.equalsIgnoreCase("Windows")) {
				UPLOAD_DIRECTORY = "/Windows/Temp";
				uploadPath = "C:" + UPLOAD_DIRECTORY;
			} else {
				UPLOAD_DIRECTORY = "/tmp";
				uploadPath = "/" + UPLOAD_DIRECTORY;
			}
			// C:\Windows\Temp
			LOGGER.info("upload path= " + uploadPath);

			
			File uploadDir = new File(uploadPath);
			System.out.println("uploadDir="+uploadDir);
			LOGGER.error("error in uploadDir="+uploadDir);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			 System.out.println("before item");
			
			List items = upload.parseRequest(request);
			 System.out.println("items="+items);
			Iterator<FileItem> iter = items.iterator();
			System.out.println("iter="+iter);
			FileItem fileItem = iter.next();
			LOGGER.info("fileItem="+fileItem);
			System.out.println("fileItem="+fileItem);
			ksID = (FileItem) items.get(0);
			System.out.println("ksID="+ksID.getString());
			libId = (FileItem) items.get(1);

			LOGGER.info("ksID="+ksID);
			LOGGER.info("libId="+libId);
			// String orgID = null;
			departMent = (FileItem) items.get(2);
			subject = (FileItem) items.get(3);
			description = (FileItem) items.get(4);
			// recipientType =new FileItem[(int)items.get(4)];

			while (iter.hasNext()) {
				fileItem = iter.next();
				if (!fileItem.isFormField()) {
					flItem = fileItem;
					fileName = new File(flItem.getName()).getName();
					filePath = uploadPath + File.separator + fileName;
					storeFile = new File(filePath);

					LOGGER.info("filePath is=" + filePath);
					LOGGER.info("1.storeFile is=" + storeFile);
					// saves the file on disk
					try {
						flItem.write(storeFile);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					status = "success";
				}
			}

			// LOGGER.info("3.storeFile is=" + storeFile);

		}

		if ("success".equals(status)) {

			LOGGER.info("3 storeFile is=" + storeFile);

			FTPClient ftpclient = new FTPClient();
			FileInputStream fis = null;
			LOGGER.info("355777777.storeFile is=" + storeFile);
			boolean result;
			//String ftpServerAddress = "localhost";
			String ftpServerAddress = PropertiesUtil.getProperty("FTP_IP");
			//String ftpServerAddress = "220.225.125.221";

		//	String userName = "ftpuser1";
		//	String password = "ftp@123#1";
			
			String userName =PropertiesUtil.getProperty("user1");
			String password =PropertiesUtil.getProperty("password1");	

			try {

				LOGGER.info("3555=" + storeFile);
				ftpclient.connect(ftpServerAddress,21);

				LOGGER.info("after connect");

				//result = true;//ftpclient.login(userName, password);
				result = ftpclient.login(userName, password);
				LOGGER.info("after login");

				if (result) {
					LOGGER.info("after true");
					LOGGER.info("Logged in Successfully !");

					ftpclient.setFileType(FTP.BINARY_FILE_TYPE);

					// ftpclient.changeWorkingDirectory("/home/ftpkds1/KnowStore/");
					// boolean folderCreated =
					// ftpclient.makeDirectory("/home/ftpkds1/KnowStore/"+session.getAttribute("orgid")+
					// session.getAttribute("userid") + ksID +"'");

					if (true) {

						String spaceInDB = kDao.fetchKnowStoreSpace(userid,orgid);
						System.out.println("spaceInDB="+spaceInDB);
						LOGGER.info("KnowSpace space is for Admin :"+ spaceInDB);

						long spaceInLong = Long.parseLong(spaceInDB);
						String ftpDirePath = "/home/ftpkds1/KnowStore/"
								+ session.getAttribute("orgid") + '/'
								+ ksID.getString() + '/' 
								+ userid + '/'
								+ libId.getString();
						LOGGER.info("ftpDirePath="+ftpDirePath);
						long[] dirInfo = calculateDirectoryInfo(ftpclient,ftpDirePath, "");
						long spaceInBytes1 = (spaceInLong * 1024 * 1024 * 1024);
						//System.out.println("Total dirs: " + dirInfo[0]);
						//System.out.println("Total files: " + dirInfo[1]);
						//System.out.println("Total size: " + dirInfo[2]);
						long remainingSizeInDb = (spaceInBytes1 - dirInfo[2]);
						System.out.println("Remaining Free Size   "+ (spaceInBytes1 - dirInfo[2]));
						if (remainingSizeInDb < storeFile.length()) {

							String Msg = "Sorry you dont have enough space in Know store ! ";
							session.setAttribute(Msg, "FacilitatorFtpSuccess");
							response.sendRedirect("../JSP/FacilitatorKnowSetup.jsp");
						} else {

							File file = new File(storeFile.getPath());
							LOGGER.info(file);
							testName = fileName;

							directoryPath = "/home/ftpkds1/KnowStore/"
									+ session.getAttribute("orgid") + '/'
									+ ksID.getString() + '/'
									+ userid + '/'
									+ libId.getString();

							String pathElements1[] = directoryPath.split("/");
							if (pathElements1 != null && pathElements1.length > 0) {
								for (String singleDir : pathElements1) {
									boolean existed = ftpclient
											.changeWorkingDirectory(singleDir);
									System.out.println("existed="+existed);
									LOGGER.info("CurrentDir: " + singleDir);

									if (!existed) {
										boolean created = ftpclient
												.makeDirectory(singleDir);
										ftpclient
												.changeWorkingDirectory(singleDir);
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
							//System.out.println("uploadedfile size  "	+ storeFile.length());

							// DBTransaction dbTransaction = new
							// DBTransaction();

							if (result == true) {

								LOGGER.info("File is uploaded successfully");
							} else {
								LOGGER.info("File uploading failed");
							}
							ftpclient.logout();
						}
					}/*
					 * else{ String Msg =
					 * "Sorry you dont have enough space in Know store ! ";
					 * session.setAttribute(Msg, "");
					 * response.sendRedirect("../JSP/FacilitatorKnowSetup.jsp");
					 * return; }
					 */

				} else {
					LOGGER.info("Login Fail!");
					return;
				}
			} 
			/*catch (FileUploadBase.SizeLimitExceededException se) {
	            String errMsg = "File exceeds size limit.";
	            LOGGER.error(errMsg, se);
	            se.printStackTrace();           
	            //session.setAttribute("errorMessage", errMsg);
	            //return; 
			}*/
			catch (FTPConnectionClosedException e1) {
				e1.printStackTrace();
			} finally {
				try {
					ftpclient.disconnect();
				} catch (FTPConnectionClosedException e2) {
					LOGGER.info(e2);
				}
			}

			LOGGER.info(testName);
			String[] details = testName.split(".");
			for (String s1 : details) {
				LOGGER.info(s1);
			}

			int size = fileName.length();
			LOGGER.info(size);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			//System.out.println(dateFormat);

			Calendar calendar = Calendar.getInstance();
			java.util.Date now = calendar.getTime();
			java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(
					now.getTime());

			DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
			System.out.println(currentTimestamp);

			ksfDomain.setKsId(ksID.getString());
			ksfDomain.setLibId(libId.getString());
			ksfDomain.setDepartment(departMent.getString());
			ksfDomain.setSubject(subject.getString());
			ksfDomain.setDescription(description.getString());
			ksfDomain.setFileName(fileName);
			ksfDomain.setFileSize(size);
			ksfDomain.setFilePath(directoryPath+"/"+fileName);
			ksfDomain.setUploadDate(currentTimestamp);
			ksfDomain.setUploadTime(currentTimestamp);
			System.out.println("session ="+userid);
			ksfDomain.setUploadedBy(userid);
			//ksfDomain.setRecipientType(recipientType);
			// LOGGER.info(recipientType.length);
			// String[] a= recipientType.getString();
			// ksfDomain.setRecipientType(recipientType.length);

			//FacilitatorknowSetUpDao ksfDao = new FacilitatorknowSetUpDao();

			String retStatus = ksfDao.insertAllInformation(ksfDomain);

			String msg = null;

			if ("valid".equals(retStatus)) {

				msg = "Knowledge assets created and uploaded on server !!";
				request.setAttribute("FacultySuccess", msg);
			} else {
				msg = "File already exists on server  !!";
				request.setAttribute("FacultyFailure", msg);
			}
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("../JSP/FacilitatorKnowSetup.jsp");
			requestDispatcher.forward(request, response);
		}
	}
}