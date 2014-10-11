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

	/* implement later
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons (IIconRegister iconRegister)
    {
        this.fastIcons = new IIcon[textureNames.length];
        this.fancyIcons = new IIcon[textureNames.length];

        for (int i = 0; i < this.fastIcons.length; i++)
        {
            if (textureNames[i] != "")
            {
                this.fastIcons[i] = iconRegister.registerIcon("tinker:crops/" + textureNames[i] + "_fast");
                this.fancyIcons[i] = iconRegister.registerIcon("tinker:crops/" + textureNames[i] + "_fancy");
            }
        }
    }
    */
	/*also implement later
	 * @Override
    public IIcon getIcon (int side, int metadata)
    {
        this.setGraphicsLevel(Minecraft.getMinecraft().gameSettings.fancyGraphics);
        if (field_150121_P)
        {
            if (metadata < 12)
            {
                return fancyIcons[metadata % 4];
            }
            else
            {
                return fancyIcons[metadata % 4 + 4];
            }
        }
        else
        {
            if (metadata < 12)
            {
                return fastIcons[metadata % 4];
            }
            else
            {
                return fastIcons[metadata % 4 + 4];
            }
        }
    }

    // Bushes are stored by size then type 
    @Override
    public int damageDropped (int metadata)
    {
        return metadata % 4;
    }

    // The following methods define a berry bush's size depending on metadata 
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool (World world, int x, int y, int z)
    {
        int l = world.getBlockMetadata(x, y, z);
        if (l < 4)
        {
            return AxisAlignedBB.getBoundingBox((double) x + 0.25D, y, (double) z + 0.25D, (double) x + 0.75D, (double) y + 0.5D, (double) z + 0.75D);
        }
        else if (l < 8)
        {
            return AxisAlignedBB.getBoundingBox((double) x + 0.125D, y, (double) z + 0.125D, (double) x + 0.875D, (double) y + 0.75D, (double) z + 0.875D);
        }
        else
        {
            return AxisAlignedBB.getBoundingBox(x + 0.0625, y, z + 0.0625, (double) x + 0.9375D, (double) y + 0.9375D, (double) z + 0.9375D);
        }
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool (World world, int x, int y, int z)
    {
        int l = world.getBlockMetadata(x, y, z);
        if (l < 4)
        {
            return AxisAlignedBB.getBoundingBox((double) x + 0.25D, y, (double) z + 0.25D, (double) x + 0.75D, (double) y + 0.5D, (double) z + 0.75D);
        }
        else if (l < 8)
        {
            return AxisAlignedBB.getBoundingBox((double) x + 0.125D, y, (double) z + 0.125D, (double) x + 0.875D, (double) y + 0.75D, (double) z + 0.875D);
        }
        else
        {
            return AxisAlignedBB.getBoundingBox(x, y, z, (double) x + 1.0D, (double) y + 1.0D, (double) z + 1.0D);
        }
    }

    @Override
    public void setBlockBoundsBasedOnState (IBlockAccess iblockaccess, int x, int y, int z)
    {
        int md = iblockaccess.getBlockMetadata(x, y, z);

        float minX;
        float minY = 0F;
        float minZ;
        float maxX;
        float maxY;
        float maxZ;

        if (md < 4)
        {
            minX = minZ = 0.25F;
            maxX = maxZ = 0.75F;
            maxY = 0.5F;
        }
        else

        if (md < 8)
        {
            minX = minZ = 0.125F;
            maxX = maxZ = 0.875F;
            maxY = 0.75F;
        }

        else
        {
            minX = minZ = 0.0F;
            maxX = maxZ = 1.0F;
            maxY = 1.0F;
        }
        setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
    }
	 */
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
 