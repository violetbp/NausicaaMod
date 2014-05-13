package mooklabs.nausicaamod.tools;

import java.util.List;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class NSword extends ItemSword {

	public NSword(ToolMaterial toolMaterial) {
		super(toolMaterial);
		this.setUnlocalizedName(toolMaterial + "Sword");// since im making wiht 2 meterials, this is the easyest way to do textures
		this.setTextureName(Main.itemfold + ":" + toolMaterial + "Sword");

	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		if (this.getToolMaterialName().equals("shell")) {
			// player.addChatMessage(new ChatComponentText("correct"));
			this.attackEntitiesInList(player, world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.copy().expand(4, 4, 4)));
			player.addExhaustion(3);
		}
		return itemStack;
	}

	/**
	 * Attacks all entities inside this list, dealing some hearts of damage.
	 */
	private void attackEntitiesInList(EntityPlayer player, List list) {
		for (int i = 0; i < list.size(); ++i) {
			Entity entity = (Entity) list.get(i);

			if (entity instanceof EntityLivingBase && (entity != player))// MAYBE change to be all entities, or itementitys
			{
				// player.addChatMessage(new ChatComponentText(entity.toString()));
				entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 5.0F);// 2.5 heart

				// /((EntityLivingBase) entity).setHealth(((EntityLivingBase) entity).getHealth() - 2);// THIS WORKS
			}
		}
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List dataList, boolean bool) {
		if (this.getToolMaterialName().equals("shell")) dataList.add("Try right clicking near some enemies, I dare you");

	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta) {
		if(this.getToolMaterialName().equals("ceramicSharp"))	return 10F;
		//if (ForgeHooks.isToolEffective(stack, block, meta)) 	return 10F;
		return super.getDigSpeed(stack, block, meta);
		
		
	}

}
