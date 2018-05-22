package com.kes.kote.domain;

public class UseUserDomain {

	private String userId;

	private String firstName;

	private String lastName;

	private String designation;

	private String department;

	private String address;

	private String city;

	private String state;

	private String country;

	private String postal;

	private String dob;

	private String gender;

	private String mobile;

	private String landline;

	private String extension;

	private String email;

	private String mailServer;

	private String space;

	private String uom;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMailServer() {
		return mailServer;
	}

	public void setMailServer(String mailServer) {
		this.mailServer = mailServer;
	}

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	@Override
	public String toString() {
		return "UseUserDomain [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", designation=" + designation + ", department=" + department + ", address=" + address + ", city="
				+ city + ", state=" + state + ", country=" + country + ", postal=" + postal + ", dob=" + dob
				+ ", gender=" + gender + ", mobile=" + mobile + ", landline=" + landline + ", extension=" + extension
				+ ", email=" + email + ", mailServer=" + mailServer + ", space=" + space + ", uom=" + uom + "]";
	}

	public boolean isValid()
	{
		if(getUserId()!=null && getUserId().length()>0)
			if(getFirstName()!=null && getFirstName().length()>0)
				if(getEmail()!=null && getEmail().length()>0)
					if(getAddress()!=null && getAddress().length()>0)		
						if(getMobile()!=null && getMobile().length()>0)
							return true;
		
		/*
		 * if(getLastName()!=null && getLastName().length()>0)
			if(getCity()!=null && getCity().length()>0)
				if(getState()!=null && getState().length()>0)
					if(getCountry()!=null && getCountry().length()>0)
						if(getPostal()!=null && getPostal().length()>0)
							if(getDob()!=null && getDob().length()>0)
								if(getGender()!=null && getGender().length()>0)
									if(getLandline()!=null && getLandline().length()>0)
										if(getExtension()!=null && getExtension().length()>0)
											if(getDesignation()!=null && getDesignation().length()>0)
												if(getDepartment()!=null && getDepartment().length()>0)
													if(getMailServer()!=null && getMailServer().length()>0)
														if(getSpace()!=null && getSpace().length()>0)
															if(getUom()!=null && getUom().length()>0)
								
		*/
												
		return false;	
	}
	
}
