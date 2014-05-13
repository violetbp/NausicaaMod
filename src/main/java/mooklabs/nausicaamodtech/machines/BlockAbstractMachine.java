package mooklabs.nausicaamodtech.machines;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.SpecialChars;
import mooklabs.nausicaamod.blocks.machines.PowerEmitter;
import mooklabs.nausicaamod.blocks.machines.PowerReceiver;
import mooklabs.nausicaamodtech.TechMain;
import mooklabs.nausicaamodtech.machines.tileentity.TileEntityMachineBase;
import mooklabs.nausicaamodtech.tube.Wire;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockAbstractMachine extends BlockContainer implements PowerReceiver, PowerEmitter,  IWailaDataProvider {

	
	public World world;
	Minecraft mc;
	public String wailaBodyToAdd="";

	public BlockAbstractMachine(Material material) {
		super(material);
	}

	/**
	 * Called whenever the block is added into the world. Args: world, x, y, z
	 */
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		if(!world.isRemote)
		this.mc = Minecraft.getMinecraft();
		this.world = world;
		this.world.scheduleBlockUpdate(x, y, z, this, 10);
		((TileEntityMachineBase)(world.getTileEntity(x, y, z))).updatePower();
	}

	
	
	//{{Waila
	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return null;
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		currenttip.add(this.getLocalizedName());
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		currenttip.add(wailaBodyToAdd);
		((TileEntityMachineBase)(accessor.getTileEntity())).updateEntity();
		currenttip.add("Current Power: " + ((TileEntityMachineBase)(accessor.getTileEntity())).currentPower);
		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		currenttip.add(SpecialChars.BLUE + SpecialChars.ITALIC + TechMain.name);
		return currenttip;
	}
	//}}
	
	
	@Override
	public int maxNeededInput() {
		return 10;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityMachineBase();
	}

	@Override
	public int maxOutput() {
		return 10;
	}
	@Override
	public int[] inputSide() {
		return this.ALL_SIDES;
	}
	@Override
	public int[] outputSide() {
		return this.ALL_SIDES;
	}
	

}
