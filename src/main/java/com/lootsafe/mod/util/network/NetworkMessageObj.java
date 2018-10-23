package com.lootsafe.mod.util.network;

import java.io.Serializable;

public class NetworkMessageObj implements Serializable {
		
	private static final long serialVersionUID = 1L;
	private String playerName;
	private String itemName;
	
	public NetworkMessageObj(String playerName, String itemName)
	{
		this.playerName = playerName;
		this.itemName = itemName;
	}
	
	public String getPlayerName()
	{
		return this.playerName;
	}
	
	public String getItemName()
	{
		return this.itemName;
	}
	
}
