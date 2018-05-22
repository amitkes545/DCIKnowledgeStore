package com.kds.KODE_DEV.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

import com.kds.KODE_DEV.util.PropertiesUtil;
import com.kds.KODE_DEV.dao.ParticipantAssignmentFileUploadDetailsDao;
import com.kds.KODE_DEV.domain.AssessmentsDetailsDomain;

@SuppressWarnings("serial")
public class AssignmentFileUploadService extends CommonService {
	FileItem facultyName=null;
	String s1="";

	FileItem flItem = null;
	FileItem assessmentName=null;
	FileItem filePath1 = null;
	String filePath = null;
	File storeFile = null;
	String fileName = null;
	String assignid = null;
	String status = null;
	//private static final String UPLOAD_DIRECTORY = "Upload";
	private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
	
	static final Logger LOGGER = Logger.getLogger(AssignmentFileUploadService.class);
	public void run() throws ServletException, IOException, FileUploadException {
		 session = request.getSession();
		//String assignid=(String)session.getAttribute("assignmentid");
		//LOGGER.info("Assignment id fileservice "+assignid);
			FTPClient ftpclient = new FTPClient();
			FileInputStream fis = null;
			boolean result;
			//String ftpServerAddress = "localhost";
			String ftpServerAddress = PropertiesUtil.getProperty("FTP_IP");
			//String ftpServerAddress = "ftp://220.225.125.221/";
			//String userName = "ftpuser1";
			//String password = "ftp@123#1";
			String userName =PropertiesUtil.getProperty("user");
			String password =PropertiesUtil.getProperty("password");	
		 if (!ServletFileUpload.isMultipartContent(request)) {
		//	PrintWriter writer = response.getWriter();
			LOGGER.info("Request does not contain upload data");
			// writer.flush();
			// return;
		} else 
		{
			//PrintWriter writer = response.getWriter();
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(THRESHOLD_SIZE);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setSizeMax(MAX_REQUEST_SIZE);

			// constructs the directory path to store upload file

			//String uploadPath = "C:/" + UPLOAD_DIRECTORY;
			String download_path = System.getProperty("user.home") + "/Downloads";
	         String download_path1 = download_path.replace("\\", "/");
	         String s =System.getProperty ("os.name");
	         LOGGER.info("download_path= "+download_path);
	         LOGGER.info("download_path1= "+download_path1);
	         LOGGER.info("os name= "+s);
	        // System.out.println("download_path="+download_path);
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
			//System.out.println("uploadDir="+uploadDir);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			 
	    	 List items = upload.parseRequest(request);
	    	 //System.out.println("items="+items);
	         Iterator<FileItem> iter = items.iterator();
	         
	             FileItem fileItem = iter.next();
	              //assessmentName=(FileItem)items.get(0);
	                // facultyName= (FileItem) items.get(1);
	                 filePath1=(FileItem) items.get(2);
	                 //System.out.println("file path in assign service:"+filePath1);
	             //LOGGER.info(assessmentName.getString());
	             //LOGGER.info(filePath1.getString());
	             while (iter.hasNext()) 
	             {
	            	 fileItem= iter.next();
	            	 if(fileItem.getFieldName().contains("assignmentId")){
	            		 assignid = fileItem.getString();
	            		 LOGGER.info("Assignment id fileservice "+assignid);
	            		 //System.out.println("assignmentId="+fileItem.getString());
	            	 }
	            	
	              if (!fileItem.isFormField())
	              {
	                 flItem = fileItem;
	                 fileName = new File(flItem.getName()).getName();
	                 filePath = uploadPath + File.separator + fileName;
	                 storeFile = new File(filePath);
	                // System.out.println("filePath is="+filePath);
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
	             //}  
	         //} //while end    
	       //LOGGER.info("3.storeFile is="+storeFile); 
	   //}
	     



			try {
				ftpclient.connect(ftpServerAddress);
				result = ftpclient.login(userName, password);

				if (result) {
					LOGGER.info("Logged in Successfully.. !");

					ftpclient.setFileType(FTP.BINARY_FILE_TYPE);
					LOGGER.info("after ftpclint"+filePath1);
					String str = filePath1.getString();
					LOGGER.info("str="+str);
					//System.out.println(" str="+str);
					String[] pathElements = str.split("/");
					//System.out.println(" pathElements="+pathElements);
					LOGGER.info("pathElements="+pathElements);
					for(int i=1;i<pathElements.length-1;i++)
					{
						s1=s1+"/"+pathElements[i];
						LOGGER.info("s1 under for="+s1);
					}
					System.out.println("before s1="+s1);
					s1="KnowStore1"+s1+"/"+session.getAttribute("userId").toString();
										//s1="KnowStore1"+"/"+session.getAttribute("userId").toString();
					//System.out.println("ftp server path in assign service:"+s1);
					LOGGER.info("after s1="+s1);
				String	pathElements1[]=s1.split("/");
					if (pathElements1 != null && pathElements1.length  > 0) {
						for (String singleDir : pathElements1) 
						{
							boolean existed = ftpclient.changeWorkingDirectory(singleDir);
							LOGGER.info("CurrentDir: "+singleDir );

							if (!existed) {
								boolean created = ftpclient.makeDirectory(singleDir);
								ftpclient.changeWorkingDirectory(singleDir);
								LOGGER.info("CurrentDir: "+singleDir );
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

				} else {
					LOGGER.info("Login Fail!");
				}

				File file = new File(storeFile.getPath());
				LOGGER.info(file);
				String testName = fileName;
				LOGGER.info(testName);
				fis = new FileInputStream(file);

				// Upload file to the ftp server
				result = ftpclient.storeFile(testName, fis);

				// DBTransaction dbTransaction = new DBTransaction();

				if (result == true) {

					LOGGER.info("File is uploaded successfully");
				} else {
					LOGGER.info("File uploading failed");
				}
				ftpclient.logout();
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
			
				
			AssessmentsDetailsDomain add=new AssessmentsDetailsDomain();
			//add.setAssessmentName(assessmentName.getString());
			add.setAssessmentId(assignid);
			add.setFilePath(s1+"/"+fileName);
			add.setOrganizationId(session.getAttribute("orgId").toString());
			add.setUploadedBy(session.getAttribute("userId").toString());
			//add.setRecipientType(facultyName.getString());
			 s1="";
			 ParticipantAssignmentFileUploadDetailsDao pf=new ParticipantAssignmentFileUploadDetailsDao();
		//String status=	pf.fileUploadDetails(add);
			 status=pf.InsertFileDetails(add);
			 
				//close loop here for all uploads
				}   //file select field end
			}   //while end
		}  //else end


		if(status.equals("valid"))
		{
			//response.sendRedirect("../JSP/ParticipantAssignment.jsp");
		LOGGER.info(status);
		String successMessage = "File upload successfully";
		request.setAttribute("fileMessage", successMessage);
		RequestDispatcher rd = request
				.getRequestDispatcher("../JSP/StudentWorkware.jsp");
		rd.forward(request, response);
		}
			 	
		}  //run function end

	} //class end 


