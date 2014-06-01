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
		setHarvestLevel("pickaxe", 3); //can only be harvested by diamond pickaxe
		setHardness(3.0F);//normal for ore
		setResistance(5.0F);//normal for ore
	}
	//TODO this block should
	@Override
	public Item getItemDropped(int p_149650_1_, Random rand, int p_149650_3_)
    {
		return LapMain.volucite;
    }

	@Override
    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random rand)
    {	
       //TOEMI do you know what this is--condition ? true : false-- return this == Blocks.lapis_ore ? 4 + rand.nextInt(5) : 1;
		return 1+rand.nextInt(4) == 0 ? 1 : 2;
    }

}
