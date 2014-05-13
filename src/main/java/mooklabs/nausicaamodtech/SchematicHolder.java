package mooklabs.nausicaamodtech;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SchematicHolder extends Item {

	public SchematicHolder() {
		this.setMaxStackSize(1);
	}

	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
		register(itemStack, player, true);
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
		if (itemStack.stackTagCompound != null) {

			String owner = itemStack.stackTagCompound.getString("owner");
			boolean grinder = itemStack.stackTagCompound.getBoolean("grinder");
			boolean furnace = itemStack.stackTagCompound.getBoolean("furnace");
			if (itemStack.stackTagCompound.getBoolean("creativeSpawned"))
				list.add("Owner: " + owner);
			
			else list.add(EnumChatFormatting.BLUE + "Research Status:");

			if (owner.equals(player.getCommandSenderName())) {// if belongs to player
				if (grinder) list.add(EnumChatFormatting.GREEN + "Grinder: " + "Researched");
				else list.add(EnumChatFormatting.RED + "Grinder: " + "Not Researched");

				if (furnace) list.add(EnumChatFormatting.GREEN + "Furnace: " + "Researched");
				else list.add(EnumChatFormatting.RED + "Furnace: " + "Not Researched");

			} else {
				list.add(EnumChatFormatting.RED + "code: " + EnumChatFormatting.OBFUSCATED + "ABCDEFGHIJKLMNOP");
			}
		} else {// if has tags
			list.add(EnumChatFormatting.BLUE + "NO INFO!!");
			list.add(EnumChatFormatting.RED + "Right click to register");
		}

	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		 if (!world.isRemote)
	        {
		if (itemStack.stackTagCompound == null) register(itemStack, player, false);
		if (player.capabilities.isCreativeMode) {
			itemStack.stackTagCompound.setBoolean("creativeSpawned", true);

			itemStack.stackTagCompound.setBoolean("grinder", true);
			itemStack.stackTagCompound.setBoolean("furnace", true);

		}
	        }
		return itemStack;
	}

	public void register(ItemStack itemStack, EntityPlayer player, boolean wasCrafted) {
		if (wasCrafted) System.out.println("[NausicaaMod]Schematic Holder crafted by " + player.getCommandSenderName());
		else {
			System.out.println("[NausicaaMod]Schematic Holder registerd by " + player.getCommandSenderName());
			System.err.println("[NausicaaMod]" + player.getCommandSenderName() + " May be in creative!!");
		}
		itemStack.stackTagCompound = new NBTTagCompound();// create tag
		itemStack.stackTagCompound.setBoolean("creativeSpawned", false);

		// itemStack.stackTagCompound.
		itemStack.stackTagCompound.setString("owner", player.getCommandSenderName());// register to name
		itemStack.stackTagCompound.setBoolean("grinder", false);// init all as false
		itemStack.stackTagCompound.setBoolean("furnace", false);

	}
	@SideOnly(Side.CLIENT)

	public static boolean getCanCraft(ItemStack itemStack, Item block) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		NBTTagCompound tags = itemStack.stackTagCompound;
		if (block.equals(Item.getItemFromBlock(TechMain.grinder)) && tags.getBoolean("grinder")) {
			player.addChatMessage(new ChatComponentText("Grinder Crafted"));
			return true;
		} else if (block.equals(Item.getItemFromBlock(TechMain.frictionFurnace)) && tags.getBoolean("furnace")) {
			player.addChatMessage(new ChatComponentText("Furnace Crafted"));
			return true;
		}
		// else
		player.addChatMessage(new ChatComponentText("Cannot craft that machine"));

		return false;

	}

}
