package com.kds.KODE_DEV.services;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.FacilitatorAssetsDeleteLibDao;
import com.kds.KODE_DEV.dao.FacilitatorSelectKsidKStoreDao;
import com.kds.KODE_DEV.domain.FacilitatorManageKnowStoreDomain;
import com.kds.KODE_DEV.domain.FacilitatorManageKnowStoreDomain;

@SuppressWarnings("serial")
public class FacilitatorAssetsDeleteLib extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(FacilitatorDeleteLib.class);

	@Override
	public void run() throws ServletException, IOException {
		
		session = request.getSession(false);
		
		FacilitatorAssetsDeleteLibDao fdlDao = new FacilitatorAssetsDeleteLibDao();
		ArrayList<FacilitatorManageKnowStoreDomain> arl = new ArrayList<FacilitatorManageKnowStoreDomain>();
		FacilitatorManageKnowStoreDomain mks = new FacilitatorManageKnowStoreDomain();
		String libID = request.getParameter("libid");
		System.out.println("libID "+libID);
		mks.setLibId(request.getParameter("libid"));
		  
		  String[] values = request.getParameterValues("libId");
			String[] libName = request.getParameterValues("libName");
			String[] libSize = request.getParameterValues("libSize");
			
			
			
			
			
			String[] checkboxGroup = request.getParameterValues("checkboxGroup");
			
			
			for(int i=0; i<checkboxGroup.length; i++){
				FacilitatorManageKnowStoreDomain mks1 = new FacilitatorManageKnowStoreDomain();
				//mks1.setLibName(request.getParameter("libName"+checkboxGroup[i]));
				 
				//String dd = (request.getParameter("libSize"+checkboxGroup[i]));
				//System.out.println("libSize="+dd);
				//String NewLibSize = dd.replaceAll("[^\\d.]", "");
				
				//int libSizeInInt  = Integer.parseInt(NewLibSize);
			//	mks1.setLibSize(libSizeInInt);
		/*		mks1.setLibId(request.getParameter("libId"+checkboxGroup[i]));
				mks1.setFileName(request.getParameter("libName"+checkboxGroup[i]));
				mks1.setUploadedBy(request.getParameter("Upload"+checkboxGroup[i]));
				
		*/		
				System.out.println("Delete =============================");
				System.out.println("Upload checkboxgroup "+request.getParameter("uploadby"+checkboxGroup[i]));
				//System.out.println("libName ('Upload'+checkboxGroup[i]) "+request.getParameter("libName"+checkboxGroup[i]));
				System.out.println("Filename'+checkboxGroup[i]) "+request.getParameter("Filename"+checkboxGroup[i]));
				System.out.println("libId('Upload'+checkboxGroup[i]) "+request.getParameter("newlibid"+checkboxGroup[i]));
			
				mks1.setUploadedBy(request.getParameter("uploadby"+checkboxGroup[i]));
				mks1.setFileName(request.getParameter("Filename"+checkboxGroup[i]));
			//	fmksDomain.setUploadedBy(request.getParameter("Upload"+checkboxGroup[i]));
				//mks1.setLibId(request.getParameter("newlibid"+checkboxGroup[i]));
				mks1.setLibId(libID);
				
				//String userId =mks1.setLibId(libID); session.getAttribute("userid").toString();
				//mks1.setUserId(userId);
				//String libId = request.getParameter("libId"+checkboxGroup[i]));
				//mks1.setKsId(request.getParameter("ksid"));
				//mks1.setUserId(request.getParameter("userid"));
				//mks1.setOrgId(request.getParameter("orgid"));
				//mks1.setStudentId(request.getParameter("oldlibName"+checkboxGroup[i]));
				////System.out.println("lib id is"+checkboxGroup[i]);
				
				
				//newly added for audit trail by dinesh 		
				String orgid=(String)session.getAttribute("orgid");		
		String userid=(String)session.getAttribute("userid");		
FacilitatorSelectKsidKStoreDao dobj2 = new FacilitatorSelectKsidKStoreDao();		
			String ksidHidden = dobj2.fetchValue1(orgid, userid);		
			System.out.println("ksidHidden"+ksidHidden);		
			
	System.out.println("ksid Audit Trail"+ksidHidden);		
	System.out.println("orgid "+orgid);		
mks1.setKsId(ksidHidden);		
mks1.setOrgId(orgid);		
// end audit trail by dinesh
				
				arl.add(mks1);
				
			}
		
	
		String deletedValues = fdlDao.deleteValues(arl);
		
	  
		if (deletedValues.equals("success"))
			
		{   
			String msg = "Record deleted successfully !!";
			request.setAttribute("FacultySuccess11", msg);
			//response.sendRedirect("../JSP/FacilitatorAssetKnow.jsp");
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/FacilitatorAssetKnow.jsp");
  			requestDispatcher.forward(request, response);
		}else{
			
			String msg = "Failed to delete record , please try again !!";
			request.setAttribute("FacultyFailure11", msg);
			//response.sendRedirect("../JSP/FacilitatorAssetKnow.jsp");
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/FacilitatorAssetKnow.jsp");
  			requestDispatcher.forward(request, response);
		}

	}
}