package mooklabs.nausicaamod.tools.parts;

import mooklabs.nausicaamod.Main;
import net.minecraft.item.Item;

public class WetClay extends Item {

    public WetClay() {
    	super();
	setMaxStackSize(3);
	setUnlocalizedName("wetClay");
	setTextureName(Main.itemfold + ":wetClay");
    }

}
