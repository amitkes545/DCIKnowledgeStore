package com.kds.KODE_DEV.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import com.kds.KODE_DEV.util.PropertiesUtil;

@SuppressWarnings("serial")
public class ParticipantAssignmentFileDownload extends CommonService {
	 String UPLOAD_DIRECTORY = "";
	public void run() throws ServletException,IOException
	{
		
		String server =PropertiesUtil.getProperty("FTP_IP");
		 //String server = "ftp://220.225.125.221/";
		int port = 21;
		
		String user =PropertiesUtil.getProperty("user1");
		String pass =PropertiesUtil.getProperty("password1");	
		
	//	String user ="ftpuser1";
	//	String pass ="ftp@123#1";
		FTPClient ftpclient = new FTPClient();
		//FtpTransaction con=new FtpTransaction();
		String filepath=request.getParameter("filePath");
		if(filepath!=null)
		{
			String [] path1=filepath.split("/");
			String fileName=path1[path1.length-1];
		boolean status=true;
		if(status)
		{	
			  	ftpclient.connect(server, port);
		         ftpclient.login(user, pass);
		         ftpclient.enterLocalPassiveMode();
		         ftpclient.setFileType(FTP.BINARY_FILE_TYPE);
	         String download_path = System.getProperty("user.home") + "/Downloads";
	         String download_path1 = download_path.replace("\\", "/");
	         String s =System.getProperty ("os.name");
	         //System.out.println("download_path= "+download_path);
	         //System.out.println("download_path1= "+download_path1);
	         //System.out.println("os name= "+s);
	         String [] win= s.split(" ");
	         String winname=win[0];
	         //System.out.println("windows name= "+winname);
	         String uploadPath;
	        if(winname.equalsIgnoreCase("Windows")){
	        	UPLOAD_DIRECTORY=download_path1;
	        	 uploadPath =  "/"+ download_path1;
	        }else{
	        	UPLOAD_DIRECTORY="/home";
	        	uploadPath =  "/"+ download_path1;
	        }String remoteFile1 = filepath;
	         File downloadFile1 = new File(uploadPath+"/"+fileName+"");
	         OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
	         boolean success = ftpclient.retrieveFile(remoteFile1, outputStream1);
	         outputStream1.close();

	         if (success) {
	             //System.out.println("File downloaded successfully.");
	             response.sendRedirect("../JSP/ParticipantAssignment.jsp");
	         }

		}
		
			
		}
	}

}

