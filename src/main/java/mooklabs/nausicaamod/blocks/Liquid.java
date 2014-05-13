package mooklabs.nausicaamod.blocks;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class Liquid extends BlockFluidClassic {

	@SideOnly(Side.CLIENT)
    protected IIcon stillIcon;
    @SideOnly(Side.CLIENT)
    protected IIcon flowingIcon;
   
    public Liquid(Fluid fluid, Material material) {
            super(fluid, material);
            this.setBlockName("liquidSandBlock");
            this.setBlockTextureName(Main.itemfold + ":liquidSandBlock");
            
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
    	this.blockIcon = iconRegister.registerIcon(this.getTextureName());
    }
    @Override
    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return this.blockIcon;
    }
   
}
