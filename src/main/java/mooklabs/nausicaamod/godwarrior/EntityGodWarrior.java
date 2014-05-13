package mooklabs.nausicaamod.godwarrior;

import java.util.ArrayList;
import java.util.List;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import mcp.mobius.waila.api.IWailaEntityProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.AnimalChest;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityGodWarrior extends EntityTameable implements IWailaEntityProvider{

	Entity entityToAttack;

	public double energy = 0;
	private double maxEnergy = 1000;

	public EnumActionType action = EnumActionType.Wait;

	private ArrayList<EntityPlayer> owner = new ArrayList();

	private AnimalChest warriorItemStorage;

	public EntityGodWarrior(World par1World) {
		super(par1World);
		this.setSize(1F, 1F);// TODO make accurate
		this.tasks.addTask(0, new EntityAIFollowOwner(this, 10D, 10F, 20F));
		this.tasks.addTask(1, new EntityAIWatchClosest(this, Entity.class, 8.0F));
			
		// this.warriorItemStorage = new AnimalChest("GodWarriorChest", 27);
		System.err.println("[NausicaaMod] GOD WARRIOR SPAWNED");
		// allready can't despawn
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		// this.applyEntityAttributes();
		this.dataWatcher.addObject(31, Byte.valueOf((byte) 0));
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		int i = 10;// this.isTamed() ? 4 : 2;
		if(entity instanceof EntityCreature)
		return ((EntityCreature)(entity)).attackEntityFrom(DamageSource.causeMobDamage(this), (float)i);
		
		return entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float)i);
	}

	@Override
	protected void applyEntityAttributes() {
		// if(this.getAttributeMap())
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);

		if (this.isTamed()) {
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
		} else {
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
		}
	}

	public void addOwner(EntityPlayer player) {
		this.setOwner(player.getCommandSenderName());
		if (player != null) this.owner.add(player);
		
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setDouble("Energy", this.energy);
		par1NBTTagCompound.setDouble("MaxEnergy", this.maxEnergy);
		// System.out.println("GOD WARRIOR add to nbt");

	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		this.energy = par1NBTTagCompound.getDouble("Energy");
		this.maxEnergy = par1NBTTagCompound.getDouble("MaxEnergy");
		this.action = action.Wait;// when init its on wait
		// TODO make special action for when it starts up??
		// System.out.println("GOD WARRIOR read form nbt");

	}

	public void attackHostileEntity() {
		ArrayList<Entity> entityList = (ArrayList<Entity>) this.worldObj.getEntitiesWithinAABBExcludingEntity(this.getOwner(), this.boundingBox.expand(10, 10, 10));
		// for(Entity entit:entityList)System.out.print(entit+"      ");
		this.entityToAttack = null;
		Entity entityTemp;
		for (int index = 0; index < entityList.size(); index++) {
			entityTemp = entityList.get(index);
			if (entityTemp != this.getOwner() && entityTemp != this && entityTemp instanceof EntityLiving && entityTemp instanceof EntityLiving) {
				this.entityToAttack = entityTemp;
				break;
			}
		}

		if (this.entityToAttack != null) System.out.println(this.entityToAttack);
	}

	public void attackEntity(Entity entity) {
		this.entityToAttack = entity;
	}

	private int counter = 0;

	@Override
	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate() {
		// System.out.println(action.toString());
		EntityPlayer player = this.getOwner();

		counter++;
		if (counter > 10) counter = 0;
		this.energy++;

		if (this.action == null) this.action = action.Wait;
		super.onUpdate();
		this.energy -= action.cost / 20;
		this.setAIMoveSpeed(.1F);
		switch (action.charType) {
		case 'w':
			this.setAIMoveSpeed(0);
			break;
		case 'a':
			if (counter == 0) {
				// if (this.entityToAttack == null)
				attackHostileEntity();
				if (this.entityToAttack != null) 
					//if (this.attackEntityAsMob(this.entityToAttack)) 
						this.attackEntityAsMob(this.entityToAttack);
				//else System.out.println("mehneah");

			}
			break;
		case 'f':
			this.posX = player.posX;
			this.posY = player.posY;
			this.posZ = player.posZ + 11;
			break;
		case 'd':
			this.setAIMoveSpeed(.23F);
			break;
		}

	}
	@Override
	 /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return false;
    }

	@Override
	public EntityPlayer getOwner() {
		if (super.getOwner() != null) return (EntityPlayer) super.getOwner();
		return Minecraft.getMinecraft().thePlayer;
	}

	public enum EnumActionType {
		Wait("Wait", 0), Attack("Attack", 10), Follow("Follow", .1), Dig("Dig", 10);

		/** for easyness */
		public final String type;
		/** cost per second */
		public double cost;

		/**
		 * for switches to support java 6<br>
		 * stores the first letter of first argument in lower case
		 */
		char charType;

		private EnumActionType(String type, double cost) {
			this.type = type;
			this.cost = cost;
			this.charType = type.toLowerCase().charAt(0);

		}

		@Override
		public String toString() {
			return "Action: " + type + " Cost: " + cost;

		}

		public String getStringWithSpace() {
			int MAXSPACE = 10;
			String toReturn = type;
			for (int i = 0; i < MAXSPACE - type.length(); i++)
				toReturn += " ";
			return toReturn;
		}

	}

	@Override
	public Entity getWailaOverride(IWailaEntityAccessor paramIWailaEntityAccessor, IWailaConfigHandler paramIWailaConfigHandler) {
		return null;
	}

	@Override
	public List<String> getWailaHead(Entity paramEntity, List<String> paramList, IWailaEntityAccessor paramIWailaEntityAccessor, IWailaConfigHandler paramIWailaConfigHandler) {
		paramList.add("God Warrior");
		return paramList;
	}

	@Override
	public List<String> getWailaBody(Entity paramEntity, List<String> paramList, IWailaEntityAccessor paramIWailaEntityAccessor, IWailaConfigHandler paramIWailaConfigHandler) {
		paramList.add("Power:  " + this.energy);
		paramList.add("Status: " + this.action.type);
		return paramList;
	}

	@Override
	public List<String> getWailaTail(Entity paramEntity, List<String> paramList, IWailaEntityAccessor paramIWailaEntityAccessor, IWailaConfigHandler paramIWailaConfigHandler) {
		paramList.add("NausicaaMod");
		return paramList;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable var1) {
		// NONONONO BAD IDEA
		return null;
	}

	

}
