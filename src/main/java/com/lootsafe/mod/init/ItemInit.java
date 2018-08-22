package com.lootsafe.mod.init;

import java.util.ArrayList;
import java.util.List;

import com.lootsafe.mod.Items.ItemBase;
import com.lootsafe.mod.util.Reference;

import net.minecraft.item.Item;

public class ItemInit {
	
	/*
	 * ModItems
	 */
	
	public static final List<Item> ITEMS = new ArrayList<Item>();
	public static Item LootCoinGold = new ItemBase("lootcoin-gold", Reference.lootcoin_gold_address, "0x1234");
	public static Item LootCoinSilver = new ItemBase("lootcoin-silver", Reference.lootcoin_silver_address, "0x5678");
	public static Item LootCoinCopper = new ItemBase("lootcoin-copper", Reference.lootcoin_copper_address, "0x9012");	
}
