package com.kes.kote.domain;

public class CineOrgDetailsDomain {
	
	private String OrgName;
	
	private String OrgLastName;
	
	private String YearofEst;
	
	private String Address;
	
	private String Country;
	
	private String State;
	
	private String City;
	
	private String PinCode;
	
	private String Website;
	
	private String LogoName;
	
	private String LogoSize;
	
	private String ksSpace;
	
	private String spaceUOM;
	
	private String teachingType;
	
	private String currencyCode;
	
	private String dateFormat;

	public String getKsSpace() {
		return ksSpace;
	}

	public void setKsSpace(String ksSpace) {
		this.ksSpace = ksSpace;
	}

	public String getSpaceUOM() {
		return spaceUOM;
	}

	public void setSpaceUOM(String spaceUOM) {
		this.spaceUOM = spaceUOM;
	}

	public String getOrgName() {
		return OrgName;
	}

	public void setOrgName(String orgName) {
		OrgName = orgName;
	}
	
	public String getOrgLastName() {
		return OrgLastName;
	}

	public void setOrgLastName(String orgLName) {
		OrgLastName = orgLName;
	}

	public String getYearofEst() {
		return YearofEst;
	}

	public void setYearofEst(String yearofEst) {
		YearofEst = yearofEst;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getPinCode() {
		return PinCode;
	}

	public void setPinCode(String pinCode) {
		PinCode = pinCode;
	}

	public String getWebsite() {
		return Website;
	}

	public void setWebsite(String website) {
		Website = website;
	}

	public String getLogoName() {
		return LogoName;
	}

	public void setLogoName(String logoName) {
		LogoName = logoName;
	}

	public String getLogoSize() {
		return LogoSize;
	}

	public void setLogoSize(String logoSize) {
		LogoSize = logoSize;
	}

	public String getTeachingType() {
		return teachingType;
	}

	public void setTeachingType(String teachingType) {
		this.teachingType = teachingType;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Override
	public String toString() {
		return "CineOrgDetailsDomain [OrgName=" + OrgName + ", OrgLastName=" + OrgLastName + ", YearofEst=" + YearofEst
				+ ", Address=" + Address + ", Country=" + Country + ", State=" + State + ", City=" + City + ", PinCode="
				+ PinCode + ", Website=" + Website + ", LogoName=" + LogoName + ", LogoSize=" + LogoSize + ", ksSpace="
				+ ksSpace + ", spaceUOM=" + spaceUOM + ", teachingType=" + teachingType + ", currencyCode="
				+ currencyCode + ", dateFormat=" + dateFormat + "]";
	}

	
	
}
