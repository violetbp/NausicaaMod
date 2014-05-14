package mooklabs.nausicaamodtech;

import java.util.Iterator;

import mooklabs.mookcore.MMod;
import mooklabs.mookcore.Requires;
import mooklabs.mookcore.ToxicWorldGenerator;
import mooklabs.mookcore.toolsandarmor.NArmor;
import mooklabs.nausicaamod.FuelBlock;
import mooklabs.nausicaamod.glider.MetalPlate;
import mooklabs.nausicaamodtech.builder.DireHouseBuilder;
import mooklabs.nausicaamodtech.builder.HologramUi;
import mooklabs.nausicaamodtech.builder.LongshaftBuilder;
import mooklabs.nausicaamodtech.builder.MineshaftBuilder;
import mooklabs.nausicaamodtech.machines.BlockFrictionFurnace;
import mooklabs.nausicaamodtech.machines.BlockWindmill;
import mooklabs.nausicaamodtech.machines.Grinder;
import mooklabs.nausicaamodtech.machines.tileentity.TileEntityFrictionFurnace;
import mooklabs.nausicaamodtech.machines.tileentity.TileEntityGrinder;
import mooklabs.nausicaamodtech.proxy.CommonProxy;
import mooklabs.nausicaamodtech.tube.BlockRedstoneTimer;
import mooklabs.nausicaamodtech.tube.Tube;
import mooklabs.nausicaamodtech.tube.Wire;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.EnumHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bcblocks.bettertable.viffy.BetterCraftingHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

// {{things to do!
/* //TODO SO MUCH STUFF
 * classes i may want to use
 * VillagerRegistry
 * GuiModOptionList
 * GuiIngameModOptions
 * ModMetadata
 * FMLClientHandler**main forge class??** */// }}
/**
 * My favorite part!
 * 
 * @author mooklabs
 */
@Requires(required = { NArmor.class, MetalPlate.class })
@Mod(modid = TechMain.modid, name = "NausicaaModTech", version = TechMain.VERSION, dependencies = "required-after:nausicaamod")
public class TechMain extends MMod {

	public static final String modid = "nausicaamodtech";
	public static final String VERSION = "0.0.02";
	public static final String name = "NausicaaModTech";

