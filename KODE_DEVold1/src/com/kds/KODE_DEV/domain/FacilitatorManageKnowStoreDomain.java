package com.kds.KODE_DEV.domain;

import java.util.Calendar;

public class FacilitatorManageKnowStoreDomain {

	private String libId;
	private String ksId;
	private String libName;
	private int libSize;
	private String userId;
	private String orgId;
	private String studentId;
	private String FileName;
	private String UploadedBy;
	private String spaceUom;
	private String courseId;
	private String subjectId;
	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	private String subjectName;

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	private String department_name;
	
	
	
	public String getSpaceUom() {
		return spaceUom;
	}

	public void setSpaceUom(String spaceUom) {
		this.spaceUom = spaceUom;
	}

	public String getUploadedBy() {
		return UploadedBy;
	}

	public void setUploadedBy(String UploadedBy) {
		this.UploadedBy = UploadedBy;
	}
	public String getFileName() {
		return FileName;
	}

	public void setFileName(String FileName) {
		this.FileName = FileName;
	}
	public String getLibId() {
		return libId;
	}

	public void setLibId(String libId) {
		this.libId = libId;
	}

	public String getKsId() {
		return ksId;
	}

	public void setKsId(String ksId) {
		this.ksId = ksId;
	}

	public String getLibName() {
		return libName;
	}

	public void setLibName(String libName) {
		this.libName = libName;
	}

	public int getLibSize() {
		return libSize;
	}

	public void setLibSize(int libSize) {
		this.libSize = libSize;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	//addded by dinesh for delete 17-11-2017
		public java.sql.Timestamp getTimeStamp()
		   {
		     java.sql.Timestamp ret=null;
		     try {
		       Calendar calendar = Calendar.getInstance();
		       java.util.Date now = calendar.getTime();
		       ret = new java.sql.Timestamp(now.getTime());
		       
		     }
		     catch(Exception ex){ex.printStackTrace();}
		     
		     return ret;
		   }
}