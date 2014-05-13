package bcblocks.bettertable.table;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import bcblocks.bettertable.BetterTable;
import bcblocks.bettertable.tinkersstuff.NewContainerGui;

public class BetterTableGui extends NewContainerGui // TODO implements INEIGuiHandler
{

	public int page = 1;
	ContainerBetterTable tableContainer;
	public static final ResourceLocation better = new ResourceLocation(BetterTable.modid, "textures/gui/betterone.png");

	public BetterTableGui(InventoryPlayer inventoryplayer, ContainerBetterTable table, World world, int i, int j, int k) {
		super(table);
		tableContainer = table;
		this.ySize = 197;
	}

	@Override
	public void onGuiClosed() {
		
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		this.fontRendererObj.drawString(StatCollector.translateToLocal("\u00a76Device"), 120, 5, 0x404040);
		this.fontRendererObj.drawString(StatCollector.translateToLocal("\u00a76Crafting"), 116, 20, 0x404040);
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, this.guiLeft + this.xSize, this.guiTop, 40, 20, "up"));
		this.buttonList.add(new GuiButton(1, this.guiLeft + this.xSize, this.guiTop + 20, 40, 20, "down"));
		this.buttonList.add(new GuiButton(2, this.guiLeft + this.xSize, this.guiTop + 40, 50, 20, "printlist"));
		this.buttonList.add(new GuiButton(3, this.guiLeft + this.xSize, this.guiTop + 60, 50, 20, "disable draw"));

		this.drawPageNumberAndOtherStuff();

		// this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 - 14, 0x404040);
	}

	/**
	 * called when button is pressed <br>
	 * NEVER USE A SWITCH IT DOES NOT WORK<br>
	 * simply changes the craftgrid number
	 */
	@Override
	public void actionPerformed(GuiButton button) {

		if (button.id == 0 && this.page < 5) this.page++;
		if (button.id == 1 && this.page > 1) this.page--;
		if (button.id == 2) for (int i = 0; i < 125; i++)
			if (this.tableContainer.getSlot(i).getStack() != null) System.out.println(i + ": " + this.tableContainer.getSlot(i).getStack().getDisplayName());
		if (button.id == 3) this.drawScren = false;

		this.drawPageNumberAndOtherStuff();
	}

	/**
	 * changes the page<br>
	 * draws page number on screen
	 */
	private void drawPageNumberAndOtherStuff() {
		this.fontRendererObj.drawString(StatCollector.translateToLocal("Page " + this.page), 119, 84, 0x404040);
		this.tableContainer.setActivePage(this.page);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(better);
		int l = (width - xSize) / 2;
		int i1 = (height - ySize) / 2;
		drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
	}

}