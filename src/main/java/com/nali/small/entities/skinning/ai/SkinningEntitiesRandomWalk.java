package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;

public class SkinningEntitiesRandomWalk extends SkinningEntitiesAI
{
    public int tick;
    public int end_tick;
    public boolean walk;

    public SkinningEntitiesRandomWalk(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        if (this.skinningentities.isWork(serverentitiesmemory.workbytes.RANDOM_WALK()))
        {
            ++this.end_tick;
            if (this.end_tick == 100)
            {
                this.end_tick = 0;
                serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();
                this.walk = false;
            }

            if (--this.tick <= 0)
            {
                serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.setGoal(this.skinningentities.posX + this.skinningentities.getRNG().nextInt(5) - this.skinningentities.getRNG().nextInt(5), this.skinningentities.posY + this.skinningentities.getRNG().nextInt(5) - this.skinningentities.getRNG().nextInt(5), this.skinningentities.posZ + this.skinningentities.getRNG().nextInt(5) - this.skinningentities.getRNG().nextInt(5));
                this.tick = this.skinningentities.getRNG().nextInt(100) + 100;
                this.walk = true;
            }
        }
        else if (this.walk)
        {
            serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();
            this.walk = false;
        }

        if (!this.walk)
        {
            serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.RANDOM_WALK()] = 0;
        }
    }
}
