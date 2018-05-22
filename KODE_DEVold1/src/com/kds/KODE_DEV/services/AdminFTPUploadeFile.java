package com.kds.KODE_DEV.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dbconnection.FTPConnection;

//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class AdminFTPUploadeFile extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(AdminFTPUploadeFile.class);

	private static final long serialVersionUID = 1L;
	String filePath = null;
	File storeFile = null;
	String fileName = null;
	FileInputStream fis = null;
	private static final String UPLOAD_DIRECTORY = "Upload";
	private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	@SuppressWarnings({ "null", "unused", "rawtypes" })
	public void run() throws ServletException, IOException {

		LOGGER.info("action start----------");
		/*// checks if the request actually contains upload file
		if (!ServletFileUpload.isMultipartContent(request)) {
			PrintWriter writer = response.getWriter();
			LOGGER.info("Request does not contain upload data");*/
			// writer.flush();
			// return;
		

		/*// ////////////////////////////////////////////////////////////////////////////
		ServletContext context = getServletContext();
		String param = context.getInitParameter("password");
		LOGGER.info(param);
		Vector v = new Vector();
		Enumeration e = context.getInitParameterNames();
		while (e.hasMoreElements()) {
			LOGGER.info(e.nextElement());
		}*/
		// /////////////////////////////////////////////////////////////////////////////

		// configures upload settings
		//PrintWriter writer = response.getWriter();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(THRESHOLD_SIZE);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(MAX_FILE_SIZE);
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// constructs the directory path to store upload file

		String uploadPath = "/home" + UPLOAD_DIRECTORY;
		/*LOGGER.info(" getServletContext()=" + getServletContext());
		LOGGER.info(" getServletContext().getRealPath()="+ getServletContext().getRealPath(""));
		LOGGER.info(" getServletContext().getRealPath()+fileseparator="+ getServletContext().getRealPath("") + File.separator);*/
		// creates the directory if it does not exist
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		try {
			// parses the request's content to extract file data
			List formItems = (List) upload.parseRequest(request);
			Iterator iter = (Iterator) ((java.util.List) formItems).iterator();

			// iterates over form's fields
			/*while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				// processes only fields that are not form fields
				if (!item.isFormField()) {*/
					File item = null;
					fileName = new File(item.getName()).getName();
					filePath = uploadPath + File.separator + fileName;
					storeFile = new File(filePath);
					LOGGER.info("filePath is=" + filePath);
					LOGGER.info("1.storeFile is=" + storeFile);
					// saves the file on disk
					((FileItem) item).write(storeFile);

				
	
				LOGGER.info("3.storeFile is=" + storeFile);
			}

			// request.setAttribute("message",
			// "Upload has been done successfully!");

		 catch(Exception ex1) {
			// request.setAttribute("message", "There was an error: " +
			// ex.getMessage());
			ex1.printStackTrace();
		}
		
		// getServletContext().getRequestDispatcher("/message.jsp").forward(request,
		// response);

		/*FTPClient ftpclient = new FTPClient();
		
		boolean result;
		String ftpServerAddress = "localhost";
		String userName = "ftpkds1";
		String password = "kompac@123#1";

		try {
			ftpclient.connect(ftpServerAddress);
			result = ftpclient.login(userName, password);

			if (result == true) {
				LOGGER.info("Logged in Successfully !");
			} else {
				LOGGER.info("Login Fail!");
				return;*/
		boolean result;
		FTPConnection ftpConnection = new FTPConnection();
		
		FTPClient ftpClient = ftpConnection.connect();
		
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			ftpClient.changeWorkingDirectory("/home/ftpkds1/");

			File file = new File(storeFile.getPath());
			LOGGER.info(file);
			String testName = fileName;
			LOGGER.info(testName);
			fis = new FileInputStream(file);

			// Upload file to the ftp server
			result = ftpClient.storeFile(testName, fis);

			// DBTransaction dbTransaction = new DBTransaction();

			if (result == true) {

				LOGGER.info("File is uploaded successfully");
			} else {
				LOGGER.info("File uploading failed");
			}
		/*	ftpclient.logout();
		} catch (FTPConnectionClosedException e1) {
			e1.printStackTrace();
		} finally {
			try {
				ftpclient.disconnect();
			} catch (FTPConnectionClosedException e2) {
				LOGGER.info(e2);*/
			}
		}
	
	