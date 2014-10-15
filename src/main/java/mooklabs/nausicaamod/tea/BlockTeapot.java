package mooklabs.nausicaamod.tea;

import java.util.ArrayList;
import java.util.List;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Somehow this is going to hold tea? What does this do
 */
public class BlockTeapot extends Block {

	public BlockTeapot() {
		super(Material.glass);
	}

	public int damageDropped(int par1) {
		return par1;
	}

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[Teas.values().length - 1];
		for (int i = 0; i < icons.length; i++) {
			icons[i] = par1IconRegister.registerIcon(Main.modid + ":" + (this.getUnlocalizedName().substring(5)) + Teas.teaMap.get(i).name);
		}
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		return icons[par2];
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs par2CreativeTabs, List par3List) {
		for (int var4 = 0; var4 < Teas.values().length - 1; ++var4) {
			par3List.add(new ItemStack(item, 1, var4));
		}
	}

	/**
	 * Called upon block activation (right click on the block.) for brewing and picking up
	 */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		ItemStack heldItem = player.getHeldItem();
		if (heldItem != null) { // if the item is null (not holing anything), and try to check it will crash
			if (heldItem.getItem() == Main.tea && heldItem.stackSize >= 1) {// some tea may eventually need to be more than one leaf
				if (true) {// if(teapot can hold tea){
					int currentHeldItemInventoryNum = player.inventory.currentItem;
					player.inventory.decrStackSize(currentHeldItemInventoryNum, 1);
					player.addChatMessage(new ChatComponentText("Envision tea music playing in the background..."));
				}

			} else {
				//TODO call to get itemblck from here
				if (player.inventory.addItemStackToInventory(new ItemStack(Items.apple))) {
					//TODO remove existing block
					int currentHeldItemInventoryNum = player.inventory.currentItem;
					player.addChatMessage(new ChatComponentText("pretend its a special apple"));
					player.inventory.decrStackSize(currentHeldItemInventoryNum, 6);
					return true;
				}
			}
		}

		return true;
	}

	/**
	 * This returns a complete list of items dropped from this block.
	 * 
	 * @param world The current world
	 * @param x X Position
	 * @param y Y Position
	 * @param z Z Position
	 * @param metadata Current metadata
	 * @param fortune Breakers fortune level
	 * @return A ArrayList containing all items this block drops
	 */
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();

		TileEntity t = world.getTileEntity(x, y, z);

		if (t instanceof TileEntityTeapot) {
			TileEntityTeapot tile = (TileEntityTeapot) t;
			String name = tile.teatype.name;

			ItemStack stack = new ItemStack(world.getBlock(x, y, z), 1, metadata);
			if (!stack.hasTagCompound()) {
				stack.setTagCompound(new NBTTagCompound());
			}
			stack.getTagCompound().setString("teaType", name);
			items.add(stack);
		}

		return items;

	}

}
