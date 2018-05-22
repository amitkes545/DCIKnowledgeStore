package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.IndividualStudentDetailsDao;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

@SuppressWarnings("serial")
public class DepartmentInfoService extends CommonService{
	
	public void run() throws ServletException,IOException{
		session=request.getSession();
		IndividualStudentDetailsDao individualstudentDetailsDao=new IndividualStudentDetailsDao();
		
		
		ArrayList<UsersInfoDomain> organizationName=individualstudentDetailsDao.getDepartmentInfo();
		//System.out.println("organizationNames in service:"+organizationName);
		if(!(organizationName.isEmpty())){
		session.setAttribute("departmentInfo", organizationName);
		
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/EngineeringInfo.jsp");
		requestDispatcher.forward(request, response);
	}else {
		request.setAttribute("alertDepartmentInfo", "for this department doesn't provide any institutes");
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/EngineeringInfo.jsp");
		requestDispatcher.forward(request, response);
	}
	}

}
