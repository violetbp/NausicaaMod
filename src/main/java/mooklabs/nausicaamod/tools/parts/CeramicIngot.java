package mooklabs.nausicaamod.tools.parts;

import mooklabs.nausicaamod.Main;
import net.minecraft.item.Item;

public class CeramicIngot extends Item {

    public CeramicIngot() {
	super();
	setMaxStackSize(3);
	setUnlocalizedName("ceramicIngot");
	setTextureName(Main.itemfold + ":ceramicIngot");

    }

}