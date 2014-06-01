package mooklabs.laputamod.blocks;

import mooklabs.laputamod.LapMain;
import mooklabs.mookcore.MLib;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
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
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
		if(player.isSprinting()){
			Vec3 desiredDirection = player.getLookVec();
			
			player.motionX = 1.3 * desiredDirection.xCoord;
			player.motionY = 1.3 * desiredDirection.yCoord;
			player.motionZ = 1.3 * desiredDirection.zCoord;
		}else if(player.isSneaking()){
			
			
		}else{
			player.motionY = 0;
		}
		
        return itemStack;
    }
	
	
	@Override
	/**
     * Called each tick while using an item.
     * @param stack The Item being used
     * @param player The Player using the item
     * @param count The amount of time in tick the item has been used for continuously
     */
    public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
    {
		MLib.printToPlayer("hmm");
		player.motionY = 0;
    }
	
	/**
     * Called when the player Left Clicks (attacks) an entity.
     * Processed before damage is done, if return value is true further processing is canceled
     * and the entity is not attacked.
     * 
     *
     * @param stack The Item being used
     * @param player The player that is attacking
     * @param entity The entity being attacked
     * @return True to cancel the rest of the interaction.
     */
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        entity.motionY = 4;
    	return true;
    }
    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    /* TODOEMI will fix later
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
    	world.getBlock();
        return false;
    }
    */
    
	
}
