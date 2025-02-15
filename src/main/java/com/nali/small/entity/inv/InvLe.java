package com.nali.small.entity.inv;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class InvLe extends InvE
{
	public NonNullList<ItemStack> hands_itemstack_nonnulllist = NonNullList.withSize(2, ItemStack.EMPTY);
	public NonNullList<ItemStack> armor_itemstack_nonnulllist = NonNullList.withSize(4, ItemStack.EMPTY);
	public ItemStack mouth_itemstack = ItemStack.EMPTY;

	@Override
	public ItemStack get(int index)
	{
		int length = super.size();
		if (index < length)
		{
			return super.get(index);
		}

		length += this.hands_itemstack_nonnulllist.size();
		if (index < length)
		{
			return this.hands_itemstack_nonnulllist.get(index);
		}

		length += this.armor_itemstack_nonnulllist.size();
		if (index < length)
		{
			return this.armor_itemstack_nonnulllist.get(index);
		}
		return this.mouth_itemstack;
	}

	@Override
	public int size()
	{
		return this.hands_itemstack_nonnulllist.size() + this.armor_itemstack_nonnulllist.size() + 1 + super.size();
	}

	@Override
	public void set(int index, ItemStack itemstack)
	{
		int length = super.size();
		if (index < length)
		{
			super.set(index, itemstack);
		}

		length += this.hands_itemstack_nonnulllist.size();
		if (index < length)
		{
			this.hands_itemstack_nonnulllist.set(index, itemstack);
		}

		length += this.armor_itemstack_nonnulllist.size();
		if (index < length)
		{
			this.armor_itemstack_nonnulllist.set(index, itemstack);
		}
		this.mouth_itemstack = itemstack;
	}
}
