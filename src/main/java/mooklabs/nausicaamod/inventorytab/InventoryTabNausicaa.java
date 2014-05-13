package mooklabs.nausicaamod.inventorytab;

import mooklabs.nausicaamod.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import tconstruct.client.tabs.AbstractTab;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;


public class InventoryTabNausicaa extends AbstractTab {

	public InventoryTabNausicaa() {
		super(0, 0, 0, new ItemStack(Main.ceramicHelmet));
	}

	@Override
	public void onTabClicked() {
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		FMLNetworkHandler.openGui(p, Main.instance, Main.nausicaaTabGuiId, Minecraft.getMinecraft().theWorld, (int)p.posX, (int)p.posY, (int)p.posZ);
	}

	@Override
	public boolean shouldAddToList() {
		
		return true;
	}

}
