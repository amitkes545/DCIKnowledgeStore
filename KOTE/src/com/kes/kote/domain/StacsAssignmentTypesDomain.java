package com.kes.kote.domain;

public class StacsAssignmentTypesDomain {

	private String typeId;
	private String typeDesc;
	private String applyStatus;
	private String instDesc;
	
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public String getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	public String getInstDesc() {
		return instDesc;
	}
	public void setInstDesc(String instDesc) {
		this.instDesc = instDesc;
	}
	@Override
	public String toString() {
		return "StacsAssignmentTypesDomain [typeId=" + typeId + ", typeDesc=" + typeDesc + ", applyStatus="
				+ applyStatus + ", instDesc=" + instDesc + "]";
	}
	
	public boolean isValid()
	{
		if(getApplyStatus()!=null && getApplyStatus().trim().length()>0 && getApplyStatus().trim().equalsIgnoreCase("Yes"))
			return true;
		
		return false;
	}
}
