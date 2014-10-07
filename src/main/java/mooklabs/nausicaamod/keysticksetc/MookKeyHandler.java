package mooklabs.nausicaamod.keysticksetc;

import mooklabs.nausicaamod.Main;
import mooklabs.nausicaamod.inventorytab.NausicaaGuiInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;

public class MookKeyHandler {

	/** Key index for easy handling */
	public static final int gliderToggleKey = 0;
	public static final int uKey = 1;
	// new KeyBinding("Glider Speed Increase", Keyboard.KEY_SPACE);
	public static final int dKey = 2;
	// new KeyBinding("Glider Speed Decrease", Keyboard.KEY_LSHIFT);
	public static final int lKey = 3;
	// new KeyBinding("Glider Speed To 0", Keyboard.KEY_LEFT);
	public static final int rKey = 4;
	// new KeyBinding("Glider Speed To Max", Keyboard.KEY_RIGHT);

	public static final int noclipKey = 5;
	// new KeyBinding("Activate NoClip", Keyboard.KEY_G);
	public static final int noclipoffKey = 6;
	// new KeyBinding("Deactivate NoClip", Keyboard.KEY_H);
	public static final int invkey = 7;

	public static boolean[] repeatings = new boolean[] { false, false, false, false, false, false };

	public static boolean rightKey = false;
	public static boolean leftKey = false;
	public static boolean upKey = false;
	public static boolean downKey = false;

	private EntityPlayer player;
	
	/** Key descriptions; use a language file to localize the description later */
	private static final String[] desc = { "key.nausicaa_something_toggle.desc", "key.nausicaa_glider_increase.desc", "key.nausicaa_glider_decrease.desc", "key.nausicaa_glider_minspeed.desc",
			"key.nausicaa_glider_maxspeed.desc", "key.nausicaa_glider_on.desc", "key.nausicaa_glider_off.desc", "invkey" };

	/** Default key values */
	private static final int[] keyValues = { Keyboard.KEY_G, Keyboard.KEY_P, Keyboard.KEY_P, Keyboard.KEY_P, Keyboard.KEY_P, Keyboard.KEY_C, Keyboard.KEY_X, Keyboard.KEY_I };

	private final KeyBinding[] keys;

	public static  boolean gliderOn = false;
	
	
	public MookKeyHandler() {
		keys = new KeyBinding[desc.length];
		for (int i = 0; i < desc.length; ++i) {
			keys[i] = new KeyBinding(desc[i], keyValues[i], "key.tutorial.category");
			ClientRegistry.registerKeyBinding(keys[i]);
		}
	}

	/**
	 * KeyInputEvent is in the FML package, so we must register to the FML event bus
	 */
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {
		if (FMLClientHandler.instance().getClient().inGameHasFocus && !FMLClientHandler.instance().isGUIOpen(GuiChat.class)) {
			player = Minecraft.getMinecraft().thePlayer;

			if (keys[rKey].isPressed()) rightKey = true;

			if (keys[lKey].isPressed()) leftKey = true;

			if (keys[uKey].isPressed()) upKey = true;

			if (keys[dKey].isPressed()) downKey = true;

			if (keys[invkey].isPressed())
			FMLNetworkHandler.openGui(player, Main.instance, Main.nausicaaTabGuiId, Minecraft.getMinecraft().theWorld, (int) player.posX, (int) player.posY, (int) player.posZ);

			if (keys[noclipKey].isPressed()) {
				gliderOn = true;
			}
			if (keys[noclipoffKey].isPressed()) {
				gliderOn = false;
			}
			

		}

	}

	// public static KeyBinding[] keyBindings = new KeyBinding[] { uKey, dKey, lKey, rKey, noclipKey, noclipoffKey};



}
