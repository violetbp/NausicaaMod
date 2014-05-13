package mooklabs.nausicaamodtech.tube;

import java.util.Random;

import mooklabs.nausicaamodtech.TechMain;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneRepeater;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockRedstoneTimer extends BlockRedstoneRepeater {

	public int counter = 20;
	
	public BlockRedstoneTimer( ) {
		super(true);
		this.setTickRandomly(true);
	}
	@Override
	/**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
    {
    	this.isProvidingWeakPower(p_149674_1_, p_149674_4_, p_149674_4_, p_149674_4_, p_149674_4_);
        
    }
	
	/**
	 * really importaint
	 */
    @Override
    public int tickRate(World world)
    {
            return 1;
    }
	
	public Item getItemDropped(int p_149650_1_, Random random, int p_149650_3_)
    {
        return Item.getItemFromBlock(TechMain.timer);
    }
	@Override
	public int isProvidingWeakPower(IBlockAccess p_149748_1_, int p_149748_2_, int p_149748_3_, int p_149748_4_, int p_149748_5_){
	return this.isProvidingStrongPower(p_149748_1_, p_149748_2_, p_149748_3_, p_149748_4_, p_149748_5_);
	}
	
	@Override
	public int isProvidingStrongPower(IBlockAccess p_149748_1_, int p_149748_2_, int p_149748_3_, int p_149748_4_, int p_149748_5_)
    {
		System.out.println(counter);
		if(counter < 1 && counter >0){
			return 15;
		}        
	       
		if(counter < 0){
			counter = 2;
			return 15;
		}        

		counter--;
		return 0;
    }
}
