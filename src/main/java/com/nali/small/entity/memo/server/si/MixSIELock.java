package com.nali.small.entity.memo.server.si;

import com.nali.da.IBothDaE;
import com.nali.list.entity.si.SIELock;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class MixSIELock
<
	BD extends IBothDaE,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, ?>
> extends MixSIE<BD, E, I, S>
{
	public SIELock<BD, E, I, S, ?> sielock;
	public MixSIELock(S s, SI[] si_array)
	{
		super(s, si_array);
		this.sielock = (SIELock<BD, E, I, S, ?>)this.si_map.get(SIELock.ID);
	}

	@Override
	public SI getSI(EntityPlayerMP entityplayermp, byte id)
	{
		return this.sielock.canPass(entityplayermp) ? super.getSI(entityplayermp, id) : null;
	}

	public void call(EntityPlayerMP entityplayermp, byte[] byte_array)
	{
		if (this.sielock.canPass(entityplayermp))
		{
			super.call(entityplayermp, byte_array);
		}
	}
}
