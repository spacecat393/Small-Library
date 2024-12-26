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
		short[] key_short_array = this.siekey.key_short_array;
		short[] fix_key_short_array = this.s.getFixKeyShortArray();
		byte[] key_data_byte_array = this.s.getKeyDataByteArray();

		byte key_short_index = key_data_byte_array[this.key_data_index];
		byte fix_key_index = this.getFixKeyIndex(key_data_byte_array);

//		Nali.warn("key_short_array[key_short_index] " + key_short_array[key_short_index]);
		if (key_short_array[key_short_index] < fix_key_short_array[fix_key_index] || key_short_array[key_short_index] > fix_key_short_array[fix_key_index + 1])
		{
//			Nali.warn("=");
			key_short_array[key_short_index] = fix_key_short_array[fix_key_index];
			this.siekey.sync_byte_arraylist.add(key_short_index);
		}
		else if (key_short_array[key_short_index] < fix_key_short_array[fix_key_index + 1])
		{
//			Nali.warn("++");
			++key_short_array[key_short_index];
			this.siekey.sync_byte_arraylist.add(key_short_index);
		}
//		else
//		{
////			Nali.warn("Done");
//			return false;
//		}

//		Nali.warn("Sync");
		return true;
	}

	public byte getFixKeyIndex(byte[] key_data_byte_array)
	{
		return key_data_byte_array[this.key_data_index + 1];
	}
}
