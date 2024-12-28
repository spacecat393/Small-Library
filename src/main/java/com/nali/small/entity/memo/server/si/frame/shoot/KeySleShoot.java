package com.nali.small.entity.memo.server.si.frame.shoot;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.da.IBothDaSe;
import com.nali.list.entity.si.SILeAttack;
import com.nali.list.entity.si.SILeFindMove;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.frame.KeyS;

public class KeySleShoot
<
	BD extends IBothDaE & IBothDaS & IBothDaSe,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends KeyS<BD, E, I, S, MS>
{
//	public byte state;//hold_before_reload
	public SILeAttack<BD, E, I, S, MS> sileattack;
	public SILeFindMove<BD, E, I, S, MS> silefindmove;

	//mix
	public KeySleShoot(S s, byte key_data_index)
	{
		super(s, key_data_index);
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
		short[] key_short_array = this.siekey.key_short_array;
		short[] fix_key_short_array = this.s.getFixKeyShortArray();
		byte[] key_data_byte_array = this.s.getKeyDataByteArray();

		byte key_short_index = key_data_byte_array[this.key_data_index];

//		Nali.warn("s0 key_short_array[key_short_index] " + key_short_array[key_short_index]);
		if (this.sileattack.magic_point <= 0)
		{
//			Nali.warn("magic_point <= 0");
			byte start_attack_fix_key_index = this.getStartAttackFixKeyIndex(key_data_byte_array);
			byte attack_fix_key_index = this.getAttackFixKeyIndex(key_data_byte_array);
			byte end_attack_fix_key_index = this.getEndAttackFixKeyIndex(key_data_byte_array);

			this.silefindmove.endGoal();

			if (key_short_array[key_short_index] >= fix_key_short_array[start_attack_fix_key_index] && key_short_array[key_short_index] <= fix_key_short_array[start_attack_fix_key_index + 1])
			{
				if (key_short_array[key_short_index] == fix_key_short_array[start_attack_fix_key_index + 1])
				{
					key_short_array[key_short_index] = fix_key_short_array[end_attack_fix_key_index];
				}
				else
				{
					++key_short_array[key_short_index];
				}
				this.siekey.sync_byte_arraylist.add(key_short_index);
			}
			else if (key_short_array[key_short_index] >= fix_key_short_array[attack_fix_key_index] && key_short_array[key_short_index] <= fix_key_short_array[attack_fix_key_index + 1])
			{
				if (key_short_array[key_short_index] == fix_key_short_array[attack_fix_key_index + 1])
				{
					key_short_array[key_short_index] = fix_key_short_array[end_attack_fix_key_index];
				}
				else
				{
					++key_short_array[key_short_index];
				}
				this.siekey.sync_byte_arraylist.add(key_short_index);
			}
			else if (key_short_array[key_short_index] >= fix_key_short_array[end_attack_fix_key_index] && key_short_array[key_short_index] < fix_key_short_array[end_attack_fix_key_index + 1])
			{
				++key_short_array[key_short_index];
				this.siekey.sync_byte_arraylist.add(key_short_index);
			}
			else
			{
				byte reload_fix_key_index = this.getReloadFixKeyIndex(key_data_byte_array);

				if (key_short_array[key_short_index] < fix_key_short_array[reload_fix_key_index] || key_short_array[key_short_index] > fix_key_short_array[reload_fix_key_index + 1])
				{
					key_short_array[key_short_index] = fix_key_short_array[reload_fix_key_index];
					this.siekey.sync_byte_arraylist.add(key_short_index);
				}
				else if (key_short_array[key_short_index] >= fix_key_short_array[reload_fix_key_index] && key_short_array[key_short_index] <= fix_key_short_array[reload_fix_key_index + 1])
				{
					if (key_short_array[key_short_index] == fix_key_short_array[reload_fix_key_index + 1])
					{
						this.sileattack.magic_point = this.sileattack.max_magic_point;
					}
					else
					{
						++key_short_array[key_short_index];
						this.siekey.sync_byte_arraylist.add(key_short_index);
					}
				}
			}

			return true;
		}
		else if ((this.sileattack.flag & 2) == 2)
		{
//			Nali.warn("(this.sileattack.flag & 2) == 2");
			byte start_attack_fix_key_index = this.getStartAttackFixKeyIndex(key_data_byte_array);
			byte attack_fix_key_index = this.getAttackFixKeyIndex(key_data_byte_array);
			byte end_attack_fix_key_index = this.getEndAttackFixKeyIndex(key_data_byte_array);

			if (key_short_array[key_short_index] >= fix_key_short_array[attack_fix_key_index] && key_short_array[key_short_index] <= fix_key_short_array[attack_fix_key_index + 1])
			{
				for (int attack_frame : this.sileattack.attack_frame_int_array)
				{
					if (key_short_array[key_short_index] == attack_frame)
					{
						this.sileattack.magic_point -= 1;
						this.sileattack.flag |= 4;
						break;
					}
				}

				this.step(key_short_array, fix_key_short_array, key_short_index, attack_fix_key_index, end_attack_fix_key_index);
			}
			else if (key_short_array[key_short_index] >= fix_key_short_array[start_attack_fix_key_index] && key_short_array[key_short_index] <= fix_key_short_array[start_attack_fix_key_index + 1])
			{
				if (key_short_array[key_short_index] == fix_key_short_array[start_attack_fix_key_index + 1])
				{
					key_short_array[key_short_index] = fix_key_short_array[attack_fix_key_index];
				}
				else
				{
					++key_short_array[key_short_index];
				}
			}
			else if (key_short_array[key_short_index] >= fix_key_short_array[end_attack_fix_key_index] && key_short_array[key_short_index] < fix_key_short_array[end_attack_fix_key_index + 1])
			{
				++key_short_array[key_short_index];
			}
			else if (key_short_array[key_short_index] < fix_key_short_array[start_attack_fix_key_index] || key_short_array[key_short_index] > fix_key_short_array[start_attack_fix_key_index + 1])
			{
				key_short_array[key_short_index] = fix_key_short_array[start_attack_fix_key_index];
			}
//			else
//			{
//				Nali.error("FALSE");
//			}

			this.siekey.sync_byte_arraylist.add(key_short_index);
			return true;
		}
		else
		{
//			return this.checkShoot(false);
//			short[] key_short_array = this.siekey.key_short_array;
//			short[] fix_key_short_array = this.s.getFixKeyShortArray();
//			byte[] key_data_byte_array = this.s.getKeyDataByteArray();
//
//			byte key_short_index = key_data_byte_array[this.key_data_index];
			byte start_attack_fix_key_index = this.getStartAttackFixKeyIndex(key_data_byte_array);
			byte attack_fix_key_index = this.getAttackFixKeyIndex(key_data_byte_array);
			byte end_attack_fix_key_index = this.getEndAttackFixKeyIndex(key_data_byte_array);

			if (key_short_array[key_short_index] >= fix_key_short_array[start_attack_fix_key_index] && key_short_array[key_short_index] <= fix_key_short_array[start_attack_fix_key_index + 1])
			{
				this.silefindmove.endGoal();
				if (key_short_array[key_short_index] == fix_key_short_array[start_attack_fix_key_index + 1])
				{
					key_short_array[key_short_index] = fix_key_short_array[end_attack_fix_key_index];
				}
				else
				{
					++key_short_array[key_short_index];
				}

				this.siekey.sync_byte_arraylist.add(key_short_index);
				return true;
			}
			else if (key_short_array[key_short_index] >= fix_key_short_array[attack_fix_key_index] && key_short_array[key_short_index] <= fix_key_short_array[attack_fix_key_index + 1])
			{
				this.silefindmove.endGoal();
				if (key_short_array[key_short_index] == fix_key_short_array[attack_fix_key_index + 1])
				{
					key_short_array[key_short_index] = fix_key_short_array[end_attack_fix_key_index];
				}
				else
				{
					++key_short_array[key_short_index];
				}

				this.siekey.sync_byte_arraylist.add(key_short_index);
				return true;
			}
			else if (key_short_array[key_short_index] >= fix_key_short_array[end_attack_fix_key_index] && key_short_array[key_short_index] < fix_key_short_array[end_attack_fix_key_index + 1])
			{
				this.silefindmove.endGoal();
				++key_short_array[key_short_index];
				this.siekey.sync_byte_arraylist.add(key_short_index);
				return true;
			}
//			else if (!try_reload && key_short_array[key_short_index] >= fix_key_short_array[end_attack_fix_key_index] && key_short_array[key_short_index] < fix_key_short_array[end_attack_fix_key_index + 1])
//			{
//				this.silefindmove.endGoal();
//			}
		}
//		Nali.warn("e0 key_short_array[key_short_index] " + key_short_array[key_short_index]);

//		int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
//		byte[] frame_byte_array = this.s.getFrameByteArray();
//		byte frame = frame_byte_array[this.index];
//		if (this.sileattack.magic_point <= 0)
//		{
//			this.step = 1;
////			if (this.checkShoot(true))
////			{
////				return true;
////			}
////			else
//			if (!this.checkShoot(true))
//			{
//				byte index3 = this.getReload();
//				this.silefindmove.endGoal();
//
//				//if (time == time_set)
//				//key_index_byte_array[i] = key_id
//				//time[i] ++ --
//				if (this.siekey.frame_int_array[frame] < frame_2d_int_array[index3][0] || this.siekey.frame_int_array[frame] > frame_2d_int_array[index3][1])
//				{
//					this.step = 0;
//					this.siekey.frame_int_array[frame] = frame_2d_int_array[index3][0];
////					return true;
//				}
//				else if (this.siekey.frame_int_array[frame] >= frame_2d_int_array[index3][0] && this.siekey.frame_int_array[frame] <= frame_2d_int_array[index3][1])
//				{
//					if (this.siekey.frame_int_array[frame] == frame_2d_int_array[index3][1])
//					{
//						this.step = 0;
//						this.sileattack.magic_point = this.sileattack.max_magic_point;
////						return true;
//					}
//				}
////				return true;
//			}
//			return true;
//		}
//		else if ((this.sileattack.flag & 2) == 2)
//		{
////			byte index = frame_byte_array[this.index + 1];
//			byte index = this.getStartAttack();
////			byte index1 = frame_byte_array[this.index + 2];
//			byte index1 = this.getAttack();
//			if (this.siekey.frame_int_array[frame] >= frame_2d_int_array[index1][0] && this.siekey.frame_int_array[frame] <= frame_2d_int_array[index1][1])
//			{
//				this.step();
//
//				for (int attack_frame : this.sileattack.attack_frame_int_array)
//				{
//					if (this.siekey.frame_int_array[frame] == attack_frame)
//					{
//						this.sileattack.magic_point -= 1;
//						this.sileattack.flag |= 4;
//						break;
//					}
//				}
//				return true;
//			}
//			else if (this.siekey.frame_int_array[frame] >= frame_2d_int_array[index][0] && this.siekey.frame_int_array[frame] <= frame_2d_int_array[index][1])
//			{
//				if (this.siekey.frame_int_array[frame] == frame_2d_int_array[index][1])
//				{
//					this.siekey.frame_int_array[frame] = frame_2d_int_array[index1][1];
//					this.step = 0;
//					return true;
//				}
//
//				this.step = 1;
//				return true;
//			}
//			else if (this.siekey.frame_int_array[frame] < frame_2d_int_array[index][0] || this.siekey.frame_int_array[frame] > frame_2d_int_array[index][1])
//			{
//				this.siekey.frame_int_array[frame] = frame_2d_int_array[index][0];
//				this.step = 0;
//				return true;
//			}
//		}
//		else
//		{
//			this.step = 1;
//			return this.checkShoot(false);
//		}

		return false;
	}

//	public boolean checkShoot(boolean try_reload)
//	{
//		short[] key_short_array = this.siekey.key_short_array;
//		short[] fix_key_short_array = this.s.getFixKeyShortArray();
//		byte[] key_data_byte_array = this.s.getKeyDataByteArray();
//
//		byte key_short_index = key_data_byte_array[this.key_data_index];
//		byte start_attack_fix_key_index = this.getStartAttackFixKeyIndex(key_data_byte_array);
//		byte attack_fix_key_index = this.getAttackFixKeyIndex(key_data_byte_array);
//		byte end_attack_fix_key_index = this.getEndAttackFixKeyIndex(key_data_byte_array);
//
//		if (key_short_array[key_short_index] >= fix_key_short_array[start_attack_fix_key_index] && key_short_array[key_short_index] <= fix_key_short_array[start_attack_fix_key_index + 1])
//		{
//			this.silefindmove.endGoal();
//			if (key_short_array[key_short_index] == fix_key_short_array[start_attack_fix_key_index + 1])
//			{
//				key_short_array[key_short_index] = fix_key_short_array[end_attack_fix_key_index];
//			}
//			return true;
//		}
//		else if (key_short_array[key_short_index] >= fix_key_short_array[attack_fix_key_index] && key_short_array[key_short_index] <= fix_key_short_array[attack_fix_key_index + 1])
//		{
//			this.silefindmove.endGoal();
//			if (key_short_array[key_short_index] == fix_key_short_array[attack_fix_key_index + 1])
//			{
//				key_short_array[key_short_index] = fix_key_short_array[end_attack_fix_key_index];
//			}
//			return true;
//		}
//		else if (!try_reload && key_short_array[key_short_index] >= fix_key_short_array[end_attack_fix_key_index] && key_short_array[key_short_index] < fix_key_short_array[end_attack_fix_key_index + 1])
//		{
//			this.silefindmove.endGoal();
//			return true;
//		}
//		else
//		{
//			return false;
//		}
//	}

	public void step(short[] key_short_array, short[] fix_key_short_array, byte key_short_index, byte attack_fix_key_index, byte end_attack_fix_key_index)
	{
		if (key_short_array[key_short_index] >= fix_key_short_array[attack_fix_key_index] && key_short_array[key_short_index] <= fix_key_short_array[attack_fix_key_index + 1])
		{
			if (key_short_array[key_short_index] == fix_key_short_array[attack_fix_key_index + 1])
			{
				key_short_array[key_short_index] = fix_key_short_array[end_attack_fix_key_index];
			}
			else
			{
				++key_short_array[key_short_index];
			}
		}
	}

	public byte getStartAttackFixKeyIndex(byte[] key_data_byte_array)
	{
		return key_data_byte_array[this.key_data_index + 1];
	}

	public byte getAttackFixKeyIndex(byte[] key_data_byte_array)
	{
		return key_data_byte_array[this.key_data_index + 2];
	}

	public byte getEndAttackFixKeyIndex(byte[] key_data_byte_array)
	{
		return key_data_byte_array[this.key_data_index + 3];
	}

	public byte getReloadFixKeyIndex(byte[] key_data_byte_array)
	{
		return key_data_byte_array[this.key_data_index + 4];
	}

//	public boolean checkShoot(boolean try_reload)
//	{
//		int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
//		byte[] frame_byte_array = this.s.getFrameByteArray();
//		byte frame = frame_byte_array[this.index];
////		byte index = frame_byte_array[this.index + 1];
//		byte index = this.getStartAttack();
////		byte index1 = frame_byte_array[this.index + 2];
//		byte index1 = this.getAttack();
////		byte index2 = frame_byte_array[this.index + 3];
//		byte index2 = this.getEndAttack();
//		if (this.siekey.frame_int_array[frame] >= frame_2d_int_array[index][0] && this.siekey.frame_int_array[frame] <= frame_2d_int_array[index][1])
//		{
//			this.silefindmove.endGoal();
//			if (this.siekey.frame_int_array[frame] == frame_2d_int_array[index][1])
//			{
//				this.siekey.frame_int_array[frame] = frame_2d_int_array[index2][0];
//				this.step = 0;
//			}
//			return true;
//		}
//		else if (this.siekey.frame_int_array[frame] >= frame_2d_int_array[index1][0] && this.siekey.frame_int_array[frame] <= frame_2d_int_array[index1][1])
//		{
//			this.silefindmove.endGoal();
//			if (this.siekey.frame_int_array[frame] == frame_2d_int_array[index1][1])
//			{
//				this.siekey.frame_int_array[frame] = frame_2d_int_array[index2][0];
//				this.step = 0;
//			}
//			return true;
//		}
//		else if (!try_reload && this.siekey.frame_int_array[frame] >= frame_2d_int_array[index2][0] && this.siekey.frame_int_array[frame] < frame_2d_int_array[index2][1])
//		{
//			this.silefindmove.endGoal();
//			return true;
//		}
//		else
//		{
//			return false;
//		}
//	}
//
//	public void step()
//	{
//		int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
//		byte[] frame_byte_array = this.s.getFrameByteArray();
//		byte frame = frame_byte_array[this.index];
////		byte index1 = frame_byte_array[this.index + 2];
//		byte index1 = this.getAttack();
//		this.step = 1;
//		if (this.siekey.frame_int_array[frame] >= frame_2d_int_array[index1][0] && this.siekey.frame_int_array[frame] <= frame_2d_int_array[index1][1])
//		{
//			if (this.siekey.frame_int_array[frame] == frame_2d_int_array[index1][1])
//			{
//				this.siekey.frame_int_array[frame] = frame_2d_int_array[index1][0];
//				this.step = 0;
//			}
//		}
//	}
//
//	public byte getStartAttack()
//	{
//		return this.s.getFrameByteArray()[this.index + 1];
//	}
//
//	public byte getAttack()
//	{
//		return this.s.getFrameByteArray()[this.index + 2];
//	}
//
//	public byte getEndAttack()
//	{
//		return this.s.getFrameByteArray()[this.index + 3];
//	}
//
//	public byte getReload()
//	{
//		return this.s.getFrameByteArray()[this.index + 4];
//	}
}
