package com.kes.kote.domain;

public class CineSpocsDomain {

	private String DeptName;
	
	private  String SPOCName;
	
	private  String Disgn;
	
	private  String MobNo;
	
	private  String LLNo;
	
	private  String ExtNo;
	
	private  String FaxNo;
	
	private  String Email;

	public String getDeptName() {
		return DeptName;
	}

	public void setDeptName(String deptName) {
		DeptName = deptName;
	}

	public String getSPOCName() {
		return SPOCName;
	}

	public void setSPOCName(String sPOCName) {
		SPOCName = sPOCName;
	}

	public String getDisgn() {
		return Disgn;
	}

	public void setDisgn(String disgn) {
		Disgn = disgn;
	}

	public String getMobNo() {
		return MobNo;
	}

	public void setMobNo(String mobNo) {
		MobNo = mobNo;
	}

	public String getLLNo() {
		return LLNo;
	}

	public void setLLNo(String lLNo) {
		LLNo = lLNo;
	}

	public String getExtNo() {
		return ExtNo;
	}

	public void setExtNo(String extNo) {
		ExtNo = extNo;
	}

	public String getFaxNo() {
		return FaxNo;
	}

	public void setFaxNo(String faxNo) {
		FaxNo = faxNo;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
	
	
	
	@Override
	public String toString() {
		return "CineSpocsDomain [DeptName=" + DeptName + ", SPOCName=" + SPOCName + ", Disgn=" + Disgn + ", MobNo="
				+ MobNo + ", LLNo=" + LLNo + ", ExtNo=" + ExtNo + ", FaxNo=" + FaxNo + ", Email=" + Email + "]";
	}

	public boolean isValid()
	{
		if(getDeptName()!=null && getDeptName().length()>0)
			if(getSPOCName()!=null && getSPOCName().length()>0)
				if(getDisgn()!=null && getDisgn().length()>0)
					if(getMobNo()!=null && getMobNo().length()>0)
						if(getEmail()!=null && getEmail().length()>0)
							return true;
		
		/*if(getLLNo()!=null && getLLNo().length()>0)
			if(getExtNo()!=null && getExtNo().length()>0)
				if(getFaxNo()!=null && getFaxNo().length()>0)
					if(getFaxNo()!=null && getFaxNo().length()>0)
		*/
		return false;
	}
}
