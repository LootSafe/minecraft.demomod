package com.lootsafe.mod.proxy;

import java.util.List;

import com.lootsafe.mod.Main;
import com.lootsafe.mod.init.EntityInit;
import com.lootsafe.mod.util.handlers.GuiHandler;
import com.lootsafe.mod.util.handlers.PlayerHandler;
import com.lootsafe.mod.util.handlers.RegistryHandlerServer;
import com.lootsafe.mod.util.network.CustomNetworkResponse;
import com.lootsafe.mod.util.network.NetworkHandler;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

@SuppressWarnings("deprecation")
public class CommonProxy {
	
	private PlayerHandler playerHandler;
	private NetworkHandler networkHandler;
	
	/* Server Proxy More So */
	
	public void preInitRegistries() 
	{
		EntityInit.registerEntities();		
		FMLCommonHandler.instance().bus().register(new RegistryHandlerServer());
		playerHandler = new PlayerHandler();
		networkHandler = new NetworkHandler();
	}
	
	public void initRegistries() 
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
	}
	
	public void registerItemRenderer(Item item, int meta, String id)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}
	
	/* Registering  */
	
	public void RegisterPlayerWallet(String playerName, String playerWalletAddress) 
	{
		playerHandler.RegisterPlayerWallet(playerName, playerWalletAddress);
	}
	
	public boolean unregisterPlayerWallet(String playerName) 
	{
		return playerHandler.UnregisterPlayerWallet(playerName);
	}	
	
	public boolean isPlayerRegistered(String playerName)
	{
		return playerHandler.isPlayerRegistered(playerName);
	}
		
	public boolean RegisterBossLoot(String playerName,String bossName, String itemAddress)
	{
		return playerHandler.RegisterBossLoot(playerName, bossName, itemAddress);
	}	

	public boolean hasKilledBossBefore(String playerName, String bossName)
	{
		return playerHandler.hasKilledBossBefore(playerName, bossName);
	}
	
	public boolean wipePlayerProgress(String playerName)
	{
		return playerHandler.WipePlayerProgress(playerName);
	}
	
	/* Other Stuff */
	
	public void UpdateServerRecords()
	{
		playerHandler.UpdateServerRecords();
	}
	
	public String getPlayerWalletAddress(String playerName)
	{
		return playerHandler.getPlayerWalletAddress(playerName);
	}
	
	public boolean addTokenizedItemStr(String playerName,String itemAddress)
	{	
		if(networkHandler.GivePlayerItem(playerName, playerHandler.getPlayerWalletAddress(playerName),  itemAddress))
		{
			playerHandler.addTokenizedItemStr(playerName, itemAddress);
			return true;
		}
		
		return false;
	}

	public List<String> getPlayerTokenizedItemList(String playerName)
	{
		return playerHandler.getPlayerTokenizedItemList(playerName);
	}

	public void PlayerItemWalletSuccess(String playerName, String uniqueItemAddress)
	{
		playerHandler.removePlayerTokenizedItem(playerName, uniqueItemAddress);	
	}
	
	public void handleNetworkResponse(CustomNetworkResponse message, MessageContext ctx)
	{
		//Override, doesn't do anything
	}
}
