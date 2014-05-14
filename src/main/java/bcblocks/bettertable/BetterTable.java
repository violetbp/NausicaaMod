package bcblocks.bettertable;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import bcblocks.bettertable.items.ItemHammer;
import bcblocks.bettertable.items.ItemHandSaw;
import bcblocks.bettertable.items.ItemNail;
import bcblocks.bettertable.table.BlockBetterTable;
import bcblocks.bettertable.tileentity.TileEntityDeviceCraftingTable;
import bcblocks.bettertable.viffy.BetterCraftingHandler;
import bcblocks.bettertable.viffy.CommonProxy;
import bcblocks.bettertable.viffy.GuiHandlerBetterTable;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * THANK YOU SO MUCH MICROJUNK YOU HAVE HELPED ME ON MORE THAN 5 OCCATIONS
 * 
 * @author microjunk
 * @EditedBy mooklabs
 */
@Mod(modid = BetterTable.modid, name = BetterTable.name, version = "0.2.3", dependencies = "required-after:nausicaamod;required-after:nausicaamodtech")
public class BetterTable {

	public static final String modid = "bettertable";
	public static final String name = "BetterTable";
	@Instance("bettertable")
	public static BetterTable instance;

	public static CreativeTabs tabBetterTable = new CreativeTabs("tabBetterTable") {

		@Override
		public Item getTabIconItem() {
			return Items.arrow;
		}
	};

	private GuiHandlerBetterTable guiHandlerBetterTable = new GuiHandlerBetterTable();

	public static Block betterTable = new BlockBetterTable().setCreativeTab(tabBetterTable);

	public static Item hammer = new ItemHammer().setCreativeTab(tabBetterTable);
	public static Item nail = new ItemNail().setCreativeTab(tabBetterTable);
	public static Item handSaw = new ItemHandSaw().setCreativeTab(tabBetterTable);

	@SidedProxy(clientSide = "bcblocks.bettertable.viffy.ClientProxy", serverSide = "bcblocks.bettertable.viffy.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void PreInit(FMLPreInitializationEvent event) {
		registerItem(hammer);
		registerItem(nail);
		registerItem(handSaw);
		registerBlock(betterTable);

		GameRegistry.registerTileEntity(TileEntityDeviceCraftingTable.class, "betterTableEntity");
		// LanguageRegistry.addName(betterTable, "Better Table");

		LanguageRegistry.addName(hammer, "Hammer");
		LanguageRegistry.addName(nail, "Nail");
		LanguageRegistry.addName(handSaw, "Hand Saw");

	}

	@EventHandler
	public void load(FMLInitializationEvent event) {

		GameRegistry.addRecipe(new ItemStack(betterTable, 1), new Object[] { "HPN", "PSP", " P ", Character.valueOf('P'), Blocks.planks, Character.valueOf('H'), BetterTable.hammer,
				Character.valueOf('N'), BetterTable.nail, Character.valueOf('S'), BetterTable.handSaw });
		GameRegistry.addRecipe(new ItemStack(hammer, 1), new Object[] { " IW", " WI", "W  ", Character.valueOf('I'), Items.iron_ingot, Character.valueOf('W'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(handSaw, 1), new Object[] { "  I", " I ", "W  ", Character.valueOf('I'), Items.iron_ingot, Character.valueOf('W'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(nail, 16), new Object[] { " I ", 'I', Items.iron_ingot });

		FMLCommonHandler.instance().bus().register(new BetterCraftingHandler());
		// GameRegistry.registerCraftingHandler(new BetterCraftingHandler());

		NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandlerBetterTable);

		proxy.registerRenderThings();

	}

	@EventHandler
	public void PostInit(FMLPostInitializationEvent event) {

	}

	public static void registerBlock(Block block) {
		GameRegistry.registerBlock(block, block.getUnlocalizedName());
	}

	public static void registerItem(Item item) {
		GameRegistry.registerItem(item, modid + item.getUnlocalizedName());
	}

}