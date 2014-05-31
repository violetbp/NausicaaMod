package mooklabs.laputamod.blocks;

import java.util.Random;

import mooklabs.laputamod.LapMain;
import mooklabs.mookcore.MLib;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ChatComponentStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

/**
 * dont mess with this please
 * 
 * @author vicky
 */
public class VoluciteBlock extends Block {

	public VoluciteBlock() {
		super(Material.iron);

		setBlockName("voluciteBlock");
		setStepSound(Block.soundTypeStone);
		setBlockTextureName(LapMain.itemfold + ":voluciteBlock");
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random rand, int p_149650_3_) {
		return Item.getItemFromBlock(this);
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		if (player.getHeldItem() == null) {//will throw null pointer if you dont check if null first
			MLib.printToPlayer("Right click with a necklace silly");
			return false;
		}

		if (isConstructedCorrectly(world, x, y, z)) {
			if (player.getHeldItem().getItem().equals(LapMain.voluciteNecklace)) {
				MLib.printToPlayer(EnumChatFormatting.DARK_BLUE.toString() + EnumChatFormatting.ITALIC + "Access Granted");
				// Suff happends here

				return true;
			} else {
				MLib.printToPlayer("Right click with a neclace");
			}
		} else {
			MLib.printToPlayer("constructed wrong");
		}
		return false;
	}

	public boolean isConstructedCorrectly(World w, int x, int y, int z) {
		for (int i = -1; i < 2; i++)
			for (int j = -1; j < 2; j++)
				if (!isBlockRight(w, x + i, y - 1, z + j, Blocks.gold_block)) return false;
		for (int i = -2; i < 3; i++)
			for (int j = -2; j < 3; j++)
				if (!isBlockRight(w, x + i, y - 2, z + j, Blocks.stonebrick)) return false;

		return true;
	}

	public boolean isBlockRight(World w, int x, int y, int z, Block n) {
		return w.getBlock(x, y, z).equals(n);
	}

}
