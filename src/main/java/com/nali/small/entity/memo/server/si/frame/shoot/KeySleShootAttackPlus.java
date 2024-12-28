package com.nali.small.entity.memo.server.si.frame.shoot;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.da.IBothDaSe;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;

public class KeySleShootAttackPlus
<
	BD extends IBothDaE & IBothDaS & IBothDaSe,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends KeySleShoot<BD, E, I, S, MS>
{
	public byte attack;
//	start_attack,
//	size;

	public KeySleShootAttackPlus(S s, byte key_data_index)
	{
		super(s, key_data_index);
//		this.size = size;
	}

//	@Override
//	public boolean onUpdate()
//	{
////		int id = 10;
////		int start_id = 2;
//
////		if (serverentitiesmemory.server_how_attack)
////		{
////		id = 13;
////		start_id = 13;
//
//		short[] key_short_array = this.siekey.key_short_array;
//		short[] fix_key_short_array = this.s.getFixKeyShortArray();
//		byte[] key_data_byte_array = this.s.getKeyDataByteArray();
//
//		byte key_short_index = key_data_byte_array[this.key_data_index];
//		byte attack_fix_key_index = this.getAttackFixKeyIndex(key_data_byte_array);
//
////		byte frame = frame_byte_array[this.index];
////		byte attack = this.getAttack();
//
//
//		return super.onUpdate();
//	}

	@Override
	public void step(short[] key_short_array, short[] fix_key_short_array, byte key_short_index, byte attack_fix_key_index, byte end_attack_fix_key_index)
	{
//		if (key_short_array[key_short_index] == fix_key_short_array[attack_fix_key_index + 1])
//		{
////			start_id = 2;
////			id = 10;
//			key_short_array[key_short_index] = fix_key_short_array[2][0];
////			serverentitiesmemory.server_how_attack = false;
//		}
////		}

		if (key_short_array[key_short_index] == fix_key_short_array[attack_fix_key_index + 1])
		{
			byte[] key_data_byte_array = this.s.getKeyDataByteArray();
//			byte start_attack_size = frame_byte_array[this.index + 1];
//			byte attack_size = frame_byte_array[this.index + 1 + start_attack_size];
//			byte id = (byte)(this.s.i.getE().ticksExisted % start_attack_size);
//			this.start_attack = id;
//			this.attack = (byte)(this.s.i.getE().ticksExisted % attack_size);
			this.attack = (byte)(this.s.i.getE().ticksExisted % key_data_byte_array[this.key_data_index + 1]);
			byte new_attack_fix_key_index = this.getAttackFixKeyIndex(key_data_byte_array);
			key_short_array[key_short_index] = fix_key_short_array[new_attack_fix_key_index];
//			serverentitiesmemory.server_how_attack = (byte)(this.ticksExisted % 20) == 0;
		}
		else
		{
			super.step(key_short_array, fix_key_short_array, key_short_index, attack_fix_key_index, end_attack_fix_key_index);
		}
	}

	@Override
	public byte getStartAttackFixKeyIndex(byte[] key_data_byte_array)
	{
//		return this.s.getFrameByteArray()[this.index + 1 + this.start_attack];
		return key_data_byte_array[this.key_data_index + 1 + this.attack];
	}

	@Override
	public byte getAttackFixKeyIndex(byte[] key_data_byte_array)
	{
//		byte start_attack_size = frame_byte_array[this.index + 1];
		return key_data_byte_array[this.key_data_index + 1 + key_data_byte_array[this.key_data_index + 1]/* + 1*/ + this.attack];
	}

	@Override
	public byte getEndAttackFixKeyIndex(byte[] key_data_byte_array)
	{
		byte size = key_data_byte_array[this.key_data_index + 1];
		return key_data_byte_array[this.key_data_index + 1 + size + size];
	}

	@Override
	public byte getReloadFixKeyIndex(byte[] key_data_byte_array)
	{
		byte size = key_data_byte_array[this.key_data_index + 1];
		return key_data_byte_array[this.key_data_index + 1 + size + size + 1];
	}
}
