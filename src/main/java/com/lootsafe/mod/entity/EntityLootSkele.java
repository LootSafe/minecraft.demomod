package com.lootsafe.mod.entity;

import com.lootsafe.mod.util.handlers.HandlerLootTable;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityLootSkele extends EntitySkeleton {

	public EntityLootSkele(World worldIn) 
	{
		super(worldIn);
	}
	
	public EntitySkeleton createChild(EntityAgeable ageable)
	{
		return new EntityLootSkele(world);
	}
	
	protected ResourceLocation getLootTable()
	{
		return HandlerLootTable.ZOMBIE;
	}	
	
	protected void applyEntityAttributes()
	{
	    super.applyEntityAttributes(); 
	    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(0.5D);
	}
}
