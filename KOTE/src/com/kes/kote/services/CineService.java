package com.kes.kote.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import org.apache.commons.fileupload.FileUploadException;

import com.kes.kote.domain.CineOrgDetailsDomain;
import com.kes.kote.domain.CineSpocsDomain;
import com.kes.kote.interfaces.CineInterface;
import com.kes.kote.interfaces.CineInterfaceImpl;
import com.kes.kote.services.CommonService;
import com.kes.kote.util.SessionUtil;

public class CineService extends CommonService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	CineInterface orgIF=new CineInterfaceImpl();
	
	@Override
	public void run() throws ServletException, IOException, FileUploadException, Exception {
		// TODO Auto-generated method stub
		
		session=request.getSession(true);
		
		String From=request.getParameter("From").toString().trim();
		if(From.trim().equalsIgnoreCase("OrgDetails"))
			actionOrgDetails();
		else if(From.trim().equalsIgnoreCase("SpocsDetails"))
			actionSpocsDetails();
	}

	void actionOrgDetails()
	{
		try
		{
			
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			
			String name=request.getParameter("fname").toString().trim();
			String lName=request.getParameter("lname").toString().trim();
			String address=request.getParameter("address").toString().trim();
			String Website=request.getParameter("website").toString().trim();
			String logo=request.getParameter("logonm").toString().trim();
			String lastyear=request.getParameter("yearestd").toString().trim();
			String country=request.getParameter("country").toString().trim();
			String state=request.getParameter("state").toString().trim();
			String City=request.getParameter("city").toString().trim();
			String Pincode=request.getParameter("Pincode").toString().trim();
			String ksspacesize=request.getParameter("ksspacesize").toString().trim();
			String spaceuom=request.getParameter("spaceuom").toString().trim();
			String currencycode=request.getParameter("currencycode").toString().trim();
			String dateft=request.getParameter("dateft").toString().trim();
			
			CineOrgDetailsDomain domain=new CineOrgDetailsDomain();
			domain.setOrgName(name);
			domain.setOrgLastName(lName);
			domain.setAddress(address);
			domain.setWebsite(Website);
			domain.setLogoName(logo);
			domain.setYearofEst(lastyear);
			domain.setCountry(country);
			domain.setState(state);
			domain.setCity(City);
			domain.setPinCode(Pincode);
			domain.setKsSpace(ksspacesize);
			domain.setSpaceUOM(spaceuom);
			domain.setCurrencyCode(currencycode);
			domain.setDateFormat(dateft);
			//util.print(domain.toString());
			
			util.setOrgAddress(address);
			
			int status=orgIF.saveOrgDetails(domain,session);
			if(status==1)
				response.sendRedirect("../JSP/cine-spocs.jsp");
			else
				response.sendRedirect("../JSP/cine-setup-middle-layer.jsp");
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	void actionSpocsDetails()
	{
		try
		{
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			
			int TotRow=util.getTotRow();
			//SessionUtil.print("actionSpocsDetails  : "+ TotRow);
			ArrayList<CineSpocsDomain> spocDetails =new ArrayList<CineSpocsDomain>();
			if(TotRow>0)
			{
				for(int i=0;i<TotRow;i++)
				{
					String deptnm="deptnm"+i;
					String spocnm="spocnm"+i;
					String desig="desig"+i;
					String mobnumbr="mobnumbr"+i;
					String llnumbr="llnumbr"+i;
					String extnumbr="extnumbr"+i;
					String faxnumbr="faxnumbr"+i;
					String emladdr="emladdr"+i;
					
					String Dept=request.getParameter(deptnm);
					String Name=request.getParameter(spocnm);
					String Design=request.getParameter(desig);
					String Mobile=request.getParameter(mobnumbr);
					String LLno=request.getParameter(llnumbr);
					String Ext=request.getParameter(extnumbr);
					String Fax=request.getParameter(faxnumbr);
					String Email=request.getParameter(emladdr);
					
					
					CineSpocsDomain domain=new CineSpocsDomain();
					
					domain.setDeptName(Dept);
					domain.setSPOCName(Name);
					domain.setDisgn(Design);
					domain.setMobNo(Mobile);
					domain.setLLNo(LLno);
					domain.setExtNo(Ext);
					domain.setFaxNo(Fax);
					domain.setEmail(Email);
					
					if(domain.isValid())
						spocDetails.add(domain);
				}
				
				
			}
			
			//util.print("spocDetails size == "+spocDetails.size());
			int status=orgIF.saveSpocDetails(spocDetails,session);
			if(status==1)
				response.sendRedirect("../JSP/user-system-parameters-use.jsp");
			else
				response.sendRedirect("../JSP/cine-spocs.jsp");
			
		}catch(Exception ex){ex.printStackTrace();}
	}
}
