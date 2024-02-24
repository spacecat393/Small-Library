package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.mixin.IMixinEntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;

public class SkinningEntitiesCareOwner extends SkinningEntitiesAI
{
    public ArrayList<Entity> target_entity_arraylist = new ArrayList<Entity>();
    public ArrayList<Double> far_double_arraylist = new ArrayList<Double>();

    public SkinningEntitiesCareOwner(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        Entity owner_entity = this.skinningentities.getOwner();
        if (this.skinningentities.isWork(serverentitiesmemory.workbytes.CARE_OWNER()) && owner_entity != null && !serverentitiesmemory.entitiesaimemory.skinningentitiesarea.all_entity_arraylist.isEmpty())
        {
            ArrayList<Entity> target_entity_arraylist = (ArrayList<Entity>)this.target_entity_arraylist.clone();
            this.target_entity_arraylist.clear();
            this.far_double_arraylist.clear();
            for (Entity entity : serverentitiesmemory.entitiesaimemory.skinningentitiesarea.all_entity_arraylist)
            {
                boolean should_attack = false;
                for (Entity old_entity : target_entity_arraylist)
                {
                    if (old_entity.equals(entity))
                    {
                        should_attack = true;
                        break;
                    }
                }

                if (should_attack || ourTarget(entity, owner_entity) || ourTarget(entity, this.skinningentities))
                {
                    this.target_entity_arraylist.add(entity);
                    this.far_double_arraylist.add(this.skinningentities.getDistanceSq(entity));
                }
            }
//            if (!this.far_double_arraylist.isEmpty())
//            {
//                int index = 0;
//                double max_dis = Double.MAX_VALUE;
//                for (int i = 0; i < this.far_double_arraylist.size(); ++i)
//                {
//                    double far = this.far_double_arraylist.get(i);
//                    if (far < max_dis)
//                    {
//                        index = i;
//                        max_dis = far;
//                    }
//                }
//
//                this.target_entity = serverentitiesmemory.entitiesaimemory.skinningentitiesarea.all_entity_arraylist.get(index);
//            }
//            else
//            {
//                this.target_entity = null;
//            }
        }
        else
        {
            this.target_entity_arraylist.clear();
            this.far_double_arraylist.clear();
        }

        serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.CARE_OWNER()] = 0;
    }

    public static boolean ourTarget(Entity entity, Entity us_entity)
    {
        return (entity instanceof EntityLivingBase &&
        (
            us_entity.equals(((EntityLivingBase)entity).getCombatTracker().getBestAttacker()) ||
            us_entity.equals(((IMixinEntityLivingBase)entity).attackingPlayer()) ||
            us_entity.equals(((EntityLivingBase)entity).getRevengeTarget()) ||
            us_entity.equals(((EntityLivingBase)entity).getLastAttackedEntity()) ||
            ((EntityLivingBase)entity).getLastDamageSource() != null && us_entity.equals(((EntityLivingBase)entity).getLastDamageSource().getTrueSource())
        ) || (entity instanceof EntityLiving && us_entity.equals(((EntityLiving)entity).getAttackTarget())))
        && /*!us_entity.equals(entity) && */!(entity instanceof EntityPlayer);// && fastCheck(entity);
    }
}
