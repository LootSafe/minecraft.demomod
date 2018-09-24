package com.lootsafe.mod.proxy;

import java.util.List;

import com.lootsafe.mod.Main;
import com.lootsafe.mod.Reference;
import com.lootsafe.mod.init.EntityInit;
import com.lootsafe.mod.items.ItemBase;
import com.lootsafe.mod.util.handlers.GuiHandler;
import com.lootsafe.mod.util.handlers.PlayerHandler;
import com.lootsafe.mod.util.handlers.RegistryHandlerClient;
import com.lootsafe.mod.util.handlers.RenderHandler;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy {
	
	public void registerItemRenderer(Item item, int meta, String id)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}
	
	public void initRegistries()
	{		
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
	}
	
	@SuppressWarnings("deprecation")
	public void preInitRegistries()
	{	
		EntityInit.registerEntities();		
		
		FMLCommonHandler.instance().bus().register(new RegistryHandlerClient());		
		RenderHandler.registerAllEntityRenders(Reference.GLOBAL_BOSSMOB_SIZE);		
	}
	
	public boolean isPlayerRegistered(String playerName)
	{
		// Disabling the chest in singleplayer.
		// Otherwise things crash when the server isn't there.
		return false;
	}
	
}
