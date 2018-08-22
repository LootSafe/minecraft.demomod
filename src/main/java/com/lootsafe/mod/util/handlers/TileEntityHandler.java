package com.lootsafe.mod.util.handlers;

import com.lootsafe.mod.blocks.tileenity.TileEntityLootChest;
import com.lootsafe.mod.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	public static void registerTileEntities(){
		GameRegistry.registerTileEntity(TileEntityLootChest.class, new ResourceLocation(Reference.MOD_ID + ":loot_chest"));
	}
}
