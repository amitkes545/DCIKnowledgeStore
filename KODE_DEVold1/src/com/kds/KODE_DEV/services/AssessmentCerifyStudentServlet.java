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
import com.kds.KODE_DEV.domain.SessionDomain;

@SuppressWarnings("serial")
public class AssessmentCerifyStudentServlet extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(AssessmentCerifyStudentServlet.class);
	
	void sendMail()
	{
		
	}
	
	public void run() throws ServletException,IOException{
		
         session=request.getSession();
		
         AssessmentDomain adminDomain = null;
		SessionDomain sessionDomain=new SessionDomain();
		CertiftStudentDao certifyStdentDao=new CertiftStudentDao();
		
		ArrayList<AssessmentDomain> arrayListForAddDomain = new ArrayList<AssessmentDomain>();
		
		String assignID=request.getParameter("assignid");
		String studentid=request.getParameter("studentID");
		
		LOGGER.info("student id are:"+studentid);
		session.setAttribute("assessid",request.getParameter("assignid"));
		session.setAttribute("studentID",request.getParameter("studentID"));
		String facultyid=session.getAttribute("userid").toString();
		String orgid=session.getAttribute("orgid").toString();
		ArrayList<String> mailerIds=new ArrayList<String>();

		String[] checkboxGroup = request.getParameterValues("checkboxGroup");
		if(checkboxGroup !=null )
			System.out.println("Number of Records being updated : "+checkboxGroup.length);
		for(int i=0; i<checkboxGroup.length; i++){
			
			adminDomain=new AssessmentDomain();
			System.out.println("---"+checkboxGroup[i]);
			System.out.println(i+" : "+request.getParameter("assessmentId"+checkboxGroup[i]));
			System.out.println(i+" : "+request.getParameter("studentid"+checkboxGroup[i]));
			System.out.println(i+" : "+request.getParameter("marks"+checkboxGroup[i]));
			System.out.println(i+" : "+request.getParameter("remarks"+checkboxGroup[i]));
			System.out.println(i+" : "+request.getParameter("status"+checkboxGroup[i]));
			adminDomain.setAssessmentId(request.getParameter("assessmentId"+checkboxGroup[i]));
			adminDomain.setRecipientType(request.getParameter("studentid"+checkboxGroup[i]));
			mailerIds.add(request.getParameter("studentid"+checkboxGroup[i]));
			adminDomain.setMark(request.getParameter("marks"+checkboxGroup[i]));
			adminDomain.setReMarks(request.getParameter("remarks"+checkboxGroup[i]));
			adminDomain.setStatus(request.getParameter("status"+checkboxGroup[i]));
			//System.out.println("Assessment id in servicec : "+checkboxGroup[i]);
	
			
			arrayListForAddDomain.add(adminDomain);
			
		}
		
	             
	        Iterator<AssessmentDomain> itr = arrayListForAddDomain.iterator();
	        
	        while(itr.hasNext()){
	        	
	        	AssessmentDomain allVallue = itr.next();
	        	
	        	//System.out.println("allVallue llll"+allVallue.getAssessmentId());
	        	//System.out.println("allVallue llll"+allVallue.getRecipientType());
	        	//System.out.println("ssssss"+allVallue.getMark());
	        	//System.out.println("reee"+allVallue.getReMarks());
	        	//System.out.println("str"+allVallue.getStatus());
	   
	        	
	        }
	        
			if(arrayListForAddDomain.size()>0){
			
			String certifyStatus = certifyStdentDao.UpdateForAssessmentCertify(arrayListForAddDomain);
			System.out.println("certifyStatus="+certifyStatus);
			LOGGER.info("certifyStatus="+certifyStatus);
			if(certifyStatus == null){
        	  	String sessionStatusFailure="Failed for update certify details";
    			request.setAttribute("FacultyFailure",sessionStatusFailure);
    			RequestDispatcher requestDispatcher3=request.getRequestDispatcher("../JSP/CertifyStudent.jsp");//?sessionStatusSuccess="+sessionStatusFailure);
    			requestDispatcher3.forward(request, response);
    			return;
			}
			else if("valid".equalsIgnoreCase(certifyStatus)){
				String sessionStatusSuccess="Successully submitted assignment for evaluation ";
    			request.setAttribute("FacultySuccess",sessionStatusSuccess);
    			
    			
    			//Mailer.send(to, subject, msg);
    			RequestDispatcher requestDispatcher2=request.getRequestDispatcher("../JSP/CertifyStudent.jsp");
    			requestDispatcher2.forward(request, response);
    			return;
       	   }
          else if("notvalid".equalsIgnoreCase(certifyStatus)){
        	  	String sessionStatusFailure="Failed for update certify details";
    			request.setAttribute("FacultyFailure",sessionStatusFailure);
    			RequestDispatcher requestDispatcher3=request.getRequestDispatcher("../JSP/CertifyStudent.jsp");
    			requestDispatcher3.forward(request, response);
    			return;
       	   }
		}
			
		
	}
}
