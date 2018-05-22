
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
import javax.servlet.ServletOutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.util.PropertiesUtil;

	@SuppressWarnings("serial")
	public class DownloadVideo extends CommonService 
	{
	
		static String dirname;
		String UPLOAD_DIRECTORY = "";
		 static final Logger LOGGER = Logger.getLogger(DownloadVideo.class);
		public void run() throws ServletException,IOException
		{
			String fileName="";
			PrintWriter out=response.getWriter();
			
			
			String filepath="/home/ftpuser1/"+request.getParameter("filePath");
			System.out.println("request filepath: "+filepath);
			if(filepath!=null)
			{
				String [] path1=filepath.split("/");
				 fileName=path1[path1.length-1];
			}
			//String server = "localhost";
			String server = PropertiesUtil.getProperty("FTP_IP");
			//String server = "220.225.125.221"; 
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
				//ftpClient.enterLocalPassiveMode();
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				ftpClient.changeWorkingDirectory("/home/ftpkds1/");
		         String download_path = System.getProperty("user.home") + "/Downloads";
		         //String download_path = "D:/Downloads";
		         String download_path1 = download_path.replace("\\", "/");
		         String s =System.getProperty ("os.name");
		         String [] win= s.split(" ");
		         String winname=win[0];
		         String uploadPath;
		        if(winname.equalsIgnoreCase("Windows"))
		        {
		        	UPLOAD_DIRECTORY=download_path1;
		        	 uploadPath =  "/"+ download_path1;
		        }else{
		        	UPLOAD_DIRECTORY="/home";
		        	uploadPath =  "/"+ download_path1;
		        }
		        String remoteFile1 = filepath;
		        System.out.println("filepath="+filepath);
		         File downloadFile1 = new File(uploadPath+"/"+fileName+"");
		         System.out.println("downloadFile1="+downloadFile1);
		         BufferedOutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
		         //InputStream fis = new FileInputStream("D:/Downloads/Income Form.pdf");
		         //OutputStream outputStream1 = ftpClient.storeFileStream(downloadFile1+ "B.pdf");
		         System.out.println("remoteFile1="+remoteFile1);
		         boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
		         outputStream1.flush();
		         //outputStream1.close();
		         System.out.println("outputStream1="+outputStream1);
		      // APPROACH #2: using InputStream retrieveFileStream(String)
		            //String remoteFile2 = "/test/song.mp3";
		            //File downloadFile2 = new File("D:/Downloads/song.mp3");
		            //OutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
		         /*   InputStream inputStream = ftpClient.retrieveFileStream(remoteFile1);
		            byte[] bytesArray = new byte[4096];
		            int bytesRead = -1;
		            while ((bytesRead = inputStream.read(bytesArray)) != -1) {
		                outputStream1.write(bytesArray, 0, bytesRead);
		            }
		            boolean success = ftpClient.completePendingCommand(); */
		            System.out.println("success="+success);
/// APPROACH #2 END
		            
		         if (success) 
		         {
			 			if(fileName == null || fileName.equals("")){
			 				LOGGER.info("File Name can't be null or empty");
			 				throw new ServletException("File Name can't be null or empty");
			 			}
			 			String uploadPathName=uploadPath+"/"+fileName;
			 			File file = new File(uploadPathName);
			 			if(!file.exists()){
			 				throw new ServletException("File doesn't exists on server.");
			 			}
			 			//InputStream fis = new FileInputStream(file);
			 			//FileInputStream fis = (FileInputStream) ftpClient.retrieveFileStream(remoteFile1);
			 			/*
			 			String extension="";
			 			int ind=fileName.lastIndexOf(".");
			 			if(ind!=-1) 
			 			extension = fileName.substring(ind);
			 			
			 			
			 			LOGGER.info("file extension="+extension);
			 			System.out.println("file extension="+extension);
			 			if(extension.equalsIgnoreCase(".pdf"))
			 				{
			 				response.setContentType("application/pdf");
			 				System.out.println("file extension= In content type pdf");
			 				}
			 			else if(extension.equalsIgnoreCase(".xlsx") || extension.equalsIgnoreCase(".xls"))
			 				response.setContentType("application/vnd.ms-excel"); 
			 			else if(extension.equalsIgnoreCase(".doc") || extension.equalsIgnoreCase(".docx"))
			 				response.setContentType("application/ms-word");
			 			else
			 				response.setContentType("application/octet-stream");
			 			*/
			 			response.setContentType("application/octet-stream");
			 			System.out.println("content set");
			 			response.setContentLength((int) file.length());
			 			
			 			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			 			FileInputStream inStream = new FileInputStream(uploadPathName);
				          //FileInputStream fileInputStream = new FileInputStream(file); 
//			 			FileInputStream inStream = (FileInputStream) ftpClient.retrieveFileStream(remoteFile1);
			 			//response.setContentLength(fileInputStream. 
			 			
			 			System.out.println("file absolute path=" + file.getAbsolutePath());
	/*			          
				          ServletOutputStream os       = response.getOutputStream();
				          
				          byte[] bufferData = new byte[1024];
				                  int read=0;
				                  while((read = fileInputStream.read(bufferData))!= -1){
				                      os.write(bufferData, 0, read);
				                  }
				                  os.flush();
				                  os.close();
				                  fileInputStream.close();
*/
				          
				          int i;
				          while ((i=inStream.read()) != -1) {  
				          out.write(i);   
				          }   
				          out.flush();
				          response.flushBuffer();
				          inStream.close();   
				          out.close();
				             
		             String message="File downloaded successfully";
		             System.out.println(message);
		             request.setAttribute("successMessage",message);
		         }else if(!success)
		        	 LOGGER.info("Failed for downloading file.");
	             String message="Failed for downloading file";
	             request.setAttribute("failureMessage",message);
	             RequestDispatcher requestDispatcher1=request.getRequestDispatcher("../JSP/KnowStoreStudent.jsp");
	}
			}
	}
