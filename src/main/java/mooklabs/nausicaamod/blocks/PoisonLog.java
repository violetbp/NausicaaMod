package mooklabs.nausicaamod.blocks;

import java.util.List;
import java.util.Random;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PoisonLog extends BlockLog{

    /** The type of tree this log came from. */
   // public static final String[] woodType = new String[] {"","","",""};
    @SideOnly(Side.CLIENT)
    private IIcon tree_side;
    @SideOnly(Side.CLIENT)
    private IIcon tree_top;
    
    
    public PoisonLog() {
	super();
      //  this.setCreativeTab(CreativeTabs.tabBlock);
        setHardness(2.0F);
        setStepSound(soundTypeWood);
        setBlockName("poisonLog");
        setBlockTextureName("poisonLog");
        }
    //@Override unneedde and doesent work
    public Item itemDropped(int par1, Random par2Random, int par3)
    {
        return Item.getItemFromBlock(Main.poisonLog);
    }
    @Override
    @SideOnly(Side.CLIENT)
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     * since im extending log I need this or poison log shows up 4 time, I will also probly need to add more wood types
     */
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        
    }
    
    @Override
    @SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		this.tree_side = iconRegister.registerIcon(Main.itemfold + ":" + this.getTextureName() +"_side");
		this.tree_top  = iconRegister.registerIcon(Main.itemfold + ":" + this.getTextureName() +"_top");
    	}
    @Override
    @SideOnly(Side.CLIENT)
    /** From the specified side and block metadata retrieves the blocks texture. Args: side, metadata*/
    public IIcon getIcon(int par1, int par2)
    	{
    	    if(par1 == 1 || par1 == 0) //1 is top, 0 is bottom
    		return this.tree_top;
    	    return this.tree_side;
    	}

}
