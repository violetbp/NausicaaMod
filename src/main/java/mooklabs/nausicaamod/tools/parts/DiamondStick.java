package mooklabs.nausicaamod.tools.parts;

import mooklabs.nausicaamod.Main;
import net.minecraft.item.Item;

public class DiamondStick extends Item{

    public DiamondStick() {
    	super();
	
	setMaxStackSize(64);
	setUnlocalizedName("diamondStick");
	setTextureName(Main.itemfold + ":diamondStick");
    }    
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     *
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
	Entity slime = new EntitySlime(world);
	slime.forceSpawn = true;
	slime.posX = player.posX;
	slime.posY = player.posY;
	System.out.print("432");
	world.spawnEntityInWorld(slime);
        return true;
    }*/
    


}
