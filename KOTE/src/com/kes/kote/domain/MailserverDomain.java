package com.kes.kote.domain;

public class MailserverDomain {
	
	private String serverId;
	private String serverName;
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	@Override
	public String toString() {
		return "MailserverDomain [serverId=" + serverId + ", serverName=" + serverName + "]";
	}
	
	

}
