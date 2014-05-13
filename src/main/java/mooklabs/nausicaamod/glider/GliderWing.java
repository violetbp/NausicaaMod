package mooklabs.nausicaamod.glider;

import java.util.List;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GliderWing extends Block {

	public GliderWing() {
		super(Material.iron);
		//this.setMaxStackSize(12);
		setBlockName("gliderWing");
		setBlockTextureName(Main.itemfold + ":gliderWing");
	}

	//@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List dataList, boolean bool) {
		dataList.add("Part of the glider");
		// if(FMLClientHandler.instance().getClient().currentScreen.isShiftKeyDown())//is shift down?
		// dataList.add("");

	}

	

}