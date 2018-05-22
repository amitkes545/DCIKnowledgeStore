package com.kds.KODE_DEV.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.AssessmentDao;
import com.kds.KODE_DEV.dao.SearchDao;
import com.kds.KODE_DEV.domain.AssessmentDomain;
import com.kds.KODE_DEV.util.PropertiesUtil;

@SuppressWarnings("serial")
public class AssessmentServlet extends CommonService{
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
	static final Logger LOGGER = Logger.getLogger(AssessmentServlet.class);
	public void run() throws ServletException, IOException, FileUploadException {
		
		
		session=request.getSession();
        FTPClient ftpClient = new FTPClient();
        AssessmentDomain assessmentDomain=new AssessmentDomain();
        AssessmentDao assessmentDao=new AssessmentDao();
		SearchDao searchdao=new SearchDao();
		String ksid="";
		//retriving organization id from session
		 String organizationID=session.getAttribute("orgid").toString();
		 String facultyId=session.getAttribute("userid").toString();
		 System.out.println("organizationID:"+organizationID);
		
		 //System.out.println( request.getParameterMap().size());
        boolean ftpLoginResult;
		boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
		String Description="",SelectedID="",facultyid="",groupid="", AssessmentName="",All="",groupvalue="",orgid="",AssessMentId="",courseId="",subjectId="";
		String path="",fullpath="",Marks="" ,assessmentId="",receipentType="";
		String UPLOAD_DIRECTORY ,ftpFilePath="";
		String assessType="",dueDate="";
		//String courseId=request.getParameter("courses");
		//System.out.println("Course Id : "+courseId);
        //to get local  system path
        String download_path = System.getProperty("user.home") + "/Downloads";
        String download_path1 = download_path.replace("\\", "/");
        String s =System.getProperty ("os.name");
        System.out.println("download_path= "+download_path);
        //System.out.println("download_path1= "+download_path1);
		//System.out.println("os name= "+s);
        String [] win= s.split(" ");
        String winname=win[0];
       System.out.println("windows name= "+winname);
        String uploadPath;
       if(winname.equalsIgnoreCase("Windows")){
       	//UPLOAD_DIRECTORY=download_path1;
       	 uploadPath =  "/"+ download_path1;
       	 System.out.println("upload path:"+uploadPath);
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
			System.out.println("welcome");
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
					System.out.println("filePath is=" + filePath);
					System.out.println("1.storeFile is=" + storeFile);
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
			    	if(asname.equals("assessId")){AssessMentId=value;}
					if(asname.equals("Description")){Description=value;}
			    	if(asname.equals("student_id")){SelectedID=value;}
			    	if(asname.equals("facultyid")){facultyid=value;}
			    	if(asname.equals("orgid")){orgid=value;}
			        if(asname.equals("group_id")){groupid=value;}
			        if(asname.equals("assessmentname")){AssessmentName=value;}
			        if(asname.equals("group")){All=value;}
			        if(asname.equals("marks")){Marks=value;}
			        if(asname.equals("courseid")){courseId=value;}
			        if(asname.equals("subjectid")){subjectId=value;}
			        if(asname.equals("radiogroup")){receipentType=value;}
			        if(asname.equals("category")){assessType=value;}
			        if(asname.equals("datetimepicker2")){dueDate=value;} 
			        
			        
			      			        
			        System.out.println("description:"+Description+ "student id/.....:"+SelectedID+ "facultyid:"+facultyid+ "groupid.....:"+groupid+"Asname:"+AssessmentName+"group....:"+All);
			    	System.out.println("course Id : "+courseId+"subject id : "+subjectId);
			    	System.out.println("organization id : "+orgid);
				}
			}
			System.out.println("Assessment ID : "+AssessMentId);
			//creating methods to retrive group values from dao
			 ksid=assessmentDao.getKsId(facultyid,orgid);
			System.out.println("ksid is:"+ksid);
			if(groupid!="select"){
				 groupvalue=assessmentDao.getGroupValues(groupid,facultyId,organizationID);
				System.out.println("group values inside:"+groupvalue);
			}
			System.out.println("group values outside:"+groupvalue);
			//selecting sequence value from dao
			String sequence=searchdao.getSequenceValue();
			 assessmentId="assess"+sequence;
 
