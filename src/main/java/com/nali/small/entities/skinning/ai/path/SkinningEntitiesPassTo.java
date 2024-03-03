package com.nali.small.entities.skinning.ai.path;

import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.ai.SkinningEntitiesAI;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public class SkinningEntitiesPassTo extends SkinningEntitiesAI
{
    public ArrayList<Block> block_arraylist = new ArrayList<Block>();
    public ArrayList<BlockPos> blockpos_arraylist = new ArrayList<BlockPos>();

    public SkinningEntitiesPassTo(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        if (serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.WALK_TO()] == 1)
        {
            for (int i = 0; i < this.blockpos_arraylist.size(); ++i)
            {
                BlockPos blockpos = this.blockpos_arraylist.get(i);
                if (this.skinningentities.getDistanceSq(blockpos) < 4.0D)
                {
                    World world = this.skinningentities.world;
                    Block block = this.block_arraylist.get(i);
                    if (block instanceof BlockDoor)
                    {
                        BlockDoor blockdoor = (BlockDoor)block;
                        blockdoor.toggleDoor(world, blockpos, !blockdoor.isPassable(world, blockpos));
                    }

                    break;
                }
            }
        }
    }
}
