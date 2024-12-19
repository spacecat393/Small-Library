package com.nali.small.entity.memo.server.si.frame.tloopfb;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.da.IBothDaSe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.frame.KeyS;
import net.minecraft.entity.Entity;

public class KeySTLoopFB
<
	BD extends IBothDaE & IBothDaS & IBothDaSe,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends KeyS<BD, E, I, S, MS>
{
	public byte state;

	public KeySTLoopFB(S s, byte key_data_index)
	{
		super(s, key_data_index);
	}

	@Override
	public boolean onUpdate()
	{
		short[] key_short_array = this.siekey.key_short_array;
		short[] fix_key_short_array = this.s.getFixKeyShortArray();
		byte[] key_data_byte_array = this.s.getKeyDataByteArray();

		byte key_short_index = key_data_byte_array[this.key_data_index];
		byte fix_key_index0 = key_data_byte_array[this.key_data_index + 1];

		if (key_short_array[key_short_index] < fix_key_short_array[fix_key_index0] || key_short_array[key_short_index] >= fix_key_short_array[fix_key_index0 + 1])
		{
			key_short_array[key_short_index] = fix_key_short_array[fix_key_index0];
		}

		if (key_short_array[key_short_index] == fix_key_short_array[fix_key_index0])
		{
			this.state &= 255-1;
		}
		else if (key_short_array[key_short_index] == fix_key_short_array[fix_key_index0 + 1])
		{
			this.state |= 1;
		}

		if ((this.state & 1) == 1)
		{
			--key_short_array[key_short_index];
		}
		else/* if ((this.state & 1) == 0)*/
		{
			++key_short_array[key_short_index];
		}

		this.siekey.sync_byte_arraylist.add(key_short_index);
		return true;

//		int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
//		byte[] frame_byte_array = this.s.getFrameByteArray();
//		byte frame = frame_byte_array[this.index];
//		byte index = frame_byte_array[this.index + 1];
//		int[] frame_int_array = this.siekey.frame_int_array;
//		if (frame_int_array[frame] < frame_2d_int_array[index][0] || frame_int_array[frame] > frame_2d_int_array[index][1])
//		{
//			this.step = 0;
//			frame_int_array[frame] = frame_2d_int_array[index][0];
//		}
//		else if (frame_int_array[frame] == frame_2d_int_array[index][0])
//		{
//			this.step = 1;
//		}
//		else if (frame_int_array[frame] == frame_2d_int_array[index][1])
//		{
//			this.step = -1;
//		}
//
//		return true;
	}
}
