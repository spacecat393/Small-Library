package com.nali.list.container;

import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.SkinningInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.network.play.server.SPacketSetSlot;
import net.minecraft.world.World;

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
//        SkinningInventory skinninginventory = null;

//        if (skinningentities == null)
//        {
//            skinninginventory = new SkinningInventory();
//        }
//        else
//        {
//            skinninginventory = skinningentities.skinninginventory;
//            this.skinningentities = skinningentities;
//        }

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
                this.addSlotToContainer(new Slot(skinninginventory, l + k * 9, i + l * 18, 89 + k * 18));
            }
        }

        this.addSlotToContainer(new Slot(skinninginventory, 27, 167, 26));
        this.addSlotToContainer(new Slot(skinninginventory, 28, 185, 26));

        this.addSlotToContainer(new Slot(skinninginventory, 32, 54, 48));
        this.addSlotToContainer(new Slot(skinninginventory, 31, 54, 68));

        this.addSlotToContainer(new Slot(skinninginventory, 30, 186, 48));
        this.addSlotToContainer(new Slot(skinninginventory, 29, 186, 68));

        this.addSlotToContainer(new Slot(skinninginventory, 33, 64, 26));

        this.addSlotToContainer(new SlotCrafting(entityplayer, this.inventorycrafting, this.inventorycraftresult, 0, 138, 58));
//        for (int l = 0; l < 3; ++l)
//        {
//            for (int j = 0; j < 3; ++j)
//            {
//                this.addSlotToContainer(new Slot(this.inventorycrafting, j + l * 3, 98 + j * 18, 18 + l * 18));
//            }
//        }
//        this.syncToCrafting();
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

            if (index == this.inventorySlots.size() - 1)
            {
                this.inventorycraftresult.clear();
            }
            if (index < cr)
            {
                if (!this.mergeItemStack(itemstack, cr, this.inventorySlots.size() - 1, true))
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

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        this.syncToCrafting();
//        SkinningInventory skinninginventory = this.skinningentities.bothentitiesmemory.skinninginventory;
//        skinninginventory.markDirty();
    }

    @Override
    public void onCraftMatrixChanged(IInventory iinventory)
    {
        this.slotChangedCraftingGrid(this.entityplayer.world, this.entityplayer, this.inventorycrafting, this.inventorycraftresult);
    }

    @Override
    public void slotChangedCraftingGrid(World world, EntityPlayer entityplayer, InventoryCrafting inventorycrafting, InventoryCraftResult inventorycraftresult)
    {
        if (!world.isRemote)
        {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)entityplayer;
            ItemStack itemstack = ItemStack.EMPTY;
            IRecipe irecipe = CraftingManager.findMatchingRecipe(inventorycrafting, world);

            if (irecipe != null && (irecipe.isDynamic() || !world.getGameRules().getBoolean("doLimitedCrafting") || entityplayermp.getRecipeBook().isUnlocked(irecipe)))
            {
                inventorycraftresult.setRecipeUsed(irecipe);
                itemstack = irecipe.getCraftingResult(inventorycrafting);
            }

            inventorycraftresult.setInventorySlotContents(0, itemstack);
            entityplayermp.connection.sendPacket(new SPacketSetSlot(this.windowId, this.inventorySlots.size() - 1, itemstack));
        }
    }

    public void syncToCrafting()
    {
        if (!this.skinningentities.world.isRemote)
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
//            skinninginventory.inventory_itemstack_nonnulllist.set(15, this.inventorycraftresult.getStackInSlot(0));
        }
    }
}
