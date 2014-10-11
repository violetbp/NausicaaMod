package mooklabs.nausicaamod.tea;

import java.util.Random;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

/**
 * how do i make a tree
 * WILL BE ITEMBLOCK PROBLAY
 * @author emilynewman
 *
 *temporary will get random tea from block
 */

// look here:
// https://github.com/SlimeKnights/TinkersConstruct/blob/80efde613ac73e98279e1ab8adb1107638f1a0e4/src/main/java/tconstruct/world/blocks/OreberryBush.java

public class TeaBush extends BlockLeavesBase implements IPlantable {
	public String[] teaTypes;
	Random random;
	public TeaBush(String[] teaTypes) {
		super(Material.leaves, false);
		this.setBlockName("bush");
		this.setBlockTextureName(Main.itemfold + ":" + "bush");
		this.setTickRandomly(true);
		random = new Random();
		this.teaTypes = teaTypes;
		this.setHardness(0.3F);
		this.setStepSound
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

}
 