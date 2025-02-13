package com.nali.small.entity.memo.server.si.frame;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.da.IBothDaSe;
import com.nali.list.entity.si.SIEHeal;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;

public class KeySleHeal
<
	BD extends IBothDaE & IBothDaS & IBothDaSe,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends KeyS<BD, E, I, S, MS>
{
	public SIEHeal<BD, E, I, S, MS> sieheal;

//	public byte rg;

	public KeySleHeal(S s, byte key_data_index)
	{
		super(s, key_data_index);
//		rg = rg;

		this.sieheal = (SIEHeal<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEHeal.ID);
	}
//
//	@Override
//	public void init()
//	{
//		super.init();
//		this.sieheal = (SILeHeal<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeHeal.ID);
//	}

	@Override
	public boolean onUpdate()
	{
		if ((this.sieheal.flag & SIEHeal.B_ON) == SIEHeal.B_ON)
		{
			short[] key_short_array = this.siekey.key_short_array;
			short[] fix_key_short_array = this.s.getFixKeyShortArray();
			byte[] key_data_byte_array = this.s.getKeyDataByteArray();

			byte key_short_index = key_data_byte_array[this.key_data_index];
			byte fix_key_index0 = key_data_byte_array[this.key_data_index + 1];

			for (int heal_frame : this.sieheal.heal_frame_int_array)
			{
				if (key_short_array[key_short_index] == heal_frame)
				{
					this.sieheal.flag |= SIEHeal.B_ANIMATE_HEAL;
					break;
				}
			}

			if (key_short_array[key_short_index] < fix_key_short_array[fix_key_index0] || key_short_array[key_short_index] > fix_key_short_array[fix_key_index0 + 1])
			{
				key_short_array[key_short_index] = fix_key_short_array[fix_key_index0];
			}
			else if (key_short_array[key_short_index] == fix_key_short_array[fix_key_index0 + 1])
			{
				key_short_array[key_short_index] = fix_key_short_array[fix_key_index0];
			}
			else
			{
				++key_short_array[key_short_index];
			}

			this.siekey.sync_byte_arraylist.add(key_short_index);
			return true;

//			this.step = 1;
//
//			int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
//			byte[] frame_byte_array = this.s.getFrameByteArray();
//			byte frame = frame_byte_array[this.rg];
//			for (int heal_frame : this.sieheal.heal_frame_int_array)
//			{
//				if (this.siekey.frame_int_array[frame] == heal_frame)
//				{
//					this.sieheal.state |= 4;
//					break;
//				}
//			}
//
//			byte rg = frame_byte_array[this.rg + 1];
//			if (this.siekey.frame_int_array[frame] < frame_2d_int_array[rg][0] || this.siekey.frame_int_array[frame] > frame_2d_int_array[rg][1])
//			{
//				this.siekey.frame_int_array[frame] = frame_2d_int_array[rg][0];
//				this.step = 0;
//			}
//			else if (this.siekey.frame_int_array[frame] == frame_2d_int_array[rg][1])
//			{
//				this.siekey.frame_int_array[frame] = frame_2d_int_array[rg][0];
//				this.step = 0;
//			}
//
//			return true;
		}

		return false;
	}
}
