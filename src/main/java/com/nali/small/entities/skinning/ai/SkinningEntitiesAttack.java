package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.skinning.SkinningEntities;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHand;

import java.util.ArrayList;

import static com.nali.small.entities.EntitiesMathHelper.isTooClose;

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
        boolean should_work = false;
        if (this.skinningentities.main_server_work_byte_array[this.skinningentities.skinningentitiesbytes.CARE_OWNER()] == 1)
        {
            this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.CARE_OWNER()] = 1;
            should_work = this.skinningentities.isWork(this.skinningentities.skinningentitiesbytes.CARE_OWNER());
            this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.CARE_OWNER()] = 0;
        }

        boolean work = this.skinningentities.isWork(this.skinningentities.skinningentitiesbytes.ATTACK());
        if ((!work && should_work && !this.skinningentities.skinningentitiescareowner.target_entity_arraylist.isEmpty()) || (work && !this.skinningentities.skinningentitiesarea.all_entity_arraylist.isEmpty()))
        {
            this.attack = true;
//            boolean attack = false;

            Entity target_entity;
            if (!this.skinningentities.skinningentitiescareowner.target_entity_arraylist.isEmpty())
            {
                target_entity = this.attackAndFind(this.skinningentities.skinningentitiescareowner.target_entity_arraylist);
            }
            else
            {
                target_entity = this.attackAndFind(this.skinningentities.skinningentitiesarea.all_entity_arraylist);
            }

            if (this.skinningentities.skinningentitiessetlocation.far == 0 || this.skinningentities.getDistanceSq(target_entity) <= this.skinningentities.skinningentitiessetlocation.far)
            {
                if (this.state == 0 || this.state == 1)
                {
                    this.skinningentities.skinningentitieslook.set(target_entity.posX, target_entity.posY, target_entity.posZ, 20.0F);
                }
                else if (!this.skinningentities.skinningentitiesfindmove.try_move)
                {
                    this.state = 0;
                    this.skinningentities.skinningentitieslook.set(target_entity.posX, target_entity.posY, target_entity.posZ, 20.0F);
                }

                if (!(this.skinningentities.canEntityBeSeen(target_entity) && isTooClose(this.skinningentities, target_entity, this.minimum_distance)))
                {
                    this.skinningentities.skinningentitiesfindmove.setGoal(target_entity.posX, target_entity.posY, target_entity.posZ);
                    this.state = 3;
                }
            }

            if (this.state == 1)
            {
                this.state = 0;
            }
        }
        else
        {
            if (this.attack)
            {
                this.state = -1;
                this.skinningentities.skinningentitiesfindmove.endGoal();
                this.attack = false;
            }

            this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.ATTACK()] = 0;
        }
    }

    public Entity attackAndFind(ArrayList<Entity> entity_arraylist)
    {
        double[] far = new double[entity_arraylist.size()];
        int index = 0;
        for (Entity entity : entity_arraylist)
        {
            far[index++] = this.skinningentities.getDistanceSq(entity);
            if (this.skinningentities.skinningentitiessetlocation.far == 0 || this.skinningentities.getDistanceSq(entity) <= this.skinningentities.skinningentitiessetlocation.far)
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

                    this.skinningentities.skinningentitiesfindmove.endGoal();
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
