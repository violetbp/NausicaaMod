package bcblocks.bettertable.table;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import bcblocks.bettertable.BetterTable;
import bcblocks.bettertable.tileentity.TileEntityDeviceCraftingTable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBetterTable extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon workbenchIconTop;
	@SideOnly(Side.CLIENT)
	private IIcon workbenchIconFront;

	public BlockBetterTable() {
		super(Material.wood);
		this.setBlockName("betterTable");
		this.setBlockTextureName("BetterTable:betterTable");
		this.setHardness(2.5F);
		this.setResistance(2000.0F);
		this.setStepSound(Block.soundTypeWood);
	}
	
	 /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return new TileEntityDeviceCraftingTable();
    }
	
    /*icons
	@SideOnly(Side.CLIENT)
	@Override
	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 *
	public IIcon getIcon(int par1, int par2) {
		return par1 == 1 ? this.workbenchIconTop : (par1 == 0 ? Blocks.planks.getBlockTextureFromSide(par1) : (par1 != 2 && par1 != 4 ? this.blockIcon : this.workbenchIconFront));
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 *
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("BetterTable:better_side");
		this.workbenchIconTop = par1IconRegister.registerIcon("BetterTable:better_top");
		this.workbenchIconFront = par1IconRegister.registerIcon("BetterTable:better_front");
	}
*/
	
    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int var6, float var7, float var8, float var9) {
		if (!player.isSneaking() && (TileEntityDeviceCraftingTable)world.getTileEntity(x, y, z) != null) {
			
			player.openGui(BetterTable.instance, 0, world, x, y, z);
			return true;
		} else {
			return false;
		}
	}
}