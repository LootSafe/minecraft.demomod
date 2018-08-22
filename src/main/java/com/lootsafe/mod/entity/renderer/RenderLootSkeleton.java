package com.lootsafe.mod.entity.renderer;

import com.lootsafe.mod.entity.EntityLootSkele;
import com.lootsafe.mod.entity.model.ModelLootSkeleton;
import com.lootsafe.mod.util.Reference;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderLootSkeleton extends RenderLiving<EntityLootSkele> {

	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/entity/lootskele.png");
	
	public RenderLootSkeleton(RenderManager manager){
		super(manager, new ModelLootSkeleton(1.0f), 1.0F);
	}
	
	protected ResourceLocation getEntityTexture(EntityLootSkele entity){
		return TEXTURES;
	}

}
