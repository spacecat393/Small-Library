package com.nali.list.entity.ai;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundDaLe;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;

import static com.nali.small.entity.EntityMath.isInArea;

public class AILeRandomWalk<SD extends ISoundDaLe, BD extends IBothDaNe, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends AI<SD, BD, E, I, S, A>
{
    public static byte ID;

    public AILeSetLocation<SD, BD, E, I, S, A> ailesetlocation;
    public AILeFindMove<SD, BD, E, I, S, A> ailefindmove;

    public int tick;
    public byte state;//on walk

    public AILeRandomWalk(S s)
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
    public void call()
    {

    }

    @Override
    public void onUpdate()
    {
        if (this.s.isMove())
        {
    //        if (serverentitiesmemory.isWork(serverentitiesmemory.workbytes.RANDOM_WALK()))
            if ((this.s.a.state & 1) == 1 && (this.state & 1) == 1)
            {
                E e = this.s.i.getE();
                if (e.ticksExisted % 100 == 0)
                {
                    this.ailefindmove.endGoal();
                    this.state &= 255-2;
//                    this.walk = false;
                }

                if (--this.tick <= 0)
                {
                    double x = e.posX + e.getRNG().nextInt(5) - e.getRNG().nextInt(5),
                    y = e.posY + e.getRNG().nextInt(5) - e.getRNG().nextInt(5),
                    z = e.posZ + e.getRNG().nextInt(5) - e.getRNG().nextInt(5);

                    if (this.ailesetlocation.far == 0 || this.ailesetlocation.blockpos == null || isInArea(x, y, z, this.ailesetlocation.blockpos, this.ailesetlocation.far))
                    {
                        this.ailefindmove.setGoal(x, y, z);
                    }
                    this.tick = e.getRNG().nextInt(100) + 100;
                    this.state |= 2;
//                    this.walk = true;
                }
            }
            else if ((this.state & 2) == 2)
            {
                this.ailefindmove.endGoal();
                this.state &= 255-2;
//                this.walk = false;
            }

    //        if (!this.walk)
    //        {
    //            serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.RANDOM_WALK() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.RANDOM_WALK() % 8));//0
    //        }
        }
    }

    @Override
    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setByte("AILeRandomWalk_state", this.state);
    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {
        this.state = nbttagcompound.getByte("AILeRandomWalk_state");
    }
}
