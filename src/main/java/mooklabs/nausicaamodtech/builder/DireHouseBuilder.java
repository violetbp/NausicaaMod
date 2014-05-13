package mooklabs.nausicaamodtech.builder;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class DireHouseBuilder extends Block {

	public DireHouseBuilder() {
		super(Material.iron);
		this.setBlockName("direHouseBuilder");
		this.setBlockTextureName(Main.itemfold + ":direhouse");

	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		int numBricks = 0;
		int glassSlot = -1;
		int torchSlot = -1;
		int[] brickSlots = {-1, -1, -1, -1, -1};

		if (player.getHeldItem() != null) {// if hand not empty
			for (int i = 0; i < 36; i++) {
				if (player.inventory.getStackInSlot(i) != null) {
					if (player.inventory.getStackInSlot(i).getItem() == Item.getItemFromBlock(Blocks.stonebrick) && player.inventory.getStackInSlot(i).stackSize >= 64) 
							 if (brickSlots[0] == -1) brickSlots[0] = i;
						else if (brickSlots[1] == -1) brickSlots[1] = i;
						else if (brickSlots[2] == -1) brickSlots[2] = i;
						else if (brickSlots[3] == -1) brickSlots[3] = i;
						else if (brickSlots[4] == -1) brickSlots[4] = i;
					if (player.inventory.getStackInSlot(i).getItem() == Item.getItemFromBlock(Blocks.glass) && player.inventory.getStackInSlot(i).stackSize >= 16) 
						glassSlot = i;
					if (player.inventory.getStackInSlot(i).getItem() == Item.getItemFromBlock(Blocks.torch) && player.inventory.getStackInSlot(i).stackSize >= 4) 
						torchSlot = i;
				}
			}
			if (glassSlot != -1 && torchSlot != -1 && brickSlots[0] != -1 && brickSlots[1] != -1) {
				//remove stuff from inventory
				player.inventory.mainInventory[glassSlot].stackSize -= 16;
				player.inventory.mainInventory[torchSlot].stackSize -= 4;
				for(int slot = 0; slot < brickSlots.length; slot++)
				player.inventory.mainInventory[brickSlots[slot]] = null;
				
				int height  = 5;
				for (int i = -4; i <= 4; i++)//floor
					for (int k = -4; k <= 4; k++)
						world.setBlock(x + i, y - 1, z + k, Blocks.stonebrick);
				
				for (int i = -4; i <= 4; i++)//cealing stone
					for (int k = -4; k <= 4; k++)
						world.setBlock(x + i, y + height, z + k, Blocks.stonebrick);
				for (int i = -2; i < 3; i++)//cealing glass
					for (int k = -2; k < 3; k++)
						world.setBlock(x + i, y + height, z + k, Blocks.glass);
				
				for (int i = -4; i <= 4; i++)//cealing stone (cross)
					world.setBlock(x + i, y + height, z    , Blocks.stonebrick);
				for (int k = -4; k <= 4; k++)
					world.setBlock(x    , y + height, z + k, Blocks.stonebrick);
				
				//walls
				for (int i = 0; i <= height; i++)
					for (int k = -4; k <= 4; k++)
						world.setBlock(x + 4, y + i, z + k, Blocks.stonebrick);
				for (int i = 0; i <= height; i++)
					for (int k = -4; k <= 4; k++)
						world.setBlock(x - 4, y + i, z + k, Blocks.stonebrick);
				
				for (int i = 0; i <= height; i++)
					for (int k = -4; k <= 4; k++)
						world.setBlock(x + k, y + i, z + 4, Blocks.stonebrick);
				for (int i = 0; i <= height; i++)
					for (int k = -4; k <= 4; k++)
						world.setBlock(x + k, y + i, z - 4, Blocks.stonebrick);
				world.setBlock(x, y + 2, z + 3, Blocks.torch);
				world.setBlock(x, y + 2, z - 3, Blocks.torch);
				world.setBlock(x + 3, y + 2, z, Blocks.torch);
				world.setBlock(x - 3, y + 2, z, Blocks.torch);


			} else {
				return false;
			}

			// dostuff

			return true;

		} else {// if hand empty
			player.addChatMessage(new ChatComponentText("[NausicaaMod]Builds the only house type worth building"));
			player.addChatMessage(new ChatComponentText("[NM]will overwrite blocks in the 9x9 area, but not the inside!"));
			player.addChatMessage(new ChatComponentText("[NM]must be in stacks!, sort inventory before use for best results"));
			player.addChatMessage(new ChatComponentText("[NM]need: 16 glass, 4 torch, 5 stacks stone brick"));

		}
		return false;
	}
	


	
}
