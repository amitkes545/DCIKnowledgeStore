package com.kes.kote.domain;

public class MenuDomain {

	private String moduleId;
	private String menuLevel;
	private String parentMenuId;
	private String menuName;
	private String menuSequence;
	private String menuScrTitle;
	private String menuUrl;
	private String wfItemId;
	private String status;

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuSequence() {
		return menuSequence;
	}

	public void setMenuSequence(String menuSequence) {
		this.menuSequence = menuSequence;
	}

	public String getMenuScrTitle() {
		return menuScrTitle;
	}

	public void setMenuScrTitle(String menuScrTitle) {
		this.menuScrTitle = menuScrTitle;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getWfItemId() {
		return wfItemId;
	}

	public void setWfItemId(String wfItemId) {
		this.wfItemId = wfItemId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
