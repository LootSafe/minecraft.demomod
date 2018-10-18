package com.lootsafe.mod.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.monster.EntityZombie;
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
	
}
