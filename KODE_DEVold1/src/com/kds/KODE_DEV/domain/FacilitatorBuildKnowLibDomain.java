package com.kds.KODE_DEV.domain;

public class FacilitatorBuildKnowLibDomain {

	private String ksId;
	private String oldName;
	private String newName;
	private int libSize;
	private String userId;
	private String orgId;
	private String studentId;
	private String spaceUom;
	
	public String getSpaceUom() {
		return spaceUom;
	}
	public void setSpaceUom(String spaceUom) {
		this.spaceUom = spaceUom;
	}
	public String getKsId() {
		return ksId;
	}
	public void setKsId(String ksId) {
		this.ksId = ksId;
	}
	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public int getLibSize() {
		return libSize;
	}
	public void setLibSize(int libSize) {
		this.libSize = libSize;
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
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}		
}