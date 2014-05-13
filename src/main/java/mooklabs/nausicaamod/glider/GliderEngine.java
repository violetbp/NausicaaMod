package mooklabs.nausicaamod.glider;

import java.util.List;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GliderEngine extends Block {

    public GliderEngine() {
	super(Material.iron);
	//this.setMaxStackSize(8);
	setBlockName("gliderEngine");
	setBlockTextureName(Main.itemfold + ":gliderEngine");
    }

    //@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List dataList, boolean bool){
	    dataList.add("Part of the glider");
	   if(GuiScreen.isShiftKeyDown())//is shift down?
		dataList.add("Powers the glider");

    }
}