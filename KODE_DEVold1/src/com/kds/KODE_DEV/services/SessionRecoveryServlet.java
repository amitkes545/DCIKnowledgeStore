package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.RecoveryDao;
import com.kds.KODE_DEV.domain.SessionDomain;

//@WebServlet("/SessionRecoveryServlet")
public class SessionRecoveryServlet extends CommonService {
	private static final long serialVersionUID = 1L;
	ArrayList<SessionDomain> al= new ArrayList<SessionDomain>();
    public void run() throws ServletException,IOException
    {
    	//System.out.println("this is service page");
    	    
    		SessionDomain sdomain=new SessionDomain();
    		/*//System.out.println("domain value is:"+sdomain.getSession_id());
    		
    		sdomain.setSession_id(request.getParameter("session_id"));*/
    		sdomain.setStatus(request.getParameter("status"));
    		
    		RecoveryDao rdao=new RecoveryDao();
    		String retstatus=rdao.updateStatus(sdomain);
    		//System.out.println("the result:"+retstatus);
    		if("success".equalsIgnoreCase(retstatus))
    		{
    			response.sendRedirect("../JSP/success.jsp");
    		} else {
    			response.sendRedirect("../JSP/failure.jsp");
    		}
    		
    		
    	}
}

