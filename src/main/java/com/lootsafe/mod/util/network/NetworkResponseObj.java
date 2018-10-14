package com.lootsafe.mod.util.network;

import java.io.Serializable;

public class NetworkResponseObj implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int statusCode = 500;
	
	public NetworkResponseObj(int statusCode)
	{
		this.statusCode = statusCode;
	}
	
	public int getStatusCode()
	{
		return this.statusCode;
	}
	
}
