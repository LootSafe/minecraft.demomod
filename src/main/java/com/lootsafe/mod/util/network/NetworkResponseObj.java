package com.lootsafe.mod.util.network;

import java.io.Serializable;

public class NetworkResponseObj implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int statusCode = 500;
	private String itemName;
	
	public NetworkResponseObj(int statusCode, String itemName)
	{
		this.statusCode = statusCode;
		this.itemName = itemName;
	}
	
	public int getStatusCode()
	{
		return this.statusCode;
	}
	
	public String getItemName()
	{
		return this.itemName;
	}
	
}
