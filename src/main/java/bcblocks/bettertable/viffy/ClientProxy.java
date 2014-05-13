package bcblocks.bettertable.viffy;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderThings()
	{
		//ClientRegistry.bindTileEntitySpecialRenderer(TileEntityForge.class, new TileEntityForgeRenderer());
		//MinecraftForgeClient.registerItemRenderer(BetterCraft.forgeID, new ItemForgeRenderer());
	}
	
	@Override
	public void registerTileEntitySpecialRenderer()
	{
    	//ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpiritTable.class, new RenderSpiritTable());
	}
	
}