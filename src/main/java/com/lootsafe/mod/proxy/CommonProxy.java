package com.lootsafe.mod.proxy;

import java.util.List;

import com.lootsafe.mod.Main;
import com.lootsafe.mod.Items.ItemBase;
import com.lootsafe.mod.init.EntityInit;
import com.lootsafe.mod.util.handlers.GuiHandler;
import com.lootsafe.mod.util.handlers.NetworkHandler;
import com.lootsafe.mod.util.handlers.PlayerHandler;
import com.lootsafe.mod.util.handlers.RegistryHandlerServer;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@SuppressWarnings("deprecation")
public class CommonProxy {
	
	private PlayerHandler playerHandler;
	private NetworkHandler networkHandler;
	
	/* Server Proxy More So */
	
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}

	public void initRegistries() {
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
	}
	
	public void preInitRegistries() {
		
		EntityInit.registerEntities();		
		FMLCommonHandler.instance().bus().register(new RegistryHandlerServer());
		
		playerHandler = new PlayerHandler();
		networkHandler = new NetworkHandler();
	}
	
	/* Registering  */
	
	public void registerPlayerWallet(String playerName, String playerWalletAddress) {
		playerHandler.registerPlayerWallet(playerName, playerWalletAddress);
	}
	
	public boolean unregisterPlayerWallet(String playerName) {
		return playerHandler.unregisterPlayerWallet(playerName);
	}
	
	
	public boolean isPlayerRegistered(String playerName){
		return playerHandler.isPlayerRegistered(playerName);
	}
		
	public boolean registerBossLoot(String playerName,String bossName, String itemAddress){
		return playerHandler.registerBossLoot(playerName, bossName, itemAddress);
	}	

	public boolean hasKilledBossBefore(String playerName, String bossName){
		return playerHandler.hasKilledBossBefore(playerName, bossName);
	}
	
	/* Other Stuff */
	
	public String getPlayerWalletAddress(String playerName){
		return playerHandler.getPlayerWalletAddress(playerName);
	}
	
	public void addTokenizedItemStr(EntityPlayer player,ItemBase item){				
		networkHandler.GivePlayerItem(player, playerHandler.getPlayerWalletAddress(player.getName()),  item);			
		playerHandler.addTokenizedItemStr(player, item);
	}

	public List<String> getPlayerTokenizedItemList(String playerName) {
		return playerHandler.getPlayerTokenizedItemList(playerName);
	}

}
