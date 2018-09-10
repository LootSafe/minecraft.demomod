package com.lootsafe.mod.util.handlers;

import com.lootsafe.mod.Main;
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

public class CustomEventHandler {
	
	@SubscribeEvent
	public void onKilledBoss(LivingDeathEvent event) {
	
		/* EntityLootSkele - Death detection and spawning item into players inventory */
		
		if (event.getEntity() instanceof EntityLootSkele && event.getSource().getTrueSource() instanceof EntityPlayer) {
			
			final String BOSS_NAME = "EntityLootSkele";
			EntityPlayer player = (EntityPlayer)event.getSource().getTrueSource();
			
			if(Main.proxy.isPlayerRegistered(player.getName())){
							
				if(Main.proxy.hasKilledBossBefore(player.getName(), BOSS_NAME) == false){						 
					 
					 int firstEmpty = player.inventory.getFirstEmptyStack();
					 
					 if(firstEmpty != -1){
						 
						 Main.proxy.registerBossLoot(player.getName(), BOSS_NAME, Reference.lootcoin_gold_address);
						 
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
			
			if(Main.proxy.isPlayerRegistered(player.getName())){
							
				if(Main.proxy.hasKilledBossBefore(player.getName(), BOSS_NAME) == false){						 
					 
					 int firstEmpty = player.inventory.getFirstEmptyStack();
					 
					 if(firstEmpty != -1){
						 
						 Main.proxy.registerBossLoot(player.getName(), BOSS_NAME, Reference.lootcoin_silver_address);
						 
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

}
