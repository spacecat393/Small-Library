package com.nali.small.entity.memo.server.si.frame.tloopinset;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.da.IBothDaSe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.frame.KeyS;
import net.minecraft.entity.Entity;

public class KeySTLoopInSet
<
	BD extends IBothDaE & IBothDaS & IBothDaSe,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends KeyS<BD, E, I, S, MS>
{
	public KeySTLoopInSet(S s, byte index)
	{
		super(s, index);
	}

	@Override
	public boolean onUpdate()
	{
		byte[] key_index_byte_array = this.siekey.key_index_byte_array;
		short[] time_short_array = this.siekey.time_short_array;

		short[] key_short_array = this.s.i.getBD().S_KeyShortArray();
		byte[] key_data_byte_array = this.s.getKeyDataByteArray();

		byte time_index = key_data_byte_array[this.key_data_index];
		byte key_index = key_data_byte_array[this.key_data_index + 1];
		byte key_index1 = key_data_byte_array[this.key_data_index + 2];
		float key = this.getKey(key_short_array, key_index, time_index);
		boolean result = key >= key_short_array[key_index + 1] && key <= key_short_array[key_index1 + 1];

		if (result)
		{
			float step = this.getStep(key_short_array, key_index);

			key_index_byte_array[time_index] = key_index;
			key_index_byte_array[time_index + 1] = key_index;
			if (key >= key_short_array[key_index1 + 1])
			{
				time_short_array[time_index] = key_short_array[key_index];
				time_short_array[time_index + 1] = (short)(time_short_array[time_index] + step);
				this.siekey.sync_byte_set.add(time_index);
				return true;
			}

			time_short_array[time_index] += step;
			time_short_array[time_index + 1] = (short)(time_short_array[time_index] + step);
			this.siekey.sync_byte_set.add(time_index);
			return true;
		}

		return false;
	}
}
