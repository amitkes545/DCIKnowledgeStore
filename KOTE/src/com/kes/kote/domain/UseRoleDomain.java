package com.kes.kote.domain;

public class UseRoleDomain {

	private String roleId;
	
	private String description;
	
	private String type;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "UseRoleDomain [roleId=" + roleId + ", description=" + description + ", type=" + type + "]";
	}
	
	public boolean isValid()
	{
		if(getRoleId()!=null && getRoleId().length()>0)
			if(getDescription()!=null && getDescription().length()>0)
				if(getType()!=null && getType().length()>0)
					return true;
		
		return false;
	}
}
