package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.SearchAdminDao;
import com.kds.KODE_DEV.domain.AdminDomain;




public class SearchAdminServlet extends CommonService {
	private static final long serialVersionUID = 1L;
	
	
	
		ArrayList<AdminDomain> ad= new ArrayList<AdminDomain>();
	
	       
		public void run() throws ServletException, IOException {
			SearchAdminDao sdao= new SearchAdminDao();
			 //ad=sdao.getAdminId();	

}
}
