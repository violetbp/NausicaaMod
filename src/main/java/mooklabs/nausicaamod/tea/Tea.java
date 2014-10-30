package mooklabs.nausicaamod.tea;

import mooklabs.mookcore.Unused;
import mooklabs.nausicaamod.Main;
import net.minecraft.item.Item;

/**
 * 
 * TODO VIC we should delete this? 
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
