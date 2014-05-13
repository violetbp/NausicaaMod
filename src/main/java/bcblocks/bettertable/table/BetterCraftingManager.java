package bcblocks.bettertable.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


import mooklabs.nausicaamod.Main;
import mooklabs.nausicaamodtech.TechMain;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import bcblocks.bettertable.BetterTable;
import bcblocks.bettertable.tablecare.BetterRecipeSorter;
import bcblocks.bettertable.tablecare.BetterShapedRecipes;
import bcblocks.bettertable.tablecare.BetterShapelessRecipes;
import bcblocks.bettertable.viffy.SchematicCrafting;

public class BetterCraftingManager {

	public static final int WILDCARD_VALUE = Short.MAX_VALUE;
	private static final BetterCraftingManager instance = new BetterCraftingManager();
	public ArrayList<IRecipe> recipes = new ArrayList();

	
	
	public static final BetterCraftingManager getInstance() {
		return instance;
	}

	public BetterCraftingManager() {
		recipes = new ArrayList();
		// Basic Crafts
		Main.debugWrite("Registering Recipies....");
		this.addBuilderShapelessRecipe(new ItemStack(Blocks.planks, 6, 0), new Object[] { new ItemStack(Blocks.log, 1, 0), new ItemStack(BetterTable.handSaw, 1, WILDCARD_VALUE) });
		this.addBuilderShapelessRecipe(new ItemStack(Blocks.planks, 6, 1), new Object[] { new ItemStack(Blocks.log, 1, 1), new ItemStack(BetterTable.handSaw, 1, WILDCARD_VALUE) });
		this.addBuilderShapelessRecipe(new ItemStack(Blocks.planks, 6, 2), new Object[] { new ItemStack(Blocks.log, 1, 2), new ItemStack(BetterTable.handSaw, 1, WILDCARD_VALUE) });
		this.addBuilderShapelessRecipe(new ItemStack(Blocks.planks, 6, 3), new Object[] { new ItemStack(Blocks.log, 1, 3), new ItemStack(BetterTable.handSaw, 1, WILDCARD_VALUE) });
		this.addBuilderShapelessRecipe(new ItemStack(Items.stick, 6), new Object[] { new ItemStack(Blocks.planks, 1, WILDCARD_VALUE), new ItemStack(BetterTable.handSaw, 1, WILDCARD_VALUE) });
		this.createRecipe(new ItemStack(Blocks.torch, 4), "C", "S", 'C', Items.coal, 'S', Items.stick);
		this.createRecipe(new ItemStack(Blocks.crafting_table, 1), new Object[] { "PP", "PP", Character.valueOf('P'), Blocks.planks });
		this.createRecipe(new ItemStack(Blocks.furnace, 1), new Object[] { "CCC", "C C", "CCC", Character.valueOf('C'), Blocks.cobblestone });
		this.createRecipe(new ItemStack(Blocks.chest, 1), new Object[] { "PPP", "P P", "PPP", Character.valueOf('P'), Blocks.planks });

		// Misc
		this.addBuilderShapelessRecipe(new ItemStack(Items.sugar, 1), new Object[] { Items.reeds });
		this.createRecipe(new ItemStack(Items.book, 1), "PPP", 'P', Items.paper);
		this.createRecipe(new ItemStack(Items.paper, 3), new Object[] { "RRR", Character.valueOf('R'), Items.reeds });
		this.createRecipe(new ItemStack(Blocks.lever, 1), new Object[] { "s", "c", Character.valueOf('s'), Items.stick, Character.valueOf('c'), Blocks.cobblestone });
		this.createRecipe(new ItemStack(TechMain.schematicHolder, 1), new Object[] { "s", "c", Character.valueOf('s'), Items.book, 'c', TechMain.thinMetalPlate });

		this.createRecipe(new ItemStack(TechMain.thinMetalPlate, 4), new Object[] { "h", "I", 'I', Items.iron_ingot, 'h', new ItemStack(BetterTable.hammer, 1, WILDCARD_VALUE) });
		this.createRecipe(new ItemStack(TechMain.largeGear, 3), new Object[] { "  I  ", " III ", "IIIII", " III ", "  I  ", Character.valueOf('I'), TechMain.thinMetalPlate });
		this.createRecipe(new ItemStack(TechMain.smallGear, 3), new Object[] { "  I  ", " III ", "  I  ", Character.valueOf('I'), TechMain.thinMetalPlate });

		this.createRecipe(new ItemStack(TechMain.belt, 8), new Object[] { "III", "I I", "I I", "III", Character.valueOf('I'), Items.leather });
		this.createRecipe(new ItemStack(TechMain.grindstone, 1), new Object[] { "IIIII", "IIIII", "IINII", "IIIII", "IIIII", Character.valueOf('I'), Blocks.stone, 'N', Items.iron_ingot });
		this.createRecipe(new ItemStack(TechMain.frictionPad, 2), new Object[] { "IN", Character.valueOf('I'), Blocks.stone, 'N', Items.iron_ingot });// TODO resipe

		// this.createSchematicRecipe(new ItemStack(TechMain.frictionFurnace, 1), new Object[] { "PPPPP", "PGSGP", "PIFIP", "PIFIP", "PPPPP", 'P', TechMain.thinMetalPlate,
		// 'F', TechMain.frictionPad, 'I',
		// TechMain.insulation, 'G', TechMain.largeGear, 'S', TechMain.smallGear });
		this.createSchematicRecipe(new ItemStack(TechMain.frictionFurnace, 1), new Object[] { "PPPPP", "PPPPP", 'P', TechMain.thinMetalPlate, 'F', TechMain.frictionPad, 'I', TechMain.insulation, 'G',
				TechMain.largeGear, 'S', TechMain.smallGear });
		this.createSchematicRecipe(new ItemStack(TechMain.grinder, 1), new Object[] { "PPPPP", "PSGSP", "PIFIP", "PIFIP", "PPPPP", 'P', TechMain.thinMetalPlate, 'F', TechMain.grindstone, 'I',
				TechMain.collector, 'G', TechMain.largeGear, 'S', TechMain.smallGear });

		Collections.sort(this.recipes, new BetterRecipeSorter(this));
		System.out.println(this.recipes.size() + " recipes");

	}

