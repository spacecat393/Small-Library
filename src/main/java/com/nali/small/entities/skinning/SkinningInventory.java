package com.nali.small.entities.skinning;

import com.google.common.collect.Lists;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import java.util.List;

public class SkinningInventory implements IInventory
{
    public NonNullList<ItemStack> hands_itemstack_nonnulllist = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
    public NonNullList<ItemStack> armor_itemstack_nonnulllist = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
    public NonNullList<ItemStack> inventory_itemstack_nonnulllist;
    public NonNullList<ItemStack> offset_itemstack_nonnulllist;
    public List<IInventoryChangedListener> changeListeners;

    public SkinningInventory(int offset)
    {
        this.inventory_itemstack_nonnulllist = NonNullList.<ItemStack>withSize(27, ItemStack.EMPTY);
        this.offset_itemstack_nonnulllist = NonNullList.<ItemStack>withSize(offset, ItemStack.EMPTY);
    }

    public void addInventoryChangeListener(IInventoryChangedListener listener)
    {
        if (this.changeListeners == null)
        {
            this.changeListeners = Lists.<IInventoryChangedListener>newArrayList();
        }

        this.changeListeners.add(listener);
    }

    public void removeInventoryChangeListener(IInventoryChangedListener listener)
    {
        this.changeListeners.remove(listener);
    }

    public ItemStack getStackInSlot(int index)
    {
        return index > -1 &&
        index < 27 ? this.inventory_itemstack_nonnulllist.get(index) :
        index < 27 + 2 ? this.hands_itemstack_nonnulllist.get(index - 27) :
        index < 27 + 2 + 4 ? this.armor_itemstack_nonnulllist.get(index - 27 - 2) :
        index < 27 + 2 + 4 + this.offset_itemstack_nonnulllist.size() ? this.offset_itemstack_nonnulllist.get(index - 27 - 2 - 4) : ItemStack.EMPTY;
    }

    public ItemStack decrStackSize(int index, int count)
    {
        ItemStack itemstack;

        if (index >= 27 + 2 + 4)
        {
            itemstack = ItemStackHelper.getAndSplit(this.offset_itemstack_nonnulllist, index - 27 - 2 - 4, count);
        }
        else if (index >= 27 + 2)
        {
            itemstack = ItemStackHelper.getAndSplit(this.armor_itemstack_nonnulllist, index - 27 - 2, count);
        }
        else if (index >= 27)
        {
            itemstack = ItemStackHelper.getAndSplit(this.hands_itemstack_nonnulllist, index - 27, count);
        }
        else
        {
            itemstack = ItemStackHelper.getAndSplit(this.inventory_itemstack_nonnulllist, index, count);
        }

        if (!itemstack.isEmpty())
        {
            this.markDirty();
        }

        return itemstack;
    }

    public ItemStack addItem(ItemStack stack)
    {
        ItemStack itemstack = stack.copy();

        for (int i = 0; i < this.getSizeInventory(); ++i)
        {
            ItemStack itemstack1 = this.getStackInSlot(i);

            if (itemstack1.isEmpty())
            {
                this.setInventorySlotContents(i, itemstack);
                this.markDirty();
                return ItemStack.EMPTY;
            }

            if (ItemStack.areItemsEqual(itemstack1, itemstack))
            {
                int j = Math.min(this.getInventoryStackLimit(), itemstack1.getMaxStackSize());
                int k = Math.min(itemstack.getCount(), j - itemstack1.getCount());

                if (k > 0)
                {
                    itemstack1.grow(k);
                    itemstack.shrink(k);

                    if (itemstack.isEmpty())
                    {
                        this.markDirty();
                        return ItemStack.EMPTY;
                    }
                }
            }
        }

        if (itemstack.getCount() != stack.getCount())
        {
            this.markDirty();
        }

        return itemstack;
    }

    public ItemStack removeStackFromSlot(int index)
    {
        ItemStack itemstack = this.inventory_itemstack_nonnulllist.get(index);

        if (itemstack.isEmpty())
        {
            return ItemStack.EMPTY;
        }
        else
        {
            if (index >= 27 + 2 + 4)
            {
                this.offset_itemstack_nonnulllist.set(index - 27 - 2 - 4, ItemStack.EMPTY);
            }
            else if (index >= 27 + 2)
            {
                this.armor_itemstack_nonnulllist.set(index - 27 - 2, ItemStack.EMPTY);
            }
            else if (index >= 27)
            {
                this.hands_itemstack_nonnulllist.set(index - 27, ItemStack.EMPTY);
            }
            else
            {
                this.inventory_itemstack_nonnulllist.set(index, ItemStack.EMPTY);
            }
            return itemstack;
        }
    }

