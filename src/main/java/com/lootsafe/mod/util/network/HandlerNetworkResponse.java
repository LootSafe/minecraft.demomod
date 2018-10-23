package com.lootsafe.mod.util.network;

import com.lootsafe.mod.Main;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class HandlerNetworkResponse implements IMessageHandler<NetworkResponse, IMessage> {

	@Override public IMessage onMessage(NetworkResponse message, MessageContext ctx) 
	{		
		Main.proxy.handleNetworkResponse(message, ctx);
		
		return null;
	}
	
}