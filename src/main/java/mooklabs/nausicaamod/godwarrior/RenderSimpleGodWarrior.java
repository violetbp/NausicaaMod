package mooklabs.nausicaamod.godwarrior;

import mooklabs.nausicaamod.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSimpleGodWarrior extends RenderLiving {

	/**
	 * texture for mob
	 */
	private static final ResourceLocation godWarriorTex = new ResourceLocation(Main.itemfold, "/textures/mobs/dragonfly.png");

	public RenderSimpleGodWarrior(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);

	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return godWarriorTex;
	}

	/**
	 * texture for beam<br>
	 * used part of the enderdragons beam
	 */
	private static final ResourceLocation enderDragonCrystalBeamTextures = new ResourceLocation("textures/entity/endercrystal/endercrystal_beam.png");

	@Override
	/**
	 * added laser to rendering
	 */
	public void doRender(EntityLiving entityLiving, double x, double y, double z, float par8, float par9) {
		//acctually renders the entity
		super.doRender((EntityLiving) entityLiving, x, y, z, par8, par9);
		
		//ya used later
		float hightOfBeamRelitiveToFeetOfAttackingEntity = 2F;
		
		//Below this renders beam
		/**
		 *  WHAT/WHO THE BEAM POINTS AT<br>
		 *  substitue with however your mob decides on the best entity to attack
		 */
		Entity entity = ((EntityGodWarrior) (entityLiving)).entityToAttack;

		//make sure there is an entity
		//may want to add another few conditions here
		if (entity != null ) {
			float f2 = par9;// (float) player.innerRotation + par9;
			float f3 = MathHelper.sin(f2 * 0.2F) / 2.0F + 0.5F;
			f3 = (f3 * f3 + f3) * 0.2F;
			//set stuff to point at entity(I just expremented until it worked)
			//xPos
			float xPosEnd = (float) (entity.posX - entityLiving.posX - (entityLiving.prevPosX - entityLiving.posX) * (double) (1.0F - par9));
			//yPos
			float yPosEnd = (float) ((double) f3 + entity.posY - 1.0D - entityLiving.posY - (entityLiving.prevPosY - entityLiving.posY) * (double) (1.0F - par9));
			//zPos
			float zPosEnd = (float) (entity.posZ - entityLiving.posZ - (entityLiving.prevPosZ - entityLiving.posZ) * (double) (1.0F - par9));
			
			//for rotation/distance
			float f7 = MathHelper.sqrt_float(xPosEnd * xPosEnd + zPosEnd * zPosEnd);
			float f8 = MathHelper.sqrt_float(xPosEnd * xPosEnd + yPosEnd * yPosEnd + zPosEnd * zPosEnd);
			// Start drawing (i think)
			GL11.glPushMatrix();
			//MOVES START OF TESSSELATOR to right location
			GL11.glTranslatef((float) x, (float) y + hightOfBeamRelitiveToFeetOfAttackingEntity, (float) z);
			GL11.glRotatef((float) (-Math.atan2((double) zPosEnd, (double) xPosEnd)) * 180.0F / (float) Math.PI - 90.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef((float) (-Math.atan2((double) f7, (double) yPosEnd)) * 180.0F / (float) Math.PI - 90.0F, 1.0F, 0.0F, 0.0F);
			Tessellator tessellator = Tessellator.instance;
			//Makes the beam look nice
			RenderHelper.disableStandardItemLighting();
			GL11.glDisable(GL11.GL_CULL_FACE);
			//makes it have a texture
			this.bindTexture(enderDragonCrystalBeamTextures);
			//Shading?
			GL11.glShadeModel(GL11.GL_SMOOTH);
			
			//from what i can tell f9 and f10 make the beam change (looks like its rotating or whatnot)
			float f9 = 0.0F - ((float) entityLiving.ticksExisted + par9) * 0.01F;
			float f10 = MathHelper.sqrt_float(xPosEnd * xPosEnd + yPosEnd * yPosEnd + zPosEnd * zPosEnd) / 32.0F - ((float) entityLiving.ticksExisted + par9) * 0.01F;

			// change draw mode
			tessellator.startDrawing(5);
			byte b0 = 8;// how many sides the circle has??

			for (int i = 0; i <= b0; ++i) {// makes one side circular(fancy math stuff)
				float f11 = MathHelper.sin((float) (i % b0) * (float) Math.PI * 2.0F / (float) b0) * 0.75F;//changing the 2 or .75 should change size of circle
				float f12 = MathHelper.cos((float) (i % b0) * (float) Math.PI * 2.0F / (float) b0) * 0.75F;
				float f13 = (float) (i % b0) * 1.0F / (float) b0;
				tessellator.setColorOpaque_I(0);
				tessellator.addVertexWithUV((double) (f11 * 0.2F), (double) (f12 * 0.2F), 0.0D, (double) f13, (double) f10);
				tessellator.setColorOpaque_I(16777215);
				tessellator.addVertexWithUV((double) f11, (double) f12, (double) f8, (double) f13, (double) f9);
			}
			// draw added verticies
			tessellator.draw();
			// reset openGl stuff?
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glShadeModel(GL11.GL_FLAT);
			RenderHelper.enableStandardItemLighting();
			// Stop drawing (i think)
			GL11.glPopMatrix();
		}

	}

}