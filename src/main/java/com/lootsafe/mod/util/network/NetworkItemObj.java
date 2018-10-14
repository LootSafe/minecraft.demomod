package com.lootsafe.mod.util.network;

import java.io.Serializable;

public class NetworkItemObj implements Serializable {
		
	private static final long serialVersionUID = 1L;
	private String playerName;
	private String itemAddress;
	
	public NetworkItemObj(String playerName, String itemAddress)
	{
		this.playerName = playerName;
		this.itemAddress = itemAddress;
	}
	
	public String getPlayerName()
	{
		return this.playerName;
	}
	
	public String getItemAddress()
	{
		return this.itemAddress;
	}
	
}
