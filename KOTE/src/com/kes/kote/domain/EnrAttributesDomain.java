package com.kes.kote.domain;

public class EnrAttributesDomain {
	
	private String attribute_id;
	private String attribute_name;
	private String attribute_description;
	private String attribute_caption;
	private String attribute_status;
	
	public String getAttribute_id() {
		return attribute_id;
	}
	public void setAttribute_id(String attribute_id) {
		this.attribute_id = attribute_id;
	}
	public String getAttribute_name() {
		return attribute_name;
	}
	public void setAttribute_name(String attribute_name) {
		this.attribute_name = attribute_name;
	}
	public String getAttribute_description() {
		return attribute_description;
	}
	public void setAttribute_description(String attribute_description) {
		this.attribute_description = attribute_description;
	}
	public String getAttribute_caption() {
		return attribute_caption;
	}
	public void setAttribute_caption(String attribute_caption) {
		this.attribute_caption = attribute_caption;
	}
	public String getAttribute_status() {
		return attribute_status;
	}
	public void setAttribute_status(String attribute_status) {
		this.attribute_status = attribute_status;
	}
	@Override
	public String toString() {
		return "ENRAttributesDomain [attribute_id=" + attribute_id + ", attribute_name=" + attribute_name
				+ ", attribute_description=" + attribute_description + ", attribute_caption=" + attribute_caption
				+ ", attribute_status=" + attribute_status + "]";
	}
	
	

}
