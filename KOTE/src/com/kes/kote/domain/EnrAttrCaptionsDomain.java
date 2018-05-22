package com.kes.kote.domain;

public class EnrAttrCaptionsDomain {
	
	private String caption_id;
	private String attribute_id;
	private String caption_description;
	public String getCaption_id() {
		return caption_id;
	}
	public void setCaption_id(String caption_id) {
		this.caption_id = caption_id;
	}
	public String getAttribute_id() {
		return attribute_id;
	}
	public void setAttribute_id(String attribute_id) {
		this.attribute_id = attribute_id;
	}
	public String getCaption_description() {
		return caption_description;
	}
	public void setCaption_description(String caption_description) {
		this.caption_description = caption_description;
	}
	@Override
	public String toString() {
		return "EnrAttrCaptionsDomain [caption_id=" + caption_id + ", attribute_id=" + attribute_id
				+ ", caption_description=" + caption_description + "]";
	}
	
	

}
