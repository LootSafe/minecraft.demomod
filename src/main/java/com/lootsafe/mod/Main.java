package com.lootsafe.mod;

import com.lootsafe.mod.commands.CommandHost;
import com.lootsafe.mod.commands.CommandLootCoinCopperAddress;
import com.lootsafe.mod.commands.CommandLootCoinGoldAddress;
import com.lootsafe.mod.commands.CommandLootCoinSilverAddress;
import com.lootsafe.mod.commands.WalletCommandRegisterPlayer;
import com.lootsafe.mod.commands.WalletCommandShowLoot;
import com.lootsafe.mod.commands.WalletCommandShowLootCommands;
import com.lootsafe.mod.commands.WalletCommandUnregisterPlayer;
import com.lootsafe.mod.commands.WalletCommandWalletAddress;
import com.lootsafe.mod.proxy.CommonProxy;
import com.lootsafe.mod.util.Reference;
import com.lootsafe.mod.util.handlers.CustomEventHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	
	private static CustomEventHandler customEventHandler = new CustomEventHandler();
	public static final CreativeTabs LOOTSTORETAB = new LootStoreTab("lootstoretab");
	
	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void serverLoad(FMLServerStartingEvent event)
	{
		// Custom Commands here
		event.registerServerCommand(new WalletCommandRegisterPlayer());
		event.registerServerCommand(new WalletCommandUnregisterPlayer());
		event.registerServerCommand(new WalletCommandWalletAddress());
		event.registerServerCommand(new WalletCommandShowLootCommands());		
		event.registerServerCommand(new WalletCommandShowLoot());
		
		event.registerServerCommand(new CommandLootCoinGoldAddress());
		event.registerServerCommand(new CommandLootCoinSilverAddress());
		event.registerServerCommand(new CommandLootCoinCopperAddress());
		event.registerServerCommand(new CommandHost());
	}
	
	/* Important Stuff */

	@EventHandler
	public static void init(FMLInitializationEvent event) {
		proxy.initRegistries();
	}
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event)
	{	
		MinecraftForge.EVENT_BUS.register(customEventHandler);
		//FMLCommonHandler.instance().bus().register(customEventHandler);
		
		proxy.preInitRegistries();
	}
	
}
