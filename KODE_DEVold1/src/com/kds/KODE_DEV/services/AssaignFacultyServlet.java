package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.AssaignDao;
import com.kds.KODE_DEV.domain.AdminDomain;

@SuppressWarnings("serial")
public class AssaignFacultyServlet extends CommonService{
	
	static final Logger LOGGER = Logger.getLogger(AssaignFacultyServlet.class);
	
      public void run() throws ServletException,IOException{
    	 AdminDomain adminDomain=new AdminDomain();
    	 
    	 String subjectid=request.getParameter("subid");
    	 String stream=request.getParameter("stream");
    	 String orgid=request.getParameter("orgid");
    	 adminDomain.setAdminId(request.getParameter("facultyid"));
    	 adminDomain.setOrgid(request.getParameter("orgid"));
    	 adminDomain.setStream(request.getParameter("stream"));
    	 LOGGER.info("subject id:"+subjectid+ "stream:"+stream+"orgid:"+orgid);
    	 AssaignDao asdao=new AssaignDao();
    	 String status=asdao.insertFacultyValues(adminDomain,subjectid);
    	 LOGGER.info("status value:"+status);
    	 if("success".equalsIgnoreCase(status)){
    		 String Studentymsg="Subject assigned for faculty successfully";
 			request.setAttribute("AdminSuccess",Studentymsg);
 			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/AssaignSubject.jsp");
 			requestDispatcher.forward(request, response);
    	 }else {

				String Studentymsg="Assigned subject for faculty has failed! try again";
				request.setAttribute("AdminFailure",Studentymsg);
				RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/AssaignSubject.jsp");
				requestDispatcher.forward(request, response);
    	 }
     }
    

}
