package mooklabs.nausicaamodtech.tube;

import java.util.Random;

import mooklabs.nausicaamod.blocks.machines.PowerEmitter;
import mooklabs.nausicaamod.blocks.machines.PowerReceiver;
import mooklabs.nausicaamodtech.machines.BlockAbstractMachine;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public class Wire extends BlockAbstractMachine implements PowerEmitter, PowerReceiver {

	public Wire() {
		super(Material.glass);
	}

	@Override
	public int maxNeededInput() {
		return 0;
	}

	@Override
	public int[] inputSide() {
		return PowerEmitter.ALL_SIDES;

	}

	@Override
	public int maxOutput() {
		return 30;
	}

	@Override
	public int[] outputSide() {
		return PowerEmitter.ALL_SIDES;//if i use "this" is ambiguous
	}

	
	
	
	
	

	

}
