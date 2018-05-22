package com.kds.KODE_DEV.domain;

public class OwnerSetUpKnowStoreDomain {
	private String KsId;
	private String emailId;
	private String orgId;
	private String orgName;
	private String userId;
	private String ksSize;
	private String status;
	private String createdBy;

	public String getKsId() {
		return KsId;
	}

	public void setKsId(String ksId) {
		KsId = ksId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getKsSize() {
		return ksSize;
	}

	public void setKsSize(String ksSize) {
		this.ksSize = ksSize;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
}