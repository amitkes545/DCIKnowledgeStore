package com.kes.kote.domain;

public class FiseFeeMapDomain {

	private String feeCode;
	private String feeDesc;
	private String prefix;
	private String body;
	private String suffix;
	private String instFeeDesc;
	private String instFeeCode;
	public String getFeeCode() {
		return feeCode;
	}
	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
	}
	public String getFeeDesc() {
		return feeDesc;
	}
	public void setFeeDesc(String feeDesc) {
		this.feeDesc = feeDesc;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getInstFeeDesc() {
		return instFeeDesc;
	}
	public void setInstFeeDesc(String instFeeDesc) {
		this.instFeeDesc = instFeeDesc;
	}
	public String getInstFeeCode() {
		return instFeeCode;
	}
	public void setInstFeeCode(String instFeeCode) {
		this.instFeeCode = instFeeCode;
	}
	@Override
	public String toString() {
		return "FiseFeeMapDomain [feeCode=" + feeCode + ", feeDesc=" + feeDesc + ", prefix=" + prefix + ", body=" + body
				+ ", suffix=" + suffix + ", instFeeDesc=" + instFeeDesc + ", instFeeCode=" + instFeeCode + "]";
	}
	
	public boolean isValidforInst()
	{
		if(getFeeCode()!=null && getFeeCode().length()>0)
			if(getFeeDesc()!=null && getFeeDesc().length()>0)
				if(getPrefix()!=null && getPrefix().length()>0)
					if(getBody()!=null && getBody().length()>0)
						if(getSuffix()!=null && getSuffix().length()>0)
							if(getInstFeeDesc()!=null && getInstFeeDesc().length()>0)
								if(getInstFeeCode()!=null && getInstFeeCode().length()>0)
									return true;
		return false;
	}
	
	public boolean isValidFeeComp()
	{
	
		if(getFeeCode()!=null && getFeeCode().length()>0)
			if(getFeeDesc()!=null && getFeeDesc().length()>0)
				return true;
		
		return false;
	}
}
