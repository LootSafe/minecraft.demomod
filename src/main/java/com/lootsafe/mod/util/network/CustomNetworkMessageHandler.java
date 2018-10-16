package com.lootsafe.mod.util.network;

import com.lootsafe.mod.Main;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CustomNetworkMessageHandler implements IMessageHandler<CustomNetworkMessage, IMessage> {

	@Override public IMessage onMessage(CustomNetworkMessage message, MessageContext ctx) 
	{		
		String playerName = message.getPlayerName();
		String itemName = message.getItemName();
		int selectedSlotId = message.getSelectedSlotId();
		int selectedSlot = message.getSlotId();
		
		boolean success = Main.proxy.addTokenizedItemStr(playerName, itemName);		
		
		int statusCode = 500;
		
		if(success){
			statusCode = 200;			
			System.out.println("API Success! " + playerName + " : " + itemName + " : " + statusCode);
		} 
		
		return new CustomNetworkResponse(statusCode, selectedSlotId, selectedSlot);		
	}
	
}