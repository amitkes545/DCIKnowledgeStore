package com.kes.kote.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.kes.kote.dao.ModuleControlDao;
import com.kes.kote.dao.TaskControlDao;
import com.kes.kote.util.CLModuleTaskControlUtil;
import com.kes.kote.util.PropertiesUtil;
import com.kes.kote.util.SessionUtil;


public class ImageService extends CommonService  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	FTPClient ftp = null;
	
	private static String ftpToServer = "";
	private static String ftpUserName = "";
	private static String ftpPassword = "";
	private static String ftpTargetPath = "";
	private static String ftpSystemPath="";
	private static String httpTargetPath = "";

	@Override
	public void run() throws ServletException, IOException, FileUploadException, Exception {
		// TODO Auto-generated method stub
		
		ftpToServer = PropertiesUtil.getImageProcessingProperty("ftpServer");
		ftpUserName = PropertiesUtil.getImageProcessingProperty("ftpUserName");
		ftpPassword = PropertiesUtil.getImageProcessingProperty("ftpPassword");
		ftpTargetPath = PropertiesUtil.getImageProcessingProperty("ftpTargetPath");
		ftpSystemPath = PropertiesUtil.getImageProcessingProperty("ftpSystemPath");
		httpTargetPath = PropertiesUtil.getImageProcessingProperty("httpTargetPath");
		
		session=request.getSession();
		
		
		readFileFromFolder();
	}

	
	void readFileFromFolder()
	{
		try
		{
			SessionUtil util=(SessionUtil)session.getAttribute("SessionUtil");
			
			util.print("1");
			ftp = new FTPClient();
			ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
			ftp.connect(ftpToServer);
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				throw new Exception("Exception in connecting to FTP Server");
			}
			ftp.login(ftpUserName, ftpPassword);
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
					
			util.print("2");
			DiskFileItemFactory factory = new DiskFileItemFactory();
	    	
        	ServletContext servletContext = request.getSession().getServletContext();
    		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
    	
    		factory.setRepository(repository);
    		
    		ServletFileUpload upload = new ServletFileUpload(factory);
    		factory.setSizeThreshold(104857600);
    		upload.setSizeMax(104857600);
    			
    		@SuppressWarnings("unchecked")
			List<FileItem> items = upload.parseRequest(request);
    		java.util.Iterator<FileItem> iter = items.iterator();
    		
    		while (iter.hasNext()) {
    		    FileItem fi = iter.next();

    		    if (!fi.isFormField()) {
    		    	
    		    	String fieldName = fi.getFieldName();
    	            String fileName = fi.getName();
    	            String contentType = fi.getContentType();
    	            boolean isInMemory = fi.isInMemory();
    	            long sizeInBytes = fi.getSize();
    	            InputStream inputStream=fi.getInputStream();
    	            
    	            fileName=fileName.split("/")[1];
    	            util.print("3"+fileName);
    	            boolean status=ftp.storeFile(ftpTargetPath + fileName, inputStream);
    	            util.print("4" +status);
    	            
    	            disconnect();
    	            
    	            moveFileToHttpServer(ftpSystemPath + fileName,util);
    	            util.print("6");
    	            
    	            util.print("1="+fieldName+"		2="+fileName+"		3="+contentType+"		4="+isInMemory+"	5="+sizeInBytes);
    		    }
    		    
    		}
    		
    		TaskControlDao taskDao=new TaskControlDao();
    		taskDao.saveTaskControl(CLModuleTaskControlUtil.IMAGE,"Completed",session);
    		ModuleControlDao moduleDao=new ModuleControlDao();
    		moduleDao.saveModuleControl("IMAGE", "Completed", session);
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public static void moveFileToHttpServer(String fromPath,SessionUtil util) {

		InputStream inStream = null;
		OutputStream outStream = null;

		try{
						
			File afile =new File(fromPath);
			util.print("Copied file:"+afile);
			
			File bfile =new File(httpTargetPath+afile.getName());
			
			inStream = new FileInputStream(afile);
			outStream = new FileOutputStream(bfile);

			byte[] buffer = new byte[1024];

			int length;
			//copy the file content in bytes
			while ((length = inStream.read(buffer)) > 0){

				outStream.write(buffer, 0, length);

			}

			inStream.close();
			outStream.close();

			//delete the original file
			afile.delete();//deleteing the FTP file

			util.print("File is copied successful!");

		}catch(IOException e){
			e.printStackTrace();
		}
}
	
	public void disconnect(){
		if (this.ftp.isConnected()) {
			try {
				this.ftp.logout();
				this.ftp.disconnect();
			} catch (IOException f) {
				// do nothing as file is already saved to server
			}
		}
	}
}
