package mooklabs.mookcore;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;


public class MLib {

	public MLib() {
		//nuffin ;(
	}
	public static FakePlayer getFakePlayer(World w){
		if (w instanceof WorldServer)
			return FakePlayerFactory.getMinecraft((WorldServer)w);
		return null;
	}

	public static void printToPlayer(String str) {
		if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.CLIENT))
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(str));
	}

	public static void printToPlayer(EntityPlayer player, String str) {
		player.addChatMessage(new ChatComponentText(str));
	}

	public static boolean isPlayerHoldingItem(EntityPlayer player, Item item) {
		ItemStack heldItem = player.getHeldItem();
		return heldItem!=null && heldItem.getItem() == item;
	}
	public static boolean doesPlayerHaveItem(EntityPlayer player, Item item) {
		return player.inventory.hasItem(item);
	}
	public static boolean doesPlayerHaveItems(EntityPlayer player, Item... item) {
		for(Item i: item) if(player.inventory.hasItem(i))return true;
		return false;
	}
}
