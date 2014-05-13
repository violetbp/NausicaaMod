package mooklabs.nausicaamod;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
/**
 * This is the ENTITY, the item's class is defined in main
 * @author victor
 *
 */
public class FlaregunBullet extends EntityArrow {

	EntityPlayer player;
	
    private boolean inGround;

	
	public FlaregunBullet(World world) {
		super(world);
	}

	public FlaregunBullet(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
	}

	public FlaregunBullet(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase, float par4, float par5) {
		super(par1World, par2EntityLivingBase, par3EntityLivingBase, par4, par5);
	}

	public FlaregunBullet(World par1World, EntityLivingBase par2EntityLivingBase, float par3) {
		super(par1World, par2EntityLivingBase, par3);
		player = (EntityPlayer) par2EntityLivingBase;

	}

	@Override
	public void onUpdate(){
		//System.out.print("working1?");
		super.onUpdate();
		//System.out.print("working2?");
		System.out.print(this.prevPosY == this.posY? "t" : "f");
		if(this.prevPosY == this.posY){
			System.out.print("on ground?");

			this.player.addChatMessage(new ChatComponentText("on ground"));
			this.worldObj.setBlock((int)super.posX, (int)super.posY, (int)super.posZ, Blocks.torch);	
			this.setDead();//remove entity
			}
	}
	
	
}
