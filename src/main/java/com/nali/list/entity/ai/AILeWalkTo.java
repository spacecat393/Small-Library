package com.nali.list.entity.ai;

import com.mojang.authlib.GameProfile;
import com.nali.data.IBothDaE;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundLe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;

import java.util.ArrayList;
import java.util.List;

public class AILeWalkTo<SD extends ISoundLe, BD extends IBothDaE, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends AI<SD, BD, E, I, S, A>
{
    public static byte ID;

    public AIESit<SD, BD, E, I, S, A> aiesit;

    public byte state;

//    public List<Block> block_list = new List<Block>();
    public List<BlockPos> blockpos_list = new ArrayList();
    public Vec3d vec3d;

//    public int tick;

    public AILeWalkTo(S s)
    {
        super(s);
    }

    @Override
    public void init()
    {
        this.aiesit = (AIESit<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AIESit.ID);
    }

    @Override
    public void call()
    {

    }

    @Override
    public void onUpdate()
    {
        if (this.s.isMove() && (this.aiesit.state & 1) == 1)
        {
            E e = this.s.getI().getE();
    //        if ((this.s.current_work_byte_array[this.s.workbytes.WALK_TO() / 8] >> this.s.workbytes.WALK_TO() % 8 & 1) == 1)
    //        if ((this.s.work_byte_array[this.num / 8] >> this.num % 8 & 1) == 1)
            if ((this.state & 1) == 1)
            {
                if (e.ticksExisted % 20 == 0)
                {
                    Vec3d vec3d = e.getPositionVector();
                    for (BlockPos blockpos : this.blockpos_list)
                    {
                        if (e.getDistanceSq(blockpos) < 8.0D && this.vec3d != null && this.vec3d.squareDistanceTo(vec3d) < 0.0001D)
                        {
                            World world = e.world;
                            IBlockState iblockstate = world.getBlockState(blockpos);
                            Block block = iblockstate.getBlock();
    //                    Block block = this.block_list.get(i);
                            if (block instanceof BlockDoor)
                            {
                                BlockDoor blockdoor = (BlockDoor)block;
    //                            blockdoor.toggleDoor(world, blockpos, !blockdoor.isPassable(world, blockpos));
                                FakePlayer fakeplayer = new FakePlayer(this.s.worldserver, new GameProfile(null, "!"));
                                fakeplayer.rotationYaw = e.rotationYaw;
                                blockdoor.onBlockActivated(world, blockpos, iblockstate, fakeplayer, EnumHand.MAIN_HAND, EnumFacing.fromAngle(fakeplayer.rotationYaw), 0, 0, 0);
                            }
                            else if (block instanceof BlockFenceGate)
                            {
                                BlockFenceGate blockfencegate = (BlockFenceGate)block;
                                FakePlayer fakeplayer = new FakePlayer(this.s.worldserver, new GameProfile(null, "!"));
                                fakeplayer.rotationYaw = e.rotationYaw;
                                blockfencegate.onBlockActivated(world, blockpos, iblockstate, fakeplayer, EnumHand.MAIN_HAND, EnumFacing.fromAngle(fakeplayer.rotationYaw), 0, 0, 0);
                            }
                            else if (block instanceof BlockTrapDoor)
                            {
                                BlockTrapDoor blocktrapdoor = (BlockTrapDoor)block;
                                FakePlayer fakeplayer = new FakePlayer(this.s.worldserver, new GameProfile(null, "!"));
                                fakeplayer.rotationYaw = e.rotationYaw;
                                blocktrapdoor.onBlockActivated(world, blockpos, iblockstate, fakeplayer, EnumHand.MAIN_HAND, EnumFacing.fromAngle(fakeplayer.rotationYaw), 0, 0, 0);
                            }

    //                        this.tick = 20;
                        }
                    }

                    this.vec3d = vec3d;
                }
            }
        }
    }

    @Override
    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setByte("AILeWalkTo_state", this.state);
    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {
        this.state = nbttagcompound.getByte("AILeWalkTo_state");
    }
}
