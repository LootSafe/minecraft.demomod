package com.lootsafe.mod.entity.renderer;

import com.lootsafe.mod.Reference;
import com.lootsafe.mod.entity.EntityLootZombie;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderLootZombie extends RenderLiving<EntityLootZombie> {
	
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/entity/lootzombie.png");
	
	public RenderLootZombie(RenderManager manager, float zombieSize)
	{
		super(manager, new ModelZombie(), zombieSize);
	}
	
	protected ResourceLocation getEntityTexture(EntityLootZombie entity)
	{
		return TEXTURES;
	}

}
