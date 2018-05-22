package com.kds.KODE_DEV.domain;

public class OwnerReportKnowStoreDomain {

	private String ksId;
	private String orgId;
	private String userId;
	private String createdBy;
	private String knowSpace;
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

	public String getKnowSpace() {
		return knowSpace;
	}

	public void setKnowSpace(String knowSpace) {
		this.knowSpace = knowSpace;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}