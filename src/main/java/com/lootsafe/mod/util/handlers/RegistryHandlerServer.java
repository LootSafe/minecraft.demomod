package com.lootsafe.mod.util.handlers;
import com.lootsafe.mod.init.BlockInit;
import com.lootsafe.mod.init.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(value = Side.SERVER)
public class RegistryHandlerServer {
	
	/* Important Stuff Here */
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event){
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event){
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));		
		TileEntityHandler.registerTileEntities();		
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event){}
	
}
