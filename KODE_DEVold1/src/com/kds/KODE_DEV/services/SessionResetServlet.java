package com.kds.KODE_DEV.services;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.SessionBackUpDao;
import com.kds.KODE_DEV.domain.SessionDomain;

@SuppressWarnings("serial")
public class SessionResetServlet extends CommonService {
	
	public void run() throws ServletException,IOException
	{
        String[] s1;
		session=request.getSession(true);
		SessionBackUpDao sessionBackupDao = new SessionBackUpDao();
		SessionDomain sessionDomain=new SessionDomain();
		String sessionid = request.getParameter("sesID");
		session.setAttribute("sesId",request.getParameter("sesID"));
		String studentid=request.getParameter("studentid");
		//System.out.println("studentid:"+studentid+ "session id:"+request.getParameter("sessionid"));
		if(sessionid!=null){
		
		String orgID=(String)session.getAttribute("orgid");
		String userID=(String)session.getAttribute("userid");
		
		//System.out.println("orgid from session:"+orgID+ "userid from session:"+userID);
		String sid=sessionBackupDao.getStudentId(sessionid);
		//System.out.println("result id are:"+sid);
		try{
		if(sid.equalsIgnoreCase("all")){
		   //System.out.println("selected all");
		    ArrayList<String> s2=sessionBackupDao.getAllStudentID(orgID,userID);
		    String[] stockArr = new String[s2.size()];
		    stockArr = s2.toArray(stockArr);
		   
		    session.setAttribute("result",stockArr);
		    RequestDispatcher requesDispatcher=request.getRequestDispatcher("../JSP/Session_reset.jsp");
			requesDispatcher.forward(request, response);
		   // response.sendRedirect("../JSP/Session_reset.jsp");
		} else {
			//System.out.println("else part with string array");
			s1=sid.split("#");
			session.setAttribute("result",s1);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/Session_reset.jsp");
			requestDispatcher.forward(request, response);
			//response.sendRedirect("../JSP/Session_reset.jsp");
		}
		}catch(NullPointerException np){
			String msg="students not available for this session";
			request.setAttribute("message",msg);
			RequestDispatcher requesDispatcher=request.getRequestDispatcher("../JSP/Session_reset.jsp");
			requesDispatcher.forward(request, response);
		}
		}
		
	
		
		/*if(studentid!=null){
		session.setAttribute("stuID",request.getParameter("studentId"));
		session.setAttribute("sesId",request.getParameter("sessionid"));*/
		if(request.getParameter("sessionid")!=null && request.getParameter("studentid")!=null){
		//System.out.println("studentid in service:"+request.getParameter("studentId")+ "session id in service:"+request.getParameter("sessionid"));
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		//System.out.println(dateFormat.format(cal.getTime())); //2014/08/06 16:00:22
	    String date=dateFormat.format(cal.getTime());
	    String[] datetime = date.split(" ");
		String date1 = datetime[0]; // 004
		String time = datetime[1]; 
		//System.out.println("date1 :"+date1+ "time in:"+time);
		/*java.util.Date utilDate = new Date();
		java.sql.Date date = new java.sql.Date(utilDate.getTime());
		//System.out.println("current date :"+date);*/
		
       
		String result=sessionBackupDao.getResultId(studentid);//selecting student login status from dao
		try
		{
		
		//System.out.println("result of logstatus"+result);
		if(result.equalsIgnoreCase("out")){
			String msg="Student not login";
			//session.setAttribute("stuID",studentid);
			request.setAttribute("msg",msg);
			//response.sendRedirect("../JSP/Session_reset.jsp");
		}else if(result.equalsIgnoreCase("in")){
			SessionDomain domain=sessionBackupDao.getDateTime(studentid);
			String[] DateAndTime=domain.getDate().split("");
			String fromDbDate=DateAndTime[0];
			String fromDbTime=DateAndTime[1];
			 
			//System.out.println("date from db:"+fromDbDate+ "time from db:"+fromDbTime);
			if(fromDbDate.equals(date1) && fromDbTime.equals(time)){
				//System.out.println("Staudent has loged in");
				String msg="Staudent has loged in";
				request.setAttribute("message",msg);
				RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/Session_reset.jsp");
				requestDispatcher.forward(request, response);
			}else if(!(fromDbDate.equals(date1))) {
				//System.out.println("Student not login this session");
				String msg="Student not login this session";
				session.setAttribute("sesId", request.getParameter("sessionid"));
				session.setAttribute("stuID",request.getParameter("studentId"));
				request.setAttribute("Loginstatus",msg);
				RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/Session_reset.jsp");
				requestDispatcher.forward(request, response);
			}
			/*//System.out.println("result for date:"+domain);
			String msg="This Student Login";
			session.setAttribute("msg",msg);
			response.sendRedirect("../JSP/Session_reset.jsp");*/
		}else {
			session.setAttribute("sesId", request.getParameter("sessionid"));
			session.setAttribute("stuID",request.getParameter("studentId"));
			String msg="This student not login this session";
			request.setAttribute("message",msg);
			RequestDispatcher requesDispatcher=request.getRequestDispatcher("../JSP/Session_reset.jsp");
			requesDispatcher.forward(request, response);
			//response.sendRedirect("../JSP/Session_reset.jsp");
		}
		
		
	}catch(NullPointerException ne)
		{
		session.setAttribute("sesId", request.getParameter("sessionid"));
		session.setAttribute("stuID",request.getParameter("studentId"));
		  //System.out.println(" catch");
		  String message="This student not login ";
			request.setAttribute("message",message);
			//response.sendRedirect("../JSP/Session_reset.jsp");
			RequestDispatcher requesDispatcher=request.getRequestDispatcher("../JSP/Session_reset.jsp");
			requesDispatcher.forward(request, response);
		}
		
		
		}
		sessionDomain.setSessionId(request.getParameter("sessionid"));
		sessionDomain.setFacultyId(request.getParameter("studentid"));
		sessionDomain.setStatus(request.getParameter("status"));
		if(request.getParameter("sessionid")!=null & request.getParameter("studentid")!=null & request.getParameter("status")!=null){
		//System.out.println("sesid:"+request.getParameter("sessionid")+ "stuid:"+request.getParameter("studentid")+ "status:"+request.getParameter("status"));
		String resultStatus=sessionBackupDao.insertSessionAccessValues(sessionDomain);
		 if("valid".equalsIgnoreCase(resultStatus)){
    		  String sessionStatus="Session access given successfully";
 			request.setAttribute("OwnerSuccess",sessionStatus);
 			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Session_reset.jsp");
 			rd.forward(request, response);
 			//sendMailIntern(host,to); 
    	   }
       else if("notvalid".equalsIgnoreCase(resultStatus)){
    		  String sessionStatus="Failure for giving session access";
 			request.setAttribute("OwnerFailure",sessionStatus);
 			RequestDispatcher rd=request.getRequestDispatcher("../JSP/Session_reset.jsp");
 			rd.forward(request, response);
    	   }
		}

	}
}
