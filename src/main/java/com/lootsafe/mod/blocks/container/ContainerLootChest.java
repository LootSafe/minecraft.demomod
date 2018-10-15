package com.lootsafe.mod.blocks.container;

import com.lootsafe.mod.Main;
import com.lootsafe.mod.Reference;
import com.lootsafe.mod.blocks.tileenity.TileEntityLootChest;
import com.lootsafe.mod.items.ItemBase;
import com.lootsafe.mod.util.network.CustomNetworkMessage;

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
	private ItemBase selectedItem;
	private int selectedItemId;
	
	private void HandleWebStuff(EntityPlayer player, int slotId, int selectedItemId)
	{
		boolean success = false;
		
		// Server Logic
		
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
		{		
			Main.network.sendToServer(new CustomNetworkMessage(player.getName(), selectedItem.getItemAddress(), selectedItemId, slotId));
		}
		
		cleanUp(success, player, slotId, selectedItemId);	
	}
	
	public void cleanUp(boolean success, EntityPlayer player, int slotId, int selectedItemId)
	{
		ItemStack itemstack;
		
		if(success) 
		{
			player.sendMessage(new TextComponentString(Reference.SendingItemText));	

			itemstack = this.inventorySlots.get(slotId).getStack();
			itemstack.shrink(1);
			itemstack = this.inventorySlots.get(selectedItemId).getStack();
			itemstack.shrink(1);
			itemstack = null; 
		}
		else
		{
			player.sendMessage(new TextComponentString(TextFormatting.RED + "Error sending item, try later."));
		}
		
		player.closeScreen();	
	}
	
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player){

		// See if what the user has clicked on is a tokenizable item
			
		Slot slot;
		
		try{
			slot = this.inventorySlots.get(slotId);
		} catch(Exception e) {
			// For some reason an array out of bounds error gets triggered
			// This is to solve these weird clicks
			return super.slotClick(slotId, dragType, clickTypeIn, player);	
		}
		
		ItemStack itemstack = slot.getStack();
		ItemBase tokenizableItem = null;
		
		if(itemstack.getItem() instanceof ItemBase) {
			
			tokenizableItem = (ItemBase)itemstack.getItem();
			
			if(tokenizableItem != null)
			{		
				if(tokenizableItem.getIsTokenizable())
				{
					// The item is in hand ready to be transferred to the chest slot
					// Only a valid tokenizable item
					// ie When the tooltip showing the item to transfer is selected
					selectedItem = tokenizableItem;
					selectedItemId = slotId;
				}				
			}
		}
		else {
			
			// It its not castable to a tokenizable object
			
			if(selectedItem != null && validSlot(slotId))
			{					
				// If a tokenizable item is selected and is being placed into an empty slot inside of the chest part of the UI 
				
				HandleWebStuff(player, slotId, selectedItemId);							
			}
		}	
		
		return super.slotClick(slotId, dragType, clickTypeIn, player);		
	}
	
	private boolean validSlot(int slotId){
		
		if(slotId >= 0 && slotId <= 71){
			return true;
		}
		else {
			return false;
		}
	}	
	
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
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
		ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            
            if (index < this.numRows * 9)
            {
                if (!this.mergeItemStack(itemstack1, this.numRows * 9, this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.numRows * 9, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
	}
	
	public TileEntityLootChest getChestInventory()
	{
		return this.chestInventory;
	}
	
}