package com.kes.kote.domain;

public class UseAccessDomain {
	
	private String roleId;
	
	private String moduleId;
	
	private String menuNamelevel1;
	
	private String menuNamelevel2;
	
	private String menuNamelevel3;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getMenuNamelevel1() {
		return menuNamelevel1;
	}

	public void setMenuNamelevel1(String menuNamelevel1) {
		this.menuNamelevel1 = menuNamelevel1;
	}

	public String getMenuNamelevel2() {
		return menuNamelevel2;
	}

	public void setMenuNamelevel2(String menuNamelevel2) {
		this.menuNamelevel2 = menuNamelevel2;
	}

	public String getMenuNamelevel3() {
		return menuNamelevel3;
	}

	public void setMenuNamelevel3(String menuNamelevel3) {
		this.menuNamelevel3 = menuNamelevel3;
	}

	@Override
	public String toString() {
		return "UseAccessDomain [roleId=" + roleId + ", moduleId=" + moduleId + ", menuNamelevel1=" + menuNamelevel1
				+ ", menuNamelevel2=" + menuNamelevel2 + ", menuNamelevel3=" + menuNamelevel3 + "]";
	}

	public boolean isValid()
	{
		if(getRoleId()!=null && getRoleId().length()>0)
			if(getModuleId()!=null && getModuleId().length()>0)
				if(getMenuNamelevel1()!=null && getMenuNamelevel1().length()>0)
					return true;
		
		return false;
	}

}
