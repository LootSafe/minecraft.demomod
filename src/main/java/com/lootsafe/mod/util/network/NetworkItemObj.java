package com.lootsafe.mod.util.network;

import java.io.Serializable;

public class NetworkItemObj implements Serializable {
		
	private static final long serialVersionUID = 1L;
	private String playerName;
	private String itemName;
	
	public NetworkItemObj(String playerName, String itemName)
	{
		this.playerName = playerName;
		this.itemName = itemName;
	}
	
	public String getPlayerName()
	{
		return this.playerName;
	}
	
	public String getItemAddress()
	{
		return this.itemName;
	}
	
}
