package com.lootsafe.mod.entity.renderer;

import com.lootsafe.mod.Reference;
import com.lootsafe.mod.entity.EntityLootSkele;
import com.lootsafe.mod.entity.model.ModelLootSkeleton;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderLootSkeleton extends RenderLiving<EntityLootSkele> {

	private static float skeleSize = 3.0f;
	
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/entity/lootskele.png");
	
	public RenderLootSkeleton(RenderManager manager){
		super(manager, new ModelLootSkeleton(skeleSize), skeleSize);
	}
	
	protected ResourceLocation getEntityTexture(EntityLootSkele entity){
		return TEXTURES;
	}

}
