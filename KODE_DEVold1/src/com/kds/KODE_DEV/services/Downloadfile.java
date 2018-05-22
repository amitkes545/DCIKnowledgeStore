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
public class Downloadfile extends CommonService 
{
	static String dirname;
	 String UPLOAD_DIRECTORY = "";
	 FTPClient ftp = null;
	 char[] csvAsString=null;
	 String filePath="";
	 
	 static final Logger LOGGER = Logger.getLogger(Downloadfile.class);
	public void run() throws ServletException,IOException
	{
		
		PrintWriter out=response.getWriter();
		//String server ="localhost";
		String server =PropertiesUtil.getProperty("FTP_IP");
		//String server = "ftp://220.225.125.221/";
		int port = 21;
	//	String user ="ftpuser1";
	//	String pass ="ftp@123#1";
		
		String user =PropertiesUtil.getProperty("user1");
		String pass =PropertiesUtil.getProperty("password1");	
		
		
		String filepath=request.getParameter("filePath");
		//String filepath="/home/lavanya/Documents/ForgotPasswordDao.java";
		filePath=filepath.substring(0,filepath.lastIndexOf("/"));
		LOGGER.info("filepath:"+filepath);
		String fileName="";
		
		if(filepath!=null)
		{
			String [] path1=filepath.split("/");
			
			 fileName=path1[path1.length-1];
		}
		FTPClient ftpclient = new FTPClient();
		if(true)
		{	
		    ftpclient.connect(server, port);
		    ftpclient.login(user, pass);
		    ftpclient.enterLocalPassiveMode();
			ftpclient.setFileType(FTP.BINARY_FILE_TYPE);
		

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
	         boolean success = ftpclient.retrieveFile(remoteFile1, outputStream1);
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
		          fileInputStream.close();   
		          out.close();   
		           
	 			/*OutputStream os       = response.getOutputStream();
	 			byte[] bufferData = new byte[1024];
	 			int read=0;
	 			while((read = fis.read(bufferData))!= -1){
	 				os.write(bufferData, 0, read);
	 			}
	 			os.flush();
	 			os.close();
	 			fis.close();*/
	 			/*LOGGER.info("File downloaded at client successfully");
	        	 response.setContentType("APPLICATION/DOWNLOAD;charset=UTF-8"); // Tell browser what content type the response body represents, so that it can associate it with e.g. MS Excel, if necessary.
	      	   response.setHeader("Content-Disposition", "attachment; filename=\""
	      			 + fileName + "\""); // Force "Save As" dialogue
	      	 FileInputStream fileInputStream = new FileInputStream(downloadFile1);
	      			
	      	 
	     	int i;
	     	while ((i = fileInputStream.read()) != -1) {
	     	out.write(i);
	     	response.getWriter().write(i);
	     	}
	     	//response.getWriter().write(i);
	     	fileInputStream.close();
	     	out.close();*/
	  			
	        	// FTPDownloadFileDemo ftpobj=new FTPDownloadFileDemo();
				// ftpobj.downloadFTPFile(fileName, UPLOAD_DIRECTORY);
	             LOGGER.info("File downloaded successfully.");
	             String message="File downloaded successfully";
	             request.setAttribute("message",message);
	             RequestDispatcher rd=request.getRequestDispatcher("../JSP/ParticipantAssessment.jsp?message="+message);
	             rd.forward(request, response);
	         }else if (!success){
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
		 			/*OutputStream os = response.getOutputStream();
		 	        byte[] buf = new byte[8192];
		 	        InputStream is = new FileInputStream(file);
		 	        int c = 0;
		 	        while ((c = is.read(buf, 0, buf.length)) > 0) {
		 	            os.write(buf, 0, c);
		 	            os.flush();
		 	        }
		 	        os.close();
		 	        is.close();*/
		 			FileInputStream inStream = new FileInputStream(uploadPathName);
			          LOGGER.info("inStream"+inStream);
			          FileInputStream fileInputStream = new FileInputStream(file);  
			          LOGGER.info("fileInputStream"+fileInputStream);
			          
			          int i;   
			          while ((i=inStream.read()) != -1) {  
			          out.write(i);   
			          }   
			          fileInputStream.close();   
			          out.close(); 

			      	/* FileInputStream fileInputStream = new FileInputStream(downloadFile1);
		      			
			      	 
				     	int i;
				     	while ((i = fileInputStream.read()) != -1) {
				     	out.write(i);
				     	}
				     	response.getWriter().write(i);
				     	fileInputStream.close();
				     	out.close();*/
		 			/*OutputStream os       = response.getOutputStream();
		 			byte[] bufferData = new byte[1024];
		 			int read=0;
		 			while((read = fis.read(bufferData))!= -1){
		 				os.write(bufferData, 0, read);
		 			}
		 			os.flush();
		 			os.close();
		 			fis.close();*/
		 			
	        	 /*response.setContentType("APPLICATION/DOWNLOAD;charset=UTF-8"); // Tell browser what content type the response body represents, so that it can associate it with e.g. MS Excel, if necessary.
		      	   response.setHeader("Content-Disposition", "attachment; filename=\""
		      			 + fileName + "\""); // Force "Save As" dialogue
		      	
		      	 FileInputStream fileInputStream = new FileInputStream(downloadFile1);
	      			
		      	 
			     	int i;
			     	while ((i = fileInputStream.read()) != -1) {
			     	out.write(i);
			     	}
			     	response.getWriter().write(i);
			     	fileInputStream.close();
			     	out.close();*/
	        	 LOGGER.info("File downloaded successfully.");
	             String message="File downloaded successfully";
	             request.setAttribute("message",message);
	             RequestDispatcher rd=request.getRequestDispatcher("../JSP/ParticipantAssessment.jsp?message="+message);
	             rd.forward(request, response);
	         }
		}
	}
}