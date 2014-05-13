package mooklabs.nausicaamod.godwarrior;

import mooklabs.nausicaamod.godwarrior.EntityGodWarrior.EnumActionType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GodWarriorControlGui extends GuiContainer {

	private static final ResourceLocation craftingTableGuiTextures = new ResourceLocation("textures/gui/container/crafting_table.png");

	public GodWarriorControlGui(Container container) {
		super(container);

	}

	/**
	 * called when button is pressed <br>
	 * NEVER USE A SWITCH IT DOES NOT WORK<br>
	 * changes god warrior's action
	 */
	@Override
	public void actionPerformed(GuiButton button) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		((GodWarriorControl) (player.getHeldItem().getItem())).setWarriorAction(EnumActionType.Attack);

		EnumActionType type = EnumActionType.Wait;

		if (button.id == 0) type = EnumActionType.Wait;
		if (button.id == 1) type = EnumActionType.Attack;
		if (button.id == 2) type = EnumActionType.Follow;
		if (button.id == 3) type = EnumActionType.Dig;

		((GodWarriorControl) (player.getHeldItem().getItem())).setWarriorAction(type);

	}

	@Override
	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items)
	 */
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		this.fontRendererObj.drawString(I18n.format("container.control", new Object[0]), 8, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);

		this.buttonList.clear();
		int i = 0;
		for (EntityGodWarrior.EnumActionType val : EntityGodWarrior.EnumActionType.values()) {
			this.buttonList.add(new GuiButton(i, this.guiLeft + this.xSize / 4, this.guiTop + i++ * 20, 100, 20, val.getStringWithSpace() + " $:" + val.cost));

		}

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(craftingTableGuiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}

}
