package mooklabs.nausicaamod.proxy;

import mooklabs.nausicaamod.Main;
import mooklabs.nausicaamod.godwarrior.ContainerGodWarrior;
import mooklabs.nausicaamod.godwarrior.GodWarriorControlGui;
import mooklabs.nausicaamod.inventorytab.NausicaaArmorExtended;
import mooklabs.nausicaamod.inventorytab.NausicaaGuiInventory;
import mooklabs.nausicaamod.inventorytab.NausicaaTabExtendedContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tconstruct.client.tabs.TabRegistry;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandlerNausicaa implements IGuiHandler {

	public static NausicaaArmorExtended nausicaaArmorExtended = new NausicaaArmorExtended();

	
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (player != null) switch (id) {
		case Main.godControlGuiID:
			return new ContainerGodWarrior(player.inventory);
		case Main.nausicaaTabGuiId:
			nausicaaArmorExtended.init(Minecraft.getMinecraft().thePlayer);
			return new NausicaaTabExtendedContainer(player.inventory, nausicaaArmorExtended);

		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		switch (id) {
		case Main.godControlGuiID:
			return player != null ? new GodWarriorControlGui(new ContainerGodWarrior(player.inventory)) : null;
			
		case Main.nausicaaTabGuiId:
			nausicaaArmorExtended.init(Minecraft.getMinecraft().thePlayer);
			return new NausicaaGuiInventory(player.inventory, nausicaaArmorExtended);
		}
		return null;
	}
}