package com.lootsafe.mod.util.handlers;

import com.lootsafe.mod.entity.EntityLootSkele;
import com.lootsafe.mod.entity.EntityLootSpider;
import com.lootsafe.mod.init.ItemInit;
import com.lootsafe.mod.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CustomEventHandler {
	
	public void onKilledBoss(LivingDeathEvent event) {
	
		/* EntityLootSkele - Death detection and spawning item into players inventory */
		
		if (event.getEntity() instanceof EntityLootSkele && event.getSource().getTrueSource() instanceof EntityPlayer) {
			
			final String BOSS_NAME = "EntityLootSkele";
			EntityPlayer player = (EntityPlayer)event.getSource().getTrueSource();
			
			if(isPlayerRegistered(player.getName())){
							
				if(hasKilledBossBefore(player.getName(), BOSS_NAME) == false){						 
					 
					 int firstEmpty = player.inventory.getFirstEmptyStack();
					 
					 if(firstEmpty != -1){
						 
						 registerBossLoot(player.getName(), BOSS_NAME, Reference.lootcoin_gold_address);
						 
						 player.inventory.addItemStackToInventory(new ItemStack(ItemInit.LootCoinGold));					 
						 player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Received: " + Reference.description_lootcoin_gold.get(0)));
					 }
				}
								
			}
			else{
				player.sendMessage(new TextComponentString(TextFormatting.RED + "Can't give LOOT to unregistered player."));
			}			

		}	
		
		/* EntityLootSpider - Death detection and spawning item into players inventory */		
		
		if (event.getEntity() instanceof EntityLootSpider && event.getSource().getTrueSource() instanceof EntityPlayer) {
		
			final String BOSS_NAME = "EntityLootSpider";
			EntityPlayer player = (EntityPlayer)event.getSource().getTrueSource();
			
			if(isPlayerRegistered(player.getName())){
							
				if(hasKilledBossBefore(player.getName(), BOSS_NAME) == false){						 
					 
					 int firstEmpty = player.inventory.getFirstEmptyStack();
					 
					 if(firstEmpty != -1){
						 
						 registerBossLoot(player.getName(), BOSS_NAME, Reference.lootcoin_silver_address);
						 
						 player.inventory.addItemStackToInventory(new ItemStack(ItemInit.LootCoinSilver));					 
						 player.sendMessage(new TextComponentString(TextFormatting.GREEN + "Received: " + Reference.description_lootcoin_silver.get(0)));
					 }
				}
								
			}
			else{
				player.sendMessage(new TextComponentString(TextFormatting.RED + "Can't give LOOT to unregistered player."));
			}			

		}	

		/* EntityLoot....... - Death detection and spawning item into players inventory */
		
	}
	
	/* Shorthand helpers, just for keeping the code clean */
	
	private boolean isPlayerRegistered(String playerName){
		return PlayerHandler.getInstance().isPlayerRegistered(playerName);
	}
	
	private boolean hasKilledBossBefore(String playerName, String bossName){
		return PlayerHandler.getInstance().hasKilledBossBefore(playerName, bossName);
	}
	
	private boolean registerBossLoot(String playerName,String bossName, String itemAddress){
		return PlayerHandler.getInstance().registerBossLoot(playerName, bossName, itemAddress);
	}
}
