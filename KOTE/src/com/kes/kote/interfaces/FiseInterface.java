package com.kes.kote.interfaces;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.kes.kote.dao.FiseDao;
import com.kes.kote.domain.FiseCourseFeeMapDomain;
import com.kes.kote.domain.FiseFCSDomain;
import com.kes.kote.domain.FiseFPMDomain;
import com.kes.kote.domain.FiseFeeMapDomain;
import com.kes.kote.domain.FiseMemberFeesDomain;

public interface FiseInterface {
	
	FiseDao dao=new FiseDao();
	
	public int saveFeeMapDetails(List<FiseFeeMapDomain> ret,HttpSession sess);
	public int saveCrsFeeMapDetails(List<FiseCourseFeeMapDomain> ret,HttpSession sess);
	public int saveMemberFeeDetails(List<FiseMemberFeesDomain> ret,HttpSession sess);
	public int saveFPMDetails(List<FiseFPMDomain> ret,HttpSession sess);
	public int saveFCSDetails(List<FiseFCSDomain> ret,HttpSession sess); 
	
	
}
