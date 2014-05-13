package mooklabs.nausicaamod.mobs;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;


public class EntityBug extends EntityMob {

	public EntityBug(World par1World) {
		super(par1World);
        this.setSize(1F, .5F);//TODO make accurate

	}

	@Override
	protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(31, new Byte((byte)0));
        this.noClip = false;
    }
	
}
