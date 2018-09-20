package com.lootsafe.mod.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.world.World;

public class EntityLootSpider extends EntitySpider {

	public EntityLootSpider(World worldIn) 
	{
		super(worldIn);
	}
	
	public EntitySpider createChild(EntityAgeable ageable)
	{
		return new EntityLootSpider(world);
	}
	
}
