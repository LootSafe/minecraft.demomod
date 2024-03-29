package com.lootsafe.mod.entity.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLootSkeleton extends ModelBase {
    public ModelRenderer field_178723_h;
    public ModelRenderer field_178721_j;
    public ModelRenderer field_78116_c;
    public ModelRenderer field_78115_e;
    public ModelRenderer field_178724_i;
    public ModelRenderer field_178722_k;
    public ModelRenderer field_178720_f;

    private float sizeMultiplier = 1.0f;
    
    public ModelLootSkeleton(float sizeMultiplier) {
    	this.sizeMultiplier = sizeMultiplier;
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.field_178722_k = new ModelRenderer(this, 0, 16);
        this.field_178722_k.mirror = true;
        this.field_178722_k.setRotationPoint(2.0F, 12.0F, 0.10000000149011612F);
        this.field_178722_k.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
        this.field_78116_c = new ModelRenderer(this, 0, 0);
        this.field_78116_c.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78116_c.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.field_178723_h = new ModelRenderer(this, 40, 16);
        this.field_178723_h.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.field_178723_h.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);
        this.setRotateAngle(field_178723_h, 0.0F, 0.0F, 0.10000000149011613F);
        this.field_78115_e = new ModelRenderer(this, 16, 16);
        this.field_78115_e.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78115_e.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.field_178724_i = new ModelRenderer(this, 40, 16);
        this.field_178724_i.mirror = true;
        this.field_178724_i.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.field_178724_i.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);
        this.setRotateAngle(field_178724_i, 0.0F, 0.0F, -0.10000000149011613F);
        this.field_178721_j = new ModelRenderer(this, 0, 16);
        this.field_178721_j.setRotationPoint(-2.0F, 12.0F, 0.10000000149011612F);
        this.field_178721_j.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
        this.field_178720_f = new ModelRenderer(this, 32, 0);
        this.field_178720_f.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_178720_f.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
	
        GL11.glPushMatrix();
        GL11.glTranslatef(0F, 1.5F - 1.5F * sizeMultiplier, 0F); 
        GL11.glScalef(sizeMultiplier, sizeMultiplier, sizeMultiplier);
            	
        this.field_178722_k.render(f5);
        this.field_78116_c.render(f5);
        this.field_178723_h.render(f5);
        this.field_78115_e.render(f5);
        this.field_178724_i.render(f5);
        this.field_178721_j.render(f5);
        this.field_178720_f.render(f5);   
        
        GL11.glPopMatrix();
    }
    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
}
