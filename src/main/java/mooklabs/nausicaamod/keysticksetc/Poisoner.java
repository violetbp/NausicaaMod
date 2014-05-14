package mooklabs.nausicaamod.keysticksetc;

import java.util.ArrayList;

import mooklabs.mookcore.Unused;
import mooklabs.nausicaamod.ExtendedPlayer;
import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;

public class Poisoner extends MooklabTickHandler {
	

	public final static boolean debug = false;
	
    private EntityPlayer player;

    private int poisonId = Potion.poison.id;// poison
    private int weaknessId = Potion.weakness.id;// weakness
    
    public Poisoner(Minecraft mc) {
		super(mc);
	}
    
    public int nextTickSpacing()
    {
      return 20; //runs each second.//TODO IMPliment this
    }
    
    //public static DamageSource. forest = new DamageSource.("forest");
    private ArrayList<Block> block = new ArrayList<Block>();// holds list of blocks that will look for
    {
	block.add(Main.poisonLeaves);
	block.add(Main.poisonLog);//if you are near these block, you be poisoned!
	block.add(Main.puffball);
	//block.add(Main.sporer);

    }

    int armorSlot = 3;//slot to check for armor, 3 is helmet
  
    @Override
    public void tickStart() {
    }
    
    @Override
    public void tickEnd() {	
    	player = FMLClientHandler.instance().getClient().thePlayer;
	if (player != null)
	    findBlock((int) player.posX, (int) player.posY, (int) player.posZ,5);

	
    }

   
    @Unused
    public String getLabel() {
	return "NausicaaMod_thePlayer";
    }

    public void findBlock(int x, int y, int z, int radius) {
    	Minecraft mc = Minecraft.getMinecraft();
    	EntityPlayer player = mc.thePlayer;
    	World world = mc.theWorld;

    	int blocksBelow = 3;// scans __ blocks below player
    	int blocksAbove = 1;// scans ___ above player
    	x -= radius;
    	y -= blocksBelow;
    	z -= radius;
    	for (int k = 0; k < blocksBelow + blocksAbove; k++)// y-val 
    	{
    		for (int j = 0; j < radius * 2; j++)// z-val
    		{
    			for (int i = 0; i < radius * 2; i++)// x-val
    			{
    				Block currblockid = world.getBlock(x + i, y + k, z + j);
					

    				for (Block currtempblock : block) {		
    					if(debug){
							if (currblockid != null)
								System.out.println(currblockid);
								System.out.println(currtempblock);}

    					if (currtempblock == currblockid) {//past here=poisoned
    						if(debug)System.out.println("poisontrue");
    						//{{kindness factoring
    						ExtendedPlayer props = ExtendedPlayer.get(player);
    						props.changeKindness(1);
    						//}}
    						if (player.getCurrentArmor(armorSlot) == null) {   							
    							player.addPotionEffect(new PotionEffect(Potion.poison.getId(), 7 * 20, 0));// *20 is for the 20ticks/sec cycle
    							player.addPotionEffect(new PotionEffect(Math.random()<0.01 ? Potion.blindness.getId():Potion.weakness.getId(), 2 * 20, 1));//switches between weekness and blindness :)
    						} else if (player.getCurrentArmor(armorSlot).getItem() != Main.ceramicHelmet) {
    							if(debug)System.out.println(player.getCurrentArmor(armorSlot));
    							
    							player.addPotionEffect(new PotionEffect(Potion.poison.getId(), 5 * 20, 1));
    							player.addPotionEffect(new PotionEffect(Math.random()<0.01 ? Potion.blindness.getId():Potion.weakness.getId(), 2 * 20, 1));
    							return;

    						} else if (player.getCurrentArmor(armorSlot).getItem() == Main.ceramicHelmet) {//cure effects if away from
    							player.removePotionEffect(Potion.weakness.getId());//TODO make it only prevent effects
    							player.removePotionEffect(Potion.harm.getId());//if have helmet on remove effects
    							player.removePotionEffect(Potion.blindness.getId());//THIS WILL MAKE IT SO YOU **CANNOT** BE AFFECTE DBY THESE POTIONS!
    							return;
    						} 
    						//player.removePotionEffect(Potion.weakness.getId());
    					}



    					
    				}

    			}
    		}

    	}
    	//player.curePotionEffects(new ItemStack(Main.diamondStick));//cure effects if away from
    }

}
