package com.lootsafe.mod.util.handlers;

import com.lootsafe.mod.init.ItemInit;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class HandlerLoot {

	public static String lootcoin_gold_address = "0xabcdef0123456789012345";
	public static String lootcoin_silver_address = "0xbcdefa0123456789012345";
	public static String lootcoin_copper_address = "0xcdefab0123456789012345";
	
	private static HandlerLoot instance;
	
	public static HandlerLoot getInstance()
	{
		if(instance == null){
			instance = new HandlerLoot();
		}
		
		return instance;
	}
	
	public boolean RestoreItem(EntityPlayer player, String itemName)
	{
		try
		{
			if(itemName.equals("item.lootcoin-gold")){
				player.inventory.addItemStackToInventory(new ItemStack(ItemInit.LootCoinGold));
				return true;
			}
			else if(itemName.equals("item.lootcoin-silver")){
				player.inventory.addItemStackToInventory(new ItemStack(ItemInit.LootCoinSilver));
				return true;
			}
			else if(itemName.equals("item.lootcoin-copper")){
				player.inventory.addItemStackToInventory(new ItemStack(ItemInit.LootCoinCopper));
				return true;
			}
			else {
				return false;
			}			
		}
		catch(Exception e) { return false; }
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
		
		return "0x9999999999999999999999";
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
