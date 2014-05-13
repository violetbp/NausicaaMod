package mooklabs.nausicaamod.tools.parts;

import java.util.List;


import mooklabs.nausicaamod.Main;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ReinforcedWetClay extends Item {

    public ReinforcedWetClay() {
	super();
	setMaxStackSize(4);
	setUnlocalizedName("reinforcedWetClay");
	setTextureName(Main.itemfold + ":reinforcedWetClay");
    }

    @Override
	public void addInformation(ItemStack itemStack, EntityPlayer player,
	    List dataList, boolean bool) {
	dataList.add("Only used for the claymore");

    }

}
