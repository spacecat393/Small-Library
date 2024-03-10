package com.nali.list.container;

import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.SkinningInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;

public class InventoryContainer extends Container
{
    public static int ID;
    public EntityPlayer entityplayer;
    public SkinningEntities skinningentities;

    public InventoryCrafting inventorycrafting = new InventoryCrafting(this, 3, 3);
    public InventoryCraftResult inventorycraftresult = new InventoryCraftResult();

    public InventoryContainer(IInventory iinventory, SkinningEntities skinningentities, EntityPlayer entityplayer)
    {
        this.entityplayer = entityplayer;
        this.skinningentities = skinningentities;
        SkinningInventory skinninginventory = this.skinningentities.bothentitiesmemory.skinninginventory;

        int i = 48;

        this.addSlotToContainer(new SlotCrafting(entityplayer, this.inventorycrafting, this.inventorycraftresult, 0, 138, 58));

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
                this.addSlotToContainer(new Slot(skinninginventory, l + k * 9, i + l * 18, 89 + k * 18)
                {
                    @Override
                    public void onSlotChanged()
                    {
                        syncToCrafting();
                        super.onSlotChanged();
                    }

                    @Override
                    public void onSlotChange(ItemStack itemstack_0, ItemStack itemstack_1)
                    {
                        syncToCrafting();
                        super.onSlotChange(itemstack_0, itemstack_1);
                    }
                });
            }
        }

        this.addSlotToContainer(new Slot(skinninginventory, 27, 167, 26));
        this.addSlotToContainer(new Slot(skinninginventory, 28, 185, 26));

        this.addSlotToContainer(new Slot(skinninginventory, 32, 54, 48));
        this.addSlotToContainer(new Slot(skinninginventory, 31, 54, 68));

        this.addSlotToContainer(new Slot(skinninginventory, 30, 186, 48));
        this.addSlotToContainer(new Slot(skinninginventory, 29, 186, 68));

        this.addSlotToContainer(new Slot(skinninginventory, 33, 64, 26));

//        for (int l = 0; l < 3; ++l)
//        {
//            for (int j = 0; j < 3; ++j)
//            {
//                this.addSlotToContainer(new Slot(this.inventorycrafting, j + l * 3, i + j * 18, 89 + (l + 6) * 18));
//            }
//        }
        this.syncToCrafting();
    }

//    @Override
//    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player)
//    {
//        if (slotId != 0)
//        {
//            this.syncToCrafting();
//        }
//
//        return super.slotClick(slotId, dragType, clickTypeIn, player);
//    }

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

            if (index == this.inventorySlots.size())
            {
                this.inventorycraftresult.clear();
            }
            if (index < cr)
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

            if (itemstack.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            ItemStack itemstack2 = slot.onTake(entityplayer, itemstack);

            if (index == 0)
            {
                entityplayer.dropItem(itemstack2, false);
            }
        }

        return out_itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return true;
    }

//    @Override
//    public void detectAndSendChanges()
//    {
//        super.detectAndSendChanges();
////        SkinningInventory skinninginventory = this.skinningentities.bothentitiesmemory.skinninginventory;
////        skinninginventory.markDirty();
//    }

    @Override
    public void onCraftMatrixChanged(IInventory iinventory)
    {
        this.slotChangedCraftingGrid(this.entityplayer.world, this.entityplayer, this.inventorycrafting, this.inventorycraftresult);
    }

    public void syncToCrafting()
    {
        SkinningInventory skinninginventory = this.skinningentities.bothentitiesmemory.skinninginventory;
        this.inventorycrafting.setInventorySlotContents(0, skinninginventory.inventory_itemstack_nonnulllist.get(6));
        this.inventorycrafting.setInventorySlotContents(1, skinninginventory.inventory_itemstack_nonnulllist.get(7));
        this.inventorycrafting.setInventorySlotContents(2, skinninginventory.inventory_itemstack_nonnulllist.get(8));

        this.inventorycrafting.setInventorySlotContents(3, skinninginventory.inventory_itemstack_nonnulllist.get(15));
        this.inventorycrafting.setInventorySlotContents(4, skinninginventory.inventory_itemstack_nonnulllist.get(16));
        this.inventorycrafting.setInventorySlotContents(5, skinninginventory.inventory_itemstack_nonnulllist.get(17));

        this.inventorycrafting.setInventorySlotContents(6, skinninginventory.inventory_itemstack_nonnulllist.get(24));
        this.inventorycrafting.setInventorySlotContents(7, skinninginventory.inventory_itemstack_nonnulllist.get(25));
        this.inventorycrafting.setInventorySlotContents(8, skinninginventory.inventory_itemstack_nonnulllist.get(26));
//        if (!this.skinningentities.world.isRemote)
//        {
//            SkinningInventory skinninginventory = this.skinningentities.bothentitiesmemory.skinninginventory;
//            this.inventorycrafting.setInventorySlotContents(0, skinninginventory.inventory_itemstack_nonnulllist.get(6));
//            this.inventorycrafting.setInventorySlotContents(1, skinninginventory.inventory_itemstack_nonnulllist.get(7));
//            this.inventorycrafting.setInventorySlotContents(2, skinninginventory.inventory_itemstack_nonnulllist.get(8));
//
//            this.inventorycrafting.setInventorySlotContents(3, skinninginventory.inventory_itemstack_nonnulllist.get(15));
//            this.inventorycrafting.setInventorySlotContents(4, skinninginventory.inventory_itemstack_nonnulllist.get(16));
//            this.inventorycrafting.setInventorySlotContents(5, skinninginventory.inventory_itemstack_nonnulllist.get(17));
//
//            this.inventorycrafting.setInventorySlotContents(6, skinninginventory.inventory_itemstack_nonnulllist.get(24));
//            this.inventorycrafting.setInventorySlotContents(7, skinninginventory.inventory_itemstack_nonnulllist.get(25));
//            this.inventorycrafting.setInventorySlotContents(8, skinninginventory.inventory_itemstack_nonnulllist.get(26));
//            this.inventorycrafting.markDirty();
////            skinninginventory.inventory_itemstack_nonnulllist.set(15, this.inventorycraftresult.getStackInSlot(0));
//        }
    }
}
