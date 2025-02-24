package com.nali.small.entity.memo.server.si.frame.floop;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.da.IBothDaSe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.frame.KeyS;
import net.minecraft.entity.Entity;

public class KeySFLoop
<
	BD extends IBothDaE & IBothDaS & IBothDaSe,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends KeyS<BD, E, I, S, MS>
{
	public KeySFLoop(S s, byte key_data_index)
	{
		super(s, key_data_index);
	}

	@Override
	public boolean onUpdate()
	{
		//!frame
		return false;
//		byte[] action_byte_array = this.siekey.action_byte_array;
//		float[] line_float_array = this.siekey.line_short_array;
//		short[] fix_key_short_array = this.s.i.getBD().S_FixKeyShortArray();
//		byte[] key_data_byte_array = this.s.getKeyDataByteArray();
//
//		byte key_short_index = key_data_byte_array[this.key_data_index];
//		byte fix_key_index = this.getFixKeyIndex(key_data_byte_array);
//
//		if (action_byte_array[key_short_index] != fix_key_index)
//		{
//			line_float_array[key_short_index] = 0;
//			action_byte_array[key_short_index] = fix_key_index;
//			this.siekey.sync_byte_arraylist.add(key_short_index);
//			return true;
//		}
//
//		float new_line = line_float_array[key_short_index] + this.fps;
//		short end = (short)(fix_key_short_array[fix_key_index + 1] - fix_key_short_array[fix_key_index]);
//		if (line_float_array[key_short_index] != end)
//		{
//			if ((this.fps > 0 && new_line < end) || (this.fps < 0 && new_line > end))
//			{
//				line_float_array[key_short_index] += new_line;
//				this.siekey.sync_byte_arraylist.add(key_short_index);
//			}
//			else
//			{
//				line_float_array[key_short_index] = end;
//				this.siekey.sync_byte_arraylist.add(key_short_index);
//			}
//		}
//
//		return true;
	}

	public byte getFixKeyIndex(byte[] key_data_byte_array)
	{
		return key_data_byte_array[this.key_data_index + 1];
	}
}
