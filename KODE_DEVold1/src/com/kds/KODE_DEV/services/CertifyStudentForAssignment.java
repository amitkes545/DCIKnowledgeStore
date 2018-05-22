package com.kds.KODE_DEV.services;

//Author:- Paramvir Singh

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.SearchDao;
import com.kds.KODE_DEV.domain.AssessmentDomain;

@SuppressWarnings("serial")
public class CertifyStudentForAssignment extends CommonService {

	static final Logger LOGGER = Logger
			.getLogger(CertifyStudentForAssignment.class);

	public void run() throws ServletException, IOException {

		session = request.getSession();

		AssessmentDomain assessmentDomain = new AssessmentDomain();

		String facultyId = (String) session.getAttribute("userid");
		String orgId = (String) session.getAttribute("orgid");

		String assignId = request.getParameter("asignID");
		assessmentDomain.setAssessmentId(assignId);

		ArrayList<AssessmentDomain> recipientTypeForAssignment=null;
		if(assignId!=null && !(assignId.equalsIgnoreCase("All"))){
		SearchDao searchDao = new SearchDao();

		// This method is for getting Recipient Type For Assignment From(Table -
		// assignment_info)
		recipientTypeForAssignment = searchDao
				.getRecipientTypeForAssignment(assessmentDomain, orgId,
						facultyId);
		}
		else if(assignId.equalsIgnoreCase("All")){
			SearchDao searchDao = new SearchDao();

			// This method is for getting Recipient Type For Assignment From(Table -
			// assignment_info)
			recipientTypeForAssignment = searchDao
					.getAllRecipientTypeForAssignment(assessmentDomain, orgId,
							facultyId);
		}
		// setting values for jsp
		if(recipientTypeForAssignment.size() !=0){
			
		request.setAttribute("recipientTypeForAssignment",
				recipientTypeForAssignment);
		request.setAttribute("assignId", assignId);
		String Tabmsg="tab2";
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("../JSP/CertifyStudent.jsp?tabValue="+Tabmsg);
		requestDispatcher.forward(request, response);
		
		}else{

				String noDataFound = "Sorry ! No data found ";
				request.setAttribute("assignId", assignId);
				request.setAttribute("noDataFoundMsg", noDataFound);
				//System.out.println("msg in service re"+noDataFound);
				String Tabmsg="tab2";
				RequestDispatcher requestDispatcherForNDF = request
						.getRequestDispatcher("../JSP/CertifyStudent.jsp?tabValue="+Tabmsg);
				requestDispatcherForNDF.forward(request, response);

			}
			
		}
	}
