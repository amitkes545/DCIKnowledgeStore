package com.kes.kote.interfaces;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.kes.kote.domain.StampCMTDomain;
import com.kes.kote.domain.StampEPWorkFlowDomain;
import com.kes.kote.domain.StampEnrollFormDomain;
import com.kes.kote.domain.StampNMTDomain;
import com.kes.kote.util.PropertiesUtil;
import com.kes.kote.util.SessionUtil;

public class StampInterfaceImpl implements StampInterface{

		
	@Override
	public int saveEPWrokFlow(ArrayList<StampEPWorkFlowDomain> EPWorkFlowDetails,HttpSession sess) {
		// TODO Auto-generated method stub
		SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
		int status=dao.saveEPWrokFlow(EPWorkFlowDetails,sess);
		if(status==1)
			util.setsuccessmsg(PropertiesUtil.getMessageProperty("EPWF.success"));
		else
			util.setfailedmsg(PropertiesUtil.getMessageProperty("EPWF.failed"));
		
		return status;
	}

	@Override
	public int saveEnrollForm(ArrayList<StampEnrollFormDomain> enrollFormDetails,HttpSession sess) {
		// TODO Auto-generated method stub
		
		SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
		int status=dao.saveEnrollForm(enrollFormDetails,sess);
		if(status==1)
			util.setsuccessmsg(PropertiesUtil.getMessageProperty("EnrForm.success"));
		else
			util.setfailedmsg(PropertiesUtil.getMessageProperty("EnrForm.failed"));
		
		return status;
	}
	
	@Override
	public int saveCMT(ArrayList<StampCMTDomain> CMTDetails,HttpSession sess) {
		// TODO Auto-generated method stub
		SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
		int status=dao.saveCMT(CMTDetails,sess);
		if(status==1)
			util.setsuccessmsg(PropertiesUtil.getMessageProperty("CMT.success"));
		else
			util.setfailedmsg(PropertiesUtil.getMessageProperty("CMT.failed"));
		return status;
	}

	@Override
	public int saveNMT(ArrayList<StampNMTDomain> NMTDetails,HttpSession sess) {
		// TODO Auto-generated method stub
		SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
		
		int status=dao.saveNMT(NMTDetails,sess);
		if(status==1)
			util.setsuccessmsg(PropertiesUtil.getMessageProperty("NMT.success"));
		else
			util.setfailedmsg(PropertiesUtil.getMessageProperty("NMT.failed"));
		
		return status;
	}

	

}
