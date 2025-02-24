package com.nali.small.entity.memo.server.si.frame.floopfree;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.da.IBothDaSe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.frame.floop.KeySFLoop;
import net.minecraft.entity.Entity;

public abstract class KeySFLoopFree
<
	BD extends IBothDaE & IBothDaS & IBothDaSe,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends KeySFLoop<BD, E, I, S, MS>
{
//	public byte bit;
	public KeySFLoopFree(S s, byte key_data_index)
	{
		super(s, key_data_index);
//		this.bit = bit;
	}

	@Override
	public boolean onUpdate()
	{
		if (this.step())
		{
			float[] line_float_array = this.siekey.line_short_array;
			short[] fix_key_short_array = this.s.i.getBD().S_FixKeyShortArray();
			byte[] key_data_byte_array = this.s.getKeyDataByteArray();

			byte key_short_index = key_data_byte_array[this.key_data_index];
			byte fix_key_index = this.getFixKeyIndex(key_data_byte_array);

			short end = (short)(fix_key_short_array[fix_key_index + 1] - fix_key_short_array[fix_key_index]);
			if (line_float_array[key_short_index] == end)
			{
				this.free();
			}
			else
			{
				super.onUpdate();
			}
//			short[] key_short_array = this.siekey.key_short_array;
//			short[] fix_key_short_array = this.s.getFixKeyShortArray();
//			byte[] key_data_byte_array = this.s.getKeyDataByteArray();
//
//			byte key_short_index = key_data_byte_array[this.key_data_index];
//			byte fix_key_index = this.getFixKeyIndex(key_data_byte_array);
//
//			if (key_short_array[key_short_index] == fix_key_short_array[fix_key_index + 1])
//			{
//				this.free();
//	//			this.s.statentitiesmemory.stat &= 255 - this.id_pack;
//
////				key_short_array[key_short_index] = fix_key_short_array[fix_key_index + 1];
//			}
//			else if (key_short_array[key_short_index] < fix_key_short_array[fix_key_index] || key_short_array[key_short_index] > fix_key_short_array[fix_key_index + 1])
//			{
//				key_short_array[key_short_index] = fix_key_short_array[fix_key_index];
//
//				this.siekey.sync_byte_arraylist.add(key_short_index);
//				return true;
//			}
//			else if (key_short_array[key_short_index] < fix_key_short_array[fix_key_index + 1])
//			{
//				++key_short_array[key_short_index];
//
//				this.siekey.sync_byte_arraylist.add(key_short_index);
//				return true;
//			}
		}

		return false;
	}

//	public byte getFixKeyIndex(byte[] key_data_byte_array)
//	{
//		return key_data_byte_array[this.key_data_index + 1];
//	}

	public abstract boolean step();
	public abstract void free();
}
