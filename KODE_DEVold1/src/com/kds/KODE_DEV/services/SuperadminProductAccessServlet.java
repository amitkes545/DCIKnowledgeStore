package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.AccessDao;
import com.kds.KODE_DEV.dao.ProcessAccessDao;
import com.kds.KODE_DEV.domain.AdminDomain;
import com.kds.KODE_DEV.domain.ProcessDomain;

@SuppressWarnings("serial")
public class SuperadminProductAccessServlet extends CommonService{

	 public void run() throws ServletException,IOException{
	    	session=request.getSession(true);
	    	AccessDao adao=new AccessDao();
	    	ProcessDomain adomain=new ProcessDomain();
	    	ProcessDomain Adomain=new ProcessDomain();
	    	AdminDomain adomain1=new AdminDomain();
	    	String orgid=(String)session.getAttribute("orgid");
	    	String userid=(String)session.getAttribute("userid");
	    	String privilege=(String)session.getAttribute("privilege");
	    	adomain1.setOrgid(orgid);
	    	adomain1.setPrivilege(privilege);
	    	//System.out.println("userid:"+userid+ " orgid:"+orgid+"privilege:"+privilege);
	    	String addName=request.getParameter("addName");
	    	String addId=request.getParameter("addId");
	    	
	    	//System.out.println("process Name:"+addName+ "processid:"+addId);
	    	String oldid=request.getParameter("oldId");
	    	String pEditId=request.getParameter("editId");
	    	String pEditName=request.getParameter("editName");
	    	//System.out.println("edit id value:"+pEditId+ "edit name :"+pEditName+"old id:"+oldid);
	    	
	    	String delID=request.getParameter("Delid");
	    	//System.out.println("delete id is:"+delID);
	    	
	    	ProcessAccessDao pdao=new ProcessAccessDao();
	    	
        if(delID!=null){
	    		
	        	String delStatus=pdao.deleteAccessIdValue(delID);
	        	if("success".equalsIgnoreCase(delStatus)){
	        		ArrayList<AdminDomain>  AccessValues=adao.accessDetails(adomain1);
					session.setAttribute("AccessValues",AccessValues);
	       		 String DelStatus="Access value deleted successfully";
	    			request.setAttribute("OwnerSuccess",DelStatus);
	    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/SuperadminProductAccess.jsp");
	    			rd.forward(request, response);
	       	   }
	          else if("failure".equalsIgnoreCase(delStatus)){
	       		  String DelStatus="Failed for deleting access values";
	    			request.setAttribute("OwnerFailure",DelStatus);
	    			RequestDispatcher rd=request.getRequestDispatcher("../JSP/SuperadminProductAccess.jsp");
	    			rd.forward(request, response);
	       	   }
	    	}
        
	    	if(!(request.getParameter("addId").isEmpty()) && request.getParameter("addName")!=null){
	    		
	    		//System.out.println("add if");
	    		Adomain.setProcessId(request.getParameter("addId"));
	    		Adomain.setProcessName(request.getParameter("addName"));
	        	String pAddId1=request.getParameter("addId");
	        	Adomain.setPrivilege(privilege);
	        	Adomain.setOrgId(orgid);
	        	adomain.setUserId(userid);
	        	adomain.setOrgId(orgid);
	        	adomain.setPrivilege(privilege);
	        	/*String privilege=pdao.getPrivilege(adomain);
	        	adomain.setPrivilege(privilege);*/
	        	ArrayList<ProcessDomain> listFromDb=pdao.getOldAccessId(adomain);
	        	//System.out.println("listFromDb:"+listFromDb);
	        	java.util.Iterator<ProcessDomain> it = listFromDb.iterator();
	        	if(listFromDb.size()==0){
	        		//System.out.println("new id"+Adomain.getProcessId());
					String addNewStatus=pdao.insertAccessIdValue(Adomain);
					//System.out.println("addnew status values:"+addNewStatus);
					if("success".equalsIgnoreCase(addNewStatus)){
						 ArrayList<AdminDomain>  AccessValues=adao.accessDetails(adomain1);
						 session.setAttribute("AccessValues",AccessValues);
		        		 String AddStatus="Access values inserted successfully";
		     			request.setAttribute("OwnerSuccess",AddStatus);
		     			RequestDispatcher rd=request.getRequestDispatcher("../JSP/SuperadminProductAccess.jsp");
		     			rd.forward(request, response);
		        	   }
		           else if("failure".equalsIgnoreCase(addNewStatus)){
		        		  String AddStatus="Failed for inserting access values";
		     			request.setAttribute("OwnerFailure",AddStatus);
		     			RequestDispatcher rd=request.getRequestDispatcher("../JSP/SuperadminProductAccess.jsp");
		     			rd.forward(request, response);
		        	   }
	        	}else {
				    while (it.hasNext()) {
					adomain = (ProcessDomain) it.next();	
					//System.out.println(adomain.getProcessId()); 
				}
					if(adomain.getProcessId().equalsIgnoreCase(Adomain.getProcessId())){
						//System.out.println("two ids are equal");
						String msg="This ID already exit";
						request.setAttribute("OwnerSuccess", msg);
						RequestDispatcher rd=request.getRequestDispatcher("../JSP/SuperadminProductAccess.jsp");
						rd.forward(request, response);
						//response.sendRedirect("../JSP/ProductAccess.jsp");
					}
					else if(adomain.getProcessId()!= pAddId1){
						//System.out.println("new id"+Adomain.getProcessId());
						String addStatus=pdao.insertAccessIdValue(Adomain);
						if("success".equalsIgnoreCase(addStatus)){
							ArrayList<AdminDomain>  AccessValues=adao.accessDetails(adomain1);
							session.setAttribute("AccessValues",AccessValues);
			        		 String AddStatus="Access values inserted successfully";
			     			request.setAttribute("OwnerSuccess",AddStatus);
			     			RequestDispatcher rd=request.getRequestDispatcher("../JSP/SuperadminProductAccess.jsp");
			     			rd.forward(request, response);
			     			/*response.sendRedirect("../JSP/ProductAccess.jsp");*/
			        	   }
			           else if("failure".equalsIgnoreCase(addStatus)){
			        		  String AddStatus="Failed for inserting access values";
			     			request.setAttribute("OwnerFailure",AddStatus);
			     			RequestDispatcher rd=request.getRequestDispatcher("../JSP/SuperadminProductAccess.jsp");
			     			rd.forward(request, response);
			        	   }
					}
	        	}
	        	
	    	} else if(pEditId!=null && pEditName!=null){
	    		//System.out.println("edit if else");
	    		Adomain.setProcessId(request.getParameter("oldId"));
	        	Adomain.setEditId(request.getParameter("editId"));
	        	Adomain.setEditName(request.getParameter("editName"));
	        	////System.out.println("edit id value:"+pEditName1+ "edit name :"+pEditId1+"old id:"+oldid1);
	        	String editStatus=pdao.editAccessValues(Adomain);
	        	if("success".equalsIgnoreCase(editStatus)){
	        		ArrayList<AdminDomain>  AccessValues=adao.accessDetails(adomain1);
					session.setAttribute("AccessValues",AccessValues);
	          		 String DelStatus="Access value modified successfully";
	       			request.setAttribute("OwnerSuccess",DelStatus);
	       			RequestDispatcher rd=request.getRequestDispatcher("../JSP/SuperadminProductAccess.jsp");
	       			rd.forward(request, response);
	       			//response.sendRedirect("../JSP/ProductAccess.jsp");
	          	   }
	             else if("failure".equalsIgnoreCase(editStatus)){
	          		  String DelStatus="Failed for modifying access values";
	       			request.setAttribute("OwnerFailure",DelStatus);
	       			RequestDispatcher rd=request.getRequestDispatcher("../JSP/SuperadminProductAccess.jsp");
	       			rd.forward(request, response);
	          	   }
	        	
	    	} 
	    }
	    
}
