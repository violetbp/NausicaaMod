package mooklabs.nausicaamod;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
//TODO should move this class somewhere else

public class FuelBlock extends Block{

	public FuelBlock() {
		super(Material.iron);
		this.setBlockName("fuelBlock");
		this.setBlockTextureName(Main.itemfold + ":fuelBlock");
		this.setHardness(.5F);
	}
	
	@Override
	public Item getItemDropped(int par1, Random random, int par3)
    {
        return random.nextInt(3)==0? Item.getItemFromBlock(this) : Item.getItemFromBlock(Main.pureDirt);
    }
	//@Override
	/**
     * Returns the quantity of items to drop on block destruction.
     *
    public int quantityDropped(Random random)
    {
        return 5; //random.nextInt(2) + 2;
    }*
	@Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Main.fuelCell;
    }*/
	
	

}
