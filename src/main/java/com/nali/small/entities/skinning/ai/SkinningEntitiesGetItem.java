package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.ai.path.SkinningEntitiesFindMove;
import com.nali.small.mixin.MixinInventoryCrafting;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

import static com.nali.small.entities.EntitiesMath.isInArea;
import static com.nali.small.entities.EntitiesMath.isTooClose;

public class SkinningEntitiesGetItem extends SkinningEntitiesAI
{
    public byte flag;//move_to | remote_xp remote_item can_take_xp can_take_item walk_to_xp walk_to_item
//    public boolean pickup;
//    public int item_time_out, xp_time_out;

    public SkinningEntitiesGetItem(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        SkinningEntitiesFindMove skinningentitiesfindmove = serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove;

        boolean xp = !serverentitiesmemory.entitiesaimemory.skinningentitiesarea.xp_entity_list.isEmpty();
        EntityXPOrb to_entityxporb = null;
        ItemStack xp_itemstack = null;
        boolean should_get_xp = false;
        if (xp)
        {
            to_entityxporb = serverentitiesmemory.entitiesaimemory.skinningentitiesarea.xp_entity_list.get(serverentitiesmemory.entitiesaimemory.skinningentitiesarea.xp_entity_list.size() - 1);
            xp_itemstack = EnchantmentHelper.getEnchantedItem(Enchantments.MENDING, this.skinningentities);
            should_get_xp = !xp_itemstack.isEmpty()/* && itemstack.isItemStackDamageable()*/ && xp_itemstack.getItemDamage() > 0/* && EnchantmentHelper.getEnchantmentLevel(Enchantments.MENDING, itemstack) > 0*/;
        }

        boolean item = !serverentitiesmemory.entitiesaimemory.skinningentitiesarea.item_entity_list.isEmpty();

        if (serverentitiesmemory.isWork(serverentitiesmemory.workbytes.GET_ITEM()))
        {
            if ((serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.MINE() / 8] >> serverentitiesmemory.workbytes.MINE() % 8 & 1) == 1 && serverentitiesmemory.entitiesaimemory.skinningentitiesmine.blockpos != null)
            {
                serverentitiesmemory.entitiesaimemory.skinningentitiesmine.breakWork();
            }

            if (xp && should_get_xp)
            {
                if ((this.flag & 32) == 32 && !isTooClose(this.skinningentities, to_entityxporb, 0))
                {
                    if (serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far == 0 || serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos == null || isInArea(to_entityxporb, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far))
                    {
//                        Nali.LOGGER.info("XP START");
                        this.flag |= 1;
                        skinningentitiesfindmove.setBreakGoal(to_entityxporb.posX, to_entityxporb.posY, to_entityxporb.posZ);
                    }
                }
            }
            else if (item)
            {
                EntityItem to_entityitem = serverentitiesmemory.entitiesaimemory.skinningentitiesarea.item_entity_list.get(serverentitiesmemory.entitiesaimemory.skinningentitiesarea.item_entity_list.size() - 1);
                if ((this.flag & 64) == 64 && !isTooClose(this.skinningentities, to_entityitem, 0))
                {
                    if (serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far == 0 || serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos == null || isInArea(to_entityitem, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far))
                    {
//                        Nali.LOGGER.info("ITEM START");
                        this.flag |= 1;
                        skinningentitiesfindmove.setBreakGoal(to_entityitem.posX, to_entityitem.posY, to_entityitem.posZ);
                    }
                }
            }
        }
        else
        {
            serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.GET_ITEM() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.GET_ITEM() % 8));
        }

        if (xp)
        {
            for (EntityXPOrb entityxporb : serverentitiesmemory.entitiesaimemory.skinningentitiesarea.xp_entity_list)
            {
                if (should_get_xp)
                {
                    if ((this.flag & 2) == 2 || isTooClose(this.skinningentities, entityxporb, 0))
                    {
                        if ((this.flag & 1) == 1)
                        {
//                            Nali.LOGGER.info("XP END");
                            skinningentitiesfindmove.endGoal();
                            this.flag &= 255-1;
                            serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.GET_ITEM() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.GET_ITEM() % 8));
                        }
//                        Nali.LOGGER.info("XP STEP");
                        if ((this.flag & 8) == 8)
                        {
//                            Nali.LOGGER.info("XP TAKE");
                            this.skinningentities.onItemPickup(entityxporb, 1);

                            if (!xp_itemstack.isEmpty() && xp_itemstack.isItemDamaged())
                            {
                                float ratio = xp_itemstack.getItem().getXpRepairRatio(xp_itemstack);
                                int i = Math.min(roundAverage(entityxporb.xpValue * ratio), xp_itemstack.getItemDamage());
                                entityxporb.xpValue -= roundAverage(i / ratio);
                                xp_itemstack.setItemDamage(xp_itemstack.getItemDamage() - i);
                            }

                            entityxporb.setDead();
                        }
                    }
                }
            }
        }

        if (item)
        {
            for (EntityItem entityitem : serverentitiesmemory.entitiesaimemory.skinningentitiesarea.item_entity_list)
            {
                ItemStack itemstack = entityitem.getItem();

                if ((this.flag & 4) == 4 || isTooClose(this.skinningentities, entityitem, 0))
                {
                    if ((this.flag & 1) == 1)
                    {
//                        Nali.LOGGER.info("ITEM END");
                        skinningentitiesfindmove.endGoal();
                        this.flag &= 255-1;
                        serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.GET_ITEM() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.GET_ITEM() % 8));
                    }
//                    Nali.LOGGER.info("ITEM STEP");
                    if ((this.flag & 16) == 16)
                    {
//                        Nali.LOGGER.info("ITEM TAKE");
                        for (byte i = 0; i < serverentitiesmemory.skinninginventory.getSizeInventory(); ++i)
                        {
                            ItemStack inv_itemstack = serverentitiesmemory.skinninginventory.getStackInSlot(i);

                            int max_stack = inv_itemstack.getMaxStackSize();
                            int e_count = itemstack.getCount();

                            if (inv_itemstack.isEmpty())
                            {
                                this.skinningentities.onItemPickup(entityitem, e_count);
                                serverentitiesmemory.skinninginventory.setInventorySlotContents(i, itemstack);
                                entityitem.setDead();

                                if (i >= 27-3*3+2+4 && i <= 27-3*3+2+4+3*3)
                                {
                                    Container container = ((MixinInventoryCrafting)this.skinningentities.bothentitiesmemory.skinninginventory.inventorycrafting).eventHandler();
                                    if (container != null)
                                    {
                                        container.onCraftMatrixChanged(this.skinningentities.bothentitiesmemory.skinninginventory.inventorycrafting);
                                    }
                                }

                                break;
                            }

                            int max_count = inv_itemstack.getCount() + e_count;
                            int count = max_count - max_stack;
                            if (isSameItemSameTags(inv_itemstack, itemstack))
                            {
                                if (count <= 0)
                                {
                                    this.skinningentities.onItemPickup(entityitem, max_count);
                                    inv_itemstack.setCount(max_count);
                                    entityitem.setDead();

                                    break;
                                }
                                else
                                {
                                    inv_itemstack.setCount(max_stack);
                                    itemstack.setCount(count);
                                    entityitem.setItem(itemstack);
                                }
                            }
                        }
                    }
                }
            }
        }

//        if ((this.flag & 1) == 1)
//        {
//            skinningentitiesfindmove.endGoal();
//            this.flag &= 255-1;
//            serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.GET_ITEM() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.GET_ITEM() % 8));
//        }
    }

    public static boolean isSameItemSameTags(ItemStack itemstack_a, ItemStack itemstack_b)
    {
        return itemstack_a.getItem() == itemstack_b.getItem() && ItemStack.areItemStackTagsEqual(itemstack_a, itemstack_b);
    }

    public static int roundAverage(float value)
    {
        double floor = Math.floor(value);
        return (int) floor + (Math.random() < value - floor ? 1 : 0);
    }
}
