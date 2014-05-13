package mooklabs.nausicaamod.glider;

import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MetalPlate extends Item {

	public MetalPlate() {
		super();
		// other suff done in the main file
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List dataList, boolean bool) {
		if (GuiScreen.isShiftKeyDown()){ // just checks to see if shift is down
			dataList.add("Used in manufactureing of the glider");
			dataList.add("Right click on the glider builder to get these");
			dataList.add("6 iron = 1 plate");
			//REF This uses full add info!
		} else dataList.add("Hold shift");

	}

}
