package com.lootsafe.mod.util.network;

import com.lootsafe.mod.Main;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CustomNetworkResponseHandler implements IMessageHandler<CustomNetworkResponse, IMessage> {

	@Override public IMessage onMessage(CustomNetworkResponse message, MessageContext ctx) 
	{		
		Main.proxy.handleNetworkResponse(message, ctx);
		
		return null;
	}
	
}