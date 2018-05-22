package com.kds.KODE_DEV.domain;

import java.io.Serializable;
import java.sql.Array;
import java.sql.Timestamp;
import java.util.Date;

public class ClassWorkFileDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String uploadedBy;
	private Date uploadDate;
	private Timestamp uploadTime;
	private String fileName;
	private String filePath;
	private String sesID;
	
	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Timestamp getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSessionID() {
		return sesID;
	}

	public void setSessionID(String sesID) {
		this.sesID = sesID;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}