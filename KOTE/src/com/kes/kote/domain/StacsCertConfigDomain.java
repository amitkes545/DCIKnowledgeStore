package com.kes.kote.domain;

public class StacsCertConfigDomain {

	private String CourseID;
	
	private String GreetingText;
	
	private String BodyText;
	
	private String WishingText;
	
	private String FooterText;
	
	private String AuthorizedSign;
	
	private String DigitalSign;
	
	private String CertificateSize;
	
	private String CertificateLayout;
	
	private String BorderImage;
	
	private String CornerImage;
	
	private String WaterMarkImage;

	public String getCourseID() {
		return CourseID;
	}

	public void setCourseID(String courseID) {
		CourseID = courseID;
	}

	public String getGreetingText() {
		return GreetingText;
	}

	public void setGreetingText(String greetingText) {
		GreetingText = greetingText;
	}

	public String getBodyText() {
		return BodyText;
	}

	public void setBodyText(String bodyText) {
		BodyText = bodyText;
	}

	public String getWishingText() {
		return WishingText;
	}

	public void setWishingText(String wishingText) {
		WishingText = wishingText;
	}

	public String getFooterText() {
		return FooterText;
	}

	public void setFooterText(String footerText) {
		FooterText = footerText;
	}

	public String getAuthorizedSign() {
		return AuthorizedSign;
	}

	public void setAuthorizedSign(String authorizedSign) {
		AuthorizedSign = authorizedSign;
	}

	public String getDigitalSign() {
		return DigitalSign;
	}

	public void setDigitalSign(String digitalSign) {
		DigitalSign = digitalSign;
	}

	public String getCertificateSize() {
		return CertificateSize;
	}

	public void setCertificateSize(String certificateSize) {
		CertificateSize = certificateSize;
	}

	public String getCertificateLayout() {
		return CertificateLayout;
	}

	public void setCertificateLayout(String certificateLayout) {
		CertificateLayout = certificateLayout;
	}

	public String getBorderImage() {
		return BorderImage;
	}

	public void setBorderImage(String borderImage) {
		BorderImage = borderImage;
	}

	public String getCornerImage() {
		return CornerImage;
	}

	public void setCornerImage(String cornerImage) {
		CornerImage = cornerImage;
	}

	public String getWaterMarkImage() {
		return WaterMarkImage;
	}

	public void setWaterMarkImage(String waterMarkImage) {
		WaterMarkImage = waterMarkImage;
	}

	@Override
	public String toString() {
		return "StacsCertConfigDomain [CourseID=" + CourseID + ", GreetingText=" + GreetingText + ", BodyText="
				+ BodyText + ", WishingText=" + WishingText + ", FooterText=" + FooterText + ", AuthorizedSign="
				+ AuthorizedSign + ", DigitalSign=" + DigitalSign + ", CertificateSize=" + CertificateSize
				+ ", CertificateLayout=" + CertificateLayout + ", BorderImage=" + BorderImage + ", CornerImage="
				+ CornerImage + ", WaterMarkImage=" + WaterMarkImage + "]";
	}

	
	public boolean isValid()
	{
		if(getCourseID()!=null &&getCourseID().length()>0)
			if(getGreetingText()!=null && getGreetingText().length()>0)
				if(getBodyText()!=null && getBodyText().length()>0)
					if(getWishingText()!=null && getWishingText().length()>0)
						if(getFooterText()!=null && getFooterText().length()>0)
							if(getAuthorizedSign()!=null && getAuthorizedSign().length()>0)
								if(getCertificateSize()!=null && getCertificateSize().length()>0)
										if(getCertificateLayout()!=null && getCertificateLayout().length()>0)
											return true;
		
		/*if(getBorderImage()!=null && getBorderImage().length()>0)
			if(getCornerImage()!=null && getCornerImage().length()>0)
				if(getWaterMarkImage()!=null && getWaterMarkImage().length()>0)
				if(getDigitalSign()!=null && getDigitalSign().length()>0)
		*/
		
		return false;
	}
	
	
}
