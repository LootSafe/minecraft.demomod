package com.lootsafe.mod.entity.renderer;

import com.lootsafe.mod.Reference;
import com.lootsafe.mod.entity.EntityLootSkele;

import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderLootSkeleton extends RenderLiving<EntityLootSkele> {
	
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/entity/lootskele.png");
	
	public RenderLootSkeleton(RenderManager manager, float skeleSize)
	{
		//super(manager, new ModelLootSkeleton(skeleSize), skeleSize);
		super(manager, new ModelSkeleton(), skeleSize);
	}
	
	protected ResourceLocation getEntityTexture(EntityLootSkele entity)
	{
		return TEXTURES;
	}

}
