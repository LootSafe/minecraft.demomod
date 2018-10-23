package com.lootsafe.mod.util.handlers;

import com.lootsafe.mod.Reference;
import com.lootsafe.mod.blocks.container.ContainerLootChest;
import com.lootsafe.mod.blocks.gui.GuiLootChest;
import com.lootsafe.mod.blocks.tileenity.TileEntityLootChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class HandlerGui implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == Reference.GUI_LOOT_CHEST)
		{
			return new ContainerLootChest(player.inventory, (TileEntityLootChest) world.getTileEntity(new BlockPos(x,y,z)), player);
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == Reference.GUI_LOOT_CHEST)
		{
			return new GuiLootChest(player.inventory, (TileEntityLootChest) world.getTileEntity(new BlockPos(x,y,z)), player);
		}
		
		return null;
	}

}
