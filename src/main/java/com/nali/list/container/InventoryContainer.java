package com.nali.list.container;

import com.nali.ilol.entities.skinning.SkinningEntities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class InventoryContainer extends Container
{
    public static int ID;
    public EntityPlayer entityplayer;
    public SkinningEntities skinningentities;
    public InventoryContainer(IInventory iinventory, SkinningEntities skinningentities, EntityPlayer entityplayer)
    {
        this.entityplayer = entityplayer;
        this.skinningentities = skinningentities;

        int i = 48;

        for (int k = 0; k < 3; ++k)
        {
            for (int l = 0; l < 9; ++l)
            {
                this.addSlotToContainer(new Slot(iinventory, l + k * 9 + 9, i + l * 18, 156 + k * 18));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1)
        {
            this.addSlotToContainer(new Slot(iinventory, i1, i + i1 * 18, 210));
        }

        for (int k = 0; k < 3; ++k)
        {
            for (int l = 0; l < 9; ++l)
            {
                this.addSlotToContainer(new Slot(skinningentities.skinninginventory, l + k * 9, i + l * 18, 89 + k * 18));
            }
        }

        this.addSlotToContainer(new Slot(skinningentities.skinninginventory, 27, 167, 26));
        this.addSlotToContainer(new Slot(skinningentities.skinninginventory, 28, 185, 26));

        this.addSlotToContainer(new Slot(skinningentities.skinninginventory, 32, 54, 48));
        this.addSlotToContainer(new Slot(skinningentities.skinninginventory, 31, 54, 68));

        this.addSlotToContainer(new Slot(skinningentities.skinninginventory, 30, 186, 48));
        this.addSlotToContainer(new Slot(skinningentities.skinninginventory, 29, 186, 68));

//        this.addSlotToContainer(new Slot(skinningentities.skinninginventory, 33, 64, 26));
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityplayer, int index)
    {
        ItemStack out_itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        int cr = 36;

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack = slot.getStack();
            out_itemstack = itemstack.copy();

            if (index < cr)
            {
                if (!this.mergeItemStack(itemstack, cr, this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack, 0, cr, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return out_itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return true;
    }
}
