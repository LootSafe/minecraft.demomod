package com.lootsafe.mod.init;

import java.util.ArrayList;
import java.util.List;

import com.lootsafe.mod.items.ItemBase;
import com.lootsafe.mod.util.handlers.HandlerLootDispenser;

import net.minecraft.item.Item;

public class ItemInit {
	
	/*
	 * ModItems
	 */
	
	public static final List<Item> ITEMS = new ArrayList<Item>();
	public static Item LootCoinGold = new ItemBase("lootcoin-gold", HandlerLootDispenser.getInstance().getGoldAddress());
	
	//Extra items we didn't end up using
	
	//public static Item LootCoinSilver = new ItemBase("lootcoin-silver", HandlerLootDispenser.getInstance().getSilverAddress());
	//public static Item LootCoinCopper = new ItemBase("lootcoin-copper", HandlerLootDispenser.getInstance().getCopperAddress());	
}
