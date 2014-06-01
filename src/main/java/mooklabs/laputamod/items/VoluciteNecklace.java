package mooklabs.laputamod.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mooklabs.laputamod.LapMain;
import mooklabs.mookcore.MLib;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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
			else list.add("Owner: " + owner);

			if (owner.equals(player.getCommandSenderName())) {// if belongs to player
				list.add(EnumChatFormatting.GREEN + "Grinder: " + "Researched");

			} else {// if doesent belong to player
				list.add(EnumChatFormatting.RED + "This Necklace belongs to someone with power," + " return it to any admin since you can't use it");
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
			itemStack.stackTagCompound.setBoolean("launch", true);
			itemStack.stackTagCompound.setBoolean("launchMob", true);
			itemStack.stackTagCompound.setBoolean("hover", true);
			itemStack.stackTagCompound.setBoolean("dig", true);

			itemStack.stackTagCompound.setString("owner", player.getCommandSenderName());

		} else {// has allready been registed

			if (itemStack.stackTagCompound.getBoolean("creativeSpawned")) {
				MLib.printToPlayer("Sorry this has restricted access by " + itemStack.stackTagCompound.getString("owner"));
				return;// if it cannot be claimed by another player(ie for admins)
			} else {
				// switch owner?

				itemStack.stackTagCompound.setString("owner", player.getCommandSenderName());// register to name

			}

		}
	}

	// when player types in chat stuff happens!

	@Override
	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		if (itemStack.stackTagCompound == null) register(itemStack, player, false);
		// NOT YET BECUAE ITS ITEM BASEDelse if (itemStack.stackTagCompound.getString("owner").equals(player.getCommandSenderName())) register(itemStack, player, false);
		NBTTagCompound tagC = itemStack.stackTagCompound;
		if (player.isSneaking()) {
			String str = "";
			switch (tagC.getString("mode")) {
			case "launch":
				str = "hover";
				if (tagC.getBoolean("hover")) break;// bit iffy but will work later

			case "hover":
				str = "dig";
				if (tagC.getBoolean("dig")) break;

			case "dig":
				str = "launchMob";
				if (tagC.getBoolean("launchMob")) break;

			case "launchMob":
				str = "launch";
				if (tagC.getBoolean("launch")) break;
			default:
				str = "launch";
			}
			MLib.printToPlayer("Effect set to" + str);

			itemStack.stackTagCompound.setString("mode", str);

		} else {
			switch (tagC.getString("mode")) {
			case "launch":
				launchPlayer(player, 1.3);
				break;
			case "hover":
				player.motionY = 0;
				break;
			case "dig":
				dig(world, player);
				break;
			case "launchMob":// will change launch velocity
				MLib.printToPlayer("You just need to left click on a mob to use this.");
				break;
			default:
				MLib.printToPlayer("Shift-right click to start using");
			}
		}

		return itemStack;
	}

	public void launchPlayer(EntityPlayer entityPlayer, double amt) {
		Vec3 desiredDirection = entityPlayer.getLookVec();

		entityPlayer.motionX = amt * desiredDirection.xCoord;
		entityPlayer.motionY = amt * desiredDirection.yCoord;
		entityPlayer.motionZ = amt * desiredDirection.zCoord;
	}

	public void dig(World w, EntityPlayer player) {
		int yaw = (int) player.rotationYaw;

		if (yaw < 0) // due to the yaw running a -360 to positive 360
		yaw += 360; // not sure why it's that way

		yaw += 22; // centers coordinates you may want to drop this line
		yaw %= 360; // and this one if you want a strict interpretation of the zones

		byte facing = (byte) (yaw / 90); // 360degrees divided by 45 == 8 zones
		int st = 0, end = 0;
		boolean nS = false;
		switch (facing) {
		case 0:
			st = 0;
			end = 10;
			nS = false;
			break;// south
		case 1:
			st = -10;
			end = 0;
			nS = true;
			break;// west
		case 2:
			st = -10;
			end = 0;
			nS = false;
			break;// north
		case 3:
			st = 0;
			end = 10;
			nS = true;
			break;// east
		}

		int x = (int) Math.floor(player.posX);
		int z = (int) Math.floor(player.posZ);
		int y = (int) Math.floor(player.posY);
		if (nS) 
			for (int j = 0; j < 3; j++)
			for (int i = st; i < end; i++)
				breakBlock(w, x + i, y + j, z);

		else 
			for (int j = 0; j < 3; j++)
			for (int i = st; i < end; i++)
				breakBlock(w, x, y + j, z + i);

	}

	private void breakBlock(World world, int x, int y, int z) {
		if (world.getBlock(x, y, z) != Blocks.bedrock) {// duh
			world.getBlock(x, y, z).dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if (stack.stackTagCompound.getBoolean("launchMob")) {
			entity.motionY = 4;
			return true;
		}
		return false;
	}
}
