package com.lootsafe.mod.util.network;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class CustomNetworkMessage implements IMessage {
	
	private CerealizerHelper cerealizerHelper;
	
	private String playerName;
	private String itemName;
	private int selectedSlotId = -1;
	private int slotId = -1;
	
	public CustomNetworkMessage()
	{
		cerealizerHelper = new CerealizerHelper();
	}
	
	public CustomNetworkMessage(String playerName, String itemName, int selectedSlotId, int slotId) 
	{
		cerealizerHelper = new CerealizerHelper();
		this.playerName = playerName;
		this.itemName = itemName;
		this.selectedSlotId = selectedSlotId;
		this.slotId = slotId;
	}
	
	@Override public void toBytes(ByteBuf buf) 
	{		
		NetworkItemObj networkItemObj = new NetworkItemObj(playerName, itemName, selectedSlotId, slotId);		
		
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
			this.itemName = networkItemObj.getItemAddress();
			this.selectedSlotId = networkItemObj.getSelectedSlotId();
			this.slotId = networkItemObj.getSlotId();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}
	
	public String toString()
	{
		return this.playerName + " : " + this.itemName + " : " + selectedSlotId + " : " + slotId;
	}
	
	public String getPlayerName()
	{
		return this.playerName;
	}
	
	public String getItemName()
	{
		return this.itemName;
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