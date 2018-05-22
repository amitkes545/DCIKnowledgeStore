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
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.AssignmentDao;
import com.kds.KODE_DEV.dao.SearchDao;
import com.kds.KODE_DEV.domain.AssessmentDomain;
import com.kds.KODE_DEV.util.PropertiesUtil;

@SuppressWarnings("serial")
public class AssignmentServlet extends CommonService{
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
	static final Logger LOGGER = Logger.getLogger(AssignmentServlet.class);
	public void run() throws ServletException, IOException {
		
		session=request.getSession(false);
		
        FTPClient ftpClient = new FTPClient();
        AssessmentDomain assignmentDomain=new AssessmentDomain();
        AssignmentDao assignmentDao=new AssignmentDao();
		SearchDao searchdao=new SearchDao();
		//retriving organization id from session
		 String organizationID=session.getAttribute("orgid").toString();
		 String facultyId=session.getAttribute("userid").toString();
		 ////System.out.println("organizationID:"+organizationID);
        boolean ftpLoginResult;
		boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
		String Description="",SelectedID="",facultyid="",groupid="", AssignmentName="",All="",groupvalue="",orgid="",AssignMentId="";
		String path="",fullpath="",Marks="" ,assignmentId="";
		//String UPLOAD_DIRECTORY;
        //to get local  system path
        String download_path = System.getProperty("user.home") + "/Downloads";
        String download_path1 = download_path.replace("\\", "/");
        String s =System.getProperty ("os.name");
        System.out.println("download_path= "+download_path);
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
			    	if(asname.equals("assignId")){AssignMentId=value;}
					if(asname.equals("Description")){Description=value;}
			    	if(asname.equals("student_id")){SelectedID=value;}
			    	if(asname.equals("facultyid")){facultyid=value;}
			    	if(asname.equals("orgid")){orgid=value;}
			        if(asname.equals("group_id")){groupid=value;}
			        if(asname.equals("assignmentname")){AssignmentName=value;}
			        if(asname.equals("group")){All=value;}
			        if(asname.equals("marks")){Marks=value;}
			    	//System.out.println("description:"+Description+ "student id:"+SelectedID+ "facultyid:"+facultyid+ "groupid:"+groupid+"Asname:"+AssignmentName+"group:"+All);
				}
			}
			//creating methods to retrive group values from dao
			String ksid=assignmentDao.getKsId(facultyid,orgid);//selecting ksid from dao
			//System.out.println("ksid is:"+ksid);
			if(groupid!="select"){
				 groupvalue=assignmentDao.getGroupValues(groupid);//selecting group values from dao
				//System.out.println("group values:"+groupvalue);
			}
			String sequence=searchdao.getAggignSequenceValue();//selecting sequence value from dao
			 assignmentId="assign"+sequence;
			//System.out.println("assignid:"+assignmentId);
 
			//System.out.println("3.storeFile is=" + storeFile);
			//seting values to domain
			assignmentDomain.setUploadedBy(facultyid);
			assignmentDomain.setDescrition(Description);
			assignmentDomain.setIndividualId(SelectedID);
			assignmentDomain.setGroupId(groupvalue);
			assignmentDomain.setOrgId(orgid);
			assignmentDomain.setPathOfFile(fullpath);
			assignmentDomain.setAssessmentName(AssignmentName);
			assignmentDomain.setAssessmentId(assignmentId);
			assignmentDomain.setMark(Marks);
		}
		
        //start ftp server code
        try {
            FileInputStream fis = null;
            // Connect to the localhost
           // ftpClient.connect("61.12.87.139");
           // String ftpServerAddress = "ftp://220.225.125.221/";
            //String ftpServerAddress = "61.12.44.18";
            String ftpServerAddress =PropertiesUtil.getProperty("FTP_IP");
            ftpClient.connect(ftpServerAddress);
            // login to ftp server
           // System.out.println("before conneciton to ftp");
            
        //	String user = "ftpuser1";
     	//	String pass = "ftp@123#1";
     		
            String user =PropertiesUtil.getProperty("user1");
			String pass =PropertiesUtil.getProperty("password1");	
            
            
            ftpLoginResult = ftpClient.login(user, pass);
           //System.out.println("ftpLoginResult:"+ftpLoginResult);

            if (ftpLoginResult) {
                //System.out.println("Logged in Successfully !");

                String ftpFilePath = "KnowStore1"+"/"+organizationID+"/"+facultyId;
                
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

                               // //System.out.println("sandeep::" + singleDir);

                                //System.out.println("Directory created successfully !");

                            } else {
                                //System.out.println("Error in creating directory !");
                                String sessionmsg="Error in creating directory !";
				    			request.setAttribute("FacultyFailure",sessionmsg);
				    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Workware.jsp");
				    			rd.forward(request, response);
                            }

                        }
                    }
                    String fullFtpFilePath=ftpFilePath+"/"+fileName;
                    assignmentDomain.setPathOfFile(fullFtpFilePath);//setting file path into domain
                    String localFileName=uploadPath+"/"+fileName;
                    //System.out.println("ftp path:"+localFileName);
                    File file = new File(localFileName);
                    String testName = file.getName();
                    fis = new FileInputStream(file);
                    // Upload file to the ftp server
                    ftpLoginResult = ftpClient.storeFile(testName, fis);
                    
                    if(ftpLoginResult){
                    	//System.out.println("file stored success fully");
                    	//checking assessment id exist or not
                    	boolean duplicate=assignmentDao.getAssignmentId(assignmentDomain);
                    	 if(duplicate)
				         {

				         	String msg = assignmentDomain.getAssessmentId()+" has exist! Try another";
				    		  request.setAttribute("MsgExit", msg);
				    		  RequestDispatcher rd=request.getRequestDispatcher("../JSP/Workware.jsp");
				    		   rd.forward(request, response);
				         }else {
						if("group".equalsIgnoreCase(All)){
							String Tabmsg="tab1";
							String GroupResult=assignmentDao.insertGroupDetails(assignmentDomain);
							//System.out.println("GroupResult are:"+GroupResult);
							if("valid".equalsIgnoreCase(GroupResult)){
								String sessionmsg="Assignment given successfully"+"#"+"Your assignment ID:"+assignmentId;
				    			request.setAttribute("FacultySuccess",sessionmsg);
				    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Workware.jsp?tabvalue="+Tabmsg);
				    			rd.forward(request, response);
							} else {
								String sessionmsg="Failed for giving assignment";
				    			request.setAttribute("FacultyFailure",sessionmsg);
				    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Workware.jsp?tabvalue="+Tabmsg);
				    			rd.forward(request, response);
							}
                         }
						 else if("select".equalsIgnoreCase(SelectedID)){
								
							 String AllStudents=assignmentDao.insertAssinmentAllDetails(assignmentDomain);
								 //System.out.println("radia button for all"+AllStudents);
								 if("valid".equalsIgnoreCase(AllStudents)){
									 String sessionmsg="Assignment given successfully"+"#"+"Your assignment id:"+assignmentId;
						    			request.setAttribute("FacultySuccess",sessionmsg);
						    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Workware.jsp");
						    			rd.forward(request, response);
								 }else {
									 String sessionmsg="Failed for giving assignment";
						    			request.setAttribute("FacultyFailure",sessionmsg);
						    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Workware.jsp");
						    			rd.forward(request, response);
									}
								
	                             }else if("select".equalsIgnoreCase(groupid)){
	                            	 String SelectedResult=assignmentDao.insertAssinmentDetails(assignmentDomain);
	     							//System.out.println("SelectedResult:"+SelectedResult);
	     							if("valid".equalsIgnoreCase(SelectedResult)){
	     								String sessionmsg="Assignment given successfully"+"#"+"Your assignment id:"+assignmentId;
						    			request.setAttribute("FacultySuccess",sessionmsg);
						    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Workware.jsp");
						    			rd.forward(request, response);
	     							} else {
	     								String sessionmsg="Failed for giving assignment";
						    			request.setAttribute("FacultyFailure",sessionmsg);
						    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Workware.jsp");
						    			rd.forward(request, response);
	     							}
	                             }
					}

            } 
                }
            }else {
                //System.out.println("Login Fail !");
                String sessionmsg="Login failed in ftp server";
    			request.setAttribute("FacultyFailure",sessionmsg);
    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Workware.jsp");
    			rd.forward(request, response);

            }
           }
                    
         catch (FTPConnectionClosedException exp) {
            exp.printStackTrace();
        }
        catch (Exception e)
		{
			////System.out.println("in catch");
			 RequestDispatcher rd=request.getRequestDispatcher("../JSP/error.jsp?errmsg="+e.toString());
	 		 rd.forward(request, response);
			e.printStackTrace();	
		}
        finally {
            try {
                ftpClient.disconnect();
            } catch (FTPConnectionClosedException exe) {
                //System.out.println(exe);
            }
        }
    }

}
