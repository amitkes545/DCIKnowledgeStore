package com.kes.kote.domain;

public class FiseFCSDomain {

	private String category;
	private String subCategory;
	private String assign;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	
	public String getAssign() {
		return assign;
	}
	public void setAssign(String assign) {
		this.assign = assign;
	}
	
	
	@Override
	public String toString() {
		return "FiseFCSDomain [category=" + category + ", subCategory=" + subCategory + ", assign=" + assign + "]";
	}
	public boolean isValid()
	{
		if(getCategory()!=null && getCategory().length()>0)
			if(getSubCategory()!=null && getSubCategory().length()>0)
				if(getAssign()!=null && getAssign().trim().length()>0 && getAssign().trim().equalsIgnoreCase("Yes"))
					return true;
			
		
					
		return false;
	}
}
