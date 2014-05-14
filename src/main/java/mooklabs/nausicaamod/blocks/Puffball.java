package mooklabs.nausicaamod.blocks;

import java.util.List;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Puffball extends Block {

	/** The type of puffball it came from. */
	public static final String[] puffballType = new String[] { "Blue", "Purple", "Pink", "Green" };

	//@SideOnly(Side.CLIENT) server didnt like??????
	private IIcon[] icons = new IIcon[puffballType.length];

	public Puffball() {
		super(Material.sponge);
		this.setBlockTextureName(Main.itemfold + ":puffball");
		this.setBlockName("puffball");
	}
	
	

	/**
	 * returns a number between 0 and 3
	 */
	public static int limitToValidMetadata(int par0) {
		return par0 & 3;
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	public IIcon getIcon(int par1, int par2) {
		try {
			return icons[par2];
		} catch (ArrayIndexOutOfBoundsException e) {
			//System.err.println("[NausicaaMod][FatalErrorCought] Metadata or something");
		}
		return icons[0];
	}

	/**
	 * Can this block stay at this position. Similar to canPlaceBlockAt except gets checked often with plants.
	 */
	@Override
	public boolean canBlockStay(World par1World, int par2, int par3, int par4) {
		return true;// TODO make it drop if not connected to anything
		/* int l = getDirection(par1World.getBlockMetadata(par2, par3, par4)); par2 += Direction.offsetX[l]; par4 += Direction.offsetZ[l]; int i1 = par1World.getBlockId(par2,
		 * par3, par4); return i1 == Main.poisonLog.blockID; */
	
	}
	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	 */
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
		par3List.add(new ItemStack(par1, 1, 3));
		par3List.add(new ItemStack(par1, 1, 4));

	}


	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 */
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		for (int i = 0; i < puffballType.length; ++i) {
			this.icons[i] = par1IconRegister.registerIcon(this.getTextureName() + puffballType[i]);
			//System.out.println(this.getTextureName() + puffballType[i]);
		}
	}
}
