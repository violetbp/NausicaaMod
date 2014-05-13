package bcblocks.bettertable.items;

import bcblocks.bettertable.BetterTable;
import net.minecraft.item.Item;

public class ItemNail extends Item
{

	public ItemNail()
	{
		super();
		this.maxStackSize = 64;
		this.setTextureName(BetterTable.modid+":nail");
		this.setUnlocalizedName("nail");

	}
	
}