package mooklabs.nausicaamod.tea;

import java.util.Random;

import tconstruct.library.tools.AbilityHelper;
import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * how do i make a bush
 * WILL BE ITEMBLOCK PROBABLY
 * @author emilynewman
 *
 *temporary will get random tea from block
 */

// look here:
// https://github.com/SlimeKnights/TinkersConstruct/blob/80efde613ac73e98279e1ab8adb1107638f1a0e4/src/main/java/tconstruct/world/blocks/OreberryBush.java

public class TeaBush extends BlockLeavesBase implements IPlantable {
	public String[] teaTypes = {"Black", "Chamomile", "Green", "Matte", "Mint", "White"};
	Random random;
	//TODO VIC I DONT GET HOW THIS WORKS?? public int itemMeat;
	public TeaBush() {
		super(Material.leaves, false);
		//this.itemMeat = meta;
		this.setBlockName("bush");
		this.setBlockTextureName(Main.itemfold + ":" + "bush");
		this.setTickRandomly(true);
		random = new Random();
		this.setHardness(0.3F);
		this.setStepSound(Block.soundTypeGrass);
		this.setCreativeTab(Main.tabTea);
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return 0;
	}
/** I DONT GET THIS AT ALL
	 // Left-click harvests TEA 
    @Override
    public void onBlockClicked (World world, int x, int y, int z, EntityPlayer player)
    {
        if (!world.isRemote)
        {
            int meta = world.getBlockMetadata(x, y, z);
            if (meta >= 12)
            {
                world.setBlock(x, y, z, this, meta - 4, 3);
                AbilityHelper.spawnItemAtPlayer(player, new ItemStack(tea));
            }
        }
    }
    
	/* Right-click harvests berries 
    @Override
    public boolean onBlockActivated (World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        /*if (world.isRemote)
            return false;

        int meta = world.getBlockMetadata(x, y, z);
        if (meta >= 12)
        {
            if (world.isRemote)
                return true;

            world.setBlock(x, y, z, this, meta - 4, 3);
            AbilityHelper.spawnItemAtPlayer(player, new ItemStack(TinkerWorld.oreBerries, random.nextInt(3) + 1, meta % 4 + itemMeat));
            return true;
        }

        return false;
    }
    **/
    /* Render logic */

    @Override
    public boolean isOpaqueCube ()
    {
        return false;
    }

    public void setGraphicsLevel (boolean flag)
    {
        field_150121_P = flag;
    }

    @Override
    public boolean renderAsNormalBlock ()
    {
        return false;
    }
    /* may not be necessary?
    @Override
    public int getRenderType ()
    {
        return OreberryRender.model;
    }
    */

    @Override
    public boolean shouldSideBeRendered (IBlockAccess iblockaccess, int x, int y, int z, int meta)
    {
        if (meta > 7 || field_150121_P)
        {
            return super.shouldSideBeRendered(iblockaccess, x, y, z, meta);
        }
        else
        {
            return true;
        }
    }

    /* Bush growth */

    @Override
    public void updateTick (World world, int x, int y, int z, Random random1)
    {
        if (world.isRemote)
        {
            return;
        }

        if (random1.nextInt(20) == 0)// && world.getBlockLightValue(x, y, z) <= 8)
        {
            if (world.getFullBlockLightValue(x, y, z) < 10)
            {
                int meta = world.getBlockMetadata(x, y, z);
                if (meta < 12)
                {
                    world.setBlock(x, y, z, this, meta + 4, 3);
                }
            }
            /*else if (meta < 8)
            {
            	world.setBlock(x, y, z, blockID, meta + 4, 3);
            }*/
        }
    }

    public boolean canSustainPlant (World world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
    {
        if (plant instanceof TeaBush)
            return (world.getBlockMetadata(x, y, z) > 7);
        return super.canSustainPlant(world, x, y, z, direction, plant);
    }

    @Override
    public boolean canPlaceBlockAt (World world, int x, int y, int z)
    {
        if (world.getFullBlockLightValue(x, y, z) < 13)
            return super.canPlaceBlockAt(world, x, y, z);
        return false;
    }
    
}
 