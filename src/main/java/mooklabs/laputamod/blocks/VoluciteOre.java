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
		
		setHardness(2.0f);
		setBlockName("voluciteOre");
		setStepSound(Block.soundTypeStone);
		setBlockTextureName(LapMain.itemfold + ":voluciteOre");

	}
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {//TODO EMI Figure out hot to make it drop volucite ingot maybe...
		return null;
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
