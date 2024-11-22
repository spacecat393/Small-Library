package com.nali.small.entity.memo.server.si.frame.shoot;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;

public class FrameSleShootReloadPlus
<
	BD extends IBothDaE & IBothDaS,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends FrameSleShoot<BD, E, I, S, MS>
{
	public byte
	reload;
//	size;

	public FrameSleShootReloadPlus(S s, int index/*, byte size*/)
	{
		super(s, index);
//		this.size = size;
	}

	@Override
	public boolean onUpdate()
	{
		if (this.sileattack.magic_point > 0)
		{
			this.reload = (byte)(this.s.i.getE().ticksExisted % /*this.size*/this.s.getFrameByteArray()[this.index + 4]);
		}

		return super.onUpdate();
	}

	@Override
	public byte getReload()
	{
		return this.s.getFrameByteArray()[this.index + 4 + 1 + this.reload];
	}
}
