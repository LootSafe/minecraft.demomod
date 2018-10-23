package com.lootsafe.mod.init;

import java.util.ArrayList;
import java.util.List;

import com.lootsafe.mod.items.ItemBase;
import com.lootsafe.mod.util.handlers.HandlerLoot;

import net.minecraft.item.Item;

public class ItemInit {
	
	/*
	 * ModItems
	 */
	
	public static final List<Item> ITEMS = new ArrayList<Item>();
	public static Item LootCoinGold = new ItemBase("lootcoin-gold", HandlerLoot.getInstance().getGoldAddress());
	public static Item LootCoinSilver = new ItemBase("lootcoin-silver", HandlerLoot.getInstance().getSilverAddress());
	public static Item LootCoinCopper = new ItemBase("lootcoin-copper", HandlerLoot.getInstance().getCopperAddress());	
}
