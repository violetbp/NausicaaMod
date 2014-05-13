package mooklabs.nausicaamod;

import java.util.List;
import java.util.Set;

import mooklabs.nausicaamod.options.NausicaaGuiOptionsRowList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

import com.google.common.collect.ImmutableSet;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.IModGuiFactory;

public class NausicaaConfigGuiFactory implements IModGuiFactory {

	public static class NausicaaConfigGuiScreen extends GuiScreen {

	    private GuiListExtended optionsRowList;
	    private static final GameSettings.Options[] videoOptions = new GameSettings.Options[] {GameSettings.Options.GRAPHICS};
	    private static final NausicaaGameSettings.Options[] videOptions = new NausicaaGameSettings.Options[] {NausicaaGameSettings.Options.INVERT_MOUE, NausicaaGameSettings.Options.INVERT_MOUSE};

		private GuiScreen parent;

		public NausicaaConfigGuiScreen(GuiScreen parent) {
			this.parent = parent;
		}

		/**
		 * Adds the buttons (and other controls) to the screen in question.
		 */
		@SuppressWarnings("unchecked")
		@Override
		public void initGui() {
			this.buttonList.add(new   GuiButton(1, this.width / 2 - 100, this.height - 26, I18n.format("Done")));
			this.buttonList.add(new   GuiOptionButton(2, this.width / 2 - 75, this.height - 68, I18n.format(Main.enableGlider ? "Glider Off" : "Glider On")));
	        this.optionsRowList = new NausicaaGuiOptionsRowList(this.mc, this.width, this.height, 32, this.height - 32, 25, videOptions);

		}

		@Override
		protected void actionPerformed(GuiButton guiButton) {
			if (guiButton.enabled) switch (guiButton.id) {
			case 1:
				FMLClientHandler.instance().showGuiScreen(parent);
				break;
			case 2:if(Main.enableGlider) Main.enableGlider = false; 
					else Main.enableGlider = true;
				break;
			case 3:
				break;
			case 4:
				break;
			}

		}

		/**
		 * Draws the screen and all the components in it.
		 */
		@Override
		public void drawScreen(int par1, int par2, float par3) {
			this.drawDefaultBackground();
	        this.optionsRowList.drawScreen(par1, par2, par3);

			this.drawCenteredString(this.fontRendererObj, "Nausicaa Mod Config Screen", this.width / 2, 13, 0xFFFFFF);
			super.drawScreen(par1, par2, par3);
		}

	}

	private Minecraft minecraft;

	@Override
	public void initialize(Minecraft minecraftInstance) {
		this.minecraft = minecraftInstance;
	}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return NausicaaConfigGuiScreen.class;
	}
	//next was private
	public static final Set<RuntimeOptionCategoryElement> nausicaaCategories = ImmutableSet.of(new RuntimeOptionCategoryElement("Help", "Nausicaa"));

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return nausicaaCategories;
	}

	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
		return new RuntimeOptionGuiHandler() {

			@Override
			public void paint(int x, int y, int w, int h) {
				System.out.println("painting");

			}

			@Override
			public void close() {
				System.out.println("closing");

			}

			@Override
			public void addWidgets(List<Gui> widgets, int x, int y, int w, int h) {
				widgets.add(new GuiButton(104, x + 30, y + 10, "HELLO"));
				System.out.println("adding Widgets");

			}

			@Override
			public void actionCallback(int actionId) {
				System.out.println("Triggered" + actionId);
			}
		};
	}

}