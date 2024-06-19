package com.nali.list.entity.ai;

import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIE;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;

public class AILeRandomWalk<E extends EntityLivingBase, I extends IMixLe<E>, S extends ServerLe<E, I, A>, A extends MixAIE<E, I, S>> extends AI<E, I, S, A>
{
    public static byte ID;

    public AILeFindMove<E, I, S, A> ailefindmove;

    public int tick;
    public byte state;//on walk

    public AILeRandomWalk(S s)
    {
        super(s);
    }

    @Override
    public void init()
    {
        this.ailefindmove = (AILeFindMove<E, I, S, A>)this.s.a.aie_map.get(AILeFindMove.ID);
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
                    this.ailefindmove.setGoal(e.posX + e.getRNG().nextInt(5) - e.getRNG().nextInt(5), e.posY + e.getRNG().nextInt(5) - e.getRNG().nextInt(5), e.posZ + e.getRNG().nextInt(5) - e.getRNG().nextInt(5));
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
