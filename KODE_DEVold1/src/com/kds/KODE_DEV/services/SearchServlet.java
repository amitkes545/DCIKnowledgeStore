package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.SearchDao;
import com.kds.KODE_DEV.domain.CreateDomain;

//@WebServlet("/SearchServlet")
public class SearchServlet extends CommonService {
	
	ArrayList<CreateDomain> al= new ArrayList<CreateDomain>();
	private static final long serialVersionUID = 1L;
       
	public void run() throws ServletException, IOException {
		SearchDao sdao= new SearchDao();
		 al=sdao.getOrg_id();	
//		 if(al!=null)
//		 {
//			 response.sendRedirect("../JSP/Details.jsp");
//		 }
		 
		
		
	}
	
	}