	public static final Logger logger = LogManager.getLogger("NausicaaModTech");

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "mooklabs.nausicaamodtech.proxy.ClientProxy", serverSide = "mooklabs.nausicaamodtech.proxy.CommonProxy")
	public static CommonProxy proxy;// THIS IS WEIRD BUT IT WORKS?

	// The instance of your mod that Forge uses.
	@Instance("nausicaamodtech")
	public static TechMain instance = new TechMain();

	public static final int grinderGuiId = 40;
	public static final int furnaceGuiId = 41;

	// inits for ease of changeing
	public final static String itemfold = "nausicaamodtech";

	public static CreativeTabs tabNTech = new CreativeTabs("tabNTech") {

		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(TechMain.grinder);
		}
	};

	// DIAMOND is(33, {3, 8, 6, 3}, 10) iron is {2, 6, 5, 2}
	// String name,int durability, int[] reductionAmounts, int enchantability
	public static ArmorMaterial insaneArmor = EnumHelper.addArmorMaterial("insane", 28, new int[] { 15, 15, 15, 15 }, 22);

	// Armor
	public static final Item insaneHelmet = new NArmor(insaneArmor, 1, 0);
	public static final Item insaneChestplate = new NArmor(insaneArmor, 1, 1);
	public static final Item insaneLegs = new NArmor(insaneArmor, 1, 2);
	public static final Item insaneBoots = new NArmor(insaneArmor, 1, 3);

	public final static Block mineshaftBuilder = new MineshaftBuilder().setCreativeTab(tabNTech);
	public final static Block longshaftBuilder = new LongshaftBuilder().setCreativeTab(tabNTech);
	public final static Block direHouseBuilder = new DireHouseBuilder().setCreativeTab(tabNTech);
	public final static Block hologramUi = new HologramUi().setCreativeTab(tabNTech);

	public final static Item metalPlate = new MetalPlate().setCreativeTab(tabNTech).setUnlocalizedName("metalPlate").setTextureName(TechMain.itemfold + ":metalPlate");
	public final static Block fuelBlock = new FuelBlock().setCreativeTab(tabNTech);
	public final static Item fuelCell = new Item().setCreativeTab(tabNTech).setUnlocalizedName("fuelCell").setTextureName(TechMain.itemfold + ":fuelCell");

	public final static Item schematicHolder = new SchematicHolder().setCreativeTab(tabNTech).setUnlocalizedName("schematicHolder").setTextureName(TechMain.itemfold + ":schematicHolder");

	// parts
	public final static Item thinMetalPlate = new Item().setCreativeTab(tabNTech).setUnlocalizedName("thinMetalPlate").setTextureName(TechMain.itemfold + ":thinMetalPlate");
	public final static Item largeGear = new Item().setCreativeTab(tabNTech).setUnlocalizedName("largeGear").setTextureName(TechMain.itemfold + ":largeGear");
	public final static Item smallGear = new Item().setCreativeTab(tabNTech).setUnlocalizedName("smallGear").setTextureName(TechMain.itemfold + ":smallGear");
	public final static Item belt = new Item().setCreativeTab(tabNTech).setUnlocalizedName("belt").setTextureName(TechMain.itemfold + ":belt");
	public final static Item grindstone = new Item().setCreativeTab(tabNTech).setUnlocalizedName("grindstone").setTextureName(TechMain.itemfold + ":grindstone");
	public final static Item frictionPad = new Item().setCreativeTab(tabNTech).setUnlocalizedName("frictionPad").setTextureName(TechMain.itemfold + ":frictionPad");
	public final static Item insulation = new Item().setCreativeTab(tabNTech).setUnlocalizedName("insulation").setTextureName(TechMain.itemfold + ":insulation");
	public final static Item collector = new Item().setCreativeTab(tabNTech).setUnlocalizedName("collector").setTextureName(TechMain.itemfold + ":collector");

	public final static Block grinder = new Grinder().setCreativeTab(tabNTech);
	public final static Block frictionFurnace = new BlockFrictionFurnace().setCreativeTab(tabNTech).setBlockName("frictionFurnace").setBlockTextureName(TechMain.itemfold + ":frictionFurnace");
	public final static Block windmill = new BlockWindmill().setCreativeTab(tabNTech);

	public final static Block tube = new Tube().setCreativeTab(tabNTech).setBlockName("tube");
	public final static Block timer = new BlockRedstoneTimer().setCreativeTab(tabNTech).setBlockName("redstoneTimer");
	public final static Block wire = new Wire().setCreativeTab(tabNTech).setBlockName("wire");

	// }}
	// {{collapse to access registration

	// worldgen
	// This just changes some stuff, it is applied after chunck gen is your world generation file.
	public static ToxicWorldGenerator modifyWorldGen = new ToxicWorldGenerator();

	// attributes?
	public static final IAttribute research = (new RangedAttribute("generic.reseach", 20.0D, 0.0D, Double.MAX_VALUE)).setDescription("Kindness Level").setShouldWatch(true);// MAYBE

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		init(this.name);

		itemBlockNameReg();

		// {{/////////////////////////////ITEMSTACKS////////////////////////////////////

		// vanilla
		ItemStack ironStack = new ItemStack(Items.iron_ingot);
		ItemStack ironStack6 = new ItemStack(Items.iron_ingot, 6);
		ItemStack stickStack = new ItemStack(Items.stick);
		ItemStack woodStack = new ItemStack(Blocks.planks, 4, 2);// birch planks
		// //////////////////////Nausicaa stacks////////////////////////////////

		// items
		ItemStack metalPlateStack = new ItemStack(metalPlate);

		// ____Stack.addEnchantment(Enchantment.sharpness, 1);

		// armor stacks
		ItemStack shellHelmetStack = new ItemStack(insaneHelmet);
		ItemStack shellChestplateStack = new ItemStack(insaneChestplate);
		ItemStack shellLegsStack = new ItemStack(insaneLegs);
		ItemStack shellBootsStack = new ItemStack(insaneBoots);

		// }}

		// {{/////////////////////////////RECIPIES////////////////////////////////////

		// ///////////////shapless recipies//////////////////
		// GameRegistry.addShapelessRecipe([output],[ingrediants],[moreingrdiants etc]);

		// //////////////////////////////shaped///////////////
		// GameRegistry.addRecipe(woodStack, " d ", " d ", " d ", 'd', logStack);

		// }}

		// {{//////////////////////////Smelting recipes////////////////////////////////////
		// GameRegistry.addSmelting(unfiredCeramicHelmetStack, insaneHelmetStack, 0.0f);

		// }}

		// Inventory tabs????
		// TODO ??????????????
		/* EntityPlayer player = Minecraft.getMinecraft().thePlayer; GuiInventory inventory = new GuiInventory(player); TabRegistry.addTabsToInventory(inventory); */

	}

	private GuiHandlerNausicaaTech guiHandlerNausicaaTech = new GuiHandlerNausicaaTech();
	private BetterCraftingHandler betterCraftingHandler = new BetterCraftingHandler();

	@EventHandler
	public void load(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandlerNausicaaTech);

		// TODO nei stuff API.registerRecipeHandler(betterCraftingHandler);

		proxy.registerRenderers();// does more than just renders :/

		logger.info("\n************************\nWelcome to NausicaaModTech!!!\nHave fun!\n***************************");
		GameRegistry.registerTileEntity(TileEntityGrinder.class, "NausicaaTileEntityGrinder");
		GameRegistry.registerTileEntity(TileEntityFrictionFurnace.class, "NausicaaTileEntityFurnace");

		// Generation
		// CRIT GameRegistry.registerWorldGenerator(oreManager);DO want

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

	private void itemBlockNameReg() {

		// }}
		// {{ registation
		registerItem(schematicHolder);

		registerItem(thinMetalPlate);
		registerItem(smallGear);
		registerItem(largeGear);
		registerItem(belt);
		registerItem(grindstone);
		registerItem(frictionPad);
		registerItem(collector);
		registerItem(insulation);

		registerBlock(frictionFurnace);
		registerBlock(grinder, "Grinder");
		registerBlock(tube);
		registerBlock(timer);
		registerBlock(windmill);
		registerBlock(wire);

		// }}

		// {{ ///////blocks that do stuff/////////
		registerBlock(fuelBlock, "Fuel Block");
		registerBlock(mineshaftBuilder, "Mineshaft Builder");
		registerBlock(longshaftBuilder, "Longshaft Builder");
		registerBlock(direHouseBuilder, "DireHouse20Builder");
		registerBlock(hologramUi, "Hologram Text");

		// }}

		// {{////////////////armor////////////////////////

		registerItem(insaneHelmet, "Insane Helmet");
		registerItem(insaneChestplate, "Insane Chestplate");
		registerItem(insaneLegs, "Insane Greaves");
		registerItem(insaneBoots, "Insane Boots");
		// }}

	}

}
