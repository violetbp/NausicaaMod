package mooklabs.nausicaamod;

import java.util.Iterator;
import java.util.List;

import mooklabs.mookcore.MMod;
import mooklabs.mookcore.ToxicWorldGenerator;
import mooklabs.mookcore.toolsandarmor.NArmor;
import mooklabs.mookcore.toolsandarmor.NAxe;
import mooklabs.mookcore.toolsandarmor.NPickaxe;
import mooklabs.mookcore.toolsandarmor.NShovel;
import mooklabs.mookcore.toolsandarmor.NSword;
import mooklabs.nausicaamod.blocks.Brush;
import mooklabs.nausicaamod.blocks.InvisibleLight;
import mooklabs.nausicaamod.blocks.Liquid;
import mooklabs.nausicaamod.blocks.PetrifiedLog;
import mooklabs.nausicaamod.blocks.PoisonDirt;
import mooklabs.nausicaamod.blocks.PoisonGrass;
import mooklabs.nausicaamod.blocks.PoisonLeaves;
import mooklabs.nausicaamod.blocks.PoisonLog;
import mooklabs.nausicaamod.blocks.PoisonSand;
import mooklabs.nausicaamod.blocks.Puffball;
import mooklabs.nausicaamod.blocks.PureDirt;
import mooklabs.nausicaamod.blocks.Quicksand;
import mooklabs.nausicaamod.blocks.machines.SporeTreeBase;
import mooklabs.nausicaamod.glider.BlockGliderBuilder;
import mooklabs.nausicaamod.glider.GliderBody;
import mooklabs.nausicaamod.glider.GliderEngine;
import mooklabs.nausicaamod.glider.GliderFull;
import mooklabs.nausicaamod.glider.GliderHandle;
import mooklabs.nausicaamod.glider.GliderWing;
import mooklabs.nausicaamod.glider.MetalPlate;
import mooklabs.nausicaamod.godwarrior.EntityGodWarrior;
import mooklabs.nausicaamod.godwarrior.GodWarriorControl;
import mooklabs.nausicaamod.inventorytab.NPlayerHandler;
import mooklabs.nausicaamod.mobs.EntityBug;
import mooklabs.nausicaamod.mobs.EntityFoxSquirrel;
import mooklabs.nausicaamod.mobs.EntityHorseclaw;
import mooklabs.nausicaamod.mobs.JungleJelly;
import mooklabs.nausicaamod.mobs.Ohmu;
import mooklabs.nausicaamod.mobs.flying.Dragonfly;
import mooklabs.nausicaamod.mobs.flying.EntityBeetle;
import mooklabs.nausicaamod.mobs.flying.EntitySpore;
import mooklabs.nausicaamod.mobs.npc.EntityNausicaaVillager;
import mooklabs.nausicaamod.proxy.CommonProxy;
import mooklabs.nausicaamod.proxy.GuiHandlerNausicaa;
import mooklabs.nausicaamod.tea.BlockTeapot;
import mooklabs.nausicaamod.tea.TeaBush;
import mooklabs.nausicaamod.tea.TeaLeaves;
import mooklabs.nausicaamod.tea.Teapot;
import mooklabs.nausicaamod.tools.Blowgun;
import mooklabs.nausicaamod.tools.InsectWhistle;
import mooklabs.nausicaamod.tools.parts.CeramicIngot;
import mooklabs.nausicaamod.tools.parts.DiamondStick;
import mooklabs.nausicaamod.tools.parts.ReinforcedWetClay;
import mooklabs.nausicaamod.tools.parts.ShellFragment;
import mooklabs.nausicaamod.tools.parts.ShellShard;
import mooklabs.nausicaamod.tools.parts.WetClay;
import mooklabs.nausicaamodtech.TechMain;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// {{things to do!
/* //TODO SO MUCH STUFF
 * classes i may want to use
 * VillagerRegistry
 * GuiModOptionList
 * GuiIngameModOptions
 * ModMetadata
 * FMLClientHandler**main forge class??
 * NausicaaMod: NausicaaMod, {\iProper Noun};\r1. A Minecraft Mod Based on the anime and manga of Hayao Miyazaki's {\ uNausicaa of the Valley of Wind}.\n2. A very badly coded eclipse project.",
 *  */// }}
/**
 * this is the main class for nausicaamod<br>
 * 
 * @author mooklabs
 */
@Mod(modid = Main.modid, name = Main.name, version = Main.VERSION, guiFactory = "mooklabs.nausicaamod.NausicaaConfigGuiFactory")
public class Main extends MMod{

