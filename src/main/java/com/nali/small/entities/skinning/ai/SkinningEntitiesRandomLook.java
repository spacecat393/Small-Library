package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.skinning.SkinningEntities;

public class SkinningEntitiesRandomLook extends SkinningEntitiesAI
{
    public int tick;
    public boolean look;
    public double x, y, z;
    public int[] bypass_int_array = {this.skinningentities.skinningentitiesbytes.SIT(), this.skinningentities.skinningentitiesbytes.PROTECT()};

    public SkinningEntitiesRandomLook(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        if (this.skinningentities.isWorkBypass(this.skinningentities.skinningentitiesbytes.RANDOM_LOOK(), this.bypass_int_array))
        {
            if (--this.tick <= 0)
            {
                this.x = this.skinningentities.posX + this.skinningentities.getRNG().nextInt(5) - this.skinningentities.getRNG().nextInt(5);
                this.y = this.skinningentities.posY + this.skinningentities.getRNG().nextInt(5) - this.skinningentities.getRNG().nextInt(5);
                this.z = this.skinningentities.posZ + this.skinningentities.getRNG().nextInt(5) - this.skinningentities.getRNG().nextInt(5);
                this.tick = this.skinningentities.getRNG().nextInt(100) + 100;
                this.look = true;
            }

            if (this.look)
            {
                this.skinningentities.skinningentitieslook.set(this.x, this.y, this.z, 1.0F);
            }
//            this.look = false;
        }

//        if (!this.look)
//        {
        this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.RANDOM_LOOK()] = 0;
//        }
    }
}
