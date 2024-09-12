package com.nali.small.entity.memo.server.si.frame.floop;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.sound.ISoundDaLe;

public class FrameSleFLoopDiePlus<SD extends ISoundDaLe, BD extends IBothDaNe, E extends EntityLe, I extends IMixE<SD, BD, E>, S extends ServerLe<SD, BD, E, I, MS> & IServerS, MS extends MixSIE<SD, BD, E, I, S>> extends FrameSleFLoopDie<SD, BD, E, I, S, MS>
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
