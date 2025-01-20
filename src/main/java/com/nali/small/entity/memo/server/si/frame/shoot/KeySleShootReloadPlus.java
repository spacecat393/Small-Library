package com.nali.small.entity.memo.server.si.frame.shoot;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.da.IBothDaSe;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;

public class KeySleShootReloadPlus
<
	BD extends IBothDaE & IBothDaS & IBothDaSe,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends KeySleShoot<BD, E, I, S, MS>
{
	public byte reload;

	public KeySleShootReloadPlus(S s, byte key_data_index)
	{
		super(s, key_data_index);
	}

	@Override
	public boolean onUpdate()
	{
		if (this.sileattack.magic_point > 0)
		{
			this.reload = (byte)(this.s.i.getE().ticksExisted % this.s.getKeyDataByteArray()[this.key_data_index + 4]);
		}

		return super.onUpdate();
	}

	@Override
	public byte getReloadFixKeyIndex(byte[] key_data_byte_array)
	{
		return key_data_byte_array[this.key_data_index + 4 + 1 + this.reload];
	}

//	@Override
//	public byte getReload()
//	{
//		return this.s.getFrameByteArray()[this.rg + 4 + 1 + this.reload];
//	}
}
