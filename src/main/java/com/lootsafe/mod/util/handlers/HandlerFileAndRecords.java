package com.lootsafe.mod.util.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.lootsafe.mod.Reference;
import com.lootsafe.mod.ServerConfig;

import net.minecraftforge.fml.common.FMLCommonHandler;

@SuppressWarnings("unchecked")
public class HandlerFileAndRecords {
			
	/* File Writing & File Updating */
	
	public ServerConfig getServerConfig()
	{
		String privateKey,hostAddress,hostPort, version, otp;
		boolean debug;
		
		if(doesFileExist(Reference.DIR_PLAYERDATA + Reference.FILENAME_CONFIG))
		{			
			JSONParser jsonParser = new JSONParser();
			
			try 
			{				
				Object parsedObj = jsonParser.parse(new FileReader(Reference.DIR_PLAYERDATA + Reference.FILENAME_CONFIG));				
				JSONObject jsonObject = (JSONObject) parsedObj;
				
				hostAddress = (String) jsonObject.get("hostAddress");
				hostPort = (String) jsonObject.get("hostPort");
				privateKey = (String) jsonObject.get("privatekey");
				version =  (String) jsonObject.get("version");
				otp = (String) jsonObject.get("otp");
				debug = (boolean) jsonObject.get("debug");
				
				return new ServerConfig(privateKey, hostAddress, hostPort, version, otp, debug);
			} 
			catch (Exception e) { e.printStackTrace(); return new ServerConfig(); } 
		}
		
		return new ServerConfig();
	}
	
	public ArrayList<HandlerLootPlayer> LoadServerRecords() {
		
		//System.out.println(Reference.CONSOLE_TAG + "Loading player records..");
		
		ArrayList<HandlerLootPlayer> lootPlayers = new ArrayList<HandlerLootPlayer>();
				
		if(!doesFileExist(Reference.DIR_PLAYERDATA + Reference.FILENAME_PLAYERDATA))
		{
			//System.out.println(Reference.CONSOLE_TAG + "Player records file not found, creating one.");
			
			createDirectory(Reference.DIR_PLAYERDATA);			
			createFile(Reference.DIR_PLAYERDATA + Reference.FILENAME_PLAYERDATA, true);
			
			return lootPlayers;
		}
		
		JSONParser jsonParser = new JSONParser();
		JSONArray jsonArray;
		
		try 
		{
			Object parsedObj = jsonParser.parse(new FileReader(Reference.DIR_PLAYERDATA + Reference.FILENAME_PLAYERDATA));			
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
		    
		    HandlerLootPlayer lootPlayer = new HandlerLootPlayer(playerName, playerWalletAddress, defeatedBosses, latestLocalTokenizedItemList);		    
		    lootPlayers.add(lootPlayer);
		}
		
		//System.out.println(Reference.CONSOLE_TAG + "Loaded Server Records Successfully.");
		
		return lootPlayers;
	}
	
	public boolean UpdateServerRecords(ArrayList<HandlerLootPlayer> lootPlayers){
		
		if(!doesFileExist(Reference.DIR_PLAYERDATA + Reference.FILENAME_PLAYERDATA))
		{
			//System.out.println(Reference.CONSOLE_TAG + "Player records file not found, creating one.");
			
			createDirectory(Reference.DIR_PLAYERDATA);			
			createFile(Reference.DIR_PLAYERDATA + Reference.FILENAME_PLAYERDATA, true);
		}
		
        BackupRecords();
		
		JSONArray parentArray = new JSONArray();    	
    	JSONArray defeatedBossesList = new JSONArray();
    	JSONArray latestLocalTokenizedItemList = new JSONArray();
        
        for(HandlerLootPlayer lootplayer : lootPlayers){
        	
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
            
            parentArray.add(tempPlayer);
        }

        try (FileWriter file = new FileWriter(Reference.DIR_PLAYERDATA + Reference.FILENAME_PLAYERDATA, false))
        {
            file.write(parentArray.toJSONString());
            file.flush();
        } 
        catch (IOException e) { e.printStackTrace(); System.out.println(Reference.CONSOLE_TAG + "Error updating server records."); return false; }

        //System.out.println(Reference.CONSOLE_TAG + "Server Records Updated @ " + Reference.DIR_PLAYERDATA + Reference.FILENAME_PLAYERDATA);
                
		return true;
	}

	private void BackupRecords(){
		
		if(!doesFolderExist(Reference.DIR_BACKUP_PLAYERDATA)){
			createDirectory(Reference.DIR_BACKUP_PLAYERDATA);
		}		
		    
		Path fromFile = Paths.get(Reference.DIR_PLAYERDATA + Reference.FILENAME_PLAYERDATA);
	    Path toBackupFile = Paths.get(Reference.DIR_BACKUP_PLAYERDATA + Reference.FILENAME_BACKUPDATA + getCurrentLocalDateTimeStamp() + "BACKUP.json");
	    
	    CopyOption[] options = new CopyOption[]{
	      StandardCopyOption.REPLACE_EXISTING,
	      StandardCopyOption.COPY_ATTRIBUTES
	    }; 
	    
	    try 
	    {
			Files.copy(fromFile, toBackupFile, options);
			//System.out.println(Reference.CONSOLE_TAG + " current file backed up.");
		} 
	    catch (IOException e) 
	    {
	    	//System.out.println(Reference.CONSOLE_TAG + " problem making a backup.");
		}
	    
	}
		
	/* Folder Creation & File Creation */
	
	private File createFile(String filename, boolean isDatafile)
	{
		File playerDataFile = new File(filename);
		
		try {
			playerDataFile.createNewFile();
			//System.out.println(Reference.CONSOLE_TAG  + "File created @ " + filename);
			
			if(isDatafile){
				UpdateServerRecords(new ArrayList<HandlerLootPlayer>());
				// Writing empty array to file so it reads back properly
			}
			
		} catch (IOException e) {
			//System.out.println(Reference.CONSOLE_TAG  + "ERROR creating new file @ " + filename);
			e.printStackTrace();
		}				
		
		return playerDataFile;
	}		
	
	private void createDirectory(String directoryLocation) {
		File file = FMLCommonHandler.instance().getMinecraftServerInstance().getFile(directoryLocation);
		file.mkdirs();	
	}
	
	private boolean doesFileExist(String filename){
		
		File file = FMLCommonHandler.instance().getMinecraftServerInstance().getFile(filename);
		
		if(file.exists() && !file.isDirectory()) 
		{ 
			//System.out.println(Reference.CONSOLE_TAG + "File EXISTS, trying to from read file.");
		    return true;
		}
		
		//System.out.println(Reference.CONSOLE_TAG + "File does NOT EXIST, creating a new file.");
		return false;
	}
	
	private boolean doesFolderExist(String directoryLocationStr) {
		
		File file = FMLCommonHandler.instance().getMinecraftServerInstance().getFile(directoryLocationStr);
		
		if(!file.exists()) 
		{ 
			file.mkdirs();
		    return true;
		}
		
		return false;
	}
	
	/* Helpers */
	
	private String getCurrentLocalDateTimeStamp() {
	    return LocalDateTime.now()
	       .format(DateTimeFormatter.ofPattern("-yyyyMMdd-HHmmssSSS-"));
	}
	
}
