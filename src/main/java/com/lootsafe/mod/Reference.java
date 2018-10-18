package com.lootsafe.mod;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.text.TextFormatting;

@SuppressWarnings("serial")
public class Reference {

	/* Crucial Mod Strings */
	
	public static final String MOD_ID = "lsm";
	public static final String NAME = "LootSafe Mod";
	public static final String VERSION = "1.0 BETA";
	public static final String ACCEPTED_VERSIONS = "[1.12.2]";
	public static final String CLIENT_PROXY_CLASS = "com.lootsafe.mod.proxy.ClientProxy";
	public static final String COMMON_PROXY_CLASS = "com.lootsafe.mod.proxy.CommonProxy";
	
	/* Custom MOB Info */
	
	public static final int ENTITY_LOOTSKELETON = 666;
	public static final int ENTITY_LOOTSPIDER = 667;
	public static final int GUI_LOOT_CHEST = 2;
	public static final float GLOBAL_BOSSMOB_SIZE = 1.0f;
	
	/* File Locations */
	
	public static final String DIR_PLAYERDATA = "lootsafedata/";
	public static final String FILENAME_PLAYERDATA = "lootplayers.json";	
	public static final String DIR_BACKUP_PLAYERDATA = "lootsafedata/lootbackup/";
	public static final String FILENAME_BACKUPDATA = "lootplayers";	
	public static final String FILENAME_CONFIG = "config.json";
	
	/* API Server connection info */
	
	public static String otp, privateKey, hostAddress, hostPort, version;
	public static boolean debug = true;
		
	/* Description Text */

	public static final String CONSOLE_TAG = "[LOOTSAFE MOD] - ";	
	public static final String RecievedItemText = (TextFormatting.BLUE + "* Received ");	
	public static final String SendingItemText = (TextFormatting.BLUE + "Sending item to your wallet is a success!");
	
	public static List<String> description_lootcoin_gold =
			new ArrayList<String>() {{
				add(TextFormatting.YELLOW + "LootSafe Gold Demo Token");
				add(TextFormatting.WHITE + "Can be stored in your wallet!");
			}};
			
	public static List<String> description_lootcoin_silver =
			new ArrayList<String>() {{
				add(TextFormatting.YELLOW + "LootSafe Silver Demo Token");
				add(TextFormatting.WHITE + "Can be stored in your wallet!");
			}};
					
	public static List<String> description_lootcoin_copper =
			new ArrayList<String>() {{
				add(TextFormatting.YELLOW + "LootSafe Copper Demo Token");
				add(TextFormatting.WHITE + "Can be stored in your wallet!");
			}};				
			
	public static List<String> description_lootchest =
			new ArrayList<String>() {{
				add(TextFormatting.WHITE + "A LootSafe for storing your Loot!");
			}};	
			
	/* Config */
				
	public static void LoadConfig(ServerConfig serverConfig)
	{
		privateKey = serverConfig.getPrivateKey();
		hostAddress = serverConfig.getHostAddress();
		hostPort = serverConfig.getHostPort();
		version = serverConfig.getVersion();
		debug = serverConfig.getDebug();
		otp = serverConfig.getOtp();
	}
	
}
