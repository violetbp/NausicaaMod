package mooklabs.nausicaamod.blocks;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class Brush extends Block {

    public Brush() {
	super(Material.leaves);
	setHardness(0F);
	setLightLevel(.6F);
        float f = 0.375F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
	setStepSound(soundTypeGrass);
	setBlockName("poisonBrush");
	setBlockTextureName(Main.itemfold + ":" + "poisonLeaves");  
	
	}
    @Override
    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;//can walk through block
    }
    @Override
    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;//not xray
    }
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;//not xray?
    }
    @Override
    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {

        return 6;//crop
    }
    @Override
    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        //if (Main.portalToxicBlock.tryToCreatePortal(par1World, par2, par3-1, par4))
        //CRIT        par1World.scheduleBlockUpdate(par2, par3, par4, this, this.tickRate(par1World) + par1World.rand.nextInt(10));
           
        
    }
    
    /**shouldent need it but i do:/
     * 
     * @return the block id
     */
    public Block getId(){
    	return this;//MAYBE remvove if not needed
    }

}
