package com.kes.kote.interfaces;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.kes.kote.dao.CineDao;
import com.kes.kote.domain.CineOrgDetailsDomain;
import com.kes.kote.domain.CineSpocsDomain;

public interface CineInterface {
	
	CineDao dao=new CineDao();
	
	public abstract int saveOrgDetails(CineOrgDetailsDomain domain,HttpSession sess);
	public abstract int saveSpocDetails(ArrayList<CineSpocsDomain> spocDetails,HttpSession sess);

}
