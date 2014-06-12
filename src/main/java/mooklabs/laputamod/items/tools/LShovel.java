package mooklabs.laputamod.items.tools;

import mooklabs.laputamod.LapMain;
import net.minecraft.item.ItemSpade;

public class LShovel extends ItemSpade{


	public LShovel(ToolMaterial toolMaterial) {
		super(toolMaterial);
		setUnlocalizedName(toolMaterial + "Shovel");//since im making wiht 2 meterials, this is the easyest way to do textures
		setTextureName(LapMain.itemfold + ":" + toolMaterial+"Shovel");
	}

}