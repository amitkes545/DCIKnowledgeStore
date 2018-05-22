package com.kes.kote.domain;

public class FiseCourseFeeMapDomain {

	private String deptId;
	private int feeSequence;
	private String instFeeId;
	private String group;
	private double feeAmount;
	private String crsFeeId;
	private String feeType;
	private int feeRecurring;
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public int getFeeSequence() {
		return feeSequence;
	}
	public void setFeeSequence(int feeSequence) {
		this.feeSequence = feeSequence;
	}
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
	public double getFeeAmount() {
		return feeAmount;
	}
	public void setFeeAmount(double feeAmount) {
		this.feeAmount = feeAmount;
	}
	public String getCrsFeeId() {
		return crsFeeId;
	}
	public void setCrsFeeId(String crsFeeId) {
		this.crsFeeId = crsFeeId;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public int getFeeRecurring() {
		return feeRecurring;
	}
	public void setFeeRecurring(int feeRecurring) {
		this.feeRecurring = feeRecurring;
	}
	@Override
	public String toString() {
		return "FiseCourseFeeMapDomain [deptId=" + deptId + ", feeSequence=" + feeSequence + ", instFeeId=" + instFeeId
				+ ", group=" + group + ", feeAmount=" + feeAmount + ", crsFeeId=" + crsFeeId + ", feeType=" + feeType
				+ ", feeRecurring=" + feeRecurring + "]";
	}
	
	public boolean isValid()
	{
		if(getDeptId()!=null && getDeptId().length()>0)
			if(getFeeSequence()>0)
				if(getInstFeeId()!=null && getInstFeeId().length()>0)
					if(getGroup()!=null && getGroup().length()>0)
						if(getFeeAmount()>0)
							if(getCrsFeeId()!=null && getCrsFeeId().length()>0)
								if(getFeeType()!=null && getFeeType().length()>0)
									return true;
		
		
		return false;
	}
	
	
}
