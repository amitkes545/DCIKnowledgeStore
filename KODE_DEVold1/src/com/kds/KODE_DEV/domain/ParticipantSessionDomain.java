package com.kds.KODE_DEV.domain;

public class ParticipantSessionDomain {
		private String sessionId;
		private  String  sessionName; 
		private String category;
		private String duration;
		private String sessionStartTiming;
		private String sessionEndTime;
		private String costOfSession;
		private String facultyName;
		private String organizationId;
		private String pathOfFile;
		
		
		public String getSessionId() {
			return sessionId;
		}
		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}
		public String getSessionName() {
			return sessionName;
		}
		public void setSessionName(String sessionName) {
			this.sessionName = sessionName;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getDuration() {
			return duration;
		}
		public void setDuration(String duration) {
			this.duration = duration;
		}
		
		public String getSessionStartTiming() {
			return sessionStartTiming;
		}
		public void setSessionStartTiming(String sessionStartTiming) {
			this.sessionStartTiming = sessionStartTiming;
		}
		public String getSessionEndTime() {
			return sessionEndTime;
		}
		public void setSessionEndTime(String sessionEndTime) {
			this.sessionEndTime = sessionEndTime;
		}
		public String getPathOfFile() {
			return pathOfFile;
		}
		public void setPathOfFile(String pathOfFile) {
			this.pathOfFile = pathOfFile;
		}
		public String getCostOfSession() {
			return costOfSession;
		}
		public void setCostOfSession(String costOfSession) {
			this.costOfSession = costOfSession;
		}
		public String getFacultyName() {
			return facultyName;
		}
		public void setFacultyName(String facultyName) {
			this.facultyName = facultyName;
		}
		public String getOrganizationId() {
			return organizationId;
		}
		public void setOrganizationId(String organizationId) {
			this.organizationId = organizationId;
		}
		
	
}
