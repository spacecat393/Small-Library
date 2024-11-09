package com.nali.small.entity.memo.server.si.frame.shoot;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.list.entity.si.SILeAttack;
import com.nali.list.entity.si.SILeFindMove;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.frame.FrameS;

public class FrameSleShoot
<
	BD extends IBothDaNe & IBothDaSn,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends FrameS<BD, E, I, S, MS>
{
	public SILeAttack<BD, E, I, S, MS> sileattack;
	public SILeFindMove<BD, E, I, S, MS> silefindmove;

	public FrameSleShoot(S s, int index)
	{
		super(s, index);
		this.init();
	}

//	@Override
	public void init()
	{
//		super.init();
		this.sileattack = (SILeAttack<BD, E, I, S, MS>)this.s.ms.si_map.get(SILeAttack.ID);
		this.silefindmove = (SILeFindMove<BD, E, I, S, MS>)this.s.ms.si_map.get(SILeFindMove.ID);
	}

	@Override
	public boolean onUpdate()
	{
		int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
		byte[] frame_byte_array = this.s.getFrameByteArray();
		byte frame = frame_byte_array[this.index];
		if (this.sileattack.magic_point <= 0)
		{
			this.step = 1;
//			if (this.checkShoot(true))
//			{
//				return true;
//			}
//			else
			if (!this.checkShoot(true))
			{
				byte index3 = this.getReload();
				this.silefindmove.endGoal();

				if (this.sieframe.frame_int_array[frame] < frame_2d_int_array[index3][0] || this.sieframe.frame_int_array[frame] > frame_2d_int_array[index3][1])
				{
					this.step = 0;
					this.sieframe.frame_int_array[frame] = frame_2d_int_array[index3][0];
//					return true;
				}
				else if (this.sieframe.frame_int_array[frame] >= frame_2d_int_array[index3][0] && this.sieframe.frame_int_array[frame] <= frame_2d_int_array[index3][1])
				{
					if (this.sieframe.frame_int_array[frame] == frame_2d_int_array[index3][1])
					{
						this.step = 0;
						this.sileattack.magic_point = this.sileattack.max_magic_point;
//						return true;
					}
				}
//				return true;
			}
			return true;
		}
		else if ((this.sileattack.flag & 2) == 2)
		{
//			byte index = frame_byte_array[this.index + 1];
			byte index = this.getStartAttack();
//			byte index1 = frame_byte_array[this.index + 2];
			byte index1 = this.getAttack();
			if (this.sieframe.frame_int_array[frame] >= frame_2d_int_array[index1][0] && this.sieframe.frame_int_array[frame] <= frame_2d_int_array[index1][1])
			{
				this.step();

				for (int attack_frame : this.sileattack.attack_frame_int_array)
				{
					if (this.sieframe.frame_int_array[frame] == attack_frame)
					{
						this.sileattack.magic_point -= 1;
						this.sileattack.flag |= 4;
						break;
					}
				}
				return true;
			}
			else if (this.sieframe.frame_int_array[frame] >= frame_2d_int_array[index][0] && this.sieframe.frame_int_array[frame] <= frame_2d_int_array[index][1])
			{
				if (this.sieframe.frame_int_array[frame] == frame_2d_int_array[index][1])
				{
					this.sieframe.frame_int_array[frame] = frame_2d_int_array[index1][1];
					this.step = 0;
					return true;
				}

				this.step = 1;
				return true;
			}
			else if (this.sieframe.frame_int_array[frame] < frame_2d_int_array[index][0] || this.sieframe.frame_int_array[frame] > frame_2d_int_array[index][1])
			{
				this.sieframe.frame_int_array[frame] = frame_2d_int_array[index][0];
				this.step = 0;
				return true;
			}
		}
		else
		{
			this.step = 1;
			return this.checkShoot(false);
		}

		return false;
	}

	public boolean checkShoot(boolean try_reload)
	{
		int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
		byte[] frame_byte_array = this.s.getFrameByteArray();
		byte frame = frame_byte_array[this.index];
//		byte index = frame_byte_array[this.index + 1];
		byte index = this.getStartAttack();
//		byte index1 = frame_byte_array[this.index + 2];
		byte index1 = this.getAttack();
//		byte index2 = frame_byte_array[this.index + 3];
		byte index2 = this.getEndAttack();
		if (this.sieframe.frame_int_array[frame] >= frame_2d_int_array[index][0] && this.sieframe.frame_int_array[frame] <= frame_2d_int_array[index][1])
		{
			this.silefindmove.endGoal();
			if (this.sieframe.frame_int_array[frame] == frame_2d_int_array[index][1])
			{
				this.sieframe.frame_int_array[frame] = frame_2d_int_array[index2][0];
				this.step = 0;
			}
			return true;
		}
		else if (this.sieframe.frame_int_array[frame] >= frame_2d_int_array[index1][0] && this.sieframe.frame_int_array[frame] <= frame_2d_int_array[index1][1])
		{
			this.silefindmove.endGoal();
			if (this.sieframe.frame_int_array[frame] == frame_2d_int_array[index1][1])
			{
				this.sieframe.frame_int_array[frame] = frame_2d_int_array[index2][0];
				this.step = 0;
			}
			return true;
		}
		else if (!try_reload && this.sieframe.frame_int_array[frame] >= frame_2d_int_array[index2][0] && this.sieframe.frame_int_array[frame] < frame_2d_int_array[index2][1])
		{
			this.silefindmove.endGoal();
			return true;
		}
		else
		{
			return false;
		}
	}

	public void step()
	{
		int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
		byte[] frame_byte_array = this.s.getFrameByteArray();
		byte frame = frame_byte_array[this.index];
//		byte index1 = frame_byte_array[this.index + 2];
		byte index1 = this.getAttack();
		this.step = 1;
		if (this.sieframe.frame_int_array[frame] >= frame_2d_int_array[index1][0] && this.sieframe.frame_int_array[frame] <= frame_2d_int_array[index1][1])
		{
			if (this.sieframe.frame_int_array[frame] == frame_2d_int_array[index1][1])
			{
				this.sieframe.frame_int_array[frame] = frame_2d_int_array[index1][0];
				this.step = 0;
			}
		}
	}

	public byte getStartAttack()
	{
		return this.s.getFrameByteArray()[this.index + 1];
	}

	public byte getAttack()
	{
		return this.s.getFrameByteArray()[this.index + 2];
	}

	public byte getEndAttack()
	{
		return this.s.getFrameByteArray()[this.index + 3];
	}

	public byte getReload()
	{
		return this.s.getFrameByteArray()[this.index + 4];
	}
}
