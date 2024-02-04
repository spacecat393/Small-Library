package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.skinning.SkinningEntities;
import net.minecraft.util.math.BlockPos;

import static com.nali.small.entities.EntitiesMathHelper.isInArea;

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
        boolean run = false;
        if (this.blockpos_long != -1 && this.far != 0)
        {
            this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.LOCATION()] = 1;
        }

        if (this.skinningentities.isWork(this.skinningentities.skinningentitiesbytes.LOCATION()))
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
                    this.skinningentities.skinningentitiesfindmove.setGoal(this.blockpos.getX() + 0.5D, this.blockpos.getY() + 0.5D, this.blockpos.getZ() + 0.5D);
                    run = true;
                    this.set_location = true;
//                    Small.LOGGER.info("WALK");
//                    Small.LOGGER.info("FAR " + this.far);
                }
                else if (this.set_location)
                {
                    this.skinningentities.skinningentitiesfindmove.endGoal();
                    this.set_location = false;
//                    Small.LOGGER.info("END");
                }
            }
        }
        else
        {
            if (this.set_location)
            {
                this.skinningentities.skinningentitiesfindmove.endGoal();
                this.set_location = false;
//                Small.LOGGER.info("END 2");
            }

//            Small.LOGGER.info("CLEAR");
            this.blockpos = null;
        }

        if (!run)
        {
            this.skinningentities.server_work_byte_array[this.skinningentities.skinningentitiesbytes.LOCATION()] = 0;
        }

//        Small.LOGGER.info("BLOCKPOS " + this.blockpos.toString());

        this.temp_blockpos = this.blockpos;
    }
}
