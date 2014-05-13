package mooklabs.nausicaamod.proxy;

import mooklabs.nausicaamod.Main;
import net.minecraft.server.MinecraftServer;

public class CommonProxy {
 
        // server stuff
        public void registerRenderers() {
        	//MinecraftServer.getServer().setOnlineMode(false);
        	MinecraftServer.getServer().setMOTD("NausicaaMod: v"+Main.VERSION);
        	MinecraftServer.getServer().setAllowFlight(true);
        	System.out.println("[NausicaaMod] Set online to false, allow flight to true, changed motd");
	        //FMLCommonHandler.instance().bus().register(new CommonProxy());
        }/*
        @SubscribeEvent
		@SideOnly(Side.SERVER)
		public void networkRegister(ServerChatEvent event) {
			System.out.println("playerJoinEvent");
		}*/
}
