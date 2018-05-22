package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.AssaignDao;
import com.kds.KODE_DEV.domain.AdminDomain;


//@WebServlet("/UpdateFacultySubject")
public class UpdateFacultySubject extends CommonService {
	private static final long serialVersionUID = 1L;
   public void run() throws ServletException,IOException{
	   session=request.getSession(true);
	   AdminDomain fdomain=new AdminDomain();
	   AssaignDao sdao=new AssaignDao();
	   List<String> finalListToDelete=new ArrayList<String>();
	   //List<String> finalListToAdd=new ArrayList<String>();
	   List<String> resultList = new ArrayList<String>(); 
	   String status=null;
	   String[] subjectid=request.getParameterValues("subjectid");
	   String facultyid=request.getParameter("facultyid");
	   String orgid=session.getAttribute("orgid").toString();
	   fdomain.setAdminId(facultyid);
	   fdomain.setOrgid(orgid);
	   //System.out.println("faculty id"+facultyid);
	   /*for(String s:subjectid){
		   //System.out.println("subject id are:"+s);
	   }*/
		  
		   ArrayList<AdminDomain> listfromDb=sdao.selectSubjectId(fdomain);
		   
		  
			 if(subjectid!=null && subjectid.length>0)
			  resultList=Arrays.asList(subjectid);
			//System.out.println("listfrom jsp values are:"+resultList.size());
			 List<String> listFromDB=new ArrayList<String>();
				//System.out.println("Result value is"+listfromDb);
		    java.util.Iterator<AdminDomain> it= listfromDb.iterator();
		    while(it.hasNext()){
		     fdomain=it.next();
		     if(fdomain.getSubject_id() != null){
		         listFromDB.add(fdomain.getSubject_id());
		         //System.out.println("listfrom db values are"+listFromDB.size());
		     }
		    }
		   
		    
		    if(listFromDB.size()>0 && resultList.size()==0){
		    	finalListToDelete.addAll(listFromDB);
		    }
		    else if(resultList.size()>0){
		    		if(resultList.size()==listFromDB.size() || resultList.size()>listFromDB.size()){
		    			for(int i=0;i<listFromDB.size();i++){
		    				if(!resultList.contains(listFromDB.get(i))){
		    					finalListToDelete.add(listFromDB.get(i));
		    				}
		    			}
		    			/*for(int i=0;i<resultList.size();i++){
		    				if(!listFromDB.contains(resultList.get(i))){
		    					finalListToAdd.add(resultList.get(i));
		    				}
		    			}*/
		    		}else if(listFromDB.size()>resultList.size()){
		    			/*for(int i=0;i<resultList.size();i++){
		    				if(!listFromDB.contains(resultList.get(i))){
		    					finalListToAdd.add(resultList.get(i));
		    				}
		    			}*/
		    			for(int i=0;i<listFromDB.size();i++){
		    				if(!resultList.contains(listFromDB.get(i))){
		    					finalListToDelete.add(listFromDB.get(i));
		    				}
		    			}

		    		}
		    		
		    	}
		    if(resultList.size() == listFromDB.size()){
		    	//System.out.println("jsp and database values are equal");
		    	String AdminMsg="No values not updated ";
     			request.setAttribute("AdminSuccess",AdminMsg);
     			RequestDispatcher rd=request.getRequestDispatcher("../JSP/DisplayFacultySubject.jsp");
     			rd.forward(request, response);
		    }
		    	
		    	if(finalListToDelete.size()>0) {
		    		//System.out.println("finalListToDelete"+finalListToDelete);
		    		status=sdao.deleteAssaignDao(facultyid, finalListToDelete);
		    		//System.out.println("delete status value is"+status);
		    		if("success".equalsIgnoreCase(status)){
		    			String AdminMsg=" Access values deleted successfully";
		     			request.setAttribute("AdminSuccess",AdminMsg);
		     			RequestDispatcher rd=request.getRequestDispatcher("../JSP/DisplayFacultySubject.jsp");
		     			rd.forward(request, response);
		    		}else {
		    			String AdminMsg="Failed for delete access values ";
		     			request.setAttribute("AdminFailure",AdminMsg);
		     			RequestDispatcher rd=request.getRequestDispatcher("../JSP/DisplayFacultySubject.jsp");
		     			rd.forward(request, response);
		    		}
		    }
		}
}
/*if(listFromDB.size()==0){
//System.out.println("listfrom db:"+listFromDB);
  status= sdao.insertAssaignDao(facultyid, resultList);
  //System.out.println("insert status value are"+status);
  if("success".equalsIgnoreCase(status)){
	  String AdminMsg=" Access values added successfully";
			request.setAttribute("AdminSuccess",AdminMsg);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/DisplayFacultySubject.jsp");
			rd.forward(request, response);
  }else {
	  String AdminMsg="Failed for add access values ";
			request.setAttribute("AdminFailure",AdminMsg);
			RequestDispatcher rd=request.getRequestDispatcher("../JSP/DisplayFacultySubject.jsp");
			rd.forward(request, response);
  }
}*/
//else

//else{
/*if(finalListToAdd.size()>0) {
//System.out.println("finalListToAdd"+finalListToAdd);
 status= sdao.updateAssaignDao(facultyid, finalListToAdd);
//System.out.println("update status value"+status);
if("success".equalsIgnoreCase(status)){
	String AdminMsg=" Access values added successfully";
		request.setAttribute("AdminSuccess",AdminMsg);
		RequestDispatcher rd=request.getRequestDispatcher("../JSP/DisplayFacultySubject.jsp");
		rd.forward(request, response);
}else {
	String AdminMsg="Failed for add access values ";
		request.setAttribute("AdminFailure",AdminMsg);
		RequestDispatcher rd=request.getRequestDispatcher("../JSP/DisplayFacultySubject.jsp");
		rd.forward(request, response);
}
}*/