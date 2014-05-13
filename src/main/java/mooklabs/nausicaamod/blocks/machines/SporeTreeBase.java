package mooklabs.nausicaamod.blocks.machines;

import java.util.Random;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SporeTreeBase extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon tree_side;
	@SideOnly(Side.CLIENT)
	private IIcon tree_top;

	public SporeTreeBase() {
		super(Material.wood);
		this.setTickRandomly(true);
		this.setBlockName("sporeTreeBase");
		setBlockTextureName("poisonLog");
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
		// EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		// ?
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.tree_side = iconRegister.registerIcon(Main.itemfold + ":" + this.getTextureName() + "_side");
		this.tree_top = iconRegister.registerIcon(Main.itemfold + ":" + this.getTextureName() + "_top");
	}

	@Override
	@SideOnly(Side.CLIENT)
	/** From the specified side and block metadata retrieves the blocks texture. Args: side, metadata*/
	public IIcon getIcon(int par1, int par2) {
		if (par1 == 1 || par1 == 0) // 1 is top, 0 is bottom
		return this.tree_top;
		return this.tree_side;
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(World world, int x, int y, int z, Random random) {
		// TechMain.printToPlayer("updating spore block");
		int l;

		for (l = 1; world.getBlock(x, y - l, z) == this; ++l)// counts blocks below it
		{
		}
		if (world.isAirBlock(x, y + 1, z) || world.getBlock(x, y+1, z) == Main.poisonLeaves) {

			if (l < 20)// TODO make random?
			{
				int i1 = world.getBlockMetadata(x, y, z);

				if (i1 <= 0) {
					world.setBlock(x, y + 1, z, this,4,3);
					this.onNeighborBlockChange(world, x, y + 1, z, this);
				} else {
					//world.setBlockMetadataWithNotify(x, y, z, i1 + 1, 4);
				}
			} else {// hight>20
				if(world.isAirBlock(x, y + 1, z))
				world.setBlock(x, y + 1, z, Main.poisonLeaves, 2, 3);
			}
		}
		if (random.nextInt(1) == 0) {// random log
			int a=0, c=0;
			if(random.nextBoolean())
			a = random.nextInt(3)-1;//random number
			else
			c = random.nextInt(3)-1;//random number
			
			int f;
			for (f = 1; world.getBlock(x, y - f, z) == this; ++f);// counts blocks below it

			if (world.isAirBlock(x + a, y, z + c) && !world.isAirBlock(x + a, y - 1, z + c) && (world.getBlock(x + a, y - 1, z + c) == Main.sporeTree)) world.setBlock(x + a, y, z + c, this,4,3);

		}
		if (random.nextInt(1) == 0 && l > 5) {
			int a=0, c=0;
			if(random.nextBoolean())
			a = random.nextInt(3)-1;//random number
			else
			c = random.nextInt(3)-1;//random number
			
			if (world.isAirBlock(x + a, y, z + c) && !world.isAirBlock(x + a, y - 1, z + c)) {
				world.setBlock(x + a, y, z + c, Main.poisonLeaves, 1, 3);
			}

		}

	}
}
