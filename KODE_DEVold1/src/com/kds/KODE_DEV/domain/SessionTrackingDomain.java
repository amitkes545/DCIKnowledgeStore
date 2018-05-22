package com.kds.KODE_DEV.domain;

public class SessionTrackingDomain {
private String loginStatus;
private String privilege;
private String studentId;

public String getLoginStatus() {
	return loginStatus;
}
public void setLoginStatus(String loginStatus) {
	this.loginStatus = loginStatus;
}
public String getPrivilege() {
	return privilege;
}
public void setPrivilege(String privilege) {
	this.privilege = privilege;
}
public String getStudentId() {
	return studentId;
}
public void setStudentId(String studentId) {
	this.studentId = studentId;
}

}
