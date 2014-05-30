package mooklabs.nausicaamod;

import mooklabs.nausicaamod.inventorytab.NausicaaArmorExtended;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import java.lang.ref.WeakReference;

public class ExtendedPlayer implements IExtendedEntityProperties {

	/* Here I create a constant EXT_PROP_NAME for this class of properties. You need a unique name for every instance of IExtendedEntityProperties you make, and doing it at
	 * the top of each class as a constant makes it very easy to organize and avoid typos. It's easiest to keep the same constant name in every class, as it will be
	 * distinguished by the class name: ExtendedPlayer.EXT_PROP_NAME vs. ExtendedEntity.EXT_PROP_NAME Note that a single entity can have multiple extended properties, so each
	 * property should have a unique name. Try to come up with something more unique than the tutorial example. */
	public final static String EXT_PROP_NAME = "NausicaaExtendedPlayer";

	// I always include the entity to which the properties belong for easy access
	// It's final because we won't be changing which player it is
	private final EntityPlayer player;
	
	// Declare other variables you want to add here

	// We're adding mana to the player, so we'll need current and max mana
	private int maxKindness;

	
	
	public static final int KINDNESS_WATCHER = 20;

	

	
	
	
	
	public ExtendedPlayer(EntityPlayer player) {		
		this.player = player;
		this.maxKindness = 2000;
		// This adds the new object at our defined index and sets the value to max mana,
		// since we should have full mana when first constructing
		this.player.getDataWatcher().addObject(KINDNESS_WATCHER, this.maxKindness);
	}

	/**
	 * Used to register these extended properties for the player during EntityConstructing event This method is for convenience only; it will make your code look nicer
	 */
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer(player));

	}

	/**
	 * Returns ExtendedPlayer properties for player This method is for convenience only; it will make your code look nicer
	 */
	public static final ExtendedPlayer get(EntityPlayer player) {
		return (ExtendedPlayer) player.getExtendedProperties(EXT_PROP_NAME);
	}

	// Save any custom data that needs saving here
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		// We need to create a new tag compound that will save everything for our Extended Properties
		NBTTagCompound properties = new NBTTagCompound();
		
		// We only have 2 variables currently; save them both to the new tag
		properties.setInteger("CurrentKindness", this.player.getDataWatcher().getWatchableObjectInt(KINDNESS_WATCHER));
		properties.setInteger("maxKindness", this.maxKindness);

		/* Now add our custom tag to the player's tag with a unique name (our property's name). This will allow you to save multiple types of properties and distinguish
		 * between them. If you only have one type, it isn't as important, but it will still avoid conflicts between your tag names and vanilla tag names. For instance, if you
		 * add some "Items" tag, that will conflict with vanilla. Not good. So just use a unique tag name. */
		compound.setTag(EXT_PROP_NAME, properties);
	}

	// Load whatever data you saved
	@Override
	public void loadNBTData(NBTTagCompound compound) {
		// Here we fetch the unique tag compound we set for this class of Extended Properties
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);

		// Get our data from the custom tag compound
		this.player.getDataWatcher().updateObject(KINDNESS_WATCHER, properties.getInteger("CurrentKindness"));
		this.maxKindness = properties.getInteger("maxKindness");
		// Just so you know it's working, add this line:
		// System.out.println("[TUT PROPS] Mana from NBT: " + this.currentMana + "/" + this.maxKindness);
	}

	/* I personally have yet to find a use for this method. If you know of any, please let me know and I'll add it in! */
	@Override
	public void init(Entity entity, World world) {
	}

	/* That's it for the IExtendedEntityProperties methods, but we need to add a few of our own in order to interact with our new variables. For now, let's make one method to
	 * consume mana and one to replenish it. */

	// This method gets a little messier, unfortunately, due to the unwieldy length of getting information
	// from DataWatcher vs. referencing a local variable, so we'll create a local variable instead
	public final boolean consumeKindness(int amount) {
		// This variable makes it easier to write the rest of the method
		int kindness = this.player.getDataWatcher().getWatchableObjectInt(KINDNESS_WATCHER);

		// These two lines are the same as before
		boolean sufficient = amount <= kindness;
		kindness -= (amount < kindness ? amount : kindness);

		// Update the data watcher object with the new value
		this.player.getDataWatcher().updateObject(KINDNESS_WATCHER, kindness);

		// note that we no longer need to call 'sync()' to update the client

		return sufficient;
	}

	// This method cleans up nicely - no more call to 'sync()' means no custom packet to handle!
	public final void replenishKindness() {
		this.player.getDataWatcher().updateObject(KINDNESS_WATCHER, this.maxKindness);
	}

	// Simple change
	public final int getCurrentKindness() {
		return this.player.getDataWatcher().getWatchableObjectInt(KINDNESS_WATCHER);
	}

	// Again, use 'updateObject'; we don't need to 'sync()' here anymore
	public final void setCurrentKindness(int amount) {
		this.player.getDataWatcher().updateObject(KINDNESS_WATCHER, (amount < this.maxKindness ? amount : this.maxKindness));
	}
	// This is the one i use
	public final void changeKindness(int amount) {
		int kindness = this.player.getDataWatcher().getWatchableObjectInt(KINDNESS_WATCHER);
		this.player.getDataWatcher().updateObject(KINDNESS_WATCHER, amount + kindness);
		}
	

	@Override
	public String toString() {
		return "Kindness: " + this.getCurrentKindness();
	}

}