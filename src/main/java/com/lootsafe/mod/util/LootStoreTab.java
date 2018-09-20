package com.lootsafe.mod.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class LootStoreTab extends CreativeTabs {

	public LootStoreTab(String label) 
	{ 
		super("lootstoretab"); 
	}

	@Override
	public ItemStack getTabIconItem() 
	{
		return null;
	}
	
}