    public void setInventorySlotContents(int index, ItemStack stack)
    {
        if (index >= 27 + 2 + 4)
        {
            this.offset_itemstack_nonnulllist.set(index - 27 - 2 - 4, stack);
        }
        else if (index >= 27 + 2)
        {
            this.armor_itemstack_nonnulllist.set(index - 27 - 2, stack);
        }
        else if (index >= 27)
        {
            this.hands_itemstack_nonnulllist.set(index - 27, stack);
        }
        else
        {
            this.inventory_itemstack_nonnulllist.set(index, stack);
        }

        if (!stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }

        this.markDirty();
    }

    public int getSizeInventory()
    {
        return 27 + 2 + 4 + this.offset_itemstack_nonnulllist.size();
    }

    public boolean isEmpty()
    {
        for (ItemStack itemstack : this.inventory_itemstack_nonnulllist)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        for (ItemStack itemstack : this.hands_itemstack_nonnulllist)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        for (ItemStack itemstack : this.armor_itemstack_nonnulllist)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        for (ItemStack itemstack : this.offset_itemstack_nonnulllist)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }

    public String getName()
    {
        return "";
    }

    public boolean hasCustomName()
    {
        return false;
    }

    public ITextComponent getDisplayName()
    {
        return new TextComponentString(this.getName());
    }

    public int getInventoryStackLimit()
    {
        return Integer.MAX_VALUE;
    }

    public void markDirty()
    {
        if (this.changeListeners != null)
        {
            for (IInventoryChangedListener changeListener : this.changeListeners)
            {
                changeListener.onInventoryChanged(this);
            }
        }
    }

    public boolean isUsableByPlayer(EntityPlayer entityplayer)
    {
        return true;
    }

    public void openInventory(EntityPlayer entityplayer)
    {
    }

    public void closeInventory(EntityPlayer entityplayer)
    {
    }

    public boolean isItemValidForSlot(int index, ItemStack itemstack)
    {
        return true;
    }

    public int getField(int id)
    {
        return 0;
    }

    public void setField(int id, int value)
    {
    }

    public int getFieldCount()
    {
        return 0;
    }

    public void clear()
    {
        this.inventory_itemstack_nonnulllist.clear();
        this.hands_itemstack_nonnulllist.clear();
        this.armor_itemstack_nonnulllist.clear();
        this.offset_itemstack_nonnulllist.clear();
    }

    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        NBTTagList nbttaglist = new NBTTagList();
        for (ItemStack itemstack : this.armor_itemstack_nonnulllist)
        {
            NBTTagCompound new_nbttagcompound = new NBTTagCompound();

            if (!itemstack.isEmpty())
            {
                itemstack.writeToNBT(new_nbttagcompound);
            }

            nbttaglist.appendTag(new_nbttagcompound);
        }

        nbttagcompound.setTag("ArmorItems", nbttaglist);
        nbttaglist = new NBTTagList();

        for (ItemStack itemstack1 : this.hands_itemstack_nonnulllist)
        {
            NBTTagCompound new_nbttagcompound = new NBTTagCompound();

            if (!itemstack1.isEmpty())
            {
                itemstack1.writeToNBT(new_nbttagcompound);
            }

            nbttaglist.appendTag(new_nbttagcompound);
        }

        nbttagcompound.setTag("HandItems", nbttaglist);

        for (int l = 0; l < this.getSizeInventory(); ++l)
        {
            ItemStack itemstack = this.getStackInSlot(l);
            if (!itemstack.isEmpty() && !(l > 27 && l < 27 + 2 + 4))
            {
                nbttagcompound.setTag("ib" + l, itemstack.writeToNBT(new NBTTagCompound()));
            }
        }
    }

    public void readNBT(NBTTagCompound nbttagcompound)
    {
        if (nbttagcompound.hasKey("ArmorItems", 9))
        {
            NBTTagList nbttaglist = nbttagcompound.getTagList("ArmorItems", 10);

            for (int l = 0; l < this.armor_itemstack_nonnulllist.size(); ++l)
            {
                this.armor_itemstack_nonnulllist.set(l, new ItemStack(nbttaglist.getCompoundTagAt(l)));
            }
        }

        if (nbttagcompound.hasKey("HandItems", 9))
        {
            NBTTagList nbttaglist1 = nbttagcompound.getTagList("HandItems", 10);

            for (int j = 0; j < this.hands_itemstack_nonnulllist.size(); ++j)
            {
                this.hands_itemstack_nonnulllist.set(j, new ItemStack(nbttaglist1.getCompoundTagAt(j)));
            }
        }

        for (int k = 0; k < this.getSizeInventory(); ++k)
        {
            String key = "ib" + k;

            if (nbttagcompound.hasKey(key, 10))
            {
                this.setInventorySlotContents(k, new ItemStack(nbttagcompound.getCompoundTag(key)));
            }
        }
    }
}
