package mooklabs.nausicaamod.mobs.flying;

import java.util.ArrayList;

import mooklabs.nausicaamod.Main;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Items;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityBeetle extends EntityFlying implements IMob {

	public int courseChangeCooldown;
	public double waypointX;
	public double waypointY;
	public double waypointZ;
	private Entity targetedEntity;
	private int range = 3;
	protected double rage = 10;
	protected boolean angry = false;

	/**
	 * who wants to die instantly?? hmm? no one? okay ill put this in.....
	 * will probaly change depending on players kindness
	 */
	protected int attackCooldown;

	
	/**
	 * THIS DOES NOTHING (yet)(i hope)
	 * Cooldown time between target loss and new target aquirement.
	 */
	private int aggroCooldown;

	public EntityBeetle(World par1World) {
		super(par1World);
		this.setSize(.5F, .5F);// TODO make accurate?
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(31, new Byte((byte) 0));
		this.targetedEntity = null;
	}
		
	/**
     * Called to update the entity's position/logic.
     */
    @Override
	public void onUpdate()
    {
        super.onUpdate();
        if(rage > 9){
        	this.enrageHugeArea();
        	//Main.debugWrite("YOU MONSTER!!");
        }
        if(rage > 3)
        this.angry = true;
        else this.angry = false;

        
        if(angry){
        	this.targetedEntity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 20.0D);
        	if(this.targetedEntity != null && this.attackCooldown < 1){
        		this.attackCooldown = 20;
        		
        		double d0 = this.targetedEntity.posX - this.posX;
        		double d1 = this.targetedEntity.posY - this.posY;
        		double d2 = this.targetedEntity.posZ - this.posZ;
        		double d3 = d0 * d0 + d1 * d1 + d2 * d2;
        		if(d3 < range * range)
        		this.targetedEntity.attackEntityFrom(DamageSource.causeMobDamage(this), 1.0F);
        	}
        	this.attackCooldown--;
        }
        else{
        	this.targetedEntity = null;

        }
       
    }

	@Override
	protected void updateEntityActionState() {
		if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
			this.setDead();
		}

		this.despawnEntity();
		double d0 = this.waypointX - this.posX;
		double d1 = this.waypointY - this.posY;
		double d2 = this.waypointZ - this.posZ;
		double d3 = d0 * d0 + d1 * d1 + d2 * d2;

		if (this.targetedEntity != null) {
			this.waypointX = this.targetedEntity.posX;  //this.posX + (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
			this.waypointY = this.targetedEntity.posY + 1;  //this.posY + (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
			this.waypointZ = this.targetedEntity.posZ;  //this.posZ + (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
		}
		else if (d3 < 1.0D || d3 > 3600.0D) {
			this.waypointX = this.posX + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
			this.waypointY = this.posY + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F-20;
			this.waypointZ = this.posZ + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
		}

		if (this.courseChangeCooldown-- <= 0) {
			this.courseChangeCooldown += this.rand.nextInt(5) + 2;
			d3 = MathHelper.sqrt_double(d3);

			if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3)) {
				this.motionX += d0 / d3 * 0.1D;
				this.motionY += d1 / d3 * 0.1D;
				this.motionZ += d2 / d3 * 0.1D;
			} else {
				this.waypointX = this.posX;
				this.waypointY = this.posY;
				this.waypointZ = this.posZ;
			}
		}

		if (this.targetedEntity != null && this.targetedEntity.isDead) {
			this.targetedEntity = null;
		}

		/*
		if (this.targetedEntity == null || this.aggroCooldown-- <= 0) {
			this.targetedEntity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 100.0D);

			if (this.targetedEntity != null) {
				this.aggroCooldown = 20;
			}
		}*/

		double d4 = 64.0D;

		if (this.targetedEntity != null && this.targetedEntity.getDistanceSqToEntity(this) < d4 * d4) {
			double d5 = this.targetedEntity.posX - this.posX;
			double d6 = this.targetedEntity.boundingBox.minY + this.targetedEntity.height / 2.0F - (this.posY + this.height / 2.0F);
			double d7 = this.targetedEntity.posZ - this.posZ;
			this.renderYawOffset = this.rotationYaw = -((float) Math.atan2(d5, d7)) * 180.0F / (float) Math.PI;

			if (this.canEntityBeSeen(this.targetedEntity)) {

			}

		} else {
			this.renderYawOffset = this.rotationYaw = -((float) Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float) Math.PI;

		}

	}

	/**
	 * True if the ghast has an unobstructed line of travel to the waypoint.
	 */
	private boolean isCourseTraversable(double par1, double par3, double par5, double par7) {
		double d4 = (this.waypointX - this.posX) / par7;
		double d5 = (this.waypointY - this.posY) / par7;
		double d6 = (this.waypointZ - this.posZ) / par7;
		AxisAlignedBB axisalignedbb = this.boundingBox.copy();

		for (int i = 1; i < par7; ++i) {
			axisalignedbb.offset(d4, d5, d6);

			if (!this.worldObj.getCollidingBoundingBoxes(this, axisalignedbb).isEmpty()) {
				return false;
			}
		}

		return true;
	}
	
	/**
     * Called when the entity is attacked.
     */
    @Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
    	this.targetedEntity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 20.0D);//TODO increase?
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
        	
            if ((par1DamageSource instanceof EntityDamageSource || par1DamageSource == DamageSource.magic))
            {
            	double rageIncrease = 2;
            	if(par1DamageSource.getSourceOfDamage() instanceof EntityPlayer)
            		if(((EntityPlayer)(par1DamageSource.getSourceOfDamage())).getHeldItem() == null)
            			rageIncrease = .1;
            		else if(((EntityPlayer)(par1DamageSource.getSourceOfDamage())).getHeldItem().equals(Items.wooden_sword))
            			rageIncrease = 10;


            	int radius = 5;
            	ArrayList<EntityBeetle> entities = new ArrayList();
            	AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(this.posX-radius, this.posY-radius, this.posZ-radius, this.posX+radius, this.posY+radius, this.posZ+radius);
            	entities = (ArrayList<EntityBeetle>) this.worldObj.getEntitiesWithinAABB(EntityBeetle.class, bb);
            	
            	for(EntityBeetle beetle: entities){
            		beetle.rage+=rageIncrease;//have 2 hits before aggro, but adds enragement to all in range.....
            	}
                
            }

            return super.attackEntityFrom(par1DamageSource, par2);
        }
    }
    /**self explanitory!
     * 
     */
    public void enrageHugeArea(){
    	int radius = 40;
    	ArrayList<EntityBeetle> entities = new ArrayList();
    	AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(this.posX-radius, this.posY-radius, this.posZ-radius, this.posX+radius, this.posY+radius, this.posZ+radius);
    	entities = (ArrayList<EntityBeetle>) this.worldObj.getEntitiesWithinAABB(EntityBeetle.class, bb);
    	
    	for(EntityBeetle beetle: entities){
    		if(beetle.rage < 1)
    		beetle.rage+=3.01;//immediate aggro if not engraged!
    	}
    }

	// rendering code
	@SideOnly(Side.CLIENT)
	public static class RenderDragonfly extends RenderLiving {

		private static final ResourceLocation dragonfly = new ResourceLocation(Main.itemfold, "/textures/mobs/dragonfly.png");

		public RenderDragonfly() {// ModelBase par1ModelBase, float par2) {
			super(new ModelDragonfly(), 0.3F);// par1ModelBase, par2);
		}

		@Override
		protected ResourceLocation getEntityTexture(Entity entity) {
			return dragonfly;
		}

	}
}
