package com.kds.KODE_DEV.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.FacilitatorknowSetUpDao;
import com.kds.KODE_DEV.dao.KnowStoreSizeGetDao;
import com.kds.KODE_DEV.dao.ParticipantFileUploadDetailsDao;
import com.kds.KODE_DEV.dao.StudentClassWorkFileDao;
import com.kds.KODE_DEV.domain.AssessmentsDetailsDomain;
import com.kds.KODE_DEV.domain.ClassWorkFileDomain;
import com.kds.KODE_DEV.domain.FacilitatorKAssetsReportDomain;
import com.kds.KODE_DEV.util.PropertiesUtil;

@SuppressWarnings("serial")
public class StudentClassWorkSendFileServlet extends CommonService {
	FileItem facultyName=null;
	String s1="";

	FileItem flItem = null;
	FileItem ksID = null;
	FileItem libId= null;
	FileItem departMent = null;
	FileItem subject = null;
	FileItem description = null;
	FileItem assessmentName=null;
	FileItem filePath1 = null;
	FileItem assessmentId=null;
	String filePath = null;
	File storeFile = null;
	String fileName = null;
	String assid=null;
	String status=null;
	String directoryPath = null;
	int size=0;
	//private static final String UPLOAD_DIRECTORY = "Upload";
	private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
	
