package com.nali.small.entity.memo.server.si.frame.shoot;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.da.IBothDaSe;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;

public class KeySleShootBF
<
	BD extends IBothDaE & IBothDaS & IBothDaSe,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends KeySleShoot<BD, E, I, S, MS>
{
	public byte state;

	public KeySleShootBF(S s, byte key_data_index)
	{
		super(s, key_data_index);
	}

	@Override
	public void step(short[] key_short_array, short[] fix_key_short_array, byte key_short_index, byte attack_fix_key_index)
	{
		if (key_short_array[key_short_index] == fix_key_short_array[attack_fix_key_index])
		{
			this.state &= 255-1;
		}
		else if (key_short_array[key_short_index] == fix_key_short_array[attack_fix_key_index + 1])
		{
			this.state |= 1;
		}

		if ((this.state & 1) == 1)
		{
			--key_short_array[key_short_index];
		}
		else
		{
			++key_short_array[key_short_index];
		}

//		int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
//		byte[] frame_byte_array = this.s.getFrameByteArray();
//		byte frame = frame_byte_array[this.index];
//		byte index1 = frame_byte_array[this.index + 2];
//		if (this.siekey.frame_int_array[frame] == frame_2d_int_array[index1][0])
//		{
//			this.step = 1;
//		}
//		else if (this.siekey.frame_int_array[frame] == frame_2d_int_array[index1][1])
//		{
//			this.step = -1;
//		}
	}
}
