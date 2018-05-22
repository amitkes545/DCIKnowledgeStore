package com.kes.kote.interfaces;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.kes.kote.dao.StacsDao;
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

public interface StacsInterface {
	
	StacsDao dao=new StacsDao();

	public abstract int saveCourseCatelog(List<StacsCourseCatelogDomain> CourseCatelogDetails,HttpSession sess);
	
	public abstract int saveSubjects(List<StacsSubjectsDomain> SubjectsDetails,HttpSession sess);
	
	public abstract int saveCOSMap(List<StacsCOSMapDomain> COSMapDetails,HttpSession sess);
	
	public abstract int saveTopicsSubTopic(List<StacsTopicsSubTopicDomain> TopicsSubTopicDetails,HttpSession sess);
	
	public abstract int saveCOSTSTMap(List<StacsCOSTSTMapDomain> COSTSTMapDetails,HttpSession sess);
	
	public abstract int saveTimeTable(List<StacsTimeTableDomain> COSTSTMapDetails,HttpSession sess);
	
	public abstract int saveSgpaCgpa(List<StacsSGPACGPADomain> gradeDetails,HttpSession sess);
	
	public abstract int saveCourseCredit(List<StacsCourseCreditDomain> CourseCreditDetails,HttpSession sess);
	
	public abstract int saveAssignTypes(List<StacsAssignmentTypesDomain> assignTypeDetail,HttpSession sess);
	
	public abstract int saveCertConfig(List<StacsCertConfigDomain> CertConfigDetails,HttpSession sess);
}
