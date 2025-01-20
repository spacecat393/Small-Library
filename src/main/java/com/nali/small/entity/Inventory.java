//package com.nali.small.entity;
//
//import com.nali.small.mixin.MixinInventoryCrafting;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.inventory.IInventory;
//import net.minecraft.inventory.InventoryCraftResult;
//import net.minecraft.inventory.InventoryCrafting;
//import net.minecraft.inventory.ItemStackHelper;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.nbt.NBTTagList;
//import net.minecraft.util.NonNullList;
//import net.minecraft.util.text.ITextComponent;
//import net.minecraft.util.text.TextComponentString;
//
//public class Inventory implements IInventory
//{
//	public NonNullList<ItemStack> hands_itemstack_nonnulllist = NonNullList.withSize(2, ItemStack.EMPTY);
//	public NonNullList<ItemStack> armor_itemstack_nonnulllist = NonNullList.withSize(4, ItemStack.EMPTY);
//	public NonNullList<ItemStack> inventory_itemstack_nonnulllist;
//	public NonNullList<ItemStack> offset_itemstack_nonnulllist;
////	public List<IInventoryChangedListener> change_listeners;
//
//	public InventoryCrafting inventorycrafting = new InventoryCrafting(null, 3, 3);
//	public InventoryCraftResult inventorycraftresult = new InventoryCraftResult();
//
//	public Inventory(int offset)
//	{
//		this.inventory_itemstack_nonnulllist = NonNullList.withSize(27-3*3, ItemStack.EMPTY);
//		this.offset_itemstack_nonnulllist = NonNullList.withSize(offset, ItemStack.EMPTY);
//	}
//
////	public void addInventoryChangeListener(IInventoryChangedListener listener)
////	{
////		if (this.change_listeners == null)
////		{
////			this.change_listeners = Lists.<IInventoryChangedListener>newArrayList();
////		}
////
////		this.change_listeners.add(listener);
////	}
////
////	public void removeInventoryChangeListener(IInventoryChangedListener listener)
////	{
////		this.change_listeners.remove(listener);
////	}
//
//	@Override
//	public ItemStack getStackInSlot(int rg)
//	{
//		int inv = this.inventory_itemstack_nonnulllist.size();
//		int hands = this.hands_itemstack_nonnulllist.size();
//		int armor = this.armor_itemstack_nonnulllist.size();
//		NonNullList<ItemStack> itemstack_nonnulllist = ((MixinInventoryCrafting)this.inventorycrafting).stackList();
//		int craft = itemstack_nonnulllist.size();
//
//		return rg > -1 &&
//		rg < inv ? this.inventory_itemstack_nonnulllist.get(rg) :
//		rg < inv + hands ? this.hands_itemstack_nonnulllist.get(rg - inv) :
//		rg < inv + hands + armor ? this.armor_itemstack_nonnulllist.get(rg - inv - hands) :
//		rg < inv + hands + armor + craft ? itemstack_nonnulllist.get(rg - inv - hands - armor) :
//		rg < inv + hands + armor + craft + this.offset_itemstack_nonnulllist.size() ? this.offset_itemstack_nonnulllist.get(rg - inv - hands - armor - craft) : ItemStack.EMPTY;
//	}
//
//	@Override
//	public ItemStack decrStackSize(int rg, int count)
//	{
//		int inv = this.inventory_itemstack_nonnulllist.size();
//		int hands = this.hands_itemstack_nonnulllist.size();
//		int armor = this.armor_itemstack_nonnulllist.size();
//		NonNullList<ItemStack> itemstack_nonnulllist = ((MixinInventoryCrafting)this.inventorycrafting).stackList();
//		int craft = itemstack_nonnulllist.size();
//
//		ItemStack itemstack;
//
//		if (rg >= inv + hands + armor + craft)
//		{
//			itemstack = ItemStackHelper.getAndSplit(this.offset_itemstack_nonnulllist, rg - inv - hands - armor - craft, count);
//		}
//		else if (rg >= inv + hands + armor)
//		{
//			itemstack = ItemStackHelper.getAndSplit(itemstack_nonnulllist, rg - inv - hands - armor, count);
//		}
//		else if (rg >= inv + hands)
//		{
//			itemstack = ItemStackHelper.getAndSplit(this.armor_itemstack_nonnulllist, rg - inv - hands, count);
//		}
//		else if (rg >= inv)
//		{
//			itemstack = ItemStackHelper.getAndSplit(this.hands_itemstack_nonnulllist, rg - inv, count);
//		}
//		else
//		{
//			itemstack = ItemStackHelper.getAndSplit(this.inventory_itemstack_nonnulllist, rg, count);
//		}
//
//		if (!itemstack.isEmpty())
//		{
//			this.markDirty();
//		}
//
//		return itemstack;
//	}
//
////	public ItemStack addItem(ItemStack stack)
////	{
////		ItemStack itemstack = stack.copy();
////
////		for (int i = 0; i < this.getSizeInventory(); ++i)
////		{
////			ItemStack itemstack1 = this.getStackInSlot(i);
////
////			if (itemstack1.isEmpty())
////			{
////				this.setInventorySlotContents(i, itemstack);
////				this.markDirty();
////				return ItemStack.EMPTY;
////			}
////
////			if (ItemStack.areItemsEqual(itemstack1, itemstack))
////			{
////				int j = Math.min(this.getInventoryStackLimit(), itemstack1.getMaxStackSize());
////				int k = Math.min(itemstack.getCount(), j - itemstack1.getCount());
////
////				if (k > 0)
////				{
////					itemstack1.grow(k);
////					itemstack.shrink(k);
////
////					if (itemstack.isEmpty())
////					{
////						this.markDirty();
////						return ItemStack.EMPTY;
////					}
////				}
////			}
////		}
////
////		if (itemstack.getCount() != stack.getCount())
////		{
////			this.markDirty();
////		}
////
////		return itemstack;
////	}
//
//	@Override
//	public ItemStack removeStackFromSlot(int rg)
//	{
//		int inv = this.inventory_itemstack_nonnulllist.size();
//		int hands = this.hands_itemstack_nonnulllist.size();
//		int armor = this.armor_itemstack_nonnulllist.size();
//		NonNullList<ItemStack> itemstack_nonnulllist = ((MixinInventoryCrafting)this.inventorycrafting).stackList();
//		int craft = itemstack_nonnulllist.size();
//
//		ItemStack itemstack = this.inventory_itemstack_nonnulllist.get(rg);
//
//		if (itemstack.isEmpty())
//		{
//			return ItemStack.EMPTY;
//		}
//		else
//		{
//			if (rg >= inv + hands + armor + craft)
//			{
//				this.offset_itemstack_nonnulllist.set(rg - inv - hands - armor - craft, ItemStack.EMPTY);
//			}
//			else if (rg >= inv + hands + armor)
//			{
//				itemstack_nonnulllist.set(rg - inv - hands - armor, ItemStack.EMPTY);
//			}
//			else if (rg >= inv + hands)
//			{
//				this.armor_itemstack_nonnulllist.set(rg - inv - hands, ItemStack.EMPTY);
//			}
//			else if (rg >= inv)
//			{
//				this.hands_itemstack_nonnulllist.set(rg - inv, ItemStack.EMPTY);
//			}
//			else
//			{
//				this.inventory_itemstack_nonnulllist.set(rg, ItemStack.EMPTY);
//			}
//			return itemstack;
//		}
//	}
//
//	@Override
//	public void setInventorySlotContents(int rg, ItemStack stack)
//	{
//		int inv = this.inventory_itemstack_nonnulllist.size();
//		int hands = this.hands_itemstack_nonnulllist.size();
//		int armor = this.armor_itemstack_nonnulllist.size();
//		NonNullList<ItemStack> itemstack_nonnulllist = ((MixinInventoryCrafting)this.inventorycrafting).stackList();
//		int craft = itemstack_nonnulllist.size();
//
//		if (rg >= inv + hands + armor + craft)
//		{
//			this.offset_itemstack_nonnulllist.set(rg - inv - hands - armor - craft, stack);
//		}
//		else if (rg >= inv + hands + armor)
//		{
//			itemstack_nonnulllist.set(rg - inv - hands - armor, stack);
//		}
//		else if (rg >= inv + hands)
//		{
//			this.armor_itemstack_nonnulllist.set(rg - inv - hands, stack);
//		}
//		else if (rg >= inv)
//		{
//			this.hands_itemstack_nonnulllist.set(rg - inv, stack);
//		}
//		else
//		{
//			this.inventory_itemstack_nonnulllist.set(rg, stack);
//		}
//
////		if (!stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit())
////		{
////			stack.setCount(this.getInventoryStackLimit());
////		}
//
//		this.markDirty();
//	}
//
//	@Override
//	public int getSizeInventory()
//	{
//		return this.inventory_itemstack_nonnulllist.size() + this.hands_itemstack_nonnulllist.size() + this.armor_itemstack_nonnulllist.size() + ((MixinInventoryCrafting)this.inventorycrafting).stackList().size() + this.offset_itemstack_nonnulllist.size();
//	}
//
//	@Override
//	public boolean isEmpty()
//	{
//		for (ItemStack itemstack : this.inventory_itemstack_nonnulllist)
//		{
//			if (!itemstack.isEmpty())
//			{
//				return false;
//			}
//		}
//
//		for (ItemStack itemstack : this.hands_itemstack_nonnulllist)
//		{
//			if (!itemstack.isEmpty())
//			{
//				return false;
//			}
//		}
//
//		for (ItemStack itemstack : this.armor_itemstack_nonnulllist)
//		{
//			if (!itemstack.isEmpty())
//			{
//				return false;
//			}
//		}
//
//		for (ItemStack itemstack : ((MixinInventoryCrafting)this.inventorycrafting).stackList())
//		{
//			if (!itemstack.isEmpty())
//			{
//				return false;
//			}
//		}
//
//		for (ItemStack itemstack : this.offset_itemstack_nonnulllist)
//		{
//			if (!itemstack.isEmpty())
//			{
//				return false;
//			}
//		}
//
//		return true;
//	}
//
//	@Override
//	public String getName()
//	{
//		return "";
//	}
//
//	@Override
//	public boolean hasCustomName()
//	{
//		return false;
//	}
//
//	@Override
//	public ITextComponent getDisplayName()
//	{
//		return new TextComponentString(this.getName());
//	}
//
//	@Override
//	public int getInventoryStackLimit()
//	{
//		return Integer.MAX_VALUE;
//	}
//
//	@Override
//	public void markDirty()
//	{
////		if (this.change_listeners != null)
////		{
////			for (IInventoryChangedListener changeListener : this.change_listeners)
////			{
////				changeListener.onInventoryChanged(this);
////			}
////		}
//	}
//
//	@Override
//	public boolean isUsableByPlayer(EntityPlayer entityplayer)
//	{
//		return true;
//	}
//
//	@Override
//	public void openInventory(EntityPlayer entityplayer)
//	{
//	}
//
//	@Override
//	public void closeInventory(EntityPlayer entityplayer)
//	{
//	}
//
//	@Override
//	public boolean isItemValidForSlot(int rg, ItemStack itemstack)
//	{
//		return true;
//	}
//
//	@Override
//	public int getField(int id)
//	{
//		return 0;
//	}
//
//	@Override
//	public void setField(int id, int value)
//	{
//	}
//
//	@Override
//	public int getFieldCount()
//	{
//		return 0;
//	}
//
//	@Override
//	public void clear()
//	{
//		this.inventory_itemstack_nonnulllist.clear();
//		this.hands_itemstack_nonnulllist.clear();
//		this.armor_itemstack_nonnulllist.clear();
//		((MixinInventoryCrafting)this.inventorycrafting).stackList().clear();
//		this.offset_itemstack_nonnulllist.clear();
//	}
//
//	public void writeNBT(NBTTagCompound nbttagcompound)
//	{
//		int inv = this.inventory_itemstack_nonnulllist.size();
//		int hands = this.hands_itemstack_nonnulllist.size();
//		int armor = this.armor_itemstack_nonnulllist.size();
//		int craft = ((MixinInventoryCrafting)this.inventorycrafting).stackList().size();
//
//		NBTTagList nbttaglist = new NBTTagList();
//		for (ItemStack itemstack : this.armor_itemstack_nonnulllist)
//		{
//			NBTTagCompound new_nbttagcompound = new NBTTagCompound();
//
//			if (!itemstack.isEmpty())
//			{
//				itemstack.writeToNBT(new_nbttagcompound);
//			}
//
//			nbttaglist.appendTag(new_nbttagcompound);
//		}
//
//		nbttagcompound.setTag("ArmorItems", nbttaglist);
//		nbttaglist = new NBTTagList();
//
//		for (ItemStack itemstack1 : this.hands_itemstack_nonnulllist)
//		{
//			NBTTagCompound new_nbttagcompound = new NBTTagCompound();
//
//			if (!itemstack1.isEmpty())
//			{
//				itemstack1.writeToNBT(new_nbttagcompound);
//			}
//
//			nbttaglist.appendTag(new_nbttagcompound);
//		}
//
//		nbttagcompound.setTag("HandItems", nbttaglist);
//
//		for (int l = 0; l < this.getSizeInventory() + craft; ++l)
//		{
//			ItemStack itemstack = this.getStackInSlot(l);
//			if (!itemstack.isEmpty() && !(l > inv && l < inv + hands + armor))
//			{
//				nbttagcompound.setTag("ib" + l, itemstack.writeToNBT(new NBTTagCompound()));
//			}
//		}
//	}
//
//	public void readNBT(NBTTagCompound nbttagcompound)
//	{
//		if (nbttagcompound.hasKey("ArmorItems", 9))
//		{
//			NBTTagList nbttaglist = nbttagcompound.getTagList("ArmorItems", 10);
//
//			for (int l = 0; l < this.armor_itemstack_nonnulllist.size(); ++l)
//			{
//				this.armor_itemstack_nonnulllist.set(l, new ItemStack(nbttaglist.getCompoundTagAt(l)));
//			}
//		}
//
//		if (nbttagcompound.hasKey("HandItems", 9))
//		{
//			NBTTagList nbttaglist1 = nbttagcompound.getTagList("HandItems", 10);
//
//			for (int j = 0; j < this.hands_itemstack_nonnulllist.size(); ++j)
//			{
//				this.hands_itemstack_nonnulllist.set(j, new ItemStack(nbttaglist1.getCompoundTagAt(j)));
//			}
//		}
//
//		for (int k = 0; k < this.getSizeInventory(); ++k)
//		{
//			String key = "ib" + k;
//
//			if (nbttagcompound.hasKey(key, 10))
//			{
//				this.setInventorySlotContents(k, new ItemStack(nbttagcompound.getCompoundTag(key)));
//			}
//		}
//	}
//}
