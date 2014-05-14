package mooklabs.mookcore.toolsandarmor;

import mooklabs.nausicaamod.Main;
import net.minecraft.item.ItemPickaxe;

public class NPickaxe extends ItemPickaxe{

	//private String tooltype;//for tooltip dependant on type

		public NPickaxe(ToolMaterial toolMaterial) {
			super(toolMaterial);
			//tooltype = par2EnumToolMaterial + ""; //for tooltip dependant on type only leaving in this for reference
			setUnlocalizedName(toolMaterial + "Pickaxe");//since im making wiht 2 meterials, this is the easyest way to do textures
			setTextureName(Main.itemfold + ":" + toolMaterial+"Pickaxe");
		}
		/*
		@SideOnly(Side.CLIENT)
		public void addInformation(ItemStack itemStack, EntityPlayer player, List dataList, boolean bool){
			if(tooltype.equals("toolType")){
				dataList.add("text");
				if(FMLClientHandler.instance().getClient().currentScreen.isShiftKeyDown())//is shift down?
					dataList.add("text");
			}
		}
		*/
}