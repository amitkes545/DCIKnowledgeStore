package com.kes.kote.domain;

public class StacsSGPACGPADomain {
	
	private String letterGrade;
	private String description;
	private double gradePoints;
	private double minRange;
	private double maxRange;
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
	public double getGradePoints() {
		return gradePoints;
	}
	public void setGradePoints(String gradePoints) {
		try{ this.gradePoints =Double.valueOf(gradePoints); }catch(Exception ex){}
	}
	public double getMinRange() {
		return minRange;
	}
	public void setMinRange(String minRange) {
		try{ this.minRange =Double.valueOf(minRange); }catch(Exception ex){}
	}
	public double getMaxRange() {
		return maxRange;
	}
	public void setMaxRange(String maxRange) {
		try{ this.maxRange =Double.valueOf(maxRange); }catch(Exception ex){}
	}
	@Override
	public String toString() {
		return "StacsSGPACGPADomain [letterGrade=" + letterGrade + ", description=" + description + ", gradePoints="
				+ gradePoints + ", minRange=" + minRange + ", maxRange=" + maxRange + "]";
	}

	public boolean isValid()
	{
		if(getLetterGrade()!=null && getLetterGrade().trim().length()>0)
			if(getDescription()!=null && getDescription().trim().length()>0)
				if(getGradePoints()>0)
					if(getMinRange()>0)
						if(getMaxRange()>0)
							return true;
		
		return false;
	}
	
	
}
