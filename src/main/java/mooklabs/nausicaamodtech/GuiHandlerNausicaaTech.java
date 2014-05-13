package mooklabs.nausicaamodtech;


import mooklabs.nausicaamod.Main;
import mooklabs.nausicaamodtech.machines.container.ContainerFrictionFurnace;
import mooklabs.nausicaamodtech.machines.container.ContainerGrinder;
import mooklabs.nausicaamodtech.machines.gui.GuiFrictionFurnace;
import mooklabs.nausicaamodtech.machines.gui.GuiGrinder;
import mooklabs.nausicaamodtech.machines.tileentity.TileEntityFrictionFurnace;
import mooklabs.nausicaamodtech.machines.tileentity.TileEntityGrinder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandlerNausicaaTech implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		Main.debugWrite("gui server");
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		switch(id)
		{	
			case TechMain.grinderGuiId: return world.getBlock(x, y, z) == TechMain.grinder ? new ContainerGrinder(player.inventory, (TileEntityGrinder)tileEntity) : null;
			case TechMain.furnaceGuiId: return world.getBlock(x, y, z) == TechMain.frictionFurnace ? new ContainerFrictionFurnace(player.inventory, (TileEntityFrictionFurnace)tileEntity) : null;

		}
		return null;
	}
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		Main.debugWrite("gui client");
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		switch(id)
		{		
			case TechMain.grinderGuiId: return world.getBlock(x, y, z) == TechMain.grinder ? new GuiGrinder(player.inventory, (TileEntityGrinder)tileEntity) : null;
			case TechMain.furnaceGuiId: return world.getBlock(x, y, z) == TechMain.frictionFurnace ? new GuiFrictionFurnace(player.inventory, (TileEntityFrictionFurnace)tileEntity) : null;

		}
		return null;
	}	
}