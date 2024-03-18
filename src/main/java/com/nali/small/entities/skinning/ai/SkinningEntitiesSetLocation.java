package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import net.minecraft.util.math.BlockPos;

import static com.nali.small.entities.EntitiesMath.isInArea;

public class SkinningEntitiesSetLocation extends SkinningEntitiesAI
{
    public long blockpos_long = -1;
    public float far;
    public BlockPos blockpos;
    public BlockPos temp_blockpos;
    public boolean set_location;

    public SkinningEntitiesSetLocation(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        boolean run = false;
        if (this.blockpos_long != -1 && this.far != 0)
        {
            serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.LOCATION() / 8] |= (byte)Math.pow(2, serverentitiesmemory.workbytes.LOCATION() % 8);//1
        }

        if (serverentitiesmemory.isWork(serverentitiesmemory.workbytes.LOCATION()))
        {
            if (this.temp_blockpos == null || this.blockpos != this.temp_blockpos)
            {
                this.blockpos = BlockPos.fromLong(this.blockpos_long);
//                Small.LOGGER.info("CEATE");
            }
            else
            {
                if (!isInArea(this.skinningentities, this.blockpos, this.far))
                {
                    serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.setGoal(this.blockpos.getX() + 0.5D, this.blockpos.getY() + 0.5D, this.blockpos.getZ() + 0.5D);
                    run = true;
                    this.set_location = true;
//                    Small.LOGGER.info("WALK");
//                    Small.LOGGER.info("FAR " + this.far);
                }
                else if (this.set_location)
                {
                    serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();
                    this.set_location = false;
//                    Small.LOGGER.info("END");
                }
            }
        }
        else
        {
            if (this.set_location)
            {
                serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();
                this.set_location = false;
//                Small.LOGGER.info("END 2");
            }

//            Small.LOGGER.info("CLEAR");
            this.blockpos = null;
        }

        if (!run)
        {
            serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.LOCATION() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.LOCATION() % 8));//0
        }

//        Small.LOGGER.info("BLOCKPOS " + this.blockpos.toString());

        this.temp_blockpos = this.blockpos;
    }
}
