package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.skinning.SkinningEntities;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHand;

import static com.nali.small.entities.EntitiesMathHelper.isTooClose;

public class SkinningEntitiesAttack extends SkinningEntitiesAI
{
    public int[] attack_frame_int_array;

    public boolean attack = false;
    public double minimum_distance = 3.0D;
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
        if (this.skinningentities.isWork(this.skinningentities.skinningentitiesbytes.ATTACK()) && !this.skinningentities.skinningentitiesarea.all_entity_arraylist.isEmpty())
        {
            this.attack = true;
//            boolean attack = false;
            double[] far = new double[this.skinningentities.skinningentitiesarea.all_entity_arraylist.size()];

            int index = 0;
            for (Entity entity : this.skinningentities.skinningentitiesarea.all_entity_arraylist)
            {
                far[index++] = this.skinningentities.getDistanceSq(entity);

                if (this.state == -1)
                {
                    this.state = 0;
                }

                if (this.skinningentities.canEntityBeSeen(entity) && isTooClose(this.skinningentities, entity, this.minimum_distance))
                {
//                    attack = true;

                    if (this.state == 1)
                    {
                        this.skinningentities.swingArm(EnumHand.MAIN_HAND);
                        this.skinningentities.attackEntityAsMob(entity);
                    }

//                    if (MixMath.isTooClose(this.skinningentities, entity, this.minimum_away_distance))
//                    {
//                        if (++this.out_tick == 60)
//                        {
//                            this.out_tick = 0;
//                            Vec3d a_pos = this.skinningentities.getPositionVector();
//                            Vec3d direction = a_pos.subtract(entity.getPositionVector()).normalize();
//                            Vec3d targetPos = a_pos.add(direction);
//                            this.skinningentities.skinningentitiesfindmove.setGoal(targetPos.x, targetPos.y, targetPos.z);
//                            this.state = 2;
//                        }
//                    }
//                    else
//                    {
                    this.skinningentities.skinningentitiesfindmove.endGoal();
//                    }
                }
//                else// if (++this.wait_tick == 60)
//                {
////                    this.wait_tick = 0;
//                    this.skinningentities.skinningentitiesfindmove.setGoal(entity.posX, entity.posY, entity.posZ);
//                    this.state = 3;
//                }
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

            Entity entity = this.skinningentities.skinningentitiesarea.all_entity_arraylist.get(index);

            if (this.state == 0 || this.state == 1)
            {
                this.skinningentities.skinningentitieslook.set(entity.posX, entity.posY, entity.posZ, 20.0F);
            }
            else if (!this.skinningentities.skinningentitiesfindmove.try_move)
            {
                this.state = 0;
                this.skinningentities.skinningentitieslook.set(entity.posX, entity.posY, entity.posZ, 20.0F);
            }

//            if (this.skinningentities.canEntityBeSeen(entity) && isTooClose(this.skinningentities, entity, this.minimum_distance))
//            {
//////                if (MixMath.isTooClose(this.skinningentities, entity, this.minimum_away_distance))
//////                {
//////                    Vec3d a_pos = this.skinningentities.getPositionVector();
//////                    Vec3d direction = a_pos.subtract(entity.getPositionVector()).normalize();
//////                    Vec3d targetPos = a_pos.add(direction);
//////                    this.skinningentities.skinningentitiesfindmove.setGoal(targetPos.x, targetPos.y, targetPos.z);
//////                    this.state = 2;
////                    if (entity instanceof EntityLivingBase)
////                    {
////                        ((EntityLivingBase)entity).moveForward = 0;
////                        ((EntityLivingBase)entity).moveVertical = 0;
////                        ((EntityLivingBase)entity).moveStrafing = 0;
////                    }
//////                }
//            }
//            else
            if (!(this.skinningentities.canEntityBeSeen(entity) && isTooClose(this.skinningentities, entity, this.minimum_distance)))
            {
                this.skinningentities.skinningentitiesfindmove.setGoal(entity.posX, entity.posY, entity.posZ);
                this.state = 3;
            }

            if (this.state == 1)
            {
                this.state = 0;
            }

//            if (attack && this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.ON_ATTACK()] != 1)
//            {
//                this.skinningentities.getDataManager().set(this.skinningentities.getByteDataParameterArray()[this.skinningentities.skinningentitiesbytes.ON_ATTACK()], (byte)1);
//            }
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
}
