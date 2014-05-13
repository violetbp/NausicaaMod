package mooklabs.nausicaamod.mobs;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.AnimalChest;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * very simple, may expand and not extned horse later!
 * 
 * @author mooklabs
 */
// this.dataWatcher.addObject(31, new Byte((byte) 0));
/**
 * Gets the name of this command sender (usually username, but possibly "Rcon")
 */
// System.out.println(i);
// 1-4 are for horses :)
// Main.debugWrite("Something bad happened related to the HorseClaw");
/**
 * very simple, may expand and not extned horse later!
 * 
 * @author mooklabs
 */
public class EntityHorseclaw extends EntityHorse {

	private static final IEntitySelector horseBreedingSelector = new IEntitySelector() {

		private static final String __OBFID = "CL_00001642";

		/**
		 * Return whether the specified entity is applicable to this filter.
		 */
		@Override
		public boolean isEntityApplicable(Entity par1Entity) {
			return par1Entity instanceof EntityHorseclaw && ((EntityHorseclaw) par1Entity).func_110205_ce();
		}
	};

	private static final IAttribute horseJumpStrength = (new RangedAttribute("horse.jumpStrength", 0.7D, 0.0D, 2.0D)).setDescription("Jump Strength").setShouldWatch(true);
	private static final String[] horseArmorTextures = new String[] { null, "textures/entity/horse/armor/horse_armor_iron.png", "textures/entity/horse/armor/horse_armor_gold.png",
			"textures/entity/horse/armor/horse_armor_diamond.png" };
	private static final String[] field_110273_bx = new String[] { "", "meo", "goo", "dio" };
	private static final int[] armorValues = new int[] { 0, 5, 7, 11 };
	private static final String[] horseTextures = new String[] { "textures/entity/horse/horse_white.png", "textures/entity/horse/horse_creamy.png", "textures/entity/horse/horse_chestnut.png",
			"textures/entity/horse/horse_brown.png", "textures/entity/horse/horse_black.png", "textures/entity/horse/horse_gray.png", "textures/entity/horse/horse_darkbrown.png" };
	private static final String[] field_110269_bA = new String[] { "hwh", "hcr", "hch", "hbr", "hbl", "hgr", "hdb" };
	private static final String[] horseMarkingTextures = new String[] { null, "textures/entity/horse/horse_markings_white.png", "textures/entity/horse/horse_markings_whitefield.png",
			"textures/entity/horse/horse_markings_whitedots.png", "textures/entity/horse/horse_markings_blackdots.png" };
	private static final String[] field_110292_bC = new String[] { "", "wo_", "wmo", "wdo", "bdo" };
	private int eatingHaystackCounter;
	private int openMouthCounter;
	private int jumpRearingCounter;
	public int field_110278_bp;
	public int field_110279_bq;
	protected boolean horseJumping;
	private AnimalChest horseChest;
	private boolean hasReproduced;
	/**
	 * "The higher this value, the more likely the horse is to be tamed next time a player rides it."
	 */
	protected int temper;
	protected float jumpPower;
	private boolean field_110294_bI;
	private float headLean;
	private float prevHeadLean;
	private float rearingAmount;
	private float prevRearingAmount;
	private float mouthOpenness;
	private float prevMouthOpenness;
	private int field_110285_bP;
	private String field_110286_bQ;
	private String[] field_110280_bR = new String[3];
	private static final String __OBFID = "CL_00001641";

