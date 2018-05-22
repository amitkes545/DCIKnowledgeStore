package com.kes.kote.domain;

public class UseParamDomain {
	
	private String description;
	
	private String key;
	
	private String value;
	
	private String type;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "UseParamDomain [description=" + description + ", key=" + key + ", value=" + value + ", type=" + type
				+ "]";
	}
	
	public boolean isValid()
	{
		if(getDescription()!=null && getDescription().length()>0)
			if(getKey()!=null && getKey().length()>0)
				if(getValue()!=null && getValue().length()>0)
					if(getType()!=null && getType().length()>0)
						return true;
		
		return false;
	}

}
