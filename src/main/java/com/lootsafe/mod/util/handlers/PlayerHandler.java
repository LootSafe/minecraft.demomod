package com.lootsafe.mod.util.handlers;

import java.util.ArrayList;
import java.util.List;

import com.lootsafe.mod.Items.ItemBase;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerHandler {
	
	List<LootPlayer> lootplayers;
	
    private static PlayerHandler instance = null;
 
    private PlayerHandler()
    {
    	lootplayers = loadLootPlayers();
    }
 
    public static PlayerHandler getInstance()
    {
        if (instance == null)
        	instance = new PlayerHandler();
 
        return instance;
    }
	
	public ArrayList<LootPlayer> loadLootPlayers(){
		// Or load from file?
		return new ArrayList<LootPlayer>();
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
	
	public boolean registerPlayerWallet(String playerName, String playerWalletAddress){
		
		if(isPlayerRegistered(playerName)){
			// Already exists, can't register twice!
			return false;
		}
		
		lootplayers.add(new LootPlayer(playerName, playerWalletAddress));
		
		return true;
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
		
		// Something bad happened...
		
		return false;
	}
	
	public boolean addTokenizedItemStr(EntityPlayer player,ItemBase item){
		
		for(LootPlayer lootplayer : lootplayers){
			
			if(lootplayer.getPlayerName().equals(player.getName())){								
				NetworkHandler.getInstance().GivePlayerItem(player, lootplayer.getPlayerWalletAddress(),  item);					
				lootplayer.addTokenizedItemStr(item.getItemAddress());
				return true;
			}				 
		}
		
		return false;
	}

	public boolean hasKilledBossBefore(String playerName,String bossName){
			
		for(LootPlayer lootplayer : lootplayers){				
			if(lootplayer.getPlayerName().equals(playerName)){
				return lootplayer.hasKilledBossBefore(bossName);					
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
