package mooklabs.laputamod.blocks;

import mooklabs.laputamod.LapMain;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.creativetab.CreativeTabs;

public class VoluciteOre extends BlockOre {

	public VoluciteOre( ) {
		super();
		
		setHardness(2.0f);
		setBlockName("voluciteOre");
		setStepSound(Block.soundTypeStone);
		setBlockTextureName(LapMain.itemfold + ":voluciteOre");

	}

}
