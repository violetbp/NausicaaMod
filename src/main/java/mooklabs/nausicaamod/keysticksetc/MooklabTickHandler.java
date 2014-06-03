package mooklabs.nausicaamod.keysticksetc;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class MooklabTickHandler {

	/** Stores an instance of Minecraft for easy access */
	protected Minecraft mc;

	// create a constructor that takes a Minecraft argument; now we have it
	// whenever we need it
	public MooklabTickHandler(Minecraft mc) {
		this.mc = mc;
	}

	@SubscribeEvent
	private void tickStartChecker(ServerTickEvent event) {
		if (event.phase == Phase.START) {
			tickStart();

		}
	}

	@SubscribeEvent
	private void tickEndChecker(ClientTickEvent event) {
		if (event.phase == Phase.END) {
			tickEnd();
		}
	}
	abstract void tickEnd();
	abstract void tickStart();

}