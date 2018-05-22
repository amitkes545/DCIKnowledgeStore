package com.kds.KODE_DEV.domain;

public class TopicInfoDomain {
	
	private String topic;
	
	private String topicId;
	
	private String sutopicId;
	public String getSutopicId() {
		return sutopicId;
	}

	public void setSutopicId(String sutopicId) {
		this.sutopicId = sutopicId;
	}

	private String subtopic;


	public String getSubtopic() {
		return subtopic;
	}

	public void setSubtopic(String subtopic) {
		this.subtopic = subtopic;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

}
