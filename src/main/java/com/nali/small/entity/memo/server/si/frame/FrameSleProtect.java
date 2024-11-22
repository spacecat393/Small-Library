package com.nali.small.entity.memo.server.si.frame;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.list.entity.si.SILeProtect;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;

public class FrameSleProtect
<
	BD extends IBothDaE & IBothDaS,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends FrameS<BD, E, I, S, MS>
{
	public SILeProtect<BD, E, I, S, MS> sileprotect;
	public FrameSleProtect(S s, int index)
	{
		super(s, index);

		this.sileprotect = (SILeProtect<BD, E, I, S, MS>)this.s.ms.si_map.get(SILeProtect.ID);
	}
//
//	@Override
//	public void init()
//	{
//		super.init();
//		this.sileprotect = (SILeProtect<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeProtect.ID);
//	}

	@Override
	public boolean onUpdate()
	{
		if ((this.sileprotect.state & 1) == 1)
		{
			int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
			byte[] frame_byte_array = this.s.getFrameByteArray();
			this.step = 1;
			byte frame = frame_byte_array[this.index];
			byte index = frame_byte_array[this.index + 1];
			byte index1 = frame_byte_array[this.index + 2];
			switch (this.sileprotect.state & (4+8))
			{
				case 0:
				{
					if (this.sieframe.frame_int_array[frame] < frame_2d_int_array[index][0] || this.sieframe.frame_int_array[frame] > frame_2d_int_array[index][1])
					{
						this.sieframe.frame_int_array[frame] = frame_2d_int_array[index][0];
						this.step = 0;
					}
					else if (this.sieframe.frame_int_array[frame] == frame_2d_int_array[index][1])
					{
						this.sieframe.frame_int_array[frame] = frame_2d_int_array[index1][0];
						this.step = 0;
		//					this.sileprotect.main_state = 1;
						this.sileprotect.state |= 4;
					}

					break;
				}
				case 4:
				{
					if (this.sieframe.frame_int_array[frame] < frame_2d_int_array[index1][0] || this.sieframe.frame_int_array[frame] > frame_2d_int_array[index1][1])
					{
						this.sieframe.frame_int_array[frame] = frame_2d_int_array[index1][0];
						this.step = 0;
					}
					else if (this.sieframe.frame_int_array[frame] == frame_2d_int_array[index1][1])
					{
						this.sieframe.frame_int_array[frame] = frame_2d_int_array[index1][0];
						this.step = 0;
					}

					break;
				}
				case 8:
				{
					byte index2 = frame_byte_array[this.index + 3];
					if (this.sieframe.frame_int_array[frame] < frame_2d_int_array[index2][0] || this.sieframe.frame_int_array[frame] > frame_2d_int_array[index2][1])
					{
						this.sieframe.frame_int_array[frame] = frame_2d_int_array[index2][0];
						this.step = 0;
					}
					else if (this.sieframe.frame_int_array[frame] == frame_2d_int_array[index2][1])
					{
						this.sieframe.frame_int_array[frame] = frame_2d_int_array[index2][0];
						this.step = 0;
		//					this.sileprotect.main_state = 1;
		//					this.sileprotect.state |= 4;
		//					this.sileprotect.state &= 255-(8);
						this.sileprotect.state ^= 4+8;
					}

					break;
				}
				case 4+8:
				{
					byte index3 = frame_byte_array[this.index + 4];
					if (this.sieframe.frame_int_array[frame] < frame_2d_int_array[index3][0] || this.sieframe.frame_int_array[frame] > frame_2d_int_array[index3][1])
					{
						this.sieframe.frame_int_array[frame] = frame_2d_int_array[index3][0];
						this.step = 0;
					}
					else if (this.sieframe.frame_int_array[frame] == frame_2d_int_array[index3][1])
					{
		//					this.sieframe.frame_int_array[frame] = frame_2d_int_array[index3][0];
						this.step = 0;
		//					this.sileprotect.main_state = -2;
						this.sileprotect.state &= 255-(4+8);
						return true;
					}
					break;
				}
				default:
				{
					break;
				}
			}

			return true;
		}

		return false;
	}
}
