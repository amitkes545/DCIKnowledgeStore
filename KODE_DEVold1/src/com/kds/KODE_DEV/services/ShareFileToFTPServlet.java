package com.kds.KODE_DEV.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.AssessmentDao;
import com.kds.KODE_DEV.dao.SessionDao;
import com.kds.KODE_DEV.domain.SessionDomain;
import com.kds.KODE_DEV.util.PropertiesUtil;

@SuppressWarnings("serial")
public class ShareFileToFTPServlet extends CommonService{
	static final Logger LOGGER = Logger.getLogger(ShareFileToFTPServlet.class);
	String status = "";
	String filePath = null;
	File storeFile = null;
	String fileName = null;
	int returnCode = 0;
	String ftpFilePath="";
	// private String UPLOAD_DIRECTORY;
	private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	FileItem flItem;
      
	public void run() throws ServletException, IOException, FileUploadException {
		session=request.getSession(true);
        FTPClient ftpClient = new FTPClient();
        SessionDomain sessionDomain=new SessionDomain();
        AssessmentDao assessmentDao=new AssessmentDao();
		//SearchDao searchdao=new SearchDao();
		SessionDao sessionDao=new SessionDao();
		//retriving organization id from session
		 String organizationID=session.getAttribute("orgid").toString();
		 String facultyId=session.getAttribute("userid").toString();
		 ////System.out.println("organizationID:"+organizationID);
        boolean ftpLoginResult;
		boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
		String SelectedID="",facultyid="",groupid="", All="",groupvalue="",orgid="";
		String fullpath="",sessionName="" ;
		String UPLOAD_DIRECTORY ,ftpFilePath="";
		
        //to get local  system path
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
       	//UPLOAD_DIRECTORY=download_path1;
       	 uploadPath =  "/"+ download_path1;
       	 //System.out.println("upload path:"+uploadPath);
       }else{
       	//UPLOAD_DIRECTORY="/home";
       	uploadPath =  "/"+ download_path1;
       	//System.out.println("uploadpath in linux:"+uploadPath);
       }

		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		//to get values from jsp using multipart form data
		if (isMultiPart) {
			//System.out.println("welcome");
			//PrintWriter writer = response.getWriter();
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(THRESHOLD_SIZE);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setSizeMax(MAX_REQUEST_SIZE);
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();

			// FileItem fileItem = iter.next();

			while (iter.hasNext()) {
				FileItem fileItem = iter.next();
				if (!fileItem.isFormField()) {
					flItem = fileItem;
					fileName = new File(flItem.getName()).getName();
					filePath = uploadPath + File.separator + fileName;
					storeFile = new File(filePath);
					//System.out.println("filePath is=" + filePath);
					//System.out.println("1.storeFile is=" + storeFile);
					// saves the file on disk
					try {
						flItem.write(storeFile);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					status = "success";
				}else {
					String asname=fileItem.getFieldName();
			    	String value = fileItem.getString();
			    	if(asname.equals("sessionName")){sessionName=value;}
			    	if(asname.equals("student_id")){SelectedID=value;}
			    	if(asname.equals("facultyid")){facultyid=value;}
			    	if(asname.equals("orgid")){orgid=value;}
			        if(asname.equals("group_id")){groupid=value;}
			        if(asname.equals("group")){All=value;}
			    	//System.out.println("student id:"+SelectedID+ "facultyid:"+facultyid+ "groupid:"+groupid+"group:"+All);
				}
			}
			//creating methods to retrive group values from dao
			/*String ksid=assessmentDao.getKsId(facultyid,orgid);
			//System.out.println("ksid is:"+ksid);*/
			SessionDomain sDomain=sessionDao.getSessionId(facultyid,orgid,sessionName);
			//System.out.println("session id in servlet:"+sDomain.getSessionId()+" costof session:"+sDomain.getCostOfSession());
			if(groupid!="select"){
				 groupvalue=assessmentDao.getGroupValues(groupid,facultyId,organizationID);
				//System.out.println("group values:"+groupvalue);
			}
			
 
			//System.out.println("3.storeFile is=" + storeFile);
			//setting values to domain
			sessionDomain.setFacultyId(facultyid);
			sessionDomain.setIndividualId(SelectedID);
			sessionDomain.setGroupId(groupvalue);
			sessionDomain.setSessionId(sDomain.getSessionId());
			sessionDomain.setSessionName(sessionName);
			sessionDomain.setCostOfSession(sDomain.getCostOfSession());
			sessionDomain.setCategory(sDomain.getCategory());
			sessionDomain.setOrgId(orgid);
			sessionDomain.setPathOfFile(fullpath);
			//System.out.println("path of file:"+sessionDomain.getPathOfFile());
		}
		
        //start ftp server code
        try {
            FileInputStream fis = null;
            // Connect to the localhost
            //ftpClient.connect("61.12.87.139");
           // String ftpServerAddress = "ftp://220.225.125.221/";
            String ftpServerAddress = PropertiesUtil.getProperty("FTP_IP");
            ftpClient.connect(ftpServerAddress);
            
        	String user =PropertiesUtil.getProperty("user1");
			String pass =PropertiesUtil.getProperty("password1");	
            
       //     String user = "ftpuser1";
       //     String pass = "ftp@123#1";
            
            
            // login to ftp server
            ftpLoginResult = ftpClient.login(user, pass);
           //System.out.println("ftpLoginResult:"+ftpLoginResult);

            if (ftpLoginResult) {
                //System.out.println("Logged in Successfully !");

                ftpFilePath = "KnowStore1"+"/"+organizationID+"/"+facultyId;
                LOGGER.info("ftpFilePath="+ftpFilePath);
                String pathElements1[] = ftpFilePath.split("/");
                if (pathElements1 != null && pathElements1.length > 0) {
                    for (String singleDir : pathElements1) {
                        boolean existed = ftpClient.changeWorkingDirectory(singleDir);
                        //System.out.println("CurrentDir: " + singleDir);

                        if (!existed) {
                            boolean created = ftpClient.makeDirectory(singleDir);
                            ftpClient.changeWorkingDirectory(singleDir);
                            //System.out.println("CurrentDir: " + singleDir);
                            
                            if (created) {
                            	

                                //System.out.println("Directory created successfully !");

                            } else {
                                //System.out.println("Error in creating directory !");
                                String sessionmsg="Error in creating directory !";
				    			request.setAttribute("FacultyFailure",sessionmsg);
				    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ShareFileToFTP.jsp");
				    			rd.forward(request, response);
                            }

                        }
                    }
                    
                    String fullFtpFilePath=ftpFilePath+"/"+fileName;
                    sessionDomain.setPathOfFile(fullFtpFilePath);//setting file path into domain
                    String localFileName=uploadPath+"/"+fileName;
                    //System.out.println("ftp path:"+localFileName);
                    File file = new File(localFileName);
                    String testName = file.getName();
                    fis = new FileInputStream(file);
                    // Upload file to the ftp server
                    ftpLoginResult = ftpClient.storeFile(testName, fis);
                    
                    if (ftpLoginResult) {
						if("select".equalsIgnoreCase(groupid) && "select".equalsIgnoreCase(SelectedID)){
							//System.out.println("All are:");
							String AllStudents=sessionDao.insertSessionAll(sessionDomain);
							
							//System.out.println("GroupResult are:"+AllStudents);
							 if("valid".equalsIgnoreCase(AllStudents)){
				         		  String fileStatus="File uploaded successfully";
				      			request.setAttribute("FacultySuccess",fileStatus);
				      			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ShareFileToFTP.jsp");
				      			rd.forward(request, response);
							   }
					            else if("notvalid".equalsIgnoreCase(AllStudents)){
					         		  String fileStatus="Uploaded file failed";
					      			request.setAttribute("FacultyFailure",fileStatus);
					      			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ShareFileToFTP.jsp");
					      			rd.forward(request, response);
					         	   }
				      			
                         }
						 else if("select".equalsIgnoreCase(SelectedID)){
							 //System.out.println("radia button for group");
							 String GroupResult=sessionDao.insertSessionGroupId(sessionDomain);
							
								 //System.out.println("radia button for all"+GroupResult);
								 if("valid".equalsIgnoreCase(GroupResult)){
					         		  String fileStatus="File uploaded successfully";
					      			request.setAttribute("FacultySuccess",fileStatus);
					      			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ShareFileToFTP.jsp");
					      			rd.forward(request, response);
								   }
						            else if("notvalid".equalsIgnoreCase(GroupResult)){
						         		  String fileStatus="Uploaded file failed";
						      			request.setAttribute("FacultyFailure",fileStatus);
						      			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ShareFileToFTP.jsp");
						      			rd.forward(request, response);
						         	   }
								
	                             }else if("select".equalsIgnoreCase(groupid)){
	                            	 //System.out.println("SelectedResult:");
	                            	 String SelectedResult=sessionDao.insertSessionIndividual(sessionDomain);
	     							//System.out.println("SelectedResult:"+SelectedResult);
	     							if("valid".equalsIgnoreCase(SelectedResult)){
						         		  String fileStatus="File uploaded successfully";
						      			request.setAttribute("FacultySuccess",fileStatus);
						      			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ShareFileToFTP.jsp");
						      			rd.forward(request, response);
									   }
							            else if("notvalid".equalsIgnoreCase(SelectedResult)){
							         		  String fileStatus="Uploaded file failed";
							      			request.setAttribute("FacultyFailure",fileStatus);
							      			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ShareFileToFTP.jsp");
							      			rd.forward(request, response);
							         	   }
	                             }
					} else {
						//System.out.println("File uploading failed");
						 String fileStatus="Uploaded file failed";
						request.setAttribute("FacultyFailure",fileStatus);
		      			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ShareFileToFTP.jsp");
		      			rd.forward(request, response);
					}
					
                }
            }
            
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
}
            
    


