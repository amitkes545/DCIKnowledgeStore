package com.kes.kote.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.kes.kote.domain.*;

public class StacsExcelReading {
	
	static ReadExcelBySheetName excelRead=new ReadExcelBySheetName();
	
	public static List<StacsCourseCatelogDomain> getcourseCatelogDetails(HttpSession sess)
	{
		List<StacsCourseCatelogDomain> ret=new ArrayList<StacsCourseCatelogDomain>();
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			String ManualorUpload=util.getConfigLayerbyManualorUpload();
			
			if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.UPLOAD))
			{
				excelRead.processBySheet(ExcelSheetUtil.COURSECATALOG,sess);
				
				ArrayList<HashMap<String,String>> info=util.getSheetDetails();
				if(info!=null && info.size()>0)
				{
					for(int i=0;i<info.size();i++)
					{
						HashMap<String,String> rowinfo=info.get(i);
						String[] cols=util.getSheetCols();
						
						String DisciplineName=rowinfo.get(cols[0]);
						String ProgramName=rowinfo.get(cols[1]);
						String CourseID=rowinfo.get(cols[2]);
						String CourseName=rowinfo.get(cols[3]);
						String CourseImageName=rowinfo.get(cols[4]);
						String CourseImageSize=rowinfo.get(cols[5]);
						String DepartmentID=rowinfo.get(cols[6]);
						String DepartmentName=rowinfo.get(cols[7]);
						String DepartmentImageName=rowinfo.get(cols[8]);
						String DepartmentImageSize=rowinfo.get(cols[9]);
						String DurationType=rowinfo.get(cols[10]);
						String DurationPeriod=rowinfo.get(cols[11]);
						String TeachingPattern=rowinfo.get(cols[12]);
						String NoOfSessions=rowinfo.get(cols[13]);
						String CanBeDoneinParallel=rowinfo.get(cols[14]);
						String PAdminUID=rowinfo.get(cols[15]);
						String SAdminUID=rowinfo.get(cols[16]);
						
						StacsCourseCatelogDomain row=new StacsCourseCatelogDomain();
						
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
						
						ret.add(row);
						
						
					}
				}
			}else
			{
				int spocsRowCount=ExcelSheetUtil.COURSECATALOGROWCOUNT-1;
				for(int i=0;i<spocsRowCount;i++)
				{
					StacsCourseCatelogDomain row=new StacsCourseCatelogDomain();
					ret.add(row);
				}
				util.setTotRow(spocsRowCount);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		
		return ret;
	}
	
	public static List<StacsSubjectsDomain> getSubjectDetails(HttpSession sess)
	{
		List<StacsSubjectsDomain> ret=new ArrayList<StacsSubjectsDomain>();
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			String ManualorUpload=util.getConfigLayerbyManualorUpload();
			
			if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.UPLOAD))
			{
				
				excelRead.processBySheet(ExcelSheetUtil.SUBJECTS,sess);
				
				ArrayList<HashMap<String,String>> info=util.getSheetDetails();
				if(info!=null && info.size()>0)
				{
					for(int i=0;i<info.size();i++)
					{
						HashMap<String,String> rowinfo=info.get(i);
						String[] cols=util.getSheetCols();
						
						String SubjectId=rowinfo.get(cols[0]);
						String SubjectName=rowinfo.get(cols[1]);
						
						StacsSubjectsDomain row=new StacsSubjectsDomain();
						row.setSubjectId(SubjectId);
						row.setSubjectName(SubjectName);
						
						ret.add(row);
						
					}
				}
			}else
			{
				int spocsRowCount=ExcelSheetUtil.SUBJECTSROWCOUNT-1;
				for(int i=0;i<spocsRowCount;i++)
				{
					StacsSubjectsDomain row=new StacsSubjectsDomain();
					ret.add(row);
				}
				util.setTotRow(spocsRowCount);
			}
		}catch(Exception ex){ex.printStackTrace();}
		return ret;
	}
	
	public static List<StacsCOSMapDomain> getCOSMapDetails(HttpSession sess)
	{
		List<StacsCOSMapDomain> ret=new ArrayList<StacsCOSMapDomain>();
		try
		{
			
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			String ManualorUpload=util.getConfigLayerbyManualorUpload();
			
			if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.UPLOAD))
			{
				ReadExcelBySheetName excelRead=new ReadExcelBySheetName();
				excelRead.processBySheet(ExcelSheetUtil.COSMAP,sess);
				
				ArrayList<HashMap<String,String>> info=util.getSheetDetails();
				if(info!=null && info.size()>0)
				{
					for(int i=0;i<info.size();i++)
					{
						HashMap<String,String> rowinfo=info.get(i);
						String[] cols=util.getSheetCols();
						
						String DisciplineName=rowinfo.get(cols[0]);
						String ProgramName=rowinfo.get(cols[1]);
						String CourseID=rowinfo.get(cols[2]);
						String CourseName=rowinfo.get(cols[3]);
						String DepartmentID=rowinfo.get(cols[4]);
						String DepartmentName=rowinfo.get(cols[5]);
						String SubjectId=rowinfo.get(cols[6]);
						String SubjectName=rowinfo.get(cols[7]);
						String SemesterID=rowinfo.get(cols[8]);
						String SemesterStartDate=rowinfo.get(cols[9]);
						String SemesterEndDate=rowinfo.get(cols[10]);
						
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
						
						ret.add(row);
						
					}
				}
			}else
			{
				int spocsRowCount=ExcelSheetUtil.COSMAPROWCOUNT-1;
				for(int i=0;i<spocsRowCount;i++)
				{
					StacsCOSMapDomain row=new StacsCOSMapDomain();
					ret.add(row);
				}
				util.setTotRow(spocsRowCount);	
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		return ret;
	}
	
	public static List<StacsTopicsSubTopicDomain> getTopicsSubTopicDetails(HttpSession sess)
	{
		List<StacsTopicsSubTopicDomain> ret=new ArrayList<StacsTopicsSubTopicDomain>();
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			String ManualorUpload=util.getConfigLayerbyManualorUpload();
			
			if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.UPLOAD))
			{
				excelRead.processBySheet(ExcelSheetUtil.TOPICSUBTOPIC,sess);
				
				ArrayList<HashMap<String,String>> info=util.getSheetDetails();
				if(info!=null && info.size()>0)
				{
					for(int i=0;i<info.size();i++)
					{
						HashMap<String,String> rowinfo=info.get(i);
						String[] cols=util.getSheetCols();
						
						String SubjectId=rowinfo.get(cols[0]);
						String TopicId=rowinfo.get(cols[1]);
						String TopicName=rowinfo.get(cols[2]);
						String SubTopicId=rowinfo.get(cols[3]);
						String SubTopicName=rowinfo.get(cols[4]);
						
						StacsTopicsSubTopicDomain row=new StacsTopicsSubTopicDomain();
						row.setSubjectId(SubjectId);
						row.setTopicId(TopicId);
						row.setTopicName(TopicName);
						row.setSubTopicId(SubTopicId);
						row.setSubTopicName(SubTopicName);
						
						ret.add(row);
					}
				}
			}else
			{
				int spocsRowCount=ExcelSheetUtil.TOPICSUBTOPICROWCOUNT-1;
				for(int i=0;i<spocsRowCount;i++)
				{
					StacsTopicsSubTopicDomain row=new StacsTopicsSubTopicDomain();
					ret.add(row);
				}
				util.setTotRow(spocsRowCount);	
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		return ret;
	}
	public static List<StacsCOSTSTMapDomain> getCOSTSTMapDetails(HttpSession sess)
	{
		List<StacsCOSTSTMapDomain> ret=new ArrayList<StacsCOSTSTMapDomain>();
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			String ManualorUpload=util.getConfigLayerbyManualorUpload();
			
			if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.UPLOAD))
			{
				
				excelRead.processBySheet(ExcelSheetUtil.COSTSTMAP,sess);
				
				ArrayList<HashMap<String,String>> info=util.getSheetDetails();
				if(info!=null && info.size()>0)
				{
					for(int i=0;i<info.size();i++)
					{
						HashMap<String,String> rowinfo=info.get(i);
						String[] cols=util.getSheetCols();
						
						String DisciplineName=rowinfo.get(cols[0]);
						String ProgramName=rowinfo.get(cols[1]);
						String CourseID=rowinfo.get(cols[2]);
						String CourseName=rowinfo.get(cols[3]);
						String DepartmentID=rowinfo.get(cols[4]);
						String DepartmentName=rowinfo.get(cols[5]);
						String SubjectId=rowinfo.get(cols[6]);
						String SubjectName=rowinfo.get(cols[7]);
						String TopicId=rowinfo.get(cols[8]);
						String TopicName=rowinfo.get(cols[9]);
						String SubTopicId=rowinfo.get(cols[10]);
						String SubTopicName=rowinfo.get(cols[11]);
						String FacultyUID=rowinfo.get(cols[12]);
						
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
						
						ret.add(row);
						
						
					}
				}
			}else
			{
				int spocsRowCount=ExcelSheetUtil.COSTSTMAPROWCOUNT-1;
				for(int i=0;i<spocsRowCount;i++)
				{
					StacsCOSTSTMapDomain row=new StacsCOSTSTMapDomain();
					ret.add(row);
				}
				util.setTotRow(spocsRowCount);	
			}
		}catch(Exception ex){ex.printStackTrace();}
		return ret;
	}
	
	public static List<StacsTimeTableDomain> getTimeTableDetails(HttpSession sess)
	{
		List<StacsTimeTableDomain> ret=new ArrayList<StacsTimeTableDomain>();
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			String ManualorUpload=util.getConfigLayerbyManualorUpload();
			
			if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.UPLOAD))
			{
				
				excelRead.processBySheet(ExcelSheetUtil.TIMETABLE,sess);
				
				ArrayList<HashMap<String,String>> info=util.getSheetDetails();
				if(info!=null && info.size()>0)
				{
					for(int i=0;i<info.size();i++)
					{
						HashMap<String,String> rowinfo=info.get(i);
						String[] cols=util.getSheetCols();
						
						String DisciplineName=rowinfo.get(cols[0]);
						String ProgramName=rowinfo.get(cols[1]);
						String CourseID=rowinfo.get(cols[2]);
						String CourseName=rowinfo.get(cols[3]);
						String DepartmentID=rowinfo.get(cols[4]);
						String DepartmentName=rowinfo.get(cols[5]);
						String SubjectId=rowinfo.get(cols[6]);
						String SubjectName=rowinfo.get(cols[7]);
						String TopicId=rowinfo.get(cols[8]);
						String TopicName=rowinfo.get(cols[9]);
						String SubTopicId=rowinfo.get(cols[10]);
						String SubTopicName=rowinfo.get(cols[11]);
						String FacultyUID=rowinfo.get(cols[12]);
						String SessionName=rowinfo.get(cols[13]);
						String SessStartDate=rowinfo.get(cols[14]);
						String SessEndDate=rowinfo.get(cols[15]);
						String SessionDuration=rowinfo.get(cols[16]);
						String SessionMaterial=rowinfo.get(cols[17]);
						
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
						row.setSessionName(SessionName);
						row.setSessionStartDateTime(SessStartDate);
						row.setSessionEndDateTime(SessEndDate);
						row.setSessionDuration(SessionDuration);
						row.setSessionMaterial(SessionMaterial);
						
						ret.add(row);
					}
				}
			}
			else
			{
				int spocsRowCount=ExcelSheetUtil.TIMETABLEROWCOUNT-1;
				for(int i=0;i<spocsRowCount;i++)
				{
					StacsTimeTableDomain row=new StacsTimeTableDomain();
					ret.add(row);
				}
				util.setTotRow(spocsRowCount);	
			}
			
		}catch(Exception ex){}
		
		return ret;
	}
	
	public static List<StacsSGPACGPADomain> getSgpaCgpaDetails(HttpSession sess)
	{
		List<StacsSGPACGPADomain> ret=new ArrayList<StacsSGPACGPADomain>();
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			String ManualorUpload=util.getConfigLayerbyManualorUpload();
			
			if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.UPLOAD))
			{
				
				excelRead.processBySheet(ExcelSheetUtil.SGPACGPA,sess);
				
				ArrayList<HashMap<String,String>> info=util.getSheetDetails();
				if(info!=null && info.size()>0)
				{
					for(int i=0;i<info.size();i++)
					{
						HashMap<String,String> rowinfo=info.get(i);
						String[] cols=util.getSheetCols();
						
						String letterGrade=rowinfo.get(cols[0]);
						String description=rowinfo.get(cols[1]);
						String gradePoints=rowinfo.get(cols[2]);
						String minRange=rowinfo.get(cols[3]);
						String maxRange=rowinfo.get(cols[4]);
	
						StacsSGPACGPADomain row=new StacsSGPACGPADomain();
						row.setLetterGrade(letterGrade);
						row.setDescription(description);
						row.setGradePoints(gradePoints);
						row.setMinRange(minRange);
						row.setMaxRange(maxRange);
						
						ret.add(row);
					}
				}
			}
			else
			{
				int spocsRowCount=ExcelSheetUtil.SGPACGPAROWCOUNT-1;
				for(int i=0;i<spocsRowCount;i++)
				{
					StacsSGPACGPADomain row=new StacsSGPACGPADomain();
					ret.add(row);
				}
				util.setTotRow(spocsRowCount);	
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		return ret;
	}
	
	public static List<StacsCourseCreditDomain> getCourseCreditDetails(HttpSession sess)
	{
		List<StacsCourseCreditDomain> ret=new ArrayList<StacsCourseCreditDomain>();
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			String ManualorUpload=util.getConfigLayerbyManualorUpload();
			
			if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.UPLOAD))
			{
				
				excelRead.processBySheet(ExcelSheetUtil.COURSECREDIT,sess);
				
				ArrayList<HashMap<String,String>> info=util.getSheetDetails();
				if(info!=null && info.size()>0)
				{
					for(int i=0;i<info.size();i++)
					{
						HashMap<String,String> rowinfo=info.get(i);
						String[] cols=util.getSheetCols();
						
						String DisciplineName=rowinfo.get(cols[0]);
						String ProgramName=rowinfo.get(cols[1]);
						String CourseID=rowinfo.get(cols[2]);
						String DepartmentID=rowinfo.get(cols[3]);
						String Semester=rowinfo.get(cols[4]);
						String SubjectID=rowinfo.get(cols[5]);
						String SessionMax=rowinfo.get(cols[6]);
						String TheoryMax=rowinfo.get(cols[7]);
						String PracticalMax=rowinfo.get(cols[8]);
						String SessionMin=rowinfo.get(cols[9]);
						String TheoryMin=rowinfo.get(cols[10]);
						String PracticalMin=rowinfo.get(cols[11]);
						String CreditHours=rowinfo.get(cols[12]);
						
						/*if(DisciplineName!=null && ProgramName!=null && CourseID!=null && DepartmentID!=null && Semester!=null &&
								SubjectID!=null &&  SessionMax!=null && TheoryMax!=null && PracticalMax!=null &&
								SessionMin!=null && TheoryMin!=null && PracticalMin!=null)
						{*/
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
							row.setCreditHours(CreditHours);
							
							ret.add(row);
						//}
					}
				}
				
			}else
			{
				int spocsRowCount=ExcelSheetUtil.COURSECREDITROWCOUNT-1;
				for(int i=0;i<spocsRowCount;i++)
				{
					StacsCourseCreditDomain row=new StacsCourseCreditDomain();
					ret.add(row);
				}
				util.setTotRow(spocsRowCount);
				
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		
		return ret;
	}
	
	public static List<StacsAssignmentTypesDomain> getAssignTypesDetails(HttpSession sess)
	{
		List<StacsAssignmentTypesDomain> ret=new ArrayList<StacsAssignmentTypesDomain>();
		try
		{
			
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			String ManualorUpload=util.getConfigLayerbyManualorUpload();
			
			if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.UPLOAD))
			{
				
				excelRead.processBySheet(ExcelSheetUtil.ASSIGNTYPES,sess);
				
				ArrayList<HashMap<String,String>> info=util.getSheetDetails();
				if(info!=null && info.size()>0)
				{
					for(int i=0;i<info.size();i++)
					{
						HashMap<String,String> rowinfo=info.get(i);
						String[] cols=util.getSheetCols();
						
						String typeId=rowinfo.get(cols[0]);
						String typeDesc=rowinfo.get(cols[1]);
						String applyStatus=rowinfo.get(cols[2]);
						String instDesc=rowinfo.get(cols[3]);
						
						StacsAssignmentTypesDomain row =new StacsAssignmentTypesDomain();
						row.setTypeId(typeId);
						row.setTypeDesc(typeDesc);
						row.setApplyStatus(applyStatus);
						row.setInstDesc(instDesc);
													
						ret.add(row);
						
					}
				}
				
			}else
			{
				int spocsRowCount=ExcelSheetUtil.ASSIGNTYPESROWCOUNT-1;
				for(int i=0;i<spocsRowCount;i++)
				{
					StacsAssignmentTypesDomain row=new StacsAssignmentTypesDomain();
					ret.add(row);
				}
				util.setTotRow(spocsRowCount);
				
			}
			
			
		}catch(Exception ex){ex.printStackTrace();}
		return ret;
	}

	public static List<StacsCertConfigDomain> getCertificateDetails(HttpSession sess)
	{
		List<StacsCertConfigDomain> ret=new ArrayList<StacsCertConfigDomain>();
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			String ManualorUpload=util.getConfigLayerbyManualorUpload();
			
			if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.UPLOAD))
			{
				
				excelRead.processBySheet(ExcelSheetUtil.CERTCONFIG,sess);
				
				ArrayList<HashMap<String,String>> info=util.getSheetDetails();
				if(info!=null && info.size()>0)
				{
					for(int i=0;i<info.size();i++)
					{
						HashMap<String,String> rowinfo=info.get(i);
						String[] cols=util.getSheetCols();
						
						String CourseID=rowinfo.get(cols[0]);
						String GreetingText=rowinfo.get(cols[1]);
						String BodyText=rowinfo.get(cols[2]);
						String WishingText=rowinfo.get(cols[3]);
						String FooterText=rowinfo.get(cols[4]);
						String AuthorizedSign=rowinfo.get(cols[5]);
						String DigitalSign=rowinfo.get(cols[6]);
						String CertificateSize=rowinfo.get(cols[7]);
						String CertificateLayout=rowinfo.get(cols[8]);
						String BorderImage=rowinfo.get(cols[9]);
						String CornerImage=rowinfo.get(cols[10]);
						String WaterMarkImage=rowinfo.get(cols[11]);
						
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
						
						ret.add(row);
					}
				}
			}else
			{
				int spocsRowCount=ExcelSheetUtil.CERTCONFIGROWCOUNT-1;
				for(int i=0;i<spocsRowCount;i++)
				{
					StacsCertConfigDomain row=new StacsCertConfigDomain();
					ret.add(row);
				}
				util.setTotRow(spocsRowCount);
				
			}
			
			
		}catch(Exception ex){ex.printStackTrace();}
		return ret;
		
	}
}
