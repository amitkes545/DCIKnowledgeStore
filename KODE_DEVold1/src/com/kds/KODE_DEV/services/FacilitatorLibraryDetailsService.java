package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;

import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.FacultyDashBoardDao;
import com.kds.KODE_DEV.domain.FacilitatorKnowReportDomain;

@SuppressWarnings("serial")
public class FacilitatorLibraryDetailsService extends CommonService{
	
	public void run() throws ServletException,IOException {
		
		FacultyDashBoardDao dao=new FacultyDashBoardDao();
		
		String libraryId=request.getParameter("libraryId");
		
		String knowStoreId=request.getParameter("ksid");
		/*String organizationId=request.getParameter("orgid");
		
		//System.out.println("libraryId::"+libraryId+ "facultyId::"+facultyId+ "organizationId::"+organizationId);*/
		
	/*	String knowStoreId=dao.getKnowStoreID(facultyId,organizationId);
		
		//System.out.println("knowStoreId::"+knowStoreId);*/
		
		ArrayList<FacilitatorKnowReportDomain>  fileNames=dao.getLibraryFiles(libraryId);
		//System.out.println("fileNames:"+fileNames);
	     //JsonResponse res = new JsonResponse();
	     JSONArray  addresses = new JSONArray();
	     addresses.add(fileNames);
		Iterator<FacilitatorKnowReportDomain> it=fileNames.iterator();
		while(it.hasNext()){
			FacilitatorKnowReportDomain domain=it.next();
			//System.out.println("file names in while::"+domain.getFilePath());
			
			
		}
		
	}

}
