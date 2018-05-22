package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.ParticipantSessionDetailsDao;
import com.kds.KODE_DEV.domain.SessionDomain;

@SuppressWarnings("serial")
public class FacilitatorSessionDetailsService extends CommonService {
	
	public void run() throws ServletException,IOException {
		String sessionId=(String)request.getParameter("sessionId");
		//System.out.println("session id:"+sessionId);
		ParticipantSessionDetailsDao padd=new ParticipantSessionDetailsDao();
		ArrayList<SessionDomain> arl=padd.SessionDetails(sessionId);
		//JsonResponse res = new JsonResponse();
		request.setAttribute("allSessionDetails",arl);
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/dashboard.jsp");
		requestDispatcher.forward(request, response);
		
	}

}
