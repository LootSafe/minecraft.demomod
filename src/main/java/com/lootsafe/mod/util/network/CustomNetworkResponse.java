package com.lootsafe.mod.util.network;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class CustomNetworkResponse implements IMessage {
	
	private CerealizerHelper cerealizerHelper;
	
	private int statusCode;
	private String itemName;
	
	public CustomNetworkResponse()
	{
		cerealizerHelper = new CerealizerHelper();
	}
	
	public CustomNetworkResponse(int statusCode, String itemName) 
	{
		cerealizerHelper = new CerealizerHelper();
		this.statusCode = statusCode;
		this.itemName = itemName;
	}
	
	@Override public void toBytes(ByteBuf buf) 
	{		
		NetworkResponseObj networkResponseObj = new NetworkResponseObj(statusCode, itemName);		
		
		try 
		{
			buf = cerealizerHelper.cerealizeNetworkResponseObj(networkResponseObj, buf);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override public void fromBytes(ByteBuf buf) 
	{
		NetworkResponseObj networkResponseObj = null;
		
		try 
		{
			networkResponseObj = cerealizerHelper.decerealizeNetworkResponseObj(buf);
			this.statusCode = networkResponseObj.getStatusCode();
			this.itemName = networkResponseObj.getItemName();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
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