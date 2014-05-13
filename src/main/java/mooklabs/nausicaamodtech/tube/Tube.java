package mooklabs.nausicaamodtech.tube;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.SpecialChars;
import mooklabs.nausicaamod.Main;
import mooklabs.nausicaamodtech.TechMain;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;


public class Tube extends Block implements IWailaDataProvider{

	
	public Tube() {
		super(Material.glass);
		this.setBlockName("tube");
		this.setBlockTextureName(TechMain.itemfold + ":tubeEnd");
	}
	
	
	
	@Override
    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if(entity instanceof EntityItem){
			double speed = .2;
			double entityMotionX=0, entityMotionY=0, entityMotionZ=0, entityPosX=x+.5, entityPosY=y+.66-entity.height, entityPosZ=z+.5; 
			switch(world.getBlockMetadata(x, y, z)){
				case 0:entityMotionX = -speed;
					break;
				case 1:entityMotionX = speed;
					break;
				case 2:entityMotionY = -(speed+.3);
					break;
				case 3:entityMotionY = (speed+.3);
					break;
				case 4:entityMotionZ = -speed;
					break;
				case 5:entityMotionZ = speed;
					break;
					
				}
			
			entity.motionY = entityMotionY;
			entity.motionX = entityMotionX;
			entity.motionZ = entityMotionZ;
			if(entityMotionX == 0)
			entity.posX = entityPosX;
			if(entityMotionY == 0)
			entity.posY = entityPosY;
			if(entityMotionZ == 0)
			entity.posZ = entityPosZ;

			//TechMain.printToPlayer("entity moved");
			
		}
		

		
	}
	
	@Override
	/**
	 * Called upon block activation (right click on the block.)
	 */
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
	int meta = world.getBlockMetadata(x, y, z);
	meta++;
	if(meta >5)
		meta=0;
	world.setBlockMetadataWithNotify(x, y, z, meta, 3);
	return true;
	}
		
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;//can pass through block
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
	
	/**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        return Item.getItemFromBlock(this);
    }
    
    /**mainly for waila, maybe for other things later
     * 
     * @param metadata
     * @return string of what direction
     */
    public String getDirectionOfPush(int metadata){
    	switch(metadata){
		case 0:  return "Negitive X";
		case 1:  return "Positive X";
		case 2:  return "Negitive Y";
		case 3:  return "Positive Y";
		case 4:  return "Negitive Z";
		case 5:  return "Positive Z";
		default: return "Error: messed up metadata";
			
		}
    }





	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return new ItemStack(this,accessor.getMetadata());
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		currenttip.add(this.getLocalizedName());
		return currenttip;
	}
	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		currenttip.add("Direction: " + itemStack.getItemDamage());
		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		currenttip.add( SpecialChars.BLUE + SpecialChars.ITALIC  + TechMain.name);
		return currenttip;
	}

	
}
