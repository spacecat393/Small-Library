package com.nali.small.entity.memo.server.si.frame.floopoffset;

import com.nali.da.IBothDaNe;
import com.nali.list.entity.si.SILeAttack;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;

public class FrameSleFLoopOffSetAttackEndWalk
<
	BD extends IBothDaNe,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends FrameSFLoopOffSet<BD, E, I, S, MS>
{
	public SILeAttack<BD, E, I, S, MS> sileattack;

	public FrameSleFLoopOffSetAttackEndWalk(S s, int index)
	{
		super(s, index);

		this.sileattack = (SILeAttack<BD, E, I, S, MS>)this.s.ms.si_map.get(SILeAttack.ID);
	}
//
//	@Override
//	public void init()
//	{
//		super.init();
//		this.sileattack = (SILeAttack<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeAttack.ID);
//	}

	@Override
	public boolean onUpdate()
	{
		return (this.sileattack.flag & 16) == 16 && this.s.i.getE().moveForward == 0 && super.onUpdate();
	}
}
