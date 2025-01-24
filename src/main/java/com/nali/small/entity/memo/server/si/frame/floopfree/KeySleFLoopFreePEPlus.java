package com.nali.small.entity.memo.server.si.frame.floopfree;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaO;
import com.nali.da.IBothDaS;
import com.nali.da.IBothDaSe;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixES;
import com.nali.small.entity.IMixESInv;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;

public class KeySleFLoopFreePEPlus
<
	BD extends IBothDaE & IBothDaO & IBothDaS & IBothDaSe,
	E extends EntityLe,
	I extends IMixE<BD, E> & IMixES & IMixESInv,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends KeySleFLoopFreePE<BD, E, I, S, MS>
{
	public byte free;
//	size;

	public KeySleFLoopFreePEPlus(S s, byte key_data_index/*, byte size*/)
	{
		super(s, key_data_index);
//		this.size = size;
	}

	@Override
	public boolean step()
	{
		boolean result = super.step();
		if (!result)
		{
			this.free = (byte)(this.s.i.getE().ticksExisted % /*this.size*/this.s.getKeyDataByteArray()[this.key_data_index + 1]);
		}

		return result;
	}

	@Override
	public byte getFixKeyIndex(byte[] key_data_byte_array)
	{
		return key_data_byte_array[this.key_data_index + 1 + 1 + this.free];
	}
}
