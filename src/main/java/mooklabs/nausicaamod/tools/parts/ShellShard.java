package mooklabs.nausicaamod.tools.parts;

import java.util.List;

import mooklabs.nausicaamod.Main;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ShellShard extends Item {

	public ShellShard() {
		super();
		setMaxStackSize(32);
		setUnlocalizedName("shellShard");
		setTextureName(Main.itemfold + ":shellShard");

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List dataList, boolean bool) {
		// if (ModLoader.getMinecraftInstance().currentScreen.isShiftKeyDown()) {
		dataList.add("Better than Diamond");
		// } else
		// dataList.add("Hold Shift for tooltip");
	}
}