			System.out.println("3.storeFile is=" + storeFile);
			//seting values to domain
			assessmentDomain.setUploadedBy(facultyid);
			assessmentDomain.setDescrition(Description);
			assessmentDomain.setIndividualId(SelectedID);
			assessmentDomain.setGroupId(groupid);//groupvalue);
			assessmentDomain.setOrgId(orgid);
			assessmentDomain.setAssessmentName(AssessmentName);
			assessmentDomain.setAssessmentId(assessmentId);
			assessmentDomain.setMark(Marks);
			assessmentDomain.setRecipientType(receipentType);
			assessmentDomain.setUserId(facultyId);
			assessmentDomain.setCourseId(courseId);
			assessmentDomain.setSubjectId(subjectId);
			assessmentDomain.setAssessmentType(assessType);
			assessmentDomain.setDueDate(dueDate);
		}
		//String ftpServerAddress = "localhost";
		String ftpServerAddress = PropertiesUtil.getProperty("FTP_IP");
		//String ftpServerAddress = "ftp://220.225.125.221/";
        //start ftp server code
        try {
            FileInputStream fis = null;
            // Connect to the localhost
            System.out.println("in try");
           // ftpClient.connect("61.12.87.139");
            //ftpClient.connect(ftpServerAddress);
            ftpClient.connect(ftpServerAddress);
            // login to ftp server
            System.out.println("after connect");
     //   	String userName = "ftpuser1";
     //		String password = "ftp@123#1";
            String userName =PropertiesUtil.getProperty("user");
			String password =PropertiesUtil.getProperty("password");		
            ftpLoginResult = ftpClient.login(userName, password);
           System.out.println("ftpLoginResult:"+ftpLoginResult);

            if (ftpLoginResult) {
                System.out.println("Logged in Successfully !");
LOGGER.info("ftpLoginResult"+ftpLoginResult);
ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                ftpFilePath = "/home/ftpkds1/KnowStore/"+organizationID+"/"+ksid+"/"+facultyId;
                LOGGER.info("ftpFilePath"+ftpFilePath);
                System.out.println("ftpFilePath: " + ftpFilePath);
                String pathElements1[] = ftpFilePath.split("/");
                if (pathElements1 != null && pathElements1.length > 0) {
                    for (String singleDir : pathElements1) {
                        boolean existed = ftpClient.changeWorkingDirectory(singleDir);
                        System.out.println("CurrentDir: " + singleDir);

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
				    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Workware.jsp");
				    			//rd.forward(request, response);
                            }

                        }
                    }
                    
                    String fullFtpFilePath=ftpFilePath+"/"+fileName;
                    assessmentDomain.setPathOfFile(fullFtpFilePath);//setting file path into domain
                    String localFileName=uploadPath+"/"+fileName;
                    //System.out.println("ftp path:"+localFileName);
                    File file = new File(localFileName);
                    String testName = file.getName();
                    fis = new FileInputStream(file);
                    // Upload file to the ftp server
                    ftpLoginResult = ftpClient.storeFile(testName, fis);
                    
                    if(ftpLoginResult){
                    	System.out.println("file stored success fully");
                    	//checking assessment id exist or not
                    	boolean duplicate=assessmentDao.getAssessmentId(assessmentDomain);
                    	System.out.println("duplicate="+duplicate);
                    	System.out.println("All="+All);
                    	 System.out.println("SelectedID.......="+SelectedID);
                    	System.out.println(" groupid....="+groupid);
						 if(duplicate)
				         {

				         	String msg = assessmentDomain.getAssessmentId()+" has exit! Try Another";
				    		  request.setAttribute("MsgExit", msg);
				    		  RequestDispatcher rd=request.getRequestDispatcher("../JSP/Workware.jsp");
				    		   rd.forward(request, response);
				         }//if not exit inserting values into database
						 else {
							 	System.out.println("Selected ID : "+SelectedID);
							 	System.out.println("Group ID  : "+groupid);
						 if("select".equalsIgnoreCase(SelectedID) && ("select".equalsIgnoreCase(groupid))){
							 System.out.println("SelectedID for all="+SelectedID);
							// String AllStudents=assessmentDao.insertAssinmentAllDetails(assessmentDomain);
							 String AllStudents=assessmentDao.insertAllAssignmentDetailsWithTransaction(assessmentDomain);
								 System.out.println("radia button for all"+AllStudents);
								 if("valid".equalsIgnoreCase(AllStudents)){
									 
									/* String msg = "File already exists on server  !!";
										request.setAttribute("FacultyFailure", msg);
										ftpclient.logout();
										RequestDispatcher requestDispatcher = request.getRequestDispatcher("../JSP/FacilitatorKnowSetup.jsp");
										requestDispatcher.forward(request, response);*/
									 
									 String sessionmsg="Assignment uploaded successfully";
									 System.out.println("Assignmentuploaded successfully for all");
						    			request.setAttribute("FacultySuccess",sessionmsg);
						    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Workware.jsp");
						    			rd.forward(request, response);
								 }else {
									 String sessionmsg="Failed for giving assignment";
									 System.out.println("Assignmentuploaded Failed for all");
						    			request.setAttribute("FacultyFailure",sessionmsg);
						    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Workware.jsp");
						    			rd.forward(request, response);
									}
								
	                             }else if("select".equalsIgnoreCase(groupid)){
	                            	 System.out.println(" groupid= indivual..."+groupid);
	                            	 //String SelectedResult=assessmentDao.insertAssinmentDetails(assessmentDomain);
	                            	 String SelectedResult=assessmentDao.insertIndividualAssinmentDetailsWithTransaction(assessmentDomain);
	                            	 
	                            	 
	     							System.out.println("SelectedResult:"+SelectedResult);
	     							if("valid".equalsIgnoreCase(SelectedResult)){
	     								 String sessionmsg="Assigmment given successfully"+"&"+"your assignment ID:"+assessmentId;
	     								 System.out.println("Assignmentuploaded Failed for individual");
							    			request.setAttribute("FacultySuccess",sessionmsg);
							    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Workware.jsp");
							    			rd.forward(request, response);
	     							} else {
	     								String sessionmsg="Failed for giving assigment";
	     								 System.out.println("Assignmentuploaded Failed for individual");
						    			request.setAttribute("FacultyFailure",sessionmsg);
						    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Workware.jsp");
						    			rd.forward(request, response);
	     							}
	                             }
	                             else //if("group".equalsIgnoreCase(All))
	                            	 {
							//	String GroupResult=assessmentDao.insertGroupDetails(assessmentDomain);
	                            	 String GroupResult=	 assessmentDao.insertGroupAssignmentDetailsWithTransaction(assessmentDomain);
								System.out.println("GroupResult are:"+GroupResult);
								if("valid".equalsIgnoreCase(GroupResult)){
									String sessionmsg="Assignment given successfully"+"&"+"your assignment ID:"+assessmentId;
									 System.out.println("Assignmentuploaded Failed for Group");
					    			request.setAttribute("FacultySuccess",sessionmsg);
					    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Workware.jsp");
					    			rd.forward(request, response);
								} else {
									String sessionmsg="Failed for giving assignment";
									 System.out.println("Assignmentuploaded Failed for Group");
					    			request.setAttribute("FacultyFailure",sessionmsg);
					    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Workware.jsp");
					    			rd.forward(request, response);
								}
	                        }
					}
                    }
                }

            } else {
                //System.out.println("Login Fail !");
                String sessionmsg="Login failed in ftp server ";
                LOGGER.info("sessionmsg="+sessionmsg);
    			request.setAttribute("FacultyFailure",sessionmsg);
    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Workware.jsp");
    			rd.forward(request, response);

            }
        } catch (FTPConnectionClosedException exp) {
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
