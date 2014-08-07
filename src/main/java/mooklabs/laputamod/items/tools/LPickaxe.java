package mooklabs.laputamod.items.tools;

import java.util.List;

import mooklabs.laputamod.Data;
import mooklabs.laputamod.LapMain;
import mooklabs.mookcore.MLib;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LPickaxe extends ItemPickaxe{

	//private String tooltype;//for tooltip dependant on type

	public LPickaxe(ToolMaterial toolMaterial) {
		super(toolMaterial);
		//tooltype = par2EnumToolMaterial + ""; //for tooltip dependant on type only leaving in this for reference
		setUnlocalizedName(toolMaterial + "Pickaxe");
		setTextureName(LapMain.itemfold + ":" + toolMaterial+"Pickaxe");
	}

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
			dataList.add("autosmelt");
			dataList.add("instant mining");
			dataList.add("Inate ability: digs though Stone like butter!");
		}
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta)
	{
		if (block == Blocks.stone)
		{
			return 10;
		}
		return super.getDigSpeed(stack, block, meta);
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