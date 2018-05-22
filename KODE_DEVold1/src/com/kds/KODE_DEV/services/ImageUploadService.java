package com.kds.KODE_DEV.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.domain.FileItemDomain;
//import com.kds.KODE_DEV.dao.UpdateUserInfoDAO;
import com.kds.KODE_DEV.util.PropertiesUtil;


@SuppressWarnings("serial")
public class ImageUploadService extends HttpServlet {
	
	static final Logger LOGGER = Logger.getLogger(ImageUploadService.class);
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 1024 * 4; // 40MB
	private static String UPLOAD_DIRECTORY = "Upload";
	 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//session = request.getSession();
		//String server = "182.75.242.2";
		String server = PropertiesUtil.getProperty("FTP_IP");
		//String ftpServerAddress ="61.12.44.18";
		int port = 21;
		//String userName = "ftpuser1";
		//String passwordftp = "ftp@123#1";
		//String user = "test";
		//String pass = "ftp";
		String user =PropertiesUtil.getProperty("user1");
		String pass =PropertiesUtil.getProperty("password1");
		HttpSession session = req.getSession();
		String imageName = null;
		String newName = null;

		FTPClient ftpClient = new FTPClient();
		try {

			//ftpClient.connect(ftpServerAddress, port);
			//ftpClient.login(userName, passwordftp);
			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();

			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			// APPROACH #1: uploads first file using an InputStream
			//System.out.println("Complete path:" + req.getParameter("imgPath"));
			//File firstLocalFile = new File(req.getParameter("imgPath"));
			ArrayList<FileItemDomain> fromLocalToTmp = fakePathToTmpPath(req);
		//	String filename=fromLocalToTmp.toString();
			//String filename=fromLocalToTmp.getName();
			//System.out.println("fromLocalToTmp="+fromLocalToTmp.get(0));
			String col_name="", org_id="";
			File filename=null;
			for(FileItemDomain al:fromLocalToTmp){
				//System.out.println("hello---"+al.getFile());
				 col_name=al.getItem1();
				 org_id=al.getItem2();
				 filename=al.getFile();
			}
			String newColName=getStringWithoutSpaces(col_name);
			String newfilename=org_id+"_"+newColName+".png";
		//	System.out.println("File name :" + fromLocalToTmp.getName());
			//System.out.println("fromLocalToTmp name :" +fromLocalToTmp);
			//System.out.println("File name :" + filename);
			/*if(session.getAttribute("userName") != null) {
				imageName = session.getAttribute("userName").toString();
				//newName = replaceImageName(imageName, filename);//fromLocalToTmp.getName());
			}*/
			String firstRemoteFile = "/home/test/images/" + newfilename;//filename;// newName;
			// System.out.println("orgid=="+session.getAttribute("orgid"));
			System.out.println("before remote");
			//String firstRemoteFile = "/home/ftpkds1/KnowStore/KDS/"+ filename;//newName;
			System.out.println("after remote"+firstRemoteFile);
			//InputStream inputStream = new FileInputStream(newfilename);//filename);//fromLocalToTmp);
			InputStream inputStream = new FileInputStream(filename);

			System.out.println("Start uploading first file");
			boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
			//inputStream.close();
			if (done) {
				System.out.println("in done");
				// String source="/home/ftpuser1/home/ftpkds1/KnowStore/"+session.getAttribute("orgid")+"/"+ksID.getString() +"/"+session.getAttribute("userid")+"/"+libId.getString()+"/"+testName;
				//String source="/home/ftpuser1/home/ftpkds1/KnowStore/Images/"+newName;
				String source="/home/test/images/"+newfilename;
				    //String source="/home/kes2/Downloads/"+testName;
				    System.out.println("source==="+source);
				    LOGGER.info("source path="+ source);
					//Files.copy(new File("/home/ftpuser1/home/ftpkds1/KnowStore/"+session.getAttribute("orgid")+"/"+ksID.getString() +"/"+session.getAttribute("userid")+"/"+libId.getString()+"/"+testName).toPath(),new File("/var/www/html/KODE_DEV/"+testName).toPath());
					//Files.copy(new File(source).toPath(),new File("/usr/tomcat7/webapps/KODE_DEV/Assets/"+testName).toPath());
				 try{ 
					 //  Files.copy(new File(source).toPath(),new File("/usr/tomcat7/webapps/KnowledgeStore/Images/"+newName).toPath(),StandardCopyOption.REPLACE_EXISTING); 
					 Files.copy(new File(source).toPath(),new File("/home/kes2/KDS/KnowledgeStore/WebContent/images/"+newfilename).toPath(),StandardCopyOption.REPLACE_EXISTING);
				 ///home/kes2/KDS/KnowledgeStore/WebContent
				 }catch(Exception e){
					 LOGGER.info(e);
					 System.out.println("error while copying="+e);
					// LOGGER.info(e.getLocalizedMessage());
				 }
				    LOGGER.info("file copied");
				System.out.println("The first file is uploaded successfully.");
				//UpdateUserInfoDAO updateDao = new UpdateUserInfoDAO();
				//updateDao.updateImageName(newName, imageName);
			}

			inputStream.close();
			// outputStream.close();

			/*boolean completed = ftpClient.completePendingCommand();
			if (completed) {
				System.out.println("The second file is uploaded successfully.");
			}*/

		} catch (IOException ex) {
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
	
	public String getStringWithoutSpaces(String s)
	{
		String str[] = s.split(" ");
		String str2 = "";
		for (int i = 0; i < str.length; i++)
		{   
			str2 = str2.concat(str[i]);
		}
		return str2;
		//System.out.println(str2);
	}


	private String replaceImageName(String replaceName, String actualName) {
		StringBuilder sb = new StringBuilder();
		System.out.println("actualName:"+actualName);
		String[] nameArr = actualName.split("\\.");
		System.out.println("Name 1"+nameArr[0]);
		System.out.println("Name 2"+nameArr[1]);
		for(String name : nameArr) {
			System.out.println(name);
		}
		sb.append(replaceName);
		sb.append(".");
		sb.append(nameArr[1]);
		return sb.toString();
	}

	private ArrayList<FileItemDomain> fakePathToTmpPath(HttpServletRequest request) throws Exception {

		String filePath = null;
		File storeFile = null;
		String fileName = null;

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);
		//upload.setFileSizeMax(MAX_FILE_SIZE);
		factory.setSizeThreshold(20480000);
		upload.setSizeMax(51500000);

		// constructs the directory path to store upload file

		// String uploadPath = "/home/paramvir/" + UPLOAD_DIRECTORY;
		String s = System.getProperty("os.name");
		System.out.println("os name= " + s);
		String[] win = s.split(" ");
		String winname = win[0];
		// String winver=win[1];
		System.out.println("windows name= " + winname);
		String uploadPath;
		if (winname.equalsIgnoreCase("Windows")) {
			UPLOAD_DIRECTORY = "/Windows/Temp";
			uploadPath = "C:" + UPLOAD_DIRECTORY;
		} else {
			UPLOAD_DIRECTORY = "/tmp";
			uploadPath = "/" + UPLOAD_DIRECTORY;
		}
		// C:\Windows\Temp
		System.out.println("upload path= " + uploadPath);

		File uploadDir = new File(uploadPath);
		System.out.println("error in uploadDir=" + uploadDir);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		List<FileItem> items = upload.parseRequest(request);
		java.util.Iterator<FileItem> iter = items.iterator();
		InputStream inputStream = null;
		long sizeInBytes = 0;
		FileItemDomain fid=new FileItemDomain();
		 FileItem course_name=null;
		 FileItem org_id=null;
		 ArrayList<FileItemDomain> fidomain=new ArrayList<FileItemDomain>();
		while (iter.hasNext()) {
			FileItem item = iter.next();
           // System.out.println("kiles: "+item.getName());
            //System.out.println("kiles: "+item.getName());
           // System.out.println("kiles: "+(FileItem) items.get(0));
             course_name=(FileItem) items.get(1);
             org_id=(FileItem) items.get(2);
          
		            System.out.println("course_name: "+course_name.getString());
		            System.out.println("org_id: "+org_id.getString());
		           // System.out.println("course_name2: "+course_name.getName());
			if (item.isFormField()) {
				 
			} else {

				// String fileName = item.getName();

				fileName = new File(item.getName()).getName();
				filePath = uploadPath + File.separator + fileName;
				//filePath = uploadPath + File.separator + org_id+"_"+course_name +"_"+fileName ;
				System.out.println("filePath"+filePath);
				storeFile = new File(filePath);
				item.write(storeFile);
				System.out.println("storeFile"+storeFile);
				// inputStream=item.getInputStream();
				// String itemString=item.getString();

			}
		}
		  fid.setItem1(course_name.getString());
          fid.setItem2(org_id.getString());
          fid.setFile(storeFile);
          System.out.println(" fid.setItem1="+ fid.getItem1());
          System.out.println(" fid.setItem2="+ fid.getItem2());
          fidomain.add(fid);
		return fidomain;

	}

}
