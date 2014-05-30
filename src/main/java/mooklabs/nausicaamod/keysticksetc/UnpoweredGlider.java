package mooklabs.nausicaamod.keysticksetc;

import mooklabs.mookcore.Unused;
import mooklabs.nausicaamod.Main;
import mooklabs.nausicaamod.inventorytab.NPlayerStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MouseHelper;
import net.minecraft.util.Vec3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Glider uses a java timer instead of a tick handler :/
 * around half of this code is unused
 * 
 * @author victor
 */
public class UnpoweredGlider extends Thread {

	public static void startGlider() {
		mc = Minecraft.getMinecraft();
		UnpoweredGlider g = new UnpoweredGlider();
		g.start();
		Main.debugWrite("glider registered!");

	}

	/** Stores an instance of Minecraft for easy access */
	static protected Minecraft mc;

	UnpoweredGlider() {
		// mc = Minecraft.getMinecraft();

	}

	private EntityPlayer player;
	private EntityRenderer renderer;

	// vars(constants)
	private double maxSpeedSurv = 1.235;
	private double minSpeedSurv = 0; // .08;
	@Unused
	private float camRollAmount = 10;
	public double maxFuel = 10;

	// for the gliderspeed
	public static double thrust = 0;
	public static double counter = 0;
	/** slow down amount */
	public static double drag = .001;

	public double fuel;
	// changing
	private double maxSpeed = .9;
	private double minSpeed = .08;
	private boolean lastTickGliding = false;

	private boolean slowFromRotation = false;

	private int mouseMoveCounter = 0;
	private float mouseMovement = 0;
	private float mouseMovementModifyer = 2;

	@SideOnly(Side.CLIENT)
	/**
	 * gives magnitude of player speed vector
	 * 
	 * @param player
	 * @return the speed
	 */
	public double getSpeed(EntityPlayer player) {
		return Math.pow(Math.pow(player.motionX, 2) + Math.pow(player.motionY, 2) + Math.pow(player.motionZ, 2), 3);
	}

	// @SideOnly(Side.CLIENT)
	@Override
	public void run() {
		while (true) {
			// System.out.println("running");
			player = mc.thePlayer;
			if (player != null) {
				NPlayerStats.get(Minecraft.getMinecraft().thePlayer);
				if ((player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Main.glider && Main.enableGlider)
						|| (MookKeyHandler.gliderOn && NPlayerStats.get(player).armor.isStackInSlot(4))) {
					// System.out.println(player.getCurrentEquippedItem().itemID);

						

						if (this.lastTickGliding) {
							UnpoweredGlider.thrust = getSpeed(player) * 2;
							this.lastTickGliding = true;
						}

						// player.addChatMessage(new ChatComponentText("glider active!"));
						// tiltScreen();

						renderer = mc.entityRenderer;
						Vec3 desiredDirection = player.getLookVec().normalize();

						boolean nokeysdown = (MookKeyHandler.upKey && MookKeyHandler.downKey);// dont need stearing for this
						if (nokeysdown) {
							slowdown(); // if not accel, slow down
						} else {// check for what key and change movements
							if (player.capabilities.isCreativeMode) {
								maxSpeed = 30;
							} else {
								maxSpeed = maxSpeedSurv;
							}
							if (UnpoweredGlider.thrust > maxSpeed) UnpoweredGlider.thrust = maxSpeed;
						}// make creative go much faster

						if (MookKeyHandler.upKey && this.fuel <= 0) {
							if (UnpoweredGlider.thrust < maxSpeed) UnpoweredGlider.thrust += 0.01;
							this.fuel -= .01;
							UnpoweredGlider.counter = 1;
						}
						if (MookKeyHandler.downKey) {
							if (UnpoweredGlider.thrust > minSpeed) UnpoweredGlider.thrust -= 0.05;
							UnpoweredGlider.counter = 1;
						}/* if(MookKeyHandler.leftKey){ this.thrust = this.minSpeed; } if(MookKeyHandler.rightKey){ if (this.thrust < maxSpeed) this.thrust = this.maxSpeed/10;
						 * } */
						if (UnpoweredGlider.thrust < maxSpeed) UnpoweredGlider.thrust += (player.lastTickPosY - player.posY) / 5;

						if (UnpoweredGlider.thrust < .05) player.motionY -= .06;

						UnpoweredGlider.thrust = Math.abs(UnpoweredGlider.thrust);

						double vx = UnpoweredGlider.thrust * desiredDirection.xCoord;
						double vy = UnpoweredGlider.thrust * desiredDirection.yCoord;
						double vz = UnpoweredGlider.thrust * desiredDirection.zCoord;
						if (slowFromRotation) {
							vx *= .7;
							vy *= .7;
							vz *= .7;
						}
						player.motionX = vx;
						player.motionY = vy;
						player.motionZ = vz;

						UnpoweredGlider.thrust -= drag;
					}
				}

				else {// if not gliding
					player = mc.thePlayer;
					if (player != null) {
						// renderer = mc.entityRenderer;
						// MAYBE renderer.camRoll = 0;
						UnpoweredGlider.thrust = 0;
						this.lastTickGliding = false;
					
				}
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				Main.debugWrite("Glider Thread Interupted!");
			}

		}
	}

