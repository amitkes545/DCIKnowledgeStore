package com.kes.kote.domain;

public class CourseInfoDomain {
	
	private String courseID;
	private String courseName;
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	@Override
	public String toString() {
		return "CourseInfoDomain [courseID=" + courseID + ", courseName=" + courseName + "]";
	}
	
	

}
