package com.kes.kote.domain;

public class InstFeeDomain {

	private String instFeeId;
	private String instFeeName;
	public String getInstFeeId() {
		return instFeeId;
	}
	public void setInstFeeId(String instFeeId) {
		this.instFeeId = instFeeId;
	}
	public String getInstFeeName() {
		return instFeeName;
	}
	public void setInstFeeName(String instFeeName) {
		this.instFeeName = instFeeName;
	}
	@Override
	public String toString() {
		return "InstFeeDomain [instFeeId=" + instFeeId + ", instFeeName=" + instFeeName + "]";
	}
	
	
}
