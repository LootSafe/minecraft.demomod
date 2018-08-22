package com.lootsafe.mod.util.handlers;

import java.util.ArrayList;
import java.util.List;

public class LootPlayer {
	
	private String playerName;
	private String playerWalletAddress;
	private List<String> defeatedBosses;
	private List<String> latestLocalTokenizedItemList;
	
	public LootPlayer(String playerName, String playerWalletAddress){
		this.playerName = playerName;
		this.playerWalletAddress = playerWalletAddress;
		this.defeatedBosses = new ArrayList<String>();
		this.latestLocalTokenizedItemList = new ArrayList<String>();
	}	
		
	public boolean hasKilledBossBefore(String bossName){
		
		for(String boss : defeatedBosses){
			if(boss.equals(bossName)){
				return true;
			}
		}
		
		return false;
	}
	
	public boolean registerBossDeath(String bossName, String itemAddress){
		
		if(hasKilledBossBefore(bossName) == false){			
			defeatedBosses.add(bossName);
			latestLocalTokenizedItemList.add(bossName);
			return true;
		}
		
		return false;
	}
	
	public void addTokenizedItemStr(String itemAddress){
		this.latestLocalTokenizedItemList.add(itemAddress);	
	}
	
	public void setLatestLocalTokenizedItemList(List<String> latestLocalTokenizedItemList)
	{
		this.latestLocalTokenizedItemList = latestLocalTokenizedItemList;
	}

	public String getPlayerName(){
		return this.playerName;
	}
	
	public String getPlayerWalletAddress(){
		return this.playerWalletAddress;
	}
	
	public List<String> getLatestLocalTokenizedItemList(){
		return this.latestLocalTokenizedItemList;
	}
	
}
