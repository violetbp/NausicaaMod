package mooklabs.nausicaamod.mobs;

import java.util.List;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Ohmu extends EntityMob {

    private boolean distroyBlocks = false;


	public Ohmu(World par1World) {
    	super(par1World);
        this.setSize(5F, 2F, 5F);//TODO make accurate
    }

    @Override
	protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(31, new Byte((byte)0));
        this.noClip = false;
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
	public void onUpdate()
    {
        super.onUpdate();
       
    }

    @Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.200000011920929D);
    }

    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    @Override
	protected Entity findPlayerToAttack()
    {
        float f = this.getBrightness(1.0F);

        if (f < 0.5F)
        {
            double d0 = 16.0D;
            return this.worldObj.getClosestVulnerablePlayerToEntity(this, d0);
        }
        else
        {
            return null;
        }
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
	protected String getLivingSound()
    {
        return "mob.spider.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
	protected String getHurtSound()
    {
        return "mob.spider.say";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
	protected String getDeathSound()
    {
        return "mob.spider.death";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.playSound("mob.spider.step", 0.15F, 1.0F);
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    @Override
	protected void attackEntity(Entity par1Entity, float par2)
    {
        float f1 = this.getBrightness(1.0F);

        if (f1 > 0.5F && this.rand.nextInt(100) == 0)
        {
            this.entityToAttack = null;
        }
        else
        {
            if (par2 > 2.0F && par2 < 6.0F && this.rand.nextInt(10) == 0)
            {
                if (this.onGround)
                {
                    double d0 = par1Entity.posX - this.posX;
                    double d1 = par1Entity.posZ - this.posZ;
                    float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
                    this.motionX = d0 / f2 * 0.5D * 0.800000011920929D + this.motionX * 0.20000000298023224D;
                    this.motionZ = d1 / f2 * 0.5D * 0.800000011920929D + this.motionZ * 0.20000000298023224D;
                    this.motionY = 0.4000000059604645D;
                }
            }
            else
            {
                super.attackEntity(par1Entity, par2);
            }
        }
        
        if (!this.worldObj.isRemote && distroyBlocks )
        {
            this.destroyBlocksInAABB(this.boundingBox);
        }
    }
    
    /**
     * Attacks all entities inside this list, dealing 5 hearts of damage.
     * copied from ender dragon, useing for splash damage
     */
    private void attackEntitiesInList(List par1List)
    {
        for (int i = 0; i < par1List.size(); ++i)
        {
            Entity entity = (Entity)par1List.get(i);

            if (entity instanceof EntityLivingBase)
            {
                entity.attackEntityFrom(DamageSource.causeMobDamage(this), 10.0F);
            }
        }
    }
    
    
    @Override
    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected Item getDropItem()
    {
        return Main.shellFragment;
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    @Override
	protected void dropFewItems(boolean par1, int par2)
    {
        super.dropFewItems(par1, par2);

        if (par1 && (this.rand.nextInt(3) == 0 || this.rand.nextInt(1 + par2) > 0))
        {
            this.dropItem(Main.shellFragment, 3);
        }
    }

   
    @Override
	public boolean isPotionApplicable(PotionEffect par1PotionEffect)
    {
        return par1PotionEffect.getPotionID() == Potion.poison.id ? false : super.isPotionApplicable(par1PotionEffect);
    }

    @Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData par1EntityLivingData)
    {
        Object par1EntityLivingData1 = super.onSpawnWithEgg(par1EntityLivingData);

        if (this.worldObj.rand.nextInt(100) == 0)
        {
            EntitySkeleton entityskeleton = new EntitySkeleton(this.worldObj);
            entityskeleton.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            entityskeleton.onSpawnWithEgg((IEntityLivingData)null);
            this.worldObj.spawnEntityInWorld(entityskeleton);
            entityskeleton.mountEntity(this);
        }
       

        return (IEntityLivingData)par1EntityLivingData1;
    }
    
    /**
     * Destroys all blocks that aren't associated with 'The End' inside the given bounding box.
     *///TODO Decide what blocks can be distroyed
    private boolean destroyBlocksInAABB(AxisAlignedBB par1AxisAlignedBB)
    {
        int i = MathHelper.floor_double(par1AxisAlignedBB.minX);
        int j = MathHelper.floor_double(par1AxisAlignedBB.minY);
        int k = MathHelper.floor_double(par1AxisAlignedBB.minZ);
        int l = MathHelper.floor_double(par1AxisAlignedBB.maxX);
        int i1 = MathHelper.floor_double(par1AxisAlignedBB.maxY);
        int j1 = MathHelper.floor_double(par1AxisAlignedBB.maxZ);
        boolean flag = false;
        boolean flag1 = false;

        for (int k1 = i; k1 <= l; ++k1)
        {
            for (int l1 = j; l1 <= i1; ++l1)
            {
                for (int i2 = k; i2 <= j1; ++i2)
                {
                    Block block = this.worldObj.getBlock(k1, l1, i2);
                    

                    if (block != null)
                    {
                        if (block.canEntityDestroy(worldObj, k1, l1, i2, this) && this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"))
                        {
                            flag1 = this.worldObj.setBlockToAir(k1, l1, i2) || flag1;
                        }
                        else
                        {
                            flag = true;
                        }
                    }
                }
            }
        }

        if (flag1)
        {
            double d0 = par1AxisAlignedBB.minX + (par1AxisAlignedBB.maxX - par1AxisAlignedBB.minX) * this.rand.nextFloat();
            double d1 = par1AxisAlignedBB.minY + (par1AxisAlignedBB.maxY - par1AxisAlignedBB.minY) * this.rand.nextFloat();
            double d2 = par1AxisAlignedBB.minZ + (par1AxisAlignedBB.maxZ - par1AxisAlignedBB.minZ) * this.rand.nextFloat();
            this.worldObj.spawnParticle("largeexplode", d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }

        return flag;
    }
    /**
     * Sets the width and height of the entity. Args: width, height
     *
     * @param par1 the height
     * @param par2 the widthX
     * @param par3 the widthZ
     */
    protected void setSize(float par1, float par2, float par3)
    {
        float f2;

        if (par1 != this.width || par2 != this.height)
        {
            f2 = this.width;
            this.width = par1;
            this.height = par2;
            this.boundingBox.maxX = this.boundingBox.minX + this.width;
            this.boundingBox.maxZ = this.boundingBox.minZ + par3;
            this.boundingBox.maxY = this.boundingBox.minY + this.height;

            //if (this.width > f2 && !this.firstUpdate && !this.worldObj.isRemote)
            
            //    this.moveEntity((double)(f2 - this.width), 0.0D, (double)(f2 - this.width));
            
        }

        f2 = par1 % 2.0F;

        if (f2 < 0.375D)
        {
            this.myEntitySize = EnumEntitySize.SIZE_1;
        }
        else if (f2 < 0.75D)
        {
            this.myEntitySize = EnumEntitySize.SIZE_2;
        }
        else if (f2 < 1.0D)
        {
            this.myEntitySize = EnumEntitySize.SIZE_3;
        }
        else if (f2 < 1.375D)
        {
            this.myEntitySize = EnumEntitySize.SIZE_4;
        }
        else if (f2 < 1.75D)
        {
            this.myEntitySize = EnumEntitySize.SIZE_5;
        }
        else
        {
            this.myEntitySize = EnumEntitySize.SIZE_6;
        }
    }
    
    public final String getBookData(){
    	return "The Ohmu is a giant beast, connected by a Hive mind, they are more " +
    			"intelligent than they seem. If you are kind to them there is no telling what might happen.....";
    }
    
    
    @SideOnly(Side.CLIENT)
    public static class RenderOhmu extends RenderLiving{

	public static final ResourceLocation ohmu = new ResourceLocation(Main.itemfold, "/textures/mobs/ohmu.png");
	
	public RenderOhmu(ModelBase par1ModelBase, float par2) {
	    super(par1ModelBase, par2);
	    
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
	    return ohmu;
	}
	
	
    }
}
