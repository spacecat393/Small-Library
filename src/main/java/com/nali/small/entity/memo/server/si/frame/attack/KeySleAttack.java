package com.nali.small.entity.memo.server.si.frame.attack;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.da.IBothDaSe;
import com.nali.list.entity.si.SILeAttack;
import com.nali.list.entity.si.SIEFindMove;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.frame.KeyS;

public class KeySleAttack
<
	BD extends IBothDaE & IBothDaS & IBothDaSe,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends KeyS<BD, E, I, S, MS>
{
	public SILeAttack<BD, E, I, S, MS> sileattack;
	public SIEFindMove<BD, E, I, S, MS> siefindmove;

	public KeySleAttack(S s, byte key_data_index)
	{
		super(s, key_data_index);

		this.sileattack = (SILeAttack<BD, E, I, S, MS>)this.s.ms.si_map.get(SILeAttack.ID);
		this.siefindmove = (SIEFindMove<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEFindMove.ID);
	}
//
//	@Override
//	public void init()
//	{
//		super.init();
//		this.sileattack = (SILeAttack<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeAttack.ID);
//		this.siefindmove = (SILeFindMove<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeFindMove.ID);
//	}

	@Override
	public boolean onUpdate()
	{
		//!frame
		return false;
//		if (this.step())
//		{
//			short[] key_short_array = this.siekey.key_short_array;
//			short[] fix_key_short_array = this.s.getFixKeyShortArray();
//			byte[] key_data_byte_array = this.s.getKeyDataByteArray();
//
//			byte key_short_index = key_data_byte_array[this.key_data_index];
//			byte fix_key_index = this.getFixKeyIndex(key_data_byte_array);
//
//			for (int attack_frame : this.sileattack.attack_frame_int_array)
//			{
//				if (key_short_array[key_short_index] == attack_frame)
//				{
//					this.sileattack.state |= SILeAttack.B_HIT;
//					break;
//				}
//			}
//
//			if (key_short_array[key_short_index] < fix_key_short_array[fix_key_index] || key_short_array[key_short_index] >= fix_key_short_array[fix_key_index + 1])
//			{
//				key_short_array[key_short_index] = fix_key_short_array[fix_key_index];
//			}
//			else
//			{
//				++key_short_array[key_short_index];
//			}
//
//			this.siekey.sync_byte_arraylist.add(key_short_index);
//			return true;
//		}
//
//		return false;
	}

	public boolean step()
	{
		return (this.sileattack.state & SILeAttack.B_PREPARE) == SILeAttack.B_PREPARE;
	}

	public byte getFixKeyIndex(byte[] key_data_byte_array)
	{
		return key_data_byte_array[this.key_data_index + 1];
	}
}
