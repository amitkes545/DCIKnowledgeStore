package com.kes.kote.domain;

public class StacsTopicsSubTopicDomain {
	
	private String SubjectId;
	
	private String TopicId;
	
	private String TopicName;

	private String SubTopicId;
	
	private String SubTopicName;

	public String getSubjectId() {
		return SubjectId;
	}

	public void setSubjectId(String subjectId) {
		SubjectId = subjectId;
	}

	public String getTopicId() {
		return TopicId;
	}

	public void setTopicId(String topicId) {
		TopicId = topicId;
	}

	public String getTopicName() {
		return TopicName;
	}

	public void setTopicName(String topicName) {
		TopicName = topicName;
	}

	public String getSubTopicId() {
		return SubTopicId;
	}

	public void setSubTopicId(String subTopicId) {
		SubTopicId = subTopicId;
	}

	public String getSubTopicName() {
		return SubTopicName;
	}

	public void setSubTopicName(String subTopicName) {
		SubTopicName = subTopicName;
	}

	@Override
	public String toString() {
		return "StacsTopicsSubTopicDomain [SubjectId=" + SubjectId + ", TopicId=" + TopicId + ", TopicName=" + TopicName
				+ ", SubTopicId=" + SubTopicId + ", SubTopicName=" + SubTopicName + "]";
	}
	
	public boolean isValid()
	{
		if(getSubjectId()!=null && getSubjectId().length()>0)
			if(getTopicId()!=null && getTopicId().length()>0)
				if(getTopicName()!=null && getTopicName().length()>0)
					if(getSubTopicId()!=null && getSubTopicId().length()>0)
						if(getSubTopicName()!=null && getSubTopicName().length()>0)
							return true;
		return false;
	}
	
}
