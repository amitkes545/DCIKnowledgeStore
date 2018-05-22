package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.TrackAssignmentDao;

@SuppressWarnings("serial")
public class TrackAssignmentServlet extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(TrackAssignmentServlet.class);
	public void run() throws ServletException, IOException{
		session=request.getSession();
		TrackAssignmentDao trackAssignmentDao=new TrackAssignmentDao();
		
		String sassignId=request.getParameter("assignID");
		//System.out.println("assignment id from script:"+sassignId);
		String studentid=request.getParameter("studentID");
		String assignid=request.getParameter("assign_id");
		//System.out.println("student id:"+studentid+ "assignment id:"+assignid);
		String domain2="";
		if(sassignId!=null){
			
			ArrayList<String> domain=trackAssignmentDao.getrecipientTypeId(sassignId);
			 String[] stockArr = new String[domain.size()];
			    stockArr = domain.toArray(stockArr);
			session.setAttribute("ResultDomain",stockArr);
			session.setAttribute("assignid",assignid);
			response.sendRedirect("../JSP/TrackAssignment.jsp");
		}
		
		else if(request.getParameter("assign_id")!=null && request.getParameter("studentID")!=null){
			String studentName=trackAssignmentDao.getStudentId(studentid);
			//System.out.println("student name:"+studentName);
			ArrayList<String> domain1=trackAssignmentDao.getAssignmentTypeId(studentid,assignid);
			java.util.Iterator<String> itr=domain1.iterator();
			while(itr.hasNext())
			{
				 domain2=itr.next();
				//System.out.println("value of domain"+domain2+"value of studentid:"+studentid);
			}
		     if(domain2.equalsIgnoreCase(studentid)){
		    	 //System.out.println("ids are matched");
		    	 String trackStatus=studentName+"\t "+ "has completed Assignment";
	    			request.setAttribute("FacultySuccess",trackStatus);
	    			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/TrackAssignment.jsp");
	    			requestDispatcher.forward(request, response);
		     }else if(domain2!=studentid){
		    	 //System.out.println("ids are not equal");
		    	 String trackStatus=studentName+" \t"+ "has not  completed Assignment";
	    			request.setAttribute("FacultyFailure",trackStatus);
	    			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/TrackAssignment.jsp");
	    			requestDispatcher.forward(request, response);
		     }
	
}
	}
}

