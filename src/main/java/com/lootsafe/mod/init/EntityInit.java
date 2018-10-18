package com.lootsafe.mod.init;

import com.lootsafe.mod.Main;
import com.lootsafe.mod.Reference;
import com.lootsafe.mod.entity.EntityLootSkele;
import com.lootsafe.mod.entity.EntityLootSpider;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {
	
	/*
	 * Methods
	 */
	
	public static void registerEntities(){
		registerEntity("lootskele", EntityLootSkele.class, Reference.ENTITY_LOOTSKELETON, 50, 000000, 255000000);
		registerEntity("lootspider", EntityLootSpider.class, Reference.ENTITY_LOOTSPIDER, 60, 000000, 000255000);
		registerEntity("lootzombie", EntityLootSpider.class, Reference.ENTITY_LOOTZOMBIE, 60, 000000, 000000255);
	}
	
	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2){
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, id, Main.instance, range, 1, true, color1, color2);
	}
	
}
