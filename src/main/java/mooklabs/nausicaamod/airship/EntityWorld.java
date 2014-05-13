package mooklabs.nausicaamod.airship;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * FUC SHT DAMIT HOW IS THIS EVEN POSSIBLE?????????
 * @author victor
 *
 */
public class EntityWorld extends EntityLiving {
	public Block[] blockArray = {Main.petrifiedLog, Blocks.bedrock};
	private World subWorld;
	public EntityWorld(World par1World) {
		super(par1World);
		
		
	}

	@Override 
	public void onUpdate(){
		//WorldRenderer render = new WorldRenderer(worldObj, null, 0, 0, 0, 1);
	}
	@Override
	protected void entityInit() {
		super.entityInit();
        this.dataWatcher.addObject(31, new Byte((byte)0));

	}

	@Override
	public void readEntityFromNBT(NBTTagCompound var1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound var1) {
		// TODO Auto-generated method stub

	}

}
