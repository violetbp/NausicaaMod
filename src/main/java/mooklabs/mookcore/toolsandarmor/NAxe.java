package mooklabs.mookcore.toolsandarmor;

import mooklabs.nausicaamod.Main;
import net.minecraft.item.ItemAxe;

public class NAxe extends ItemAxe{

		public NAxe(ToolMaterial toolMaterial) {
			super(toolMaterial);

			setUnlocalizedName(toolMaterial + "Axe");//since im making wiht 2 meterials, this is the easyest way to do textures
			setTextureName(Main.itemfold + ":" + toolMaterial+"Axe");
		}
	}