package mooklabs.laputamod;

import java.util.Iterator;

import mooklabs.laputamod.blocks.VoluciteBlock;
import mooklabs.laputamod.blocks.VoluciteIngot;
import mooklabs.laputamod.blocks.VoluciteOre;
import mooklabs.laputamod.items.NecklaceString;
import mooklabs.laputamod.items.VoluciteNecklace;
import mooklabs.laputamod.items.VolucitePendant;
import mooklabs.laputamod.items.tools.LArmor;
import mooklabs.laputamod.items.tools.LAxe;
import mooklabs.laputamod.items.tools.LPickaxe;
import mooklabs.laputamod.items.tools.LShovel;
import mooklabs.laputamod.items.tools.LSword;
import mooklabs.laputamod.proxy.CommonProxy;
import mooklabs.mookcore.MMod;
import mooklabs.mookcore.ToxicWorldGenerator;
import mooklabs.nausicaamod.proxy.GuiHandlerNausicaa;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.EnumHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;


/**
 * this is the main class for laputamod<br>
 * spacegeek will try to work on it
 * not dependent on nausicaamod
 * @author mooklabs(emiAndVic)
 */
//the @mod annotation tells forge its a mod
@Mod(modid = LapMain.modid, version = LapMain.VERSION, name = LapMain.name)
public class LapMain extends MMod{

