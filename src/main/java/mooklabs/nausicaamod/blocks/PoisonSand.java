package mooklabs.nausicaamod.blocks;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.BlockSand;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PoisonSand extends BlockSand {
    
    /** The type of tree this log came from. */
    public static final String[] sandType = new String[] {"Red", "Blue"};
    
    //@SideOnly(Side.CLIENT) server didnt like?
    private IIcon[] icons = new IIcon[sandType.length];
    
    public PoisonSand() {
		super();
	this.setHarvestLevel("shovel", 1);
	this.setBlockName("sporeSand");
	this.setBlockTextureName(Main.itemfold + ":" + "sporeSand" );
    }
    
    /**
     * returns a number between 0 and 1
     */
    public static int limitToValidMetadata(int par0)
    {
        return par0 & 1;
    }
    
    @Override
	@SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public IIcon getIcon(int par1, int par2)
    {
	try{
        return icons[par2];
	}catch(ArrayIndexOutOfBoundsException e){
	    System.out.println("[FatalErrorCought] Metadata or something");
	    }
	return icons[0];
    }
    
    //@SideOnly(Side.CLIENT)
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     *
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
    }*/

    @Override
	@SideOnly(Side.CLIENT)
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        for (int i = 0; i < PoisonSand.sandType.length; ++i)
        {
            this.icons[i] = par1IconRegister.registerIcon(this.getTextureName() + PoisonSand.sandType[i]);
            
        }
    }
}
