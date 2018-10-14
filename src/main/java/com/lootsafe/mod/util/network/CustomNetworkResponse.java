package com.lootsafe.mod.util.network;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class CustomNetworkResponse implements IMessage {
	
	private int statusCode;
	private CerealizerHelper cerealizerHelper;
	
	public CustomNetworkResponse()
	{
		cerealizerHelper = new CerealizerHelper();
	}
	
	public CustomNetworkResponse(int statusCode) 
	{
		cerealizerHelper = new CerealizerHelper();
		this.statusCode = statusCode;
	}
	
	@Override public void toBytes(ByteBuf buf) 
	{		
		NetworkResponseObj networkResponseObj = new NetworkResponseObj(statusCode);		
		
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
	
}