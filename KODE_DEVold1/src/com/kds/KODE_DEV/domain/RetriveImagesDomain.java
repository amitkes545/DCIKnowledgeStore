package com.kds.KODE_DEV.domain;

import java.io.InputStream;

public class RetriveImagesDomain {
private String courseName;
private InputStream courseImage;
private String courseImageSize;
private String streamId;
private String streamName;
private String imagePath;

public String getImagePath() {
	return imagePath;
}
public void setImagePath(String imagePath) {
	this.imagePath = imagePath;
}
public String getStreamId() {
	return streamId;
}
public void setStreamId(String streamId) {
	this.streamId = streamId;
}
public String getStreamName() {
	return streamName;
}
public void setStreamName(String streamName) {
	this.streamName = streamName;
}
public String getCourseImageSize() {
	return courseImageSize;
}
public void setCourseImageSize(String courseImageSize) {
	this.courseImageSize = courseImageSize;
}
private byte[] image;
private String courseImageLocation;

public String getCourseImageLocation() {
	return courseImageLocation;
}
public void setCourseImageLocation(String courseImageLocation) {
	this.courseImageLocation = courseImageLocation;
}
public byte[] getImage() {
	return image;
}
public void setImage(byte[] image) {
	this.image = image;
}

public String getCourseName() {
	return courseName;
}
public void setCourseName(String courseName) {
	this.courseName = courseName;
}
public InputStream getCourseImage() {
	return courseImage;
}
public void setCourseImage(InputStream courseImage) {
	this.courseImage = courseImage;
}


}
