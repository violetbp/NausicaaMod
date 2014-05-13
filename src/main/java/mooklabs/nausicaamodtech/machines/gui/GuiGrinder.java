package mooklabs.nausicaamodtech.machines.gui;

import mooklabs.nausicaamodtech.TechMain;
import mooklabs.nausicaamodtech.machines.container.ContainerGrinder;
import mooklabs.nausicaamodtech.machines.tileentity.TileEntityGrinder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;



public class GuiGrinder extends GuiContainer {
	public static final ResourceLocation texture = new ResourceLocation(TechMain.modid, "textures/gui/grinder.png");
	
	public TileEntityGrinder grinder;
	
	public GuiGrinder(InventoryPlayer invPlayer, TileEntityGrinder entity) {
		super(new ContainerGrinder(invPlayer, entity));
		
		this.grinder = entity;

		this.xSize = 176;
		this.ySize = 165;
	}
	
	@Override
	public void drawGuiContainerForegroundLayer(int par1, int par2){
		String s = this.grinder.hasCustomInventoryName() ? this.grinder.getInventoryName() : I18n.format(this.grinder.getInventoryName());
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 5, 4210752);
	}
	
	@Override
	public void drawGuiContainerBackgroundLayer(float f, int j, int i) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		
		Minecraft.getMinecraft().getTextureManager().
		bindTexture(texture);
		
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		int i1;

		if(this.grinder.hasPower()){
			i1 = this.grinder.getBurnTimeRemainingScaled(45);
			this.drawTexturedModalRect(guiLeft + 8, guiTop + 53 - i1, 176, 62 - i1, 16, i1);
		}
		
		i1 = this.grinder.getCookProgressScaled(24);
		this.drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 0, i1 + 1, 16);
	}
}
