package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.FacilitatorBuildKnowLibDao;
import com.kds.KODE_DEV.dao.FacilitatorSelectLibIdKnowStore;
import com.kds.KODE_DEV.dao.FacilitatorUpdateLibDao;
import com.kds.KODE_DEV.domain.FacilitatorManageKnowStoreDomain;
import com.kds.KODE_DEV.util.PropertiesUtil;

@SuppressWarnings("serial")
public class FacilitatorUpdateLib24june extends CommonService {

	static final Logger LOGGER = Logger.getLogger(FacilitatorUpdateLib24june.class);

	FacilitatorSelectLibIdKnowStore fslikStore = new FacilitatorSelectLibIdKnowStore();
	FacilitatorBuildKnowLibDao fDao = new FacilitatorBuildKnowLibDao();

	@Override
	public void run() throws ServletException, IOException {

		session = request.getSession();
		
		int libSize1 = 0;

		String username = (String) session.getAttribute("username");
		LOGGER.info(username);
		/*String userid = (String) session.getAttribute("userid");*/
		String orgid = (String) session.getAttribute("orgid");
		boolean success = false;

		FacilitatorUpdateLibDao facilitatorULDao = new FacilitatorUpdateLibDao();

		ArrayList<FacilitatorManageKnowStoreDomain> arl = new ArrayList<FacilitatorManageKnowStoreDomain>();

		LOGGER.info("ksid value is" + request.getParameter("ksid"));
		////System.out.println("libId  "+request.getParameter("libId"));
		////System.out.println("libId  "+request.getParameter("libName"));
		////System.out.println("libId  "+request.getParameter("libSize"));
		String update="";
		String[] values = request.getParameterValues("libId");
		String[] libName = request.getParameterValues("libName");
		String[] libSize = request.getParameterValues("libSize");
		String[] checkboxGroup = request.getParameterValues("checkboxGroup");
		System.out.println("checkboxGroup in store  "+request.getParameterValues("checkboxGroup"));
		FacilitatorManageKnowStoreDomain fmksDomain = null;
		boolean isDuplicate=false;
		for(int i=0; i<checkboxGroup.length; i++){
			 fmksDomain = new FacilitatorManageKnowStoreDomain();
			fmksDomain.setLibName(request.getParameter("libName"+checkboxGroup[i]));
			//System.out.println("old lib Name="+request.getParameter("libName"+checkboxGroup[i]));
			fmksDomain.setLibSize(Integer.parseInt(request.getParameter("libSize"+checkboxGroup[i])));
			libSize1 = (Integer.parseInt(request.getParameter("libSize"+checkboxGroup[i])));
			fmksDomain.setLibId(request.getParameter("libId"+checkboxGroup[i]));
			//String libId = request.getParameter("libId"+checkboxGroup[i]));
			fmksDomain.setKsId(request.getParameter("ksid"));
			fmksDomain.setUserId(request.getParameter("userid"));
			fmksDomain.setOrgId(request.getParameter("orgid"));
			fmksDomain.setStudentId(request.getParameter("oldlibName"+checkboxGroup[i]));
			
			arl.add(fmksDomain);
			
			String new_lib=request.getParameter("libName"+checkboxGroup[i]);
			String old_lib=request.getParameter("oldlibName"+checkboxGroup[i]);
			
			System.out.println("new lib new is="+new_lib);
			System.out.println("old lib new is="+old_lib);
			
			if(!new_lib.equalsIgnoreCase(old_lib)){
				 update="yes";
				 System.out.println("in if");
			 isDuplicate = facilitatorULDao.libNameDuplicate(fmksDomain);}
			else
			{	update="no";
			System.out.println("in else");
			// isDuplicate = facilitatorULDao.libNameDuplicate(fmksDomain);
			}
			//System.out.println("update=="+update);
		
		
		
		/*if(update=="yes"){
		 isDuplicate = facilitatorULDao.libNameDuplicate(fmksDomain);
		}*/
		}
		if (isDuplicate) {
		System.out.println("lib name already exists");
		arl.clear();
		}
		else
		{System.out.println("lib name not exists");
			
		}
		
		String ksID = request.getParameter("ksid");
		//LOGGER.info("lib id is"+libId);
		//fmksDomain.setKsId(request.getParameter("ksid"));
		
		//String libName = request.getParameter("libname");
		//LOGGER.info("new lib name"+libName);
		
		/*String sizeInNumber = libsize.replaceAll("[^0-9]", "");
		fmksDomain.setLibSize(Integer.parseInt(sizeInNumber));*/
		//fmksDomain.setUserId(request.getParameter("userid"));
		//fmksDomain.setOrgId(request.getParameter("orgid"));

		LOGGER.info("userid is" + request.getParameter("userid"));
		if(arl.size()>0){
			System.out.println("in arl .size");
		FTPClient ftpclient = new FTPClient();
		boolean result;
		String ftpServerAddress = PropertiesUtil.getProperty("FTP_IP");
	//	String ftpServerAddress = "localhost";
		//String ftpServerAddress = "220.225.125.221";
		//String userName = "ftpuser1";
		//String password = "ftp@123#1";
		
		String userName =PropertiesUtil.getProperty("user1");
		String password =PropertiesUtil.getProperty("password1");	
		
		
		System.out.println("before try");
		try {
			System.out.println("before connect");
			ftpclient.connect(ftpServerAddress);
			//System.out.println("beroe connect1");
			result = ftpclient.login(userName, password);
			//System.out.println("beroe connect2");
			if (result == true) {
				LOGGER.info("Logged in Successfully !");

				//String oldlibName = fslikStore.fetchLibName(fmksDomain);
			//	LOGGER.info("old lib name is:"+oldlibName);

				ftpclient.setFileType(FTP.BINARY_FILE_TYPE);
				Iterator<FacilitatorManageKnowStoreDomain> it=arl.iterator();
				while(it.hasNext()){
					FacilitatorManageKnowStoreDomain fmksDomain1=it.next();
				String newlibDir = "/home/ftpuser1/home/ftpkds1/KnowStore/"
						+  fmksDomain1.getOrgId()+ '/'
						+ fmksDomain1.getUserId() + '/' + ksID + "/"
						+ fmksDomain1.getLibName();
				String oldLibDir = "/home/ftpuser1/home/ftpkds1/KnowStore/"
						+ fmksDomain1.getOrgId() + '/'
						+ fmksDomain1.getUserId() + '/' + ksID + "/"
						+ fmksDomain1.getStudentId();
				LOGGER.info(oldLibDir);
				LOGGER.info("olsd DIR :::"+oldLibDir);
				LOGGER.info("Library path is:" + oldLibDir);

				success = ftpclient.rename(oldLibDir, newlibDir);
				
				if (success) {
					
					LOGGER.info(oldLibDir + " was successfully renamed to: "
							+ newlibDir);

				//	fmksDomain.setLibName(fmksDomain.getLibName());

				} else {
					LOGGER.info("Failed to rename: " + newlibDir);
				}
				}

			}
		} 
		
				catch (FTPConnectionClosedException e1) {
			////System.out.println("in catch");
			 RequestDispatcher rd=request.getRequestDispatcher("../JSP/error.jsp");
	 		 rd.forward(request, response);
			e1.printStackTrace();

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
				ftpclient.logout();

				ftpclient.disconnect();
			} catch (FTPConnectionClosedException e2) {
				LOGGER.info(e2);
			}
		}
		
		String userId = session.getAttribute("userid").toString();
		String allLibSizeOfDb = fDao.selectAllLibSpace(userId);
		System.out.println("allLibSizeOfDb:" + allLibSizeOfDb);
		int allLibSizeOfDbInt=0;
		 if(allLibSizeOfDb !=null){
		 allLibSizeOfDbInt = Integer.parseInt(allLibSizeOfDb);
		 }
		int facultyKnowSpace = fDao.selectFacultyKnowSpace(orgid);
		System.out.println("facultyKnowSpace:" + facultyKnowSpace);
		int facultyKnowSpaceInt = facultyKnowSpace;

		int remainingSize = facultyKnowSpaceInt - allLibSizeOfDbInt;
		System.out.println("remainingSize:" + remainingSize);
		System.out.println("allLibSizeOfDbInt:" + allLibSizeOfDbInt);
		System.out.println("libSize1:" + libSize1);
		if(allLibSizeOfDbInt>=libSize1){
			System.out.println("in if");
			String s1 = facilitatorULDao.updateValues(arl);
			System.out.println("s1="+s1);
			request.setAttribute("msgvalue", arl);
			if(s1!=null){
			if (s1.equals("success")) {
				String msg = "Library modified successfully";
				request.setAttribute("FacultySuccess11", msg);

			} else {

				String msg = " Failed to update library !!";
				request.setAttribute("FacultyFailure11", msg);
			}
		}
			else {

				String msg = " Failed to update library !!";
				request.setAttribute("FacultyFailure11", msg);
			}
		}
		else if (remainingSize >= (libSize1-allLibSizeOfDbInt)) {
			System.out.println("in if1"+arl);
		String s1 = facilitatorULDao.updateValues(arl);
		System.out.println("s1="+s1);
		request.setAttribute("msgvalue", arl);
		if (s1.equals("success")) {
			String msg = "Library modified successfully";
			request.setAttribute("FacultySuccess11", msg);

		} else {

			String msg = " Failed to update library !!";
			request.setAttribute("FacultyFailure11", msg);
		}
		}
		else{
			String msg = "You don't have enough Space !!";
			request.setAttribute("FacultyFailure11", msg);
		}
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("../JSP/FacilitatorManageLib.jsp");
		requestDispatcher.forward(request, response);
		}
		String msg = "Lib name already exists !!";
		System.out.println("msg="+msg);
		request.setAttribute("FacultyFailure11", msg);
		
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("../JSP/FacilitatorManageLib.jsp");
		requestDispatcher.forward(request, response);
	}
}