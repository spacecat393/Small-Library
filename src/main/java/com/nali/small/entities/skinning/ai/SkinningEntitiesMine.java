package com.nali.small.entities.skinning.ai;

import com.mojang.authlib.GameProfile;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.ai.path.SkinningEntitiesFindMove;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;

public class SkinningEntitiesMine extends SkinningEntitiesAI
{
    public byte state;//remote1 x2-4 y8-16 z32-64 +-
    public BlockPos blockpos, start_blockpos, end_blockpos;
    public float hardness;
    public float goal_x, goal_y, goal_z;

    public SkinningEntitiesMine(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    @Override
    public void onUpdate()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        if (serverentitiesmemory.isWork(serverentitiesmemory.workbytes.MINE()))
        {
            serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.MINE() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.MINE() % 8));
        }
//        else
//        {
//            this.clear();
//        }
    }

    public void breakWork()
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)this.skinningentities.bothentitiesmemory;
        SkinningEntitiesFindMove skinningentitiesfindmove = serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove;

        if (this.skinningentities.isEntityInsideOpaqueBlock())
        {
            this.blockpos = this.skinningentities.getPosition().up();
        }

        if (this.blockpos == null)
        {
            serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.MINE() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.MINE() % 8));//0
//            this.reset();
        }
        else
        {
            WorldServer worldserver = (WorldServer)this.skinningentities.world;
            IBlockState iblockstate = worldserver.getBlockState(this.blockpos);
            Block block = iblockstate.getBlock();

            if (block == Blocks.AIR)
            {
                this.clear();
//                    worldserver.sendBlockBreakProgress(this.skinningentities.getEntityId(), this.blockpos, -1);
                serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.MINE() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.MINE() % 8));//0
                return;
            }

            if (this.skinningentities.getDistanceSq(this.blockpos) > 8.0D)
            {
//                    BlockPos blockpos = this.blockpos;
                skinningentitiesfindmove.setGoal(this.blockpos.getX(), this.blockpos.getY(), this.blockpos.getZ());
//                    this.blockpos = blockpos;
            }
            else
            {
                serverentitiesmemory.entitiesaimemory.skinningentitieslook.set(this.blockpos.getX() + 0.5D, this.blockpos.getY(), this.blockpos.getZ() + 0.5D, 5.0F);
                skinningentitiesfindmove.endGoal();

                FakePlayer fakeplayer = new FakePlayer(worldserver, new GameProfile(null, "!"));
                fakeplayer.setHeldItem(EnumHand.MAIN_HAND, this.skinningentities.getHeldItemMainhand());
                float f = iblockstate.getPlayerRelativeBlockHardness(fakeplayer, worldserver, this.blockpos);
                if (f < 0.005F)
                {
                    f = 0.005F;
                }

                this.hardness += f;
                if (this.hardness > 1.0F)
                {
                    this.hardness = 1.0F;
                }

                if (this.skinningentities.ticksExisted % 4 == 0)
                {
                    worldserver.sendBlockBreakProgress(this.skinningentities.getEntityId(), this.blockpos, (int)(this.hardness * 10) - 1);
                    SoundType soundtype = block.getSoundType();
                    worldserver.playSound(null, this.blockpos, soundtype.getHitSound(), SoundCategory.NEUTRAL, (soundtype.getVolume() + 1.0F) / 8.0F, soundtype.getPitch() * 0.5F);
                }

                if (this.hardness == 1.0F)
                {
                    worldserver.playEvent(2001, this.blockpos, Block.getIdFromBlock(block));

                    if (block.removedByPlayer(iblockstate, worldserver, this.blockpos, fakeplayer, false))
                    {
                        block.onPlayerDestroy(worldserver, this.blockpos, iblockstate);
                        block.harvestBlock(worldserver, fakeplayer, this.blockpos, iblockstate, worldserver.getTileEntity(this.blockpos), this.skinningentities.getHeldItemMainhand());
                        this.skinningentities.getHeldItemMainhand().damageItem(1, this.skinningentities);
                    }

//                        worldserver.sendBlockBreakProgress(this.skinningentities.getEntityId(), this.blockpos, -1);
                    this.clear();
                    serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.MINE() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.MINE() % 8));//0
                }
            }
        }
    }

    public void clear()
    {
        this.reset();
        this.blockpos = null;
    }

    public void reset()
    {
        if (this.hardness != 0)
        {
            WorldServer worldserver = (WorldServer)this.skinningentities.world;
            worldserver.sendBlockBreakProgress(this.skinningentities.getEntityId(), this.blockpos, -1);
            this.hardness = 0;
        }
    }
}
