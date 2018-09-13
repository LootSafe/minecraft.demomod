package com.lootsafe.mod.entity.renderer;

import com.lootsafe.mod.entity.EntityLootSpider;
import com.lootsafe.mod.entity.model.ModelLootSpider;
import com.lootsafe.mod.util.Reference;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderLootSpider extends RenderLiving<EntityLootSpider> {

	private static float spiderSize = 3.0f;
	
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/entity/lootspider.png");
	
	public RenderLootSpider(RenderManager manager){
		super(manager, new ModelLootSpider(spiderSize), spiderSize);
	}
	
	protected ResourceLocation getEntityTexture(EntityLootSpider entity){
		return TEXTURES;
	}

}
