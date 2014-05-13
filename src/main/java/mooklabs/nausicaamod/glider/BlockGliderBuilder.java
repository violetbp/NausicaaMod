package mooklabs.nausicaamod.glider;

import java.util.Random;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGliderBuilder extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon gliderBuilderIconTop;
	@SideOnly(Side.CLIENT)
	private IIcon gliderBuilderIconFront;

	public BlockGliderBuilder() {
		super(Material.rock);
		this.setBlockName("gliderBuilder");
	}
	
	@Override
	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return Item.getItemFromBlock(Main.gliderBuilder);
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	public IIcon getIcon(int par1, int par2) {
		return par1 == 1 ? this.gliderBuilderIconTop : (par1 == 0 ? this.gliderBuilderIconTop : (par1 != par2 ? this.blockIcon : this.gliderBuilderIconFront));
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 */
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(Main.itemfold + ":gliderBuilder_side");
		this.gliderBuilderIconFront = par1IconRegister.registerIcon(Main.itemfold + ":gliderBuilder_front");
		this.gliderBuilderIconTop = par1IconRegister.registerIcon(Main.itemfold + ":gliderBuilder_top");
	}
	@Override
	/**
	 * Called upon block activation (right click on the block.)
	 */
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float what, float these, float are) {
		ItemStack heldItem = player.getHeldItem();
		if (heldItem != null) {
			if (heldItem.getItem() == Items.iron_ingot && heldItem.stackSize >= 6) {
				if (player.inventory.addItemStackToInventory(new ItemStack(Main.metalPlate))) {
					int currentHeldItemInventoryNum = player.inventory.currentItem;
					player.addChatMessage(new ChatComponentText("Creating metal plate"));
					player.inventory.decrStackSize(currentHeldItemInventoryNum, 6);
					return true;
				}
			}

		}
		// else if no item held make a glider
		else {

			boolean cancelz = false;
			boolean cancelx = false;

			boolean useXaxis = false;

			if (world.getBlock(x, y + 1, z) == Main.gliderBody) {
				player.addChatMessage(new ChatComponentText("body detected"));
				if (world.getBlock(x, y + 2, z) != Main.gliderHandle) {
					player.addChatMessage(new ChatComponentText( "handle not found"));
					return true;
				} else // else check to see if wings right
				for (int x1 = -3; x1 <= 3; x1++)
					if (!(world.getBlock(x + x1, y + 1, z) == Main.gliderWing || x1 == 0)) {
						player.addChatMessage(new ChatComponentText( "wing at pos" + x1 + " not found"));
						cancelx = true;
						break;
					} else useXaxis = false;
				if (cancelx) for (int z1 = -3; z1 <= 3; z1++)
					if (!(world.getBlock(x, y + 1, z + z1) == Main.gliderWing || z1 == 0)) {
						player.addChatMessage(new ChatComponentText( "wing at pos" + z1 + " not found"));
						cancelz = true;
						break;
					} else useXaxis = true;

				if (cancelx && cancelz) return true;

				if (player.inventory.addItemStackToInventory(new ItemStack(Main.glider, 1))) {
					world.setBlockToAir(x, y + 1, z);
					world.setBlockToAir(x, y + 2, z);
					if (useXaxis) for (int z1 = -3; z1 <= 3; z1++)
						if ((world.getBlock(x, y + 1, z + z1) == Main.gliderWing)) world.setBlockToAir(x, y + 1, z + z1);
					if (!useXaxis) for (int x1 = -3; x1 <= 3; x1++)
						if ((world.getBlock(x + x1, y + 1, z) == Main.gliderWing)) world.setBlockToAir(x + x1, y + 1, z);

				}
			}
		}
		return true;
	}

	/**
	 * Called when the block is placed in the world.
	 *
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		int l = MathHelper.floor_double((double) (par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
		if (l == 1) par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
		if (l == 2) par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
		if (l == 3) par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
		if (par6ItemStack.hasDisplayName()) ((TileEntityGliderBuilder) par1World.getTileEntity(par2, par3, par4)).setGuiDisplayName(par6ItemStack.getDisplayName());

	}*/

	
}