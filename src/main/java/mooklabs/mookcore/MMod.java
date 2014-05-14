package mooklabs.mookcore;

import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;


public  class MMod {


    public static String modid = "MMod";
	public final String VERSION ="0";
	public final String name = 	"MMod";
    
    public static Logger logger;
    
    
    public void init(String name){
    	logger = LogManager.getLogger(name);
    }
    
	
	@EventHandler
	public void messageRecieve(IMCEvent event) {
		
		   Iterator<IMCMessage> itr = event.getMessages().iterator();
	      while(itr.hasNext()) {
	         IMCMessage element = itr.next();
	         logger.info("Sender: " + element.getSender() + "   Value: " + element.getStringValue() + " ");
	      } 
	      
	}
	
	//{{ ///////////block reg methods////////////
		public static void registerBlock(Block block) {
			GameRegistry.registerBlock(block,modid + block.getUnlocalizedName());
		}
		public static void registerBlock(Block block,String unused) {
			GameRegistry.registerBlock(block,modid + block.getUnlocalizedName());
			LanguageRegistry.addName(block, unused);
		}
		public static void registerItem(Item item) {
			GameRegistry.registerItem(item,  modid + item. getUnlocalizedName());
			}
		public static void registerItem(Item item,String unused) {
			GameRegistry.registerItem(item,  modid + item. getUnlocalizedName());
			LanguageRegistry.addName(item, unused);
			}
	//}}
		
		//{{/////////////Mobs!////////////
		public void registerEntity(Class<? extends Entity> entityClass, String entityName, int bkEggColor, int fgEggColor) {
			int id = EntityRegistry.findGlobalUniqueEntityId();

			EntityRegistry.registerGlobalEntityID(entityClass, entityName, id);
			EntityList.entityEggs.put(Integer.valueOf(id), new EntityEggInfo(id, bkEggColor, fgEggColor));
		}

		public void addSpawn(Class<? extends EntityLiving> entityClass, int spawnProb, int min, int max, BiomeGenBase[] biomes) {
			if (spawnProb > 0) {
				EntityRegistry.addSpawn(entityClass, spawnProb, min, max, EnumCreatureType.creature, biomes);
			}
		}//}}

}
