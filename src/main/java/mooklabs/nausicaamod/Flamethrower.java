package mooklabs.nausicaamod;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
//TODO should move this somewhere else

public class Flamethrower extends Item {

	public Flamethrower() {
		this.setUnlocalizedName("flamethrower");
		this.setTextureName(Main.itemfold + ":flamethrower");

	}
	
	@Override
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     * When i used onItemUse it didnt set mobs on fire
     */
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
		//player.addChatMessage(new ChatComponentText("correct"));
		//AxisAlignedBB player.boundingBox.copy();
	       int yaw = (int)player.rotationYaw;

           if (yaw<0)      //due to the yaw running a -360 to positive 360
              yaw+=360;    //not sure why it's that way

           yaw+=22;     //centers coordinates you may want to drop this line
           yaw%=360;  //and this one if you want a strict interpretation of the zones

           byte facing = (byte) (yaw/45);   //  360degrees divided by 45 == 8 zones
           if(Main.debug)Main.debugWrite("Yaw is " + yaw + "facing is " + facing);
           int x,z;
           switch(facing){
           case 0:x =  0; z =  2;//south (+z)
        	   break;
           case 1:x = -2; z =  2;
    	   break;
           case 2:x = -2; z =  0;//west (-x)
    	   break;
           case 3:x = -2; z = -2;
    	   break;
           case 4:x =  0; z = -2;//north (-z)
    	   break;
           case 5:x =  2; z = -2;
    	   break;
           case 6:x =  2; z = 0;//east (+x)
    	   break;
           case 7:x =  2; z = 2;
    	   break;
           default: x=0; z=0;
           }
           
	       //for the up/down of the flamethrowerint pitch = (int)player.rotationPitch/90;//360/90 = 4
	       
           
           
           
	    AxisAlignedBB boundingBox = player.boundingBox.copy().offset(x, 0, z).expand(1, 1, 1);
		this.attackEntitiesInList(player, world.getEntitiesWithinAABBExcludingEntity(player, boundingBox));
		//TODO kill sporeballs, 
        return itemStack;
    }
	
	/**
     * Attacks all entities inside this list, dealing some hearts of damage.
     */
    private void attackEntitiesInList(EntityPlayer player, List list)
    {
        for (int i = 0; i < list.size(); ++i)
        {
            Entity entity = (Entity)list.get(i);

            if (entity instanceof EntityLivingBase && (entity != player))//MAYBE change to be all entities, or itementitys
            {
        		//player.addChatMessage(new ChatComponentText(entity.toString()));
                (entity).setFire(5);
                //entity.attackEntityFrom(DamageSource.onFire, 20.0F);//half heart
                //((EntityLivingBase) entity).setHealth(((EntityLivingBase) entity).getHealth()-1);//THIS WORKS
            }
        }
    }
	

}
