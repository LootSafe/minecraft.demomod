package com.lootsafe.mod;

import com.lootsafe.mod.blocks.gui.LootStoreTab;
import com.lootsafe.mod.commands.HelpCommands;
import com.lootsafe.mod.commands.admin.AdminAddressGold;
import com.lootsafe.mod.commands.admin.AdminForceSave;
import com.lootsafe.mod.commands.admin.AdminHostAddress;
import com.lootsafe.mod.commands.admin.AdminWipeProgess;
import com.lootsafe.mod.commands.player.BlankChat;
import com.lootsafe.mod.commands.player.WalletRegisterPlayer;
import com.lootsafe.mod.commands.player.WalletUnregisterPlayer;
import com.lootsafe.mod.commands.player.WalletWalletAddress;
import com.lootsafe.mod.proxy.CommonProxy;
import com.lootsafe.mod.util.handlers.HandlerCustomEvent;
import com.lootsafe.mod.util.network.HandlerNetworkMessage;
import com.lootsafe.mod.util.network.HandlerNetworkResponse;
import com.lootsafe.mod.util.network.NetworkMessage;
import com.lootsafe.mod.util.network.NetworkResponse;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings("deprecation")
@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	
	private static HandlerCustomEvent customEventHandler = new HandlerCustomEvent();
	public static final CreativeTabs LOOTSTORETAB = new LootStoreTab("lootstoretab");	
	public static SimpleNetworkWrapper network;
	
	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	/* Important Stuff */

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) 
	{	
		MinecraftForge.EVENT_BUS.register(customEventHandler);
		FMLCommonHandler.instance().bus().register(customEventHandler);
		
		network = NetworkRegistry.INSTANCE.newSimpleChannel("CustomChannel");
		network.registerMessage(HandlerNetworkMessage.class, NetworkMessage.class, 0, Side.SERVER);
		network.registerMessage(HandlerNetworkResponse.class, NetworkResponse.class, 1, Side.CLIENT);
	
		proxy.preInitRegistries();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) 
	{
		proxy.initRegistries();
	}

	/* Server Booting and Server Shutting Down */
	
	@EventHandler
	@SideOnly(Side.SERVER)
	public static void serverStarting(FMLServerStartingEvent event) 
	{
		event.registerServerCommand(new AdminHostAddress());
		event.registerServerCommand(new AdminForceSave());
		event.registerServerCommand(new AdminAddressGold());
		event.registerServerCommand(new AdminWipeProgess());
		event.registerServerCommand(new BlankChat());
		event.registerServerCommand(new WalletRegisterPlayer());
		event.registerServerCommand(new WalletUnregisterPlayer());
		event.registerServerCommand(new WalletWalletAddress());
		event.registerServerCommand(new HelpCommands());		
	}
	
	@EventHandler
	@SideOnly(Side.SERVER)
	public void serverStopping(FMLServerStoppingEvent event)
	{
	    Main.proxy.UpdateServerRecords();
	}
	
}
