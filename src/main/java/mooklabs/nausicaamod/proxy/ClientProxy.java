package mooklabs.nausicaamod.proxy;

import mooklabs.nausicaamod.Main;
import mooklabs.nausicaamod.godwarrior.EntityGodWarrior;
import mooklabs.nausicaamod.godwarrior.RenderSimpleGodWarrior;
import mooklabs.nausicaamod.keysticksetc.MookKeyHandler;
import mooklabs.nausicaamod.keysticksetc.NausicaaEventHandler;
import mooklabs.nausicaamod.keysticksetc.UnpoweredGlider;
import mooklabs.nausicaamod.mobs.EntityFoxSquirrel;
import mooklabs.nausicaamod.mobs.EntityFoxSquirrel.RenderFoxSquirrel;
import mooklabs.nausicaamod.mobs.JungleJelly;
import mooklabs.nausicaamod.mobs.JungleJelly.RenderJungleJelly;
import mooklabs.nausicaamod.mobs.ModelOhmu;
import mooklabs.nausicaamod.mobs.Ohmu;
import mooklabs.nausicaamod.mobs.Ohmu.RenderOhmu;
import mooklabs.nausicaamod.mobs.flying.Dragonfly;
import mooklabs.nausicaamod.mobs.flying.Dragonfly.RenderDragonfly;
import mooklabs.nausicaamod.mobs.flying.EntityBeetle;
import mooklabs.nausicaamod.mobs.flying.EntitySpore;
import mooklabs.nausicaamod.mobs.flying.EntitySpore.RenderSpore;
import mooklabs.nausicaamod.mobs.flying.ModelSpore;
import mooklabs.nausicaamod.mobs.npc.EntityNausicaaVillager;
import mooklabs.nausicaamod.mobs.npc.EntityNausicaaVillager.RenderNausicaaVillager;
import mooklabs.nausicaamod.mobs.npc.ModelNPC;
import mooklabs.nausicaamod.models.ModelsmallInsect;
import mooklabs.nausicaamod.models.RenderText;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelOcelot;
import net.minecraft.client.model.ModelSlime;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	@SideOnly(Side.CLIENT)
	@Override
	public void registerRenderers() { 							// This is for rendering entities and so forth
		RenderingRegistry.registerEntityRenderingHandler(Ohmu.class, new RenderOhmu(new ModelOhmu(), 0.5f));

		RenderingRegistry.registerEntityRenderingHandler(EntityNausicaaVillager.class, new RenderNausicaaVillager(new EntityNausicaaVillager(Minecraft.getMinecraft().theWorld), new ModelNPC(0F), 0.5F));
		// RenderingRegistry.registerEntityRenderingHandler(EntityNausicaaVillager.class, new RenderNausicaaVillager(new ModelBiped(0F), 0.5F));

		RenderingRegistry.registerEntityRenderingHandler(JungleJelly.class, new RenderJungleJelly(new ModelSlime(0), 0.5F));

		RenderingRegistry.registerEntityRenderingHandler(EntityFoxSquirrel.class, new RenderFoxSquirrel(new ModelOcelot(), 0.3F));//only shadowsize //wingspeed, shadowsize, scale

		RenderingRegistry.registerEntityRenderingHandler(EntitySpore.class, new RenderSpore(new ModelSpore(), 0.0F));//only shadowsize

		//Dragonfly
		RenderingRegistry.registerEntityRenderingHandler(Dragonfly.class, new RenderDragonfly(new ModelsmallInsect(), 0.3F));//only shadowsize //wingspeed, shadowsize, scale

		RenderingRegistry.registerEntityRenderingHandler(EntityBeetle.class, new RenderDragonfly(new ModelsmallInsect(), 0.3F));//only shadowsize //wingspeed, shadowsize, scale

		RenderingRegistry.registerEntityRenderingHandler(EntityGodWarrior.class, new RenderSimpleGodWarrior(new ModelOhmu(), 10F));
		//  		new RenderGodWarrior(new ModelBiped(), 5F, 5F));//only shadowsize //wingspeed, shadowsize, scale


		//thin Tree :D
		//RenderingRegistry.registerBlockHandler(new RenderThinLog()); // Or 'this' if your proxy happens to be the one that implements the block render interface.

		RenderingRegistry.registerBlockHandler(new RenderText()); // Or 'this' if your proxy happens to be the one that implements the block render interface.


		//starts the keyhandler (for glider)
		FMLCommonHandler.instance().bus().register(new MookKeyHandler());


		//for poison
		//CRIT FMLCommonHandler.instance().bus().register(new Poisoner(Minecraft.getMinecraft()));
		//FMLCommonHandler.instance().bus().register(Main.instance.unpoweredGliderTickHandle);

		Main.instance.initDebug();
		tileEntities();
		networkRegisters();

		MinecraftForge.EVENT_BUS.register(new NausicaaEventHandler());


	}

	@SideOnly(Side.CLIENT)

	// Tile Entities
	public void tileEntities() {
		//MAYBE GameRegistry.registerTileEntity(TileEntityGliderBuilder.class, "GliderBuilderTileEntity");
	}

	@SideOnly(Side.CLIENT)
	// Network Registry
	public void networkRegisters() {
		// CRIT NetworkRegistry.instance().registerGuiHandler(this, guiHandlerGliderBuilder);
		Main.debugWrite("registering glider");
		//Main.instance.unpoweredGliderTickHandle = new UnpoweredGlider(Minecraft.getMinecraft());
		//FMLCommonHandler.instance().bus().register(Main.instance.unpoweredGliderTickHandle);

		if(Main.enableGlider)UnpoweredGlider.startGlider();

	}


}





