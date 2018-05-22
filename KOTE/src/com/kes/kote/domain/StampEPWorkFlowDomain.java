package com.kes.kote.domain;

public class StampEPWorkFlowDomain {
	
	private String ItemName;
	
	private String StageName;
	
	private String ApprovedUsrID;
	
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public String getStageName() {
		return StageName;
	}
	public void setStageName(String stageName) {
		StageName = stageName;
	}
	public String getApprovedUsrID() {
		return ApprovedUsrID;
	}
	public void setApprovedUsrID(String approvedUsrID) {
		ApprovedUsrID = approvedUsrID;
	}

	@Override
	public String toString()
	{
		return getItemName()+" : "+getStageName()+" : "+getApprovedUsrID();
	}
}
