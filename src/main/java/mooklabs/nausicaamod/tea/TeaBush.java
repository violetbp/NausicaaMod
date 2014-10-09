package mooklabs.nausicaamod.tea;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * how do i make a tree
 * WILL BE ITEMBLOCK PROBLAY
 * @author emilynewman
 *
 *temporary will get random tea from block
 */

// look here:
// https://github.com/SlimeKnights/TinkersConstruct/blob/80efde613ac73e98279e1ab8adb1107638f1a0e4/src/main/java/tconstruct/world/blocks/OreberryBush.java

public class TeaBush extends Block {

	public TeaBush() {
		super(Material.leaves);
		this.setBlockName("bush");
		this.setBlockTextureName(Main.itemfold + ":" + "bush");

	}

}
 