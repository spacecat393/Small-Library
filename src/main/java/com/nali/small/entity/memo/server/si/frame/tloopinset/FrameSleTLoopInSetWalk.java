package com.nali.small.entity.memo.server.si.frame.tloopinset;

import com.nali.da.IBothDaE;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;

public class FrameSleTLoopInSetWalk
<
	BD extends IBothDaE,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends KeySTLoopInSet<BD, E, I, S, MS>
{
	public FrameSleTLoopInSetWalk(S s, int index)
	{
		super(s, index);
	}

	@Override
	public boolean onUpdate()
	{
		return this.s.i.getE().moveForward != 0 && super.onUpdate();
	}
}
