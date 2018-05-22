package com.kds.KODE_DEV.domain;

public class DisplayCoursesDomain {
 private String courseName;
 private String imagePath;
 private String courseDetails;
 private String courseFee;
 private String uploadby;
 
 private String courseId;
 private String subjectId;
 
 public String getUploadby() {
	return uploadby;
}
public void setUploadby(String uploadby) {
	this.uploadby = uploadby;
}
public String getCourseId() {
	return courseId;
}
public void setCourseId(String courseId) {
	this.courseId = courseId;
}
public String getSubjectId() {
	return subjectId;
}
public void setSubjectId(String subjectId) {
	this.subjectId = subjectId;
}
public String getUploady() {
	return uploadby;
}
public void setUploady(String uploadby) {
	this.uploadby = uploadby;
}
public String getCourseFee() {
	return courseFee;
}
public void setCourseFee(String courseFee) {
	this.courseFee = courseFee;
}
public String getCourseDetails() {
	return courseDetails;
}

public void setCourseDetails(String courseDetails) {
	this.courseDetails = courseDetails;
}



public String getImagePath() {
	return imagePath;
}

public void setImagePath(String imagePath) {
	this.imagePath = imagePath;
}

public String getCourseName() {
	return courseName;
}

public void setCourseName(String courseName) {
	this.courseName = courseName;
}
 
 
}
