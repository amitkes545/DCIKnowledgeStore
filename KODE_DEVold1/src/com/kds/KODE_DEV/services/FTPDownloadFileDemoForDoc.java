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

/**
 * A program demonstrates how to upload files from local computer to a remote
 * FTP server using Apache Commons Net API.
 *
 * @author www.codejava.net
 */
@SuppressWarnings("serial")
public class FTPDownloadFileDemoForDoc extends CommonService {

	static String dirname;
	
	 String UPLOAD_DIRECTORY = "";
	 
	 static final Logger LOGGER = Logger.getLogger(FTPDownloadFileDemoForDoc.class);
	public void run() throws ServletException, IOException {
		System.out.println("in download");
		String filepath = request.getParameter("filePath");
		LOGGER.info("filepath:" + filepath);
		String fileName = "";
		PrintWriter out=response.getWriter();
		if (filepath != null) {
			String[] path1 = filepath.split("/");
			fileName = path1[path1.length - 1];
		}
		try {

			//String server = "localhost";
			//String server = "61.12.44.18";
			
			String server=PropertiesUtil.getProperty("FTP_IP");
			
			System.out.println("FTP_Server IP ="+server);
			
			
			//String server = "ftp://220.225.125.221/";
			int port = 21;
		//	String user = "ftpuser1";
		//	String pass = "ftp@123#1";
			
			
			String user =PropertiesUtil.getProperty("user1");
			String pass =PropertiesUtil.getProperty("password1");	
			
		//	final String name = null;
			//final FTPClient ftpClient = new FTPClient();

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
		         if (success) {
						//FTPDownloadFileDemo ftpobj=new FTPDownloadFileDemo();
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
			 				System.out.println("File doesn't exists on server.");
			 				throw new ServletException("File doesn't exists on server.");
			 			}
			 			LOGGER.info("File location on server::"+file.getAbsolutePath());
			 			System.out.println("File location on server::"+file.getAbsolutePath());
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
				          
					//}
						String successmsg = "File downloaded successfully";
						System.out.println("successmsg="+successmsg);
						request.setAttribute("successMessage", successmsg);
					/*	RequestDispatcher rd = request.getRequestDispatcher("../JSP/CertifyStudent.jsp");
						rd.forward(request, response);
						*/
				          } 
					if (!success) {

						String failuremsg = "failed for downloading file";
						System.out.println("failuremsg="+failuremsg);
						request.setAttribute("failureMessage", failuremsg);
						RequestDispatcher rd = request.getRequestDispatcher("../JSP/DocumentVerification.jsp");
						rd.forward(request, response);
					}
				}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	}

			/*
			 * try { files =
			 * ftp.listFiles("/home/ftpkds1/Kpostbox/ID/"+username
			 * +"/Migration/gmail/" + gmailID + "/"); } catch (IOException ex) {
			 * Logger
			 * .getLogger(FTPDownloadFileDemo.class.getName()).log(Level.SEVERE,
			 * null, ex); }
			 */
			/*JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("Chose location");
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setAcceptAllFileFilterUsed(true);
			int i=JFileChooser.CANCEL_OPTION;
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				LOGGER.info("getCurrentDirectory(): "
						+ chooser.getCurrentDirectory());
				LOGGER.info("getSelectedFile() : "
						+ chooser.getSelectedFile());

				dirname = chooser.getSelectedFile() + "/Downloads/";
				LOGGER.info(dirname);
				File dir = new File(dirname);
				dir.mkdirs();*/
			/*response.setContentType("text/csv"); // Tell browser what content type the response body represents, so that it can associate it with e.g. MS Excel, if necessary.
       	   response.setHeader("Content-Disposition", "attachment; filename="+filepath); // Force "Save As" dialogue
              String csvAsString = null;
			response.getWriter().write(csvAsString);*/
				/* for (int i = 0; i < files.length; i++) { */
				/*OutputStream outputStream1 = null;
				try {

					// String remoteFile1 =
					// "/home/ftpkds1/KnowStore/SRM/Admin/Downloadfile.java";
					String remoteFile1 = filepath;
					File downloadFile1 = new File(csvAsString + "/" + fileName);
					LOGGER.info("downloadFile1" + downloadFile1);
					outputStream1 = new BufferedOutputStream(
							new FileOutputStream(downloadFile1));
					try {
						boolean b = ftp
								.retrieveFile(remoteFile1, outputStream1);
						LOGGER.info("boolean value:" + b);
						if (b) {
							FTPDownloadFileDemo ftpobj=new FTPDownloadFileDemo();
							 ftpobj.downloadFTPFile("Shruti.txt", "/users/shruti/Shruti.txt");
							String successmsg = "File downloaded successfully";
							request.setAttribute("successMessage", successmsg);
							RequestDispatcher rd = request
									.getRequestDispatcher("../JSP/AssessmentCertifyStudent.jsp");
							rd.forward(request, response);
						}
						if (!b) {

							String successmsg = "failed for downloading file";
							request.setAttribute("failureMessage", successmsg);
							RequestDispatcher rd = request
									.getRequestDispatcher("../JSP/AssessmentCertifyStudent.jsp");
							rd.forward(request, response);
						}
					} catch (IOException ex) {
						Logger.getLogger(FTPDownloadFileDemo.class.getName())
								.log(Level.SEVERE, null, ex);
					}
					byte[] bytesArray = new byte[4096];
					int bytesRead = -1;
					try {
						outputStream1.close();
					} catch (IOException ex) {
						Logger.getLogger(FTPDownloadFileDemo.class.getName())
								.log(Level.SEVERE, null, ex);
					}
				} catch (FileNotFoundException ex) {
					Logger.getLogger(FTPDownloadFileDemo.class.getName()).log(
							Level.SEVERE, null, ex);
				} finally {
					try {
						outputStream1.close();
					} catch (IOException ex) {
						Logger.getLogger(FTPDownloadFileDemo.class.getName())
								.log(Level.SEVERE, null, ex);
					}
				}

				 } 
						 * else { LOGGER.info("No Selection "); }
						 

				try {
					if (ftpClient.isConnected()) {
						ftpClient.logout();
						ftpClient.disconnect();
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				// FetchOldGmail redGmail=new FetchOldGmail();
				// redGmail.setVisible(true);
			} 
			
			
			
			if(i==1)
			{
				// JOptionPane.showMessageDialog(this,
				// "Dialog cancelled by the user");
				LOGGER.info("user cancel selected"
						+ JFileChooser.CANCEL_OPTION);
				RequestDispatcher rd = request
						.getRequestDispatcher("../JSP/AssessmentCertifyStudent.jsp");
				rd.forward(request, response);
				// I assume I need something here...

			}
		//} 
	catch (Exception ex) {
			Logger.getLogger(FTPDownloadFileDemo.class.getName()).log(
					Level.SEVERE, null, ex);
		}
	}
	 // Download the FTP File from the FTP Server
    public void downloadFTPFile(String source, String destination) {
        try (FileOutputStream fos = new FileOutputStream(destination)) {
            this.ftp.retrieveFile(source, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    
