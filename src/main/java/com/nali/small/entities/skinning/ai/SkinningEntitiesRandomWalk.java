package com.nali.small.entities.skinning.ai;

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
        if (this.skinningentities.isWork(this.skinningentities.skinningentitiesbytes.RANDOM_WALK()))
        {
            ++this.end_tick;
            if (this.end_tick == 100)
            {
                this.end_tick = 0;
                this.skinningentities.skinningentitiesfindmove.endGoal();
                this.walk = false;
            }

            if (--this.tick <= 0)
            {
                this.skinningentities.skinningentitiesfindmove.setGoal(this.skinningentities.posX + this.skinningentities.getRNG().nextInt(5) - this.skinningentities.getRNG().nextInt(5), this.skinningentities.posY + this.skinningentities.getRNG().nextInt(5) - this.skinningentities.getRNG().nextInt(5), this.skinningentities.posZ + this.skinningentities.getRNG().nextInt(5) - this.skinningentities.getRNG().nextInt(5));
                this.tick = this.skinningentities.getRNG().nextInt(1000) + 100;
                this.walk = true;
            }
        }
        else if (this.walk)
        {
            this.skinningentities.skinningentitiesfindmove.endGoal();
            this.walk = false;
        }

        if (!this.walk)
        {
            this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.RANDOM_WALK()] = 0;
        }
    }
}
