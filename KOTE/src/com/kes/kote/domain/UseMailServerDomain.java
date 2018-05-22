package com.kes.kote.domain;

public class UseMailServerDomain {
	
	private String name;
	
	private String address;
	
	private String host;
	
	private String port;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "UseMailServerDomain [name=" + name + ", address=" + address + ", host=" + host + ", port=" + port + "]";
	}
	
	
	public boolean isValid()
	{
		if(getName()!=null && getName().length()>0)
			if(getAddress()!=null && getAddress().length()>0)
				if(getHost()!=null && getHost().length()>0)
					if(getPort()!=null && getPort().length()>0)
						return true;
		return false;
	}

}
