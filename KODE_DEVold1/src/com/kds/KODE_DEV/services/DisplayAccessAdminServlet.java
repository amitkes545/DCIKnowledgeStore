package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.AdminAccessControlDao;
import com.kds.KODE_DEV.dao.AdminAccessDao;
import com.kds.KODE_DEV.domain.AdminDomain;

@SuppressWarnings("serial")
public class DisplayAccessAdminServlet extends CommonService {
	//private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(DisplayAccessAdminServlet.class);
	public void run() throws ServletException,IOException{
		session=request.getSession();
		AdminDomain adomain=new AdminDomain();
		AdminAccessControlDao pdao=new AdminAccessControlDao();
		AdminDomain adminDomain=new AdminDomain();
		String organizationid=(String)session.getAttribute("orgid");
		
		adminDomain.setAdminId(request.getParameter("selectedUserId"));
		//System.out.println("selectedUserId in admin:"+request.getParameter("selectedUserId"));
		adminDomain.setOrgid(organizationid);
		LOGGER.info("selectedUserId:"+request.getParameter("selectedUserId"));
		AdminAccessDao adminAccessDao=new AdminAccessDao();
		 List<String> finalListToAdd=new ArrayList<String>();
		   List<String> finalListToDelete=new ArrayList<String>();
		 List<String> resultList = new ArrayList<String>(); 
		 List<String> listFromDb=new ArrayList<String>();
		String userid=(String)session.getAttribute("userid");
		String adminid=request.getParameter("selectedUserId");
		adminDomain.setAdminId(adminid);
		//session.setAttribute("adminid", adminid);
		String status=null;
    	LOGGER.info("userid is"+userid+ "adminid:"+adminid);
    	ArrayList<AdminDomain> listfromdb=(ArrayList<AdminDomain>) session.getAttribute("AvailableValueinDB");
        LOGGER.info("listfromdb"+listfromdb);
     String[] access=request.getParameterValues("access");
     if(access!=null && access.length>0)
   	  resultList=Arrays.asList(access);
     java.util.Iterator<AdminDomain> it= listfromdb.iterator();
     while(it.hasNext()){
     	adomain=it.next();
     if(adomain.getProcessid() != null){
       listFromDb.add(adomain.getProcessid());
       LOGGER.info("listfrom db values are"+listFromDb.size());
     		 
     }
     	 
      }
     if(listFromDb.size()>0 && resultList.size()==0){
      	finalListToDelete.addAll(listFromDb);
      }
     else if(access==null){
    	 LOGGER.info("No Values Are Not Selected");
    	 String OrgStatus="Access values are not Selected";
		request.setAttribute("SuperAdminSuccess",OrgStatus);
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/DisplayAccessAdmin.jsp");
		requestDispatcher.forward(request, response);
     }
     else {
    /*if(access!=null && access.length>0)
	  resultList=Arrays.asList(access);
	
		LOGGER.info("Result value is"+listfromdb);
    java.util.Iterator<AdminDomain> it1= listfromdb.iterator();
    while(it1.hasNext()){
    	adomain=it1.next();
    if(adomain.getProcessid() != null){
      listFromDb.add(adomain.getProcessid());
      LOGGER.info("listfrom db values are"+listFromDb.size());
    		 
    }
    	 
     }*/
    session.removeAttribute("AvailableValueinDB");
   
   
	  
    if(listFromDb.size()==0 ){
    	LOGGER.info("listfrom db:"+listFromDb);
          status= pdao.insertAdminAccessDao(adminid, resultList);
          LOGGER.info("insert status value are"+status);
          if("success".equalsIgnoreCase(status)){
        	  ArrayList<AdminDomain>    AvailableValueinDB=adminAccessDao.retriveAdminAllDetails(adminDomain);//selecting access values from dao
 			// LOGGER.info("process id are"+AvailableValueinDB);
        	  session.setAttribute("resultvalue", AvailableValueinDB);
        	  String OrgStatus="Access values given successfully";
  			request.setAttribute("SuperAdminSuccess",OrgStatus);
  			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/DisplayAccessSuperAdmin.jsp");
  			requestDispatcher.forward(request, response);
          }else {
        	  String OrgStatus="Failured for giving access values";
  			request.setAttribute("SuperAdminFailure",OrgStatus);
  			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/DisplayAccessSuperAdmin.jsp");
  			requestDispatcher.forward(request, response);
          }
    }
    else if(listFromDb.size()>0 && resultList.size()==0){
    	finalListToDelete.addAll(listFromDb);
    }
    	else{
    	   
    	if(resultList.size()>0){
    		if(resultList.size()==listFromDb.size() || resultList.size()>listFromDb.size()){
    			for(int i=0;i<listFromDb.size();i++){
    				if(!resultList.contains(listFromDb.get(i))){
    					finalListToDelete.add(listFromDb.get(i));
    				}
    			}
    			for(int i=0;i<resultList.size();i++){
    				if(!listFromDb.contains(resultList.get(i))){
    					finalListToAdd.add(resultList.get(i));
    				}
    			}
    		}else if(listFromDb.size()>resultList.size()){
    			for(int i=0;i<resultList.size();i++){
    				if(!listFromDb.contains(resultList.get(i))){
    					finalListToAdd.add(resultList.get(i));
    				}
    			}
    			for(int i=0;i<listFromDb.size();i++){
    				if(!resultList.contains(listFromDb.get(i))){
    					finalListToDelete.add(listFromDb.get(i));
    				}
    			}

    		}
    		
    	}
    	}
     }
    	if(finalListToAdd.size()>0) {
    		LOGGER.info("finalListToAdd"+finalListToAdd);
    		 status= pdao.updateAdminAccessDao(adminid, finalListToAdd);
    		LOGGER.info("update status value"+status);
    		if("success".equalsIgnoreCase(status)){
    			ArrayList<AdminDomain>    AvailableValueinDB=adminAccessDao.retriveAdminAllDetails(adminDomain);//selecting access values from dao
     			// LOGGER.info("process id are"+AvailableValueinDB);
            	 // session.setAttribute("resultvalue", AvailableValueinDB);
          	  session.setAttribute("resultvalue", AvailableValueinDB);
    			String OrgStatus="Access values given successfully";
    			request.setAttribute("SuperAdminSuccess",OrgStatus);
    			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/DisplayAccessAdmin.jsp");
    			requestDispatcher.forward(request, response);
    		}else {
    			String OrgStatus="Failed for giving access values";
     			request.setAttribute("SuperAdminFailure",OrgStatus);
     			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/DisplayAccessAdmin.jsp");
     			requestDispatcher.forward(request, response);
    		}
    	}
    	if(finalListToDelete.size()>0) {
    		LOGGER.info("finalListToDelete"+finalListToDelete);
    		status=pdao.deleteAdminAccessDao(adminid, finalListToDelete);
    		LOGGER.info("delete status value is"+status);
    		if("success".equalsIgnoreCase(status)){
    			ArrayList<AdminDomain>    AvailableValueinDB=adminAccessDao.retriveAdminAllDetails(adminDomain);//selecting access values from dao
     			// LOGGER.info("process id are"+AvailableValueinDB);
            	  //session.setAttribute("resultvalue", AvailableValueinDB);
          	  session.setAttribute("resultvalue", AvailableValueinDB);
    			String OrgStatus="Access values deleted successfully";
    			request.setAttribute("SuperAdminSuccess",OrgStatus);
    			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/DisplayAccessAdmin.jsp");
    			requestDispatcher.forward(request, response);
    		}else {
    			String OrgStatus="Failed for deleted access values ";
     			request.setAttribute("SuperAdminFailure",OrgStatus);
     			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/DisplayAccessAdmin.jsp");
     			requestDispatcher.forward(request, response);
    		}
    }}

    
}
