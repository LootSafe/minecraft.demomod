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
		
		if(!doesFileExist())
		{
			createFile();
			return lootPlayers;
		}
		
		JSONParser jsonParser = new JSONParser();
		JSONArray jsonArray;
		
		try 
		{
			Object parsedObj = jsonParser.parse(new FileReader(Reference.SERVER_FILE_NAME));
			
			System.out.println(parsedObj.toString());
			
			jsonArray = (JSONArray) parsedObj;
		} 
		catch (FileNotFoundException e) { e.printStackTrace(); System.out.println(Reference.CONSOLE_TAG + "Error loading records from file."); return lootPlayers; } 
		catch (IOException e) { e.printStackTrace(); System.out.println(Reference.CONSOLE_TAG + "Error loading records from file."); return lootPlayers; } 
		catch (ParseException e) { e.printStackTrace(); System.out.println(Reference.CONSOLE_TAG + "Error loading records from file."); return lootPlayers; } 
	
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
		
		System.out.println(Reference.CONSOLE_TAG + "Loaded Server Records Successfully.");
		
		return lootPlayers;
	}
	
	public boolean UpdateServerRecords(ArrayList<LootPlayer> lootPlayers){
		
		if(!doesFileExist())
		{
			createFile();
		}
		
        JSONObject parentObject = new JSONObject();    	
    	JSONArray defeatedBossesList = new JSONArray();
    	JSONArray latestLocalTokenizedItemList = new JSONArray();
        
        for(LootPlayer lootplayer : lootPlayers){
        	
        	JSONObject tempPlayer = new JSONObject();
            defeatedBossesList = new JSONArray();
            latestLocalTokenizedItemList = new JSONArray();
        	
        	tempPlayer.put("playerName", lootplayer.getPlayerName());
        	tempPlayer.put("currentWalletAddress", lootplayer.getPlayerWalletAddress());          
            
            for(String tokenizedItemIdentifier : lootplayer.getLatestLocalTokenizedItemList())
            {
            	latestLocalTokenizedItemList.add(tokenizedItemIdentifier);
            }            

            tempPlayer.put("latestLocalTokenizedItemList", latestLocalTokenizedItemList);
            
            for(String bossIdentifier : lootplayer.getDefeatedBossesList())
            {
            	defeatedBossesList.add(bossIdentifier);
            }  
            
            tempPlayer.put("defeatedBossesList", defeatedBossesList);
            
            parentObject.put("player", tempPlayer);
        }

        try (FileWriter file = new FileWriter(Reference.SERVER_FILE_NAME)) 
        {
            file.write(parentObject.toJSONString());
            file.flush();
        } 
        catch (IOException e) { e.printStackTrace(); System.out.println(Reference.CONSOLE_TAG + "Error updating server records."); return false; }

        System.out.println(Reference.CONSOLE_TAG + "Server Records Updated @ " + Reference.SERVER_FILE_NAME);
        
		return true;
	}
	
	public boolean doesFileExist(){
		
		File file = FMLCommonHandler.instance().getMinecraftServerInstance().getFile(Reference.SERVER_FILE_NAME);
		
		if(file.exists() && !file.isDirectory()) 
		{ 
			System.out.println(Reference.CONSOLE_TAG + "File EXISTS, trying to from read file.");
		    return true;
		}
		
		System.out.println(Reference.CONSOLE_TAG + "File does NOT EXIST, creating a new file.");
		return false;
	}
	
	public File createFile()
	{
		File playerDataFile = new File(Reference.SERVER_FILE_NAME);
		System.out.println(Reference.CONSOLE_TAG  + "File created @ " + Reference.SERVER_FILE_NAME);
		return playerDataFile;
	}
	
}
