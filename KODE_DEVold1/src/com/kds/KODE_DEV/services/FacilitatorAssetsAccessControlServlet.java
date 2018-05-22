package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.FacilitatorSelectKsidKStoreDao;
import com.kds.KODE_DEV.dao.OwnerAControlKStoreDao;
import com.kds.KODE_DEV.domain.FacilitatorKAssetsReportDomain;
import com.kds.KODE_DEV.domain.OwnerAControlKStoreDomain;

@SuppressWarnings("serial")
public class FacilitatorAssetsAccessControlServlet extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(FacilitatorAssetsAccessControlServlet.class);
	
	@Override
	public void run() throws ServletException, IOException {

		session = request.getSession();
		
		//String kID = request.getParameter("ksid");
		String[] checkboxGroup = request.getParameterValues("checkboxGroup");
		System.out.println("checkboxGroup in assets="+checkboxGroup);	
		
		//String status = request.getParameter("status");
		//System.out.println("checkboxGroupLength="+checkboxGroup.length);
		//LOGGER.info(status);
		ArrayList<FacilitatorKAssetsReportDomain> arl = new ArrayList<FacilitatorKAssetsReportDomain>();
		String libId=request.getParameter("libid");
		String courseid = request.getParameter("courseid");
		String topicid = request.getParameter("topicid");
		String subtopicid = request.getParameter("subtopicid");
		String filename = request.getParameter("filename");
		String newlibid = request.getParameter("newlibid");
		String uploadby = request.getParameter("uploadby");
		System.out.println("courseid "+courseid);
		System.out.println("topicid "+topicid);
		System.out.println("subtopicid "+subtopicid);
		System.out.println("filename "+filename);
		System.out.println("uploadby "+uploadby);
		System.out.println("newlibid "+libId);
		String libID = request.getParameter("libid");
		System.out.println("libID "+libID);
		
		
		//newly added for audit trail by dinesh 				
		
		
		
		String orgid=(String)session.getAttribute("orgid");		
				String userid=(String)session.getAttribute("userid");		
		FacilitatorSelectKsidKStoreDao dobj2 = new FacilitatorSelectKsidKStoreDao();		
					String ksidHidden = dobj2.fetchValue1(orgid, userid);		
					System.out.println("ksidHidden"+ksidHidden);		
					
			System.out.println("ksid Audit Trail"+ksidHidden);		
			System.out.println("orgid "+orgid);		
				// newly added for audit trail end
			
			
		
		
		
		
		String[] values = request.getParameterValues("libId");
		System.out.println("values="+values);
		String[] libName = request.getParameterValues("libName");
		System.out.println("libName="+libName);
		String[] libSize = request.getParameterValues("libSize");
		System.out.println("libSize="+libSize);
		
		
		System.out.println("checkboxGroup="+checkboxGroup.length);
		
		for(int i=0; i<checkboxGroup.length; i++){
			String lib=checkboxGroup[i];
		//	String libId="libId"+lib;
		//	System.out.println("libId="+libId+";");
			
			//System.out.println("libId="+request.getParameter("libIdLib7"));
			
			FacilitatorKAssetsReportDomain fmksDomain = new FacilitatorKAssetsReportDomain();
			System.out.println("=============================");
			System.out.println("Upload checkboxgroup "+request.getParameter("uploadby"+checkboxGroup[i]));
			//System.out.println("libName ('Upload'+checkboxGroup[i]) "+request.getParameter("libName"+checkboxGroup[i]));
			System.out.println("Filename'+checkboxGroup[i]) "+request.getParameter("Filename"+checkboxGroup[i]));
			System.out.println("libId('Upload'+checkboxGroup[i]) "+request.getParameter("newlibid"+checkboxGroup[i]));
			System.out.println("status ('Upload'+checkboxGroup[i]) "+request.getParameter("status"+checkboxGroup[i]));
			fmksDomain.setUploadedBy(request.getParameter("uploadby"+checkboxGroup[i]));
			fmksDomain.setFileName(request.getParameter("Filename"+checkboxGroup[i]));
		//	fmksDomain.setUploadedBy(request.getParameter("Upload"+checkboxGroup[i]));
			//fmksDomain.setLibId(request.getParameter("newlibid"+checkboxGroup[i]));
			//fmksDomain.setLibId(libId);
			fmksDomain.setLibId(libID);
			System.out.println("STATUS "+ request.getParameter("status"+checkboxGroup[i]) );
		
			fmksDomain.setStatus(request.getParameter("status"+checkboxGroup[i]));
	
			fmksDomain.setCourseId(courseid);
			fmksDomain.setTopicId(topicid);
			fmksDomain.setSubTopicId(subtopicid);
			
			//newly added for audit trail purpose by dinesh		
			fmksDomain.setOrgId(orgid);		
			fmksDomain.setKsId(ksidHidden);		
		//newly added for audit trail purpose by dinesh		
			
			
		//	fmksDomain.setFileName(filename);
			//fmksDomain.setLibId(newlibid);
		//	fmksDomain.setUploadedBy(uploadby);
			System.out.println("=============================");
			/*assetDomain.setTopic(resultSet.getString(1));
			assetDomain.setSubTopic(resultSet.getString(2));
			assetDomain.setFileName(resultSet.getString(3));
			assetDomain.setFileSize(resultSet.getInt(4));
			assetDomain.setFilePath(resultSet.getString(5));
			assetDomain.setStatus(resultSet.getString(6)); */
			//fmksDomain.setKsId(request.getParameter("ksid"));
			//System.out.println("testlib="+request.getParameter("mytestlib"));
			//file_name, lib_id, uploaded_by
			
			//System.out.println("status="+request.getParameter("status"+checkboxGroup[i]));
			//System.out.println("libId="+request.getParameter("libId"+checkboxGroup[i]));
			//String libId = request.getParameter("libId"+checkboxGroup[i]));
			//System.out.println("ksid="+request.getParameter("ksid"));
			//System.out.println("lib id is"+checkboxGroup[i]);
			arl.add(fmksDomain);
			
		}
		String retStatus = "";


		OwnerAControlKStoreDao mksDao = new OwnerAControlKStoreDao();
		//method for updating knowStore status active
		//if ("Active".equals(status)) {
             
			retStatus = mksDao.updateValues(arl);
			
			if ("success".equals(retStatus)) {

				String msg = "Successfully Published Knowledge Assets";
				request.setAttribute("FacultySuccess", msg);
				//response.sendRedirect("../JSP/FacilitatorAssetKnow.jsp");
				
				  RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/FacilitatorAssetKnow.jsp");
		  			requestDispatcher.forward(request, response);
		  		 
		  			/*	RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("../JSP/FacilitatorKnowPublish.jsp");
				requestDispatcher.forward(request, response);*/
			}
			if ("failure".equals(retStatus)) {

				String msg = "Error during Knowledge Assets !";
				request.setAttribute("FacultyFailure", msg);
				//response.sendRedirect("../JSP/FacilitatorAssetKnow.jsp");
				 RequestDispatcher requestDispatcher=request.getRequestDispatcher("../JSP/FacilitatorAssetKnow.jsp");
		  			requestDispatcher.forward(request, response);
		  		
				/*RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("../JSP/FacilitatorKnowPublish.jsp");
				requestDispatcher.forward(request, response);*/
			}
         // method for updating knowStore status inactive
		//} else if ("InActive".equals(status)) {
			
		/*	retStatus = mksDao.updateValuesInActive(mksDomain);

			if ("success".equals(retStatus)) {

				String msg2 = "Knowledge Assets  inactive now !";

				session.setAttribute("FacultyFailure", msg2);
				response.sendRedirect("../JSP/FacilitatorKnowPublish.jsp");
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("../JSP/FacilitatorKnowPublish.jsp");
				requestDispatcher.forward(request, response);
			}*/
            
		}
		//forwarding success message to home page
		
	}