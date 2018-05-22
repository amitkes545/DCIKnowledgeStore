package com.kds.KODE_DEV.domain;

public class SubTopicInfoDomain {
	
	private String subTopic;
	
	private String subTopicId;
	
	private String topicId;

	public String getTopicId() {
		return topicId;
	}

	
	private String date;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getSubTopic() {
		return subTopic;
	}

	public void setSubTopic(String subTopic) {
		this.subTopic = subTopic;
	}

	public String getSubTopicId() {
		return subTopicId;
	}

	public void setSubTopicId(String subTopicId) {
		this.subTopicId = subTopicId;
	}

}