	public static final String modid = "laputamod";
	public static final String VERSION = "0.0.01";
	public static final String name = "LaputaMod";
	public final static String itemfold = "laputamod";

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "mooklabs.laputamod.proxy.ClientProxy", serverSide = "mooklabs.laputamod.proxy.CommonProxy")
	public static CommonProxy proxy;

	// The instance of your mod that Forge uses. basically its the object, not the static class
	@Instance("laputamod")
	public static LapMain instance = new LapMain();

	// GUI ids later

	// {{ Creative tab madness!
	public static CreativeTabs tabLap = new CreativeTabs("tabNLaputa") {

		@Override
		public Item getTabIconItem() {
			return LapMain.volucite;
		}
	};
	// }}

	// {{Enums: this makes the tool and armor types and sets their stats! pritty simple really!
	// diamond is(3, 1561, 8.0F, 3.0F, 10) IRON is(2, 250, 6.0F, 2.0F, 14)
	public static ToolMaterial weakVolucite = EnumHelper.addToolMaterial("crystal", 3, 1600, 40F, 7F, 50);

	// DIAMOND is(33, {3, 8, 6, 3}, 10) iron is {2, 6, 5, 2}
	// String name,int durability, int[] reductionAmounts, int enchantability
	public static ArmorMaterial weakVoluciteArmor = EnumHelper.addArmorMaterial("weakVolucite", 50, new int[] {6,10,8,6}, 50);//very enchantable


	// }}

	//{{ Tools
	public static final Item voluciteSword = new LSword(weakVolucite).setCreativeTab(tabLap).setUnlocalizedName("voluciteSword").setTextureName(LapMain.itemfold + ":voluciteSword");
	public static final Item volucitePickaxe = new LPickaxe(weakVolucite).setCreativeTab(tabLap).setUnlocalizedName("volucitePickaxe").setTextureName(LapMain.itemfold + ":volucitePickaxe");
	public static final Item voluciteAxe = new LAxe(weakVolucite).setCreativeTab(tabLap).setUnlocalizedName("voluciteAxe").setTextureName(LapMain.itemfold + ":voluciteAxe");
	public static final Item voluciteShovel = new LShovel(weakVolucite).setCreativeTab(tabLap).setUnlocalizedName("voluciteShovel").setTextureName(LapMain.itemfold + ":voluciteShovel");
	


	// Armor
	public static final Item unfiredCeramicHelmet = new LArmor(weakVoluciteArmor, 1, 0).setCreativeTab(tabLap);
	public static final Item unfiredCeramicChestplate = new LArmor(weakVoluciteArmor, 1, 1).setCreativeTab(tabLap);
	public static final Item unfiredCeramicLegs = new LArmor(weakVoluciteArmor, 1, 2).setCreativeTab(tabLap);
	public static final Item unfiredCeramicBoots = new LArmor(weakVoluciteArmor, 1, 3).setCreativeTab(tabLap);

	// }}

	// {{ blocks and items

	//since constructor isnt public adding "{}" causes it to be a subclass
	public final static Block voluciteBlock = new VoluciteOre().setCreativeTab(tabLap);
	public final static Block solidVoluciteBlock = new VoluciteBlock().setCreativeTab(tabLap);

	public final static Item volucite = new VoluciteIngot().setCreativeTab(tabLap).setUnlocalizedName("voluciteCrystal").setTextureName(LapMain.itemfold + ":voluciteCrystal");
	public final static Item volucitePendant = new VolucitePendant().setCreativeTab(tabLap).setUnlocalizedName("volucitePendant").setTextureName(LapMain.itemfold + ":volucitePendant");
	public final static Item voluciteNecklace = new VoluciteNecklace().setCreativeTab(tabLap).setUnlocalizedName("voluciteNecklace").setTextureName(LapMain.itemfold + ":voluciteNecklace");

	public final static Item string = new NecklaceString().setCreativeTab(tabLap).setUnlocalizedName("necklaceString").setTextureName(LapMain.itemfold + ":necklaceString");
	//public final static Item clasp = new hdfsa().setCreativeTab(tabLap);


	// }}


	// worldgen
	// This just changes some stuff, it is applied after chunck gen is your world generation file.
	public static ToxicWorldGenerator modifyWorldGen = new ToxicWorldGenerator();

	public static final Logger logger = LogManager.getLogger("LaputaMod");


	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		init(this.name);
		proxy.registerRenderers();// does more than just renders

		//quite important
		itemBlockNameReg();

		//{{EVERYTHING
		// {{/////////////////////////////ITEMSTACKS////////////////////////////////////
		//itemstacks are what you have in your inventory slots, it can be up to 64 of an item, with metadata

		ItemStack voluciteStack = new ItemStack(volucite);
		ItemStack voluciteBlockStack = new ItemStack(voluciteBlock);
		ItemStack volucitePendantStack = new ItemStack(volucitePendant);
		ItemStack voluciteNecklaceStack = new ItemStack(voluciteNecklace);
		ItemStack necklaceStringStack = new ItemStack(string);



		// }}
		// }}



		// {{/////////////////////////////RECIPES////////////////////////////////////

		/**			shapeless recipes			*/
		// GameRegistry.addShapelessRecipe([output],[ingredients],[more ingredients etc]);

		GameRegistry.addShapelessRecipe(voluciteNecklaceStack, volucitePendantStack, necklaceStringStack);

		/**			shaped   recipes			*/

		//This makes a diamond Pick
		//GameRegistry.addRecipe(diamondPickStack, "ddd", " s ", " s ", 'd', diamondStack, 's', stickStack);

		GameRegistry.addRecipe(volucitePendantStack, " x ", "x x", " x ", 'x', volucite);


		//SMELTING
		//GameRegistry.addSmelting(inputStack, outputStack, float valueOfExpFromSmelting);

		GameRegistry.addSmelting(voluciteBlockStack, voluciteStack, 5);
		//}}


	}


	GuiHandlerNausicaa guiHandler = new GuiHandlerNausicaa();

	@EventHandler
	public void load(FMLInitializationEvent event) {





		logger.info("\n************************\nWelcome to LaputaMod!!!\nBlow up the World!...\n..if you can!\n***************************");




		// {{entities

		BiomeGenBase[] biomesToSpawnIn = { BiomeGenBase.forest, BiomeGenBase.jungle, BiomeGenBase.desert, BiomeGenBase.taiga };

		//registerEntity(Ohmu.class, "Ohmu", 0xeaeaea, 0x111111);
		//addSpawn(Ohmu.class, 1, 1, 1, biomesToSpawnIn);

		// }}

	}

	// /////////////Mobs!////////////

	/**
	 * @param entityClass a Entity__.class
	 * @param entityName name of entity
	 * @param the color of egg
	 */
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


	}

	private void itemBlockNameReg() {


		// {{ block registration
		registerBlock(voluciteBlock, "Infused Stone");

		registerBlock(solidVoluciteBlock, "Solid Volucite Block");//very powerful!


		//more blocks


		// }}

		// {{ items
		registerItem(string, "String");
		registerItem(volucitePendant, "Volucite Pendant");
		registerItem(voluciteNecklace, "Volucite Necklace");
		registerItem(volucite, "Volucite Crystal");


		//more items

		// {{//////////////tools////////////////////////////////////
		registerItem(voluciteSword, "Volucite Sword");
		registerItem(volucitePickaxe, "Volucite Pickaxe");
		registerItem(voluciteAxe, "Volucite Axe");
		registerItem(voluciteShovel, "Volucite Shovel");

		// }}

		// {{////////////////armor////////////////////////

		registerItem(unfiredCeramicHelmet, "Volucite Helmet");
		registerItem(unfiredCeramicChestplate, "Volucite Chestplate");
		registerItem(unfiredCeramicLegs, "Volucite Greaves");
		registerItem(unfiredCeramicBoots, "Volucite Boots");



		// }}
		logger.warn("Don't Let tinkers Take over! ");
		FMLInterModComms.sendMessage(LapMain.modid, "boo", "Release the Omhu!");
		logger.warn("*menacingly* I'll use the Crystal.");

	}

	// ///////////block reg again////////////
	public static void registerBlock(Block block, String name) {
		///tells forge(and mc) that the block exists
		GameRegistry.registerBlock(block, block.getUnlocalizedName());
		//might set the name
		LanguageRegistry.addName(block, name);
	}

	public static void registerItem(Item item, String name) {
		GameRegistry.registerItem(item, modid + item.getUnlocalizedName());
		LanguageRegistry.addName(item, name);
	}

	/** this is more just for future reference than anything else
	 * you dont need to understand it
	 */
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


}
