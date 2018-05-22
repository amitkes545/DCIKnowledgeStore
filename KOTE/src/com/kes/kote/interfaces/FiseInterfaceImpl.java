package com.kes.kote.interfaces;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.kes.kote.domain.FiseCourseFeeMapDomain;
import com.kes.kote.domain.FiseFCSDomain;
import com.kes.kote.domain.FiseFPMDomain;
import com.kes.kote.domain.FiseFeeMapDomain;
import com.kes.kote.domain.FiseMemberFeesDomain;
import com.kes.kote.util.PropertiesUtil;
import com.kes.kote.util.SessionUtil;

public class FiseInterfaceImpl implements FiseInterface{

	@Override
	public int saveFeeMapDetails(List<FiseFeeMapDomain> FeeMapDetails,HttpSession sess) {
		// TODO Auto-generated method stub
		
		SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
		
		int status=dao.saveFeeMapDetails(FeeMapDetails,sess);
		if(status==1)
			util.setsuccessmsg(PropertiesUtil.getMessageProperty("feemap.success"));
		else
			util.setfailedmsg(PropertiesUtil.getMessageProperty("feemap.failed"));
		
		return status;
	}

	@Override
	public int saveCrsFeeMapDetails(List<FiseCourseFeeMapDomain> CrsFeeMapDetails,HttpSession sess) {
		// TODO Auto-generated method stub
		
		SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
		
		int status=dao.saveCrsFeeMapDetails(CrsFeeMapDetails,sess);
		if(status==1)
			util.setsuccessmsg(PropertiesUtil.getMessageProperty("crsfeemap.success"));
		else
			util.setfailedmsg(PropertiesUtil.getMessageProperty("crsfeemap.failed"));
		
		return status;
	}

	@Override
	public int saveMemberFeeDetails(List<FiseMemberFeesDomain> MemberFeeDetails,HttpSession sess) {
		// TODO Auto-generated method stub
		
		SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
		
		int status=dao.saveMemberFeeDetails(MemberFeeDetails,sess);
		if(status==1)
			util.setsuccessmsg(PropertiesUtil.getMessageProperty("memberfeemap.success"));
		else
			util.setfailedmsg(PropertiesUtil.getMessageProperty("memberfeemap.failed"));
		
		return status;
	}

	@Override
	public int saveFPMDetails(List<FiseFPMDomain> FPMDetails,HttpSession sess) {
		// TODO Auto-generated method stub
		
		SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
		
		int status=dao.saveFPMDetails(FPMDetails,sess);
		if(status==1)
			util.setsuccessmsg(PropertiesUtil.getMessageProperty("fpm.success"));
		else
			util.setfailedmsg(PropertiesUtil.getMessageProperty("fpm.failed"));
		
		return status;
	}

	@Override
	public int saveFCSDetails(List<FiseFCSDomain> FCSDetails,HttpSession sess) {
		// TODO Auto-generated method stub
		
		SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
		
		int status=dao.saveFCSDetails(FCSDetails,sess);
		if(status==1)
			util.setsuccessmsg(PropertiesUtil.getMessageProperty("fcs.success"));
		else
			util.setfailedmsg(PropertiesUtil.getMessageProperty("fcs.failed"));
		
		return status;
	}

}
