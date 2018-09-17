package com.lootsafe.mod.util.handlers;

import java.util.ArrayList;
import java.util.List;

import com.lootsafe.mod.Items.ItemBase;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerHandler {
	
	private List<LootPlayer> lootplayers;	
 
	/* Constructors & Init */
	
    public PlayerHandler()
    {
    	lootplayers = loadLootPlayers();
    }
	
	public ArrayList<LootPlayer> loadLootPlayers(){
		ServerRecordHandler serverRecordHandler = new ServerRecordHandler();
		return serverRecordHandler.LoadServerRecords();
	}
	
	/* Wallet */
	
	public void registerPlayerWallet(String playerName, String playerWalletAddress){	
		lootplayers.add(new LootPlayer(playerName, playerWalletAddress));	
	}
	
	public boolean unregisterPlayerWallet(String playerName){
		
		if(isPlayerRegistered(playerName)){
			
			int index = -1;
			int counter = -1;
			
			for(LootPlayer lootplayer : lootplayers){
				
				counter++;
				
				if(lootplayer.getPlayerName().equals(playerName)){					
					
					index = counter;
				}				
			}
			
			if(index != -1){
				lootplayers.remove(index);
				return true;
			}
		}
		
		return false;
	}

	public String getPlayerWalletAddress(String playerName){
		
		if(isPlayerRegistered(playerName) == false){
			return null;
		}
		
		for(LootPlayer lootplayer : lootplayers){
			if(lootplayer.getPlayerName().equals(playerName)){					
				return lootplayer.getPlayerWalletAddress();
			}
		}
		
		return null;
	}
	
	public boolean registerPlayerLoot(String playerName, String lootAddress){
		
		for(LootPlayer lootplayer : lootplayers){
			if(lootplayer.getPlayerName().equals(playerName)){					
				lootplayer.addTokenizedItemStr(lootAddress);
				return true;
			}
		}
		
		return false;
	}
	
	public boolean registerBossLoot(String playerName,String bossName, String itemAddress){
		
		if(hasKilledBossBefore(playerName, bossName) == false){			
			for(LootPlayer lootplayer : lootplayers){				
				if(lootplayer.getPlayerName().equals(playerName)){
					return lootplayer.registerBossDeath(bossName, itemAddress);		
				}				
			}
			
			return false;
		}
		else{
			return false;
		}
		
	}	
	
	/* Helpers */
	
	public void addTokenizedItemStr(EntityPlayer player,ItemBase item){
		
		for(LootPlayer lootplayer : lootplayers){
			
			if(lootplayer.getPlayerName().equals(player.getName())){					
				lootplayer.addTokenizedItemStr(item.getItemAddress());
			}				 
		}
	}

	public boolean hasKilledBossBefore(String playerName,String bossName){
			
		for(LootPlayer lootplayer : lootplayers){				
			if(lootplayer.getPlayerName().equals(playerName)){
				return lootplayer.hasKilledBossBefore(bossName);					
			}				
		}
		
		return false;
	}
	
	public List<String> getPlayerTokenizedItemList(String playerName){
		
		List<String> tokenizedItemList = new ArrayList<String>();
		
		for(LootPlayer lootplayer : lootplayers){
			if(lootplayer.getPlayerName().equals(playerName)){
				
				if(lootplayer.getLatestLocalTokenizedItemList().isEmpty()){
					tokenizedItemList.add("No registered tokens to return.");
					return tokenizedItemList;
				}
				else{
					for(String item : lootplayer.getLatestLocalTokenizedItemList()){
						tokenizedItemList.add(item);
					}
				}
			}
		}			
		
		return tokenizedItemList;
	}
	
	public boolean isPlayerRegistered(String playerName){
		
		for(LootPlayer lootplayer : lootplayers){
			if(lootplayer.getPlayerName().equals(playerName)){
				return true;
			}
		}
		
		return false;
	}
	
}
