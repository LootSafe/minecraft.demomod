package com.lootsafe.mod.util.handlers;
public class LootHandler {

	public static String lootcoin_gold_address = "0xabcdef0123456789012345";
	public static String lootcoin_silver_address = "0xbcdefa0123456789012345";
	public static String lootcoin_copper_address = "0xcdefab0123456789012345";
	
	private static LootHandler instance;
	
	public static LootHandler getInstance()
	{
		if(instance == null){
			instance = new LootHandler();
		}
		
		return instance;
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
