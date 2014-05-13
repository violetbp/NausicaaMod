package mcp.mobius.waila.api;

import java.util.List;
import net.minecraft.entity.Entity;

public abstract interface IWailaEntityProvider
{
  public abstract Entity getWailaOverride(IWailaEntityAccessor paramIWailaEntityAccessor, IWailaConfigHandler paramIWailaConfigHandler);

  public abstract List<String> getWailaHead(Entity paramEntity, List<String> paramList, IWailaEntityAccessor paramIWailaEntityAccessor, IWailaConfigHandler paramIWailaConfigHandler);

  public abstract List<String> getWailaBody(Entity paramEntity, List<String> paramList, IWailaEntityAccessor paramIWailaEntityAccessor, IWailaConfigHandler paramIWailaConfigHandler);

  public abstract List<String> getWailaTail(Entity paramEntity, List<String> paramList, IWailaEntityAccessor paramIWailaEntityAccessor, IWailaConfigHandler paramIWailaConfigHandler);
}