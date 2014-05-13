package mcp.mobius.waila.api;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public abstract interface IWailaEntityAccessor
{
  public abstract World getWorld();

  public abstract EntityPlayer getPlayer();

  public abstract Entity getEntity();

  public abstract MovingObjectPosition getPosition();

  public abstract Vec3 getRenderingPosition();

  public abstract NBTTagCompound getNBTData();

  public abstract int getNBTInteger(NBTTagCompound paramNBTTagCompound, String paramString);

  public abstract double getPartialFrame();
}