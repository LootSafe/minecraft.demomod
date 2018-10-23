package com.lootsafe.mod.util.handlers;

import java.util.ArrayList;
import java.util.List;

import com.lootsafe.mod.Reference;

public class HandlerPlayer {
	
	private ArrayList<HandlerLootPlayer> lootPlayers;	
	private HandlerFileAndRecords serverRecordHandler;
 
	/* Constructors & Initialized */
	
    public HandlerPlayer()
    {    	
    	serverRecordHandler = new HandlerFileAndRecords();
       	
    	Reference.LoadConfig(serverRecordHandler.getServerConfig());
       	
    	lootPlayers = LoadServerRecords();  	 
    }
	
    /* Server Load & Save */
    
    public ArrayList<HandlerLootPlayer> LoadServerRecords()
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
		lootPlayers.add(new HandlerLootPlayer(playerName, playerWalletAddress));	
	}
	
	public boolean UnregisterPlayerWallet(String playerName)
	{		
		if(isPlayerRegistered(playerName))
		{			
			int index = -1;
			int counter = -1;
			
			for(HandlerLootPlayer lootplayer : lootPlayers)
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
		for(HandlerLootPlayer lootplayer : lootPlayers)
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
		for(HandlerLootPlayer lootplayer : lootPlayers)
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
			for(HandlerLootPlayer lootplayer : lootPlayers)
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
	
	public void addTokenizedItemStr(String playerName,String itemAddress)
	{		
		for(HandlerLootPlayer lootplayer : lootPlayers)
		{			
			if(lootplayer.getPlayerName().equals(playerName))
			{					
				lootplayer.addTokenizedItemStr(itemAddress);
			}				 
		}
	}

	public boolean hasKilledBossBefore(String playerName,String bossName)
	{			
		for(HandlerLootPlayer lootplayer : lootPlayers)
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
		
		for(HandlerLootPlayer lootplayer : lootPlayers)
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
		for(HandlerLootPlayer lootplayer : lootPlayers)
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
			
			for(HandlerLootPlayer lootplayer : lootPlayers)
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
		for(HandlerLootPlayer lootplayer : lootPlayers)
		{
			if(lootplayer.getPlayerName().equals(playerName))
			{
				lootplayer.removeTokenizedItemAddress(uniqueItemAddress);
			}
		}		
	}
	
}
