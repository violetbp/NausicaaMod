package mooklabs.nausicaamodtech.builder;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mooklabs.mookcore.Unused;
import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;


public class HologramUi extends Block {
	@Unused
	/**ThisWILL  BE the texture for the laser<br> I don't know how to define an icon not in a block class, and this on existed allready so......*/
	@SideOnly(Side.CLIENT)
	public static IIcon redTexture;

		public HologramUi() {
			super(Material.iron);
			this.setBlockName("hologramUi");
			this.setBlockTextureName(Main.itemfold + ":gliderBuilder_text");
	        float f = 0.375F; this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f, 0.5F + f);

		}
		@Override
	    public int getRenderType()
	    {
			//render id :/
	        return Main.textRenderId;
	    }

		/**
	     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	     */
	    @Override
		public boolean isOpaqueCube()
	    {
	        return false;
	    }/**
	     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	     */
	    @Override
		public boolean renderAsNormalBlock()
	    {
	        return false;
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
		
	    /**
	     * not for this block
	     */
	    @Override
		@SideOnly(Side.CLIENT)
		public void registerBlockIcons(IIconRegister iconRegister) {
			this.blockIcon = iconRegister.registerIcon(this.getTextureName());
			
			
	    }
	    
	    
	}
