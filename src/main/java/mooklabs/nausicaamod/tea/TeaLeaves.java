package mooklabs.nausicaamod.tea;

import java.util.List;

import mooklabs.mookcore.Unused;
import mooklabs.nausicaamod.Main;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

/**
 * leaves
 * @author emilynewman
 */
public class TeaLeaves extends Item{

	public TeaLeaves(){
        super();
        setUnlocalizedName("tea");
		setTextureName(Main.itemfold + ":tea");
		this.setMaxStackSize(16);
		
    }
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
		if (itemStack.stackTagCompound != null) {
			//taken from god warrior need to change
			String owner = itemStack.stackTagCompound.getString("owner");
			list.add(EnumChatFormatting.GREEN + "Owner:" + owner);

		} else {// if has tags
			list.add(EnumChatFormatting.BLUE + "Unregisted God Warrior Controler!");
			list.add(EnumChatFormatting.RED + "Right click on ground to register");
		}

	}
	
	@Deprecated @Unused
	public void register(ItemStack itemStack, EntityPlayer player) {
		if (itemStack.stackTagCompound == null)
			itemStack.stackTagCompound = new NBTTagCompound();// create tag

		//then nbt way of doing this
		itemStack.stackTagCompound.setBoolean("", false);
		itemStack.stackTagCompound.setString("", player.getCommandSenderName());// register to name

	}
}
