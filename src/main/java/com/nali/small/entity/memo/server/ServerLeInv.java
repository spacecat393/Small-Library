package com.nali.small.entity.memo.server;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.EntityLeInv;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.inv.InvLe;
import com.nali.small.entity.memo.IBothLeInv;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.sound.ISoundDaLe;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.EntityDataManager;

import static com.nali.small.entity.EntityLeInv.MOUTH_ITEMSTACK_DATAPARAMETER;

public abstract class ServerLeInv<IE extends InvLe, SD extends ISoundDaLe, BD extends IBothDaNe, E extends EntityLeInv, I extends IMixE<SD, BD, E>, MS extends MixSIE<SD, BD, E, I, ?>> extends ServerLe<SD, BD, E, I, MS> implements IBothLeInv<IE, SD, BD, E, I>
{
	public IE ie;

	public ServerLeInv(I i)
	{
		super(i);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		IE ie = this.getIE();
		EntityDataManager entitydatamanager = this.i.getE().getDataManager();
		if (ie.mouth_itemstack.isEmpty())
		{
			entitydatamanager.set(MOUTH_ITEMSTACK_DATAPARAMETER, ItemStack.EMPTY);
		}
		else
		{
			entitydatamanager.set(MOUTH_ITEMSTACK_DATAPARAMETER, ie.mouth_itemstack);
		}
	}

	@Override
	public void damageArmor(float damage)
	{
		damage = damage / 4.0F;

		if (damage < 1.0F)
		{
			damage = 1.0F;
		}

		IE ie = this.getIE();
		for (int i = 0; i < ie.armor_itemstack_nonnulllist.size(); ++i)
		{
			ItemStack itemstack = ie.armor_itemstack_nonnulllist.get(i);

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
		IE ie = this.getIE();
		switch (entityequipmentslot.getSlotType())
		{
			case HAND:
			{
				ie.hands_itemstack_nonnulllist.set(entityequipmentslot.getIndex(), itemstack);
				break;
			}
			case ARMOR:
			{
				ie.armor_itemstack_nonnulllist.set(entityequipmentslot.getIndex(), itemstack);
				break;
			}
			default:
			{
				break;
			}
		}
	}

	@Override
	public IE getIE()
	{
		return this.ie;
	}
}
