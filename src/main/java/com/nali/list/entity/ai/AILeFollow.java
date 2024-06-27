package com.nali.list.entity.ai;

import com.nali.data.IBothDaE;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.sound.ISoundLe;
import com.nali.sound.ISoundN;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

import static com.nali.small.entity.EntityMath.getDistanceAABBToAABB;
import static com.nali.small.entity.EntityMath.isInArea;
import static com.nali.small.entity.memo.server.ai.path.PathMath.PATH_BYTE_ARRAY;

public class AILeFollow<SD extends ISoundLe, BD extends IBothDaE, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends AI<SD, BD, E, I, S, A>
{
    public static byte ID;

    public AILeSetLocation<SD, BD, E, I, S, A> ailesetlocation;
    public AILeFindMove<SD, BD, E, I, S, A> ailefindmove;

    public float max_distance = 196.0F;
    public float min_distance = 96.0F;
    public byte flag = 4;//move_to | tp_to walk_to

    public AILeFollow(S s)
    {
        super(s);
    }

    @Override
    public void init()
    {
        this.ailesetlocation = (AILeSetLocation<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeSetLocation.ID);
        this.ailefindmove = (AILeFindMove<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeFindMove.ID);
    }

    @Override
    public void onUpdate()
    {
        Entity owner_entity = this.s.getOwner();

        if (owner_entity instanceof EntityPlayerMP)
        {
            if (((EntityPlayerMP)owner_entity).isSpectator())
            {
//                this.s.current_work_byte_array[this.s.bytele.FOLLOW() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.FOLLOW() % 8));
                return;
            }
        }

        E e = this.s.i.getE();
        boolean move_to = (this.flag & 1) == 1;
        if (owner_entity != null &&
            (this.s.a.state & 1) == 1 &&
//            this.s.isWork(this.s.bytele.FOLLOW()) &&
            (this.ailesetlocation.far == 0 || this.ailesetlocation.blockpos == null || isInArea(owner_entity, this.ailesetlocation.blockpos, this.ailesetlocation.far)) &&
//            (e.getDistanceSq(owner_entity) > this.min_distance || move_to))
            (getDistanceAABBToAABB(e, owner_entity) > this.min_distance || move_to))
        {
//            if ((owner_entity.world).provider.getDimension() != ((e.world).provider.getDimension()))
//            {
//                if (move_to)
//                {
//                    this.ailefindmove.endGoal();
//                    this.flag ^= 1;
//                }
//
//                this.s.current_work_byte_array[this.s.bytele.FOLLOW() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.FOLLOW() % 8));
//                return;
//            }

//            if ((this.s.main_work_byte_array[this.s.bytele.MINE() / 8] >> this.s.bytele.MINE() % 8 & 1) == 1 && this.s.entitiesaimemory.skinningentitiesmine.blockpos != null)
//            {
//                this.s.entitiesaimemory.skinningentitiesmine.breakWork();
//            }

            this.flag |= 1;
            if (e.isRiding())
            {
                e.dismountRidingEntity();
            }

//            double step = e.getDistanceSq(owner_entity);

//            if ((this.flag & 2) == 2 && step >= this.max_distance)
//            if ((this.flag & 2) == 2 && e.getDistanceSq(owner_entity) >= this.max_distance)
            if ((this.flag & 2) == 2 && getDistanceAABBToAABB(e, owner_entity) >= this.max_distance)
            {
                this.tryTeleport(owner_entity);
            }
            else if ((this.flag & 4) == 4)
            {
//                if (step <= getClose(e, owner_entity, 1.0D))
                if (getDistanceAABBToAABB(e, owner_entity) <= 1.0D)
                {
                    this.ailefindmove.endGoal();
                    this.flag &= 255-1;
                }
                else
                {
//                    this.ailefindmove.setBreakGoal(owner_entity.posX, owner_entity.posY, owner_entity.posZ);
                    this.ailefindmove.setGoal(owner_entity.posX, owner_entity.posY, owner_entity.posZ);
                }
            }
        }
        else if (move_to)
        {
            this.ailefindmove.endGoal();
            this.flag ^= 1;
        }

//        if ((this.flag & 1) == 0)
//        {
//            this.s.current_work_byte_array[this.s.bytele.FOLLOW() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.FOLLOW() % 8));
//        }
    }

    @Override
    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setByte("AILeFollow_flag", this.flag);
        nbttagcompound.setFloat("AILeFollow_min_distance", this.min_distance);
        nbttagcompound.setFloat("AILeFollow_max_distance", this.max_distance);
    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {
        this.flag = nbttagcompound.getByte("AILeFollow_flag");
        this.min_distance = nbttagcompound.getFloat("AILeFollow_min_distance");
        this.max_distance = nbttagcompound.getFloat("AILeFollow_max_distance");
    }

    public void tryTeleport(Entity owner_entity)
    {
        BlockPos blockpos = owner_entity.getPosition();

        for (byte xi : PATH_BYTE_ARRAY)
        {
            for (byte yi : PATH_BYTE_ARRAY)
            {
                for (byte zi : PATH_BYTE_ARRAY)
                {
                    if (!(xi == 0 && yi == 0 && zi == 0))
                    {
                        if (!this.tryTeleportTo(owner_entity, blockpos.getX() + xi, blockpos.getY() + yi, blockpos.getZ() + zi))
                        {
                            continue;
                        }

                        return;
                    }
                }
            }
        }
    }

    public boolean tryTeleportTo(Entity owner_entity, int x, int y, int z)
    {
        BlockPos to_blockpos = new BlockPos(x, y, z);
        BlockPos down_blockpos = to_blockpos.down();

        if (AILeFindMove.fallBlock(this.s.getMaterial(down_blockpos)) || AILeFindMove.isBlock(this.s.getMaterial(to_blockpos)))
        {
            return false;
        }

        this.s.i.getE().setPositionAndRotation(x + 0.5D, y, z + 0.5D, owner_entity.rotationYaw, owner_entity.rotationPitch);
//        this.s.current_work_byte_array[this.s.bytele.FOLLOW() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.FOLLOW() % 8));
        this.ailefindmove.endGoal();

        return true;
    }
}
