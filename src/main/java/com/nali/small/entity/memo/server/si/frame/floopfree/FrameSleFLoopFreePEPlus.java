package com.nali.small.entity.memo.server.si.frame.floopfree;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;

public class FrameSleFLoopFreePEPlus
<
	BD extends IBothDaNe & IBothDaSn,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends FrameSleFLoopFreePE<BD, E, I, S, MS>
{
	public byte
	free;
//	size;

	public FrameSleFLoopFreePEPlus(S s, int index/*, byte size*/)
	{
		super(s, index);
//		this.size = size;
	}

	@Override
	public boolean step()
	{
		boolean result = super.step();
		if (!result)
		{
			this.free = (byte)(this.s.i.getE().ticksExisted % /*this.size*/this.s.getFrameByteArray()[this.index + 1]);
		}

		return result;
	}

	@Override
	public byte getIndex()
	{
		return this.s.getFrameByteArray()[this.index + 1 + 1 + this.free];
	}
}
