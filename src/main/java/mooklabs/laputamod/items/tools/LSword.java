package mooklabs.laputamod.items.tools;

import java.util.List;

import mooklabs.laputamod.Data;
import mooklabs.laputamod.LapMain;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LSword extends ItemSword {

	public LSword(ToolMaterial toolMaterial) {
		super(toolMaterial);
		this.setUnlocalizedName(toolMaterial + "Sword");// since im making wiht 2 meterials, this is the easyest way to do textures
		this.setTextureName(LapMain.itemfold + ":" + toolMaterial + "Sword");

	}

	//CRIT this is for pickaxe still
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
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List dataList, boolean bool){
		dataList.add(Data.ability);
		if(FMLClientHandler.instance().getClient().currentScreen.isShiftKeyDown()){//is shift down?
			dataList.add("Range\n kill");
			dataList.add("Data Removal");
		}
	}

	public void changeChargeLevel(){
		//todo DM like
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack)
	{return true;}

	@Override
	/**Queries the percentage of the 'Durability' bar that should be drawn.
	 * @param stack The current ItemStack
	 * @return 1.0 for 100% 0 for 0%
	 */
	public double getDurabilityForDisplay(ItemStack stack)
	{
		return (double)stack.getItemDamageForDisplay() / (double)stack.getMaxDamage();
	}
}
