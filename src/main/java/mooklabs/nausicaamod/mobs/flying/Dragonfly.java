package mooklabs.nausicaamod.mobs.flying;

import java.util.ArrayList;

import mooklabs.nausicaamod.Main;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class Dragonfly extends EntityFlying implements IMob{
	/**
     * randomly selected ChunkCoordinates in a 7x6x7 box around the bat (y offset -2 to 4) towards which it will fly.
     * upon getting close a new target will be selected
     */
    protected ChunkCoordinates currentFlightTarget;
	
	
	protected boolean angry = false;

	public Dragonfly(World world) {
		super(world);
		this.setSize(1.3F, .7F);//TODO make accurate?
	}
	@Override
	protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(31, new Byte((byte)0));
    }
	/**
     * Called to update the entity's position/logic.
     */
    @Override
	public void onUpdate()
    {
        super.onUpdate();
        if(this.attackingPlayer != null ){//&& angry){
        float gotoX = (float) this.attackingPlayer.posX;
        float gotoY = (float) this.attackingPlayer.posY;
        float gotoZ = (float) this.attackingPlayer.posZ;

        this.moveEntity(gotoX, gotoY, gotoZ);
        }
        //if(angry)
       // this.findPlayerToAttack();
       
    }
    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    @Override
	public boolean getCanSpawnHere()
    {
        return true; //this.rand.nextInt(20) == 0 && super.getCanSpawnHere() && this.worldObj.difficultySetting > 0;
    }
    
    @Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(16.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0400000011920929D);
    }

    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected Entity findPlayerToAttack()
    {
        return this.angry ? this.findPlayerToAttackFromEntityMob() : null;
    }
    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected Entity findPlayerToAttackFromEntityMob()
    {
        return this.worldObj.getClosestVulnerablePlayerToEntity(this, 100.0D);//radius needs to be huge
        //return entityplayer != null ? entityplayer : null;
    }
    
    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity par1Entity, float par2)
    {
    	
        if (this.attackTime <= 0 && par2 < 2.0F && par1Entity.boundingBox.maxY > this.boundingBox.minY && par1Entity.boundingBox.minY < this.boundingBox.maxY)
        {
            this.attackTime = 20;
            this.attackEntityAsMob(par1Entity);
        }
    }
    
    @Override
	protected void updateAITasks(){
    
    if (this.currentFlightTarget != null && (!this.worldObj.isAirBlock(this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ) || this.currentFlightTarget.posY < 1))
    {
        this.currentFlightTarget = null;
    }

    if (this.currentFlightTarget == null || this.rand.nextInt(30) == 0 || this.currentFlightTarget.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 4.0F)
    {
        this.currentFlightTarget = new ChunkCoordinates((int)this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int)this.posY + this.rand.nextInt(6) - 2, (int)this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
    }

    double d0 = this.currentFlightTarget.posX + 0.5D - this.posX;
    double d1 = this.currentFlightTarget.posY + 0.1D - this.posY;
    double d2 = this.currentFlightTarget.posZ + 0.5D - this.posZ;
    this.motionX += (Math.signum(d0) * 0.5D - this.motionX) * 0.10000000149011612D;
    this.motionY += (Math.signum(d1) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
    this.motionZ += (Math.signum(d2) * 0.5D - this.motionZ) * 0.10000000149011612D;
    float f = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
    float f1 = MathHelper.wrapAngleTo180_float(f - this.rotationYaw);
    this.moveForward = 0.5F;
    this.rotationYaw += f1;
}
    /**
     * Called when the entity is attacked.
     */
    @Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
        	
            if ((par1DamageSource instanceof EntityDamageSource || par1DamageSource == DamageSource.magic))
            {
            	int radius = 20;
            	ArrayList<Dragonfly> entities = new ArrayList();
            	AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(this.posX-radius, this.posY-radius, this.posZ-radius, this.posX+radius, this.posY+radius, this.posZ+radius);
            	entities = (ArrayList<Dragonfly>) this.worldObj.getEntitiesWithinAABB(Dragonfly.class, bb);
            	
            	for(Dragonfly fly: entities){
            		if(fly != this)
            		System.out.println(fly.toString());
            		fly.angry = true;
            	}
                
            }

            return super.attackEntityFrom(par1DamageSource, par2);
        }
    }
  

	
	//rendering code
	@SideOnly(Side.CLIENT)
    public static class RenderDragonfly extends RenderLiving{

	private static final ResourceLocation dragonfly = new ResourceLocation(Main.itemfold, "/textures/mobs/dragonfly.png");
	
	public RenderDragonfly(ModelBase par1ModelBase, float par2) {
	    super(par1ModelBase, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
	    return dragonfly;
	}
	
	
    }
}
