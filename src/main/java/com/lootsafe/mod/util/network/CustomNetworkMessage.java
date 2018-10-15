package com.lootsafe.mod.util.network;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class CustomNetworkMessage implements IMessage {
	
	private String playerName;
	private String itemAddress;
	private int selectedSlotId = -1;
	private int slotId = -1;
	private CerealizerHelper cerealizerHelper;
	
	public CustomNetworkMessage()
	{
		cerealizerHelper = new CerealizerHelper();
	}
	
	public CustomNetworkMessage(String playerName, String itemAddress, int selectedSlotId, int slotId) 
	{
		cerealizerHelper = new CerealizerHelper();
		this.playerName = playerName;
		this.itemAddress = itemAddress;
		this.selectedSlotId = selectedSlotId;
		this.slotId = slotId;
	}
	
	@Override public void toBytes(ByteBuf buf) 
	{		
		NetworkItemObj networkItemObj = new NetworkItemObj(playerName, itemAddress, selectedSlotId, slotId);		
		
		try 
		{
			buf = cerealizerHelper.cerealizeNetworkItemObj(networkItemObj, buf);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override public void fromBytes(ByteBuf buf) 
	{
		NetworkItemObj networkItemObj = null;
		
		try 
		{
			networkItemObj = cerealizerHelper.decerealizeNetworkItemObj(buf);
			this.playerName = networkItemObj.getPlayerName();
			this.itemAddress = networkItemObj.getItemAddress();
			this.selectedSlotId = networkItemObj.getSelectedSlotId();
			this.slotId = networkItemObj.getSelectedSlotId();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}
	
	public String getPlayerName()
	{
		return this.playerName;
	}
	
	public String getItemAddress()
	{
		return this.itemAddress;
	}

	public int getSelectedSlotId()
	{
		return this.selectedSlotId;
	}

	public int getSlotId() 
	{
		return this.slotId;
	}
	
}