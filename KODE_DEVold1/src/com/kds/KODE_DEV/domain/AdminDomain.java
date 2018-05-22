package com.kds.KODE_DEV.domain;

import java.sql.Date;




public class AdminDomain {
	 private String adminname;
	 private String adminid;
	 private String pwd;
	 private String email;
	 private String address;
	 private String phone;
	 private String privilege;
	 private String deptdetails;
	 private String belongs;
	 private String orgdetails;
	 private String orgtype;
	 private String orgid;
	 private String cspecification;
	 private String stream;
	 private String processname;
	 private String processid;
	 private String role_id;
	 private String subject_id;
	 private String created_by;
	 private String group_name;
	 private String new_group_name;
	 private String Assignment_name;
	 private String Assignment_ID;
	 private String FileName;
	 private long SizeInBytes;
	 private String FieldName;
	 private String IndivialStudentId;
	 private String GroupId;
	 private String pathofFile;
	private String country;
	private String state;
	private String city;
	private String postalCode;
	private String gender;
	private Date  dateofbirth;
	
	 public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(Date sqlDate) {
		this.dateofbirth = sqlDate;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getPathofFile() {
		return pathofFile;
	}
	public void setPathofFile(String path) {
		this.pathofFile = path;
	}
	public String getIndivialStudentId() {
		return IndivialStudentId;
	}
	public void setIndivialStudentId(String indivialStudentId) {
		IndivialStudentId = indivialStudentId;
	}
	public String getGroupId() {
		return GroupId;
	}
	public void setGroupId(String groupId) {
		GroupId = groupId;
	}
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	public long getSizeInBytes() {
		return SizeInBytes;
	}
	public void setSizeInBytes(long sizeInBytes) {
		SizeInBytes = sizeInBytes;
	}
	public String getFieldName() {
		return FieldName;
	}
	public void setFieldName(String fieldName) {
		FieldName = fieldName;
	}
	public String getAssignment_name() {
		return Assignment_name;
	}
	public void setAssignment_name(String assignment_name) {
		Assignment_name = assignment_name;
	}
	public String getAssignment_ID() {
		return Assignment_ID;
	}
	public void setAssignment_ID(String Assignment_ID) {
		this.Assignment_ID = Assignment_ID;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	private String Description;
	
	 
	 public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	 public String getNew_Group_name() {
			return new_group_name;
		}
		public void setNew_Group_name(String new_group_name) {
			this.new_group_name = new_group_name;
		}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(String subject_id) {
		this.subject_id = subject_id;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public void setAdminName(String adminname)
	 {
		 this.adminname=adminname;
	 }
	public String getAdminName()
	{
		return adminname;
	}
	public void setAdminId(String adminid)
	{
		this.adminid=adminid;
	}
	public String getAdminId()
	{
		return adminid;
	}
	public void setPwd(String pwd)
	{
		this.pwd=pwd;
	}
	public String getPwd()
	{
		return pwd;
	}
	public void setEmail(String email)
	{
		this.email=email;
	}
	public String getEmail()
	{
		return email;
	}
	public void setAddress(String address)
	{
		this.address=address;
	}
	public String getAddress()
	{
		return address;
	}
	public void setPhone(String phone)
	{
		this.phone=phone;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPrivilege(String privilege)
	{
		this.privilege=privilege;
	}
	public String getPrivilege()
	{
		return privilege;
	}
	public void setDeptDetails(String deptdetails)
	{
		this.deptdetails=deptdetails;
	}
	public String getDeptDetails()
	{
		return deptdetails;
	}
	public void setBelongs(String belongs)
	{
		this.belongs=belongs;
	}
	public String getBelongs()
	{
		return belongs;
	}
	public void setOrgDetails(String orgdetails)
	{
		this.orgdetails=orgdetails;
	}
	public String getOrgDetails()
	{
		return orgdetails;
	}
	public void setOrgType(String orgtype)
	{
		this.orgtype=orgtype;
	}
	public String getOrgType()
	{
		return orgtype;
	}
	public void setOrgid(String orgid){
		this.orgid=orgid;
	} public String getOrgid(){
		return orgid;
	}
	public void setCspecification(String cspecification){
		this.cspecification=cspecification;
	}public String getCspecification(){
		return cspecification;
	}public void setStream(String stream){
		this.stream=stream;
	}
	public String getStream(){
		return stream;
	}public void setProcessname(String processname){
		this.processname=processname;
	}public String getProcessname(){
		return processname;
	}public void setProcessid(String processid){
		this.processid=processid;
	}public String getProcessid(){
		return processid;
	}
	
	
}
	
	
	
	
	
	
	
	
	
	
