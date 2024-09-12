package com.nali.small.entity.memo.server.si.frame.shoot;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.sound.ISoundDaLe;

public class FrameSleShootAttackPlus<SD extends ISoundDaLe, BD extends IBothDaNe & IBothDaSn, E extends EntityLe, I extends IMixE<SD, BD, E>, S extends ServerLe<SD, BD, E, I, MS> & IServerS, MS extends MixSIE<SD, BD, E, I, S>> extends FrameSleShoot<SD, BD, E, I, S, MS>
{
	public byte
//	start_attack,
	attack;
//	size;

	public FrameSleShootAttackPlus(S s, int index/*, byte size*/)
	{
		super(s, index);
//		this.size = size;
	}

	@Override
	public boolean onUpdate()
	{
//		int id = 10;
//		int start_id = 2;

//		if (serverentitiesmemory.server_how_attack)
//		{
//		id = 13;
//		start_id = 13;

		int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
		byte[] frame_byte_array = this.s.getFrameByteArray();
		byte frame = frame_byte_array[this.index];
		byte attack = this.getAttack();
		if (this.sieframe.frame_int_array[frame] == frame_2d_int_array[attack][1] - 1)
		{
//			start_id = 2;
//			id = 10;
			this.sieframe.frame_int_array[frame] = frame_2d_int_array[2][0];
//			serverentitiesmemory.server_how_attack = false;
		}
//		}

		if (this.sieframe.frame_int_array[frame] == frame_2d_int_array[attack][1] - 1)
		{
//			byte start_attack_size = frame_byte_array[this.index + 1];
//			byte attack_size = frame_byte_array[this.index + 1 + start_attack_size];
//			byte id = (byte)(this.s.i.getE().ticksExisted % start_attack_size);
//			this.start_attack = id;
//			this.attack = (byte)(this.s.i.getE().ticksExisted % attack_size);
			this.attack = (byte)(this.s.i.getE().ticksExisted % frame_byte_array[this.index + 1]);
//			serverentitiesmemory.server_how_attack = (byte)(this.ticksExisted % 20) == 0;
		}

		return super.onUpdate();
	}

	@Override
	public byte getStartAttack()
	{
//		return this.s.getFrameByteArray()[this.index + 1 + this.start_attack];
		return this.s.getFrameByteArray()[this.index + 1 + this.attack];
	}

	@Override
	public byte getAttack()
	{
		byte[] frame_byte_array = this.s.getFrameByteArray();
//		byte start_attack_size = frame_byte_array[this.index + 1];
		return frame_byte_array[this.index + 1 + frame_byte_array[this.index + 1]/* + 1*/ + this.attack];
	}

	@Override
	public byte getEndAttack()
	{
		byte[] frame_byte_array = this.s.getFrameByteArray();
		byte size = frame_byte_array[this.index + 1];
		return frame_byte_array[this.index + 1 + size + size];
	}

	@Override
	public byte getReload()
	{
		byte[] frame_byte_array = this.s.getFrameByteArray();
		byte size = frame_byte_array[this.index + 1];
		return frame_byte_array[this.index + 1 + size + size + 1];
	}
}
