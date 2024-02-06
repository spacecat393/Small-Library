package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.ai.path.SkinningEntitiesFindMove;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;

import static com.nali.small.entities.EntitiesMathHelper.isInArea;

public class SkinningEntitiesGetItem extends SkinningEntitiesAI
{
    public boolean pickup;
    public int item_time_out, xp_time_out;

    public SkinningEntitiesGetItem(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        SkinningEntitiesFindMove skinningentitiesfindmove = this.skinningentities.skinningentitiesfindmove;

        boolean should_run = this.skinningentities.isWork(this.skinningentities.skinningentitiesbytes.FIND_ITEM());
        boolean keep_should_run = should_run;

        if (!this.skinningentities.skinningentitiesarea.xp_entity_arraylist.isEmpty())
        {
            EntityXPOrb to_entityxporb = this.skinningentities.skinningentitiesarea.xp_entity_arraylist.get(this.skinningentities.skinningentitiesarea.xp_entity_arraylist.size() - 1);
            ItemStack itemstack = EnchantmentHelper.getEnchantedItem(Enchantments.MENDING, this.skinningentities);
            boolean should_get_xp = !itemstack.isEmpty()/* && itemstack.isItemStackDamageable()*/ && itemstack.getItemDamage() > 0/* && EnchantmentHelper.getEnchantmentLevel(Enchantments.MENDING, itemstack) > 0*/;
            if (should_run && should_get_xp && (this.skinningentities.skinningentitiessetlocation.far == 0 || this.skinningentities.skinningentitiessetlocation.blockpos == null || isInArea(to_entityxporb, this.skinningentities.skinningentitiessetlocation.blockpos, this.skinningentities.skinningentitiessetlocation.far)))
            {
                this.pickup = true;
                should_run = false;
                skinningentitiesfindmove.setGoal(to_entityxporb.posX, to_entityxporb.posY, to_entityxporb.posZ);
            }

            for (EntityXPOrb entityxporb : this.skinningentities.skinningentitiesarea.xp_entity_arraylist)
            {
                if (should_get_xp && (this.skinningentities.getDistanceSq(entityxporb) <= 4.0D || (keep_should_run && ++this.xp_time_out >= 600)))
                {
                    this.xp_time_out = 0;

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

        if (!this.skinningentities.skinningentitiesarea.item_entity_arraylist.isEmpty())
        {
            EntityItem to_entityitem = this.skinningentities.skinningentitiesarea.item_entity_arraylist.get(this.skinningentities.skinningentitiesarea.item_entity_arraylist.size() - 1);
            if (should_run && (this.skinningentities.skinningentitiessetlocation.far == 0 || this.skinningentities.skinningentitiessetlocation.blockpos == null || isInArea(to_entityitem, this.skinningentities.skinningentitiessetlocation.blockpos, this.skinningentities.skinningentitiessetlocation.far)))
            {
                this.pickup = true;
                skinningentitiesfindmove.setGoal(to_entityitem.posX, to_entityitem.posY, to_entityitem.posZ);
            }

            for (EntityItem entityitem : this.skinningentities.skinningentitiesarea.item_entity_arraylist)
            {
                ItemStack itemstack = entityitem.getItem();

                if (this.skinningentities.getDistanceSq(entityitem) <= 4.0D || (should_run && ++this.item_time_out >= 600))
                {
                    this.item_time_out = 0;

                    if (this.pickup)
                    {
                        skinningentitiesfindmove.endGoal();
                        this.pickup = false;
                    }

                    for (int i = 0; i < this.skinningentities.skinninginventory.getSizeInventory(); ++i)
                    {
                        ItemStack inventories_itemstack = this.skinningentities.skinninginventory.getStackInSlot(i);

                        if (inventories_itemstack.isEmpty() || isSameItemSameTags(inventories_itemstack, itemstack) && inventories_itemstack.getCount() < inventories_itemstack.getMaxStackSize())//canAddItem
                        {
                            int max_count = inventories_itemstack.getCount() + itemstack.getCount();
                            int count = max_count - 64;

                            if (inventories_itemstack.isEmpty() || isSameItemSameTags(inventories_itemstack, itemstack) && count <= 0)
                            {
                                this.skinningentities.onItemPickup(entityitem, max_count);

                                if (inventories_itemstack.isEmpty())
                                {
                                    this.skinningentities.skinninginventory.setInventorySlotContents(i, itemstack);
                                }
                                else
                                {
                                    inventories_itemstack.setCount(max_count);
                                }

                                entityitem.setDead();
                            }
                            else
                            {
                                inventories_itemstack.setCount(64);
                                itemstack.setCount(count);
                                entityitem.setItem(itemstack);
                            }

                            break;
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
            this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.FIND_ITEM()] = 0;
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
