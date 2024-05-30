package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHand;

import java.util.List;

import static com.nali.small.entities.EntitiesMath.isInArea;
import static com.nali.small.entities.EntitiesMath.isTooClose;

public class SkinningEntitiesAttack extends SkinningEntitiesAI
{
    public int[] attack_frame_int_array;

    public byte flag = 16;//move_to prepare hit | remote walk_to
    public float minimum_distance = 3.0F;
    public int magic_point,
    max_magic_point = 16;

    public SkinningEntitiesAttack(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        boolean should_work = false;
        if ((serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.CARE_OWNER() / 8] >> serverentitiesmemory.workbytes.CARE_OWNER() % 8 & 1) == 1)
        {
            serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.CARE_OWNER() / 8] |= (byte)Math.pow(2, serverentitiesmemory.workbytes.CARE_OWNER() % 8);
            should_work = serverentitiesmemory.isWork(serverentitiesmemory.workbytes.CARE_OWNER());
            serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.CARE_OWNER() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.CARE_OWNER() % 8));
        }

        boolean work = serverentitiesmemory.isWork(serverentitiesmemory.workbytes.ATTACK());
        if ((!work && should_work && !serverentitiesmemory.entitiesaimemory.skinningentitiescareowner.target_entity_list.isEmpty()) || (work && !serverentitiesmemory.entitiesaimemory.skinningentitiesarea.all_entity_list.isEmpty()))
        {
            if ((serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.MINE() / 8] >> serverentitiesmemory.workbytes.MINE() % 8 & 1) == 1 && serverentitiesmemory.entitiesaimemory.skinningentitiesmine.blockpos != null)
            {
                serverentitiesmemory.entitiesaimemory.skinningentitiesmine.breakWork();
            }

            Entity target_entity;
            if (!serverentitiesmemory.entitiesaimemory.skinningentitiescareowner.target_entity_list.isEmpty())
            {
                target_entity = this.attackAndFind(serverentitiesmemory.entitiesaimemory.skinningentitiescareowner.target_entity_list);
            }
            else
            {
                target_entity = this.attackAndFind(serverentitiesmemory.entitiesaimemory.skinningentitiesarea.all_entity_list);
            }

            if (serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far == 0 || serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos == null || isInArea(target_entity, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far))
            {
                this.flag |= 2;

                if (!serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.try_move)
                {
                    serverentitiesmemory.entitiesaimemory.skinningentitieslook.set(target_entity.posX, target_entity.posY, target_entity.posZ, 20.0F);
                }

                if ((this.flag & 16+8) == 16 && !(this.skinningentities.canEntityBeSeen(target_entity) && isTooClose(this.skinningentities, target_entity, this.minimum_distance)))
                {
                    serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.setBreakGoal(target_entity.posX, target_entity.posY, target_entity.posZ);
                    this.flag |= 1;
                    this.flag &= 255-2;
                }
            }

            this.flag &= 255-4;
        }
        else
        {
            if ((this.flag & 1) == 1)
            {
                serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();
//                this.flag &= 255-(1+2+4);
            }
            this.flag &= 255-(1+2+4);

            serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.ATTACK() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.ATTACK() % 8));
        }
    }

    public Entity attackAndFind(List<Entity> entity_list)
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
//        double[] far = new double[entity_list.size()];
//        int index = 0;
        int index = -1;
        double max_dis = Double.MAX_VALUE;
        for (int i = 0; i < entity_list.size(); ++i)
        {
            Entity entity = entity_list.get(i);
            double far = this.skinningentities.getDistanceSq(entity);
            if (far < max_dis)
            {
                index = i;
                max_dis = far;
            }
//            far[index++] = this.skinningentities.getDistanceSq(entity);
            if (serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far == 0 || serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos == null ||
                isInArea(entity, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far))
            {
                if ((this.flag & 8) == 8 || (this.skinningentities.canEntityBeSeen(entity) && isTooClose(this.skinningentities, entity, this.minimum_distance)))
                {
                    if ((this.flag & 4) == 4)
                    {
                        this.skinningentities.swingArm(EnumHand.MAIN_HAND);
                        this.skinningentities.attackEntityAsMob(entity);
                    }

                    serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();
                }
            }
        }

//        index = 0;

//        double max_dis = Double.MAX_VALUE;

//        for (int i = 0; i < far.length; ++i)
//        {
//            if (far[i] < max_dis)
//            {
//                index = i;
//                max_dis = far[i];
//            }
//        }

        if (index == -1)
        {
            return null;
        }
        else
        {
            return entity_list.get(index);
        }
    }
}
