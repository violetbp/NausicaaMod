package mooklabs.nausicaamod.blocks;

import java.util.Random;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PetrifiedLog extends Block {

	/** The type of tree this log came from. */
	// public static final String[] woodType = new String[] {"","","",""};
	@SideOnly(Side.CLIENT)
	private IIcon tree_side;
	@SideOnly(Side.CLIENT)
	private IIcon tree_top;

	public PetrifiedLog() {
		super(Material.rock);
		setHardness(2.0F);
		setHardness(1.5F);
		setResistance(10.0F);
		setStepSound(Block.soundTypeStone);
		setBlockName("fossilTrunk");
		setBlockTextureName("fossilTrunk");
		
	}
	@Override
	public Item getItemDropped(int par1, Random random, int par3)
    {
        return random.nextInt(3)==0? Item.getItemFromBlock(this) : Item.getItemFromBlock(Main.pureDirt);
    }
	/**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    @Override
	protected boolean canSilkHarvest()
    {
        return true;
    }
    @Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.tree_side = iconRegister.registerIcon(Main.itemfold + ":" + this.getTextureName() + "_Side");
		this.tree_top = iconRegister.registerIcon(Main.itemfold + ":" + this.getTextureName() + "_Bottom");
	}
    @Override
	@SideOnly(Side.CLIENT)
	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	public IIcon getIcon(int par1, int par2) {
		if (par1 == 1 || par1 == 0) // 1 is top, 0 is bottom
		return this.tree_top;
		return this.tree_side;
	}
}
