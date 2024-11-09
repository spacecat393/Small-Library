package com.nali.small.entity.memo.server.si.frame.shoot;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.list.entity.si.SIEPlayWithRSe;
import com.nali.list.entity.si.SILeAttack;
import com.nali.list.entity.si.SILeFindMove;
import com.nali.list.entity.si.SILePlayWithSSle;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;

public class FrameSleShootSSle
<
	R2 extends SIEPlayWithRSe<S, BD, E, I, MS, BD2, E2, I2, S2, A2>,
	S2 extends ServerLe<BD2, E2, I2, A2> & IServerS,
	BD2 extends IBothDaNe & IBothDaSn,
	E2 extends EntityLe,
	I2 extends IMixE<BD2, E2>,
	A2 extends MixSIE<BD2, E2, I2, S2>,
	BD extends IBothDaNe & IBothDaSn,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends FrameSleShoot<BD, E, I, S, MS>
{
	public SILePlayWithSSle<R2, S2, BD2, E2, I2, A2, BD, E, I, S, MS> sileplaywithssle;

	public FrameSleShootSSle(S s, int index)
	{
		super(s, index);
	}

	@Override
	public void init()
	{
		this.sileplaywithssle = (SILePlayWithSSle<R2, S2, BD2, E2, I2, A2, BD, E, I, S, MS>)this.s.ms.si_map.get(SILePlayWithSSle.ID);
	}

	@Override
	public boolean onUpdate()
	{
		boolean result = this.sileplaywithssle.s2 != null;
		if (result)
		{
			this.sileattack = (SILeAttack/*<SD2, BD2, E2, I2, S2, A2>*/)this.sileplaywithssle.s2.ms.si_map.get(SILeAttack.ID);
			this.silefindmove = (SILeFindMove/*<SD2, BD2, E2, I2, S2, A2>*/)this.sileplaywithssle.s2.ms.si_map.get(SILeFindMove.ID);
		}
		return result && super.onUpdate();
	}
}