	static final Logger LOGGER = Logger.getLogger(StudentClassWorkSendFileServlet.class);
	public void run() throws ServletException, IOException, FileUploadException {
		 session = request.getSession();
		 String userid = (String) session.getAttribute("userid");
			String orgid = (String) session.getAttribute("orgid");
			KnowStoreSizeGetDao kDao = new KnowStoreSizeGetDao();

			String directoryPath = null;
			String status = "";
			String filePath = null;
			File storeFile = null;
			String fileName = null;
			FileItem flItem;
			FileItem ksID = null;
			FileItem libId= null;
			// String orgID = null;
			// String userid=null;
			FileItem departMent = null;
			FileItem subject = null;
			FileItem description = null;
		//String assid=(String)session.getAttribute("assessmentid");
		System.out.println("in FileUploadService");
//		LOGGER.info("Assessment id fileservice "+assid);
			FileInputStream fis = null;
			boolean result;
			//String ftpServerAddress = "localhost";
			String ftpServerAddress = PropertiesUtil.getProperty("FTP_IP");
			//String ftpServerAddress = "ftp://220.225.125.221/";
	//		String userName = "ftpuser1";
	//		String password = "ftp@123#1";
			
			String userName =PropertiesUtil.getProperty("user1");
			String password =PropertiesUtil.getProperty("password1");	

			FTPClient ftpclient = new FTPClient();
			String testName = null;
			ClassWorkFileDomain ksfDomain = new ClassWorkFileDomain();
			//FacilitatorKAssetsReportDomain ksfDomain = new FacilitatorKAssetsReportDomain();
			//FacilitatorknowSetUpDao ksfDao = new FacilitatorknowSetUpDao();
		if (!ServletFileUpload.isMultipartContent(request)) {
			//PrintWriter writer = response.getWriter();
			System.out.println("in if");
			//String assid=request.getParameter("assessmentId");
			LOGGER.info("Request not contain upload data");
			// writer.flush();
			// return;
		} else 
		{
			System.out.println("in else");
			//PrintWriter writer = response.getWriter();
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(THRESHOLD_SIZE);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setSizeMax(MAX_REQUEST_SIZE);
			StudentClassWorkFileDao ksfDao = new StudentClassWorkFileDao();
			// constructs the directory path to store upload file

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

			//File uploadDir = new File(uploadPath);
			/*System.out.println("uploadDir="+uploadDir);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}*/
			System.out.println("before list");
			/*List items = upload.parseRequest(request);
			System.out.println("alter list"+items);
			
			Iterator<FileItem> iter = items.iterator();
			System.out.println("iter="+iter);
			FileItem fileItem = iter.next();
			LOGGER.info("fileItem="+fileItem);
			System.out.println("fileItem="+fileItem);
			ksID = (FileItem) items.get(0);
			System.out.println("ksID="+ksID.getString());
			libId = (FileItem) items.get(1);
String libidstring=libId.getString();
			LOGGER.info("ksID="+ksID);
			LOGGER.info("libId="+libId);
			System.out.println("ksID="+ksID);
			System.out.println("libId="+libId);
			System.out.println("libidstring="+libidstring);
			// String orgID = null;
			departMent = (FileItem) items.get(2);
			subject = (FileItem) items.get(3);
			description = (FileItem) items.get(4);*/
			// recipientType =new FileItem[(int)items.get(4)];
			
	         
	        //      assessmentName=(FileItem)items.get(0);
	          //       facultyName= (FileItem) items.get(1);
	          //  filePath1=(FileItem) items.get(2);
	                // assessmentId=(FileItem) items.get(3);
	             //LOGGER.info(assessmentName.getString());
	             //LOGGER.info("filePath1="+filePath1.getString());
			List items = null;
			try {
				items = upload.parseRequest(request);
			} /*catch (FileUploadBase.SizeLimitExceededException se) {
	            String errMsg = "File exceeds size limit.";
System.out.println("error="+errMsg);
			}*/ catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("items="+items);
			String sesID="";
			Iterator<FileItem> iter = items.iterator();
			FileItem fileItem = iter.next();
			if(fileItem.getFieldName().equals("sesid"))
			{sesID=fileItem.getString();}
			/*if(fileItem.getFieldName().equals("filename"))
			{fileName=fileItem.getString();}*/
			System.out.println("sesid====="+sesID);
			//System.out.println("fileName====="+fileName);
			
			fileItem = iter.next();
			//System.out.println("fileItem="+fileItem);
			//System.out.println("input name="+fileItem.getName());
	              if (!fileItem.isFormField())   //file select field
	              {
	            	 // System.out.println("in if !fileItem.isFormField");
	            	 // System.out.println("input name="+fileItem.getName());
	                 flItem = fileItem;
	                 fileName = new File(flItem.getName()).getName();
	               //  System.out.println("fileName:"+fileName);
	                 filePath = uploadPath + File.separator + fileName;
	                // System.out.println("filepath:"+filePath);
	                 storeFile = new File(filePath);
	                // System.out.println("storeFile:"+storeFile);
	                LOGGER.info("filePath is="+filePath);
	                LOGGER.info("1.storeFile is="+storeFile);
	               
	                //System.out.println("1.storeFile is="+storeFile);
	                // saves the file on disk
	                try {
						flItem.write(storeFile);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                File file = new File(storeFile.getPath());
					size = (int) file.length();
					LOGGER.info("file=="+file);
					//double avail_space = ksfDao.CheckLibSize(orgid,libidstring);
					/*System.out.println("avail_space="+avail_space);
					LOGGER.info("avail_space in lib="+avail_space);
					LOGGER.info("size of file="+size);
					if(size > avail_space)
					{
						System.out.println("You don't have enough space in this library");
						LOGGER.info("You don't have enough space in this library");
						//response.sendRedirect("../JSP/ParticipantAssessment.jsp");
						String successMessage = "You don't have enough space in this library  !!";
						request.setAttribute("FacultyFailure", successMessage);
					}
					else{*/
//	             }   //file select field end
//	         }   //while end
//	   }  //else end
	     

			try {
				ftpclient.connect(ftpServerAddress);
				result = ftpclient.login(userName, password);
				System.out.println("after connection result="+result);
				if (result) {
					LOGGER.info("Logged in Successfully !");

					ftpclient.setFileType(FTP.BINARY_FILE_TYPE); //commented by prity
					 LOGGER.info("filePath1 in try="+filePath);
					String str = filePath.toString();
					LOGGER.info("str="+str);
					System.out.println(" filePath1="+str);
					String[] pathElements = str.split("/");
					//System.out.println("vefore pathElements="+pathElements);
										for(int i=1;i<pathElements.length-1;i++)
					{
						s1=s1+"/"+pathElements[i];
						LOGGER.info("s1 under for="+s1);
					}
									System.out.println("before s1="+s1);
									//"/home/ftpuser1/KnowStore/SRM/Admin/KS73/student1@srm/pgadmin.log"
					//s1="KnowStore1"+s1+"/"+session.getAttribute("userId").toString();
									//s1="/home/ftpuser1/KnowStore/SRM/KS387"+"/"+session.getAttribute("userId").toString();
//s1="KnowStore1"+"/"+session.getAttribute("userId").toString();
					System.out.println("after s1="+s1);
					LOGGER.info("file full path in service:"+s1);
					System.out.println("file full path in service:"+s1);
					s1 = "/home/ftpkds1/KnowStore/"
							+ orgid + '/'
							+ userid;
							
					System.out.println("directoryPath="+s1);
				String	pathElements1[]=s1.split("/");
					if (pathElements1 != null && pathElements1.length  > 0) {
						for (String singleDir : pathElements1) 
						{
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
				
				
				
				
			/*	double bytes = file.length();
				double kilobytes = (bytes / 1024);
				double megabytes = (kilobytes / 1024);
				double gigabytes = (megabytes / 1024);
				double terabytes = (gigabytes / 1024);
				double petabytes = (terabytes / 1024);
				double exabytes = (petabytes / 1024);
				double zettabytes = (exabytes / 1024);
				double yottabytes = (zettabytes / 1024);

				System.out.println("bytes : " + bytes);
				System.out.println("kilobytes : " + kilobytes);
				System.out.println("megabytes : " + megabytes);
				System.out.println("gigabytes : " + gigabytes);
				System.out.println("terabytes : " + terabytes);
				System.out.println("petabytes : " + petabytes);
				System.out.println("exabytes : " + exabytes);
				System.out.println("zettabytes : " + zettabytes);
				System.out.println("yottabytes : " + yottabytes);*/
				
				
				System.out.println("file in:"+file);
				LOGGER.info(file);
				testName = fileName;
				LOGGER.info(testName);
				fis = new FileInputStream(file);
                  System.out.println("ftp path in service:"+fis);
				// Upload file to the ftp server
				result = ftpclient.storeFile(testName, fis);

				// DBTransaction dbTransaction = new DBTransaction();

				if (result) {
					LOGGER.info("File is uploaded successfully");
				} else {
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
	              //}
			LOGGER.info(testName);
			String[] details = testName.split(".");
			for (String s1 : details) {
				LOGGER.info(s1);
			}

			//int size = fileName.length();
			LOGGER.info(size);
			System.out.println("size="+size);
					System.out.println("filename="+fileName);
					//System.out.println("filename size="+file.size());
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			System.out.println(dateFormat);

			Calendar calendar = Calendar.getInstance();
			java.util.Date now = calendar.getTime();
			java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(
					now.getTime());
			DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
			System.out.println("currentTimestamp="+currentTimestamp);
			System.out.println("file path="+s1+"/"+fileName);
			ksfDomain.setFileName(fileName);
			ksfDomain.setFilePath(s1+"/"+fileName);
			ksfDomain.setUploadDate(currentTimestamp);
			ksfDomain.setUploadTime(currentTimestamp);
			//System.out.println("session ="+userid);
			ksfDomain.setUploadedBy(userid);
		
			ksfDomain.setSessionID(sesID);
			 
			String retStatus = ksfDao.insertClassWorkFileInformation(ksfDomain);

			String msg = null;
			System.out.println("retStatus="+retStatus);
			if(retStatus!=null){
			if(retStatus.equals("success"))
			{
			LOGGER.info(status);
			//response.sendRedirect("../JSP/ParticipantAssessment.jsp");
			String successMessage = "File upload successfully";
			request.setAttribute("FacultySuccess", successMessage);
			
			}
			else{
				LOGGER.info(status);
				//response.sendRedirect("../JSP/ParticipantAssessment.jsp");
				String successMessage = "File already exists on server  !!";
				request.setAttribute("FacultyFailure", successMessage);
				
			}
			}
			else
			{
				String successMessage = "File already exists on server  !!";
				request.setAttribute("FacultyFailure", successMessage);
			}
					//}
			
			//System.out.println("status="+status);
	              
			//close loop here for all uploads
		             }   //file select field end
	              String successMsg = "Unable to send file on server  !!";
					request.setAttribute("FacultyFailure", successMsg);
	              RequestDispatcher requestDispatcher = request
	  					.getRequestDispatcher("../JSP/StudentClasswork.jsp?ses_id="+sesID+"");
	  			requestDispatcher.forward(request, response);
		       //  }   //while end
		   }  //else end
		
		
		}   //Run function end

	}  //class end


