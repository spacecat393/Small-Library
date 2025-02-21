package com.nali.small.entity.memo.server;

import com.nali.da.IBothDaE;
import com.nali.list.entity.si.SIEInv;
import com.nali.list.entity.si.SILeLockDMG;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.IBothLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.small.entity.memo.server.si.SILeInv;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public abstract class ServerLe
<
	BD extends IBothDaE,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	MS extends MixSIE<BD, E, I, ?>
> extends ServerE<BD, E, I, MS> implements IBothLe<E>
{
//	public static DataParameter<ItemStack> MOUTH_ITEMSTACK_DATAPARAMETER;
//	public WorkEBodyYaw workebodyyaw;
//	public byte serverle_state;//attackEntityAsMob attackEntityFrom attackEntityAsMob_trigger attackEntityFrom_trigger
//	public float fix_yaw_head;

//	public Entity attack_entity_as_mob_entity;
//	public DamageSource attack_entity_from_damagesource;
//	public AIEOwner<SD, BD, E, I, ?, MS> sieowner;
	public SILeLockDMG<BD, E, I, ?, MS> silelockdmg;
	public SILeInv<BD, E, I, ?, MS> sileinv;

	public ServerLe(I i)
	{
		super(i);
	}

	public void init()
	{
		this.sileinv = (SILeInv<BD, E, I, ?, MS>)this.ms.si_map.get(SIEInv.ID);
		this.silelockdmg = (SILeLockDMG<BD, E, I, ?, MS>)this.ms.si_map.get(SILeLockDMG.ID);
	}

//	@Override
//	public void onUpdate()
//	{
//		//sync
////		++this.i.getE().rotationYaw;
////		Nali.warn("rY " + this.i.getE().rotationYaw);
////		--this.i.getE().rotationPitch;
////		Nali.warn("rY " + this.i.getE().rotationPitch);
//		//not sync
////		++this.i.getE().renderYawOffset;
////		Nali.warn("rY " + this.i.getE().renderYawOffset);
//		//sync
////		++this.i.getE().rotationYawHead;
////		Nali.warn("rY " + this.i.getE().rotationYawHead);
//		//not sync
////		++this.i.getE().cameraPitch;
////		Nali.warn("rY " + this.i.getE().cameraPitch);
//		//normal look
//		super.onUpdate();
//		E e = this.i.getE();
////		++e.rotationYaw;
////		if (this.fix_yaw_head != 0)
////		{
////			this.rotation_yaw_body = this.fix_yaw_head;
////			e.prev_rotation_yaw_head = this.fix_yaw_head;
////		}
//		//normal -180 180 -90 90
//		//yaw move/body
//		//yawhead head
//		e.rotationPitch = EntityMath.normalize(e.rotationPitch, 180.0F);
////		this.rotation_yaw_body = EntityMath.normalize(this.rotation_yaw_body, 360.0F);
//
////		float difference = e.rotationYaw - this.rotation_yaw_body - 45.0F;
//		float difference = e.rotation_yaw_body - e.rotationYaw;
//
//		float new_difference = (e.rotation_yaw_body + 360) % 360 - (e.rotationYaw + 360) % 360;
//		if (Math.abs(new_difference) < Math.abs(difference))
//		{
//			difference = ((new_difference + 180) % 360 + 360) % 360 - 180;
//		}
//		if (difference > 45.0F)
//		{
//			difference = 45.0F;
//		}
//		else if (difference < -45.0F)
//		{
//			difference = -45.0F;
//		}
//
//		//rotationYawHead not sync in correct
//		//create new rotationYawHead
//		e.rotation_yaw_body = EntityMath.normalize(e.rotationYaw + difference, 360.0F);
////		this.rotation_yaw_body = EntityMath.normalize(this.rotation_yaw_body + difference, 360.0F);
//		e.rotationYaw = EntityMath.normalize(e.rotationYaw, 360.0F);
////		e.prev_rotation_yaw_head = this.rotation_yaw_body;
////		e.prevRotationYaw = e.rotationYaw;
////		Nali.warn("YH " + this.rotation_yaw_body);
//
//		int rotation_yaw_head_int = Float.floatToIntBits(e.rotation_yaw_body);
//		I i = this.i;
//		EntityDataManager entitydatamanager = i.getE().getDataManager();
//		DataParameter<Byte>[] byte_dataparameter_array = i.getByteDataParameterArray();
//
//		entitydatamanager.set(byte_dataparameter_array[4], (byte)(rotation_yaw_head_int & 0xFF));
//		entitydatamanager.set(byte_dataparameter_array[5], (byte)((rotation_yaw_head_int >> 8) & 0xFF));
//		entitydatamanager.set(byte_dataparameter_array[6], (byte)((rotation_yaw_head_int >> 8*2) & 0xFF));
//		entitydatamanager.set(byte_dataparameter_array[7], (byte)((rotation_yaw_head_int >> 8*3) & 0xFF));
//	}

	@Override
	public boolean attackEntityFrom(DamageSource damagesource, float amount)
	{
//		this.attack_entity_from_damagesource = damagesource;
//		this.serverle_state |= 8;
//		this.ms.call(SILeLockDMG.ID);
		return this.silelockdmg.attackEntityFrom(damagesource, amount);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity)//both?
	{
//		this.attack_entity_as_mob_entity = entity;
//		this.serverle_state |= 4;
//		this.ms.call(SILeLockDMG.ID);
//		return (this.serverle_state & 1) == 1;
		return this.silelockdmg.attackEntityAsMob(entity);
	}

	@Override
	public int size()
	{
		return super.size() + 4;
	}

	@Override
	public void writeFile(SIData sidata)
	{
		ByteWriter.set(sidata.byte_array, this.i.getE().rotationYawHead, sidata.index);
		sidata.index += 4;
	}

	@Override
	public void readFile(SIData sidata)
	{
		E e = this.i.getE();
		e.rotation_yaw_body = ByteReader.getFloat(sidata.byte_array, sidata.index);
//		e.prev_rotation_yaw_head = e.rotation_yaw_body;
//		this.fix_yaw_head = ByteReader.getFloat(sidata.byte_array, sidata.rg);
		sidata.index += 4;
	}

	@Override
	public void initFile()
	{
		E e = this.i.getE();
//		this.fix_yaw_head = e.rotationYaw;
		e.rotation_yaw_body = e.rotationYaw;
//		e.prev_rotation_yaw_head = e.rotationYaw;
	}
//	@Override
//	public WorkEBodyYaw getWorkEBodyYaw()
//	{
//		return this.workebodyyaw;
//	}

//	@Override
//	public void getHurtSound(DamageSource damagesource)
//	{
////		this.ms.byte_array = new byte[1 + 8 + 1 + 4];
////		ByteWriter.set(this.ms.byte_array, this.i.getSD().HURT(), 1 + 8 + 1);
////		this.ms.call(SIESound.ID);
//	}
//
//	@Override
//	public void getDeathSound()
//	{

//	@Override
//	public void entityInit()
//	{
//		this.i.getE().getDataManager().register(MOUTH_ITEMSTACK_DATAPARAMETER, ItemStack.EMPTY);
//	}

	////		this.ms.byte_array = new byte[1 + 8 + 1 + 4];
////		ByteWriter.set(this.ms.byte_array, this.i.getSD().DEATH(), 1 + 8 + 1);
////		this.ms.call(SIESound.ID);
//	}

	//inv
	@Override
	public void damageArmor(float damage)
	{
		damage = damage / 4.0F;

		if (damage < 1.0F)
		{
			damage = 1.0F;
		}

		for (int i = 0; i < this.sileinv.armor_itemstack_nonnulllist.size(); ++i)
		{
			ItemStack itemstack = this.sileinv.armor_itemstack_nonnulllist.get(i);

			if (itemstack.getItem() instanceof ItemArmor)
			{
				itemstack.damageItem((int)damage, this.i.getE());
			}
		}
	}

	@Override
	public void setItemStackToSlot(EntityEquipmentSlot entityequipmentslot, ItemStack itemstack)
	{
		this.i.getE().playEquipSound(itemstack);
		switch (entityequipmentslot.getSlotType())
		{
			case HAND:
			{
				this.sileinv.hands_itemstack_nonnulllist.set(entityequipmentslot.getIndex(), itemstack);
				break;
			}
			case ARMOR:
			{
				this.sileinv.armor_itemstack_nonnulllist.set(entityequipmentslot.getIndex(), itemstack);
				break;
			}
			default:
			{
				break;
			}
		}
	}

	@Override
	public Iterable<ItemStack> getHeldEquipment()
	{
		return this.sileinv.hands_itemstack_nonnulllist;
	}

	@Override
	public Iterable<ItemStack> getArmorInventoryList()
	{
		return this.sileinv.armor_itemstack_nonnulllist;
	}

	@Override
	public ItemStack getItemStackFromSlot(EntityEquipmentSlot entityequipmentslot)
	{
		switch (entityequipmentslot.getSlotType())
		{
			case HAND:
			{
				return this.sileinv.hands_itemstack_nonnulllist.get(entityequipmentslot.getIndex());
			}
			case ARMOR:
			{
				return this.sileinv.armor_itemstack_nonnulllist.get(entityequipmentslot.getIndex());
			}
			default:
			{
				return ItemStack.EMPTY;
			}
		}
	}
}
