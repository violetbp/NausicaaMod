package mooklabs.laputamod.items;

import java.util.List;

import mooklabs.laputamod.LapMain;
import mooklabs.mookcore.MLib;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.BonemealEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * DONT PLAY WITH THIS FOR AWHILE
 * 
 * @author moolabs
 */
public class VoluciteNecklace extends Item {

	public static DamageSource ownMagic = new DamageSource("ownMagic").setDamageBypassesArmor().setMagicDamage();

	public VoluciteNecklace() {
		super();
		setUnlocalizedName("voluciteNecklace");
		setTextureName(LapMain.itemfold + ":voluciteNecklace");
		this.setMaxStackSize(1);
	}

	/**
	 * i think this is like onCrafted, not creative spawned
	 */
	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
		register(itemStack, player, true);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
		if (itemStack.stackTagCompound != null) {

			String owner = itemStack.stackTagCompound.getString("owner");

			if (itemStack.stackTagCompound.getBoolean("creativeSpawned")) list.add("Sole Owner: " + owner);
			else list.add("Owner: " + owner);

			if (owner.equals(player.getCommandSenderName())) {// if belongs to player
				list.add(EnumChatFormatting.GREEN + "Correct owner");
				list.add(EnumChatFormatting.GREEN + "Power: " + getPower(itemStack) + "/" + getMaxPower(itemStack));
				list.add(EnumChatFormatting.RED + "Cooldown: " + getCooldown(itemStack) + "/" + getMaxCooldown(itemStack));
			} else {// if doesent belong to player//TODO logic here is wrong
				list.add(EnumChatFormatting.RED + "This Necklace belongs to someone with power," + " return it to any admin since you can't use it");
			}
		} else {// if has no tags
			list.add(EnumChatFormatting.BLUE + "NO INFO!");
			list.add(EnumChatFormatting.RED + "Right click to register");
		}

	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 * 
	 * @Override public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) { if (!world.isRemote) { if (itemStack.stackTagCompound == null)
	 *           register(itemStack, player, false); if (player.capabilities.isCreativeMode) { itemStack.stackTagCompound.setBoolean("creativeSpawned", true); } } return
	 *           itemStack; }
	 */
	public void register(ItemStack itemStack, EntityPlayer player, boolean wasCrafted) {
		if (itemStack.stackTagCompound == null) {
			itemStack.stackTagCompound = new NBTTagCompound();// create tag
			itemStack.stackTagCompound.setBoolean("creativeSpawned", wasCrafted);
			itemStack.stackTagCompound.setBoolean("launch", true);
			itemStack.stackTagCompound.setBoolean("launchMob", true);
			itemStack.stackTagCompound.setBoolean("hover", true);
			itemStack.stackTagCompound.setBoolean("dig", true);

			itemStack.stackTagCompound.setInteger("power", 50);
			itemStack.stackTagCompound.setInteger("maxPower", 50);
			itemStack.stackTagCompound.setInteger("cooldown", 0);
			itemStack.stackTagCompound.setInteger("maxCooldown", 50);

			itemStack.stackTagCompound.setString("owner", player.getCommandSenderName());

		} else {// has allready been registed

			if (itemStack.stackTagCompound.getBoolean("creativeSpawned")) {
				MLib.printToPlayer("Sorry this has restricted access by " + itemStack.stackTagCompound.getString("owner"));
				return;// if it cannot be claimed by another player(ie for admins)
			} else {
				// switch owner?

				itemStack.stackTagCompound.setString("owner", player.getCommandSenderName());// register to name

			}

		}
	}

	@Override
	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		if (itemStack.stackTagCompound == null) register(itemStack, player, false);
		// NOT YET BECUAE ITS ITEM BASEDelse if (itemStack.stackTagCompound.getString("owner").equals(player.getCommandSenderName())) register(itemStack, player, false);
		NBTTagCompound tagC = itemStack.stackTagCompound;

		if (tagC.getInteger("power") <= getCooldown(itemStack)) {
			return itemStack;// not enough power to execute
		}

		addPower(itemStack, -getCooldown(itemStack));
		addCooldown(itemStack, 1);

		if (getCooldown(itemStack) > 20) player.attackEntityFrom(ownMagic, 4);

		if (tagC.getInteger("power") <= 0) {
			setPower(itemStack, 0);// should never happen
		}

		if (player.isSneaking()) {

			String str = switchMode(itemStack);

			if (str.equals("")) {
				str = switchMode(itemStack);
				if (str.equals("")) {
					tagC.setString("mode", "launch");
				} else {
					tagC.setString("mode", str);

				}
			} else {
				tagC.setString("mode", str);

			}

			MLib.printToPlayer("Effect set to " + tagC.getString("mode"));

		} else {

			switch (tagC.getString("mode")) {
			case "launch":
				launchPlayer(player, 1.3);
				break;
			case "hover":
				player.motionY = 0;
				break;
			case "dig":
				dig(world, player);
				break;
			case "launchMob":// will change launch velocity
				MLib.printToPlayer("You just need to left click on a mob to use this.");
				break;
			default:
				MLib.printToPlayer("Shift-right click to start using");
			}
		}

		return itemStack;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if (stack.stackTagCompound == null) return false;
		if (stack.stackTagCompound.getBoolean("launchMob")) {
			entity.motionY = 4;
			return true;
		}
		return false;
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if (itemStack.stackTagCompound == null) return false;

		NBTTagCompound tagC = itemStack.stackTagCompound;

		if (world.getBlock(par4, par5, par6) == LapMain.solidVoluciteBlock) {
			itemStack.stackTagCompound.setInteger("power", itemStack.stackTagCompound.getInteger("maxPower"));
			itemStack.stackTagCompound.setInteger("cooldown", 0);

			return true;
		} else if (tagC.getInteger("power") <= 0) return false;
		else if (entityPlayer.canPlayerEdit(par4, par5, par6, par7, itemStack) && applyBonemeal(itemStack, world, par4, par5, par6, entityPlayer)) {
			tagC.setInteger("power", tagC.getInteger("power") - tagC.getInteger("cooldown"));
			tagC.setInteger("cooldown", tagC.getInteger("cooldown") + 1);

			if (!world.isRemote) world.playAuxSFX(2005, par4, par5, par6, 0);

			return true;
		}
		return false;
	}

	/**
	 * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and update it's contents.
	 */
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int slotNumber, boolean isHeld) {
		if (itemStack.stackTagCompound == null) return;
		NBTTagCompound tagC = itemStack.stackTagCompound;
		if (tagC.getByte("counter") == 0) {

			addPower(itemStack, 1);//
			addCooldown(itemStack, -1);

			tagC.setByte("counter", (byte) 20);
		}
		tagC.setByte("counter", (byte) (tagC.getByte("counter") - 1));
	}

	public String switchMode(ItemStack stack) {
		NBTTagCompound tagC = stack.stackTagCompound;
		String str = tagC.getString("mode");
		if (str.equals("launch")) {
			str = "hover";
			if (tagC.getBoolean("hover")) return str;
		}
		if (str.equals("hover")) {
			str = "dig";
			if (tagC.getBoolean("dig")) return str;
		}
		if (str.equals("dig")) {
			str = "launchMob";
			if (tagC.getBoolean("launchMob")) return str;
		}
		if (str.equals("launchMob")) {
			str = "launch";
			if (tagC.getBoolean("launch")) return str;
		}

		return "";

	}

	// {{methods that run the actions
	public void launchPlayer(EntityPlayer entityPlayer, double amt) {
		Vec3 desiredDirection = entityPlayer.getLookVec();

		entityPlayer.motionX = amt * desiredDirection.xCoord;
		entityPlayer.motionY = amt * desiredDirection.yCoord;
		entityPlayer.motionZ = amt * desiredDirection.zCoord;
	}

	public void dig(World w, EntityPlayer player) {
		int yaw = (int) player.rotationYaw;

		if (yaw < 0) // due to the yaw running a -360 to positive 360
			yaw += 360; // not sure why it's that way

		yaw += 22; // centers coordinates you may want to drop this line
		yaw %= 360; // and this one if you want a strict interpretation of the zones

		byte facing = (byte) (yaw / 90); // 360degrees divided by 45 == 8 zones
		int st = 0, end = 0;
		boolean nS = false;
		switch (facing) {
		case 0:
			st = 0;
			end = 10;
			nS = false;
			break;// south
		case 1:
			st = -10;
			end = 0;
			nS = true;
			break;// west
		case 2:
			st = -10;
			end = 0;
			nS = false;
			break;// north
		case 3:
			st = 0;
			end = 10;
			nS = true;
			break;// east
		}

		int x = (int) Math.floor(player.posX);
		int z = (int) Math.floor(player.posZ);
		int y = (int) Math.floor(player.posY);
		if (nS) for (int j = 0; j < 3; j++)
			for (int i = st; i < end; i++)
				breakBlock(w, x + i, y + j, z);

		else for (int j = 0; j < 3; j++)
			for (int i = st; i < end; i++)
				breakBlock(w, x, y + j, z + i);

	}

	private void breakBlock(World world, int x, int y, int z) {
		if (world.getBlock(x, y, z) != Blocks.bedrock) {// duh
			world.getBlock(x, y, z).dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}

	public static boolean applyBonemeal(ItemStack itemstack, World world, int x, int y, int z, EntityPlayer player) {
		Block block = world.getBlock(x, y, z);

		BonemealEvent event = new BonemealEvent(player, world, block, x, y, z);
		if (MinecraftForge.EVENT_BUS.post(event)) {
			return false;
		}

		if (event.getResult() == Result.ALLOW) {
			if (!world.isRemote) {
				itemstack.stackSize--;
			}
			return true;
		}

		if (block instanceof IGrowable) {
			IGrowable igrowable = (IGrowable) block;

			if (igrowable.func_149851_a(world, x, y, z, world.isRemote)) {
				if (!world.isRemote) {
					if (igrowable.func_149852_a(world, world.rand, x, y, z)) {
						igrowable.func_149853_b(world, world.rand, x, y, z);
					}

				}

				return true;
			}
		}

		return false;
	}

	// }}

	// {{ unrelated stuff
	// {{for gui mostly
	public int getPower(ItemStack item) {
		return item.stackTagCompound.getInteger("power");
	}

	public int getCooldown(ItemStack item) {
		return item.stackTagCompound.getInteger("cooldown");
	}

	public void setPower(ItemStack item, int x) {
		item.stackTagCompound.setInteger("power", x);
	}

	public void addPower(ItemStack item, int x) {
		NBTTagCompound tagC = item.stackTagCompound;
		setPower(item, getPower(item) + x);
		if (tagC.getInteger("maxPower") < tagC.getInteger("power")) setPower(item, tagC.getInteger("maxPower"));
		if (0 > tagC.getInteger("power")) setPower(item, 0);

	}

	public void setCooldown(ItemStack item, int x) {
		item.stackTagCompound.setInteger("cooldown", x);
	}

	public void addCooldown(ItemStack item, int x) {
		NBTTagCompound tagC = item.stackTagCompound;
		setCooldown(item, getCooldown(item) + x);
		if (tagC.getInteger("maxCooldown") < tagC.getInteger("cooldown")) setCooldown(item, tagC.getInteger("maxCooldown"));
		if (0 > tagC.getInteger("cooldown")) setCooldown(item, 0);

	}

	public int getMaxPower(ItemStack item) {
		return item.stackTagCompound.getInteger("maxPower");
	}

	public int getMaxCooldown(ItemStack item) {
		return item.stackTagCompound.getInteger("maxCooldown");
	}

	/**
	 * sec will be the amt added(normally 1 each per second)<br>
	 * will not go over max
	 * 
	 * @param itemStack to affect
	 * @param sec the number of seconds passed since last update(higher efficicny in chests etc)
	 */
	public void restorePower(ItemStack item, int sec) {
		NBTTagCompound tagC = item.stackTagCompound;
		addPower(item, sec);
		addCooldown(item, sec);

	}

	// }}

	@Override
	public int getEntityLifespan(ItemStack itemStack, World world) {
		return 60;
	}
	// }}
}
