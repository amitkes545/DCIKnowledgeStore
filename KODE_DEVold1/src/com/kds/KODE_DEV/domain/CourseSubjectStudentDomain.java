package com.kds.KODE_DEV.domain;

public class CourseSubjectStudentDomain {
	private String subjectId;
	private String SubjectName;
	private String topicName;
	private String subTopicName;
	private String assignmentName;
	private String assignmenId;
	public String getAssignmenId() {
		return assignmenId;
	}
	public void setAssignmenId(String assignmenId) {
		this.assignmenId = assignmenId;
	}
	private String courseId;
	private String libId;
	public String getLibId() {
		return libId;
	}
	public void setLibId(String libId) {
		this.libId = libId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getAssignmentName() {
		return assignmentName;
	}
	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getSubTopicName() {
		return subTopicName;
	}
	public void setSubTopicName(String subTopicName) {
		this.subTopicName = subTopicName;
	}
	
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return SubjectName;
	}
	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}
	

}
