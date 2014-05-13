package mooklabs.nausicaamod;

import java.io.File;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MathHelper;


public class NausicaaGameSettings extends GameSettings {

	public NausicaaGameSettings(Minecraft par1Minecraft, File par2File) {
		super(par1Minecraft, par2File);
		
	}
	

	
	
	@SideOnly(Side.CLIENT)
    public static enum Options
    {
       INVERT_MOUSE("options.invertMouse", false, true),
       INVERT_MOUE("options.invertMouse", false, true);
       
       private final boolean enumFloat;
       private final boolean enumBoolean;
       private final String enumString;
       private final float valueStep;
       private float valueMin;
       private float valueMax;
       
		private Options(String par3Str, boolean par4, boolean par5)
        {
            this(par3Str, par4, par5, 0.0F, 1.0F, 0.0F);
        }

        private Options(String p_i45004_3_, boolean p_i45004_4_, boolean p_i45004_5_, float p_i45004_6_, float p_i45004_7_, float p_i45004_8_)
        {
            this.enumString = p_i45004_3_;
            this.enumFloat = p_i45004_4_;
            this.enumBoolean = p_i45004_5_;
            this.valueMin = p_i45004_6_;
            this.valueMax = p_i45004_7_;
            this.valueStep = p_i45004_8_;
        }
        
        public static NausicaaGameSettings.Options getEnumOptions(int par0)
        {
            NausicaaGameSettings.Options[] aoptions = values();
            int j = aoptions.length;

            for (int k = 0; k < j; ++k)
            {
            	NausicaaGameSettings.Options options = aoptions[k];

                if (options.returnEnumOrdinal() == par0)
                {
                    return options;
                }
            }

            return null;
        }
        public boolean getEnumFloat()
        {
            return this.enumFloat;
        }

        public boolean getEnumBoolean()
        {
            return this.enumBoolean;
        }

        public int returnEnumOrdinal()
        {
            return this.ordinal();
        }

        public String getEnumString()
        {
            return this.enumString;
        }

        public float getValueMax()
        {
            return this.valueMax;
        }

        public void setValueMax(float p_148263_1_)
        {
            this.valueMax = p_148263_1_;
        }

        public float normalizeValue(float p_148266_1_)
        {
            return MathHelper.clamp_float((this.snapToStepClamp(p_148266_1_) - this.valueMin) / (this.valueMax - this.valueMin), 0.0F, 1.0F);
        }

        public float denormalizeValue(float p_148262_1_)
        {
            return this.snapToStepClamp(this.valueMin + (this.valueMax - this.valueMin) * MathHelper.clamp_float(p_148262_1_, 0.0F, 1.0F));
        }

        public float snapToStepClamp(float p_148268_1_)
        {
            p_148268_1_ = this.snapToStep(p_148268_1_);
            return MathHelper.clamp_float(p_148268_1_, this.valueMin, this.valueMax);
        }

        protected float snapToStep(float p_148264_1_)
        {
            if (this.valueStep > 0.0F)
            {
                p_148264_1_ = this.valueStep * Math.round(p_148264_1_ / this.valueStep);
            }

            return p_148264_1_;
        }

        Options(String p_i45005_3_, boolean p_i45005_4_, boolean p_i45005_5_, float p_i45005_6_, float p_i45005_7_, float p_i45005_8_, Object p_i45005_9_)
        {
            this(p_i45005_3_, p_i45005_4_, p_i45005_5_, p_i45005_6_, p_i45005_7_, p_i45005_8_);
        }
        
    }

}
