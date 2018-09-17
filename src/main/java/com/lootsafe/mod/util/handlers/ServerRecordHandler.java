package com.lootsafe.mod.util.handlers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.lootsafe.mod.Reference;

public class ServerRecordHandler {
			
	public ArrayList<LootPlayer> LoadServerRecords(){
		
		ArrayList<LootPlayer> lootplayers = new ArrayList<LootPlayer>();
		
		if(!doesFileExist()){
			createFile();
			return lootplayers;
		}
		
        // TODO		
		
		return lootplayers;
	}
	
	@SuppressWarnings("unchecked")
	public boolean UpdateServerRecords(ArrayList<LootPlayer> lootPlayers){
		
		if(!doesFileExist()){
			createFile();
		}
		
        JSONObject jsonObject = new JSONObject();    	
    	JSONArray defeatedBossesList = new JSONArray();
    	JSONArray latestLocalTokenizedItemList = new JSONArray();
        
        for(LootPlayer lootplayer : lootPlayers){
        	
            jsonObject.put("playerName", lootplayer.getPlayerName());
            jsonObject.put("currentWalletAddress", lootplayer.getPlayerWalletAddress());
            
            for(String tokenizedItemIdentifier : lootplayer.getLatestLocalTokenizedItemList()){
            	defeatedBossesList.add(tokenizedItemIdentifier);
            }
            
            for(String bossIdentifier : lootplayer.getDefeatedBossesList()){
            	defeatedBossesList.add(bossIdentifier);
            }   
            
        }

        jsonObject.put("defeatedBossesList", defeatedBossesList);
        jsonObject.put("latestLocalTokenizedItemList", latestLocalTokenizedItemList);

        try (FileWriter file = new FileWriter(Reference.SERVER_FILE_NAME)) {

            file.write(jsonObject.toJSONString());
            file.flush();

        } catch (IOException e) { e.printStackTrace(); return false; }

		return true;
	}
	
	public boolean doesFileExist(){
		
		File f = new File(Reference.SERVER_FILE_NAME);
		
		if(f.exists() && !f.isDirectory()) { 
		    return true;
		}
		
		return false;
	}
	
	public File createFile(){
		return new File(Reference.SERVER_FILE_NAME);
	}
	
}