	public EntityHorseclaw(World par1World) {
		super(par1World);
		this.setSize(1.4F, 1.6F);
		this.isImmuneToFire = false;
		this.setChested(false);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 1.2D));
		// this.tasks.addTask(1, new EntityAIRunAroundLikeCrazy(this, 1.2D));
		this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(4, new EntityAIFollowParent(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWander(this, 0.7D));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.func_110226_cD();
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		// this.dataWatcher.addObject(16, Integer.valueOf(0));
		// this.dataWatcher.addObject(19, Byte.valueOf((byte)0));
		// this.dataWatcher.addObject(20, Integer.valueOf(0));
		// this.dataWatcher.addObject(21, String.valueOf(""));
		// this.dataWatcher.addObject(22, Integer.valueOf(0));
		// this.dataWatcher.addObject(31, new Byte((byte)0));
		this.setHorseType(5);

	}

	@Override
	public void setHorseType(int par1) {
		this.dataWatcher.updateObject(19, Byte.valueOf((byte) par1));
		this.func_110230_cF();
	}

	/**
	 * returns the horse type
	 */
	@Override
	public int getHorseType() {
		return this.dataWatcher.getWatchableObjectByte(19);
	}

	@Override
	public void setHorseVariant(int par1) {
		this.dataWatcher.updateObject(20, Integer.valueOf(par1));
		this.func_110230_cF();
	}

	@Override
	public int getHorseVariant() {
		return this.dataWatcher.getWatchableObjectInt(20);
	}

	private boolean getHorseWatchableBoolean(int par1) {
		return (this.dataWatcher.getWatchableObjectInt(16) & par1) != 0;
	}

	private void setHorseWatchableBoolean(int par1, boolean par2) {
		int j = this.dataWatcher.getWatchableObjectInt(16);

		if (par2) {
			this.dataWatcher.updateObject(16, Integer.valueOf(j | par1));
		} else {
			this.dataWatcher.updateObject(16, Integer.valueOf(j & ~par1));
		}
	}

	@Override
	/**
	 * Gets the name of this command sender (usually username, but possibly "Rcon")
	 */
	public String getCommandSenderName() {
		if (this.hasCustomNameTag()) {
			return this.getCustomNameTag();
		} else {
			int i = this.getHorseType();
			// System.out.println(i);
			switch (i) {
			// 1-4 are for horses :)
			case 5:
				return StatCollector.translateToLocal("entity.horseclaw.name");
			default:
				// Main.debugWrite("Something bad happened related to the HorseClaw");
				return StatCollector.translateToLocal("entity.horseclaw.name");
			}
		}
	}

	@Override
	public boolean isAdultHorse() {
		return !this.isChild();
	}

	@Override
	public boolean isTame() {
		return this.getHorseWatchableBoolean(2);
	}

	@Override
	public boolean func_110253_bW() {
		return this.isAdultHorse();
	}

	@Override
	public String getOwnerName() {
		return this.dataWatcher.getWatchableObjectString(21);
	}

	@Override
	public void setOwnerName(String par1Str) {
		this.dataWatcher.updateObject(21, par1Str);
	}

	@Override
	public float getHorseSize() {
		int i = this.getGrowingAge();
		return i >= 0 ? 1.0F : 0.5F + (-24000 - i) / -24000.0F * 0.5F;
	}

	/**
	 * "Sets the scale for an ageable entity according to the boolean parameter, which says if it's a child."
	 */
	@Override
	public void setScaleForAge(boolean par1) {
		if (par1) {
			this.setScale(this.getHorseSize());
		} else {
			this.setScale(1.0F);
		}
	}

	@Override
	public boolean isHorseJumping() {
		return this.horseJumping;
	}

	@Override
	public void setHorseTamed(boolean par1) {
		this.setHorseWatchableBoolean(2, par1);
	}

	@Override
	public void setHorseJumping(boolean par1) {
		this.horseJumping = par1;
	}

	@Override
	public boolean allowLeashing() {
		return !this.func_110256_cu() && super.allowLeashing();
	}

	@Override
	protected void func_142017_o(float par1) {
		if (par1 > 6.0F && this.isEatingHaystack()) {
			this.setEatingHaystack(false);
		}
	}

	@Override
	public boolean isChested() {
		return this.getHorseWatchableBoolean(8);
	}

	@Override
	public int func_110241_cb() {
		return this.dataWatcher.getWatchableObjectInt(22);
	}

	/**
	 * 0 = iron, 1 = gold, 2 = diamond
	 */
	private int getHorseArmorIndex(ItemStack par1ItemStack) {
		if (par1ItemStack == null) {
			return 0;
		} else {
			Item item = par1ItemStack.getItem();
			return item == Items.iron_horse_armor ? 1 : (item == Items.golden_horse_armor ? 2 : (item == Items.diamond_horse_armor ? 3 : 0));
		}
	}

	@Override
	public boolean isEatingHaystack() {
		return this.getHorseWatchableBoolean(32);
	}

	@Override
	public boolean isRearing() {
		return this.getHorseWatchableBoolean(64);
	}

	@Override
	public boolean func_110205_ce() {
		return this.getHorseWatchableBoolean(16);
	}

	@Override
	public boolean getHasReproduced() {
		return this.hasReproduced;
	}

	@Override
	public void func_146086_d(ItemStack p_146086_1_) {
		this.dataWatcher.updateObject(22, Integer.valueOf(this.getHorseArmorIndex(p_146086_1_)));
		this.func_110230_cF();
	}

	@Override
	public void func_110242_l(boolean par1) {
		this.setHorseWatchableBoolean(16, par1);
	}

	@Override
	public void setChested(boolean par1) {
		this.setHorseWatchableBoolean(8, par1);
	}

	@Override
	public void setHasReproduced(boolean par1) {
		this.hasReproduced = par1;
	}

	@Override
	public void setHorseSaddled(boolean par1) {
		this.setHorseWatchableBoolean(4, par1);
	}

	@Override
	public int getTemper() {
		return this.temper;
	}

	@Override
	public void setTemper(int par1) {
		this.temper = par1;
	}

	@Override
	public int increaseTemper(int par1) {
		int j = MathHelper.clamp_int(this.getTemper() + par1, 0, this.getMaxTemper());
		this.setTemper(j);
		return j;
	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		Entity entity = par1DamageSource.getEntity();
		return this.riddenByEntity != null && this.riddenByEntity.equals(entity) ? false : super.attackEntityFrom(par1DamageSource, par2);
	}

	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	@Override
	public int getTotalArmorValue() {
		return armorValues[this.func_110241_cb()];
	}

	/**
	 * Returns true if this entity should push and be pushed by other entities when colliding.
	 */
	@Override
	public boolean canBePushed() {
		return this.riddenByEntity == null;
	}

	@Override
	public boolean prepareChunkForSpawn() {
		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.posZ);
		this.worldObj.getBiomeGenForCoords(i, j);
		return true;
	}

	@Override
	public void dropChests() {
		if (!this.worldObj.isRemote && this.isChested()) {
			this.dropItem(Item.getItemFromBlock(Blocks.chest), 1);
			this.setChested(false);
		}
	}

	private void func_110266_cB() {
		this.openHorseMouth();
		this.worldObj.playSoundAtEntity(this, "eating", 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
	}

	/**
	 * Called when the mob is falling. Calculates and applies fall damage.
	 */
	@Override
	protected void fall(float par1) {
		if (par1 > 1.0F) {
			this.playSound("mob.horse.land", 0.4F, 1.0F);
		}

		int i = MathHelper.ceiling_float_int(par1 * 0.5F - 3.0F);

		if (i > 0) {
			this.attackEntityFrom(DamageSource.fall, i);

			if (this.riddenByEntity != null) {
				this.riddenByEntity.attackEntityFrom(DamageSource.fall, i);
			}

			Block block = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 0.2D - this.prevRotationYaw), MathHelper.floor_double(this.posZ));

			if (block.getMaterial() != Material.air) {
				Block.SoundType soundtype = block.stepSound;
				this.worldObj.playSoundAtEntity(this, soundtype.getStepResourcePath(), soundtype.getVolume() * 0.5F, soundtype.getPitch() * 0.75F);
			}
		}
	}

	private int func_110225_cC() {
		int i = this.getHorseType();
		return this.isChested() && (i == 1 || i == 2) ? 17 : 2;
	}

	private void func_110226_cD() {
		AnimalChest animalchest = this.horseChest;
		this.horseChest = new AnimalChest("HorseChest", this.func_110225_cC());
		this.horseChest.func_110133_a(this.getCommandSenderName());

		if (animalchest != null) {
			animalchest.func_110132_b(this);
			int i = Math.min(animalchest.getSizeInventory(), this.horseChest.getSizeInventory());

			for (int j = 0; j < i; ++j) {
				ItemStack itemstack = animalchest.getStackInSlot(j);

				if (itemstack != null) {
					this.horseChest.setInventorySlotContents(j, itemstack.copy());
				}
			}

			animalchest = null;
		}

		this.horseChest.func_110134_a(this);
		this.func_110232_cE();
	}

	private void func_110232_cE() {
		if (!this.worldObj.isRemote) {
			this.setHorseSaddled(this.horseChest.getStackInSlot(0) != null);

			if (this.func_110259_cr()) {
				this.func_146086_d(this.horseChest.getStackInSlot(1));
			}
		}
	}

	/**
	 * Called by InventoryBasic.onInventoryChanged() on a array that is never filled.
	 */
	@Override
	public void onInventoryChanged(InventoryBasic par1InventoryBasic) {
		int i = this.func_110241_cb();
		boolean flag = this.isHorseSaddled();
		this.func_110232_cE();

		if (this.ticksExisted > 20) {
			if (i == 0 && i != this.func_110241_cb()) {
				this.playSound("mob.horse.armor", 0.5F, 1.0F);
			} else if (i != this.func_110241_cb()) {
				this.playSound("mob.horse.armor", 0.5F, 1.0F);
			}

			if (!flag && this.isHorseSaddled()) {
				this.playSound("mob.horse.leather", 0.5F, 1.0F);
			}
		}
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	@Override
	public boolean getCanSpawnHere() {
		this.prepareChunkForSpawn();
		return super.getCanSpawnHere();
	}

	@Override
	protected EntityHorseclaw getClosestHorse(Entity par1Entity, double par2) {
		double d1 = Double.MAX_VALUE;
		Entity entity1 = null;
		List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(par1Entity, par1Entity.boundingBox.addCoord(par2, par2, par2), horseBreedingSelector);
		Iterator iterator = list.iterator();

		while (iterator.hasNext()) {
			Entity entity2 = (Entity) iterator.next();
			double d2 = entity2.getDistanceSq(par1Entity.posX, par1Entity.posY, par1Entity.posZ);

			if (d2 < d1) {
				entity1 = entity2;
				d1 = d2;
			}
		}

		return (EntityHorseclaw) entity1;
	}

	@Override
	public double getHorseJumpStrength() {
		return 1;// this.getEntityAttribute(horseJumpStrength).getAttributeValue();
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	@Override
	protected String getDeathSound() {
		this.openHorseMouth();
		int i = this.getHorseType();
		return i == 3 ? "mob.horse.zombie.death" : (i == 4 ? "mob.horse.skeleton.death" : (i != 1 && i != 2 ? "mob.horse.death" : "mob.horse.donkey.death"));
	}

	@Override
	protected Item getDropItem() {
		boolean flag = this.rand.nextInt(4) == 0;
		int i = this.getHorseType();
		return i == 4 ? Items.bone : (i == 3 ? (flag ? Item.getItemById(0) : Items.rotten_flesh) : Items.leather);
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
	protected String getHurtSound() {
		this.openHorseMouth();

		if (this.rand.nextInt(3) == 0) {
			this.makeHorseRear();
		}

		int i = this.getHorseType();
		return i == 3 ? "mob.horse.zombie.hit" : (i == 4 ? "mob.horse.skeleton.hit" : (i != 1 && i != 2 ? "mob.horse.hit" : "mob.horse.donkey.hit"));
	}

	@Override
	public boolean isHorseSaddled() {
		return this.getHorseWatchableBoolean(4);
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected String getLivingSound() {
		this.openHorseMouth();

		if (this.rand.nextInt(10) == 0 && !this.isMovementBlocked()) {
			this.makeHorseRear();
		}

		int i = this.getHorseType();
		return i == 3 ? "mob.horse.zombie.idle" : (i == 4 ? "mob.horse.skeleton.idle" : (i != 1 && i != 2 ? "mob.horse.idle" : "mob.horse.donkey.idle"));
	}

	@Override
	protected String getAngrySoundName() {
		this.openHorseMouth();
		this.makeHorseRear();
		int i = this.getHorseType();
		return i != 3 && i != 4 ? (i != 1 && i != 2 ? "mob.horse.angry" : "mob.horse.donkey.angry") : null;
	}

	@Override
	protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
		Block.SoundType soundtype = p_145780_4_.stepSound;

		if (this.worldObj.getBlock(p_145780_1_, p_145780_2_ + 1, p_145780_3_) == Blocks.snow_layer) {
			soundtype = Blocks.snow_layer.stepSound;
		}

		if (!p_145780_4_.getMaterial().isLiquid()) {
			int l = this.getHorseType();

			if (this.riddenByEntity != null && l != 1 && l != 2) {
				++this.field_110285_bP;

				if (this.field_110285_bP > 5 && this.field_110285_bP % 3 == 0) {
					this.playSound("mob.horse.gallop", soundtype.getVolume() * 0.15F, soundtype.getPitch());

					if (l == 0 && this.rand.nextInt(10) == 0) {
						this.playSound("mob.horse.breathe", soundtype.getVolume() * 0.6F, soundtype.getPitch());
					}
				} else if (this.field_110285_bP <= 5) {
					this.playSound("mob.horse.wood", soundtype.getVolume() * 0.15F, soundtype.getPitch());
				}
			} else if (soundtype == Block.soundTypeWood) {
				this.playSound("mob.horse.wood", soundtype.getVolume() * 0.15F, soundtype.getPitch());
			} else {
				this.playSound("mob.horse.soft", soundtype.getVolume() * 0.15F, soundtype.getPitch());
			}
		}
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		// this.getAttributeMap().registerAttribute(horseJumpStrength);
		// this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(53.0D);
		// this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.22499999403953552D);
	}

	/**
	 * Will return how many at most can spawn in a chunk at once.
	 */
	@Override
	public int getMaxSpawnedInChunk() {
		return 6;
	}

	@Override
	public int getMaxTemper() {
		return 100;
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	@Override
	protected float getSoundVolume() {
		return 0.8F;
	}

	/**
	 * Get number of ticks, at least during which the living entity will be silent.
	 */
	@Override
	public int getTalkInterval() {
		return 400;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean func_110239_cn() {
		return this.getHorseType() == 0 || this.func_110241_cb() > 0;
	}

	private void func_110230_cF() {
		this.field_110286_bQ = null;
	}

	@SideOnly(Side.CLIENT)
	private void setHorseTexturePaths() {
		this.field_110286_bQ = "horse/";
		this.field_110280_bR[0] = null;
		this.field_110280_bR[1] = null;
		this.field_110280_bR[2] = null;
		int i = this.getHorseType();
		int j = this.getHorseVariant();
		int k;

		if (i == 0) {
			k = j & 255;
			int l = (j & 65280) >> 8;
			this.field_110280_bR[0] = horseTextures[k];
			this.field_110286_bQ = this.field_110286_bQ + field_110269_bA[k];
			this.field_110280_bR[1] = horseMarkingTextures[l];
			this.field_110286_bQ = this.field_110286_bQ + field_110292_bC[l];
		} else {
			this.field_110280_bR[0] = "";
			this.field_110286_bQ = this.field_110286_bQ + "_" + i + "_";
		}

		k = this.func_110241_cb();
		this.field_110280_bR[2] = horseArmorTextures[k];
		this.field_110286_bQ = this.field_110286_bQ + field_110273_bx[k];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getHorseTexture() {
		if (this.field_110286_bQ == null) {
			this.setHorseTexturePaths();
		}

		return this.field_110286_bQ;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String[] getVariantTexturePaths() {
		if (this.field_110286_bQ == null) {
			this.setHorseTexturePaths();
		}

		return this.field_110280_bR;
	}

	@Override
	public void openGUI(EntityPlayer player) {
		if (!this.worldObj.isRemote && (this.riddenByEntity == null || this.riddenByEntity == player) && this.isTame()) {
			this.horseChest.func_110133_a(this.getCommandSenderName());
			ItemStack heldItem = player.inventory.getCurrentItem();
			if (heldItem != null) player.displayGUIChest(this.horseChest);

			else try {
				player.displayGUIHorse(this, this.horseChest);

			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			// par1EntityPlayer.displayGUIHorse(this, this.horseChest);
		}
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();

		if (itemstack != null && itemstack.getItem() == Items.spawn_egg) {
			return super.interact(par1EntityPlayer);
		} else if (!this.isTame() && this.func_110256_cu()) {
			return false;
		} else if (this.isTame() && this.isAdultHorse() && par1EntityPlayer.isSneaking()) {
			this.openGUI(par1EntityPlayer);
			return true;
		} else if (this.func_110253_bW() && this.riddenByEntity != null) {
			return super.interact(par1EntityPlayer);
		} else {
			if (itemstack != null) {
				boolean flag = false;

				if (this.func_110259_cr()) {
					byte b0 = -1;

					if (itemstack.getItem() == Items.iron_horse_armor) {
						b0 = 1;
					} else if (itemstack.getItem() == Items.golden_horse_armor) {
						b0 = 2;
					} else if (itemstack.getItem() == Items.diamond_horse_armor) {
						b0 = 3;
					}

					if (b0 >= 0) {
						if (!this.isTame()) {
							this.makeHorseRearWithSound();
							return true;
						}

						this.openGUI(par1EntityPlayer);
						return true;
					}
				}

				if (!flag && !this.func_110256_cu()) {
					float f = 0.0F;
					short short1 = 0;
					byte b1 = 0;

					if (itemstack.getItem() == Items.wheat) {
						f = 2.0F;
						short1 = 60;
						b1 = 3;
					} else if (itemstack.getItem() == Items.sugar) {
						f = 1.0F;
						short1 = 30;
						b1 = 3;
					} else if (itemstack.getItem() == Items.bread) {
						f = 7.0F;
						short1 = 180;
						b1 = 3;
					} else if (Block.getBlockFromItem(itemstack.getItem()) == Blocks.hay_block) {
						f = 20.0F;
						short1 = 180;
					} else if (itemstack.getItem() == Items.apple) {
						f = 3.0F;
						short1 = 60;
						b1 = 3;
					} else if (itemstack.getItem() == Items.golden_carrot) {
						f = 4.0F;
						short1 = 60;
						b1 = 5;

						if (this.isTame() && this.getGrowingAge() == 0) {
							flag = true;
							this.func_146082_f(par1EntityPlayer);
						}
					} else if (itemstack.getItem() == Items.golden_apple) {
						f = 10.0F;
						short1 = 240;
						b1 = 100;

						if (this.isTame() && this.getGrowingAge() == 0) {
							flag = true;
							this.func_146082_f(par1EntityPlayer);
						}
					}

					if (this.getHealth() < this.getMaxHealth() && f > 0.0F) {
						this.heal(f);
						flag = true;
					}

					if (!this.isAdultHorse() && short1 > 0) {
						this.addGrowth(short1);
						flag = true;
					}

					if (b1 > 0 && (flag || !this.isTame()) && b1 < this.getMaxTemper()) {
						flag = true;
						this.increaseTemper(b1);
					}

					if (flag) {
						this.func_110266_cB();
					}
				}

				if (!this.isTame() && !flag) {
					if (itemstack != null && itemstack.interactWithEntity(par1EntityPlayer, this)) {
						return true;
					}

					this.makeHorseRearWithSound();
					return true;
				}

				if (!flag && this.func_110229_cs() && !this.isChested() && itemstack.getItem() == Item.getItemFromBlock(Blocks.chest)) {
					this.setChested(true);
					this.playSound("mob.chickenplop", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
					flag = true;
					this.func_110226_cD();
				}

				if (!flag && this.func_110253_bW() && !this.isHorseSaddled() && itemstack.getItem() == Items.saddle) {
					this.openGUI(par1EntityPlayer);
					return true;
				}

				if (flag) {
					if (!par1EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize == 0) {
						par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack) null);
					}

					return true;
				}
			}

			if (this.func_110253_bW() && this.riddenByEntity == null) {
				if (itemstack != null && itemstack.interactWithEntity(par1EntityPlayer, this)) {
					return true;
				} else {
					this.func_110237_h(par1EntityPlayer);
					return true;
				}
			} else {
				return super.interact(par1EntityPlayer);
			}
		}
	}

	private void func_110237_h(EntityPlayer par1EntityPlayer) {
		par1EntityPlayer.rotationYaw = this.rotationYaw;
		par1EntityPlayer.rotationPitch = this.rotationPitch;
		this.setEatingHaystack(false);
		this.setRearing(false);

		if (!this.worldObj.isRemote) {
			par1EntityPlayer.mountEntity(this);
		}
	}

	@Override
	public boolean func_110259_cr() {
		return this.getHorseType() == 0;
	}

	@Override
	public boolean func_110229_cs() {
		return true;
	}

	/**
	 * Dead and sleeping entities cannot move
	 */
	@Override
	protected boolean isMovementBlocked() {
		return this.riddenByEntity != null && this.isHorseSaddled() ? true : this.isEatingHaystack() || this.isRearing();
	}

	@Override
	public boolean func_110256_cu() {
		int i = this.getHorseType();
		return i == 3 || i == 4;
	}

	@Override
	public boolean func_110222_cv() {
		return this.func_110256_cu() || this.getHorseType() == 2;
	}

	/**
	 * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
	 * the animal type)
	 */
	@Override
	public boolean isBreedingItem(ItemStack par1ItemStack) {
		return false;
	}

	private void func_110210_cH() {
		this.field_110278_bp = 1;
	}

	/**
	 * Called when the mob's health reaches 0.
	 */
	@Override
	public void onDeath(DamageSource par1DamageSource) {
		super.onDeath(par1DamageSource);

		if (!this.worldObj.isRemote) {
			this.dropChestItems();
		}
	}

	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	@Override
	public void onLivingUpdate() {
		if (this.rand.nextInt(200) == 0) {
			this.func_110210_cH();
		}

		super.onLivingUpdate();

		if (!this.worldObj.isRemote) {
			if (this.rand.nextInt(900) == 0 && this.deathTime == 0) {
				this.heal(1.0F);
			}

			if (!this.isEatingHaystack() && this.riddenByEntity == null && this.rand.nextInt(300) == 0
					&& this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY) - 1, MathHelper.floor_double(this.posZ)) == Blocks.grass) {
				this.setEatingHaystack(true);
			}

			if (this.isEatingHaystack() && ++this.eatingHaystackCounter > 50) {
				this.eatingHaystackCounter = 0;
				this.setEatingHaystack(false);
			}

			if (this.func_110205_ce() && !this.isAdultHorse() && !this.isEatingHaystack()) {
				EntityHorseclaw entityHorseclaw = this.getClosestHorse(this, 16.0D);

				if (entityHorseclaw != null && this.getDistanceSqToEntity(entityHorseclaw) > 4.0D) {
					PathEntity pathentity = this.worldObj.getPathEntityToEntity(this, entityHorseclaw, 16.0F, true, false, false, true);
					this.setPathToEntity(pathentity);
				}
			}
		}
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		super.onUpdate();

		if (this.worldObj.isRemote && this.dataWatcher.hasChanges()) {
			this.dataWatcher.func_111144_e();
			this.func_110230_cF();
		}

		if (this.openMouthCounter > 0 && ++this.openMouthCounter > 30) {
			this.openMouthCounter = 0;
			this.setHorseWatchableBoolean(128, false);
		}

		if (!this.worldObj.isRemote && this.jumpRearingCounter > 0 && ++this.jumpRearingCounter > 20) {
			this.jumpRearingCounter = 0;
			this.setRearing(false);
		}

		if (this.field_110278_bp > 0 && ++this.field_110278_bp > 8) {
			this.field_110278_bp = 0;
		}

		if (this.field_110279_bq > 0) {
			++this.field_110279_bq;

			if (this.field_110279_bq > 300) {
				this.field_110279_bq = 0;
			}
		}

		this.prevHeadLean = this.headLean;

		if (this.isEatingHaystack()) {
			this.headLean += (1.0F - this.headLean) * 0.4F + 0.05F;

			if (this.headLean > 1.0F) {
				this.headLean = 1.0F;
			}
		} else {
			this.headLean += (0.0F - this.headLean) * 0.4F - 0.05F;

			if (this.headLean < 0.0F) {
				this.headLean = 0.0F;
			}
		}

		this.prevRearingAmount = this.rearingAmount;

		if (this.isRearing()) {
			this.prevHeadLean = this.headLean = 0.0F;
			this.rearingAmount += (1.0F - this.rearingAmount) * 0.4F + 0.05F;

			if (this.rearingAmount > 1.0F) {
				this.rearingAmount = 1.0F;
			}
		} else {
			this.field_110294_bI = false;
			this.rearingAmount += (0.8F * this.rearingAmount * this.rearingAmount * this.rearingAmount - this.rearingAmount) * 0.6F - 0.05F;

			if (this.rearingAmount < 0.0F) {
				this.rearingAmount = 0.0F;
			}
		}

		this.prevMouthOpenness = this.mouthOpenness;

		if (this.getHorseWatchableBoolean(128)) {
			this.mouthOpenness += (1.0F - this.mouthOpenness) * 0.7F + 0.05F;

			if (this.mouthOpenness > 1.0F) {
				this.mouthOpenness = 1.0F;
			}
		} else {
			this.mouthOpenness += (0.0F - this.mouthOpenness) * 0.7F - 0.05F;

			if (this.mouthOpenness < 0.0F) {
				this.mouthOpenness = 0.0F;
			}
		}
	}

	private void openHorseMouth() {
		if (!this.worldObj.isRemote) {
			this.openMouthCounter = 1;
			this.setHorseWatchableBoolean(128, true);
		}
	}

	private boolean func_110200_cJ() {
		return this.riddenByEntity == null && this.ridingEntity == null && this.isTame() && this.isAdultHorse() && !this.func_110222_cv() && this.getHealth() >= this.getMaxHealth();
	}

	@Override
	public void setEating(boolean par1) {
		this.setHorseWatchableBoolean(32, par1);
	}

	@Override
	public void setEatingHaystack(boolean par1) {
		this.setEating(par1);
	}

	@Override
	public void setRearing(boolean par1) {
		if (par1) {
			this.setEatingHaystack(false);
		}

		this.setHorseWatchableBoolean(64, par1);
	}

	private void makeHorseRear() {
		if (!this.worldObj.isRemote) {
			this.jumpRearingCounter = 1;
			this.setRearing(true);
		}
	}

	@Override
	public void makeHorseRearWithSound() {
		this.makeHorseRear();
		String s = this.getAngrySoundName();

		if (s != null) {
			this.playSound(s, this.getSoundVolume(), this.getSoundPitch());
		}
	}

	@Override
	public void dropChestItems() {
		this.dropItemsInChest(this, this.horseChest);
		this.dropChests();
	}

	private void dropItemsInChest(Entity par1Entity, AnimalChest par2AnimalChest) {
		if (par2AnimalChest != null && !this.worldObj.isRemote) {
			for (int i = 0; i < par2AnimalChest.getSizeInventory(); ++i) {
				ItemStack itemstack = par2AnimalChest.getStackInSlot(i);

				if (itemstack != null) {
					this.entityDropItem(itemstack, 0.0F);
				}
			}
		}
	}

	@Override
	public boolean setTamedBy(EntityPlayer par1EntityPlayer) {
		this.setOwnerName(par1EntityPlayer.getCommandSenderName());
		this.setHorseTamed(true);
		return true;
	}

	/**
	 * Moves the entity based on the specified heading. Args: strafe, forward
	 */
	@Override
	public void moveEntityWithHeading(float par1, float par2) {
		if (this.riddenByEntity != null && this.isHorseSaddled()) {
			this.prevRotationYaw = this.rotationYaw = this.riddenByEntity.rotationYaw;
			this.rotationPitch = this.riddenByEntity.rotationPitch * 0.5F;
			this.setRotation(this.rotationYaw, this.rotationPitch);
			this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
			par1 = ((EntityLivingBase) this.riddenByEntity).moveStrafing * 0.5F;
			par2 = ((EntityLivingBase) this.riddenByEntity).moveForward;

			if (par2 <= 0.0F) {
				par2 *= 0.25F;
				this.field_110285_bP = 0;
			}

			if (this.onGround && this.jumpPower == 0.0F && this.isRearing() && !this.field_110294_bI) {
				par1 = 0.0F;
				par2 = 0.0F;
			}

			if (this.jumpPower > 0.0F && !this.isHorseJumping() && this.onGround) {
				this.motionY = this.getHorseJumpStrength() * this.jumpPower;

				if (this.isPotionActive(Potion.jump)) {
					this.motionY += (this.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F;
				}

				this.setHorseJumping(true);
				this.isAirBorne = true;

				if (par2 > 0.0F) {
					float f2 = MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F);
					float f3 = MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F);
					this.motionX += -0.4F * f2 * this.jumpPower;
					this.motionZ += 0.4F * f3 * this.jumpPower;
					this.playSound("mob.horse.jump", 0.4F, 1.0F);
				}

				this.jumpPower = 0.0F;
			}

			this.stepHeight = 1.0F;
			this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

			if (!this.worldObj.isRemote) {
				this.setAIMoveSpeed((float) this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
				super.moveEntityWithHeading(par1, par2);
			}

			if (this.onGround) {
				this.jumpPower = 0.0F;
				this.setHorseJumping(false);
			}

			this.prevLimbSwingAmount = this.limbSwingAmount;
			double d1 = this.posX - this.prevPosX;
			double d0 = this.posZ - this.prevPosZ;
			float f4 = MathHelper.sqrt_double(d1 * d1 + d0 * d0) * 4.0F;

			if (f4 > 1.0F) {
				f4 = 1.0F;
			}

			this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
			this.limbSwing += this.limbSwingAmount;
		} else {
			this.stepHeight = 0.5F;
			this.jumpMovementFactor = 0.02F;
			super.moveEntityWithHeading(par1, par2);
		}
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setBoolean("EatingHaystack", this.isEatingHaystack());
		par1NBTTagCompound.setBoolean("ChestedHorse", this.isChested());
		par1NBTTagCompound.setBoolean("HasReproduced", this.getHasReproduced());
		par1NBTTagCompound.setBoolean("Bred", this.func_110205_ce());
		par1NBTTagCompound.setInteger("Type", this.getHorseType());
		par1NBTTagCompound.setInteger("Variant", this.getHorseVariant());
		par1NBTTagCompound.setInteger("Temper", this.getTemper());
		par1NBTTagCompound.setBoolean("Tame", this.isTame());
		par1NBTTagCompound.setString("OwnerName", this.getOwnerName());

		if (this.isChested()) {
			NBTTagList nbttaglist = new NBTTagList();

			for (int i = 2; i < this.horseChest.getSizeInventory(); ++i) {
				ItemStack itemstack = this.horseChest.getStackInSlot(i);

				if (itemstack != null) {
					NBTTagCompound nbttagcompound1 = new NBTTagCompound();
					nbttagcompound1.setByte("Slot", (byte) i);
					itemstack.writeToNBT(nbttagcompound1);
					nbttaglist.appendTag(nbttagcompound1);
				}
			}

			par1NBTTagCompound.setTag("Items", nbttaglist);
		}

		if (this.horseChest.getStackInSlot(1) != null) {
			par1NBTTagCompound.setTag("ArmorItem", this.horseChest.getStackInSlot(1).writeToNBT(new NBTTagCompound()));
		}

		if (this.horseChest.getStackInSlot(0) != null) {
			par1NBTTagCompound.setTag("SaddleItem", this.horseChest.getStackInSlot(0).writeToNBT(new NBTTagCompound()));
		}
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		this.setEatingHaystack(par1NBTTagCompound.getBoolean("EatingHaystack"));
		this.func_110242_l(par1NBTTagCompound.getBoolean("Bred"));
		this.setChested(par1NBTTagCompound.getBoolean("ChestedHorse"));
		this.setHasReproduced(par1NBTTagCompound.getBoolean("HasReproduced"));
		this.setHorseType(par1NBTTagCompound.getInteger("Type"));
		this.setHorseVariant(par1NBTTagCompound.getInteger("Variant"));
		this.setTemper(par1NBTTagCompound.getInteger("Temper"));
		this.setHorseTamed(par1NBTTagCompound.getBoolean("Tame"));

		if (par1NBTTagCompound.hasKey("OwnerName", 8)) {
			this.setOwnerName(par1NBTTagCompound.getString("OwnerName"));
		}

		IAttributeInstance iattributeinstance = this.getAttributeMap().getAttributeInstanceByName("Speed");

		if (iattributeinstance != null) {
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(iattributeinstance.getBaseValue() * 0.25D);
		}

		if (this.isChested()) {
			NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", 10);
			this.func_110226_cD();

			for (int i = 0; i < nbttaglist.tagCount(); ++i) {
				NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
				int j = nbttagcompound1.getByte("Slot") & 255;

				if (j >= 2 && j < this.horseChest.getSizeInventory()) {
					this.horseChest.setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(nbttagcompound1));
				}
			}
		}

		ItemStack itemstack;

		if (par1NBTTagCompound.hasKey("ArmorItem", 10)) {
			itemstack = ItemStack.loadItemStackFromNBT(par1NBTTagCompound.getCompoundTag("ArmorItem"));

			if (itemstack != null && func_146085_a(itemstack.getItem())) {
				this.horseChest.setInventorySlotContents(1, itemstack);
			}
		}

		if (par1NBTTagCompound.hasKey("SaddleItem", 10)) {
			itemstack = ItemStack.loadItemStackFromNBT(par1NBTTagCompound.getCompoundTag("SaddleItem"));

			if (itemstack != null && itemstack.getItem() == Items.saddle) {
				this.horseChest.setInventorySlotContents(0, itemstack);
			}
		} else if (par1NBTTagCompound.getBoolean("Saddle")) {
			this.horseChest.setInventorySlotContents(0, new ItemStack(Items.saddle));
		}

		this.func_110232_cE();
	}

	/**
	 * Returns true if the mob is currently able to mate with the specified mob.
	 */
	@Override
	public boolean canMateWith(EntityAnimal par1EntityAnimal) {
		if (par1EntityAnimal == this) {
			return false;
		} else if (par1EntityAnimal.getClass() != this.getClass()) {
			return false;
		} else {
			EntityHorseclaw entityHorseclaw = (EntityHorseclaw) par1EntityAnimal;

			if (this.func_110200_cJ() && entityHorseclaw.func_110200_cJ()) {
				int i = this.getHorseType();
				int j = entityHorseclaw.getHorseType();
				return i == j || i == 0 && j == 1 || i == 1 && j == 0;
			} else {
				return false;
			}
		}
	}

	@Override
	public EntityAgeable createChild(EntityAgeable par1EntityAgeable) {
		EntityHorseclaw entityHorseclaw = (EntityHorseclaw) par1EntityAgeable;
		EntityHorseclaw entityHorseclaw1 = new EntityHorseclaw(this.worldObj);
		int i = this.getHorseType();
		int j = entityHorseclaw.getHorseType();
		int k = 0;

		if (i == j) {
			k = i;
		} else if (i == 0 && j == 1 || i == 1 && j == 0) {
			k = 2;
		}

		if (k == 0) {
			int i1 = this.rand.nextInt(9);
			int l;

			if (i1 < 4) {
				l = this.getHorseVariant() & 255;
			} else if (i1 < 8) {
				l = entityHorseclaw.getHorseVariant() & 255;
			} else {
				l = this.rand.nextInt(7);
			}

			int j1 = this.rand.nextInt(5);

			if (j1 < 2) {
				l |= this.getHorseVariant() & 65280;
			} else if (j1 < 4) {
				l |= entityHorseclaw.getHorseVariant() & 65280;
			} else {
				l |= this.rand.nextInt(5) << 8 & 65280;
			}

			entityHorseclaw1.setHorseVariant(l);
		}

		entityHorseclaw1.setHorseType(k);
		double d2 = this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue() + par1EntityAgeable.getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue()
				+ this.func_110267_cL();
		entityHorseclaw1.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(d2 / 3.0D);
		double d1 = this.getEntityAttribute(horseJumpStrength).getBaseValue() + par1EntityAgeable.getEntityAttribute(horseJumpStrength).getBaseValue() + this.func_110245_cM();
		entityHorseclaw1.getEntityAttribute(horseJumpStrength).setBaseValue(d1 / 3.0D);
		double d0 = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue() + par1EntityAgeable.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue()
				+ this.func_110203_cN();
		entityHorseclaw1.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(d0 / 3.0D);
		return entityHorseclaw1;
	}

	/* public IEntityLivingData onSpawnWithEgg(IEntityLivingData par1EntityLivingData)
	 * {
	 * Object par1EntityLivingData1 = super.onSpawnWithEgg(par1EntityLivingData);
	 * boolean flag = false;
	 * int i = 0;
	 * int l;
	 * if (par1EntityLivingData1 instanceof EntityHorseclaw.GroupData)
	 * {
	 * l = ((EntityHorseclaw.GroupData)par1EntityLivingData1).field_111107_a;
	 * i = ((EntityHorseclaw.GroupData)par1EntityLivingData1).field_111106_b & 255 | this.rand.nextInt(5) << 8;
	 * }
	 * else
	 * {
	 * if (this.rand.nextInt(10) == 0)
	 * {
	 * l = 1;
	 * }
	 * else
	 * {
	 * int j = this.rand.nextInt(7);
	 * int k = this.rand.nextInt(5);
	 * l = 0;
	 * i = j | k << 8;
	 * }
	 * par1EntityLivingData1 = new EntityHorseclaw.GroupData(l, i);
	 * }
	 * this.setHorseType(l);
	 * this.setHorseVariant(i);
	 * if (this.rand.nextInt(5) == 0)
	 * {
	 * this.setGrowingAge(-24000);
	 * }
	 * if (l != 4 && l != 3)
	 * {
	 * this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.func_110267_cL());
	 * if (l == 0)
	 * {
	 * this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(this.func_110203_cN());
	 * }
	 * else
	 * {
	 * this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.17499999701976776D);
	 * }
	 * }
	 * else
	 * {
	 * this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0D);
	 * this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20000000298023224D);
	 * }
	 * if (l != 2 && l != 1)
	 * {
	 * this.getEntityAttribute(horseJumpStrength).setBaseValue(this.func_110245_cM());
	 * }
	 * else
	 * {
	 * this.getEntityAttribute(horseJumpStrength).setBaseValue(0.5D);
	 * }
	 * this.setHealth(this.getMaxHealth());
	 * return (IEntityLivingData)par1EntityLivingData1;
	 * } */

	@Override
	@SideOnly(Side.CLIENT)
	public float getGrassEatingAmount(float par1) {
		return this.prevHeadLean + (this.headLean - this.prevHeadLean) * par1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getRearingAmount(float par1) {
		return this.prevRearingAmount + (this.rearingAmount - this.prevRearingAmount) * par1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float func_110201_q(float par1) {
		return this.prevMouthOpenness + (this.mouthOpenness - this.prevMouthOpenness) * par1;
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	@Override
	public void setJumpPower(int par1) {
		if (this.isHorseSaddled()) {
			if (par1 < 0) {
				par1 = 0;
			} else {
				this.field_110294_bI = true;
				this.makeHorseRear();
			}

			if (par1 >= 90) {
				this.jumpPower = 1.0F;
			} else {
				this.jumpPower = 0.4F + 0.4F * par1 / 90.0F;
			}
		}
	}

	/**
	 * "Spawns particles for the horse entity. par1 tells whether to spawn hearts. If it is false, it spawns smoke."
	 */
	@Override
	@SideOnly(Side.CLIENT)
	protected void spawnHorseParticles(boolean par1) {
		String s = par1 ? "heart" : "smoke";

		for (int i = 0; i < 7; ++i) {
			double d0 = this.rand.nextGaussian() * 0.02D;
			double d1 = this.rand.nextGaussian() * 0.02D;
			double d2 = this.rand.nextGaussian() * 0.02D;
			this.worldObj.spawnParticle(s, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ
					+ this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleHealthUpdate(byte par1) {
		if (par1 == 7) {
			this.spawnHorseParticles(true);
		} else if (par1 == 6) {
			this.spawnHorseParticles(false);
		} else {
			super.handleHealthUpdate(par1);
		}
	}

	@Override
	public void updateRiderPosition() {
		super.updateRiderPosition();

		if (this.prevRearingAmount > 0.0F) {
			float f = MathHelper.sin(this.renderYawOffset * (float) Math.PI / 180.0F);
			float f1 = MathHelper.cos(this.renderYawOffset * (float) Math.PI / 180.0F);
			float f2 = 0.7F * this.prevRearingAmount;
			float f3 = 0.15F * this.prevRearingAmount;
			this.riddenByEntity.setPosition(this.posX + f2 * f, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset() + f3, this.posZ - f2 * f1);

			if (this.riddenByEntity instanceof EntityLivingBase) {
				((EntityLivingBase) this.riddenByEntity).renderYawOffset = this.renderYawOffset;
			}
		}
	}

	private float func_110267_cL() {
		return 15.0F + this.rand.nextInt(8) + this.rand.nextInt(9);
	}

	private double func_110245_cM() {
		return 0.4000000059604645D + this.rand.nextDouble() * 0.2D + this.rand.nextDouble() * 0.2D + this.rand.nextDouble() * 0.2D;
	}

	private double func_110203_cN() {
		return (0.44999998807907104D + this.rand.nextDouble() * 0.3D + this.rand.nextDouble() * 0.3D + this.rand.nextDouble() * 0.3D) * 0.25D;
	}

	public static boolean func_146085_a(Item p_146085_0_) {
		return p_146085_0_ == Items.iron_horse_armor || p_146085_0_ == Items.golden_horse_armor || p_146085_0_ == Items.diamond_horse_armor;
	}

	/**
	 * returns true if this entity is by a ladder, false otherwise
	 */
	@Override
	public boolean isOnLadder() {
		return false;
	}

	public static class GroupData implements IEntityLivingData {

		public int field_111107_a;
		public int field_111106_b;
		private static final String __OBFID = "CL_00001643";

		public GroupData(int par1, int par2) {
			this.field_111107_a = par1;
			this.field_111106_b = par2;
		}
	}
}