package mooklabs.mookcore.toolsandarmor;

import mooklabs.nausicaamod.Main;
import net.minecraft.item.ItemSpade;

public class NShovel extends ItemSpade{

	
		public NShovel(ToolMaterial toolMaterial) {
			super(toolMaterial);
			setUnlocalizedName(toolMaterial + "Shovel");//since im making wiht 2 meterials, this is the easyest way to do textures
			setTextureName(Main.itemfold + ":" + toolMaterial+"Shovel");
		}
		
}