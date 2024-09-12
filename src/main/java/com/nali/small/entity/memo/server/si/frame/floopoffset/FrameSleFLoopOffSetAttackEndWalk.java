package com.nali.small.entity.memo.server.si.frame.floopoffset;

import com.nali.da.IBothDaNe;
import com.nali.list.entity.si.SILeAttack;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.sound.ISoundDaLe;

public class FrameSleFLoopOffSetAttackEndWalk<SD extends ISoundDaLe, BD extends IBothDaNe, E extends EntityLe, I extends IMixE<SD, BD, E>, S extends ServerLe<SD, BD, E, I, MS> & IServerS, MS extends MixSIE<SD, BD, E, I, S>> extends FrameSFLoopOffSet<SD, BD, E, I, S, MS>
{
	public SILeAttack<SD, BD, E, I, S, MS> sileattack;

	public FrameSleFLoopOffSetAttackEndWalk(S s, int index)
	{
		super(s, index);
	}

	@Override
	public void init()
	{
		super.init();
		this.sileattack = (SILeAttack<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeAttack.ID);
	}

	@Override
	public boolean onUpdate()
	{
		return (this.sileattack.flag & 16) == 16 && this.s.i.getE().moveForward == 0 && super.onUpdate();
	}
}
