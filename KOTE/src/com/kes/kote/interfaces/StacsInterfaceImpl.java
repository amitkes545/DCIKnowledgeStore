package com.kes.kote.interfaces;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.kes.kote.domain.StacsAssignmentTypesDomain;
import com.kes.kote.domain.StacsCOSMapDomain;
import com.kes.kote.domain.StacsCOSTSTMapDomain;
import com.kes.kote.domain.StacsCertConfigDomain;
import com.kes.kote.domain.StacsCourseCatelogDomain;
import com.kes.kote.domain.StacsCourseCreditDomain;
import com.kes.kote.domain.StacsSGPACGPADomain;
import com.kes.kote.domain.StacsSubjectsDomain;
import com.kes.kote.domain.StacsTimeTableDomain;
import com.kes.kote.domain.StacsTopicsSubTopicDomain;
import com.kes.kote.util.PropertiesUtil;
import com.kes.kote.util.SessionUtil;

public class StacsInterfaceImpl implements StacsInterface{

	@Override
	public int saveCourseCatelog(List<StacsCourseCatelogDomain> CourseCatelogDetails,HttpSession sess) {
		// TODO Auto-generated method stub
		
		SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
		
		int ret=dao.saveCourseCatelog(CourseCatelogDetails,sess);
		
		if(ret==1)
			util.setsuccessmsg(PropertiesUtil.getMessageProperty("coursecatelog.success"));
		else
			util.setfailedmsg(PropertiesUtil.getMessageProperty("coursecatelog.failed"));
			
		return ret;
	}

	@Override
	public int saveSubjects(List<StacsSubjectsDomain> SubjectsDetails,HttpSession sess) {
		// TODO Auto-generated method stub
		SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
		int status=dao.saveSubjects(SubjectsDetails,sess);
		if(status==1)
			util.setsuccessmsg(PropertiesUtil.getMessageProperty("subjects.success"));
		else
			util.setfailedmsg(PropertiesUtil.getMessageProperty("subjects.failed"));
		
		return status;
	}

	@Override
	public int saveTopicsSubTopic(List<StacsTopicsSubTopicDomain> TopicsSubTopicDetails,HttpSession sess) {
		// TODO Auto-generated method stub
		
		SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
		int status=dao.saveTopicsSubTopic(TopicsSubTopicDetails,sess);
		if(status==1)
			util.setsuccessmsg(PropertiesUtil.getMessageProperty("TST.success"));
		else
			util.setfailedmsg(PropertiesUtil.getMessageProperty("TST.failed"));
		
		return status;
	}
	
	@Override
	public int saveCOSMap(List<StacsCOSMapDomain> COSMapDetails,HttpSession sess) {
		// TODO Auto-generated method stub
		
		SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
		
		int status=dao.saveCOSMap(COSMapDetails,sess);
		if(status==1)
			util.setsuccessmsg(PropertiesUtil.getMessageProperty("COSMap.success"));
		else
			util.setfailedmsg(PropertiesUtil.getMessageProperty("COSMap.failed"));
		
		return status;
	}

	@Override
	public int saveCOSTSTMap(List<StacsCOSTSTMapDomain> COSTSTMapDetails,HttpSession sess) {
		// TODO Auto-generated method stub
		
		SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
		
		int status=dao.saveCOSTSTMap(COSTSTMapDetails,sess);
		if(status==1)
			util.setsuccessmsg(PropertiesUtil.getMessageProperty("COSTSTMap.success"));
		else
			util.setfailedmsg(PropertiesUtil.getMessageProperty("COSTSTMap.failed"));
		
		return status;
	}

	@Override
	public int saveCourseCredit(List<StacsCourseCreditDomain> CourseCreditDetails,HttpSession sess) {
		// TODO Auto-generated method stub
		
		SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
		
		int status=dao.saveCourseCredit(CourseCreditDetails,sess);
		if(status==1)
			util.setsuccessmsg(PropertiesUtil.getMessageProperty("coursecredit.success"));
		else
			util.setfailedmsg(PropertiesUtil.getMessageProperty("coursecredit.failed"));
		
		return status;
	}

	@Override
	public int saveCertConfig(List<StacsCertConfigDomain> CertConfigDetails,HttpSession sess) {
		// TODO Auto-generated method stub
		
		SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
		
		int status=dao.saveCertConfig(CertConfigDetails,sess);
		if(status==1)
			util.setsuccessmsg(PropertiesUtil.getMessageProperty("certconfig.success"));
		else
			util.setfailedmsg(PropertiesUtil.getMessageProperty("certconfig.failed"));
		
		return status;
	}

	@Override
	public int saveTimeTable(List<StacsTimeTableDomain> timeTableDetails, HttpSession sess) {
		// TODO Auto-generated method stub
		SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
		
		int status=dao.saveTimeTable(timeTableDetails,sess);
		if(status==1)
			util.setsuccessmsg(PropertiesUtil.getMessageProperty("TimeTable.success"));
		else
			util.setfailedmsg(PropertiesUtil.getMessageProperty("TimeTable.failed"));
		
		return status;
	}

	@Override
	public int saveSgpaCgpa(List<StacsSGPACGPADomain> gradeDetails, HttpSession sess) {
		// TODO Auto-generated method stub
		SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
		
		int status=dao.saveSgpaCgpa(gradeDetails,sess);
		if(status==1)
			util.setsuccessmsg(PropertiesUtil.getMessageProperty("SGPACGPA.success"));
		else
			util.setfailedmsg(PropertiesUtil.getMessageProperty("SGPACGPA.failed"));
		
		return status;
		
	}

	@Override
	public int saveAssignTypes(List<StacsAssignmentTypesDomain> assignTypeDetail, HttpSession sess) {
		// TODO Auto-generated method stub
		
		SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
		
		int status=dao.saveAssignTypes(assignTypeDetail,sess);
		if(status==1)
			util.setsuccessmsg(PropertiesUtil.getMessageProperty("AssignTypes.success"));
		else
			util.setfailedmsg(PropertiesUtil.getMessageProperty("AssignTypes.failed"));
		
		return status;
	}

}
