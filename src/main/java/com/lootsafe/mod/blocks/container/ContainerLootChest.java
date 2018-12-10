package com.lootsafe.mod.blocks.container;

import com.lootsafe.mod.Main;
import com.lootsafe.mod.blocks.tileenity.TileEntityLootChest;
import com.lootsafe.mod.items.ItemBase;
import com.lootsafe.mod.util.network.NetworkMessage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class ContainerLootChest extends Container
{
	private final int numRows;
	private final TileEntityLootChest chestInventory;	
	private ItemBase selectedItem = null;

	/* Chest Logic */
	
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player)
	{	
		boolean isValidSlot = validSlot(slotId);
		boolean dragging = dragType == 1 ? true : false;
		
		Slot slot;
		ItemStack itemStack;
		
		if(slotId < 0) { return super.slotClick(slotId, dragType, clickTypeIn, player); }
			
		slot = this.inventorySlots.get(slotId);
		itemStack = slot.getStack();
		
		if(!dragging && isPlayerSlot(slotId))
		{								
			if(itemStack.getItem() instanceof ItemBase)
			{
				selectedItem = (ItemBase) itemStack.getItem();
			}
		}
		
		if(selectedItem != null && dragging)
		{
			// Disables right click selecting object from chest, causes issues
			return ItemStack.EMPTY;
		}
						
		if(!(itemStack.getItem() instanceof ItemBase))
		{
			if(!dragging)
			{							
				if(selectedItem != null && isValidSlot)
				{					
					player.inventory.setItemStack(ItemStack.EMPTY);
					player.inventory.setInventorySlotContents(slotId, ItemStack.EMPTY);
					
					if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
					{		
						Main.network.sendToServer(new NetworkMessage(player.getName(), selectedItem.getUnlocalizedName()));
					}	
																						
					return ItemStack.EMPTY;
				}
			}		
		}
		else
		{
			if(dragging)
			{
				selectedItem = (ItemBase) itemStack.getItem();
				return super.slotClick(slotId, dragType, clickTypeIn, player);
			}		
		}		
		
		return super.slotClick(slotId, dragType, clickTypeIn, player);
	}
	
	private boolean isPlayerSlot(int slotId)
	{
		if(slotId >= 72 && slotId <= 107)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private boolean validSlot(int slotId)
	{		
		if(slotId >= 0 && slotId <= 71)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

	/* Other Chest Logic */
	
	public ContainerLootChest(InventoryPlayer playerInv, TileEntityLootChest tileEntityLootChest, EntityPlayer player) 
	{
		this.chestInventory = tileEntityLootChest;
		this.numRows = tileEntityLootChest.getSizeInventory() / 9;
		tileEntityLootChest.openInventory(player);
		
		for(int i = 0; i < this.numRows; ++i)
		{
			for(int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(tileEntityLootChest, j + i*9, 8 + j*18, 18 + i*18));
			}
		}
		
		for(int y = 0; y < 3; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				this.addSlotToContainer(new Slot(playerInv, x + y*9 + 9, 8 + x*18, 175 + y*18));
			}
		}
		
		for(int x = 0; x < 9; x++)
		{
			this.addSlotToContainer(new Slot(playerInv, x, 8 + x*18, 233));
		}
				
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		boolean flag = Main.proxy.isPlayerRegistered(playerIn.getName());
		
		if(flag == false)
		{
			playerIn.sendMessage(new TextComponentString(TextFormatting.RED + "Please Register Your Wallet Address."));	
		}
		
		return flag;
	}
	
	@Override
	public void onContainerClosed(EntityPlayer playerIn) 
	{
		super.onContainerClosed(playerIn);
		chestInventory.closeInventory(playerIn);
	}
	
	/* Other */
	
	public TileEntityLootChest getChestInventory()
	{
		return this.chestInventory;
	}	
	
}