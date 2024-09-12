package com.nali.small.entity.memo.server.si.frame.tloop;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.sound.ISoundDaLe;

public class FrameSleTLoopWalk<SD extends ISoundDaLe, BD extends IBothDaNe, E extends EntityLe, I extends IMixE<SD, BD, E>, S extends ServerLe<SD, BD, E, I, MS> & IServerS, MS extends MixSIE<SD, BD, E, I, S>> extends FrameSTLoop<SD, BD, E, I, S, MS>
{
	public FrameSleTLoopWalk(S s, int index)
	{
		super(s, index);
	}

	@Override
	public boolean onUpdate()
	{
		return this.s.i.getE().moveForward != 0 && super.onUpdate();
	}
}
