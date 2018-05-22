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
public class CertifyStudentServlet extends CommonService {

	static final Logger LOGGER = Logger.getLogger(CertifyStudentServlet.class);

	public void run() throws ServletException, IOException {

		session = request.getSession();
//System.out.println("in service");
		AssessmentDomain assessmentDomain = new AssessmentDomain();
		//System.out.println("in service uid="+ session.getAttribute("userId"));
		//System.out.println("in service ogid="+ session.getAttribute("orgId"));
		String facultyIdInSession = (String) session.getAttribute("userId");
		String orgIdInSession = (String) session.getAttribute("orgId");
		
		
		System.out.println("Faculty Id : "+facultyIdInSession);
		System.out.println("Organization Id : "+orgIdInSession);
		System.out.println("course id====="+request.getParameter("courses"));
		System.out.println("subject id====="+request.getParameter("subjects"));
		assessmentDomain.setCourseId(request.getParameter("courses"));
		assessmentDomain.setSubjectId(request.getParameter("subjects"));
		// Getting assessment id from jsp
		String assesmentId = request.getParameter("asessID");
         System.out.println("Assessment Id : "+assesmentId);
		assessmentDomain.setAssessmentId(assesmentId);
		System.out.println("in service1 assesmentId="+assesmentId);
		ArrayList<AssessmentDomain> recipientTypeForAssessment = null;
		//System.out.println("in service2");
		if(assesmentId!=null && !(assesmentId.equalsIgnoreCase("All"))){
		SearchDao searchDao = new SearchDao();

		// This method for getting one Recipient Type From assessment_info table
		recipientTypeForAssessment = searchDao
				.getRecipientType(assessmentDomain, orgIdInSession,
						facultyIdInSession);
		System.out.println("recipientTypeForAssessment::::"+recipientTypeForAssessment);
		}
		else if(assesmentId.equalsIgnoreCase("All")){
			SearchDao searchDao = new SearchDao();

			// This method for getting all Recipient Type From assessment_info table
		recipientTypeForAssessment = searchDao
					.getAllRecipientType(assessmentDomain, orgIdInSession,
							facultyIdInSession);
		System.out.println("this method is getting all recipienttypeforAssests"+recipientTypeForAssessment);
		}
		// setting values for getting in jsp
		if (recipientTypeForAssessment.size() != 0) {
			request.setAttribute("recipientTypeForAssessment",
					recipientTypeForAssessment);
			request.setAttribute("asessID", assesmentId);
			String Tabmsg = "tab1";
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("../JSP/CertifyStudent.jsp?tabValue="+Tabmsg);
			requestDispatcher.forward(request, response);

		} else {

			String noDataFoundMsg = "Sorry ! No data found ";
			request.setAttribute("asessID", assesmentId);
			request.setAttribute("noDataFoundMsg", noDataFoundMsg);
			String Tabmsg = "tab1";
			RequestDispatcher requestDispatcherFND = request
					.getRequestDispatcher("../JSP/CertifyStudent.jsp?tabValue="+Tabmsg);
			requestDispatcherFND.forward(request, response);
		}
	}
}