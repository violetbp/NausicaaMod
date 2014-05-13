package mooklabs.nausicaamod.glider;

import java.util.List;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GliderBody extends Block {

    public GliderBody() {
	super(Material.iron);
	//this.setMaxStackSize(8);
	setBlockName("gliderBody");
	setBlockTextureName(Main.itemfold + ":gliderBody");
    }

    //@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List dataList, boolean bool){
	    dataList.add("Part of the glider");
	   //if(FMLClientHandler.instance().getClient().currentScreen.isShiftKeyDown())//is shift down?
		//dataList.add("");

    }
}