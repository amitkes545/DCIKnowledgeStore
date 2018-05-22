package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileUploadException;

import com.kds.KODE_DEV.dao.SearchDao;
import com.kds.KODE_DEV.domain.CreateDomain;


@SuppressWarnings("serial")
public class ManageCustomerServlet extends CommonService{

	@Override
	public void run() throws ServletException, IOException,
			FileUploadException, Exception {
		// TODO Auto-generated method stub
		CreateDomain crtdomain=new CreateDomain();
		crtdomain.setOrg_id(request.getParameter("org_id"));
		
		session=request.getSession(true);
		String organizationId=request.getParameter("org_id");
       //System.out.println("organizationId="+organizationId);
		if(request.getParameter("org_id")!=null && !(request.getParameter("org_id").equalsIgnoreCase("All"))){
        SearchDao searchDao=new SearchDao();
        ArrayList<CreateDomain>  dDomain = searchDao.fetchOrgId(crtdomain);
        //System.out.println("after search dao="+dDomain);
        CreateDomain organizationValues=searchDao.getOrganizationDetails(organizationId);
        
        request.setAttribute("msgvalue", dDomain);
        session.setAttribute("organizationValues", organizationValues);
        request.setAttribute("Orgid", organizationId);
		// response.sendRedirect("../JSP/ManageCustomerUpdateDeleteLib.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/ManageCustomerUpdateDeleteLib.jsp?select=single");
		
		dispatcher.forward(request, response);
		}else if(request.getParameter("org_id").equalsIgnoreCase("All")){
		//	//System.out.println("org=="+request.getParameter("org_id"));
			SearchDao adao = new SearchDao();
			ArrayList<CreateDomain>  dDomain = adao.fetchAllOrgValue(crtdomain);
		////System.out.println("=dDomain="+dDomain);
		request.setAttribute("msgvalue", dDomain);
		request.setAttribute("Orgid", organizationId);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("../JSP/ManageCustomerUpdateDeleteLib.jsp?select=all");
		
		dispatcher.forward(request, response);	

		}
	}
}
