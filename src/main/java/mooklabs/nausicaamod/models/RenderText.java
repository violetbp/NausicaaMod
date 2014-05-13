package mooklabs.nausicaamod.models;

import org.lwjgl.opengl.GL11;

import mooklabs.nausicaamod.Main;
import mooklabs.nausicaamod.NausicaaDebugWindow;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderText extends Render implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		// Your rendering code
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		double d0 = .5D; // 0.4000000059604645D;
		double d1 = 0.5D - d0; // 0.5D - d0;
		double d2 = 0D;// 0.20000000298023224D;

		this.renderTextAtAngle(block, x - d1, y + d2, z, -d0, 0.0D, 0, 1);
		return true;

	}

	public boolean shouldRender3DInInventory() {
		// This is where it asks if you want the renderInventory part called or not.
		return false; // Change to 'true' if you want the Inventory render to be called.
		// TODO set to true and do what i have to for inv. render
	}

	@Override
	public int getRenderId() {
		// This is one place we need that renderId from earlier.
		return Main.textRenderId;
	}

	// ///////////////////
	/**
	 * Renders a torch at the given coordinates, with the base slanting at the given delta
	 */
	public void renderTextAtAngle(Block par1Block, double x, double y, double z, double par8, double par10, int par12, int growthAmount) {
		double deltaPosX = ((Minecraft.getMinecraft().thePlayer.posX) - x) / 2;
		double deltaPosZ = ((Minecraft.getMinecraft().thePlayer.posZ) - z) / 2;
		double delta = 0 + Math.hypot(deltaPosX, deltaPosZ);
		NausicaaDebugWindow.overWrite(Double.toString(delta));
		if (delta < 5) {
			Tessellator tessellator = Tessellator.instance;
			IIcon icon = this.getBlockIconFromSideAndMetadata(par1Block, 0, par12);

			double d5 = icon.getMinU();
			double d6 = icon.getMinV();
			double d7 = icon.getMaxU();
			double d8 = icon.getMaxV();
			x += 0.5D;
			z += 0.5D;
			double d19 = z - 0.5D;
			double d20 = z + 0.5D;
			double d21 = 0.0D;

			NausicaaDebugWindow.overWrite(Double.toString(delta));

			
			tessellator.addVertexWithUV(x - d21 - deltaPosZ, y + delta, d19 + deltaPosX, d5, d6);// left
			tessellator.addVertexWithUV(x - d21 - deltaPosZ, y + 0.0D, d19 + deltaPosX, d5, d8);// left
			tessellator.addVertexWithUV(x - d21 + deltaPosZ, y + 0.0D, d20 - deltaPosX, d7, d8);// right
			tessellator.addVertexWithUV(x - d21 + deltaPosZ, y + delta, d20 - deltaPosX, d7, d6);// right
			
			
		} else {}

			//Minecraft mc = Minecraft.getMinecraft();
			//this.renderText(tessellator, "sadsafsadfsafs", x, y, z, 64, delta);

		
	}

	public IIcon getBlockIconFromSideAndMetadata(Block par1Block, int par2, int par3) {
		return this.getIconSafe(par1Block.getIcon(par2, par3));
	}

	public IIcon getIconSafe(IIcon par1Icon) {
		if (par1Icon == null) {
			par1Icon = ((TextureMap) Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.locationBlocksTexture)).getAtlasSprite("missingno");
		}

		return par1Icon;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		// TODO Auto-generated method stub
		return false;
	}

	protected void renderText(Tessellator tessellator, String text, double x, double y, double z, int renderDist, double delta) {
		try{
			setRenderManager(RenderManager.instance);
		    GL11.glPushMatrix();
            int lines = 1;
			GL11.glTranslatef((float)x + 0.5F, (float)y + 1.2F, (float)z + 0.5F);
			GL11.glNormal3f(0, 1, 0);
			GL11.glRotatef(-renderManager.playerViewY, 0, 1, 0);
			GL11.glRotatef(renderManager.playerViewX, 1, 0, 0);
			GL11.glScalef(-0.016666668F, -0.016666668F, 0.016666668F);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDepthMask(false);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glDisable(GL11.GL_TEXTURE_2D);				
			tessellator.startDrawingQuads();
			tessellator.setColorRGBA_F(0, 0, 0, 0.25F);
			tessellator.addVertex(-renderManager.getFontRenderer().getStringWidth(text) / 2 - 1, -1, 0);
			tessellator.addVertex(-renderManager.getFontRenderer().getStringWidth(text) / 2 - 1, 2 + 8 * lines, 0);
			tessellator.addVertex(renderManager.getFontRenderer().getStringWidth(text) / 2 + 1, 2 + 8 * lines, 0);
			tessellator.addVertex(renderManager.getFontRenderer().getStringWidth(text) / 2 + 1, -1, 0);
			tessellator.draw();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			RenderManager.instance.getFontRenderer().drawString(text, -renderManager.getFontRenderer().getStringWidth(text) / 2, 0 , 553648127);		
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(true);
			RenderManager.instance.getFontRenderer().drawString(text, -renderManager.getFontRenderer().getStringWidth(text) / 2, 0, -1);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glColor4f(1, 1, 1, 1);
    
			GL11.glPopMatrix();
		}catch(IllegalStateException e){
			//System.err.println(e.getMessage());
		}
		

	}

	@Override
	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
		// TODO Auto-generated method stub

	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		// TODO Auto-generated method stub
		return null;
	}

}