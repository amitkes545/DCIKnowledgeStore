package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.IndividualStudentDetailsDao;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

@SuppressWarnings("serial")
public class UniversityTypeService extends CommonService {
	
	public void run() throws ServletException,IOException {
		
		
		UsersInfoDomain usersInfodomain=new UsersInfoDomain();
	    IndividualStudentDetailsDao individualstudentDetailsDao=new IndividualStudentDetailsDao();
		String universityTypeName=request.getParameter("universityType");
		//System.out.println("universityTypeName:"+universityTypeName);
		usersInfodomain.setOrgId(request.getParameter("universityType"));
		
		ArrayList<UsersInfoDomain> universityType=individualstudentDetailsDao.getUniversityType(usersInfodomain);
		//System.out.println("universityType in service:"+universityType);
		if(!(universityType.isEmpty())){
		request.setAttribute("universityType", universityType);
		
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/EngineeringInfo.jsp");
		requestDispatcher.forward(request, response);
	}else {
		request.setAttribute("alertUniversityInfo", "for this university doesn't provide any institutes");
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/EngineeringInfo.jsp");
		requestDispatcher.forward(request, response);
	}
		
	}

}
