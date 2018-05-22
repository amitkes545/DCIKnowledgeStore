package com.kes.kote.domain;

public class StacsSubjectsDomain {
	
	

	private String SubjectId;
	
	private String SubjectName;

	public String getSubjectId() {
		return SubjectId;
	}

	public void setSubjectId(String subjectId) {
		SubjectId = subjectId;
	}

	public String getSubjectName() {
		return SubjectName;
	}

	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}
	
	@Override
	public String toString() {
		return "StacsSubjectsDomain [SubjectId=" + SubjectId + ", SubjectName=" + SubjectName + "]";
	}
	
	public boolean isValid()
	{
		if(getSubjectId()!=null && getSubjectId().length()>0)
			if(getSubjectName()!=null && getSubjectName().length()>0)
				return true;
		
		return false;
	}
}
