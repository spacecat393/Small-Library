package com.nali.small.entity.memo.server.si.frame.tloopfb;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.frame.FrameS;
import net.minecraft.entity.Entity;

public class FrameSTLoopFB
<
	BD extends IBothDaNe,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends FrameS<BD, E, I, S, MS>
{
	public FrameSTLoopFB(S s, int index)
	{
		super(s, index);
	}

	@Override
	public boolean onUpdate()
	{
		int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
		byte[] frame_byte_array = this.s.getFrameByteArray();
		byte frame = frame_byte_array[this.index];
		byte index = frame_byte_array[this.index + 1];
		int[] frame_int_array = this.sieframe.frame_int_array;
		if (frame_int_array[frame] < frame_2d_int_array[index][0] || frame_int_array[frame] > frame_2d_int_array[index][1])
		{
			this.step = 0;
			frame_int_array[frame] = frame_2d_int_array[index][0];
		}
		else if (frame_int_array[frame] == frame_2d_int_array[index][0])
		{
			this.step = 1;
		}
		else if (frame_int_array[frame] == frame_2d_int_array[index][1])
		{
			this.step = -1;
		}

		return true;
	}
}
