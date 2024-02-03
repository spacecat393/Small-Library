package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.skinning.SkinningEntities;
import net.minecraft.util.math.BlockPos;

public class SkinningEntitiesSetLocation extends SkinningEntitiesAI
{
    public long blockpos_long = -1;
    public float far;

    public SkinningEntitiesSetLocation(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        boolean run = false;
        if (this.blockpos_long != -1 && this.far != 0)
        {
            this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.LOCATION()] = 1;
        }

        if (this.skinningentities.isWork(this.skinningentities.skinningentitiesbytes.LOCATION()))
        {
            BlockPos blockpos = BlockPos.fromLong(this.blockpos_long);
            if (this.skinningentities.getDistanceSq(blockpos) > this.far)
            {
                this.skinningentities.skinningentitiesfindmove.setGoal(blockpos.getX(), blockpos.getY(), blockpos.getZ());
                run = true;
            }
        }

        if (!run)
        {
            this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.LOCATION()] = 0;
        }
    }
}
