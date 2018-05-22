package com.kds.KODE_DEV.domain;

public class SubjectMasterDomain {
	private String DispId;
	private String Subject;
	private String SubID;
	
	public String getDiscplineID() {
		return DispId;
	}
	public void setDisciplineID(String DispId) {
		this.DispId = DispId;
	}
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String Subject) {
		this.Subject = Subject;
	}
	public String getSubjectID() {
		return SubID;
	}
	public void setSubjectID(String SubID) {
		this.SubID = SubID;
	}
}