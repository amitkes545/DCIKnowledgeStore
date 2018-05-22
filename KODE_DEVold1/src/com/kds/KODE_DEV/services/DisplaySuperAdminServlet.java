package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.DisplayDao;
import com.kds.KODE_DEV.domain.CreateDomain;
import com.kds.KODE_DEV.domain.DisplayDomain;


//@WebServlet("/UpdateSuperAdmin")
@SuppressWarnings("serial")
public class DisplaySuperAdminServlet extends CommonService 
{
	
	//private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(DisplaySuperAdminServlet.class);
	@Override
	public void run() throws ServletException, IOException {

		
		
		String org_id=request.getParameter("org_id");
		DisplayDomain dDomain=new DisplayDomain();
		 dDomain.setOrg_id(org_id);
		 DisplayDao dDao=new DisplayDao();
		 CreateDomain dDomain1=dDao.retriveData(dDomain);
		 LOGGER.info("create domain is:"+dDomain1);
		 LOGGER.info("create domain value is:"+dDomain1.getAddress());
		 session.setAttribute("CreateDomain", dDomain1);
		 response.sendRedirect("../JSP/Details.jsp");
		 
		 /*response.sendRedirect("../JSP/Details.jsp?organization_id="+dDomain1.getOrg_id()+" organization_name="+dDomain1.getOrg_name()+"organization_type="+dDomain1.getOrg_type()+"Address="+dDomain1.getAddress()+"City="+dDomain1.getCity()+
	 		  "Country="+dDomain1.getCountry()+"PostalCode="+dDomain1.getPcode()+"Telephoneno="+dDomain1.getPhone()+"Fax="+dDomain1.getFax()+ "EmergencyPhoneNo="+dDomain1.getEcno()+"EmailId="+dDomain1.getEmail_id()+"Url="+dDomain1.getUrl()+"Logo="+dDomain1.getLogo()+
 		        "YearOf Passing="+dDomain1.getYof()+"Belongs="+dDomain1.getBelongs()+"Date="+dDomain1.getDate());
//		 
*///		organization_id="+dDomain1.getOrg_id()+" organization_name="+dDomain1.getOrg_name()+"organization_type="+dDomain1.getOrg_type()+"Address="+dDomain1.getAddress()+"City="+dDomain1.getCity()+
//		 		  "Country="+dDomain1.getCountry()+"PostalCode="+dDomain1.getPcode()+"Telephoneno="+dDomain1.getPhone()+"Fax="+dDomain1.getFax()+ "EmergencyPhoneNo="+dDomain1.getEcno()+"EmailId="+dDomain1.getEmail_id()+"Url="+dDomain1.getUrl()+"Logo="+dDomain1.getLogo()+
//		 		        "YearOf Passing="+dDomain1.getYof()+"Belongs="+dDomain1.getBelongs()+"Date="+dDomain1.getDate());
	}
}

   