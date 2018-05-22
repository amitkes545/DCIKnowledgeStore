
package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.InstitutionsListDao;
import com.kds.KODE_DEV.domain.DisplayCoursesDomain;

@SuppressWarnings("serial")
public class InstitutionsListService extends CommonService {
//	private static final long serialVersionUID = 1L;
	
	public void run() throws ServletException,IOException
	{	
		
		//System.out.println("hi1");
		InstitutionsListDao ild=new InstitutionsListDao();
		ArrayList<DisplayCoursesDomain> arl=ild.selectInstitutions();
		request.setAttribute("InstitutionsList",arl);
		RequestDispatcher rd=request.getRequestDispatcher("../JSP/InstitutionsList.jsp");
		rd.forward(request, response); 
			
	}
}
