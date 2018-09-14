package com.lootsafe.mod;

import com.lootsafe.mod.commands.AdminHost;
import com.lootsafe.mod.commands.AdminAddressGold;
import com.lootsafe.mod.commands.AdminAddressSilver;
import com.lootsafe.mod.commands.WalletRegisterPlayer;
import com.lootsafe.mod.commands.WalletShowLoot;
import com.lootsafe.mod.commands.WalletShowCommands;
import com.lootsafe.mod.commands.WalletUnregisterPlayer;
import com.lootsafe.mod.commands.WalletWalletAddress;
import com.lootsafe.mod.proxy.CommonProxy;
import com.lootsafe.mod.util.LootStoreTab;
import com.lootsafe.mod.util.handlers.CustomEventHandler;

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
		event.registerServerCommand(new WalletRegisterPlayer());
		event.registerServerCommand(new WalletUnregisterPlayer());
		event.registerServerCommand(new WalletWalletAddress());
		event.registerServerCommand(new WalletShowCommands());		
		event.registerServerCommand(new WalletShowLoot());
		
		event.registerServerCommand(new AdminAddressGold());
		event.registerServerCommand(new AdminAddressSilver());
		event.registerServerCommand(new AdminHost());

		//event.registerServerCommand(new CommandLootCoinCopperAddress());
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
		FMLCommonHandler.instance().bus().register(customEventHandler);
		
		proxy.preInitRegistries();
	}
	
}
