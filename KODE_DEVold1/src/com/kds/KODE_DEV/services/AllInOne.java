package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class AllInOne extends CommonService {
	public void run() throws ServletException,IOException {
		//session=request.getSession(false);
		//HttpSession session = request.getSession(false);
		session = request.getSession();
		//System.out.println("in java");
		String path="https://localhost:8443/KODE_DEV/ControllerServlet/AllInOne?";
		 String sesid=request.getParameter("sesid");
		// System.out.println("in java1");
		String priv=request.getParameter("privilege");
		System.out.println("in priv"+priv);
	    String userid=request.getParameter("ID");
	    System.out.println("sesid123="+sesid);
	    System.out.println("in userid"+userid);
	    session.setAttribute("previlege",priv);
	    session.setAttribute("userid",userid);
	    System.out.println("session userid= "+session.getAttribute("userid"));
	    System.out.println("session previlegeServlet= "+session.getAttribute("previlege"));
				  response.sendRedirect("../JSP/All-in-one.jsp?sesid="+sesid);
			
	
	}
}
