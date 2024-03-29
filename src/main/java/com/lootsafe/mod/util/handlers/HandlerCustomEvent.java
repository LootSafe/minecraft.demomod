package com.lootsafe.mod.util.handlers;

import com.lootsafe.mod.Main;
import com.lootsafe.mod.Reference;
import com.lootsafe.mod.entity.EntityLootSkele;
import com.lootsafe.mod.entity.EntityLootSpider;
import com.lootsafe.mod.entity.EntityLootZombie;
import com.lootsafe.mod.init.ItemInit;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerCustomEvent {
	
	/* Server Events */
	
	@SubscribeEvent 
	public void serverSaving(WorldEvent.Save event)
	{
		Main.proxy.UpdateServerRecords();
	}

    @SubscribeEvent
    public void onEntityJoinWorldEvent(EntityJoinWorldEvent event) {    	
    	if (event.getEntity().world.isRemote && event.getEntity() == Minecraft.getMinecraft().player) {
	        EntityPlayer player = (EntityPlayer) event.getEntity();
	        player.sendMessage(Reference.WELCOME_MESSAGE);				
    	}    	
    }
	
	/* Boss Events */
	
	@SubscribeEvent 
	public void bossSunscreen(LivingUpdateEvent event)
	{		
		/* EntityLootSkele - Sun Screen for daytime */
		
		if (event.getEntity() instanceof EntityLootSkele) 
		{			
			if(event.getEntity().isBurning())
			{
				event.getEntity().extinguish();
			}
		}	
		
		/* EntityLootSpider - Sun Screen for daytime */		
		
		if (event.getEntity() instanceof EntityLootSpider)
		{				
			if(event.getEntity().isBurning())
			{
				event.getEntity().extinguish();
			}
		}			
		
		/* EntityLootZombie - Sun Screen for daytime */		
		
		if (event.getEntity() instanceof EntityLootZombie)
		{				
			if(event.getEntity().isBurning())
			{
				event.getEntity().extinguish();
			}
		}		
	}
	
	@SubscribeEvent
	public void onKilledBoss(LivingDeathEvent event) 
	{	
		/* EntityLootSkele - Death detection and spawning item into players inventory */
		
		if (event.getEntity() instanceof EntityLootSkele && event.getSource().getTrueSource() instanceof EntityPlayer)
		{			
			final String BOSS_NAME = "EntityLootSkele";
			EntityPlayer player = (EntityPlayer)event.getSource().getTrueSource();
			
			if(Main.proxy.isPlayerRegistered(player.getName()))
			{							
				if(Main.proxy.hasKilledBossBefore(player.getName(), BOSS_NAME) == false)
				{						 
					 int firstEmpty = player.inventory.getFirstEmptyStack();
					 
					 if(firstEmpty != -1)
					 {				
						 String lootcoin_gold_address = HandlerLootDispenser.getInstance().getLootAddressByName("");
						 Main.proxy.RegisterBossLoot(player.getName(), BOSS_NAME, lootcoin_gold_address);
						 
						 player.inventory.addItemStackToInventory(new ItemStack(ItemInit.LootCoinGold));									 
						 player.sendMessage(new TextComponentString(Reference.RECEIVED_ITEM + Reference.description_lootcoin_gold.get(0)));
					 }					 
				}								
			}
			else
			{
				player.sendMessage(new TextComponentString(TextFormatting.RED + "Can't give LOOT to unregistered player."));
			}			

		}	
		
		/*
		
		//EntityLootSpider - Death detection and spawning item into players inventory		
		
		if (event.getEntity() instanceof EntityLootSpider && event.getSource().getTrueSource() instanceof EntityPlayer) 
		{		
			final String BOSS_NAME = "EntityLootSpider";
			EntityPlayer player = (EntityPlayer)event.getSource().getTrueSource();
			
			if(Main.proxy.isPlayerRegistered(player.getName()))
			{							
				if(Main.proxy.hasKilledBossBefore(player.getName(), BOSS_NAME) == false)
				{		  
					 int firstEmpty = player.inventory.getFirstEmptyStack();
					 
					 if(firstEmpty != -1)
					 {						 
						 String lootcoin_silver_address = HandlerLootDispenser.getInstance().getLootAddressByName("");
						 Main.proxy.RegisterBossLoot(player.getName(), BOSS_NAME, lootcoin_silver_address);	
						 
						 player.inventory.addItemStackToInventory(new ItemStack(ItemInit.LootCoinSilver));									 
						 player.sendMessage(new TextComponentString(Reference.RECEIVED_ITEM + Reference.description_lootcoin_silver.get(0)));
					 }
				}
								
			}
			else
			{
				player.sendMessage(new TextComponentString(TextFormatting.RED + "Can't give LOOT to unregistered player."));
			}			

		}	

		// EntityLootZombie - Death detection and spawning item into players inventory
		
		if (event.getEntity() instanceof EntityLootZombie && event.getSource().getTrueSource() instanceof EntityPlayer)
		{			
			final String BOSS_NAME = "EntityLootZombie";
			EntityPlayer player = (EntityPlayer)event.getSource().getTrueSource();
			
			if(Main.proxy.isPlayerRegistered(player.getName()))
			{							
				if(Main.proxy.hasKilledBossBefore(player.getName(), BOSS_NAME) == false)
				{						 
					 int firstEmpty = player.inventory.getFirstEmptyStack();
					 
					 if(firstEmpty != -1)
					 {				
						 String lootcoin_gold_address = HandlerLootDispenser.getInstance().getLootAddressByName("");
						 Main.proxy.RegisterBossLoot(player.getName(), BOSS_NAME, lootcoin_gold_address);		
						 
						 player.inventory.addItemStackToInventory(new ItemStack(ItemInit.LootCoinGold));									 
						 player.sendMessage(new TextComponentString(Reference.RECEIVED_ITEM + Reference.description_lootcoin_gold.get(0)));
					 }					 
				}								
			}
			else
			{
				player.sendMessage(new TextComponentString(TextFormatting.RED + "Can't give LOOT to unregistered player."));
			}			

		}
		
		*/
		
	}	

}
