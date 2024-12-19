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
	public KeySTLoopInSet(S s, byte key_data_index)
	{
		super(s, key_data_index);
	}

	//+1 +1 0 -> 1 / 1 -> ... / ... -> 1
	@Override
	public boolean onUpdate()
	{
		short[] key_short_array = this.siekey.key_short_array;
		short[] fix_key_short_array = this.s.getFixKeyShortArray();
		byte[] key_data_byte_array = this.s.getKeyDataByteArray();

		byte key_short_index = key_data_byte_array[this.key_data_index];
		byte fix_key_index0 = key_data_byte_array[this.key_data_index + 1];
		byte fix_key_index1 = key_data_byte_array[this.key_data_index + 2];

		if (key_short_array[key_short_index] < fix_key_short_array[fix_key_index0] || key_short_array[key_short_index] >= fix_key_short_array[fix_key_index1 + 1])
		{
			key_short_array[key_short_index] = fix_key_short_array[fix_key_index0];
		}
		else if (key_short_array[key_short_index] == fix_key_short_array[fix_key_index1 + 1])
		{
			key_short_array[key_short_index] = fix_key_short_array[fix_key_index1];
		}
		else
		{
			++key_short_array[key_short_index];
		}

		this.siekey.sync_byte_arraylist.add(key_short_index);
		return true;
//		return false;

//		byte[] key_index_byte_array = this.siekey.key_index_byte_array;
//		short[] time_short_array = this.siekey.time_short_array;
//
//		short[] key_short_array = this.s.i.getBD().S_KeyShortArray();
//		byte[] key_data_byte_array = this.s.getKeyDataByteArray();
//
//		byte time_index = key_data_byte_array[this.key_data_index];
//		byte fix_key_index0 = key_data_byte_array[this.key_data_index + 1];
//		byte fix_key_index1 = key_data_byte_array[this.key_data_index + 2];
////		float key = this.getKey(key_short_array, fix_key_index0, time_index);
//		float step0 = this.getStep(key_short_array, fix_key_index0);
//		float step1 = this.getStep(key_short_array, fix_key_index1);
////		float on_key = key / step0;
//
//		//check fix_key_index0 and check time % as id
//		if (key_index_byte_array[time_index] != fix_key_index0)
//		{
//			if (key_index_byte_array[time_index] != fix_key_index1)
//			{
//				key_index_byte_array[time_index] = fix_key_index0;
//				key_index_byte_array[time_index + 1] = fix_key_index0;
//				time_short_array[time_index] = key_short_array[fix_key_index0];
//				time_short_array[time_index + 1] = (short)(time_short_array[time_index] + step0);
////				this.siekey.sync_byte_set.add(time_index);
//			}
//			else
//			{
//				if (key_short_array[fix_key_index1] + time_short_array[time_index] >= key_short_array[fix_key_index1 + 1])
//				{
//					key_index_byte_array[time_index] = fix_key_index1;
//					key_index_byte_array[time_index + 1] = fix_key_index1;
//					time_short_array[time_index] = key_short_array[fix_key_index1];
//					time_short_array[time_index + 1] = (short)(time_short_array[time_index] + step1);
////					this.siekey.sync_byte_set.add(time_index);
//				}
//				else
//				{
//					key_index_byte_array[time_index] = fix_key_index1;
//					key_index_byte_array[time_index + 1] = fix_key_index1;
//					time_short_array[time_index] = (short)(key_short_array[fix_key_index1] + step1);
//					time_short_array[time_index + 1] = (short)(time_short_array[time_index] + step1);
////					this.siekey.sync_byte_set.add(time_index);
//				}
//			}
//		}
//		else
//		{
//			if (key_short_array[fix_key_index0] + time_short_array[time_index] >= key_short_array[fix_key_index0 + 1])
//			{
//				key_index_byte_array[time_index] = fix_key_index1;
//				key_index_byte_array[time_index + 1] = fix_key_index1;
//				time_short_array[time_index] = key_short_array[fix_key_index1];
//				time_short_array[time_index + 1] = (short)(time_short_array[time_index] + step1);
////				this.siekey.sync_byte_set.add(time_index);
//			}
//			else
//			{
//				key_index_byte_array[time_index] = fix_key_index0;
//				key_index_byte_array[time_index + 1] = fix_key_index0;
//				time_short_array[time_index] = (short)(key_short_array[fix_key_index0] + step0);
//				time_short_array[time_index + 1] = (short)(time_short_array[time_index] + step0);
////				this.siekey.sync_byte_set.add(time_index);
//			}
//		}
//		this.siekey.sync_byte_set.add(time_index);
//		return true;
//
////		if (on_key >= key_short_array[fix_key_index0 + 1] && on_key <= key_short_array[fix_key_index1 + 1])
////		{
////			key_index_byte_array[time_index] = fix_key_index0;
////			key_index_byte_array[time_index + 1] = fix_key_index0;
////			if (on_key >= key_short_array[fix_key_index1 + 1])
////			{
////				time_short_array[time_index] = key_short_array[fix_key_index0];
////				time_short_array[time_index + 1] = (short)(time_short_array[time_index] + step);
////				this.siekey.sync_byte_set.add(time_index);
////				return true;
////			}
////
////			time_short_array[time_index] += step;
////			time_short_array[time_index + 1] = (short)(time_short_array[time_index] + step);
////			this.siekey.sync_byte_set.add(time_index);
////			return true;
////		}
//
////		return false;
	}
}
