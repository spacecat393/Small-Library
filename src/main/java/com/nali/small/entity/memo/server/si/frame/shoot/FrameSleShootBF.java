package com.nali.small.entity.memo.server.si.frame.shoot;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.sound.ISoundDaLe;

public class FrameSleShootBF<SD extends ISoundDaLe, BD extends IBothDaNe & IBothDaSn, E extends EntityLe, I extends IMixE<SD, BD, E>, S extends ServerLe<SD, BD, E, I, MS> & IServerS, MS extends MixSIE<SD, BD, E, I, S>> extends FrameSleShoot<SD, BD, E, I, S, MS>
{
	public FrameSleShootBF(S s, int index)
	{
		super(s, index);
	}

	@Override
	public void step()
	{
		int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
		byte[] frame_byte_array = this.s.getFrameByteArray();
		byte frame = frame_byte_array[this.index];
		byte index1 = frame_byte_array[this.index + 2];
		if (this.sieframe.frame_int_array[frame] == frame_2d_int_array[index1][0])
		{
			this.step = 1;
		}
		else if (this.sieframe.frame_int_array[frame] == frame_2d_int_array[index1][1])
		{
			this.step = -1;
		}
	}
}
