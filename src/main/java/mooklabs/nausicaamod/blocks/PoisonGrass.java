package mooklabs.nausicaamod.blocks;

import java.util.Random;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PoisonGrass extends BlockGrass {

	@SideOnly(Side.CLIENT)
	private IIcon iconGrassTop;
	@SideOnly(Side.CLIENT)
	private IIcon iconSnowSide;
	@SideOnly(Side.CLIENT)
	private IIcon iconGrassBottom;
	@SideOnly(Side.CLIENT)
	private IIcon iconGrassSide;

	public PoisonGrass() {// TODO make more like grass,(spread etc)
		super();
		this.setHarvestLevel("shovel", 1);
		setHardness(0.6F);
		setStepSound(Block.soundTypeGrass);
		setBlockName("poisonGrass");
		setBlockTextureName("grass");

	}

	@Override
	public IIcon getIcon(int par1, int par2) {
		return par1 == 1 ? this.iconGrassTop : (par1 == 0 ? this.iconGrassBottom : this.iconGrassSide);
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
	 */
	public IIcon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		if (par5 == 1) {
			return this.iconGrassTop;
		} else if (par5 == 0) {
			return this.iconGrassBottom;
		} else {
			return this.iconGrassSide;
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 */
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.iconGrassSide = par1IconRegister.registerIcon(Main.itemfold + ":" + this.getTextureName() + "Side");
		this.iconGrassTop = par1IconRegister.registerIcon(Main.itemfold + ":" + this.getTextureName() + "Top");
		this.iconGrassBottom = par1IconRegister.registerIcon(Main.itemfold + ":" + "poisonDirt");

	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return Item.getItemFromBlock(Main.poisonDirt);
	}

	@Override
	/**
	 * Determines if this block can support the passed in plant, allowing it to be planted and grow.
	 * Some examples:
	 * @param world The current world
	 * @param x X Position
	 * @param y Y Position
	 * @param z Z position
	 * @param direction The direction relative to the given position the plant wants to be, typically its UP
	 * @param plant The plant that wants to check
	 * @return True to allow the plant to be planted/stay.
	 */
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
		return true;

	}

	/**
	 * Determines if this block can support the passed in plant, allowing it to be planted and grow.
	 * Some examples:
	 * Reeds check if its a reed, or if its sand/dirt/grass and adjacent to water
	 * Cacti checks if its a cacti, or if its sand
	 * Nether types check for soul sand
	 * Crops check for tilled soil
	 * Caves check if it's a colid surface
	 * Plains check if its grass or dirt
	 * Water check if its still water
	 * 
	 * @param world The current world
	 * @param x X Position
	 * @param y Y Position
	 * @param z Z position
	 * @param direction The direction relative to the given position the plant wants to be, typically its UP
	 * @param plant The plant that wants to check
	 * @return True to allow the plant to be planted/stay.
	 * 
	         public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
	 *         {
	 *         int plantID = plant.getPlantID(world, x, y + 1, z);
	 *         EnumPlantType plantType = plant.getPlantType(world, x, y + 1, z);
	 *         if (plantID == cactus.blockID && blockID == cactus.blockID)
	 *         {
	 *         return true;
	 *         }
	 *         if (plantID == reed.blockID && blockID == reed.blockID)
	 *         {
	 *         return true;
	 *         }
	 *         //next line has a private method in it //TODO make flowers become poisonous if planted on grass
	 *         //if (plant instanceof BlockFlower && ((BlockFlower)plant).canThisPlantGrowOnThisBlockID(blockID)) return true;
	 *         switch (plantType)
	 *         {
	 *         case Desert: return blockID == sand.blockID;
	 *         case Nether: return blockID == slowSand.blockID;
	 *         case Crop: return blockID == tilledField.blockID;
	 *         case Cave: return isBlockSolidOnSide(world, x, y, z, UP);
	 *         case Plains: return blockID == grass.blockID || blockID == dirt.blockID;
	 *         case Water: return world.getBlockMaterial(x, y, z) == Material.water && world.getBlockMetadata(x, y, z) == 0;
	 *         case Beach:
	 *         boolean isBeach = (blockID == Block.grass.blockID || blockID == Block.dirt.blockID || blockID == Block.sand.blockID);
	 *         boolean hasWater = (world.getBlockMaterial(x - 1, y, z ) == Material.water ||
	 *         world.getBlockMaterial(x + 1, y, z ) == Material.water ||
	 *         world.getBlockMaterial(x, y, z - 1) == Material.water ||
	 *         world.getBlockMaterial(x, y, z + 1) == Material.water);
	 *         return isBeach && hasWater;
	 *         }
	 *         return false;
	 */

}
