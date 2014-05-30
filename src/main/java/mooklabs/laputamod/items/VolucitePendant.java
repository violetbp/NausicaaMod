package mooklabs.laputamod.items;

import mooklabs.laputamod.LapMain;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class VolucitePendant extends Item {

	public VolucitePendant() {
		super();
		setMaxStackSize(32);
		setUnlocalizedName("volucitePendant");
		setTextureName(LapMain.itemfold + ":volucitePendant");
		
	}
	//when player types in chat stuff happens!

	@Override
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	//TODO EMI slow down fall??
        return par1ItemStack;
    }
	
}
