package com.lootsafe.mod.util.handlers;

import com.lootsafe.mod.entity.EntityLootSkele;
import com.lootsafe.mod.entity.EntityLootSpider;
import com.lootsafe.mod.entity.EntityLootZombie;
import com.lootsafe.mod.entity.renderer.RenderLootSkeleton;
import com.lootsafe.mod.entity.renderer.RenderLootSpider;
import com.lootsafe.mod.entity.renderer.RenderLootZombie;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class HandlerRender {
	
	public static void registerEntityRenderEntityLootSkele(float skeleSize){
		
		RenderingRegistry.registerEntityRenderingHandler(EntityLootSkele.class, new IRenderFactory<EntityLootSkele>(){

			@Override
			public Render<? super EntityLootSkele> createRenderFor(RenderManager manager) 
			{
				return new RenderLootSkeleton(manager, skeleSize);
			}
			
		});
		
	}
	
	public static void registerEntityRenderEntityLootSpider(float spiderSize){
		
		RenderingRegistry.registerEntityRenderingHandler(EntityLootSpider.class, new IRenderFactory<EntityLootSpider>(){

			@Override
			public Render<? super EntityLootSpider> createRenderFor(RenderManager manager) 
			{
				return new RenderLootSpider(manager, spiderSize);
			}
			
		});
		
	}
	
	public static void registerEntityRenderEntityLootZombie(float zombieSize){
		
		RenderingRegistry.registerEntityRenderingHandler(EntityLootZombie.class, new IRenderFactory<EntityLootZombie>(){

			@Override
			public Render<? super EntityLootZombie> createRenderFor(RenderManager manager) 
			{
				return new RenderLootZombie(manager, zombieSize);
			}
			
		});
		
	}
	
	public static void registerAllEntityRenders(float size)
	{		
		registerEntityRenderEntityLootSkele(size);
		registerEntityRenderEntityLootSpider(size);
		registerEntityRenderEntityLootZombie(size);
	}
	
}
