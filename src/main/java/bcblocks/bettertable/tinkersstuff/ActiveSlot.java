package bcblocks.bettertable.tinkersstuff;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;

public class ActiveSlot extends Slot
{
    protected boolean active;
    public int activeSlotNumber;

    public ActiveSlot(IInventory iinventory, int par2, int par3, int par4, boolean isActive)
    {
        super(iinventory, par2, par3, par4);
        active = isActive;
    }

    public void setActive (boolean flag)
    {
        active = flag;
    }

    public boolean getActive ()
    {
        return active;
    }
}