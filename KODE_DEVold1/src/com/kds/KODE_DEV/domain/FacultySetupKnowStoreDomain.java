package com.kds.KODE_DEV.domain;

public class FacultySetupKnowStoreDomain {

	private String ksId;
	private String orgId;
	private String emailId;
	private String userId;
	private String createdBy;
	private int ksSize;
	private String status;

	public String getKsId() {
		return ksId;
	}

	public void setKsId(String ksId) {
		this.ksId = ksId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public int getKsSize() {
		return ksSize;
	}

	public void setKsSize(int ksSize) {
		this.ksSize = ksSize;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}