package com.kes.kote.interfaces;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.kes.kote.dao.StampDao;
import com.kes.kote.domain.StampCMTDomain;
import com.kes.kote.domain.StampEPWorkFlowDomain;
import com.kes.kote.domain.StampEnrollFormDomain;
import com.kes.kote.domain.StampNMTDomain;

public interface StampInterface {
	
	StampDao dao=new StampDao();
	
	public int saveEPWrokFlow(ArrayList<StampEPWorkFlowDomain> EPWorkFlowDetails,HttpSession sess);
	
	public int saveEnrollForm(ArrayList<StampEnrollFormDomain> enrollFormDetails,HttpSession sess);
	
	public int saveCMT(ArrayList<StampCMTDomain> CMTDetails,HttpSession sess);
	
	public int saveNMT(ArrayList<StampNMTDomain> NMTDetails,HttpSession sess);
	
}
