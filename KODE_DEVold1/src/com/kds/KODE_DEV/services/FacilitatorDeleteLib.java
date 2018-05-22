package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.FacilitatorDeleteLibDao;
import com.kds.KODE_DEV.domain.FacilitatorManageKnowStoreDomain;

@SuppressWarnings("serial")
public class FacilitatorDeleteLib extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(FacilitatorDeleteLib.class);

	@Override
	public void run() throws ServletException, IOException {
		
		session = request.getSession(false);
		
		FacilitatorDeleteLibDao fdlDao = new FacilitatorDeleteLibDao();
		ArrayList<FacilitatorManageKnowStoreDomain> arl = new ArrayList<FacilitatorManageKnowStoreDomain>();
		FacilitatorManageKnowStoreDomain mks = new FacilitatorManageKnowStoreDomain();
		  mks.setLibId(request.getParameter("libid"));
		  
		  String[] values = request.getParameterValues("libId");
			String[] libName = request.getParameterValues("libName");
			String[] libSize = request.getParameterValues("libSize");
			
			
			
			
			
			String[] checkboxGroup = request.getParameterValues("checkboxGroup");
			
			
			for(int i=0; i<checkboxGroup.length; i++){
				FacilitatorManageKnowStoreDomain mks1 = new FacilitatorManageKnowStoreDomain();
				//mks1.setLibName(request.getParameter("libName"+checkboxGroup[i]));
				 
				String dd = (request.getParameter("libSize"+checkboxGroup[i]));
				
				String NewLibSize = dd.replaceAll("[^\\d.]", "");
				LOGGER.info("NewLibSize="+NewLibSize);
				int libSizeInInt  = Integer.parseInt(NewLibSize);
				mks1.setLibSize(libSizeInInt);
				mks1.setLibId(request.getParameter("libId"+checkboxGroup[i]));
				mks1.setFileName(request.getParameter("libName"+checkboxGroup[i]));
				mks1.setUploadedBy(request.getParameter("Upload"+checkboxGroup[i]));
				
				String userId = session.getAttribute("userid").toString();
				mks1.setUserId(userId);
				//String libId = request.getParameter("libId"+checkboxGroup[i]));
				//mks1.setKsId(request.getParameter("ksid"));
				//mks1.setUserId(request.getParameter("userid"));
				//mks1.setOrgId(request.getParameter("orgid"));
				//mks1.setStudentId(request.getParameter("oldlibName"+checkboxGroup[i]));
				////System.out.println("lib id is"+checkboxGroup[i]);
				arl.add(mks1);
				
			}
		
	LOGGER.info("Before deleete arl="+arl);
		String deletedValues = fdlDao.deleteValues(arl);
		
		LOGGER.info("After deleete deletedValues"+deletedValues);
		if(deletedValues!=null){
		if (deletedValues.equals("success"))
			
		{   
			String msg = "Library deleted successfully !!";
			request.setAttribute("FacultySuccess", msg);
			
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("../JSP/FacilitatorManageLib.jsp");
			requestDispatcher.forward(request, response);
			//response.sendRedirect("../JSP/FacilitatorManageLib.jsp");
		}else{
			
			String msg = "Failed to delete Library , please try again !!";
			request.setAttribute("FacultySuccess", msg);
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("../JSP/FacilitatorManageLib.jsp");
			requestDispatcher.forward(request, response);
			//response.sendRedirect("../JSP/FacilitatorManageLib.jsp");
		}
		}else{
			
			String msg = "Failed to delete Library , please try again !!";
			request.setAttribute("FacultySuccess", msg);
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("../JSP/FacilitatorManageLib.jsp");
			requestDispatcher.forward(request, response);
			//response.sendRedirect("../JSP/FacilitatorManageLib.jsp");
		}
	}
}