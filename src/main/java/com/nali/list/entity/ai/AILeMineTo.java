package com.nali.list.entity.ai;

import com.mojang.authlib.GameProfile;
import com.nali.data.IBothDaE;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundLe;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.FakePlayer;

import static com.nali.small.entity.EntityMath.isInArea;

public class AILeMineTo<SD extends ISoundLe, BD extends IBothDaE, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends AI<SD, BD, E, I, S, A>
{
    public static byte ID;

    public AILeSetLocation<SD, BD, E, I, S, A> ailesetlocation;
    public AILeFindMove<SD, BD, E, I, S, A> ailefindmove;
    public AILeLook<SD, BD, E, I, S, A> ailelook;

    public byte state;//on //| remote1 x2-4 y8-16 z32-64 +-
    public BlockPos blockpos/*, start_blockpos, end_blockpos*/;
    public float hardness;
//    public float goal_x, goal_y, goal_z;
    public double goal_x, goal_y, goal_z;

    public AILeMineTo(S s)
    {
        super(s);
    }

    @Override
    public void init()
    {
        this.ailesetlocation = (AILeSetLocation<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeSetLocation.ID);
        this.ailefindmove = (AILeFindMove<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeFindMove.ID);
        this.ailelook = (AILeLook<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeLook.ID);
    }

    @Override
    public void call()
    {

    }

    @Override
    public void onUpdate()
    {
        if (this.s.isMove())
        {
            if ((this.state & 1) == 1)
            {
                E e = this.s.getI().getE();

    //            if (e.isEntityInsideOpaqueBlock())
                if (this.s.worldserver.getBlockState(e.getPosition().up()).getMaterial().isSolid())
                {
                    this.blockpos = e.getPosition().up();
                }

    //            if (this.blockpos == null)
    //            {
    ////            this.s.current_work_byte_array[this.s.bytele.MINE() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.MINE() % 8));//0
    ////            this.reset();
    //            }
    //            else
                if (this.blockpos != null)
                {
                    IBlockState iblockstate = this.s.worldserver.getBlockState(this.blockpos);
                    Block block = iblockstate.getBlock();

                    if (block == Blocks.AIR)
                    {
                        this.clear();
    //                    worldserver.sendBlockBreakProgress(e.getEntityId(), this.blockpos, -1);
    //                this.s.current_work_byte_array[this.s.bytele.MINE() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.MINE() % 8));//0
                        return;
                    }

                    if (e.getDistanceSq(this.blockpos) > 8.0D)
                    {
    //                    BlockPos blockpos = this.blockpos;
                        if (this.ailesetlocation.far == 0 || this.ailesetlocation.blockpos == null || isInArea(this.blockpos, this.ailesetlocation.blockpos, this.ailesetlocation.far))
                        {
                            this.ailefindmove.setGoal(this.blockpos.getX() + 0.5D, this.blockpos.getY() + 0.5D, this.blockpos.getZ() + 0.5D);
                        }
    //                    this.blockpos = blockpos;
                    }
                    else
                    {
                        this.ailelook.set(this.blockpos.getX() + 0.5D, this.blockpos.getY(), this.blockpos.getZ() + 0.5D, 5.0F);
                        this.ailefindmove.endGoal();

                        FakePlayer fakeplayer = new FakePlayer(this.s.worldserver, new GameProfile(null, "!"));
                        fakeplayer.setHeldItem(EnumHand.MAIN_HAND, e.getHeldItemMainhand());
                        float f = iblockstate.getPlayerRelativeBlockHardness(fakeplayer, this.s.worldserver, this.blockpos);
                        if (f < 0.005F)
                        {
                            f = 0.005F;
                        }

                        this.hardness += f;
                        if (this.hardness > 1.0F)
                        {
                            this.hardness = 1.0F;
                        }

                        if (e.ticksExisted % 4 == 0)
                        {
                            this.s.worldserver.sendBlockBreakProgress(e.getEntityId(), this.blockpos, (int)(this.hardness * 10) - 1);
                            SoundType soundtype = block.getSoundType();
                            this.s.worldserver.playSound(null, this.blockpos, soundtype.getHitSound(), SoundCategory.NEUTRAL, (soundtype.getVolume() + 1.0F) / 8.0F, soundtype.getPitch() * 0.5F);
                        }

                        if (this.hardness == 1.0F)
                        {
                            this.s.worldserver.playEvent(2001, this.blockpos, Block.getIdFromBlock(block));

                            if (block.removedByPlayer(iblockstate, this.s.worldserver, this.blockpos, fakeplayer, false))
                            {
                                block.onPlayerDestroy(this.s.worldserver, this.blockpos, iblockstate);
                                block.harvestBlock(this.s.worldserver, fakeplayer, this.blockpos, iblockstate, this.s.worldserver.getTileEntity(this.blockpos), e.getHeldItemMainhand());
                                e.getHeldItemMainhand().damageItem(1, e);
                            }

    //                        worldserver.sendBlockBreakProgress(e.getEntityId(), this.blockpos, -1);
                            this.clear();
    //                    this.s.current_work_byte_array[this.s.bytele.MINE() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.MINE() % 8));//0
                        }
                    }

                    this.s.a.state &= 255-1;
                }
            }
    //        switch (this.s.a.state & 4+1)
    //        {
    //            case 1:
    //                this.breakWork();
    //                break;
    //            case 4:
    //                //mine ai later
    //                if (this.s.isMove())
    //                {
    //
    //                }
    ////                this.s.current_work_byte_array[this.s.bytele.MINE() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.MINE() % 8));
    //                break;
    //            default:
    //        }
    //        if (this.s.isMove())
    //        {
            //        if ((this.s.main_work_byte_array[this.s.bytele.MINE() / 8] >> this.s.bytele.MINE() % 8 & 1) == 1 && this.s.entitiesaimemory.blockpos != null)

    //        if ((this.s.a.state & 1) == 1)
    //        {
    //        }

    //        if (this.s.isWork(this.s.bytele.MINE()))
    //        if ((this.s.a.state & 4) == 4)
    //        {
    //        }
    //        else
    //        {
    //            this.clear();
    //        }
    //        }
        }
    }

