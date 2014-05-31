package mooklabs.laputamod.items;

import mooklabs.laputamod.LapMain;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class NecklaceString extends Item {

	public NecklaceString() {
		super();
		setMaxStackSize(32);
		setUnlocalizedName("necklaceString");
		setTextureName(LapMain.itemfold + ":necklaceString");
		
	}
	//when player types in chat stuff happens!

	@Override
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        return par1ItemStack;
    }
	
}
