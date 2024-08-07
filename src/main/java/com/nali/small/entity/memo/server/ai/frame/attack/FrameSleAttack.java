package com.nali.small.entity.memo.server.ai.frame.attack;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.list.entity.ai.AILeAttack;
import com.nali.list.entity.ai.AILeFindMove;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerSle;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.small.entity.memo.server.ai.frame.FrameS;
import com.nali.sound.ISoundDaLe;

public class FrameSleAttack<SD extends ISoundDaLe, BD extends IBothDaNe & IBothDaSn, E extends EntityLe, I extends IMixLe<SD, BD, E>, S extends ServerSle<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends FrameS<SD, BD, E, I, S, A>
{
    public AILeAttack<SD, BD, E, I, S, A> aileattack;
    public AILeFindMove<SD, BD, E, I, S, A> ailefindmove;

    public FrameSleAttack(S s, int index)
    {
        super(s, index);
    }

    @Override
    public void init()
    {
        super.init();
        this.aileattack = (AILeAttack<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeAttack.ID);
        this.ailefindmove = (AILeFindMove<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeFindMove.ID);
    }

    @Override
    public boolean onUpdate()
    {
        if (this.step())
        {
            int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
//            byte[] frame_byte_array = this.s.getFrameByteArray();
            this.step = 1;

//            byte frame = frame_byte_array[this.index];
            byte frame = this.s.getFrameByteArray()[this.index];
            byte id = this.getIndex();

            if (this.s.frame_int_array[frame] < frame_2d_int_array[id][0] || this.s.frame_int_array[frame] >= frame_2d_int_array[id][1])
            {
                this.s.frame_int_array[frame] = frame_2d_int_array[id][0];
                this.step = 0;
                return true;
            }

            for (int attack_frame : this.aileattack.attack_frame_int_array)
            {
                if (this.s.frame_int_array[frame] == attack_frame)
                {
                    this.aileattack.flag |= 4;
                    break;
                }
            }

            return true;
        }

        return false;
    }

    public boolean step()
    {
        return (this.aileattack.flag & 2) == 2;
    }

    public byte getIndex()
    {
        return this.s.getFrameByteArray()[this.index + 1];
    }
}
