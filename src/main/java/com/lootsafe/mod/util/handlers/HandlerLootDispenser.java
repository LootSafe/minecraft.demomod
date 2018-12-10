package com.lootsafe.mod.util.handlers;

import com.lootsafe.mod.Reference;
import com.lootsafe.mod.init.ItemInit;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class HandlerLootDispenser {

	public static String lootcoin_gold_address = Reference.BUM_ADDRESS;
	public static String lootcoin_silver_address = Reference.BUM_ADDRESS;
	public static String lootcoin_copper_address = Reference.BUM_ADDRESS;
	
	private static HandlerLootDispenser instance;
	
	public static HandlerLootDispenser getInstance()
	{
		if(instance == null){
			instance = new HandlerLootDispenser();
		}
		
		return instance;
	}
	
	public boolean RestoreItemToPlayer(EntityPlayer player, String itemName)
	{
		boolean flag = false;
		
		try
		{
			switch(itemName)
			{
				case "item.lootcoin-gold":
					flag = player.inventory.addItemStackToInventory(new ItemStack(ItemInit.LootCoinGold));
					break;
				case "item.lootcoin-silver":
					//flag = player.inventory.addItemStackToInventory(new ItemStack(ItemInit.LootCoinSilver));
					break;
				case "item.lootcoin-copper":
					//flag = player.inventory.addItemStackToInventory(new ItemStack(ItemInit.LootCoinCopper));
					break;
				default:
					return false;
			}		
		}
		catch(Exception e) { return false; }
		
		if(flag)
		{
			player.inventoryContainer.detectAndSendChanges();
			return true;
		}
			
		return false;
		
	}
	
	public void setGoldAddress(String gold_address )
	{
		lootcoin_gold_address = gold_address; 
	}

	public void setSilverAddress(String silver_address)
	{
		lootcoin_silver_address = silver_address; 
	}
	
	public void setCopperAddress(String copper_address)
	{
		lootcoin_copper_address = copper_address; 
	}
	
	public String getLootAddressByName(String lootName)
	{
		if(lootName.equals("item.lootcoin-gold")){
			return lootcoin_gold_address;
		}
		else if(lootName.equals("item.lootcoin-silver")){
			return lootcoin_silver_address;
		}
		else if(lootName.equals("item.lootcoin-copper")){
			return lootcoin_copper_address;
		}
		
		return Reference.BUM_ADDRESS;
	}
	
	public String getGoldAddress() 
	{
		return lootcoin_gold_address;
	}
	
	public String getSilverAddress()
	{
		return lootcoin_silver_address;
	}
	
	public String getCopperAddress()
	{
		return lootcoin_copper_address;
	}
	
}
