package com.kds.KODE_DEV.domain;

public class OwnerAControlKStoreDomain {

	private String ksId;
	private String status;
	private String libName;
	private String libId;
	private String fileName;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	private String uploadBy;
	

	
	
	public String getUploadBy() {
		return uploadBy;
	}
	public void setUploadBy(String uploadBy) {
		this.uploadBy = uploadBy;
	}
	public String getKsId() {
		return ksId;
	}
	public void setKsId(String ksId) {
		this.ksId = ksId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLibName() {
		return libName;
	}
	public void setLibName(String libName) {
		this.libName = libName;
	}
	public String getLibId() {
		return libId;
	}
	public void setLibId(String libId) {
		this.libId = libId;
	}
}