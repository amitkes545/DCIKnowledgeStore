package com.kes.kote.interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.kes.kote.domain.CourseInfoDomain;
import com.kes.kote.domain.CrsFeeDomain;
import com.kes.kote.domain.DepartmetInfoDomain;
import com.kes.kote.domain.DisciplineDomain;
import com.kes.kote.domain.EnrAttrCaptionsDomain;
import com.kes.kote.domain.EnrAttributesDomain;
import com.kes.kote.domain.InstFeeDomain;
import com.kes.kote.domain.LetterGradeDomain;
import com.kes.kote.domain.MailserverDomain;
import com.kes.kote.domain.MenuDomain;
import com.kes.kote.domain.ProgramDomain;
import com.kes.kote.domain.SubTopicDomain;
import com.kes.kote.domain.SubjectDomain;
import com.kes.kote.domain.TopicDomain;
import com.kes.kote.domain.UseRoleDomain;
import com.kes.kote.domain.UsersInfoDomain;

public interface LookupInterface {
	
	public List<DisciplineDomain> getDesciplineList(HttpSession sess);
	public List<ProgramDomain> getProgramList(HttpSession sess);
	public List<CourseInfoDomain> getCourseList(HttpSession sess);
	public List<DepartmetInfoDomain> getDeptList(HttpSession sess);
	public List<SubjectDomain> getSubjectsList(HttpSession sess);
	public List<TopicDomain> getTopicsList(HttpSession sess);
	public List<SubTopicDomain> getSubTopicsList(HttpSession sess);
	public List<UsersInfoDomain> getUsersListByPrivilege(String Privilege,HttpSession sess);
	public List<UsersInfoDomain> getAllUsersList(HttpSession sess);
	public List<InstFeeDomain> getInstFeeDetails(HttpSession sess);
	public List<CrsFeeDomain> getCrsFeeDetails(HttpSession sess);
	public List<MailserverDomain> getMailserverDetails(HttpSession sess);
	public List<UseRoleDomain> getRoleList(HttpSession sess);
	public List<MenuDomain> getMenuNameListByLevel(int level,HttpSession sess);
	public List<EnrAttributesDomain> getEnrAttributes(HttpSession sess);
	public List<EnrAttrCaptionsDomain> getEnrAttCaptions(HttpSession sess);
	public List<LetterGradeDomain> getLetterGrades(HttpSession sess);
	
	public HashMap<String,String> getUidMapping(HttpSession sess);
	
		
}
