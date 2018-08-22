package com.lootsafe.mod.util.handlers;
import com.lootsafe.mod.Main;
import com.lootsafe.mod.blocks.animation.RenderLootChest;
import com.lootsafe.mod.blocks.tileenity.TileEntityLootChest;
import com.lootsafe.mod.init.BlockInit;
import com.lootsafe.mod.init.CustomInit;
import com.lootsafe.mod.init.ItemInit;
import com.lootsafe.mod.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@EventBusSubscriber
public class RegistryHandler {
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event){
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event){
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLootChest.class, new RenderLootChest());
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event){
		
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(BlockInit.LOOT_CHEST), 0, "container.loot_chest");
		
		for(Item item : ItemInit.ITEMS){
			if(item instanceof IHasModel){
				((IHasModel)item).registerModels();
			}
		}
		
		for(Block block : BlockInit.BLOCKS){
			if(block instanceof IHasModel){
				((IHasModel)block).registerModels();
			}
		}
	}
	
	public static void preInitRegistries(){		
		CustomInit.registerEntities();
		RenderHandler.registerEntityRenders();
	}
	
	public static void initRegistries(){
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
	}
}
