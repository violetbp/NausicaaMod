package mooklabs.nausicaamodtech.builder;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class MineshaftBuilder extends Block {

	public MineshaftBuilder() {
		super(Material.iron);
		this.setBlockName("mineshaftBuilder");
		this.setBlockTextureName(Main.itemfold + ":multishaft");

	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		if (player.getHeldItem() != null) {
			if (player.getHeldItem().getItem() == Items.iron_pickaxe) {
				player.getHeldItem().damageItem(100, player);
				for (int i = -10; i < 10; i++) {// xval
					for (int j = 0; j < 2; j++)
						// yval
						world.setBlockToAir(x + i, y + j, z);
				}

				for (int i = -10; i < 10; i++) {
					for (int j = 0; j < 2; j++)
						// yval
						world.setBlockToAir(x, y + j, z + i);
				}
				// player.getHeldItem().damageItem(100, player);
				return true;
			}
		} else {//if hand empty
			player.addChatMessage(new ChatComponentText("[NausicaaMod]This block digs 10x2x1 mineshafts in all 4 directions"));
			player.addChatMessage(new ChatComponentText("**DISTROYS BLOCK WITH NO DROPS**"));

		}
		return false;
	}

}
