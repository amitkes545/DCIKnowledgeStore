package com.kds.KODE_DEV.services;

//Author:- Paramvir Singh

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.CertiftStudentDao;
import com.kds.KODE_DEV.domain.AssessmentDomain;

@SuppressWarnings("serial")
public class AssignmentCertifyStudent extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(AssignmentCertifyStudent.class);
	
	public void run() throws ServletException,IOException{
		
         session=request.getSession();
		
         AssessmentDomain adminDomain = null;
		
		CertiftStudentDao certifyStdentDao=new CertiftStudentDao();
		
		ArrayList<AssessmentDomain> arrayListForAddDomain = new ArrayList<AssessmentDomain>();
		

		session.setAttribute("assessid",request.getParameter("assignid"));
		session.setAttribute("studentID",request.getParameter("studentID"));
		String facultyid=session.getAttribute("userid").toString();
		String orgid=session.getAttribute("orgid").toString();
		

		String[] checkboxGroup = request.getParameterValues("checkboxGroup");
		
		for(int i=0; i<checkboxGroup.length; i++){
			
			adminDomain=new AssessmentDomain();
			adminDomain.setAssessmentId(request.getParameter("assignmentId"+checkboxGroup[i]));
			adminDomain.setRecipientType(request.getParameter("recipient"+checkboxGroup[i]));
			adminDomain.setMark(request.getParameter("marks"+checkboxGroup[i]));
			adminDomain.setReMarks(request.getParameter("remarks"+checkboxGroup[i]));
			adminDomain.setStatus(request.getParameter("status"+checkboxGroup[i]));
			System.out.println("Assignment id in servicecccccccc"+request.getParameter("assignmentId"+checkboxGroup[i]));
			arrayListForAddDomain.add(adminDomain);
			
		}
	        Iterator<AssessmentDomain> itr = arrayListForAddDomain.iterator();
	        
	        while(itr.hasNext()){
	        	
	        	AssessmentDomain allVallue = itr.next();
	        	
	        	//System.out.println("allVallue llll"+allVallue.getRecipientType());
	        	//System.out.println("ssssss"+allVallue.getMark());
	        	//System.out.println("reee"+allVallue.getReMarks());
	        	//System.out.println("str"+allVallue.getStatus());
	   
	        	
	        }
	        
			if(arrayListForAddDomain.size()>0){
				
			//This method is for updating assignment value (Table- assignment_student_info) 
			String certifyStatus = certifyStdentDao.UpdateForAssignmenmentCertify(arrayListForAddDomain);
              	
			 if("valid".equalsIgnoreCase(certifyStatus)){
				String sessionStatus="Certify details uploaded successfully";
    			//request.setAttribute("FacultySuccess",sessionStatus);
    			RequestDispatcher requestDispatcher2=request.getRequestDispatcher("../JSP/CertifyStudent.jsp?sessionStatusSuccess="+sessionStatus);
    			requestDispatcher2.forward(request, response);
    			return;
       	   }
          else if("notvalid".equalsIgnoreCase(certifyStatus)){
       		  String sessionStatus="Failed for update certify details";
    			//request.setAttribute("FacultyFailure",sessionStatus);
    			RequestDispatcher requestDispatcher3=request.getRequestDispatcher("../JSP/CertifyStudent.jsp?sessionStatusSuccess="+sessionStatus);
    			requestDispatcher3.forward(request, response);
    			return;
       	   }
		}
	}
}
