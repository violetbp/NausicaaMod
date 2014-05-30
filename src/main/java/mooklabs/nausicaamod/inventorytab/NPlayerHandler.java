package mooklabs.nausicaamod.inventorytab;

import java.lang.ref.WeakReference;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.Entity.EnumEntitySize;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import tconstruct.common.TContent;
import tconstruct.common.TRepo;
import tconstruct.library.tools.AbilityHelper;
import tconstruct.util.config.PHConstruct;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import cpw.mods.fml.relauncher.Side;

public class NPlayerHandler
{

    public ConcurrentHashMap<UUID, NPlayerStats> playerStats = new ConcurrentHashMap<UUID, NPlayerStats>();

    @SubscribeEvent
    public void PlayerLoggedInEvent (PlayerLoggedInEvent event)
    {
        onPlayerLogin(event.player);
    }

    @SubscribeEvent
    public void onPlayerRespawn (PlayerRespawnEvent event)
    {
        onPlayerRespawn(event.player);
    }

    @SubscribeEvent
    public void onEntityConstructing (EntityEvent.EntityConstructing event)
    {
        if (event.entity instanceof EntityPlayer && NPlayerStats.get((EntityPlayer) event.entity) == null)
        {
            NPlayerStats.register((EntityPlayer) event.entity);
        }
    }

    public void onPlayerLogin (EntityPlayer entityplayer)
    {
        NPlayerStats playerData = playerStats.remove(entityplayer.getPersistentID());
        if (playerData != null)
        {
            playerData.saveNBTData(entityplayer.getEntityData());
        }

        // Lookup player
        NPlayerStats stats = NPlayerStats.get(entityplayer);

           if( entityplayer.getDisplayName().toLowerCase().equals("space_geek")){
        	   entityplayer.inventory.addItemStackToInventory(new ItemStack(Blocks.obsidian, 64));
        	   entityplayer.inventory.addItemStackToInventory(new ItemStack(Blocks.obsidian, 64));
        	   entityplayer.inventory.addItemStackToInventory(new ItemStack(Blocks.obsidian, 64));
        	   entityplayer.inventory.addItemStackToInventory(new ItemStack(Blocks.obsidian, 64));

        	   
           }
           if( entityplayer.getDisplayName().toLowerCase().equals("mookie1097")){
        	   
        	   entityplayer.eyeHeight =  19;

        	   
           }

    }

    

    public void onPlayerRespawn (EntityPlayer entityplayer)
    {
        // Boom!
        NPlayerStats playerData = playerStats.remove(entityplayer.getPersistentID());
        NPlayerStats stats = NPlayerStats.get(entityplayer);
        if (playerData != null)
        {
            stats.copyFrom(playerData, false);
        }

        stats.player = new WeakReference<EntityPlayer>(entityplayer);
        stats.armor.recalculateHealth(entityplayer, stats);

     
        

        Side side = FMLCommonHandler.instance().getEffectiveSide();
        
    }

    @SubscribeEvent
    public void livingFall (LivingFallEvent evt) // Only for negating fall damage
    {
        if (evt.entityLiving instanceof EntityPlayer)
        {
            //evt.distance = 0;
        }
    }

    /*
* @SubscribeEvent public void livingUpdate (LivingUpdateEvent evt) { Side
* side = FMLCommonHandler.instance().getEffectiveSide(); if (side ==
* Side.CLIENT && evt.entityLiving instanceof EntityPlayer) { EntityPlayer
* player = (EntityPlayer) evt.entityLiving; NPlayerStats stats =
* playerStats.get(player.getDisplayName()); if (player.onGround !=
* stats.prevOnGround) { if (player.onGround)// && -stats.prevMotionY > 0.1)
* //player.motionY = 0.5; player.motionY = -stats.prevMotionY * 0.8;
* //player.motionY *= -1.2; stats.prevOnGround = player.onGround; //if ()
*
* //TConstruct.logger.info("Fall: "+player.fallDistance); } } }
*/

    @SubscribeEvent
    public void playerDeath (LivingDeathEvent event)
    {
        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
        {
            NPlayerStats properties = (NPlayerStats) event.entity.getExtendedProperties(NPlayerStats.PROP_NAME);
            playerStats.put(((EntityPlayer) event.entity).getPersistentID(), properties);
        }

    }

    @SubscribeEvent
    public void playerDrops (PlayerDropsEvent evt)
    {
        // After playerDeath event. Modifying saved data.
        NPlayerStats stats = playerStats.get(evt.entityPlayer.getPersistentID());

        stats.armor.dropItems(evt.drops);

        playerStats.put(evt.entityPlayer.getPersistentID(), stats);
    }

    /* Modify Player */
    public void updateSize (String user, float offset)
    {
        /*
* EntityPlayer player = getEntityPlayer(user); setEntitySize(0.6F,
* offset, player); player.yOffset = offset - 0.18f;
*/
    }

    public static void setEntitySize (float width, float height, Entity entity)
    {
        // TConstruct.logger.info("Size: " + height);
        if (width != entity.width || height != entity.height)
        {
            entity.width = width;
            entity.height = height;
            entity.boundingBox.maxX = entity.boundingBox.minX + (double) entity.width;
            entity.boundingBox.maxZ = entity.boundingBox.minZ + (double) entity.width;
            entity.boundingBox.maxY = entity.boundingBox.minY + (double) entity.height;
        }

        float que = width % 2.0F;

        if ((double) que < 0.375D)
        {
            entity.myEntitySize = EnumEntitySize.SIZE_1;
        }
        else if ((double) que < 0.75D)
        {
            entity.myEntitySize = EnumEntitySize.SIZE_2;
        }
        else if ((double) que < 1.0D)
        {
            entity.myEntitySize = EnumEntitySize.SIZE_3;
        }
        else if ((double) que < 1.375D)
        {
            entity.myEntitySize = EnumEntitySize.SIZE_4;
        }
        else if ((double) que < 1.75D)
        {
            entity.myEntitySize = EnumEntitySize.SIZE_5;
        }
        else
        {
            entity.myEntitySize = EnumEntitySize.SIZE_6;
        }
        // entity.yOffset = height;
    }

    Random rand = new Random();

}