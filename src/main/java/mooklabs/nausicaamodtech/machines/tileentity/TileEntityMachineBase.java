package mooklabs.nausicaamodtech.machines.tileentity;

import java.util.ArrayList;

import mooklabs.nausicaamod.blocks.machines.PowerEmitter;
import mooklabs.nausicaamodtech.TechMain;
import mooklabs.nausicaamodtech.machines.BlockAbstractMachine;
import mooklabs.nausicaamodtech.machines.BlockWindmill;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMachineBase extends TileEntity {

	public int currentPower = 0;

	public TileEntityMachineBase() {

	}

	public void updatePower() {
		int x = this.xCoord;
		int y = this.yCoord;
		int z = this.zCoord;
		ArrayList<TileEntity> tileEntityList = new ArrayList();
		for (int inputSide : ((BlockAbstractMachine) (this.worldObj.getBlock(x, y, z))).inputSide()) {

			switch (inputSide) {
			case PowerEmitter.TOP:
				if (worldObj.getTileEntity(x, y + 1, z) != null) tileEntityList.add(worldObj.getTileEntity(x, y + 1, z));
				break;
			case PowerEmitter.BOTTOM:
				if (worldObj.getTileEntity(x, y - 1, z) != null) tileEntityList.add(worldObj.getTileEntity(x, y - 1, z));
				break;
			case PowerEmitter.EAST:
				if (worldObj.getTileEntity(x + 1, y, z) != null) tileEntityList.add(worldObj.getTileEntity(x + 1, y, z));
				break;
			case PowerEmitter.WEST:
				if (worldObj.getTileEntity(x - 1, y, z) != null) tileEntityList.add(worldObj.getTileEntity(x - 1, y, z));
				break;
			case PowerEmitter.NORTH:
				if (worldObj.getTileEntity(x, y, z - 1) != null) tileEntityList.add(worldObj.getTileEntity(x, y, z - 1));
				break;
			case PowerEmitter.SOUTH:
				if (worldObj.getTileEntity(x, y, z + 1) != null) tileEntityList.add(worldObj.getTileEntity(x, y, z + 1));
				break;
			}
			int maxPowerInput = 0;
			this.currentPower = 0;
			for (TileEntity tileEntity : tileEntityList) {
				if (tileEntity instanceof TileEntityMachineBase) {// && (tileEntity.getBlockType() instanceof Wire)) {
					if (((TileEntityMachineBase) tileEntity).currentPower > maxPowerInput) maxPowerInput = ((TileEntityMachineBase) tileEntity).currentPower;
				}

			}

			this.currentPower = maxPowerInput;
		}
	}

	public int updatePowerBlock() {
		int x = this.xCoord;
		int y = this.yCoord;
		int z = this.zCoord;
		
		ArrayList<TileEntity> tileEntityList = new ArrayList();
		for (int inputSide : ((BlockAbstractMachine) (this.worldObj.getBlock(x, y, z))).inputSide()) {

			switch (inputSide) {
			case PowerEmitter.TOP:
				if (worldObj.getBlock(x, y + 1, z) instanceof BlockAbstractMachine) tileEntityList.add(worldObj.getTileEntity(x, y + 1, z));
				break;
			case PowerEmitter.BOTTOM:
				if (worldObj.getBlock(x, y - 1, z) instanceof BlockAbstractMachine) tileEntityList.add(worldObj.getTileEntity(x, y - 1, z));
				break;
			case PowerEmitter.EAST:
				if (worldObj.getBlock(x + 1, y, z) instanceof BlockAbstractMachine) tileEntityList.add(worldObj.getTileEntity(x + 1, y, z));
				break;
			case PowerEmitter.WEST:
				if (worldObj.getBlock(x - 1, y, z) instanceof BlockAbstractMachine) tileEntityList.add(worldObj.getTileEntity(x - 1, y, z));
				break;
			case PowerEmitter.NORTH:
				if (worldObj.getBlock(x, y, z - 1) instanceof BlockAbstractMachine) tileEntityList.add(worldObj.getTileEntity(x, y, z - 1));
				break;
			case PowerEmitter.SOUTH:
				if (worldObj.getBlock(x, y, z + 1) instanceof BlockAbstractMachine) tileEntityList.add(worldObj.getTileEntity(x, y, z + 1));
				break;
			}
			int maxPowerInput = 0;
			this.currentPower = 0;
			for (TileEntity tileEntity : tileEntityList) {
				if(tileEntity.getBlockType() instanceof BlockWindmill){
					this.currentPower = tileEntity.yCoord;
					return this.currentPower;
				}
					
				((TileEntityMachineBase) tileEntity).updatePowerBlock();

			}
			}
		
		return this.currentPower;
	}

	@Override
	public void updateEntity() {
		//this.currentPower = this.updatePowerBlock();
		updateSolar();
		// for windmill power curretnly unused
		if (this.getBlockType() == TechMain.windmill) // && this.yCoord > this.currentPower)
		this.currentPower = this.yCoord;
	}

	/** if it should be activated */
	public boolean canBeActive() {
		return ((BlockAbstractMachine) this.getBlockType()).maxNeededInput() >= this.input();
	}
	public void updateSolar(){
		int solar = this.worldObj.getBlockLightValue(this.xCoord, this.yCoord+1, this.zCoord);
    	if(solar >= this.currentPower)
    		this.currentPower = solar;
	}

	@Override
	/**
	 * ensures tileentity will update
	 */
	public boolean canUpdate() {
		return true;
	}

	/** output from block */
	public int output() {
		return this.currentPower;
	}

	/** input to block */
	public int input() {
		return this.currentPower;
	}

}
