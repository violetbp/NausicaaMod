package mooklabs.nausicaamodtech.machines.container;

import mooklabs.nausicaamodtech.machines.tileentity.TileEntityGrinder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

	public class ContainerGrinder extends Container
	{
		private TileEntityGrinder grinder;
		private int lastCookTime;
		private int lastBurnTime;

		public ContainerGrinder(InventoryPlayer inventoryPlayer, TileEntityGrinder tileEntityGrinder)
		{
			this.grinder = tileEntityGrinder;
			this.addSlotToContainer(new Slot(tileEntityGrinder, 0, 56, 35));
			this.addSlotToContainer(new Slot(tileEntityGrinder, 1, 8, 56));
			this.addSlotToContainer(new SlotFurnace(inventoryPlayer.player, tileEntityGrinder, 2, 116, 35));
			int i;

			for (i = 0; i < 3; ++i)
			{
				for (int j = 0; j < 9; ++j)
				{
					this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
				}
			}

			for (i = 0; i < 9; ++i)
			{
				this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
			}
		}

		@Override
		public void addCraftingToCrafters(ICrafting par1ICrafting)
		{
			super.addCraftingToCrafters(par1ICrafting);
			par1ICrafting.sendProgressBarUpdate(this, 0, this.grinder.cookTime);
			par1ICrafting.sendProgressBarUpdate(this, 1, this.grinder.power);
		}

		/**
		 * Looks for changes made in the container, sends them to every listener.
		 */
		@Override
		public void detectAndSendChanges()
		{
			super.detectAndSendChanges();

			for (int i = 0; i < this.crafters.size(); ++i)
			{
				ICrafting icrafting = (ICrafting)this.crafters.get(i);

				if (this.lastCookTime != this.grinder.cookTime)
				{
					icrafting.sendProgressBarUpdate(this, 0, this.grinder.cookTime);
				}

				if (this.lastBurnTime != this.grinder.power)
				{
					icrafting.sendProgressBarUpdate(this, 1, this.grinder.power);
				}
			}

			this.lastCookTime = this.grinder.cookTime;
			this.lastBurnTime = this.grinder.power;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public void updateProgressBar(int par1, int par2)
		{
			if (par1 == 0)
			{
				this.grinder.cookTime = par2;
			}

			if (par1 == 1)
			{
				this.grinder.power = par2;
			}
		}

		@Override
		public boolean canInteractWith(EntityPlayer par1EntityPlayer)
		{
			return this.grinder.isUseableByPlayer(par1EntityPlayer);
		}

		/**
		 * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
		 */
		@Override
		public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
		{
			ItemStack itemstack = null;
			Slot slot = (Slot)this.inventorySlots.get(par2);

			if (slot != null && slot.getHasStack())
			{
				ItemStack itemstack1 = slot.getStack();
				itemstack = itemstack1.copy();

				if (par2 == 2)
				{
					if (!this.mergeItemStack(itemstack1, 3, 39, true))
					{
						return null;
					}

					slot.onSlotChange(itemstack1, itemstack);
				}
				else if (par2 != 1 && par2 != 0)
				{
					/*if (reRecipes.ores().smelting().getSmeltingResult(itemstack1) != null)
					{//CRIT figure out what this is
						if (!this.mergeItemStack(itemstack1, 0, 1, false))
						{
							return null;
						}
					}
					else */
					if (TileEntityGrinder.isItemFuel(itemstack1))
					{
						if (!this.mergeItemStack(itemstack1, 1, 2, false))
						{
							return null;
						}
					}
					else if (par2 >= 3 && par2 < 30)
					{
						if (!this.mergeItemStack(itemstack1, 30, 39, false))
						{
							return null;
						}
					}
					else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
					{
						return null;
					}
				}
				else if (!this.mergeItemStack(itemstack1, 3, 39, false))
				{
					return null;
				}

				if (itemstack1.stackSize == 0)
				{
					slot.putStack((ItemStack)null);
				}
				else
				{
					slot.onSlotChanged();
				}

				if (itemstack1.stackSize == itemstack.stackSize)
				{
					return null;
				}

				slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
			}

			return itemstack;
		}
	}