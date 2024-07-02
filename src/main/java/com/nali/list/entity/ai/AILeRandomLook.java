package com.nali.list.entity.ai;

import com.nali.data.IBothDaNe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundLe;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;

public class AILeRandomLook<SD extends ISoundLe, BD extends IBothDaNe, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends AI<SD, BD, E, I, S, A>
{
    public static byte ID;

    public AILeLook<SD, BD, E, I, S, A> ailelook;

    public int tick;
    public byte state;//on look
    public double x, y, z;
//    public byte[] bypass_int_array = {e.bothentitiesmemory.workbytes.SIT(), e.bothentitiesmemory.workbytes.PROTECT()};

    public AILeRandomLook(S s)
    {
        super(s);
    }

    @Override
    public void init()
    {
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
            if ((this.s.a.state & 2) == 2 && (this.state & 1) == 1)
//            if (serverentitiesmemory.isWorkBypass(serverentitiesmemory.workbytes.RANDOM_LOOK(), this.bypass_int_array))
            {
                if (--this.tick <= 0)
                {
                    E e = this.s.i.getE();
                    this.x = e.posX + e.getRNG().nextInt(5) - e.getRNG().nextInt(5);
                    this.y = e.posY + e.getRNG().nextInt(5) - e.getRNG().nextInt(5);
                    this.z = e.posZ + e.getRNG().nextInt(5) - e.getRNG().nextInt(5);
                    this.tick = e.getRNG().nextInt(100) + 100;
//                    this.look = true;
                    this.s.a.state |= 2;
                }

//                if (this.look)
                if ((this.state & 2) == 2)
                {
                    this.ailelook.set(this.x, this.y, this.z, 1.0F);
                }
    //            this.look = false;
                this.s.a.state &= 255-2;
            }

//    //        if (!this.look)
//    //        {
//            serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.RANDOM_LOOK() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.RANDOM_LOOK() % 8));//0
//    //        }
        }
    }

    @Override
    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setByte("AILeRandomLook_state", this.state);
    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {
        this.state = nbttagcompound.getByte("AILeRandomLook_state");
    }
}
