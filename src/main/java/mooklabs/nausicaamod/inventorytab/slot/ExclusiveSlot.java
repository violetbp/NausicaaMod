package mooklabs.nausicaamod.inventorytab.slot;

import mooklabs.nausicaamod.glider.GliderFull;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ExclusiveSlot extends Slot
{
	private Item toInclude;
	
    public ExclusiveSlot(IInventory par2IInventory, int par3, int par4, int par5, Item exclusiveItem)
    {
        super(par2IInventory, par3, par4, par5);
        this.toInclude = exclusiveItem;
    }
    public ExclusiveSlot(IInventory par2IInventory, int par3, int par4, int par5)
    {
        super(par2IInventory, par3, par4, par5);
        this.toInclude = null;
    }

    /**
* Returns the maximum stack size for a given slot (usually the same as
* getInventoryStackLimit(), but 1 in the case of armor slots)
*/
    @Override
    public int getSlotStackLimit ()
    {
        return this.toInclude == null ? 64 : this.toInclude.getItemStackLimit();
    }

    /**
* Check if the stack is a valid item for this slot. Always true beside for
* the armor slots.
*/
    @Override
    public boolean isItemValid (ItemStack par1ItemStack)
    {
        Item item = (par1ItemStack == null ? null : par1ItemStack.getItem());
        if(this.toInclude == null)
        	return true;
        return item != null && (item == this.toInclude);
    }
}