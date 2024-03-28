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

public class SkinningEntitiesGetItem extends SkinningEntitiesAI
{
    //-8
    public byte state = 8;//remote_xp remote_item can_take_xp can_take_item walk_to_xp walk_to_item
    public boolean pickup;
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

        boolean should_run = serverentitiesmemory.isWork(serverentitiesmemory.workbytes.GET_ITEM());
        boolean keep_should_run = should_run;

        if ((serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.MINE() / 8] >> serverentitiesmemory.workbytes.MINE() % 8 & 1) == 1 && serverentitiesmemory.entitiesaimemory.skinningentitiesmine.blockpos != null)
        {
            serverentitiesmemory.entitiesaimemory.skinningentitiesmine.breakWork();
        }

        if (!serverentitiesmemory.entitiesaimemory.skinningentitiesarea.xp_entity_arraylist.isEmpty())
        {
            EntityXPOrb to_entityxporb = serverentitiesmemory.entitiesaimemory.skinningentitiesarea.xp_entity_arraylist.get(serverentitiesmemory.entitiesaimemory.skinningentitiesarea.xp_entity_arraylist.size() - 1);
            ItemStack itemstack = EnchantmentHelper.getEnchantedItem(Enchantments.MENDING, this.skinningentities);
            boolean should_get_xp = !itemstack.isEmpty()/* && itemstack.isItemStackDamageable()*/ && itemstack.getItemDamage() > 0/* && EnchantmentHelper.getEnchantmentLevel(Enchantments.MENDING, itemstack) > 0*/;

            if ((this.state & 16) == 16)
            {
                if (should_run && should_get_xp && (serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far == 0 || serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos == null || isInArea(to_entityxporb, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far)))
                {
                    this.pickup = true;
                    should_run = false;
                    serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.setBreakGoal(to_entityxporb.posX, to_entityxporb.posY, to_entityxporb.posZ);
//                skinningentitiesfindmove.setGoal(to_entityxporb.posX, to_entityxporb.posY, to_entityxporb.posZ);
                }
            }

            if ((this.state & 4) == 4)
            {
                for (EntityXPOrb entityxporb : serverentitiesmemory.entitiesaimemory.skinningentitiesarea.xp_entity_arraylist)
                {
                    if (should_get_xp && (this.skinningentities.getDistanceSq(entityxporb) <= 4.0D || (keep_should_run && (this.state & 1) == 1/*++this.xp_time_out >= 600*/)))
                    {
//                    this.xp_time_out = 0;

                        if (this.pickup)
                        {
                            skinningentitiesfindmove.endGoal();
                            this.pickup = false;
                        }

                        this.skinningentities.onItemPickup(entityxporb, 1);

                        if (!itemstack.isEmpty() && itemstack.isItemDamaged())
                        {
                            float ratio = itemstack.getItem().getXpRepairRatio(itemstack);
                            int i = Math.min(roundAverage(entityxporb.xpValue * ratio), itemstack.getItemDamage());
                            entityxporb.xpValue -= roundAverage(i / ratio);
                            itemstack.setItemDamage(itemstack.getItemDamage() - i);
                        }

                        entityxporb.setDead();
                    }
                }
            }
        }

        if (!serverentitiesmemory.entitiesaimemory.skinningentitiesarea.item_entity_arraylist.isEmpty())
        {
            EntityItem to_entityitem = serverentitiesmemory.entitiesaimemory.skinningentitiesarea.item_entity_arraylist.get(serverentitiesmemory.entitiesaimemory.skinningentitiesarea.item_entity_arraylist.size() - 1);
            if ((this.state & 32) == 32)
            {
                if (should_run && (serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far == 0 || serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos == null || isInArea(to_entityitem, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far)))
                {
                    this.pickup = true;
                    serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.setBreakGoal(to_entityitem.posX, to_entityitem.posY, to_entityitem.posZ);
//                skinningentitiesfindmove.setGoal(to_entityitem.posX, to_entityitem.posY, to_entityitem.posZ);
                }
            }

            if ((this.state & 8) == 8)
            {
                for (EntityItem entityitem : serverentitiesmemory.entitiesaimemory.skinningentitiesarea.item_entity_arraylist)
                {
                    ItemStack itemstack = entityitem.getItem();

                    if (this.skinningentities.getDistanceSq(entityitem) <= 4.0D || (should_run && (this.state & 2) == 2/*++this.item_time_out >= 600*/))
                    {
//                    this.item_time_out = 0;

                        if (this.pickup)
                        {
                            skinningentitiesfindmove.endGoal();
                            this.pickup = false;
                        }

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

        if (!keep_should_run && this.pickup)
        {
            skinningentitiesfindmove.endGoal();
            this.pickup = false;
        }

        if (!this.pickup)
        {
            serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.GET_ITEM() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.GET_ITEM() % 8));//0
        }
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
