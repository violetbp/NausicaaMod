package mooklabs.mookcore;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

/**
 * this class is so pe
 * 
 * @author mooklabs
 */

public class MLib {

	public static DamageSource ownMagic = new DamageSource("ownMagic").setDamageBypassesArmor().setMagicDamage();


	public static FakePlayer getFakePlayer(World w) {
		if (w instanceof WorldServer) return FakePlayerFactory.getMinecraft((WorldServer) w);
		return null;
	}

	public static void printToPlayer(String str) {
		if (FMLCommonHandler.instance().getEffectiveSide().equals(Side.CLIENT)) Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(str));
	}

	public static void printToPlayer(EntityPlayer player, String str) {
		player.addChatMessage(new ChatComponentText(str));
	}

	public static boolean isPlayerHoldingItem(EntityPlayer player, Item item) {
		ItemStack heldItem = player.getHeldItem();
		return heldItem != null && heldItem.getItem() == item;
	}

	public static boolean doesPlayerHaveItem(EntityPlayer player, Item item) {
		return player.inventory.hasItem(item);
	}

	public static boolean doesPlayerHaveItems(EntityPlayer player, Item... item) {
		for (Item i : item)
			if (player.inventory.hasItem(i)) return true;
		return false;
	}

	/**
	 * nice and simple breaking and item dropping
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void breakBlock(World world, int x, int y, int z) {
		if (world.getBlock(x, y, z) != Blocks.bedrock) {// duh
			world.getBlock(x, y, z).dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}

	/**
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param smeltChance from 0 to 1
	 * @param multiplyer is spelled wrong
	 */
	public static void specialBreakBlock(World world, int x, int y, int z, double smeltChance, int multiplyer) {
		if (!world.isRemote && world.getBlock(x, y, z) != Blocks.bedrock) {// duh
			Random rand = world.rand;
			multiplyer = rand.nextInt(multiplyer);
			if(multiplyer<=0)multiplyer=1;
			ItemStack stack = new ItemStack(world.getBlock(x, y, z).getItemDropped(0, new Random(), 0), world.getBlock(x, y, z).quantityDropped(rand) + multiplyer);
			ItemStack stacksmelted = FurnaceRecipes.smelting().getSmeltingResult(stack);

			if (stacksmelted != null && rand.nextDouble() < smeltChance) {
				stacksmelted.stackSize = stack.stackSize;
				dropBlockAsItem(world, x, y, z, stacksmelted);
			} else for (int i = 0; i < multiplyer; i++)
				world.getBlock(x, y, z).dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);

			world.setBlockToAir(x, y, z);
		}


	}

	/**
	 * Spawns EntityItem in the world for the given ItemStack if the world is not remote.
	 */
	public static void dropBlockAsItem(World world, int x, int y, int z, ItemStack stack) {
		if (!world.isRemote) {
			System.out.println("meh");
			float f = 0.7F;
			double d0 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
			double d1 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
			double d2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
			EntityItem entityitem = new EntityItem(world, x + d0, y + d1, z + d2, stack.copy());
			// entityitem.delayBeforeCanPickup = 10;
			System.out.println(entityitem);
			entityitem.forceSpawn = true;
			world.spawnEntityInWorld(entityitem);
		}
	}

}
