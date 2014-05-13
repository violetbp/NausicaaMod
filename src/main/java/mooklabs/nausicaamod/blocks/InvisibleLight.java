package mooklabs.nausicaamod.blocks;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**BECAUSE I CAN<br>
 * invisible light for people who dont like torches!
 * @author mooklabs
 *
 */
public class InvisibleLight extends Block {

	public InvisibleLight() {
		super(Material.leaves);
		this.setLightLevel(1);
		this.setBlockName("invisibleLight");
		this.setBlockTextureName(Main.itemfold + ":invisible");
		float start = .2F;
		float end =   .8F;
		this.setBlockBounds(start, start, start, end, end, end);
		//this.setLightOpacity(1);dono what this is
	}
	
    @Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;//can walk through block
    }
    @Override
    public boolean isOpaqueCube()
    {
        return false;//not xray
    }
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;//not xray?
    }
	

}









