package mooklabs.laputamod.blocks;

import java.util.Random;

import mooklabs.laputamod.LapMain;
import mooklabs.mookcore.MLib;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

/**
 * that suff that laputas made out of
 * @author vicky
 */
public class HighTechBrick extends Block {

	public HighTechBrick() {
		super(Material.iron);

		setBlockName("highTechBrick");
		setStepSound(Block.soundTypeStone);
		setBlockTextureName(LapMain.itemfold + ":highTechBrick");
		

	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random rand, int p_149650_3_) {
		return Item.getItemFromBlock(this);
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		return false;
	}

}
