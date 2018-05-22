package com.kds.KODE_DEV.domain;

import java.io.InputStream;
import java.sql.Timestamp;




public class CreateDomain {
	private String org_id;
	private String org_name;
	private String org_type;
	private String org_type_name;
	private String address;
	private String city;
	private String country;
	private String pcode;
	private String phone;
	private String fax;
	private String ecno;
	private String email_id;
	private String url;
	private String logo;
	private String yof;
	private String belongs;
	private Timestamp date;
	private String process_id;
	private String process_name;
	private String state;
	private InputStream Image;
	private long ImageSize;
	private String ImageName;
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_Type_Name(String org_type_name) {
		this.org_type_name = org_type_name;
	}

	public String getOrg_Type_Name() {
		return org_type_name;
	}
	public void setOrg_type(String org_type) {
		this.org_type = org_type;
	}

	public String getOrg_type() {
		return org_type;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry() {
		return country;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFax() {
		return fax;
	}
	public void setEcno(String ecno) {
		this.ecno = ecno;
	}

	public String getEcno() {
		return ecno;
	}

	

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getEmail_id() {
		return email_id;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
	public void setLogo(String logo) {
		this.logo =  logo;
	}

	public String getLogo() {
		return logo;
	}
	public void setYof(String yof) {
		this.yof = yof;
	}

	public String getYof() {
		return yof;
	}
	public void setBelongs(String belongs) {
		this.belongs = belongs;
	}

	public String getBelongs() {
		return belongs;
	}
	public void setDate(Timestamp timestamp) {
		this.date = timestamp;
	}

	public Timestamp getDate() {
		return date;
	}
	public void setProcess_id(String process_id){
		this.process_id=process_id;
	}
	public String getProcess_id(){
		return process_id;
	}
	public void setState(String state){
		this.state=state;
	}
	public String getState(){
		return state;
	}
	public void setProcess_name(String process_name){
		this.process_name=process_name;
	}
	public String getProcess_name(){
		return process_name;
	}
	public void setImageName(String ImageName){
		this.ImageName=ImageName;
	}
	public String getImageName(){
		return ImageName;
	}
	public void setImage(InputStream Image){
		this.Image=Image;
	}
	public InputStream getImage(){
		return Image;
	}
	public void setImageSize(long sizeInBytes){
		this.ImageSize=sizeInBytes;
	}
	public long getImageSize(){
		return ImageSize;
	}

}
