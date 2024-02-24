package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import static com.nali.small.entities.EntitiesMath.isTooClose;

public class SkinningEntitiesHeal extends SkinningEntitiesAI
{
    public int[] heal_frame_int_array;

    public int cooldown;
    public boolean heal = false;
    public float how_heal = 16.0F;
    public double minimum_distance = 3.0D;
    public byte state = -1;

    public SkinningEntitiesHeal(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        if (this.skinningentities.isWork(serverentitiesmemory.workbytes.HEAL()) && !serverentitiesmemory.entitiesaimemory.skinningentitiesarea.out_entity_arraylist.isEmpty() && ++this.cooldown >= 200)
        {
            this.heal = true;
            double[] far = new double[serverentitiesmemory.entitiesaimemory.skinningentitiesarea.out_entity_arraylist.size()];
            boolean should_move = false;
            boolean should_move2 = true;

            int index = 0;
            for (Entity entity : serverentitiesmemory.entitiesaimemory.skinningentitiesarea.out_entity_arraylist)
            {
                if (!(entity instanceof EntityLivingBase) || ((EntityLivingBase)entity).getMaxHealth() - ((EntityLivingBase)entity).getHealth() < 1.0F)
                {
                    far[index] = Double.MAX_VALUE;
                }
                else
                {
                    if (this.skinningentities.equals(entity))
                    {
                        should_move2 = false;
                    }

                    far[index] = this.skinningentities.getDistanceSq(entity);

                    if (isTooClose(this.skinningentities, entity, this.minimum_distance))
                    {
                        if (this.state == -1)
                        {
                            this.state = 0;
                        }

                        if (this.state == 1)
                        {
                            ((EntityLivingBase)entity).heal(this.how_heal);
                        }

                        serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();
                    }
                    else
                    {
                        should_move = true;
                    }
                }
                ++index;
            }

            if (should_move && should_move2)
            {
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

                Entity entity = serverentitiesmemory.entitiesaimemory.skinningentitiesarea.out_entity_arraylist.get(index);

//            /*if (this.state == 0 || this.state == 1)
//            {
////                serverentitiesmemory.entitiesaimemory.skinningentitieslook.set(entity.posX, entity.posY, entity.posZ, 20.0F);
//            }
//            else */if (!serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.try_move)
//            {
//                this.state = 0;
////                serverentitiesmemory.entitiesaimemory.skinningentitieslook.set(entity.posX, entity.posY, entity.posZ, 20.0F);
//            }

//                if (!isTooClose(this.skinningentities, entity, this.minimum_distance))
//                {
                serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.setGoal(entity.posX, entity.posY, entity.posZ);
//                this.state = -1;
//                }
            }

            if (this.state == 1)
            {
                this.cooldown = 0;
                this.state = -1;
            }

            if (!should_move && should_move2)
            {
                serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.HEAL()] = 0;
            }
        }
        else
        {
            if (this.heal)
            {
                this.cooldown = 0;
                this.state = -1;
                serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();
                this.heal = false;
            }

            serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.HEAL()] = 0;
        }
    }
}
