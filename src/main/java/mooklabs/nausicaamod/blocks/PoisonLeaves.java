package mooklabs.nausicaamod.blocks;

import java.util.Random;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

// TODO make acctuall leaves

public class PoisonLeaves extends Block {

	public PoisonLeaves() {
		super(Material.leaves);
		setHardness(0.2F);
		this.setTickRandomly(true);
		setLightOpacity(1);
		setStepSound(soundTypeGrass);
		setBlockName("posionLeaves");
		setBlockTextureName(Main.itemfold + ":" + "poisonLeaves");

	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return Items.stick;
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(World world, int x, int y, int z, Random random) {
		// TechMain.printToPlayer("updating spore block");

		if (!world.isAirBlock(x, y, z)) {
			int l, j, l1, j1, xSize, ySixe;

			for (l = 1; world.getBlock(x, y, z - l) == this; ++l);// counts blocks to left and right
			

			for (j1 = 1; world.getBlock(x - j1, y, z) == this; ++j1);//moves to far x val
			for (j = j1; world.getBlock(x + j, y, z) == this; ++j);//moves other way to count all of them

			int i1 = world.getBlockMetadata(x, y, z);
			int size = 3;
			if (i1 == 1) size = 6;
			if (i1 == 2) size = z;

			if (l < size)// TODO make random
			{

				if (random.nextBoolean()) {
					if (world.isAirBlock(x, y, z+1)) 
						world.setBlock(x, y, z+1, this);
				} else {
					if (world.isAirBlock(x, y, z-1)) world.setBlock(x, y, z-1, this);
				}
			}
			if (j < size)// TODO make random
			{

				if (random.nextBoolean()) {
					if (world.isAirBlock(x+1, y, z)) world.setBlock(x + 1, y, z, this);
				} else {
					if (world.isAirBlock(x-1, y, z)) world.setBlock(x - 1, y, z, this);
				}

			}
		}

	}
}

/* public class PoisonLeaves extends BlockLeaves{
 * private Icon tex;
 * public static final String[][] field_94396_b = new String[][] {{"leaves_oak"}, {"leaves_oak_opaque"}};
 * public PoisonLeaves(int id) {
 * super(id);
 * setHardness(0.2F);
 * setLightOpacity(1);
 * setStepSound(soundGrassFootstep);
 * setUnlocalizedName("posionLeaves");
 * //setTextureName("posionLeaves");
 * setTextureName(Main.itemfold + ":" + "leaves");
 * }
 * @SideOnly(Side.CLIENT)
 * /**
 * Pass true to draw this block using fancy graphics, or false for fast graphics.
 * public void setGraphicsLevel(boolean par1)
 * {
 * this.graphicsLevel = par1;
 * // this.iconType = par1 ? 0 : 1;
 * }
 * @SideOnly(Side.CLIENT)
 * /**
 * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
 * public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
 * {
 * par3List.add(new ItemStack(par1, 1, 0));
 * }
 * /**
 * Is this block (a) opaque and (b) a full 1m cube? This determines whether or not to render the shared face of two
 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
 * public boolean isOpaqueCube()
 * {
 * return !this.graphicsLevel;
 * }
 * @Override
 * public boolean isShearable(ItemStack item, World world, int x, int y, int z) {
 * // TODO Auto-generated method stub
 * return false;
 * }
 * public int idDropped(int par1, Random par2Random, int par3)
 * {
 * return Main.poisonLeaves.blockID;
 * }
 * @Override
 * public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x,
 * int y, int z, int fortune) {
 * // TODO Auto-generated method stub
 * return null;
 * }
 * @SideOnly(Side.CLIENT)
 * /**
 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
 * is the only chance you get to register icons.
 * public void registerIcons(IconRegister par1IconRegister)
 * {
 * tex = par1IconRegister.registerIcon(Main.itemfold + ":" + "leaves");
 * if(false)
 * for (int i = 0; i < field_94396_b.length; ++i)
 * {
 * // this.iconArray[i] = new Icon[field_94396_b[i].length];
 * for (int j = 0; j < field_94396_b[i].length; ++j)
 * {
 * // this.iconArray[i][j] = par1IconRegister.registerIcon(field_94396_b[i][j]);
 * }
 * }
 * }
 * /**
 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
 * public Icon getIcon(int par1)
 * {
 * return tex; //(par2 & 3) == 1 ? this.iconArray[this.iconType][1] : ((par2 & 3) == 3 ? this.iconArray[this.iconType][3] : ((par2 & 3) == 2 ? this.iconArray[this.iconType][2]
 * : this.iconArray[this.iconType][0]));
 * // return (par2 & 3) == 1 ? this.iconArray[this.iconType][1] : ((par2 & 3) == 3 ? this.iconArray[this.iconType][3] : ((par2 & 3) == 2 ? this.iconArray[this.iconType][2] :
 * this.iconArray[this.iconType][0]));
 * }
 * } */
