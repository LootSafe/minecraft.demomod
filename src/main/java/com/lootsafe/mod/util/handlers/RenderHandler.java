package com.lootsafe.mod.util.handlers;

import com.lootsafe.mod.entity.EntityLootSkele;
import com.lootsafe.mod.entity.renderer.RenderLootSkeleton;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {
	
	public static void registerEntityRenders(){
		
		RenderingRegistry.registerEntityRenderingHandler(EntityLootSkele.class, new IRenderFactory<EntityLootSkele>(){

			@Override
			public Render<? super EntityLootSkele> createRenderFor(RenderManager manager) {
				return new RenderLootSkeleton(manager);
			}
			
		});
		
	}
	
}
