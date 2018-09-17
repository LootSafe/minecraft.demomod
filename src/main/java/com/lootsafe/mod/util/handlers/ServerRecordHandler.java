package com.lootsafe.mod.util.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.lootsafe.mod.Reference;

@SuppressWarnings("unchecked")
public class ServerRecordHandler {
			
	public ArrayList<LootPlayer> LoadServerRecords() {
		
		ArrayList<LootPlayer> lootplayers = new ArrayList<LootPlayer>();
		
		if(!doesFileExist()){
			createFile();
			return lootplayers;
		}
		
		JSONParser jsonParser = new JSONParser();
		JSONArray jsonArray;
		try {
			jsonArray = (JSONArray) jsonParser.parse(new FileReader(Reference.SERVER_FILE_NAME));
		} 
		catch (FileNotFoundException e) { e.printStackTrace(); return lootplayers; } 
		catch (IOException e) { e.printStackTrace(); return lootplayers; } 
		catch (ParseException e) { e.printStackTrace(); return lootplayers; } 
	
		for (Object o : jsonArray)
		{
			JSONObject jsonObject = (JSONObject) o;

		    String playerName = (String) jsonObject.get("playerName");
		    String playerWalletAddress = (String) jsonObject.get("currentWalletAddress");
		    ArrayList<String> defeatedBosses = (ArrayList<String>) jsonObject.get("defeatedBossesList");
		    ArrayList<String> latestLocalTokenizedItemList = (ArrayList<String>) jsonObject.get("latestLocalTokenizedItemList");	
		    
		    LootPlayer lootPlayer = new LootPlayer(playerName, playerWalletAddress, defeatedBosses, latestLocalTokenizedItemList);
		    
		    lootplayers.add(lootPlayer);
		}
		
		return lootplayers;
	}
	
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
