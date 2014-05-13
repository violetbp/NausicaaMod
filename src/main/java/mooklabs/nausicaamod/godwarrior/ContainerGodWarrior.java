package mooklabs.nausicaamod.godwarrior;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

/**
 * container for god warrior, will contain upgrades 
 * @author mooklabs
 *
 */
public class ContainerGodWarrior extends Container {

	public ContainerGodWarrior(InventoryPlayer par1InventoryPlayer) {
		
		
		
		
		for (int l = 0; l < 3; ++l)
        {
            for (int i1 = 0; i1 < 9; ++i1)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18));
            }
        }

        for (int l = 0; l < 9; ++l)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, l, 8 + l * 18, 142));
        }
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		if(player.getHeldItem() != null && player.getHeldItem().getItem() instanceof GodWarriorControl)
		return true;
		return false;
	}
	

}
