package mooklabs.nausicaamod.tea;

import mooklabs.mookcore.Unused;
import mooklabs.nausicaamod.Main;
import net.minecraft.item.Item;

/**
 * ive forgotten entirely what i was doing frick
 * well here's commit 1...  only 49 to go!
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