	public BetterShapedRecipes createSchematicRecipe(ItemStack itemStack, Object... par2ArrayOfObj) {

		return this.createRecipe(itemStack, par2ArrayOfObj).addSchematic(itemStack.getItem());
	}

	public BetterShapedRecipes createRecipe(ItemStack par1ItemStack, Object... par2ArrayOfObj) {
		String var3 = "";
		int var4 = 0;
		int var5 = 0;
		int var6 = 0;

		if (par2ArrayOfObj[var4] instanceof String[]) {
			String[] var7 = ((String[]) par2ArrayOfObj[var4++]);

			for (int var8 = 0; var8 < var7.length; ++var8) {
				String var9 = var7[var8];
				++var6;
				var5 = var9.length();
				var3 = var3 + var9;
			}
		} else {
			while (par2ArrayOfObj[var4] instanceof String) {
				String var11 = (String) par2ArrayOfObj[var4++];
				++var6;
				var5 = var11.length();
				var3 = var3 + var11;
			}
		}

		HashMap var12;

		for (var12 = new HashMap(); var4 < par2ArrayOfObj.length; var4 += 2) {
			Character var13 = (Character) par2ArrayOfObj[var4];
			ItemStack var14 = null;

			if (par2ArrayOfObj[var4 + 1] instanceof Item) {
				var14 = new ItemStack((Item) par2ArrayOfObj[var4 + 1]);
			} else if (par2ArrayOfObj[var4 + 1] instanceof Block) {
				var14 = new ItemStack((Block) par2ArrayOfObj[var4 + 1], 1, -1);
			} else if (par2ArrayOfObj[var4 + 1] instanceof ItemStack) {
				var14 = (ItemStack) par2ArrayOfObj[var4 + 1];
			}

			var12.put(var13, var14);
		}

		ItemStack[] var15 = new ItemStack[var5 * var6];

		for (int var16 = 0; var16 < var5 * var6; ++var16) {
			char var10 = var3.charAt(var16);

			if (var12.containsKey(Character.valueOf(var10))) {
				var15[var16] = ((ItemStack) var12.get(Character.valueOf(var10))).copy();
			} else {
				var15[var16] = null;
			}
		}

		BetterShapedRecipes var17 = new BetterShapedRecipes(var5, var6, var15, par1ItemStack);
		this.recipes.add(var17);
		return var17;
	}

