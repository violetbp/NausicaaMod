package mooklabs.nausicaamod;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;


public class Flaregun extends ItemBow {
	
	public static final String[] bowPullIconNameArray = new String[] {""};
	public Flaregun(){
		this.setTextureName(Main.itemfold + ":" + "flareGun" );
		this.setUnlocalizedName("flareGun");
	}
	@Override
	/**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer player, int par4)
    {
        int j = 72000;//this.getMaxItemUseDuration(par1ItemStack) - par4;
        //player.addChatMessage(new ChatComponentText( par4+""));//REF for chat

        ArrowLooseEvent event = new ArrowLooseEvent(player, par1ItemStack, j);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return;
        }
        j = event.charge;

        boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

        if (flag || player.inventory.hasItem(Main.flare))
        {
            float f = j / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;

            if (f < 0.1D)
            {
                return;
            }

            if (f > 1.0F)
            {
                f = 1.0F;
            }

            FlaregunBullet flareGunBullet = new FlaregunBullet(par2World, player, f * 2.0F);

            if (f == 1.0F)
            {
                flareGunBullet.setIsCritical(true);
            }

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);

            if (k > 0)
            {
                flareGunBullet.setDamage(flareGunBullet.getDamage() + k * 0.5D + 0.5D);
            }

            int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);

            if (l > 0)
            {
                flareGunBullet.setKnockbackStrength(l);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
            {
                flareGunBullet.setFire(100);
            }

            par1ItemStack.damageItem(1, player);//TODO make it not break?
            par2World.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            if (flag)
            {
                 flareGunBullet.canBePickedUp = 2;//if on creative cant pick up arrows
            }
            else
            {
                player.inventory.consumeInventoryItem(Main.flare);//if on creative dont use arrows
            }

            if (!par2World.isRemote)
            {
                par2World.spawnEntityInWorld(flareGunBullet);
            }
        }
    }
	@Override
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return event.result;
        }

        if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(Main.flare))
        {
            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        }

        return par1ItemStack;
    }
	
    @Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(this.getIconString() + "_standby");
        
    }
	 @Override
	public void addInformation(ItemStack itemStack, EntityPlayer player,
		    List dataList, boolean bool) {
		//dataList.add("");//REF add information

	    }
}
