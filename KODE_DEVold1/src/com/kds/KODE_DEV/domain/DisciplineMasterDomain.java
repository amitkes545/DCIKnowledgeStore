package com.kds.KODE_DEV.domain;

public class DisciplineMasterDomain {
	private String DispId;
	private String discipline;
	private String CustId;
	
	public String getDiscplineID() {
		return DispId;
	}
	public void setDisciplineID(String DispId) {
		this.DispId = DispId;
	}
	public String getDiscipline() {
		return discipline;
	}
	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}
	public String getCustomerID() {
		return CustId;
	}
	public void setCustomerID(String CustId) {
		this.CustId = CustId;
	}
}