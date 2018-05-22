package com.kes.kote.domain;

public class UseRoleMapDomain {
	
	private String userId;
	
	private String roleId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UseRoleMapDomain [userId=" + userId + ", roleId=" + roleId + "]";
	}
	
    public boolean isValid()
    {
    	if(getUserId()!=null && getUserId().length()>0)
    		if(getRoleId()!=null && getRoleId().length()>0)
    			return true;
    	
    	return false;
    }
	
}
