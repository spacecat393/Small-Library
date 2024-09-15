package com.nali.small.entity.inv;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class InvLe extends InvE
{
	public NonNullList<ItemStack> hands_itemstack_nonnulllist = NonNullList.withSize(2, ItemStack.EMPTY);
	public NonNullList<ItemStack> armor_itemstack_nonnulllist = NonNullList.withSize(4, ItemStack.EMPTY);
	public ItemStack mouth_itemstack = ItemStack.EMPTY;
}
