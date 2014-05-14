package mooklabs.mookcore;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;


public class MLib {

	public MLib() {
	//nuffin ;(	
	}

	public static void printToPlayer(String str) {
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(str));
	}

	public static void printToPlayer(EntityPlayer player, String str) {
		player.addChatMessage(new ChatComponentText(str));
	}

}
