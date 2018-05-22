package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.StudentGroupNameDao;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

public class ParticipantModifyUserDetailsService extends CommonService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String userid="";
	String strName="";
	String strUserId="";
	String strEmailID="";
	String strAddress="";
	String strContactNo="";
	String strStatus="";
	
	
	static final Logger LOGGER = Logger.getLogger(getViewDetailServlet.class);
	public void run() throws ServletException,IOException{
		
		session=request.getSession();
		String facultyid=(String)session.getAttribute("userid");
		String orgid=(String)session.getAttribute("orgid");
		String strViewId=request.getParameter("idvalue");
		String strViewId111=request.getParameter("ViewID");
		//System.out.println("strViewId============"+strViewId+"ViewID=== "+strViewId111);  
			  
	
		 //System.out.println("strViewId="+strViewId);
	  
		 
		if(strViewId.equalsIgnoreCase("All")){
			StudentGroupNameDao studoa= new StudentGroupNameDao();
           	ArrayList<UsersInfoDomain> strStudentval=studoa.getParticipatsModifyAll(facultyid,orgid);
           	//System.out.println("strStudentval============"+strStudentval);
	       //}
        if(strStudentval.size()!=0){
			request.setAttribute("studentdetail", strStudentval);
			request.setAttribute("ViewID", strViewId);
			session.setAttribute("studentdetail", strStudentval);
			String Tabmsg="tab1";
			//RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/ParticipantModifyUser.jsp?");
			//response.sendRedirect("../JSP/ParticipantModifyAllInTabView.jsp?tabvalue="+Tabmsg);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/ParticipantModifyAllInTabView.jsp?tabvalue="+Tabmsg);
			requestDispatcher.forward(request, response);
		}else{															//else part add by jitendra to validate error.
			String msg="Sorry! No data Found.";
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/ParticipantModifyAllInTabView.jsp?msg="+msg);
			//RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/ParticipantModifyAllInTabView.jsp?msg="+msg);
			requestDispatcher.forward(request, response);
		}
           	
           	
		}
	//	}////////////////upto here/////////////
		
		else{
			StudentGroupNameDao studoa= new StudentGroupNameDao();
         	String strStudentid=strViewId;
		 //System.out.println("strStudentid=="+strStudentid);
 	    	 	ArrayList<UsersInfoDomain> stusinglelist=studoa.SingleStudentDetails(strStudentid);
	        if(stusinglelist.size()!=0){
	        session.setAttribute("studentdetail", stusinglelist);
	        request.setAttribute("ViewID", strViewId);
			request.setAttribute("studentdetail", stusinglelist);
			String Tabmsg="tab1";
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/ParticipantModifyAllInTabView.jsp?tabvalue="+Tabmsg);
			//RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/ParticipantModifyUser.jsp");
			requestDispatcher.forward(request, response);
		}else{															//else part add by jitendra if any error occured
			String msg="Sorry! No data Found.";
			//RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/ParticipantModifyUser.jsp?msg="+msg);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/ParticipantModifyAllInTabView.jsp?msg="+msg);
			requestDispatcher.forward(request, response);
		}
           	
		}
		}
		
		 
}
