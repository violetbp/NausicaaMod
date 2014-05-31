package mooklabs.laputamod.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mooklabs.laputamod.LapMain;
import mooklabs.mookcore.MLib;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * DONT PLAY WITH THIS FOR AWHILE
 * 
 * @author moolabs
 */
public class VoluciteNecklace extends Item {

	public VoluciteNecklace() {
		super();
		setUnlocalizedName("voluciteNecklace");
		setTextureName(LapMain.itemfold + ":voluciteNecklace");
		this.setMaxStackSize(1);
	}

	/**
	 * i think this is like onCrafted, not creative spawned
	 */
	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
		register(itemStack, player, true);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
		if (itemStack.stackTagCompound != null) {

			String owner = itemStack.stackTagCompound.getString("owner");

			if (itemStack.stackTagCompound.getBoolean("creativeSpawned")) list.add("Sole Owner: " + owner);


			if (owner.equals(player.getCommandSenderName())) {// if belongs to player
				if (true) list.add(EnumChatFormatting.GREEN + "Grinder: " + "Researched");
				else list.add(EnumChatFormatting.RED + "Grinder: " + "Not Researched");

				if (false) list.add(EnumChatFormatting.GREEN + "Furnace: " + "Researched");
				else list.add(EnumChatFormatting.RED + "Furnace: " + "Not Researched");

			} else {
				list.add(EnumChatFormatting.RED + "This Necklace belongs to someone with power,"+
								" return it to any admin since you cant use it");
			}
		} else {// if has tags
			list.add(EnumChatFormatting.BLUE + "NO INFO!");
			list.add(EnumChatFormatting.RED + "Right click to register");
		}

	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 * 
	 * @Override public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) { if (!world.isRemote) { if (itemStack.stackTagCompound == null)
	 *           register(itemStack, player, false); if (player.capabilities.isCreativeMode) { itemStack.stackTagCompound.setBoolean("creativeSpawned", true); } } return
	 *           itemStack; }
	 */

	public void register(ItemStack itemStack, EntityPlayer player, boolean wasCrafted) {
		if (itemStack.stackTagCompound == null) {
			itemStack.stackTagCompound = new NBTTagCompound();// create tag
			itemStack.stackTagCompound.setBoolean("creativeSpawned", wasCrafted);

		} else {// has allready been registed

			if (itemStack.stackTagCompound.getBoolean("creativeSpawned")) {
				MLib.printToPlayer("Sorry this has restricted access by " + itemStack.stackTagCompound.getString("owner"));
				return;//if it cannot be claimed by another player(ie for admins)
			} else {
				//switch owner?

				itemStack.stackTagCompound.setString("owner", player.getCommandSenderName());// register to name
				
				
			}
			
		}
	}

	// when player types in chat stuff happens!

	@Override
	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if (par3EntityPlayer.isSprinting()) {
			Vec3 desiredDirection = par3EntityPlayer.getLookVec();

			par3EntityPlayer.motionX = 1.3 * desiredDirection.xCoord;
			par3EntityPlayer.motionY = 1.3 * desiredDirection.yCoord;
			par3EntityPlayer.motionZ = 1.3 * desiredDirection.zCoord;
		} else if (par3EntityPlayer.isSneaking()) {

		} else {
			par3EntityPlayer.motionY = 0;
		}

		return par1ItemStack;
	}

}
