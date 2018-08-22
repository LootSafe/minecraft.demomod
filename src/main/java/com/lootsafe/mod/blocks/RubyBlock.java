package com.lootsafe.mod.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class RubyBlock extends BlockBase{

	public RubyBlock(String name, Material material) {
		super(name, material);
		
		setSoundType(SoundType.METAL); // Sound the block makes when you walk over it
		setHardness(5.0f); // How long it takes to break the block
		setResistance(6000.0f); // This is how resistant the block is to explosives
		setHarvestLevel("pickaxe", 2); // Tool used to break your block
		setLightLevel(15.0f); // How much light your block emits
		setLightOpacity(0); // How much light passed through the object 
		setBlockUnbreakable(); // Makes the block unbreakable
	}
	
}
