package com.kds.KODE_DEV.services;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.SendEmailDao;
import com.kds.KODE_DEV.dao.StudentGroupNameDao;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;
import com.kds.KODE_DEV.domain.SessionDomain;
import com.kds.KODE_DEV.domain.UsersInfoDomain;


@SuppressWarnings("serial")
public class getViewDetailServlet extends CommonService {
	String userid="";
	String strName="";
	String strUserId="";
	String strEmailID="";
	String strAddress="";
	String strContactNo="";
	String strStatus="";
	
	
	static final Logger LOGGER = Logger.getLogger(getViewDetailServlet.class);
	public void run() throws ServletException,IOException{
		
		session=request.getSession();
		StudentGroupNameDao createDao=new StudentGroupNameDao();
		UsersInfoDomain sessionDomain=new UsersInfoDomain();
		
		ArrayList<UsersInfoDomain> arrayList=new ArrayList<UsersInfoDomain>();		
		String facultyid=(String)session.getAttribute("userid");
		String orgid=(String)session.getAttribute("orgid");
		String strViewId=request.getParameter("idvalue");
		////System.out.println("servlet idvalue="+strViewId);
		session.setAttribute("Participant_viewid", strViewId);
		//String strViewId111=request.getParameter("ViewID");
		////System.out.println("strViewId="+strViewId);//+"ViewID= "+strViewId111);  
			  
		// to identify group  or student id  
		 String strTextval = strViewId.substring(0, 2);
		  //to get the id value to pass query
		 String strIdvalue=strViewId.substring(2);
		 
		 //System.out.println("strTextval="+strTextval);
	  
		 
		if(strTextval.equals("GR")){
			StudentGroupNameDao studoa= new StudentGroupNameDao();
           	ArrayList<UsersInfoDomain> al=new ArrayList<UsersInfoDomain>();
        String strStudentval=studoa.getGroupDetails(facultyid,orgid,strIdvalue);
        //System.out.println("strStudentval"+strStudentval);
        String[] stuidval = strStudentval.split("#");	   
        ////////////////////////Changed By jitendra from here////////////
        //System.out.println("stuidval===length== "+stuidval.length);
        ArrayList<UsersInfoDomain> stulist=null;							
        ArrayList<UsersInfoDomain> arl1= new ArrayList<UsersInfoDomain>();
	       for(String stuval:stuidval){
	    	 //System.out.println("After sPlit Value"+stuval);
	    	 	stulist=studoa.SingleStudentDetails(stuval);
	    	 	arl1.addAll(stulist);
	    	 	}
	       session.setAttribute("studentdetail", arl1);
	       //System.out.println("length of arl=="+arl1);
        if(arl1.size()!=0){
			request.setAttribute("studentdetail", stulist);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/StudentReports.jsp");
			requestDispatcher.forward(request, response);
		}else{															//else part add by jitendra to validate error.
			String msg="Sorry! No data Found.";
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/StudentReports.jsp?msg="+msg);
			requestDispatcher.forward(request, response);
		}
           	
           	
		}
	//	}////////////////upto here/////////////
		
if(strTextval.equals("ID")){
			StudentGroupNameDao studoa= new StudentGroupNameDao();
           	ArrayList<UsersInfoDomain> al1=new ArrayList<UsersInfoDomain>();
            // to get the studnetid only  Vinay Kumar<faculty1@srm>"
       	/* //System.out.println("called=");
            String[] strStudentfulvalue = strIdvalue.split("<");
        	 //System.out.println("called1=");
			String userName =strStudentfulvalue[0];
			 //System.out.println("userName="+userName); 
			String strstuid=strStudentfulvalue[1]; 
		 //System.out.println("strstuid=="+strstuid);
		 String[] stuidvalue = strstuid.split(">"); 
		 String strStudentid = stuidvalue[0];*////////upto here no use of this code commented by jitendra//////
           	String strStudentid=strIdvalue;
		 //System.out.println("strStudentid=="+strStudentid);
 	    	 	ArrayList<UsersInfoDomain> stusinglelist=studoa.SingleStudentDetails(strStudentid);
	        if(stusinglelist.size()!=0){
	        session.setAttribute("studentdetail", stusinglelist);
			request.setAttribute("studentdetail", stusinglelist);
			//System.out.println("stusinglelist="+stusinglelist);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/StudentReports.jsp");
			requestDispatcher.forward(request, response);
		}else{															//else part add by jitendra if any error occured
			String msg="Sorry! No data Found.";
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/StudentReports.jsp?msg="+msg);
			requestDispatcher.forward(request, response);
		}
           	
		}
		}
		
		 
	
	}	



