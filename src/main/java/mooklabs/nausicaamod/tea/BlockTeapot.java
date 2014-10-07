package mooklabs.nausicaamod.tea;

import java.util.List;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTeapot extends Block {

	public BlockTeapot() {
		super(Material.glass);
	}

	public int damageDropped(int par1) {
		return par1;
	}

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[Teas.values().length-1];

		for (int i = 0; i < icons.length; i++) {
			icons[i] = par1IconRegister.registerIcon(Main.modid + ":" + (this.getUnlocalizedName().substring(5)) + Teas.teaMap.get(i).name);
		}
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		return icons[par2];
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs par2CreativeTabs, List par3List) {
		for (int var4 = 0; var4 < Teas.values().length-1 ; ++var4) {
			par3List.add(new ItemStack(item, 1, var4));
		}
	}
}
