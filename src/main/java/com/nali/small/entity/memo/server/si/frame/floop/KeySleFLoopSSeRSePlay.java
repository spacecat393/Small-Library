package com.nali.small.entity.memo.server.si.frame.floop;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.da.IBothDaSe;
import com.nali.list.entity.si.SIEPlayWithRSe;
import com.nali.small.entity.memo.server.si.play.SILePlayWithSSe;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import net.minecraft.entity.Entity;

public class KeySleFLoopSSeRSePlay
<
	R2 extends SIEPlayWithRSe<S, BD, E, I, MS, BD2, E2, I2, S2, A2>,
	S2 extends ServerE<BD2, E2, I2, A2>,
	BD2 extends IBothDaE,
	E2 extends Entity,
	I2 extends IMixE<BD2, E2>,
	A2 extends MixSIE<BD2, E2, I2, S2>,
	BD extends IBothDaE & IBothDaS & IBothDaSe,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends KeySFLoop<BD, E, I, S, MS>
{
	public SILePlayWithSSe<R2, S2, BD2, E2, I2, A2, BD, E, I, S, MS> sileplaywithsse;

	public KeySleFLoopSSeRSePlay(S s, byte key_data_index)
	{
		super(s, key_data_index);

		this.sileplaywithsse = (SILePlayWithSSe<R2, S2, BD2, E2, I2, A2, BD, E, I, S, MS>)this.s.ms.si_map.get(SILePlayWithSSe.ID);
	}
//
//	@Override
//	public void init()
//	{
//		super.init();
//		this.sileplaywithsse = (SILePlayWithSSe<R2, S2, BD2, E2, I2, A2, SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILePlayWithSSe.ID);
//	}

	@Override
	public boolean onUpdate()
	{
		return this.sileplaywithsse.s2 != null && (((R2)this.sileplaywithsse.s2.ms.si_map.get(R2.ID)).state & 2) == 2 && super.onUpdate();
	}
}
