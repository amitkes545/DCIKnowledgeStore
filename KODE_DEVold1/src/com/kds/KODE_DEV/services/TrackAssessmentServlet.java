package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.TrackAssessmentDao;


@SuppressWarnings("serial")
public class TrackAssessmentServlet extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(TrackAssessmentServlet.class);
	public void run() throws ServletException,IOException{
		session=request.getSession();
		TrackAssessmentDao trackAssessmentDao=new TrackAssessmentDao();
		String sassessid=request.getParameter("assessID");
		LOGGER.info("assessment id from script:"+sassessid);
		session.setAttribute("asesId",request.getParameter("assessId"));
		//String studentid=request.getParameter("studentid");
		//String assessId=request.getParameter("assessId");
		//LOGGER.info("student id:"+studentid+ "assessment id:"+assessId);
		//String submit1=request.getParameter("submit1");
		//String submit2=request.getParameter("submit2");
		String studentID=request.getParameter("studentid");
		String assessid=request.getParameter("assess_id");
		LOGGER.info("student id:"+studentID+ "assessment id :"+assessid);
		String domain2="";
		if(sassessid!=null){
			String domain=trackAssessmentDao.getrecipientTypeId(sassessid);
			LOGGER.info("result ids are in service:"+domain);
			String[] items = domain.split("#");
			//String[] stockArr = new String[domain.size()];
		    //stockArr = domain.toArray(stockArr);
			  session.setAttribute("resultId",items);
			session.setAttribute("assessid",sassessid);
			response.sendRedirect("../JSP/TrackAssessment.jsp");
		}
		
		else if(request.getParameter("assess_id")!=null && request.getParameter("studentid")!=null){
			String studentName=trackAssessmentDao.getStudentId(studentID);
			
			LOGGER.info("student name:"+studentName);
			ArrayList<String> domain1=trackAssessmentDao.getAssessmentTypeId(studentID,assessid);
			java.util.Iterator<String> itr=domain1.iterator();
			while(itr.hasNext())
			{
				 domain2=itr.next();
				LOGGER.info("value of domain"+domain2+"value of studentid:"+studentID);
			}
		     if(domain2.equalsIgnoreCase(studentID)){
		    	 LOGGER.info("ids are matched");
		    	 String trackStatus=studentName+"   \t " + "has Completed Assessment";
	    			request.setAttribute("FacultySuccess",trackStatus);
	    			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/TrackAssessment.jsp");
	    			requestDispatcher.forward(request, response);
		     }else if(domain2!=studentID){
		    	 LOGGER.info("ids are not equal");
		    	 String trackStatus=studentName+ "  \t"+ "has not  completed Assessment";
	    			request.setAttribute("FacultyFailure",trackStatus);
	    			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/TrackAssessment.jsp");
	    			requestDispatcher.forward(request, response);
		     }
	
}
	}
}
