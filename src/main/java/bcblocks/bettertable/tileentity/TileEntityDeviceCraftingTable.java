package bcblocks.bettertable.tileentity;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;


public class TileEntityDeviceCraftingTable extends TileEntityChest {

    public ItemStack[] tableContents = new ItemStack[125];

	
	public TileEntityDeviceCraftingTable() {
		
	}

	public TileEntityDeviceCraftingTable(int par1) {
		super(par1);
		
	}

	

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 125;
    }

    /**
     * Returns the stack in slot <br>
     * from 0 to invsize - 1
     */
    public ItemStack getStackInSlot(int slot)
    {
        return this.tableContents[slot];
    }

}
