package com.nali.ilol.entities.skinning.ai;

import com.nali.ilol.entities.skinning.SkinningEntities;
import com.nali.ilol.entities.skinning.ai.path.SkinningEntitiesFindMove;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

public class SkinningEntitiesGetItem extends SkinningEntitiesAI
{
    public boolean pickup;
    public int time_out;

    public SkinningEntitiesGetItem(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        Entity[] entity_array = this.skinningentities.skinningentitiesarea.target_entity_array;
        EntityItem entityitem = (EntityItem)entity_array[1];
        SkinningEntitiesFindMove skinningentitiesfindmove = this.skinningentities.skinningentitiesfindmove;

        if (entityitem != null && entityitem.isEntityAlive())
        {
            if (this.skinningentities.isWork(this.skinningentities.skinningentitiesbytes.FIND_ITEM()))
            {
                this.pickup = true;
                // this.getLookControl().setLookAt(this.target_itementity, 10.0F, this.getMaxHeadXRot());
                skinningentitiesfindmove.setGoal(entityitem.posX, entityitem.posY, entityitem.posZ);
                // this.getNavigation().stop();
                // this.getNavigation().moveTo(this.target_itementity, 4.0D);
                // Main.LOGGER.info("setItemEntity Start");
            }

            ItemStack itemstack = entityitem.getItem();

            if (this.skinningentities.getDistanceSq(entityitem) < 4.0D/* || --this.pickup_tick <= 0*/ || ++this.time_out >= 600)
            {
                this.time_out = 0;

                if (this.pickup)
                {
                    skinningentitiesfindmove.endGoal();
                    this.pickup = false;
                }
//                entityitem.setDead();
//                entity_array[1] = null;
                // this.pickup_tick = 60;
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
//                            this.skinningentities.onItemPickup(entityitem, inventories_itemstack.getMaxStackSize() - inventories_itemstack.getCount());
                            inventories_itemstack.setCount(64);
                            itemstack.setCount(count);
                            entityitem.setItem(itemstack);
                        }

                        entity_array[1] = null;

                        break;
                    }
                }
            }
        }
//        else if (this.pickup)
//        {
//            skinningentitiesfindmove.endGoal();
//            entity_array[1] = null;
//            this.pickup = false;
//            // this.getNavigation().stop();
//            // ((EntitiesNodeEvaluator)this.getNavigation().getNodeEvaluator()).x_step = 0;
//            // Main.LOGGER.info("setItemEntity End");
//        }
        else if (this.pickup)
        {
            skinningentitiesfindmove.endGoal();
            entity_array[1] = null;
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
}
