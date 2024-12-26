package com.nali.small.entity.memo.server.si.frame.floop;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.da.IBothDaSe;
import com.nali.list.entity.si.SIEPlayWithRSe;
import com.nali.list.entity.si.SILePlayWithSSle;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;

public class KeySleFLoopSSleStart
<
	R2 extends SIEPlayWithRSe<S, BD, E, I, MS, BD2, E2, I2, S2, A2>,
	S2 extends ServerLe<BD2, E2, I2, A2>,
	BD2 extends IBothDaE,
	E2 extends EntityLe,
	I2 extends IMixE<BD2, E2>,
	A2 extends MixSIE<BD2, E2, I2, S2>,
	BD extends IBothDaE & IBothDaS & IBothDaSe,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends KeySFLoop<BD, E, I, S, MS>
{
	public SILePlayWithSSle<R2, S2, BD2, E2, I2, A2, BD, E, I, S, MS> sileplaywithssle;

	public KeySleFLoopSSleStart(S s, byte key_data_index)
	{
		super(s, key_data_index);

		this.sileplaywithssle = (SILePlayWithSSle<R2, S2, BD2, E2, I2, A2, BD, E, I, S, MS>)this.s.ms.si_map.get(SILePlayWithSSle.ID);
	}
//
//	@Override
//	public void init()
//	{
//		super.init();
//		this.sileplaywithssle = (SILePlayWithSSle<R2, S2, SD2, BD2, E2, I2, A2, SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILePlayWithSSle.ID);
//	}

	@Override
	public boolean onUpdate()
	{
		return this.sileplaywithssle.s2 != null && super.onUpdate();
	}
}
