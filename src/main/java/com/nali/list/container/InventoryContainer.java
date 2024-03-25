package com.nali.list.container;

import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.SkinningInventory;
import com.nali.small.mixin.MixinInventoryCrafting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
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
        SkinningInventory skinninginventory = this.skinningentities.bothentitiesmemory.skinninginventory;
        ((MixinInventoryCrafting)skinninginventory.inventorycrafting).eventHandler(this);
//        skinninginventory.reflectFinal(this);

        int i = 48;

        this.addSlotToContainer(new SlotCrafting(entityplayer, skinninginventory.inventorycrafting, skinninginventory.inventorycraftresult, 0, 138, 58));

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
            for (int l = 0; l < 9-3; ++l)
            {
                this.addSlotToContainer(new Slot(skinninginventory, l + k * (9-3), i + l * 18, 89 + k * 18));
            }
        }

        this.addSlotToContainer(new Slot(skinninginventory, 27-3*3, 167, 26));
        this.addSlotToContainer(new Slot(skinninginventory, 28-3*3, 185, 26));

        this.addSlotToContainer(new Slot(skinninginventory, 32-3*3, 54, 48));
        this.addSlotToContainer(new Slot(skinninginventory, 31-3*3, 54, 68));

        this.addSlotToContainer(new Slot(skinninginventory, 30-3*3, 186, 48));
        this.addSlotToContainer(new Slot(skinninginventory, 29-3*3, 186, 68));

        this.addSlotToContainer(new Slot(skinninginventory, 33, 64, 26));

        for (int l = 0; l < 3; ++l)
        {
            for (int j = 0; j < 3; ++j)
            {
                this.addSlotToContainer(new Slot(skinninginventory.inventorycrafting, j + l * 3, i + (j + (9-3)) * 18, 89 + l * 18));
            }
        }

        this.onCraftMatrixChanged(skinninginventory.inventorycrafting);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityplayer, int index)
    {
        ItemStack out_itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        int cr = 36 + 1;

//        Small.LOGGER.info("I " + index);
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack = slot.getStack();
            out_itemstack = itemstack.copy();

//            if (index == this.inventorySlots.size())
//            {
//                this.skinningentities.bothentitiesmemory.skinninginventory.inventorycraftresult.clear();
//            }

            if (index < cr && index != 0)
            {
                if (!this.mergeItemStack(itemstack, cr, this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack, 1, cr, false))
            {
                return ItemStack.EMPTY;
            }

            if (index == 0)
            {
                slot.onSlotChange(itemstack, out_itemstack);
            }

            if (out_itemstack.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack.getCount() == out_itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            ItemStack itemstack2 = slot.onTake(entityplayer, itemstack);

            if (index == 0)
            {
                entityplayer.dropItem(itemstack2, false);
            }

            if (index > this.inventorySlots.size() - 3*3 - 1 && index < this.inventorySlots.size() + 3*3 - 1)
            {
                this.onCraftMatrixChanged(this.skinningentities.bothentitiesmemory.skinninginventory.inventorycrafting);
            }
        }

        return out_itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return true;
    }

    @Override
    public void onCraftMatrixChanged(IInventory iinventory)
    {
        this.slotChangedCraftingGrid(this.entityplayer.world, this.entityplayer, this.skinningentities.bothentitiesmemory.skinninginventory.inventorycrafting, this.skinningentities.bothentitiesmemory.skinninginventory.inventorycraftresult);
    }
}
