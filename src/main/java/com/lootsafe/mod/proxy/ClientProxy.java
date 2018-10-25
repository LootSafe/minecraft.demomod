package com.lootsafe.mod.proxy;

import java.util.List;

import com.lootsafe.mod.Main;
import com.lootsafe.mod.Reference;
import com.lootsafe.mod.init.EntityInit;
import com.lootsafe.mod.items.ItemBase;
import com.lootsafe.mod.util.handlers.HandlerGui;
import com.lootsafe.mod.util.handlers.HandlerLoot;
import com.lootsafe.mod.util.handlers.HandlerPlayer;
import com.lootsafe.mod.util.handlers.HandlerClientRegistry;
import com.lootsafe.mod.util.handlers.HandlerRender;
import com.lootsafe.mod.util.network.NetworkResponse;
import com.lootsafe.mod.util.network.HandlerNetwork;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy {
	
	public void initRegistries()
	{		
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new HandlerGui());	
	}
	
	@SuppressWarnings("deprecation")
	public void preInitRegistries()
	{	
		EntityInit.registerEntities();		
		
		FMLCommonHandler.instance().bus().register(new HandlerClientRegistry());		
		HandlerRender.registerAllEntityRenders(Reference.GLOBAL_BOSSMOB_SIZE);	
	}
	
	public void registerItemRenderer(Item item, int meta, String id)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}
	
	public boolean isPlayerRegistered(String playerName)
	{
		// Disabling the chest in singleplayer.
		// Otherwise things crash when the server isn't there.
		return false;
	}
	
	public void handleNetworkResponse(NetworkResponse message, MessageContext ctx)
	{
		int statusCode = message.getStatusCode();		
		String itemName = message.getItemName();
		
		EntityPlayer player = Minecraft.getMinecraft().player;	
		
		player.sendMessage(new TextComponentString(Reference.TryingSendingItemText));
		
		if(statusCode == 200)
		{
			player.sendMessage(new TextComponentString(Reference.SendingItemText));	
		}
		else
		{
			player.sendMessage(new TextComponentString(TextFormatting.RED + " Error sending item!"));	
			
			// Put item back in the inventory, bad stuff happened.
			
			HandlerLoot.getInstance().RestoreItem(player, itemName);	
		}
		
		player.closeScreen();
	}
}
