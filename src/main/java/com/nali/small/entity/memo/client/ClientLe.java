package com.nali.small.entity.memo.client;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaO;
import com.nali.render.RenderO;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.IBothLe;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import com.nali.system.bytes.ByteReader;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientLe
<
	BD extends IBothDaE & IBothDaO,
	R extends RenderO<BD>,
//	SD extends ISoundDaLe,
	E extends EntityLe,
	I extends IMixE<BD, E>/* & IMixESoundDa<SD>*/,
	MC extends MixCIE<BD, R, E, I, MB, MR, ?>,
	MB extends MixBoxE<BD, R, E, I, MC, MR, ?>,
	MR extends MixRenderE<BD, R, E, I, MC, MB, ?>
> extends ClientE<BD, R, E, I, MC, MB, MR> implements IBothLe<E>
{
	public NonNullList<ItemStack> hands_itemstack_nonnulllist = NonNullList.withSize(2, ItemStack.EMPTY);
	public NonNullList<ItemStack> armor_itemstack_nonnulllist = NonNullList.withSize(4, ItemStack.EMPTY);
//	public WorkEBodyYaw workebodyyaw;

//	public byte[] work_byte_array;

	public ClientLe(I i, R r)
	{
		super(i, r);
//		this.workebodyyaw = new WorkEBodyYaw();
//		this.work_byte_array = new byte[workbytes.MAX_WORKS()];
	}

	public ClientLe(R r)
	{
		super(r);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		E e = this.i.getE();
		e.rotation_yaw_body = ByteReader.getFloat(this.sync_byte_array, 4);
//		e.prev_rotation_yaw_head = e.rotation_yaw_body;
//		while (e.rotation_yaw_body - e.prev_rotation_yaw_head < -180.0F)
//		{
//			e.prev_rotation_yaw_head -= 360.0F;
//		}
//
//		while (e.rotation_yaw_body - e.prev_rotation_yaw_head >= 180.0F)
//		{
//			e.prev_rotation_yaw_head += 360.0F;
//		}
	}

	@Override
	public void getHurtSound(DamageSource damagesource)
	{
//		this.getSound().play(this.i.getSD().HURT());
	}

	@Override
	public void getDeathSound()
	{
//		this.getSound().play(this.i.getSD().DEATH());
	}

	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		return false;
	}

	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float v)
	{
		return false;
	}

	//s0-inv
	@Override
	public void damageArmor(float damage)
	{
	}

	@Override
	public void setItemStackToSlot(EntityEquipmentSlot entityequipmentslot, ItemStack itemstack)
	{
		this.i.getE().playEquipSound(itemstack);
		switch (entityequipmentslot.getSlotType())
		{
			case HAND:
			{
				this.hands_itemstack_nonnulllist.set(entityequipmentslot.getIndex(), itemstack);
				break;
			}
			case ARMOR:
			{
				this.armor_itemstack_nonnulllist.set(entityequipmentslot.getIndex(), itemstack);
				break;
			}
			default:
			{
				break;
			}
		}
	}

	@Override
	public ItemStack getItemStackFromSlot(EntityEquipmentSlot entityequipmentslot)
	{
		switch (entityequipmentslot.getSlotType())
		{
			case HAND:
			{
				return this.hands_itemstack_nonnulllist.get(entityequipmentslot.getIndex());
			}
			case ARMOR:
			{
				return this.armor_itemstack_nonnulllist.get(entityequipmentslot.getIndex());
			}
			default:
			{
				return ItemStack.EMPTY;
			}
		}
	}

	@Override
	public Iterable<ItemStack> getArmorInventoryList()
	{
		return this.armor_itemstack_nonnulllist;
	}

	@Override
	public Iterable<ItemStack> getHeldEquipment()
	{
		return this.hands_itemstack_nonnulllist;
	}
	//e0-inv

	//	@Override
//	public WorkEBodyYaw getWorkEBodyYaw()
//	{
//		return this.workebodyyaw;
//	}
}
