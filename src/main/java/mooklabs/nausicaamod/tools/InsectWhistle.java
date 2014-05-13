package mooklabs.nausicaamod.tools;

import mooklabs.nausicaamod.Main;
import mooklabs.nausicaamod.models.RenderLine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;


public class InsectWhistle extends Item {

	public InsectWhistle() {
		super();
		this.setUnlocalizedName("insectWhistle");
		this.setTextureName(Main.itemfold + ":insectWhistle");

	}
	@Override
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     * When i used onItemUse it didnt set mobs on fire
     */
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
		player.addChatMessage(new ChatComponentText("{test} trying to render laser"));
		RenderLine.renderLaser(1,1,1,4,4,4);
		return itemStack;
    }
}