	int prevDeltaX;

	private void manageFuel() {
		EntityPlayer player = mc.thePlayer;
		if (this.fuel < this.maxFuel && player.inventory.consumeInventoryItem(Main.fuelCell)) this.fuel++;
		if (fuel == Math.floor(this.fuel))
		;
		Main.debugWrite("fuel: " + this.fuel);
	}

	@SideOnly(Side.CLIENT)
	/**
	 * this tilts the screen and makes you slow down(not yet)
	 */
	private void tiltScreen() {
		MouseHelper mouse = mc.mouseHelper;
		EntityRenderer renderer = mc.entityRenderer;
		// if (mouseMoveCounter == 5) {
		// TODO CAMROLL-renderer.camRoll = (float) (mouseMovement + renderer.prevCamRoll) / 2;
		mouseMoveCounter = 0;
		// }
		mouseMoveCounter++;
		mouseMovement = (mouseMovement + mouse.deltaX) / mouseMovementModifyer;

		if (mouse.deltaX > -1 || mouse.deltaX < 1) slowFromRotation = true;
		else slowFromRotation = false;

		/* MouseHelper mouse = mc.mouseHelper;
		 * EntityRenderer renderer = mc.entityRenderer;
		 * prevDeltaX = mouse.deltaX;
		 * if (mouseMoveCounter == 1) {
		 * renderer.camRoll = (float) (mouseMovement) / 2;
		 * mouseMoveCounter = 0;
		 * }
		 * mouseMovement = (float) ((mouse.deltaX + mouseMovementModifyer) / mouseMovementModifyer);
		 * //renderer.camRoll = (mouseMovement) / 2;
		 * // System.out.println(mouse.deltaX);
		 * // if(renderer.camRoll<mouse.deltaX)
		 * // renderer.camRoll = (float) smooth(mouse.deltaX,.5F);
		 * // if((mouse.deltaX < 10) && renderer.camRoll != 0)
		 * // renderer.camRoll -= 1;
		 * // if((-10 < mouse.deltaX) && renderer.camRoll != 0)
		 * // renderer.camRoll += 10;
		 * // mouseMoveCounter = 0;
		 * // }
		 * mouseMoveCounter++;
		 * // mouseMovement = (float) ((prevDeltaX + mouse.deltaX) / mouseMovementModifyer);
		 * /* if(mouse.deltaX>-1 || mouse.deltaX < 1) slowFromRotation = true; else slowFromRotation = false; */
	}

	public double getYThrust() {
		return player.motionY;// returns the y motion
	}

	@Unused
	public String getLabel() {
		return "NausicaaMod_Glider";
	}

	public void slowdown() {
		if (UnpoweredGlider.counter > .1) {// && this.thrust > minSpeed)
			UnpoweredGlider.counter -= .01;
			if (UnpoweredGlider.thrust > minSpeed) UnpoweredGlider.thrust -= .009;
		}
	}

	@Unused
	/**
	 * Sets the rotation of the entity not public in Entity.java
	 */
	protected void setYaw(float yaw) {
		player.rotationYaw = yaw % 360.0F;
	}

}
