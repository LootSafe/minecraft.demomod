package com.lootsafe.mod.Items;

import java.util.List;

import com.lootsafe.mod.Main;
import com.lootsafe.mod.Reference;
import com.lootsafe.mod.init.ItemInit;
import com.lootsafe.mod.util.Modable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBase extends Item implements Modable {

	private String itemAddress;
	private boolean isTokenizable;
	
	public ItemBase(String name){
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MATERIALS);	
		
		this.isTokenizable = false;
		
		ItemInit.ITEMS.add(this);
	}		
	
	public ItemBase(String name, String itemAddress){
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MATERIALS);	
		
		this.itemAddress = itemAddress;
		this.isTokenizable = true;
		
		ItemInit.ITEMS.add(this);
	}	
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "Inventory");		
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {	
		
		if(stack.getItem().getUnlocalizedName().equals("item.lootcoin-gold"))
		{
			for(String s : Reference.description_lootcoin_gold){
				tooltip.add(s);
			}
			
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
		
		if(stack.getItem().getUnlocalizedName().equals("item.lootcoin-silver"))
		{
			for(String s : Reference.description_lootcoin_silver){
				tooltip.add(s);
			}
			
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
		
		if(stack.getItem().getUnlocalizedName().equals("item.lootcoin-copper"))
		{
			for(String s : Reference.description_lootcoin_copper){
				tooltip.add(s);
			}
			
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
		
		if(stack.getItem().getUnlocalizedName().equals("LootSafe.name"))
		{
			for(String s : Reference.description_lootchest){
				tooltip.add(s);
			}
			
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}		
		
	}
	
	public String getItemAddress(){
		return this.itemAddress;
	}
	
	public boolean getIsTokenizable(){
		return this.isTokenizable;
	}
	
}
