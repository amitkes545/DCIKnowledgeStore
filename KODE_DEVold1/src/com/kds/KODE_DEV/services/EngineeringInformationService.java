package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.IndividualStudentDetailsDao;
import com.kds.KODE_DEV.domain.DisplayDomain;

@SuppressWarnings("serial")
public class EngineeringInformationService extends CommonService {
	
	public void run() throws ServletException,IOException {
		session=request.getSession();
		IndividualStudentDetailsDao individualstudentDetailsDao=new IndividualStudentDetailsDao();
		String streamName=request.getParameter("streamName");
		//System.out.println("streamName:"+streamName + "stream id:"+request.getParameter("streamId"));
		ArrayList<DisplayDomain> streamInfo=individualstudentDetailsDao.getStreamInfo(request.getParameter("streamId"));
		//System.out.println("streamInfo in service:"+streamInfo);
		
		if(!(streamInfo.isEmpty())){
			//System.out.println("if part");
		session.setAttribute("streamInfo", streamInfo);
		session.setAttribute("streamName", streamName);
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/EngineeringInfo.jsp");
		requestDispatcher.forward(request, response);
	}else {
		//System.out.println("else part");
		request.setAttribute("alertInfo", "for this stream doesn't have any departments");
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/stream.jsp");
		requestDispatcher.forward(request, response);
	}
	}

}
