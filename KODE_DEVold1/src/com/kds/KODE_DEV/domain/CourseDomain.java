package com.kds.KODE_DEV.domain;

import java.io.InputStream;
import java.sql.Timestamp;

public class CourseDomain {
	private String course_id;
	private String subject_id;
	private String course_name;
	private String stram;
	private String duration;
	private String subject;
	private String topic;
	private String subtopic;
	private String nsession;
	private String cust_id;
	
	public String getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(String subject_id) {
		this.subject_id = subject_id;
	}
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getStram() {
		return stram;
	}
	public void setStram(String stram) {
		this.stram = stram;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getSubtopic() {
		return subtopic;
	}
	public void setSubtopic(String subtopic) {
		this.subtopic = subtopic;
	}
	public String getNsession() {
		return nsession;
	}
	public void setNsession(String nsession) {
		this.nsession = nsession;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	

}
