package com.kes.kote.interfaces;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.kes.kote.domain.CineOrgDetailsDomain;
import com.kes.kote.domain.CineSpocsDomain;
import com.kes.kote.util.PropertiesUtil;
import com.kes.kote.util.SessionUtil;

public class CineInterfaceImpl implements CineInterface{
	
	
	@Override
	public int saveOrgDetails(CineOrgDetailsDomain domain,HttpSession sess)
	{
		int ret=0;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			ret=dao.saveOrgDetails(domain,sess);
			if(ret==1)
				util.setsuccessmsg(PropertiesUtil.getMessageProperty("orgdetails.success"));
			else
				util.setfailedmsg(PropertiesUtil.getMessageProperty("orgdetails.failed"));
		}catch(Exception ex){ex.printStackTrace();}
		return ret;
	}
	
	@Override
	public int saveSpocDetails(ArrayList<CineSpocsDomain> spocDetails,HttpSession sess)
	{
		int ret=0;
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			ret=dao.saveSpocDetails(spocDetails,sess);
			if(ret==1)
				util.setsuccessmsg(PropertiesUtil.getMessageProperty("spocs.success"));
			else
				util.setfailedmsg(PropertiesUtil.getMessageProperty("spocs.failed"));
			
		}catch(Exception ex){ex.printStackTrace();}
		return ret;
	}
}
