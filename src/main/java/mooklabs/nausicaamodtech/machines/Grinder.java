package mooklabs.nausicaamodtech.machines;

import java.util.List;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mooklabs.nausicaamod.Main;
import mooklabs.nausicaamodtech.TechMain;
import mooklabs.nausicaamodtech.machines.tileentity.TileEntityGrinder;
import mooklabs.nausicaamodtech.machines.tileentity.TileEntityMachineBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Grinder extends BlockAbstractMachine {

	
	private float curBlockDamageMP = 0;
	private int counter = 10;

	@SideOnly(Side.CLIENT)
    private IIcon topIcon;
    @SideOnly(Side.CLIENT)
    private IIcon sideIcon;
	
	public Grinder() {
		super(Material.rock);
		
		this.setBlockName("grinder");
		this.setBlockTextureName(Main.itemfold + ":grinder");
		this.setTickRandomly(true);
	}

	@Override
	/**
	 * Called upon block activation (right click on the block.)
	 */
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		/* if (player.getHeldItem() == null) {
		 * this.counter--;
		 * if (this.counter <= 0) {
		 * Block block = world.getBlock(x, y + 1, z);
		 * if (player.inventory.addItemStackToInventory(this.getRightItemToEmit(block))) {
		 * world.setBlockToAir(x, y + 1, z);
		 * this.counter = 10;
		 * }
		 * }
		 * return true;
		 * } */
		
		
		

		if (world.isRemote) {
			return true;
		} else {
			TileEntityGrinder tileentitygrinder = (TileEntityGrinder) world.getTileEntity(x, y, z);

			if (tileentitygrinder != null) {
				FMLNetworkHandler.openGui(player, TechMain.instance, TechMain.grinderGuiId, world, x, y, z);

			}

			return true;
		}
	}
	
	   /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return side == 2 ? this.topIcon : (side == 0 ? this.topIcon : (side != meta ? this.blockIcon : this.topIcon));
    }


	@Override
	/**
	 * Returns a new instance of a block's tile entity class. Called on placing the block.
	 */
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityGrinder();
	}

	@Override
	/**
	 * Called when the block is placed in the world.
	 */
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack) {
		int rotation = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		switch (rotation) {
		case 0:
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
			break;

		case 1:
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
			break;

		case 2:
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
			break;

		case 3:
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
			break;
		}

		if (itemStack.hasDisplayName()) ((TileEntityGrinder) world.getTileEntity(x, y, z)).func_145951_a(itemStack.getDisplayName());

	}

	@Override
	/**
	 * Gets an item for the block being called on. Args: world, x, y, z
	 */
	@SideOnly(Side.CLIENT)
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
		return Item.getItemFromBlock(this);
	}

	/**
	 * returns the itemstack appropriate for this ore
	 * 
	 * @param block
	 * @return
	 */
	public ItemStack getRightItemToEmit(Block block) {
		if (block == Blocks.iron_ore) return new ItemStack(Items.iron_ingot, 2);
		else if (block == Blocks.gold_ore) return new ItemStack(Items.gold_ingot, 2);
		else if (block == Blocks.coal_ore) return new ItemStack(Items.coal, 2);
		else if (block == Blocks.redstone_ore) return new ItemStack(Items.redstone, 12);
		else if (block == Blocks.quartz_ore) return new ItemStack(Items.quartz, 2);
		else if (block == Blocks.emerald_ore) return new ItemStack(Items.emerald, 2);
		else if (block == Blocks.lapis_ore) return new ItemStack(Blocks.lapis_block, 2);// couldent find the item......
		else if (block == Blocks.diamond_ore) return new ItemStack(Items.diamond, 2);
		else if (block == Blocks.cobblestone) return new ItemStack(Items.diamond, 2);

		else return null;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		currenttip.add("not working currently");
		currenttip.add("Current Power: " + ((TileEntityMachineBase)(accessor.getTileEntity())).currentPower);

		//duplicates? currenttip.addAll(super.getWailaBody(itemStack, currenttip, accessor, config));
		return currenttip;
	}

	

	

}
