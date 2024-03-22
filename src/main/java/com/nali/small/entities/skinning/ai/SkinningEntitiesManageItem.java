package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.SkinningInventory;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.nali.small.entities.skinning.ai.SkinningEntitiesGetItem.isSameItemSameTags;

public class SkinningEntitiesManageItem extends SkinningEntitiesAI
{
    public BlockPos in_blockpos, out_blockpos;
    public byte state;//put take remote_put random_find

    public SkinningEntitiesManageItem(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        //walk to
        if (this.skinningentities.ticksExisted % 20 == 0)
        {
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;

            if (serverentitiesmemory.isWork(serverentitiesmemory.workbytes.MANAGE_ITEM()))
            {
                World world = this.skinningentities.world;
                SkinningInventory skinninginventory = serverentitiesmemory.skinninginventory;
                ItemStack itemstack = ItemStack.EMPTY;

                if (this.in_blockpos != null)
                {
                    IBlockState iblockstate = world.getBlockState(this.in_blockpos);
                    Block block = iblockstate.getBlock();

                    if (block.hasTileEntity(iblockstate))
                    {
                        TileEntity tileentity = world.getTileEntity(this.in_blockpos);

                        if (tileentity instanceof IInventory)
                        {
                            IInventory iinventory = (IInventory)tileentity;

                            for (int i = 0; i < skinninginventory.getSizeInventory(); ++i)
                            {
                                itemstack = skinninginventory.getStackInSlot(i);
                                if (!itemstack.isEmpty())
                                {
                                    break;
                                }
                            }

                            this.manage(iinventory, itemstack);
                        }
                    }
                }

                if (this.out_blockpos != null)
                {
                    IBlockState iblockstate = world.getBlockState(this.out_blockpos);
                    Block block = iblockstate.getBlock();

                    if (block.hasTileEntity(iblockstate))
                    {
                        TileEntity tileentity = world.getTileEntity(this.out_blockpos);

                        if (tileentity instanceof IInventory)
                        {
                            IInventory iinventory = (IInventory)tileentity;

                            for (int i = 0; i < iinventory.getSizeInventory(); ++i)
                            {
                                itemstack = iinventory.getStackInSlot(i);
                                if (!itemstack.isEmpty())
                                {
                                    break;
                                }
                            }

                            this.manage(skinninginventory, itemstack);
                        }
                    }
                }

                serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.MANAGE_ITEM() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.MANAGE_ITEM() % 8));
            }
        }
    }

    public void manage(IInventory iinventory, ItemStack itemstack)
    {
        for (int i = 0; i < iinventory.getSizeInventory(); ++i)
        {
            ItemStack inventories_itemstack = iinventory.getStackInSlot(i);

            if (inventories_itemstack.isEmpty() || isSameItemSameTags(inventories_itemstack, itemstack) && inventories_itemstack.getCount() < inventories_itemstack.getMaxStackSize())//canAddItem
            {
                int max_count = inventories_itemstack.getCount() + itemstack.getCount();
                int count = max_count - 64;

                if (inventories_itemstack.isEmpty() || isSameItemSameTags(inventories_itemstack, itemstack) && count <= 0)
                {
                    if (inventories_itemstack.isEmpty())
                    {
                        iinventory.setInventorySlotContents(i, itemstack);
                    }
                    else
                    {
                        inventories_itemstack.setCount(max_count);
                    }
                }
                else
                {
                    inventories_itemstack.setCount(64);
                    itemstack.setCount(count);
                }

                break;
            }
        }
    }
}
