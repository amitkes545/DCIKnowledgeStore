package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.SuperAdminAccessControlDao;
import com.kds.KODE_DEV.domain.AdminDomain;

@SuppressWarnings("serial")
public class DisplayAccessSuperAdminServlet extends CommonService {
	//private static final long serialVersionUID = 1L;
       
	static final Logger LOGGER = Logger.getLogger(DisplayAccessSuperAdminServlet.class);
    public void run() throws ServletException,IOException{
    	session=request.getSession();
    	AdminDomain superAdminDomain=new AdminDomain();
    	 List<String> finalListToAdd=new ArrayList<String>();
  	   List<String> finalListToDelete=new ArrayList<String>();
  	 SuperAdminAccessControlDao superAdminAccessDao=new SuperAdminAccessControlDao();
  	List<String> resultList = new ArrayList<String>(); 
    	String status=null;
    	String userid=request.getParameter("selecteduserid");
    	String organizationId=(String)session.getAttribute("orgid");
    	superAdminDomain.setAdminId(request.getParameter("selecteduserid"));
    	superAdminDomain.setOrgid(organizationId);
    	LOGGER.info("selecteduserid:"+userid);
    	
     String[] access=request.getParameterValues("access");
      ArrayList<AdminDomain> listfromdb=(ArrayList<AdminDomain>) session.getAttribute("AvailableValueinDB");
     LOGGER.info("listfromdb"+listfromdb);
   
     
	 if(access!=null && access.length>0)
	  resultList=Arrays.asList(access);
	List<String> listFromDb=new ArrayList<String>();
		LOGGER.info("Result value is"+listfromdb);
    java.util.Iterator<AdminDomain> it= listfromdb.iterator();
    while(it.hasNext()){
    	superAdminDomain=it.next();
    if(superAdminDomain.getProcessid() != null){
      listFromDb.add(superAdminDomain.getProcessid());
      LOGGER.info("listfrom db values are"+listFromDb.size());
    		 
    }
    	 
     }
    if(listFromDb.size()>0 && resultList.size()==0){
      	finalListToDelete.addAll(listFromDb);
      }
     else if(access==null){
    	 LOGGER.info("No Values Are not Selected");
    	 String OrgStatus="Access values are not selected";
		request.setAttribute("OwnerSuccess",OrgStatus);
		RequestDispatcher rd=request.getRequestDispatcher("../JSP/DisplayAccessSuperAdminOwner.jsp");
		rd.forward(request, response);
     }else {
    session.removeAttribute("AvailableValueinDB");
   
    
	  
    if(listFromDb.size()==0){
    	LOGGER.info("listfrom db:"+listFromDb);
          status= superAdminAccessDao.insertAccessDao(userid, resultList);
          LOGGER.info("insert status value are"+status);
          if("success".equalsIgnoreCase(status)){
        	//selection access values from dao
        		ArrayList<AdminDomain>    AvailableValueinDB=superAdminAccessDao.retriveAllDetails(superAdminDomain);
        		session.setAttribute("AvailableValueinDB", AvailableValueinDB);
        	  String OrgStatus="Access values given successfully";
    			request.setAttribute("OwnerSuccess",OrgStatus);
    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/DisplayAccessSuperAdminOwner.jsp");
    			rd.forward(request, response);
          }else {
        	  String OrgStatus="Failured for giving access values";
    			request.setAttribute("OwnerFailure",OrgStatus);
    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/DisplayAccessSuperAdminOwner.jsp");
    			rd.forward(request, response);
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
    		 status= superAdminAccessDao.updateAccessDao(userid, finalListToAdd);
    		LOGGER.info("update status value"+status);
    		if("success".equalsIgnoreCase(status)){
    			//selection access values from dao
        		ArrayList<AdminDomain>    AvailableValueinDB=superAdminAccessDao.retriveAllDetails(superAdminDomain);
        		session.setAttribute("AvailableValueinDB", AvailableValueinDB);
    			String OrgStatus="Access values given successfully";
    			request.setAttribute("OwnerSuccess",OrgStatus);
    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/DisplayAccessSuperAdminOwner.jsp");
    			rd.forward(request, response);
    		}else {
    			 String OrgStatus="Failured for giving access values";
     			request.setAttribute("OwnerFailure",OrgStatus);
     			RequestDispatcher rd=request.getRequestDispatcher("../JSP/DisplayAccessSuperAdminOnwer.jsp");
     			rd.forward(request, response);
    		}
    	}
    	if(finalListToDelete.size()>0) {
    		LOGGER.info("finalListToDelete"+finalListToDelete);
    		status=superAdminAccessDao.deleteAccessDao(userid, finalListToDelete);
    		LOGGER.info("delete status value is"+status);
    		if("success".equalsIgnoreCase(status)){
    			//selection access values from dao
        		ArrayList<AdminDomain>    AvailableValueinDB=superAdminAccessDao.retriveAllDetails(superAdminDomain);
        		session.setAttribute("AvailableValueinDB", AvailableValueinDB);
    			String OrgStatus="Access values deleted successfully";
    			request.setAttribute("OwnerSuccess",OrgStatus);
    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/DisplayAccessSuperAdminOwner.jsp");
    			rd.forward(request, response);
    		}else {
    			 String OrgStatus="Failured for delete access values";
     			request.setAttribute("OwnerFailure",OrgStatus);
     			RequestDispatcher rd=request.getRequestDispatcher("../JSP/DisplayAccessSuperAdminOwner.jsp");
     			rd.forward(request, response);
    		}
    }}

     
    }

