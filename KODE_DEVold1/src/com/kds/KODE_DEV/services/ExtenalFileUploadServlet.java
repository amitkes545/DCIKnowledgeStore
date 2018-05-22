package com.kds.KODE_DEV.services;

import java.io.File;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@SuppressWarnings("serial")
public class ExtenalFileUploadServlet extends CommonService {
	String status="";
	String filePath = null;
	File storeFile = null;
	String fileName = null;
	private static final String UPLOAD_DIRECTORY = "Upload";
	private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	FileItem flItem;

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public void run() throws Exception {
		String testName =null;
		boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
		if (isMultiPart) 
		{
				//System.out.println("welcome");
			PrintWriter writer = response.getWriter();
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(THRESHOLD_SIZE);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setSizeMax(MAX_REQUEST_SIZE);

			// constructs the directory path to store upload file

			String uploadPath = "c:/" + UPLOAD_DIRECTORY;

			File uploadDir = new File(uploadPath);
			
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			List items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();

			//FileItem fileItem = iter.next();
			
			while (iter.hasNext()) {
				FileItem	fileItem = iter.next();
				if (!fileItem.isFormField()) {
					flItem = fileItem;
					fileName = new File(flItem.getName()).getName();
					filePath = uploadPath + File.separator + fileName;
					storeFile = new File(filePath);
					
					//System.out.println("filePath is=" + filePath);
					//System.out.println("1.storeFile is=" + storeFile);
					// saves the file on disk
					flItem.write(storeFile);
					status="success";
				}
			}

			//System.out.println("3.storeFile is=" + storeFile);
		}
/*
				if ("success".equals(status)) {
		
			// String ksid="";
			FTPClient ftpclient = new FTPClient();
			FileInputStream fis = null;
			boolean result;
			String ftpServerAddress = "61.12.87.139";
			String userName = "ftpkds1";
			String password = "kompac@123#1";

			try {
				ftpclient.connect(ftpServerAddress);
				result = ftpclient.login(userName, password);

				if (result == true) {
					//System.out.println("Logged in Successfully !");

					ftpclient.setFileType(FTP.BINARY_FILE_TYPE);
					//ftpclient.changeWorkingDirectory("/home/ftpkds1/KnowStore/SRM/Admin/");
					//boolean folderCreated = ftpclient.makeDirectory("ks45");

					if (true) {
						//System.out.println("Directory created successfully !");
						ftpclient.changeWorkingDirectory("/home/ftpkds1/KnowStore/SRM/Admin/ks45/");
						File file = new File(storeFile.getPath());
						//System.out.println(file);
						 testName = fileName;
						//System.out.println(testName);
						fis = new FileInputStream(file);

						//Upload file to the ftp server
						result = ftpclient.storeFile(testName, fis);

						 //DBTransaction dbTransaction = new DBTransaction();

						if (result == true) {

							//System.out.println("File is uploaded successfully");
						} else {
							//System.out.println("File uploading failed");
						}
						ftpclient.logout();
					} else {
						//System.out.println("Error in creating directory !");
					}
				} else {
					//System.out.println("Login Fail!");
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
					//System.out.println(e2);
				}
			}
			
			
			//String str = (String)session.getAttribute("userid");
			//File f1=new File(filePath);
			//System.out.println(testName);
			String[] details=testName.split(".");
			for(String s1:details){
				//System.out.println(s1);
			}
		//	String fileNameWithoutExtension=details[0];
			////System.out.println(details[0]);
		//	String ext=details[1];
			////System.out.println(details[1]);
			int size=fileName.length();
			//System.out.println(size);
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		       //get current date time with Date()
		       Date date = new Date();
		       //System.out.println(dateFormat.format(date));
			
			Calendar calendar = Calendar.getInstance();
			java.util.Date now = calendar.getTime();
			java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(
					now.getTime());
			
			DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
		       //get current date time with Date()
		       Date date1 = new Date();
		       //System.out.println("time:"+dateFormat1.format(date1));
		     
		       
	 
			//FacilitatorReportKnowDao fDao = new FacilitatorReportKnowDao();

	}*/

}
}