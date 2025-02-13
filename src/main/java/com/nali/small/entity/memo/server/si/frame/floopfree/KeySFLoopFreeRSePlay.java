package com.nali.small.entity.memo.server.si.frame.floopfree;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.da.IBothDaSe;
import com.nali.list.entity.si.SIEPlayWithRSe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import net.minecraft.entity.Entity;

public class KeySFLoopFreeRSePlay
<
	S2 extends ServerE<BD2, E2, I2, A2>,
	BD2 extends IBothDaE,
	E2 extends Entity,
	I2 extends IMixE<BD2, E2>,
	A2 extends MixSIE<BD2, E2, I2, S2>,
	BD extends IBothDaE & IBothDaS & IBothDaSe,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends KeySFLoopFree<BD, E, I, S, MS>
{
	public SIEPlayWithRSe<S2, BD2, E2, I2, A2, BD, E, I, S, MS> sieplaywithrse;

	public KeySFLoopFreeRSePlay(S s, byte key_data_index)
	{
		super(s, key_data_index);
		this.sieplaywithrse = (SIEPlayWithRSe<S2, BD2, E2, I2, A2, BD, E, I, S, MS>)this.s.ms.si_map.get(SIEPlayWithRSe.ID);
	}

	@Override
	public boolean step()
	{
		return this.sieplaywithrse.s2 == null ? false : (((SIEPlayWithRSe)this.sieplaywithrse.s2.ms.si_map.get(SIEPlayWithRSe.ID)).flag & SIEPlayWithRSe.B_PLAY) == SIEPlayWithRSe.B_PLAY;
	}

	@Override
	public void free()
	{
		if (this.sieplaywithrse.s2 != null)
		{
			((SIEPlayWithRSe)this.sieplaywithrse.s2.ms.si_map.get(SIEPlayWithRSe.ID)).flag &= 255 - SIEPlayWithRSe.B_PLAY;
		}
	}
}
