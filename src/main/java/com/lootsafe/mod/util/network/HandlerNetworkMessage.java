package com.lootsafe.mod.util.network;

import com.lootsafe.mod.Main;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class HandlerNetworkMessage implements IMessageHandler<NetworkMessage, IMessage> {

	@Override public IMessage onMessage(NetworkMessage message, MessageContext ctx) 
	{		
		String playerName = message.getPlayerName();
		String itemName = message.getItemName();
		
		boolean success = Main.proxy.addTokenizedItemStr(playerName, itemName);		
		
		int statusCode = 500;
		
		if(success){
			statusCode = 200;			
			System.out.println("API Success! " + playerName + " : " + itemName);
		} 
		
		return new NetworkResponse(statusCode, itemName);		
	}
	
}