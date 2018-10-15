package com.lootsafe.mod.util.network;

import java.io.Serializable;

public class NetworkItemObj implements Serializable {
		
	private static final long serialVersionUID = 1L;
	private String playerName;
	private String itemAddress;
	private int selectedSlotId = -1;
	private int slotId = -1;
	
	public NetworkItemObj(String playerName, String itemAddress, int selectedSlotId, int slotId)
	{
		this.playerName = playerName;
		this.itemAddress = itemAddress;
		this.selectedSlotId = selectedSlotId;
		this.slotId = slotId;
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
