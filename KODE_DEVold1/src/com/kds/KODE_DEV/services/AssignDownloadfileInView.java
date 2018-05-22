
	package com.kds.KODE_DEV.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.util.PropertiesUtil;

	@SuppressWarnings("serial")
	public class AssignDownloadfileInView extends CommonService 
	{
		static String dirname;
		String UPLOAD_DIRECTORY = "";
		 static final Logger LOGGER = Logger.getLogger(AssignDownloadfile.class);
		public void run() throws ServletException,IOException
		{
			String fileName="";
			PrintWriter out=response.getWriter();
			
			String filepath=request.getParameter("filePath");
			LOGGER.info("filepath:"+filepath);
			if(filepath!=null)
			{
				String [] path1=filepath.split("/");
				 fileName=path1[path1.length-1];
			}
			//String server = "localhost";
			String server = PropertiesUtil.getProperty("FTP_IP");
			int port = 21;
		//	String user = "ftpuser1";
		//	String pass = "ftp@123#1";
			
			String user =PropertiesUtil.getProperty("user1");
			String pass =PropertiesUtil.getProperty("password1");	
			
			
			FTPClient ftpClient = new FTPClient();
			if(true)
			{	
				ftpClient.connect(server, port);
				ftpClient.login(user, pass);
				ftpClient.enterLocalPassiveMode();
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		         String download_path = System.getProperty("user.home") + "/Downloads";
		         String download_path1 = download_path.replace("\\", "/");
		         String s =System.getProperty ("os.name");
		         LOGGER.info("download_path= "+download_path);
		         LOGGER.info("download_path1= "+download_path1);
		         LOGGER.info("os name= "+s);
		         String [] win= s.split(" ");
		         String winname=win[0];
		        LOGGER.info("windows name= "+winname);
		         String uploadPath;
		        if(winname.equalsIgnoreCase("Windows"))
		        {
		        	UPLOAD_DIRECTORY=download_path1;
		        	 uploadPath =  "/"+ download_path1;
		        }else{
		        	UPLOAD_DIRECTORY="/home";
		        	uploadPath =  "/"+ download_path1;
		        	LOGGER.info("uploadpath:"+uploadPath);
		        }
		        String remoteFile1 = filepath;
		         File downloadFile1 = new File(uploadPath+"/"+fileName+"");
		         LOGGER.info("downloadFile1:"+downloadFile1);
		         OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
		         LOGGER.info("outputStream1:"+outputStream1);
		         boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
		         outputStream1.flush();
		         LOGGER.info("success:"+success);
		        
		         if (success) 
		         {
		        	//String fileName = request.getParameter("fileName");
			 			if(fileName == null || fileName.equals("")){
			 				LOGGER.info("File Name can't be null or empty");
			 				throw new ServletException("File Name can't be null or empty");
			 			}
			 			String uploadPathName=uploadPath+"/"+fileName;
			 			LOGGER.info("uploadPathName:"+uploadPathName);
			 			File file = new File(uploadPathName);
			 			LOGGER.info("file::"+file);
			 			if(!file.exists()){
			 				LOGGER.info("File doesn't exists on server.");
			 				throw new ServletException("File doesn't exists on server.");
			 			}
			 			LOGGER.info("File location on server::"+file.getAbsolutePath());
			 			//ServletContext ctx = getServletContext();
			 			InputStream fis = new FileInputStream(file);
			 			//String mimeType = ctx.getMimeType(file.getAbsolutePath());
			 			response.setContentType("application/octet-stream");
			 			response.setContentLength((int) file.length());
			 			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

				      	
			 			FileInputStream inStream = new FileInputStream(uploadPathName);
				          LOGGER.info("inStream"+inStream);
				          FileInputStream fileInputStream = new FileInputStream(file);  
				          LOGGER.info("fileInputStream"+fileInputStream);
				          
				          int i;   
				          while ((i=inStream.read()) != -1) {  
				          out.write(i);   
				          }   
				          out.flush();
				          response.flushBuffer();
				          fileInputStream.close();   
				          out.close();   
		             LOGGER.info("File downloaded successfully.");
		             String message="File downloaded successfully";
		             request.setAttribute("successMessage",message);
		             //RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/StudentWorkware.jsp");
		             //requestDispatcher.forward(request, response);
		         }else if(!success)
		        	 LOGGER.info("Failed for downloading file.");
	             String message=""
	             		+ "Failed for downloading file";
	             request.setAttribute("failureMessage",message);
	             RequestDispatcher requestDispatcher1=request.getRequestDispatcher("../JSP/ViewWorkware.jsp");
	             requestDispatcher1.forward(request, response);
	}
			
				
			}
	}

			
	



