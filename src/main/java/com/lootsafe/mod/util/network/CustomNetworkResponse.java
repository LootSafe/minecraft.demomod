package com.lootsafe.mod.util.network;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class CustomNetworkResponse implements IMessage {
	
	private int statusCode;
	private int selectedSlotId;
	private int slotId;
	private CerealizerHelper cerealizerHelper;
	
	public CustomNetworkResponse()
	{
		cerealizerHelper = new CerealizerHelper();
	}
	
	public CustomNetworkResponse(int statusCode, int selectedSlotId, int slotId) 
	{
		cerealizerHelper = new CerealizerHelper();
		this.statusCode = statusCode;
		this.selectedSlotId = selectedSlotId;
		this.slotId = slotId;
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
			this.selectedSlotId = networkResponseObj.getSelectedSlotId();
			this.slotId = networkResponseObj.getSlotId();
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
	
	public int getSlotId()
	{
		return this.slotId;
	}
	
	public int getSelectedSlotId()
	{
		return this.selectedSlotId;
	}
	
}