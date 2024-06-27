package com.nali.small.entity.memo.server.ai.frame;

import com.nali.data.IBothDaE;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundLe;
import net.minecraft.entity.EntityLivingBase;

public class FrameLeShootBF<SD extends ISoundLe, BD extends IBothDaE, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends FrameLeShoot<SD, BD, E, I, S, A>
{
    public FrameLeShootBF(S s, byte frame, byte index, byte index1, byte index2, byte index3)
    {
        super(s, frame, index, index1, index2, index3);
    }

    @Override
    public void step()
    {
        int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
        if (this.s.frame_int_array[this.frame] == frame_2d_int_array[this.index1][0])
        {
            this.step = 1;
        }
        else if (this.s.frame_int_array[this.frame] == frame_2d_int_array[this.index1][1])
        {
            this.step = -1;
        }
    }
}
