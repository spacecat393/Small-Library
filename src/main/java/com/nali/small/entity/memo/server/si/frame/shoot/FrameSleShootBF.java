package com.nali.small.entity.memo.server.si.frame.shoot;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;

public class FrameSleShootBF
<
	BD extends IBothDaE & IBothDaS,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends FrameSleShoot<BD, E, I, S, MS>
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
