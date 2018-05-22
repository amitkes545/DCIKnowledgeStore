package com.kds.KODE_DEV.services;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dbconnection.DBTransaction;


@SuppressWarnings("serial")
public class Student extends CommonService {
	
	private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
          String filePath="";
          File storeFile;
          //private static final long serialVersionUID = 1L;
      	//static final Logger LOGGER = Logger.getLogger(Student.class); 
          static final Logger LOGGER = Logger.getLogger(Student.class);
	public void run() throws ServletException,IOException{
		FileItem flItem;
	    String fileName = "";
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
       if(winname.equalsIgnoreCase("Windows")){
       	//UPLOAD_DIRECTORY=download_path1;
       	 uploadPath =  "/"+ download_path1;
       	 LOGGER.info("upload path:"+uploadPath);
       }else{
       	//UPLOAD_DIRECTORY="/home";
       	uploadPath =  "/"+ download_path1;
       	LOGGER.info("uploadpath in linux:"+uploadPath);
       }

		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
		//to get values from jsp using multipart form data
				if (isMultiPart) {
					LOGGER.info("welcome");
					//PrintWriter writer = response.getWriter();
					DiskFileItemFactory factory = new DiskFileItemFactory();
					factory.setSizeThreshold(THRESHOLD_SIZE);
					factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
					ServletFileUpload upload = new ServletFileUpload(factory);
					upload.setFileSizeMax(MAX_FILE_SIZE);
					upload.setSizeMax(MAX_REQUEST_SIZE);
					List<FileItem> items = null;
					try {
						items = upload.parseRequest(request);
					} catch (FileUploadException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Iterator<FileItem> iter = items.iterator();

					// FileItem fileItem = iter.next();

					while (iter.hasNext()) {
						FileItem fileItem = iter.next();
						if (!fileItem.isFormField()) {
							flItem = fileItem;
							fileName = new File(flItem.getName()).getName();
							filePath = uploadPath + File.separator + fileName;
							storeFile = new File(filePath);
							LOGGER.info("filePath is=" + filePath);
							LOGGER.info("1.storeFile is=" + storeFile);
							//LOGGER.info("filePath===>>>"+filePath);
							// saves the file on disk
							try {
								flItem.write(storeFile);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}else {
							
						}
					}
		
			try{

				CSVLoader loader = new CSVLoader(getCon());
				
				loader.setSeprator(',');
				
				//loader.loadCSV("/home/lavanya/Documents/studentDetails.csv", "users_info", true);
				String status=loader.loadCSV(filePath,"users_info", true);
				LOGGER.info("status value2:"+status);
				if(status.equals("Inserted")){
					String sessionmsg="Student records inserted successfully";
	    			request.setAttribute("FacultySuccess",sessionmsg);
	    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Student.jsp");
	    			rd.forward(request, response);
				}else if(status.equals("Failure")){
					String sessionmsg="Failure for inserting student records";
	    			request.setAttribute("FacultyFailure",sessionmsg);
	    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Student.jsp");
	    			rd.forward(request, response);
				}else if(status.equals("BatchException")){
					String sessionmsg="File having duplicate records!please check";
	    			request.setAttribute("FacultyFailure",sessionmsg);
	    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Student.jsp");
	    			rd.forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
				}
	}
private static Connection getCon() throws Exception {
	Connection connection = null;
	try {
	 connection= DBTransaction.connect();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return connection;
}
	/*Connection connection = null;
	try {
		Class.forName("org.postgresql.Driver");
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KODE_DEV","postgres","12345");

	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}

	return connection;
}*/
	}


