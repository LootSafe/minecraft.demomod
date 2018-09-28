package com.lootsafe.mod.util.handlers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerHandler {
	
	private ArrayList<LootPlayer> lootPlayers;	
	private ServerRecordHandler serverRecordHandler;
 
	/* Constructors & Initialized */
	
    public PlayerHandler()
    {
    	serverRecordHandler = new ServerRecordHandler();
    	lootPlayers = LoadServerRecords();
    }
	
    /* Server Load & Save */
    
    public ArrayList<LootPlayer> LoadServerRecords()
    {     
    	return serverRecordHandler.LoadServerRecords();
    }
	
	public boolean UpdateServerRecords()
	{
		return serverRecordHandler.UpdateServerRecords(lootPlayers);
	}
	
	/* Wallet */
	
	public void RegisterPlayerWallet(String playerName, String playerWalletAddress)
	{	
		lootPlayers.add(new LootPlayer(playerName, playerWalletAddress));	
	}
	
	public boolean UnregisterPlayerWallet(String playerName)
	{		
		if(isPlayerRegistered(playerName))
		{			
			int index = -1;
			int counter = -1;
			
			for(LootPlayer lootplayer : lootPlayers)
			{				
				counter++;
				
				if(lootplayer.getPlayerName().equals(playerName))
				{					
					index = counter;
				}				
			}
			
			if(index != -1)
			{
				lootPlayers.remove(index);
				return true;
			}
		}
		
		return false;
	}

	public String getPlayerWalletAddress(String playerName)
	{		
		for(LootPlayer lootplayer : lootPlayers)
		{
			if(lootplayer.getPlayerName().equals(playerName))
			{		
				return lootplayer.getPlayerWalletAddress();
			}
		}
		
		return "null";
	}
	
	public boolean RegisterPlayerLoot(String playerName, String lootAddress)
	{		
		for(LootPlayer lootplayer : lootPlayers)
		{
			if(lootplayer.getPlayerName().equals(playerName))
			{					
				lootplayer.addTokenizedItemStr(lootAddress);
				return true;
			}
		}
		
		return false;
	}
	
	public boolean RegisterBossLoot(String playerName, String bossName, String itemAddress)
	{		
		if(hasKilledBossBefore(playerName, bossName) == false)
		{			
			for(LootPlayer lootplayer : lootPlayers)
			{				
				if(lootplayer.getPlayerName().equals(playerName))
				{
					return lootplayer.registerBossDeath(bossName, itemAddress);		
				}				
			}
			
			return false;
		}
		else
		{
			return false;
		}		
	}	
	
	/* Helpers */
	
	public void addTokenizedItemStr(EntityPlayer player,String itemAddress)
	{		
		for(LootPlayer lootplayer : lootPlayers)
		{			
			if(lootplayer.getPlayerName().equals(player.getName()))
			{					
				lootplayer.addTokenizedItemStr(itemAddress);
			}				 
		}
	}

	public boolean hasKilledBossBefore(String playerName,String bossName)
	{			
		for(LootPlayer lootplayer : lootPlayers)
		{				
			if(lootplayer.getPlayerName().equals(playerName))
			{
				return lootplayer.hasKilledBossBefore(bossName);					
			}				
		}
		
		return false;
	}
	
	public List<String> getPlayerTokenizedItemList(String playerName)
	{		
		List<String> tokenizedItemList = new ArrayList<String>();
		
		for(LootPlayer lootplayer : lootPlayers)
		{
			if(lootplayer.getPlayerName().equals(playerName))
			{				
				if(lootplayer.getLatestLocalTokenizedItemList().isEmpty())
				{
					tokenizedItemList.add("No registered tokens to return.");
					return tokenizedItemList;
				}
				else
				{
					return lootplayer.getLatestLocalTokenizedItemList();
				}
			}
		}			
		
		return tokenizedItemList;
	}
	
	public boolean isPlayerRegistered(String playerName)
	{
		for(LootPlayer lootplayer : lootPlayers)
		{
			if(lootplayer.getPlayerName().equals(playerName))
			{
				return true;
			}
		}
		
		return false;
	}

	public boolean WipePlayerProgress(String playerName) 
	{		
		if(isPlayerRegistered(playerName))
		{			
			int pos = -1;
			int cnt = 0;
			
			for(LootPlayer lootplayer : lootPlayers)
			{
				if(lootplayer.getPlayerName().equals(playerName))
				{
					pos = cnt;
				}
				
				++cnt;
			}
			
			if(pos != -1)
			{
				lootPlayers.remove(pos);
				return true;				
			}
		}
		
		return false;
	}

	public void removePlayerTokenizedItem(String playerName, String uniqueItemAddress)
	{		
		for(LootPlayer lootplayer : lootPlayers)
		{
			if(lootplayer.getPlayerName().equals(playerName))
			{
				lootplayer.removeTokenizedItemAddress(uniqueItemAddress);
			}
		}		
	}
	
}
