package com.kes.kote.domain;

public class SubTopicDomain {

	private String subTopic_id;
	private String subTopic_id_by_ts;
	private String subTopic_name;
	public String getSubTopic_id() {
		return subTopic_id;
	}
	public void setSubTopic_id(String subTopic_id) {
		this.subTopic_id = subTopic_id;
	}
	public String getSubTopic_id_by_ts() {
		return subTopic_id_by_ts;
	}
	public void setSubTopic_id_by_ts(String subTopic_id_by_ts) {
		this.subTopic_id_by_ts = subTopic_id_by_ts;
	}
	public String getSubTopic_name() {
		return subTopic_name;
	}
	public void setSubTopic_name(String subTopic_name) {
		this.subTopic_name = subTopic_name;
	}
	@Override
	public String toString() {
		return "SubTopicDomain [subTopic_id=" + subTopic_id + ", subTopic_id_by_ts=" + subTopic_id_by_ts
				+ ", subTopic_name=" + subTopic_name + "]";
	}
	
	
}
