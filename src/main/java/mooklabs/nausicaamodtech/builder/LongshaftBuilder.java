package mooklabs.nausicaamodtech.builder;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class LongshaftBuilder extends Block {

	public LongshaftBuilder() {
		super(Material.iron);
		this.setBlockName("longshaftBuilder");
		this.setBlockTextureName(Main.itemfold + ":longshaft");

	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		if (player.getHeldItem() != null) {
			if (player.getHeldItem().getItem() == Items.iron_pickaxe && player.getHeldItem().getItem().getDamage(player.getHeldItem()) < 300) {
				player.getHeldItem().damageItem(300, player);
				Block block = null;
				for (int i = 1; i < 100; i++) {// xval
					for (int j = -1; j < 2; j++) {
						// yval
						block = world.getBlock(x + i, y + j, z);
						if(j == -1){
							if(block == Blocks.air)
								world.setBlock(x + i, y + j, z, Blocks.cobblestone);//bottom
						}
						else if (block == Blocks.dirt || block == Blocks.grass || block == Blocks.stone || block == Blocks.cobblestone || block == Blocks.gravel) {
							world.setBlock(x + i, y + j, z, Blocks.air);
						}
					}
				}
				for (int i = 1; i < 100; i += 10) {// xval
					world.setBlock(x + i, y, z, Blocks.torch);//torch every 10 blocks
				}
				/*for (int i = -10; i < 10; i++) {
				 * for (int j = 0; j < 2; j++)
				 * // yval
				 * world.setBlockToAir(x, y + j, z + i);
				 * } */

				return true;
			}
		} else {// if hand empty
			player.addChatMessage(new ChatComponentText("[NausicaaMod]This block digs a 100x2x1 mineshaft"));
			player.addChatMessage(new ChatComponentText("**Will only distroy 'crappy' blocks(torches overwrite though)**"));

		}
		return false;
	}

}
