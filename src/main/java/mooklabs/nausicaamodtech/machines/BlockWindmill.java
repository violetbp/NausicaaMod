package mooklabs.nausicaamodtech.machines;

import java.util.List;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.SpecialChars;
import mooklabs.nausicaamod.Main;
import mooklabs.nausicaamod.blocks.machines.PowerEmitter;
import mooklabs.nausicaamodtech.TechMain;
import mooklabs.nausicaamodtech.machines.tileentity.TileEntityMachineBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public class BlockWindmill extends BlockAbstractMachine{

	int output = 0;
	
	public BlockWindmill() {
		super(Material.iron);
		this.setBlockName("windMill");
		this.setBlockTextureName(Main.itemfold + ":grinder");
	}
	/**
	 * Called whenever the block is added into the world. Args: world, x, y, z
	 */
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		

	}

	@Override
	public int maxOutput() {
		return 10;
	}

	@Override
	public int[] outputSide() {
		return this.ALL_SIDES;
	}
	

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return null;
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		currenttip.add(this.getUnlocalizedName());
		return currenttip;
	}

	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		currenttip.add("Current Power: " + ((TileEntityMachineBase)(accessor.getTileEntity())).currentPower);
		return currenttip;
	}
	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		currenttip.add(SpecialChars.BLUE + SpecialChars.ITALIC + TechMain.name);
		return currenttip;
	}
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityMachineBase();
	}
	

}
