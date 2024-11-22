package com.nali.small.entity.memo.server.si.frame.floop;

import com.nali.da.IBothDaE;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;

public class FrameSleFLoopDiePlus
<
	BD extends IBothDaE,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends FrameSleFLoopDie<BD, E, I, S, MS>
{
	public byte die;

	public FrameSleFLoopDiePlus(S s, int index)
	{
		super(s, index);
	}

	@Override
	public boolean step()
	{
		boolean result = super.step();
		if (!result)
		{
			this.die = (byte)(this.s.i.getE().ticksExisted % this.s.getFrameByteArray()[this.index + 1]);
		}

		return super.step();
	}

	@Override
	public byte getIndex()
	{
		return this.s.getFrameByteArray()[this.index + 1 + 1 + this.die];
	}
}
