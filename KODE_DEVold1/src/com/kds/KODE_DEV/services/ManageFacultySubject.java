package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.AssaignDao;
import com.kds.KODE_DEV.domain.AdminDomain;


//@WebServlet("/ManageFacultySubject")
public class ManageFacultySubject extends CommonService {
	private static final long serialVersionUID = 1L;
    public void run() throws ServletException,IOException{
    	session=request.getSession(true);
    	String facultyid=request.getParameter("facultyid");
    	
    	AdminDomain adminDomain=new AdminDomain();
    	String orgid=session.getAttribute("orgid").toString();
    	//System.out.println("faculty id:"+facultyid+ "orgid:"+orgid);
    	adminDomain.setOrgid(orgid);
    	adminDomain.setAdminId(facultyid);
    	AssaignDao sdao=new AssaignDao();
    	ArrayList<AdminDomain> status=sdao.selectSubjectId(adminDomain);
    	session.setAttribute("facultyid",facultyid);
    	session.setAttribute("success",status);
    	RequestDispatcher rd=request.getRequestDispatcher("../JSP/DisplayFacultySubject.jsp");
    	rd.forward(request, response);
    	
    }
   
}
