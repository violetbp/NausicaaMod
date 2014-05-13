package mooklabs.nausicaamod.glider;

import java.util.List;

import mooklabs.nausicaamod.Main;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GliderFull extends Item {

    public GliderFull() {
	super();
	this.setMaxStackSize(1);
	setUnlocalizedName("gliderFull");
	setTextureName(Main.itemfold + ":gliderFull");
    }

    @Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List dataList, boolean bool){
	    dataList.add("Very expensive, way overpowered!(tm)");
	    dataList.add("Press Shift");
	   if(GuiScreen.isShiftKeyDown()){//is shift down?
		dataList.add("configure on/off keys in conrtrols");
		dataList.add("Don't land to hard!");
	   }

    }
}