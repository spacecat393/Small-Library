package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import java.util.ArrayList;
import java.util.List;

import static com.nali.small.entities.EntitiesMath.isTooClose;

public class SkinningEntitiesProtect extends SkinningEntitiesAI
{
    public boolean protect = false;
    public List<Entity> entity_list = new ArrayList();
    public List<Integer> cooldown_int_list = new ArrayList();
    public double minimum_distance = 1.5D;
    public byte state = -1, main_state = -1;

    public SkinningEntitiesProtect(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        if (this.main_state != -1)
        {
            this.state = this.main_state;
            this.main_state = -1;
        }

        for (int i = 0; i < this.cooldown_int_list.size(); ++i)
        {
            this.cooldown_int_list.set(i, this.cooldown_int_list.get(i) + 1);
            if (this.cooldown_int_list.get(i) == 1200)
            {
                this.entity_list.remove(i);
                this.cooldown_int_list.remove(i);
                --i;
            }
        }

        if (serverentitiesmemory.isWork(serverentitiesmemory.workbytes.PROTECT()) && !serverentitiesmemory.entitiesaimemory.skinningentitiesarea.out_entity_list.isEmpty())
        {
            this.protect = true;
            serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();

            if (this.state < 0)
            {
                this.state = 0;
            }

            for (Entity entity : serverentitiesmemory.entitiesaimemory.skinningentitiesarea.out_entity_list)
            {
                if (isTooClose(this.skinningentities, entity, this.minimum_distance))
                {
                    boolean in_list = false;
                    for (Entity in_entity : this.entity_list)
                    {
                        in_list = in_entity.equals(entity);
                        if (in_list)
                        {
                            break;
                        }
                    }

                    if (!in_list && this.state == 1 || this.state == 2)
                    {
                        this.entity_list.add(entity);
                        this.cooldown_int_list.add(0);
                        this.state = 2;

                        if (entity instanceof EntityLivingBase)
                        {
//                            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 1200, 10));
                            entity.getEntityData().setByte("protect_nali", (byte)3);
                        }
                    }
                }
            }
        }
        else
        {
            if (this.protect)
            {
                this.state = 3;
                this.protect = false;
            }

            if (this.state == 3)
            {
                serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();
            }
            else
            {
                serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.PROTECT() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.PROTECT() % 8));//0
            }
        }
    }
}
