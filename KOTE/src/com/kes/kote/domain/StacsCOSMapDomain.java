package com.kes.kote.domain;

public class StacsCOSMapDomain {

	private String DisciplineName;
	
	private String ProgramName;
	
	private String CourseID;
	
	private String CourseName;
	
	private String DepartmentID;
	
	private String DepartmentName;
	
	private String SubjectId;
	
	private String SubjectName;
	
	private int SemesterID;
	
	private String SemesterStartDate;
	
	private String SemesterEndDate;

	public String getDisciplineName() {
		return DisciplineName;
	}

	public void setDisciplineName(String disciplineName) {
		DisciplineName = disciplineName;
	}

	public String getProgramName() {
		return ProgramName;
	}

	public void setProgramName(String programName) {
		ProgramName = programName;
	}

	public String getCourseID() {
		return CourseID;
	}

	public void setCourseID(String courseID) {
		CourseID = courseID;
	}

	public String getCourseName() {
		return CourseName;
	}

	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

	public String getDepartmentID() {
		return DepartmentID;
	}

	public void setDepartmentID(String departmentID) {
		DepartmentID = departmentID;
	}

	public String getDepartmentName() {
		return DepartmentName;
	}

	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}

	public String getSubjectId() {
		return SubjectId;
	}

	public void setSubjectId(String subjectId) {
		SubjectId = subjectId;
	}

	public String getSubjectName() {
		return SubjectName;
	}

	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}

	public int getSemesterID() {
		return SemesterID;
	}

	public void setSemesterID(String semesterID) {
		try
		{
			if(semesterID.contains("Semester"))
			{
				semesterID=semesterID.substring(semesterID.indexOf("r")+1, semesterID.length());
			}
			if(semesterID!=null && semesterID.trim().length()>0)
				SemesterID = Integer.parseInt(semesterID.trim());
			else
				SemesterID =0;
		}catch(Exception ex){ SemesterID =0;}
		
	}

	public String getSemesterStartDate() {
		return SemesterStartDate;
	}

	public void setSemesterStartDate(String semesterStartDate) {
		SemesterStartDate = semesterStartDate;
	}

	public String getSemesterEndDate() {
		return SemesterEndDate;
	}

	public void setSemesterEndDate(String semesterEndDate) {
		SemesterEndDate = semesterEndDate;
	}

	@Override
	public String toString() {
		return "StacsCOSMapDomain [DisciplineName=" + DisciplineName + ", ProgramName=" + ProgramName + ", CourseID="
				+ CourseID + ", CourseName=" + CourseName + ", DepartmentID=" + DepartmentID + ", DepartmentName="
				+ DepartmentName + ", SubjectId=" + SubjectId + ", SubjectName=" + SubjectName + ", SemesterID="
				+ SemesterID + ", SemesterStartDate=" + SemesterStartDate + ", SemesterEndDate=" + SemesterEndDate
				+ "]";
	}

	public boolean isValid()
	{
		if(getDepartmentID()!=null && getDepartmentID().length()>0)
			if(getSubjectId()!=null &&getSubjectId().length()>0)
				if(getSubjectName()!=null && getSubjectName().length()>0)
					return true;
		
		/*
		 * if(getSemesterID()>=0)
						if(getSemesterStartDate()!=null && getSemesterStartDate().length()>0)
							if(getSemesterEndDate()!=null && getSemesterEndDate().length()>0)
							
		 */
		return false;
	}
	
}
