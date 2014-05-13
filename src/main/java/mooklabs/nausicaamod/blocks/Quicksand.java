package mooklabs.nausicaamod.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class Quicksand extends Block {

	public Quicksand() {
		super(Material.sand);
		setHarvestLevel("shovel", 1);
		setHardness(1.0F);
		setStepSound(soundTypeGravel);
		setBlockName("quicksand");
		setBlockTextureName(Main.itemfold + ":sporeSandBlue");

	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		return null;// can walk through block
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Called whenever an entity is walking on top of this block. Args: world, x, y, z, entity
	 */
	public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity entity) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		// if(entity.equals(player)){
		// player.motionX = 0;
		// player.motionY = -.1;
		// player.motionZ = 0;
		// }
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
	 */
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity entity) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		if (entity.equals(player) && !player.capabilities.isCreativeMode) {
			entity.motionX *= 0.5;
			entity.motionY = -.1;
			entity.motionZ *= 0.5;
			System.out.println(entity.fallDistance);
			if (entity.fallDistance > 4) entity.fallDistance -= 1;
			System.out.println(entity.fallDistance);

		}

	}

}
