

package mooklabs.nausicaamod.inventorytab;

import java.lang.ref.WeakReference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;


public class NPlayerStats implements IExtendedEntityProperties
{
    public static final String PROP_NAME = "Nausciaa";

    public WeakReference<EntityPlayer> player;

    
    public NausicaaArmorExtended armor;

    public NPlayerStats()
    {

    }

    public NPlayerStats(EntityPlayer entityplayer)
    {
        this.player = new WeakReference<EntityPlayer>(entityplayer);
        this.armor = new NausicaaArmorExtended();
        this.armor.init(entityplayer);

        
    }

    @Override
    public void saveNBTData (NBTTagCompound compound)
    {
        NBTTagCompound tTag = new NBTTagCompound();
        armor.saveToNBT(tTag);
        compound.setTag(PROP_NAME, tTag);
    }

    @Override
    public void loadNBTData (NBTTagCompound compound)
    {
        NBTTagCompound properties = (NBTTagCompound) compound.getTag(PROP_NAME);

        this.armor.readFromNBT(properties);
        
    }

    @Override
    public void init (Entity entity, World world)
    {
    }

    public void copyFrom(NPlayerStats stats, boolean copyCalc)
    {
        this.armor = stats.armor;
       
    }

    public static final void register (EntityPlayer player)
    {
    	
        player.registerExtendedProperties(NPlayerStats.PROP_NAME, new NPlayerStats(player));
    }

    public static final NPlayerStats get (EntityPlayer player)
    {
        return (NPlayerStats) player.getExtendedProperties(PROP_NAME);
    }

}