package com.lootsafe.mod.util.network;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class CustomNetworkMessage implements IMessage {
	
	private String playerName;
	private String itemAddress;
	private CerealizerHelper cerealizerHelper;
	
	public CustomNetworkMessage()
	{
		cerealizerHelper = new CerealizerHelper();
	}
	
	public CustomNetworkMessage(String playerName, String itemAddress) 
	{
		cerealizerHelper = new CerealizerHelper();
		this.playerName = playerName;
		this.itemAddress = itemAddress;
	}
	
	@Override public void toBytes(ByteBuf buf) 
	{		
		NetworkItemObj networkItemObj = new NetworkItemObj(playerName, itemAddress);		
		
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
	
}