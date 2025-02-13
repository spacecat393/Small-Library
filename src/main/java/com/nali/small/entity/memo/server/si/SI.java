package com.nali.small.entity.memo.server.si;

import com.nali.da.IBothDaE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public abstract class SI
<
	BD extends IBothDaE,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
>
{
	public S s;

	public SI(S s)
	{
		this.s = s;
	}

	public abstract void init();
	public abstract void call(EntityPlayerMP entityplayermp, byte[] byte_array);
//	{
//		MS ms = this.s.ms;
//		if ((ms.flag & MixSIE.B_LOCK_NET) == 0)
//		{
//			ms.flag |= MixSIE.B_LOCK_NET;
//			ms.entityplayermp = entityplayermp;
//			ms.byte_array = byte_array;
//		}
////		ms.flag &= 255 - MixSIE.B_LOCK_NET;
//	}
	public abstract void onUpdate();
	public abstract void writeFile(SIData sidata);
	public abstract void readFile(SIData sidata);
	public abstract int size();
}
