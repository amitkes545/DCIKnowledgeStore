package com.kds.KODE_DEV.domain;


public class LoginDomain {

	private String LoginId;
	private String Password;
	private String currentpwd;
	private String newpwd;
	private String conformpwd;
	public String getLoginId() {
		return LoginId;
	}
	public void setLoginId(String loginId) {
		LoginId = loginId;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getCurrentpwd() {
		return currentpwd;
	}
	public void setCurrentpwd(String currentpwd) {
		this.currentpwd = currentpwd;
	}
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
	public String getConformpwd() {
		return conformpwd;
	}
	public void setConformpwd(String conformpwd) {
		this.conformpwd = conformpwd;
	}
	

}
