package com.lootsafe.mod.util.handlers;

import com.lootsafe.mod.Reference;
import com.lootsafe.mod.blocks.tileenity.TileEntityLootChest;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class HandlerTileEntity {
	
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityLootChest.class, new ResourceLocation(Reference.MOD_ID + ":loot_chest"));
	}
	
}
