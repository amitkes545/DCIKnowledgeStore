package com.kes.kote.domain;

public class FiseFPMDomain {

	private String gatewayId;
	private String gatewayName;
	private String gatewayURL;
	private String parameterList;
	public String getGatewayId() {
		return gatewayId;
	}
	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}
	public String getGatewayName() {
		return gatewayName;
	}
	public void setGatewayName(String gatewayName) {
		this.gatewayName = gatewayName;
	}
	public String getGatewayURL() {
		return gatewayURL;
	}
	public void setGatewayURL(String gatewayURL) {
		this.gatewayURL = gatewayURL;
	}
	public String getParameterList() {
		return parameterList;
	}
	public void setParameterList(String parameterList) {
		this.parameterList = parameterList;
	}
	@Override
	public String toString() {
		return "FiseFPMDomain [gatewayId=" + gatewayId + ", gatewayName=" + gatewayName + ", gatewayURL=" + gatewayURL
				+ ", parameterList=" + parameterList + "]";
	}
	
	public boolean isValid()
	{
		if(getGatewayId()!=null && getGatewayId().length()>0)
			if(getGatewayName()!=null && getGatewayName().length()>0)
				if(getGatewayURL()!=null && getGatewayURL().length()>0)
					if(getParameterList()!=null && getParameterList().length()>0)
						return true;
		
		return false;
	}
}
