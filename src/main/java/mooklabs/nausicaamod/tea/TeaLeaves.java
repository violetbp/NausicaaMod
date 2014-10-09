package mooklabs.nausicaamod.tea;

import java.util.List;

import mooklabs.mookcore.Unused;
import mooklabs.nausicaamod.Main;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * leaves
 * 
 * @author emilynewman
 */
public class TeaLeaves extends Item {

	public TeaLeaves() {
		super();
		setUnlocalizedName("teaLeaves");
		setTextureName(Main.itemfold + ":tea");
		this.setMaxStackSize(16);
		this.setHasSubtypes(true);

	}
	
	
	//Teas.teaMap.get(i).name - this returns the name at metadata i

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[Teas.values().length-1];
		
		for (int i = 0; i < icons.length; i++) {
			icons[i] = par1IconRegister.registerIcon(Main.modid + ":" + (this.getUnlocalizedName().substring(5)) + Teas.teaMap.get(i).name);
		}
	}

	public String getUnlocaluuiizedName(ItemStack par1ItemStack) {
		int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
		return super.getUnlocalizedName() + "." + Teas.teaMap.get(i).name;
	}

	public IIcon getIconFromDamage(int par1) {
		return icons[par1];
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs creativeTabs, List list) {
		for (int x = 0; x < Teas.values().length; x++) {
			list.add(new ItemStack(this, 1, x));
		}
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
		if (itemStack.stackTagCompound != null) {
			// taken from god warrior need to change
			String owner = itemStack.stackTagCompound.getString("owner");
			list.add(EnumChatFormatting.GREEN + "Owner:" + owner);

		} else {// if has tags
			list.add(EnumChatFormatting.BLUE + "Unregisted God Warrior Controler!");
			list.add(EnumChatFormatting.RED + "Right click on ground to register");
		}

	}

	@Deprecated
	@Unused
	public void register(ItemStack itemStack, EntityPlayer player) {
		if (itemStack.stackTagCompound == null) itemStack.stackTagCompound = new NBTTagCompound();// create tag

		// then nbt way of doing this
		itemStack.stackTagCompound.setBoolean("", false);
		itemStack.stackTagCompound.setString("", player.getCommandSenderName());// register to name

	}
}
