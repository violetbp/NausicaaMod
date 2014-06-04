package mooklabs.mookcore.toolsandarmor;

import java.util.ArrayList;

import mooklabs.laputamod.LapMain;
import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NArmor extends ItemArmor {

	/**
	 * The type of armor in a string format.<br>
	 * First letter is caps, can be: Helmet, Chestplate, Legs, Boots
	 */
	String typeStr;
	String armorMaterial;

	private ArrayList<Block> block = new ArrayList<Block>();// holds list of
	// blocks that will
	// look for
	{
		block.add(Main.poisonLeaves);
		block.add(Main.poisonLog);
	}

	int armorSlot = 3;// slot to check for armor, 3 is helmet

	// Stores the armor type: 0 is helmet, 1 is plate, 2 is legs and 3 is boots
	public NArmor(ArmorMaterial armorMaterial, int renderType, int kindOfArmor) {

		super(armorMaterial, 1, kindOfArmor);// calls superconstructor

		this.armorMaterial = armorMaterial + "";// sets the armor material type for world rendering

		switch (kindOfArmor) { // sets a string for armor type for less coding later
		case 0:
			typeStr = "Helmet";// since it allready calls for an int val for type, one less thing to pass
			break;
		case 1:
			typeStr = "Chestplate";
			break;
		case 2:
			typeStr = "Legs";
			break;
		case 3:
			typeStr = "Boots";
			break;
		default:
			typeStr = "Error: armor type must be between 0 and 3 inclusive";
			LapMain.logger.error(typeStr);
		}
		setUnlocalizedName(armorMaterial + typeStr);
		setCreativeTab(Main.tabCombat);
		if (!armorMaterial.equals(LapMain.weakVoluciteArmor)) setTextureName(Main.itemfold + ":" + armorMaterial + typeStr);// render armor in ui
		else setTextureName(LapMain.itemfold + ":" + armorMaterial + typeStr);// render armor in ui
		// CRIT when we get textures need to change this
		// itemfolder + : + armor material +armor type ie "endcraft:crystalArmorHelmet"
	}

	// CRIT @Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {// renders armor in world
		if (stack.toString().toLowerCase().contains("legs")) return Main.itemfold + ":textures/items/" + armorMaterial + "_2.png";// i messed around with this until it worked,
		// my leggings are called "legs"
		if (stack.toString().contains("Legging")) return Main.itemfold + ":textures/items/" + armorMaterial + "_2.png";// leggings, (i dont think this includes boots)
		return Main.itemfold + ":textures/items/" + armorMaterial + "_1.png";// chestplate,helmet
		// return null;
	}

	/* //TODO remove later
	 * @SideOnly(Side.CLIENT) public void addInformation(ItemStack itemStack, EntityPlayer player, List dataList, boolean bool){
	 * if(armorMaterial.equals("hardenedGlowyArmor")){ dataList.add("You are basicly invincible now"); dataList.add("Acheivement geet OP armor?"); } } */
	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
		EntityPlayer player = (EntityPlayer) entity;
		if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == this) {
			player.addPotionEffect((new PotionEffect(Potion.poison.getId(), 0, 1)));
		} else {
			player.curePotionEffects(itemstack);
		}
	}

	@SideOnly(Side.CLIENT)
	public void findBlock(int x, int y, int z, int radius) {
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		World world = mc.theWorld;

		int blocksBelow = 3;// scans __ blocks below/above player
		int blocksAbove = 1;
		x -= radius;
		y -= blocksBelow;
		z -= radius;
		for (int k = 0; k < blocksBelow + blocksAbove; k++)// y-val (from one
			// block below to one
			// above)
		{
			for (int j = 0; j < radius * 2; j++)// z-val
			{
				for (int i = 0; i < radius * 2; i++)// x-val
				{
					Block currblockid = world.getBlock(x + i, y + k, z + j);

					for (Block currtempblock : block) {

						if (currtempblock == currblockid) {
							if (player.getCurrentArmor(armorSlot) == null)
								// System.out.println(player.getCurrentArmor(armorSlot));

								if (player.getCurrentArmor(armorSlot) == null) {
									player.addPotionEffect(new PotionEffect(Potion.poison.getId(), 7 * 20, 0));
									player.addPotionEffect(new PotionEffect(Potion.weakness.getId(), 7 * 20, 0));

								} else if (player.getCurrentArmor(armorSlot).getItem() != Main.ceramicHelmet) {

									// System.out.println(player.getCurrentArmor(armorSlot));
									player.addPotionEffect(new PotionEffect(Potion.poison.getId(), 7 * 20, 1));
									player.addPotionEffect(new PotionEffect(Potion.weakness.getId(), 7 * 20, 1));

								} else
									;
							player.curePotionEffects(new ItemStack(Items.milk_bucket));

							return;
						}// *20 is for the 20ticks/sec cycle

					}
				}
			}
		}
		player.curePotionEffects(new ItemStack(Items.milk_bucket));// cure effects if away from
	}

	// private double[] prevMotion;
	AttributeModifier mod = new AttributeModifier("armorSpeedUp", 1, 1);
	boolean bla = false;

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemstack) {
		// Main.debugWrite("armorTick");

		if (this.armorMaterial.equals("special")) {
			switch (this.armorType) {
			case 0:// helmet
				// FMLCommonHandler.instance().firePlayerLoggedIn(player);
				break;
			case 1:// chestplate
				break;
			case 2:// legs

				//player.capabilities.setPlayerWalkSpeed(.3F);

				break;
			case 3:// boots

				player.stepHeight = 2;
				break;
			}
		} else {
			switch (this.armorType) {
			case 0:// helmet

				break;
			case 1:// chestplate

				break;
			case 2:// legs

				//player.capabilities.setPlayerWalkSpeed(.1F);
				break;
			case 3:// boots
				player.stepHeight = 0;
				break;
			}
			// figure it out later :P
			// player.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.10000000149011612D);

		}

	}
}
