package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SkinningEntitiesBreak extends SkinningEntitiesAI
{
    public BlockPos blockpos;
    public int tick;
    public int current_tick;

    public SkinningEntitiesBreak(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        if (serverentitiesmemory.isWork(serverentitiesmemory.workbytes.MINE()))
        {
            if (this.blockpos != null)
            {
                World world = this.skinningentities.world;
                Block block = world.getBlockState(this.blockpos).getBlock();

                if (block == Blocks.AIR)
                {
                    this.tick = 0;
                    this.current_tick = 0;
                    this.blockpos = null;
                }
                else
                {
                    int i = (int)(this.tick++ / 240.0F * 10.0F);

                    if (i != this.current_tick)
                    {
                        world.sendBlockBreakProgress(this.skinningentities.getEntityId(), this.blockpos, i);
                        world.playEvent(1019, this.blockpos, 0);
                        this.current_tick = i;
                    }

                    if (this.tick == 240)
                    {
                        world.setBlockToAir(this.blockpos);
                        world.playEvent(1021, this.blockpos, 0);
                        world.playEvent(2001, this.blockpos, Block.getIdFromBlock(block));
                        this.tick = 0;
                        this.current_tick = 0;
                        this.blockpos = null;
                    }
                }
            }
        }
    }
}
