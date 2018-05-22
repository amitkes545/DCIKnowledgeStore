package com.kds.KODE_DEV.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.FacilitatorSelectKnowStoreIdDao;
import com.kds.KODE_DEV.dao.FacilitatorknowSetUpDao;
import com.kds.KODE_DEV.domain.FacilitatorKAssetsReportDomain;
import com.kds.KODE_DEV.util.PropertiesUtil;

@SuppressWarnings("serial")
public class FacilitatorKnowSetupServlet extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(FacilitatorKnowSetupServlet.class);
	
	String directorypath=null;
	String status="";
	String filePath = null;
	File   storeFile = null;
	String fileName = null;
	private static final String UPLOAD_DIRECTORY = "Upload";
	private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	FileItem flItem;
	
	FileItem ksID = null;
	//String orgID = null;
	//String userid=null;
	FileItem departMent = null;
	FileItem subject = null;
	FileItem description = null;
	//FileItem[] recipientType = null;
	
	//HttpSession mess =request.getSession(false);
	 
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public void run() throws Exception {
		HttpSession session=request.getSession(false);
		String testName =null;
		boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
		if (isMultiPart) 
		{
			
			PrintWriter writer = response.getWriter();
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(THRESHOLD_SIZE);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setSizeMax(MAX_REQUEST_SIZE);

			// constructs the directory path to store upload file

			String uploadPath = "/home/paramvir/" + UPLOAD_DIRECTORY;
			//System.out.println("uploadPath"+uploadPath);
		  
			File uploadDir = new File(uploadPath);
			
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			List items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();

			FileItem fileItem = iter.next();
			
			 ksID = (FileItem)items.get(0);
			//String orgID = null;
			 departMent = (FileItem)items.get(1);
			 subject = (FileItem)items.get(2);
			 description = (FileItem)items.get(3);
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
					flItem.write(storeFile);
					status="success";
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
					
					 String  orgid  =(String)session.getAttribute("orgid");
			           String  userID =(String)session.getAttribute("userid");
						
						FacilitatorSelectKnowStoreIdDao ksidDao = new FacilitatorSelectKnowStoreIdDao();
						
						String KsID1 = ksidDao.fetchDynamicKsid(userID);
						
						LOGGER.info(KsID1);
					

					ftpclient.setFileType(FTP.BINARY_FILE_TYPE);
					ftpclient.changeWorkingDirectory("/home/ftpkds1/KnowStore/");
					boolean folderCreated = ftpclient.makeDirectory("/home/ftpkds1/KnowStore/NSIC");/* +'/' +session.getAttribute("orgid")+ '/' + session.getAttribute("userid")+ '/' + KsID1 +"'");*/

					if (folderCreated==true) {
						LOGGER.info("Directory created successfully !");
						//ftpclient.changeWorkingDirectory("/home/ftpkds1/KnowStore/");
						File file = new File(storeFile.getPath());
						LOGGER.info(file);
						 testName = fileName;
						 directorypath="/home/ftpkds1/KnowStore/"+session.getAttribute("orgid")+ '/' + session.getAttribute("userid") + '/' + KsID1;
						LOGGER.info(testName);
						fis = new FileInputStream(file);

						//Upload file to the ftp server
						result = ftpclient.storeFile(testName, fis);

						 //DBTransaction dbTransaction = new DBTransaction();

						if (result == true) {

							LOGGER.info("File is uploaded successfully");
						} else {
							LOGGER.info("File uploading failed");
						}
						ftpclient.logout();
					} else {
						LOGGER.info("Error in creating directory !");
					}
				} else {
					LOGGER.info("Login Fail!");
					return;
				}
			} catch (FTPConnectionClosedException e1) {
				e1.printStackTrace();
			} 
			finally 
			{
				try {
					ftpclient.disconnect();
				}
				catch (FTPConnectionClosedException e2) {
					LOGGER.info(e2);
				}
			}
			
			LOGGER.info(testName);
			String[] details=testName.split(".");
			for(String s1:details){
				LOGGER.info(s1);
			}
		
			int size=fileName.length();
			LOGGER.info(size);
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		       
			Calendar calendar = Calendar.getInstance();
			java.util.Date now = calendar.getTime();
			java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(
					now.getTime());
			
			DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
		     
		       FacilitatorKAssetsReportDomain ksfDomain = new FacilitatorKAssetsReportDomain();
		   	
				ksfDomain.setKsId(ksID.getString());
				ksfDomain.setDepartment(departMent.getString());
				ksfDomain.setSubject(subject.getString());
				ksfDomain.setDescription(description.getString());
				ksfDomain.setFileName(fileName);
				ksfDomain.setFileSize(size);
				ksfDomain.setFilePath(directorypath);
				ksfDomain.setUploadDate(currentTimestamp);
				ksfDomain.setUploadDate(currentTimestamp);
				ksfDomain.setUploadedBy(session.getAttribute("userid").toString());
				//ksfDomain.setRecipientType(recipientType);
				//ksfDomain.setLibId(libId);
				//LOGGER.info(recipientType.length);
			//String[] a=	recipientType.getString();
				// ksfDomain.setRecipientType(recipientType.length);
				
				FacilitatorknowSetUpDao ksfDao = new FacilitatorknowSetUpDao();
				
			    String  retStatus = ksfDao.insertAllInformation(ksfDomain);
			
				String msg = null;
				
				if("valid".equals(retStatus)){
					
				msg = "Knowledge assets created and uploaded on server !!";			
				request.setAttribute("FacultySuccess", msg);
				}
				else
				{
					msg = "Failed to create knowledge assets and upload on server !!";
					request.setAttribute("FacultyFailure", msg);
				
				}
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("../JSP/Home.jsp");
				requestDispatcher.forward(request, response);
			}
		}
	}