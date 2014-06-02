package mooklabs.laputamod.proxy;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	@SideOnly(Side.CLIENT)
	@Override
	public void registerRenderers() {

		tileEntities();
		networkRegisters();
		MinecraftForge.EVENT_BUS.register(new GuiManaBar(Minecraft.getMinecraft()));//this makes gui run, if you remove it it will not work


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





