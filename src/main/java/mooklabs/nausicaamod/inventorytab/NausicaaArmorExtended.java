package mooklabs.nausicaamod.inventorytab;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.UUID;

import tconstruct.TConstruct;

import mooklabs.mookcore.MLib;
import mooklabs.nausicaamod.Main;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;

public class NausicaaArmorExtended implements IInventory {

	public ItemStack[] inventory = new ItemStack[7];
	public WeakReference<EntityPlayer> parent;
	public UUID globalID = UUID.fromString("89d3c870-d93c-11e3-9c1a-0800200c9a66");// dont know, replaced with one generted online

	public void init(EntityPlayer player) {
		this.parent = new WeakReference<EntityPlayer>(player);
		//System.out.println();
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	public boolean isStackInSlot(int slot) {
		return inventory[slot] != null;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int quantity) {
		//System.out.println("decr " + slot);

		if (inventory[slot] != null) {
			//System.out.println("Took something from slot " + slot);
			 //Main.logger.info("Took something from slot " + slot);
			if (inventory[slot].stackSize <= quantity) {
				ItemStack stack = inventory[slot];
				inventory[slot] = null;
				return stack;
			}
			ItemStack split = inventory[slot].splitStack(quantity);
			if (inventory[slot].stackSize == 0) {
				inventory[slot] = null;
			}
			EntityPlayer player = parent.get();
			NPlayerStats stats = NPlayerStats.get(player);
			recalculateHealth(player, stats);
			return split;
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack) {
		inventory[slot] = itemstack;
		// TConstruct.logger.info("Changed slot " + slot + " on side " +
		// FMLCommonHandler.instance().getEffectiveSide());
		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}

		EntityPlayer player = parent.get();
		NPlayerStats stats = NPlayerStats.get(player);
		recalculateHealth(player, stats);
	}

	@Override
	public String getInventoryName() {
		return "Inventory";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 50;
	}

	@Override
	public void markDirty() {
		EntityPlayer player = parent.get();
		NPlayerStats stats = NPlayerStats.get(player);
		// recalculateSkills(player, stats);
		recalculateHealth(player, stats);

		/* if (inventory[2] == null && stats.knapsack != null) {
		 * stats.knapsack.unequipItems(); } */
	}

	/* public void recalculateSkills(EntityPlayer player, NPlayerStats stats) {
	 * if (inventory[1] != null && inventory[1].getItem() == TRepo.glove) { if
	 * (stats.skillList.size() < 1) { try {
	 * stats.skillList.add(SkillRegistry.skills.get("Wall Building").copy()); }
	 * catch (Exception e) { e.printStackTrace(); } } } else { if
	 * (stats.skillList.size() > 0) { stats.skillList.remove(0); } } } */

	public void recalculateHealth(EntityPlayer player, NPlayerStats stats) {
		// REF for sideonly Side side = FMLCommonHandler.instance().getEffectiveSide();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	public void openChest() {
	}

	public void closeChest() {
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
		return false;
	}

	/* Save/Load */
	public void saveToNBT(NBTTagCompound tagCompound) {
		NBTTagList tagList = new NBTTagList();
		NBTTagCompound invSlot;

		for (int i = 0; i < this.inventory.length; ++i) {
			if (this.inventory[i] != null) {
				invSlot = new NBTTagCompound();
				invSlot.setByte("NSlot", (byte) i);
				this.inventory[i].writeToNBT(invSlot);
				tagList.appendTag(invSlot);
			}
		}

		tagCompound.setTag("NInventory", tagList);
	}

	public void readFromNBT(NBTTagCompound tagCompound) {
		if (tagCompound != null) {
			System.out.println("[NMod]nbtReadSuccess");

			NBTTagList tagList = tagCompound.getTagList("NInventory", 10);
			for (int i = 0; i < tagList.tagCount(); ++i) {
				NBTTagCompound nbttagcompound = (NBTTagCompound) tagList.getCompoundTagAt(i);
				int j = nbttagcompound.getByte("NSlot") & 255;
				ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbttagcompound);

				if (itemstack != null) {
					this.inventory[j] = itemstack;
				}
			}
		}
	}

	public void dropItems(ArrayList<EntityItem> drops) {
		EntityPlayer player = parent.get();
		for (int i = 0; i < 4; ++i) {
			if (this.inventory[i] != null) {
				EntityItem entityItem = player.func_146097_a(this.inventory[i], true, false);
				drops.add(entityItem);
				this.inventory[i] = null;
			}
		}
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	public void writeInventoryToStream(ByteBuf os) throws IOException {
		System.err.println("\n\n\n\n\nWRITE\n\n\n\n");
		for (int i = 0; i < 7; i++)
			ByteBufUtils.writeItemStack(os, inventory[i]);
	}

	public void readInventoryFromStream(ByteBuf is) throws IOException {
		System.err.println("\n\n\n\n\nREAD\n\n\n\n");

		for (int i = 0; i < 7; i++)
			inventory[i] = ByteBufUtils.readItemStack(is);
	}
}