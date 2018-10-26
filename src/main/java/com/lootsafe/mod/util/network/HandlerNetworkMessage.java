package com.lootsafe.mod.util.network;

import com.lootsafe.mod.Main;
import com.lootsafe.mod.util.handlers.HandlerLootDispenser;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;
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
			System.out.println("API Success transferring item for " + playerName + " : " + itemName);
		} 
		else{			
			EntityPlayer player = getPlayerByName(playerName);
			HandlerLootDispenser.getInstance().RestoreItemToPlayer(player, itemName);
		}
		
		return new NetworkResponse(statusCode, itemName);		
	}
	
	private EntityPlayer getPlayerByName(String playerName)
	{
		return FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(playerName);
	}
	
}