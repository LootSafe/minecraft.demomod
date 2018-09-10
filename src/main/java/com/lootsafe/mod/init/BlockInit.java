package com.lootsafe.mod.init;

import java.util.ArrayList;
import java.util.List;

import com.lootsafe.mod.blocks.BlockLootChest;

import net.minecraft.block.Block;

public class BlockInit {
	
	/*
	 * ModBlocks
	 */
	
	public static final List<Block> BLOCKS = new ArrayList<Block>();	
	public static final Block LOOT_CHEST = new BlockLootChest("loot_chest");
}
