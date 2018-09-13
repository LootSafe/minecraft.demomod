package com.lootsafe.mod.util.handlers;

import com.lootsafe.mod.entity.EntityLootSkele;
import com.lootsafe.mod.entity.EntityLootSpider;
import com.lootsafe.mod.entity.renderer.RenderLootSkeleton;
import com.lootsafe.mod.entity.renderer.RenderLootSpider;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {
	
	public static void registerEntityRenderEntityLootSkele(){
		
		RenderingRegistry.registerEntityRenderingHandler(EntityLootSkele.class, new IRenderFactory<EntityLootSkele>(){

			@Override
			public Render<? super EntityLootSkele> createRenderFor(RenderManager manager) {
				return new RenderLootSkeleton(manager);
			}
			
		});
		
	}
	
	public static void registerEntityRenderEntityLootSpider(){
		
		RenderingRegistry.registerEntityRenderingHandler(EntityLootSpider.class, new IRenderFactory<EntityLootSpider>(){

			@Override
			public Render<? super EntityLootSpider> createRenderFor(RenderManager manager) {
				return new RenderLootSpider(manager);
			}
			
		});
		
	}
	
	public static void registerAllEntityRenders(){		
		registerEntityRenderEntityLootSkele();
		registerEntityRenderEntityLootSpider();
	}
	
}
