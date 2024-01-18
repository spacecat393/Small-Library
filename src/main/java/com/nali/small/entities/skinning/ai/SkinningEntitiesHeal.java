package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.skinning.SkinningEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

import static com.nali.small.entities.EntitiesMathHelper.isTooClose;

public class SkinningEntitiesHeal extends SkinningEntitiesAI
{
    public int[] heal_frame_int_array;

    public int cooldown;
    public boolean heal = false;
    public float how_heal = 16.0F;
    public double minimum_distance = 8.0D;
    public byte state = -1;

    public SkinningEntitiesHeal(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        if (++this.cooldown >= 200 && this.skinningentities.isWork(this.skinningentities.skinningentitiesbytes.HEAL()) && !this.skinningentities.skinningentitiesarea.out_entity_arraylist.isEmpty())
        {
            this.heal = true;
            double[] far = new double[this.skinningentities.skinningentitiesarea.out_entity_arraylist.size()];

            int index = 0;
            for (Entity entity : this.skinningentities.skinningentitiesarea.out_entity_arraylist)
            {
                if (!(entity instanceof EntityLiving) || (entity.equals(this.skinningentities) && this.skinningentities.getHealth() == this.skinningentities.getMaxHealth()))
                {
                    far[index++] = Double.MAX_VALUE;
                }
                else if (((EntityLiving)entity).getHealth() < ((EntityLiving)entity).getMaxHealth())
                {
                    far[index++] = this.skinningentities.getDistanceSq(entity);

                    if (this.state == -1)
                    {
                        this.state = 0;
                    }

                    if (isTooClose(this.skinningentities, entity, this.minimum_distance))
                    {
                        if (this.state == 1)
                        {
                            this.cooldown = 0;
                            ((EntityLiving)entity).heal(this.how_heal);
                        }

                        this.skinningentities.skinningentitiesfindmove.endGoal();
                    }
                }
            }

            double max_dis = Double.MAX_VALUE;

            for (int i = 0; i < far.length; ++i)
            {
                if (far[i] < max_dis)
                {
                    index = i;
                    max_dis = far[i];
                }
            }

            Entity entity = this.skinningentities.skinningentitiesarea.out_entity_arraylist.get(index);

            /*if (this.state == 0 || this.state == 1)
            {
//                this.skinningentities.skinningentitieslook.set(entity.posX, entity.posY, entity.posZ, 20.0F);
            }
            else */if (!this.skinningentities.skinningentitiesfindmove.try_move)
            {
                this.state = 0;
//                this.skinningentities.skinningentitieslook.set(entity.posX, entity.posY, entity.posZ, 20.0F);
            }

            if (!isTooClose(this.skinningentities, entity, this.minimum_distance))
            {
                this.skinningentities.skinningentitiesfindmove.setGoal(entity.posX, entity.posY, entity.posZ);
                this.state = 3;
            }

            if (this.state == 1)
            {
                this.state = 0;
            }
        }
        else
        {
            if (this.heal)
            {
                this.state = -1;
                this.skinningentities.skinningentitiesfindmove.endGoal();
                this.heal = false;
            }

            this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.HEAL()] = 0;
        }
    }
}
