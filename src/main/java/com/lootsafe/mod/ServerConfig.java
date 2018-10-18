package com.lootsafe.mod;

public class ServerConfig {
	
	private String privateKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	private String hostAddress = "http://www.example.com/";
	private String hostPort = ":80";
	private String version = "1";
	private String otp = "";
	private boolean debug = true;
	
	public ServerConfig(){}
	
	public ServerConfig(String privateKey, String hostAddress, String hostPort, String version, String otp, boolean debug)
	{
		this.privateKey = privateKey;
		this.hostAddress = hostAddress;
		this.hostPort = hostPort;
		this.version = version;
		this.otp = otp;
		this.debug = debug;
	}
	
	public String getPrivateKey()
	{
		return this.privateKey;
	}
	
	public String getHostAddress()
	{
		return this.hostAddress;
	}
	
	public String getHostPort()
	{
		return this.hostPort;
	}
	
	public String getVersion() {
		return this.version;
	}

	public String getOtp() {
		return this.otp;
	}
	
	public boolean getDebug() {
		return this.debug;
	}	
	
	public String getFullAddress()
	{
		return this.hostAddress + this.hostPort;
	}
	
}
