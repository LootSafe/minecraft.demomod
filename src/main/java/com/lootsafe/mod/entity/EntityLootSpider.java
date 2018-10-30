package com.lootsafe.mod.entity;

import com.lootsafe.mod.util.handlers.HandlerLootTable;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;
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
