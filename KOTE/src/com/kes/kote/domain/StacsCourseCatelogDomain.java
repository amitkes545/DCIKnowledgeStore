package com.kes.kote.domain;

public class StacsCourseCatelogDomain {
	
	
	private String DisciplineID;
	
	private String DisciplineName;
	
	private String ProgramName;
	
	private String CourseID;
	
	private String CourseName;
	
	private String CourseImageName;
	
	private String CourseImageSize;
	
	private String DepartmentID;
	
	private String DepartmentName;
	
	private String DepartmentImageName;
	
	private String DepartmentImageSize;
	
	private String DurationType;
	
	private int DurationPeriod;
	
	private String TeachingPattern;
	
	private int NoOfSessions;
	
	private String CanBeDoneinParallel;
	
	private String PAdminUID;
	
	private String SAdminUID;
	
	public String getDisciplineID() {
		return DisciplineID;
	}

	public void setDisciplineID(String disciplineID) {
		DisciplineID = disciplineID;
	}
	
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

	public String getCourseImageName() {
		return CourseImageName;
	}

	public void setCourseImageName(String courseImageName) {
		CourseImageName = courseImageName;
	}

	public String getCourseImageSize() {
		return CourseImageSize;
	}

	public void setCourseImageSize(String courseImageSize) {
		CourseImageSize = courseImageSize;
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

	public String getDepartmentImageName() {
		return DepartmentImageName;
	}

	public void setDepartmentImageName(String departmentImageName) {
		DepartmentImageName = departmentImageName;
	}

	public String getDepartmentImageSize() {
		return DepartmentImageSize;
	}

	public void setDepartmentImageSize(String departmentImageSize) {
		DepartmentImageSize = departmentImageSize;
	}

	public String getDurationType() {
		return DurationType;
	}

	public void setDurationType(String durationType) {
		DurationType = durationType;
	}

	public int getDurationPeriod() {
		return DurationPeriod;
	}

	public void setDurationPeriod(String durationPeriod) {
		if(durationPeriod!=null && durationPeriod.trim().length()>0)
			DurationPeriod = Integer.parseInt(durationPeriod);
	}

	public String getTeachingPattern() {
		return TeachingPattern;
	}

	public void setTeachingPattern(String teachingPattern) {
		TeachingPattern = teachingPattern;
	}

	public int getNoOfSessions() {
		return NoOfSessions;
	}

	public void setNoOfSessions(String noOfSessions) {
		if(noOfSessions!=null && noOfSessions.trim().length()>0)
			NoOfSessions = Integer.parseInt(noOfSessions);
	}

	public String getCanBeDoneinParallel() {
		return CanBeDoneinParallel;
	}

	public void setCanBeDoneinParallel(String canBeDoneinParallel) {
		CanBeDoneinParallel = canBeDoneinParallel;
	}

	public String getPAdminUID() {
		return PAdminUID;
	}

	public void setPAdminUID(String pAdminUID) {
		PAdminUID = pAdminUID;
	}

	public String getSAdminUID() {
		return SAdminUID;
	}

	public void setSAdminUID(String sAdminUID) {
		SAdminUID = sAdminUID;
	}

	public void setDurationPeriod(int durationPeriod) {
		DurationPeriod = durationPeriod;
	}

	public void setNoOfSessions(int noOfSessions) {
		NoOfSessions = noOfSessions;
	}

	
	@Override
	public String toString() {
		return "StacsCourseCatelogDomain [DisciplineID=" + DisciplineID + ", DisciplineName=" + DisciplineName
				+ ", ProgramName=" + ProgramName + ", CourseID=" + CourseID + ", CourseName=" + CourseName
				+ ", CourseImageName=" + CourseImageName + ", CourseImageSize=" + CourseImageSize + ", DepartmentID="
				+ DepartmentID + ", DepartmentName=" + DepartmentName + ", DepartmentImageName=" + DepartmentImageName
				+ ", DepartmentImageSize=" + DepartmentImageSize + ", DurationType=" + DurationType
				+ ", DurationPeriod=" + DurationPeriod + ", TeachingPattern=" + TeachingPattern + ", NoOfSessions="
				+ NoOfSessions + ", CanBeDoneinParallel=" + CanBeDoneinParallel + ", PAdminUID=" + PAdminUID
				+ ", SAdminUID=" + SAdminUID + "]";
	}

	public boolean isValid()
	{
		if(getDisciplineName()!=null && getDisciplineName().length()>0)
			if(getProgramName()!=null && getProgramName().length()>0)
				if(getCourseID()!=null && getCourseID().length()>0)
					if(getDepartmentID()!=null &&getDepartmentID().length()>0)
						if(getDurationType()!=null &&getDurationType().length()>0)
							if(getDurationPeriod()>0)
								if(getTeachingPattern()!=null &&getTeachingPattern().length()>0)
									if(getNoOfSessions()>0)
										if(getCanBeDoneinParallel()!=null && getCanBeDoneinParallel().length()>0)
											if(getPAdminUID()!=null && getPAdminUID().length()>0)
												return true;
		return false;
	}
	
}
