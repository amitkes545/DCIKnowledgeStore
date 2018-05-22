package com.kds.KODE_DEV.domain;


public class SessionDomain  {
	private String sessionId;
	private String status;
	private String sessionName;
	private String category;
	private String duration;
	private String costOfSession;
	private String sessionStartTime;
	private String facultyId;
	private String sessionEndTime;
	private String orgId;
	private String individualId;
	private String groupId;
	private String groupValue;
	private String all;
	private String attendance;
	private String groupName;
	private String courseName;
	private String userName;
	// changes made
	private String courseId;
	private String syllabusId;
	private String subjectId;
	private String topicId;
	private String subtopicId;
	private String subjectName;
	
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getSyllabusId() {
		return syllabusId;
	}
	public void setSyllabusId(String syllabusId) {
		this.syllabusId = syllabusId;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public String getSubtopicId() {
		return subtopicId;
	}
	public void setSubtopicId(String subtopicId) {
		this.subtopicId = subtopicId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getAttendance() {
		return attendance;
	}
	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private String recipientType;
	private String pathOfFile;
	private String email="";
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	private String marks;
	private String reMarks;
	private String assessmentId;
	private String date;
	private String time;
	private String studentName="";
	
	
	/*public SessionDomain(String sessionId, String sessionName,
			String category, String startTime, String endTime,
			String duration, String costOfSession, String recipientType,
			String status) {
		// TODO Auto-generated constructor stub
		this.sessionId=sessionId;
		this.sessionName=sessionName;
		this.category=category;
		this.sessionStartTime=startTime;
		this.sessionEndTime=endTime;
		this.duration=duration;
		this.costOfSession=costOfSession;
		this.recipientType=recipientType;
		this.status=status;
	}*/
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAssessmentId() {
		return assessmentId;
	}
	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public String getReMarks() {
		return reMarks;
	}
	public void setReMarks(String reMarks) {
		this.reMarks = reMarks;
	}
	public String getPathOfFile() {
		return pathOfFile;
	}
	public void setPathOfFile(String pathOfFile) {
		this.pathOfFile = pathOfFile;
	}
	public String getRecipientType() {
		return recipientType;
	}
	public void setRecipientType(String recipientType) {
		this.recipientType = recipientType;
	}
	public String getIndividualId() {
		return individualId;
	}
	public void setIndividualId(String individualId) {
		this.individualId = individualId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupValue() {
		return groupValue;
	}
	public void setGroupValue(String groupValue) {
		this.groupValue = groupValue;
	}
	public String getAll() {
		return all;
	}
	public void setAll(String all) {
		this.all = all;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSessionName() {
		return sessionName;
	}
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String hours) {
		this.duration = hours;
	}
	public String getCostOfSession() {
		return costOfSession;
	}
	public void setCostOfSession(String costOfSession) {
		this.costOfSession = costOfSession;
	}
	public String getSessionStartTime() {
		return sessionStartTime;
	}
	public void setSessionStartTime(String sessionStartTime) {
		this.sessionStartTime = sessionStartTime;
	}
	public String getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(String facultyId) {
		this.facultyId = facultyId;
	}
	public String getSessionEndTime() {
		return sessionEndTime;
	}
	public void setSessionEndTime(String sessionEndTime) {
		this.sessionEndTime = sessionEndTime;
	}
	
  
}
