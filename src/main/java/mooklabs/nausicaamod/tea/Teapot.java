package mooklabs.nausicaamod.tea;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * teapots are important for tea right?
 * 
 * here's number 2
 * @author emilynewman
 *
 */

public class Teapot extends ItemBlock {

	public Teapot() {
		super(Main.blockTeapot);
		setMaxStackSize(1);
	}
	
	//TODO make it not error-prone
	@Override
	public String getUnlocalizedName(ItemStack itemstack)
    {
		return "teapot";
         // return getUnlocalizedName() + "." + Teas.teaMap.get(itemstack.getItemDamage()).name;
    }
   
    public int getMetadata(int meta)
    {
          return meta;
    }
	
	

}
