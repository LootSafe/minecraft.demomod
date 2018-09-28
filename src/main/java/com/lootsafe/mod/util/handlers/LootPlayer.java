package com.lootsafe.mod.util.handlers;

import java.util.ArrayList;
import java.util.List;

public class LootPlayer {
	
	private String playerName;
	private String playerWalletAddress;
	private List<String> defeatedBossesList;
	private List<String> latestLocalTokenizedItemList;
	
	public LootPlayer(String playerName, String playerWalletAddress)
	{
		this.playerName = playerName;
		this.playerWalletAddress = playerWalletAddress;
		this.defeatedBossesList = new ArrayList<String>();
		this.latestLocalTokenizedItemList = new ArrayList<String>();
	}	
	
	public LootPlayer(String playerName, String playerWalletAddress, ArrayList<String> defeatedBosses, ArrayList<String> latestLocalTokenizedItemList)
	{
		this.playerName = playerName;
		this.playerWalletAddress = playerWalletAddress;
		this.defeatedBossesList = defeatedBosses;
		this.latestLocalTokenizedItemList = latestLocalTokenizedItemList;
	}	
	
	public boolean removeTokenizedItemAddress(String itemAddress)
	{
		int position = -1;
		int count = 0;
		
		for(String itemAdd : latestLocalTokenizedItemList)
		{
			if(itemAddress == itemAdd) 
			{
				position = count;
			}
			
			count++;
		}
		
		if(position != -1)
		{
			latestLocalTokenizedItemList.remove(position);
			return true;
		}
		
		return false;
	}
		
	public boolean hasKilledBossBefore(String bossName)
	{		
		for(String boss : defeatedBossesList)
		{
			if(boss.equals(bossName))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean registerBossDeath(String bossName, String itemAddress)
	{
		if(hasKilledBossBefore(bossName) == false)
		{			
			this.defeatedBossesList.add(bossName);
			this.latestLocalTokenizedItemList.add(itemAddress);
			return true;
		}
		
		return false;
	}
	
	public void addTokenizedItemStr(String itemAddress)
	{
		this.latestLocalTokenizedItemList.add(itemAddress);	
	}
	
	/* Getters & Setters */
	
	public void setPlayerName(String playerName)
	{
		this.playerName = playerName;
	}
	
	public String getPlayerName()
	{
		return this.playerName;
	}
		
	public void setLatestLocalTokenizedItemList(List<String> latestLocalTokenizedItemList)
	{
		this.latestLocalTokenizedItemList = latestLocalTokenizedItemList;
	}
	
	public List<String> getLatestLocalTokenizedItemList()
	{
		return this.latestLocalTokenizedItemList;
	}
	
	public List<String> getDefeatedBossesList()
	{
		return this.defeatedBossesList;
	}
	
	public String getPlayerWalletAddress()
	{
		return this.playerWalletAddress;
	}
	
	public String toString()
	{
		String s = "";
		
		s += "\n-------------------------------------------------------------------\n";
		s += this.playerName + " : " + this.playerWalletAddress;
		s += "\n" + this.defeatedBossesList.toString();
		s += "\n" + this.latestLocalTokenizedItemList.toString();
		s += "\n-------------------------------------------------------------------";
		
		return s;
	}
}
