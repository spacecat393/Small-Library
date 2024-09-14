package com.nali.small.entity.memo.server.si.frame.shoot;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixESoundDa;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.sound.ISoundDaLe;

public class FrameSleShootReloadPlus
<
	SD extends ISoundDaLe,
	BD extends IBothDaNe & IBothDaSn,
	E extends EntityLe,
	I extends IMixE<BD, E> & IMixESoundDa<SD>,
	S extends ServerLe<SD, BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends FrameSleShoot<SD, BD, E, I, S, MS>
{
	public byte
	reload;
//	size;

	public FrameSleShootReloadPlus(S s, int index/*, byte size*/)
	{
		super(s, index);
//		this.size = size;
	}

	@Override
	public boolean onUpdate()
	{
		if (this.sileattack.magic_point > 0)
		{
			this.reload = (byte)(this.s.i.getE().ticksExisted % /*this.size*/this.s.getFrameByteArray()[this.index + 4]);
		}

		return super.onUpdate();
	}

	@Override
	public byte getReload()
	{
		return this.s.getFrameByteArray()[this.index + 4 + 1 + this.reload];
	}
}
