package mooklabs.laputamod.items.tools;

import java.util.List;

import mooklabs.laputamod.Data;
import mooklabs.laputamod.LapMain;
import mooklabs.mookcore.MLib;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LAxe extends ItemAxe{

	public LAxe(ToolMaterial toolMaterial) {
		super(toolMaterial);

		setUnlocalizedName(toolMaterial + "Axe");//since im making wiht 2 meterials, this is the easyest way to do textures
		setTextureName(LapMain.itemfold + ":" + toolMaterial+"Axe");
	}


	//CRIT this is for pickaxe still
	@Override
	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		return itemstack;
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		if(player.getHealth()>4)MLib.specialBreakBlock(world, x, y, z,1,5);
		if(player.getHealth()>4)
			player.attackEntityFrom(MLib.ownMagic, 4);
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List dataList, boolean bool){
		dataList.add(Data.ability);
		if(FMLClientHandler.instance().getClient().currentScreen.isShiftKeyDown()){//is shift down?
			dataList.add("Timber");
		}
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