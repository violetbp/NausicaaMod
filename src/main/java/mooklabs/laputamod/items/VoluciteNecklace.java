package mooklabs.laputamod.items;

import mooklabs.laputamod.LapMain;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class VoluciteNecklace extends Item {

	public VoluciteNecklace() {
		super();
		setMaxStackSize(32);
		setUnlocalizedName("voluciteNecklace");
		setTextureName(LapMain.itemfold + ":voluciteNecklace");
		
	}
	//when player types in chat stuff happens!

	@Override
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		if(par3EntityPlayer.isSprinting()){
			Vec3 desiredDirection = par3EntityPlayer.getLookVec();
			
			par3EntityPlayer.motionX = 1.3 * desiredDirection.xCoord;
			par3EntityPlayer.motionY = 1.3 * desiredDirection.yCoord;
			par3EntityPlayer.motionZ = 1.3 * desiredDirection.zCoord;
		}else if(par3EntityPlayer.isSneaking()){
			
			
		}else{
			par3EntityPlayer.motionY = 0;
		}
		
        return par1ItemStack;
    }
	
}
