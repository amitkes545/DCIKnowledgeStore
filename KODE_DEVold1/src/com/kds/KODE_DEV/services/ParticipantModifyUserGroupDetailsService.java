package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.StudentGroupNameDao;
import com.kds.KODE_DEV.domain.UsersInfoDomain;

public class ParticipantModifyUserGroupDetailsService extends CommonService {

	/**@author jitendra
	 *
	 */
	private static final long serialVersionUID = 1L;

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
		String facultyid=(String)session.getAttribute("userid");
		String orgid=(String)session.getAttribute("orgid");
		String strViewId=request.getParameter("idvalue");
		request.setAttribute("ViewID", strViewId);
		//String strViewId111=request.getParameter("ViewID");
		//System.out.println("strViewId============"+strViewId+"ViewID=== "+strViewId111);
		StudentGroupNameDao studoa= new StudentGroupNameDao();
       	ArrayList<UsersInfoDomain> al=new ArrayList<UsersInfoDomain>();
    String strStudentval=studoa.getGroupDetails(facultyid,orgid,strViewId);
    //System.out.println("strStudentval"+strStudentval);
    if(strStudentval!=null && strStudentval!=""){
    String[] stuidval = strStudentval.split("#");	
    
   // System.out.println("strStudentval="+strStudentval);
    String userids=strStudentval.substring(0, strStudentval.length()-1);
    userids="'"+userids+"'";
    userids=userids.replaceAll("#", "','");
    //System.out.println(userids);
    
    //System.out.println("stuidval===length== "+stuidval.length);
    ArrayList<UsersInfoDomain> stulist=null;							
    ArrayList<UsersInfoDomain> arl1= new ArrayList<UsersInfoDomain>();
       for(String stuval:stuidval){
    	 //System.out.println("After sPlit Value"+stuval);
    	 	stulist=studoa.SingleStudentDetails(stuval);
    	 	arl1.addAll(stulist);
    	 	}
       session.setAttribute("studentdetail1", arl1);
       //System.out.println("length of arl=="+arl1.size());
    ArrayList<UsersInfoDomain> reststulist=studoa.RestStudentDetails(userids,orgid);   
    System.out.println("reststulist="+reststulist);
    if(arl1.size()!=0){
    	//String responseMsg="User detalis updated successfully.";
    	request.setAttribute("studentdetail1", arl1);
		request.setAttribute("reststudentdetail1", reststulist);
		String Tabmsg="tab2";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/ParticipantModifyAllInTabView.jsp?tabvalue="+Tabmsg);
		requestDispatcher.forward(request, response);
	}else{															//else part add by jitendra to validate error.
		String msg="Sorry! No data Found.";
		String Tabmsg="tab2";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/ParticipantModifyAllInTabView.jsp?msg="+msg+"&tabvalue="+Tabmsg);
		requestDispatcher.forward(request, response);
	}
    }else{
    	String msg="Sorry! No data Found, Please try again later ";
    	String Tabmsg="tab2";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/ParticipantModifyAllInTabView.jsp");
		requestDispatcher.forward(request, response);
    }
	}
}
