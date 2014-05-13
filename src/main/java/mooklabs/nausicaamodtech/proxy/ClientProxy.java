package mooklabs.nausicaamodtech.proxy;

import java.util.TimerTask;

import mooklabs.nausicaamod.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelOcelot;
import net.minecraft.client.model.ModelSlime;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
       
		@SideOnly(Side.CLIENT)
        @Override
        public void registerRenderers() { 						
           
            tileEntities();
            networkRegisters();
            
            
        }   
		
		@SideOnly(Side.CLIENT)

		// Tile Entities
		public void tileEntities() {
			
		}
		
		@SideOnly(Side.CLIENT)
		// Network Registry
		public void networkRegisters() {
			
		} 
		
		
}

		
		
        
       
