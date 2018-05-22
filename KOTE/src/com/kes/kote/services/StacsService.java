package com.kes.kote.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import org.apache.commons.fileupload.FileUploadException;

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
import com.kes.kote.interfaces.StacsInterface;
import com.kes.kote.interfaces.StacsInterfaceImpl;
import com.kes.kote.util.SessionUtil;

public class StacsService extends CommonService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	StacsInterface StacsIF=new StacsInterfaceImpl();
	
	String disid1="Medical";
	String disid2="Engineering";
	String disid3="Arts and Science";
	String disid4="Management";
	String disid5="Architecture";
	String disid6="Subjects";

	
	@Override
	public void run() throws ServletException, IOException, FileUploadException, Exception {
		// TODO Auto-generated method stub
		
		session=request.getSession(true);
		
		String From=request.getParameter("From");
		if(From.trim().equalsIgnoreCase("CourseCatelog"))
			actionCourseCatelog();
		else if(From.trim().equalsIgnoreCase("Subjects"))
			actionSubjects();
		else if(From.trim().equalsIgnoreCase("TopicSubTopic"))
			actionTopicSubTopic();
		else if(From.trim().equalsIgnoreCase("COSMap"))
			actionCOSMap();
		else if(From.trim().equalsIgnoreCase("COSTSTMap"))
			actionCOSTSTMap();
		else if(From.trim().equalsIgnoreCase("TimeTable"))
			actionTimeTable();
		else if(From.trim().equalsIgnoreCase("SGPACGPA"))
			actionSGPACGPA();
		else if(From.trim().equalsIgnoreCase("CourseCredit"))
			actionCourseCredit();
		else if(From.trim().equalsIgnoreCase("AssignmentType"))
			actionAssignTypes();
		else if(From.trim().equalsIgnoreCase("CertiConfig"))
			actionCertiConfig();
	}

	public void actionCourseCatelog()
	{
		try
		{
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			
			List<StacsCourseCatelogDomain> CourseCatelogDetails=new ArrayList<StacsCourseCatelogDomain>();
			int TotRow=util.getTotRow();
			for(int i=0;i<TotRow;i++)
			{
				
				String DisciplineID="DiscName"+i;
				String DisciplineName="";
				String ProgramName="ProgName"+i;
				String CourseID="CrsID"+i;
				String CourseName="CrsName"+i;
				String CourseImageName="CrsImgName"+i;
				String CourseImageSize="CrsImgSize"+i;
				String DepartmentID="DeptID"+i;
				String DepartmentName="DeptName"+i;
				String DepartmentImageName="DeptImgName"+i;
				String DepartmentImageSize="DeptImgSize"+i;
				String DurationType="DurType"+i;
				String DurationPeriod="DurPrd"+i;
				String TeachingPattern="TeachPatt"+i;
				String NoOfSessions="NoOfSess"+i;
				String CanBeDoneinParallel="CanBe"+i;
				String PAdminUID="PAdminUID"+i;
				String SAdminUID="SAdminUID"+i;
				
				DisciplineID=request.getParameter(DisciplineID);
				DisciplineName=getDiscName(DisciplineID);
				ProgramName=request.getParameter(ProgramName);
				CourseID=request.getParameter(CourseID);
				CourseName=request.getParameter(CourseName);
				CourseImageName=request.getParameter(CourseImageName);
				CourseImageSize=request.getParameter(CourseImageSize);
				DepartmentID=request.getParameter(DepartmentID);
				DepartmentName=request.getParameter(DepartmentName);
				DepartmentImageName=request.getParameter(DepartmentImageName);
				DepartmentImageSize=request.getParameter(DepartmentImageSize);
				DurationType=request.getParameter(DurationType);
				DurationPeriod=request.getParameter(DurationPeriod);
				TeachingPattern=request.getParameter(TeachingPattern);
				NoOfSessions=request.getParameter(NoOfSessions);
				CanBeDoneinParallel=request.getParameter(CanBeDoneinParallel);
				PAdminUID=request.getParameter(PAdminUID);
				SAdminUID=request.getParameter(SAdminUID);
				
				StacsCourseCatelogDomain row=new StacsCourseCatelogDomain();
				
				row.setDisciplineID(DisciplineID);
				row.setDisciplineName(DisciplineName);
				row.setProgramName(ProgramName);
				row.setCourseID(CourseID);
				row.setCourseName(CourseName);
				row.setCourseImageName(CourseImageName);
				row.setCourseImageSize(CourseImageSize);
				row.setDepartmentID(DepartmentID);
				row.setDepartmentName(DepartmentName);
				row.setDepartmentImageName(DepartmentImageName);
				row.setDepartmentImageSize(DepartmentImageSize);
				row.setDurationType(DurationType);
				row.setDurationPeriod(DurationPeriod);
				row.setTeachingPattern(TeachingPattern);
				row.setNoOfSessions(NoOfSessions);
				row.setCanBeDoneinParallel(CanBeDoneinParallel);
				row.setPAdminUID(PAdminUID);
				row.setSAdminUID(SAdminUID);
				
				//SessionUtil.print(i+" = "+row.isValid());
				if(row.isValid())
					{
						// util.print(row.toString());
						CourseCatelogDetails.add(row);
					}
				
			}
			
			int status=StacsIF.saveCourseCatelog(CourseCatelogDetails,session);
			if(status==1)
				response.sendRedirect("../JSP/cors-course-subjects-stacs.jsp");
			else
				response.sendRedirect("../JSP/cacs-course-catalog-system-stacs.jsp");
		}catch(Exception ex){ex.printStackTrace();}
	}
	String getDiscName(String DiscID)
	{
		//System.out.println(DiscID);
		if(DiscID.trim().equalsIgnoreCase("disid1")) return disid1;
		else if(DiscID.trim().equalsIgnoreCase("disid2")) return disid2;
		else if(DiscID.trim().equalsIgnoreCase("disid3")) return disid3;
		else if(DiscID.trim().equalsIgnoreCase("disid4")) return disid4;
		else if(DiscID.trim().equalsIgnoreCase("disid5")) return disid5;
		else if(DiscID.trim().equalsIgnoreCase("disid6")) return disid6;
		
		return "";
	}
	public void actionSubjects()
	{
		List<StacsSubjectsDomain> SubjectsDetails=new ArrayList<StacsSubjectsDomain>();
		
		try
		{
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			
			int TotRow=util.getTotRow();
			for(int i=0;i<TotRow;i++)
			{
				
				String SubjectId="SubID"+i;
				String SubjectName="SubName"+i;
				
				SubjectId=request.getParameter(SubjectId);
				SubjectName=request.getParameter(SubjectName);
				
				
				StacsSubjectsDomain row=new StacsSubjectsDomain();
				row.setSubjectId(SubjectId);
				row.setSubjectName(SubjectName);
				
				if(row.isValid())
					{
						//util.print(row.toString());
						SubjectsDetails.add(row);
					}
				
			}
			int status=StacsIF.saveSubjects(SubjectsDetails,session);
			if(status==1)
				response.sendRedirect("../JSP/tops-topics-and-sub-topics-stacs.jsp");
			else
				response.sendRedirect("../JSP/cors-course-subjects-stacs.jsp");
			
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public void actionTopicSubTopic()
	{
		try
		{
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			
			List<StacsTopicsSubTopicDomain> TopicsSubTopicDetails=new ArrayList<StacsTopicsSubTopicDomain>();
			
			int TotRow=util.getTotRow();
			for(int i=0;i<TotRow;i++)
			{
				String SubjectId="SubID"+i;
				String TopicId="TopicID"+i;
				String TopicName="TopicName"+i;
				String SubTopicId="SubTopicID"+i;
				String SubTopicName="SubTopicName"+i;
				
				SubjectId=request.getParameter(SubjectId);
				TopicId=request.getParameter(TopicId);
				TopicName=request.getParameter(TopicName);
				SubTopicId=request.getParameter(SubTopicId);
				SubTopicName=request.getParameter(SubTopicName);
				
				StacsTopicsSubTopicDomain row=new StacsTopicsSubTopicDomain();
				row.setSubjectId(SubjectId);
				row.setTopicId(TopicId);
				row.setTopicName(TopicName);
				row.setSubTopicId(SubTopicId);
				row.setSubTopicName(SubTopicName);
			
				if(row.isValid())
				{
					//util.print(row.toString());
					TopicsSubTopicDetails.add(row);
				}
			}
			
			int status=StacsIF.saveTopicsSubTopic(TopicsSubTopicDetails,session);
			if(status==1)
				response.sendRedirect("../JSP/cosmap-course-subject-mapping-stacs.jsp");
			else
				response.sendRedirect("../JSP/tops-topics-and-sub-topics-stacs.jsp");
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public void actionCOSMap()
	{
		try
		{
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			
			List<StacsCOSMapDomain> COSMapDetails=new ArrayList<StacsCOSMapDomain>();
			int TotRow=util.getTotRow();
			for(int i=0;i<TotRow;i++)
			{
				String DisciplineName="DiscName"+i;
				String ProgramName="ProgName"+i;
				String CourseID="CrsID"+i;
				String CourseName="CrsName"+i;
				String DepartmentID="DeptID"+i;
				String DepartmentName="DeptName"+i;
				String SubjectId="SubID"+i;
				String SubjectName="SubName"+i;
				String SemesterID="SemID"+i;
				String SemesterStartDate="SemSDate"+i;
				String SemesterEndDate="SemEDate"+i;
				
				DisciplineName=request.getParameter(DisciplineName);
				ProgramName=request.getParameter(ProgramName);
				CourseID=request.getParameter(CourseID);
				CourseName=request.getParameter(CourseName);
				DepartmentID=request.getParameter(DepartmentID);
				DepartmentName=request.getParameter(DepartmentName);
				SubjectId=request.getParameter(SubjectId);
				SubjectId=SubjectId.split("~")[0];
				SubjectName=request.getParameter(SubjectName);
				if(SubjectName.trim().equalsIgnoreCase("/"))
					SubjectName="";
				SemesterID=request.getParameter(SemesterID);
				SemesterStartDate=request.getParameter(SemesterStartDate);
				SemesterEndDate=request.getParameter(SemesterEndDate);
				
				StacsCOSMapDomain row=new StacsCOSMapDomain();
				row.setDisciplineName(DisciplineName);
				row.setProgramName(ProgramName);
				row.setCourseID(CourseID);
				row.setCourseName(CourseName);
				row.setDepartmentID(DepartmentID);
				row.setDepartmentName(DepartmentName);
				row.setSubjectId(SubjectId);
				row.setSubjectName(SubjectName);
				row.setSemesterID(SemesterID);
				row.setSemesterStartDate(SemesterStartDate);
				row.setSemesterEndDate(SemesterEndDate);
				
				if(row.isValid())
				{
					//util.print(row.toString());
					COSMapDetails.add(row);
				}
			}
			int status=StacsIF.saveCOSMap(COSMapDetails,session);
			if(status==1)
				response.sendRedirect("../JSP/costst-course-subject-topic-and-sub-topic-mapping-stacs.jsp");
			else
				response.sendRedirect("../JSP/cosmap-course-subject-mapping-stacs.jsp");
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public void actionCOSTSTMap()
	{
		try
		{
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			
			List<StacsCOSTSTMapDomain> COSTSTMapDetails=new ArrayList<StacsCOSTSTMapDomain>();
			int TotRow=util.getTotRow();
			for(int i=0;i<TotRow;i++)
			{
				String DisciplineName="DiscName"+i;
				String ProgramName="ProgName"+i;
				String CourseID="CrsID"+i;
				String CourseName="CrsName"+i;
				String DepartmentID="DeptID"+i;
				String DepartmentName="DeptName"+i;
				String SubjectId="SubID"+i;
				String SubjectName="SubName"+i;
				String TopicId="TopicID"+i;
				String TopicName="TopicName"+i;
				String SubTopicId="SubTopicID"+i;
				String SubTopicName="SubTopicName"+i;
				String FacultyUID="FacultyUID"+i;
				
				DisciplineName=request.getParameter(DisciplineName);
				ProgramName=request.getParameter(ProgramName);
				CourseID=request.getParameter(CourseID);
				CourseName=request.getParameter(CourseName);
				DepartmentID=request.getParameter(DepartmentID);
				DepartmentName=request.getParameter(DepartmentName);
				SubjectId=request.getParameter(SubjectId);
				SubjectName=request.getParameter(SubjectName);
				TopicId=request.getParameter(TopicId);
				TopicName=request.getParameter(TopicName);
				SubTopicId=request.getParameter(SubTopicId);
				SubTopicName=request.getParameter(SubTopicName);
				FacultyUID=request.getParameter(FacultyUID);
				
				
				StacsCOSTSTMapDomain row=new StacsCOSTSTMapDomain();
				row.setDisciplineName(DisciplineName);
				row.setProgramName(ProgramName);
				row.setCourseID(CourseID);
				row.setCourseName(CourseName);
				row.setDepartmentID(DepartmentID);
				row.setDepartmentName(DepartmentName);
				row.setSubjectId(SubjectId);
				row.setSubjectName(SubjectName);
				row.setTopicId(TopicId);
				row.setTopicName(TopicName);
				row.setSubTopicId(SubTopicId);
				row.setSubTopicName(SubTopicName);
				row.setFacultyUID(FacultyUID);
				if(row.isValid())
					COSTSTMapDetails.add(row);
			}
			int status=StacsIF.saveCOSTSTMap(COSTSTMapDetails,session);
			if(status==1)
				response.sendRedirect("../JSP/timetable-stacs.jsp");
			else
				response.sendRedirect("../JSP/costst-course-subject-topic-and-sub-topic-mapping-stacs.jsp");
			
		}catch(Exception ex){ex.printStackTrace();}
		
	}
	
	public void actionTimeTable()
	{
		try
		{
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			
			List<StacsTimeTableDomain> timeTableDetails=new ArrayList<StacsTimeTableDomain>();
			int TotRow=util.getTotRow();
			for(int i=0;i<TotRow;i++)
			{
				String DisciplineName="DiscName"+i;
				String ProgramName="ProgName"+i;
				String CourseID="CrsID"+i;
				String CourseName="CrsName"+i;
				String DepartmentID="DeptID"+i;
				String DepartmentName="DeptName"+i;
				String SubjectId="SubID"+i;
				String SubjectName="SubName"+i;
				String TopicId="TopicID"+i;
				String TopicName="TopicName"+i;
				String SubTopicId="SubTopicID"+i;
				String SubTopicName="SubTopicName"+i;
				String FacultyUID="FacultyUID"+i;
				
				String sessionName="sessionName"+i;
				String sessionsDate="sessionsDate"+i;
				String sessioneDate="sessioneDate"+i;
				String sessionDuration="sessionDuration"+i;
				String sessionDocu="sessionDocu"+i;
				
				DisciplineName=request.getParameter(DisciplineName);
				ProgramName=request.getParameter(ProgramName);
				CourseID=request.getParameter(CourseID);
				CourseName=request.getParameter(CourseName);
				DepartmentID=request.getParameter(DepartmentID);
				DepartmentName=request.getParameter(DepartmentName);
				SubjectId=request.getParameter(SubjectId);
				SubjectName=request.getParameter(SubjectName);
				TopicId=request.getParameter(TopicId);
				TopicName=request.getParameter(TopicName);
				SubTopicId=request.getParameter(SubTopicId);
				SubTopicName=request.getParameter(SubTopicName);
				FacultyUID=request.getParameter(FacultyUID);
				
				sessionName=request.getParameter(sessionName);
				sessionsDate=request.getParameter(sessionsDate);
				sessioneDate=request.getParameter(sessioneDate);
				sessionDuration=request.getParameter(sessionDuration);
				sessionDocu=request.getParameter(sessionDocu);
				
				StacsTimeTableDomain row=new StacsTimeTableDomain();
				row.setDisciplineName(DisciplineName);
				row.setProgramName(ProgramName);
				row.setCourseID(CourseID);
				row.setCourseName(CourseName);
				row.setDepartmentID(DepartmentID);
				row.setDepartmentName(DepartmentName);
				row.setSubjectId(SubjectId);
				row.setSubjectName(SubjectName);
				row.setTopicId(TopicId);
				row.setTopicName(TopicName);
				row.setSubTopicId(SubTopicId);
				row.setSubTopicName(SubTopicName);
				row.setFacultyUID(FacultyUID);
				
				row.setSessionName(sessionName);
				row.setSessionStartDateTime(sessionsDate);
				row.setSessionEndDateTime(sessioneDate);
				row.setSessionDuration(sessionDuration);
				row.setSessionMaterial(sessionDocu);
				
				if(row.isValid())
					timeTableDetails.add(row);
			}
			int status=StacsIF.saveTimeTable(timeTableDetails,session);
			if(status==1)
				response.sendRedirect("../JSP/gpa-letter-grade-stacs.jsp"); // cocr-course-credit-system-stacs.jsp
			else
				response.sendRedirect("../JSP/timetable-stacs.jsp");
			
		}catch(Exception ex){ex.printStackTrace();}
		
	}
	
	public void actionSGPACGPA()
	{
		try
		{
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			
			List<StacsSGPACGPADomain> gradesDetails=new ArrayList<StacsSGPACGPADomain>();
			int TotRow=util.getTotRow();
			for(int i=0;i<TotRow;i++)
			{
				
				String letterGrade="LG"+i;
        		String description="Desc"+i;
        		String gradePoints="GP"+i;
        		String MinRange="Min"+i;
        		String MaxRange="Max"+i;
        		
        		letterGrade=request.getParameter(letterGrade);
        		description=request.getParameter(description);
        		gradePoints=request.getParameter(gradePoints);
        		MinRange=request.getParameter(MinRange);
        		MaxRange=request.getParameter(MaxRange);
        		
        		StacsSGPACGPADomain row=new StacsSGPACGPADomain();
        		row.setLetterGrade(letterGrade);
        		row.setDescription(description);
        		row.setGradePoints(gradePoints);
        		row.setMinRange(MinRange);
        		row.setMaxRange(MaxRange);
        		
        		if(row.isValid())
        			gradesDetails.add(row);
        		
			}
			int status=StacsIF.saveSgpaCgpa(gradesDetails, session);
			if(status==1)
				response.sendRedirect("../JSP/cocr-course-credit-system-stacs.jsp");
			else
				response.sendRedirect("../JSP/gpa-letter-grade-stacs.jsp");
					
			
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public void actionCourseCredit()
	{
		try
		{
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			
			List<StacsCourseCreditDomain> CourseCreditDetails=new ArrayList<StacsCourseCreditDomain>();
			int TotRow=util.getTotRow();
			for(int i=0;i<TotRow;i++)
			{
				String DisciplineName="DiscName"+i;
				String ProgramName="ProgName"+i;
				String CourseID="CrsID"+i;
				String DepartmentID="DeptID"+i;
				String Semester="Semester"+i;
				String SubjectID="SubID"+i;
				String SessionMax="SMax"+i;
				String TheoryMax="TMax"+i;
				String PracticalMax="PMax"+i;
				String SessionMin="SMin"+i;
				String TheoryMin="TMin"+i;
				String PracticalMin="PMin"+i;
				String CHRK="CreditHours"+i;
				
				DisciplineName=request.getParameter(DisciplineName);
				ProgramName=request.getParameter(ProgramName);
				CourseID=request.getParameter(CourseID);
				DepartmentID=request.getParameter(DepartmentID);
				Semester=request.getParameter(Semester);
				SubjectID=request.getParameter(SubjectID);
				SessionMax=request.getParameter(SessionMax);
				TheoryMax=request.getParameter(TheoryMax);
				PracticalMax=request.getParameter(PracticalMax);
				SessionMin=request.getParameter(SessionMin);
				TheoryMin=request.getParameter(TheoryMin);
				PracticalMin=request.getParameter(PracticalMin);
				CHRK=request.getParameter(CHRK);
				
				StacsCourseCreditDomain row =new StacsCourseCreditDomain();
				row.setDisciplineName(DisciplineName);
				row.setProgramName(ProgramName);
				row.setCourseID(CourseID);
				row.setDepartmentID(DepartmentID);
				row.setSemester(Semester);
				row.setSubjectID(SubjectID);
				row.setSessionMax(SessionMax);
				row.setTheoryMax(TheoryMax);
				row.setPracticalMax(PracticalMax);
				row.setSessionMin(SessionMin);
				row.setTheoryMin(TheoryMin);
				row.setPracticalMin(PracticalMin);
				row.setCreditHours(CHRK);
				
				if(row.isValid())
					CourseCreditDetails.add(row);
				
			}
			int status=StacsIF.saveCourseCredit(CourseCreditDetails,session);
			if(status==1)
				response.sendRedirect("../JSP/assignment-types-stacs.jsp");
			else
				response.sendRedirect("../JSP/cocr-course-credit-system-stacs.jsp");
			
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public void actionAssignTypes()
	{
		try
		{
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			
			List<StacsAssignmentTypesDomain> AssignTypesDetails=new ArrayList<StacsAssignmentTypesDomain>();
			int TotRow=util.getTotRow();
			for(int i=0;i<TotRow;i++)
			{
				String typeId="typeId"+i;
				String typeDesc="typeDesc"+i;
				String applyStatus="applyStatus"+i;
				String instDesc="instDesc"+i;
				
				
				typeId=request.getParameter(typeId);
				typeDesc=request.getParameter(typeDesc);
				applyStatus=request.getParameter(applyStatus);
				String instDescVal=request.getParameter(instDesc);
				
				
				StacsAssignmentTypesDomain row =new StacsAssignmentTypesDomain();
				row.setTypeId(typeId);
				row.setTypeDesc(typeDesc);
				row.setApplyStatus(applyStatus);
				if(instDescVal!=null && instDescVal.trim().length()>0)
					row.setTypeDesc(instDescVal);
				
				if(row.isValid())
					AssignTypesDetails.add(row);
				
			}
			int status= StacsIF.saveAssignTypes(AssignTypesDetails,session);
			if(status==1)
				response.sendRedirect("../JSP/ccse-course-certificate-setup-stacs.jsp");
			else
				response.sendRedirect("../JSP/assignment-types-stacs.jsp");
			
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public void actionCertiConfig()
	{
		try
		{
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			
			List<StacsCertConfigDomain> CertConfigDetails=new ArrayList<StacsCertConfigDomain>();
			int TotRow=util.getTotRow();
			for(int i=0;i<TotRow;i++)
			{
				String CourseID="CourseID"+i;
				String GreetingText="GText"+i;
				String BodyText="BText"+i;
				String WishingText="WText"+i;
				String FooterText="FText"+i;
				String AuthorizedSign="ASign"+i;
				String DigitalSign="DSign"+i;
				String CertificateSize="CSize"+i;
				String CertificateLayout="CLayout"+i;
				String BorderImage="BImage"+i;
				String CornerImage="CImage"+i;
				String WaterMarkImage="WImage"+i;
				
				CourseID=request.getParameter(CourseID);
				GreetingText=request.getParameter(GreetingText);
				BodyText=request.getParameter(BodyText);
				WishingText=request.getParameter(WishingText);
				FooterText=request.getParameter(FooterText);
				AuthorizedSign=request.getParameter(AuthorizedSign);
				DigitalSign=request.getParameter(DigitalSign);
				CertificateSize=request.getParameter(CertificateSize);
				CertificateLayout=request.getParameter(CertificateLayout);
				BorderImage=request.getParameter(BorderImage);
				CornerImage=request.getParameter(CornerImage);
				WaterMarkImage=request.getParameter(WaterMarkImage);
				
				StacsCertConfigDomain row=new StacsCertConfigDomain();
				row.setCourseID(CourseID);
				row.setGreetingText(GreetingText);
				row.setBodyText(BodyText);
				row.setWishingText(WishingText);
				row.setFooterText(FooterText);
				row.setAuthorizedSign(AuthorizedSign);
				row.setDigitalSign(DigitalSign);
				row.setCertificateSize(CertificateSize);
				row.setCertificateLayout(CertificateLayout);
				row.setBorderImage(BorderImage);
				row.setCornerImage(CornerImage);
				row.setWaterMarkImage(WaterMarkImage);
				if(row.isValid())
				{
					//util.print(row.toString());
					CertConfigDetails.add(row);	
				}
				
				
			}
			int status=StacsIF.saveCertConfig(CertConfigDetails,session);
			if(status==1)
				response.sendRedirect("../JSP/fms-institution-fee-mapping-fise.jsp");
			else
				response.sendRedirect("../JSP/ccse-course-certificate-setup-stacs.jsp");
		}catch(Exception ex){ex.printStackTrace();}
	}
}
