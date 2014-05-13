package mooklabs.nausicaamod.mobs.npc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import mooklabs.nausicaamod.Main;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowGolem;
import net.minecraft.entity.ai.EntityAILookAtTradePlayer;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIPlay;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITradePlayer;
import net.minecraft.entity.ai.EntityAIVillagerMate;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityNausicaaVillager extends EntityVillager implements
		IMerchant, INpc {

	public EntityNausicaaVillager(World par1World) {
		super(par1World);
		// TODO Auto-generated constructor stub
	}

	@SideOnly(Side.CLIENT)
	public static class RenderNausicaaVillager extends RenderLiving {

		private static final ResourceLocation richMan = new ResourceLocation(
				Main.itemfold, "/textures/mobs/npcRichMan.png");
		private static final ResourceLocation advantagier = new ResourceLocation(
				Main.itemfold, "/textures/mobs/npcAdvantagier.png");
		private static final ResourceLocation commander = new ResourceLocation(
				Main.itemfold, "/textures/mobs/npcCommander.png");
		private static final ResourceLocation butler = new ResourceLocation(
				Main.itemfold, "/textures/mobs/npcButler.png");
		private static final ResourceLocation giantWarrior = new ResourceLocation(
				Main.itemfold, "/textures/mobs/giantWarrior.png");

		public RenderNausicaaVillager(EntityVillager par1EntityVillager,
				ModelNPC modelNPC, float par2) {
			super(modelNPC, par2);// render like a villager

		}

		public RenderNausicaaVillager(ModelBiped modelNPCBiped, float par2) {
			super(modelNPCBiped, par2);// render like player

		}

		@Override
		protected ResourceLocation getEntityTexture(Entity entity) {
			switch (((EntityVillager) (entity)).getProfession()) {
			case 0:
				return richMan;
			case 1:
				return advantagier;
			case 2:
				return commander;
			case 3:
				return butler;
			case 4:
				return giantWarrior;
			default:
				return richMan;
			}
		}

	}
}
