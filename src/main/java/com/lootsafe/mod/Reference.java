package com.lootsafe.mod;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.text.TextFormatting;

@SuppressWarnings("serial")
public class Reference {

	public static final String MOD_ID = "lsm";
	public static final String NAME = "LootSafe Mod";
	public static final String VERSION = "1.0 alpha";
	public static final String ACCEPTED_VERSIONS = "[1.12.2]";
	public static final String CLIENT_PROXY_CLASS = "com.lootsafe.mod.proxy.ClientProxy";
	public static final String COMMON_PROXY_CLASS = "com.lootsafe.mod.proxy.CommonProxy";
	public static final String CONSOLE_TAG = "[LOOTSAFE MOD] - ";
	
	public static final String DIR_PLAYERDATA = "lootsafedata/";
	public static final String FILENAME_PLAYERDATA = "lootplayers.json";
	
	public static final String DIR_BACKUP_PLAYERDATA = "lootsafedata/lootbackup/";
	public static final String FILENAME_BACKUPDATA = "lootplayers";
	
	public static final int ENTITY_LOOTSKELETON = 666;
	public static final int ENTITY_LOOTSPIDER = 667;
	public static final int GUI_LOOT_CHEST = 2;
	public static final float GLOBAL_BOSSMOB_SIZE = 1.0f;
	
	/* Server Stuff */
	
	public static String otp = "";
	public static String privateKey = "";
	public static String host = "http://localhost:1337/v1/";
	public static String version = "";
	public static boolean debug = false;

	public static String lootcoin_gold_address = "0x00";
	public static String lootcoin_silver_address = "0x00";
	public static String lootcoin_copper_address = "0x00";
	
	/* Server Stuff */
	
	public static List<String> description_lootcoin_gold =
			new ArrayList<String>() {{
				add(TextFormatting.YELLOW + "LootCoin Gold");
				add(TextFormatting.WHITE + "A tokenized asset that can be stored in your wallet!");
			}};
			
	public static List<String> description_lootcoin_silver =
			new ArrayList<String>() {{
				add(TextFormatting.AQUA + "LootCoin Silver");
				add(TextFormatting.WHITE + "A tokenized asset that can be stored in your wallet!");
			}};
					
	public static List<String> description_lootcoin_copper =
			new ArrayList<String>() {{
				add(TextFormatting.DARK_RED + "LootCoin Copper");
				add(TextFormatting.WHITE + "A tokenized asset that can be stored in your wallet!");
			}};				
			
	public static List<String> description_lootchest =
			new ArrayList<String>() {{
				add(TextFormatting.WHITE + "LootSafe for storing your Loot!");
			}};	
			
}
