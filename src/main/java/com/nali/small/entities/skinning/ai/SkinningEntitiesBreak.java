package com.nali.small.entities.skinning.ai;

import com.mojang.authlib.GameProfile;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;

public class SkinningEntitiesBreak extends SkinningEntitiesAI
{
    public BlockPos blockpos;
    public float hardness;

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
            if (this.blockpos == null)
            {
                serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.MINE() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.MINE() % 8));//0
            }
            else
            {
                if (this.skinningentities.getDistanceSq(this.blockpos) > 4.0D)
                {
                    serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.setGoal(this.blockpos.getX(), this.blockpos.getY(), this.blockpos.getZ());
                }
                else
                {
                    serverentitiesmemory.entitiesaimemory.skinningentitiesfindmove.endGoal();

                    WorldServer worldserver = (WorldServer)this.skinningentities.world;
                    IBlockState iblockstate = worldserver.getBlockState(this.blockpos);
                    Block block = iblockstate.getBlock();

                    if (block == Blocks.AIR)
                    {
                        this.hardness = 0;
                        this.blockpos = null;
//                    worldserver.sendBlockBreakProgress(this.skinningentities.getEntityId(), this.blockpos, -1);
                    }
                    else
                    {
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
                            this.hardness = 0;
                            this.blockpos = null;
                        }
                    }
                }
            }
        }
        else
        {
            this.blockpos = null;
        }
    }
}
