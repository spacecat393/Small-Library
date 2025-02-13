package com.nali.small.entity.memo.server.si;

import com.nali.da.IBothDaE;
import com.nali.list.entity.si.SIEInvLockInv;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class MixSIEInv
<
	BD extends IBothDaE,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, ?>
> extends MixSIE<BD, E, I, S>
{
	public SIEInvLockInv<BD, E, I, S, ?> aileinvlockinv;
	public MixSIEInv(S s, SI[] si_array)
	{
		super(s, si_array);
		this.aileinvlockinv = (SIEInvLockInv<BD, E, I, S, ?>)this.si_map.get(SIEInvLockInv.ID);
	}

	public void call(EntityPlayerMP entityplayermp, byte[] byte_array)
	{
		if (this.aileinvlockinv.canPass(entityplayermp))
		{
			super.call(entityplayermp, byte_array);
		}
	}
}
