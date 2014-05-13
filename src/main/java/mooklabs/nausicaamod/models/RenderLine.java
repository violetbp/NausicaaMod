package mooklabs.nausicaamod.models;

import mooklabs.nausicaamod.Main;
import mooklabs.nausicaamodtech.builder.HologramUi;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;


public class RenderLine implements ISimpleBlockRenderingHandler{

	public RenderLine() {
		
		
	}
	/**
	 * Renders a torch at the given coordinates, with the base slanting at the given delta
	 */
	public static void renderLaser(double posX1, double posY1, double posZ1, double posX2, double posY2, double posZ2) {
		
			//NausicaaDebugWindow.overWrite(Double.toString());
		
			Tessellator tessellator = Tessellator.instance;
			IIcon icon = HologramUi.redTexture;

			double d5 = icon.getMinU();
			double d6 = icon.getMinV();
			double d7 = icon.getMaxU();
			double d8 = icon.getMaxV();
			
			double dx = 1D;
			double dy = 1D;
			double dz = 1D;

			//NausicaaDebugWindow.overWrite(Double.toString());

			//z constant
			tessellator.addVertexWithUV(posX1-dx, posY1+dy+3, posZ1, d5, d6);// left top
			tessellator.addVertexWithUV(posX1-dx, posY1-dy, posZ1, d5, d8);// left
			tessellator.addVertexWithUV(posX1+dx, posY1+dy+5, posZ1+7, d7, d6);// right top
			tessellator.addVertexWithUV(posX1+dx, posY1-dy, posZ1, d7, d8);// right
			
			//maybe use this somehow?GL11.glTexImage1D();
		
	}
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {		
	}
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}
	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}
	@Override
	public int getRenderId() {
		return Main.lineRenderId;
	}

}
