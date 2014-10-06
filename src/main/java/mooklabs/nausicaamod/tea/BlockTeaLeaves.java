package mooklabs.nausicaamod.tea;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockTeaLeaves extends BlockLeaves {
	int[] field_150128_a;
    @SideOnly(Side.CLIENT)
    protected int field_150127_b;
    protected IIcon[][] field_150129_M = new IIcon[2][];
    private static final String __OBFID = "CL_00000263";

    public BlockTeaLeaves()
    {
        super();
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setHardness(0.2F);
        this.setLightOpacity(1);
        this.setStepSound(soundTypeGrass);
    }

	@Override
	public IIcon getIcon(int var1, int var2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] func_150125_e() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random p_149745_1_)
    {
        return 1;
    }
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
    	int choose = (int)Math.random();
    	if (choose == 1)
    		return Item.getItemFromBlock(Blocks.sapling); //Blocks.teaTreeSapling
    	else
    		return Item.getItemFromBlock(Blocks.sapling); //Blocks.teaLeaves
    }
}
