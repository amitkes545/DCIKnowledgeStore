package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.FacilitatorKnowPublishDao;
import com.kds.KODE_DEV.domain.FacilitatorKnowPublishDomain;

@SuppressWarnings("serial")
public class FacilitatorKnowPublishServlet extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(FacilitatorKnowPublishServlet.class);

	public void run() throws ServletException, IOException {

		// LOGGER.info("run hit");

	    session = request.getSession();

		session.removeAttribute("MsgValue");

		String kID = request.getParameter("ksid");
		String status = request.getParameter("status");
		LOGGER.info(status);
		FacilitatorKnowPublishDomain mksDomain = new FacilitatorKnowPublishDomain();
		String retStatus = "";
		mksDomain.setKsId(kID);
		
		FacilitatorKnowPublishDao mksDao = new FacilitatorKnowPublishDao();
		
		if ("Active".equals(status)) {

			retStatus = mksDao.updateValues(mksDomain);

			if ("success".equals(retStatus)) {
				
				  String msg = "Knowledge Asset Published Now !!!";
					
					request.setAttribute("FacultySuccess", msg);	
					
				}

					}
					else if("InActive".equals(status)){
					retStatus = mksDao.updateValuesInActive(mksDomain);
					
				   if ("success".equals(retStatus)) {
			   
			      String  msg = "Knowledge Asset UnPublished Now !!!";
				
				    request.setAttribute("FacultySuccess", msg);
			    	RequestDispatcher requestDispatcher = request.getRequestDispatcher("../JSP/Home.jsp");
				    requestDispatcher.forward(request, response);

				}

			}
		  }
		}