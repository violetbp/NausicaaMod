package mooklabs.nausicaamod.godwarrior;

import java.util.List;

import mooklabs.MLib;
import mooklabs.nausicaamod.Main;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class GodWarriorControl extends Item {

	private EntityGodWarrior pairedWarrior;

	public GodWarriorControl() {
		this.setMaxStackSize(1);
	}

	/** called after crafting|||may remove */
	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
		register(itemStack, player, true);
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
		if (itemStack.stackTagCompound != null) {

			String owner = itemStack.stackTagCompound.getString("owner");
			list.add(EnumChatFormatting.GREEN + "Owner:" + owner);

			if (owner.equals(player.getCommandSenderName())) {// if belongs to player
				// display status
				try{
				list.add(EnumChatFormatting.BLUE + "Status:" + pairedWarrior.action.type);
				list.add(EnumChatFormatting.BLUE + "Power:" + pairedWarrior.energy);
				}
				catch(java.lang.NullPointerException e){
					itemStack.stackTagCompound = null;
					this.pairedWarrior = null;
				}
			} else {
				list.add(EnumChatFormatting.RED + "'Encrypted Data': " + EnumChatFormatting.OBFUSCATED + "ABCDEFGHIJKLMNOP");
			}
		} else {// if has tags
			list.add(EnumChatFormatting.BLUE + "Unregisted God Warrior Controler!");
			list.add(EnumChatFormatting.RED + "Right click on ground to register");
		}

	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		if (itemStack.stackTagCompound == null) {
			MLib.printToPlayer("Point at ground to spawn god warrior(temp for testing)");
			register(itemStack, player, false);
			
			// pairedWarrior.addOwner(player);
		}
		else{		
			//mc.objectMouseOver.entityHit; to get the pointed at entity
		player.openGui(Main.instance, Main.godControlGuiID, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
		
		}
		return itemStack;
	}

	/**
	 * Spawns god warrior at point if one is not paired allready<br>
	 * this is called when you point the item at a block
	 */
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		if (((GodWarriorControl)(itemStack.getItem())).pairedWarrior == null) {

			register(itemStack, player, false);
			this.pairedWarrior = new EntityGodWarrior(world);

			this.pairedWarrior.setLocationAndAngles((double) x + 0.5D, (double) y + 5D, (double) z + 0.5D, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
			this.pairedWarrior.rotationYawHead = this.pairedWarrior.rotationYaw;
			this.pairedWarrior.renderYawOffset = this.pairedWarrior.rotationYaw;
			this.pairedWarrior.onSpawnWithEgg((IEntityLivingData) null);
			world.spawnEntityInWorld(this.pairedWarrior);
			this.pairedWarrior.playLivingSound();
			pairedWarrior.addOwner(player);
			return true;
		}
		return false;
	}

	public void register(ItemStack itemStack, EntityPlayer player, boolean wasCrafted) {
		if (itemStack.stackTagCompound == null) itemStack.stackTagCompound = new NBTTagCompound();// create tag

		
		itemStack.stackTagCompound.setBoolean("creativeSpawned", false);
		// itemStack.stackTagCompound.
		itemStack.stackTagCompound.setString("owner", player.getCommandSenderName());// register to name

	}


	/**
	 * @param actionType EnumActionType to have it be set as 
	 */
	public void setWarriorAction(EntityGodWarrior.EnumActionType actionType) {
		this.pairedWarrior.action = actionType;
	}

}
