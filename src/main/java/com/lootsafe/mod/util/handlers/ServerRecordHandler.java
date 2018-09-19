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

import net.minecraftforge.fml.common.FMLCommonHandler;

@SuppressWarnings("unchecked")
public class ServerRecordHandler {
			
	public ArrayList<LootPlayer> LoadServerRecords() {
		
		ArrayList<LootPlayer> lootPlayers = new ArrayList<LootPlayer>();
		
		if(!doesFileExist()){
			createFile();
			return lootPlayers;
		}
		
		JSONParser jsonParser = new JSONParser();
		JSONArray jsonArray;
		
		try {
			jsonArray = (JSONArray) jsonParser.parse(new FileReader(Reference.SERVER_FILE_NAME));
		} 
		catch (FileNotFoundException e) { e.printStackTrace(); System.out.println("Error loading records from file."); return lootPlayers; } 
		catch (IOException e) { e.printStackTrace(); System.out.println("Error loading records from file."); return lootPlayers; } 
		catch (ParseException e) { e.printStackTrace(); System.out.println("Error loading records from file."); return lootPlayers; } 
	
		for (Object o : jsonArray)
		{
			JSONObject jsonObject = (JSONObject) o;

		    String playerName = (String) jsonObject.get("playerName");
		    String playerWalletAddress = (String) jsonObject.get("currentWalletAddress");
		    ArrayList<String> defeatedBosses = (ArrayList<String>) jsonObject.get("defeatedBossesList");
		    ArrayList<String> latestLocalTokenizedItemList = (ArrayList<String>) jsonObject.get("latestLocalTokenizedItemList");	
		    
		    LootPlayer lootPlayer = new LootPlayer(playerName, playerWalletAddress, defeatedBosses, latestLocalTokenizedItemList);
		    
		    lootPlayers.add(lootPlayer);
		}
		
		System.out.println("Loaded Server Records Successfully.");
		
		return lootPlayers;
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

        } catch (IOException e) { e.printStackTrace(); System.out.println("Error updating server records."); return false; }

        System.out.println("Server Records Updated.");
        
		return true;
	}
	
	public boolean doesFileExist(){
		
		File file = FMLCommonHandler.instance().getMinecraftServerInstance().getFile(Reference.SERVER_FILE_NAME);
		
		if(file.exists() && !file.isDirectory()) { 
		    return true;
		}
		
		return false;
	}
	
	public File createFile(){
		File playerDataFile = new File(Reference.SERVER_FILE_NAME);
		return playerDataFile;
	}
	
}
