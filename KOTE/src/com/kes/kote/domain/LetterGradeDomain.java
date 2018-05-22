package com.kes.kote.domain;

public class LetterGradeDomain {

	private String letterGrade;
	private String description;
	public String getLetterGrade() {
		return letterGrade;
	}
	public void setLetterGrade(String letterGrade) {
		this.letterGrade = letterGrade;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "LetterGradeDomain [letterGrade=" + letterGrade + ", description=" + description + "]";
	}
	
	
}
