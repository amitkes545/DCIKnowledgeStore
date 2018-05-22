package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;

import com.kds.KODE_DEV.dao.DeleteDao;
import com.kds.KODE_DEV.dao.UpdateDao;
import com.kds.KODE_DEV.domain.CreateDomain;




public class UpdateServlet extends CommonService {
	private static final long serialVersionUID = 1L;
	
       
	@Override
	public void run() throws ServletException, IOException {
              
		CreateDomain cDomain = new CreateDomain();
		cDomain.setOrg_id(request.getParameter("org_id"));
		cDomain.setOrg_name(request.getParameter("org_name"));
		cDomain.setOrg_type(request.getParameter("org_type"));
		
		cDomain.setAddress(request.getParameter("address"));
		cDomain.setCity(request.getParameter("city"));
		cDomain.setCountry(request.getParameter("country"));
		cDomain.setPcode(request.getParameter("pcode"));
		cDomain.setPhone(request.getParameter("phone"));
		cDomain.setFax(request.getParameter("fax"));
		cDomain.setEcno(request.getParameter("ecno"));
		cDomain.setEmail_id(request.getParameter("email"));
		cDomain.setUrl(request.getParameter("url"));
		cDomain.setLogo(request.getParameter("logo"));
		cDomain.setYof(request.getParameter("yof"));
		cDomain.setBelongs(request.getParameter("belongs"));
         cDomain.setDate(convertStringToTimestamp(request.getParameter("date")));

		
		if(request.getParameter("update")!=null){
		
         UpdateDao updatedao = new UpdateDao();
 		String updatestatus = updatedao.updateValues(cDomain);
 		if("success".equalsIgnoreCase(updatestatus)){
 			response.sendRedirect("../JSP/success.jsp");
 		}else{
 			response.sendRedirect("../JSP/failure.jsp");
 		}
		}else {
			
	         DeleteDao deletedao = new DeleteDao();
	 		String deletestatus = deletedao.deleteValues(cDomain);
	 		if("success".equalsIgnoreCase(deletestatus)){
	 			response.sendRedirect("../JSP/success.jsp");
	 		}else{
	 			response.sendRedirect("../JSP/failure.jsp");
	 		}
		}
	}
	public static Timestamp convertStringToTimestamp(String str_date) {
	    try {
	      DateFormat formatter;
	      formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	       // you can change format of date
	      Date date = formatter.parse(str_date);
	      java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());

	      return timeStampDate;
	    } catch (ParseException e) {
	      //System.out.println("Exception :" + e);
	      return null;
	    }
	  }
}