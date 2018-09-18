package com.lootsafe.mod.blocks.animation;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * ModelChest - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelLootChest extends ModelBase {

    public ModelRenderer chest;
	public ModelRenderer lock;
    public ModelRenderer lid;

    public ModelLootChest() 
    {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.lid = new ModelRenderer(this, 0, 0);
        this.lid.setRotationPoint(1.0F, 6.5F, 15.0F);
        this.lid.addBox(0.0F, -5.53F, -14.0F, 14, 5, 14, 0.0F);
        this.chest = new ModelRenderer(this, 0, 19);
        this.chest.setRotationPoint(1.0F, 6.0F, 1.0F);
        this.chest.addBox(0.0F, 0.0F, 0.0F, 14, 10, 14, 0.0F);        
        this.lock = new ModelRenderer(this, 0, 0);
        this.lock.setRotationPoint(8.0F, 7.0F, 15.0F);
        this.lock.addBox(-1.0F, -2.0F, -15.0F, 2, 4, 1, 0.0F);
    }

    public void renderAll()
    {
        this.lock.rotateAngleX = this.lid.rotateAngleX;
        this.lid.render(0.0625F);
        this.lock.render(0.0625F);
        this.chest.render(0.0625F);
    }
    
}