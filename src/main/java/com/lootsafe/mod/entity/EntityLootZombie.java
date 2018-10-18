package com.lootsafe.mod.entity;

import com.lootsafe.mod.util.handlers.LootTableHandler;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityLootZombie extends EntityZombie {

	public EntityLootZombie(World worldIn) 
	{
		super(worldIn);
	}
	
	public EntityZombie createChild(EntityAgeable ageable)
	{
		return new EntityZombie(world);
	}
	
	protected ResourceLocation getLootTable()
	{
		return LootTableHandler.ZOMBIE;
	}
	
}
