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
public class ProductAccessServlet extends CommonService {
	
       
    public void run() throws ServletException,IOException{
    	session=request.getSession(true);
    	AccessDao accessDao=new AccessDao();
    	ProcessDomain processDomain=new ProcessDomain();
    	ProcessDomain processDomain1=new ProcessDomain();
    	AdminDomain adminDomain1=new AdminDomain();
    	ProcessAccessDao processAccessDao=new ProcessAccessDao();
    	String orgid=(String)session.getAttribute("orgid");
    	String userid=(String)session.getAttribute("userid");
    	String privilege=(String)session.getAttribute("privilege");
    	adminDomain1.setOrgid(orgid);
    	adminDomain1.setPrivilege(privilege);
    	//System.out.println("userid:"+userid+ " orgid:"+orgid+"privilege:"+privilege);
    	//String pAddName=request.getParameter("PAddName");
    	//String pAddId=request.getParameter("addId");
    	String addName=request.getParameter("addName");
    	String addId=request.getParameter("addId");
    	
    	//System.out.println("addId:"+addId +"addName:"+addName);
    	String oldid=request.getParameter("oldId");
    	String pEditId=request.getParameter("editId");
    	String pEditName=request.getParameter("editName");
    	//System.out.println("edit id value:"+pEditId+ "edit name :"+pEditName+"old id:"+oldid);
    	
    	String delID=request.getParameter("Delid");
    	//System.out.println("delete id is:"+delID);
    	 if(delID!=null){
       		//System.out.println("delete called");
       		String delStatus=processAccessDao.deleteAccessIdValue(delID);
           	if("success".equalsIgnoreCase(delStatus)){
           		ArrayList<AdminDomain>  AccessValues=accessDao.accessDetails(adminDomain1);
   				session.setAttribute("AccessValues",AccessValues);
          		 String DelStatus="Access value deleted successfully";
       			request.setAttribute("OwnerSuccess",DelStatus);
       			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ProductAccess.jsp");
       			rd.forward(request, response);
          	   }
             else{ 
           	  String DelStatus="Failed for deleting access values";
       			request.setAttribute("OwnerFailure",DelStatus);
       			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ProductAccess.jsp");
       			rd.forward(request, response);
          	   }
      	 }//end of delete if condition
     	 
    	
    	/* else
    	 {
    		 //System.out.println("delte else called");
         	String delStatus=pdao.deleteAccessIdValue(delID);
         	if("success".equalsIgnoreCase(delStatus)){
         		ArrayList<AdminDomain>  AccessValues=adao.accessDetails(adomain1);
 				session.setAttribute("AccessValues",AccessValues);
        		 String DelStatus="Access value deleted successfully";
     			request.setAttribute("OwnerSuccess",DelStatus);
     			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ProductAccess.jsp");
     			rd.forward(request, response);
        	   }
           else{ 
         	  String DelStatus="Failed for deleting access values";
     			request.setAttribute("OwnerFailure",DelStatus);
     			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ProductAccess.jsp");
     			rd.forward(request, response);
        	   }
    	 }*/
    	//addition function calling
    	if(!(request.getParameter("addId").isEmpty()) && request.getParameter("addName")!=null){
    		
    		//System.out.println(" add if inside the empty method");
    		processDomain1.setProcessId(request.getParameter("addId"));
    		processDomain1.setProcessName(request.getParameter("addName"));
        	String pAddId1=request.getParameter("PAddId");
        	processDomain1.setPrivilege(privilege);
        	processDomain1.setOrgId(orgid);
        	processDomain.setUserId(userid);
        	processDomain.setOrgId(orgid);
        	processDomain.setPrivilege(privilege);
        	/*String privilege=pdao.getPrivilege(adomain);
        	adomain.setPrivilege(privilege);*/
        	ArrayList<ProcessDomain> listFromDb=processAccessDao.getOldAccessId(processDomain);
        	//System.out.println("listFromDb:"+listFromDb);
        	java.util.Iterator<ProcessDomain> it = listFromDb.iterator();
        	if(listFromDb.size()==0){
        		
        		//System.out.println("new id"+processDomain1.getProcessId());
				String addNewStatus=processAccessDao.insertAccessIdValue(processDomain1);
				//System.out.println("addnew status values:"+addNewStatus);
				if("success".equalsIgnoreCase(addNewStatus)){
					 ArrayList<AdminDomain>  AccessValues=accessDao.accessDetails(adminDomain1);
					 session.setAttribute("AccessValues",AccessValues);
	        		 String AddStatus="Access values given successfully";
	     			request.setAttribute("OwnerSuccess",AddStatus);
	     			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ProductAccess.jsp");
	     			rd.forward(request, response);
	        	   }
	           else if("failure".equalsIgnoreCase(addNewStatus)){
	        		  String AddStatus="Failed for giving access values";
	     			request.setAttribute("OwnerFailure",AddStatus);
	     			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ProductAccess.jsp");
	     			rd.forward(request, response);
	        	   }
        	}else {
			    while (it.hasNext()) {
			    	processDomain = (ProcessDomain) it.next();	
				//System.out.println(processDomain.getProcessId()); 
			}
				if(processDomain.getProcessId().equalsIgnoreCase(processDomain1.getProcessId())){
					//System.out.println("two ids are equal");
					String msg="This ID Already exit";
					request.setAttribute("OwnerSuccess", msg);
					RequestDispatcher rd=request.getRequestDispatcher("../JSP/ProductAccess.jsp");
					rd.forward(request, response);
					//response.sendRedirect("../JSP/ProductAccess.jsp");
				}
				else if(processDomain.getProcessId()!= pAddId1){
					//System.out.println("new id"+processDomain1.getProcessId());
					String addStatus=processAccessDao.insertAccessIdValue(processDomain1);
					if("success".equalsIgnoreCase(addStatus)){
						ArrayList<AdminDomain>  AccessValues=accessDao.accessDetails(adminDomain1);
						session.setAttribute("AccessValues",AccessValues);
		        		 String AddStatus="Access values given successfully";
		     			request.setAttribute("OwnerSuccess",AddStatus);
		     			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ProductAccess.jsp");
		     			rd.forward(request, response);
		     			/*response.sendRedirect("../JSP/ProductAccess.jsp");*/
		        	   }
		           else if("failure".equalsIgnoreCase(addStatus)){
		        		  String AddStatus="Failed for giving access values";
		     			request.setAttribute("OwnerFailure",AddStatus);
		     			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ProductAccess.jsp");
		     			rd.forward(request, response);
		        	   }
				}
        	
        	}
    	}
    	 if(!(pEditId.isEmpty()) && !(pEditName.isEmpty())){
    		//System.out.println("edit if else");
    		processDomain1.setProcessId(request.getParameter("oldId"));
    		processDomain1.setEditId(request.getParameter("editId"));
    		processDomain1.setEditName(request.getParameter("editName"));
        	////System.out.println("edit id value:"+pEditName1+ "edit name :"+pEditId1+"old id:"+oldid1);
        	String editStatus=processAccessDao.editAccessValues(processDomain1);
        	if("success".equalsIgnoreCase(editStatus)){
        		ArrayList<AdminDomain>  AccessValues=accessDao.accessDetails(adminDomain1);
				session.setAttribute("AccessValues",AccessValues);
          		 String DelStatus="Access value modified successfully";
       			request.setAttribute("OwnerSuccess",DelStatus);
       			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ProductAccess.jsp");
       			rd.forward(request, response);
       			//response.sendRedirect("../JSP/ProductAccess.jsp");
          	   }
             else if("failure".equalsIgnoreCase(editStatus)){
          		  String DelStatus="Failed for modifying access values";
       			request.setAttribute("OwnerFailure",DelStatus);
       			RequestDispatcher rd=request.getRequestDispatcher("../JSP/ProductAccess.jsp");
       			rd.forward(request, response);
          	   }
        	
    	 }  
    	
    }
}

    	
    
        	
    	
    


    	
    	/*AdminDomain pdomain=new AdminDomain();
    	String status=null;
    	String userid=request.getParameter("userid");
    	
    	int checked=0;
     String[] access=request.getParameterValues("access");
     
     for(String s:access)
     {
    	 //System.out.println("selected id are"+s);
     }
     ArrayList<AdminDomain> listfromjsp= (ArrayList<AdminDomain>) session.getAttribute("listfromdb");
     //System.out.println("database values are"+listfromjsp);
     
     List<String> resultList = new ArrayList<String>(); 
	 if(access!=null && access.length>0)
	  resultList=Arrays.asList(access);
	List<String> listFromDb=new ArrayList<String>();
		//System.out.println("Result value is"+listfromjsp);
    java.util.Iterator<AdminDomain> it= listfromjsp.iterator();
    while(it.hasNext()){
     pdomain=it.next();
    if(pdomain.getProcessid() != null){
      listFromDb.add(pdomain.getProcessid());
      //System.out.println("listfrom db values are"+listFromDb.size());
    		 
    }
    	 
     }
    session.removeAttribute("listfromdb");
    ProcessAccessDao pdao=new ProcessAccessDao();
    List<String> finalListToAdd=new ArrayList<String>();
	   List<String> finalListToDelete=new ArrayList<String>();
	  
    if(listFromDb.size()==0){
    	//System.out.println("listfrom db:"+listFromDb);
          status= pdao.insertAccessDao(userid, resultList);
          //System.out.println("insert status value are"+status);
          if("success".equalsIgnoreCase(status)){
        	  response.sendRedirect("../JSP/success.jsp");
          }else {
        	  response.sendRedirect("../JSP/failure.jsp");
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
    	if(finalListToAdd.size()>0) {
    		//System.out.println("finalListToAdd"+finalListToAdd);
    		 status= pdao.updateAccessDao(userid, finalListToAdd);
    		//System.out.println("update status value"+status);
    		if("success".equalsIgnoreCase(status)){
    			response.sendRedirect("../JSP/success.jsp");
    		}else {
    			response.sendRedirect("../JSP/failure.jsp");
    		}
    	}
    	if(finalListToDelete.size()>0) {
    		//System.out.println("finalListToDelete"+finalListToDelete);
    		status=pdao.deleteAccessDao(userid, finalListToDelete);
    		//System.out.println("delete status value is"+status);
    		if("success".equalsIgnoreCase(status)){
    			response.sendRedirect("../JSP/success.jsp");
    		}else {
    			response.sendRedirect("../JSP/failure.jsp");
    		}
    }}
}
    	 
    	 Iterator it=InitialValues.iterator();
    	 while(it.hasNext()){
    		 adomain=(AdminDomain) it.next();
    		 //System.out.println("initial values are"+adomain.getProcessid());
    	 }
    	 for( i=1;i<access.length;i++){
    	          
    		 if(adomain.getProcessid().equals(access[i])){
    			 //System.out.println("two ids are equal"+access[i]);
    		 }else {
    			 //System.out.println("two ids are not equal"+access[i]);

    			 String string = access[i];

    			 finalStrings.add(string);

    		 }
    	 }
    	 //System.out.println("final values are"+finalStrings);
     
    // ProcessAccessDao pdao=new ProcessAccessDao();
     
    String updatestatus= pdao.updateAccessDao(userid,finalStrings);
     //System.out.println("resulltvalues are"+updatestatus);
     
     if("success".equalsIgnoreCase(updatestatus))
     {
    	 response.sendRedirect("../JSP/success.jsp");
     }else {
    	 response.sendRedirect("../JSP/failure.jsp");
     }
     */
   


     
    

