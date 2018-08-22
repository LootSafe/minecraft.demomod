package com.lootsafe.mod.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.world.World;

public class EntityLootSkele extends EntitySkeleton {

	public EntityLootSkele(World worldIn) {
		super(worldIn);
	}
	
	public EntitySkeleton createChild(EntityAgeable ageable){
		return new EntityLootSkele(world);
	}
	
}
