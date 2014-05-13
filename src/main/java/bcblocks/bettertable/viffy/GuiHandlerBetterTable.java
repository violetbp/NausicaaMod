package bcblocks.bettertable.viffy;

import bcblocks.bettertable.BetterTable;
import bcblocks.bettertable.table.BetterTableGui;
import bcblocks.bettertable.table.ContainerBetterTable;
import bcblocks.bettertable.tileentity.TileEntityDeviceCraftingTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandlerBetterTable implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		switch (id) {
		case 0:
			if (id == 0 && world.getBlock(x, y, z) == BetterTable.betterTable  ) 
				return new ContainerBetterTable(player.inventory,(TileEntityDeviceCraftingTable) tileEntity, world, x, y, z);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		switch (id) {
		case 0:
			if (id == 0 && world.getBlock(x, y, z) == BetterTable.betterTable ) {
				ContainerBetterTable container = new ContainerBetterTable(player.inventory, ((TileEntityDeviceCraftingTable) tileEntity), world, x, y, z);
				return new BetterTableGui(player.inventory, container, world, x, y, z);
			}

		}
		return null;
	}
}