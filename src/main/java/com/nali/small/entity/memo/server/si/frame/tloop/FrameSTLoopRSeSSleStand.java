package com.nali.small.entity.memo.server.si.frame.tloop;

import com.nali.da.IBothDaNe;
import com.nali.list.entity.si.SIEPlayWithRSe;
import com.nali.list.entity.si.SILePlayWithSSle;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.sound.ISoundDaLe;
import net.minecraft.entity.EntityLivingBase;

public class FrameSTLoopRSeSSleStand<MA extends SIEPlayWithRSe<S2, SD2, BD2, E2, I2, A2, SD, BD, E, I, S, MS>, R2 extends SILePlayWithSSle<MA, S, SD, BD, E, I, MS, SD2, BD2, E2, I2, S2, A2>, S2 extends ServerLe<SD2, BD2, E2, I2, A2>, SD2 extends ISoundDaLe, BD2 extends IBothDaNe, E2 extends EntityLivingBase, I2 extends IMixE<SD2, BD2, E2>, A2 extends MixSIE<SD2, BD2, E2, I2, S2>, SD extends ISoundDaLe, BD extends IBothDaNe, E extends EntityLivingBase, I extends IMixE<SD, BD, E>, S extends ServerLe<SD, BD, E, I, MS> & IServerS, MS extends MixSIE<SD, BD, E, I, S>> extends FrameSTLoopRSeSSeStand<MA, R2, S2, SD2, BD2, E2, I2, A2, SD, BD, E, I, S, MS>
{
	public FrameSTLoopRSeSSleStand(S s, int index)
	{
		super(s, index);
	}
}
