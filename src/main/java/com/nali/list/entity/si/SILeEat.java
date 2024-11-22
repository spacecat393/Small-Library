package com.nali.list.entity.si;

import com.mojang.authlib.GameProfile;
import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.math.M4x4;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixES;
import com.nali.small.entity.IMixESInv;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.small.mixin.IMixinItemFood;
import com.nali.system.opengl.memo.MemoF2;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.nali.system.BothLoader.F2_LIST;

public class SILeEat
<
	BD extends IBothDaE & IBothDaS,
	E extends EntityLe,
	I extends IMixE<BD, E> & IMixES & IMixESInv,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public List<Runnable> runnable_list = new ArrayList();
	public static byte ID;

	public byte state;//t-eat t-drinkMilk
	public float time;

	public SIEFrame<BD, E, I, S, MS> sieframe;

	public SILeEat(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{
		this.sieframe = (SIEFrame<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEFrame.ID);
	}

	@Override
	public void call()
	{
		EntityPlayerMP entityplayermp = this.s.ms.entityplayermp;
		this.runnable_list.add(() ->
		{
			ItemStack itemstack = entityplayermp.getHeldItemMainhand();
			if (itemstack.getItem() == Items.MILK_BUCKET)
			{
				this.drinkMilk(entityplayermp);
			}
			else
			{
				this.eat(itemstack, entityplayermp);
			}
		});
	}

	public void eat(ItemStack itemstack, EntityPlayerMP entityplayermp)
	{
//		ItemStack itemstack = entityplayermp.getHeldItemMainhand();
		if (itemstack.getItem() instanceof ItemFood)
		{
//			EntityPlayerMP entityplayermp = this.s.ms.entityplayermp;
			E e = this.s.i.getE();
//			byte[] byte_array = this.s.ms.byte_array;
			WorldServer worldserver = this.s.worldserver;
			ItemFood itemfood = (ItemFood)itemstack.getItem();
			FakePlayer fakeplayer = new FakePlayer(worldserver, new GameProfile(null, "!"));
			fakeplayer.connection = entityplayermp.connection;
			((IMixinItemFood)itemfood).GOonFoodEaten(itemstack, worldserver, fakeplayer);
			Collection<PotionEffect> potioneffect_collection = fakeplayer.getActivePotionEffects();
			for (PotionEffect potioneffect : potioneffect_collection)
			{
				e.addPotionEffect(potioneffect);
			}

			this.state |= 1;

			worldserver.spawnEntity(new EntityXPOrb(worldserver, e.posX, e.posY, e.posZ, 10));
			this.time += itemfood.getHealAmount(itemstack) + itemfood.getSaturationModifier(itemstack);
			e.heal(this.time);
			itemstack.shrink(1);

//						Vec3d view_vec3d = skinningentities.getLookVec().scale(0.25F);
			e.playSound(SoundEvents.ENTITY_GENERIC_EAT, e.getSoundVolume(), e.getSoundPitch());
//			double x = /*e.posX + */ByteReader.getFloat(byte_array, 1 + 8 + 1);
//			double y = /*e.posY + */ByteReader.getFloat(byte_array, 1 + 8 + 1 + 4);
//			double z = /*e.posZ + */ByteReader.getFloat(byte_array, 1 + 8 + 1 + 4 + 4);

			int[] iv_int_array = this.s.i.getIVIntArray();
			//s0-skinning
			int frame_id = this.s.i.getBD().S_FrameID();
			MemoF2 bf2 = F2_LIST.get(frame_id);
			int max_bones = bf2.bone;
			float[] skinning_float_array = new float[max_bones * 16];
			//s1-init
			for (int i = 0; i < max_bones; ++i)
			{
				System.arraycopy(M4x4.IDENTITY, 0, skinning_float_array, i * 16, 16);
			}
			//e1-init
			//s1-set
			int max_key = bf2.length;

			int[] frame_int_array = this.sieframe.frame_int_array;
			for (int i = 0; i < max_bones; ++i)
			{
				for (int f : frame_int_array)
				{
//					if ((this.frame_byte_array[l / 8] >> l % 8 & 1) == 1)
					if (f != -1)
					{
						M4x4.multiply(bf2.transforms_float_array, skinning_float_array, (f + max_key * i) * 16, i * 16);
					}
				}

				M4x4.inverse(skinning_float_array, i * 16);
			}
			//e1-set
			//s1-multiplyAnimation
			this.s.i.mulFrame(skinning_float_array, this.sieframe.frame_int_array, 1);
			//e1-multiplyAnimation
			//e0-skinning
			float[] pos_vec4 = bf2.getScale3DSkinning(this.s.scale, skinning_float_array, (float)e.posX, (float)e.posY, (float)e.posZ, 0, 0, 0, iv_int_array[10], iv_int_array[11]);

			double x = pos_vec4[0] / pos_vec4[3];
			double y = pos_vec4[1] / pos_vec4[3];
			double z = pos_vec4[2] / pos_vec4[3];

//			if (itemstack.getHasSubtypes())
//			{
			this.s.worldserver.spawnParticle(EnumParticleTypes.ITEM_CRACK, x, y, z, 10, 0.1D, 0.1D, 0.1D, 0.0D, Item.getIdFromItem(itemstack.getItem()), itemstack.getMetadata());
//			this.s.worldserver.spawnParticle(EnumParticleTypes.BARRIER, x, y, z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
//			}
//			else
//			{
//				this.s.worldserver.spawnParticle(EnumParticleTypes.ITEM_CRACK, x, y, z, 0.0D, 0.0D, 0.0D, Item.getIdFromItem(itemstack.getItem()), itemstack.getMetadata());
//			}
//			byte[] byte_array = new byte[1 + 4 + 4 + 4 + 4];
//			byte_array[0] = CRenderFood.ID;
//			ByteWriter.set(byte_array, ByteReader.getFloat(a_byte_array, 1 + 16 + 1), 1);
//			ByteWriter.set(byte_array, ByteReader.getFloat(a_byte_array, 1 + 16 + 1 + 4), 1 + 4);
//			ByteWriter.set(byte_array, ByteReader.getFloat(a_byte_array, 1 + 16 + 1 + 4 + 4), 1 + 4 + 4);
//			ByteWriter.set(byte_array, Item.getIdFromItem(itemfood), 1 + 4 + 4 + 4);
//			NetworkRegistry.I.sendToAll(new ClientMessage(byte_array));
		}
	}

	public void drinkMilk(EntityPlayerMP entityplayermp)
	{
//		EntityPlayerMP entityplayermp = this.s.ms.entityplayermp;
//		ItemStack itemstack = entityplayermp.getHeldItemMainhand();
//		if (itemstack.getItem() == Items.MILK_BUCKET)
//		{
		E e = this.s.i.getE();
		e.clearActivePotions();
		entityplayermp.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.BUCKET));

		e.playSound(SoundEvents.ENTITY_GENERIC_DRINK, e.getSoundVolume(), e.getSoundPitch());
//		}
	}

	@Override
	public void onUpdate()
	{
		E e = this.s.i.getE();
		if (this.time > 0 && !this.s.isZeroMove(e))
		{
			e.heal(1);
			this.time -= 0.05F;
		}

		for (int i = 0; i < this.runnable_list.size(); ++i)
		{
			Runnable runnable = this.runnable_list.get(i);
			runnable.run();
		}
		this.runnable_list.clear();
	}

	@Override
	public void writeFile(SIData sidata)
	{
		sidata.byte_array[sidata.index++] = this.state;
	}

	@Override
	public void readFile(SIData sidata)
	{
		this.state = sidata.byte_array[sidata.index++];
	}

	@Override
	public int size()
	{
		return 1;
	}
}
