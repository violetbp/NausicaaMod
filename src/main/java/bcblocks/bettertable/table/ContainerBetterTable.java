package bcblocks.bettertable.table;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import bcblocks.bettertable.BetterTable;
import bcblocks.bettertable.tileentity.TileEntityDeviceCraftingTable;
import bcblocks.bettertable.tinkersstuff.ActiveContainer;
import bcblocks.bettertable.tinkersstuff.ActiveSlot;
import bcblocks.bettertable.viffy.SchematicCrafting;

public class ContainerBetterTable extends ActiveContainer {

	public IInventory craftMatrix;
	public IInventory craftResult;
	public IInventory schematicSlot;

	private World worldObj;
	private int posX;
	private int posY;
	private int posZ;

	public ContainerBetterTable(InventoryPlayer inventoryplayer, TileEntityDeviceCraftingTable tileEntity, World world, int i, int j, int k) {
		craftMatrix = new SchematicCrafting(this, tileEntity, 5, 5);
		

		craftResult = new InventoryCraftResult();
		schematicSlot = new InventoryBasic("schematicSlot", false, 1);
		worldObj = world;
		posX = i;
		posY = j;
		posZ = k;

		// crafting grid
		for (int page = 0; page < 5; page++)
			for (int l = 0; l < 5; l++) {
				for (int k1 = 0; k1 < 5; k1++) {
					this.addDualSlotToContainer(new ActiveSlot(craftMatrix, k1 + l * 5 + page * 25, 8 + k1 * 18, 18 + l * 18, page == 0));
					// active if first page
				}

			}

		// slot for the skematics
		this.addSlotToContainer(new Slot(schematicSlot, 0, 131, 56));
		// output slot
		this.addSlotToContainer(new SlotCrafting(inventoryplayer.player, craftMatrix, craftResult, 0, 131, 94));

		// player inventory
		for (int i1 = 0; i1 < 3; i1++) {
			for (int l1 = 0; l1 < 9; l1++) {
				this.addSlotToContainer(new Slot(inventoryplayer, l1 + i1 * 9 + 9, 8 + l1 * 18, 94 + 21 + i1 * 18));
			}

		}

		for (int j1 = 0; j1 < 9; j1++) {
			this.addSlotToContainer(new Slot(inventoryplayer, j1, 8 + j1 * 18, 148 + 25));
		}

		// System.out.println("active: " + this.activeInventorySlots.size());
		// System.out.println("slots: " + this.inventorySlots.size());

		setActivePage(1);

		onCraftMatrixChanged(craftMatrix);
		// System.out.println("active: " + this.activeInventorySlots.size());
		// System.out.println("slots: " + this.inventorySlots.size());
	}

	@Override
	public void onCraftMatrixChanged(IInventory iinventory) {

		craftResult.setInventorySlotContents(0, BetterCraftingManager.getInstance().findMatchingRecipe((InventoryCrafting) craftMatrix, schematicSlot, worldObj));

	}

	public void setActivePage(int pagenum) {
		for (int page = 0; page < 5; page++)
			// cycles through pages
			for (int index = 0; index < 25; index++)
				((ActiveSlot) (this.activeInventorySlots.get(index + (page * 25)))).setActive(pagenum == page + 1);
	}

	@Override
	public void onContainerClosed(EntityPlayer entityPlayer) {
		//DO not call super for this one, it will drop all items (duping)
		
        if (!this.worldObj.isRemote)
        {
            for (int i = 0; i < 125; ++i)
            {
                ItemStack itemstack = this.craftMatrix.getStackInSlotOnClosing(i);

            }
        }

	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		if (worldObj.getBlock(posX, posY, posZ) != BetterTable.betterTable) {
			return false;
		} else {
			return entityplayer.getDistanceSq(posX + 0.5D, posY + 0.5D, posZ + 0.5D) <= 128D;// make sure it is in the range
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(par2);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (par2 == 0) {
				if (!mergeItemStack(itemstack1, 10, 46, true)) {
					return null;
				}
			} else if (par2 >= 10 && par2 < 37) {
				if (!mergeItemStack(itemstack1, 37, 46, false)) {
					return null;
				}
			} else if (par2 >= 37 && par2 < 46) {
				if (!mergeItemStack(itemstack1, 10, 37, false)) {
					return null;
				}
			} else if (!mergeItemStack(itemstack1, 10, 46, false)) {
				return null;
			}
			if (itemstack1.stackSize == 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}
			if (itemstack1.stackSize != itemstack.stackSize) {
				slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
			} else {
				return null;
			}
		}
		return itemstack;
	}
}