	public void addBuilderShapelessRecipe(ItemStack par1ItemStack, Object... par2ArrayOfObj) {
		ArrayList var3 = new ArrayList();
		Object[] var4 = par2ArrayOfObj;
		int var5 = par2ArrayOfObj.length;

		for (int var6 = 0; var6 < var5; ++var6) {
			Object var7 = var4[var6];

			if (var7 instanceof ItemStack) {
				var3.add(((ItemStack) var7).copy());
			} else if (var7 instanceof Item) {
				var3.add(new ItemStack((Item) var7));
			} else {
				if (!(var7 instanceof Block)) {
					throw new RuntimeException("Invalid shapeless recipy!");
				}

				var3.add(new ItemStack((Block) var7));
			}
		}

		this.recipes.add(new BetterShapelessRecipes(par1ItemStack, var3));
	}

	public ItemStack findMatchingRecipe(InventoryCrafting inventoryCrafting, IInventory schematic, World world) {
		int var3 = 0;
		ItemStack itemStack1 = null;
		ItemStack itemStack2 = null;
		int counter;

		for (counter = 0; counter < inventoryCrafting.getSizeInventory(); ++counter) {
			ItemStack itemStackInSlot = inventoryCrafting.getStackInSlot(counter);

			if (itemStackInSlot != null) {
				if (var3 == 0) {
					itemStack1 = itemStackInSlot;
				}

				if (var3 == 1) {
					itemStack2 = itemStackInSlot;
				}

				++var3;
			}
		}

		if (var3 == 2 && itemStack1.getItem() == itemStack2.getItem() && itemStack1.stackSize == 1 && itemStack2.stackSize == 1 && itemStack1.getItem().isRepairable()) {
			Item var11 = itemStack1.getItem();
			int var13 = var11.getMaxDamage() - itemStack1.getItemDamageForDisplay();
			int var8 = var11.getMaxDamage() - itemStack2.getItemDamageForDisplay();
			int var9 = var13 + var8 + var11.getMaxDamage() * 5 / 100;
			int var10 = var11.getMaxDamage() - var9;

			if (var10 < 0) {
				var10 = 0;
			}

			return new ItemStack(itemStack1.getItem(), 1, var10);
		} else {
			for (counter = 0; counter < this.recipes.size(); ++counter) {
				if(this.recipes.get(counter) instanceof BetterShapedRecipes){
				BetterShapedRecipes var12 =  (BetterShapedRecipes)this.recipes.get(counter);
				if (var12.matches(inventoryCrafting,schematic, world)) 
					return var12.getCraftingResult(inventoryCrafting);
				}
				else {
				BetterShapelessRecipes var12 =  (BetterShapelessRecipes)this.recipes.get(counter);
				if (var12.matches(inventoryCrafting,schematic, world)) 
					return var12.getCraftingResult(inventoryCrafting);
				}
				
			}

			return null;
		}
	}

	/**
	 * returns the List<> of all recipes
	 */
	public List getRecipeList() {
		return this.recipes;
	}
}