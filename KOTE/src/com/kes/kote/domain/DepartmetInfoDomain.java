package com.kes.kote.domain;

public class DepartmetInfoDomain {
	
	private String deptID;
	private String deptName;
	public String getDeptID() {
		return deptID;
	}
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Override
	public String toString() {
		return "DepartmetInfoDomain [deptID=" + deptID + ", deptName=" + deptName + "]";
	}
	

}
