package com.kds.KODE_DEV.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class FacilitatorKAssetsReportDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ksId;
	private String libId;
	private String department;
	private String subject;
	private String description;
	private String uploadedBy;
	private String fileName;
	private int fileSize;
	private Date uploadDate;
	private Timestamp uploadTime;
	private String filePath;
	private String status;
	private String recipientType;
	private String userId;
	private String orgId;
	private String action;
	private String courseId;
	private String participants;
	private String subjectId;
	private String subTopicId;
	
	public String getSubTopicId() {
		return subTopicId;
	}


	public void setSubTopicId(String subTopicId) {
		this.subTopicId = subTopicId;
	}

	private String courses;
	public String getCourses() {
		return courses;
	}


	public void setCourses(String courses) {
		this.courses = courses;
	}


	public String getDepartments() {
		return departments;
	}


	public void setDepartments(String departments) {
		this.departments = departments;
	}


	public String getSubjects() {
		return subjects;
	}


	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}


	public String getTopic() {
		return topic;
	}


	public void setTopic(String topic) {
		this.topic = topic;
	}


	public String getSubTopic() {
		return subTopic;
	}


	public void setSubTopic(String subTopic) {
		this.subTopic = subTopic;
	}


	public String getSubjctId() {
		return subjctId;
	}


	public void setSubjctId(String subjctId) {
		this.subjctId = subjctId;
	}


	public String getTopicId() {
		return topicId;
	}


	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	private String departments; 
	private String  subjects;
	private String topic;
	private String subTopic;
	private String subjctId;
	private String topicId;
	
	public String getSubjectId() {
		return subjectId;
	}
	
	
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getParticipants() {
		return participants;
	}
	public void setParticipants(String participants) {
		this.participants = participants;
	}
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getKsId() {
		return ksId;
	}

	public void setKsId(String ksId) {
		this.ksId = ksId;
	}

	public String getLibId() {
		return libId;
	}

	public void setLibId(String libId) {
		this.libId = libId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Timestamp getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRecipientType() {
		return recipientType;
	}

	public void setRecipientType(String recipientType) {
		this.recipientType = recipientType;
	}


	public java.sql.Timestamp getTimeStamp()
	   {
	     java.sql.Timestamp ret=null;
	     try {
	       Calendar calendar = Calendar.getInstance();
	       java.util.Date now = calendar.getTime();
	       ret = new java.sql.Timestamp(now.getTime());
	       
	     }
	     catch(Exception ex){ex.printStackTrace();}
	     
	     return ret;
	   }
}