package mooklabs.nausicaamod.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelText extends ModelBase
{
    private ModelRenderer batHead;
   
    public ModelText()
    {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.batHead = new ModelRenderer(this, 0, 0);
        this.batHead.addBox(-3.0F, -3.0F, -3.0F, 20, 6, 1);
        
    }

    /**
     * not actually sure this is size, is not used as of now, but the model would be recreated if the value changed and
     * it seems a good match for a bats size
     */
    public int getBatSize()
    {
        return 36;
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        float f6;
        
        
            f6 = (180F / (float)Math.PI);
            this.batHead.rotateAngleX = par6 / (180F / (float)Math.PI);
            this.batHead.rotateAngleY = par5 / (180F / (float)Math.PI);
            this.batHead.rotateAngleZ = 0.0F;
            this.batHead.setRotationPoint(0.0F, 0.0F, 0.0F);
            
        

        this.batHead.render(par7);
    }
}