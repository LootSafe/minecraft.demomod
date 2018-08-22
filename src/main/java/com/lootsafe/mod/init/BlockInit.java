package com.lootsafe.mod.init;

import java.util.ArrayList;
import java.util.List;

import com.lootsafe.mod.blocks.BlockBase;
import com.lootsafe.mod.blocks.BlockLootChest;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit {
	
	/*
	 * ModBlocks
	 */
	
	public static final List<Block> BLOCKS = new ArrayList<Block>();	
	public static final Block RUBY_BLOCK = new BlockBase("test", Material.IRON);
	public static final Block LOOT_CHEST = new BlockLootChest("loot_chest");
}
