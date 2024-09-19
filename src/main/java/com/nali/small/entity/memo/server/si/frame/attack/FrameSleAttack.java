package com.nali.small.entity.memo.server.si.frame.attack;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.list.entity.si.SILeAttack;
import com.nali.list.entity.si.SILeFindMove;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixESoundDa;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.frame.FrameS;
import com.nali.sound.ISoundDaLe;

public class FrameSleAttack
<
	SD extends ISoundDaLe,
	BD extends IBothDaNe & IBothDaSn,
	E extends EntityLe,
	I extends IMixE<BD, E> & IMixESoundDa<SD>,
	S extends ServerLe<SD, BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends FrameS<BD, E, I, S, MS>
{
	public SILeAttack<SD, BD, E, I, S, MS> sileattack;
	public SILeFindMove<SD, BD, E, I, S, MS> silefindmove;

	public FrameSleAttack(S s, int index)
	{
		super(s, index);

		this.sileattack = (SILeAttack<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeAttack.ID);
		this.silefindmove = (SILeFindMove<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeFindMove.ID);
	}
//
//	@Override
//	public void init()
//	{
//		super.init();
//		this.sileattack = (SILeAttack<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeAttack.ID);
//		this.silefindmove = (SILeFindMove<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeFindMove.ID);
//	}

	@Override
	public boolean onUpdate()
	{
		if (this.step())
		{
			int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
//			byte[] frame_byte_array = this.s.getFrameByteArray();
			this.step = 1;

//			byte frame = frame_byte_array[this.index];
			byte frame = this.s.getFrameByteArray()[this.index];
			byte id = this.getIndex();

			if (this.sieframe.frame_int_array[frame] < frame_2d_int_array[id][0] || this.sieframe.frame_int_array[frame] >= frame_2d_int_array[id][1])
			{
				this.sieframe.frame_int_array[frame] = frame_2d_int_array[id][0];
				this.step = 0;
				return true;
			}

			for (int attack_frame : this.sileattack.attack_frame_int_array)
			{
				if (this.sieframe.frame_int_array[frame] == attack_frame)
				{
					this.sileattack.flag |= 4;
					break;
				}
			}

			return true;
		}

		return false;
	}

	public boolean step()
	{
		return (this.sileattack.flag & 2) == 2;
	}

	public byte getIndex()
	{
		return this.s.getFrameByteArray()[this.index + 1];
	}
}
