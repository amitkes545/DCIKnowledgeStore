package com.kes.kote.domain;

public class StacsTimeTableDomain {

	private String DisciplineName;
	private String ProgramName;
	private String CourseID;
	private String CourseName;
	private String DepartmentID;
	private String DepartmentName;
	private String SubjectId;
	private String SubjectName;
	private String TopicId;
	private String TopicName;
	private String SubTopicId;
	private String SubTopicName;
	private String FacultyUID;
	private String sessionName;
	private String sessionDuration;
	private String sessionStartDateTime;
	private String sessionEndDateTime;
	private String sessionMaterial;
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
	public String getTopicId() {
		return TopicId;
	}
	public void setTopicId(String topicId) {
		TopicId = topicId;
	}
	public String getTopicName() {
		return TopicName;
	}
	public void setTopicName(String topicName) {
		TopicName = topicName;
	}
	public String getSubTopicId() {
		return SubTopicId;
	}
	public void setSubTopicId(String subTopicId) {
		SubTopicId = subTopicId;
	}
	public String getSubTopicName() {
		return SubTopicName;
	}
	public void setSubTopicName(String subTopicName) {
		SubTopicName = subTopicName;
	}
	public String getFacultyUID() {
		return FacultyUID;
	}
	public void setFacultyUID(String facultyUID) {
		FacultyUID = facultyUID;
	}
	public String getSessionName() {
		return sessionName;
	}
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	public String getSessionStartDateTime() {
		return sessionStartDateTime;
	}
	public void setSessionStartDateTime(String sessionStartDateTime) {
		this.sessionStartDateTime = sessionStartDateTime;
	}
	public String getSessionEndDateTime() {
		return sessionEndDateTime;
	}
	public void setSessionEndDateTime(String sessionEndDateTime) {
		this.sessionEndDateTime = sessionEndDateTime;
	}
	public String getSessionMaterial() {
		return sessionMaterial;
	}
	public void setSessionMaterial(String sessionMaterial) {
		this.sessionMaterial = sessionMaterial;
	}
	
	public String getSessionDuration() {
		return sessionDuration;
	}
	public void setSessionDuration(String sessionDuration) {
		this.sessionDuration = sessionDuration;
	}
	public boolean isValid()
	{
		if(getDepartmentID()!=null && getDepartmentID().trim().length()>0)
			if(getSubjectId()!=null &&  getSubjectId().trim().length()>0)
				if(getTopicId()!=null && getTopicId().trim().length()>0)
					if(getSubTopicId()!=null && getSubTopicId().trim().length()>0)
						if(getFacultyUID()!=null && getFacultyUID().trim().length()>0)
							if(getSessionName()!=null && getSessionName().trim().length()>0)
								if(getSessionStartDateTime()!=null && getSessionStartDateTime().trim().length()>0)
									if(getSessionEndDateTime()!=null && getSessionEndDateTime().trim().length()>0)
										if(getSessionDuration()!=null && getSessionDuration().trim().length()>0)
											return true;
		
		return false;
	}
	
	
}
