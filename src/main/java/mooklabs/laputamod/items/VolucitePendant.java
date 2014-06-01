package mooklabs.laputamod.items;

import mooklabs.laputamod.LapMain;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.entity.player.BonemealEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;

public class VolucitePendant extends Item {

	public VolucitePendant() {
		super();
		setMaxStackSize(32);
		setUnlocalizedName("volucitePendant");
		setTextureName(LapMain.itemfold + ":volucitePendant");

	}

	@Override
	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		return par1ItemStack;
	}

	/**
	 * The bonemeal method!!!
	 * 
	 * @param itemstack
	 * @param world
	 * @param p_150919_2_
	 * @param p_150919_3_
	 * @param p_150919_4_
	 * @param player
	 * @return
	 */
	public static boolean applyBonemeal(ItemStack itemstack, World world, int p_150919_2_, int p_150919_3_, int p_150919_4_, EntityPlayer player) {
		Block block = world.getBlock(p_150919_2_, p_150919_3_, p_150919_4_);

		BonemealEvent event = new BonemealEvent(player, world, block, p_150919_2_, p_150919_3_, p_150919_4_);
		if (MinecraftForge.EVENT_BUS.post(event)) {
			return false;
		}

		if (event.getResult() == Result.ALLOW) {
			if (!world.isRemote) {
				itemstack.stackSize--;
			}
			return true;
		}

		if (block instanceof IGrowable) {
			IGrowable igrowable = (IGrowable) block;

			if (igrowable.func_149851_a(world, p_150919_2_, p_150919_3_, p_150919_4_, world.isRemote)) {
				if (!world.isRemote) {
					if (igrowable.func_149852_a(world, world.rand, p_150919_2_, p_150919_3_, p_150919_4_)) {
						igrowable.func_149853_b(world, world.rand, p_150919_2_, p_150919_3_, p_150919_4_);
					}

					--itemstack.stackSize;
				}

				return true;
			}
		}

		return false;
	}

	/**
	 * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return True if something happen and false if it don't. This is
	 * for ITEMS, not BLOCKS
	 */
	// TODO fix this
	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {

		if (entityPlayer.canPlayerEdit(par4, par5, par6, par7, itemStack) && applyBonemeal(itemStack, world, par4, par5, par6, entityPlayer)) {

			if (!world.isRemote) world.playAuxSFX(2005, par4, par5, par6, 0);

			return true;
		}
		return false;
	}

	/**
	 * I might use this later to make is affect wider area<br>
	 * Im pritty sure this is for a block/item to use if it is not being held by a player(it has fakeplayer)
	 * 
	 * @return
	 */
	public static boolean func_150919_a(ItemStack itemstack, World p_150919_1_, int p_150919_2_, int p_150919_3_, int p_150919_4_) {
		if (p_150919_1_ instanceof WorldServer) return applyBonemeal(itemstack, p_150919_1_, p_150919_2_, p_150919_3_, p_150919_4_, FakePlayerFactory.getMinecraft((WorldServer) p_150919_1_));
		return false;
	}
}
