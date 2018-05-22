package com.kes.kote.domain;

public class StampNMTDomain {
	
	private String NotificationType;
	
	private String Subject;
	
	private String Header;
	
	private String Body;
	
	private String Footer;
	
	private String UIDOfSender;
	
	private String keyValForRef;

	public String getNotificationType() {
		return NotificationType;
	}

	public void setNotificationType(String notificationType) {
		NotificationType = notificationType;
	}

	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	public String getHeader() {
		return Header;
	}

	public void setHeader(String header) {
		Header = header;
	}

	public String getBody() {
		return Body;
	}

	public void setBody(String body) {
		Body = body;
	}

	public String getFooter() {
		return Footer;
	}

	public void setFooter(String footer) {
		Footer = footer;
	}

	public String getUIDOfSender() {
		return UIDOfSender;
	}

	public void setUIDOfSender(String uIDOfSender) {
		UIDOfSender = uIDOfSender;
	}

	public String getKeyValForRef() {
		return keyValForRef;
	}

	public void setKeyValForRef(String keyValForRef) {
		this.keyValForRef = keyValForRef;
	}

	@Override
	public String toString() {
		return "StampNMTDomain [NotificationType=" + NotificationType + ", Subject=" + Subject + ", Header=" + Header
				+ ", Body=" + Body + ", Footer=" + Footer + ", UIDOfSender=" + UIDOfSender + ", keyValForRef="
				+ keyValForRef + "]";
	}
	
	
}
