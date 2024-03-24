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

import java.util.Random;

import static com.nali.small.entities.skinning.ai.SkinningEntitiesGetItem.isSameItemSameTags;

public class SkinningEntitiesManageItem extends SkinningEntitiesAI
{
    public BlockPos in_blockpos, out_blockpos;
    public byte state;//remote_in remote_out random_in random_out
    public int random_area_in = 1, random_area_out = 1;

    public SkinningEntitiesManageItem(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;

        if (serverentitiesmemory.isWork(serverentitiesmemory.workbytes.MANAGE_ITEM()))
        {
            if (this.skinningentities.ticksExisted % 20 == 0)
            {
                Random random = this.skinningentities.getRNG();
                World world = this.skinningentities.world;
                SkinningInventory skinninginventory = serverentitiesmemory.skinninginventory;
                ItemStack itemstack = ItemStack.EMPTY;

                if ((serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.MINE() / 8] >> serverentitiesmemory.workbytes.MINE() % 8 & 1) == 1 && serverentitiesmemory.entitiesaimemory.skinningentitiesmine.blockpos != null)
                {
                    serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.MANAGE_ITEM() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.MANAGE_ITEM() % 8));

                    serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.FIND_ITEM() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.ATTACK() % 8));
                }

                //random
                if ((this.state & 4) == 4)
                {
                    this.in_blockpos = new BlockPos(this.skinningentities.posX + random.nextInt(this.random_area_in) - random.nextInt(this.random_area_in), this.skinningentities.posY + random.nextInt(this.random_area_in) - random.nextInt(this.random_area_in), this.skinningentities.posZ + random.nextInt(this.random_area_in) - random.nextInt(this.random_area_in));
                }
                if ((this.state & 8) == 8)
                {
                    this.out_blockpos = new BlockPos(this.skinningentities.posX + random.nextInt(this.random_area_out) - random.nextInt(this.random_area_out), this.skinningentities.posY + random.nextInt(this.random_area_out) - random.nextInt(this.random_area_out), this.skinningentities.posZ + random.nextInt(this.random_area_out) - random.nextInt(this.random_area_out));
                }

                if (this.in_blockpos != null)
                {
                    //walk to
                    if ((this.state & 1) == 1 || this.skinningentities.getDistanceSq(this.in_blockpos) < 4.0D)
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
                    else
                    {
                        serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.setBreakGoal(this.in_blockpos.getX(), this.in_blockpos.getY(), this.in_blockpos.getZ());
                    }
                }
                if (this.out_blockpos != null)
                {
                    if ((this.state & 2) == 2 || this.skinningentities.getDistanceSq(this.out_blockpos) < 4.0D)
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
                    else
                    {
                        serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.setBreakGoal(this.out_blockpos.getX(), this.out_blockpos.getY(), this.out_blockpos.getZ());
                    }
                }
            }

            serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.MANAGE_ITEM() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.MANAGE_ITEM() % 8));
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
