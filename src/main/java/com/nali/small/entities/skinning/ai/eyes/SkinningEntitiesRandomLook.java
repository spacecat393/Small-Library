package com.nali.small.entities.skinning.ai.eyes;

import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.ai.SkinningEntitiesAI;

public class SkinningEntitiesRandomLook extends SkinningEntitiesAI
{
    public int tick;
    public boolean look;
    public double x, y, z;
    public int[] bypass_int_array = {this.skinningentities.bothentitiesmemory.workbytes.SIT(), this.skinningentities.bothentitiesmemory.workbytes.PROTECT()};

    public SkinningEntitiesRandomLook(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        if (serverentitiesmemory.isWorkBypass(serverentitiesmemory.workbytes.RANDOM_LOOK(), this.bypass_int_array))
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
                serverentitiesmemory.entitiesaimemory.skinningentitieslook.set(this.x, this.y, this.z, 1.0F);
            }
//            this.look = false;
        }

//        if (!this.look)
//        {
        serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.RANDOM_LOOK()] = 0;
//        }
    }
}
