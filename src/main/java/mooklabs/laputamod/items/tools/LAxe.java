package mooklabs.laputamod.items.tools;

import mooklabs.laputamod.LapMain;
import net.minecraft.item.ItemAxe;

public class LAxe extends ItemAxe{

	public LAxe(ToolMaterial toolMaterial) {
		super(toolMaterial);

		setUnlocalizedName(toolMaterial + "Axe");//since im making wiht 2 meterials, this is the easyest way to do textures
		setTextureName(LapMain.itemfold + ":" + toolMaterial+"Axe");
	}
}