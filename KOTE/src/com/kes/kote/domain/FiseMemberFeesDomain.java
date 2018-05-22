package com.kes.kote.domain;

public class FiseMemberFeesDomain {

	private String deptId;
	private String 	groupFeeId;
	private int groupSeq;
	private String memberFeeId;
	private double memberFeeVal;
	private String memberFeeType;
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getGroupFeeId() {
		return groupFeeId;
	}
	public void setGroupFeeId(String groupFeeId) {
		this.groupFeeId = groupFeeId;
	}
	public int getGroupSeq() {
		return groupSeq;
	}
	public void setGroupSeq(int groupSeq) {
		this.groupSeq = groupSeq;
	}
	public String getMemberFeeId() {
		return memberFeeId;
	}
	public void setMemberFeeId(String memberFeeId) {
		this.memberFeeId = memberFeeId;
	}
	public double getMemberFeeVal() {
		return memberFeeVal;
	}
	public void setMemberFeeVal(double memberFeeVal) {
		this.memberFeeVal = memberFeeVal;
	}
	public String getMemberFeeType() {
		return memberFeeType;
	}
	public void setMemberFeeType(String memberFeeType) {
		this.memberFeeType = memberFeeType;
	}
	@Override
	public String toString() {
		return "FiseMemberFeesDomain [deptId=" + deptId + ", groupFeeId=" + groupFeeId + ", groupSeq=" + groupSeq
				+ ", memberFeeId=" + memberFeeId + ", memberFeeVal=" + memberFeeVal + ", memberFeeType=" + memberFeeType
				+ "]";
	}
	
	public boolean isvalid()
	{ 
		if(getDeptId()!=null && getDeptId().length()>0)
			if(getGroupFeeId()!=null && getGroupFeeId().length()>0)
				if(getGroupSeq()>0)
					if(getMemberFeeId()!=null && getMemberFeeId().length()>0)
						if(getMemberFeeVal()>0)
							if(getMemberFeeType()!=null && getMemberFeeType().length()>0)
								return true;
		
			
			
		return false;
	}
}
