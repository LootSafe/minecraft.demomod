package com.lootsafe.mod.blocks.gui;

import com.lootsafe.mod.Reference;
import com.lootsafe.mod.blocks.container.ContainerLootChest;
import com.lootsafe.mod.blocks.tileenity.TileEntityLootChest;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiLootChest extends GuiContainer {

	private static final ResourceLocation GUI_CHEST = new ResourceLocation(Reference.MOD_ID + ":textures/gui/loot_chest.png");
	private final InventoryPlayer playerInventory;
	private final TileEntityLootChest te;
	
	public GuiLootChest(InventoryPlayer playerInventory, TileEntityLootChest chestInventory, EntityPlayer player) 
	{
		super(new ContainerLootChest(playerInventory, chestInventory, player));
		this.playerInventory = playerInventory;
		this.te = chestInventory;		
		this.xSize = 179;
		this.ySize = 256;
	}
		
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		this.fontRenderer.drawString(this.te.getDisplayName().getUnformattedText(), 8, 6, 16086784);
		this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 92, 16086784);
	}
	
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		this.drawDefaultBackground();
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(GUI_CHEST);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
}
