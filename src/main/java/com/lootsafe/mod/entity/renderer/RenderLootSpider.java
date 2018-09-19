package com.lootsafe.mod.entity.renderer;

import com.lootsafe.mod.Reference;
import com.lootsafe.mod.entity.EntityLootSpider;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderLootSpider extends RenderLiving<EntityLootSpider> {

	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/entity/lootspider.png");
	
	public RenderLootSpider(RenderManager manager, float spiderSize){
		//super(manager, new ModelLootSpider(spiderSize), spiderSize);
		super(manager, new ModelSpider(), spiderSize);
	}
	
	protected ResourceLocation getEntityTexture(EntityLootSpider entity){
		return TEXTURES;
	}

}
