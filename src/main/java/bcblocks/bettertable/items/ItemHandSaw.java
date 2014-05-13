package bcblocks.bettertable.items;

import bcblocks.bettertable.BetterTable;
import net.minecraft.item.Item;


public class ItemHandSaw extends Item
{

	public ItemHandSaw()
	{
		super();
		this.maxStackSize = 1;
		this.setMaxDamage(32);
        //this.setContainerItem(this);
		this.setUnlocalizedName("handsaw");
		this.setTextureName(BetterTable.modid+":handsaw");

		
	}

	
}