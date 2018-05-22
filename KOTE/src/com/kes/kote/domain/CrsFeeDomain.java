package com.kes.kote.domain;

public class CrsFeeDomain {
	
	private String instFeeId;
	private String group;
	private String feeAmount;
	private String crsFeeId;
	private String crsFeeType;
	private String crsFeeReq;
	public String getInstFeeId() {
		return instFeeId;
	}
	public void setInstFeeId(String instFeeId) {
		this.instFeeId = instFeeId;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getFeeAmount() {
		return feeAmount;
	}
	public void setFeeAmount(String feeAmount) {
		this.feeAmount = feeAmount;
	}
	public String getCrsFeeId() {
		return crsFeeId;
	}
	public void setCrsFeeId(String crsFeeId) {
		this.crsFeeId = crsFeeId;
	}
	public String getCrsFeeType() {
		return crsFeeType;
	}
	public void setCrsFeeType(String crsFeeType) {
		this.crsFeeType = crsFeeType;
	}
	public String getCrsFeeReq() {
		return crsFeeReq;
	}
	public void setCrsFeeReq(String crsFeeReq) {
		this.crsFeeReq = crsFeeReq;
	}
	@Override
	public String toString() {
		return "CrsFeeDomain [instFeeId=" + instFeeId + ", group=" + group + ", feeAmount=" + feeAmount + ", crsFeeId="
				+ crsFeeId + ", crsFeeType=" + crsFeeType + ", crsFeeReq=" + crsFeeReq + "]";
	}
	
	

}
