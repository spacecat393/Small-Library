package com.nali.small.entity.memo.server.si;

import com.nali.da.IBothDaE;
import com.nali.list.entity.si.SIEInv;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class SILeInv
<
	BD extends IBothDaE,
	E extends EntityLivingBase,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SIEInv<BD, E, I, S, MS>
{
	//s0-inv
	public NonNullList<ItemStack> hands_itemstack_nonnulllist = NonNullList.withSize(2, ItemStack.EMPTY);
	public NonNullList<ItemStack> armor_itemstack_nonnulllist = NonNullList.withSize(4, ItemStack.EMPTY);
	public ItemStack mouth_itemstack = ItemStack.EMPTY;

	@Override
	public ItemStack invGet(int index)
	{
//		Nali.warn("index " + index);
		int length = super.invSize();
		if (index < length)
		{
			return super.invGet(index);
		}

		index -= length;
//		Nali.warn("new_index " + index);
		length = this.hands_itemstack_nonnulllist.size();
//		Nali.warn("new_length " + length);
		if (index < length)
		{
			return this.hands_itemstack_nonnulllist.get(index);
		}

		index -= length;
		length = this.armor_itemstack_nonnulllist.size();
		if (index < length)
		{
			return this.armor_itemstack_nonnulllist.get(index);
		}
		return this.mouth_itemstack;
	}

	@Override
	public int invSize()
	{
		return this.hands_itemstack_nonnulllist.size() + this.armor_itemstack_nonnulllist.size() + 1 + super.invSize();
	}

	@Override
	public void invSet(int index, ItemStack itemstack)
	{
//		Nali.warn("index " + index);
		int length = super.invSize();
//		Nali.warn("length " + length);
		if (index < length)
		{
			super.invSet(index, itemstack);
			return;
		}

		index -= length;
//		Nali.warn("new_index " + index);
		length = this.hands_itemstack_nonnulllist.size();
//		Nali.warn("new_length " + length);
		if (index < length)
		{
			this.hands_itemstack_nonnulllist.set(index, itemstack);
			return;
		}

		index -= length;
		length = this.armor_itemstack_nonnulllist.size();
		if (index < length)
		{
			this.armor_itemstack_nonnulllist.set(index, itemstack);
			return;
		}
		this.mouth_itemstack = itemstack;
	}
	//e0-inv

	public SILeInv(S s)
	{
		super(s);
	}

	@Override
	public ItemStack getXPItemStack(E e)
	{
		return EnchantmentHelper.getEnchantedItem(Enchantments.MENDING, e);
	}
}
