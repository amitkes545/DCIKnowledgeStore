package com.kes.kote.domain;

public class SubjectDomain {
	
	private String subjectId;
	private String subjectName;
	private String DBSubjectId;
	
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	public String getDBSubjectId() {
		return DBSubjectId;
	}
	public void setDBSubjectId(String dBSubjectId) {
		DBSubjectId = dBSubjectId;
	}
	@Override
	public String toString() {
		return "SubjectDomain [subjectId=" + subjectId + ", subjectName=" + subjectName + ", DBSubjectId=" + DBSubjectId
				+ "]";
	}


	
	

}
