package mooklabs.nausicaamod.inventorytab;

import mooklabs.nausicaamod.Main;
import mooklabs.nausicaamod.inventorytab.slot.ExclusiveSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import tconstruct.inventory.ActiveContainer;
import tconstruct.inventory.SlotArmorCopy;
import tconstruct.inventory.SlotKnapsack;
import tconstruct.inventory.SlotUnused;

public class NausicaaTabExtendedContainer extends ActiveContainer
{
    public InventoryPlayer invPlayer;
    public NausicaaArmorExtended invSlots;

    public NausicaaTabExtendedContainer(InventoryPlayer inventoryplayer, NausicaaArmorExtended armor)
    {
        invPlayer = inventoryplayer;
        this.invSlots = armor;

        this.addSlotToContainer(new ExclusiveSlot(armor, 0, 80, 17, Main.gasMask));
        this.addSlotToContainer(new SlotUnused(armor, 1, 80, 35));
        this.addSlotToContainer(new SlotKnapsack(armor, 2, 116, 17));
        this.addSlotToContainer(new SlotUnused(armor, 3, 116, 35));
        this.addSlotToContainer(new ExclusiveSlot(armor, 4, 152, 53, Main.glider));
        this.addSlotToContainer(new ExclusiveSlot(armor, 5, 152, 35, Main.blowGunDart));
        this.addSlotToContainer(new ExclusiveSlot(armor, 6, 152, 17, Main.godWarriorControler)); 
        /* Player inventory */
        //armor
        for (int playerArmor = 0; playerArmor < 4; ++playerArmor)
        {
            this.addSlotToContainer(new SlotArmorCopy(this, inventoryplayer, inventoryplayer.getSizeInventory() - 1 - playerArmor, 98, 8 + playerArmor * 18, playerArmor));
        }
        //inv
        for (int column = 0; column < 3; column++)
        {
            for (int row = 0; row < 9; row++)
            {
                this.addSlotToContainer(new Slot(inventoryplayer, row + column * 9 + 9, 8 + row * 18, 84 + column * 18));
            }
        }
        //hotbar
        for (int column = 0; column < 9; column++)
        {
            this.addSlotToContainer(new Slot(inventoryplayer, column, 8 + column * 18, 142));
        }

    }

    @Override
    public boolean canInteractWith (EntityPlayer var1)
    {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot (EntityPlayer player, int slotID)
    {
    	
    	return null;/*
    	Slot slot = (Slot)this.inventorySlots.get(slotID);
    	if(slot == null || !slot.getHasStack())return null;
    	
    	if(slot.getStack().getItem() == Main.glider);
    		
    	return slot.getStack();*/
        
    }
}