package com.kes.kote.domain;

public class UseNotificationDomain {
	
	private String notificationType;
	
	private String subject;
	
	private String header;
	
	private String body;
	
	private String footer;
	
	private String uidSender;
	
	private String keywords;

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public String getUidSender() {
		return uidSender;
	}

	public void setUidSender(String uidSender) {
		this.uidSender = uidSender;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Override
	public String toString() {
		return "UseNotificationDomain [notificationType=" + notificationType + ", subject=" + subject + ", header="
				+ header + ", body=" + body + ", footer=" + footer + ", uidSender=" + uidSender + ", keywords="
				+ keywords + "]";
	}
	

	public boolean isValid()
	{
		if(getNotificationType()!=null && getNotificationType().length()>0)
			if(getSubject()!=null && getSubject().length()>0)
				if(getHeader()!=null && getHeader().length()>0)
					if(getBody()!=null && getBody().length()>0)
						if(getFooter()!=null && getFooter().length()>0)
							if(getUidSender()!=null &&getUidSender().length()>0)
								return true;
								
		return false;
	}
}
