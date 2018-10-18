package com.lootsafe.mod.util.handlers;

import com.lootsafe.mod.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootTableHandler {
	public static final ResourceLocation ZOMBIE = LootTableList.register(new ResourceLocation(Reference.MOD_ID, "lootzombie"));
}
