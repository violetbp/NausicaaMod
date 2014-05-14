package mooklabs.nausicaamod.keysticksetc;

import mooklabs.mookcore.Unused;
import mooklabs.nausicaamod.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MouseHelper;
import net.minecraft.util.Vec3;

public class BasicGlider extends MooklabTickHandler {

	private EntityPlayer player;
	private EntityRenderer renderer;

	// vars(constants)
	private double maxSpeedSurv = .9;
	private double minSpeedSurv = .08;
	private float camRollAmount = 10;

	// for the gliderspeed
	public static double thrust = 0;
	public static double counter = 0;

	// changeing
	private double maxSpeed = .9;
	private double minSpeed = .08;

	private boolean slowFromRotation = false;

	private int mouseMoveCounter = 0;
	private float mouseMovement = 0;
	private float mouseMovementModifyer = 2;

	public BasicGlider(Minecraft mc) {
		super(mc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tickStart() {
	}// nothing

	@Override
	public void tickEnd() {
		player = Minecraft.getMinecraft().thePlayer;
		if (player != null) {
			if (player.getCurrentEquippedItem() != null) {
				// System.out.println(player.getCurrentEquippedItem().itemID);

				if (player.getCurrentEquippedItem().getItem() == Main.glider && Main.enableGlider) {
					// player.addChatMessage("glider active!");
					tiltScreen();

					renderer = Minecraft.getMinecraft().entityRenderer;
					double downforce = .5-BasicGlider.thrust/10;
					if(downforce<0)downforce = 0;
					Vec3 desiredDirection = player.getLookVec().addVector(0, downforce, 0).normalize();
					// SPeed? System.out.println(player.getLookVec().lengthVector());

					boolean nokeysdown = (MookKeyHandler.upKey && MookKeyHandler.downKey);// dont need stearing for this
					if (nokeysdown) {
						slowdown(); // if not accel, slow down
					} else {// check for what key and change movements
						if (player.capabilities.isCreativeMode) {
							maxSpeed = 30;
							minSpeed = 0;
						} else {
							maxSpeed = maxSpeedSurv;
							minSpeed = minSpeedSurv;
							if (BasicGlider.thrust > maxSpeed) BasicGlider.thrust = maxSpeed;
						}// make creative go much faster

						if (MookKeyHandler.upKey) {
							if (BasicGlider.thrust < maxSpeed) BasicGlider.thrust += 0.01;
							BasicGlider.counter = 1;
							//TODO lower fuel
						}
						if (MookKeyHandler.downKey) {
							if (BasicGlider.thrust > minSpeed) BasicGlider.thrust -= 0.01;
							BasicGlider.counter = 1;
						}
						if (MookKeyHandler.leftKey) {
							BasicGlider.thrust = this.minSpeed;
						}
						if (MookKeyHandler.rightKey) {
							if (BasicGlider.thrust < maxSpeed) BasicGlider.thrust = this.maxSpeed / 10;
						}

						double vx = BasicGlider.thrust * desiredDirection.xCoord;
						double vy = BasicGlider.thrust * desiredDirection.yCoord;
						double vz = BasicGlider.thrust * desiredDirection.zCoord;
						if (slowFromRotation) {
							vx *= .7;
							vy *= .7;
							vz *= .7;
						}
						player.motionX = vx;
						player.motionY = vy;
						player.motionZ = vz;
						// NOPE System.out.println( Math.pow(vx*vx+vy*vy+vz*vz,1/3));//(double)Math.pow(Math.abs(Math.pow(vx,2)+Math.pow(vy,2)+Math.pow(vz,2)),1/2));

					}
				}

				else {// if not glideing
					player = Minecraft.getMinecraft().thePlayer;
					if (player != null) {
						renderer = Minecraft.getMinecraft().entityRenderer;
						//renderer.camRoll = 0;
					}
				}
			}
		}
	}

	int prevDeltaX;

	/**
	 * this tilts the screen and makes you slow down(not yet)
	 */
	private void tiltScreen() {
		MouseHelper mouse = Minecraft.getMinecraft().mouseHelper;
		EntityRenderer renderer = Minecraft.getMinecraft().entityRenderer;

		prevDeltaX = mouse.deltaX;

		// if (mouseMoveCounter == 1) {
		// renderer.camRoll = (float) (mouseMovement + renderer.prevCamRoll) / 2;
		// }
		// mouseMovement = (float) ((prevDeltaX + mouse.deltaX) / mouseMovementModifyer);
		// renderer.camRoll = (mouseMovement+ prevDeltaX)/2;

		// System.out.println(mouse.deltaX);

		// if(renderer.camRoll<mouse.deltaX)
		// renderer.camRoll = (float) smooth(mouse.deltaX,.5F);
		// if((mouse.deltaX < 10) && renderer.camRoll != 0)
		// renderer.camRoll -= 1;

		// if((-10 < mouse.deltaX) && renderer.camRoll != 0)
		// renderer.camRoll += 10;

		// mouseMoveCounter = 0;
		// }
		// mouseMoveCounter++;
		// mouseMovement = (float) ((prevDeltaX + mouse.deltaX) / mouseMovementModifyer);
		/*if(mouse.deltaX>-1 || mouse.deltaX < 1) slowFromRotation = true; else slowFromRotation = false; */
	}

	private float a;
	private float b;
	private float c;

	/**
	 * Smooths mouse input
	 */
	// TODO finish this
	public float smooth(float par1, float par2) {
		this.a += par1;
		par1 = (this.a - this.b) * par2;
		this.c += (par1 - this.c) * 0.5F;

		if (par1 > 0.0F && par1 > this.c || par1 < 0.0F && par1 < this.c) {
			par1 = this.c;
		}

		this.b += par1;
		return par1;
	}

	
	@Unused
	public String getLabel() {
		return "NausicaaMod_Glider";
	}

	public void slowdown() {
		if (BasicGlider.counter > .1) {// && this.thrust > minSpeed)
			BasicGlider.counter -= .01;
			if (BasicGlider.thrust > minSpeed) BasicGlider.thrust -= .005;
		}
	}

	/**
	 * Sets the rotation of the entity not public in Entity.java
	 */
	protected void setYaw(float yaw) {
		player.rotationYaw = yaw % 360.0F;
	}

}