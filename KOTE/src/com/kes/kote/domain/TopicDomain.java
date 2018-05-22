package com.kes.kote.domain;

public class TopicDomain {
	
	private String topic_id;
	private String topic_id_by_ts;
	private String topic_name;
	public String getTopic_id() {
		return topic_id;
	}
	public void setTopic_id(String topic_id) {
		this.topic_id = topic_id;
	}
	public String getTopic_id_by_ts() {
		return topic_id_by_ts;
	}
	public void setTopic_id_by_ts(String topic_id_by_ts) {
		this.topic_id_by_ts = topic_id_by_ts;
	}
	public String getTopic_name() {
		return topic_name;
	}
	public void setTopic_name(String topic_name) {
		this.topic_name = topic_name;
	}
	@Override
	public String toString() {
		return "TopicDomain [topic_id=" + topic_id + ", topic_id_by_ts=" + topic_id_by_ts + ", topic_name=" + topic_name
				+ "]";
	}

	
	
}
