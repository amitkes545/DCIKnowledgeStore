package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.FacultyShareFileDao;
import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.FacultyShareFileDomain;

@SuppressWarnings("serial")
public class FacultyShareFileServlet extends CommonService {

	static final Logger LOGGER = Logger
			.getLogger(SuperAdminKnowStoreSetupServlet.class);
	Connection con = null;
	String userid = "";
	String emailid = "";
	String password = "";
	String host = "";
	String pop = "",file_path="";
	int port;
	String sesid="";
	String ksId = "";
	String msg="";
	FacultyShareFileDomain aksDomain = new FacultyShareFileDomain();
	FacultyShareFileDao ksDao = new FacultyShareFileDao();

	@Override
	public void run() throws ServletException, IOException {
		try {
		session = request.getSession(false);
		con = DBTransaction.connect();
		//FTPClient ftpClient = new FTPClient();
		// String orgID = request.getParameter("orgid");
		String[] selectstudent = request.getParameterValues("selectstudent");
		String ksfile = request.getParameter("ksfile");
		//Object[] filepath = request.getParameterValues("filepath");
		//LOGGER.info("file path in FacultyShareFileServlet="+filepath);
		sesid=request.getParameter("ses_id");
		System.out.println("sesid in FacultyShareFileServlet servlet"+sesid);
		Object[] filename = request.getParameterValues("filename");
		System.out.println("file in request="+request.getParameterValues("filename"));
		LOGGER.info("file name in FacultyShareFileServlet="+filename.toString());
		LOGGER.info("file name in FacultyShareFileServlet   00  ="+filename[0].toString());
		System.out.println("file name in FacultyShareFileServlet="+filename.toString());
		System.out.println("file name in FacultyShareFileServlet   00  ="+filename[0].toString());
		//String filePath[]=filename.substring(0,filename.lastIndexOf("/"));
		String actualpath[]=((String) filename[0]).split("/");
		System.out.println("actualpath="+actualpath);
		StringBuffer stuidbuffer = new StringBuffer();
		StringBuffer filenamebuffer = new StringBuffer();
		StringBuffer filepathbuffer = new StringBuffer();
		//int ksSize = Integer.parseInt(ksSize1);
		
		if(filename.length > 0){ 
           
			//filename[0].toString().substring(filename[0].toString().lastIndexOf('/')+1);
			filenamebuffer.append(filename[0].toString().substring(filename[0].toString().lastIndexOf('/')+1));
			filepathbuffer.append(filename[0].toString().substring(0,filename[0].toString().lastIndexOf('/')));
                for(int i=1; i < filename.length; i++){
                	//filenamebuffer.append("\",\"").append(filename[i]);
                	filenamebuffer.append("\",\"").append(filename[i].toString().substring(filename[i].toString().lastIndexOf('/')+1));
                	filepathbuffer.append("\",\"").append(filename[i].toString().substring(0,filename[i].toString().lastIndexOf('/')));
                }
               
        } 
/*if(filepath.length > 0){ 
            file_path=(String)filepath[0];
			filenamebuffer.append(filen[0]);
                for(int i=1; i < filename.length; i++){
                	filenamebuffer.append("\",\"").append(filename[i]);
                }
               
        } */
if(selectstudent.length > 0){ 
            
        	stuidbuffer.append(selectstudent[0]);
                for(int i=1; i < selectstudent.length; i++){
                	stuidbuffer.append("\",\"").append(selectstudent[i]);
                }
               
        } 
        System.out.println("studnet id are----------"  + stuidbuffer.toString());
        System.out.println("files are----------"  + filenamebuffer.toString());
        System.out.println("files paths are----------"  + filepathbuffer.toString());
        String filepath=filenamebuffer.toString();
        //String file_path=filepath.substring(0, filepath.lastIndexOf('/'));
        //String file_name=filepath.substring(filepath.lastIndexOf('/')+1);
		//System.out.println("selectstudent=" + selectstudent);
		//System.out.println("file_path=" + file_path);
		//System.out.println("file_name=" + file_name);
		//System.out.println("file_name  2=" + filepath.substring(filepath.lastIndexOf('/')+1));
		//System.out.println("filename=" + filename);
	//	Array fileArray[]={filenamebuffer.toString()};
		//String staTus = request.getParameter("status");
//System.out.println("{\""+filenamebuffer.toString()+"\"}");
		 //String[] s = new String[] { "abc", "def" };
		//String[] usa = {"New York", "Chicago", "San Francisco"};
		//Array filearray=con.createArrayOf("text", usa);
//System.out.println("filearray:"+filearray);
//Array participantsarray=con.createArrayOf("text", selectstudent);
//System.out.println("participantsarray:"+participantsarray);
		//aksDomain.setFileName("{\""+filenamebuffer.toString()+"\"}");

		aksDomain.setFileName("{\""+filenamebuffer.toString()+"\"}");
		
		//aksDomain.setFileName("{\""+file_name+"\"}");
		aksDomain.setUploadedBy(session.getAttribute("userid").toString());
		aksDomain.setSessionID(sesid);
		aksDomain.setAction(ksfile);
		LOGGER.info("file path="+file_path);
		aksDomain.setFilePath("{\""+filepathbuffer.toString()+"\"}");
		//aksDomain.setFilePath(file_path);
		aksDomain.setParticipants("{\""+stuidbuffer.toString()+"\"}");

	//	filearray.free();
		System.out.println("after set");
		System.out.println(aksDomain.getFileName());
	//	session.setAttribute("usersID", userID);
		
				//String ksId = (String) saskeDao.fetchKnowStoreID(aksDomain);
				//String emailId = (String) saskeDao.fetchEmailId(aksDomain);
LOGGER.info("Before dao in facultyshare file servlet");
		System.out.println("before dao");
					String retStatus =ksDao.insertShareFileInformation(aksDomain);
					System.out.println("after dao retstatus="+retStatus);
					
					if ("success".equals(retStatus)) {
						 msg = "Successfully sent !";

						//request.setAttribute("SuperAdminSuccess", msg);

					} else if ("failure".equals(retStatus)) {

						 msg = "Failed to send file! Please try again ! ";

						//request.setAttribute("SuperAdminFailure", msg);

					}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
				   con.close();
				 //  pstmt.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		RequestDispatcher rd=null;
		System.out.println("done in servlet");
		request.setAttribute("FacultySuccess",msg);
		rd = request.getRequestDispatcher("../JSP/FacultyClasswork.jsp?ses_id="+sesid+"");
		rd.forward(request, response);
		System.out.println("in the last");
	}
}
