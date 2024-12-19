package com.nali.small.entity.memo.server.si.frame.floop;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.da.IBothDaSe;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;

public class KeySleFLoopDiePlus
<
	BD extends IBothDaE & IBothDaS & IBothDaSe,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends KeySleFLoopDie<BD, E, I, S, MS>
{
	public byte die;

	public KeySleFLoopDiePlus(S s, byte key_data_index)
	{
		super(s, key_data_index);
	}

	@Override
	public boolean step()
	{
		boolean result = super.step();
		if (!result)
		{
			this.die = (byte)(this.s.i.getE().ticksExisted % this.s.getKeyDataByteArray()[this.key_data_index + 1]);
		}

		return super.step();
	}

	@Override
	public byte getFixKeyIndex(byte[] key_data_byte_array)
	{
		return key_data_byte_array[this.key_data_index + 1 + 1 + this.die];
	}
}
