package bcblocks.bettertable.viffy;

import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import bcblocks.bettertable.BetterTable;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class BetterCraftingHandler {

 
	@SubscribeEvent
	public void onCrafting(ItemCraftedEvent event) {

		IInventory craftMatrix = event.craftMatrix;
		ItemStack item = event.crafting;
		EntityPlayer player = event.player;

		int i;
		for (i = 0; i < craftMatrix.getSizeInventory() ; i++) 
		{
			if (craftMatrix.getStackInSlot(i) != null) 
			{
				ItemStack j = craftMatrix.getStackInSlot(i); // Gets the item
				if (j.getItem() != null && j.getItem() == BetterTable.hammer) {
					ItemStack k = new ItemStack(BetterTable.hammer, 2, (j.getItemDamage() + 1));
					if (k.getItemDamage() >= k.getMaxDamage()) {
						k.stackSize--;
					}
					craftMatrix.setInventorySlotContents(i, k);
				}
				if (j.getItem() != null && j.getItem() == BetterTable.handSaw) {
					ItemStack k = new ItemStack(BetterTable.handSaw, 2, (j.getItemDamage() + 1));
					if (k.getItemDamage() >= k.getMaxDamage()) {
						k.stackSize--;
					}
					craftMatrix.setInventorySlotContents(i, k);
				}
			}
		}				


	}
	// @SubscribeEvent
	// public void onSmelting(ItemSmeltedEvent event) {
	// EntityPlayer player=event.player;
	// ItemStack item = event.smelting;
	// }

	
}
