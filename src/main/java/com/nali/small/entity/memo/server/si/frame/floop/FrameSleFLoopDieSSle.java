package com.nali.small.entity.memo.server.si.frame.floop;

import com.nali.da.IBothDaNe;
import com.nali.list.entity.si.SIEPlayWithRSe;
import com.nali.list.entity.si.SILePlayWithSSle;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixESoundDa;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.sound.ISoundDaLe;
import net.minecraft.entity.EntityLivingBase;

public class FrameSleFLoopDieSSle
<
	R2 extends SIEPlayWithRSe<S, BD, E, I, MS, BD2, E2, I2, S2, A2>,
	S2 extends ServerLe<SD2, BD2, E2, I2, A2>,
	SD2 extends ISoundDaLe,
	BD2 extends IBothDaNe,
	E2 extends EntityLivingBase,
	I2 extends IMixE<BD2, E2> & IMixESoundDa<SD2>,
	A2 extends MixSIE<BD2, E2, I2, S2>,
	SD extends ISoundDaLe,
	BD extends IBothDaNe,
	E extends EntityLe,
	I extends IMixE<BD, E> & IMixESoundDa<SD>,
	S extends ServerLe<SD, BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends FrameSleFLoopDie<SD, BD, E, I, S, MS>
{
	public SILePlayWithSSle<R2, S2, SD2, BD2, E2, I2, A2, SD, BD, E, I, S, MS> sileplaywithssle;

	public FrameSleFLoopDieSSle(S s, int index)
	{
		super(s, index);

		this.sileplaywithssle = (SILePlayWithSSle<R2, S2, SD2, BD2, E2, I2, A2, SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILePlayWithSSle.ID);
	}
//
//	@Override
//	public void init()
//	{
//		super.init();
//		this.sileplaywithssle = (SILePlayWithSSle<R2, S2, SD2, BD2, E2, I2, A2, SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILePlayWithSSle.ID);
//	}

	@Override
	public boolean step()
	{
		return this.sileplaywithssle.s2 != null && this.sileplaywithssle.s2.isZeroMove(this.sileplaywithssle.s2.i.getE());
	}
}
