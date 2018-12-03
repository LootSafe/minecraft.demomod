package com.lootsafe.mod.util;

public class ServerConfig {
	
	private String privateKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	private String hostAddress = "http://www.example.com/";
	private String hostPort = ":80";
	private String version = "1";
	private String otp = "";
	private boolean debug = true;
	
	private String defGoldAddress = "";
	private String defSilverAddress = "";
	private String defCopperAddress = "";
	
	public ServerConfig()
	{
		System.out.println("[ERROR] - Couldn't load the configuration file. WebRequests will fail.");
	}
	
	public ServerConfig(String privateKey, String hostAddress, String hostPort, String version, String otp, boolean debug, String defGoldAddress, String defSilverAddress, String defCopperAddress)
	{
		this.privateKey = privateKey;
		this.hostAddress = hostAddress;
		this.hostPort = hostPort;
		this.version = version;
		this.otp = otp;
		this.debug = debug;
		
		this.defCopperAddress = defCopperAddress;
		this.defSilverAddress = defSilverAddress;
		this.defGoldAddress = defGoldAddress;
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
	
	public String getDefGoldAddress()
	{
		return this.defGoldAddress;
	}

	public String getDefSilverAddress()
	{
		return this.defSilverAddress;
	}	
	
	public String getDefCopperAddress()
	{
		return this.defCopperAddress;
	}
	
}
