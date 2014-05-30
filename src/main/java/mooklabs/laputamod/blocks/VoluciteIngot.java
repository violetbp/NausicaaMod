package mooklabs.laputamod.blocks;

import mooklabs.laputamod.LapMain;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class VoluciteIngot extends Item {

	public VoluciteIngot() {
		super();
		setMaxStackSize(64);
		setUnlocalizedName("voluciteCrystal");
		setTextureName(LapMain.itemfold + ":voluciteCrystal");
		
	}
	//when player types in chat stuff happens!

	@Override
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
    	//TODO EMI slow down fall??
		entityPlayer.fallDistance = 0;
        return itemStack;
    }
	
}
