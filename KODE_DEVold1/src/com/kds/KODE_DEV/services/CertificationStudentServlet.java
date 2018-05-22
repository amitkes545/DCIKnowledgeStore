package com.kds.KODE_DEV.services;

//Author:- Paramvir Singh -----

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.SearchDao;
import com.kds.KODE_DEV.domain.AssessmentDomain;

@SuppressWarnings("serial")
public class CertificationStudentServlet extends CommonService {

	static final Logger LOGGER = Logger.getLogger(CertificationStudentServlet.class);

	public void run() throws ServletException, IOException {

		session = request.getSession();
//System.out.println("in service");
		AssessmentDomain assessmentDomain = new AssessmentDomain();
		//System.out.println("in service uid="+ session.getAttribute("userId"));
		//System.out.println("in service ogid="+ session.getAttribute("orgId"));
		String facultyIdInSession = (String) session.getAttribute("userId");
		String orgIdInSession = (String) session.getAttribute("orgId");

		// Getting assessment id from jsp
		String assesmentId = request.getParameter("asessID");

		assessmentDomain.setAssessmentId(assesmentId);
		//System.out.println("in service1"+assesmentId);
		ArrayList<AssessmentDomain> recipientTypeForAssessment = null;
		//System.out.println("in service2");
		if(assesmentId!=null && !(assesmentId.equalsIgnoreCase("All"))){
		SearchDao searchDao = new SearchDao();

		// This method for getting all Recipient Type From assessment_info table
		recipientTypeForAssessment = searchDao
				.getRecipientTypeMarks(assessmentDomain, orgIdInSession,
						facultyIdInSession);
		}
		else if(assesmentId.equalsIgnoreCase("All")){
			SearchDao searchDao = new SearchDao();

			// This method for getting all Recipient Type From assessment_info table
		recipientTypeForAssessment = searchDao
					.getAllRecipientTypeMarks(assessmentDomain, orgIdInSession,
							facultyIdInSession);	
		}
		// setting values for getting in jsp
		if (recipientTypeForAssessment.size() != 0) {
			request.setAttribute("recipientTypeForAssessment",
					recipientTypeForAssessment);
			request.setAttribute("asessID", assesmentId);
			String Tabmsg = "tab1";
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("../JSP/CertificationsList.jsp?tabValue="+Tabmsg);
			requestDispatcher.forward(request, response);

		} else {

			String noDataFoundMsg = "Sorry ! No data found ";
			request.setAttribute("asessID", assesmentId);
			request.setAttribute("noDataFoundMsg", noDataFoundMsg);
			String Tabmsg = "tab1";
			RequestDispatcher requestDispatcherFND = request
					.getRequestDispatcher("../JSP/CertificationsList.jsp?tabValue="+Tabmsg);
			requestDispatcherFND.forward(request, response);
		}
	}
}