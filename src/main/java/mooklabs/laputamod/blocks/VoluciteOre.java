package mooklabs.laputamod.blocks;

import java.util.Random;

import mooklabs.laputamod.LapMain;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class VoluciteOre extends BlockOre {

	public VoluciteOre( ) {
		super();
		
		setBlockName("voluciteOre");
		setStepSound(Block.soundTypeStone);
		setBlockTextureName(LapMain.itemfold + ":voluciteOre");
		setHarvestLevel("pickaxe", 3); //EMI //can only be harvested by diamond pickaxe

	}
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {//TODO EMI Figure out how to make it drop volucite ingot maybe...
		return LapMain.volucite;
    }

	@Override
    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random p_149745_1_)
    {
        return this == Blocks.lapis_ore ? 4 + p_149745_1_.nextInt(5) : 1;
    }

}
