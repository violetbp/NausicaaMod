package mooklabs.laputamod.items;

import java.util.List;

import mooklabs.laputamod.LapMain;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
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
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List dataList, boolean bool){
		dataList.add("Can use special abilities");
		if(FMLClientHandler.instance().getClient().currentScreen.isShiftKeyDown()){//is shift down?
			dataList.add("autosmelt");
			dataList.add("instant mining");
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
}