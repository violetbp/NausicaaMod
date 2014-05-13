package mooklabs.nausicaamod.tools.parts;

import mooklabs.nausicaamod.Main;
import net.minecraft.item.Item;


public class ShellFragment extends Item{
    public ShellFragment() {
    	super();
    	setMaxStackSize(64);
	setUnlocalizedName("shellFragment");
	setTextureName(Main.itemfold + ":shellFragment");
    }
}