	public static final String modid = "nausicaamod";
	public static final String VERSION = "0.0.09";
	public static final String name = "NausicaaMod";

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "mooklabs.nausicaamod.proxy.ClientProxy", serverSide = "mooklabs.nausicaamod.proxy.CommonProxy")
	public static CommonProxy proxy;// THIS IS WEIRD BUT IT WORKS?

	// The instance of your mod that Forge uses.
	@Instance("nausicaamod")
	public static Main instance = new Main();

	// GUIs
	// REF private GuiHandler guiHandlerGliderBuilder = new GuiHandler();
	public static final int godControlGuiID = 0;
	public static final int inventoryGui = 1;
	public static final int nausicaaGuiID = 2;
	public static final int nausicaaTabGuiId = 3;


	// inits for ease of changing
	public final static String itemfold = "nausicaamod";
	// TODO CANNOT USE THIS ID public final static int thinTreeRenderId = 40;
	public final static int textRenderId = 50;
	public final static int lineRenderId = 51;

	/**
	 * The game settings that currently hold effect.
	 *server doesent like
	@Unused
	public NausicaaGameSettings gameSettings;*/

	//I don't think ill ever actually use this value, but its here anyway
	public final static boolean debug = false;

	// {{ Creative tab madness!
	public static CreativeTabs tabNCombat = new CreativeTabs("tabNCombat") {
		
		@Override
		public Item getTabIconItem() {
			return Main.ceramicKnife;
		}
	};
	public static CreativeTabs tabNBlocks = new CreativeTabs("tabNBlocks") {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(Main.poisonGrass);
		}
	};
	public static CreativeTabs tabNTools = new CreativeTabs("tabNTools") {
		@Override
		public Item getTabIconItem() {
			return Main.ceramicAxe;
		}
	};
	public static CreativeTabs tabNGlider = new CreativeTabs("tabNGlider") {
		@Override
		public Item getTabIconItem() {
			return Main.glider;
		}
	};
	public static CreativeTabs tabNItems = new CreativeTabs("tabNItems") {
		@Override
		public Item getTabIconItem() {
			return Main.wetClay;
		}
	};
	public static CreativeTabs tabNTea = new CreativeTabs("tabNTea") {
		@Override
		public Item getTabIconItem() {
			return Main.glider;
		}
	};
	/*public static CreativeTabs tabNPlants = new CreativeTabs("tabNPlants") {

		@Override
		public Item getTabIconItem() {
			return Main.wetClay;
		}
	};
	public static CreativeTabs tabNCooking = new CreativeTabs("tabNCooking") {

		@Override
		public Item getTabIconItem() {
			return Main.wetClay;
		}
	};
	public static CreativeTabs tabNUtil = new CreativeTabs("tabNUtil") {

		@Override
		public Item getTabIconItem() {
			return Main.wetClay;
		}
	};*/

	public static CreativeTabs tabGlider = Main.tabNGlider;
	public static CreativeTabs tabTools = Main.tabNTools;
	public static CreativeTabs tabCombat = Main.tabNCombat;
	public static CreativeTabs tabBlock = Main.tabNBlocks;
	public static CreativeTabs tabItems = Main.tabNItems;
	public static CreativeTabs tabPlants = Main.tabNBlocks;//Main.tabNPlants;
	public static CreativeTabs tabCooking = Main.tabNBlocks;//Main.tabNCooking;
	public static CreativeTabs tabUtil = Main.tabNBlocks;//Main.tabNUtil;
	public static CreativeTabs tabTea = Main.tabNTea;
	// }}

	// {{this makes the tool and armor types and sets their stats!
	// diamond is(3, 1561, 8.0F, 3.0F, 10) IRON is(2, 250, 6.0F, 2.0F, 14)
	public static ToolMaterial unfiredCeramicTool = EnumHelper.addToolMaterial("unfiredCeramic", 0, 2, 1F, 0F, 0);// TODO make all tools start as this
	public static ToolMaterial ceramicTool = EnumHelper.addToolMaterial("ceramic", 2, 300, 5.0F, 1.5F, 18);
	public static ToolMaterial shellTool = EnumHelper.addToolMaterial("shell", 3, 2000, 15.0F, 4F, 10);// name,hardness,uses,speed,damage,enchant
	public static ToolMaterial ceramicSharp = EnumHelper.addToolMaterial("ceramicSharp", 0, 2000, 0.0F, 10F, 1);// name,hardness,uses,speed,damage,enchant

	// DIAMOND is(33, {3, 8, 6, 3}, 10) iron is {2, 6, 5, 2}
	// String name,int durability, int[] reductionAmounts, int enchantability
	public static ArmorMaterial unfiredCeramicArmor = EnumHelper.addArmorMaterial("unfiredCeramic", 1, new int[] { 1, 1, 1, 1 }, 50);
	public static ArmorMaterial ceramicArmor = EnumHelper.addArmorMaterial("ceramic", 15, new int[] { 2, 7, 5, 2 }, 13);
	public static ArmorMaterial shellArmor = EnumHelper.addArmorMaterial("shell", 28, new int[] { 3, 8, 6, 3 }, 22);
	public static ArmorMaterial specialArmor = EnumHelper.addArmorMaterial("special", 28, new int[] { 2, 2, 2, 2 }, 15);

	// }}

	// {{ tools and armor
	public static final Item ceramicSword = new NSword(ceramicTool).setCreativeTab(tabCombat);
	public static final Item ceramicPickaxe = new NPickaxe(ceramicTool).setCreativeTab(tabTools);
	public static final Item ceramicAxe = new NAxe(ceramicTool).setCreativeTab(tabTools);
	public static final Item ceramicShovel = new NShovel(ceramicTool).setCreativeTab(tabTools);

	public static final Item shellSword = new NSword(shellTool).setCreativeTab(tabCombat);
	public static final Item shellPickaxe = new NPickaxe(shellTool).setCreativeTab(tabTools);
	public static final Item shellAxe = new NAxe(shellTool).setCreativeTab(tabTools);
	public static final Item shellShovel = new NShovel(shellTool).setCreativeTab(tabTools);

	public static final Item unfiredCeramicKnife = new NSword(unfiredCeramicTool).setCreativeTab(tabCombat);
	public static final Item ceramicKnife = new NSword(ceramicSharp).setCreativeTab(tabCombat);

	// Armor
	public static final Item unfiredCeramicHelmet = new NArmor(unfiredCeramicArmor, 1, 0);
	public static final Item unfiredCeramicChestplate = new NArmor(unfiredCeramicArmor, 1, 1);
	public static final Item unfiredCeramicLegs = new NArmor(unfiredCeramicArmor, 1, 2);
	public static final Item unfiredCeramicBoots = new NArmor(unfiredCeramicArmor, 1, 3);

	public static final Item ceramicHelmet = new NArmor(ceramicArmor, 1, 0);
	public static final Item ceramicChestplate = new NArmor(ceramicArmor, 1, 1);
	public static final Item ceramicLegs = new NArmor(ceramicArmor, 1, 2);
	public static final Item ceramicBoots = new NArmor(ceramicArmor, 1, 3);

	public static final Item shellHelmet = new NArmor(shellArmor, 1, 0);
	public static final Item shellChestplate = new NArmor(shellArmor, 1, 1);
	public static final Item shellLegs = new NArmor(shellArmor, 1, 2);
	public static final Item shellBoots = new NArmor(shellArmor, 1, 3);

	public static final Item specialHelmet = new NArmor(specialArmor, 1, 0);
	public static final Item specialChestplate = new NArmor(specialArmor, 1, 1);
	public static final Item specialLegs = new NArmor(specialArmor, 1, 2);
	public static final Item specialBoots = new NArmor(specialArmor, 1, 3);

	public static final Item gasMask = new NArmor(ceramicArmor, 1, 0);

	// }}

	// {{ blocks and items
	public final static Item shellShard = new ShellShard().setCreativeTab(tabItems);
	public static Item wetClay = new WetClay().setCreativeTab(tabItems);
	public static Item reinforcedWetClay = new ReinforcedWetClay().setCreativeTab(tabItems);
	public static Item ceramicIngot = new CeramicIngot().setCreativeTab(tabItems);
	public final static Item shellFragment = new ShellFragment().setCreativeTab(tabItems);
	public final static Item diamondStick = new DiamondStick().setCreativeTab(tabItems);

	// BLOCKS
	public final static Block poisonDirt = new PoisonDirt().setCreativeTab(tabBlock);
	public final static Block poisonGrass = new PoisonGrass().setCreativeTab(tabBlock);
	public final static Block poisonLog = new PoisonLog().setCreativeTab(tabBlock);
	public final static Block petrifiedLog = new PetrifiedLog().setCreativeTab(tabBlock);
	// CRIT MAYBE public final static Block poisonThinLog = new ThinLog().setCreativeTab(tabBlock);
	// public final static Block poisonWood = new PoisonWood().setCreativeTab(tabBlock);
	public final static Block poisonLeaves = new PoisonLeaves().setCreativeTab(tabBlock);
	public final static Block poisonBrush = new Brush().setCreativeTab(tabBlock);
	public final static Block puffball = new Puffball().setCreativeTab(tabBlock);
	public final static Block quicksand = new Quicksand().setCreativeTab(tabBlock);
	public final static Block poisonSand = new PoisonSand().setCreativeTab(tabBlock);
	public final static Block pureDirt = new PureDirt().setCreativeTab(tabBlock);
	public final static Block sporeTree = new SporeTreeBase().setCreativeTab(tabBlock);

	// LIQUIDS ////////////////////////////////////////////////////////////////////////////
	public static final Material acid = (new MaterialLiquid(MapColor.waterColor));
	public static final Fluid liquidSand = new Fluid("liquidSand");
	public static Block liquidSandBlock;
	// TODO "acid bucket" public final static Item acidBucket = new AcidBucket(liquidSandBlock);

	// }}

	public final static Block invisibleLight = new InvisibleLight().setCreativeTab(tabBlock);//TODO tabutil

	public final static Block gliderBuilder = new BlockGliderBuilder().setCreativeTab(tabGlider);
	// public final static Block craftingTable = new
	// BlockWorkbench().setCreativeTab(tabBlock);

	public final static Item metalPlate = new MetalPlate().setCreativeTab(tabNGlider).setUnlocalizedName("metalPlate").setTextureName(Main.itemfold + ":metalPlate");
	public final static Block gliderWing = new GliderWing().setCreativeTab(tabGlider);
	public final static Block gliderBody = new GliderBody().setCreativeTab(tabGlider);
	public final static Block gliderEngine = new GliderEngine().setCreativeTab(tabGlider);
	public final static Block gliderHandle = new GliderHandle().setCreativeTab(tabGlider);
	public final static Item glider = new GliderFull().setCreativeTab(tabGlider);
	public final static Block fuelBlock = new FuelBlock().setCreativeTab(tabGlider);
	public final static Item fuelCell = new Item().setCreativeTab(tabGlider).setUnlocalizedName("fuelCell").setTextureName(Main.itemfold + ":fuelCell");

	public final static Item flamethrower = new Flamethrower().setCreativeTab(tabCombat);

	public final static Item godWarriorControler = new GodWarriorControl().setCreativeTab(tabTools).setUnlocalizedName("godWarriorControler");

	public final static Item blowGun = new Blowgun().setCreativeTab(tabCombat);
	public final static Item blowGunDart = new Item().setUnlocalizedName("blowDart").setCreativeTab(tabCombat).setTextureName(Main.itemfold + ":blowDart").setMaxStackSize(64);
	public final static Item insectWhistle = new InsectWhistle().setCreativeTab(tabTools).setUnlocalizedName("insectWhistle");
	public final static Item flareGun = new Flaregun().setCreativeTab(tabCombat);
	public final static Item flare = new Item() {
		@Override
		public void addInformation(ItemStack itemStack, EntityPlayer player, List dataList, boolean bool) {
			dataList.add("You strap a torch to an arrow");
			dataList.add("Whats the worst that could happen?");
		}
	}.setUnlocalizedName("flare").setCreativeTab(tabCombat).setTextureName(Main.itemfold + ":blowDart");

	//{tea
	//public final static Block blockTeapot = new BlockTeapot().setCreativeTab(tabCombat).setBlockName("teapot);
	//public final static Item teapot = new Teapot().setCreativeTab(tabCombat);
	//public final static Block teaBush = new TeaBush().setCreativeTab(tabCombat);
	public final static Item teaLeaves = new TeaLeaves().setCreativeTab(tabCombat);

	//}
	
	
	
	
	/* //plants //public static Item toxicSeeds = new Seeds().setCreativeTab(tabPlants); public static Block sporer = new Sporer().setCreativeTab(tabNPlants); //Drink public
	 * final static Item drinks = new Drinks().setCreativeTab(tabItems).setUnlocalizedName("drinks");//TODO change???? public final static Block teapot = new
	 * Teapot().setCreativeTab(tabCooking); public final static Block stoveIdle = new Stove(true).setHardness(3.5F).setStepSound(Block.soundStoneFootstep)
	 * .setCreativeTab(tabCooking); public final static Block stoveBurning = new Stove (true).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setLightValue
	 * (0.875F).setUnlocalizedName("activeStove"); */

	// {{Biomes, only poisonForest spawns in overworld
	// public static final BiomeGenBase poisonForest = (new
	// BiomeGenPoisonJungle(60)).setColor(353825).setBiomeName("Toxic Jungle").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F);
	// public static final BiomeGenBase poisonDesert = (new
	// BiomeGenPoisonDesert(61)).setColor(353825).setBiomeName("Toxic Desert").func_76733_a(5159473).setTemperatureRainfall(1F, 0F);
	// public static final BiomeGenBase poisonForestLakes = (new
	// BiomeGenPoisonLakes(62)).setColor(353825).setBiomeName("Toxic Lake").func_76733_a(5159473).setTemperatureRainfall(1F,
	// 0F).setMinMaxHeight(-1.0F, 0.4F);;
	// public static final BiomeGenBase poisonForestVillage = (new
	// BiomeGenPoisonJungleVillage(63)).setColor(353825).setBiomeName("Toxic Jungle (Villages)").func_76733_a(5159473).setTemperatureRainfall(
	// 1F, 0F);
	// public static final BiomeGenBase poisonSwamp = (new
	// BiomeGenPoisonSwamp(64)).setColor(353825).setBiomeName("Toxic Swamp").func_76733_a(5159473).setTemperatureRainfall(1F, 0F);
	// public static final BiomeGenBase poisonForestHills = (new
	// BiomeGenPoisonJungle(65)).setColor(353825).setBiomeName("Toxic Hills").func_76733_a(5159473).setTemperatureRainfall(1F, 0F);
	// }}
	// public static final BiomeGenBase airspaceForest = (new
	// BiomeGenAirspaceForest(66)).setColor(353825).setBiomeName("Toxic Airy Jungle").func_76733_a(5159473).setTemperatureRainfall(1F, 0F);

	/* //TODO this -Desert with occasional tree trunks and sporeballs (toxicDesert) -Jungle with huge crevasses (called airspaceJungle) -Jungle with large lakes for Ohmu to
	 * live in (toxicLakes) -Jungle with ruined buildings (toxicVillage) -Swamp with toxic trees, dirt, plants (toxicSwamp) -Toxic jungle mountains (toxicHills) */

	// /////////////Dimension//////////////////////
	// public static BlockPortalToxic portalToxicBlock = (BlockPortalToxic) new BlockPortalToxic().setBlockName("portalToxicBlock").setCreativeTab(tabBlock);

	public final static int dimensionId = 8;

	// worldgen
	// This just changes some stuff, it is applied after chunck gen is your world generation file.
	public static ToxicWorldGenerator modifyWorldGen = new ToxicWorldGenerator();
	// /////end dimension

	// attributes?
	public static final IAttribute kindness = (new RangedAttribute("generic.kindness", 20.0D, 0.0D, Double.MAX_VALUE)).setDescription("Kindness Level").setShouldWatch(true);// MAYBE

	/**
	 * this boolean enables use of the glider
	 */
	public static boolean enableGlider = true;
	/**
	 * this boolean disables most of the other biomes for testing
	 */
	// TODO put in a config
	public static final boolean removeMostOtherBiomes = false;


	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		init(this.name);

		//CRIT TabRegistry.registerTab(new InventoryTabNausicaa());
		itemBlockNameReg();

		//{{EVERYTHING
		// {{/////////////////////////////ITEMSTACKS////////////////////////////////////

		// vanilla
		ItemStack waterBucketStack = new ItemStack(Items.water_bucket);
		ItemStack clayBlockStack = new ItemStack(Blocks.clay);
		ItemStack clayStack = new ItemStack(Items.clay_ball);

		ItemStack ironStack = new ItemStack(Items.iron_ingot);
		ItemStack diamondStack = new ItemStack(Items.diamond);
		ItemStack stickStack = new ItemStack(Items.stick);
		ItemStack leatherStack = new ItemStack(Items.leather);
		ItemStack furnaceStack = new ItemStack(Blocks.furnace);
		ItemStack woodStack = new ItemStack(Blocks.planks, 4, 2);// birch planks
		ItemStack reedStack = new ItemStack(Blocks.reeds);
		// {{ //////////////////////Nausicaa stacks////////////////////////////////
		// blocks
		ItemStack poisonLogStack = new ItemStack(poisonLog);

		// items
		ItemStack wetClayStack = new ItemStack(wetClay);
		ItemStack reinforcedWetClayStack = new ItemStack(reinforcedWetClay);

		ItemStack ceramicIngotStack = new ItemStack(ceramicIngot);
		ItemStack shellShardStack = new ItemStack(shellShard);
		ItemStack shellFragmentStack = new ItemStack(shellFragment);
		ItemStack diamondStickStack = new ItemStack(diamondStick);
		ItemStack diamondStickStack4 = new ItemStack(diamondStick, 4);
		ItemStack blowDartStack = new ItemStack(blowGunDart);
		ItemStack blowGunStack = new ItemStack(blowGun, 4);

		// glider
		ItemStack engineStack = new ItemStack(gliderEngine);
		ItemStack bodyStack = new ItemStack(gliderBody);
		ItemStack wingStack = new ItemStack(gliderWing);
		ItemStack handleStack = new ItemStack(gliderHandle);
		ItemStack gliderStack = new ItemStack(glider);
		ItemStack metalPlateStack = new ItemStack(metalPlate);

		// }}

		// {{ tool stacks
		ItemStack ceramicPickaxeStack = new ItemStack(ceramicPickaxe);
		ItemStack ceramicSwordStack = new ItemStack(ceramicSword);
		ceramicSwordStack.addEnchantment(Enchantment.sharpness, 1);
		ItemStack ceramicAxeStack = new ItemStack(ceramicAxe);
		ItemStack ceramicShovelStack = new ItemStack(ceramicShovel);

		ItemStack unfiredClaymoreStack = new ItemStack(unfiredCeramicKnife);
		ItemStack claymoreStack = new ItemStack(ceramicKnife);
		claymoreStack.addEnchantment(Enchantment.looting, 1);

		ItemStack shellPickaxeStack = new ItemStack(shellPickaxe);
		ItemStack shellSwordStack = new ItemStack(shellSword);
		ItemStack shellAxeStack = new ItemStack(shellAxe);
		ItemStack shellShovelStack = new ItemStack(shellShovel);

		// }}

		// {{armor stacks
		ItemStack unfiredCeramicHelmetStack = new ItemStack(unfiredCeramicHelmet);
		ItemStack unfiredCeramicChestplateStack = new ItemStack(unfiredCeramicChestplate);
		ItemStack unfiredCeramicLegsStack = new ItemStack(unfiredCeramicLegs);
		ItemStack unfiredCeramicBootsStack = new ItemStack(unfiredCeramicBoots);

		ItemStack ceramicHelmetStack = new ItemStack(ceramicHelmet);
		ItemStack ceramicChestplateStack = new ItemStack(ceramicChestplate);
		ItemStack ceramicLegsStack = new ItemStack(ceramicLegs);
		ItemStack ceramicBootsStack = new ItemStack(ceramicBoots);
		ceramicChestplateStack.addEnchantment(Enchantment.protection, 2);// enchant

		ItemStack shellHelmetStack = new ItemStack(shellHelmet);
		ItemStack shellChestplateStack = new ItemStack(shellChestplate);
		ItemStack shellLegsStack = new ItemStack(shellLegs);
		ItemStack shellBootsStack = new ItemStack(shellBoots);

		// }}
		// }}



		// {{/////////////////////////////RECIPES////////////////////////////////////

		// ///////////////shapeless recipes//////////////////
		// GameRegistry.addShapelessRecipe([output],[ingredients],[more
		// ingredients etc]);
		GameRegistry.addShapelessRecipe(woodStack, poisonLogStack);

		// //////////////////////////////shaped///////////////
		// items etc
		GameRegistry.addRecipe(diamondStickStack4, " d ", " d ", " d ", 'd', diamondStack);
		GameRegistry.addRecipe(shellShardStack, "sss", "sss", "sss", 's', shellFragmentStack);
		// GameRegistry.addRecipe(wetClayStack, "iii", "cwc", "ccc", 'c', clayBlockStack, 'w', waterBucketStack, 'i', clayStack);
		GameRegistry.addRecipe(reinforcedWetClayStack, "cwc", "cwc", "cwc", 'c', clayBlockStack, 'w', diamondStickStack);

		GameRegistry.addRecipe(wetClayStack, new Object[] { "iii", "cwc", "ccc", 'c', clayBlockStack, 'w', waterBucketStack, 'i', clayStack });// i dont think this version is
		// needed

		// {{ ------------TOOLS-------------
		// ceramic tools
		GameRegistry.addRecipe(ceramicPickaxeStack, "ccc", " s ", " s ", 'c', ceramicIngotStack, 's', stickStack);
		GameRegistry.addRecipe(ceramicSwordStack, " c ", " c ", " s ", 'c', ceramicIngotStack, 's', stickStack);
		GameRegistry.addRecipe(ceramicAxeStack, "cc ", "cs ", " s ", 'c', ceramicIngotStack, 's', stickStack);
		GameRegistry.addRecipe(ceramicShovelStack, " c ", " s ", " s ", 'c', ceramicIngotStack, 's', stickStack);

		GameRegistry.addRecipe(unfiredClaymoreStack, " c ", " c ", " s ", 'c', reinforcedWetClayStack, 's', diamondStickStack);

		// shell Tools
		GameRegistry.addRecipe(shellPickaxeStack, "ccc", " s ", " s ",// TODO
				// make
				// it
				// not
				// duplicates?
				// or
				// change
				'c', shellShardStack, 's', diamondStickStack);
		GameRegistry.addRecipe(shellSwordStack, "  c", "ec ", "se ", 'c', shellShardStack, 'e', ironStack, 's', diamondStickStack);// TODO
		// fancy
		// sword???????????
		GameRegistry.addRecipe(shellAxeStack, "cc ", "cs ", " s ", 'c', shellShardStack, 's', diamondStickStack);
		GameRegistry.addRecipe(shellShovelStack, " c ", " s ", " s ", 'c', shellShardStack, 's', diamondStickStack);

		// }}

		// {{ ----------ARMOR---------------
		// unfired ceramic
		GameRegistry.addRecipe(unfiredCeramicHelmetStack, "ccc", "s s", "   ", 'c', wetClayStack, 's', wetClayStack);
		GameRegistry.addRecipe(unfiredCeramicChestplateStack, "s s", "ccc", "ccc",// TODO add reenforcement
				'c', wetClayStack, 's', wetClayStack);
		GameRegistry.addRecipe(unfiredCeramicLegsStack, "scs", "c c", "c c", 'c', wetClayStack, 's', wetClayStack);
		GameRegistry.addRecipe(unfiredCeramicBootsStack, "   ", "s s", "c c", 'c', wetClayStack, 's', wetClayStack);

		// shell
		GameRegistry.addRecipe(shellHelmetStack, "sss", "s s", "   ", 's', shellShardStack);
		GameRegistry.addRecipe(shellChestplateStack, "s s", "sss", "sss", 's', shellShardStack);
		GameRegistry.addRecipe(shellLegsStack, "sss", "s s", "s s", 's', shellShardStack);
		GameRegistry.addRecipe(shellBootsStack, "   ", "s s", "s s", 's', shellShardStack);
		// }}

		// Glider
		GameRegistry.addRecipe(engineStack, "csc", "sss", "csc", 'c', furnaceStack, 's', metalPlateStack);
		GameRegistry.addRecipe(bodyStack, "sss", "csc", "cec", 'c', leatherStack, 's', metalPlateStack, 'e', engineStack);
		GameRegistry.addRecipe(wingStack, "csc", "csc", "scs", 'c', leatherStack, 's', metalPlateStack);
		GameRegistry.addRecipe(handleStack, "sss", "s s", "c c", 'c', leatherStack, 's', metalPlateStack);

		// }}//////////////////////////Smelting recipes////////////////////////////////////

		//{{SMELTING
		GameRegistry.addSmelting(unfiredCeramicHelmetStack, ceramicHelmetStack, 0.0f);
		GameRegistry.addSmelting(unfiredCeramicChestplateStack, ceramicChestplateStack, 0.0f);
		GameRegistry.addSmelting(unfiredCeramicLegsStack, ceramicLegsStack, 0.0f);
		GameRegistry.addSmelting(unfiredCeramicBootsStack, ceramicBootsStack, 0.0f);

		// claymore
		GameRegistry.addSmelting(unfiredCeramicKnife, claymoreStack, 0.0f);

		GameRegistry.addSmelting(wetClay, ceramicIngotStack, 0.0f);
		//}}


		// CRIT NausicaaWorldType.addRemoveNeededBiomes();
		//}}


		playerTracker = new NPlayerHandler();
		// GameRegistry.registerPlayerTracker(playerTracker);
		FMLCommonHandler.instance().bus().register(playerTracker);
		MinecraftForge.EVENT_BUS.register(playerTracker);




	}
	public static NPlayerHandler playerTracker;

	GuiHandlerNausicaa guiHandler = new GuiHandlerNausicaa();

	@EventHandler
	public void load(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);

		proxy.registerRenderers();// does more than just renders


		// Dimension
		// CRIT DimensionManager.registerProviderType(Main.dimensionId, WorldProviderToxic.class, false);
		// CRIT DimensionManager.registerDimension(Main.dimensionId, Main.dimensionId);

		// CRIT GameRegistry.registerWorldGenerator(modifyWorldGen, 0);

		// GUI
		// CRIT NetworkRegistry.registerGuiHandler(Main.instance, new GuiHandler());

		logger.info("\n************************\nWelcome to NausicaaMod!!!\nEnjoy your stay...\n..if you can!\n***************************");

		// {{//////////////ITEMSTACKS//////////////////

		// {{ vanilla stacks////////////////////////////////

		ItemStack goldStack = new ItemStack(Items.gold_ingot);
		ItemStack coalStack = new ItemStack(Items.coal);
		ItemStack paperStack = new ItemStack(Items.paper);

		ItemStack dirt = new ItemStack(Blocks.dirt);
		ItemStack stone = new ItemStack(Blocks.stone);
		ItemStack glowStone = new ItemStack(Blocks.glowstone);
		ItemStack ironOreStack = new ItemStack(Blocks.iron_ore);
		ItemStack obsidianStack = new ItemStack(Blocks.obsidian);

		// used
		ItemStack waterBucketStack = new ItemStack(Items.water_bucket);
		ItemStack clayBlockStack = new ItemStack(Blocks.clay);
		ItemStack clayStack = new ItemStack(Items.clay_ball);

		ItemStack ironStack = new ItemStack(Items.iron_ingot);
		ItemStack diamondStack = new ItemStack(Items.diamond);
		ItemStack stickStack = new ItemStack(Items.stick);
		ItemStack leatherStack = new ItemStack(Items.leather);
		ItemStack furnaceStack = new ItemStack(Blocks.furnace);
		ItemStack woodStack = new ItemStack(Blocks.planks, 4);
		ItemStack reedStack = new ItemStack(Blocks.reeds);

		// }}

		// register block name this is like the most important thing
		// names blocks and items AND INITITALIZEZ them
		// MAYBE itemBlockNameReg();

		// Generation
		// GameRegistry.registerWorldGenerator(oreManager);dont want any

		// add biome to world////////
		// for(int i = 0; i < BiomeGenBase.biomeList.length; i++)//was testing
		// to see if this would remove all biomes, didnt work?
		// GameRegistry.removeBiome(BiomeGenBase.biomeList[i]);
		// CRIT GameRegistry.addBiome(poisonForest);

		/* if(removeMostOtherBiomes){ GameRegistry.removeBiome(BiomeGenBase.ocean); GameRegistry.removeBiome(BiomeGenBase.plains);
		 * GameRegistry.removeBiome(BiomeGenBase.taiga); GameRegistry.removeBiome(BiomeGenBase.forestHills); GameRegistry.removeBiome(BiomeGenBase.extremeHills);
		 * GameRegistry.removeBiome(BiomeGenBase.desert); GameRegistry.removeBiome(BiomeGenBase.frozenOcean); GameRegistry.removeBiome(BiomeGenBase.river);
		 * GameRegistry.removeBiome(BiomeGenBase.swampland); GameRegistry.removeBiome(BiomeGenBase.desert); GameRegistry.removeBiome(BiomeGenBase.forest);
		 * GameRegistry.removeBiome(BiomeGenBase.jungle); } */

		// {{entities

		// BiomeGenBase[] nausicaaBiomes = { Main.poisonForest, Main.poisonSwamp, Main.poisonDesert, Main.poisonForestVillage, Main.poisonSwamp };
		BiomeGenBase[] nausicaaBiomes = { BiomeGenBase.forest, BiomeGenBase.jungle, BiomeGenBase.desert, BiomeGenBase.taiga };
		registerEntity(Ohmu.class, "Ohmu", 0xeaeaea, 0x111111);
		addSpawn(Ohmu.class, 1, 1, 1, nausicaaBiomes);

		registerEntity(EntityNausicaaVillager.class, "NPC", 0x00FF00, 0xFF0000);

		registerEntity(JungleJelly.class, "JungleJelly", 0x0000FF, 0x00FF00);
		addSpawn(JungleJelly.class, 1, 1, 1, nausicaaBiomes);

		registerEntity(Dragonfly.class, "Dragonfly", 0xFF00FF, 0x00FF00);

		registerEntity(EntityBeetle.class, "Beetle", 0xF000FF, 0x00FF00);
		addSpawn(EntityBeetle.class, 10, 10, 15, nausicaaBiomes);

		registerEntity(EntityFoxSquirrel.class, "Fox Squirrel", 0x00000F, 0x000F00);

		registerEntity(EntitySpore.class, "Spore", 0x00FFFF, 0x000F00);
		addSpawn(EntitySpore.class, 1, 1, 1, nausicaaBiomes);

		// registerEntity(EntityWorld.class, "World Entity(does nothing)(May crash game!!!)", 0x000000, 0x000000);

		registerEntity(EntityBug.class, "Bug", 0x00FF00, 0xFF00AA);
		addSpawn(EntityBug.class, 10, 4, 110, nausicaaBiomes);

		registerEntity(EntityHorseclaw.class, "Horseclaw", 0xFFFF00, 0xFF40AA);

		registerEntity(EntityGodWarrior.class, "God Warrior", 0x000000, 0x000000);

		// }}

	}

	// /////////////Mobs!////////////
	@Override
	public void registerEntity(Class<? extends Entity> entityClass, String entityName, int bkEggColor, int fgEggColor) {
		int id = EntityRegistry.findGlobalUniqueEntityId();

		EntityRegistry.registerGlobalEntityID(entityClass, entityName, id);
		EntityList.entityEggs.put(Integer.valueOf(id), new EntityEggInfo(id, bkEggColor, fgEggColor));
	}

	@Override
	public void addSpawn(Class<? extends EntityLiving> entityClass, int spawnProb, int min, int max, BiomeGenBase[] biomes) {
		if (spawnProb > 0) {
			EntityRegistry.addSpawn(entityClass, spawnProb, min, max, EnumCreatureType.creature, biomes);
		}
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		try {
			if (Loader.isModLoaded("nausicaamodtech")) FMLLog.info("[NausicaaMod]: Loaded NausicaaModTech addon(Good Job!)");
			else FMLLog.warning("[NausicaaMod]: DID NOT LOAD NausicaaModTech addon\nYou did something wrong, like not download and put it in the right folder :P");

		} catch (Exception e) {
			FMLLog.severe("[NausicaaMod]: Something went wrong when checking for a mod being loaded");
		}
		//TabRegistry.addTabsToInventory(new NausicaaGuiInventory(Minecraft.getMinecraft().thePlayer));


		/* impliments later...better way to waila
		 * if (Loader.isModLoaded("waila")) {
		 * IWailaDataProvider provider = new HUDHandlerNausicaa();
		 * package mcp.mobius.waila.addons.vanillamc.HUDHandlerVanilla;
		 * ModuleRegistrar.instance().registerStackProvider(provider, silverfish.getClass());
		 * } */
	}

	private void itemBlockNameReg() {
		FluidRegistry.registerFluid(liquidSand);
		Main.liquidSandBlock = new Liquid(liquidSand, Material.lava).setCreativeTab(tabBlock);//TODO tabutil
		registerBlock(liquidSandBlock, "Poison Dirt");

		// {{ block registration
		registerBlock(poisonDirt, "Poison Dirt");
		registerBlock(poisonGrass, "Poison Grass");
		registerBlock(poisonLog, "Poison Log");
		registerBlock(petrifiedLog, "Petrified Log");
		// registerBlock(poisonThinLog , "Thin Poison Log");

		// registerBlock(poisonWood , "Poison Wood");
		registerBlock(poisonLeaves, "Poison Leaves");
		registerBlock(poisonBrush, "Poison Brush");
		// registerBlock(puffball , "Puffball");done lower
		registerBlock(quicksand, "Quicksand");
		registerBlock(poisonSand, "Spore Sand");
		registerBlock(pureDirt, "Purified Dirt");
		registerBlock(sporeTree, "Purified Dirt");

		// ///////blocks that do stuff/////////
		registerBlock(gliderBuilder, "Glider Builder");
		registerBlock(fuelBlock, "Fuel Block");

		registerBlock(invisibleLight, "Invisible Light");

		// registerBlock(portalToxicBlock, "Portal to the toxic forest");
		/* registerBlock(craftingTable , "Test Crafting Table");
		 * registerBlock(stoveIdle , "Stove"); registerBlock(stoveBurning ,
		 * "Active Stove"); registerBlock(teapot , "Teapot"); */
		// }}

		// {{ items
		registerItem(wetClay, "Wet Clay");
		registerItem(reinforcedWetClay, "Reinforced Wet Clay");
		registerItem(shellFragment, "Ohmu Shell Fragment");
		registerItem(shellShard, "Ohmu Shell Shard");
		registerItem(ceramicIngot, "Ceramic Ingot");
		registerItem(diamondStick, "Diamond Stick");
		registerItem(metalPlate, "Metal Plate");
		// TODO registerItem(acidBucket, "Metal Plate");

		// glider
		registerBlock(gliderWing, "Glider Wing");
		registerBlock(gliderBody, "Glider Body");
		registerBlock(gliderEngine, "Glider Engine");
		registerBlock(gliderHandle, "Glider Handle");
		registerItem(glider, "Glider");
		registerItem(fuelCell, "Fuel Cell");

		registerItem(blowGun, "Blow Gun");
		registerItem(blowGunDart, "Blow Gun Dart");
		registerItem(flareGun, "Flare Gun");
		registerItem(flare, "Flare Gun Bullet");
		registerItem(flamethrower, "");

		registerItem(insectWhistle, "Insect Whistle");

		registerItem(godWarriorControler, "");

		// puffballs
		GameRegistry.registerBlock(puffball, puffball.getUnlocalizedName());
		/* LanguageRegistry.addName(new ItemStack(puffball, 1, 0), "Blue Puffball");
		 * LanguageRegistry.addName(new ItemStack(puffball, 1, 1), "Purple Puffball");
		 * LanguageRegistry.addName(new ItemStack(puffball, 1, 2), "Pink Puffball");
		 * LanguageRegistry.addName(new ItemStack(puffball, 1, 3), "Green Puffball");// these register all different names for */// metadata

		/* drinks
		 * GameRegistry.registerItem(drinks, drinks.getUnlocalizedName());
		 * LanguageRegistry.addName(new ItemStack(drinks, 1, 0), "Beer");
		 * LanguageRegistry.addName(new ItemStack(drinks, 1, 1), "Black Tea");
		 * LanguageRegistry.addName(new ItemStack(drinks, 1, 2), "ButterBeer");
		 * LanguageRegistry.addName(new ItemStack(drinks, 1, 3), "Coffee");// these register all different names for metadata
		 * LanguageRegistry.addName(new ItemStack(drinks, 1, 4), "Green Tea");
		 * LanguageRegistry.addName(new ItemStack(drinks, 1, 5), "Rad Elixir");
		 * LanguageRegistry.addName(new ItemStack(drinks, 1, 6), "Wine");
		 * // seeds
		 * GameRegistry.registerItem(toxicSeeds, toxicSeeds.getUnlocalizedName());
		 * LanguageRegistry.addName(new ItemStack(toxicSeeds, 1, 0), "Dead Sporer Seeds???? What???");
		 * LanguageRegistry.addName(new ItemStack(toxicSeeds, 1, 1), "Blue Sporer Seeds");
		 * LanguageRegistry.addName(new ItemStack(toxicSeeds, 1, 2), "Purple Sporer Seeds");
		 * LanguageRegistry.addName(new ItemStack(toxicSeeds, 1, 3), "Red Sporer Seeds");// these register all different names for metadata
		 * LanguageRegistry.addName(new ItemStack(toxicSeeds, 1, 4), "Silver Sporer Seeds");
		 * LanguageRegistry.addName(new ItemStack(toxicSeeds, 1, 5), "White Sporer Seeds");
		 * // sporers
		 * GameRegistry.registerBlock(sporer, "sporer");
		 * LanguageRegistry.addName(new ItemStack(sporer, 1, 0), "Dead Sporer");
		 * LanguageRegistry.addName(new ItemStack(sporer, 1, 1), "Blue Sporer");
		 * LanguageRegistry.addName(new ItemStack(sporer, 1, 2), "Purple Sporer");// these register all different names for metadata
		 * LanguageRegistry.addName(new ItemStack(sporer, 1, 3), "Red Sporer");
		 * LanguageRegistry.addName(new ItemStack(sporer, 1, 4), "Silver Sporer");
		 * LanguageRegistry.addName(new ItemStack(sporer, 1, 5), "White Sporer"); */
		// }}

		// wetTools
		// registerItem(wetCeramicSword, "Wet Ceramic Sword");

		// {{//////////////tools////////////////////////////////////
		registerItem(ceramicSword, "Ceramic Sword");
		registerItem(ceramicPickaxe, "Ceramic Pickaxe");
		registerItem(ceramicAxe, "Ceramic Axe");
		registerItem(ceramicShovel, "Ceramic Shovel");

		registerItem(unfiredCeramicKnife, "Unfired Claymore");
		registerItem(ceramicKnife, "Claymore");

		registerItem(shellSword, "Shell Sword");
		registerItem(shellPickaxe, "Shell Pickaxe");
		registerItem(shellAxe, "Shell Axe");
		registerItem(shellShovel, "Shell Shovel");
		// }}

		// {{////////////////armor////////////////////////

		registerItem(unfiredCeramicHelmet, "Unfired Ceramic Helmet");
		registerItem(unfiredCeramicChestplate, "Unfired Ceramic Chestplate");
		registerItem(unfiredCeramicLegs, "Unfired Ceramic Greaves");
		registerItem(unfiredCeramicBoots, "Unfired Ceramic Boots");

		//registerItem(ceramicHelmet, "Ceramic Helmet");
		registerItem(ceramicChestplate, "Ceramic Chestplate");
		registerItem(ceramicLegs, "Ceramic Greaves");
		registerItem(ceramicBoots, "Ceramic Boots");

		registerItem(shellHelmet, "Shell Helmet");
		registerItem(shellChestplate, "Shell Chestplate");
		registerItem(shellLegs, "Shell Greaves");
		registerItem(shellBoots, "Shell Boots");

		registerItem(specialHelmet, "Special Helmet");
		registerItem(specialChestplate, "Special Chestplate");
		registerItem(specialLegs, "Special Greaves");
		registerItem(specialBoots, "Special Boots");

		registerItem(gasMask, "Gas Mask");

		// }}
		
		//{{//////Tea/////
		//registerBlock(blockTeapot, "teapot");
		//registerBlock(teaBush, "bush");
		//registerItem(teapot, "teapot");
		registerItem(teaLeaves, "tea leaves");

		//}}
		for(int times =0; times <100; times++)System.out.println(times);
		FMLInterModComms.sendMessage(TechMain.modid, "boo", "HAI");

	}

	// ///////////block reg again////////////
	public static void registerBlock(Block block, String name) {
		GameRegistry.registerBlock(block, block.getUnlocalizedName());
		LanguageRegistry.addName(block, name);
	}

	public static void registerItem(Item item, String name) {
		GameRegistry.registerItem(item, modid + item.getUnlocalizedName());
		LanguageRegistry.addName(item, name);
	}

	/** this is more just for future reference than anything else */
	@Override
	@EventHandler
	public void messageRecieve(IMCEvent event) {

		Iterator<IMCMessage> itr = event.getMessages().iterator();
		while(itr.hasNext()) {
			IMCMessage element = itr.next();
			logger.info("Sender: " + element.getSender() + "Value: " + element.getStringValue() + " ");
		}
		System.out.println();
	}



	// {{extrastuff
	/**
	 * calls debug start in other class. <br>
	 * note: THIS IS FLIPPIN POINTLESS ITS A METHOD WITH ONE LINE
	 */
	@SideOnly(Side.CLIENT)
	public void initDebug() {
		//NausicaaDebugWindow.init();
	}

	@SideOnly(Side.CLIENT)
	/**
	 * write to the debug window!<br>
	 * since I dont want to fill the mc console up!
	 */
	public static void debugWrite(String str) {
		//NausicaaDebugWindow.write(str);
	}

}

