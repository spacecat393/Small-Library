package com.nali.small.entity.memo.server.si.frame;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.list.entity.si.SILeHeal;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.sound.ISoundDaLe;

public class FrameSleHeal<SD extends ISoundDaLe, BD extends IBothDaNe & IBothDaSn, E extends EntityLe, I extends IMixE<SD, BD, E>, S extends ServerLe<SD, BD, E, I, MS> & IServerS, MS extends MixSIE<SD, BD, E, I, S>> extends FrameS<SD, BD, E, I, S, MS>
{
	public SILeHeal<SD, BD, E, I, S, MS> sileheal;

//	public byte index;

	public FrameSleHeal(S s, int index)
	{
		super(s, index);
//		index = index;
	}

	@Override
	public void init()
	{
		super.init();
		this.sileheal = (SILeHeal<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeHeal.ID);
	}

	@Override
	public boolean onUpdate()
	{
		this.step = 1;
		if (this.sileheal.state == 0 || this.sileheal.state == 1)
		{
			int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
			byte[] frame_byte_array = this.s.getFrameByteArray();
			byte frame = frame_byte_array[this.index];
			for (int heal_frame : this.sileheal.heal_frame_int_array)
			{
				if (this.sieframe.frame_int_array[frame] == heal_frame)
				{
					this.sileheal.state = 1;
					break;
				}
			}

			byte index = frame_byte_array[this.index + 1];
			if (this.sieframe.frame_int_array[frame] < frame_2d_int_array[index][0] || this.sieframe.frame_int_array[frame] > frame_2d_int_array[index][1])
			{
				this.sieframe.frame_int_array[frame] = frame_2d_int_array[index][0];
				this.step = 0;
			}
			else if (this.sieframe.frame_int_array[frame] == frame_2d_int_array[index][1])
			{
				this.sieframe.frame_int_array[frame] = frame_2d_int_array[index][0];
				this.step = 0;
			}

			return true;
		}

		return false;
	}
}
