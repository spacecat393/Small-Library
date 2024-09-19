package com.nali.small.entity.memo.server.si.frame.tloop;

import com.nali.da.IBothDaNe;
import com.nali.list.entity.si.SILeAttack;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixESoundDa;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.sound.ISoundDaLe;

public class FrameSleTLoopAttackStand
<
	SD extends ISoundDaLe,
	BD extends IBothDaNe,
	E extends EntityLe,
	I extends IMixE<BD, E> & IMixESoundDa<SD>,
	S extends ServerLe<SD, BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends FrameSTLoop<BD, E, I, S, MS>
{
	public SILeAttack<SD, BD, E, I, S, MS> sileattack;

	public FrameSleTLoopAttackStand(S s, int index)
	{
		super(s, index);

		this.sileattack = (SILeAttack<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeAttack.ID);
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
		return (this.sileattack.flag & 16) == 16 && super.onUpdate();
	}
}