    @Override
    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setByte("AILeMineTo_state", this.state);
//        if (this.start_blockpos != null)
//        {
//            new_nbttagcompound.setLong("start_blockpos", this.start_blockpos.toLong());
//        }
//        if (this.end_blockpos != null)
//        {
//            new_nbttagcompound.setLong("end_blockpos", this.end_blockpos.toLong());
//        }
    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {
        this.state = nbttagcompound.getByte("AILeMineTo_state");
//            if (new_nbttagcompound.hasKey("start_blockpos_mine"))
//            {
//                this.start_blockpos = BlockPos.fromLong(new_nbttagcompound.getLong("start_blockpos_mine"));
//            }
//            if (new_nbttagcompound.hasKey("end_blockpos_mine"))
//            {
//                this.end_blockpos = BlockPos.fromLong(new_nbttagcompound.getLong("end_blockpos_mine"));
//            }
    }

//    private void breakWork()
//    {
//        E e = this.s.getI().getE();
//
//        if (e.isEntityInsideOpaqueBlock())
//        {
//            this.blockpos = e.getPosition().up();
//        }
//
//        if (this.blockpos == null)
//        {
//            this.s.a.state &= 255-1;
////            this.s.current_work_byte_array[this.s.bytele.MINE() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.MINE() % 8));//0
////            this.reset();
//        }
//        else
//        {
//            WorldServer worldserver = (WorldServer)e.world;
//            IBlockState iblockstate = worldserver.getBlockState(this.blockpos);
//            Block block = iblockstate.getBlock();
//
//            if (block == Blocks.AIR)
//            {
//                this.clear();
////                    worldserver.sendBlockBreakProgress(e.getEntityId(), this.blockpos, -1);
//                this.s.a.state &= 255-1;
////                this.s.current_work_byte_array[this.s.bytele.MINE() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.MINE() % 8));//0
//                return;
//            }
//
//            if (e.getDistanceSq(this.blockpos) > 8.0D)
//            {
////                    BlockPos blockpos = this.blockpos;
//                this.ailefindmove.setGoal(this.blockpos.getX() + 0.5D, this.blockpos.getY() + 0.5D, this.blockpos.getZ() + 0.5D);
////                    this.blockpos = blockpos;
//            }
//            else
//            {
//                this.ailelook.set(this.blockpos.getX() + 0.5D, this.blockpos.getY(), this.blockpos.getZ() + 0.5D, 5.0F);
//                this.ailefindmove.endGoal();
//
//                FakePlayer fakeplayer = new FakePlayer(worldserver, new GameProfile(null, "!"));
//                fakeplayer.setHeldItem(EnumHand.MAIN_HAND, e.getHeldItemMainhand());
//                float f = iblockstate.getPlayerRelativeBlockHardness(fakeplayer, worldserver, this.blockpos);
//                if (f < 0.005F)
//                {
//                    f = 0.005F;
//                }
//
//                this.hardness += f;
//                if (this.hardness > 1.0F)
//                {
//                    this.hardness = 1.0F;
//                }
//
//                if (e.ticksExisted % 4 == 0)
//                {
//                    worldserver.sendBlockBreakProgress(e.getEntityId(), this.blockpos, (int)(this.hardness * 10) - 1);
//                    SoundType soundtype = block.getSoundType();
//                    worldserver.playSound(null, this.blockpos, soundtype.getHitSound(), SoundCategory.NEUTRAL, (soundtype.getVolume() + 1.0F) / 8.0F, soundtype.getPitch() * 0.5F);
//                }
//
//                if (this.hardness == 1.0F)
//                {
//                    worldserver.playEvent(2001, this.blockpos, Block.getIdFromBlock(block));
//
//                    if (block.removedByPlayer(iblockstate, worldserver, this.blockpos, fakeplayer, false))
//                    {
//                        block.onPlayerDestroy(worldserver, this.blockpos, iblockstate);
//                        block.harvestBlock(worldserver, fakeplayer, this.blockpos, iblockstate, worldserver.getTileEntity(this.blockpos), e.getHeldItemMainhand());
//                        e.getHeldItemMainhand().damageItem(1, e);
//                    }
//
////                        worldserver.sendBlockBreakProgress(e.getEntityId(), this.blockpos, -1);
//                    this.clear();
//                    this.s.a.state &= 255-1;
////                    this.s.current_work_byte_array[this.s.bytele.MINE() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.MINE() % 8));//0
//                }
//            }
//        }
//    }

    public void clear()
    {
        this.reset();
        this.blockpos = null;
    }

    public void reset()
    {
        if (this.hardness != 0)
        {
            this.s.worldserver.sendBlockBreakProgress(this.s.i.getE().getEntityId(), this.blockpos, -1);
            this.hardness = 0;
        }
    }
}
