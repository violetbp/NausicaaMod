package bcblocks.bettertable.items;

import bcblocks.bettertable.BetterTable;
import net.minecraft.item.Item;

public class ItemHammer extends Item
{

	public ItemHammer()
	{
		
		super();
		this.maxStackSize = 1;
		this.setMaxDamage(32);
        //this.setContainerItem(this);
		this.setUnlocalizedName("hammer");
		this.setTextureName(BetterTable.modid+":workHammer");
		
	}
	
	
	
}