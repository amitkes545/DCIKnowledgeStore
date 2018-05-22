package com.kds.KODE_DEV.domain;


/**
 * Servlet implementation class DisplayDomain
 */

public class DisplayDomain  {
	
	private String org_id;
	private String departId;
	private String departName;
	private String stream;
	
	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public String getOrg_id() {
		return org_id;
	}
       
}
