package mooklabs.nausicaamodtech.machines;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import mooklabs.nausicaamodtech.TechMain;
import mooklabs.nausicaamodtech.machines.tileentity.TileEntityFrictionFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockFrictionFurnace extends BlockAbstractMachine {

	public BlockFrictionFurnace() {
		super(Material.iron);
		wailaBodyToAdd = "Only solar power currently";
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityFrictionFurnace();
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		if (world.isRemote) {
			return true;
		} else {
			TileEntityFrictionFurnace tileentityfurnace = (TileEntityFrictionFurnace) world.getTileEntity(x, y, z);
			if (tileentityfurnace != null) 
				FMLNetworkHandler.openGui(player, TechMain.instance, TechMain.furnaceGuiId, world, x, y, z);

			return true;
		}
	}
}
