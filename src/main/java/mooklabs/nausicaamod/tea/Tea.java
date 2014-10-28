package mooklabs.nausicaamod.tea;

import mooklabs.mookcore.Unused;
import mooklabs.nausicaamod.Main;
import net.minecraft.item.Item;

/**
 * What does this class function as?
 * also why do we have a class named "Tea" and one named "Teas" that's wayyy too similar
 */
@Deprecated @Unused
public class Tea extends Item {
	public Tea() {
		super();
		setUnlocalizedName("tea");
		setTextureName(Main.itemfold + ":tea");
		this.setMaxStackSize(16);
	}

}
