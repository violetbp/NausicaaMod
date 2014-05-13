package mooklabs.nausicaamod.mobs.flying;

import java.util.ArrayList;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntitySpore extends EntityBat implements IMob {

	/**
	 * randomly selected ChunkCoordinates in a 7x6x7 box around the spore (y offset -2 to 4) towards which it will fly.
	 * upon getting close a new target will be selected
	 */
	private ChunkCoordinates currentFlightTarget;
	private World world;

	public EntitySpore(World par1World) {
		super(par1World);
		this.world = par1World;
		this.setSize(.03F, .03F);// TODO make accurate?
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(31, new Byte((byte) 0));
	}

	private ArrayList<Block> contaminatableBlocks = new ArrayList<Block>();// holds list of blocks that will look for
	{
		contaminatableBlocks.add(Blocks.log);
		contaminatableBlocks.add(Blocks.log2);
		contaminatableBlocks.add(Blocks.dirt);
		contaminatableBlocks.add(Blocks.grass);
		contaminatableBlocks.add(Blocks.sand);
		contaminatableBlocks.add(Blocks.tallgrass);// goes into brush
	}
	private ArrayList<Block> contaminatedBlocks = new ArrayList<Block>();// holds list of blocks that will look for
	{
		contaminatedBlocks.add(Main.poisonLog);
		contaminatedBlocks.add(Main.poisonLog);
		contaminatedBlocks.add(Main.poisonDirt);
		contaminatedBlocks.add(Main.poisonGrass);
		contaminatedBlocks.add(Main.poisonSand);
		contaminatedBlocks.add(Main.poisonBrush);

	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate() {

		int posX = (int) this.posX;
		int posY = (int) this.posY;
		int posZ = (int) this.posZ;
		super.onUpdate();
		Block currentBlock;
		Block currBlock;
		int a = 0, b = 0, c = 0;
		for (int i = 0; i < 6; i++) {

			switch (i) {// changes position of block its checking
			case 0:
				a = 1;
				b = 0;
				c = 0;
				break;
			case 1:
				a = -1;
				b = 0;
				c = 0;
				break;
			case 2:
				a = 0;
				b = 1;
				c = 0;
				break;
			case 3:
				a = 0;
				b = -1;
				c = 0;
				break;
			case 4:
				a = 0;
				b = 0;
				c = 1;
				break;
			case 5:
				a = 0;
				b = 0;
				c = -1;
				break;
			}

			currentBlock = this.world.getBlock(posX + a, posY + b, posZ + c);
			if (currentBlock != null) {
				for (int index = 0; index < contaminatableBlocks.size(); index++) {
					currBlock = contaminatableBlocks.get(index);
					if (currBlock == currentBlock) {
						world.setBlock(posX + a, posY + b, posZ + c, contaminatedBlocks.get(index));//set to corrisponding block

						this.setDead();// kills spore
					}
				}
			}
		}

	}

	// rendering code (class inside class)
	@SideOnly(Side.CLIENT)
	public static class RenderSpore extends RenderLiving {

		private static final ResourceLocation spore = new ResourceLocation(Main.itemfold, "/textures/mobs/spore.png");

		public RenderSpore(ModelBase par1ModelBase, float par2) {
			super(par1ModelBase, par2);
		}

		@Override
		protected ResourceLocation getEntityTexture(Entity entity) {
			return spore;
		}

	}
}
