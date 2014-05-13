package mooklabs.nausicaamod.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mooklabs.nausicaamod.Main;
import net.minecraft.block.BlockDirt;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class PureDirt extends BlockDirt {

	public PureDirt() {
		super();
		setHarvestLevel("shovel", 1);
		setHardness(0.5F);
		setLightLevel(0.6F);
		setStepSound(soundTypeGravel);
		setBlockName("pureDirt");
		setBlockTextureName(Main.itemfold + ":pureDirt");
	}
	
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
        p_149666_3_.add(new ItemStack(this, 1, 0));
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
    	this.blockIcon = p_149651_1_.registerIcon(this.getTextureName());
    	}
    @Override
    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return this.blockIcon;
    }
}
