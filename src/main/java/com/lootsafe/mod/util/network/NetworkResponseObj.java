package com.lootsafe.mod.util.network;

import java.io.Serializable;

public class NetworkResponseObj implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int statusCode = 500;
	private int slotId = -1;
	private int selectedSlotId = -1;
	
	public NetworkResponseObj(int statusCode, int selectedSlotId, int slotId)
	{
		this.statusCode = statusCode;
		this.selectedSlotId = selectedSlotId;
		this.slotId = slotId;
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
