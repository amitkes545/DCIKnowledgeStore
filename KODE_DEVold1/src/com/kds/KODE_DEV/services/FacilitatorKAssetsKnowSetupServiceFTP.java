package com.kds.KODE_DEV.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.FacilitatorknowSetUpDao;
import com.kds.KODE_DEV.dao.ParticipantFileUploadDetailsDao;
import com.kds.KODE_DEV.domain.AssessmentsDetailsDomain;
import com.kds.KODE_DEV.domain.FacilitatorKAssetsReportDomain;
import com.kds.KODE_DEV.util.PropertiesUtil;

@SuppressWarnings("serial")
public class FacilitatorKAssetsKnowSetupServiceFTP extends CommonService {
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
	
	FileItem courses,test1,test2,test3 = null;
	FileItem newtopics = null;
	FileItem subtopics = null;
	int size=0;
	//private static final String UPLOAD_DIRECTORY = "Upload";
	private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 100; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 100; // 50MB
	
	static final Logger LOGGER = Logger.getLogger(FacilitatorKAssetsKnowSetupServiceFTP.class);
	public void run() throws ServletException, IOException, FileUploadException {
		
		 session = request.getSession();
		 
		 String userid = (String) session.getAttribute("userid");
			String orgid = (String) session.getAttribute("orgid");
			String courses1 =  request.getParameter("coursesid");
			String newtopics1 =  request.getParameter("selectvalues1");
			String ksid = request.getParameter("ksid");
		
			
			String subtopics1 =  request.getParameter("selectvalues2");
			String libid = request.getParameter("txtlibid");
			LOGGER.info("ksid "+ksid);
			LOGGER.info("libid "+libid);
			LOGGER.info("userid "+userid);
			LOGGER.info("orgid "+orgid);
			LOGGER.info("courses "+courses1);
			LOGGER.info("newtopics "+newtopics1);
			LOGGER.info("subtopics "+subtopics1);
			
			
			System.out.println("ksid "+ksid);
			System.out.println("libid "+libid);
			System.out.println("userid "+userid);
			System.out.println("orgid "+orgid);
			System.out.println("courses "+courses1);
		
			System.out.println("newtopics "+newtopics1);
			System.out.println("subtopics "+subtopics1);
			
		    //String assid=(String)session.getAttribute("assessmentid");
		    System.out.println("in FileUploadService");
           //LOGGER.info("Assessment id fileservice "+assid);
			FileInputStream fis = null;
			boolean result;
			//String ftpServerAddress = "localhost";
			String ftpServerAddress = PropertiesUtil.getProperty("FTP_IP");
			//String ftpServerAddress =PropertiesUtil.getProperty("FTP_IP");
			//String ftpServerAddress = "ftp://220.225.125.221/";
			//String userName = "ftpuser1";
			//String password = "ftp@123#1";
			
			String userName =PropertiesUtil.getProperty("user");
			String password =PropertiesUtil.getProperty("password");	
			System.out.println("FTP Server Connected 109 line");

			FTPClient ftpclient = new FTPClient();
			String testName = null;
			FacilitatorKAssetsReportDomain ksfDomain = new FacilitatorKAssetsReportDomain();
			FacilitatorknowSetUpDao ksfDao = new FacilitatorknowSetUpDao();
			System.out.println("****************************");
		if (!ServletFileUpload.isMultipartContent(request)) {
			//PrintWriter writer = response.getWriter();
			System.out.println("in if***************");
			//String assid=request.getParameter("assessmentId");
			LOGGER.info("Request not contain upload data");
			// writer.flush();
			// return;
		} else 
		{
			System.out.println("in else &&&&&&&&&&&&&&&&&&&&");
			LOGGER.info("in else ");
			//PrintWriter writer = response.getWriter();
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(THRESHOLD_SIZE);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setSizeMax(MAX_REQUEST_SIZE);

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

			File uploadDir = new File(uploadPath);
			System.out.println("uploadDir="+uploadDir);
			LOGGER.info("uploadDir="+uploadDir);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			System.out.println("before list");
			List items = upload.parseRequest(request);
			System.out.println("alter list"+items);
			LOGGER.info("alter list"+items);
			Iterator<FileItem> iter = items.iterator();
			System.out.println("iter="+iter);
			
			FileItem fileItem = iter.next();
			LOGGER.info("fileItem="+fileItem);
			System.out.println("fileItem  =   "+fileItem);
			ksID = (FileItem) items.get(0);
			System.out.println("ksID="+ksID.getString());
			LOGGER.info("ksID="+ksID.getString());
			courses = (FileItem) items.get(1);
			System.out.println("course="+courses.getString());
			LOGGER.info("course="+courses.getString());
			test1 = (FileItem) items.get(1);
			System.out.println("test1="+test1.getString());
			LOGGER.info("test1="+test1.getString());
			test2 = (FileItem) items.get(1);
			System.out.println("test2 ="+test2.getString());
			LOGGER.info("test2="+test2.getString());
			subject = (FileItem) items.get(4);
			System.out.println("subject="+subject.getString());
			LOGGER.info("subject="+subject.getString());
			test3 = (FileItem) items.get(5);
			System.out.println("courses ="+test3.getString());
			LOGGER.info("courses ="+test3.getString());
			newtopics = (FileItem) items.get(6);
			System.out.println("newtopics ="+newtopics.getString());
			LOGGER.info("newtopics ="+newtopics.getString());
			subtopics = (FileItem) items.get(7);
			System.out.println("subtopics ="+subtopics.getString());
			LOGGER.info("subtopics ="+subtopics.getString());
			libId = (FileItem) items.get(8);
			System.out.println("libid ="+libId.getString());
			LOGGER.info("libid ="+libId.getString());
			String libidstring =libId.getString();
			description = (FileItem) items.get(14);
			System.out.println("description ="+description.getString());
			LOGGER.info("description ="+description.getString());
			/*ksID = (FileItem) items.get(0);
			System.out.println("ksID="+ksID.getString());
			libId = (FileItem) items.get(1);
			String libidstring=libId.getString();
			LOGGER.info("ksID  ="+ksID);
			LOGGER.info("libId ="+libId);
			System.out.println("ksID ="+ksID);
			System.out.println("libId ="+libId);
			
			System.out.println("libidstring ="+libidstring);
		
			// String orgID = null;
			departMent = (FileItem) items.get(2);
			subject = (FileItem) items.get(3);
			description = (FileItem) items.get(4);
			newtopics = (FileItem) items.get(5);
			subtopics = (FileItem) items.get(6);
			courses = (FileItem) items.get(7);*/
		/*	System.out.println("ksid  ="+ksid);
			System.out.println("departMent ="+departMent);
			System.out.println("subject ="+subject);
			System.out.println("description ="+description);
			
			System.out.println("courses ="+subtopics.getString());
			//System.out.println("newtopics  ="+newtopics);
			System.out.println("newtopics  ="+subtopics.getString());
			System.out.println("topics = "+ courses.getString());
			System.out.println("subjects = "+ newtopics.getString());*/
			// recipientType =new FileItem[(int)items.get(4)];
			
	         
	        //      assessmentName=(FileItem)items.get(0);
	          //       facultyName= (FileItem) items.get(1);
	          //  filePath1=(FileItem) items.get(2);
	                // assessmentId=(FileItem) items.get(3);
	             //LOGGER.info(assessmentName.getString());
	             //LOGGER.info("filePath1="+filePath1.getString());
	             while (iter.hasNext()) 
	             {
	            	 fileItem= iter.next();
	            	 if(fileItem.getFieldName().contains("assessmentId")){
	            		 assid = fileItem.getString();
	            		 LOGGER.info("Assessment id fileservice "+assid);
	            		 System.out.println("assessmentId="+fileItem.getString());
	            	 }
	            	 
	              if (!fileItem.isFormField())   //file select field
	              {
	                 flItem = fileItem;
	                 fileName = new File(flItem.getName()).getName();
	                 filePath = uploadPath + File.separator + fileName;
	                 storeFile = new File(filePath);
	                LOGGER.info("filePath is="+filePath);
	                LOGGER.info("1.storeFile is="+storeFile);
	               System.out.println("filepath:"+filePath);
	                //System.out.println("1.storeFile is="+storeFile);
	                // saves the file on disk
	                try {
						flItem.write(storeFile);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						LOGGER.info("EXCEPTION ="+e);
					}
	                File file = new File(storeFile.getPath());
					size = (int) file.length();
					LOGGER.info("file=="+file);
					double avail_space = ksfDao.CheckLibSize(orgid,libidstring);
					System.out.println("avail_space="+avail_space);
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
					else{
//	             }   //file select field end
//	         }   //while end
//	   }  //else end
	     

			try {
				System.out.println("tryyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
				ftpclient.connect(ftpServerAddress);
				result = ftpclient.login(userName, password);
				System.out.println("after connection result++++++++++++++++++++++="+result);
				if (result) {
					System.out.println("result in ftp connected_______________________");
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
							+ session.getAttribute("orgid") + '/'
							+ ksID.getString() + '/'
							+ userid + '/'
							+ libId.getString();
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
				boolean result2 = ftpclient.storeFile(testName, fis);
					
				// DBTransaction dbTransaction = new DBTransaction();
				System.out.println("Result 2=="+result2);
				if (result2 == true) {
					LOGGER.info("FTP success");
               //addedd by prity from here
					LOGGER.info("result="+result);
					String extension="";
					System.out.println("testName="+testName);
					int ind=testName.lastIndexOf(".");
						if(ind!=-1) 
						extension = testName.substring(ind); 
						System.out.println("ext="+extension);
						if(extension.equalsIgnoreCase(".mp4") || extension.equalsIgnoreCase(".pdf")) {
							System.out.println("in if="+testName);
							
							//File f = new File("C:\\Apache24\\htdocs\\"+testName);
							//File f = new File("/var/www/html/KODE/"+testName);
							String pdfpath = PropertiesUtil.getProperty("pdf_path");
							System.out.println("PDF PATH "+pdfpath);
							File f = new File(pdfpath);
							// /var/www/HTML/video/sample.mp4
							if(!(f.exists() && f.isFile())){
								LOGGER.info("File not exists in local dir");
					//Files.copy(new File("G:\\ftp\\home\\ftpkds1\\KnowStore\\"+session.getAttribute("orgid")+"\\"+ksID.getString() +"\\"+session.getAttribute("userid")+"\\"+libId.getString()+"\\"+testName).toPath(),new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\webapps\\ROOT\\"+testName).toPath());
								/*File sourceFile = new File("/home/ftpuser1/home/ftpkds1/KnowStore/"+session.getAttribute("orgid")+"/"+ksID.getString() +"/"+session.getAttribute("userid")+"/"+libId.getString()+"/"+testName);
							    InputStream iStream = new FileInputStream(sourceFile);
							    
							    File targetFile = new File("/var/www/html/KODE/"+testName);
							    OutputStream tStream = new FileOutputStream(targetFile);*/
								
							    //System.out.println("path s1=home/ftpkds1/KnowStore/srm/KS387/faculty@srm/Lib16");
							    LOGGER.info("before source : /home/ftpuser1/home/ftpkds1/KnowStore/"+session.getAttribute("orgid")+"/"+ksID.getString() +"/"+session.getAttribute("userid")+"/"+libId.getString()+"/"+testName);
							  String source="/home/ftpuser1/home/ftpkds1/KnowStore/"+session.getAttribute("orgid")+"/"+ksID.getString() +"/"+session.getAttribute("userid")+"/"+libId.getString()+"/"+testName;
							  //  String source="/usr/tomcat7/Downloads/"+testName;
							    System.out.println("source==="+source);
							    LOGGER.info("source path="+ source);
								//Files.copy(new File("/home/ftpuser1/home/ftpkds1/KnowStore/"+session.getAttribute("orgid")+"/"+ksID.getString() +"/"+session.getAttribute("userid")+"/"+libId.getString()+"/"+testName).toPath(),new File("/var/www/html/KODE/"+testName).toPath());
								//Files.copy(new File(source).toPath(),new File("/usr/tomcat7/webapps/KODE/Assets/"+testName).toPath());
							 try{
								System.out.println("Moved "+pdfpath+testName);
								//FileUtils.copyFileToDirectory(new File("/home/ftpuser1/home/ftpkds1/KnowStore/QSPVTI0283/KS047/Dindin982/Lib9997/payment gateway5.pdf"), new File(pdfpath));
								LOGGER.info("From Source Moved source "+source);
								LOGGER.info("Last Source "+pdfpath+testName);
								LOGGER.info("file copied");
								System.out.println("File moved  to assets folder ");
								Files.copy(new File(source).toPath(),new File(pdfpath+testName).toPath(),StandardCopyOption.REPLACE_EXISTING);
								// Files.copy(new File(source).toPath(),new File("/usr/tomcat7/webapps/KODE_DEV/Assets/"+testName).toPath(),StandardCopyOption.REPLACE_EXISTING);
							//	 Files.copy(new File(source1).toPath(),new File("/usr/tomcat7/webapps/KODE_DEV/Assets/"+testName).toPath(),StandardCopyOption.REPLACE_EXISTING);
							  
							 }catch(Exception e){
								 System.out.println("File not moved  to assets folder ");
								 e.printStackTrace();
								 LOGGER.info("FILE NOT MOVED ASSETS FOLDER - EXCEPTION "+e);
								// LOGGER.info(e.getLocalizedMessage());
							 }
							    LOGGER.info("file copied");
								//Files.copy(new File("C:/PRITY/abc.txt").toPath(), new File("E:/PRTy/sss/txt").toString());
							    //Files.copy(iStream,tStream);
					///home/ftpkds1/KnowStore/"	+ session.getAttribute("orgid")+'/'+ksID.getString() +'/'+session.getAttribute("userid")+'/'+ libId.getString()
					//C:\Program Files\Apache Software Foundation\Tomcat 7.0\webapps\ROOT
					//G:\ftp\home\ftpkds1\KnowStore\InsComBal
							} else{
								String msg = "File already exists on server  !!";
							request.setAttribute("FacultyFailure", msg);
							ftpclient.logout();
							RequestDispatcher requestDispatcher = request.getRequestDispatcher("../JSP/FacilitatorKnowSetup.jsp");
							requestDispatcher.forward(request, response);
							}
						}
						LOGGER.info("File is uploaded successfully");
				}else {
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

			ksfDomain.setKsId(ksID.getString());
			System.out.println("ksID.getString()   " +ksID.getString());
			
			//ksfDomain.setLibId(libId.getString());
			System.out.println("libId.getString()   " +libId.getString());
			ksfDomain.setLibId(libId.getString());
			//System.out.println("libId.getString()   " +libId.getString());
			
			ksfDomain.setDepartment(courses.getString());
			System.out.println("departMent.getString()   " +courses.getString());
			
			//ksfDomain.setSubject(subject.getString());
			ksfDomain.setSubject(subject.getString());
			System.out.println("subject.getString()   " +subject.getString());
			
			ksfDomain.setDescription(description.getString());
			System.out.println("description.getString()   " +description.getString());
			
			ksfDomain.setFileName(fileName);
			System.out.println("fileName   " +fileName);
			
			ksfDomain.setFileSize(size);
			System.out.println("size   " +size);
			
			ksfDomain.setFilePath(s1+"/"+fileName);
			//System.out.println("   " +);
			
			ksfDomain.setUploadDate(currentTimestamp);
			System.out.println("currentTimestamp   " +currentTimestamp);
			
			ksfDomain.setUploadTime(currentTimestamp);
			System.out.println("set upload time   " +currentTimestamp);
			
			System.out.println("session ="+userid);
			ksfDomain.setUploadedBy(userid);
			//newly added
			System.out.println("Courses  ="+libId.getString());
			System.out.println("topics  ="+subtopics.getString());
			System.out.println("subtopics  ="+courses.getString());
			
			ksfDomain.setCourses(courses.getString());
			ksfDomain.setDepartments(newtopics.getString());
			ksfDomain.setSubjects(subtopics.getString());
			ksfDomain.setOrgId(orgid);
			/*ksfDomain.setCourses(libidstring);
			ksfDomain.setDepartments(subtopics.getString());
			ksfDomain.setSubjects(courses.getString());*/
			String retStatus = ksfDao.insertAllInformation(ksfDomain);

			String msg = null;
			System.out.println("retStatus="+retStatus);
			if(retStatus!=null){
			if(retStatus.equals("valid"))
			{
			LOGGER.info(status);
			//response.sendRedirect("../JSP/ParticipantAssessment.jsp");
			String successMessage = "Successfully uploaded Knowledge Assets";
			//newly added for audit trail purpose by dinesh 
			session.setAttribute("KSID", ksID.getString());
			session.setAttribute("orgid",orgid);
			//newly added for audit trail purpose by dinesh 
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
				String successMessage = "Login failed in ftp server  !!";
				request.setAttribute("FacultyFailure", successMessage);
			}
					
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("../JSP/FacilitatorKnowSetup.jsp");
			requestDispatcher.forward(request, response);
			//System.out.println("status="+status);
	              
			//close loop here for all uploads
		             }   //file select field end
		         }   //while end
		   }  //else end
		
		
		}   //Run function end

	}  //class end

}
