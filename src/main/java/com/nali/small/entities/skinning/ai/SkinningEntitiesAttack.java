package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHand;

import java.util.ArrayList;

import static com.nali.small.entities.EntitiesMath.isInArea;
import static com.nali.small.entities.EntitiesMath.isTooClose;

public class SkinningEntitiesAttack extends SkinningEntitiesAI
{
    public int[] attack_frame_int_array;

    public boolean attack = false;
    public double minimum_distance = 3.0D;
    public byte max_ammo = 16;
//    public double minimum_away_distance = 2.0D;
//    public byte wait_tick = 0;
//    public byte out_tick = 0;
    public byte state = -1;

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
            serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.CARE_OWNER() / 8] |= (byte)Math.pow(2, serverentitiesmemory.workbytes.CARE_OWNER() % 8);//1
            should_work = serverentitiesmemory.isWork(serverentitiesmemory.workbytes.CARE_OWNER());
            serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.CARE_OWNER() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.CARE_OWNER() % 8));//0
        }

        boolean work = serverentitiesmemory.isWork(serverentitiesmemory.workbytes.ATTACK());
        if ((!work && should_work && !serverentitiesmemory.entitiesaimemory.skinningentitiescareowner.target_entity_arraylist.isEmpty()) || (work && !serverentitiesmemory.entitiesaimemory.skinningentitiesarea.all_entity_arraylist.isEmpty()))
        {
            if ((serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.MINE() / 8] >> serverentitiesmemory.workbytes.MINE() % 8 & 1) == 1 && serverentitiesmemory.entitiesaimemory.skinningentitiesmine.blockpos != null)
            {
//                if (this.attack)
//                {
//                    this.state = -1;
////                    serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();
//                    this.attack = false;
//                }
//                serverentitiesmemory.entitiesaimemory.skinningentitiesmine.walk();
                serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.ATTACK() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.ATTACK() % 8));//0

                serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.MANAGE_ITEM() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.MANAGE_ITEM() % 8));
                serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.FIND_ITEM() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.ATTACK() % 8));
            }
//            else
//            {
            this.attack = true;
//            boolean attack = false;

            Entity target_entity;
            if (!serverentitiesmemory.entitiesaimemory.skinningentitiescareowner.target_entity_arraylist.isEmpty())
            {
                target_entity = this.attackAndFind(serverentitiesmemory.entitiesaimemory.skinningentitiescareowner.target_entity_arraylist);
            }
            else
            {
                target_entity = this.attackAndFind(serverentitiesmemory.entitiesaimemory.skinningentitiesarea.all_entity_arraylist);
            }

            if (serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far == 0 || serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos == null || isInArea(target_entity, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far))
            {
                if (this.state == 0 || this.state == 1)
                {
                    serverentitiesmemory.entitiesaimemory.skinningentitieslook.set(target_entity.posX, target_entity.posY, target_entity.posZ, 20.0F);
                }
                else if (!serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.try_move)
                {
                    this.state = 0;
                    serverentitiesmemory.entitiesaimemory.skinningentitieslook.set(target_entity.posX, target_entity.posY, target_entity.posZ, 20.0F);
                }

                if (!(this.skinningentities.canEntityBeSeen(target_entity) && isTooClose(this.skinningentities, target_entity, this.minimum_distance)))
                {
                    serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.setBreakGoal(target_entity.posX, target_entity.posY, target_entity.posZ);
                    this.state = 3;
                }
            }

            if (this.state == 1)
            {
                this.state = 0;
            }
//            }
        }
        else
        {
            if (this.attack)
            {
                this.state = -1;
                serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();
                this.attack = false;
            }

            serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.ATTACK() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.ATTACK() % 8));//0
        }
    }

    public Entity attackAndFind(ArrayList<Entity> entity_arraylist)
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        double[] far = new double[entity_arraylist.size()];
        int index = 0;
        for (Entity entity : entity_arraylist)
        {
            far[index++] = this.skinningentities.getDistanceSq(entity);
            if (serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far == 0 || serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos == null ||
                isInArea(entity, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far))
            {
                if (this.state == -1)
                {
                    this.state = 0;
                }

                if (this.skinningentities.canEntityBeSeen(entity) && isTooClose(this.skinningentities, entity, this.minimum_distance))
                {
                    if (this.state == 1)
                    {
                        this.skinningentities.swingArm(EnumHand.MAIN_HAND);
                        this.skinningentities.attackEntityAsMob(entity);
                    }

                    serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();
                }
            }
        }

        index = 0;

        double max_dis = Double.MAX_VALUE;

        for (int i = 0; i < far.length; ++i)
        {
            if (far[i] < max_dis)
            {
                index = i;
                max_dis = far[i];
            }
        }

        return entity_arraylist.get(index);
    }
}
